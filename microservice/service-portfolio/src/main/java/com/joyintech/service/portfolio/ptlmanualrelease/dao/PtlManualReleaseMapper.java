/*
 * 文件名：PtlCashFlowMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlmanualrelease.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.ptlmanualrelease.dto.PtlManualRelease001Dto;
import com.joyintech.tams.common.base.entity.PtlSecPosition;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenlei1
 * @version 1.0 2019年12月10日
 * @see PtlManualReleaseMapper
 * @since 1.0
 */
@Repository
public interface PtlManualReleaseMapper {

	/**
     * 获取组合代码的联想文本框
     * <功能详细描述>
     * @param map map
     * @return SuggestDto
     * @see
     * @since 1.0
     */
   // List<SelectOptionDto> getPortfolioId(Map<String,Object> map);
    List<SuggestDto> getPortfolioId(Map<String,Object> map);
    /**
     * 获取组合代码的联想文本框
     * <功能详细描述>
     * @param map map
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioIdToNew(Map<String, Object> map);
    /**
     * 查询交易对手
     * <功能详细描述>
     * @param param param
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    //List<SelectOptionDto> getCounterId(Map<String, Object> param);
    List<SuggestDto> getCounterId(Map<String,Object> map);

    /**
     * 证券账户账号联想下拉框
     */
    List<SuggestDto> getSecAcctId(@Param("secAcctId")String secAcctId);
    
    
    /**
     * 质押式正回购查询
     */
    List<PtlManualRelease001Dto> selectForPageRs(Map<String, Object> pageParam);
    
    /**
     * 质押券查询
     */
    List<PtlManualRelease001Dto> findPageRepInfo(Map<String, Object> pageParam);

    /**
     * 质押式逆回购查询
     */
    List<PtlManualRelease001Dto> selectForPageRb(Map<String, Object> pageParam);
    
    /**
     * 质押式正释放状态查询
     */
	String getReleaseStatusRs(@Param("tradeid")String tradeid);
	
	 /**
     * 质押式正释放状态更新
     */
	int updateReleaseStatusRs(@Param("tradeid")String tradeid);

	 /**
     * 组合持仓流水查询
     */
    List<PtlSecPosition> selectSecPosition(Map<String, Object> pageParam);
    
    /**
     * 质押式逆释放状态查询
     */
	String getReleaseStatusRb(@Param("tradeid")String tradeid);
	
	 /**
     * 质押式逆释放状态更新
     */
	int updateReleaseStatusRb(@Param("tradeid")String tradeid);
	
	
	PtlManualRelease001Dto selectTradeInfo(Map<String, Object> pageParam);

	
	
	List<PtlManualRelease001Dto> selectForPageRsAddSec(@Param("tradeid")String tradeid);
	
	List<PtlManualRelease001Dto> selectForPageRbAdd(@Param("tradeid")String tradeid);
	
	String selectAccId(@Param("tradeid")String tradeid);

}
