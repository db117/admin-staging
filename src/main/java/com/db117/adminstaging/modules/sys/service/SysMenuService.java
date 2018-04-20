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

    List<ZtreeVO> getAllTree();
}
