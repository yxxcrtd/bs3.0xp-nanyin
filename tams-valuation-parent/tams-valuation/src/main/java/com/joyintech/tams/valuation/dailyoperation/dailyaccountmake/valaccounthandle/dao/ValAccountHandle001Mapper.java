/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.system.batch.batchprocess.dto.BatchTaskDto;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle002Dto;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;

import feign.Param;

/**
 * 接口任务表
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see ValAccountHandle001Mapper
 * @since 1.0
 */
@Repository
public interface ValAccountHandle001Mapper {
    
   /**
    * 
    * @param params
    * @return
    */
    List<ValAccountHandle001Dto> getInterGroup(Map<String, Object> params);
    
    
    /**
     * 
     * @param params
     * @return
     */
    List<SelectOptionDto> getInterGroupCode(Map<String, Object> params);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<BokBookset> getBookSetId(Map<String, Object> map);
    /**
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBookSetId2(Map<String, Object> map);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValBooksetDateSwitch001Dto> findBooksetDate(Map<String, Object> map);
    
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getProdId(Map<String, Object> map);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValAccountHandle001Dto> selectBatch(Map<String, Object> map);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<ValAccountHandle001Dto> findAllTask(Map<String, Object> params);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<ValAccountHandle001Dto> findPGrpTask(Map<String, Object> params);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<BokBooksetDate> checkBokkSetDate(Map<String, Object> map);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<BatchTaskDto> getBatchTaskid(Map<String, Object> map);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    int checkExecute(Map<String, Object> map);
    

    /**
     * <一句话功能简述>
     * 债券投资目的对应的估值方案查询
     * @param Date  sysDate
     * @return List<ValAccountHandle002Dto>
     * @see
     * @since 1.0
     */
    List<ValAccountHandle002Dto> selectBokPosition( Map<String,Object> cmap);
    
    /**
     * <一句话功能简述>
     * 根据日期删除批处理日志信息
     * @param Date sysDate
     * @returna
     * @see
     * @since 1.0
     */
    void  deleteLogInfo(Map<String, Object> map);
    
    /**
     * <一句话功能简述>
     * 批处理信息入库
     * @param ValAccountHandle002Dto dto
     * @return
     * @see
     * @since 1.0
     */
    void insertLogInfo(ValAccountHandle002Dto dto);
    
    /**
     * <一句话功能简述>
     * 查询批处理日志信息是否有信息
     * @param Date sysDate
     * @return
     * @see
     * @since 1.0
     */
    int  selectBokCheckInfoCount(Map<String, Object> map);
    
    List<SelectOptionDto> selectBooksetIdByGrp(@Param("search")String search);

    /**
     * 查询账套组
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectBooksetIdByGrp(Map<String, Object> map);

    /**
     * 查询批前检查日志
     * 
     * @param map
     * @return
     */
	List<ValAccountHandle002Dto> selectCheckLog(Map<String, Object> map);
	
	/**
     * 产品组
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> selectBatchGroup(Map<String, Object> pageParam);
    
    /**
     * 获取产品组下的账套
     * @param params
     * @return
     */
    List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params);
    
    /**
     * 获取账套下的分层代码
     * @param booksetIds
     * @return
     */
    List<String> getBooksetIdbyLayeringInfo(Map<String, Object> map);
    
	/**
	 * 获取子账套代码
	 * @param map
	 * @return
	 */
    List<String> getBooksetIdSon(Map<String, Object> map);
}
