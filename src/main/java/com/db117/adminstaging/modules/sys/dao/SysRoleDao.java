package com.db117.adminstaging.modules.sys.dao;

import com.db117.adminstaging.common.base.BaseDao;
import com.db117.adminstaging.modules.sys.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
public interface SysRoleDao extends BaseDao<SysRole> {

    /**
     * 根据用户查询所有角色
     */
    List<SysRole> findByUser(String userId);
}
