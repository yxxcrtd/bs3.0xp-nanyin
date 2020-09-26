/*
 * 文件名：ValPlannedassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assets.plan.dto.ValPlannedassetquery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 
 * 债券类资产查询
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValPlannedassetquery001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValPlannedassetquery001Service extends CommonExportService<ValPlannedassetquery001Dto> {
    
    /**
     * ValPlannedassetquery001Mapper
     */
//    @Autowired
//    private ValPlannedassetquery001Mapper mapper;
    
    
    /**
     * 债券下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    
    public Page<SelectOptionDto> getBondId(Map<String, Object> params) {
//        return super.queryForPageForSelectReturn(()->mapper.getBondId(params));
    	 return restTemplate.postForPage(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/ValPlannedassetquery001/getBondId",
                 params, SelectOptionDto.class);
    }
    

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValPlannedassetquery001Dto> findPage(Map<String, Object> params) throws Exception {
        
//        String bond = (String)params.get("bond");
//        String profitType = (String)params.get("profitType");
//        String assetType = (String)params.get("assetType");
//        
//        String[] bondArr = null;
//        if(StringUtil.isNotEmpty(bond)){
//            bondArr = bond.split(",");
//        }
//        String[] profitTypeArr = null;
//        if(StringUtil.isNotEmpty(profitType)){
//            profitTypeArr = profitType.split(",");
//        }
//        String[] assetTypeArr = null;
//        if(StringUtil.isNotEmpty(assetType)){
//            assetTypeArr = assetType.split(",");
//        }
//        
//        params.put("bondArr",bondArr);
//        params.put("profitTypeArr", profitTypeArr);
//        params.put("assetTypeArr", assetTypeArr);
//        params.put("finprodType", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        
//        return super.queryForPage(()-> mapper.queryPageList(params));
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValPlannedassetquery001/findPage",
                params, ValPlannedassetquery001Dto.class);
    }


    /**
     * 导出功能
     * <功能详细描述>
     * @param paraMap
     * @return
     * @see
     * @since 1.0
     */
    public List<ValPlannedassetquery001Dto> findPageout(Map<String, Object> params) throws Exception {
       
//        return mapper.queryPageList(params);
    	  return restTemplate.postForList(
                  ServiceApplication.VALUATION_SERVICE_NAME + "/ValPlannedassetquery001/findAll",
                  params, ValPlannedassetquery001Dto.class);
    }

    /**
     * 
     * 根据条件查询全部信息
     * @param params 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
//    public List<ValBondassetquery001Dto> selectAll(ValBondassetquery001SearchDto dto){
//        return mapper.queryPageList(dto);
//    }
}

