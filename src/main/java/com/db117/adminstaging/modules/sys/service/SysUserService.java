package com.db117.adminstaging.modules.sys.service;

import com.db117.adminstaging.common.base.BaseService;
import com.db117.adminstaging.modules.sys.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
public interface SysUserService extends BaseService<SysUser> {
    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);
}
