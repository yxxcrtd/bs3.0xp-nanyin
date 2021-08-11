package com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
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
 * @see ModStockDivitrateConfig001Controller
 * @since 1.0
 */
@Controller
@PageId("ModStockDivitrateConfig001")
@RequestMapping("/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001")
public class ModStockDivitrateConfig001Controller extends BaseController{
	
	
	@Autowired 
	private ModStockConfigInfoService modStockConfigInfoService;
	
	 /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
	/**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        //this.setSelectOptionToModel(model, DictCode.季度);
        return super.init(model);
    }
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ModStockDivitrateConfigInfoDto> selectAll()
        throws Exception {
        Page<ModStockDivitrateConfigInfoDto> page = modStockConfigInfoService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    
	/**
     * 资产代码的联想文本框事件
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetCodeList")
    public Page<SelectOptionDto> getAssetCodeList()
        throws Exception { 
    	Map<String,Object> param = RequestUtil.getPageParam();
    	Page<SelectOptionDto> temp = modStockConfigInfoService.getAssetCodeList(param);
    	/*if(param.get("initValue")!=null) {//编辑时
    		temp = modStockConfigInfoService.getAssetCodeList(param);
    	}else {//新建页面时过滤以维护资产代码
    		temp = modStockConfigInfoService.getAssetCodeListExceptExsits(param);
    	}*/
        return temp;
    }
	
    
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ModStockDivitrateConfigInfoDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("ModStockDivitrateConfig002");
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
//        Date sysDate = DateUtil.platDateFromCache();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if(StringUtil.isNotEmpty(dto) && !StringUtil.isEmptyList(dto.getSelectedItem())){
                    dto.getSelectedItem().forEach(item->{
                        if (StringUtil.isNotEmpty(item.getFinprodId()) && StringUtil.isNotEmpty(item.getSecTypeModel())){
							wflValidInputParameter.setBusinessDataPk1(item.getFinprodId());
							wflValidInputParameter.setBusinessDataPk2(item.getSecTypeModel());
                            if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                                errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"+" 资产代码: "+item.getFinprodId()+" 资产类型: "+item.getSecTypeModel()));
                            }
                        }
                    });
                }
            }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }else if(ajaxResult.getData()!=null) {
        	return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    
    /**
     * 删除操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) ModStockDivitrateConfigInfoDto dto, BindingResult br)
            throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ModStockDivitrateConfigInfoDto> processParameter = new ProcessParameter<ModStockDivitrateConfigInfoDto>();
        WorkFlowParameter<ModStockDivitrateConfigInfoDto> params = new WorkFlowParameter<ModStockDivitrateConfigInfoDto>();
        params.setPageId("ModStockDivitrateConfig002");
        params.setElementId(dto.getElementId());
        //业务编号
        params.setBusinessCode(item -> item.getFinprodId()+"_"+item.getSecTypeModel());
        params.setBusinessType(DictCodeListExt3.共通业务类型_预期股息增长率配置.getValue());
        // 设置流程业务说明:资产代码 + "(" +资产代码+ ")" + "-"  + "资产类型" + "-"  + "资产类型"
        params.setComment(
                entity -> "资产代码" + "(" + entity.getFinprodId() + ")"
                        + "资产类型" + "(" + entity.getSecTypeModel() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = modStockConfigInfoService.start(
                processParameter, dto.getSelectedItem().toArray(new ModStockDivitrateConfigInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    

}
