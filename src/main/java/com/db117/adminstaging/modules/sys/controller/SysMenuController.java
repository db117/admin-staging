package com.db117.adminstaging.modules.sys.controller;


import com.db117.adminstaging.common.base.BaseController;
import com.db117.adminstaging.common.vo.ZtreeVO;
import com.db117.adminstaging.modules.sys.entity.SysMenu;
import com.db117.adminstaging.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 进入菜单列表页
     */
    @GetMapping(value = "list")
    public String list() {
        return "sys/menu/list";
    }

    /**
     * 获取菜单树
     */
    @GetMapping(value = "tree")
    @ResponseBody
    public List<ZtreeVO> tree() {
        return sysMenuService.getAllTree();
    }

    /**
     * 进入添加页面
     */
    @GetMapping(value = "add")
    public String add(Model model) {
        List<SysMenu> dirMenu = sysMenuService.findAllDir();
        model.addAttribute("dirMenu", dirMenu);
        return "sys/menu/add";
    }

    @PostMapping(value = "findByPid")
    @ResponseBody
    public List<SysMenu> findByPid(String pid) {
        return sysMenuService.findByPid(pid);
    }
}

