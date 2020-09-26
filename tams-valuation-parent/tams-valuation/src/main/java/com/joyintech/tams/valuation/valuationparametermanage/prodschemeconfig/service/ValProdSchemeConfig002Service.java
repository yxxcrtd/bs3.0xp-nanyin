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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.common.base.dao.BokBooksetPlanMapper;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.dao.ValProdSchemeConfig002Mapper;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.dto.ValProdSchemeConfig002Dto;


/**
 * 产品对账方案配置Service
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValProdSchemeConfig002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValProdSchemeConfig002Service extends CommonExportService<ValProdSchemeConfig002Dto> {
    
//    @Autowired
//    private ValProdSchemeConfig002Mapper mapper;
//
//    @Autowired
//    private BokBooksetPlanMapper bokBooksetPlanMapper;
    
//    @Autowired
//    private DBUtil dBUtil;
    
    public List<ValProdSchemeConfig002Dto> findPage(Map<String, Object> params) throws Exception{
//        params.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
//        return mapper.queryPageList(params);
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValProdSchemeConfig002/findPage",
                params, ValProdSchemeConfig002Dto.class);
    }
    
    /**
     * 更新或者入库数据
     * @param schId 方案代码
     * @param schemeType
     * @param listData 批量数据
     * @see
     * @since 1.0
     */
//    public void doInsertOrupdate(String schId, String schemeType, List<ValProdSchemeConfig002Dto> listdto){
//        // 根据方案代码查询对应的方案类型
//        for(ValProdSchemeConfig002Dto item : listdto){
//            BokBooksetPlan plan = new BokBooksetPlan();
//            plan.setBooksetId(item.getBooksetId());
//            plan.setSchemeId(item.getNowSchId());
//            plan.setSchemeType(schemeType);
//            // 检查账套方案代码是否为空，有，则说明是更新操作，没有则是新建操作
//            if(StringUtil.isNotEmpty(item.getBooksetSchemeId())){
//                plan.setBooksetSchemeId(item.getBooksetSchemeId());
//                // 更新操作
//                bokBooksetPlanMapper.updateByPrimaryKey(plan);
//            }else{
//                // 初始化账套方案代码
//                plan.setBooksetSchemeId(dBUtil.getSequenceIndexData(DataIndexSequenceType.处理序列));
//                // 入库操作
//                bokBooksetPlanMapper.insert(plan);
//            }
//        }
//    }
    
    /**
     * 更新或者入库数据
     * @param schId 方案代码
     * @param schemeType
     * @param listData 批量数据
     * @see
     * @since 1.0
     */
    public void doInsertOrupdate(String schId, String schemeType, String datas){
    	Map<String,Object> params = new HashMap<>();
    	params.put("schId", schId);
    	params.put("schemeType", schemeType);
    	params.put("datas", datas);
    	restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValProdSchemeConfig002/doInsertOrupdate",
                params,Integer.class);
    }
}
