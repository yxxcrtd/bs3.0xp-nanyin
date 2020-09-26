/*
 * 文件名：NPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngassetn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.NPlannedAssetBaseInfoCommonRecResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nmngasset.NPlannedBaseInfoTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngassetn.dto.NPlannedAssetBaseInfoRecResult;

/**
 * 批处理启动文件接收批处理
 * 对计划类资产（净值型）基础信息文件系列机型处理，供给Writer使用
 * @author ChuFengjie
 * @version 1.0 2018年8月3日
 * @see NPlannedAssetBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPlannedAssetBaseInfoRecService {
    
    /**
     * 基础信息 模板处理Service
     */
    @Autowired
    private NPlannedBaseInfoTemplateRecService nPlannedBaseInfoTemplateRecService;  
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public NPlannedAssetBaseInfoRecResult execute(TemplateOrderInfoReaderResult acctEntity) {
               
        //定义返回变量
        NPlannedAssetBaseInfoCommonRecResult rtnResult = new NPlannedAssetBaseInfoCommonRecResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
                
       
        //计划类资产（净值型）基础信息数据模板-产品基础信息
        if(TempConstant.计划类资产净值型基础信息表.equals(acctEntity.getTmpEName())){            
            rtnResult = nPlannedBaseInfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }       
        return new NPlannedAssetBaseInfoRecResult(rtnResult);
    }
    
    

}
