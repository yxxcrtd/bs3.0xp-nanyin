/*
 * 文件名：BaseTradeProductDealDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：huwenbin 修改时间：2018年9月4日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.busiAutoAddSecInfo.dto;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 金融产品交易信息实体 〈功能详细描述〉
 *
 * @author huwenbin
 * @version 1.0 2018年9月4日
 * @see BusiAutoAddTradeProductDealRedisDto
 * @since 1.0
 */
public class BusiAutoAddTradeProductDealRedisDto {

    /**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 上市代码
     */
    public String appearMarketId;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 组装后交易数据
     */
    List<BaseTradeInfoDto> baseTradeInfoDtos = new ArrayList<BaseTradeInfoDto>();

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getAppearMarketId() {
        return appearMarketId;
    }

    public void setAppearMarketId(String appearMarketId) {
        this.appearMarketId = appearMarketId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public List<BaseTradeInfoDto> getBaseTradeInfoDtos() {
        return baseTradeInfoDtos;
    }

    public void setBaseTradeInfoDtos(List<BaseTradeInfoDto> baseTradeInfoDtos) {
        this.baseTradeInfoDtos = baseTradeInfoDtos;
    }
}
