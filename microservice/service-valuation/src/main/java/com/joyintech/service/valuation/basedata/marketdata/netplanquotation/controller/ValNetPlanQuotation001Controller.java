/*
 * 文件名：NetPlanQuotation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.netplanquotation.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation001Dto;
import com.joyintech.service.valuation.basedata.marketdata.netplanquotation.service.ValNetPlanQuotation001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 计划资产净值信息Controller 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年10月11日
 * @see ValNetPlanQuotation001Controller
 * @since 1.0
 */
//@RestController(value="serviceValNetPlanQuotation001Controller")
@RestController("valNetPlanQuotation001Ctl_dtx")
@RequestMapping("/ValNetPlanQuotation001")
public class ValNetPlanQuotation001Controller extends BaseController {

	@Autowired
	private ValNetPlanQuotation001Service valNetPlanQuotation001Service;

	@Override
	public String init(Model model) {
		// this.setSelectOptionToModel(model, DictCode.录入方式);
		return super.init(model);
	}

	/**
	 * 计划资产净值信息查询 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/ValNetPlanQuotation001Search/{page}/{length}")
	public Page<ValNetPlanQuotation001Dto> queryPageList(@RequestBody Map<String, Object> params,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
//		Map<String, Object> params = this.getRequestMap();
//		ValNetPlanQuotation001SearchDto dto = (ValNetPlanQuotation001SearchDto) JsonUtils.map2entity(params,
//				ValNetPlanQuotation001SearchDto.class);
//		 dto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
		// dto.setProfitType(DictCodeList.收益类型_净值型.getValue());
		// dto.setProfitType(DictCodeList.收益类型_货币型.getValue());
		return valNetPlanQuotation001Service.queryPageList(page,length,params);
	}

	/**
	 * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
	 * 
	 * @return paraMap
	 * @throws Exception
	 *             ParseException
	 * @see
	 * @since 1.0
	 */
	public Map<String, Object> getRequestMap() throws ParseException {
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		// 估值日期区间
		String valueDateBegin = this.getParam("valueDateBegin");
		String valueDateEnd = this.getParam("valueDateEnd");
		if (StringUtil.isNotEmpty(valueDateBegin)) {
			paraMap.put("valueDateBegin", DateUtil.parseDate(valueDateBegin, "yyyy-MM-dd"));
		} else {
			paraMap.remove("valueDateBegin");
		}
		if (StringUtil.isNotEmpty(valueDateEnd)) {
			paraMap.put("valueDateEnd", DateUtil.parseDate(valueDateEnd, "yyyy-MM-dd"));
		} else {
			paraMap.remove("valueDateEnd");
		}
		return paraMap;
	}

	/**
	 * 下拉框获取：资产
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	
	
	@GetMapping("getFinprodId")
	public List<SuggestDto> getFinprodId(String param) throws Exception {
//		String param = super.getRequest().getParameter("param");
		return valNetPlanQuotation001Service.getFinprodId(param);
	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/findPageout")
	public List<ValNetPlanQuotation001Dto> findPageout(@RequestBody Map<String, Object> params)
			throws Exception {
			return  valNetPlanQuotation001Service.findPageout(params);		
	}

	/**
	 * 
	 * 通用删除验证：根据主键判断
	 * 
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */	
	@PostMapping("/findFinproduct")
	public List<ValNetPlanQuotation001Dto> findFinproduct(@RequestBody String finproId) {
			return  valNetPlanQuotation001Service.findFinproduct(finproId); // 资产有交易			
	}
	
	/**
	 * 删除
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return
	 */
/*	@ResponseBody
	@RequestMapping(value = "deleteNetPlan", method = RequestMethod.POST)
	public AjaxResult deleteScheme(@RequestBody ValNetPlanQuotation001Dto dto) {
		String msg = StringUtils.EMPTY;
		String finprodId = dto.getFinprodId();
		String valueDate = dto.getValueDateFmt();
		int row = valNetPlanQuotation001Service.deleteByPrimaryKey(finprodId,valueDate);
		if (row > 0) {
			msg = "删除成功";
			return new AjaxResult(msg);
		}
		msg = "删除失败";
		return new AjaxResult(msg, MessageType.错误消息);
	}*/
//	@Transactional
//	@DTXTransaction
//	@ResponseBody
//	@RequestMapping(ACTION_DELETE)
//	public AjaxResult delete(@RequestBody @Valid ValNetPlanQuotation001Dto dto, BindingResult br) throws Exception{
//	    
//	    if(br.hasErrors()){
//	        return new AjaxResult(br);
//	    }
//	    
//	    // 启动流程
//	    ProcessParameter<ValNetPlanQuotation001Dto> proParam = new ProcessParameter<>();
//	    WorkFlowParameter<ValNetPlanQuotation001Dto> params = new WorkFlowParameter<>();
//	    
//	    // 业务代码（凭证流水号）
//	    params.setBusinessCode(dto.getFinprodId());
//	    // 业务类型
//	    params.setBusinessType(DictCodeList.估值核算业务类型_计划类资产净值信息.getValue());
//	    // 页面id
//	    params.setPageId("ValNetPlanQuotation001");
//	    // 元素id
//	    params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
//	    
//	    //设置流程业务说明, entity即传入的dto
//	    params.setComment(entity -> WorkFlowUtil.concatComment("计划类资产净值信息删除：",
//	        dto.getFinprodId(),//计划类资产净值信息的资产代码 
//	        dto.getFinprodAbbr(),//计划类资产净值信息的资产名称
//	        dto.getValueDate()//估值日期
//	        ));
//	    
//	    // 塞入流程参数
//	    proParam.setWorkFlowParameter(params);
//	    
//	    String msg = getMessage("COMMON_I_0001");
//	    try {
//	        msg = valNetPlanQuotation001Service.start(proParam, dto);
//	    }
//	    catch (Exception e) {
//	        getLogger().error("启动流程失败：", e);
//	        return new AjaxResult(e.getMessage());
//	    }
//	
//	    return new AjaxResult(msg);
//	}
	
	
		@Transactional
	    @DTXTransaction
	    @PostMapping("delete")
	    public int delete(@RequestBody ValNetPlanQuotation001Dto entity){
	         return valNetPlanQuotation001Service.doComplete(entity);
	    }
}
