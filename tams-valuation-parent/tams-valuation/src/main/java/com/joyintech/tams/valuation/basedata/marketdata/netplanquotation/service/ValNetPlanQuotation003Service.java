/*
 * 文件名：ValNetPlanQuotation003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.dao.ValNetPlanQuotation003Mapper;
import com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation003Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * 计划类资产净值信息新建修改删除service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月16日
 * @see ValNetPlanQuotation003Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValNetPlanQuotation003Service extends CommonExportService<ValNetPlanQuotation003Dto> implements BaseProcessService<ValNetPlanQuotation003Dto> {

//	@Autowired
//	private ValNetPlanQuotation003Mapper valNetPlanQuotation003Mapper;
	
    /**
     * 联动获取资产名称
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     */
	public String getFinprodAbbr(Map<String, Object> map) {	
//		String msg="";
//		String finprodId = (String) map.get("finprodId");		
//		msg= valNetPlanQuotation003Mapper.getFinprodAbbr(finprodId);	
//		return msg;
		return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation003/getFinprodAbbr",
                map, String.class);
		
	}
	
    /**
     * 审批完成后完成新增和修改操作
     * {@inheritDoc}
     */
	@Override
	public void doComplete(ValNetPlanQuotation003Dto entity, OperationType type) {		
	      // 新建操作
//        if(OperationType.NEW.equals(type)) {
//        	entity.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
//        	valNetPlanQuotation003Mapper.insert(entity);
//        }//修改
//        if(OperationType.MODIFY.equals(type)){
//        	valNetPlanQuotation003Mapper.updateByPrimaryKeySelective(entity);
//        }
		
	    String action = StringUtil.EMPTY;
	    if (OperationType.NEW.equals(type)) {
	        action = "insert";
	    }else if (OperationType.MODIFY.equals(type)){
	        action = "update";
	    }
	    /**add by yanya on 20191031 增加删除逻辑（从单估值合并过来）**/
	    else if(OperationType.DELETE.equals(type)) {
	        action = "delete";
	    }

	    restTemplate.postForObject(
	        ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation003/" + action, entity,
	        Integer.class);
	}	
    
	/**
	 * 新增前检验唯一性
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param finprodId
	 * @param valueDate
	 * @return
	 */
	public List<ValNetPlanQuotation003Dto> searchList(Map<String, Object> map) {
//			return valNetPlanQuotation003Mapper.searchList(map);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation003/searchList",
                map, ValNetPlanQuotation003Dto.class);
		}
   /**
    * 根据finproId和valueDate查询
    * <一句话功能简述>
    * <功能详细描述>
    * @param finprodId
    * @param valueDate
    * @return
    */
	public ValNetPlanQuotation003Dto findAllById(Map<String, Object> map) {
//		return valNetPlanQuotation003Mapper.findAllById(finprodId,valueDate);
		return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation003/findAllById",
                map, ValNetPlanQuotation003Dto.class);
	}
    
	/**
	 * 新建是资产代码校验
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param finprodId
	 * @return
	 */
    public List<ValNetPlanQuotation003Dto> searchfinprodId(String finprodId) {
//	    return  valNetPlanQuotation003Mapper.searchfinprodId(finprodId);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation003/searchfinprodId",
                finprodId, ValNetPlanQuotation003Dto.class);
    }

}
