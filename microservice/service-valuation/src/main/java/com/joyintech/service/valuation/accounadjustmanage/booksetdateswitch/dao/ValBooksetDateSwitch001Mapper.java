/*
 * 文件名：ValBooksetDateSwitch001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.system.customerreport.config.dao.CustomerReportConfig001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;

/**
 * 账套日期切换
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig001Mapper
 * @since 1.0
 */
@Repository(value="serviceValBooksetDateSwitch001Mapper")
public interface ValBooksetDateSwitch001Mapper {
    
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValVoucherQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValBooksetDateSwitch001Dto> queryPageList(Map<String, Object> params);

    /**
     * 产品组
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> selectBatchGroup(Map<String, Object> pageParam);

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> getBooksetId(Map<String, Object> pageParam);

    /**
     * 获取产品组下的账套
     * @param params
     * @return
     */
    List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params);
}
