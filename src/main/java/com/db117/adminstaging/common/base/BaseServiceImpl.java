package com.db117.adminstaging.common.base;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 业务实现类基类
 *
 * @author 大兵
 * @date 2018-04-16 10:47
 **/
public class BaseServiceImpl<D extends BaseDao<T>, T> extends ServiceImpl<D, T> {
}
