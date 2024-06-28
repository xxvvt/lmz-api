package com.bing.admin.exception;

import com.bing.admin.model.IErrorInfo;
import com.bing.admin.model.Results;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lcd
 * @date 2022-01-08 23:14
 * @description 自定义异常
 */
public class BlogException extends RuntimeException {
    private final IErrorInfo errorInfo;

    public  BlogException(IErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 将异常转换为 ResultVO 对象返回给前端
     *
     * @return 封装了异常信息的 ResultVO 对象
     */

    public Results toResultVO() {
        return Results.fromErrorInfo(errorInfo);
    }
}
