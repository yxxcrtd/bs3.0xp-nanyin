/*
 * 文件名：FinProductFeePay004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zcw
 * 修改时间：2019年02月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.feepay.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay001Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay002Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay005Dto;
import com.joyintech.tams.invest.product.feepay.service.FinProductFeePay002Service;
import com.joyintech.tams.invest.product.feepay.service.FinProductFeePay004Service;
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
import java.util.List;

/**
 * 产品费用支付管理-新建
 * 
 * @author zcw
 * @version 1.0 2019年02月13日
 * @see FinProductFeePay004Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductFeePay004")
@RequestMapping("/invest/product/feepay/FinProductFeePay004")
public class FinProductFeePay004Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePay004Service FinProductFeePay004Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePay002Service finProductFeePay002Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.是否);
        model.addAttribute("yesFlag", DictCodeList.是否_是.getValue());
        model.addAttribute("ps", DictCodeList.交易方向_产品付费.getValue());
        model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());

        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        // 平台日期
        model.addAttribute("platDate", iPlatformDate.getSysDateStr());

        //增加收
        model.addAttribute("DEAL_MODE_11", DictCodeList.处理模式_分档.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        //增加组合Id yzg 20200110
        model.addAttribute("ptlId",this.getParam("ptlId"));
        if (StringUtil.equals(this.getParam("isTodoList"), "true")) {
            ProcessMark pro = FinProductFeePay004Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
            	FinProductFeePay005Dto entity = (FinProductFeePay005Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductFeePay005Dto.class);
                model.addAttribute(GlobalConstant.ENTITY, entity);
                model.addAttribute(GlobalConstant.WORKFLOW_ID,
                    this.getParam(GlobalConstant.WORKFLOW_ID));
                if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
                    model.addAttribute("entity_bussTrdTransferOrder",
                        JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
                }
            }
        }else {
        	FinProductFeePay005Dto finProductFeePay005Dto = new FinProductFeePay005Dto();
            finProductFeePay005Dto.setConfirmFlag(this.getParam("confirmFlag"));
            finProductFeePay005Dto.setCcy(this.getParam("ccy"));
            finProductFeePay005Dto.setFeeId(this.getParam("feeId"));
            finProductFeePay005Dto.setFeeName(this.getParam("feeName"));
            finProductFeePay005Dto.setFeeType(this.getParam("feeType"));
            //增加根据组合id查询产品收益模式和处理模式字段
            FinProductFeePay001Dto finProductFeePay001DtoT = finProductFeePay002Service.queryByPtlId(this.getParam("ptlId"));
            if(finProductFeePay001DtoT!=null){
            	finProductFeePay005Dto.setProfitType(finProductFeePay001DtoT.getProfitType());
            	finProductFeePay005Dto.setDealMode(finProductFeePay001DtoT.getDealMode());
            }
            model.addAttribute(GlobalConstant.ENTITY, finProductFeePay005Dto);
        }

        return super.init(model);
    }

    /**
     * 保存check
     * 
     * @param dto 费用新建信息
     * @param br 验证消息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinProductFeePay005Dto dto, BindingResult br) {
        return this.commonCheck(br, dto.getOperateType());
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br 验证信息
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinProductFeePay005Dto dto, BindingResult br)
        throws Exception {
        ProcessParameter<FinProductFeePay005Dto> processParameter = new ProcessParameter<FinProductFeePay005Dto>();
        WorkFlowParameter<FinProductFeePay005Dto> params = new WorkFlowParameter<FinProductFeePay005Dto>();
        params.setElementId(dto.getElementId());
        params.setPageId(this.getPageId());
        
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品费用支付管理);
            dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            for(FinProductFeePay002Dto finProductFeePay002Dto: dto.getFinProductFeePay()) {
            	finProductFeePay002Dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
        }
        
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_产品费用支付管理.getValue());
        // 设置工作流注解
        params.setComment(
            entity -> this.getMessage("FinProductFeePay004.feeName") + "(" + entity.getFeeName()
                      + ")" + this.getMessage("FinProductFeePay004.payDate") + "("
                      + DateUtil.format(entity.getPayDate()) + ")");
        processParameter.setWorkFlowParameter(params);

        // 调用工作流事件
        String msg = FinProductFeePay004Service.start(processParameter, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
    
    /**
     * 加载产品表格
     * 
     * @param FinProductFeePay001Dto 
     * @return FinProductFeePay002Dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryprodInfoList")
    public List<FinProductFeePay002Dto> queryprodInfoList() {
    	// 待办事项
        if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
            ProcessMark pro = FinProductFeePay004Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductFeePay005Dto entity = (FinProductFeePay005Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductFeePay005Dto.class);
                if (entity != null) {
                    return entity.getFinProductFeePay();
                }
            }
        }
        return FinProductFeePay004Service.queryprodInfoList(RequestUtil.getPageParam());
    }
    
    /**
     * 查询组合下产品关联的费用
     * 
     * @return 关联的费用
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList() {
        return FinProductFeePay004Service.queryFeeInfoList(RequestUtil.getPageParam());
    }
}
