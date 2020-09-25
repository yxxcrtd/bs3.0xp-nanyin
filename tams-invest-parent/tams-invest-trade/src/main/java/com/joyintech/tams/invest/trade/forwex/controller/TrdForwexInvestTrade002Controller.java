/*
 * 文件名：TrdForwexInvestTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.forwex.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.AuthorizationService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.common.controller.TrdCommonController;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.forwex.dto.TrdForwexInvestTradeDto;
import com.joyintech.tams.invest.trade.forwex.service.TrdForwexInvestTrade002Service;
import com.joyintech.workflow.engine.TaskAuthentication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.ProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇远期投资交易新建界面 〈功能详细描述〉
 * 
 * @author admin
 * @version 1.0 2019年7月2日
 * @see TrdForwexInvestTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdForwexInvestTrade002")
@RequestMapping("/invest/trade/forwex/TrdForwexInvestTrade002")
public class TrdForwexInvestTrade002Controller extends BaseController {

    @Autowired
    private TrdForwexInvestTrade002Service trdForwexInvestTrade002Service;

    @Autowired
    private TradeCommonService tradeCommonService;

    @Autowired
    private BaseGetBusinessId bussGetBussinessIdService;

    @Autowired
    private DateCalService dateCalService;

    @Autowired
    ProcessService processService;

    @Autowired(required = false)
    private AuthorizationService authorizationService;

    @Autowired(required = false)
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    @Autowired
    private TrdCommonController trdCommonController;

    /**
     * 初始化界面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 数据字典
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.外汇交易类型, DictCode.货币对, DictCode.交易方向, DictCode.交易日历,
                DictCode.外汇期限, DictCode.币种, DictCode.我方角色, DictCode.外汇交易模式, DictCode.结算方式,
                DictCode.参考汇率, DictCode.投资目的, DictCode.是否, DictCode.持有目的});
        model.addAttribute("SETTMODE_CENT_SETT", DictCodeListExt2.结算方式_集中净额清算.getValue());
        model.addAttribute("SETTMODE_BIL_SETT", DictCodeListExt2.结算方式_双边全额清算.getValue());
        model.addAttribute("SETTMODE_DIF_SETT", DictCodeListExt2.结算方式_差额清算.getValue());
        model.addAttribute("EXCDEALTYPE_FGN_CY", DictCodeListExt2.外汇交易类型_外币对.getValue());
        model.addAttribute("EXCDEALTYPE_RMB_FX", DictCodeListExt2.外汇交易类型_人民币外汇交易.getValue());
        model.addAttribute("DEALMODE_OTC", DictCodeListExt2.外汇交易模式_询价交易.getValue());
        model.addAttribute("DEALMODE_BANK_EXCH", DictCodeListExt2.外汇交易模式_本行结售汇.getValue());
        model.addAttribute("CURPAIR_JPY_CNY", DictCodeList.货币对_JPY_CNY.getValue());
        model.addAttribute("CCY_CNY", DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("CCY_USD", DictCodeList.币种_美元_USD.getValue());
        model.addAttribute("acctTypeA01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        model.addAttribute("acctTypeA05", DictCodeListExt1.资金账户类型_交易对手账户.getValue());
        model.addAttribute("ISDIFSQU_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("ISDIFSQU_N", DictCodeList.是否_否.getValue());
        model.addAttribute("PS_T07", DictCodeList.交易方向_到期.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        setSelectOptionToModelForDifPs(model);
        // ----add by jjpei 20190718--------交易监控风险试算-----------start--------
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F12", DictCodeListExt1.金融产品类型_投管用_远期.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        // ----add by jjpei 20190718--------交易监控风险试算-----------end----------
        TrdForwexInvestTradeDto dto = new TrdForwexInvestTradeDto();
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = this.getParam("entrustId");
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = trdForwexInvestTrade002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            // if (StringUtil.isBlank(dto.getTradeId())) {
            // // 根据工作流代码查询交易编号
            // dto.setTradeId(trdForwexInvestTrade002Service.selectTradeIdByWorkflowId(workflowId));
            // }

        }

        else if (StringUtil.isNotEmpty(tradeId)) {
            dto = trdForwexInvestTrade002Service.getForwexInvestTradeDto(tradeId);
            if (StringUtil.equals(DictCodeListExt2.结算方式_差额清算.getValue(), dto.getDeliveryType())
                && StringUtil.isNotEmpty(dto.getTradeId1())) {
                dto.setIsDifSquEx(DictCodeList.是否_是.getValue());
            }
        }
        else if (StringUtil.isNotEmpty(entrustId)) {
            dto = trdForwexInvestTrade002Service.getDealInfoByEntrustId(entrustId);
            if (StringUtil.isNotEmpty(entrustId)) {
                dto.setEntrustId(entrustId);
            }
        }

        model.addAttribute("entityList",
            JsonUtils.object2Json(dto.getBussTrdTransferOrder()));

        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
            model.addAttribute("businessId", tradeId);
        }
        else {
            model.addAttribute("tradeId", dto.getTradeId());
            model.addAttribute("businessId", dto.getTradeId());
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }

        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_远期.getValue());

        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
            dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                dto.getTrdInvestPersonInfo()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                JsonUtils.object2Json(dto.getBussTrdTransferOrder()));
        }
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        model.addAttribute("PINAN_ASSET_TYPE_F14",DictCodeListExt2.平安资产类型_外汇远期.getValue());
        return super.init(model);
    }

    private void setSelectOptionToModelForDifPs(Model model) {
    	List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
    	SelectOptionDto selectOptionDto = new SelectOptionDto();
    	selectOptionDto.setId(DictCodeList.交易方向_收取.getValue());
        // 文本
    	selectOptionDto.setText(DictCodeList.交易方向_收取.getCodeName());
        // 简称
    	selectOptionDto.setCol("DIF_PS");

    	options.add(selectOptionDto);
    	

    	SelectOptionDto selectOptionDto2 = new SelectOptionDto();
    	selectOptionDto2.setId(DictCodeList.交易方向_支付.getValue());
        // 文本
    	selectOptionDto2.setText(DictCodeList.交易方向_支付.getCodeName());
        // 简称
    	selectOptionDto2.setCol("DIF_PS");

    	options.add(selectOptionDto2);
    	
    	
        model.addAttribute("DIF_PS", JsonUtils.objectArray2Json(options.toArray()));
        
        
    }
    
    /**
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return trdForwexInvestTrade002Service.getProAvailableCash(param);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) TrdForwexInvestTradeDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        // modified by wangguoxian 修复YUCHUBS-98投资端-外汇远掉期委托及交易，买入外汇时判断持仓提示错误 20190809 start
        // 货币对
        String curPair = dto.getCurPair();
        // 交易货币
        String dealCur = dto.getCcy();
        // 基准货币金额
        BigDecimal baseCurAmt = null;
        // 基准货币可用持仓
        BigDecimal baseCurPos = null;
        // 非基准货币金额
        BigDecimal noBaseCurAmt = null;
        // 非基准货币可用持仓
        BigDecimal noBaseCurPos = null;

        if (StringUtil.isNotEmpty(curPair) && curPair.indexOf(dealCur) == 0){
            // 交易货币是基准货币
            baseCurAmt = dto.getPrinAmt();
            baseCurPos = dto.getProAvailableCash();
            // 对应货币是非基准货币
            noBaseCurAmt = dto.getTradeAmt();
            noBaseCurPos = dto.getCorCurPos();

        }
        else if (StringUtil.isNotEmpty(curPair) && curPair.indexOf(dealCur) > 0){
            // 交易货币是非基准货币
            noBaseCurAmt = dto.getPrinAmt();
            noBaseCurPos = dto.getProAvailableCash();
            // 对应货币是基准货币
            baseCurAmt = dto.getFpriceAmt();
            baseCurPos = dto.getCorCurPos();
        }

        // 当交易方向为买入时，非基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
            if (noBaseCurAmt != null && noBaseCurPos != null && noBaseCurAmt.compareTo(noBaseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                        this.getMessage("TRD_SPOTEX_002", this.getMessage("TrdForwexInvestTrade002.t01"),
                                this.getMessage("TrdForwexInvestTrade002.noBaseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }

        // 当交易方向为卖出时，基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            if (baseCurAmt != null && baseCurPos != null && baseCurAmt.compareTo(baseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                        this.getMessage("TRD_SPOTEX_002", this.getMessage("TrdForwexInvestTrade002.t02"),
                                this.getMessage("TrdForwexInvestTrade002.baseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }

        /*if (StringUtil.isNotEmpty(dto.getCurPair())) {
            AjaxResult ar = new AjaxResult();
            // 根据交易方向，货币对，交易币种，对应币种 校验可用持仓
            String[] curArr = dto.getCurPair().split("/");
            String cur1 = "";
            // 交易方向为买入时
            if (DictCodeList.交易方向_买入.getValue().equals(dto.getPs())) {
                // 非基准货币（货币对后一个币种）金额不大于可用持仓
                cur1 = curArr[1];
            }
            // 交易方向为卖出时
            if (DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())) {
                // 基准货币（货币对前一个币种）金额不大于可用持仓
                cur1 = curArr[0];
            }
            // 交易币种 需要判断交易
            if (cur1.equals(dto.getCcy())) {
                // 可用持仓 小于金额
                if (dto.getProAvailableCash() != null && dto.getPrinAmt() != null
                    && dto.getProAvailableCash().compareTo(dto.getPrinAmt()) < 0) {
                    ar.setData(
                        getMessage("ETR_FORWEX_003", getMessage("TrdForwexInvestTrade.prinAmt"),
                            getMessage("TrdForwexInvestTrade.proAvailableCash")));
                    ar.setMessageType(MessageType.确认消息);
                    return ar;
                } ;
            }

            // 对应币种
            if (cur1.equals(dto.getCorCcy())) {
                // 可用持仓 小于金额
                if (dto.getCorCurPos() != null && dto.getFpriceAmt() != null
                    && dto.getCorCurPos().compareTo(dto.getFpriceAmt()) < 0) {
                    ar.setData(
                        getMessage("ETR_FORWEX_003", getMessage("TrdForwexInvestTrade.fpriceAmt"),
                            getMessage("TrdForwexInvestTrade.corCurPos")));
                    ar.setMessageType(MessageType.确认消息);
                    return ar;
                } ;
            }
        }*/

        // modified by wangguoxian 修复YUCHUBS-98投资端-外汇远掉期委托及交易，买入外汇时判断持仓提示错误 20190809 end

        // huwenbin 指令接收交易录单验证数据已存在流程中
        /*if (StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()) {
			
			  String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
			  if (StringUtil.isNotEmpty(str)) { 
				  AjaxResult res = new AjaxResult();
				  res.setMessage(str, MessageType.错误消息); 
				  return res; 
			  }
			 
        	// 查询交易流程中录入的总金额	王贵奇	2019/9/3
        	BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
            BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getPortfolioId());
            BigDecimal finAmt =StringUtil.isNotEmpty(dto.getPrinAmt())?dto.getPrinAmt():StringUtil.isNotEmpty(dto.getShareAmt())?dto.getShareAmt():dto.getTradeAmt();
            if(entrustNum.compareTo(wflPrinAmt.add(finAmt)) == -1) {
            	AjaxResult res= new AjaxResult();
            	 List<ValidMessage> errorList = new ArrayList<>();
            	 ValidMessage validMessage = new ValidMessage("prinAmt",
            			 "可用委托金额不足,当前最多可委托金额为:"+ entrustNum.subtract(wflPrinAmt));
                     errorList.add(validMessage);
                res.setErrorList(errorList);
                res.setMessageType(MessageType.错误消息);
                return res;
            }
        }*/
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) TrdForwexInvestTradeDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        
        
        ProcessParameter<TrdForwexInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdForwexInvestTradeDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam("elementId"));
        if (StringUtil.isEmpty(dto.getTradeId())) {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(
                DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇远期投资交易));
            String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                param) : IDGenerator.generate();
            dto.setTradeId(code);
        }
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(), DictCodeListExt2.平安资产类型_外汇远期.getValue(),
                    (en)->interfRiskRdsService.sendRiskRdsData(dto,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_外汇远期投资交易.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("交易编号：" + dto.getTradeId(),
            "货币对：" + dto.getCurPair(), "交易日：" + DateUtil.format(entity.getTradeDate())));
        processParameter.setWorkFlowParameter(params);
        String msg = trdForwexInvestTrade002Service.start(processParameter, dto);
        
        //授信占用额度
