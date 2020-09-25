package com.joyintech.interf.bankname.cfetsup.secrepoquote.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCode;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCodeList;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote001Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.entity.FxSecrepoQuote;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.service.FxSecrepoQuote001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 质押式回购对话报价查询
 * @author zcf
 * @version 1.0
 * @date 2020-01-05
 */

@Controller
@PageId("FxSecrepoQuote001")
@RequestMapping("/interf/cfetsup/secrepoquote/FxSecrepoQuote001")
public class FxSecrepoQuote001Controller extends BaseController {

    @Autowired
    private FxSecrepoQuote001Service fxSecrepoQuote001Service;

    @Override
    public String init(Model model) {
        //交易方向
        model.addAttribute(FxDictCode.交易方向.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.交易方向)));
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("selectForPage")
    @ResponseBody
    public Page<FxSecrepoQuote001Dto> selectForPage(){
        return fxSecrepoQuote001Service.selectPageData(RequestUtil.getPageParam());
    }

    /**
     * 查询组合代码
     * @return
     */
    @RequestMapping("getPortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> getPortfolioId(){
        return fxSecrepoQuote001Service.getPortfolioId(RequestUtil.getPageParam());
    }

    /**
     * 查询组合名称
     * @return
     */
    @RequestMapping("getPortfolioName")
    @ResponseBody
    public Page<SelectOptionDto> getPortfolioName(){
        return fxSecrepoQuote001Service.getPortfolioName(RequestUtil.getPageParam());
    }

    /**
     * 查询交易员信息
     * @return
     */
    @RequestMapping("getMenInfo")
    @ResponseBody
    public Page<SelectOptionDto> getMenInfo(){
        return fxSecrepoQuote001Service.getMenInfo(RequestUtil.getPageParam());
    }

    /**
     * 查询质押式回购报价各字段信息
     * @return
     */
    @RequestMapping("getSecrepoQuoteInfo")
    @ResponseBody
    public List<SuggestDto> getSecrepoQuoteInfo(){
        return fxSecrepoQuote001Service.getSecrepoQuoteInfo(RequestUtil.getPageParam());
    }

    /**
     * 质押式回购报价删除校验
     * @param dto
     * @return
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check( FxSecrepoQuote001Dto dto) {
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        AjaxResult result = new AjaxResult();
        return result;
    }

    /**
     * 再次发送
     * @return
     */
    @RequestMapping("resend")
    @ResponseBody
    public AjaxResult resend() {
        AjaxResult result = new AjaxResult();
        Map para = RequestUtil.getPageParam();
        String data = (String) para.get("data");
        FxSecrepoQuote001Dto dto = JSONObject.parseObject(data, FxSecrepoQuote001Dto.class);
        String message = fxSecrepoQuote001Service.resend(dto);
        result.setMessage(message);
        return result;
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupEdit.class}) FxSecrepoQuote001Dto dto, BindingResult br)
            throws Exception {
        ProcessParameter<FxSecrepoQuote001Dto> processParameter = new ProcessParameter<FxSecrepoQuote001Dto>();
        WorkFlowParameter<FxSecrepoQuote001Dto> params = new WorkFlowParameter<FxSecrepoQuote001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(FxDictCodeList.共通业务类型_质押式回购报价.getItemCode());
        params.setComment(entity -> "删除质押式回购报价，报价编号" + "(" + entity.getQuoteid()
                + ")-交易类型 ：" + FxDictCodeList.from(FxDictCode.交易方向, entity.getPs()).getItemValue());
        processParameter.setWorkFlowParameter(params);
        String msg = fxSecrepoQuote001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
