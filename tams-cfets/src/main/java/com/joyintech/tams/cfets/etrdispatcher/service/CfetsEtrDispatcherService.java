/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.etrdispatcher.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto;
import com.joyintech.tams.cfets.etrdispatcher.dao.CfetsEtrDispatcherMapper;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListResponse;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListRequest;
import com.joyintech.tams.common.base.entity.User;
import com.joyintech.tams.constant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 场外指令分发
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherService
 * @since 1.0
 */
@Service("CfetsEtrDispatcherService")
public class CfetsEtrDispatcherService extends BaseService{

    @Autowired
    private CfetsEtrDispatcherMapper cfetsEtrDispatcherMapper;

    /**
     * 指令列表分页查询
     * @param dto 入数
     * @return Page<CfetsEtrDispatcherTradeListResponse>
     */
    public Page<CfetsEtrDispatcherTradeListResponse> findAllEtrDispatcherCommon(CfetsEtrDispatcherTradeListRequest dto) {
        Map<String,Object> pageParam = BeanUtil.object2Map(dto);
        pageParam.put("entrustAwait", DictCodeList.委托状态_待接收.getValue());
        pageParam.put("entrustDistribute", DictCodeListExt3.委托状态_待分发.getValue());
        pageParam.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
        pageParam.put("entrustUntread", DictCodeList.委托状态_已完成.getValue());
        pageParam.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
        pageParam.put("entrustReturn", DictCodeList.委托状态_已退回.getValue());
        pageParam.put("entrustRecall", DictCodeList.委托状态_已撤回.getValue());


        pageParam.put("busiTypePosition01", DictCodeListExt3.头寸业务类型_债券一级投标.getValue());
        pageParam.put("busiTypePosition02", DictCodeListExt3.头寸业务类型_现券交易.getValue());
        pageParam.put("busiTypePosition03", DictCodeListExt3.头寸业务类型_债券行权交易.getValue());

        String entrustStatus = (String)pageParam.get("entrustStatus");
        if(StringUtil.isNotEmpty(pageParam.get("entrustStatus"))){
            List<String> entrustStatusList = StringUtil.splitToList(",",(String)pageParam.get("entrustStatus"));
            pageParam.put("entrustStatusList", entrustStatusList);
        }
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
            List<String> portfolioIdList = StringUtil.splitToList(",",(String)pageParam.get("portfolioId"));
            pageParam.put("portfolioIdList", portfolioIdList);
        }

        String finprodMarketId = (String)pageParam.get("finprodMarketId");
        if(StringUtil.isNotEmpty(pageParam.get("finprodMarketId"))){
            List<String> finprodMarketIdList = StringUtil.splitToList(",",(String)pageParam.get("finprodMarketId"));
            pageParam.put("finprodMarketIdList", finprodMarketIdList);
        }

        String secAcctId = (String)pageParam.get("secAcctId");
        if(StringUtil.isNotEmpty(pageParam.get("secAcctId"))){
            List<String> secAcctIdList = StringUtil.splitToList(",",(String)pageParam.get("secAcctId"));
            pageParam.put("secAcctIdList", secAcctIdList);
        }

        String invManagerId = (String)pageParam.get("invManagerId");
        if(StringUtil.isNotEmpty(pageParam.get("invManagerId"))){
            List<String> invManagerIdList = StringUtil.splitToList(",",(String)pageParam.get("invManagerId"));
            pageParam.put("invManagerIdList", invManagerIdList);
        }

        String finprodType2Old = (String)pageParam.get("finprodType2");
        if (StringUtil.isNotEmpty(finprodType2Old) || StringUtil.isNotEmpty(pageParam.get("ps"))) {
            List<String> finprodType2ListOld = StringUtil.splitToList(GlobalConstant.COMMA, finprodType2Old);
//            pageParam.put("finprodType2List", finprodType2ListOld);
            //交易方向为利率互换 转换
            if(StringUtil.isNotEmpty((String)pageParam.get("ps"))){
                if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取固定.getValue()) ){
                    pageParam.put("ps", DictCodeListExt2.互换方式_收取固定.getValue());
                    pageParam.put("IRSps", DictCodeListExt2.互换方式_收取固定.getValue());
                }
                if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取浮动.getValue()) ){
                    pageParam.put("ps", DictCodeListExt2.互换方式_收取浮动.getValue());
                    pageParam.put("IRSps", DictCodeListExt2.互换方式_收取浮动.getValue());
                }
                pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());
            }
        }
        pageParam.put("IRSType_01", DictCodeListExt2.互换方式_收取固定.getValue());
        pageParam.put("IRSType_02", DictCodeListExt2.互换方式_收取浮动.getValue());
        pageParam.put("ASSET_TYPE_07", DictCodeList.平安资产类型_拆借.getValue());
        pageParam.put("ASSET_TYPE_08", DictCodeList.平安资产类型_质押式回购.getValue());
        pageParam.put("ASSET_TYPE_10", DictCodeList.平安资产类型_买断式回购.getValue());
        pageParam.put("ASSET_TYPE_12", DictCodeListExt2.平安资产类型_债券借贷.getValue());
        pageParam.put("ASSET_TYPE_16", DictCodeListExt2.平安资产类型_利率互换.getValue());
        pageParam.put("BACK_LEND_DIRECTION", DictCode.回购拆借方向.getValue());
        pageParam.put("IRS_TYPE", DictCode.互换方式.getValue());
        pageParam.put("PS", DictCode.交易方向.getValue());
        pageParam.put("effectFlag",DictCodeListExt1.有效状态_有效.getValue());
