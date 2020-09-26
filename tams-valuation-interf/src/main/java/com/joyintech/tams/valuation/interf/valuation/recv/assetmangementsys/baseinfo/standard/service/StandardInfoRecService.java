package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.StandardInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.standard.StandardInterfService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.entity.StandardInfoProcessorResult;

@Service
public class StandardInfoRecService {
	/**
     * 标的账户信息  模板处理Service
     */
	 @Autowired
	private StandardInterfService standardInterfService;
	 
	
	/**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
	 public StandardInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
		 StandardInfoCommonResult result = new StandardInfoCommonResult();
	        // 设置启动源属性
	        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
	        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
	       
	        if(TempConstant.标的账户信息表.equals(acctEntity.getTmpEName())){
	        	StandardInfoCommonResult resultMap = standardInterfService.execute(
	                    templateSourceFlgDTO, acctEntity);
	        	result.setValFinProductAcctList(resultMap.getValFinProductAcctList());
	        	result.setValBooksetCapacctInfoList(resultMap.getValBooksetCapacctInfoList());
	        	// ADD LEIPENG 20180830  标的基础信息无日志数据
	        	result.setValInterflog(resultMap.getValInterflog());
	        	
	        	/**add by wujie on 20180814 start remark:活期利息改造**/
	        	result.setBokDetailIdList(resultMap.getBokDetailIdList());
	        	result.setParamMap(resultMap.getParamMap());
	        	result.setListValSubjectInfo(resultMap.getListValSubjectInfo());
	        	/**add by wujie on 20180814 end**/
	        }
	        return new StandardInfoProcessorResult(result);
	 }
}
