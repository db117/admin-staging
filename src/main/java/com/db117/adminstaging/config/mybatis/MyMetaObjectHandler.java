package com.db117.adminstaging.config.mybatis;


import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.db117.adminstaging.config.shiro.ShiroUtils;
import com.db117.adminstaging.modules.sys.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;


/**
 * @author 大兵
 * @date 2018-04-04 22:41
 * 注入公共字段自动填充,任选注入方式即可
 **/
@Slf4j
public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("新增的时候干点不可描述的事情");

        SysUser sysUser = ShiroUtils.getUserEntity();

        //创建时间
        Object createDate = getFieldValByName("createDate", metaObject);
        if (null == createDate) {
            setFieldValByName("createDate", new Date(), metaObject);
        }

        //创建人
        Object createBy = getFieldValByName("createBy", metaObject);
        if (null == createBy) {
            if (sysUser != null) {
                setFieldValByName("createBy", sysUser.getId(), metaObject);
            }
        }

        //更新时间
        Object updateDate = getFieldValByName("updateDate", metaObject);
        if (null == updateDate) {
            setFieldValByName("updateDate", new Date(), metaObject);
        }

        //更新者
        Object updateBy = getFieldValByName("updateBy", metaObject);
        if (null == updateBy) {
            if (sysUser != null) {
                setFieldValByName("updateBy", sysUser.getId(), metaObject);
            }
        }
        //删除标识
        Object delFlag = getFieldValByName("delFlag", metaObject);
        if (null == delFlag) {
            setFieldValByName("delFlag", "1", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新的时候干点不可描述的事情");
        SysUser sysUser = ShiroUtils.getUserEntity();

        //更新时间
        Object updateDate = getFieldValByName("updateDate", metaObject);
        if (null == updateDate) {
            setFieldValByName("updateDate", new Date(), metaObject);
        }

        //那个用户更新的
        Object updateId = getFieldValByName("updateBy", metaObject);
        if (null == updateId) {
            if (sysUser != null) {
                setFieldValByName("updateBy", sysUser.getId(), metaObject);
            }
        }
    }
}

