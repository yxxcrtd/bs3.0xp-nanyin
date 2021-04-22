package com.joyintech.tams.invest.trade.projectnet.controller;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.trade.common.aop.TradeRdsCommonAop;
import com.joyintech.tams.invest.trade.projectnet.dto.TrdProjectNetInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade001Service;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade003Service;
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
import java.util.*;

@PageId("TrdProjectNetInvestTrade003")
@Controller
@RequestMapping("/invest/trade/projectnet/TrdProjectNetInvestTrade003")
public class TrdProjectNetInvestTrade003Controller extends BaseController {

    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;

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
     * 确认service
     */
    @Autowired
    private TrdProjectNetInvestTrade003Service trdProjectNetInvestTrade003Service;

    @Autowired
    private TrdProjectNetInvestTrade001Service trdProjectNetInvestTrade001Service;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        /*添加二三四级数据字典 zhangpuyu 2019/8/7 start*/
        setSelectOptionToModel(model,
                new DictCode[] {DictCode.计划类资产一级分类,DictCode.计划类资产二级分类, DictCode.交易场所,
                        DictCode.交易方向, DictCode.清算速度, DictCode.币种,DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类,});
        /*添加二三四级数据字典 zhangpuyu 2019/8/7 end*/
        model.addAttribute("ps_T05", DictCodeList.交易方向_申购确认.getValue());
        model.addAttribute("ps_T19", DictCodeList.交易方向_赎回确认.getValue());
        model.addAttribute("ps_T24", DictCodeList.交易方向_赎回到账.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        TrdProjectNetInvestTradeConfirmDto dto = new TrdProjectNetInvestTradeConfirmDto();
        String tradeId = this.getParam("tradeId");
        String status = this.getParam("validStatus");
        String ps = this.getParam("ps");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String pageModel = getParam("pageModel");
        if (StringUtil.isNotEmpty(tradeId)) {
            if (StringUtil.equals(ps, DictCodeList.交易方向_赎回到账.getValue())) {
                List<BussTrdTransferOrderBase> bttob = null;
                String isFromCheck = this.getParam("isFromCheck");
                String statusValid = this.getParam("statusValid");
                if(StringUtil.equals(isFromCheck, "true")){
                    if(StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())){
                        dto = trdProjectNetInvestTrade003Service.getTrdFundDto(tradeId);
                    }else{
                        dto = trdProjectNetInvestTrade003Service.getTrdFundDtoForAccount(tradeId);
                        bttob = trdProjectNetInvestTrade003Service.selectOrdersByBizCode(tradeId);
                        model.addAttribute("single", 1);
                    }
                }else{
                    if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_有效.getValue())) {
                        dto = trdProjectNetInvestTrade003Service.getTrdFundDto(tradeId);
                    }
                    else if(StringUtil.equals(status, DictCodeListExt1.交易流程状态_撤销.getValue())){
                        dto = trdProjectNetInvestTrade003Service.getTrdFundDto(tradeId);
                    }
                    else {
                        dto = trdProjectNetInvestTrade003Service.getTrdFundDtoForAccount(tradeId);
                        bttob = trdProjectNetInvestTrade003Service.selectOrdersByBizCode(tradeId);
                    }
                }

