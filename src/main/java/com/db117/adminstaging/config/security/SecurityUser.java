package com.db117.adminstaging.config.security;

import com.db117.adminstaging.modules.sys.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 权限用户类
 *
 * @author 大兵
 * @date 2018-04-18 23:12
 **/
public class SecurityUser extends SysUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    public SecurityUser(SysUser suser) {
        if (suser != null) {
            this.setId(suser.getId());
            this.setName(suser.getName())
                    .setEmail(suser.getEmail())
                    .setPassword(suser.getPassword())
                    .setLoginName(suser.getLoginName())
                    .setCompanyId(suser.getCompanyId())
                    .setLoginDate(suser.getLoginDate())
                    .setLoginFlag(suser.getLoginFlag())
                    .setLoginIp(suser.getLoginIp())
                    .setMobile(suser.getMobile())
                    .setNo(suser.getNo())
                    .setOfficeId(suser.getOfficeId())
                    .setPhoto(suser.getPhoto())
                    .setSex(suser.getSex())
                    .setUserType(suser.getUserType())
                    .setUpdateDate(suser.getUpdateDate());
            this.setUpdateBy(suser.getUpdateBy());
            this.setRemarks(suser.getRemarks());
            this.setCreateBy(suser.getCreateBy());
            this.setCreateDate(suser.getCreateDate());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //todo 获取用户权限列表
//        Set<SysRole> userRoles = this.getSysRoles();

//        if(userRoles != null)
//        {
//            for (SysRole role : userRoles) {
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
//                authorities.add(authority);
//            }
//        }
        if ("test02".equals(getLoginName())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        authorities.add(new SimpleGrantedAuthority("user"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLoginName();
    }

    /**
     * 帐号是否不过期，false则验证不通过
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 帐号是否不锁定，false则验证不通过
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证是否不过期，false则验证不通过
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 该帐号是否启用，false则验证不通过
     */
    @Override
    public boolean isEnabled() {
        return "0".equals(getLoginFlag());
    }
}
