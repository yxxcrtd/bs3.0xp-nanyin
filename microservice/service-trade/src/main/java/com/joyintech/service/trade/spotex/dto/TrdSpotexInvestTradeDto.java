/*
 * 文件名：TrdSpotexInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.spotex.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;


/**
 * 外汇即期交易Dto
 * @author wangguoxian
 * @version 1.0 2019年7月4日
 * @see TrdSpotexInvestTradeDto
 * @since 1.0
 */
public class TrdSpotexInvestTradeDto extends TrdProductDeal {
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
     * 货币对
     */
    private String curPair;

    /**
     * 交易货币
     */
    private String dealCur;

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
     * 人员信息
     */
    private TrdInvestPersonInfoDto trdInvestPersonInfo;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 金融产品代码（系统自动生成）
     */
    private String systemFinprodId;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 费用明细表
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;

    /**
     * 交易日开始
     */
    private Date tradeDateBegin;

    /**
     * 交易日结束
     */
    private Date tradeDateEnd;


    /**
     * 获取 entrustId
     *
     * @return entrustId
     */
    public String getEntrustId() {
        return entrustId;
    }


    /**
     * 获取外汇交易类型
     * 
     * @return 外汇交易类型
     */
    public String getExcDealType() {
        return excDealType;
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
     * 获取货币对
     * 
     * @return 货币对
     */
    public String getCurPair() {
        return curPair;
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
     * 获取交易货币
     * 
     * @return 交易货币
     * @see
     * @since 1.0
     */
    public String getDealCur() {
        return dealCur;
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
     * 获取折美元金额
     * 
     * @return 折美元金额
     * @see
     * @since 1.0
     */
    public BigDecimal getUsdAmt() {
        return usdAmt;
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
     * 
     * 获取交易日历
     * @return 交易日历
     * @see
     * @since 1.0
     */
    public String getCalendarId() {
        return calendarId;
    }


    /**
     * 
     * 设置交易日历
     * @param calendarId 交易日历
     * @see
     * @since 1.0
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }


    /**
     * 
     * 获取交易模式
     * @return 交易模式
     * @see
     * @since 1.0
     */
    public String getDealMode() {
        return dealMode;
    }


    /**
     * 
     * 设置交易模式
     * @param dealMode 交易模式
     * @see
     * @since 1.0
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }


    /**
     * 
     * 获取我方角色
     * @return 我方角色
     * @see
     * @since 1.0
     */
    public String getOurRole() {
        return ourRole;
    }


    /**
     * 
     * 设置我方角色
     * @param ourRole 我方角色
     * @see
     * @since 1.0
     */
    public void setOurRole(String ourRole) {
        this.ourRole = ourRole;
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
    
    
}
