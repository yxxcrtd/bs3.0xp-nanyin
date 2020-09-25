package com.joyintech.interf.bankname.cfetsup.secrepoquote.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCode;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCodeList;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.EtrRepoDto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote003Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.service.FxSecrepoQuote002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 新建质押式回购对话报价
 * @author zcf
 * @version 1.0
 * @date 2020-01-05
 */

@Controller
@PageId("FxSecrepoQuote002")
@RequestMapping("/interf/cfetsup/secrepoquote/FxSecrepoQuote002")
public class FxSecrepoQuote002Controller extends BaseController{

    @Autowired
    private FxSecrepoQuote002Service fxSecrepoQuote002Service;
    @Autowired
    private IPlatformDate iPlatformDate;

    @Override
    public String init(Model model) {
        //交易方向
        model.addAttribute(FxDictCode.交易方向.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.交易方向)));
        //交易场所
        model.addAttribute(DictCode.交易场所.getValue(), JsonUtils.object2Json(DictCodeListExt1.fromDictCode(DictCode.交易场所)));
        //计息基础
        model.addAttribute(DictCode.计息基础.getValue(),JsonUtils.object2Json(DictCodeList.fromDictCode(DictCode.计息基础)));
        //清算速度
        model.addAttribute(DictCode.清算速度.getValue(),JsonUtils.object2Json(DictCodeList.fromDictCode(DictCode.清算速度)));
        //结算方式
        model.addAttribute(FxDictCode.结算方式.getCode(),JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.结算方式)));
        //清算类型
        model.addAttribute(FxDictCode.清算类型.getCode(),JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.清算类型)));
        //币种
        model.addAttribute(DictCode.币种.getValue(),JsonUtils.object2Json(DictCodeList.fromDictCode(DictCode.币种)));
        //期限品种
        model.addAttribute(DictCode.期限品种_回购.getValue(),JsonUtils.object2Json(DictCodeList.fromDictCode(DictCode.期限品种_回购)));
        //本方角色
        model.addAttribute(FxDictCode.本方角色.getCode(),JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.本方角色)));
        String seqno = this.getParam("seqno");
        String quoteid = this.getParam("quoteid");
        String pageModel = this.getParam("pageModel");
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("ccy1",DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("TRANSACTION_CALENDAR",DictCodeList.交易日历_中国银行间交易日历.getValue());
        String entrustId = this.getParam("entrustId");
        String workflowId = this.getParam("workflowId");
        FxSecrepoQuote002Dto dto = new FxSecrepoQuote002Dto();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
            //审批
            dto = fxSecrepoQuote002Service.getBussinessEntityByWorkflowId(workflowId);
        }else if(StringUtil.isNotEmpty(entrustId)){
            //新增
            EtrRepoDto etrRepoDto = fxSecrepoQuote002Service.selectByEntrustId(entrustId);
            if(StringUtil.isNotEmpty(etrRepoDto)){
                model.addAttribute("TRANSACTION_CALENDAR",etrRepoDto.getCalendarId());
            }
            dto = fxSecrepoQuote002Service.getRepoQuoteInfo(etrRepoDto);
            dto.setOperateType2(pageModel);
        }else if(StringUtil.isNotEmpty(seqno) && StringUtil.isNotEmpty(quoteid)){
            //查看修改
            dto = fxSecrepoQuote002Service.selectSecRepoQuoteInfo(seqno);
            dto.setOperateType2(pageModel);
            if(pageModel.equalsIgnoreCase("3")){
                //回复报价
                model.addAttribute(FxDictCode.回复报价_操作类型.getCode(),JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.回复报价_操作类型)));
            }
            if(pageModel.equalsIgnoreCase("7")){
                //复制

            }
        }
        if(StringUtil.isNotEmpty(dto) && !StringUtil.isEmptyList(dto.getFinprodList())){
            model.addAttribute("finprodList",JSONObject.toJSONString(dto.getFinprodList()));
        }
        model.addAttribute("entity",dto);
        model.addAttribute("sysdate",iPlatformDate.getSysDateStr());
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("selectBondForPage")
    @ResponseBody
    public List<FxSecrepoQuote003Dto> selectForPage(){
        Map para = RequestUtil.getPageParam();
        List<FxSecrepoQuote003Dto> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(para.get("finprodList"))){
            String finprodList = para.get("finprodList").toString();
            list = JSONObject.parseArray(finprodList,FxSecrepoQuote003Dto.class);
        }
        return list;
    }

    /**
     * 质押式回购报价提交校验
     * @param dto
     * @param result
     * @return
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) FxSecrepoQuote002Dto dto, BindingResult result) {
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        if(result.getAllErrors().isEmpty() &&  StringUtils.isNoneEmpty(dto.getOperateType())) {
            AjaxResult result2 = new AjaxResult();
            String msg = "";
            if(StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,dto.getOperateType1())) {
                msg = "请确认是否保存?";
            }else if(StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,dto.getOperateType1())){
                msg = "请确认是否提交?";
            }else if(StringUtil.equalsIgnoreCase("revoke",dto.getOperateType1())){
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

    /**
     * 查询质押式回购交易信息
     * @return
     */
    @RequestMapping("queryTrdRepoTradeList")
    @ResponseBody
    public List<SelectOptionDto> queryTrdRepoTradeList(){
        Map<String, Object> map = RequestUtil.getPageParam();
        return null;
    }

    /**
     * 查询组合T日可以现金
     * @return
     */
    @RequestMapping("selectUsableCashByPortfolio")
    @ResponseBody
    public BigDecimal selectUsableCashByPortfolio(){
        return fxSecrepoQuote002Service.selectUsableCashByPortfolio(RequestUtil.getPageParam());
    }

    /**
     * 保存
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FxSecrepoQuote002Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        String seqno = IDGenerator.generate();
        if(StringUtil.isNotEmpty(dto.getSeqno())){
            dto.setPseqno(dto.getSeqno());
        }else{
            dto.setPseqno(seqno);
        }
        //重新生成seqno
        dto.setSeqno(seqno);
        ProcessParameter<FxSecrepoQuote002Dto> processParameter = new ProcessParameter<FxSecrepoQuote002Dto>();
        WorkFlowParameter<FxSecrepoQuote002Dto> params = new WorkFlowParameter<FxSecrepoQuote002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(FxDictCodeList.共通业务类型_质押式回购报价.getItemCode());
        params.setComment(entity -> "质押式回购报价，报价编号" + "(" + entity.getQuoteid()
                + ")-交易类型 ：" + FxDictCodeList.from(FxDictCode.交易方向, entity.getPs()).getItemValue());
        processParameter.setWorkFlowParameter(params);
        String msg = fxSecrepoQuote002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
