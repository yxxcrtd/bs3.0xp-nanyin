package com.joyintech.tams.invest.period.trdirsduration.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinCashPlanEntity;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration004ProcessDto;
import com.joyintech.tams.invest.period.trdirsduration.service.TrdIRSDuration004Service;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 利率互换存续期->利率互换收付息计划维护
 *
 * @author : xkni
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.controller
 * @Description: 利率互换收付息计划维护
 * @date Date : 2019年08月14日
 */
@Controller
@PageId("TrdIRSDuration004")
@RequestMapping("/invest/period/trdirsduration/TrdIRSDuration004")
public class TrdIRSDuration004Controller extends BaseController {
    @Autowired
    private TrdIRSDuration004Service trdIRSDuration004Service;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Override
    public String init(Model model) {
        Map<String, Object> params = RequestUtil.getPageParam();
        String workflowId = RequestUtil.getParam("workflowId");
        TrdIRSDuration004ProcessDto entity = new TrdIRSDuration004ProcessDto();
        if (StringUtil.isNotEmpty(workflowId)) {
            TrdIRSDuration004ProcessDto bussinessEntityByWorkflowId = trdIRSDuration004Service.getBussinessEntityByWorkflowId(workflowId);
            if(bussinessEntityByWorkflowId!=null)
            {
                entity.setPortfolioId(bussinessEntityByWorkflowId.getPortfolioId());
                entity.setFinprodId(bussinessEntityByWorkflowId.getFinprodId());
                entity.setIrsType(bussinessEntityByWorkflowId.getIrsType());
                entity.setVdate(bussinessEntityByWorkflowId.getVdate());
                entity.setMdate(bussinessEntityByWorkflowId.getMdate());
                entity.setTradeDate(bussinessEntityByWorkflowId.getTradeDate());
                model.addAttribute("fixedPaymentPlanTable", JsonUtils.object2Json(bussinessEntityByWorkflowId.getFixedPaymentPlanTable()));
                model.addAttribute("floatPaymentPlanTable", JsonUtils.object2Json(bussinessEntityByWorkflowId.getFloatPaymentPlanTable()));
                model.addAttribute("paymentPlanListMsg", bussinessEntityByWorkflowId.getPaymentPlanListMsg());
                entity.setFixedCashId(bussinessEntityByWorkflowId.getFixedCashId());
                entity.setFloatCashId(bussinessEntityByWorkflowId.getFloatCashId());
                model.addAttribute(GlobalConstant.ENTITY, entity);
            }
        }
        else {
            entity.setPortfolioId((String) params.get("portfolioId"));
            entity.setFinprodId((String) params.get("finprodId"));
            entity.setIrsType((String) params.get("irsTypeName"));
            entity.setVdate(new Date(Long.parseLong((String) params.get("vdate"))));
            entity.setMdate(new Date(Long.parseLong((String) params.get("mdate"))));
            entity.setTradeDate(new Date(Long.parseLong((String) params.get("tradeDate"))));

            //调用交易微服务-利率互换交易-利率互换交易查询接口，将数据显示到页面上。传入参数：交易编号
            List<FinCashPlanEntity> paymentPlanList = getPaymentPlanList(entity.getFinprodId());
            TrdIRSDuration004ProcessDto fixedAndFloatPaymentPlanList = getFixedAndFloatPaymentPlanList(paymentPlanList);
            model.addAttribute("fixedPaymentPlanTable", JsonUtils.object2Json(fixedAndFloatPaymentPlanList.getFixedPaymentPlanTable()));
            model.addAttribute("floatPaymentPlanTable", JsonUtils.object2Json(fixedAndFloatPaymentPlanList.getFloatPaymentPlanTable()));
            entity.setFixedCashId(fixedAndFloatPaymentPlanList.getFixedCashId());
            entity.setFloatCashId(fixedAndFloatPaymentPlanList.getFloatCashId());
            model.addAttribute("paymentPlanListMsg", JsonUtils.object2Json(paymentPlanList));
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        return super.init(model);
    }


    /**
     * 调用交易微服务-利率互换交易-利率互换交易查询接口，将数据显示到页面上。传入参数：交易编号
     *  页面初始化列表数据加载
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     * @param finprodId
     */
    public List<FinCashPlanEntity> getPaymentPlanList(String finprodId)
    {
        return trdIRSDuration004Service.getPaymentPlanList(finprodId);
    }
    /**
     * 根据微服务获取的数据进行转换
     *
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     * @param paymentPlanList
     */
    public TrdIRSDuration004ProcessDto getFixedAndFloatPaymentPlanList(List<FinCashPlanEntity> paymentPlanList)
    {
        TrdIRSDuration004ProcessDto trdIRSDuration004ProcessDto=new TrdIRSDuration004ProcessDto();
        List<TrdIRSDurationDto> fixedList=new ArrayList<>();//固息端利息list
        List<TrdIRSDurationDto> floatList=new ArrayList<>();// 浮动端利息list
        //lanList= JSONObject.parseArray(paymentPlanList.toString(),FinCashPlanEntity.class);
        paymentPlanList.forEach(item-> {
                    TrdIRSDurationDto trdIRSDurationDto=new TrdIRSDurationDto();
                    if(item.getCashTypeTwo().equals(DictCodeListExt2.现金流二级类型_支付.getValue()))
                    {
                        trdIRSDurationDto.setFixVdate(item.getVdate());
                        trdIRSDurationDto.setFixMdate(item.getMdate());
                        trdIRSDurationDto.setFixPayDate(item.getPayDate());
                        trdIRSDurationDto.setRemark(item.getRemark());
                        trdIRSDurationDto.setStatusValid(item.getIsPayInt().equals(DictCodeList.是否_是.getValue())?"已付息":"未付息");
                        fixedList.add(trdIRSDurationDto);
                        trdIRSDuration004ProcessDto.setFixedCashId(item.getCashId());
                    }
                    else  if(item.getCashTypeTwo().equals(DictCodeListExt2.现金流二级类型_收取.getValue()))
                    {
                        trdIRSDurationDto.setFloatVdate(item.getVdate());
                        trdIRSDurationDto.setFloatMdate(item.getMdate());
                        trdIRSDurationDto.setFloatPayDate(item.getPayDate());
                        trdIRSDurationDto.setRemark(item.getRemark());
                        trdIRSDurationDto.setStatusValid(item.getIsPayInt().equals(DictCodeList.是否_是.getValue())?"已付息":"未付息");
                        floatList.add(trdIRSDurationDto);
                        trdIRSDuration004ProcessDto.setFloatCashId(item.getCashId());
                    }
                }

        );
        trdIRSDuration004ProcessDto.setFloatPaymentPlanTable(floatList);
        trdIRSDuration004ProcessDto.setFixedPaymentPlanTable(fixedList);
        trdIRSDuration004ProcessDto.setPaymentPlanList(paymentPlanList);
        return  trdIRSDuration004ProcessDto;
    }


    /**
     * 画面点击保存按钮时check
     *
     * @param dto
     *            实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(
            @RequestBody @Validated({ ValidateGroupEdit.class }) TrdIRSDuration004ProcessDto dto,
            BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        if(dto.getFixedPaymentPlanTable()!=null && dto.getFixedPaymentPlanTable().size()>0) {
            if (dto.getVdate().compareTo(dto.getFixedPaymentPlanTable().get(0).getFixVdate()) != 0) {

                ajaxResult.setMessage("第一条数据计息开始日应为起息日", MessageType.错误消息);
                return ajaxResult;
            }
            if (dto.getMdate().compareTo(dto.getFixedPaymentPlanTable().get(dto.getFixedPaymentPlanTable().size() - 1).getFixMdate()) != 0) {

                ajaxResult.setMessage("最后一条的结束日需为到期日", MessageType.错误消息);
                return ajaxResult;
            }
           Map<Date, List<TrdIRSDurationDto>> fixVdateCollectMap = dto.getFixedPaymentPlanTable().stream().collect(Collectors.groupingBy(s -> s.getFixVdate()));
            if(fixVdateCollectMap.size()!= dto.getFixedPaymentPlanTable().size())
            {
                ajaxResult.setMessage("固息端列表计息开始日重复,请核查", MessageType.错误消息);
                return ajaxResult;
            }
            Map<Date, List<TrdIRSDurationDto>> fixMdateCollectMap = dto.getFixedPaymentPlanTable().stream().collect(Collectors.groupingBy(s -> s.getFixMdate()));
            if(fixMdateCollectMap.size()!= dto.getFixedPaymentPlanTable().size())
            {
                ajaxResult.setMessage("固息端列表计息结束日重复,请核查", MessageType.错误消息);
                return ajaxResult;
            }
        }
        if(dto.getFloatPaymentPlanTable()!=null && dto.getFloatPaymentPlanTable().size()>0) {
            if (dto.getVdate().compareTo(dto.getFloatPaymentPlanTable().get(0).getFloatVdate()) != 0) {

                ajaxResult.setMessage("第一条数据计息开始日应为起息日", MessageType.错误消息);
                return ajaxResult;
            }
            if (dto.getMdate().compareTo(dto.getFloatPaymentPlanTable().get(dto.getFloatPaymentPlanTable().size() - 1).getFloatMdate()) != 0) {

                ajaxResult.setMessage("最后一条的结束日需为到期日", MessageType.错误消息);
                return ajaxResult;
            }

            Map<Date, List<TrdIRSDurationDto>> floatVdateCollectMap = dto.getFloatPaymentPlanTable().stream().collect(Collectors.groupingBy(s -> s.getFloatVdate()));
            if(floatVdateCollectMap.size()!= dto.getFloatPaymentPlanTable().size())
            {
                ajaxResult.setMessage("浮息端列表计息开始日重复,请核查", MessageType.错误消息);
                return ajaxResult;
            }
            Map<Date, List<TrdIRSDurationDto>> floatMdateCollectMap = dto.getFloatPaymentPlanTable().stream().collect(Collectors.groupingBy(s -> s.getFloatMdate()));
            if(floatMdateCollectMap.size()!= dto.getFloatPaymentPlanTable().size())
            {
                ajaxResult.setMessage("浮息端列表计息结束日重复,请核查", MessageType.错误消息);
                return ajaxResult;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 利率互换付息计划维护保存提交
     *
     * @param dto
     *            dto
     * @param br
     *
     * @return 结果
     * @throws Exception
     *             异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(
            @RequestBody @Validated({ ValidateGroupEdit.class }) TrdIRSDuration004ProcessDto dto,
            BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdIRSDuration004ProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdIRSDuration004ProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();

        parameter.setCommonBusinessType(DictCodeList.共通业务类型_利率互换付息计划维护);
        parameter.setCommonBusinessExtType(
                DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_利率互换付息计划维护));
        dto.setBusiCode(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getFinprodId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换付息计划维护.getValue());

        // 设置流程业务说明, entity即传入的dto

        // 流程业务说明-业务说明：“交易编号：” + 上述取得的交易编号+“交易日：”
      /*  params.setComment(entity -> WorkFlowUtil.concatComment("利率互换付息计划维护",
                entity.getBusiCode()//
        ));*/
        params.setComment(entity -> WorkFlowUtil.concatComment(
                "交易编号：" + entity.getFinprodId() + "交易日期：" + com.joyintech.framework.util.DateUtil.format(entity.getTradeDate())
        ));
        // 数据留痕
//        ProcessMark newEntity;
//        try {
//            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(TrdIRSDuration004ProcessDto.class,
//                    true);
//            // 工作流ID
//            newEntity.setPageId(this.getPageId());
//            newEntity.setBusinessDataPk1(dto.getBusiCode());
//            // 更新业务表数据
//            // 操作留痕表
//            WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {

            dto.setBaseTotalTradeInfoDto(trdIRSDuration004Service.genFinProductTradeDto2(dto));
        }
        processParameter.setOperationType(OperationType.NEW);
        processParameter.setPageId(this.getPageId());

        String msg = trdIRSDuration004Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
