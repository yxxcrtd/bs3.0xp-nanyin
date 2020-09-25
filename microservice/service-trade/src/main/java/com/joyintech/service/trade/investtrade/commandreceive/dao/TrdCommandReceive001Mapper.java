/*
 * 文件名：TrdCommandDispatcher001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.commandreceive.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;
import com.joyintech.tams.common.base.entity.User;
/**
 * 指令接收mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年9月26日
 * @see TrdCommandReceive001Mapper
 * @since 1.0
 */
public interface TrdCommandReceive001Mapper {
	/**
	 * 指令已接收查询
	 * @param map
	 * @return
	 */
	List<TrdCommandReceive001Dto> selectForPage(Map<String, Object> map);
	/**
	 * 指令未接收查询
	 * @param map
	 * @return
	 */
	List<TrdCommandReceive001Dto> otherSelectForPage(Map<String, Object> map);
	/**
	 * 市场代码联想框查询  add by songyawei 共有类资产：债券，基金，股票
	 * @param finprodMarketId
	 * @return
	 */
	List<SuggestDto> getCommonFinprodMarketIds(@Param("finprodMarketId")String finprodMarketId);
	/**
	 * 市场代码联想框查询 add by songyawei 非共有类资产：除 债券，基金，股票
	 * @param finprodMarketId
	 * @return
	 */
	List<SuggestDto> getFinprodMarketIds(@Param("finprodMarketId")String finprodMarketId);
	/**
	 * 查看交易明细
	 * @param entrustId
	 * @return
	 */
	List<TrdCommandReceive001Dto> selectEtrInvest(@Param("entrustId")String entrustId);
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
     * 根据委托代码查出一级投标委托的信息
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    TrdCommandReceive001Dto getEtrBidInfo(String entrustId);
    /** @MethodName: 投资指令和预录入交易
    * @Description: TODO
    * @Param: [map]
    * @Return: java.util.List<com.joyintech.service.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto>
    * @Author: wangxuelei
    * @Date: 2020/1/9 0009
    **/
	List<TrdCommandReceive001Dto> selectForPageFromCstp(Map<String, Object> map);

	
}
