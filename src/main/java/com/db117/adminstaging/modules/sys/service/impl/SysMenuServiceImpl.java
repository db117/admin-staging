package com.db117.adminstaging.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.db117.adminstaging.common.base.BaseServiceImpl;
import com.db117.adminstaging.modules.sys.dao.SysMenuDao;
import com.db117.adminstaging.modules.sys.entity.SysMenu;
import com.db117.adminstaging.modules.sys.entity.SysRoleMenu;
import com.db117.adminstaging.modules.sys.service.SysMenuService;
import com.db117.adminstaging.modules.sys.service.SysRoleMenuService;
import com.db117.adminstaging.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserService sysUserService;


    @Override
    public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu menu : menuList){
            if(menuIdList.contains(menu.getId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(String parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenu> getUserMenuList(String userId) {
        //系统管理员，拥有最高权限
        if(Objects.equals(userId, "0")){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(String menuId){
        //删除菜单
        this.deleteById(menuId);
        //删除菜单与角色关联
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setMenuId(menuId);
        Wrapper<SysRoleMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_id", menuId);
        sysRoleMenuService.delete(wrapper);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<String> menuIdList){
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId("0", menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<String> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();

        for(SysMenu entity : menuList){
            //目录
            if("0".equals(entity.getType())){
                entity.setList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
