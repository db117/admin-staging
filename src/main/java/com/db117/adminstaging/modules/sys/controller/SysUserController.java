package com.db117.adminstaging.modules.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.db117.adminstaging.common.Result;
import com.db117.adminstaging.common.base.BaseController;
import com.db117.adminstaging.config.security.SecurityUser;
import com.db117.adminstaging.modules.sys.entity.SysUser;
import com.db117.adminstaging.modules.sys.service.SysUserService;
import com.db117.adminstaging.modules.sys.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.db117.adminstaging.config.shiro.ShiroUtils;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
@Controller
@RequestMapping("/sysUser")
@Slf4j
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 进入用户详情页面
     */
    @RequestMapping("info")
    public String info(Model model) {
        SecurityUser sysUser = SecurityUtil.currentUser();
        model.addAttribute("user", sysUser);
        return "sys/user/info";
    }

    /**
     * 进行保存用户
     */
    @PostMapping(value = "save")
    @ResponseBody
    public Result save(SysUser sysUser) {
        if (sysUserService.updateById(sysUser)) {
            return Result.getSuccess("修改成功");
        }
        return Result.getFailure("修改失败");
    }

    /**
     * 进入添加用户页面
     */
    @GetMapping(value = "add")
    public String add() {
        return "sys/user/add";
    }

    /**
     * 进行添加操作
     */
    @PostMapping(value = "add")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result add(SysUser sysUser) {
        if (!StrUtil.isAllBlank(sysUser.getName(), sysUser.getPassword(), sysUser.getLoginName())) {
            try {
                //密码加密
                String pass = new BCryptPasswordEncoder().encode(sysUser.getPassword());
                sysUser.setLoginFlag("0").setPassword(pass);

                //todo 添加权限和公司


                sysUserService.insertAllColumn(sysUser);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.getFailure("添加失败");
            }
            return Result.getSuccess("添加成功");
        }
        return Result.getFailure("添加失败");
    }

    /**
     * 进入列表页面
     */
    @GetMapping(value = "list")
    public String list() {
        return "sys/user/list";
    }

    /**
     * 列表数据查询接口
     */
    @PostMapping(value = "list")
    @ResponseBody
    public Result list(SysUser sysUser, Page<SysUser> page) {
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        ew.like("name", sysUser.getName());
        return Result.getPage(sysUserService.selectPage(page, ew));
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public Result del(SysUser sysUser) {
        if (sysUserService.deleteById(sysUser.getId())) {
            return Result.getSuccess("删除成功!");
        }
        return Result.getFailure("删除失败!");
    }

    @RequestMapping(value = "edit")
    public String edit(SysUser sysUser, Model model) {
        sysUser = sysUserService.selectById(sysUser.getId());
        if (sysUser == null) {
            model.addAttribute("sysUser", new SysUser());
            return "sys/user/edit";
        }
        model.addAttribute("sysUser", sysUser);
        return "sys/user/edit";
    }
}

