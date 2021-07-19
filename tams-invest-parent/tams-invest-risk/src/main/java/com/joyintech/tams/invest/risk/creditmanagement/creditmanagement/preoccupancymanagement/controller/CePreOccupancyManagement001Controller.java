package com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.controller;

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
import com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.dto.CePreOccupancyManagementDto;
import com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.service.CePreOccupancyManagement001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉 预占用管理
 * 
 * @author 裴家俊
 * @version 1.0 2019年1月7日
 * @see CePreOccupancyManagement001Controller
 * @since 1.0
 */

@Controller
@PageId("CePreOccupancyManagement001")
@RequestMapping("/invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement001")
public class CePreOccupancyManagement001Controller extends BaseController{
    
    /**
     * CePreOccupancyManagement001Service
     */
    @Autowired(required = false)
    private CePreOccupancyManagement001Service cePreOccupancyManagement001Service;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
  
    @ResponseBody
    @RequestMapping("/getInstitutionName")
    public List<SelectOptionDto> getInstitutionName()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        String param = super.getRequest().getParameter("search");
        map.put("customerId", param);
        List<SelectOptionDto> temp = cePreOccupancyManagement001Service.getCustomerId(map);
      
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CePreOccupancyManagementDto> selectAll()
        throws Exception {
        return cePreOccupancyManagement001Service.selectForPage(
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
        ValidateGroupDelete.class}) CePreOccupancyManagementDto dto, BindingResult result ) {
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
        ValidateGroupDelete.class}) CePreOccupancyManagementDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<CePreOccupancyManagementDto> processParameter = new ProcessParameter<CePreOccupancyManagementDto>();
        WorkFlowParameter<CePreOccupancyManagementDto> params = new WorkFlowParameter<CePreOccupancyManagementDto>();
        params.setPageId("CePreOccupancyManagement002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSeqno());
        params.setBusinessType(DictCodeList.共通业务类型_预占用管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
        	   return WorkFlowUtil.concatComment(getMessage("CePreOccupancyManagement002.seqno"),
                       GlobalConstant.OPEN_PARENTHESIS, entity.getSeqno(), GlobalConstant.CLOSE_PARENTHESIS,
                       getMessage("CePreOccupancyManagement002.customerId"), GlobalConstant.OPEN_PARENTHESIS,
                       entity.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = cePreOccupancyManagement001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new CePreOccupancyManagementDto[dto.getSelectedItem().size()]));
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
        return cePreOccupancyManagement001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
