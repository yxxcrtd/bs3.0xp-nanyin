/*
 * 文件名：TrdCommandsCollection002Service.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.tams.invest.trade.investtrade.commandscollection.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection001Dto;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection002Dto;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection003Dto;

/**
 *指令汇总002Service
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollection002Service
 * @since 1.0
 */
@Service
public class TrdCommandsCollection002Service extends CommonExportService<TrdCommandsCollection002Dto>  {

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    /**
     * 查询委托详情列表
     * @param assetType 资产大类
     * @param dto 汇总指令
     * @return List<TrdCommandsCollection002Dto> 委托详情列表
     * @see
     * @since 1.0
     */
    public List<TrdCommandsCollection002Dto> selectEntrustList(String assetType, TrdCommandsCollection001Dto dto) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user", LoginUserUtil.getUserName());
        param.put("assetType", assetType);
        param.put("finprodId", dto.getFinprodId());
        param.put("ps", dto.getPs());
        param.put("tradeMarket", dto.getTradeMarket());
        param.put("tradePlat", dto.getTradePlat());
        param.put("settleSpeed", dto.getSettleSpeed());
        param.put("secAcctId", dto.getSecAcctId());
        param.put("chlId", dto.getChlId());
        param.put("dealMode", dto.getDealMode());
        param.put("board", dto.getBoard());
        param.put("counterId", dto.getCounterId());
        param.put("purchModle", dto.getPurchModle());
        param.put("purchType", dto.getPurchType());
        if(StringUtil.equals(assetType, DictCodeList.平安资产类型_质押式回购.getValue()))
            param.put("repoType", dto.getType6());
        if(StringUtil.equals(assetType, DictCodeList.平安资产类型_拆借.getValue()))
            param.put("bankLendingType", dto.getType6());
        param.put("termDays", dto.getTermDays());
        param.put("pledgeFinprod", dto.getPledgeFinprod());
        param.put("PS_T04", DictCodeList.交易方向_申购.getValue());
        param.put("PS_T49", DictCodeListExt1.交易方向_债转股.getValue());
        param.put("sysDate", DateUtil.format(DateUtil.platDateFromCache()));
        param.put("POSITION_TYPE_E01",DictCodeListExt1.持仓类型_委托指令可用.getValue());
        param.put("entrustStatus", DictCodeList.委托状态_已接收.getValue());
        param.put("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        param.put("PINAN_ASSET_TYPE_F02", DictCodeList.平安资产类型_基金.getValue());
        param.put("PINAN_ASSET_TYPE_F03", DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        param.put("PINAN_ASSET_TYPE_F05", DictCodeList.平安资产类型_存款.getValue());
        param.put("PINAN_ASSET_TYPE_F06", DictCodeList.平安资产类型_项目类资产.getValue());
        param.put("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        param.put("PINAN_ASSET_TYPE_F08", DictCodeList.平安资产类型_拆借.getValue());
        param.put("PINAN_ASSET_TYPE_F10", DictCodeList.平安资产类型_买断式回购.getValue());
        param.put("PINAN_ASSET_TYPE_F11", DictCodeListExt2.平安资产类型_股票.getValue());
        param.put("PINAN_ASSET_TYPE_F17", DictCodeListExt2.平安资产类型_一级市场债.getValue());
        param.put("TYPE6_B14", DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        param.put("TYPE6_B15", DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        return restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandsCollection/selectEntrustList", param,
                TrdCommandsCollection002Dto.class);
    }
    

    /**
     * 委托质押券详情查询
     * @param entrustId 委托代码
     * @return List<TrdCommandsCollection003Dto> 委托质押券详情
     * @see
     * @since 1.0
     */
    public List<TrdCommandsCollection003Dto> selectPledgeList(TrdCommandsCollection001Dto dto) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user", LoginUserUtil.getUserName());
        param.put("ps", dto.getPs());
        param.put("tradeMarket", dto.getTradeMarket());
        param.put("settleSpeed", dto.getSettleSpeed());
        param.put("termDays", dto.getTermDays());
        param.put("repoType", dto.getType6());
        param.put("pledgeFinprod", dto.getPledgeFinprod());
        param.put("entrustStatus", DictCodeList.委托状态_已接收.getValue());
        param.put("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        param.put("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        return restTemplate.postForList(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandsCollection/selectPledgeList", param,
            TrdCommandsCollection003Dto.class);
    }

    /**
     * 校验指令是否已经转交易到流程中
     * @param dto
     */
    public void checkEtrInFlow(TrdCommandsCollection001Dto dto) {
        if (dto != null && !StringUtil.isEmptyList(dto.getEntrustList())){
            List<TrdCommandsCollection002Dto> dtoList = dto.getEntrustList();
            for (TrdCommandsCollection002Dto entity: dtoList){
                List<WflTrdProductDealAdd> list = trdCommonBaseService.checkEtrToTrdInFlow(entity.getEntrustId());
                if (!StringUtil.isEmptyList(list)){
                    throw new RuntimeException("MSG:存在流程中数据:" + entity.getEntrustId());
                }
            }
        }
    }
}
