package org.spring.springboot.commons.base;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

/**
 * Created by ThinkPad on 2018/1/11.
 */
public class Result {

    private int code;
    private String msg;
    private Object data;

    public Result() {
    }

    public static Result NEW() {
        return new Result();
    }

    public Result addCode(int code) {
        this.code = code;
        return this;
    }

    public Result addMsg(String msg) {
        this.msg = StringUtils.isEmpty(msg)?"":msg;
        return this;
    }

    public Result addData(Object data) {
        this.data = data;
        return this;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = StringUtils.isEmpty(msg)?"":msg;
        this.data = data;
    }

    public static Result success(String content) {
        return new Result(0, content, (Object)null);
    }

    public static Result success(String content, Object data) {
        return new Result(0, content, data);
    }

    public static Result error(int code, String content) {
        return new Result(code, content, (Object)null);
    }

    public static Result error(String content) {
        return new Result(1, content, (Object)null);
    }

    public static Result success() {
        return new Result(0, "globals.result.success", (Object)null);
    }

    public static Result error() {
        return new Result(1, "globals.result.error", (Object)null);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
