package com.joyintech.tams.cfets.etrib.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 持仓列表查询，请求参数
 */
@ApiModel(value="债券指令持仓信息请求参数",description="债券指令持仓信息请求参数")
public class CfetsEtrIbBondPosinfo001RequestDto {


    @ApiModelProperty(name="portfolioIdList",value="组合代码数据",example="zh-xxxx")
    private String portfolioId;

    @ApiModelProperty(name="cdate",value="交易日期",example="2021-10-20")
    private String cdate;

    @ApiModelProperty(name="finprodId",value="资产代码",example="xxxx1111")
    private String finprodId;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
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
}
