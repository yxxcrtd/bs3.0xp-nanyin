package com.joyintech.tams.invest.common.dto.period.common;

import java.util.List;

import com.joyintech.tams.common.base.entity.*;

/**
 * @program: tams-invest-parent
 * @description: 汇总交易相关EntityDto
 * @author: 强成西
 * @create: 2018-11-02 09:12
 **/
public class BaseTotalTradeInfoDto {
	/**
	 * 划款指令是否需要入库（适应一些在调用共通时需要，但不需要入库的场景）
	 */
	private boolean isNeedStlPayOrders = true;
    /**
     * 汇总交易表
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 汇总交易扩展
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     * 汇总费用明细
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;


    /**
     * 交易相关list
     */
    private List<BaseTradeInfoDto> baseTradeInfoDtoList;
    /**
     * 划款指令
     */
    private StlPayOrders stlPayOrders;
    //金融产品交易汇总附属
    private TrdDealTotalAdd trdDealTotalAdd;
    /**
     * 金融产品实物汇总交易
     */
    private TrdFinProductBarterTotal trdFinProductBarterTotal;
    /**
	 * 获取 isNeedStlPayOrders
	 * @return isNeedStlPayOrders.
	 */
	public boolean isNeedStlPayOrders() {
		return isNeedStlPayOrders;
	}

	private boolean isBack;

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }

    /**
	 * 设置 isNeedStlPayOrders
	 * @param isNeedStlPayOrders isNeedStlPayOrders
	 */
	public void setNeedStlPayOrders(boolean isNeedStlPayOrders) {
		this.isNeedStlPayOrders = isNeedStlPayOrders;
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
     * 获取 baseTradeInfoDtoList
     *
     * @return baseTradeInfoDtoList
     */
    public List<BaseTradeInfoDto> getBaseTradeInfoDtoList() {
        return baseTradeInfoDtoList;
    }

    /**
     * 设置 baseTradeInfoDtoList
     *
     * @param baseTradeInfoDtoList baseTradeInfoDtoList
     */
    public void setBaseTradeInfoDtoList(List<BaseTradeInfoDto> baseTradeInfoDtoList) {
        this.baseTradeInfoDtoList = baseTradeInfoDtoList;
    }

    public TrdDealTotalAdd getTrdDealTotalAdd() {
        return trdDealTotalAdd;
    }

    public void setTrdDealTotalAdd(TrdDealTotalAdd trdDealTotalAdd) {
        this.trdDealTotalAdd = trdDealTotalAdd;
    }

    /**
     * 获取 划款指令是否需要入库（适应一些在调用共通时需要，但不需要入库的场景）
     *
     * @return isNeedStlPayOrders 划款指令是否需要入库（适应一些在调用共通时需要，但不需要入库的场景）
     */
    public boolean isIsNeedStlPayOrders() {
        return this.isNeedStlPayOrders;
    }

    /**
     * 设置 划款指令是否需要入库（适应一些在调用共通时需要，但不需要入库的场景）
     *
     * @param isNeedStlPayOrders 划款指令是否需要入库（适应一些在调用共通时需要，但不需要入库的场景）
     */
    public void setIsNeedStlPayOrders(boolean isNeedStlPayOrders) {
        this.isNeedStlPayOrders = isNeedStlPayOrders;
    }

    /**
     * 获取 金融产品实物汇总交易
     *
     * @return trdFinProductBarterTotal 金融产品实物汇总交易
     */
    public TrdFinProductBarterTotal getTrdFinProductBarterTotal() {
        return this.trdFinProductBarterTotal;
    }

    /**
     * 设置 金融产品实物汇总交易
     *
     * @param trdFinProductBarterTotal 金融产品实物汇总交易
     */
    public void setTrdFinProductBarterTotal(TrdFinProductBarterTotal trdFinProductBarterTotal) {
        this.trdFinProductBarterTotal = trdFinProductBarterTotal;
    }

    /**
     * 获取
     *
     * @return isBack
     */
    public boolean isIsBack() {
        return this.isBack;
    }

    /**
     * 设置
     *
     * @param isBack
     */
    public void setIsBack(boolean isBack) {
        this.isBack = isBack;
    }

    /**
     * 获取 划款指令
     *
     * @return stlPayOrders 划款指令
     */
    public StlPayOrders getStlPayOrders() {
        return this.stlPayOrders;
    }

    /**
     * 设置 划款指令
     *
     * @param stlPayOrders 划款指令
     */
    public void setStlPayOrders(StlPayOrders stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }
}
