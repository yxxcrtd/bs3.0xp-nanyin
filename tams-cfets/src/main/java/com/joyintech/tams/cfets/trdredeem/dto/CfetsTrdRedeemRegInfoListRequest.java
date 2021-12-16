package com.joyintech.tams.cfets.trdredeem.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 银行间赎回登记
 * 交易登记查询请求条件DTO
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdRedeemRegInfoListRequest
 * @since 1.0
 */
@ApiModel(value="赎回登记查询请求参数",description="赎回登记查询请求参数")
public class CfetsTrdRedeemRegInfoListRequest {

    @ApiModelProperty(name="effectiveStatus",value="有效状态",example="A1")
    private String effectiveStatus;

    @ApiModelProperty(name="portfolioId",value="组合代码集合",example="字符串1,字符串2,...")
    private String portfolioId;

    @ApiModelProperty(name="finprodId",value="资产代码",example="字符串1,字符串2,...")
    private String finprodId;

    @ApiModelProperty(name="startRegDate",value="查询登记开始日期",example="12")
    private Date startRegDate;

    @ApiModelProperty(name="endRegDate",value="查询登记结束日期",example="12")
    private Date endRegDate;

    @ApiModelProperty(name="startExerDate",value="查询理论行权开始日期",example="12")
    private Date startExerDate;

    @ApiModelProperty(name="endExerDate",value="查询理论行权结束日期",example="12")
    private Date endExerDate;

    @ApiModelProperty(name="busiCode",value="业务编号",example="字符串1")
    private String busiCode;

    @ApiModelProperty(name="tabType",value="查询tab页",example="12")
    private String tabType;

    @ApiModelProperty(name="pageMode",value="分页对象")
    private PageMode pageMode;

    @ApiModelProperty(name="search",value="关键字",example="12")
    private String search;


    public String getEffectiveStatus() {
        return effectiveStatus;
    }

    public void setEffectiveStatus(String effectiveStatus) {
        this.effectiveStatus = effectiveStatus;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Date getStartRegDate() {
        return startRegDate;
    }

    public void setStartRegDate(Date startRegDate) {
        this.startRegDate = startRegDate;
    }

    public Date getEndRegDate() {
        return endRegDate;
    }

    public void setEndRegDate(Date endRegDate) {
        this.endRegDate = endRegDate;
    }

    public Date getStartExerDate() {
        return startExerDate;
    }

    public void setStartExerDate(Date startExerDate) {
        this.startExerDate = startExerDate;
    }

    public Date getEndExerDate() {
        return endExerDate;
    }

    public void setEndExerDate(Date endExerDate) {
        this.endExerDate = endExerDate;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }
}

