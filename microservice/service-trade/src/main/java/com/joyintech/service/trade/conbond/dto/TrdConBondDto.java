/*
 * 文件名：TrdConBondDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.conbond.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

/**
 * 可转债申购Dto
 *
 * @author wangpeng
 * @version 1.0 2019年11月14日
 * @see TrdConBondDto
 * @since 1.0
 */
public class TrdConBondDto extends TrdFinProductDealTotal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8259658424290761601L;

    /**
     * 有效状态
     */
    private String validStatus;
    
    /**
     * 业务编号
     */
    private String busiCode;
    
    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 债券一级分类
     */
    private String windtypeone;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 工作流状态
     */
    private String workflowStatus;

    /**
     * 计算类型
     */
    private String calculateField;

    /**
     * 金融产品交易汇总附属
     */
    private TrdDealTotalAdd trdDealTotalAdd;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;

    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 人员信息
     */
    private TrdInvestPersonInfoDto trdInvestPersonInfo;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 申购方式
     */
    private String purchModle;
    
    /**
     * 申购类型
     */
    private String purchType;
    
    /**
     * 申购日期
     */
    private Date purchDate;
    
    /**
     * 保证金
     */
    private BigDecimal depositAmt;
    
    /**
     * 补缴款金额
     */
    private BigDecimal supplementaryAmt;
    
    /**
     * 退款金额
     */
    private BigDecimal refundAmt;
    
    /**
     * 中签券面总额
     */
    private BigDecimal winlotteryAmt;
    
    /**
     * 退款日期
     */
    private Date refundDate;
    
    /**
     * 金融产品交易汇总
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;
    
    /**
     * 金融产品交易汇总扩展
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;
    
    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;
    
    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;
    
    /**
     * 金融产品交易
     */
    private List<TrdProductDeal> trdProductDealList;
    
    /**
     * 金融产品交易附属
     */
    private List<TrdProductDealAdd> trdProductDealAddList;
    
    /**
     * 金融产品交易扩展
     */
    private List<PaTrdProductDeal> paTrdProductDealList;
    
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    

    
    /**
     * 撤销说明
     */
    private String revokeReason;
    
    /**
     * 控制操作按钮
     */
    private String flg;


	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	@Override
	public String getPortfolioId() {
		return portfolioId;
	}

	@Override
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getWindtypeone() {
		return windtypeone;
	}

	public void setWindtypeone(String windtypeone) {
		this.windtypeone = windtypeone;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public String getCalculateField() {
		return calculateField;
	}

	public void setCalculateField(String calculateField) {
		this.calculateField = calculateField;
	}

	public TrdDealTotalAdd getTrdDealTotalAdd() {
		return trdDealTotalAdd;
	}

	public void setTrdDealTotalAdd(TrdDealTotalAdd trdDealTotalAdd) {
		this.trdDealTotalAdd = trdDealTotalAdd;
	}

	

	public PaTrdProductDeal getPaTrdProductDeal() {
		return paTrdProductDeal;
	}

	public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
		this.paTrdProductDeal = paTrdProductDeal;
	}

	

	public TrdInvestPersonInfoDto getTrdInvestPersonInfo() {
		return trdInvestPersonInfo;
	}

	public void setTrdInvestPersonInfo(TrdInvestPersonInfoDto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}

	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	public String getPurchModle() {
		return purchModle;
	}

	public void setPurchModle(String purchModle) {
		this.purchModle = purchModle;
	}

	public String getPurchType() {
		return purchType;
	}

	public void setPurchType(String purchType) {
		this.purchType = purchType;
	}

	public Date getPurchDate() {
		return purchDate;
	}

	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}

	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	public BigDecimal getSupplementaryAmt() {
		return supplementaryAmt;
	}

	public void setSupplementaryAmt(BigDecimal supplementaryAmt) {
		this.supplementaryAmt = supplementaryAmt;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	public List<RiskTrialRetDto> getRiskTrialRetDto() {
		return riskTrialRetDto;
	}

	public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
		this.riskTrialRetDto = riskTrialRetDto;
	}

	public TrdFinProductDealTotal getTrdFinProductDealTotal() {
		return trdFinProductDealTotal;
	}

	public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
		this.trdFinProductDealTotal = trdFinProductDealTotal;
	}

	public TrdProductDeal getTrdProductDeal() {
		return trdProductDeal;
	}

	public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
		this.trdProductDeal = trdProductDeal;
	}

	public TrdProductDealAdd getTrdProductDealAdd() {
		return trdProductDealAdd;
	}

	public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
		this.trdProductDealAdd = trdProductDealAdd;
	}

	public String getActPortfolioId() {
		return actPortfolioId;
	}

	public void setActPortfolioId(String actPortfolioId) {
		this.actPortfolioId = actPortfolioId;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public List<PtlTrade> getPtlTrade() {
		return ptlTrade;
	}

	public void setPtlTrade(List<PtlTrade> ptlTrade) {
		this.ptlTrade = ptlTrade;
	}

	public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
		return paTrdFinProductDealTotal;
	}

	public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
		this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
	}

	public BigDecimal getWinlotteryAmt() {
		return winlotteryAmt;
	}

	public void setWinlotteryAmt(BigDecimal winlotteryAmt) {
		this.winlotteryAmt = winlotteryAmt;
	}

	public List<TrdProductDeal> getTrdProductDealList() {
		return trdProductDealList;
	}

	public void setTrdProductDealList(List<TrdProductDeal> trdProductDealList) {
		this.trdProductDealList = trdProductDealList;
	}

	public List<TrdProductDealAdd> getTrdProductDealAddList() {
		return trdProductDealAddList;
	}

	public void setTrdProductDealAddList(List<TrdProductDealAdd> trdProductDealAddList) {
		this.trdProductDealAddList = trdProductDealAddList;
	}

	public List<PaTrdProductDeal> getPaTrdProductDealList() {
		return paTrdProductDealList;
	}

	public void setPaTrdProductDealList(List<PaTrdProductDeal> paTrdProductDealList) {
		this.paTrdProductDealList = paTrdProductDealList;
	}

	public List<SysUploadFileInfo> getFileArray() {
		return fileArray;
	}

	public void setFileArray(List<SysUploadFileInfo> fileArray) {
		this.fileArray = fileArray;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	
}
