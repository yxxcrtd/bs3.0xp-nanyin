/*
 * 文件名：TrdIRSTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.irs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.irs.dao.TrdIRSTradeMapper;
import com.joyintech.service.trade.irs.dto.TrdIRSTradeDto;
import com.joyintech.tams.common.base.entity.FinCashPlanEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 利率互换交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月16日
 * @see TrdIRSTradeService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdIRSTradeService extends BaseService<TrdIRSTradeDto>{
	
	@Autowired
	private TrdIRSTradeMapper trdIRSTradeMapper;
	 /**
     * 分页查询
     *
     * @param page 页编号
     * @param length 页条数
     * @param pageParam 画面参数
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<TrdIRSTradeDto> selectForPage(int page, int length,
                                                         Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("VALID_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F14", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());
        pageParam.put("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
        pageParam.put("INT_TYPE_02", DictCodeList.利率类型_浮动利率.getValue());
        pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());

        return this.queryForPage(page, length,
            () -> trdIRSTradeMapper.selectDeal(pageParam));
    }
    
    
    
    /**
     * 外汇远期投资交易信息
     *
     * @param tradeId 交易编号
     * @return 外汇远期投资交易信息
     * @see
     * @since 1.0
     */
    public TrdIRSTradeDto getTrdIRSTradeDto(String tradeId,Long branch) {
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("FINPROD_TYPE_TG_F14", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        params.put("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
        params.put("INT_TYPE_02", DictCodeList.利率类型_浮动利率.getValue());
        params.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        params.put("FEE_TYPE_B06", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        params.put("FEE_TYPE_B07", DictCodeListExt1.一次性费用类型_结算费.getValue());
        params.put("ACCTTYPE_01", DictCodeListExt2.标的账户类型_本方账户.getValue());
        params.put("ACCTTYPE_02", DictCodeListExt2.标的账户类型_对手方账户.getValue());
        params.put("BRANCH_0", branch);
        TrdIRSTradeDto resultDto = trdIRSTradeMapper.getTrdIRSTradeDto(params);
        return resultDto;
    }
    
    /**
     * 由委托代码带出交易信息
     * 
     * @param entrustId 委托代码
     * @return 交易信息
     * @see
     * @since 1.0
     */
    public TrdIRSTradeDto getDealInfoByEntrustId(String entrustId) {
        return trdIRSTradeMapper.getDealInfoByEntrustId(entrustId);
    }

    /**
     * 根据交易编号获取相应的现金流计划明细
     *
     * @param tradeId 交易编号
     * @return 现金流计划明细list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<FinCashPlanEntity> getCashPlanList(String tradeId) {
        return trdIRSTradeMapper.getCashPlanList(tradeId);
    }

    public List<FinCashInfo> getFinCashInfoList(String tradeId) {
        return trdIRSTradeMapper.getFinCashInfoList(tradeId);
    }
}
