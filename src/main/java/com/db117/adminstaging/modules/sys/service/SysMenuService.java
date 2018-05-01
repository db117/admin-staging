package com.db117.adminstaging.modules.sys.service;

import com.db117.adminstaging.common.base.BaseService;
import com.db117.adminstaging.common.vo.ZtreeVO;
import com.db117.adminstaging.modules.sys.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
public interface SysMenuService extends BaseService<SysMenu> {
    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<SysMenu> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(String userId);

    /**
     * 删除
     */
    void delete(String menuId);
}