//        trdCommonController.delLinesDeal(dto.getRiskCreditListCommonDto(),dto.getTradeId());
        return new AjaxResult(msg);
    }

    /**
     * 自动保存（批处理用）
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @PostMapping("autosave")
    public String autosave(@RequestBody TrdForwexInvestTradeDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return br.toString();
        }
        ProcessParameter<TrdForwexInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdForwexInvestTradeDto> params = new WorkFlowParameter<>();
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessExtType(
            DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇远期投资交易));
        params.setBusinessCode(dto.getTrdProductDeal().getTradeId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_外汇远期投资交易.getValue());
        processParameter.setPageId("TrdForwexInvestTrade002");
        processParameter.setOperationType(OperationType.NEW);
        params.setPageId("TrdForwexInvestTrade002");
        params.setElementId("TrdForwexInvestTrade002_btn_save");
        params.setServiceName("trdForwexInvestTrade002Service");
        
        // 重新封装实体类数据
        trdForwexInvestTrade002Service.convert2TrdForwexInvestTradeDto(dto);
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> WorkFlowUtil.concatComment("交易编号：" + entity.getTrdProductDeal().getTradeId(),
                "货币对：" + entity.getTrdProductDealAdd().getCurPair(),
                "交易日：" + DateUtil.format(entity.getTrdProductDeal().getTradeDate())));
        processParameter.setWorkFlowParameter(params);
        try {
            String userStr = dto.getTrdProductDeal().getTrader();
            // 分布式事务报错 modify by renping 20191106 start
            TaskAuthentication.setCandidateUser(Arrays.asList(userStr));
            dto.setWorkflowParameter("new|||0|TrdForwexInvestTrade002|TrdForwexInvestTrade002_btn_save|true");
            User user = authorizationService.getUser(userStr);
            // 将用户信息保存到session中
            SecurityUtil.addUserToSession(user);
            trdForwexInvestTrade002Service.start(processParameter, dto);
//            WorkFlowUtil.startProcess(params, userStr, "", userStr, OperationType.NEW, false, dto);
            // 分布式事务报错 modify by renping 20191106 end
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "1";
    }
    /*添加交割日与期限联动规则 gaopeng 2019/10/08 start*/
    /**
     * 推算交割日
     * @return 交割日
     */
    @RequestMapping("getsettleDate")
    @ResponseBody
    public Map<String,Object> getsettleDate(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return trdForwexInvestTrade002Service.getsettleDate(map);
    }
    /*添加交割日与期限联动规则 gaopeng 2019/10/08 end*/
    /**
     * 定价日自动带出为交割日前2个工作日 <功能详细描述>
     * 
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBeforeTwoWorkDay")
    public Map<String, Object> getBeforeTwoWorkDay(@RequestBody Map<String, Object> param) {

        String settleDate = (String)param.get("settleDate");

        String calendarId = (String)param.get("calendarId");

        // 交易日历取得
        List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(
            DateUtil.addDay(DateUtil.parse(settleDate), -30), DateUtil.parse(settleDate),
            calendarId);
        // T+2日调整后

        String pricedDate = settleDate;
        try {
            pricedDate = dateCalService.calculationTradingDayForBefore(settleDate, 2,
                dealCalendarHolidayList, "1");
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //
        param.put("pricedDate", pricedDate);
        return param;
    }
    
    /**
     * 划款指令生成实体类
     * 
     * @param dto 外汇远期投资交易实体类
     * @return 金融产品交易实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntity")
    public List<TrdProductDeal> getEntity() {
        Map<String, Object> map = RequestUtil.getPageParam();
        TrdForwexInvestTradeDto dto = BeanUtil.mapToObject(map,
        		TrdForwexInvestTradeDto.class);
        TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto = new TrdInvestPersonInfo002Dto();
        dto.setTrdInvestPersonInfo(trdInvestPersonInfo002Dto);
        dto.getTrdInvestPersonInfo().setContactInfo(
            map.get("trdInvestPersonInfo[contactInfo]")==null?"":map.get("trdInvestPersonInfo[contactInfo]").toString());
        dto.getTrdInvestPersonInfo().setCounterId(
            map.get("trdInvestPersonInfo[counterId]")==null?"":map.get("trdInvestPersonInfo[counterId]").toString());
        dto.getTrdInvestPersonInfo().setCounterTrader(
            map.get("trdInvestPersonInfo[counterTrader]")==null?"":map.get("trdInvestPersonInfo[counterTrader]").toString());
        dto.getTrdInvestPersonInfo().setIsOutTrade(
            map.get("trdInvestPersonInfo[isOutTrade]")==null?"":map.get("trdInvestPersonInfo[isOutTrade]").toString());
        dto.getTrdInvestPersonInfo().setTrader(
            map.get("trdInvestPersonInfo[trader]")==null?"":map.get("trdInvestPersonInfo[trader]").toString());
        return trdForwexInvestTrade002Service.getEntity(dto);
    }

    
}
