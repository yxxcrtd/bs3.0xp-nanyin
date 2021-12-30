package com.joyintech.tams.exchange.common;

import java.lang.annotation.Annotation;

import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.domain.Message;

import cn.hutool.core.util.StrUtil;

@RestControllerAdvice(basePackages = "com.joyintech.tams.exchange")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof Message) {
            return body;
        }
        if (body instanceof ResultBean) {
        	return new Message<Object>((ResultBean)body);
        }
        return new Message<Object>(body);
    }

    /**
     * 处理自定义的业务异常
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BusinessResultException.class)
    public Message<Object> businessResultExceptionHandler(BusinessResultException businessResultException) {
        Message<Object> resultMessage = businessResultException.getResultMessage();
        ErrorCode errorCode = businessResultException.getErrorCode();
        if (resultMessage == null && errorCode == null) {
            return new Message<Object>().fail();
        }
        if (resultMessage != null) {
            return resultMessage;
        } else {
            String code;
            String message = StrUtil.isNotEmpty(businessResultException.getMessage()) ?
                    errorCode.errorMsg + ":" + businessResultException.getMessage() : errorCode.errorMsg;
            if (errorCode.isSuccess()) {
                code = "000";
            } else {
                code = errorCode.value + "";
            }
            return new Message<Object>().fail(code, message);
        }
    }

}
