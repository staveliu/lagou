package com.newer.lagou.controller;


import com.newer.lagou.domain.ErrorInfo;
import com.newer.lagou.domain.LagouException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shining
 * ControllerAdvice用于注解所有Controller抛异常时的统一处理类
 */
@ControllerAdvice
public class ExceptionController {

    @ResponseBody//将方法返回值转换json对象，写入响应主体返回
    @ExceptionHandler(value = LagouException.class)
    public ErrorInfo<String> exceptionHandler1(LagouException e, HttpServletRequest request){
        //异常处理器
        ErrorInfo<String> errorInfo=new ErrorInfo<>();
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setData("服务端接口异常！");
        return errorInfo;
    }



}
