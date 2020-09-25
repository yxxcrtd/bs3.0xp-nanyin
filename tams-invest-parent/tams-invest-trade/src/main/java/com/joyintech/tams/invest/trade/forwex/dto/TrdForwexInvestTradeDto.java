/*
 * 文件名：TrdForwexInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.forwex.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.WflTrdProductDealOth;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.forwex.validator.TrdForwexInvestTradeValidator;
import com.joyintech.tams.invest.trade.lend.dto.FinTradeProductDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 外汇远期交易
 * 〈功能详细描述〉
 * @author admin
 * @version 1.0 2019年7月4日
 * @see TrdForwexInvestTradeDto
 * @since 1.0
 */
@PageId("TrdForwexInvestTrade002")
@ValidLogic(targetClass = TrdForwexInvestTradeValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class TrdForwexInvestTradeDto extends TrdProductDeal implements ProcessEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -5874242382259939082L;


    /**
     * 委托代码
     */
    private String entrustId;
    
    /**
     * 操作类型
     */
    private String operateType;


    /**
     * 画面模式
     */
    private String pageModel;
    /**
     * 金融产品交易Entity
     */
    private TrdProductDeal trdProductDeal;
    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

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
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;
    /**
     * 交易费用明细表Entity
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;
    /**
     * 组合交易表
     */
    private PtlTrade ptlTrade;

    /**
     * 交易类金融产品Entity
     */
    @Valid
    private FinTradeProductDto finTradeProduct;
    
    /**
     * 金融产品交易业务流程其他信息
     */ 
    private WflTrdProductDealOth wflTrdProductDealOth;
    /**
     * 对手方 金融产品账户表
     */
    private StlFinProductAcct stlFinProductAcctCor;
    /**
     *本方 金融产品账户表
     */
    private StlFinProductAcct stlFinProductAcct;
    /**
     * 货币对
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.curPair",groups = ValidateGroupEdit.class)
    private String curPair;
    
    /**
     * 组合代码
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.portfolioId",groups = ValidateGroupEdit.class)
    private String portfolioId;
    
    /**
     * 生成方式
     */
    private String genType;
    
    /**
     * 交易类型
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.excDealType",groups = ValidateGroupEdit.class)
    private String excDealType;
    
    /**
     * 折美元金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    @NotEmpty(fieldName = "TrdForwexInvestTrade.usdAmt",groups = ValidateGroupEdit.class)
    private BigDecimal usdAmt;
    
    /**
     * 我方角色
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.ourRole",groups = ValidateGroupEdit.class)
    private String ourRole;
    
    /**
     * 交易模式
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.dealMode",groups = ValidateGroupEdit.class)
    private String dealMode;
    
    /**
     * 报价精度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal quotPre;
    
    /**
     * 交易费用
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    @NotEmpty(fieldName = "TrdForwexInvestTrade.feeAmt",groups = ValidateGroupEdit.class)
    private BigDecimal feeAmt;
    
    /**
     * 是否差额清算
     */
    private String isDifSqu;
    
    /**
     * 交易日历
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.calendarId",groups = ValidateGroupEdit.class)
    private String calendarId;
    
    /**
     * 期限 
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.term",groups = ValidateGroupEdit.class)
    private String termType;
    
    /**
     * 定价日
     */
    private Date pricedDate;
    
    /**
     * 差额交割参考汇率
     */
    private String refRate;
    
    /**
     * 定价日即期汇率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pricedDateRate;
    
    /**
     * 差额收付方向
     */
    private String difPs;
    
    /**
     * 差额收付金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal difPayAmt;
    
    /**
     * 交易币种可用持仓
     */
    private BigDecimal proAvailableCash;
    
    /**
     * 对应货币
     */
    private String corCcy;
    
    /**
     * 对应货币可用持仓
     */
    private BigDecimal corCurPos;
    
    /**
     * 本方账户
     */
    // 差额清算账户联动 delete by renping 20191031 start
    // @NotEmpty(fieldName = "TrdForwexInvestTrade.dealAcct",groups = ValidateGroupEdit.class)
    // 差额清算账户联动 delete by renping 20191031 end
    private String dealAcct;
    
    /**
     * 对手方账户
     */
    // 差额清算账户联动 delete by renping 20191031 start
    // @NotEmpty(fieldName = "TrdForwexInvestTrade.dealAcct",groups = ValidateGroupEdit.class)
    // 差额清算账户联动 delete by renping 20191031 end
    private String corAcct;
    
    /**
     * 撤销说明
     */
    private String revokeReason;
    
    /**
     * 有效状态
     */
    private String validStatus;
    
    /**
     * workflowId
     */
    private String workflowId;
    
    /**
     * workflowStatus
     */
    private String workflowStatus;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 金融产品分类
     */
    private FinProductType finProductType;


    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;
    
    /**
     * 是否差额清算，以及远端到期交易
     */
    private String isDifSquEx;
    /**
     * 子组合代码
     */
    private String actPortfolioId;

    /**
     * 到期交易的交易编号
     */
    private String tradeId1;
    
    private RiskCreditListCommonDto riskCreditListCommonDto;

    /**
     * 获取 tradeId1
     * 
     * @return tradeId1.
     */
    public String getTradeId1() {
        return tradeId1;
    }

    /**
     * 设置 tradeId1
     * 
     * @param tradeId1 tradeId1
     */
    public void setTradeId1(String tradeId1) {
        this.tradeId1 = tradeId1;
    }

    /**
     * 交易方向
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.excPs",groups = ValidateGroupEdit.class)
    public String getPs() {
    	return super.getPs();
    }
    
    /**
     * 交易日期
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.tradeDate",groups = ValidateGroupEdit.class)
    public Date getTradeDate() {
    	return super.getTradeDate();
    }
    
    /**
     * 交割日期
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.settleDate",groups = ValidateGroupEdit.class)
    public Date getSettleDate() {
    	return super.getSettleDate();
    }
    /**
     * 投资目的
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.invAim",groups = ValidateGroupEdit.class)
    public String getInvAim() {
    	return super.getInvAim();
    }
    
    /**
     * 即期汇率
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.unitCprice",groups = ValidateGroupEdit.class)
    public BigDecimal getUnitCprice() {
    	return super.getUnitCprice();
    }
    
    /**
     * 远期点
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.unitInt",groups = ValidateGroupEdit.class)
    public BigDecimal getUnitInt() {
    	return super.getUnitInt();
    }
    
    /**
     * 远期全价
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.unitFprice",groups = ValidateGroupEdit.class)
    public BigDecimal getUnitFprice() {
    	return super.getUnitFprice();
    }
    
    /**
     * 交易货币
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.ccy",groups = ValidateGroupEdit.class)
    public String getCcy() {
    	return super.getCcy();
    }
    
    /**
     * 交易货币金额
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.prinAmt",groups = ValidateGroupEdit.class)
    public BigDecimal getPrinAmt() {
    	return super.getPrinAmt();
    }
    
    /**
     * 对应货币金额
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.fpriceAmt",groups = ValidateGroupEdit.class)
    public BigDecimal getFpriceAmt() {
    	return super.getFpriceAmt();
    }
    
    /**
     * 清算模式
     */
    @NotEmpty(fieldName = "TrdForwexInvestTrade.deliveryType",groups = ValidateGroupEdit.class)
    public String getDeliveryType() {
    	return super.getDeliveryType();
    }
    
	/**
	 * 有效状态str
	 */
    public String getValidStatusStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getValidStatus());
    }
    
    /**
	 * 交易类型str
	 */
    public String getExcDealTypeStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.外汇交易类型, this.getExcDealType());
    }
    
    /**
	 * 交易方向str
	 */
    public String getPsStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    
    /**
	 * 清算类型str
	 */
    public String getDeliveryTypeStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.结算方式, super.getDeliveryType());
    }
    
    /**
	 * 交易日期str
	 */
    public String getTradeDateStr(){
    	return DateUtil.format(super.getTradeDate());
    }
    
    /**
	 * 交割日期str
	 */
    public String getSettleDateStr(){
    	return DateUtil.format(super.getSettleDate());
    }
    
    /**
   	 * 定价日期str
   	 */
   public String getPricedDateStr(){
   		return DateUtil.format(this.getPricedDate());
   }
   
   /**
	 * 币种str
	 */
   public String getCcyStr(){
   	return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
   }
   
   /**
  	 *对应币种str
  	 */
     public String getCorCcyStr(){
     	return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCorCcy());
     }
     
     /**
      * 期限品种str
      */
    public String getTermTypeStr() {
    	return SysDataUtil.getNameFromDataDict(DictCode.外汇期限, this.getTermType());
    }
     
    /**
     * 差额收付方向
     */
    public String getDifPsStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.getDifPs());
    }
    
    /**
     * 取得 交易编号
     *
     * @return 交易编号
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId() {
        return super.getTradeId();
    }
    /**
     * 
     * 业务类型
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType(){
        return super.getBusiType();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_5)
    public String getFinprodId() {
        return super.getFinprodId();
    }

	/**
	 * 获取 entrustId
	 * @return entrustId.
	 */
    @PrimaryKey(PrimaryKey.Field.PK_3)
	public String getEntrustId() {
		return entrustId;
	}

	/**
	 * 设置 entrustId
	 * @param entrustId entrustId
	 */
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
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
	 * 获取 curPair
	 * @return curPair.
	 */
	public String getCurPair() {
		return curPair;
	}

	/**
	 * 设置 curPair
	 * @param curPair curPair
	 */
	public void setCurPair(String curPair) {
		this.curPair = curPair;
	}
	/**
	 * 获取 pageModel
	 * @return pageModel.
	 */
	public String getPageModel() {
		return pageModel;
	}
	/**
	 * 设置 pageModel
	 * @param pageModel pageModel
	 */
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}
	/**
	 * 获取 trdProductDeal
	 * @return trdProductDeal.
	 */
	public TrdProductDeal getTrdProductDeal() {
		return trdProductDeal;
	}
	/**
	 * 设置 trdProductDeal
	 * @param trdProductDeal trdProductDeal
	 */
	public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
		this.trdProductDeal = trdProductDeal;
	}
	/**
	 * 获取 paTrdProductDeal
	 * @return paTrdProductDeal.
	 */
	public PaTrdProductDeal getPaTrdProductDeal() {
		return paTrdProductDeal;
	}
	/**
	 * 设置 paTrdProductDeal
	 * @param paTrdProductDeal paTrdProductDeal
	 */
	public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
		this.paTrdProductDeal = paTrdProductDeal;
	}
	/**
	 * 获取 bussTrdTransferOrder
	 * @return bussTrdTransferOrder.
	 */
	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}
	/**
	 * 设置 bussTrdTransferOrder
	 * @param bussTrdTransferOrder bussTrdTransferOrder
	 */
	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}
	/**
	 * 获取 uploadFileList
	 * @return uploadFileList.
	 */
	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}
	/**
	 * 设置 uploadFileList
	 * @param uploadFileList uploadFileList
	 */
	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
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
	/**
	 * 获取 trdInvestPersonInfo
	 * @return trdInvestPersonInfo.
	 */
	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
		return trdInvestPersonInfo;
	}
	/**
	 * 设置 trdInvestPersonInfo
	 * @param trdInvestPersonInfo trdInvestPersonInfo
	 */
	public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}
	/**
	 * 获取 trdProductDealAdd
	 * @return trdProductDealAdd.
	 */
	public TrdProductDealAdd getTrdProductDealAdd() {
		return trdProductDealAdd;
	}
	/**
	 * 设置 trdProductDealAdd
	 * @param trdProductDealAdd trdProductDealAdd
	 */
	public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
		this.trdProductDealAdd = trdProductDealAdd;
	}
	/**
	 * 获取 trdTradeFeeDetail
	 * @return trdTradeFeeDetail.
	 */
	public TrdTradeFeeDetail getTrdTradeFeeDetail() {
		return trdTradeFeeDetail;
	}
	/**
	 * 设置 trdTradeFeeDetail
	 * @param trdTradeFeeDetail trdTradeFeeDetail
	 */
	public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
		this.trdTradeFeeDetail = trdTradeFeeDetail;
	}
	/**
	 * 获取 ptlTrade
	 * @return ptlTrade.
	 */
	public PtlTrade getPtlTrade() {
		return ptlTrade;
	}
	/**
	 * 设置 ptlTrade
	 * @param ptlTrade ptlTrade
	 */
	public void setPtlTrade(PtlTrade ptlTrade) {
		this.ptlTrade = ptlTrade;
	}
	/**
	 * 获取 finTradeProduct
	 * @return finTradeProduct.
	 */
	public FinTradeProductDto getFinTradeProduct() {
		return finTradeProduct;
	}
	/**
	 * 设置 finTradeProduct
	 * @param finTradeProduct finTradeProduct
	 */
	public void setFinTradeProduct(FinTradeProductDto finTradeProduct) {
		this.finTradeProduct = finTradeProduct;
	}
	/**
	 * 获取 wflTrdProductDealOth
	 * @return wflTrdProductDealOth.
	 */
	public WflTrdProductDealOth getWflTrdProductDealOth() {
		return wflTrdProductDealOth;
	}
	/**
	 * 设置 wflTrdProductDealOth
	 * @param wflTrdProductDealOth wflTrdProductDealOth
	 */
	public void setWflTrdProductDealOth(WflTrdProductDealOth wflTrdProductDealOth) {
		this.wflTrdProductDealOth = wflTrdProductDealOth;
	}
	/**
	 * 获取 stlFinProductAcctCor
	 * @return stlFinProductAcctCor.
	 */
	public StlFinProductAcct getStlFinProductAcctCor() {
		return stlFinProductAcctCor;
	}
	/**
	 * 设置 stlFinProductAcctCor
	 * @param stlFinProductAcctCor stlFinProductAcctCor
	 */
	public void setStlFinProductAcctCor(StlFinProductAcct stlFinProductAcctCor) {
		this.stlFinProductAcctCor = stlFinProductAcctCor;
	}
	/**
	 * 获取 stlFinProductAcct
	 * @return stlFinProductAcct.
	 */
	public StlFinProductAcct getStlFinProductAcct() {
		return stlFinProductAcct;
	}

	/**
	 * 设置 stlFinProductAcct
	 * @param stlFinProductAcct stlFinProductAcct
	 */
	public void setStlFinProductAcct(StlFinProductAcct stlFinProductAcct) {
		this.stlFinProductAcct = stlFinProductAcct;
	}

	/**
	 * 获取 portfolioId
	 * @return portfolioId.
	 */
	public String getPortfolioId() {
		return portfolioId;
	}
	/**
	 * 设置 portfolioId
	 * @param portfolioId portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	/**
	 * 获取 genType
	 * @return genType.
	 */
	public String getGenType() {
		return genType;
	}
	/**
	 * 设置 genType
	 * @param genType genType
	 */
	public void setGenType(String genType) {
		this.genType = genType;
	}
	/**
	 * 获取 excDealType
	 * @return excDealType.
	 */
	public String getExcDealType() {
		return excDealType;
	}
	/**
	 * 设置 excDealType
	 * @param excDealType excDealType
	 */
	public void setExcDealType(String excDealType) {
		this.excDealType = excDealType;
	}
	/**
	 * 获取 usdAmt
	 * @return usdAmt.
	 */
	public BigDecimal getUsdAmt() {
		return usdAmt;
	}
	/**
	 * 设置 usdAmt
	 * @param usdAmt usdAmt
	 */
	public void setUsdAmt(BigDecimal usdAmt) {
		this.usdAmt = usdAmt;
	}
	/**
	 * 获取 ourRole
	 * @return ourRole.
	 */
	public String getOurRole() {
		return ourRole;
	}
	/**
	 * 设置 ourRole
	 * @param ourRole ourRole
	 */
	public void setOurRole(String ourRole) {
		this.ourRole = ourRole;
	}
	/**
	 * 获取 dealMode
	 * @return dealMode.
	 */
	public String getDealMode() {
		return dealMode;
	}
	/**
	 * 设置 dealMode
	 * @param dealMode dealMode
	 */
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}
	/**
	 * 获取 quotPre
	 * @return quotPre.
	 */
	public BigDecimal getQuotPre() {
		return quotPre;
	}
	/**
	 * 设置 quotPre
	 * @param quotPre quotPre
	 */
	public void setQuotPre(BigDecimal quotPre) {
		this.quotPre = quotPre;
	}
	/**
	 * 获取 feeAmt
	 * @return feeAmt.
	 */
	public BigDecimal getFeeAmt() {
		return feeAmt;
	}
	/**
	 * 设置 feeAmt
	 * @param feeAmt feeAmt
	 */
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	/**
	 * 获取 isDifSqu
	 * @return isDifSqu.
	 */
	public String getIsDifSqu() {
		return isDifSqu;
	}
	/**
	 * 设置 isDifSqu
	 * @param isDifSqu isDifSqu
	 */
	public void setIsDifSqu(String isDifSqu) {
		this.isDifSqu = isDifSqu;
	}
	/**
	 * 获取 calendarId
	 * @return calendarId.
	 */
	public String getCalendarId() {
		return calendarId;
	}
	/**
	 * 设置 calendarId
	 * @param calendarId calendarId
	 */
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
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
	 * 获取 pricedDate
	 * @return pricedDate.
	 */
	public Date getPricedDate() {
		return pricedDate;
	}
	/**
	 * 设置 pricedDate
	 * @param pricedDate pricedDate
	 */
	public void setPricedDate(Date pricedDate) {
		this.pricedDate = pricedDate;
	}
	/**
	 * 获取 refRate
	 * @return refRate.
	 */
	public String getRefRate() {
		return refRate;
	}
	/**
	 * 设置 refRate
	 * @param refRate refRate
	 */
	public void setRefRate(String refRate) {
		this.refRate = refRate;
	}
	/**
	 * 获取 pricedDateRate
	 * @return pricedDateRate.
	 */
	public BigDecimal getPricedDateRate() {
		return pricedDateRate;
	}
	/**
	 * 设置 pricedDateRate
	 * @param pricedDateRate pricedDateRate
	 */
	public void setPricedDateRate(BigDecimal pricedDateRate) {
		this.pricedDateRate = pricedDateRate;
	}
	/**
	 * 获取 difPs
	 * @return difPs.
	 */
	public String getDifPs() {
		return difPs;
	}
	/**
	 * 设置 difPs
	 * @param difPs difPs
	 */
	public void setDifPs(String difPs) {
		this.difPs = difPs;
	}
	/**
	 * 获取 difPayAmt
	 * @return difPayAmt.
	 */
	public BigDecimal getDifPayAmt() {
		return difPayAmt;
	}
	/**
	 * 设置 difPayAmt
	 * @param difPayAmt difPayAmt
	 */
	public void setDifPayAmt(BigDecimal difPayAmt) {
		this.difPayAmt = difPayAmt;
	}
	/**
	 * 获取 proAvailableCash
	 * @return proAvailableCash.
	 */
	public BigDecimal getProAvailableCash() {
		return proAvailableCash;
	}
	/**
	 * 设置 proAvailableCash
	 * @param proAvailableCash proAvailableCash
	 */
	public void setProAvailableCash(BigDecimal proAvailableCash) {
		this.proAvailableCash = proAvailableCash;
	}
	/**
	 * 获取 corCcy
	 * @return corCcy.
	 */
	public String getCorCcy() {
		return corCcy;
	}
	/**
	 * 设置 corCcy
	 * @param corCcy corCcy
	 */
	public void setCorCcy(String corCcy) {
		this.corCcy = corCcy;
	}
	/**
	 * 获取 corCurPos
	 * @return corCurPos.
	 */
	public BigDecimal getCorCurPos() {
		return corCurPos;
	}
	/**
	 * 设置 corCurPos
	 * @param corCurPos corCurPos
	 */
	public void setCorCurPos(BigDecimal corCurPos) {
		this.corCurPos = corCurPos;
	}
	/**
	 * 获取 dealAcct
	 * @return dealAcct.
	 */
	public String getDealAcct() {
		return dealAcct;
	}
	/**
	 * 设置 dealAcct
	 * @param dealAcct dealAcct
	 */
	public void setDealAcct(String dealAcct) {
		this.dealAcct = dealAcct;
	}
	/**
	 * 获取 corAcct
	 * @return corAcct.
	 */
	public String getCorAcct() {
		return corAcct;
	}
	/**
	 * 设置 corAcct
	 * @param corAcct corAcct
	 */
	public void setCorAcct(String corAcct) {
		this.corAcct = corAcct;
	}
	/**
	 * 获取 revokeReason
	 * @return revokeReason.
	 */
	public String getRevokeReason() {
		return revokeReason;
	}
	/**
	 * 设置 revokeReason
	 * @param revokeReason revokeReason
	 */
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	/**
	 * 获取 validStatus
	 * @return validStatus.
	 */
	public String getValidStatus() {
		return validStatus;
	}

	/**
	 * 设置 validStatus
	 * @param validStatus validStatus
	 */
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	/**
	 * 获取 workflowId
	 * @return workflowId.
	 */
	public String getWorkflowId() {
		return workflowId;
	}

	/**
	 * 设置 workflowId
	 * @param workflowId workflowId
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	/**
	 * 获取 workflowStatus
	 * @return workflowStatus.
	 */
	public String getWorkflowStatus() {
		return workflowStatus;
	}

	/**
	 * 设置 workflowStatus
	 * @param workflowStatus workflowStatus
	 */
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
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
	 * 获取 finProductType
	 * @return finProductType.
	 */
	public FinProductType getFinProductType() {
		return finProductType;
	}

	/**
	 * 设置 finProductType
	 * @param finProductType finProductType
	 */
	public void setFinProductType(FinProductType finProductType) {
		this.finProductType = finProductType;
	}

	/**
	 * 获取 stlPayOrdersList
	 * @return stlPayOrdersList.
	 */
	public List<StlPayOrdersDto> getStlPayOrdersList() {
		return stlPayOrdersList;
	}

	/**
	 * 设置 stlPayOrdersList
	 * @param stlPayOrdersList stlPayOrdersList
	 */
	public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
		this.stlPayOrdersList = stlPayOrdersList;
	}

	/**
	 * 获取 isDifSquEx
	 * @return isDifSquEx.
	 */
    @PrimaryKey(PrimaryKey.Field.PK_4)
	public String getIsDifSquEx() {
		return isDifSquEx;
	}

	/**
	 * 设置 isDifSquEx
	 * @param isDifSquEx isDifSquEx
	 */
	public void setIsDifSquEx(String isDifSquEx) {
		this.isDifSquEx = isDifSquEx;
	}
    
    /**
     * 获取 actPortfolioId
     * @return actPortfolioId.
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }
    
    /**
     * 设置 actPortfolioId
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }

    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }
}
