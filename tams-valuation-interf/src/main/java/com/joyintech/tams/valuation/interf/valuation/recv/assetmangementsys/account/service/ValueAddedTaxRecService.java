package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValueAddedTaxRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.account.ValueAddedTaxTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.account.dto.ValueAddedTaxRecProcessorResult;

/**
/**
  * 增值税信息接口Service
 * @author dongxiangyang
 * @version 1.0 2018年6月28日
 * @see ValueAddedTaxRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValueAddedTaxRecService {

	   /**
     * 基础信息 模板处理Service
     */
    @Autowired
    private ValueAddedTaxTemplateRecService valueAddedTaxTemplateRecService;

    /**
     * <一句话功能简述>
     * <功能 详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public ValueAddedTaxRecProcessorResult excute(TemplateOrderInfoReaderResult acctEntity){
    	//定义换回变量
    	ValueAddedTaxRecCommonResult result=new ValueAddedTaxRecCommonResult();
    	
    	 //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        result=valueAddedTaxTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        
        return new ValueAddedTaxRecProcessorResult(result);
    }
}
