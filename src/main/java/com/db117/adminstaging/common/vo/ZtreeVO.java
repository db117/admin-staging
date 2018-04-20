package com.db117.adminstaging.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Ztree 树
 */
@Data
public class ZtreeVO implements Serializable {

    /**
     * id
     */
    private String id;
    /**
     * 父级id
     */
    private String pid;
    /**
     * 名称
     */
    private String name;
    /**
     * URL
     */
    private String url;
    /**
     * 是否打开
     */
    private Boolean open = true;
    /**
     * 是否是父级目录
     */
    private Boolean isParent;
    /**
     * 图标
     */
    private String icon;

    /**
     * 子树
     */
    private List<ZtreeVO> children;


}
