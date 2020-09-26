/*
 * 文件名：ValOrdersQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.squaremanagement.ordersquery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.squaremanagement.ordersquery.dao.ValOrdersQuery001Mapper;
import com.joyintech.tams.valuation.squaremanagement.ordersquery.dto.ValOrdersQuery001Dto;

/**
 * 
 * 结算指令查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValOrdersQuery001Service
 * @since 1.0
 */
@Service
public class ValOrdersQuery001Service extends CommonExportService<ValOrdersQuery001Dto> {
	
    /**
     * ValOrdersQuery001Mapper
     */
//    @Autowired
//    private ValOrdersQuery001Mapper mapper;
    
//    /**
//     * 根据条件获取表格信息
//     * 
//     * @param params 筛选条件
//     * @return 表格信息
//     * @throws Exception 
//     */
//    public Page<ValOrdersQuery001Dto> findPage(Map<String, Object> params) throws Exception {
//        //关联入账信息表的业务编号和业务表--对应金融产品表
//        params.put("busiTable", DictCodeList.业务表_结算指令表.getValue());
//        return super.queryForPage(()-> mapper.queryPageList(params));
//    }
    
      /**
      * 根据条件获取表格信息
      * 
      * @param params 筛选条件
      * @return 表格信息
      * @throws Exception 
      */
     public Page<ValOrdersQuery001Dto> findPage(Map<String, Object> params) throws Exception {
         return restTemplate.postForPage(
             ServiceApplication.VALUATION_SERVICE_NAME + "/ValOrdersQuery001/findPage",
             params, ValOrdersQuery001Dto.class);
     }
    
    
    
}
