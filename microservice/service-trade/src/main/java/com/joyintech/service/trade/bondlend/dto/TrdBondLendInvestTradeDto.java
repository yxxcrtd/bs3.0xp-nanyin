/*
 * 文件名：TrdBondInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年6月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.bondlend.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.trade.bond.dto.TrdInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.*;

import javax.validation.Valid;

/**
 * 债券借贷交易Dto
 *
 * @author 裴家俊
 * @version 1.0 2019年6月12日
 * @see TrdBondLendInvestTradeDto
 * @since 1.0
 */
public class TrdBondLendInvestTradeDto extends TrdProductDeal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8259658424290761601L;

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
     * 资产名称
     */
    private String finprodName;

    /**
     * 清算速度
     */
    private String settleSpeedStr;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 期限(天数，交易类金融产品表中)
     */
    private Long term;
    /**
     * 首次结算方式(金融产品交易中结算方式)
     */
    private String deliveryType;
    /**
     * 到期结算方式(交易类金融产品中到期结算方式)
     */
    private String mDeliveryType;

    /**
     * 争议解决方案
     */
    private String conflictSolveWay;

    /**
     * 借贷费率（交易类金融产品中的到期收益率）
     */
    private BigDecimal mYield;

    /**
     * 借贷费用（交易类金融产品中的到期金额）
     */
    private BigDecimal mTradeAmt;
    /**
     * 实际占券天数
     */
    private String actAmtDays;
    /**
     * 出/回款账号
     */
    private  String acctOwenAcctId;
    /**
     * 对手方账号
     */
    private  String acctRivalId;
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
     * 金融产品分类
     */
    private FinProductType finProductType;

    /**
     * 金融产品账户信息
     */
    private StlFinProductAcct stlFinProductAcct;

    /**
     * 金融产品交易业务流程信息
     */
    private WflTrdProductDeal wflTrdProductDeal;

    /**
     * 金融产品交易业务流程其他信息
     */
    private WflTrdProductDealOth wflTrdProductDealOth;
    /**
     * 划款指令
     */
    private List<StlPayOrders> bussTrdTransferOrder;

    /**
     * 人员信息
     */
    private TrdInvestPersonInfoDto trdInvestPersonInfo;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 券面总额
     */
    private BigDecimal totalFaceValue;

    /**
     * 金融产品代码（系统自动生成）
     */
    private String systemFinprodId;

    private String supplyClause;
    /**
     * 质押劵置换安排
     */
    private String exchangePledge;

    public String getExchangePledge() {
        return exchangePledge;
    }

    public void setExchangePledge(String exchangePledge) {
        this.exchangePledge = exchangePledge;
    }
    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<StlPayOrders> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<StlPayOrders> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 trdInvestPersonInfo
     *
     * @return trdInvestPersonInfo
     */
    public TrdInvestPersonInfoDto getTrdInvestPersonInfo() {
        return trdInvestPersonInfo;
    }

    /**
     * 设置 trdInvestPersonInfo
     *
     * @param trdInvestPersonInfo trdInvestPersonInfo
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfoDto trdInvestPersonInfo) {
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
     * 获取 finprodName
     *
     * @return finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     *
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 settleSpeedStr
     *
     * @return settleSpeedStr.
     */
    public String getSettleSpeedStr() {
        return settleSpeedStr;
    }

    /**
     * 设置 settleSpeedStr
     *
     * @param settleSpeedStr settleSpeedStr
     */
    public void setSettleSpeedStr(String settleSpeedStr) {
        this.settleSpeedStr = settleSpeedStr;
    }

    /**
     * 获取 calendarId
     *
     * @return calendarId
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     *
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
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
     * 获取 calculateField
     *
     * @return calculateField
     */
    public String getCalculateField() {
        return calculateField;
    }

    /**
     * 设置 calculateField
     *
     * @param calculateField calculateField
     */
    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField;
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
     * 获取 totalFaceValue
     *
     * @return totalFaceValue.
     */
    public BigDecimal getTotalFaceValue() {
        return totalFaceValue;
    }

    /**
     * 设置 totalFaceValue
     *
     * @param totalFaceValue totalFaceValue
     */
    public void setTotalFaceValue(BigDecimal totalFaceValue) {
        this.totalFaceValue = totalFaceValue;
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


    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    @Override
    public String getDeliveryType() {
        return deliveryType;
    }

    @Override
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getmDeliveryType() {
        return mDeliveryType;
    }

    public void setmDeliveryType(String mDeliveryType) {
        this.mDeliveryType = mDeliveryType;
    }

    public String getConflictSolveWay() {
        return conflictSolveWay;
    }

    public void setConflictSolveWay(String conflictSolveWay) {
        this.conflictSolveWay = conflictSolveWay;
    }

    public BigDecimal getmYield() {
        return mYield;
    }

    public void setmYield(BigDecimal mYield) {
        this.mYield = mYield;
    }

    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    public String getActAmtDays() {
        return actAmtDays;
    }

    public void setActAmtDays(String actAmtDays) {
        this.actAmtDays = actAmtDays;
    }

    public String getAcctOwenAcctId() {
        return acctOwenAcctId;
    }

    public void setAcctOwenAcctId(String acctOwenAcctId) {
        this.acctOwenAcctId = acctOwenAcctId;
    }

    public String getAcctRivalId() {
        return acctRivalId;
    }

    public void setAcctRivalId(String acctRivalId) {
        this.acctRivalId = acctRivalId;
    }

    public FinProductType getFinProductType() {
        return finProductType;
    }

    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    public StlFinProductAcct getStlFinProductAcct() {
        return stlFinProductAcct;
    }

    public void setStlFinProductAcct(StlFinProductAcct stlFinProductAcct) {
        this.stlFinProductAcct = stlFinProductAcct;
    }

    public WflTrdProductDeal getWflTrdProductDeal() {
        return wflTrdProductDeal;
    }

    public void setWflTrdProductDeal(WflTrdProductDeal wflTrdProductDeal) {
        this.wflTrdProductDeal = wflTrdProductDeal;
    }

    public WflTrdProductDealOth getWflTrdProductDealOth() {
        return wflTrdProductDealOth;
    }

    public void setWflTrdProductDealOth(WflTrdProductDealOth wflTrdProductDealOth) {
        this.wflTrdProductDealOth = wflTrdProductDealOth;
    }

    public String getSupplyClause() {
        return supplyClause;
    }

    public void setSupplyClause(String supplyClause) {
        this.supplyClause = supplyClause;
    }
}
