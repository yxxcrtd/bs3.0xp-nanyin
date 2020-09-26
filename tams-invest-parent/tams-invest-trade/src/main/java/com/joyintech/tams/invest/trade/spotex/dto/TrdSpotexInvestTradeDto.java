/*
 * 文件名：TrdSpotexInvestTrade001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.spotex.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.spotex.validator.TrdSpotexInvestTradeDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 外汇即期交易画面Dto
 * 
 * @author wangguoxian
 * @version 1.0 2019年7月2日
 * @see TrdSpotexInvestTradeDto
 * @since 1.0
 */
@PageId("TrdSpotexInvestTrade002")
@ValidLogic(targetClass = TrdSpotexInvestTradeDtoValidator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdSpotexInvestTradeDto extends TrdProductDeal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 外汇交易类型
     */
    private String excDealType;

    /**
     * 外汇交易类型名称
     */
    private String excDealTypeName;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 货币对名称
     */
    private String curPairName;

    /**
     * 交易方向名称
     */
    private String psName;

    /**
     * 有效状态
     */
    private String validStatusName;

    /**
     * 交易日
     */
    private String tradeDateText;
    
    /**
     * 交易日开始
     */
    private Date tradeDateBegin;

    /**
     * 交易日结束
     */
    private Date tradeDateEnd;

    /**
     * 清算速度-数据字典表
     */
    private String accountSpeed;

    /**
     * 清算速度名称
     */
    private String settleSpeedName;

    /**
     * 交割日
     */
    private String settleDateText;

    /**
     * 交易货币
     */
    private String dealCur;

    /**
     * 交易货币名称
     */
    private String dealCurName;

    /**
     * 折美元金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal usdAmt;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 交易模式
     */
    private String dealMode;

    /**
     * 我方角色
     */
    private String ourRole;

    private List<TrdSpotexInvestTradeDto> selectedItem;

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
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 组合交易
     */
    private PtlTrade ptlTrade;

    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;
    
    /**
     * 交易类金融产品
     */
    private FinTradeProduct finTradeProduct;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    
    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;

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
     * 对应货币名称
     */
    private String ccyName;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 金融产品代码（系统自动生成）
     */
    private String systemFinprodId;
    
    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 费用明细表
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;
    
    private RiskCreditListCommonDto riskCreditListCommonDto;
    
    /**
     * 交易货币可用持仓
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal proAvailableCash;
    
    /**
     * 对应货币可用持仓
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal corProAvailableCash;
    /**
     * 子组合代码
     */
    private String actPortfolioId;

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
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType() {
        return DictCodeListExt2.共通业务类型_外汇即期投资交易.getValue();
    }

    /**
     * 获取组合代码
     *
     * @return 组合代码
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 获取清算速度-数据字典 表
     * 
     * @return 清算速度-数据字典表
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.settleSpeed", groups = {ValidateGroupEdit.class})
    public String getAccountSpeed() {
        if (super.getSettleSpeed() == null) {
            return null;
        }
        long settleSpeedTmp = super.getSettleSpeed() + 1;
        String accountSpeedTmp = null;
        if (settleSpeedTmp < 10) {
            accountSpeedTmp = "0" + settleSpeedTmp;
        }
        else {
            accountSpeedTmp = Long.toString(settleSpeedTmp);
        }
        return accountSpeedTmp;
    }

    /**
     * 取得 交易日期
     *
     * @return 交易日期
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.tradeDate", groups = {ValidateGroupEdit.class})
    @Override
    public Date getTradeDate() {
        return super.getTradeDate();
    }

    /**
     * 取得 交割日期
     *
     * @return 交割日期
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.settleDate", groups = {ValidateGroupEdit.class})
    @Override
    public Date getSettleDate() {
        return super.getSettleDate();
    }

    /**
     * 取得 交易方向，买入、卖出
     *
     * @return 交易方向，买入、卖出
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.ps", groups = {ValidateGroupEdit.class})
    @Override
    public String getPs() {
        return super.getPs();
    }


    /**
     * 取得 单位净价
     *
     * @return 单位净价
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.unitCprice", groups = {ValidateGroupEdit.class})
    @Override
    public BigDecimal getUnitCprice() {
        return super.getUnitCprice();
    }

    /**
     * 获取交易日历
     * 
     * @return 交易日历
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.calendarId", groups = {ValidateGroupEdit.class})
    public String getCalendarId() {
        return calendarId;
    }
    
    /**
     * 获取外汇交易类型
     * 
     * @return 外汇交易类型
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.excDealType", groups = {ValidateGroupEdit.class})
    public String getExcDealType() {
        return excDealType;
    }
    /**
     * 获取货币对
     * 
     * @return 货币对
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.curPair", groups = {ValidateGroupEdit.class})
    public String getCurPair() {
        return curPair;
    }
    
    /**
     * 获取我方角色
     * 
     * @return 我方角色
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.ourRole", groups = {ValidateGroupEdit.class})
    public String getOurRole() {
        return ourRole;
    }
    /**
     * 获取交易模式
     * 
     * @return 交易模式
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.dealMode", groups = {ValidateGroupEdit.class})
    public String getDealMode() {
        return dealMode;
    }
    /**
     * 
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.deliveryType", groups = {ValidateGroupEdit.class})
    @Override
    public String getDeliveryType() {
        return super.getDeliveryType();
    }
    
    /**
     * 获取交易货币
     * 
     * @return 交易货币
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.dealCur", groups = {ValidateGroupEdit.class})
    public String getDealCur() {
        return dealCur;
    }
    /**
     * 
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.prinAmt", groups = {ValidateGroupEdit.class})
    @Override
    public BigDecimal getPrinAmt() {
        return super.getPrinAmt();
    }
    /**
     * 
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.ccy", groups = {ValidateGroupEdit.class})
    @Override
    public String getCcy() {
        return super.getCcy();
    }
    
    /**
     * 
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.tradeAmt", groups = {ValidateGroupEdit.class})
    @Override
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }
    
    /**
     * 获取折美元金额
     * 
     * @return 折美元金额
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.usdAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getUsdAmt() {
        return usdAmt;
    }
    
    /**
     * 
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdSpotexInvestTrade.feeAmt", groups = {ValidateGroupEdit.class})
    @Override
    public BigDecimal getFeeAmt() {
        return super.getFeeAmt();
    }

    /**
     * 设置交易日历
     * 
     * @param calendarId 交易日历
     * @see
     * @since 1.0
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 entrustId
     *
     * @return entrustId
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置清算速度-数据字典表
     * 
     * @param accountSpeed 清算速度-数据字典表
     * @see
     * @since 1.0
     */
    public void setAccountSpeed(String accountSpeed) {
        if (StringUtil.isNotEmpty(accountSpeed)) {
            super.setSettleSpeed(Long.parseLong(accountSpeed) - 1);
        }

    }

    /**
     * 获取清算速度名称
     * 
     * @return 清算速度名称
     * @see
     * @since 1.0
     */
    public String getSettleSpeedName() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getAccountSpeed());
    }

    

    /**
     * 设置外汇交易类型
     * 
     * @param excDealType 外汇交易类型
     */
    public void setExcDealType(String excDealType) {
        this.excDealType = excDealType;
    }

    /**
     * 获得外汇交易类型名称
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getExcDealTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.外汇交易类型, this.getExcDealType());
    }

    /**
     * 设置外汇交易类型名称
     * 
     * @param excDealTypeName 外汇交易类型名称
     * @see
     * @since 1.0
     */
    public void setExcDealTypeName(String excDealTypeName) {
        this.excDealTypeName = excDealTypeName;
    }

    

    /**
     * 设置货币对
     * 
     * @param curPair 货币对
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair;
    }

    /**
     * 获取货币对名称
     * 
     * @return 货币对名称
     * @see
     * @since 1.0
     */
    public String getCurPairName() {
        return SysDataUtil.getNameFromDataDict(DictCode.货币对, this.getCurPair());
    }

    /**
     * 设置货币对名称
     * 
     * @param curPairName 货币对名称
     * @see
     * @since 1.0
     */
    public void setCurPairName(String curPairName) {
        this.curPairName = curPairName;
    }

    

    /**
     * 设置交易货币
     * 
     * @param dealCur 交易货币
     * @see
     * @since 1.0
     */
    public void setDealCur(String dealCur) {
        this.dealCur = dealCur;
    }

    /**
     * 获取交易货币名称
     * 
     * @return 交易货币名称
     * @see
     * @since 1.0
     */
    public String getDealCurName() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getDealCur());
    }

    /**
     * 设置交易货币名称
     * 
     * @param dealCurName 交易货币名称
     * @see
     * @since 1.0
     */
    public void setDealCurName(String dealCurName) {
        this.dealCurName = dealCurName;
    }

    

    /**
     * 设置折美元金额
     * 
     * @param usdAmt 折美元金额
     * @see
     * @since 1.0
     */
    public void setUsdAmt(BigDecimal usdAmt) {
        this.usdAmt = usdAmt;
    }

    /**
     * 获取 riskTrialRetDto
     * 
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    /**
     * 设置 riskTrialRetDto
     * 
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 trdInvestPersonInfo
     *
     * @return trdInvestPersonInfo
     */
    public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
        return trdInvestPersonInfo;
    }

    /**
     * 设置 trdInvestPersonInfo
     *
     * @param trdInvestPersonInfo trdInvestPersonInfo
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
        this.trdInvestPersonInfo = trdInvestPersonInfo;
    }

    /**
     * 获取 validStatus
     *
     * @return validStatus
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 设置 validStatus
     *
     * @param validStatus validStatus
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 设置 entrustId
     *
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     *
     * @return pageModel
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 trdProductDealAdd
     *
     * @return trdProductDealAdd
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     *
     * @param trdProductDealAdd trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 ptlTrade
     *
     * @return ptlTrade
     */
    public PtlTrade getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     *
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(PtlTrade ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 paTrdProductDeal
     *
     * @return paTrdProductDeal
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     *
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
    }

    /**
     * 获取 uploadFileList
     *
     * @return uploadFileList.
     */
    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    /**
     * 设置 uploadFileList
     *
     * @param uploadFileList uploadFileList
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    /**
     * 获取 workflowStatus
     *
     * @return workflowStatus.
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 workflowStatus
     *
     * @param workflowStatus workflowStatus
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * 获取 ccyName
     *
     * @return validStatusName
     */
    public String getvalidStatusName() {
        validStatusName = SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, validStatus);
        return validStatusName;
    }

    /**
     * 设置 validStatusName
     *
     * @param validStatusName validStatusName
     */
    public void setvalidStatusName(String validStatusName) {
        this.validStatusName = validStatusName;
    }

    /**
     * 获取 psText
     *
     * @return psText.
     */
    public String getPsName() {
        psName = SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.getPs());
        return psName;
    }

    /**
     * 设置 psText
     *
     * @param psText psText
     */
    public void setPsName(String psName) {
        this.psName = psName;
    }

    /**
     * 获取 ccyName
     *
     * @return ccyName.
     */
    public String getCcyName() {
        ccyName = SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
        return ccyName;
    }

    /**
     * 设置 ccyName
     *
     * @param ccyName ccyName
     */
    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    /**
     * 获取 tradeDateText
     *
     * @return tradeDateText.
     */
    public String getTradeDateText() {
        tradeDateText = DateUtil.format(this.getTradeDate());
        return tradeDateText;
    }

    /**
     * 设置 tradeDateText
     *
     * @param tradeDateText tradeDateText
     */
    public void setTradeDateText(String tradeDateText) {
        this.tradeDateText = tradeDateText;
    }

    /**
     * 获取 settleDateText
     *
     * @return settleDateText.
     */
    public String getSettleDateText() {
        settleDateText = DateUtil.format(this.getSettleDate());
        return settleDateText;
    }

    /**
     * 设置 settleDateText
     *
     * @param settleDateText settleDateText
     */
    public void setSettleDateText(String settleDateText) {
        this.settleDateText = settleDateText;
    }

    /**
     * 获取 revokeReason
     *
     * @return revokeReason.
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     *
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 systemFinprodId
     *
     * @return systemFinprodId.
     */
    public String getSystemFinprodId() {
        return systemFinprodId;
    }

    /**
     * 设置 systemFinprodId
     *
     * @param systemFinprodId systemFinprodId
     */
    public void setSystemFinprodId(String systemFinprodId) {
        this.systemFinprodId = systemFinprodId;
    }

    /**
     * 获取费用明细
     * 
     * @return
     * @see
     * @since 1.0
     */
    public TrdTradeFeeDetail getTrdTradeFeeDetail() {
        return trdTradeFeeDetail;
    }

    /**
     * 设置费用明细
     * 
     * @param trdTradeFeeDetail 费用明细
     * @see
     * @since 1.0
     */
    public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
        this.trdTradeFeeDetail = trdTradeFeeDetail;
    }

    

    /**
     * 设置交易模式
     * 
     * @param dealMode 交易模式
     * @see
     * @since 1.0
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    

    /**
     * 设置我方角色
     * 
     * @param ourRole 我方角色
     * @see
     * @since 1.0
     */
    public void setOurRole(String ourRole) {
        this.ourRole = ourRole;
    }

    /**
     * 
     * 获取交易货币可用持仓
     * @return 交易货币可用持仓
     * @see
     * @since 1.0
     */
    public BigDecimal getProAvailableCash() {
        return proAvailableCash;
    }

    /**
     * 
     * 设置交易货币可用持仓
     * @param proAvailableCash 交易货币可用持仓
     * @see
     * @since 1.0
     */
    public void setProAvailableCash(BigDecimal proAvailableCash) {
        this.proAvailableCash = proAvailableCash;
    }

    /**
     * 
     * 获取对应货币可用持仓
     * @return 对应货币可用持仓
     * @see
     * @since 1.0
     */
    public BigDecimal getCorProAvailableCash() {
        return corProAvailableCash;
    }

    /**
     * 
     * 设置对应货币可用持仓
     * @param corProAvailableCash 对应货币可用持仓
     * @see
     * @since 1.0
     */
    public void setCorProAvailableCash(BigDecimal corProAvailableCash) {
        this.corProAvailableCash = corProAvailableCash;
    }

    /**
     * 
     * 获取交易类金融产品
     * @return 交易类金融产品
     * @see
     * @since 1.0
     */
    public FinTradeProduct getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 
     * 设置交易类金融产品
     * @param finTradeProduct 交易类金融产品
     * @see
     * @since 1.0
     */
    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 
     * 获取划款指令
     * @return 划款指令
     * @see
     * @since 1.0
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 
     * 设置划款指令
     * @param stlPayOrdersList 划款指令
     * @see
     * @since 1.0
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 
     * 获取交易日开始
     * @return 交易日开始
     * @see
     * @since 1.0
     */
    public Date getTradeDateBegin() {
        return tradeDateBegin;
    }

    /**
     * 
     * 设置交易日开始
     * @param tradeDateBegin 交易日开始
     * @see
     * @since 1.0
     */
    public void setTradeDateBegin(Date tradeDateBegin) {
        this.tradeDateBegin = tradeDateBegin;
    }

    /**
     * 
     * 获取交易日结束
     * @return 交易日结束
     * @see
     * @since 1.0
     */
    public Date getTradeDateEnd() {
        return tradeDateEnd;
    }

    /**
     * 
     * 设置交易日结束
     * @param tradeDateEnd 交易日结束
     * @see
     * @since 1.0
     */
    public void setTradeDateEnd(Date tradeDateEnd) {
        this.tradeDateEnd = tradeDateEnd;
    }

    public List<TrdSpotexInvestTradeDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<TrdSpotexInvestTradeDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 金融产品分类
     */
    private FinProductType finProductType;

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
