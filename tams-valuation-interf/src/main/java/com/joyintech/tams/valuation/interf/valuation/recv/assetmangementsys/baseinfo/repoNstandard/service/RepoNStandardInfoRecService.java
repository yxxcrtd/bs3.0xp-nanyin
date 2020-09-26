/*
 * 文件名：NPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.repoNstandard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.RepoNStandardCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.repoNstandard.RepDealAssetTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.repoNstandard.RepDealSecSubjectTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.repoNstandard.entity.RepoNStandardInfoProcessorResult;

/**
 * 批处理启动文件接收批处理
 * 对债券质押式回购标的信息文件系列机型处理，供给Writer使用
 * @author qiangchengxi
 * @version 1.0 2018年6月22日
 * @see RepoNStandardInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepoNStandardInfoRecService {
    
    /**
     * 债券质押式回购标的信息 模板处理Service
     */
    @Autowired
    private RepDealSecSubjectTemplateRecService repDealSecSubjectTemplateRecService;
    
    
    /**
     * 债券回购质押品信息 模板处理Service
     */
    @Autowired
    private RepDealAssetTemplateRecService repDealAssetTemplateRecService;
    
  
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public RepoNStandardInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
    	RepoNStandardCommonResult resultCommon = new RepoNStandardCommonResult();
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        if (TempConstant.债券回购标的信息.equals(acctEntity.getTmpEName())) {
            resultCommon = repDealSecSubjectTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }else if (TempConstant.债券回购质押品信息.equals(acctEntity.getTmpEName())) {
            resultCommon = repDealAssetTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        return new RepoNStandardInfoProcessorResult(resultCommon);
    }
    
    

}
