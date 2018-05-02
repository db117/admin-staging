package com.db117.adminstaging.modules.sys.controller;


import com.db117.adminstaging.common.util.Result;
import com.db117.adminstaging.common.base.BaseController;
import com.db117.adminstaging.common.vo.ZtreeVO;
import com.db117.adminstaging.modules.sys.entity.SysMenu;
import com.db117.adminstaging.modules.sys.service.SysMenuService;
import com.db117.adminstaging.modules.sys.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    /**
//     * 进入添加页面
//     */
//    @GetMapping(value = "add")
//    public String add(Model model) {
//        List<SysMenu> dirMenu = sysMenuService.findAllDir();
//        model.addAttribute("dirMenu", dirMenu);
//        return "sys/menu/add";
//    }

    /**
     * 根据父级id获取下级列表
     */
    @PostMapping(value = "findByPid")
    @ResponseBody
    public List<SysMenu> findByPid(String pid) {
        return sysMenuService.queryListParentId(pid);
    }
//
//
//    /**
//     * 进行添加
//     *
//     * @param sysMenu 菜单类
//     * @param dirId   目录id
//     * @param menuId  菜单id
//     */
//    @PostMapping(value = "add")
//    @ResponseBody
//    public Result add(SysMenu sysMenu, String dirId, String menuId) {
//        if (StrUtil.isBlank(sysMenu.getName())) {
//            return Result.getFailure("名称不能为空");
//        }
//
//        String type = sysMenu.getType();
//        if ("0".equals(type)) {
//            sysMenu.setParentId("1");
//            sysMenu.setParentIds("0,1");
//            sysMenuService.insertAllColumn(sysMenu);
//            return Result.getSuccess("添加成功");
//        }
//
//        if (StrUtil.isBlank(sysMenu.getHref())) {
//            return Result.getFailure("链接不能为空");
//        }
//        if ("1".equals(type)) {
//            sysMenu.setParentId(dirId);
//            sysMenu.setParentIds("0,1," + dirId);
//        }
//
//        if ("2".equals(type)) {
//            sysMenu.setParentId(menuId);
//            sysMenu.setParentIds("0,1," + dirId + "," + menuId);
//        }
//        sysMenuService.insertAllColumn(sysMenu);
//        return Result.getSuccess("添加成功");
//    }

    /**
     * 进入表单界面
     */
    @GetMapping(value = "form")
    public String form(String id, Model model) {
        SysMenu sysMenu = sysMenuService.selectById(id);
        model.addAttribute("sysMenu", sysMenu);

        //如果是菜单
        int type = sysMenu.getType();
        if (1 == type) {
            List<SysMenu> dirMenuList = sysMenuService.selectList(null);
            model.addAttribute("dirMenuList", dirMenuList);
        }

        //如果是按钮
        if (1 == type) {
            String ppid = sysMenu.getParentIds();
//            String[] strings=ppid
//            List<SysMenu> menuList=sysMenuService
        }
        return "sys/menu/form";
    }

    /**
     * 导航菜单
     */
    @RequestMapping("/nav")
    @ResponseBody
    public Result nav() {
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
//        return (Map) MapUtil.newHashMap().put("menuList", menuList);
        return Result.getData(menuList);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/allList")
    @ResponseBody
    public List<SysMenu> allList() {
        List<SysMenu> menuList = sysMenuService.selectList(null);
        for (SysMenu sysMenuEntity : menuList) {
            SysMenu parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
            if (parentMenuEntity != null) {
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }

//    /**
//     * 选择菜单(添加、修改菜单)
//     */
//    @RequestMapping("/select")
//    @RequiresPermissions("sys:menu:select")
//    public R select(){
//        //查询列表数据
//        List<SysMenu> menuList = sysMenuService.queryNotButtonList();
//
//        //添加顶级菜单
//        SysMenu root = new SysMenu();
//        root.setId("0");
//        root.setName("一级菜单");
//        root.setParentId("-1");
//        root.setOpen(true);
//        menuList.add(root);
//
//        return R.ok().put("menuList", menuList);
//    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    public Result info(@PathVariable("menuId") String menuId) {
        SysMenu menu = sysMenuService.selectById(menuId);
        return Result.getData(menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.insertAllColumn(menu);

        return Result.getSuccess();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return Result.getSuccess();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(String menuId) {
        if ("0".equals(menuId)) {
            return Result.getFailure("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return Result.getFailure("请先删除子菜单或按钮");
        }

        sysMenuService.delete(menuId);

        return Result.getSuccess();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RuntimeException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RuntimeException("上级菜单不能为空");
        }

        //菜单
        if (Constant.MenuType.MENU.getValue() == menu.getType()) {
            if (StringUtils.isBlank(menu.getHref())) {
                throw new RuntimeException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (!"".equals(menu.getParentId())) {
            SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new RuntimeException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new RuntimeException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}

