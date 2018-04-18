package com.db117.adminstaging.common;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

/**
 * 返回对象
 *
 * @author 大兵
 * @date 2018-03-07 11:09
 **/
@Data
public class Result {
    /**
     * 状态码 默认0成功
     */
    private Integer code;
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

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, long count, Object data) {
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
    public static Result getPageRes(int count, Object data) {
        return new Result(0, "", count, data);
    }

    /**
     * 返回成功
     *
     * @param msg 成功消息
     */
    public static Result getSuccess(String msg) {
        return new Result(0, msg);
    }

    /**
     * 返回失败
     *
     * @param msg 失败消息
     */
    public static Result getFailure(String msg) {
        return new Result(1, msg);
    }

    /**
     * 返回数据表格页面
     */
    public static Result getPage(Page page) {
        return new Result(0, null, page.getTotal(), page.getRecords());
    }

}
