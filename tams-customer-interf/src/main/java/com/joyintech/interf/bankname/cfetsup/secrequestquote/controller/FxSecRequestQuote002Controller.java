package com.joyintech.interf.bankname.cfetsup.secrequestquote.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCode;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCodeList;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto.FxImixAccountInfo001Dto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto.FxTraderManagerDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteCounterDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.service.FxSecRequestQuoteService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.fop.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PageId("FxSecRequestQuote002")
@RequestMapping("/interf/cfetsup/secrequestquote/FxSecRequestQuote002")
public class FxSecRequestQuote002Controller extends BaseController {

    @Autowired
    private FxSecRequestQuoteService fxSecRequestQuoteService;

    @Override
    public String init(Model model) {
        //操作类型
        String operateType = this.getParam("operateType");
        //流水号
        String seqno = this.getParam("seqno");
        //流程id
        String workflowId = this.getParam("workflowId");
        ////业务类型
        //String businessType = this.getParam("businessType");
        FxSecRequestQuoteDto quote = new FxSecRequestQuoteDto();
        List<FxSecRequestQuoteCounterDto> counterList=new ArrayList<>();
        if (workflowId != null) {
            quote = fxSecRequestQuoteService.getBussinessEntityByWorkflowId(workflowId);
           // quote.setBusinessType(businessType);
            counterList = fxSecRequestQuoteService.findCounterDtoByPseqno(quote.getSeqno());
            model.addAttribute("counterList", JSONObject.toJSONString(counterList));
            quote.setOperateType("submit");
            //交易场所默认银行间 不可修改
            quote.setJycs("IB");
        } else {
            if (seqno != null && !"".equals(seqno)) {
                quote = fxSecRequestQuoteService.findRequestQuoteDtoBySeqno(seqno);
                //交易场所默认银行间 不可修改
                quote.setJycs("IB");
                counterList = fxSecRequestQuoteService.findCounterDtoByPseqno(seqno);
                model.addAttribute("counterList", JSONObject.toJSONString(counterList));
            }
            if (operateType != null) {
                //交易场所默认银行间 不可修改
                quote.setJycs("IB");
                if (operateType.equals("add")) {//新增
                    String trustid = this.getParam("trustid");
                    //数据通过委托查询
                    //FxSecRequestQuoteDto quoto=fxSecRequestQuoteService.setQuoteInfoFromTrust(trustid);
                    quote.setTrustid(trustid);
                    //初始化交易员
                    FxTraderManagerDto fxTraderManagerDto= fxSecRequestQuoteService.findHomeTrader(LoginUserUtil.getUserName());
                    if(fxTraderManagerDto!=null){
                        quote.setHometrader(fxTraderManagerDto.getCstptraderid());
                        quote.setHometradername(fxTraderManagerDto.getCstptrader());
                    }
                    quote.setOperateType("add");
                    quote.setBusinessType("add");
                } else if (operateType.equals("reply")) {//回复
                    quote.setOperateType("reply");
                    quote.setBusinessType("confirm");
                } else if (operateType.equals("copy")) {//复制
                    quote.setOperateType("copy");
                    quote.setBusinessType("add");
                } else if (operateType.equals("see")) {//查看
                    quote.setOperateType("see");
                }
            }
        }
        model.addAttribute("entity", quote);
        return super.init(model);
    }