                if (bttob != null && bttob.size() > 0) {
                    List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                    BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                    BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                    bttodList.add(bttod);
                    model.addAttribute("entity_bussRepFinProductInfoDto",
                            JsonUtils.object2Json(bussTrdTransferOrderService.save(bttodList).get(0)));

                }
                Map<String, Object> map = trdProjectNetInvestTrade003Service.getTradeRefrence(
                        dto.getbTradeId());
                if (map.get("feeAmtPay") != null) {
                    dto.setRedemptionFee(new BigDecimal((String)map.get("feeAmtPay")));
                }
                if (map.get("channalFee") != null) {
                    dto.setChannelFee(new BigDecimal((String)map.get("channalFee")));
                }
                dto.setHostingFee(ArithmeticUtil.subtract(ArithmeticUtil.subtract(dto.getFeeAmtPay(), dto.getRedemptionFee()), dto.getChannelFee()));
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", dto.getTradeId());
                model.addAttribute("tradeId", dto.getTradeId());
            }
            // 双击查看反显流程中的数据
            else if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_流程中.getValue())) {
                dto = trdProjectNetInvestTrade003Service.getBussinessEntityByWorkflowId(workflowId);
                BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("entity_bussRepFinProductInfoDto",
                        JsonUtils.object2Json(bttoDto));
//                model.addAttribute("riskTrialRetDto",
//                    JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                model.addAttribute("businessId", workflowId);
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", workflowId);
            }
            // 双击查看反显流程中的数据
            else  if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_流程中.getValue())) {
                dto = trdProjectNetInvestTrade003Service.getBussinessEntityByWorkflowId(workflowId);
                BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("entity_bussRepFinProductInfoDto",
                        JsonUtils.object2Json(bttoDto));
                //                model.addAttribute("riskTrialRetDto",
                //                    JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                model.addAttribute("businessId", workflowId);
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", workflowId);
            }
            // 双击查看反显非流程中的数据
            else {
                dto = trdProjectNetInvestTrade003Service.getTrdFundDto(tradeId);
                if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_撤销.getValue())) {
                    tradeId = this.getParam("pTradeId");
                }
                Map<String, Object> map1 = trdProjectNetInvestTrade003Service.getTradeRefrence(tradeId);
                if (map1.get("feeAmtPay") != null) {
                    dto.setRedemptionFee(new BigDecimal((String)map1.get("feeAmtPay")));
                }
                if (map1.get("channalFee") != null) {
                    dto.setChannelFee(new BigDecimal((String)map1.get("channalFee")));
                }
                if (map1.get("hostingFee") != null) {
                    dto.setHostingFee(new BigDecimal((String)map1.get("hostingFee")));
                }

                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue()) || StringUtil.equals(dto.getPs(),DictCodeList.交易方向_赎回.getValue())){
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("finprodId", dto.getFinprodId());
                    Map<String, Object> result = trdProjectNetInvestTrade003Service.getPurSpeed(param);
                    String speed = (String)result.get("SPEED");
                    String calId = (String)result.get("CALID");
                    if (StringUtil.equals(speed, "1")) {
                        Date tradeDate = dto.getTradeDate();
                        List<String> holidayList = dateCalService.getDealCalendarHoliday(
                                DateUtil.parse(DateUtil.format(tradeDate)),
                                DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15), calId);
                        Date tradeDay = dateCalService.calculationTradingDay(
                                DateUtil.parse(DateUtil.format(tradeDate)), 1, holidayList, "1");
                        dto.setTradeDate(tradeDay);
                    }
                }
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                    Map<String, Object> accountTradId = new HashMap<>();
                    if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_撤销.getValue())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("tradeId", dto.getpTradeId());
                        map.put("N", DictCodeList.是否_否.getValue());
                        accountTradId = trdProjectNetInvestTrade001Service.getAccountId(map);
                        if(accountTradId == null){
                            accountTradId = trdProjectNetInvestTrade001Service.selectAccountTrdIdinwfl(dto.getpTradeId());
                        }
                    }
                    else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("tradeId", dto.getTradeId());
                        map.put("N", DictCodeList.是否_否.getValue());
                        accountTradId = trdProjectNetInvestTrade001Service.getAccountId(map);
                        if(accountTradId == null){
                            accountTradId = trdProjectNetInvestTrade001Service.selectAccountTrdIdinwfl(dto.getTradeId());
                        }
                    }
                    if (accountTradId != null) {
                        List<BussTrdTransferOrderBase> bttob = new ArrayList<>();
                        if (StringUtil.equals((String)accountTradId.get("WORKFLOWID"), "0")) {
                            bttob = trdProjectNetInvestTrade003Service.selectOrdersByBizCode(
                                    (String)accountTradId.get("TRADID"));
                        }
                        else {
                            bttob = bussSettleOrderCommService.selectOrdersByBizCode(
                                    (String)accountTradId.get("TRADID"));
                        }
                        if (bttob != null && bttob.size() > 0) {
                            List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                            BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                            BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                            bttodList.add(bttod);
                            model.addAttribute("entity_bussRepFinProductInfoDto",
                                    JsonUtils.object2Json(
                                            bussTrdTransferOrderService.save(bttodList).get(0)));

                        }
                    }
                }
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                    dto.setPs(DictCodeList.交易方向_申购确认.getValue());
                    dto.setPrinAmt(dto.getTradeAmt());
                }
                else if (StringUtil.equals(dto.getPs(),DictCodeList.交易方向_赎回.getValue())) {
                    dto.setPs(DictCodeList.交易方向_赎回确认.getValue());
                    dto.setPrinAmt(dto.getTradeAmt());
                }else if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue()) && !StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)){
                    dto.setPs(DictCodeList.交易方向_赎回到账.getValue());
                    dto.setPageModel(GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE);
                    model.addAttribute("single", 1);
                    dto.setbTradeId(dto.getTradeId());
                }
                if (!StringUtil.isNotEmpty(dto.getbTradeId())) {
                    dto.setbTradeId(dto.getTradeId());
                }

                model.addAttribute("tradeId", dto.getTradeId());
                model.addAttribute("entity_trdInvestPersonInfo",
                        JsonUtils.object2Json(dto.getTrdInvestPersonInfo()));
                model.addAttribute("businessId", dto.getTradeId());
            }
        }
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = trdProjectNetInvestTrade003Service.getBussinessEntityByWorkflowId(workflowId);
            if (dto.getBussTrdTransferOrder() != null
                    && dto.getBussTrdTransferOrder().size() > 0) {
                BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
//                model.addAttribute("riskTrialRetDto",
//                    JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                model.addAttribute("entity_bussRepFinProductInfoDto",
                        JsonUtils.object2Json(bttoDto));
            }
            TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
            model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
            model.addAttribute("businessId", workflowId);
        }
        //---add by 裴家俊 20200327 添加交易编号 start-----
        model.addAttribute("workflowId", workflowId);
        //---add by 裴家俊 20200327 添加交易编号 end-----
        dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        dto.setProfitType(DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        dto.setEntrustId(null);
        model.addAttribute("FINPROD_TYPE_F17", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        model.addAttribute("entity", dto);

        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));

        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        return super.init(model);
    }

    /**
     * 查询单位净值 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getPrice")
    @ResponseBody
    public Map<String, Object> getPrice(@RequestBody Map<String, Object> map) {
        BigDecimal price = trdProjectNetInvestTrade003Service.getPrice(map);
        Map<String, Object> result = new HashMap<>();
        if (price == null) {
            result.put("success", false);
        }
        else {
            result.put("success", true);
            result.put("price", price);
        }
        return result;
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
            ValidateGroupEdit.class}) TrdProjectNetInvestTradeConfirmDto dto, BindingResult result) {
        AjaxResult ar = commonCheck(result, dto.getOperateType());
        /*if (dto.getTradeAmt() != null && dto.getProAvailableCash() != null) {
            if (dto.getTradeAmt().compareTo(dto.getProAvailableCash()) == 1) {
                ar.setData(this.getMessage("TRD_X_0002") + (String)ar.getData());
            }
        }*/
        //校验流程是否已存在 yjm start 20190816
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId(getPageId());
        //parameter.setBusinessDataPk2(BussinessDictCodeList.共同业务类型_计划类资产_净值型_投资交易.getValue());
        parameter.setBusinessDataPk3(dto.getTradeId());
        if (mstWorkflowCommonService.isInWflProcess(parameter)){
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("待确认的数据已存在，请在待办事项中处理。"));
            ar.setErrorList(errorList);
        }
        //校验流程是否已存在 yjm end 20190816
        if (StringUtil.isNotEmpty(dto.getCalendarId()) && dto.getTradeDate() != null) {
            boolean flg = dateCalService.isDealCalendarHoliday(dto.getTradeDate(),
                    dto.getCalendarId());
            if (flg == true) {
                ar.setData(this.getMessage("TRD_X_0003") + (String)ar.getData());
            }
        }
        return ar;
    }

    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdProjectNetInvestTradeConfirmDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            //TODO
            //codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_交易);
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
        ProcessParameter<TrdProjectNetInvestTradeConfirmDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdProjectNetInvestTradeConfirmDto> workFlowParameter = new WorkFlowParameter<>();
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
        prepareEntity(dto);

        String msg = trdProjectNetInvestTrade003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 处理基金投资交易自定义dto <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    private void prepareEntity(TrdProjectNetInvestTradeConfirmDto dto) {
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));

        // 一体化对应etliu start
        dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        // 一体化对应etliu end

