/*
 * 文件名：ValManualVoucher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.productinfoquery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.service.valuation.basedata.productinfoquery.service.ValProductInfoQuery001Service;
import com.joyintech.tams.common.base.entity.BokBooksetExt;

/**
 * 新建-手工凭证
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher003Controller
 * @since 2.0
 */
@RestController(value="serviceValProductInfoQuery002Contoller")
@RequestMapping("/ValProductInfoQuery002")
public class ValProductInfoQuery002Contoller extends BaseController {

    @Autowired
    private ValProductInfoQuery001Service valProductInfoQuery001Service;

    @PostMapping("/queryProductByfinprodId")
    public ValProductInfoQuery001Dto queryProductByfinprodId(@RequestBody Map<String, Object> map) {
       return valProductInfoQuery001Service.queryProductByfinprodId(map);
        
    }
    
    @PostMapping("/queryBooksetExt")
    public List<BokBooksetExt> queryBooksetExt(@RequestBody Map<String, Object> map){
    	return valProductInfoQuery001Service.queryBooksetExt(map);
    }
    
    @PostMapping("/queryBooksetExtByKey")
    public List<BokBooksetExt> queryBooksetExtByKey(@RequestBody Map<String, Object> map){
    	return valProductInfoQuery001Service.queryBooksetExtByKey(map);
    }
    
    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
//    @RequestMapping("queryFeeList")
//    @ResponseBody
//    public List<ValProductInfoFeeDto> queryFeeList()
//        throws Exception {
//        return valProductInfoQuery001Service.queryFeeList(RequestUtil.getPageParam());
//    }
}
