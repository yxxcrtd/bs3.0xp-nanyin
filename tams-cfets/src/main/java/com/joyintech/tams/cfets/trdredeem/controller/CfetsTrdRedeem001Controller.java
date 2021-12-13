/*
 * 文件名：CfetsBondDealQuotedPrice002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.trdredeem.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondRegCheckDetaileRequst;
import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondRegCheckRequst;
import com.joyintech.tams.cfets.bankuplink.bond.service.CfetsQuotedCommonService;
import com.joyintech.tams.cfets.bankuplink.bond.service.IbuQuoteBondService;
import com.joyintech.tams.cfets.trdredeem.dto.CfetsTrdRedeemDealCommonDto;
import com.joyintech.tams.cfets.trdredeem.dto.CfetsTrdRedeemDealRegisterQuoteDto;
import com.joyintech.tams.cfets.trdredeem.service.CfetsTrdRedeem001Service;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service.getBussinessCodeParameter;

/**
 * 赎回登记
 */
@Controller
@PageId("CfetsTrdRedeem001")
@RequestMapping("/cfets/trdredeem/CfetsTrdRedeem001")
@Api(tags = "赎回登记")
public class CfetsTrdRedeem001Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsTrdRedeem001Service cfetsTrdRedeem001Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private IbuQuoteBondService ibuQuoteBondService;
    @Autowired
    private CfetsQuotedCommonService cfetsQuotedCommonService;
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
        CfetsTrdRedeemDealRegisterQuoteDto cfetsTrdRedeemDealRegisterQuoteDto = new CfetsTrdRedeemDealRegisterQuoteDto();
        CfetsTrdRedeemDealCommonDto cfetsTrdRedeemDealCommonDto = new CfetsTrdRedeemDealCommonDto();
        if (StringUtil.equals(this.getParam("workflowStatus"), BussinessDictCodeList.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(getParam(GlobalConstant.WORKFLOW_ID)))) {
            cfetsTrdRedeemDealRegisterQuoteDto = cfetsTrdRedeem001Service.getBussinessEntityByWorkflowId(getParam(GlobalConstant.WORKFLOW_ID));
            model.addAttribute(GlobalConstant.WORKFLOW_ID,getParam(GlobalConstant.WORKFLOW_ID));
            BeanUtil.copyPropertiesIgnoreNull(cfetsTrdRedeemDealCommonDto,cfetsTrdRedeemDealRegisterQuoteDto);
            //收益率*100
            if(null!=cfetsTrdRedeemDealCommonDto.getMatureYield()){
                cfetsTrdRedeemDealCommonDto.setMatureYield(ArithmeticUtil.multiply(cfetsTrdRedeemDealCommonDto.getMatureYield(),new BigDecimal(100)));
            }
            if(null!=cfetsTrdRedeemDealCommonDto.getExerYield()){
                cfetsTrdRedeemDealCommonDto.setExerYield(ArithmeticUtil.multiply(cfetsTrdRedeemDealCommonDto.getExerYield(),new BigDecimal(100)));
            }
            //券面万元
            cfetsTrdRedeemDealCommonDto.setShareAmt(ArithmeticUtil.divide(cfetsTrdRedeemDealCommonDto.getShareAmt(),new BigDecimal(10000),2));
            //分配列表元转化万元
            if(CollectionUtils.isNotEmpty(cfetsTrdRedeemDealCommonDto.getPtlAllocatios())){
                cfetsTrdRedeemDealCommonDto.getPtlAllocatios().stream().forEach(item ->{
                    item.setAvaNum(ArithmeticUtil.divide(item.getAvaNum(),new BigDecimal(10000),2));
                    item.setShareAmt(ArithmeticUtil.divide(item.getShareAmt(),new BigDecimal(10000),2));
                    item.setOurEntNum(ArithmeticUtil.divide(item.getOurEntNum(),new BigDecimal(10000),2));
                    item.setLendinEntNum(ArithmeticUtil.divide(item.getLendinEntNum(),new BigDecimal(10000),2));
                    item.setSellAbleNum(ArithmeticUtil.divide(item.getSellAbleNum(),new BigDecimal(10000),2));
                    item.setMatureYield(ArithmeticUtil.multiply(item.getMatureYield(),new BigDecimal(100)));
                    item.setExerYield(ArithmeticUtil.multiply(item.getExerYield(),new BigDecimal(100)));
                });
            }
        }
        model.addAttribute("PRICE_MODEL_PRM01",DictCodeListExt2.价格模式_指定价格.getValue());
        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("PS_T03", DictCodeList.交易方向_行权_回售.getValue());
        model.addAttribute("quotaPageModel","new");
        model.addAttribute("ptlAllocatios", JsonUtils.object2Json(cfetsTrdRedeemDealCommonDto.getPtlAllocatios()));
        //分配方式
        model.addAttribute("distribution",cfetsTrdRedeemDealCommonDto.getDistribution());
        //卖空模式
        model.addAttribute("sellPara",CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
        model.addAttribute(GlobalConstant.ENTITY, cfetsTrdRedeemDealCommonDto);
        return super.init(model);
    }

   /* @ApiOperation(value = "登记初始化", notes = "登记初始化")
    @PostMapping("/initReg")
    @ResponseBody
    public Message<CfetsTrdRedeemDealCommonDto> initPrice(@RequestBody CfetsTrdRedeemDealCommonDto cfetsTrdRedeemDealCommonDto){
        cfetsTrdRedeemDealCommonDto = cfetsTrdRedeem001Service.initReg(cfetsTrdRedeemDealCommonDto);
        return new Message<CfetsTrdRedeemDealCommonDto>().success().setData(cfetsTrdRedeemDealCommonDto);
    }*/

    @ApiOperation(value = "当日登记撤销检查", notes = "当日登记撤销检查")
    @ResponseBody
    @RequestMapping("/checkDayRegRevokeCheck")
    public Message<AjaxResult> checkDayRegRevokeCheck(@RequestBody CfetsBondRegCheckRequst cfetsBondRegCheckRequst) {
        return new Message<AjaxResult>().success().setData(ibuQuoteBondService.checkDayRegRevokeCheck(cfetsBondRegCheckRequst));
    }


    @ApiOperation(value = "当日登记撤销", notes = "当日登记撤销")
    @ResponseBody
    @RequestMapping("regRevoke")
    public Message<AjaxResult> regRevoke(@RequestBody CfetsBondRegCheckRequst cfetsBondRegCheckRequst) throws WorkFlowException{
        //前后台转换
        AjaxResult ajaxResult = ibuQuoteBondService.checkDayRegRevokeCheck(cfetsBondRegCheckRequst);
        if(CollectionUtils.isNotEmpty(ajaxResult.getErrorList())){
            return new Message<AjaxResult>().success().setData(ajaxResult);
        }
        List<CfetsTrdRedeemDealRegisterQuoteDto> CfetsBondDealRegisterQuoteDtos = new ArrayList<>();
        for (CfetsBondRegCheckDetaileRequst cfetsBondRegCheckDetaileRequst : cfetsBondRegCheckRequst.getCfetsBondRegCheckDetaileRequsts()) {
            CfetsTrdRedeemDealRegisterQuoteDto cfetsTrdRedeemDealRegisterQuoteDto = new CfetsTrdRedeemDealRegisterQuoteDto();
            cfetsTrdRedeemDealRegisterQuoteDto.setTotleTradeId(cfetsBondRegCheckDetaileRequst.getQuoteId());
            cfetsTrdRedeemDealRegisterQuoteDto.setRemark(cfetsBondRegCheckRequst.getRemark());
            cfetsTrdRedeemDealRegisterQuoteDto.setOperatorType("revoke");
            CfetsBondDealRegisterQuoteDtos.add(cfetsTrdRedeemDealRegisterQuoteDto);
        }
        ProcessParameter<CfetsTrdRedeemDealRegisterQuoteDto> processParameter = new ProcessParameter<CfetsTrdRedeemDealRegisterQuoteDto>();
        WorkFlowParameter<CfetsTrdRedeemDealRegisterQuoteDto> workFlowParameter = new WorkFlowParameter<CfetsTrdRedeemDealRegisterQuoteDto>();
        workFlowParameter.setPageId("CfetsTrdRedeem001");
        workFlowParameter.setElementId("CfetsTrdRedeem001_btn_revoke");
        workFlowParameter.setBusinessCode(item -> item.getTotleTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_赎回登记.getValue());
        workFlowParameter.setComment(entity ->  "赎回登记编号(" + entity.getTotleTradeId() + ")");
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setOperationType(OperationType.REVOKE);
        String msg = cfetsTrdRedeem001Service.start(processParameter,
                CfetsBondDealRegisterQuoteDtos.toArray(new CfetsTrdRedeemDealRegisterQuoteDto[CfetsBondDealRegisterQuoteDtos.size()]));
        return new Message<AjaxResult>().success().setData(new AjaxResult(msg));
    }

    @ApiOperation(value = "登记提交检查", notes = "登记提交检查")
    @PostMapping("/check")
    @ResponseBody
    public Message<AjaxResult> check(@RequestBody CfetsTrdRedeemDealCommonDto cfetsTrdRedeemDealCommonDto){
        //check必填项
        AjaxResult ajaxResult = cfetsTrdRedeem001Service.checkNotEmpty(cfetsTrdRedeemDealCommonDto);
        if(CollectionUtils.isEmpty(ajaxResult.getErrorList())){
           // ajaxResult = cfetsTrdRedeem001Service.checkcheckAnchoring(cfetsTrdRedeemDealCommonDto);
        }
        if(CollectionUtils.isEmpty(ajaxResult.getErrorList())){
           // ajaxResult = cfetsQuotedCommonService.positionCheck(cfetsTrdRedeemDealCommonDto);
        }
        return new Message<AjaxResult>().success().setData(ajaxResult);
    }

    @ApiOperation(value = "登记提交(保存)", notes = "登记提交(保存)")
	@RequestMapping("save")
    @ResponseBody
	public Message<AjaxResult> save(@RequestBody CfetsTrdRedeemDealCommonDto dto, BindingResult br) throws Exception{
        if (br.hasErrors()) {
            return new Message<AjaxResult>().success().setData(new AjaxResult(br));
		}
        CfetsTrdRedeemDealRegisterQuoteDto cfetsTrdRedeemDealRegisterQuoteDto = new CfetsTrdRedeemDealRegisterQuoteDto();
        //前台实体转成后台dto
        BeanUtil.copyPropertiesIgnoreNull(cfetsTrdRedeemDealRegisterQuoteDto, dto);
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = getBussinessCodeParameter(dto.getPs());
            cfetsTrdRedeemDealRegisterQuoteDto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            cfetsTrdRedeemDealRegisterQuoteDto.setTotleTradeId(IDGenerator.generate());
        }
		ProcessParameter<CfetsTrdRedeemDealRegisterQuoteDto> processParameter = new ProcessParameter<CfetsTrdRedeemDealRegisterQuoteDto>();
		WorkFlowParameter<CfetsTrdRedeemDealRegisterQuoteDto> params = new WorkFlowParameter<CfetsTrdRedeemDealRegisterQuoteDto>();
		params.setPageId("CfetsTrdRedeem001");
		params.setElementId(cfetsTrdRedeemDealRegisterQuoteDto.getElementId());
       // params.setElementId("CfetsTrdRedeem001_btn_submit");
		params.setBusinessType(DictCodeListExt3.共通业务类型_赎回登记.getValue());
		params.setBusinessCode(cfetsTrdRedeemDealRegisterQuoteDto.getTotleTradeId());
        params.setComment(entity -> getMessage("登记报价编号") + "(" + entity.getTotleTradeId() + ")-"
                + "业务类型" + "(现券登记)-" + "平台日" + "("
                + DateUtil.format(iPlatformDate.getSysDate()) + ")");
        cfetsTrdRedeemDealRegisterQuoteDto.setQuoteType(DictCodeListExt3.报价系统区分类型_现券登记报价.getValue());
		processParameter.setWorkFlowParameter(params);
        processParameter.setOperationType(OperationType.NEW);
        String msg = null;
        try{
            msg = cfetsTrdRedeem001Service.start(processParameter, cfetsTrdRedeemDealRegisterQuoteDto);
            return new Message<AjaxResult>().success().setData(new AjaxResult(msg));
        }
        catch (Exception e) {
            return PositionUtil.ajaxErrorReturn(e);
        }
	}


}
