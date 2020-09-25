/*
 * 文件名：PtlAnalyzeAfterInvest001Dto
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：李淑萍
 * 修改时间：2019/10/23
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.service.portfolio.ptlanalyzeafterinvest.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinProduct;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author 李淑萍
 * @version 1.0 2019/10/23
 * @see
 * @since 1.0
 */
@PageId("PtlActivityPeriod001")
public class PtlAnalyzeAfterInvest001Dto extends FinProduct {


    /**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1007914875768669910L;
	/**
     * 质押券状态
     */
    public String pledgeStatus;
    /**
     * 质押股票代码
     */
    public String pledgeStockId;
    /**
     * 质押股票名称
     */
    public String pledgeStockName;
    /**
     * 预警线
     */
    public String warningLine;
    /**
     * 平仓线
     */
    public String closeLine;
    /**
     * 当前价格
     */
    public String presentPrice;

    /**
     * pledgeStatus
     * @return pledgeStatus
     */
    public String getPledgeStatus() {
        return pledgeStatus;
    }

    /**
     * pledgeStatus
     * @param pledgeStatus pledgeStatus
     */
    public void setPledgeStatus(String pledgeStatus) {
        this.pledgeStatus = pledgeStatus;
    }

    /**
     * pledgeStockId
     * @return pledgeStockId
     */
    public String getPledgeStockId() {
        return pledgeStockId;
    }

    /**
     * pledgeStockId
     * @param pledgeStockId pledgeStockId
     */
    public void setPledgeStockId(String pledgeStockId) {
        this.pledgeStockId = pledgeStockId;
    }

    /**
     * pledgeStockName
     * @return pledgeStockName
     */
    public String getPledgeStockName() {
        return pledgeStockName;
    }

    /**
     * pledgeStockName
     * @param pledgeStockName pledgeStockName
     */
    public void setPledgeStockName(String pledgeStockName) {
        this.pledgeStockName = pledgeStockName;
    }

    /**
     * warningLine
     * @return warningLine
     */
    public String getWarningLine() {
        return warningLine;
    }

    /**
     * warningLine
     * @param warningLine warningLine
     */
    public void setWarningLine(String warningLine) {
        this.warningLine = warningLine;
    }

    /**
     * closeLine
     * @return closeLine
     */
    public String getCloseLine() {
        return closeLine;
    }

    /**
     * closeLine
     * @param closeLine closeLine
     */
    public void setCloseLine(String closeLine) {
        this.closeLine = closeLine;
    }

    /**
     * presentPrice
     * @return presentPrice
     */
    public String getPresentPrice() {
        return presentPrice;
    }

    /**
     * presentPrice
     * @param presentPrice presentPrice
     */
    public void setPresentPrice(String presentPrice) {
        this.presentPrice = presentPrice;
    }

    public String getVdateStr(){
        return DateUtil.format(this.getVdate());
    }

    public String getMdateStr(){
        return DateUtil.format(this.getMdate());
    }


}
