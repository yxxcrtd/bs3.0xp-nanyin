/*
 * 文件名：TrdFundDuration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdassetlossclear.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdassetlossclear.dto.TrdAssetLossClear001Dto;
import com.joyintech.tams.invest.period.trdassetlossclear.service.TrdAssetLossClear001Service;
import com.joyintech.tams.invest.period.trdplannetduration.dto.TrdPlanNetDuration001Dto;
import com.joyintech.tams.invest.period.trdplannetduration.service.TrdPlanNetDuration001Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * 存续期基金分红
 * 
 * @author chufengjie 
 * @version 1.0 2020年10月20日
 * @see TrdAssetLossClear001Controller
 * @since 1.0
 */
@PageId("TrdAssetLossClear001")
@RequestMapping("/invest/period/trdassetlossclear/TrdAssetLossClear001")
@Controller
public class TrdAssetLossClear001Controller extends BaseController {
    /**
     * 基金存续期service
     */
    @Autowired
    private TrdPlanNetDuration001Service trdPlanNetDuration001Service;
    /**
     * 减值清理service
     */
    @Autowired
    private TrdAssetLossClear001Service trdAssetLossClear001Service;
    
    
    /**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    
    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.画面数据状态};
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        	TrdAssetLossClear001Dto dto = trdAssetLossClear001Service.getBussinessEntityByWorkflowId(
                    workflowId);
        	
        	String date =DateUtil.format(dto.getSelectedItem().get(0).getTradeDate());
        	if(StringUtil.isNotEmpty(date)){
        	    date = date + " - " + date;
        	}        	
            model.addAttribute("entity", dto.getSelectedItem());
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("finprodMarketId", dto.getSelectedItem().get(0).getFinprodId());
            model.addAttribute("clearDate", DateUtil.format(dto.getSelectedItem().get(0).getTradeDate()));            
        }
        return super.init(model);
    }

    /**
     * 获取所有资产代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdAssetLossClear001Service.finprodMarketId();
    }
    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdAssetLossClear001Dto> findPage() {
        return trdAssetLossClear001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object>  paraMap = this.getRequestMap();
        TrdAssetLossClear001Dto dto = (TrdAssetLossClear001Dto)JsonUtils.map2entity(paraMap, TrdAssetLossClear001Dto.class);
        List<TrdAssetLossClear001Dto> listdto = new ArrayList<>();
		if (datas == null) {
			listdto = (List<TrdAssetLossClear001Dto>)trdAssetLossClear001Service.selectForExport(paraMap);
		} else {
			listdto = JsonUtils.json2List(datas, TrdAssetLossClear001Dto.class);
		}
		 List<TrdAssetLossClear001Dto> listdto1 = new ArrayList<>();
		for(TrdAssetLossClear001Dto resultDto : listdto){
			resultDto.setTradeDateExcel(DateUtil.format(resultDto.getTradeDate(), "yyyy-MM-dd"));	
			listdto1.add(resultDto);
		}
        File outFile = trdAssetLossClear001Service.makeFile(
                "/com/joyintech/tams/invest/period/trdassetlossclear/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(DateUtil.parse(DateUtil.getSysDate())) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
    

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdAssetLossClear001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        TrdAssetLossClear001Dto temp = new TrdAssetLossClear001Dto();
        temp.setTotleTradeId(dto.getTotleTradeId());
        temp.setOperateType(dto.getOperateType());
        temp.setSelectedItem(dto.getSelectedItem());
        ProcessParameter<TrdAssetLossClear001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdAssetLossClear001Dto> params = new WorkFlowParameter<>();
        params.setPageId("TrdAssetLossClear002");
        params.setElementId(dto.getElementId());
        //模式为撤销
        temp.setPageModel(OperationType.REVOKE.toString());
        params.setBusinessCode(temp.getTotleTradeId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产减值清理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
        		entity -> "交易代码："+temp.getTotleTradeId()+"业务类型："+ DictCodeListExt3.共通业务类型_资产减值清理.getCodeName()+"交易日："+DateUtil.getSysDate());
        processParameter.setWorkFlowParameter(params);
        String msg = trdAssetLossClear001Service.start(processParameter, temp);
        return new AjaxResult(msg);
    }

	/**
	 * 撤销check
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupDelete.class }) TrdAssetLossClear001Dto dto,
			BindingResult result) {
	    WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("TrdAssetLossClear002");
        parameter.setBusinessDataPk1(dto.getTotleTradeId());
        //parameter.setBusinessDataPk2("F01");
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(parameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
		if (StringUtil.isNotEmpty(dto.getTradeDate())) {
			ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
			if (dto.getTradeDate().compareTo(DateUtil.parse(DateUtil.getSysDate())) != 0) {
				errorList.add(new ValidMessage("lossDate", "当前账套会计日不等于数据账套会计日，请调整！"));
				AjaxResult ar = new AjaxResult();
				ar.setErrorList(errorList);
				return ar;
			}

		}
		return commonCheck(result, dto.getOperateType());
	}
	
	/**
	 * 导出参数组装
	 *
	 * @return
	 */	
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 发生日期区间
        String clearDateStart = this.getParam("clearDateStart");
        if(StringUtil.isNotEmpty(clearDateStart)){
            paraMap.put("clearDateStart", DateUtil.parseDate(clearDateStart, "yyyy-MM-dd"));
        }
        String clearDateEnd = this.getParam("clearDateEnd");
        if(StringUtil.isNotEmpty(clearDateEnd)){
            paraMap.put("clearDateEnd", DateUtil.parseDate(clearDateEnd, "yyyy-MM-dd"));
        }
        String finprodMarketId = this.getParam("finprodMarketId");
        if (StringUtil.isNotEmpty(finprodMarketId)){
            paraMap.put("finprodMarketId", finprodMarketId);
        }
        return paraMap;
    }

}
