package com.joyintech.tams.invest.settle.netting.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.impl.BaseGetBusinessIdImpl;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersProcessDto;
import com.joyintech.tams.invest.settle.netting.dto.NettingManagement001Dto;
import com.joyintech.tams.invest.settle.netting.dto.NettingProcessDto;
import com.joyintech.tams.invest.settle.netting.service.NettingManagement002Service;
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
 * @author 姚轶文
 * @create 2019- 11-07 17:01
 */
@Controller
@PageId("NettingManagement002")
@RequestMapping("/invest/settle/netting/NettingManagement002")
public class NettingManagement002Controller extends BaseController {

    @Autowired
    public NettingManagement002Service nettingManagement002Service;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private BaseGetBusinessIdImpl baseGetBusinessIdImpl;

    @Override
    public String init(Model model) {
        String workflowId = getParam("workflowId");
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            NettingProcessDto entity = nettingManagement002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("bizCodes", entity.getOrderId());
            model.addAttribute("orderId", entity.getOrderId());
        }
        model.addAllAttributes(RequestUtil.getPageParam());
        model.addAttribute("isTodoList", !WorkFlowUtil.isNotFromToDoList());
        setSelectOptionToModel(model, DictCode.是否);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findOrderDataTable")
    public Page<NettingProcessDto> findOrderDataTable()
    {
        if (Boolean.parseBoolean(RequestUtil.getParam("isEmpty"))) {
            return new Page(Collections.emptyList());
        }
        //System.out.println(RequestUtil.getSession().getAttribute("DvpOrdersManagement001_orderDto").getClass().getName());
        NettingProcessDto dto = (NettingProcessDto)RequestUtil.getSession().getAttribute("NettingProcessDto_orderDto");
        if (dto == null) {
            return new Page<>();
        }
        return new Page(Arrays.asList(dto));
    }

    @ResponseBody
    @RequestMapping("findTradeData")
    public Page<NettingManagement001Dto> findTradeData(String[] bizCodes, String isTodoList, String workflowId, String orderId)
    {
        RequestUtil.getSession().removeAttribute("NettingProcessDto_orderDto");
        if (StringUtil.isNotEmpty(workflowId)) {
            if (Boolean.parseBoolean(isTodoList)) {
                NettingProcessDto dto = nettingManagement002Service.getBussinessEntityByWorkflowId(workflowId);
                //System.out.println(dto.getClass().getName());
                RequestUtil.getSession().setAttribute("NettingProcessDto_orderDto", dto);
                return new Page<>(dto.getTradeDatas());
            }
        }
        return nettingManagement002Service.findTradeData(bizCodes, orderId);
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
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid NettingProcessDto dto, BindingResult br) {
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
    public AjaxResult save(@RequestBody @Valid NettingProcessDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<NettingProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<NettingProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        Date sysDate = platformDateImpl.getSysDate();
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_DVP清算指令管理);
        dto.setActOrderId(
                DateUtil.format(sysDate, DateUtil.DEFAULT_OTHER_FORMAT) + baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getOrderId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品端轧差划款.getValue());

        System.out.println(JSONObject.toJSONString(dto));
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("轧差划款指令, 指令编号：" + dto.getOrderId()));
        processParameter.setWorkFlowParameter(params);
        String msg = nettingManagement002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
