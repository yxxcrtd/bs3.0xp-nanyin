/*
 * 文件名：ValNetPlanQuotation003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.netplanquotation.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation003Dto;
import com.joyintech.service.valuation.basedata.marketdata.netplanquotation.service.ValNetPlanQuotation003Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 计划类资产净值信息新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月15日
 * @see ValNetPlanQuotation003Controller
 * @since 1.0
 */
//@RestController(value="serviceValNetPlanQuotation003Controller")
@RestController("valNetPlanQuotation003Ctl_dtx")
@RequestMapping("/ValNetPlanQuotation003")
public class ValNetPlanQuotation003Controller extends BaseController {
	
	@Autowired
	private ValNetPlanQuotation003Service valNetPlanQuotation003Service;
		
	@PostMapping("/findAllById")
	public ValNetPlanQuotation003Dto findAllById(@RequestBody Map<String, Object> map) {
        	return valNetPlanQuotation003Service.findAllById(map);
          
	}
	
	@Transactional
	@DTXTransaction
	@PostMapping("/insert")
    public void insert(@RequestBody ValNetPlanQuotation003Dto dto) throws Exception {
		valNetPlanQuotation003Service.doComplete(dto,OperationType.NEW);
    }
    
    /**
     * 修改
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
	@Transactional
	@DTXTransaction
    @PostMapping("/update")
    public void update(@RequestBody ValNetPlanQuotation003Dto dto) throws Exception{
    	valNetPlanQuotation003Service.doComplete(dto,OperationType.MODIFY);
    }
	
	
	   /**
     * 下拉框获取：资产
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
//     @RequestMapping("/getFinprodId")
//     @ResponseBody
//     public List<SuggestDto> getFinprodId()
//            throws Exception {
//        String param = super.getRequest().getParameter("param");
//        return valNetPlanQuotation001Service.getFinprodId(param);
//     }
     
     /**
      * 联动获取资产名称
      * <一句话功能简述>
      * <功能详细描述>
      * @return
      */
     @PostMapping("/getFinprodAbbr")
     public String getFinprodAbbr(@RequestBody Map<String, Object> map) {
    	 return valNetPlanQuotation003Service.getFinprodAbbr(map);
          
     }
	
