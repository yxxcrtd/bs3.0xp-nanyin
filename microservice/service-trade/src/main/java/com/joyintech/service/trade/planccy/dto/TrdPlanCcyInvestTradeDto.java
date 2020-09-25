/*
 * 文件名：TrdPlanCcyInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.planccy.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.service.trade.bond.dto.TrdInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月7日
 * @see TrdPlanCcyInvestTradeDto
 * @since 1.0
 */
public class TrdPlanCcyInvestTradeDto extends TrdProductDeal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7688818923314379175L;
    /**
     * 发行机构代码
     */
    private String issuePartyId;
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 组合T日可用现金
     */
    private BigDecimal proAvailableCash;
    /**
     * 预计赎回到账日
     */
    private Date redCfmDate;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 控制操作按钮
     */
    private String flg;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
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
     * 基金类型
     */
    private String type6;
    /*添加二-四级分类字段 lishuping 2019/7/30 start*/
    /**
     * 资产二级分类
     */
    private String type9;
    /**
     * 资产三级分类
     */
    private String type10;
    /**
     * 资产四级分类
     */
    private String type11;

    /**
     * 通道费
     */
    private BigDecimal channelFee;
    
    /**
     * 委托金额
     */
    private BigDecimal entrustAmt;
    
    /**
     * 委托数量
     */
    private BigDecimal entrustNum;
    /**
     * 完成委托额度
     */
    private BigDecimal finishAmt;
    /**
     * 委托金额
     */
    private BigDecimal prinAmt;
    
    

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

	@Override
	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	@Override
	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	/**
     * type9
     * @return type9
     */
    public String getType9() {
        return type9;
    }

    /**
     * type9
     * @param type9 type9
     */
    public void setType9(String type9) {
        this.type9 = type9;
    }

    /**
     * type10
     * @return type10
     */
    public String getType10() {
        return type10;
    }

    /**
     * type10
     * @param type10 type10
     */
    public void setType10(String type10) {
        this.type10 = type10;
    }

    /**
     * type11
     * @return type11
     */
    public String getType11() {
        return type11;
    }

    /**
     * type11
     * @param type11 type11
     */
    public void setType11(String type11) {
        this.type11 = type11;
    }
    /*添加二-四级分类字段 lishuping 2019/7/30 end*/
    /**
     * 计划类二级类型
     */
    private String type7;
    /**
     * 交易场所
     */
    private String tradeMarket;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;
    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 通道费明细
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;
    /**
     * 划款指令
     */
    private List<StlPayOrders> bussTrdTransferOrder;

    /**
     * 人员信息
     */
    private TrdInvestPersonInfoDto trdInvestPersonInfo;
    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;
    
    
    /**
     * 获取 issuePartyId
     * @return issuePartyId.
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }




    
    /**
     * 设置 issuePartyId
     * @param issuePartyId issuePartyId
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }




    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }



    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }



    /**
     * 获取 type7
     * @return type7.
     */
    public String getType7() {
        return type7;
    }


    
    /**
     * 设置 type7
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
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
     * 获取 redCfmDate
     * @return redCfmDate.
     */
    public Date getRedCfmDate() {
        return redCfmDate;
    }

    
    /**
     * 设置 redCfmDate
     * @param redCfmDate redCfmDate
     */
    public void setRedCfmDate(Date redCfmDate) {
        this.redCfmDate = redCfmDate;
    }

    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    
    /**
     * 获取 flg
     * @return flg.
     */
    public String getFlg() {
        return flg;
    }





    
    /**
     * 设置 flg
     * @param flg flg
     */
    public void setFlg(String flg) {
        this.flg = flg;
    }





    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
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
     * 获取 entrustId
     * @return entrustId.
     */
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
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }

    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    
    /**
     * 获取 tradeMarket
     * @return tradeMarket.
     */
    @Override
	public String getTradeMarket() {
        return tradeMarket;
    }

    
    /**
     * 设置 tradeMarket
     * @param tradeMarket tradeMarket
     */
    @Override
	public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
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
    public List<StlPayOrders> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    
    /**
     * 设置 bussTrdTransferOrder
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<StlPayOrders> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    
    /**
     * 获取 trdInvestPersonInfo
     * @return trdInvestPersonInfo.
     */
    public TrdInvestPersonInfoDto getTrdInvestPersonInfo() {
        return trdInvestPersonInfo;
    }

    
    /**
     * 设置 trdInvestPersonInfo
     * @param trdInvestPersonInfo trdInvestPersonInfo
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfoDto trdInvestPersonInfo) {
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
     * 获取 ptlTrade
     * @return ptlTrade.
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }

    
    /**
     * 设置 ptlTrade
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 trdTradeFeeDetail
     *
     * @return trdTradeFeeDetail
     */
    public TrdTradeFeeDetail getTrdTradeFeeDetail() {
        return trdTradeFeeDetail;
    }

    /**
     * 设置 trdTradeFeeDetail
     *
     * @param trdTradeFeeDetail trdTradeFeeDetail
     */
    public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
        this.trdTradeFeeDetail = trdTradeFeeDetail;
    }

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
}
