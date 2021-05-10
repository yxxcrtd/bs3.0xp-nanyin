/*
 * 文件名：TrdFundDuration002Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：dingzhipeng
 * 修改时间：2018年10月15日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdassetlossclear.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
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
import com.joyintech.workflow.engine.constant.OperationType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author dingzhipeng
 * @version 1.0 2018年10月15日
 * @see TrdAssetLossClear002Controller
 * @since 1.0
 */
@PageId("TrdAssetLossClear002")
@RequestMapping("/invest/period/trdassetlossclear/TrdAssetLossClear002")
@Controller
public class TrdAssetLossClear002Controller extends BaseController {
    
    @Autowired
    private TrdAssetLossClear001Service trdAssetLossClear001Service;
    
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        String workflowId = getParam("workflowId");   
        //工作流待办跳转
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        TrdAssetLossClear001Dto dto = trdAssetLossClear001Service.getBussinessEntityByWorkflowId(workflowId);
        if(!BussinessConstant.REVOKE.equals(dto.getOperateType())){
            model.addAttribute("entity", dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("finprodMarketId", dto.getFinprodId());
            model.addAttribute("clearDate", dto.getTradeDate());
        }else{
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodMarketId", dto.getSelectedItem().get(0).getFinprodId());  
            param.put("clearDate",DateUtil.format(DateUtil.addDay(dto.getSelectedItem().get(0).getTradeDate(), -1)));  
            List<TrdAssetLossClear001Dto> resultDto = trdAssetLossClear001Service.findNewList(param);
            model.addAttribute(GlobalConstant.ENTITY,resultDto.get(0));
            model.addAttribute("finprodId", dto.getSelectedItem().get(0).getFinprodId());
            model.addAttribute("clearDate", dto.getSelectedItem().get(0).getTradeDate());
        }
        }
        //新建
        else {       	
            model.addAttribute("clearDate", sysDate);
        }
        return super.init(model);
    }
    
    /**
     * 分页查询 市场代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdAssetLossClear001Service.finprodMarketId();
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdAssetLossClear001Dto dto,
                            BindingResult result) {
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("TrdAssetLossClear002");
        parameter.setBusinessDataPk2(dto.getFinprodId());
        //parameter.setBusinessDataPk2("F01");
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(parameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
		Map<String, Object> param = new HashMap<>();
		param.put("portfolioId", dto.getFinprodId());
		param.put("clearDate", DateUtil.format(dto.getTradeDate()));
    	int count = trdAssetLossClear001Service.selectAredyLossAsset(param);
    	ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
    	if (count > 0){
			errorList.add(new ValidMessage("同一资产同一清理日只能做一次清理!"));
			AjaxResult ar = new AjaxResult();
			ar.setErrorList(errorList);
			return ar;
    	}
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 新建页面画面联动
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("findNewPage")
    public Page<TrdAssetLossClear001Dto> findNewPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        if(!StringUtil.isNotEmpty(param.get("finprodMarketId"))){
        	return new Page<TrdAssetLossClear001Dto>();
        }
        String clearDate =  (String) param.get("clearDate");
        param.put("clearDate", DateUtil.addDay(clearDate, -1));
        return trdAssetLossClear001Service.findNewPage(param);
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdAssetLossClear001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdAssetLossClear001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdAssetLossClear001Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        params.setBusinessCode(dto.getFinprodId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产减值清理.getValue());
        String dealId = trdAssetLossClear001Service.createStrategyId();
        dto.setTotleTradeId(dealId);
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(this.getMessage("交易编号") +
            GlobalConstant.OPEN_PARENTHESIS + dealId + GlobalConstant.CLOSE_PARENTHESIS,
            this.getMessage("业务类型") +
            GlobalConstant.OPEN_PARENTHESIS + DictCodeListExt3.共通业务类型_资产减值清理.getCodeName()
            + GlobalConstant.CLOSE_PARENTHESIS,
            this.getMessage("交易日") +
            GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(DateUtil.platDateFromCache()) + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        String msg = trdAssetLossClear001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
