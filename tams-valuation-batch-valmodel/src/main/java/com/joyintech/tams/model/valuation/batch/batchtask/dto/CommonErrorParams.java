package com.joyintech.tams.model.valuation.batch.batchtask.dto;

import java.io.Serializable;

/**
 * ead计算报错信息记录
 * @author zhangfei
 * @date 2021年7月27日09:31:03
 */
public class CommonErrorParams implements Serializable {

    /**
     */
    private static final long serialVersionUID = 0L;

    /**
     * 资产代码
     */
    private String finprodId;
    /**
     * 批处理对应序号
     */
    private String batchSeqNo;
    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 错误类型
     */
    private String errorType;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getBatchSeqNo() {
        return batchSeqNo;
    }

    public void setBatchSeqNo(String batchSeqNo) {
        this.batchSeqNo = batchSeqNo;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}
