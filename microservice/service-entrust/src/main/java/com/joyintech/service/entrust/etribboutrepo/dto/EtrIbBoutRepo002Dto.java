/*
 * 文件名：EtrBoutRepoDto.java
 */
 
package com.joyintech.service.entrust.etribboutrepo.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @since 1.0
 */
public class EtrIbBoutRepo002Dto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1770266413189669438L;

    /**
     * 所属部门
     */
    private String room;

    /**
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    /**
     * 期限1
     */
    private BigDecimal termDays;
    /**
     * 期限2
     */
    private BigDecimal termDays2;
    /**
     * 添加质押券信息
     */
    private BussRepoSecDto bussRepoSec;
    /**
     * 首次净价
     */
    private BigDecimal firstprice;
    
    /**
     * 首次净价
     */
    private BigDecimal firstprice2;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 证券托管户
     */
    private String securitiesCust;
    /**
     * 资金可用日
     */
    private Date availableDay;
    /**
     * 到期日
     */
    private Date mdateAdd;
    /**
     * 起息日
     */
    private Date vdateAdd;
    /**
     * 清算速度
     */
    private String settleSpreed;
    /**
     * 清算速度（long）
     */
    private Long settleSpeed;
    /**
     * 回购期限
     */
    private String repoTerm;
    /**
     * 回购期限2
     */
    private String repoTerm2;
    /**
     * 期限品种
     */
    private String termType;
    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 人员信息业务组件DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 委托关闭方式
     */
    private String CloseWay;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产一级分类
     */
    private String assetType1;
    /**
     * 金融产品全称
     */
    private String finprodName;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    /**
     * 多指令导入
     */
    private boolean importFlow;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * 获取 finvdate
     * @return finvdate.
     */
    public Date getFinvdate() {
        return finvdate;
    }

    
    /**
     * 设置 finvdate
     * @param finvdate finvdate
     */
    public void setFinvdate(Date finvdate) {
        this.finvdate = finvdate;
    }

    
    /**
     * 获取 finmdate
     * @return finmdate.
     */
    public Date getFinmdate() {
        return finmdate;
    }

    
    /**
     * 设置 finmdate
     * @param finmdate finmdate
     */
    public void setFinmdate(Date finmdate) {
        this.finmdate = finmdate;
    }

    /**
     * 获取 repoTerm2
     * @return repoTerm2.
     */
    // @NotEmpty(fieldName = "EtrBoutRepo002.repoTerm2", groups = {ValidateGroupEdit.class})
    public String getRepoTerm2() {
        return repoTerm2;
    }

    /**
     * 设置 repoTerm2
     * @param repoTerm2 repoTerm2
     */
    public void setRepoTerm2(String repoTerm2) {
        this.repoTerm2 = repoTerm2;
    }


    /**
     * 获取 termDays
     * @return termDays.
     */
    public BigDecimal getTermDays() {
        return termDays;
    }

    
    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(BigDecimal termDays) {
        this.termDays = termDays;
    }

    /**
     * 获取 termDays2
     * @return termDays2.
     */
    public BigDecimal getTermDays2() {
        return termDays2;
    }

    /**
     * 设置 termDays2
     * @param termDays2 termDays2
     */
    public void setTermDays2(BigDecimal termDays2) {
        this.termDays2 = termDays2;
    }


    /**
     * 获取 bussRepoSec
     * @return bussRepoSec.
     */
    public BussRepoSecDto getBussRepoSec() {
        return bussRepoSec;
    }

    /**
     * 设置 bussRepoSec
     * @param bussRepoSec bussRepoSec
     */
    public void setBussRepoSec(BussRepoSecDto bussRepoSec) {
        this.bussRepoSec = bussRepoSec;
    }

    /**
     * 获取 firstprice2
     * @return firstprice2.
     */
    //@NotEmpty(fieldName = "EtrBoutRepo002.firstprice2", groups = {ValidateGroupEdit.class})
    public BigDecimal getFirstprice2() {
        return firstprice2;
    }

    /**
     * 设置 firstprice2
     * @param firstprice2 firstprice2
     */
    public void setFirstprice2(BigDecimal firstprice2) {
        this.firstprice2 = firstprice2;
    }




    /**
     * 获取 firstprice
     * @return firstprice.
     */
    //@NotEmpty(fieldName = "EtrBoutRepo002.firstprice1", groups = {ValidateGroupEdit.class})
    public BigDecimal getFirstprice() {
        return firstprice;
    }


    
    /**
     * 设置 firstprice
     * @param firstprice firstprice
     */
    public void setFirstprice(BigDecimal firstprice) {
        this.firstprice = firstprice;
    }


    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "EtrBoutRepo002.finprodId", groups = {ValidateGroupEdit.class})
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 securitiesCust
     * @return securitiesCust.
     */
    public String getSecuritiesCust() {
        return securitiesCust;
    }
    
    /**
     * 设置 securitiesCust
     * @param securitiesCust securitiesCust
     */
    public void setSecuritiesCust(String securitiesCust) {
        this.securitiesCust = securitiesCust;
    }
    
    /**
     * 获取 availableDay
     * @return availableDay.
     */
    public Date getAvailableDay() {
        return availableDay;
    }
    
    /**
     * 设置 availableDay
     * @param availableDay availableDay
     */
    public void setAvailableDay(Date availableDay) {
        this.availableDay = availableDay;
    }
    
    /**
     * 获取 mdateAdd
     * @return mdateAdd.
     */
    public Date getMdateAdd() {
        return mdateAdd;
    }
    
    /**
     * 设置 mdateAdd
     * @param mdateAdd mdateAdd
     */
    public void setMdateAdd(Date mdateAdd) {
        this.mdateAdd = mdateAdd;
    }
    
    /**
     * 获取 vdateAdd
     * @return vdateAdd.
     */
    public Date getVdateAdd() {
        return vdateAdd;
    }
    
    /**
     * 设置 vdateAdd
     * @param vdateAdd vdateAdd
     */
    public void setVdateAdd(Date vdateAdd) {
        this.vdateAdd = vdateAdd;
    }
    
    /**
     * 获取 settleSpreed
     * @return settleSpreed.
     */
    @NotEmpty(fieldName = "EtrBoutRepo002.settleSpreed", groups = {ValidateGroupEdit.class})
    public String getSettleSpreed() {
        return settleSpreed;
    }
    
    /**
     * 设置 settleSpreed
     * @param settleSpreed settleSpreed
     */
    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }
    
    /**
     * 获取 settleSpeed
     * @return settleSpeed.
     */
    public Long getSettleSpeed() {
        return settleSpeed;
    }
    
    /**
     * 设置 settleSpeed
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(Long settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 repoTerm
     * @return repoTerm.
     */
    @NotEmpty(fieldName = "EtrBoutRepo002.repoTerm", groups = {ValidateGroupEdit.class})
    public String getRepoTerm() {
            return repoTerm;
    }
    
    /**
     * 设置 repoTerm
     * @param repoTerm repoTerm
     */
    public void setRepoTerm(String repoTerm) {
        this.repoTerm = repoTerm;
    }
    
    /**
     * 获取 termType
     * @return termType.
     */
    public String getTermType() {
        return termType;
    }
    
    /**
     * 设置 termType
     * @param termType termType
     */
    public void setTermType(String termType) {
        this.termType = termType;
    }
    
    /**
     * 获取 assetAvailablePosition
     * @return assetAvailablePosition.
     */
    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }
    
    /**
     * 设置 assetAvailablePosition
     * @param assetAvailablePosition assetAvailablePosition
     */
    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
    }
    
    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }
    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }
    
    /**
     * 获取 etrInvestPersonInfo
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }
    
    /**
     * 设置 etrInvestPersonInfo
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    /**
     * 获取 closeWay
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }
    
    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }
    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    /**
     * 获取 assetType1
     * @return assetType1.
     */
    public String getAssetType1() {
        return assetType1;
    }
    
    /**
     * 设置 assetType1
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }
    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }
    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 riskTrialRetDto
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }
    
    /**
     * 设置 riskTrialRetDto
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }
    
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId(){
        return super.getEntrustId();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt(){
        return super.getEntrustAmt();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.tradeMarket", groups = {ValidateGroupEdit.class})
    public String getTradeMarket(){
        return super.getTradeMarket();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    
    @Override
    public BigDecimal getPrice(){
        return super.getPrice();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.calendarId", groups = {ValidateGroupEdit.class})
    public String getCalendarId(){
        return super.getCalendarId();
    }

    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.priceModel", groups = {ValidateGroupEdit.class})
    public String getPriceModel() {
        return super.getPriceModel();
    }

    /**
     * 获取 importFlow
     *
     * @return importFlow
     */
    public boolean isImportFlow() {
        return importFlow;
    }

    /**
     * 设置 importFlow
     *
     * @param importFlow importFlow
     */
    public void setImportFlow(boolean importFlow) {
        this.importFlow = importFlow;
    }

    private String parentPortfolioId;


    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }
}
