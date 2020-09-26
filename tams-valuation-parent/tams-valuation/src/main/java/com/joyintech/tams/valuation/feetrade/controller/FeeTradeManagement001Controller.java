/*
 * 文件名：FinProductDurDeal001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.feetrade.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeManagementDto;
import com.joyintech.tams.valuation.feetrade.service.FeeTradeManagement001Service;
import com.joyintech.tams.valuation.feetrade.service.FeeTradeManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 费用交易管理
 * @version 1.0 2018年01月18日
 * @see FeeTradeManagement001Controller
 * @since 1.0
 */
@Controller
@PageId("FeeTradeManagement001")
@RequestMapping("/valuation/feetrade/FeeTradeManagement001")
public class FeeTradeManagement001Controller extends BaseController {
	
	@Autowired
	private FeeTradeManagement001Service feeTradeManagement001Service;
	
	@Autowired
	private FeeTradeManagement002Service feeTradeManagement002Service;
	
	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.登记托管机构, DictCode.是否);
		 //DictCode.现金流二级类型.
    	List<DataDictItem> cashList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.现金流二级类型.getValue());
    	//DictCode.一次性费用类型
    	List<DataDictItem> feeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.一次性费用类型.getValue());
    	List<SelectOptionDto> feeTypeList = new ArrayList<SelectOptionDto>();
    	for(DataDictItem item : cashList) {
    		SelectOptionDto selectOpt = new SelectOptionDto();
    		selectOpt.setId(item.getItemCode());
    		selectOpt.setText(item.getItemValue());
    		feeTypeList.add(selectOpt);
    	}
    	for(DataDictItem item : feeList) {
    		SelectOptionDto selectOpt = new SelectOptionDto();
    		selectOpt.setId(item.getItemCode());
    		selectOpt.setText(item.getItemValue());
    		feeTypeList.add(selectOpt);
    	}
    	model.addAttribute("feeTypeSelect", JsonUtils.objectArray2Json(feeTypeList.toArray()));
		return super.init(model);
	}
	
	/**
     * 查询
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<FeeTradeManagementDto> findPage() throws Exception {
    	return feeTradeManagement001Service.findPage(RequestUtil.getPageParam());
    }
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) FeeTradeManagementDto dto
    		, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) FeeTradeManagementDto dto
    		, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FeeTradeManagementDto> processParameter = new ProcessParameter<FeeTradeManagementDto>();
        WorkFlowParameter<FeeTradeManagementDto> params = new WorkFlowParameter<FeeTradeManagementDto>();
        params.setPageId("FeeTradeManagement002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_费用交易管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> "账套名称"+ "(" + entity.getBookSetName()+")" +  "-" 
            		+ "支付日"+ "(" + DateUtil.format(entity.getPayDate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = feeTradeManagement002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
    	return feeTradeManagement001Service.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
