package com.db117.adminstaging.config;


import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

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
        Object createDate = getFieldValByName("createDate", metaObject);
        Object createId = getFieldValByName("createBy", metaObject);
        Object updateDate = getFieldValByName("updateDate", metaObject);
        Object updateId = getFieldValByName("updateBy", metaObject);
        Object delFlag = getFieldValByName("delFlag", metaObject);

//        SysUser sysUser = ShiroUtils.getUserEntity();
//
//        if (null == createDate) {
//            setFieldValByName("createDate", new Date(), metaObject);
//        }
//        if (null == createId) {
//            if (sysUser != null) {
//                setFieldValByName("createBy", sysUser.getId(), metaObject);
//            }
//        }
//        if (null == updateDate) {
//            setFieldValByName("updateDate", new Date(), metaObject);
//        }
//        if (null == updateId) {
//            if (sysUser != null) {
//                setFieldValByName("updateBy", sysUser.getId(), metaObject);
//            }
//        }
//        if (null == delFlag) {
//            setFieldValByName("delFlag", "1", metaObject);
//        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新的时候干点不可描述的事情");
//        //更新时间
//        setFieldValByName("updateDate", new Date(), metaObject);
//        //那个用户更新的
//        SysUser sysUser = ShiroUtils.getUserEntity();
//        Object updateId = getFieldValByName("updateBy", metaObject);
//        if (null == updateId) {
//            if (sysUser != null) {
//                setFieldValByName("updateBy", sysUser.getId(), metaObject);
//            }
//        }
    }
}

