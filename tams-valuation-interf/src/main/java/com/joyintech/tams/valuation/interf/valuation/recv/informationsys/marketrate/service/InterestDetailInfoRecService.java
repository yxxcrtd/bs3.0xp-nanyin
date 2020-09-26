package com.joyintech.tams.valuation.interf.valuation.recv.informationsys.marketrate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dto.InterestInfoRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.marketrate.InterestRateTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.informationsys.marketrate.dto.InterestDetailInfoProcessorResult;


/**
/**
  * 行情利率信息接口Service
 * @author dongxiangyang
 * @version 1.0 2018年7月06日
 * @see InterestDetailInfoRecService
 * @since 1.0
 */

@Service
@Scope("prototype")
public class InterestDetailInfoRecService {

	/**
     * 基础信息 模板处理Service
     */
	  @Autowired
   private InterestRateTemplateRecService interestRateTemplateRecService;
   
    public InterestDetailInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
    	//定义返回变量
    	InterestInfoRecCommonResult rtnResult=new InterestInfoRecCommonResult();
    	
    	 //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
    	
        rtnResult=interestRateTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
       
        return new InterestDetailInfoProcessorResult(rtnResult);
    }
    
}
