package com.db117.adminstaging.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.db117.adminstaging.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author db117
 * @since 2018-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级编号
     */
    private String parentId;
    /**
     * 所有父级编号
     */
    private String parentIds;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 链接
     */
    private String href;
    /**
     * 目标
     */
    private String target;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否在菜单中显示(1显示,0不显示)
     */
    private String isShow;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 类型(0目录1菜单,2按钮)
     */
    private String type;
    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> list;
    /**
     * 父菜单名称
     */
    @TableField(exist=false)
    private String parentName;
    /**
     * ztree属性
     */
    @TableField(exist=false)
    private Boolean open;
}
