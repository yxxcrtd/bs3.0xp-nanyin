package com.joyintech.tams.invest.settle.dvporder.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.impl.BaseGetBusinessIdImpl;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersManagement001Dto;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersProcessDto;
import com.joyintech.tams.invest.settle.dvporder.service.DvpOrdersManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * @program: tams-invest-parent
 * @description: DvpOrdersManagement002Controller
 * @author: 强成西
 * @create: 2018-12-01 10:51
 **/
@Controller
@PageId("DvpOrdersManagement002")
@RequestMapping("/invest/settle/dvporder/DvpOrdersManagement002")
public class DvpOrdersManagement002Controller extends BaseController {

    @Autowired
    private DvpOrdersManagement002Service dvpOrdersManagement002Service;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    public String init(Model model) {
    	String workflowId = getParam("workflowId");
    	if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
    		DvpOrdersProcessDto entity = dvpOrdersManagement002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("bizCodes", entity.getOrderId());
            model.addAttribute("orderId", entity.getOrderId());
        }
        model.addAllAttributes(RequestUtil.getPageParam());
        model.addAttribute("isTodoList", !WorkFlowUtil.isNotFromToDoList());
        setSelectOptionToModel(model, DictCode.是否);
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findTradeData")
    public Page<DvpOrdersManagement001Dto> findTradeData(String[] bizCodes, String isTodoList, String workflowId,
                                                         String orderId) {
        RequestUtil.getSession().removeAttribute("DvpOrdersManagement001_orderDto");
        if (bizCodes == null || bizCodes.length == 0 || StringUtil.isNotEmpty(workflowId)) {
            if (Boolean.parseBoolean(isTodoList)) {
                DvpOrdersProcessDto dto = dvpOrdersManagement002Service.getBussinessEntityByWorkflowId(workflowId);
                RequestUtil.getSession().setAttribute("DvpOrdersManagement001_orderDto", JsonUtils.object2Json(dto));
                return new Page<>(dto.getTradeDatas());
            }
        }
        return dvpOrdersManagement002Service.findTradeData(bizCodes, orderId);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findOrderDataTable")
    public Page<DvpOrdersProcessDto> findTradeData() {
        if (Boolean.parseBoolean(RequestUtil.getParam("isEmpty"))) {
            return new Page(Collections.emptyList());
        }
        DvpOrdersProcessDto dto = JsonUtils.json2Object((String) RequestUtil.getSession().getAttribute("DvpOrdersManagement001_orderDto"),
                DvpOrdersProcessDto.class);
        if (dto == null) {
            return new Page<>();
        }
        return new Page(Arrays.asList(dto));
    }

    @Autowired
    private BaseGetBusinessIdImpl baseGetBusinessIdImpl;

    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid DvpOrdersProcessDto dto, BindingResult br) {
        return commonCheck(br, dto.getOperateType());
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
    public AjaxResult save(@RequestBody @Valid DvpOrdersProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<DvpOrdersProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<DvpOrdersProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        Date sysDate = platformDateImpl.getSysDate();
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_DVP清算指令管理);
        dto.setOrderId(
            DateUtil.format(sysDate, DateUtil.DEFAULT_OTHER_FORMAT) + baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getOrderId());
        params.setBusinessType(DictCodeList.共通业务类型_DVP清算指令管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("DVP清算指令, 指令编号：" + dto.getOrderId()));
        processParameter.setWorkFlowParameter(params);
        String msg = dvpOrdersManagement002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