    /**
     * 请求报价校验
     *
     * @param dto
     * @param result
     * @return
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) FxSecRequestQuoteDto dto, BindingResult result) {
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        if (result.getAllErrors().isEmpty() && StringUtils.isNoneEmpty(dto.getOperateType())) {
            AjaxResult result2 = new AjaxResult();
            String msg = "";
            if (StringUtil.equalsIgnoreCase("add", dto.getOperateType())) {
                msg = "请确认是否保存?";
            } else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, dto.getOperateType())) {
                msg = "请确认是否提交?";
            } else if (StringUtil.equalsIgnoreCase("revoke", dto.getOperateType())) {
                msg = "请确认是否撤销?";
            }else if(StringUtil.equalsIgnoreCase("confirm", dto.getOperateType())){
                msg = "请确认是否成交?";
            }
            result2.setData(this.getMessage(msg));
            return result2;
        } else {
            AjaxResult wr = commonCheck(result, dto.getOperateType());
            wr.setJoyinMessages(joyinMessages);
            return wr;
        }
    }

    /**
     * 保存
     *
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = POST)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) FxSecRequestQuoteDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (StringUtil.isEmpty(dto.getSeqno())) {
            dto.setSeqno(IDGenerator.generate());
        }
        //报价编号生成
        String quoteStr = "QTSECREQU";
        SimpleDateFormat simdate = new SimpleDateFormat("yyyyMMdd");
        String dateStr = simdate.format(new Date());
        String sequence = fxSecRequestQuoteService.findSequence();
        dto.setNbquoteid(quoteStr + dateStr + sequence);
        //流程参数设置
        ProcessParameter<FxSecRequestQuoteDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FxSecRequestQuoteDto> params = new WorkFlowParameter<>();
        params.setPageId("FxSecRequestQuote002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(FxDictCodeList.共通业务类型_现券买卖请求报价.getItemCode());
        params.setComment(entity -> "现券买卖请求报价新增，报价编号" + "(" + entity.getNbquoteid()
                + ")-交易类型 ：" + FxDictCodeList.from(FxDictCode.交易方向_现券, entity.getPs()).getItemValue());
        processParameter.setWorkFlowParameter(params);
        processParameter.setOperationType(OperationType.NEW);
        //启动流程
        fxSecRequestQuoteService.start(processParameter, dto);

        return new AjaxResult("操作成功");
    }

    /**
     * 成交
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/confirm", method = POST)
   public AjaxResult confirm(@RequestBody @Validated({
            ValidateGroupEdit.class}) FxSecRequestQuoteDto dto, BindingResult br)throws Exception{
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //流水号重新生产
        //设置原流水号
        dto.setPseqno(dto.getSeqno());
        dto.setSeqno(IDGenerator.generate());

        //流程参数设置
        ProcessParameter<FxSecRequestQuoteDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FxSecRequestQuoteDto> params = new WorkFlowParameter<>();
        params.setPageId("FxSecRequestQuote002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(FxDictCodeList.共通业务类型_现券买卖请求报价.getItemCode());
        params.setComment(entity -> "现券买卖请求报价成交，报价编号" + "(" + entity.getNbquoteid()
                + ")-交易类型 ：" + FxDictCodeList.from(FxDictCode.交易方向_现券, entity.getPs()).getItemValue());
        processParameter.setWorkFlowParameter(params);
        processParameter.setOperationType(OperationType.NEW);
        //启动流程
        fxSecRequestQuoteService.start(processParameter, dto);
        return new AjaxResult("操作成功");
   }
    /**
     * @return
     */
    @RequestMapping("getDataDict")
    @ResponseBody
    public List<SelectOptionDto> getDataDict() {
        Map<String, Object> map = RequestUtil.getPageParam();
        //QUOTETRADESTATUS SETTLESPEED SETTLETYPE EFFECTFALG SENDSTATUS JYFX JSFS HOMEROLE
        String key = this.getParam("key");
        map.put("key", key);
        return fxSecRequestQuoteService.getDataDict(map);
    }

    /**
     * @return
     */
    @RequestMapping("getProductid")
    @ResponseBody
    public List<SelectOptionDto> getProductid() {
        return fxSecRequestQuoteService.getProductid(RequestUtil.getPageParam());
    }

    /**
     * @return
     */
    @RequestMapping("getSeccode")
    @ResponseBody
    public List<SelectOptionDto> getSeccode() {
        return fxSecRequestQuoteService.getSeccode(RequestUtil.getPageParam());
    }
    @ResponseBody
    @RequestMapping(value = "/findHomeCstp",method = POST)
    public Map<String,Object> findHomeCstp(@RequestBody FxSecRequestQuoteDto dto){
        Map<String,Object> map=new HashMap<>();
        FxImixAccountInfo001Dto fxImixAccountInfo001Dto=fxSecRequestQuoteService.findHomeCstp(dto.getAccountid());
        if(fxImixAccountInfo001Dto!=null){
            map.put("result","true");
            //本币本方21位机构码
            map.put("jgdm",fxImixAccountInfo001Dto.getCfetsid());
            //本币本方名称
            map.put("jgmc",fxImixAccountInfo001Dto.getCfetsname());
        }else {
            map.put("result","false");
            map.put("msg","产品未关联外汇交易中心交易账户！");
        }
        return map;
    }


}
