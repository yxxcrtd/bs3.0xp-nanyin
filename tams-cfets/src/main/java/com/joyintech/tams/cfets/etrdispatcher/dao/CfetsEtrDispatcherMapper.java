/*
 * 文件名：EtrBondFirstBiddingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.cfets.etrdispatcher.dao;

import com.joyintech.service.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto;
import com.joyintech.tams.cfets.etrdispatcher.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 场外指令分发
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherMapper
 * @since 1.0
 */
@Repository
public interface CfetsEtrDispatcherMapper {

    /**
     * 指令列表分页查询
     * @param paraMap 参数
     * @return List<CfetsEtrDispatcherTradeListResponse>
     */
    List<CfetsEtrDispatcherTradeListResponse> findAllEtrDispatcherCommon(Map<String,Object> paraMap);

    /** @MethodName: 删除自动分发规则
     * @Return: int
     * @Author: sjj
     * @Date: 2021/10/29
     **/
    int deleteEtrAutoRule();

    /** @MethodName: 删除自动分发规则明细
     * @Return: int
     * @Author: sjj
     * @Date: 2021/10/29
     **/
    int deleteEtrAutoRuleDetail();

    /**
     * 查询交易员指令分发权限配置列表
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    List<CfetsEtrDispatcherTraderAuthListResponse>  getTraderAuthList();

    /**
     * 删除交易员指令分发的权限
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    int deleteTraderAuthList();

    /**
     * 指令信息明细查询
     *
     * @param pageParam
     * @return Page<CfetsEtrDispatcherInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    CfetsEtrDispatcherInfoResponse getEtrDispatcherInfo(Map<String, Object> pageParam);

    /**
     * 指令风险试算查询
     *
     * @param pageParam
     * @return Page<CfetsEtrDispatcherRskInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    List<CfetsEtrDispatcherRskInfoResponse> getEtrDispatcherRskInfo(Map<String, Object> pageParam);

    /**
     * 交易员列表模糊查询
     * @param param
     * @return List<TrdCommandDispatcher001Dto>
     * @see
     * @since 1.0
     */
    List<CfetsEtrDispatcherTraderListRequest> getEtrTraderList(Map<String, Object> param);

    /**
     * 交易员与工作量列表查询
     * @param param
     * @return List<CfetsEtrDispatcherTraderListRequest>
     * @see
     * @since 1.0
     */
    List<CfetsEtrDispatcherTraderListRequest> getEtrTraderAndWorkloadList(Map<String, Object> param);
}
