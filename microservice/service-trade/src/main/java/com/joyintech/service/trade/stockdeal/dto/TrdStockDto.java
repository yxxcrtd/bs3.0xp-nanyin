/*
 * 文件名：TrdStockDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.stockdeal.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;


public class TrdStockDto extends TrdProductDeal {
	
	 /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8259658424290761601L;

    /**
     * 有效状态
     */
    private String validStatus;
    
    /**
     * 销售代码
     */
    private  String saleCode;
    
    /**
     * 获取销售代码
     * @return
     */
    @Override
	public String getSaleCode() {
		return saleCode;
	}

    /**
     * 设置销售代码
     * @return
     */
	@Override
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	
	/**
	 * 印花税
	 */
	private BigDecimal stampDuty;
	/**
	 * 佣金
	 */
	private BigDecimal commission;

	/**
	 * 委托数量
	 */
	private BigDecimal entrustAmt;
	/**
	 * 委托额度
	 */
	private BigDecimal entrustNum;
	/**
	 * 委托单价
	 */
	private BigDecimal price;
	/**
	 * 完成数量
	 */
	private BigDecimal finishAmt;

	/**
     * 上市板
     */
    private String board;
    
  
    /**
     * 通道
     */
    private String chlId;
    

	/**
	 * 获取 chlId
	 *
	 * @return chlId
	 */
	@Override
	public String getChlId() {
		return chlId;
	}
    
    /**
	 * 设置 chlId
	 *
	 * @param chlId chlId
	 */
	@Override
	public void setChlId(String chlId) {
		this.chlId = chlId;
	}
    
    
    /**
     * 资产名称
     */
    
    private String finprodAbbr;
    
    /**
     * 获取资产名称
     * @return
     */
    public String getFinprodAbbr() {
		return finprodAbbr;
	}

    /**
     * 设置资产名称
     * @param finprodAbbr
     */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	/**
     * 获取上市板
     * @return
     */
    public String getBoard() {
		return board;
	}
    
    /**
     * 设置上市板
     * @param board
     */
	public void setBoard(String board) {
		this.board = board;
	}

	/**
     * 交易方式
     */
    private String dealMode;

    /**
     * 获取交易方式
     * @return
     */
    public String getDealMode() {
		return dealMode;
	}
    /**
     * 是否有锁定期
     */
    private String withLockPeriod;
    
    /**
     * 获取锁定期
     * @return
     */
    public String getWithLockPeriod() {
		return withLockPeriod;
	}
    /**
     * 设置锁定期
     * @param withLockPeriod
     */
	public void setWithLockPeriod(String withLockPeriod) {
		this.withLockPeriod = withLockPeriod;
	}
	
	/**
	 * 锁定截止日
	 */
	 private String lockMdate;
	 
	 /**
	  * 获取锁定截止日
	  * @return
	  */
	public String getLockMdate() {
		return lockMdate;
	}
	/**
	 * 设置锁定截止日
	 * @param lockMdate
	 */
	public void setLockMdate(String lockMdate) {
		this.lockMdate = lockMdate;
	}


    /**
     * 设置交易方式
     * @param dealMode
     */
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

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
     * 资产代码
     */
    private String finprodId;
    
	/**
	 * 印花税是否随交易支付
	 */
	private String stampDutyIsPayWithTrade;
	/**
	 * 佣金是否随交易支付
	 */
	private String commissionIsPayWithTrade;
	

    /**
     * 获取资产代码
     */
    @Override
	public String getFinprodId() {
		return finprodId;
	}

    
    /**
	 * 资产可用持仓
	 */
	 private BigDecimal bottomAssetAmount;
	
	 
	 /**
	     * 获取 bottomAssetAmount
	     * @return bottomAssetAmount.
	     */
	    public BigDecimal getBottomAssetAmount() {
	        return bottomAssetAmount;
	    }
	    
	    /**
	     * 设置 bottomAssetAmount
	     * @param bottomAssetAmount bottomAssetAmount
	     */
	    public void setBottomAssetAmount(BigDecimal bottomAssetAmount) {
	        this.bottomAssetAmount = bottomAssetAmount;
	    }
	    
    /**
     * 设置资产代码
     */
	@Override
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 交易场所
     */
    private String tradeMarket;
    
    

    /**
     * 获取交易场所
     */
    @Override
	public String getTradeMarket() {
		return tradeMarket;
	}

    /**
     * 设置交易场所
     */
	@Override
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

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
	 * 费用明细表
	 */
	private TrdTradeFeeDetail trdTradeFeeDetail;

	public TrdTradeFeeDetail getTrdTradeFeeDetail() {
		return trdTradeFeeDetail;
	}

	public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
		this.trdTradeFeeDetail = trdTradeFeeDetail;
	}

	public BigDecimal getEntrustAmt() {
		return entrustAmt;
	}

	public void setEntrustAmt(BigDecimal entrustAmt) {
		this.entrustAmt = entrustAmt;
	}

	public BigDecimal getEntrustNum() {
		return entrustNum;
	}

	public void setEntrustNum(BigDecimal entrustNum) {
		this.entrustNum = entrustNum;
	}

	public BigDecimal getFinishAmt() {
		return finishAmt;
	}

	public void setFinishAmt(BigDecimal finishAmt) {
		this.finishAmt = finishAmt;
	}

	public BigDecimal getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(BigDecimal stampDuty) {
		this.stampDuty = stampDuty;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getStampDutyIsPayWithTrade() {
		return stampDutyIsPayWithTrade;
	}

	public void setStampDutyIsPayWithTrade(String stampDutyIsPayWithTrade) {
		this.stampDutyIsPayWithTrade = stampDutyIsPayWithTrade;
	}

	public String getCommissionIsPayWithTrade() {
		return commissionIsPayWithTrade;
	}

	public void setCommissionIsPayWithTrade(String commissionIsPayWithTrade) {
		this.commissionIsPayWithTrade = commissionIsPayWithTrade;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}