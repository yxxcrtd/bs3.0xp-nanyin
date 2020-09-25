package com.joyintech.tams.invest.common.dto.period.common;

import com.joyintech.tams.common.base.entity.*;

import java.util.List;

/**
 * @program: tams-invest-parent
 * @description: 交易相关EntityDto
 * @author: 强成西
 * @create: 2018-11-02 09:14
 **/
public class BaseTradeInfoDto extends BaseTrade {

    /**
     * 汇总交易
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;
    
    /**
     * 汇总附属
     */
    private TrdDealTotalAdd trdDealTotalAdd;

    /**
     * 汇总扩展
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     * 交易费用明细
     */
    private List<TrdTradeFeeDetail> trdTradeFeeDetail;
    /**
     * 实物交易表
     */
    private TrdFinProductBarter trdFinProductBarter;
    /**
     * 划款指令
     */
    private StlPayOrders stlPayOrders;

    /**
     * 其他需要登录的交易信息，如到期还本时的还本数据
     */
    private BaseTrade baseTrade;
    /**
     *  其他交易list信息，入子组合交易信息
     */
    private List<BaseTradeInfoDto> baseTradeInfoDtoList;
    
    /**
     *  还本不带息利息明细
     */
    private List<BaseTradeInfoDto> repaymentUnpayInterestBaseTradeInfoDtoList;

    /**
     * 罚息利息明细
     */
    private List<BaseTradeInfoDto> penaltyIntBaseTradeInfoDtoList;
    
    public List<BaseTradeInfoDto> getBaseTradeInfoDtoList() {
        return baseTradeInfoDtoList;
    }

    public void setBaseTradeInfoDtoList(List<BaseTradeInfoDto> baseTradeInfoDtoList) {
        this.baseTradeInfoDtoList = baseTradeInfoDtoList;
    }

    /**
     * 获取 baseTrade
     *
     * @return baseTrade
     */
    public BaseTrade getBaseTrade() {
        return baseTrade;
    }

    /**
     * 设置 baseTrade
     *
     * @param baseTrade baseTrade
     */
    public void setBaseTrade(BaseTrade baseTrade) {
        this.baseTrade = baseTrade;
    }

    /**
     * 获取 trdTradeFeeDetail
     *
     * @return trdTradeFeeDetail
     */
    public List<TrdTradeFeeDetail> getTrdTradeFeeDetail() {
        return trdTradeFeeDetail;
    }

    /**
     * 设置 trdTradeFeeDetail
     *
     * @param trdTradeFeeDetail trdTradeFeeDetail
     */
    public void setTrdTradeFeeDetail(List<TrdTradeFeeDetail> trdTradeFeeDetail) {
        this.trdTradeFeeDetail = trdTradeFeeDetail;
    }

    /**
     * 获取 stlPayOrders
     *
     * @return stlPayOrders
     */
    public StlPayOrders getStlPayOrders() {
        return stlPayOrders;
    }

    /**
     * 设置 stlPayOrders
     *
     * @param stlPayOrders stlPayOrders
     */
    public void setStlPayOrders(StlPayOrders stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }

    /**
     * 获取 trdFinProductDealTotal
     *
     * @return trdFinProductDealTotal
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 trdFinProductDealTotal
     *
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 获取 paTrdFinProductDealTotal
     *
     * @return paTrdFinProductDealTotal
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }

    /**
     * 设置 paTrdFinProductDealTotal
     *
     * @param paTrdFinProductDealTotal paTrdFinProductDealTotal
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    /**
     * 获取 实物交易表
     *
     * @return trdFinProductBarter 实物交易表
     */
    public TrdFinProductBarter getTrdFinProductBarter() {
        return this.trdFinProductBarter;
    }

    /**
     * 设置 实物交易表
     *
     * @param trdFinProductBarter 实物交易表
     */
    public void setTrdFinProductBarter(TrdFinProductBarter trdFinProductBarter) {
        this.trdFinProductBarter = trdFinProductBarter;
    }

	public TrdDealTotalAdd getTrdDealTotalAdd() {
		return trdDealTotalAdd;
	}

	public void setTrdDealTotalAdd(TrdDealTotalAdd trdDealTotalAdd) {
		this.trdDealTotalAdd = trdDealTotalAdd;
	}

	/**
	 * @return 还本不带息利息明细
	 */
	public List<BaseTradeInfoDto> getRepaymentUnpayInterestBaseTradeInfoDtoList() {
		return repaymentUnpayInterestBaseTradeInfoDtoList;
	}

	/**
	 * @param 还本不带息利息明细
	 */
	public void setRepaymentUnpayInterestBaseTradeInfoDtoList(List<BaseTradeInfoDto> repaymentUnpayInterestBaseTradeInfoDtoList) {
		this.repaymentUnpayInterestBaseTradeInfoDtoList = repaymentUnpayInterestBaseTradeInfoDtoList;
	}

	/**
	 * @return 罚息利息明细
	 */
	public List<BaseTradeInfoDto> getPenaltyIntBaseTradeInfoDtoList() {
		return penaltyIntBaseTradeInfoDtoList;
	}

	/**
	 * @param 罚息利息明细
	 */
	public void setPenaltyIntBaseTradeInfoDtoList(List<BaseTradeInfoDto> penaltyIntBaseTradeInfoDtoList) {
		this.penaltyIntBaseTradeInfoDtoList = penaltyIntBaseTradeInfoDtoList;
	}


}
