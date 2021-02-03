/*
 * 文件名：TrdBondInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.bond.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.*;

/**
 * 债券交易Dto
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see TrdBondInvestTradeDto
 * @since 1.0
 */
public class TrdBondInvestTradeDto extends TrdFinProductDealTotal {

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
     * 债券一级分类
     */
    private String windtypeone;

    /**
     * 资产二级类型
     */
    private String type9;

    /**
     * 资产三级类型
     */
    private String type10;

    /**
     * 资产四级类型
     */
    private String type11;

    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public String getType10() {
        return type10;
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }

    public String getType11() {
        return type11;
    }

    public void setType11(String type11) {
        this.type11 = type11;
    }

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
     * 是否含权债
     */
    private String isRight;

    /**
     * 金融产品代码（系统自动生成）
     */
    private String systemFinprodId;
    /**
     * 结算费
     */
    private BigDecimal settleAmt;

    /**
     * 经手费
     */
    private BigDecimal exchangeAmt;

    public BigDecimal getExchangeAmt() {
        return exchangeAmt;
    }

    public void setExchangeAmt(BigDecimal exchangeAmt) {
        this.exchangeAmt = exchangeAmt;
    }

    /**
     * 交易手续费
     */
    private BigDecimal procedureAmt;

    /**
     * 结算服务费
     */
    private BigDecimal settleServiceAmt;

    public BigDecimal getSettleServiceAmt() {
        return settleServiceAmt;
    }

    public void setSettleServiceAmt(BigDecimal settleServiceAmt) {
        this.settleServiceAmt = settleServiceAmt;
    }

    /**
     * 佣金
     */
    private BigDecimal commissionAmt;
    
    // add by wangpeng 费用设置 20180818 start
    /**
     * 印花税
     */
    private BigDecimal stampDutyAmt;
   
    /**
     * 印花税是否随交易支付
     */
    private String stampDutyIsPayWithTrade;
    // add by wangpeng 费用设置 20180818 end
    //        推荐方 by zhuyongkang 20191023 start

    /**
     * 本方券券面总额
     */
    private BigDecimal ownTradeAmt;

    /**
     * 融入券券面总额
     */
    private BigDecimal integrateTradeAmt;
    /**
     * 本方券应计利息
     */
    private BigDecimal ownIntAmt;

    /**
     * 融入券应计利息
     */
    private BigDecimal integrateIntAmt;

    /**
     * 本方券全价总额
     */
    private BigDecimal ownFpriceAmt;

    /**
     * 融入券全价总额
     */
    private BigDecimal integrateFpriceAmt;

    /**
     * 融入券净价总额
     */
    private BigDecimal integrateCpriceAmt;
    /**
     * 金融产品交易附属业务流程表
     */
    private WflTrdProductDealAdd wflTrdProductDealAdd;

    public WflTrdProductDealAdd getWflTrdProductDealAdd() {
        return wflTrdProductDealAdd;
    }

    public void setWflTrdProductDealAdd(WflTrdProductDealAdd wflTrdProductDealAdd) {
        this.wflTrdProductDealAdd = wflTrdProductDealAdd;
    }
//        推荐方 by zhuyongkang 20191023 start

    // add by wangguoxian 费用设置 20180808 start
    /**
     * 佣金是否随交易支付
     */
    private String isPayWithTrade;
    // add by wangguoxian 费用设置 20180808 end

    private String settleIsPayWithTrade;

    private String exchangeIsPayWithTrade;

    public String getSettleIsPayWithTrade() {
        return settleIsPayWithTrade;
    }

    public void setSettleIsPayWithTrade(String settleIsPayWithTrade) {
        this.settleIsPayWithTrade = settleIsPayWithTrade;
    }

    public String getExchangeIsPayWithTrade() {
        return exchangeIsPayWithTrade;
    }

    public void setExchangeIsPayWithTrade(String exchangeIsPayWithTrade) {
        this.exchangeIsPayWithTrade = exchangeIsPayWithTrade;
    }

    // 增加偏离度by赵东东 2019.9.23 start
    /**
     * 偏离度
     */
    private BigDecimal deviation;
    // 增加偏离度by赵东东 2019.9.23 end
    
    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<StlPayOrders> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }
    
// 增加获取，设置偏离度by赵东东 2019.9.24 start
    /**
     * 获取偏离度
     * @return
     */
    public BigDecimal getDeviation() {
		return deviation;
	}
     /**
      * 设置偏离度
      * @param deviation
      */
	public void setDeviation(BigDecimal deviation) {
		this.deviation = deviation;
	}
// 增加获取，设置偏离度by赵东东 2019.9.24 start
	
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
     * 合作资产
     */
    private String chlFinprodId;

    public String getChlFinprodId() {
        return chlFinprodId;
    }

    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }

    /**
     * 设置 isExercise
     *
     * @param isExercise isExercise
     */
    public void setIsExercise(String isExercise) {
        this.isExercise = isExercise;
    }
    //    资产推荐方 新增 zhuyongkang 20191023 start
    private String assetRecommandOrg;

    public String getAssetRecommandOrg() {
        return assetRecommandOrg;
    }

    public void setAssetRecommandOrg(String assetRecommandOrg) {
        this.assetRecommandOrg = assetRecommandOrg;
    }
