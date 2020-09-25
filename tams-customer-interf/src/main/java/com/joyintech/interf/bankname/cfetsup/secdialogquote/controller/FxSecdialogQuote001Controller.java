package com.joyintech.interf.bankname.cfetsup.secdialogquote.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCode;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCodeList;
import com.joyintech.interf.bankname.cfetsup.outbound.CallReturn;
import com.joyintech.interf.bankname.cfetsup.outbound.service.ClientService;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dto.FxSecdialogQuote001Dto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.entity.FxSecdialogQuote;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.service.FxSecdialogQuote001Service;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.service.FxSecdialogQuote002Service;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@PageId("FxSecdialogQuote001")
@RequestMapping("/interf/cfetsup/secdialogquote/FxSecdialogQuote001")
public class FxSecdialogQuote001Controller extends BaseController {

    @Autowired
    private FxSecdialogQuote001Service fxSecdialogQuote001Service;
    @Autowired
    private FxSecdialogQuote002Service fxSecdialogQuote002Service;

    @Autowired
    private ClientService clientService;
    @Override
    public String init(Model model) {
        //交易方向
        model.addAttribute(FxDictCode.交易方向_现券.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.交易方向_现券)));
        //发送状态
        model.addAttribute(FxDictCode.发送状态.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.发送状态)));
        //交易状态
        model.addAttribute(FxDictCode.交易状态.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.交易状态)));

        return super.init(model);
    }

    @RequestMapping("send")
    @ResponseBody
    public AjaxResult send(){
        AjaxResult result = new AjaxResult();
        String seqno = this.getParam("seqno");
        FxSecdialogQuote dto = fxSecdialogQuote001Service.selectByPrimaryKey(seqno);
        //发送接口
        CallReturn callReturn = clientService.send(dto,dto.getBusinessType(),dto.getBusinessType()+"Quote");
        System.out.println(callReturn);
        return  new AjaxResult();
    }
    @RequestMapping("revoke")
    @ResponseBody
    public AjaxResult revoke(){
        AjaxResult result = new AjaxResult();
        String seqno = this.getParam("seqno");
        FxSecdialogQuote dto = fxSecdialogQuote001Service.selectByPrimaryKey(seqno);
        fxSecdialogQuote002Service.revoke(dto);
        return  new AjaxResult();
    }

    /**
     * 查询组合代码
     * @return
     */
    @RequestMapping("getPortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> getPortfolioId(){

        return fxSecdialogQuote001Service.getPortfolioId(RequestUtil.getPageParam());

    }

    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<FxSecdialogQuote001Dto> selectAll(){

        Page<FxSecdialogQuote001Dto> page = fxSecdialogQuote001Service.selectForPage(RequestUtil.getPageParam());
        return page;
    }

    /**
     *发送时检查
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkSend")
    public AjaxResult checkSend(@RequestBody @Validated({
            ValidateGroupDelete.class}) FxSecdialogQuote001Dto dto, BindingResult result) {

        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) FxSecdialogQuote001Dto dto, BindingResult result) {

        return commonCheck(result, dto.getOperateType());
    }

    /**
     *  页面删除
     * @param dto 费用品种
     * @param br br
     * @return 删除结果
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) FxSecdialogQuote001Dto dto, BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FxSecdialogQuote001Dto> processParameter = new ProcessParameter<FxSecdialogQuote001Dto>();
        WorkFlowParameter<FxSecdialogQuote001Dto> params = new WorkFlowParameter<FxSecdialogQuote001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(FxDictCodeList.共通业务类型_现券买卖对话报价.getItemCode());
        params.setComment(entity -> "删除现券买卖对话报价，报价编号" + "(" + entity.getQuoteid()
                + ")-交易方向 ：" + FxDictCodeList.from(FxDictCode.交易方向_现券, entity.getPs()).getItemValue());
        processParameter.setWorkFlowParameter(params);
        String msg = fxSecdialogQuote001Service.start(processParameter,dto);
        return new AjaxResult(msg);
    }





}
