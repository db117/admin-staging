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
     * 获取所有的菜单树
     */
    List<ZtreeVO> getAllTree();

    /**
     * 获取所有的目录
     */
    List<SysMenu> findAllDir();

    /**
     * 根据父级id查询
     *
     * @param pid 父级id
     */
    List<SysMenu> findByPid(String pid);
}
