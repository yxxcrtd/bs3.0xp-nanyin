package com.joyintech.service.entrust.etrautomanagementaccount.dto;

import com.joyintech.service.entrust.etrfund.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustWw;
import com.joyintech.tams.common.base.entity.EtrEntrustWwDetail;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EtrAutoManagementAccountDto extends EtrEntrustWw {

    /**
     * 页面提交模式
     */
    private String pageModel;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 投资人员Dto
     */
    private BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto;

    public BussEtrInvestPersonInfoDto getBussEtrInvestPersonInfoDto() {
        return bussEtrInvestPersonInfoDto;
    }

    public void setBussEtrInvestPersonInfoDto(BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto) {
        this.bussEtrInvestPersonInfoDto = bussEtrInvestPersonInfoDto;
    }

    /**
     * 选中的数据
     */
    private transient List<EtrAutoManagementAccountDto> selectedItem;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<EtrAutoManagementAccountDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<EtrAutoManagementAccountDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    private String entrustId;
    private String entrustStatus;
    private String portfolioId;
    private String portfolioName;
    private String finprodMarketId;
    private String finprodType2;
    private String type6;
    private String type7;
    private Date entrustDate;
    private String ps;
    private BigDecimal entrustAmt;
    private BigDecimal portfolioAmt;
    private BigDecimal subjectNum;
    private String portfolioIdChange;
    private String assetType;

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getPortfolioIdChange() {
        return portfolioIdChange;
    }

    public void setPortfolioIdChange(String portfolioIdChange) {
        this.portfolioIdChange = portfolioIdChange;
    }

    private List<EtrEntrustWwDetail> etrEntrustWwDetailList;

    public List<EtrEntrustWwDetail> getEtrEntrustWwDetailList() {
        return etrEntrustWwDetailList;
    }

    public void setEtrEntrustWwDetailList(List<EtrEntrustWwDetail> etrEntrustWwDetailList) {
        this.etrEntrustWwDetailList = etrEntrustWwDetailList;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public BigDecimal getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(BigDecimal subjectNum) {
        this.subjectNum = subjectNum;
    }

    public BigDecimal getPortfolioAmt() {
        return portfolioAmt;
    }

    public void setPortfolioAmt(BigDecimal portfolioAmt) {
        this.portfolioAmt = portfolioAmt;
    }

    @Override
    public String getEntrustId() {
        return entrustId;
    }

    @Override
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    @Override
    public String getEntrustStatus() {
        return entrustStatus;
    }

    @Override
    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    @Override
    public String getPortfolioId() {
        return portfolioId;
    }

    @Override
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public String getType7() {
        return type7;
    }

    public void setType7(String type7) {
        this.type7 = type7;
    }

    @Override
    public Date getEntrustDate() {
        return entrustDate;
    }

    @Override
    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }

    @Override
    public String getPs() {
        return ps;
    }

    @Override
    public void setPs(String ps) {
        this.ps = ps;
    }

    @Override
    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    @Override
    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    @Override
    public String getFinprodType2() {
        return finprodType2;
    }

    @Override
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
}
