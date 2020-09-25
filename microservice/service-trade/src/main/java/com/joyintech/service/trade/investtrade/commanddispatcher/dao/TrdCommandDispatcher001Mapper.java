/*
 * 文件名：TrdCommandDispatcher001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.commanddispatcher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto;
import com.joyintech.tams.common.base.entity.EtrDistributeKey;
import com.joyintech.tams.common.base.entity.PaEtrAutoDispenseTime;
import com.joyintech.tams.common.base.entity.User;


/**
 * 指令分发mapper
 * @author wangkang
 * @version 1.0 2018年9月12日
 * @see TrdCommandDispatcher001Mapper
 * @since 1.0
 */
@Repository
public interface TrdCommandDispatcher001Mapper {
	/**
     * 查询 <功能详细描述>
     * 
     * @param param 查询参数
     * @return 查询返回数据
     * @see
     * @since 1.0
     */
    List<TrdCommandDispatcher001Dto> selectForPage(Map<String, Object> param);
    /**
     * 委托代码的联想文本框的查询方法
     * @param param 
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getEntrustId(Map<String,Object> param);
    /**
     * 组合代码的联想文本框的查询方法
     * @param param 
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getPortfolioIds(Map<String,Object> param);
    /**
     * 市场代码的联想文本框的查询方法
     * @param param 
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getCommonFinprodIds(@Param("finprodId")String finprodId);
    /**
     * 市场代码的联想文本框的查询方法
     * @param param 
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodIds(@Param("finprodId")String finprodId);
    
    /**
     * 获取radio交易员组的查询方法
     * @param param 
     * @return List<TrdCommandDispatcher001Dto>
     * @see
     * @since 1.0
     */
    List<TrdCommandDispatcher001Dto> getRadios(Map<String, Object> param);
    
    /**
     * 获取联想文本框交易员的查询方法
     * @param param 
     * @return List<TrdCommandDispatcher001Dto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getUsers(Map<String, Object> param);
    /**
     * 获取投资经理
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getManagers(Map<String, Object> param);
    
    /**
     * 获取委托自动分发时间的方法
     * @return List<TrdCommandDispatcher001Dto>
     */
    List<TrdCommandDispatcher001Dto>   selectEtrAutoTime();
    
    /**
     * 判断用户是否有操作相应资产类型的权限
     * @param param
     * @return
     */
    List<TrdCommandDispatcher001Dto>   getAssetTypeUser(Map<String, Object> param);
    /**
     * 删除原先分发的所有交易员记录
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
    int deleteEtrDistributeByPrimaryKey(EtrDistributeKey key);
    /**
     * 根据用户名查找真实姓名
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    User getRealNameByUserName(@Param("userName")String userName);
    
    /**
     * 删除自动分发的日期
     * @return
     */
    int deleteEtrAutoTime();
    
    /**
     * 插入自动分发的日期
     * @return
     */
    int insertEtrAutoTime(PaEtrAutoDispenseTime dto);
    /**
     * 根据委托代码查询委托投标标位
     * @return
     */
    TrdCommandDispatcher001Dto getEtrBidInfo(String entrustId);
    /**
     * 根据投标日查询一投标债的交易人员的信息
     * @param
     * @return
     */
    List<TrdCommandDispatcher001Dto> getTraders(TrdCommandDispatcher001Dto dto);
    /** @MethodName: 删除自动分发规则
    * @Description: TODO
    * @Param: []
    * @Return: int
    * @Author: wangxuelei
    * @Date: 2020/1/3 0003
    **/
    int deleteEtrAutoRule();
    /** @MethodName: 删除自动分发规则明细
    * @Description: TODO
    * @Param: []
    * @Return: int
    * @Author: wangxuelei
    * @Date: 2020/1/3 0003
    **/
    int deleteEtrAutoRuleDetail();

    List<TrdCommandDispatcher001Dto> selectEtrAutoRuleAndDetail(@Param("sysDate") String sysDate);

    /**
     * 查询交易员指令分发权限配置列表
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    List<TrdCommandDispatcher001Dto>  getTraderAuthList();

    /**
     * 删除交易员指令分发的权限
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    int deleteTraderAuthList();
    
    /**
     * 根据委托代码查询委托状态
     * add by hpp 20200710
     * @param map
     * @return
     */
    List<TrdCommandDispatcher001Dto> getEntrustStatus(Map<String, Object> map);
}
