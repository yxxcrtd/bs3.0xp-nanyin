/*
 * 文件名：ValNetPlanQuotation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.dao.ValNetPlanQuotation001Mapper;
import com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class ValNetPlanQuotation001Service extends CommonExportService<ValNetPlanQuotation001Dto> implements BaseProcessService<ValNetPlanQuotation001Dto>{
	/**
	 * 计划类资产净值信息mapper
	 */
     @Autowired
	 private ValNetPlanQuotation001Mapper valNetPlanQuotation001Mapper;
     
     
     /**
      * 
      * <一句话功能简述> 计划类资产净值信息查询service
      *  <功能详细描述>
      * @param dto
      * @return
      * @throws Exception
      */
     public Page<ValNetPlanQuotation001Dto> queryPageList(Map<String, Object> params) throws Exception{
         
//         return super.queryForPage(()->valNetPlanQuotation001Mapper.queryPageList(dto));
    	 return restTemplate.postForPage(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation001/ValNetPlanQuotation001Search",
                 params, ValNetPlanQuotation001Dto.class);
     }
     
     /**
      * 下拉框获取：资产
      * 
      * @param params
      * @return select节点对象
      * @see
      * @since 1.0
      */
/*     public List<SelectOptionDto> getFinprodId() {
       //  params.put("finprodType", "F17");// 仅查询计划净值信息
         List<ValNetPlanQuotation001Dto> resultList = valNetPlanQuotation001Mapper.getFinprodId();
         List<SelectOptionDto> selectOptionDtoList = new ArrayList<SelectOptionDto>();
         resultList.forEach(item -> {
        	 selectOptionDtoList.add(createSelect(item.getFinprodId(), item.getFinprodAbbr()));
         });
         return selectOptionDtoList;
     }*/
     
     /**
      * 格式化创建SelectOptionDto对象
      * <功能详细描述>
      * @param selectId 下拉Id值
      * @param text	下拉文本值
      * @return dto
      * @see
      * @since 1.0
      */
     public SelectOptionDto createSelect(String selectId, String text){
     	SelectOptionDto dto=new SelectOptionDto();
     	dto.setId(selectId);
     	dto.setText(text);
     	return dto;
     }
     /**
      * 资产代码下拉框
      * <一句话功能简述>
      * <功能详细描述>
      * @param param
      * @return
      */
     public List<SuggestDto> getFinprodId(String param) {
//         String finprodType = DictCodeListExt1.金融产品类型_计划类资产.getValue();
//         return queryForSuggest(() -> {
//             return valNetPlanQuotation001Mapper.getFinprodId(finprodType);
//         }, null, param);
    	 return restTemplate.getForList(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation001/getFinprodId?param="
                 +param, SuggestDto.class);
     }
     
     
     /**
 	 * 导出功能
 	 * <一句话功能简述>
 	 * <功能详细描述>
 	 * @param params
 	 * @return
 	 * @throws Exception
 	 */
 	 public List<ValNetPlanQuotation001Dto> findPageout(Map<String, Object> params) throws Exception {
 	       
// 	        return  valNetPlanQuotation001Mapper.queryPageList(dto);
 		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation001/ValNetPlanQuotation001Search",
                params, ValNetPlanQuotation001Dto.class);
 	    }

 	/**
 	 * 删除
 	 * @param schemeId
 	 * @return
 	 */
 	public int deleteByPrimaryKey(String finprodId,String valueDate) {
 		return valNetPlanQuotation001Mapper.deleteByPrimaryKey(finprodId,valueDate);
 	}
   /**
    * 删除是检验资产是否有交易
    * <一句话功能简述>
    * <功能详细描述>
    * @param finproId
    * @param valueDate
    * @return
    */
	public List<ValNetPlanQuotation001Dto> findFinproduct(String finproId) {
//		return valNetPlanQuotation001Mapper.findFinproduct(finproId);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation001/findFinproduct",
                finproId, ValNetPlanQuotation001Dto.class);
	}

    @Override
    public void doComplete(ValNetPlanQuotation001Dto entity, OperationType type) {
     // 删除操作
        if(OperationType.DELETE.equals(type)) {        	
//        	valNetPlanQuotation001Mapper.deleteByPrimaryKey(entity.getFinprodId(), entity.getValueDateFmt());
        	restTemplate.postForObject(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation001/delete",
                    entity, Integer.class);
        }
	
 }
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<String> getBusinessDataInFlow(ProcessMark processMark) {
        return valNetPlanQuotation001Mapper.getBusinessDataInFlow(processMark);
    }

 	

}
