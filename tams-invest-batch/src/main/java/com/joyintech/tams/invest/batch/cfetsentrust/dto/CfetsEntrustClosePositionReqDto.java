package com.joyintech.tams.invest.batch.cfetsentrust.dto;



import java.util.List;

/**
 * 头寸信息查询，请求参数
 */
public class CfetsEntrustClosePositionReqDto {
    /**
     * 用户名
     */
    private String userId;
    /**
     * 汇总交易编号
     */
    private String totalTradeId;
    /**
     * 指令或交易标识
     */
    private String modelType;
    /**
     * 指令数据集
     */
    private List<CfetsEntrustClosePosEtrReqDto> data;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalTradeId() {
        return totalTradeId;
    }

    public void setTotalTradeId(String totalTradeId) {
        this.totalTradeId = totalTradeId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }


    public List<CfetsEntrustClosePosEtrReqDto> getData() {
        return data;
    }

    public void setData(List<CfetsEntrustClosePosEtrReqDto> data) {
        this.data = data;
    }
}
