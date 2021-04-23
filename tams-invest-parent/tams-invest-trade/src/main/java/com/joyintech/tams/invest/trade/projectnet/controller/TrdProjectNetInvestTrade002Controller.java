package com.joyintech.tams.invest.trade.projectnet.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.projectnet.dto.TrdProjectNetInvestTradeDto;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageId("TrdProjectNetInvestTrade002")
@Controller
@RequestMapping("/invest/trade/projectnet/TrdProjectNetInvestTrade002")
public class TrdProjectNetInvestTrade002Controller extends BaseController {
    @Autowired
    private TradeCommonService tradeCommonService;


    @Autowired
    private TrdProjectNetInvestTrade002Service trdProjectNetInvestTrade002Service;

    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * 划款指令业务组件service
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;

    @Autowired(required = false)
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        /*添加资产二级-四级分类的数据字典值 zhangpuyu 2019/7/31 start*/
        setSelectOptionToModel(model, new DictCode[] {DictCode.计划类资产一级分类,DictCode.计划类资产二级分类,
                DictCode.交易方向, DictCode.清算速度, DictCode.币种,DictCode.行内资产二级分类, DictCode.行内资产三级分类,
                DictCode.行内资产四级分类});
        /*添加资产二级-四级分类的数据字典值 zhangpuyu 2019/7/31 end*/
        model.addAttribute("ps_T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("ps_T06", DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("ps_mr", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("ps_mc", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("plansType01", DictCodeList.计划类资产二级分类_公募计划.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());

        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));

        TrdProjectNetInvestTradeDto dto = new TrdProjectNetInvestTradeDto();
        String tradeId = this.getParam("tradeId");
        String status = this.getParam("validStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        model.addAttribute("TodoList", !WorkFlowUtil.isNotFromToDoList());
        if (StringUtil.isNotEmpty(tradeId)) {
            // 双击查看反显流程中的数据
            if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_流程中.getValue())) {
                dto = trdProjectNetInvestTrade002Service.getBussinessEntityByWorkflowId(workflowId);
                if (dto.getBussTrdTransferOrder() != null
                        && dto.getBussTrdTransferOrder().size() > 0) {
                    BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                    model.addAttribute("entity_bussRepFinProductInfoDto",
                            JsonUtils.object2Json(bttoDto));
                }
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", workflowId);
            }
            // 双击查看反显非流程中的数据
            else {
                if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_撤销.getValue())) {
                    tradeId = this.getParam("pTradeId");
                }
                dto = trdProjectNetInvestTrade002Service.getTrdFundDto(tradeId);
                /*if (dto.getSettleSpeed() != null){
                    if (dto.getSettleSpeed() == 0) {
                        dto.setSettleSpreed(DictCodeList.清算速度_T_0.getValue());
                    }
                                dto.setSettleSpreed(DictCodeList.清算速度_T_1.getValue());
                    }else if(dto.getSettleSpeed() == 2){
                        dto.setSettleSpreed(DictCodeList.清算速度_T_2.getValue());
                    }
                }*/
                model.addAttribute("tradeId", dto.getTradeId());
                model.addAttribute("entity_trdInvestPersonInfo",
                        JsonUtils.object2Json(dto.getTrdInvestPersonInfo()));
                model.addAttribute("businessId", dto.getTradeId());
            }
        }
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = trdProjectNetInvestTrade002Service.getBussinessEntityByWorkflowId(workflowId);
            if (dto.getBussTrdTransferOrder() != null
                    && dto.getBussTrdTransferOrder().size() > 0) {
                BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                model.addAttribute("entity_bussRepFinProductInfoDto",
                        JsonUtils.object2Json(bttoDto));
            }
            TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
//            for(RiskTrialRetDto dt : dto.getRiskTrialRetDto()){
                /*dt.setFoulCond(">1%");
                dt.setWarnCond(">1%");*/
