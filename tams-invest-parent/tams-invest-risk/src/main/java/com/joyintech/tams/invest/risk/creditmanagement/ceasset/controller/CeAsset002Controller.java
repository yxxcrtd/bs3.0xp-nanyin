package com.joyintech.tams.invest.risk.creditmanagement.ceasset.controller;

import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
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
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.service.CeAsset002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 资产授权
 * @author 裴家俊
 * @version 1.0 2019年10月23日
 * @see CeAsset002Controller
 * @since 1.0
 */
@Controller
@PageId("CeAsset002")
@RequestMapping("/invest/risk/creditmanagement/ceasset/CeAsset002")
public class CeAsset002Controller extends BaseController {
	
	/**
     * CeAsset002Service
     */
	 @Autowired(required = false)
	 private CeAsset002Service ceAsset002Service;
	 
	 @Autowired(required = false)
	 private BaseGetBusinessId baseGetBusinessId;
	    
	 @Autowired 
	 private FileAttachmentProcess fileAttachmentProcess;
    /**
     * 初始化的查询条件
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.指标类型,
        DictCode.是否
       };

    @Override
    public String init(Model model) {
    	model.addAttribute("cemodel", CacheUtil.getSysParam(BussinessConstant.CEMODEL));
        setSelectOptionToModel(model, DICT_CODES);
        String workflowId = getParam("workflowId");
        // 待办跳转
        if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotEmpty(workflowId)) {
            model.addAttribute("workflowId", workflowId);
            CreditLinesAstDto creditLinesDto = ceAsset002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", creditLinesDto);
        } else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(this.getParam("seqno"))){
            String seqno = this.getParam("seqno");
            CreditLinesAstDto creditLinesDto = ceAsset002Service.getBussinessEntityByIndId(seqno);
            model.addAttribute(GlobalConstant.ENTITY, creditLinesDto);
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
        ValidateGroupEdit.class}) CreditLinesAstDto dto, BindingResult result ) {
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
    public AjaxResult save(@RequestBody CreditLinesAstDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
       	 if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_资产授信);
                dto.setSeqno(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
            else {
                dto.setSeqno(IDGenerator.generate());
            }
       }
       //文件信息
       if (CollectionUtils.isNotEmpty(dto.getUploadFileList())
           && StringUtil.isNotEmpty(dto.getSeqno())) {
           // 将附件和当前业务对象绑定（更新附件的业务代码）
           fileAttachmentProcess.updateBussinessNo(
               dto.getUploadFileList().stream().map(UploadFile::getFileNo).collect(
                   Collectors.toList()),
               dto.getSeqno());
       }
       BussinessCodeParameter parameter = new BussinessCodeParameter();
       parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产授信);
       ProcessParameter<CreditLinesAstDto> processParameter = new ProcessParameter<>();
       WorkFlowParameter<CreditLinesAstDto> params = new WorkFlowParameter<>();
       params.setPageId(this.getPageId());
       params.setElementId(dto.getElementId());
       // 授权代码作为业务编号
       String businessCode = dto.getSeqno();
       params.setBusinessCode(businessCode);
       params.setBusinessType(DictCodeList.共通业务类型_资产授信.getValue());

       // 设置流程业务说明, entity即传入的dto
       if("01".equals(CacheUtil.getSysParam(BussinessConstant.CEMODEL))){
	       params.setComment(entity -> {
	           return WorkFlowUtil.concatComment(getMessage("CeAsset002.seqno"),
	               GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
	               getMessage("CeAsset002.customerId"), GlobalConstant.OPEN_PARENTHESIS,
	               entity.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS);
	       });
       }
       else{
    	   params.setComment(entity -> {
	           return WorkFlowUtil.concatComment(getMessage("CeAsset002.seqno"),
	               GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
	               getMessage("CeAsset002.pflshName"), GlobalConstant.OPEN_PARENTHESIS,
	               entity.getPflshName(), GlobalConstant.CLOSE_PARENTHESIS);
	       });
       }
       processParameter.setWorkFlowParameter(params);
       String msg = ceAsset002Service.start(processParameter, dto);
       return new AjaxResult(msg);
    }
}
