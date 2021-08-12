package com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.service.LimitManage003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import java.util.Date;


/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see CeLine002Controller
 * @since 1.0
 */
@Controller
@PageId("LimitManage003")
@RequestMapping("/invest/risk/creditrisklimitmanagement/limitmanage/LimitManage003")
public class LimitManage003Controller extends BaseController {
	 
	/**
     * limitManage003Service
     */
    @Autowired(required = false)
    private LimitManage003Service limitManage003Service;
    
    
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    @Override
    @RequestMapping(PAGE_INIT)    
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.机构类型_风险,DictCode.额度分类,
        		DictCode.期限种类,DictCode.是否);

        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        String workflowId = getParam("workflowId");
         // 待办跳转
         if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotEmpty(workflowId)) {
             model.addAttribute("workflowId", workflowId);
             model.addAttribute("isTodoList", getParam("isTodoList"));
             CreditItemInfoDto creditItemInfoDto = limitManage003Service.getBussinessEntityByWorkflowId(workflowId);
 /*            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             String timestamp = sdf.format(new Date());             
           try{
                creditItemInfoDto.setUpdateTime(sdf.parse(DateUtil.getSysDate()));//数据日期
            }catch(ParseException e){
            	creditItemInfoDto.setUpdateTime(new Date());
            }
*/
             model.addAttribute("entity", creditItemInfoDto);

         } else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(this.getParam("seqno"))){
             String seqno = this.getParam("seqno");
             CreditItemInfoDto creditItemInfoDto = limitManage003Service.getBussinessEntityByIndId(seqno);
	        // model.addAttribute("mdate", creditItemInfoDto.getMdate());

        /*     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             String timestamp = sdf.format(new Date());             
            try{
                creditItemInfoDto.setUpdateTime(sdf.parse(DateUtil.getSysDate()));//数据日期
            }catch(ParseException e){
            	creditItemInfoDto.setUpdateTime(new Date());
            }
            
            */
	         model.addAttribute(GlobalConstant.ENTITY, creditItemInfoDto);
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	 if("0".equals(this.getPageModel())){
    	         //model.addAttribute("updateTime",new Date());//数据日期 
        	 }
        	 if("1".equals(this.getPageModel())){
    	         model.addAttribute("index", getParam("index"));
    	         model.addAttribute("object", getParam("object"));
    	         
    	         model.addAttribute("creditTagId", getParam("creditTagId"));
    	         model.addAttribute("seqno", getParam("seqno"));
    	         model.addAttribute("orgType", getParam("orgType"));
    	         model.addAttribute("creditType", getParam("creditType"));
    	         model.addAttribute("creditAmt", getParam("creditAmt"));
    	         model.addAttribute("term", getParam("term"));
    	         model.addAttribute("termUnit", getParam("termUnit"));
    	         model.addAttribute("riskRatio", getParam("riskRatio"));
    	  	    
       	         String timestamp = sdf.format(creditItemInfoDto.getMdate()); 
       	         model.addAttribute("mdate",timestamp);
               

    	  	    
    	         model.addAttribute("upTime", getParam("upTime"));
    	         model.addAttribute("remark", getParam("remark"));  
    	         model.addAttribute("status", getParam("status"));     	         
        	 }

         }else{
 	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         String timestamp = sdf.format(new Date()); 
        	 if("0".equals(this.getPageModel())){
    	         //model.addAttribute("updateTime", sdf.format(new Date()));//数据日期 
        	 }
        	 if("1".equals(this.getPageModel())){
    	         model.addAttribute("index", getParam("index"));
    	         model.addAttribute("object", getParam("object"));
    	         
    	         model.addAttribute("creditTagId", getParam("creditTagId"));
    	         model.addAttribute("seqno", getParam("seqno"));
    	         model.addAttribute("orgType", getParam("orgType"));
    	         model.addAttribute("creditType", getParam("creditType"));
    	         model.addAttribute("creditAmt", getParam("creditAmt"));
    	         model.addAttribute("term", getParam("term"));
    	         model.addAttribute("termUnit", getParam("termUnit"));
    	         model.addAttribute("riskRatio", getParam("riskRatio"));
    	         model.addAttribute("mdate", getParam("mdate"));
    	         model.addAttribute("upTime", getParam("upTime"));
    	         model.addAttribute("remark", getParam("remark"));  
    	         model.addAttribute("status", getParam("status"));     	         
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
    	ValidateGroupEdit.class}) CreditItemInfoDto dto, BindingResult result ) {
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) CreditItemInfoDto dto, BindingResult br) throws Exception{
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getCreditTagId())){
    	   dto.setCreditTagId(IDGenerator.generate());
        }
   
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
        ProcessParameter<CreditItemInfoDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<CreditItemInfoDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getCreditTagId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_机构授信.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("CeLine002"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("CeLine002.labelName"), GlobalConstant.OPEN_PARENTHESIS,entity.getCreditTagName(),
                 GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = limitManage003Service.start(processParameter, dto);
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
        return limitManage003Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
  
    /**
     * 获取额度标签
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("getCreditItemInfoDtoList")
    @ResponseBody
    public Page<SelectOptionDto> getCreditItemInfoDtoList(@RequestParam Map<String, Object> params) {
        return limitManage003Service.getCreditItemInfoDtoList(params);
    }

}
