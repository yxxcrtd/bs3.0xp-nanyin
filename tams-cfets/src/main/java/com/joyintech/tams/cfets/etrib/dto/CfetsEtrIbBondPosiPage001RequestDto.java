package com.joyintech.tams.cfets.etrib.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 持仓列表查询，请求参数
 */
@ApiModel(value="债券指令持仓信息请求参数",description="债券指令持仓信息请求参数")
public class CfetsEtrIbBondPosiPage001RequestDto {


    @ApiModelProperty(name="portfolioId",value="组合代码",example="xxx")
    private List<String> portfolioId;

    @ApiModelProperty(name="cdate",value="交易日期",example="2021-10-20", required = true)
    private String cdate;

    @ApiModelProperty(name="finprodId",value="资产代码",example="xxx")
    private String finprodId;
    
    @ApiModelProperty(name="finprodId",value="债券一级分类",example="F01")
    private String type8;

    public List<String> getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(List<String> portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getType8() {
        return type8;
    }

    public void setType8(String type8) {
        this.type8 = type8;
    }
}
