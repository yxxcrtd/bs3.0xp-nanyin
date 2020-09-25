/*
 * 文件名：TrdForwexInvestTrade.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.forwex.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.forwex.dao.TrdForwexInvestTradeMapper;
import com.joyintech.service.trade.forwex.dto.TrdForwexInvestTradeDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;

/**
 *  外汇远期投资交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月4日
 * @see TrdForwexInvestTradeService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdForwexInvestTradeService extends BaseService<TrdForwexInvestTradeDto> {

	/**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required=false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    
    /**
     * trdForwexInvestTradeMapper
     */
    @Autowired
    private TrdForwexInvestTradeMapper trdForwexInvestTradeMapper;
	 /**
     * 组合可用现金查询
     * <功能详细描述>
     * @param param PtlCashPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        /*BigDecimal result=null;
        if(basePtlCashPositionInfoService!=null){
            result = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
        }
        return result;*/
        if (basePtlCashPositionInfoService != null) {
            PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
            Date vdate = DateUtil.parse((String)param.get("vdate"));
            Map<String, Object> result = new HashMap<>();
            dto.setCdate(vdate);
            List<String> acctTypeList = new ArrayList<>();
            acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
            dto.setAcctType(acctTypeList);
            dto.setPortfolioId((String)param.get("portfolioId"));
            dto.setCcy((String)param.get("ccy"));
            BigDecimal cashTG = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashTG", cashTG);
            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
            BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashT", cashT);
            if (StringUtil.isNotEmpty(param.get("vdate1"))) {
                PtlCashPositionFlowDto dto2 = new PtlCashPositionFlowDto();
                Date vdate1 = DateUtil.parse((String)param.get("vdate1"));
                dto2.setCdate(vdate1);
                dto2.setAcctType(acctTypeList);
                dto2.setPortfolioId((String)param.get("portfolioId"));
                dto2.setCcy((String)param.get("ccy"));
                BigDecimal cashT1 = basePtlCashPositionInfoService.queryUsableCashPosition(dto2);
                result.put("cashT1", cashT1);
            }
            return result;
        }
        else {
            return null;
        }
    }
    
    
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
    public Page<TrdForwexInvestTradeDto> selectForPage(int page, int length,
                                                         Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("VALID_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F12", DictCodeListExt1.金融产品类型_投管用_远期.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());
        pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        pageParam.put("PS_T07", DictCodeList.交易方向_到期.getValue());

        return this.queryForPage(page, length,
            () -> trdForwexInvestTradeMapper.selectDeal(pageParam));
    }
    
    
    
    /**
     * 外汇远期投资交易信息
     *
     * @param tradeId 交易编号
     * @return 外汇远期投资交易信息
     * @see
     * @since 1.0
     */
    public TrdForwexInvestTradeDto getTrdForwexInvestTradeDto(String tradeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        params.put("PS_T07", DictCodeList.交易方向_到期.getValue());
        params.put("FINPROD_TYPE_TG_F12", DictCodeListExt1.金融产品类型_投管用_远期.getValue());
        params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        params.put("ASSET_ACCT_TYPE_01", DictCodeListExt2.标的账户类型_本方账户.getValue());
        params.put("ASSET_ACCT_TYPE_02", DictCodeListExt2.标的账户类型_对手方账户.getValue());
        params.put("SETTMODE_DIF_SETT", DictCodeListExt2.结算方式_差额清算.getValue());
        params.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        TrdForwexInvestTradeDto resultDto = trdForwexInvestTradeMapper.getTrdForwexInvestTradeDto(params);
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
    public TrdForwexInvestTradeDto getDealInfoByEntrustId(String entrustId) {
        return trdForwexInvestTradeMapper.getDealInfoByEntrustId(entrustId);
    }
    
    
}