//        dto.setTradeAmt(dto.getPrinAmt());
        dto.setYield(ArithmeticUtil.divide(dto.getYield(), new BigDecimal(100)));
        dto.setExerYield(ArithmeticUtil.divide(dto.getExerYield(), new BigDecimal(100)));
        dto.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        dto.setIsCancel(DictCodeList.是否_否.getValue());
        dto.setBusiType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
        dto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        dto.setIsClean(DictCodeList.是否_是.getValue());
        dto.setSettleDate(dto.getTradeDate());
        dto.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())
                || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回到账.getValue())) {
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
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
            trdProductDealAdd.setPurCfmDate(dto.getTradeDate());
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
        }
        trdProductDealAdd.setTradeId(dto.getTradeId());
        trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
        trdProductDealAdd.setEntrustId(dto.getEntrustId());
        //trdProductDealAdd.setProdId(dto.getFinprodId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        dto.setTrdProductDealAdd(trdProductDealAdd);
        // 组合交易表
        List<PtlTrade> ptlTrades = new ArrayList<>();
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
        // 在申购确认时获取划款指令dto
        if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), dto.getPs())) {
            List<BussTrdTransferOrderBase> bttob = bussSettleOrderCommService.selectOrdersByBizCode(
                    dto.getbTradeId());
            if (bttob != null && bttob.size() > 0) {
                List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                bttodList.add(bttod);
                dto.setBussTrdTransferOrder(bttodList);
            }
        }
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
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_申购申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_申购确认.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_赎回申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_赎回确认.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回到账.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_赎回到账.getValue();
        }
        paTrdProductDeal.setPaBussType(paBussType);
        paTrdProductDeal.setDeptCode(personInfo==null?null:personInfo.getTraderDept());
        dto.setPaTrdProductDeal(paTrdProductDeal);
        dto.setPortfolioId(dto.getActPortfolioId());
    }
}