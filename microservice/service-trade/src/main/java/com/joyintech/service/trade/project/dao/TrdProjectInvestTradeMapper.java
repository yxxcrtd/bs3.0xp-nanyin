/*
 * 文件名：TrdDepositInvestTrade001Mapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：fanshuanghu
 * 修改时间：2018年10月10日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.project.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.invest.common.dto.trade.project.TrdProjectInvestTradeDto;

/**
 * 存款类投资交易
 * 
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdProjectInvestTradeMapper
 * @since 1.0
 */
@Repository
public interface TrdProjectInvestTradeMapper {

    List<SelectOptionDto> findEntrustId(Map<String, Object> param);

    List<TrdProjectInvestTradeDto> findPage(Map<String, Object> param);

    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);

    Map<String, Object> findInvestRate(Map<String, Object> param);

    TrdProjectInvestTradeDto findEffectiveData(Map<String, Object> param);

    void updatePayOrders(StlPayOrders stlPayOrders);

    int countFollowDeal(Map<String, Object> param);
    /**
     * 从汇总页面查询
     * @param param 资产代码
     * @return
     * @see
     * @since 1.0
     */
    List<TrdProjectInvestTradeDto> findTotalPage(Map<String, Object> param);

    /**
     * 是否还本不带息
     * @param map 资产代码
     * @return 现金流种类
     * @see
     * @since 1.0
     */
    String getIsPayInt(Map<String,Object> map);

    List<BigDecimal> getFinPrintAmt(Map<String, Object> param);

	BigDecimal getEntrustAmtByEntrustId(Map<String, Object> param);
	
	int findRevokeDeal(Map<String, Object> param);
}
