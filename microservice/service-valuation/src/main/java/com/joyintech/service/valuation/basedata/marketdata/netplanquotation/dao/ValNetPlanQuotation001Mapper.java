/*
 * 文件名：ValNe.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation001Dto;

@Repository(value="serviceValNetPlanQuotation001Mapper")
public interface ValNetPlanQuotation001Mapper {
	/**
	 * 计划类资产净值信息查询
	 * @param ValNetPlanQuotation001SearchDto
	 * @return ValNetPlanQuotation001Dto
	 */
	List<ValNetPlanQuotation001Dto> queryPageList(Map<String, Object> params); 
	
	  /**
     * 
     * 资产下拉框初始化
     * @return List<ValNetPlanQuotation001Dto>  
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodId(@Param("finprodType") String finprodType);
    //List<SuggestDto> getFinprodId();
	/**
	 * 删除    
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param finprodId
	 * @return
	 */
	int deleteByPrimaryKey(@Param("finprodId")String finprodId,@Param("valueDate")String valueDate);
   /**
    * 删除是校验
    * <一句话功能简述>
    * <功能详细描述>
    * @param finproId
    * @param valueDate
    * @return
    */
	 List<ValNetPlanQuotation001Dto> findFinproduct(@Param("finprodId") String finprodId);
	 
	 /**
	  * 判断是否在流程中的数据
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @param processMark
	  * @return
	  */
	 List<String> getBusinessDataInFlow(ProcessMark processMark);
}
