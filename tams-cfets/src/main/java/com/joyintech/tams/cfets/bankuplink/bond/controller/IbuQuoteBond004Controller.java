/*
 * 文件名：CfetsBondDealQuotedPrice002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.bankuplink.bond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondDealQuoteCommonDto;
import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondDealRegisterQuoteDto;
import com.joyintech.tams.cfets.bankuplink.bond.service.CfetsBondDealQuotedPrice004Service;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service.getBussinessCodeParameter;

/**
 * 现券买卖(登记)
 */
@Controller
@PageId("IbuQuoteBond004")
@RequestMapping("/cfets/bond/IbuQuoteBond004")
@Api(tags = "CFETS债券交易-登记")
public class IbuQuoteBond004Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsBondDealQuotedPrice004Service cfetsBondDealQuotedPrice004Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.交易方向, BussinessDictCode.清算速度,
            BussinessDictCode.结算方式, BussinessDictCode.清算类型, BussinessDictCode.币种);
        model.addAttribute("ACCOUNT_SPEED_0", BussinessDictCodeList.清算速度_T加0.getValue());
        model.addAttribute("ACCOUNT_SPEED_1", BussinessDictCodeList.清算速度_T加1.getValue());
        model.addAttribute("ACCOUNT_SPEED_2", BussinessDictCodeList.清算速度_T加2.getValue());
        model.addAttribute("yesNoFlag_yes", BussinessDictCodeList.是否_是.getValue());
        model.addAttribute("assetTypeOne_abs", BussinessDictCodeList.平安资产一级分类_资产支持证券.getValue());
        model.addAttribute("calcTypeUnitCprice", BussinessDictCodeList.计算选择方式_净价.getValue());
        model.addAttribute("calcTypeYield", BussinessDictCodeList.计算选择方式_到期收益率.getValue());
        model.addAttribute("calcTypeExecYield", BussinessDictCodeList.计算选择方式_行权收益率.getValue());
        // 本方交易员是否可改
        model.addAttribute("ouTraderIdCanEditFlag", CacheUtil.getSysParam("ibuFlag_ouTraderIdCanEdit"));
        // 无委托指令是否允许回复
        model.addAttribute("ibuFlag_startDialogueQuoteWithNoEntrust", CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"));
        CfetsBondDealRegisterQuoteDto cfetsBondDealRegisterQuoteDto = new CfetsBondDealRegisterQuoteDto();
        // 目前流程中场合只适用撤销逻辑,因为新建没走流程拿不到数据留痕表的记录，只能根据报价代码反查  20201225开始新增走流程了
        if (StringUtil.equals(this.getParam("workflowStatus"), BussinessDictCodeList.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(getParam(GlobalConstant.WORKFLOW_ID)))) {
            cfetsBondDealRegisterQuoteDto = cfetsBondDealQuotedPrice004Service.getBussinessEntityByWorkflowId(getParam(GlobalConstant.WORKFLOW_ID));
            model.addAttribute(GlobalConstant.WORKFLOW_ID,getParam(GlobalConstant.WORKFLOW_ID));
        }
        model.addAttribute("PRICE_MODEL_PRM01",DictCodeListExt2.价格模式_指定价格.getValue());
        model.addAttribute(GlobalConstant.ENTITY, cfetsBondDealRegisterQuoteDto);
        return super.init(model);
    }


    @ApiOperation(value = "登记提交检查", notes = "登记提交检查")
    @PostMapping("/check")
    @ResponseBody
    public Message<AjaxResult> check(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto){
        //check必填项
        AjaxResult ajaxResult = cfetsBondDealQuotedPrice004Service.checkNotEmpty(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isEmpty(ajaxResult.getErrorList())){
            //chenck价格  收益率
            ajaxResult = cfetsBondDealQuotedPrice004Service.checkcheckAnchoring(cfetsBondDealQuoteCommonDto);
        }
        return new Message<AjaxResult>().success().setData(ajaxResult);
    }

    @ApiOperation(value = "登记提交(保存)", notes = "登记提交(保存)")
	@RequestMapping("save")
    @ResponseBody
	public AjaxResult save(@RequestBody CfetsBondDealQuoteCommonDto dto, BindingResult br) throws WorkFlowException {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
        CfetsBondDealRegisterQuoteDto cfetsBondDealRegisterQuoteDto = new CfetsBondDealRegisterQuoteDto();
        //前台实体转成后台dto
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealRegisterQuoteDto, dto);
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = getBussinessCodeParameter(dto.getPs());
            cfetsBondDealRegisterQuoteDto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            cfetsBondDealRegisterQuoteDto.setTotleTradeId(IDGenerator.generate());
        }
		ProcessParameter<CfetsBondDealRegisterQuoteDto> processParameter = new ProcessParameter<CfetsBondDealRegisterQuoteDto>();
		WorkFlowParameter<CfetsBondDealRegisterQuoteDto> params = new WorkFlowParameter<CfetsBondDealRegisterQuoteDto>();
		params.setPageId("IbuQuoteBond004");
		params.setElementId(cfetsBondDealRegisterQuoteDto.getElementId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
		params.setBusinessCode(cfetsBondDealRegisterQuoteDto.getTotleTradeId());
        cfetsBondDealRegisterQuoteDto.setQuoteType(DictCodeListExt3.报价系统区分类型_现券登记报价.getValue());
		processParameter.setWorkFlowParameter(params);
		String msg = cfetsBondDealQuotedPrice004Service.start(processParameter, cfetsBondDealRegisterQuoteDto);
		return new AjaxResult(msg);
	}


}
