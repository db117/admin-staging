package com.db117.adminstaging.modules.sys.dao;

import com.db117.adminstaging.common.base.BaseDao;
import com.db117.adminstaging.modules.sys.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
public interface SysUserDao extends BaseDao<SysUser> {
    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);
}
