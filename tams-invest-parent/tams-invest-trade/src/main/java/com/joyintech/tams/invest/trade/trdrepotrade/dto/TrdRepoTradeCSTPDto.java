/*
 * 文件名：TrdRepoTradeCSTPDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年4月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trdrepotrade.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

import java.math.BigDecimal;
import java.util.List;


/**
 * 回购CSTP交易
 * 
 * @author zhangliang
 * @version 1.0 2019年4月9日
 * @see TrdRepoTradeCSTPDto
 * @since 1.0
 */
public class TrdRepoTradeCSTPDto extends InterfOpicsTradRepo {
    
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6419698209427700925L;
    
    /**
     * {@linkplain}
     */
    private transient List<PledgeBondInfoDto> pledgeBondInfoDtoList;
    private Integer cashHoldingDays;//实际占款天数

    private BigDecimal repoamtcstp;

    public BigDecimal getRepoamtcstp() {
        return repoamtcstp;
    }

    public void setRepoamtcstp(BigDecimal repoamtcstp) {
        this.repoamtcstp = repoamtcstp;
    }

    public Integer getCashHoldingDays() {
        return cashHoldingDays;
    }

    public void setCashHoldingDays(Integer cashHoldingDays) {
        this.cashHoldingDays = cashHoldingDays;
    }

    /**
     * {@inheritDoc}
     */
    public String getDealdateStr() {
        return DateUtil.format(super.getDealdate());
    }
    
    /**
     * {@inheritDoc}
     */
    public String getVdateStr() {
        return DateUtil.format(super.getVdate());
    }
    
    /**
     * {@inheritDoc}
     */
    public String getMdateStr() {
        return DateUtil.format(super.getMdate());
    }

    
    /**
     * 获取 pledgeBondInfoDtoList
     * @return pledgeBondInfoDtoList.
     */
    public List<PledgeBondInfoDto> getPledgeBondInfoDtoList() {
        return pledgeBondInfoDtoList;
    }

    
    /**
     * 设置 pledgeBondInfoDtoList
     * @param pledgeBondInfoDtoList pledgeBondInfoDtoList
     */
    public void setPledgeBondInfoDtoList(List<PledgeBondInfoDto> pledgeBondInfoDtoList) {
        this.pledgeBondInfoDtoList = pledgeBondInfoDtoList;
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
    
}
