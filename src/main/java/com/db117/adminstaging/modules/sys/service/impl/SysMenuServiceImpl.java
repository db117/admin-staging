package com.db117.adminstaging.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.db117.adminstaging.common.base.BaseServiceImpl;
import com.db117.adminstaging.common.vo.ZtreeVO;
import com.db117.adminstaging.modules.sys.dao.SysMenuDao;
import com.db117.adminstaging.modules.sys.entity.SysMenu;
import com.db117.adminstaging.modules.sys.service.SysMenuService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ZtreeVO> getAllTree() {
        EntityWrapper<SysMenu> wrapper = new EntityWrapper<SysMenu>();
        wrapper.orderBy("sort");
        List<SysMenu> sysMenuList = baseMapper.selectList(wrapper);
        return getZTree(null, sysMenuList, new ArrayList<>());
    }
}