//            }
            // add by yushilong 交易编号未传参导致划款信息为空 20200724 start
            model.addAttribute("tradeId", dto.getTradeId());
            // add by yushilong 交易编号未传参导致划款信息为空 20200724 end
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
            model.addAttribute("businessId", workflowId);
        }
        else {
            String entrustId = this.getParam("entrustId");
            if (StringUtil.isNotEmpty(entrustId)) {
                BaseEtrInvestInfoDto entrustDto = trdProjectNetInvestTrade002Service.getEtrustDto(
                        entrustId);
                if (entrustDto != null) {
                    EtrEntrust eedto = entrustDto.getEtrEntrust();
                    EtrEntrustAdd eeAdto = entrustDto.getEtrEntrustAdd();
                    if (eedto != null) {
                        dto.setFinprodId(eedto.getFinprodId());
                        dto.setPortfolioId(eedto.getPortfolioId());
                        dto.setPs(eedto.getPs());
                        dto.setPrinAmt(eedto.getEntrustAmt());
                        dto.setTradeAmt(eedto.getEntrustAmt());
                        dto.setShareAmt(eedto.getEntrustNum());
                        // add by zhangrui1 缺少市场代码 start
                        dto.setFinprodMarketId(entrustDto.getFinprodMarketId());
                        // add by zhangrui1 缺少市场代码 end
                        TrdInvestPersonInfo002Dto trdInvestPersonInfo = new TrdInvestPersonInfo002Dto();
                        trdInvestPersonInfo.setCounterTrader(eedto.getCounterTrader());
                        trdInvestPersonInfo.setIsOutTrade(eedto.getIsOutTrade());
                        trdInvestPersonInfo.setCounterId(eedto.getCounterId());
                        trdInvestPersonInfo.setContactInfo(eedto.getContactInfo());
                        if (entrustDto.getEtrDistributeList() != null
                                && entrustDto.getEtrDistributeList().size() > 0) {
                            trdInvestPersonInfo.setTrader(
                                    entrustDto.getEtrDistributeList().get(0).getTrader());
                        }
                        model.addAttribute("entity_trdInvestPersonInfo",
                                JsonUtils.object2Json(trdInvestPersonInfo));
                    }
                    if (eeAdto!=null){
                        dto.setType9(eeAdto.getAssetType1());
                        dto.setType10(eeAdto.getAssetType10());
                        dto.setType11(eeAdto.getAssetType11());
                    }
                }
                dto.setEntrustId(entrustId);
                model.addAttribute("businessId", entrustId);
            }
        }
        dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        dto.setProfitType(DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("FINPROD_TYPE_F17", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        model.addAttribute("entity", dto);
        //---add by 裴家俊---2019/04/04-----
        model.addAttribute("workflowId", workflowId);
        //---add by 裴家俊---2019/04/04-----
        //sjj add 20190422 计划类净值型申购的清算速度默认T+1 start
        model.addAttribute("settleSpreed_T1", DictCodeList.清算速度_T_1.getValue());
        model.addAttribute("settleSpreed_T2", DictCodeListExt2.清算速度_T_2.getValue());
        //sjj add 20190422 计划类净值型申购的清算速度默认T+1 end
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        model.addAttribute("CUSTOM_ASSET_TYPE_TWO_P03", "P03");
        return super.init(model);
    }

    /**
     * 资产代码联想下拉框 <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId")
    @ResponseBody
    Page<SelectOptionDto> getFinprodId() {
        return trdProjectNetInvestTrade002Service.getFinprodId(RequestUtil.getPageParam());
    }

    /**
     * 保存按钮点击时的check
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
            ValidateGroupEdit.class}) TrdProjectNetInvestTradeDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ar = commonCheck(result, dto.getOperateType());
        if (dto.getTradeAmt() != null && dto.getProAvailableCash() != null) {
            if (dto.getTradeAmt().compareTo(dto.getProAvailableCash()) == 1) {
                ar.setData("交易金额大于组合可用金额（可透支）," + ar.getData());
            }
        }
        // 授信占用check
//        AjaxResult ajaxResult = AllTradeCommonUtil.checkBeforeTradeComplete(dto);
        AjaxResult ajaxResult = null;
        List<JoyinMessage> joyinMessages = new ArrayList<JoyinMessage>();
        ar.setJoyinMessages(joyinMessages);
        if (ajaxResult != null && StringUtil.isNotEmpty(ajaxResult.getData())){
            joyinMessages.add(new JoyinMessage(ajaxResult.getData().toString(), MessageType.警告消息));
        }
        //huwenbin 指令接收交易录单验证数据已存在流程中
      /*  if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
    		String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
    		if(StringUtil.isNotEmpty(str)){
    			AjaxResult res= new AjaxResult();
				res.setMessage(str, MessageType.错误消息);
    			return res;
    		}
        	// 查询交易流程中录入的总金额	王贵奇	2019/9/3
        	BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
            BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getPortfolioId());
            if(entrustNum.compareTo(wflPrinAmt.add(dto.getFpriceAmt())) == -1) {
            	AjaxResult res= new AjaxResult();
                res.setMessage("可用委托金额不足："+ entrustNum.subtract(wflPrinAmt), MessageType.错误消息);
                return res;
            }
    	}*/
//        if (StringUtil.isNotEmpty(dto.getCalendarId()) && dto.getTradeDate() != null) {
//            boolean flg = dateCalService.isDealCalendarHoliday(dto.getTradeDate(),
//                dto.getCalendarId());
//            if (flg == true) {
//                ar.setData("交易日期为节假日," + (String)ar.getData());
//            }
//        }
        //外部交易对手买入，校验持仓+本金 < 融资金额或者发行金额
        if ((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) ||
                StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue()) ) &&
                StringUtil.equals(dto.getTrdInvestPersonInfo().getIsOutTrade(), "S")){
            // 调用基础组件.【组合可用现金和实际现金查询更新组件.查询持仓(可用)】
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("portfolioId", dto.getPortfolioId());
            paramMap.put("tradeDate", DateUtil.format(dto.getTradeDate()));
            paramMap.put("ccy", dto.getCcy());
            paramMap.put("calendarId", dto.getCalendarId());
            paramMap.put("chlId", dto.getChlId());
            paramMap.put("finprodId", dto.getFinprodId());
            //获得资产维度的持仓
            Map<String, Object>  resultMap = bussTrdAvailableCashQueryService.getFinAvaPosition(paramMap);

            BigDecimal position = new BigDecimal((String) resultMap.get("POSITION"));
            //融资金额
            BigDecimal issueAmt = new BigDecimal((String)resultMap.get("ISSUEAMT"));
            position = ArithmeticUtil.doAdd(position, dto.getTradeAmt());
            //add by cw 统计流程中的金额 begin
            BigDecimal wflPrinAmt = tradeCommonService.queryWflPrinAmtByAssetId(dto.getFinprodId());
            position = ArithmeticUtil.doAdd(position, wflPrinAmt);

            //add by cw 统计流程中的金额 end

            //资产 融资金额、发行金额
            if (issueAmt.compareTo(position) < 0) {
                ar.setData(getMessage("TRD_C_0006") + (String)ar.getData());
            }
        }
        return ar;
    }

    /**
     * 新建/修改流程 <功能详细描述>
     *
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(@RequestBody TrdProjectNetInvestTradeDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_权益类资产投资交易);
            dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            dto.setTradeId(IDGenerator.generate());
        }
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),DictCodeListExt3.平安资产类型_权益类资产.getValue(),
                    (en)->interfRiskRdsService.sendRiskRdsData(dto,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
//            // add by zhangrui1 start
//            // 将实占信息设置到线程缓存出错时撤销用
//            TradeRdsCommonAop.setLocalCache(dto);
//            // add by zhangrui1 end
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        ProcessParameter<TrdProjectNetInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdProjectNetInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
        workFlowParameter.setComment(
                (entity) -> WorkFlowUtil.concatComment(getMessage("TrdPlanNetInvestTrade002.tradeId"),
                        entity.getTradeId(),
                        getMessage("TrdPlanNetInvestTrade002.finprodName"),
                        entity.getFinprodName(),
                        getMessage("TrdPlanNetInvestTrade002.tradeDate"),
                        DateUtil.format(entity.getTradeDate())));
        String portfolioId = dto.getPortfolioId();
        prepareEntity(dto);
        dto.setPortfolioId(portfolioId);
        String msg = trdProjectNetInvestTrade002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 处理基金投资交易自定义dto <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    public void prepareEntity(TrdProjectNetInvestTradeDto dto) {
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));
//        dto.setTradeAmt(dto.getPrinAmt());
        dto.setPrinAmt(dto.getTradeAmt());
        dto.setYield(com.joyintech.calculation.util.ArithmeticUtil.divide(dto.getYield(), new BigDecimal(100)));
        dto.setExerYield(com.joyintech.calculation.util.ArithmeticUtil.divide(dto.getExerYield(), new BigDecimal(100)));
        dto.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        dto.setIsCancel(DictCodeList.是否_否.getValue());
        dto.setBusiType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
        dto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        /*if (StringUtil.equals(dto.getSettleSpreed(), DictCodeList.清算速度_T_0.getValue())) {
            dto.setSettleSpeed((long)0);
        }
        else if (StringUtil.equals(dto.getSettleSpreed(),
            DictCodeList.清算速度_T_1.getValue())) {
            dto.setSettleSpeed((long)1);
        }else if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_2.getValue())) {
            dto.setSettleSpeed((long)2);
        }*/
        dto.setSettleDate(dto.getTradeDate());
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
            dto.setIsClean(DictCodeList.是否_是.getValue());
            dto.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                BigDecimal zero = new BigDecimal(0);
                BigDecimal redemptionFee = dto.getRedemptionFee();
                if (redemptionFee == null) {
                    redemptionFee = zero;
                }
                BigDecimal channelFee = dto.getChannelFee();
                if (channelFee == null) {
                    channelFee = zero;
                }
                BigDecimal hostingFee = dto.getHostingFee();
                if (hostingFee == null) {
                    hostingFee = zero;
                }
                dto.setFeeAmtPay(redemptionFee.add(channelFee.add(hostingFee)));
                if (dto.getFeeAmtPay().equals(zero)) {
                    dto.setFeeAmtPay(null);
                }
            }
            dto.setFeeAmt(dto.getFeeAmtPay());
        }
        TrdInvestPersonInfo002Dto personInfo = dto.getTrdInvestPersonInfo();
        if (personInfo != null) {
            dto.setTrader(personInfo.getTrader());
            dto.setCounterId(personInfo.getCounterId());
            dto.setIsOutTrade(personInfo.getIsOutTrade());
            dto.setCounterTrader(personInfo.getCounterTrader());
            dto.setCounterContact(personInfo.getContactInfo());
        }
        // 金融产品交易附属表
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();

        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                ||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
            // 申购申请、赎回申请，将确认日期保存到 预计申购确认日 字段
            trdProductDealAdd.setPurCfmDate(dto.getPurCfmDate());
            /*Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodId", dto.getFinprodId());
            Map<String, Object> result = trdPlanNetInvestTrade003Service.getPurSpeed(param);
            String speed = (String)result.get("SPEED");
            String calId = (String)result.get("CALID");
            if (StringUtil.equals(speed, "1") || StringUtil.equals(speed, "2")) {
                int speedCount = Integer.parseInt(speed);
                Date tradeDate = dto.getTradeDate();
                List<String> holidayList = dateCalService.getDealCalendarHoliday(
                    DateUtil.parse(DateUtil.format(tradeDate)),
                    DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15), calId);
                Date tradeDay = dateCalService.calculationTradingDay(
                    DateUtil.parse(DateUtil.format(tradeDate)), speedCount, holidayList, "1");
                trdProductDealAdd.setPurCfmDate(tradeDay);
            }*/
        }
        else {
            trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
        }
        trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
        trdProductDealAdd.setTradeId(dto.getTradeId());
        if (!StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
            trdProductDealAdd.setPortfolioId(dto.getActPortfolioId());
        }
        else {
            trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
        }
        trdProductDealAdd.setEntrustId(dto.getEntrustId());
        //trdProductDealAdd.setProdId(dto.getFinprodId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        dto.setTrdProductDealAdd(trdProductDealAdd);

        List<PtlTrade> ptlTrades = new ArrayList<>();
        // 组合交易表
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getPortfolioId());
        ptlTrade.setBusiNo(dto.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(dto.getFinprodId());
        ptlTrade.setBusiType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
        ptlTrade.setSettleDate(dto.getTradeDate());
        ptlTrades.add(ptlTrade);
        if (!dto.getPortfolioId().equals(dto.getActPortfolioId())) {
            // 组合交易表
            PtlTrade ptlTrade1 = new PtlTrade();
            ptlTrade1.setPortfolioId(dto.getActPortfolioId());
            ptlTrade1.setBusiNo(dto.getTradeId());
            ptlTrade1.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade1.setBusiId(dto.getFinprodId());
            ptlTrade1.setBusiType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
            ptlTrade1.setSettleDate(dto.getTradeDate());
            ptlTrades.add(ptlTrade1);
        }
        dto.setPtlTrade(ptlTrades);
        // 划款指令
        if (dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size() > 0) {
            dto.getBussTrdTransferOrder().get(0).setTradeId(dto.getTradeId());
            dto.setBussTrdTransferOrder(
                    bussTrdTransferOrderService.save(dto.getBussTrdTransferOrder()));
            dto.getBussTrdTransferOrder().get(0).setIsTotalTrade(DictCodeList.是否_否.getValue());
        }
        // 金融产品交易扩展
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(dto.getTradeId());
        String paBussType = null;
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_申购申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_申购确认.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_赎回申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_赎回确认.getValue();
        }
        paTrdProductDeal.setPaBussType(paBussType);
        paTrdProductDeal.setDeptCode(personInfo==null?null:personInfo.getTraderDept());
        dto.setPaTrdProductDeal(paTrdProductDeal);
        dto.setPortfolioId(dto.getActPortfolioId());
    }

    /**
     * 组合可用现金查询 <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return trdProjectNetInvestTrade002Service.getProAvailableCash(param);
    }

    /**
     * 查询组合可用持仓 <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPtlSecPosition")
    public BigDecimal getPtlSecPosition(@RequestBody PtlSecPositionFlowDto dto) {
        return trdProjectNetInvestTrade002Service.getPtlSecPosition(dto);
    }
}
