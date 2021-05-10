/*
 * 文件名：ValAssetBookParameter002Controller.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.service.ValAssetBookParameter002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("ValAssetBookParameter002")
@RequestMapping("/valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002")
public class ValAssetBookParameter002Controller extends BaseController {

    @Autowired
    private ValAssetBookParameter002Service valAssetBookParameter002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.账套参数类型);
        String booksetId = this.getParam("booksetId");
        String paramCode = this.getParam("paramCode");
        String paramType = this.getParam("paramType");
        
        ValAssetBookParameter002Dto dto = new ValAssetBookParameter002Dto();
        
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);

        if (StringUtil.isNotEmpty(booksetId)) {
        	Map<String,Object> params = new HashMap<String,Object>();
            params.put("booksetId",booksetId);
            params.put("paramCode",paramCode);
            params.put("paramType",paramType);
        	dto = valAssetBookParameter002Service.getByKey(params);
            
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = valAssetBookParameter002Service.getBussinessEntityByWorkflowId(workflowId);
        }

        model.addAttribute(GlobalConstant.ENTITY, dto);

        return super.init(model);
    }
    
    
	/**
	 * 下拉框 ： 参数代码
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findParam")
	public Page<SelectOptionDto> findParam() throws Exception {
		return valAssetBookParameter002Service.findParam(RequestUtil.getPageParam());
	}
	
	/**
	 * 通过参数名，获取参数值
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getParamValue")
	public ValAssetBookParameter002Dto getParamValue() throws Exception {
		return valAssetBookParameter002Service.getParamValue(RequestUtil.getPageParam());
	}

    /**
     * 新建修改
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
    public AjaxResult doUpdate(ValAssetBookParameter002Dto dto) {
        ProcessParameter<ValAssetBookParameter002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetBookParameter002Dto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto.getBooksetId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_账套参数管理.getValue());
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        String comment = "";
        if (StringUtil.equals(dto.getPageModel(),"0")){
            comment = "账套参数新建";
        }else if (StringUtil.equals(dto.getPageModel(),"1")){
            comment = "账套参数修改";
        }
        // 设置流程业务说明, entity即传入的dto
        String finalComment = comment;
        params.setComment(entity -> WorkFlowUtil.concatComment(finalComment,
            entity.getBooksetId()// 账套代码
        ));

        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetBookParameter002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        return new AjaxResult(msg);
    }

    /**
     * 保存提交check
     *
     * @param ValAssetBookParameter002Dto dto
     * @param BindingResult result
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check( @Valid ValAssetBookParameter002Dto dto, BindingResult result,
                            String operateType) {
        /**
         * 判断数据库中是否存在主键相同的数据，主键：账套id（booksetId），参数id（paramCode）
         */
        List<ValAssetBookParameter002Dto> resultDtos = null;
        if (StringUtil.isNotEmpty(dto.getBooksetId()) && StringUtil.isNotEmpty(dto.getParamCode())){
            resultDtos = valAssetBookParameter002Service.checkExistSameDto(dto);
        }
        if (resultDtos != null && resultDtos.size()>=2){
            return commonCheck(result, operateType);
        }
        // 新建的时候进行判断，编辑的时候不用判断  0:新建，1：编辑
        if ("0".equals(dto.getPageModel())){
            if (resultDtos != null && resultDtos.size()>0){
                AjaxResult ajaxResult = new AjaxResult();
                List<ValidMessage> errorList = new ArrayList<>();
                errorList.add(new ValidMessage("存在相同的账套："+dto.getBooksetId()+",参数："+dto.getParamCode()+"！请重新选择数据！"));
                ajaxResult.setErrorList(errorList);
                return ajaxResult;
            }
        }
        return commonCheck(result, operateType);
    }

}
