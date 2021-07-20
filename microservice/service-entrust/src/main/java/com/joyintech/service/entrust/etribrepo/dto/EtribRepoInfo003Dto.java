/*
 * 文件名：EtribRepoInfo003Dto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月20日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.service.entrust.etribrepo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 王波
 * @date 2021年01月20日
 * @see EtribRepoInfo003Dto
 * @since 1.0
 */
public class EtribRepoInfo003Dto extends EtrEntrust implements ProcessEntity {

    /**
     * 按钮id
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 多指令导入
     */
    private boolean importFlow;

    /**
     * 劵面总额
     */
    private BigDecimal tradeAmt;
    
    /**
     * 质押券库
     */
    private String zyqk;
    
	public String getZyqk() {
		return zyqk;
	}

	public void setZyqk(String zyqk) {
		this.zyqk = zyqk;
	}

    /**
     * 清算速度
     */
    private String settleSpeed;
    
    /**
     * 清算速度
     */
    private String settleSpreed;
    
    /**
     * 获取 settleSpreed
     * @return settleSpreed.
     */
    @NotEmpty(fieldName = "EtrIbRepoinfo002.settleSpreed", groups = {ValidateGroupEdit.class})
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
     * 交割日期
     */
    private Date settleDate;

    /**
     * 全价
     */
    private BigDecimal fpriceAmt;

    private BigDecimal matureYield;

    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;

    /**
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    
    /**
     * 回购期限
     */
    private BigDecimal repoTerm;
    /**
     * 期限品种
     */
    private String termType;
    
    /**
     * 获取 repoTerm
     * @return repoTerm.
     */
    public BigDecimal getRepoTerm() {
        return repoTerm;
    }
    
    /**
     * 设置 repoTerm
     * @param repoTerm repoTerm
     */
    public void setRepoTerm(BigDecimal repoTerm) {
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
     * 证券托管户
     */
    private String secAcctId;

    List<EtribRepoInfo003Dto> etrInvestRepoInfoDtos;


    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 本方券券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal entrustOwnNum;
    /**
     * 融入券/卖空购回券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustIntegrateNum;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 多组合汇总指令ID
     */
    private String totalEtrustId;

    /**
     * 画面文件上传用的ID
     */
    private String businessId;

    /**
     * 资产二级分类（原资产一级分类，委托页面的债券一级分类，修改：王贵奇，2019/9/27）
     */
    private String assetType1;


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public boolean isImportFlow() {
        return importFlow;
    }

    public void setImportFlow(boolean importFlow) {
        this.importFlow = importFlow;
    }



    public String getTotalEtrustId() {
        return totalEtrustId;
    }

    public void setTotalEtrustId(String totalEtrustId) {
        this.totalEtrustId = totalEtrustId;
    }

    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public BigDecimal getEntrustIntegrateNum() {
        return entrustIntegrateNum;
    }

    public void setEntrustIntegrateNum(BigDecimal entrustIntegrateNum) {
        this.entrustIntegrateNum = entrustIntegrateNum;
    }

    public BigDecimal getEntrustOwnNum() {
        return entrustOwnNum;
    }

    public void setEntrustOwnNum(BigDecimal entrustOwnNum) {
        this.entrustOwnNum = entrustOwnNum;
    }

    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    public List<EtribRepoInfo003Dto> getEtrInvestRepoInfoDtos() {
        return etrInvestRepoInfoDtos;
    }

    public void setEtrInvestRepoInfoDtos(List<EtribRepoInfo003Dto> etrInvestRepoInfoDtos) {
        this.etrInvestRepoInfoDtos = etrInvestRepoInfoDtos;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 投资人员信息DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;

    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }

    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }

    public Date getFinvdate() {
        return finvdate;
    }

    public void setFinvdate(Date finvdate) {
        this.finvdate = finvdate;
    }

    public Date getFinmdate() {
        return finmdate;
    }

    public void setFinmdate(Date finmdate) {
        this.finmdate = finmdate;
    }

    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }

    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
    }

    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 接收状态
     */
    private String recStatus;

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public String getCloseWay() {
        return CloseWay;
    }

    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }

    /**
     * 选中的数据
     */
    private transient List<EtribRepoInfo003Dto> selectedItem;

    public List<EtribRepoInfo003Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<EtribRepoInfo003Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 行权收益率
     */
    private BigDecimal exerYield;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    public BigDecimal getMatureYield() {
        return matureYield;
    }

    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    private String parentPortfolioId;


    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }
}
