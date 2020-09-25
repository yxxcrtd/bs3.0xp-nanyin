/*
 * 文件名：TrdConBond003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBond003Dto;
import com.joyintech.tams.invest.trade.conbond.validator.TrdConBond003DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 可转债申购确认Dto
 *
 * @author wangpeng
 * @version 1.0 2019年11月14日
 * @see TrdConBond003Dto
 * @since 1.0
 */
@PageId("TrdConBond003")
@ValidLogic(targetClass = TrdConBond003DtoValidator.class, groups = ValidateGroupEdit.class)
public class TrdConBond003Dto extends TrdFinProductDealTotal implements ProcessEntity {

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
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

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
     * 表格所选数据
     */
    private List<TrdConBond003Dto> selectedItem;
    
    /**
     * 撤销说明
     */
    private String revokeReason;
    
    /**
     * 组合可用现金
     */
    private BigDecimal proAvailableCash;

    /**
     * 交易所资金账户可用现金
     */
    private BigDecimal tradeMarketCash;
    
    /**
     * 通道可用现金
     */
    private BigDecimal channelAvailableCash;
    
    /**
     * 实际到账日
     * @return
     */
    private Date actTradeDate;
    

    @PrimaryKey(PrimaryKey.Field.PK_3)
	public String getEntrustId() {
		return entrustId;
	}
    
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
	public String getbTradeId() {
		return super.getbTradeId();
	}

//    @Override
//	@NotEmpty(fieldName = "TrdConBond002.secManageAcctId", groups = {ValidateGroupEdit.class})
//	public String getSecManageAcctId() {
//        return super.getSecManageAcctId();
//    }
    
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

	

	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
		return trdInvestPersonInfo;
	}

	public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}

	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	@NotEmpty(fieldName = "TrdConBond002.purchModle", groups = {ValidateGroupEdit.class})
	public String getPurchModle() {
		return purchModle;
	}

	public void setPurchModle(String purchModle) {
		this.purchModle = purchModle;
	}

	@NotEmpty(fieldName = "TrdConBond002.purchType", groups = {ValidateGroupEdit.class})
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

	public String getPsStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.getPs());
	}
	
	public String getTradeMarketStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getTradeMarket());
	}
	
	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
	}
	
	public String getPurchModleStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.申购方式, this.getPurchModle());
	}
	
	public String getPurchTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.申购类型, this.getPurchType());
	}
	
	public String getTradeStatusStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易状态, super.getTradeStatus());
	}
	
    public String getValidStatusStr() {
    	return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getValidStatus());
    }

	@PrimaryKey(PrimaryKey.Field.PK_1)
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

	@NotEmpty(fieldName = "TrdConBond002.winlotteryAmt", groups = {ValidateGroupEdit.class})
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

	public List<TrdConBond003Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<TrdConBond003Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	@Override
	@NotEmpty(fieldName = "TrdConBond002.invAim", groups = {ValidateGroupEdit.class})
	public String getInvAim() {
		return super.getInvAim();
	}

	@Override
	@NotEmpty(fieldName = "TrdConBond002.tradeDate", groups = {ValidateGroupEdit.class})
	public Date getTradeDate() {
		return super.getTradeDate();
	}

	@Override
	@NotEmpty(fieldName = "TrdConBond002.settleDate", groups = {ValidateGroupEdit.class})
	public Date getSettleDate() {
		return super.getSettleDate();
	}

	@Override
	@NotEmpty(fieldName = "TrdConBond002.ccy", groups = {ValidateGroupEdit.class})
	public String getCcy() {
		return super.getCcy();
	}

	@Override
	@NotEmpty(fieldName = "TrdConBond002.finprodId", groups = {ValidateGroupEdit.class})
	public String getFinprodId() {
		return super.getFinprodId();
	}

	@Override
	@NotEmpty(fieldName = "TrdConBond002.ps", groups = {ValidateGroupEdit.class})
	public String getPs() {
		return super.getPs();
	}

	public BigDecimal getProAvailableCash() {
		return proAvailableCash;
	}

	public void setProAvailableCash(BigDecimal proAvailableCash) {
		this.proAvailableCash = proAvailableCash;
	}

	public BigDecimal getTradeMarketCash() {
		return tradeMarketCash;
	}

	public void setTradeMarketCash(BigDecimal tradeMarketCash) {
		this.tradeMarketCash = tradeMarketCash;
	}

	public BigDecimal getChannelAvailableCash() {
		return channelAvailableCash;
	}

	public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
		this.channelAvailableCash = channelAvailableCash;
	}

	public Date getActTradeDate() {
		return actTradeDate;
	}

	public void setActTradeDate(Date actTradeDate) {
		this.actTradeDate = actTradeDate;
	}
	
}
