package com.db117.adminstaging.config.security;

import com.db117.adminstaging.modules.sys.entity.SysUser;
import com.db117.adminstaging.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 当用户通过验证后进行一系列操作
 *
 * @author 大兵
 * @date 2018-04-18 23:09
 **/
@Slf4j
public class LoginSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private SysUserService sysUserService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        //获得授权后可得到用户信息   可使用SUserService进行数据库操作
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        // 把登录时间写入数据库
        sysUser.setLoginIp(getIpAddress(request)).setLoginDate(new Date());
        sysUserService.updateById(sysUser);


        //输出登录提示信息
        log.info(sysUser.getName() + " 登录" + "//n IP :" + getIpAddress(request));

        super.onAuthenticationSuccess(request, response, authentication);
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
