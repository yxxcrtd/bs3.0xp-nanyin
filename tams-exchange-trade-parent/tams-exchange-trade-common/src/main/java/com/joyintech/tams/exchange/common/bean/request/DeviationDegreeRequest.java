package com.joyintech.tams.exchange.common.bean.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 偏离度接口请求参数
 *
 * @author swwan
 * @date 2022/1/19 - 15:57
 */
@ApiModel(value="偏离度接口",description="偏离度接口请求参数")
public class DeviationDegreeRequest {

    @ApiModelProperty(name="finprodId",value="资产代码",example="F01_100303")
    @NotBlank(message = "资产代码不可为空")
    private String finprodId;

    @ApiModelProperty(name="finprodMarketId",value="资产市场代码",example="100303.SZ")
    @NotBlank(message = "资产市场代码不可为空")
    private String finprodMarketId;

    @ApiModelProperty(name="secId",value="净价",example="100.00")
    @NotBlank(message = "净价不可为空")
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
