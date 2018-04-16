package com.db117.adminstaging.modules.sys.entity;

import com.db117.adminstaging.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户-角色
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 角色编号
     */
    private String roleId;


}
