package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 偏离度接口请求参数
 *
 */
@ApiModel(value="偏离度接口",description="偏离度接口请求参数")
public class CalcDeviationDegreeRequest {

    @ApiModelProperty(name="finprodId",value="资产代码",example="A1")
    private String finprodId;

    @ApiModelProperty(name="finprodMarketId",value="资产代码(市场代码)",example="A1")
    private String finprodMarketId;

    @ApiModelProperty(name="secId",value="净价",example="A1")
    private String unitCprice;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(String unitCprice) {
        this.unitCprice = unitCprice;
    }
}