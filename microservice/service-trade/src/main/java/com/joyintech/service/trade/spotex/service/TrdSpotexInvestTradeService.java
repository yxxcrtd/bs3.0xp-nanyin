/*
 * 文件名：TrdSpotexInvestTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.spotex.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.trade.spotex.dao.TrdSpotexInvestTradeMapper;
import com.joyintech.service.trade.spotex.dto.TrdSpotexInvestTradeDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;

/**
 * 外汇即期交易微服务用Service
 * 
 * @author wangguoxian
 * @version 1.0 2019年7月5日
 * @see TrdSpotexInvestTradeService
 * @since 1.0
 */
@Service
public class TrdSpotexInvestTradeService extends BaseService<TrdSpotexInvestTradeDto> {

    /**
     * TrdSpotexInvestTradeMapper
     */
    @Autowired
    private TrdSpotexInvestTradeMapper trdSpotexInvestTradeMapper;
    
    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 分页查询
     * 
     * @param page 页数
     * @param length 每页显示条数
     * @param pageParam 查询参数
     * @return 查询结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<TrdSpotexInvestTradeDto> selectForPage(int page, int length,
                                                       Map<String, Object> pageParam){
        pageParam.put("effective", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("revoke", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("inflow", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("FINPROD_TYPE_TG_F11", DictCodeListExt1.金融产品类型_投管用_即期.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());
        return this.queryForPage(page, length,
            () -> trdSpotexInvestTradeMapper.findPage(pageParam));

    }

    /**
     * 
     * 查询交易详情信息
     * @param tradeId 交易编号
     * @return 交易详情
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public TrdSpotexInvestTradeDto getDealInfo(Map<String, Object> map){
        Page<TrdSpotexInvestTradeDto> dtoList = this.selectForPage(1, 1, map);
        if (dtoList != null && dtoList.getRoot() != null && dtoList.getRoot().size() > 0) {
            return dtoList.getRoot().get(0);
        }
        return null;
    }
    
    /**
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        Date sysdate = DateUtil.parse((String)param.get("sysDate"));
        dto.setCdate(sysdate);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        dto.setAcctType(acctTypeList);
        dto.setPortfolioId((String)param.get("portfolioId"));
        dto.setCcy((String)param.get("ccy"));
        BigDecimal cashTG = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
        result.put("cashTG", cashTG);

        return result;
    }
    
    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return trdSpotexInvestTradeMapper.selectInFlowDataCount(tradeId) > 0;
    }
    
    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isAlreadyRevoked(String tradeId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tradeId", tradeId);
        paramMap.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        paramMap.put("TRADE_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        return trdSpotexInvestTradeMapper.selectRevokedDataCount(paramMap) > 0;
    }

    /**
     * 根据委托ID查询剩余可用委托额度
     * @param entrustId 委托ID
     * @return 剩余可用委托额度
     */
    public BigDecimal selAvailableEtrAmt(String entrustId){
        return trdSpotexInvestTradeMapper.selAvailableEtrAmt(entrustId);
    }

}