//        //未完成状态的对应
//        if(StringUtil.isNotEmpty(entrustStatus) ){
//            if(StringUtil.equals("99", entrustStatus)){
//                pageParam.put("entrustStatus1", DictCodeList.委托状态_已接收.getValue());
//                pageParam.put("entrustStatus2", DictCodeList.委托状态_待接收.getValue());
//                pageParam.put("entrustStatus8", DictCodeListExt3.委托状态_待分发.getValue());
//            }
//        }
        pageParam.put("finType_zq", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("ps_49", DictCodeListExt3.交易方向_债转股.getValue());

        //根据组合权限表添加检索权限
        Page<CfetsEtrDispatcherTradeListResponse> pageLimit = new Page<CfetsEtrDispatcherTradeListResponse>();
        if(StringUtil.isNotEmpty(SecurityUtil.getLoginUser())) {
            pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
            pageLimit = super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(),
                    () -> cfetsEtrDispatcherMapper.findAllEtrDispatcherCommon(pageParam));
        }else{
            return pageLimit;
        }

        //将交易员和投资经理转为真实姓名
        List<CfetsEtrDispatcherTradeListResponse> list = pageLimit.getRoot();
        if(!list.isEmpty()){
//            for(CfetsEtrDispatcherTradeListResponse  cfetsEtrDispatcherTradeListResponse : list){
//                if(StringUtil.isNotEmpty(cfetsEtrDispatcherTradeListResponse.getTrader())){
//                    String[] traders = cfetsEtrDispatcherTradeListResponse.getTrader().split(",");
//                    StringBuffer buffer = new StringBuffer();
//                    StringBuffer userName = new StringBuffer();
//                    for (String trader : traders) {
//                        userName.append(trader + ",");
//                        User user=trdCommandDispatcher001Service.getRealNameByUserName(trader);
//                        if(StringUtil.isNotEmpty(user)){
//                            buffer.append(user.getRealName() + ",");
//                        }
//                    }
//                    if(buffer.toString().length() !=0){
//                        cfetsEtrDispatcherTradeListResponse.setTrader(buffer.toString().substring(0, buffer.toString().length() - 1));
//                    }
//                    if(userName.toString().length() !=0){
//                        cfetsEtrDispatcherTradeListResponse.setUserName(userName.toString().substring(0,userName.toString().length() - 1));
//                    }
//                }
//                if(StringUtil.isNotEmpty(cfetsEtrDispatcherTradeListResponse.getInvManagerId())){
//                    User user1=trdCommandDispatcher001Service.getRealNameByUserName(cfetsEtrDispatcherTradeListResponse.getInvManagerId());
//                    if(StringUtil.isNotEmpty(user1)){
//                        cfetsEtrDispatcherTradeListResponse.setInvManagerId(user1.getRealName());
//                    }
//                }
//                if(StringUtil.isNotEmpty(cfetsEtrDispatcherTradeListResponse.getBusiPs())){
//                    //委托指令附属表中取出回购/拆借方向
//                    if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), cfetsEtrDispatcherTradeListResponse.getFinprodType2())
//                            || StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), cfetsEtrDispatcherTradeListResponse.getFinprodType2()) ){
//                        cfetsEtrDispatcherTradeListResponse.setPs(cfetsEtrDispatcherTradeListResponse.getBusiPs());
//                    }
//                }
//                if(StringUtil.equals(cfetsEtrDispatcherTradeListResponse.getFinprodType2(), DictCodeListExt2.平安资产类型_一级市场债.getValue())){
//                    //债券一级投标交易方向的查询结果显示为空；
//                    cfetsEtrDispatcherTradeListResponse.setPs(null);
//                    TrdCommandDispatcher001Dto  dto2=trdCommandDispatcher001Service.getEtrBidInfo(dto.getEntrustId());
//                    if(StringUtil.isNotEmpty(dto2)){
//                        //列表项中委托额度后增加字段 投标标位
//                        cfetsEtrDispatcherTradeListResponse.setBidPrice(dto2.getBidPrice());
//                        // 委托额度显示的是投标委托的投标券面总额汇总；
//                        cfetsEtrDispatcherTradeListResponse.setEntrustAmt(dto2.getBidPrinAmt());
//                        //设置投标日期
//                        cfetsEtrDispatcherTradeListResponse.setBussBidDate(DateUtil.format(dto2.getBidDate()));
//                    }
//                }
//            }
            //TODO 需要特殊处理清算速度 0转成T+0
        }
        return pageLimit;
//        return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> cfetsEtrDispatcherMapper.findAllEtrDispatcherCommon(pageParam));
    }
}
