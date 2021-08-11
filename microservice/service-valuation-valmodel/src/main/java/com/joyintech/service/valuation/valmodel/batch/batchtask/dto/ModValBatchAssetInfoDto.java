package com.joyintech.service.valuation.valmodel.batch.batchtask.dto;

/**
 * @author 王波
 * @date 2021-07-15
 * @see ModValBatchAssetInfoDto
 * @since 1.0
 */
public class ModValBatchAssetInfoDto {

    // 资产代码
    private String finprodId;

    // 资产名称
    private String finprodName;

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
}
