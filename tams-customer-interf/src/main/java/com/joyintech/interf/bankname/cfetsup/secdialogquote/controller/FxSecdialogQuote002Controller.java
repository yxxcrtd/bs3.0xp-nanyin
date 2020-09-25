package com.joyintech.interf.bankname.cfetsup.secdialogquote.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCode;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCodeList;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dto.FxSecdialogQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.service.FxSecdialogQuote002Service;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.EtrRepoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 现券买卖对话报价新建
 *
 * @author Zane.Y
 * @version 1.0 2020年01月10日
 * @see FxSecdialogQuote002Controller
 * @since 1.0
 */
@Controller
@PageId("FxSecdialogQuote002")
@RequestMapping("/interf/cfetsup/secdialogquote/FxSecdialogQuote002")
public class FxSecdialogQuote002Controller extends BaseController {

    @Autowired
    private FxSecdialogQuote002Service fxSecdialogQuote002Service;

    @Override
    public String init(Model model) {
        //交易方向
        model.addAttribute(FxDictCode.交易方向_现券.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.交易方向_现券)));
        //交易场所
        model.addAttribute(DictCode.交易场所.getValue(), JsonUtils.object2Json(DictCodeListExt1.fromDictCode(DictCode.交易场所)));
        //清算速度
        model.addAttribute(DictCode.清算速度.getValue(),JsonUtils.object2Json(DictCodeList.fromDictCode(DictCode.清算速度)));
        //结算方式
        model.addAttribute(FxDictCode.结算方式.getCode(),JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.结算方式)));
        //清算类型
        model.addAttribute(FxDictCode.清算类型_现券.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.清算类型_现券)));
        //币种
        model.addAttribute(DictCode.币种.getValue(),JsonUtils.object2Json(DictCodeList.fromDictCode(DictCode.币种)));
        //本方角色
        model.addAttribute(FxDictCode.本方角色.getCode(),JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.本方角色)));
        //回复报价
        model.addAttribute(FxDictCode.回复报价_操作类型.getCode(),JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.回复报价_操作类型)));
        String seqno = this.getParam("seqno");
        String pageModel = this.getParam("pageModel");
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("ccy1",DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("TRANSACTION_CALENDAR",DictCodeList.交易日历_中国银行间交易日历.getValue());
        String entrustId = this.getParam("entrustId");
        //审批
        String workflowId = this.getParam("workflowId");
        FxSecdialogQuote002Dto fxSecdialogQuote002Dto = new FxSecdialogQuote002Dto();
        if (StringUtil.isNotEmpty(seqno)) {
            //查看修改
            ProcessMark processMark = new ProcessMark();
            processMark.setBusinessDataPk1(seqno);
           fxSecdialogQuote002Dto = fxSecdialogQuote002Service.selectByPrimaryKey(seqno);
        }else if(StringUtil.isNotEmpty(entrustId)){
            //新增
            EtrRepoDto etrRepoDto = fxSecdialogQuote002Service.selectByEntrustId(entrustId);
            if(StringUtil.isNotEmpty(etrRepoDto)){
                model.addAttribute("TRANSACTION_CALENDAR",etrRepoDto.getCalendarId());
            }
            fxSecdialogQuote002Dto = fxSecdialogQuote002Service.getRepoQuoteInfo(etrRepoDto);
            model.addAttribute("entity", fxSecdialogQuote002Dto);
        }else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            fxSecdialogQuote002Dto = fxSecdialogQuote002Service.getBussinessEntityByWorkflowId(workflowId);
        }
        model.addAttribute("entity", fxSecdialogQuote002Dto);
        return super.init(model);
    }

    /**
     * 债券代码：下拉框
     *
     * @param params 画面参数
     * @return 债券代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId(@RequestParam Map<String, Object> params) {
        params.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return fxSecdialogQuote002Service.getFinprodId(params);
    }

    /**
     * 获取下一个工作日
     *
     * @param params 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    @RequestMapping("getNextWorkDay")
    @ResponseBody
    public String getNextWorkDay(@RequestParam Map<String, Object> params) {
        return fxSecdialogQuote002Service.getNextWorkDay(params);
    }

    /**
     * 行权收益率是否显示
     *
     * @param param 画面参数
     * @return 行权收益率是否显示
     * @see
     * @since 1.0
     */
    @RequestMapping("isExerYieldDivShow")
    @ResponseBody
    public String isExerYieldDivShow(@RequestParam Map<String, Object> param) {
        return fxSecdialogQuote002Service.isExerYieldDivShow(param);
    }

    /**
     * 获取行权日
     * @param param
     * @return
     */
    @RequestMapping("getExerciseDate")
    @ResponseBody
    public String getExerciseDate(@RequestParam Map<String, Object> param){

        return null;
    }

    /**
     * 1.7计算按钮点击事件
     *
     * @param inputDto 画面参数
     * @return 计算结果
     * @see
     * @since 1.0
     */
    @RequestMapping("calculateData")
    @ResponseBody
    public AjaxResult calculateData(@RequestBody FxSecdialogQuote002Dto inputDto) {

        return fxSecdialogQuote002Service.calculateData(inputDto);
    }


    @ResponseBody
    @RequestMapping("/check")
    public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class}) FxSecdialogQuote002Dto dto, BindingResult result){
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        if(result.getAllErrors().isEmpty() &&  StringUtils.isNoneEmpty(dto.getOperateType())) {
            AjaxResult result2 = new AjaxResult();
            String msg = "";
            if(StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,dto.getBtnOperateType())) {
                msg = "请确认是否保存?";
            }else if(StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,dto.getBtnOperateType())){
                msg = "请确认是否提交?";
            }else if(StringUtil.equalsIgnoreCase("revoke",dto.getBtnOperateType())){
                msg = "请确认是否撤销?";
            }
            result2.setData(this.getMessage(msg));
            return result2;
        }else {
            AjaxResult wr = commonCheck(result, dto.getOperateType());
            wr.setJoyinMessages(joyinMessages);
            return wr;
        }
    }

    @ResponseBody
    @RequestMapping("/save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) FxSecdialogQuote002Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getSeqno())){
            dto.setSeqno(IDGenerator.generate());
        }
        ProcessParameter<FxSecdialogQuote002Dto> processParameter = new ProcessParameter<FxSecdialogQuote002Dto>();
        WorkFlowParameter<FxSecdialogQuote002Dto> params = new WorkFlowParameter<FxSecdialogQuote002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(FxDictCodeList.共通业务类型_现券买卖对话报价.getItemCode());
        params.setComment(entity -> "新增现券买卖对话报价，委托编号" + "(" + entity.getTrustid()
                + ")-交易方向 ：" + FxDictCodeList.from(FxDictCode.交易方向_现券, entity.getPs()).getItemValue());
        processParameter.setWorkFlowParameter(params);
        String msg = fxSecdialogQuote002Service.start(processParameter, dto);
        return new AjaxResult(msg);

    }






}
