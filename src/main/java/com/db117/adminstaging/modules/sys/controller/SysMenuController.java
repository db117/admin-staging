package com.db117.adminstaging.modules.sys.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.db117.adminstaging.common.Result;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    /**
//     * 进入菜单列表页
//     */
//    @GetMapping(value = "list")
//    public String list() {
//        return "sys/menu/list";
//    }
//
//    /**
//     * 获取菜单树
//     */
//    @GetMapping(value = "tree")
//    @ResponseBody
//    public List<ZtreeVO> tree() {
//        return sysMenuService.getAllTree();
//    }
//
//    /**
//     * 进入添加页面
//     */
//    @GetMapping(value = "add")
//    public String add(Model model) {
//        List<SysMenu> dirMenu = sysMenuService.findAllDir();
//        model.addAttribute("dirMenu", dirMenu);
//        return "sys/menu/add";
//    }
//
//    /**
//     * 根据父级id获取下级列表
//     */
//    @PostMapping(value = "findByPid")
//    @ResponseBody
//    public List<SysMenu> findByPid(String pid) {
//        return sysMenuService.findByPid(pid);
//    }
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
//
//    /**
//     * 进入表单界面
//     */
//    @GetMapping(value = "form")
//    public String form(String id, Model model) {
//        SysMenu sysMenu = sysMenuService.selectById(id);
//        model.addAttribute("sysMenu", sysMenu);
//
//        //如果是菜单
//        String type = sysMenu.getType();
//        if ("1".equals(type)) {
//            List<SysMenu> dirMenuList = sysMenuService.findAllDir();
//            model.addAttribute("dirMenuList", dirMenuList);
//        }
//
//        //如果是按钮
//        if ("2".equals(type)) {
//            String ppid = sysMenu.getParentIds();
////            String[] strings=ppid
////            List<SysMenu> menuList=sysMenuService
//        }
//        return "sys/menu/form";
//    }
    /**
     * 导航菜单
     */
    @RequestMapping("/nav")
    public Map nav(){
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        return (Map) MapUtil.newHashMap().put("menuList", menuList);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    public List<SysMenu> list(){
        List<SysMenu> menuList = sysMenuService.selectList(null);
        for(SysMenu sysMenuEntity : menuList){
            SysMenu parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
            if(parentMenuEntity != null){
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

//    /**
//     * 菜单信息
//     */
//    @RequestMapping("/info/{menuId}")
//    @RequiresPermissions("sys:menu:info")
//    public R info(@PathVariable("menuId") Long menuId){
//        SysMenu menu = sysMenuService.selectById(menuId);
//        return R.ok().put("menu", menu);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("sys:menu:save")
//    public R save(@RequestBody SysMenu menu){
//        //数据校验
//        verifyForm(menu);
//
//        sysMenuService.insert(menu);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("sys:menu:update")
//    public R update(@RequestBody SysMenu menu){
//        //数据校验
//        verifyForm(menu);
//
//        sysMenuService.updateById(menu);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("sys:menu:delete")
//    public R delete(long menuId){
//        if(menuId <= 31){
//            return R.error("系统菜单，不能删除");
//        }
//
//        //判断是否有子菜单或按钮
//        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
//        if(menuList.size() > 0){
//            return R.error("请先删除子菜单或按钮");
//        }
//
//        sysMenuService.delete(menuId);
//
//        return R.ok();
//    }
//
//    /**
//     * 验证参数是否正确
//     */
//    private void verifyForm(SysMenu menu){
//        if(StringUtils.isBlank(menu.getName())){
//            throw new RRException("菜单名称不能为空");
//        }
//
//        if(menu.getParentId() == null){
//            throw new RRException("上级菜单不能为空");
//        }
//
//        //菜单
//        if(menu.getType() == Constant.MenuType.MENU.getValue()){
//            if(StringUtils.isBlank(menu.getUrl())){
//                throw new RRException("菜单URL不能为空");
//            }
//        }
//
//        //上级菜单类型
//        int parentType = Constant.MenuType.CATALOG.getValue();
//        if(menu.getParentId() != 0){
//            SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
//            parentType = parentMenu.getType();
//        }
//
//        //目录、菜单
//        if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
//                menu.getType() == Constant.MenuType.MENU.getValue()){
//            if(parentType != Constant.MenuType.CATALOG.getValue()){
//                throw new RRException("上级菜单只能为目录类型");
//            }
//            return ;
//        }
//
//        //按钮
//        if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
//            if(parentType != Constant.MenuType.MENU.getValue()){
//                throw new RRException("上级菜单只能为菜单类型");
//            }
//            return ;
//        }
//    }
}

