package com.db117.adminstaging.modules.sys.controller;

import cn.hutool.core.util.NetUtil;
import com.db117.adminstaging.config.shiro.ShiroUtils;
import com.db117.adminstaging.modules.sys.entity.SysUser;
import com.db117.adminstaging.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 大兵
 * @date 2018-04-15 18:30
 **/
@Controller
@Slf4j
public class LoginController {
    @Autowired
    private SysUserService sysUserService;


    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    /**
     * 进行登录
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "doLogin")
    public String doLogin(String username, String password, Model model, HttpSession session) {
        log.info("帐号密码:" + username + "," + password);

        Subject subject = ShiroUtils.getSubject();
        SysUser sysUser = ShiroUtils.getUserEntity();
        //如果已经登录
        if (sysUser != null) {
            return "redirect:index";
        }

        //进行登录
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            model.addAttribute("error", "帐号和密码错误!!!");
            return "login";
        }

        //更新登录信息
        sysUser = ShiroUtils.getUserEntity();
        sysUser.setLoginIp(NetUtil.getLocalhostStr());
        sysUser.setLoginDate(new Date());
        sysUserService.updateById(sysUser);

        return "redirect:index";
    }
}
