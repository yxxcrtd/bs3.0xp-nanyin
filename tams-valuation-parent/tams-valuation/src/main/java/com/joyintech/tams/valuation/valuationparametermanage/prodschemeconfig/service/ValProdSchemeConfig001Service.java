/*
 * 文件名：ReconcileSchemeInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.dao.ValProdSchemeConfig001Mapper;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.dto.ValProdSchemeConfig001Dto;


/**
 * 产品对账方案配置Service
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValProdSchemeConfig001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValProdSchemeConfig001Service extends CommonExportService<ValProdSchemeConfig001Dto> {
    
//    @Autowired
//    private ValProdSchemeConfig001Mapper mapper;
    
    public Page<ValProdSchemeConfig001Dto> findPage(Map<String, Object> params) throws Exception{
//        List<String> schemeTypeList = new ArrayList<String>();
//        String schemeType = (String) params.get("schemeType");
//        // 查询所需配置的账套是否有方案类型
//        if(StringUtil.isNotEmpty(schemeType)){
//            schemeTypeList.add(schemeType);
//        }
//        // 若没有，则查询全部的估值表和余额表的对账方案
//        else{
//            schemeTypeList.add(DictCodeListExt1.配置方案类型_估值表对账方案.getValue());
//            //schemeTypeList.add(DictCodeListExt1.配置方案类型_余额表对账方案.getValue());
//        }
//        params.put("schemeTypeList", schemeTypeList);
//        params.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
//        /**modify by dongjun on 20181205 remark:HZ-UAT-050 start **/
//        String booksetId = (String)params.get("booksetId");
//        String[] booksetIdArr = null;
//        if(StringUtil.isNotEmpty(booksetId)){
//            booksetIdArr = booksetId.split(",");
//        }
//        params.put("booksetIdArr", booksetIdArr);
//        /**modify by dongjun on 20181205 remark:HZ-UAT-050 end **/
//        return super.queryForPage(()-> mapper.queryPageList(params));
    	
    	return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValProdSchemeConfig001/findPage",
                params, ValProdSchemeConfig001Dto.class);
    }
    
    public List<ValProdSchemeConfig001Dto> selectAll(Map<String, Object> params) throws Exception{
//        List<String> schemeTypeList = new ArrayList<String>();
//        String schemeType = (String) params.get("schemeType");
//        // 查询所需配置的账套是否有方案类型
//        if(StringUtil.isNotEmpty(schemeType)){
//            schemeTypeList.add(schemeType);
//        }
//        // 若没有，则查询全部的估值表和余额表的对账方案
//        else{
//            schemeTypeList.add(DictCodeListExt1.配置方案类型_估值表对账方案.getValue());
//            schemeTypeList.add(DictCodeListExt1.配置方案类型_余额表对账方案.getValue());
//        }
//        params.put("schemeTypeList", schemeTypeList);
//        params.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
//        return mapper.queryPageList(params);
    	
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValProdSchemeConfig001/selectAll",
                params, ValProdSchemeConfig001Dto.class);
    }
    
    /**
     * 检索方案信息
     * 
     * @param param 参数
     * @return 方案信息List
     * @throws Exception 错误
     * @since 1.0
     */
    public List<SelectOptionDto> getSchemeInfoList(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValProdSchemeConfig001/getSchemeInfoList",
                params, SelectOptionDto.class);
    }
}
