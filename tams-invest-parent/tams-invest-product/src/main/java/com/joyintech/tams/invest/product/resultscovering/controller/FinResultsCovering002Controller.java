package com.joyintech.tams.invest.product.resultscovering.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.resultscovering.dto.FinResultsCovering002Dto;
import com.joyintech.tams.invest.product.resultscovering.service.FinResultsCovering002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("FinResultsCovering002")
@RequestMapping("/invest/product/resultscovering/FinResultsCovering002")
public class FinResultsCovering002Controller extends BaseController{

	@Autowired
	private FinResultsCovering002Service finResultsCovering002Service;
	@Autowired
	private BaseGetBusinessId baseGetBusinessIdImpl;
	 @Autowired
	private IPlatformDate iPlatformDate;
	@Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.回补方式, DictCode.回补规则,DictCode.有效状态,DictCode.币种);
		model.addAttribute("ANAPLEROTIC_RULE_01", DictCodeListExt1.回补规则_仅使用固定管理费回补.getValue());
		model.addAttribute("ANAPLEROTIC_RULE_02", DictCodeListExt1.回补规则_仅使用浮动管理费回补.getValue());
		model.addAttribute("ANAPLEROTIC_RULE_03", DictCodeListExt1.回补规则_优先使用浮动管理费回补.getValue());
		model.addAttribute("YES_NO_YES", DictCodeList.是否_是.getValue());
		String prodId=getParam("prodId");
        String tradeId = getParam("tradeId");
        String workflowId = getParam("workflowId");
        String bookDate = getParam("coveringDate");
        if(!StringUtil.isNotEmpty(tradeId) && WorkFlowUtil.isNotFromToDoList()){
            //新建 初始化
      	    Date date = new Date();
		    //格式里的时如果用hh表示用12小时制，HH表示用24小时制。MM必须是大写!
      	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      	    if(bookDate != null){
      	    	date.setTime(Long.parseLong(bookDate));//java里面应该是按毫秒
      	    }
            FinResultsCovering002Dto finResultsCovering002Dto = new FinResultsCovering002Dto();
            //若分档产品，将分档的产品代码传入后台查询
            String layFinprodId = getParam("layFinprodId");
            if(StringUtil.isNotEmpty(prodId)){
            	if(StringUtil.isNotEmpty(layFinprodId)){
            	    prodId = layFinprodId;
            	}
            	List<FinResultsCovering002Dto> listDto =  finResultsCovering002Service.selectInfoCovering(prodId,sdf.format(date));
            	if(listDto != null && listDto.size()>0){
            	    model.addAttribute("entity", listDto.get(0));
            	}
            	else{
            	    finResultsCovering002Dto.setCoveringDate(iPlatformDate.getSysDate());
                    model.addAttribute("entity", finResultsCovering002Dto);
            	}
            }
            else{
            	finResultsCovering002Dto.setCoveringDate(iPlatformDate.getSysDate());
                model.addAttribute("entity", finResultsCovering002Dto);
            }	
        }else if(StringUtil.isNotEmpty(tradeId) && WorkFlowUtil.isNotFromToDoList()){
        	FinResultsCovering002Dto entity = finResultsCovering002Service.selectInfo(prodId,tradeId);
        	model.addAttribute("entity", entity);
        }else if(!WorkFlowUtil.isNotFromToDoList()){
        	//流程初始化
        	FinResultsCovering002Dto finResultsCovering002Dto = finResultsCovering002Service.getBussinessEntityByWorkflowId(workflowId);
            // 设置到model中
            model.addAttribute("entity", finResultsCovering002Dto);
        }
		return super.init(model);
	}

	/**
	 * 查询基本信息(根据产品代码)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectBaseInfo")
	public FinResultsCovering002Dto selectBaseInfo(){
	    return finResultsCovering002Service.selectBaseInfo(RequestUtil.getPageParam());
	}
	
	@ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinResultsCovering002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
	
	@ResponseBody
	@RequestMapping(OPERATE_TYPE_SAVE)
	public AjaxResult save(@RequestBody @Validated({
		ValidateGroupEdit.class}) FinResultsCovering002Dto dto, BindingResult br)
				throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		// 新建留痕表参数和工作流参数
		ProcessParameter<FinResultsCovering002Dto> processParameter = new ProcessParameter<FinResultsCovering002Dto>();
		WorkFlowParameter<FinResultsCovering002Dto> params = new WorkFlowParameter<FinResultsCovering002Dto>();
		params.setPageId("FinResultsCovering002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getProdId());
		BussinessCodeParameter parameter = new BussinessCodeParameter();
		parameter.setCommonBusinessExtType(DictCodeListExt1.fromDictCodeList(DictCodeListExt1.共通业务类型_回补交易));
		dto.setTradeId(baseGetBusinessIdImpl.buildBussinessCode(parameter));
		params.setBusinessCode((entity) -> entity.getTradeId());
		params.setBusinessType(DictCodeListExt1.共通业务类型_回补交易.getValue());
		// 设置流程业务说明, entity即传入的dto
		// "产品代码"+"("+产品代码+")"+"-""产品简称"+"("+产品简称+")"
		params.setComment(entity -> this.getMessage("FinResultsCovering001.prodId") + "("
				+ entity.getProdId() + ")"
				+ this.getMessage("FinResultsCovering002.tradeId")
				+ "(" +entity.getTradeId() + ")"
				+ this.getMessage("FinResultsCovering001.coveringDate") + "("
				+ DateUtil.format(entity.getCoveringDate()) + ")");
		// 塞入工作流参数
		processParameter.setWorkFlowParameter(params);
		// 流程开始
		String msg = finResultsCovering002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
	
	
}