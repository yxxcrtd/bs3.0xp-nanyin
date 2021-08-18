package com.joyintech.tams.valuation.valmodel.batch.batchtask.model.dto;

/**
 * @author 王波
 * @date 2021-07-20
 * @see ModBatchDetailDto
 * @since 1.0
 */
public class ModBatchDetailDto {

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 执行状态
     */
    private String execStatus;

    /**
     * 消息详情
     */
    private String execDetail;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus;
    }

    public String getExecDetail() {
        return execDetail;
    }

    public void setExecDetail(String execDetail) {
        this.execDetail = execDetail;
    }
}
