package com.db117.adminstaging.modules.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.db117.adminstaging.common.Result;
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

    /**
     * 根据父级id获取下级列表
     */
    @PostMapping(value = "findByPid")
    @ResponseBody
    public List<SysMenu> findByPid(String pid) {
        return sysMenuService.findByPid(pid);
    }


    /**
     * 进行添加
     *
     * @param sysMenu 菜单类
     * @param dirId   目录id
     * @param menuId  菜单id
     */
    @PostMapping(value = "add")
    @ResponseBody
    public Result add(SysMenu sysMenu, String dirId, String menuId) {
        if (StrUtil.isBlank(sysMenu.getName())) {
            return Result.getFailure("名称不能为空");
        }

        String type = sysMenu.getType();
        if ("0".equals(type)) {
            sysMenu.setParentId("1");
            sysMenu.setParentIds("0,1");
            sysMenuService.insertAllColumn(sysMenu);
            return Result.getSuccess("添加成功");
        }

        if (StrUtil.isBlank(sysMenu.getHref())) {
            return Result.getFailure("链接不能为空");
        }
        if ("1".equals(type)) {
            sysMenu.setParentId(dirId);
            sysMenu.setParentIds("0,1," + dirId);
        }

        if ("2".equals(type)) {
            sysMenu.setParentId(menuId);
            sysMenu.setParentIds("0,1," + dirId + "," + menuId);
        }
        sysMenuService.insertAllColumn(sysMenu);
        return Result.getSuccess("添加成功");
    }

    /**
     * 进入表单界面
     */
    @GetMapping(value = "form")
    public String form(String id, Model model) {
        SysMenu sysMenu = sysMenuService.selectById(id);
        model.addAttribute("sysMenu", sysMenu);

        //如果是菜单
        String type = sysMenu.getType();
        if ("1".equals(type)) {
            List<SysMenu> dirMenuList = sysMenuService.findAllDir();
            model.addAttribute("dirMenuList", dirMenuList);
        }

        //如果是按钮
        if ("2".equals(type)) {
            String ppid = sysMenu.getParentIds();
//            String[] strings=ppid
//            List<SysMenu> menuList=sysMenuService
        }
        return "sys/menu/form";
    }
}

