package com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.dto.ModAseetMappingDto;
import com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.dto.ModStockDivitrateConfigInfoDto;
import com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.service.ModStockConfigInfoService;
import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 预期股息增长率配置查询
 * 
 * @author zhangfei
 * @version 1.0 2021年7月1日10:36:24
 * @see ModStockDivitrateConfig002Controller
 * @since 1.0
 */
@Controller
@PageId("ModStockDivitrateConfig002")
@RequestMapping("/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002")
public class ModStockDivitrateConfig002Controller extends BaseController{
	
	
	@Autowired 
	private ModStockConfigInfoService modStockConfigInfoService;
	
	/**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
	/**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String seqno = getParam("seqno");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
        	ModStockDivitrateConfigInfoDto dto = modStockConfigInfoService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (StringUtils.isNotEmpty(seqno)) {
        	ModStockDivitrateConfigInfoDto dto = modStockConfigInfoService.findByPrimaryKey(RequestUtil.getPageParam());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        this.setSelectOptionToModel(model, DictCode.资产类型);
        this.setSelectOptionToModel(model, DictCode.资产分类);
        this.setSelectOptionToModel(model, DictCode.证券品种);
        return super.init(model);
    }
	
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto    dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ModStockDivitrateConfigInfoDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
		/*
		 * ArrayList<ValidMessage> errorList1 = new ArrayList<>();
		 * if(dto.getStockIntarte().compareTo(new BigDecimal(100))>0){
		 * errorList1.add(new ValidMessage("增长率值不能大于100")); AjaxResult ar = new
		 * AjaxResult(); ar.setErrorList(errorList1); return ar; }
		 */
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("ModStockDivitrateConfig002");
        wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
        wflValidInputParameter.setBusinessDataPk2(dto.getSecTypeModel());
        boolean is = WorkFlowUtil.isNotFromToDoList();
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if (WorkFlowUtil.isNotFromToDoList()) {
               if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                   if (StringUtil.isNotEmpty(dto.getFinprodId())) {
                       Map<String, Object> map = RequestUtil.getPageParam();
                       map.put("finprodId", dto.getFinprodId());
                       //新建时,资产代码是否已维护的情况下，报错
                       int sfsy = modStockConfigInfoService.checkByFinprodId(map);
                       if (sfsy>0) {
                           errorList.add(new ValidMessage("系统已维护预期股息增长率的资产。"));
                       }
                   }
               }else if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
					/*
					 * Map<String, Object> map = RequestUtil.getPageParam();
					 * map.put("seqno",dto.getSeqno()); int exists =
					 * pdFactorConfig001Service.checkBySeqNo(map); //配置因子只要被PD生成批处理调用过 if(exists>0)
					 * { ajaxResult.setData(this.getMessage("本次修改是否影响历史数据？",
					 * this.getMessage("SysCommon.save"))); return ajaxResult; }
					 */
               }
        }
        else{
        	if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
        		if (StringUtil.isNotEmpty(dto.getFinprodId())) {
                    Map<String, Object> map = RequestUtil.getPageParam();
                    map.put("finprodId", dto.getFinprodId());
                    //新建时,资产代码是否已维护的情况下，报错
                    int sfsy = modStockConfigInfoService.checkByFinprodId(map);
                    if (sfsy>0) {
                        errorList.add(new ValidMessage("系统已维护预期股息增长率的资产。"));
                    }
                }
            }else if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
				/*
				 * Map<String, Object> map = RequestUtil.getPageParam();
				 * map.put("seqno",dto.getSeqno()); int exists =
				 * pdFactorConfig001Service.checkBySeqNo(map); //配置因子只要被PD生成批处理调用过 if(exists>0)
				 * { ajaxResult.setData(this.getMessage("本次修改是否影响历史数据？",
				 * this.getMessage("SysCommon.save"))); return ajaxResult; }
				 */
           }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 资产代码的联想文本框事件
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetCodeListExceptExsits")
    public Page<SelectOptionDto> getAssetCodeList()
        throws Exception { 
    	Map<String,Object> param = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = null;
        if(param.get("initValue")!=null) {//编辑时
            temp = modStockConfigInfoService.getAssetCodeList(param);
        }else {//新建页面时过滤以维护资产代码
            temp = modStockConfigInfoService.getAssetCodeListExceptExsits(param);
        }
        return temp;
    }
	
    
    /**
     * 
     * 保存提交工作流
     * <功能详细描述>
     * @param dto 保存参数
     * @param br 保存参数
     * @return 保存返回信息
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) ModStockDivitrateConfigInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getSeqno())){
            dto.setSeqno(IDGenerator.generate());
        }
        String businessCode = dto.getFinprodId()+"_"+dto.getSecTypeModel();
        ProcessParameter<ModStockDivitrateConfigInfoDto> processParameter = new ProcessParameter<ModStockDivitrateConfigInfoDto>();
        WorkFlowParameter<ModStockDivitrateConfigInfoDto> params = new WorkFlowParameter<ModStockDivitrateConfigInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_预期股息增长率配置.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "资产代码" + "-" + entity.getFinprodId()+ "资产类型" + "-"+entity.getSecTypeModel() );
        processParameter.setWorkFlowParameter(params);
        String msg = modStockConfigInfoService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findModAseetMapping")
    public ModAseetMappingDto selectAll()throws Exception {
    	Map<String,Object> param = RequestUtil.getPageParam();
        return modStockConfigInfoService.findModAseetMappingByOutput(param);
    }

}
