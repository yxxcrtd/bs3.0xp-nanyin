package com.joyintech.service.bankuplink.cfets.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Classname GateWayReturnMsgDto
 * @Description http请求返回值
 * @author zhuhanlu 
 * @date 2020/12/22 8:50 
 * @version 1.0 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CfetsInterfResultDto {

    /**
     * 状态码
     */
    private String code;


    /**
     * 返回数据
     */
/*    private List data;*/

    /**
     * 日志
     */
    private String log;

    /**
     * 消息
     */
    private String message;

    /**
     * 成功与否
     */

    private boolean success;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

/*    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }*/

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
