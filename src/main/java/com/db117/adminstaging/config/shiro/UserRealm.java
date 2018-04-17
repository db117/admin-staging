package com.db117.adminstaging.config.shiro;


import cn.hutool.core.util.StrUtil;
import com.db117.adminstaging.modules.sys.dao.SysMenuDao;
import com.db117.adminstaging.modules.sys.dao.SysUserDao;
import com.db117.adminstaging.modules.sys.entity.SysMenu;
import com.db117.adminstaging.modules.sys.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 认证
 *
 * @author chenshun
 */
@Component
public class UserRealm extends AuthorizingRealm {
    private final SysUserDao sysUserDao;
    private final SysMenuDao sysMenuDao;

    @Autowired
    public UserRealm(SysUserDao sysUserDao, SysMenuDao sysMenuDao) {
        this.sysUserDao = sysUserDao;
        this.sysMenuDao = sysMenuDao;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        String userId = user.getId();

        List<String> permsList;

        //系统管理员，拥有最高权限
        if ("1".equalsIgnoreCase(userId)) {
            List<SysMenu> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenu menu : menuList) {
                permsList.add(menu.getPermission());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StrUtil.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        //查询用户信息
        SysUser user = null;
        if (!"".equals(token.getUsername())) {
            user = sysUserDao.selectOne(new SysUser().setLoginName(token.getUsername()));
        }

        //账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        if (!"1".equalsIgnoreCase(user.getLoginFlag())) {
            throw new LockedAccountException("帐号被锁定");
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.HASH_ALGORITHM_NAME);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
