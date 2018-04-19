package com.db117.adminstaging.modules.sys.util;

import com.db117.adminstaging.config.security.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 权限工具类
 *
 * @author 大兵
 * @date 2018-04-19 15:44
 **/
public class SecurityUtil {

    /**
     * 获取当前用户
     */
    public static SecurityUser currentUser() {
        return (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
