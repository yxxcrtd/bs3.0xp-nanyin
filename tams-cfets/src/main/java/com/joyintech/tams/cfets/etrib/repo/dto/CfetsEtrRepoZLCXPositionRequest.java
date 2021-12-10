package com.joyintech.tams.cfets.etrib.repo.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *
 * 指令下拉框模糊查询
 */
@ApiModel(value="质押券库查询入参",description="质押券库查询入参")
public class CfetsEtrRepoZLCXPositionRequest {

    @ApiModelProperty(name="pageMode",value="分页对象",example="{pageSize: 10,pageNo: 1, sort: 'name', direction: 'asc'}")
    private PageMode pageMode;

    @ApiModelProperty(name="portfolioIdList",value="组合代码",example="['1','2']")
    private List<String> portfolioIdList;

    @ApiModelProperty(name="zyqk",value="质押券库",example="03")
    private String zyqk;

    @ApiModelProperty(name="secAcctIdList",value="证券账户",example="['1','2']")
    private List<String> secAcctIdList;

    @ApiModelProperty(name="registOrg",value="登记托管机构",example="CBC")
    private String registOrg;

    @ApiModelProperty(name="finprodAbbr",value="债券简称",example="CBC")
    private String finprodAbbr;

    @ApiModelProperty(name="finprodType",value="债券分类",example="CBC")
    private String finprodType;

    @ApiModelProperty(name="keywords",value="关键字",example="CBC")
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

    public String getZyqk() {
        return zyqk;
    }

    public void setZyqk(String zyqk) {
        this.zyqk = zyqk;
    }

    public List<String> getSecAcctIdList() {
        return secAcctIdList;
    }

    public void setSecAcctIdList(List<String> secAcctIdList) {
        this.secAcctIdList = secAcctIdList;
    }

    public String getRegistOrg() {
        return registOrg;
    }

    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
