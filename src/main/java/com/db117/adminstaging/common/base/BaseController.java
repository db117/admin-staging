package com.db117.adminstaging.common.base;

import com.db117.adminstaging.modules.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * 控制层基类
 *
 * @author 大兵
 * @date 2018-04-16 10:50
 **/
public class BaseController {
    /**
     * 当前用户
     */
    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }
}
