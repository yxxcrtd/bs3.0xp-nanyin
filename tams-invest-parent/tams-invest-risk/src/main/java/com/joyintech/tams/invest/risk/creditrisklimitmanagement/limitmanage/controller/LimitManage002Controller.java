package com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.ParseException;

import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditmanagement.creditlinemanage.controller.CeLine002Controller;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfo;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.service.LimitManage002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see CeLine002Controller
 * @since 1.0
 */
@Controller
@PageId("LimitManage002")
@RequestMapping("/invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002")
public class LimitManage002Controller extends BaseController {
	 
	/**
     * limitManage002Service
     */
    @Autowired(required = false)
    private LimitManage002Service limitManage002Service;
    
    
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    @Override
    @RequestMapping(PAGE_INIT)    
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.扣减规则,DictCode.额度分类,
        		DictCode.发行方式,DictCode.期限种类,DictCode.货币对,DictCode.是否);

        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        String workflowId = getParam("workflowId");
         // 待办跳转d
         if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotEmpty(workflowId)) {
             model.addAttribute("workflowId", workflowId);
             model.addAttribute("isTodoList", getParam("isTodoList"));
             LimitManageInfo limitManageInfo = limitManage002Service.getBussinessEntityByWorkflowId(workflowId);

             model.addAttribute("entity", limitManageInfo);

         } else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(this.getParam("customerId"))){
             Map<String,Object> parmMap=new HashMap<String,Object>();
        	 String pflsh = getParam("pflsh");
             String mdate = getParam("mdate");  

             String customerId = getParam("customerId");
             String ccy = getParam("ccy");
             parmMap.put("pflsh", pflsh);
             parmMap.put("customerId", customerId);
             parmMap.put("ccy", ccy);
            // LimitManageInfo limitManageInfo = limitManage002Service.getBussinessEntityByIndId(seqno);
             LimitManageInfo limitManageInfo = limitManage002Service.getBussinessEntityByMap(parmMap);
             if(StringUtil.isNotEmpty(mdate)){
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 try{
    	         String timestamp = sdf.format(sdf.parse(mdate)); 
                 parmMap.put("mdate",timestamp);
                 model.addAttribute("mdate", timestamp);
                 }catch(ParseException e){
                	 e.printStackTrace();
                 }
             }
             model.addAttribute("pflsh", pflsh);

             model.addAttribute(GlobalConstant.ENTITY, limitManageInfo);
         }else{
        	 if("0".equals(this.getPageModel())){
                 String pflsh = this.getParam("pflsh");
                 String mdate = this.getParam("mdate");
                 model.addAttribute("pflsh", pflsh);
                 model.addAttribute("mdate", mdate);

        	 }
        	 if("1".equals(this.getPageModel())){
        		 
        	 }
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
    	ValidateGroupEdit.class}) LimitManageInfo dto, BindingResult result ) {
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) LimitManageInfo dto, BindingResult br) throws Exception{
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
     /*   if(StringUtil.isEmpty(dto.getCreditTagId())){
    	   dto.setCreditTagId(IDGenerator.generate());
        }*/
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
       if(!StringUtil.isNotEmpty(dto.getPflsh())){
           BussinessCodeParameter codeParameter = new BussinessCodeParameter();
           codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_额度管理);
           String pfh=baseGetBusinessId.buildBussinessCode(codeParameter);
           dto.setPflsh(pfh);
        }

        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
        ProcessParameter<LimitManageInfo> processParameter = new ProcessParameter<>();
        WorkFlowParameter<LimitManageInfo> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getSeqno();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_机构授信.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("LimitManage001"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("LimitManage001.creditAmt"), GlobalConstant.OPEN_PARENTHESIS,entity.getCreditAmt().toString(),
                 GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = limitManage002Service.start(processParameter, dto);
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
        return limitManage002Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
  
  /*  *//**
     * 获取额度标签
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     *//*
    @RequestMapping("getLimitManageInfoList")
    @ResponseBody
    public Page<SelectOptionDto> getLimitManageInfoList(@RequestParam Map<String, Object> params) {
        return limitManage002Service.getLimitManageInfoList(params);
    }*/
	@ResponseBody
    @RequestMapping("getCreditConfig")
    public Page<CreditItemInfoDto> getCreditConfig()
        throws Exception {
        Page<CreditItemInfoDto> page = limitManage002Service.getCreditConfig(RequestUtil.getPageParam());
        return page;
    }
}
