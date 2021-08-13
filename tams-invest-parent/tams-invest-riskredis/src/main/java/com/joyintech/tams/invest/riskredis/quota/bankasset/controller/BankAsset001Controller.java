package com.joyintech.tams.invest.riskredis.quota.bankasset.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.riskredis.quota.bankasset.dto.BankAsset001Dto;
import com.joyintech.tams.invest.riskredis.quota.bankasset.service.BankAsset001Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 商业银行净资产维护
 * @author libaisong
 * @version 1.0 2020年12月29日
 * @see BankAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("BankAsset001")
@RequestMapping("/invest/riskredis/quota/bankasset/BankAsset001")
public class BankAsset001Controller extends BaseController{

	@Autowired
	private BankAsset001Service bankAsset001Service;
	
	/**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, BussinessDictCodeRds.商业银行净资产维护_有效状态);
        model.addAttribute("platDate", DateUtil.format(DateUtil.platDateFromCache())+" - "+DateUtil.format(DateUtil.platDateFromCache()));
        return super.init(model);
    }
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<BankAsset001Dto> findPage()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Object customerId = map.get("customerId");
		if (null != customerId) {
			List<String> customerIdList = StringUtil.splitToList(GlobalConstant.COMMA, String.valueOf(customerId));
			map.put("customerId", customerIdList);
		}
		Object effDate = map.get("effDate");
		Object startEffDate = (effDate).toString().substring(0,10);
		map.put("startEffDate", startEffDate);
		Object endEffDate = (effDate).toString().substring(13,23);
		map.put("endEffDate", endEffDate);
        Page<BankAsset001Dto> page = bankAsset001Service.selectForPage(map);
        return page;
    }
    
    /**
     * 主体代码下拉框查询
     * @return
     * @throws Exception
     */
	@ResponseBody
	@RequestMapping(value = "selectCustomerId", method = RequestMethod.GET)
	public Page<SelectOptionDto> selectCustomerId() throws Exception {
		Map<String, Object> map = RequestUtil.getParameters(getRequest());
		Page<SelectOptionDto> page = bankAsset001Service.selectCustomerId(map);
		return page;
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) BankAsset001Dto dto, BindingResult result) {
    	if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
    	WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("BankAsset002");
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        
        if(WorkFlowUtil.isNotFromToDoList()){
        	 if (StringUtil.equals(dto.getOperateType(), "delete")) {
        		 if(StringUtil.isNotEmpty(dto) && !StringUtil.isEmptyList(dto.getSelectedItem())){
        			 dto.getSelectedItem().forEach(item->{
                         if (StringUtil.isNotEmpty(item.getCustomerId()) && StringUtil.isNotEmpty(item.getEffDateStr())){
                             wflValidInputParameter.setBusinessDataPk1(item.getCustomerId());
                             wflValidInputParameter.setBusinessDataPk2(item.getEffDateStr());
                             if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                                 errorList.add(new ValidMessage("所选对象存在流程中的数据"+" 更新时间:"+item.getEffDateStr()+" 主体代码:"+item.getCustomerId()));
                             }
                         }
                     });
        		 }
        	 }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
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
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) BankAsset001Dto dto, BindingResult br)
            throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<BankAsset001Dto> processParameter = new ProcessParameter<BankAsset001Dto>();
        WorkFlowParameter<BankAsset001Dto> params = new WorkFlowParameter<BankAsset001Dto>();
        params.setPageId("BankAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getCustomerId());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_商业银行净资产维护.getValue());
        // 设置流程业务说明:主体代码 + "(" + 主体代码 + ")" + "-"  + "更新时间" + "-"  + "更新时间"
        params.setComment(
                entity -> "主体代码" + "(" + entity.getCustomerId() + ")"
                        + "更新时间" + "(" + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = bankAsset001Service.start(
                processParameter, dto.getSelectedItem().toArray(new BankAsset001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        return bankAsset001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
        throws Exception{
        return this.bankAsset001Service.importData(fileName);
    }
    
    
}
