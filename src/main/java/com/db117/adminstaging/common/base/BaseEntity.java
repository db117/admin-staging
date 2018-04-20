package com.db117.adminstaging.common.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.db117.adminstaging.modules.sys.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类
 *
 * @author 大兵
 * @date 2018-04-16 10:39
 **/
@Data
public class BaseEntity implements Serializable {

    /**
     * 编号
     */
    @TableId
    private String id;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;
    /**
     * 创建者
     */
    @TableField(exist = false)
    protected SysUser createUser;

    /**
     * 修改者
     */
    @TableField(exist = false)
    protected SysUser updateUser;
}
