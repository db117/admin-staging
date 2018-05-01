package com.db117.adminstaging.modules.sys.dao;

import com.db117.adminstaging.common.base.BaseDao;
import com.db117.adminstaging.modules.sys.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
public interface SysMenuDao extends BaseDao<SysMenu> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();
}
