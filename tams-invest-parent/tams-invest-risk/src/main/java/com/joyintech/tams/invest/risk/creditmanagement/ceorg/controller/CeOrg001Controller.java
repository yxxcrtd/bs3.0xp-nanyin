package com.joyintech.tams.invest.risk.creditmanagement.ceorg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.service.CeOrg001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 监控指标
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeOrg001Controller
 * @since 1.0
 */
@Controller
@PageId("CeOrg001")
@RequestMapping("/invest/risk/creditmanagement/ceorg/CeOrg001")
public class CeOrg001Controller extends BaseController {
	 
	/**
     * CeOrg001Service
     */
    @Autowired(required = false)
    private CeOrg001Service ceOrg001Service;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
  
    @ResponseBody
    @RequestMapping("/getInstitutionName")
    public Page<SelectOptionDto> getInstitutionName()
        throws Exception {
    	Map<String, Object> map = RequestUtil.getPageParam();
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        Page<SelectOptionDto> temp = ceOrg001Service.getCustomerId(map);
      
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CreditLinesOrgDto> selectAll()
        throws Exception {
        return ceOrg001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
   
	
	/**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) CreditLinesOrgDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
	 /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) CreditLinesOrgDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<CreditLinesOrgDto> processParameter = new ProcessParameter<CreditLinesOrgDto>();
        WorkFlowParameter<CreditLinesOrgDto> params = new WorkFlowParameter<CreditLinesOrgDto>();
        params.setPageId("CeOrg002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSeqno());
        params.setBusinessType(DictCodeList.共通业务类型_机构授信.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
        	   return WorkFlowUtil.concatComment(getMessage("CeOrg002.seqno"),
                       GlobalConstant.OPEN_PARENTHESIS, entity.getSeqno(), GlobalConstant.CLOSE_PARENTHESIS,
                       getMessage("CeOrg002.customerId"), GlobalConstant.OPEN_PARENTHESIS,
                       entity.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = ceOrg001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new CreditLinesOrgDto[dto.getSelectedItem().size()]));
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
        return ceOrg001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
