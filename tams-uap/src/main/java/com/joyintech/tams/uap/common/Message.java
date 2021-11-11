package com.joyintech.tams.uap.common;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "消息对象", description = "消息对象")
public class Message<T> implements Serializable {

    private static final long serialVersionUID = 8909160204700409989L;

    private static final String SUCCESS = "000";
    private static final String FAIL = "001";

    @ApiModelProperty(required = true, name = "code", value = "响应码", example = "")
    private String code;

    @ApiModelProperty(required = true, name = "message", value = "消息", example = "")
    private String message;

    @ApiModelProperty(required = true, name = "data", value = "数据", example = "")
    private T data;

    /**
     * 默认构造方法
     */
    public Message() {
    }

    public Message(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Message(T data) {
    	this.code = SUCCESS;
        this.data = data;
    }


    public Message<T> success() {
        this.code = SUCCESS;
        return this;
    }

    public Message<T> success(String message) {
        this.code = SUCCESS;
        this.message = message;
        return this;
    }

    public Message<T> fail() {
        this.code = FAIL;
        return this;
    }

    public Message<T> fail(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }


    public String getCode() {
        return code;
    }

    public Message<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Message<T> setData(T data) {
        this.data = data;
        return this;
    }

}
