package com.joyintech.tams.cfets.etrib.repo.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *
 * 指令下拉框模糊查询
 */
@ApiModel(value="指令查询入参",description="指令查询入参")
public class CfetsEtrRepoZLCXRequest {

    @ApiModelProperty(name="pageMode",value="分页对象",example="{pageSize: 10,pageNo: 1, sort: 'name', direction: 'asc'}")
    private PageMode pageMode;

    @ApiModelProperty(name="portfolioIdList",value="组合代码",example="['1','2']")
    private List<String> portfolioIdList;

    @ApiModelProperty(name="ps",value="交易方向",example="03")
    private String ps;

    @ApiModelProperty(name="termType",value="期限品种",example="1")
    private String termType;

    @ApiModelProperty(name="entrustStatusList",value="指令状态",example="['1','2']")
    private List<String> entrustStatusList;

    @ApiModelProperty(name="entrustDate",value="指令日期",example="2021-11-29")
    private String entrustDate;

    @ApiModelProperty(name="entrustId",value="指令编号",example="ETRREPO202111290001")
    private String entrustId;

    @ApiModelProperty(name="invManagerId",value="投资经理",example="wp")
    private String invManagerId;

    @ApiModelProperty(name="keywords",value="关键词",example="wp")
    private String keywords;

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public List<String> getPortfolioIdList() {
        return portfolioIdList;
    }

    public void setPortfolioIdList(List<String> portfolioIdList) {
        this.portfolioIdList = portfolioIdList;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getInvManagerId() {
        return invManagerId;
    }

    public void setInvManagerId(String invManagerId) {
        this.invManagerId = invManagerId;
    }

    public List<String> getEntrustStatusList() {
        return entrustStatusList;
    }

    public void setEntrustStatusList(List<String> entrustStatusList) {
        this.entrustStatusList = entrustStatusList;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
