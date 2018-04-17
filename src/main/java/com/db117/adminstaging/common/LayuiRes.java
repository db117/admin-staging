package com.db117.adminstaging.common;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

/**
 * layui返回对象
 *
 * @author 大兵
 * @date 2018-03-07 11:09
 **/
@Data
public class LayuiRes {
    /**
     * 状态码 默认0成功
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 总条数
     */
    private Long count;
    /**
     * 返回数据
     */
    private Object data;

    public LayuiRes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayuiRes(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    /**
     * 获取页面返回对象
     *
     * @param count 总条数
     * @param data  表格数据
     */
    public static LayuiRes getPageRes(int count, Object data) {
        return new LayuiRes(0, "", count, data);
    }

    /**
     * 返回成功
     *
     * @param msg 成功消息
     */
    public static LayuiRes getSuccess(String msg) {
        return new LayuiRes(0, msg);
    }

    /**
     * 返回失败
     *
     * @param msg 失败消息
     */
    public static LayuiRes getFailure(String msg) {
        return new LayuiRes(1, msg);
    }

    /**
     * 返回数据表格页面
     */
    public static LayuiRes getPage(Page page) {
        return new LayuiRes(0, null, page.getTotal(), page.getRecords());
    }

}
