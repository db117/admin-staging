package com.db117.adminstaging.config.security;

import com.db117.adminstaging.modules.sys.dao.SysUserDao;
import com.db117.adminstaging.modules.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义用户服务类
 * @author 大兵
 * @date 2018-04-18 16:38
 **/
@Component
public class CustomUserService implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 加载用户,根据用户名查找用户信息
     * @param s 用户名
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser= null;
        try {
            sysUser = sysUserDao.selectOne(new SysUser().setLoginName(s));
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("用户没有找到!!!");
        }
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户没有找到!!!");
        }

        //todo 用户权限
        return new SecurityUser(sysUser);
    }
}
