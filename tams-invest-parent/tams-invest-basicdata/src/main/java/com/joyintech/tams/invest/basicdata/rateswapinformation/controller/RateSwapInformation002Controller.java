package com.joyintech.tams.invest.basicdata.rateswapinformation.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.rateswapinformation.dto.RateSwapInformationDto;
import com.joyintech.tams.invest.basicdata.rateswapinformation.service.RateSwapInformationService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 利率互换行情信息新建
 * 〈功能详细描述〉
 * @author daizhiwei
 * @version 1.0 2019年7月19日
 * @see RateSwapInformation002Controller
 * @since 1.0
 */
@PageId("RateSwapInformation002")
@RequestMapping("/invest/basicdata/rateswapinformation/RateSwapInformation002")
@Controller
public class RateSwapInformation002Controller extends BaseController{

    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Autowired
    private RateSwapInformationService rateSwapInformationService;

	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.利率行情);
        this.setSelectOptionToModel(model, DictCode.期限品种_利率互换);
        this.setSelectOptionToModel(model, DictCode.利率互换曲线类型);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String curveCode = getParam("curveCode");
        String curveDate = getParam("curveDate");
        model.addAttribute("validExist", true);
        model.addAttribute("disabled",false);
        model.addAttribute("PRICE_TYPE_01", DictCodeListExt2.价格类型_报买.getValue());
        model.addAttribute("PRICE_TYPE_02", DictCodeListExt2.价格类型_报卖.getValue());
        model.addAttribute("PRICE_TYPE_03", DictCodeListExt2.价格类型_均值.getValue());
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            RateSwapInformationDto dto = rateSwapInformationService.getBussinessEntityByWorkflowId(workflowId);
            dto.setWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("curveCode",dto.getCurveCode());
            model.addAttribute("curveDate",dto.getCurveDate());
            model.addAttribute("dataObj", dto.getRateSwapInformationDtoList());
            model.addAttribute("businessId",dto.getWorkflowId());
            model.addAttribute("validExist", true);
            model.addAttribute("disabled",dto.isDisabled());
        }else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(curveCode) && StringUtil.isNotEmpty(curveDate)){
            Map<String, Object> map = new HashMap<>();
            map.put("curveCode",curveCode);
            map.put("curveDate",curveDate);
            Page<RateSwapInformationDto> dtoList = rateSwapInformationService.selectByParams(map);
            model.addAttribute("dataObj", dtoList.getRoot());
            model.addAttribute("curveCode",curveCode);
            model.addAttribute("validExist", false);
            model.addAttribute("curveDate",DateUtil.parse(curveDate));
            model.addAttribute("disabled",true);
        }
        return super.init(model);
    }

    /**
     *@title: check
     *@description: 保存按钮点击时的check
     *@author: daizhiwei
     *@date: 2019/07/22 10:19
     *@param: * @param dto 1
     * @param result 2
     *@return: com.joyintech.framework.ajax.AjaxResult
     *@throws:
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) RateSwapInformationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *@title: save
     *@description: 保存/提交
     *@author: junYin
     *@date: 2019/06/04 10:20
     *@param: * @param dto 1
     * @param br 2
     *@return: com.joyintech.framework.ajax.AjaxResult
     *@throws:
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) RateSwapInformationDto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        if(StringUtil.isNotEmpty(dto.getCurveCode())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_利率互换行情信息));
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): IDGenerator.generate();
            dto.setBusinessId(code);
        }
        ProcessParameter<RateSwapInformationDto> processParameter = new ProcessParameter<RateSwapInformationDto>();
        WorkFlowParameter<RateSwapInformationDto> params = new WorkFlowParameter<RateSwapInformationDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBusinessId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        if(dto.getCurveCode() != null){
            params.setComment(
                entity -> this.getMessage("RateSwapInformation002.curveCode") + "(" + entity.getCurveCode() + ")("
                    + this.getMessage("RateSwapInformation002.curveDate") + ":" + DateUtil.format(entity.getCurveDate())
                    + ")" +
                    "");
        }
        processParameter.setWorkFlowParameter(params);
        String msg = rateSwapInformationService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     *@title: getCurveType
     *@description: 获取利率互换曲线类型（名称）
     *@author: 王贵奇
     *@date: 2019/09/27
     *@return: Page<SelectOptionDto>
     *@throws:
     */
	@ResponseBody
    @SuppressWarnings("unchecked")
    @RequestMapping("getCurveType")
    public Page<SelectOptionDto> getCurveType() {
    	Page<SelectOptionDto> temp = rateSwapInformationService.getCurveType(RequestUtil.getPageParam());
    	return temp;
    }
}
