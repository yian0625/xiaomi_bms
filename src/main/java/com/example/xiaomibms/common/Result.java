package com.example.xiaomibms.common;
import lombok.Data;
import java.io.Serializable;

/**
 * 统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer status;
    private String msg; //错误信息
    private T data; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.msg="ok";
        result.status = 200;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.msg="ok";
        result.status = 200;
        result.data = object;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.status = 500;
        return result;
    }

}
