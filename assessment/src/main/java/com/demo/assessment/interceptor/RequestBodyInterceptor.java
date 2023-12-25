package com.demo.assessment.interceptor;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.demo.assessment.util.LoggingUtil;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RequestBodyInterceptor extends RequestBodyAdviceAdapter {
	
	@Autowired
	LoggingUtil loggingUtil;

	@Autowired
    HttpServletRequest request;

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    	//loggingInterceptors.displayReq(request,body);
    	loggingUtil.displayReq(request, body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
	
}
