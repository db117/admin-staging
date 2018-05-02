package com.db117.adminstaging.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.db117.adminstaging.common.base.BaseServiceImpl;
import com.db117.adminstaging.common.vo.ZtreeVO;
import com.db117.adminstaging.modules.sys.dao.SysMenuDao;
import com.db117.adminstaging.modules.sys.entity.SysMenu;
import com.db117.adminstaging.modules.sys.entity.SysRoleMenu;
import com.db117.adminstaging.modules.sys.service.SysMenuService;
import com.db117.adminstaging.modules.sys.service.SysRoleMenuService;
import com.db117.adminstaging.modules.sys.service.SysUserService;
import com.google.common.collect.Lists;
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



    @Override
    public List<ZtreeVO> getAllTree() {
        EntityWrapper<SysMenu> wrapper = new EntityWrapper<SysMenu>();
        wrapper.orderBy("sort");
        List<SysMenu> sysMenuList = baseMapper.selectList(wrapper);
        return getZTree(null, sysMenuList, new ArrayList<>());
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
            if(entity.getType()==0){
                entity.setList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    /**
     * 递归拉取菜单树的数据
     */
    private List<ZtreeVO> getZTree(ZtreeVO tree, List<SysMenu> total, List<ZtreeVO> result) {
        String pid = tree == null ? "0" : tree.getId();
        List<ZtreeVO> childList = Lists.newArrayList();
        for (SysMenu m : total) {
            if (!StrUtil.isBlank(pid) && pid.equals(m.getParentId())) {
                ZtreeVO ztreeVO = new ZtreeVO();
                ztreeVO.setId(m.getId());
                ztreeVO.setName(m.getName());
                ztreeVO.setPid(pid);
                ztreeVO.setUrl(m.getHref());
                ztreeVO.setSpread(true);
                childList.add(ztreeVO);
                getZTree(ztreeVO, total, result);
            }
        }
        if (tree != null) {
            tree.setChildren(childList);
        } else {
            result = childList;
        }
        return result;
    }

}