     /**
      * 新建----计划类资产净值信息 ---走流程
      * <一句话功能简述>
      * <功能详细描述>
      * @param dto
      * @param br
      * @return
      */
     @Transactional
     @DTXTransaction
     @ResponseBody
     @RequestMapping(value = PAGE_SAVE, method=RequestMethod.POST)
     public AjaxResult doSave(@RequestBody @Valid ValNetPlanQuotation003Dto dto, BindingResult br) {
         
         if(br.hasErrors()){
             return new AjaxResult(br);
         }        
         // 启动流程
         ProcessParameter<ValNetPlanQuotation003Dto> proParam = new ProcessParameter<>();
         WorkFlowParameter<ValNetPlanQuotation003Dto> params = new WorkFlowParameter<>();                 
         
         // 业务代码（资产代码）
         params.setBusinessCode(dto.getFinprodId());
         // 业务类型
          params.setBusinessType(DictCodeList.共通业务类型_计划类资产净值信息.getValue());
         // 页面id
         params.setPageId(this.getPageId());
         // 元素id
         params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
         
         //设置流程业务说明, entity即传入的dto
         params.setComment(entity -> WorkFlowUtil.concatComment("计划类资产净值新建：",
             entity.getFinprodId(),//资产代码
             DateUtil.format(DateUtil.parse(entity.getValueDate()),"yyyy-MM-dd"),//估值日期
             entity.getFinprodType()//资产类型
             ));
         
         // 塞入流程参数
         proParam.setWorkFlowParameter(params);
         
         String msg = getMessage("COMMON_I_0001");
         try {
             msg = valNetPlanQuotation003Service.start(proParam, dto);
         }
         catch (Exception e) {
             getLogger().error("启动流程失败：", e);
             return new AjaxResult(e.getMessage());
         }

         return new AjaxResult(msg);
     }
     
     
 	/**
 	 * 新建
 	 * 
 	 * @param dto
 	 * @param bindingResult
 	 * @return
 	 */
/* 	@ResponseBody
 	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
 	public AjaxResult save(@RequestBody @Valid ValNetPlanQuotation003Dto dto, BindingResult bindingResult) {
 		String msg = StringUtil.EMPTY;
 		if (StringUtils.isEmpty(dto.getFinprodId())) {
 			dto.setFinprodId(IDGenerator.generate());
 		}

 		String operateType = dto.getOperateType();
 		//dto.setValueDate(dto.getValueDateFmt());
 		
        dto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
 		if ("submit".equals(operateType)) {
 			valNetPlanQuotation003Service.insert(dto);
 			msg = "新建成功！";
 		} else if ("save".equals(operateType)) {
 			valNetPlanQuotation003Service.updateByPrimaryKeySelective(dto);
 			msg = "修改成功！";
 		}		

 		return new AjaxResult(msg);
 	}*/
 	
 	
	/**
	 * 新增/修改验证：基础验证、唯一性
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/searchList")
	public List<ValNetPlanQuotation003Dto> searchList(@RequestBody Map<String, Object> map) {
			return valNetPlanQuotation003Service.searchList(map);
			
	}
	@PostMapping("/searchfinprodId")
	public List<ValNetPlanQuotation003Dto> searchfinprodId(@RequestBody String finprodId) {
		return valNetPlanQuotation003Service.searchfinprodId(finprodId);
	}
	  /**
	   * 计划类资产净值信息---修改--走流程
	   * <一句话功能简述>
	   * <功能详细描述>
	   * @param dto
	   * @return
	   */
		@Transactional
		@DTXTransaction
	    @ResponseBody
	    @RequestMapping(value = "doUpdate", method=RequestMethod.POST)
	    public AjaxResult doUpdate(@RequestBody @Valid ValNetPlanQuotation003Dto dto, BindingResult br) {
	         // 启动流程
	         ProcessParameter<ValNetPlanQuotation003Dto> proParam = new ProcessParameter<>();
	         WorkFlowParameter<ValNetPlanQuotation003Dto> params = new WorkFlowParameter<>();                 
	         
	         // 业务代码（资产代码）
	         params.setBusinessCode(dto.getFinprodId());
	         // 业务类型
	          params.setBusinessType(DictCodeList.共通业务类型_计划类资产净值信息.getValue());
	         // 页面id
	         params.setPageId(this.getPageId());
	         // 元素id
	         params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
	        //设置流程业务说明, entity即传入的dto
	         params.setComment(entity -> WorkFlowUtil.concatComment("计划类资产净值信息修改：",
	                 entity.getFinprodId(),//资产代码
	                 DateUtil.format(DateUtil.parse(entity.getValueDate()),"yyyy-MM-dd"),//估值日期
	                 entity.getFinprodType()//资产类型
	                 ));
	        
	        proParam.setWorkFlowParameter(params);
	        
	        String msg = getMessage("COMMON_I_0001");
	        try {
	            msg = valNetPlanQuotation003Service.start(proParam, dto);
	        }
	        catch (Exception e) {
	            getLogger().error("启动流程失败：", e);
	            return new AjaxResult(e.getMessage());
	        }
	        return new AjaxResult(msg);
	    }
		
		/**
		 * 删除
		 * @param dto
		 * @throws Exception
		 * @see
		 * @since 1.0
		 */
		@Transactional
		@DTXTransaction
		@PostMapping("/delete")
		public void delete(@RequestBody ValNetPlanQuotation003Dto dto) throws Exception{
		    valNetPlanQuotation003Service.doComplete(dto,OperationType.DELETE);
		}
}
