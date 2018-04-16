package com.db117.adminstaging.modules.sys.entity;

import com.db117.adminstaging.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色-菜单
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    private String roleId;
    /**
     * 菜单编号
     */
    private String menuId;


}
