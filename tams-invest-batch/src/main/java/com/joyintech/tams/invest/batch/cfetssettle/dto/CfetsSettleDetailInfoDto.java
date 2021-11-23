/*
 * 文件名：MstIntRateInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.cfetssettle.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.invest.batch.resetbaseruleinfo.dto.MstIntRateInfoDto;

import java.math.BigDecimal;

/**
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2021年11月19日
 * @see CfetsSettleDetailInfoDto
 * @since 1.0
 */
public class CfetsSettleDetailInfoDto extends NeedToWriteEntity{


    //交易编号
    private String tradeId;

    //组合代码
    private String portfolioId;

    //组合交收数量
    private BigDecimal settleNum;

    //组合交收金额
    private BigDecimal settleAmt;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public BigDecimal getSettleNum() {
        return settleNum;
    }

    public void setSettleNum(BigDecimal settleNum) {
        this.settleNum = settleNum;
    }

    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}
