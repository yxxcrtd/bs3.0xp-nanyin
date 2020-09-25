/*
 * 文件名：BusiAutoEtrDispMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.entrust.impl.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.base.entrust.impl.dto.TrdCommandDispatcher001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 委托自动分发 
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年12月14日
 * @see BusiAutoEtrDispMapper
 * @since 1.0
 */
@Repository
public interface BusiAutoEtrDispMapper {
    
    /**
     * 
     * 查询委托下达时间
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    int checkEntrustTime(Map<String,Object> param);


    int checkEntrustTimeByAuto(Map<String,Object> param);
    /**
     * 
     * 根据委托指令、授权检索有权限交易员
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<String> selectTrader(Map<String,Object> param);

    /** @MethodName: 已分发查询
     * @Description: TODO
     * @Param: [param]
     * @Return: java.util.List<com.joyintech.service.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto>
     * @Author: wangxuelei
     * @Date: 2020/1/3 0003
     **/
    List<TrdCommandDispatcher001Dto> selectAlreadyDistribute(Map<String, Object> param);
    /** @MethodName: 交易员序列顺序
     * @Description: TODO
     * @Param: [param]
     * @Return: java.util.List<com.joyintech.service.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto>
     * @Author: wangxuelei
     * @Date: 2020/1/3 0003
     **/
    List<TrdCommandDispatcher001Dto> selectTradeSeqbByEntrust(Map<String, Object> param);

    /** @MethodName: 获取自动分发的规则
     * @Description: TODO
     * @Param: [param]
     * @Return: java.util.List<com.joyintech.service.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto>
     * @Author: wangxuelei
     * @Date: 2020/1/3 0003
     **/
    List<TrdCommandDispatcher001Dto> selectEtrAutoRuleAndDetail(@Param("sysDate") String sysDate);

    //根据当前指派的交易员编号获取对应的权限列表
    List<TrdCommandDispatcher001Dto> getAuthType(Map<String, Object> param);
}
