package com.joyintech.tams.cfets.etrib.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 持仓列表查询，请求参数
 */
@ApiModel(value="债券指令持仓信息请求参数",description="债券指令持仓信息请求参数")
public class CfetsEtrIbBondPosiPage001RequestDto {

    @ApiModelProperty(name="orderFiled",value="排序字段",example="portfolioId")
    private String orderFiled;

    @ApiModelProperty(name="orderRule",value="排序规则",example="desc|asc")
    private String orderRule;
    
    @ApiModelProperty(name="portfolioId",value="组合代码",example="xxx")
    private String portfolioId;

    @ApiModelProperty(name="cdate",value="交易日期",example="2021-10-20", required = true)
    private String cdate;

    @ApiModelProperty(name="finprodId",value="资产代码",example="xxx")
    private String finprodId;
    
    @ApiModelProperty(name="finprodId",value="债券一级分类",example="F01")
    private String type8;

    @ApiModelProperty(name="pageMode",value="分页对象",example="{pageSize: 10,pageNo: 1, sort: 'name', direction: 'asc'}")
    private PageMode pageMode;

    public String getOrderFiled() {
        return orderFiled;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }

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

    public String getType8() {
        return type8;
    }

    public void setType8(String type8) {
        this.type8 = type8;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public int getPageNo() {
        return pageMode.getPageNo();
    }
    public int getPageSize() {
        return pageMode.getPageSize();
    }

}
