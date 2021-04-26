package com.joyintech.tams.valuation.valapi.dto;
import com.joyintech.tams.valuation.common.interf.common.ResultCode;

import java.io.Serializable;

/**
 * @ClassName ValApiResultObject
 * @Description: 调用估值api返回结果集
 * @Author yanya
 * @Date 2021/2/3
 * @Version V1.0
 **/
public class ValApiResultObject  implements Serializable {

    private static final long serialVersionUID = -7678212649982643724L;

    public ValApiResultObject() {
        super();
    }

    public ValApiResultObject(ResultCode resultCode) {
        super();
        this.resultCode = resultCode.getCode();
        this.resultMsg = resultCode.getName();
    }

    public ValApiResultObject(String resultCode, String resultMsg) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /**
     * 处理结果代码
     */
    private String resultCode;

    /**
     * 处理结果描述
     */
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
