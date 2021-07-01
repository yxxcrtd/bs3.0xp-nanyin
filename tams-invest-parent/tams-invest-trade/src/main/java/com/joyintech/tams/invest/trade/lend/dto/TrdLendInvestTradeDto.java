/*
 * 文件名：TrdLendInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.lend.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflTrdProductDealOth;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.lend.validator.TrdLendInvestTradeDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("TrdLendInvestTrade002")
@ValidLogic(targetClass = TrdLendInvestTradeDtoValidator.class)
public class TrdLendInvestTradeDto extends TrdProductDeal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

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
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     *
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodType2() {
        return super.getFinprodType2();
    }

    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 有效状态
     */
    private String validStatusText;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.portfolioId")
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
     * 拆借类型
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.paBussType")
    private String paBussType;

    /**
     * 拆借类型
     */
    private String paBussTypeText;

    /**
     * 回购拆借方向
     */
    private String busiPsText;

    /**
     * 币种
     */
    private String ccyText;

    /**
     * 交易日期(文本，导出用)
     */
    private String tradeDateText;

    /**
     * 是否保本
     */
    private String isGuarantee;

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
     * 金融产品交易业务流程其他信息
     */
    private WflTrdProductDealOth wflTrdProductDealOth;

    /**
     * 金融产品分类
     */
    private FinProductType finProductType;

    /**
     * 交易类金融产品
     */
    @Valid
    private FinTradeProductDto finTradeProduct;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

    /**
     * 附件上传
     */
    private List<UploadFile> fileArray;

    /**
     * 委托金额
     */
    private BigDecimal entrustNum;

    /**
     * 委托所输利率
     */
    private BigDecimal entrustIntRate;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 托管户剩余现金
     */
    private BigDecimal secAcctBalance;

    /**
     * 组合T日可用现金
     */
    private BigDecimal avalibleCash;


    /**
     * 组合T日可用现金,用于APP显示
     *
     * @return
     */
    private String appAvalibleCash;

    /**
     * 组合T+1日可用现金,用于APP显示
     *
     * @return
     */
    private String appAvalibleCash1;

    /**
     * 授信来源
     */
    private String creditSource;

    /**
     * 回购/拆借方向，正回购、逆回购、拆入、拆出
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.ps")
    private String busiPs;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    private RiskCreditListCommonDto riskCreditListCommonDto;

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
     * 取得 交易日期
     *
     * @return 交易日期
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.tradeDate")
    @Override
    public Date getTradeDate() {
        return super.getTradeDate();
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
     * 获取 isExercise
     *
     * @return isExercise
     */
    public String getIsExercise() {
        return isExercise;
    }

    /**
     * 设置 isExercise
     *
     * @param isExercise isExercise
     */
    public void setIsExercise(String isExercise) {
        this.isExercise = isExercise;
    }

    /**
     * 是否行权
     */
    private String isExercise;

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
     * 获取 entrustId
     *
     * @return entrustId
     */
    //huwenbin 指令接收交易录单验证数据已存在流程中
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getEntrustId() {
        return entrustId;
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
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
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
     * 获取 paBussType
     *
     * @return paBussType
     */
    public String getPaBussType() {
        return paBussType;
    }

    /**
     * 设置 paBussType
     *
     * @param paBussType paBussType
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    /**
     * 获取 finTradeProduct
     *
     * @return finTradeProduct
     */
    public FinTradeProductDto getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 设置 finTradeProduct
     *
     * @param finTradeProduct finTradeProduct
     */
    public void setFinTradeProduct(FinTradeProductDto finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 获取 paBussTypeText
     *
     * @return paBussTypeText
     */
    public String getPaBussTypeText() {
        paBussTypeText = SysDataUtil.getNameFromDataDict(DictCode.拆借类型, paBussType);
        return paBussTypeText;
    }

    /**
     * 获取 busiPsText
     *
     * @return busiPsText
     */
    public String getBusiPsText() {
        if (StringUtil.isNotBlank(this.getFinprodType2())) {
            busiPsText = SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,
                    this.getFinprodType2());
        }
        // busiPsText = finTradeProduct != null ? SysDataUtil.getNameFromDataDict(
        // DictCode.回购拆借方向, finTradeProduct.getBusiPs()) : null;
        return busiPsText;
    }

    /**
     * 获取 ccyText
     *
     * @return ccyText
     */
    public String getCcyText() {
        ccyText = SysDataUtil.getNameFromDataDict(DictCode.币种, getCcy());
        return ccyText;
    }

    /**
     * 获取 ccyText
     *
     * @return validStatusText
     */
    public String getValidStatusText() {
        validStatusText = SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, validStatus);
        return validStatusText;
    }

    /**
     * 获取 isGuarantee
     *
     * @return isGuarantee
     */
    public String getIsGuarantee() {
        return isGuarantee;
    }

    /**
     * 设置 isGuarantee
     *
     * @param isGuarantee isGuarantee
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    /**
     * 获取 finProductType
     *
     * @return finProductType
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 finProductType
     *
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    /**
     * 获取 tradeDateText
     *
     * @return tradeDateText.
     */
    public String getTradeDateText() {
        tradeDateText = DateUtil.format(getTradeDate());
        return tradeDateText;
    }

    /**
     * 设置 validStatusText
     *
     * @param validStatusText validStatusText
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    /**
     * 设置 paBussTypeText
     *
     * @param paBussTypeText paBussTypeText
     */
    public void setPaBussTypeText(String paBussTypeText) {
        this.paBussTypeText = paBussTypeText;
    }

    /**
     * 设置 busiPsText
     *
     * @param busiPsText busiPsText
     */
    public void setBusiPsText(String busiPsText) {
        this.busiPsText = busiPsText;
    }

    /**
     * 设置 ccyText
     *
     * @param ccyText ccyText
     */
    public void setCcyText(String ccyText) {
        this.ccyText = ccyText;
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
     * 获取 wflTrdProductDealOth
     *
     * @return wflTrdProductDealOth.
     */
    public WflTrdProductDealOth getWflTrdProductDealOth() {
        return wflTrdProductDealOth;
    }

    /**
     * 设置 wflTrdProductDealOth
     *
     * @param wflTrdProductDealOth wflTrdProductDealOth
     */
    public void setWflTrdProductDealOth(WflTrdProductDealOth wflTrdProductDealOth) {
        this.wflTrdProductDealOth = wflTrdProductDealOth;
    }

    /**
     * 获取 fileArray
     *
     * @return fileArray.
     */
    public List<UploadFile> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     *
     * @param fileArray fileArray
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 entrustNum
     *
     * @return entrustNum.
     */
    public BigDecimal getEntrustNum() {
        return entrustNum;
    }

    /**
     * 设置 entrustNum
     *
     * @param entrustNum entrustNum
     */
    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    /**
     * 获取 entrustIntRate
     *
     * @return entrustIntRate.
     */
    public BigDecimal getEntrustIntRate() {
        return entrustIntRate;
    }

    /**
     * 设置 entrustIntRate
     *
     * @param entrustIntRate entrustIntRate
     */
    public void setEntrustIntRate(BigDecimal entrustIntRate) {
        this.entrustIntRate = entrustIntRate;
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
     * 获取 secAcctBalance
     *
     * @return secAcctBalance.
     */
    public BigDecimal getSecAcctBalance() {
        return secAcctBalance;
    }

    /**
     * 设置 secAcctBalance
     *
     * @param secAcctBalance secAcctBalance
     */
    public void setSecAcctBalance(BigDecimal secAcctBalance) {
        this.secAcctBalance = secAcctBalance;
    }

    /**
     * 获取 avalibleCash
     *
     * @return avalibleCash.
     */
    public BigDecimal getAvalibleCash() {
        return avalibleCash;
    }

    /**
     * 设置 avalibleCash
     *
     * @param avalibleCash avalibleCash
     */
    public void setAvalibleCash(BigDecimal avalibleCash) {
        this.avalibleCash = avalibleCash;
    }

    /**
     * 获取 busiPs
     *
     * @return busiPs.
     */
    public String getBusiPs() {
        return busiPs;
    }

    /**
     * 设置 busiPs
     *
     * @param busiPs busiPs
     */
    public void setBusiPs(String busiPs) {
        this.busiPs = busiPs;
    }

    /**
     * 获取 actPortfolioId
     *
     * @return actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     *
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

    public String getAppAvalibleCash() {
        return appAvalibleCash;
    }

    public void setAppAvalibleCash(String appAvalibleCash) {
        this.appAvalibleCash = appAvalibleCash;
    }

    public String getAppAvalibleCash1() {
        return appAvalibleCash1;
    }

    public void setAppAvalibleCash1(String appAvalibleCash1) {
        this.appAvalibleCash1 = appAvalibleCash1;
    }

    public String getCreditSource() {
        return creditSource;
    }

    public void setCreditSource(String creditSource) {
        this.creditSource = creditSource;
    }
}