//    资产推荐方 新增 zhuyongkang 20191023 start
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
     * 获取 windtypeone
     *
     * @return windtypeone
     */
    public String getWindtypeone() {
        return windtypeone;
    }

    /**
     * 设置 windtypeone
     *
     * @param windtypeone windtypeone
     */
    public void setWindtypeone(String windtypeone) {
        this.windtypeone = windtypeone;
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
     * 获取 isRight
     *
     * @return isRight.
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 设置 isRight
     *
     * @param isRight isRight
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight;
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

    // 通道交易 新增 强成西 2019/03/26 start
    /**
     * 通道费
     */
    private BigDecimal channelFee;

    /**
     * 获取 channelFee
     *
     * @return channelFee
     */
    public BigDecimal getChannelFee() {
        return channelFee;
    }

    /**
     * 设置 channelFee
     *
     * @param channelFee channelFee
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }
    // 通道交易 新增 强成西 2019/03/26 end
	
	/**
     * 获取 settleAmt
     * 
     * @return settleAmt.
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置 settleAmt
     * 
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 获取 procedureAmt
     * 
     * @return procedureAmt.
     */
    public BigDecimal getProcedureAmt() {
        return procedureAmt;
    }

    /**
     * 设置 procedureAmt
     * 
     * @param procedureAmt procedureAmt
     */
    public void setProcedureAmt(BigDecimal procedureAmt) {
        this.procedureAmt = procedureAmt;
    }

    /**
     * 获取 commissionAmt
     * 
     * @return commissionAmt.
     */
    public BigDecimal getCommissionAmt() {
        return commissionAmt;
    }

    /**
     * 设置 commissionAmt
     * 
     * @param commissionAmt commissionAmt
     */
    public void setCommissionAmt(BigDecimal commissionAmt) {
        this.commissionAmt = commissionAmt;
    }

    // add by wangguoxian 费用设置 20180808 start
    /**
     * 获取佣金是否随交易支付
     * @return 佣金是否随交易支付
     */
    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    /**
     * 设置佣金是否随交易支付
     * @param isPayWithTrade 佣金是否随交易支付
     */
    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }
    // add by wangguoxian 费用设置 20180808 end

    /**
	 * 费用明细表
	 */
	private TrdTradeFeeDetail trdTradeFeeDetail;

	public TrdTradeFeeDetail getTrdTradeFeeDetail() {
		return trdTradeFeeDetail;
	}

	public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
		this.trdTradeFeeDetail = trdTradeFeeDetail;
	}

	public BigDecimal getStampDutyAmt() {
		return stampDutyAmt;
	}

	public void setStampDutyAmt(BigDecimal stampDutyAmt) {
		this.stampDutyAmt = stampDutyAmt;
	}

	public String getStampDutyIsPayWithTrade() {
		return stampDutyIsPayWithTrade;
	}

	public void setStampDutyIsPayWithTrade(String stampDutyIsPayWithTrade) {
		this.stampDutyIsPayWithTrade = stampDutyIsPayWithTrade;
	}

    /**
     * 获取 ownTradeAmt
     *
     * @return ownTradeAmt
     */
    public BigDecimal getOwnTradeAmt() {
        return ownTradeAmt;
    }

    /**
     * 设置 ownTradeAmt
     *
     * @param ownTradeAmt ownTradeAmt
     */
    public void setOwnTradeAmt(BigDecimal ownTradeAmt) {
        this.ownTradeAmt = ownTradeAmt;
    }

    /**
     * 获取 integrateTradeAmt
     *
     * @return integrateTradeAmt
     */
    public BigDecimal getIntegrateTradeAmt() {
        return integrateTradeAmt;
    }

    /**
     * 设置 integrateTradeAmt
     *
     * @param integrateTradeAmt integrateTradeAmt
     */
    public void setIntegrateTradeAmt(BigDecimal integrateTradeAmt) {
        this.integrateTradeAmt = integrateTradeAmt;
    }

    /**
     * 获取 ownIntAmt
     *
     * @return ownIntAmt
     */
    public BigDecimal getOwnIntAmt() {
        return ownIntAmt;
    }

    /**
     * 设置 ownIntAmt
     *
     * @param ownIntAmt ownIntAmt
     */
    public void setOwnIntAmt(BigDecimal ownIntAmt) {
        this.ownIntAmt = ownIntAmt;
    }

    /**
     * 获取 integrateIntAmt
     *
     * @return integrateIntAmt
     */
    public BigDecimal getIntegrateIntAmt() {
        return integrateIntAmt;
    }

    /**
     * 设置 integrateIntAmt
     *
     * @param integrateIntAmt integrateIntAmt
     */
    public void setIntegrateIntAmt(BigDecimal integrateIntAmt) {
        this.integrateIntAmt = integrateIntAmt;
    }

    /**
     * 获取 ownFpriceAmt
     *
     * @return ownFpriceAmt
     */
    public BigDecimal getOwnFpriceAmt() {
        return ownFpriceAmt;
    }

    /**
     * 设置 ownFpriceAmt
     *
     * @param ownFpriceAmt ownFpriceAmt
     */
    public void setOwnFpriceAmt(BigDecimal ownFpriceAmt) {
        this.ownFpriceAmt = ownFpriceAmt;
    }

    /**
     * 获取 integrateFpriceAmt
     *
     * @return integrateFpriceAmt
     */
    public BigDecimal getIntegrateFpriceAmt() {
        return integrateFpriceAmt;
    }

    /**
     * 设置 integrateFpriceAmt
     *
     * @param integrateFpriceAmt integrateFpriceAmt
     */
    public void setIntegrateFpriceAmt(BigDecimal integrateFpriceAmt) {
        this.integrateFpriceAmt = integrateFpriceAmt;
    }

    /**
     * 获取 integrateCpriceAmt
     *
     * @return integrateCpriceAmt
     */
    public BigDecimal getIntegrateCpriceAmt() {
        return integrateCpriceAmt;
    }

    /**
     * 设置 integrateCpriceAmt
     *
     * @param integrateCpriceAmt integrateCpriceAmt
     */
    public void setIntegrateCpriceAmt(BigDecimal integrateCpriceAmt) {
        this.integrateCpriceAmt = integrateCpriceAmt;
    }
}
