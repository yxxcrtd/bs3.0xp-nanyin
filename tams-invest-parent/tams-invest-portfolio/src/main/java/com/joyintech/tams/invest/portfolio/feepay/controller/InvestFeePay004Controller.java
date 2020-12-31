/*
 * 文件名：InvestFeePay003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.feepay.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay002Dto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay003Dto;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay002Service;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay003Service;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.dto.FeeProvisionSetting001Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


/**
 * 费用明细
 * 
 * @author malinlin
 * @version 1.0 2019年12月9日
 * @see InvestFeePay004Controller
 * @since 1.0
 */
@Controller
@PageId("InvestFeePay004")
@RequestMapping("/invest/portfolio/feepay/InvestFeePay004")
public class InvestFeePay004Controller extends BaseController {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePay003Service investFeePay003Service;
    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePay002Service investFeePay002Service;

    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.投资端费用品种类型,DictCode.是否);
        model.addAttribute("ps", DictCodeList.交易方向_产品付费.getValue());
        if (StringUtil.equals(this.getParam("isTodoList"), "true")) {
            ProcessMark pro = investFeePay003Service.getBusinessInfo(this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                InvestFeePay002Dto entity = (InvestFeePay002Dto) JsonUtils.json2Object(
                        pro.getBusinessData(), InvestFeePay002Dto.class);
                model.addAttribute(GlobalConstant.ENTITY, entity);
                model.addAttribute(GlobalConstant.WORKFLOW_ID,
                        this.getParam(GlobalConstant.WORKFLOW_ID));
            }
        }else  {
            InvestFeePay002Dto entity = new InvestFeePay002Dto();
            entity.setFeeType(this.getParam("feeType1"));
            entity.setSecAcctId(this.getParam("secAcctId"));
            entity.setPayAmt(new BigDecimal(this.getParam("feeAmt")));
            entity.setPayDate(DateUtil.parse(this.getParam("payDateStr")));
            entity.setBussTrdTransferOrder(investFeePay002Service.selectPayOrders(this.getParam("tradeId")));
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("tradeId", this.getParam("tradeId"));
        }
        return super.init(model);
    }

    /**
     *
     * 费用明细数据表格查询
     * <功能详细描述>
     * @return Page<PaRskCustomerRatingDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<InvestFeePay003Dto> selectDetail()
            throws Exception {
        Page<InvestFeePay003Dto> page = investFeePay003Service.selectDetail(RequestUtil.getPageParam());
        return page;
    }

    /**
     * check
     * @param dto 费用新建信息
     * @param br 验证消息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody InvestFeePay002Dto dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
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
    public AjaxResult save(@RequestBody InvestFeePay002Dto dto, BindingResult br)
            throws Exception {
        ProcessParameter<InvestFeePay002Dto> processParameter = new ProcessParameter<InvestFeePay002Dto>();
        WorkFlowParameter<InvestFeePay002Dto> params = new WorkFlowParameter<InvestFeePay002Dto>();
        params.setElementId(dto.getElementId());
        params.setPageId(this.getPageId());

        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_产品费用支付管理.getValue());
        // 设置工作流注解
        params.setComment(
                entity ->  this.getMessage("InvestFeePay001.table.tradeId") + "(" + entity.getTradeId() + ")"
                         +  this.getMessage("InvestFeePay002.secAcctId") + "(" + entity.getSecAcctId() + ")"
                        + this.getMessage("InvestFeePay002.payDate") + "(" + DateUtil.format(entity.getPayDate()) + ")");
        processParameter.setWorkFlowParameter(params);

        // 调用工作流事件
        String msg = investFeePay003Service.start(processParameter, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
}
