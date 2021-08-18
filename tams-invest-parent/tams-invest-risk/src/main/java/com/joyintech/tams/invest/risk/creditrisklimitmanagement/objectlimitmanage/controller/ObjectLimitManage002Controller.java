package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.service.ObjectLimitManageService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2020年03月08日
 * @see ObjectLimitManage002Controller
 * @since 1.0
 */
@Controller
@PageId("ObjectLimitManage002")
@RequestMapping("/invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002")
public class ObjectLimitManage002Controller extends BaseController {
	 
	/**
     * objectLimitManageService
     */
    @Autowired(required = false)
    private ObjectLimitManageService objectLimitManageService;
    
    
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    @Override
    @RequestMapping(PAGE_INIT)    
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.资产五级分类,DictCode.期限种类,DictCode.是否);

        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        String workflowId = getParam("workflowId");
         // 待办跳转
         if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotEmpty(workflowId)) {
             model.addAttribute("workflowId", workflowId);
             model.addAttribute("isTodoList", getParam("isTodoList"));
             ObjectLimitManageInfoDto objectLimitManageInfoDto = objectLimitManageService.getBussinessEntityByWorkflowId(workflowId);

             model.addAttribute("entity", objectLimitManageInfoDto);

         } else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(this.getParam("seqno"))){
             String seqno = this.getParam("seqno");
             ObjectLimitManageInfoDto objectLimitManageInfoDto = objectLimitManageService.getBussinessEntityByIndId(seqno);

             model.addAttribute(GlobalConstant.ENTITY, objectLimitManageInfoDto);
         }
         if("01".equals(CacheUtil.getSysParam("IS_NB_CREDIT"))){
             model.addAttribute("isNbCredit", CacheUtil.getSysParam("IS_NB_CREDIT"));
         }
        return super.init(model);
    }

   
	/**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
    	ValidateGroupEdit.class}) ObjectLimitManageInfoDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ObjectLimitManageInfoDto dto, BindingResult br) throws Exception{
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
 
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
       	 if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
                dto.setSeqno(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
            else {
                dto.setSeqno(IDGenerator.generate());
            }
       }
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
        ProcessParameter<ObjectLimitManageInfoDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ObjectLimitManageInfoDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getCreditTagId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_机构授信.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("ObjectLimitManage001"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("ObjectLimitManage001"), GlobalConstant.OPEN_PARENTHESIS,entity.getCustomerId(),
                 GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = objectLimitManageService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return objectLimitManageService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
  
    /**
     * 获取额度标签
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("getCreditTagInfoList")
    @ResponseBody
    public Page<SelectOptionDto> getCreditTagInfoList(@RequestParam Map<String, Object> params) {
        return objectLimitManageService.getCreditTagInfoList(params);
    }

}
