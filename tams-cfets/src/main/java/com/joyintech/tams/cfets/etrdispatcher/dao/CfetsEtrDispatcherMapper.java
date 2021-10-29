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

import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListResponse;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListRequest;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTraderListRequest;
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


    /**
     * 交易员列表模糊查询
     * @param param
     * @return List<TrdCommandDispatcher001Dto>
     * @see
     * @since 1.0
     */
    List<CfetsEtrDispatcherTraderListRequest> getEtrTraderList(Map<String, Object> param);
}
