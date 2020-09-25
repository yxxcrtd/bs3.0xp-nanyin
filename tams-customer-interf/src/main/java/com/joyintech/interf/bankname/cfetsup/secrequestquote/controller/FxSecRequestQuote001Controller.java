package com.joyintech.interf.bankname.cfetsup.secrequestquote.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.outbound.CallReturn;
import com.joyintech.interf.bankname.cfetsup.outbound.service.ClientService;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.service.FxSecRequestQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@PageId("FxSecRequestQuote001")
@RequestMapping("/interf/cfetsup/secrequestquote/FxSecRequestQuote001")
public class FxSecRequestQuote001Controller extends BaseController {
    @Autowired
    private FxSecRequestQuoteService fxSecRequestQuoteService;
    @Autowired
    private ClientService clientService;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<FxSecRequestQuoteDto> findPage() throws Exception {
        Page<FxSecRequestQuoteDto> pageInfo = fxSecRequestQuoteService.findPage(RequestUtil.getPageParam());
        return pageInfo;
    }

    /**
     * 撤回报价
     *
     * @return
     */
    @RequestMapping("revoke")
    @ResponseBody
    public AjaxResult revoke() {
        AjaxResult result = new AjaxResult();
        String errorMsg = "";
        //校验当前登录人是否是该报价的指令接收人 创建人
        String seqnos = this.getParam("seqnos");
        String operateType = this.getParam("operateType");
        if (seqnos != null && !"".equals(seqnos)) {
            String[] seqnoArr = seqnos.split(",");
            for (int i = 0; i < seqnoArr.length; i++) {
                FxSecRequestQuoteDto dto = fxSecRequestQuoteService.findRequestQuoteDtoBySeqno(seqnoArr[i]);
                if (dto.getCreatorid() != null) {
                    if (!LoginUserUtil.getUserName().equals(dto.getCreatorid())) {
                        errorMsg = dto.getQuoteid() + ",";
                    } else {
                        dto.setBusinessType("revoke");
                        //调用接口撤回报价
                        CallReturn callReturn = clientService.reSend(dto, 1, dto.getBusinessType(), "revokeQuote");
                        if (callReturn.isSuccess()) {
                            //发送中
                            dto.setSendstatus("sending");
                            dto.setTradestatus("revoking");
                            result.setStatuCode(200);
                            result.setMessage("撤销请求报价发送中...");
                        } else {
                            dto.setSendstatus("failed");
                            //更新错误信息
                            dto.setErrormsg(callReturn.getMessage());
                            result.setStatuCode(9999);
                            result.setMessage(callReturn.getMessage());
                        }
                        fxSecRequestQuoteService.updateStatus(dto);
                    }
                } else {
                    errorMsg = dto.getQuoteid() + ",";
                }
            }
        } else {
            result.setStatuCode(9999);
            result.setMessage("参数传入空值，无法撤回！");
        }
        if ("".equals(errorMsg)) {
            result.setStatuCode(200);
            result.setMessage("撤回报价已发送接口！");
        } else {
            result.setStatuCode(9999);
            result.setMessage("报价" + errorMsg + "无法撤回，请检查报价创建人与当前登录人是否一致！");
        }
        return result;
    }

    /**
     * 报价再次发送
     * @return
     */
    @RequestMapping("resend")
    @ResponseBody
    public AjaxResult resend() {
        AjaxResult result = new AjaxResult();
        String seqno = this.getParam("seqno");
        FxSecRequestQuoteDto dto = fxSecRequestQuoteService.findRequestQuoteDtoBySeqno(seqno);
        if (dto != null) {
            if (!LoginUserUtil.getUserName().equals(dto.getCreatorid())) {
                result.setStatuCode(9999);
                result.setMessage("当前登录人与报价的创建人不一致！无法操作");
            } else {
                CallReturn callReturn = null;
                //调用接口发送
                if (dto.getBusinessType().equals("add")) {//新增报价
                    callReturn = clientService.reSend(dto, 1, dto.getBusinessType(), "addQuote");
                    if (callReturn.isSuccess()) {
                        dto.setTradestatus("adding");
                        dto.setSendstatus("sending");
                        result.setStatuCode(200);
                        result.setMessage("新增请求报价发送中...");
                    } else {
                        dto.setSendstatus("failed");
                        dto.setErrormsg(callReturn.getMessage());
                        result.setStatuCode(9999);
                        result.setMessage(callReturn.getMessage());
                    }
                } else if (dto.getBusinessType().equals("revoke")) {//撤回报价
                    callReturn = clientService.reSend(dto, 1, dto.getBusinessType(), "revokeQuote");
                    if (callReturn.isSuccess()) {
                        dto.setTradestatus("revoking");
                        dto.setSendstatus("sending");
                        result.setStatuCode(200);
                        result.setMessage("撤回请求报价发送中...");
                    } else {
                        dto.setSendstatus("failed");
                        dto.setErrormsg(callReturn.getMessage());
                        result.setStatuCode(9999);
                        result.setMessage(callReturn.getMessage());
                    }
                } else if (dto.getBusinessType().equals("confirm")) {//确认成交报价
                    callReturn = clientService.reSend(dto, 1, dto.getBusinessType(), "confirmQuote");
                    if (callReturn.isSuccess()) {
                        dto.setSendstatus("sending");
                        dto.setTradestatus("dealing");
                        result.setStatuCode(200);
                        dto.setErrormsg(callReturn.getMessage());
                        result.setMessage("确认成交报价发送中...");
                    } else {
                        dto.setSendstatus("failed");
                        result.setStatuCode(9999);
                        dto.setErrormsg(callReturn.getMessage());
                        result.setMessage(callReturn.getMessage());
                    }
                } else {
                    result.setStatuCode(9999);
                    result.setMessage("不存在的业务操作类型，请确认业务操作类型!");
                }
                fxSecRequestQuoteService.updateStatus(dto);
            }
        }
        return result;
    }

    /**
     * 报价撤销
     * @return
     */
    @RequestMapping("cancel")
    @ResponseBody
    public AjaxResult cancel(){
        AjaxResult result = new AjaxResult();
        String seqno = this.getParam("seqno");
        FxSecRequestQuoteDto dto = fxSecRequestQuoteService.findRequestQuoteDtoBySeqno(seqno);
        dto.setProcstatus("已撤销");
        dto.setEffectflag("D");
        dto.setLastmodifierid(LoginUserUtil.getUserName());
        fxSecRequestQuoteService.updateProcstatus(dto);
        result.setMessage("撤销成功");
        result.setStatuCode(200);
        return result;
    }

}
