package com.db117.adminstaging.modules.sys.entity;

import com.db117.adminstaging.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 归属机构
     */
    private String officeId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 英文名称
     */
    private String enname;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 数据范围
     */
    private String dataScope;
    /**
     * 是否系统数据
     */
    private String isSys;
    /**
     * 是否可用
     */
    private String useable;


}
