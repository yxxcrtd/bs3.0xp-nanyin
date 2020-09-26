/*
 * 文件名：ProductBalanceReconciliation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.balancereconcy.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.balancereconcy.dao.ProductBalanceReconciliation001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.balancereconcy.dto.ProductBalanceReconciliation001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.ConfirmPaDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 产品余额表对账 - service
 * 
 * @author yangjian
 * @version 1.0 2018年11月16日
 * @see ProductBalanceReconciliation001Service
 * @since 1.0
 */
@Service
public class ProductBalanceReconciliation001Service extends CommonExportService<ProductBalanceReconciliation001Dto> {

    /**
     * ProductBalanceReconciliation001Mapper
     */
    @Autowired
    private ProductBalanceReconciliation001Mapper mapper;

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ProductBalanceReconciliation001Dto> findPage(Map<String, Object> params)
        throws Exception {

        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ProductBalanceReconciliation001/findPage", params, ProductBalanceReconciliation001Dto.class);
        
//        // 所选产品
//        String productIds = (String)params.get("productIds");
//        String[] finprodIdArr = null;
//        if (StringUtil.isNotEmpty(productIds)) {
//            finprodIdArr = productIds.split(",");
//        }
//        params.put("finprodIdArr", finprodIdArr);
//
//        return super.queryForPage(() -> mapper.queryPageList(params));
    }
    
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ProductBalanceReconciliation001/getFinprodId", map, SelectOptionDto.class);
    }
    
    /**
     * 余额表对账
     * 
     * @param paDto
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult doConfirm(List<ConfirmPaDto> paDtoList) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ProductBalanceReconciliation001/doConfirm", paDtoList, AjaxResult.class);
    }
    
    /**
     * 取消对账操作
     * 
     * @param paDtoList
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult doCancle(List<ConfirmPaDto> paDtoList) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ProductBalanceReconciliation001/doCancle", paDtoList, AjaxResult.class);
    }
    
    /**
     * 调整操作
     * 
     * @param paDtoList
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult doAdjust(List<ConfirmPaDto> paDtoList) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ProductBalanceReconciliation001/doAdjust", paDtoList, AjaxResult.class);
    }
}
