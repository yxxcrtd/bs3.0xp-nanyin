package com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysWflBusinessList;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.service.ValTrdAccountmfConfig002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 
 * 预提费用配置
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001
 * @since 1.0
 */
@Controller
@PageId("ValTrdAccountmfConfig002")
@RequestMapping("/valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002")
public class ValTrdAccountmfConfig002Controller extends BaseController {

    /**
     *预提费用配置service
     */
    @Autowired
    private ValTrdAccountmfConfig002Service valTrdAccountmfConfig002Service;

    @Override
    public String init(Model model) {
		String seqNo = getParam("seqNo");
		String operateType = getParam("operateType");
		String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
		ValTrdAccountmfConfig001Dto dto = new ValTrdAccountmfConfig001Dto();
		
		if (StringUtils.isNotEmpty(seqNo)) { // 修改
			Map<String,Object> params = new HashMap<>();
			params.put("seqNo", seqNo);
			dto = valTrdAccountmfConfig002Service.findBySeqNo(params);
		}else if(StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
		    dto = valTrdAccountmfConfig002Service.getBussinessEntityByWorkflowId(workflowId);
		    
		    seqNo = dto.getSeqNo(); // 避免再生成主键
		}

		if (StringUtils.isEmpty(seqNo)) {
			dto.setSeqNo(IDGenerator.generate());// 新建自动生成 主键
		}
		dto.setOperateType(operateType);
		model.addAttribute("entity", dto);

		super.setSelectOptionToModel(model, DictCode.一次性费用类型);
    	super.setSelectOptionToModel(model, DictCode.登记托管机构);
    	super.setSelectOptionToModel(model, DictCode.频率);

		return super.init(model);
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId()
        throws Exception {
        return valTrdAccountmfConfig002Service.getBooksetId(RequestUtil.getPageParam());
    }
    
    /**
     * 计提开始日检查
     * 
     * @param positionStartDate 计提开始日
     * @return 返回结果小于0时锁定计提开始日字段
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("dateCheck")
    public int dateCheck(String positionStartDate,String booksetId)
        throws Exception {
    	// 修改页面进入判断
    	if(StringUtil.isNotEmpty(positionStartDate)){
    		// 获取账套当前日期
        	Map<String,Object> params = new HashMap<>();
        	params.put("booksetId", booksetId);
        	BokBooksetDate bokBooksetDate = valTrdAccountmfConfig002Service.getBooksetDate(params);
        	// 选择记录.计提结束日 < 账套日期表.当前日期
            return DateUtil.compareDate(DateUtil.parse(positionStartDate),bokBooksetDate.getBooksetDate());
    	}
    	// 新建页面不做控制，直接返回1
        return 1;
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValTrdAccountmfConfig001Dto dto, 
    		BindingResult bindingResult) throws Exception {
    	String msg = StringUtil.EMPTY;
    	String operateType = dto.getOperateType();
        if (!bindingResult.hasErrors()) {// 基础验证通过
        	// 获取账套当前日期
        	Map<String,Object> params = new HashMap<>();
        	params.put("booksetId", dto.getBooksetId());
        	// 选择记录.计提开始日 > 选择记录.计提结束日
        	if(DateUtil.compareDate(DateUtil.parse(dto.getPositionStartDate()),DateUtil.parse(dto.getPositionEndDate()))>0){
                return new AjaxResult("计提开始日应小于等于计提结束日！", MessageType.错误消息);
        	}
        	BokBooksetDate bokBooksetDate = valTrdAccountmfConfig002Service.getBooksetDate(params);
        	if("edit".equals(operateType)){
        		// 选择记录.计提结束日 < 账套日期表.当前日期
            	if(DateUtil.compareDate(DateUtil.parse(dto.getPositionEndDate()),bokBooksetDate.getBooksetDate())<0){
                    return new AjaxResult("计提结束日小于账套日期，请切换账套日期，并在结束后重新登账！", MessageType.错误消息);
            	}
            	Map<String,Object> paramedit = new HashMap<>();
            	paramedit.put("seqNo", dto.getSeqNo());
            	ValTrdAccountmfConfig001Dto editDto = valTrdAccountmfConfig002Service.findBySeqNo(paramedit);
            	// 选择记录.计提开始日 < 账套日期表.当前日期
            	if(DateUtil.compareDate(DateUtil.parse(editDto.getPositionStartDate()),bokBooksetDate.getBooksetDate())>=0 
            			&& DateUtil.compareDate(DateUtil.parse(dto.getPositionStartDate()),bokBooksetDate.getBooksetDate())<0){
            		return new AjaxResult("计提开始日小于账套日期，请切换账套日期，并在结束后重新登账！", MessageType.错误消息);
            	}
        	}else {
        		// 选择记录.计提开始日 < 账套日期表.当前日期
            	if(DateUtil.compareDate(DateUtil.parse(dto.getPositionStartDate()),bokBooksetDate.getBooksetDate())<0){
                    return new AjaxResult("计提开始日小于账套日期，请切换账套日期，并在结束后重新登账！", MessageType.错误消息);
            	}
        	}
        	// 数据重复性校验
        	List<ValTrdAccountmfConfig001Dto> listdto = valTrdAccountmfConfig002Service.duplicateCheck(dto);
        	if(listdto != null && !listdto.isEmpty()){
        		msg = msg.concat("该账套已存在计提区间：");
        		for(ValTrdAccountmfConfig001Dto item : listdto){
        			msg = msg.concat(System.getProperty("line.separator")+DateUtil.getYear(item.getPositionStartDate())+"年"+DateUtil.getMonth(item.getPositionStartDate())+"月"+
        				DateUtil.getDayOfMonth(item.getPositionStartDate())+"日~ "+DateUtil.getYear(item.getPositionEndDate())+"年"+
        				DateUtil.getMonth(item.getPositionEndDate())+"月"+DateUtil.getDayOfMonth(item.getPositionEndDate())+"日，");
        		}
        		msg = msg.substring(0, msg.length()-1);
        		msg = msg.concat("；存在重复预提配置，请修改！");
        		return new AjaxResult(msg, MessageType.错误消息);
        	}
        	// 数据重复性校验——流程中
        	List<SysWflBusinessList> workflowIdList = valTrdAccountmfConfig002Service.getworkflowId(this.getPageId());
        	if(workflowIdList != null && !workflowIdList.isEmpty()){
        		msg = msg.concat("该账套流程中存在计提区间：");
        		List<ValTrdAccountmfConfig001Dto> duplicateWorkflowIdList = new ArrayList<>();
        		for(SysWflBusinessList item : workflowIdList){
        			ValTrdAccountmfConfig001Dto workflowDto = valTrdAccountmfConfig002Service.getBussinessEntityByWorkflowId(item.getSeqNo());
        			if(dto.getBooksetId().equals(workflowDto.getBooksetId()) 
        					&& dto.getFeeType().equals(workflowDto.getFeeType())
        					&& dto.getRegistOrg().equals(workflowDto.getRegistOrg()) 
        					&& DateUtil.compareDate(dto.getPositionStartDate(), workflowDto.getPositionEndDate())<=0 
        					&& DateUtil.compareDate(dto.getPositionEndDate(), workflowDto.getPositionStartDate())>=0){
        				msg = msg.concat(DateUtil.getYear(workflowDto.getPositionStartDate())+"年"+DateUtil.getMonth(workflowDto.getPositionStartDate())+"月"+
                				DateUtil.getDayOfMonth(workflowDto.getPositionStartDate())+"日~ "+DateUtil.getYear(workflowDto.getPositionEndDate())+"年"+
                				DateUtil.getMonth(workflowDto.getPositionEndDate())+"月"+DateUtil.getDayOfMonth(workflowDto.getPositionEndDate())+"日，");
        				duplicateWorkflowIdList.add(workflowDto);
        			}
        		}
        		if(!duplicateWorkflowIdList.isEmpty()){
        			msg = msg.substring(0, msg.length()-1);
        			msg = msg.concat("；存在重复预提配置，请修改！");
            		return new AjaxResult(msg, MessageType.错误消息);
        		}
        	}
        	BokBookset bokBookset = valTrdAccountmfConfig002Service.getBooksetEndDate(params);
        	// 选择记录.计提结束日 > 账套结束日
        	if(DateUtil.compareDate(DateUtil.parse(dto.getPositionEndDate()),bokBookset.getEndDate())>0){
                return new AjaxResult("计提结束日大于账套结束日，请再次确认！", MessageType.确认消息);
        	}
//        	return new AjaxResult("是否确定提交？", MessageType.确认消息);
        }
        return commonCheck(bindingResult, operateType);
    }


    /**
     * 新建修改，并启动流程
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
	@ResponseBody
	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ValTrdAccountmfConfig001Dto dto, BindingResult bindingResult)
            throws Exception {
		String msg = StringUtil.EMPTY;

		ProcessParameter<ValTrdAccountmfConfig001Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ValTrdAccountmfConfig001Dto> workFlowParameter = new WorkFlowParameter<>();
		
		try {
		    workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSeqNo());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_预提费用配置.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
            		entity.getSeqNo(), "预提费用配置"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            valTrdAccountmfConfig002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
		} catch (Exception e) {
			this.getLogger().error("启动预提费用配置流程失败：", e);
            msg = e.getMessage();
		}
		
		return new AjaxResult(msg);
    }

}
