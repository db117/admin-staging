package com.db117.adminstaging.modules.sys.controller;


import com.alibaba.fastjson.JSON;
import com.db117.adminstaging.common.base.BaseController;
import com.db117.adminstaging.common.vo.ZtreeVO;
import com.db117.adminstaging.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping(value = "list")
    public String list(Model model) {

        model.addAttribute("tree", JSON.toJSONString(sysMenuService.getAllTree()));
        return "sys/menu/list";
    }

    @GetMapping(value = "tree")
    @ResponseBody
    public List<ZtreeVO> tree() {
        return sysMenuService.getAllTree();
    }

    @GetMapping(value = "add")
    public String add() {
        return "sys/menu/add";
    }
}

