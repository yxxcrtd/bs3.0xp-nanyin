/*
 * 文件名：TrdFundInvestTrade003Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting
 * 修改时间：2018年11月7日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.fund.controller;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.fund.service.TrdFundInvestTrade001Service;
import com.joyintech.tams.invest.trade.fund.service.TrdFundInvestTrade003Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 基金投资交易确认 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年11月7日
 * @see TrdFundInvestTrade003Controller
 * @since 1.0
 */
@Controller
@PageId("TrdFundInvestTrade003")
@RequestMapping("/invest/trade/fund/TrdFundInvestTrade003")
public class TrdFundInvestTrade003Controller extends BaseController {

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
    private TrdFundInvestTrade003Service trdFundInvestTrade003Service;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 确认service
     */
    @Autowired
    private TrdFundInvestTrade001Service trdFundInvestTrade001Service;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;


    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.基金类资产类型, DictCode.交易场所, DictCode.行内资产二级分类, DictCode.行内资产三级分类, DictCode.行内资产四级分类, // 新增资产二三四级类型 王贵奇 2019/8/5
                DictCode.交易方向, DictCode.清算速度, DictCode.币种, DictCode.交易所基金类型});
        // 场内基金改造 add by renping 20191117 start
        model.addAttribute("ps_T18", DictCodeList.交易方向_认购确认.getValue());
        model.addAttribute("COUNTER_PARTY_ID_SHANGHAI", BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
        model.addAttribute("COUNTER_PARTY_ID_SHENZHEN", BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
        model.addAttribute("TRADE_PLACE_03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        model.addAttribute("collectType_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
        // 场内基金改造 add by renping 20191117 end

        model.addAttribute("PROFITTYPE_VALUE_01", DictCodeListExt2.收益类型_净值.getValue());
        model.addAttribute("PROFITTYPE_VALUE_03", DictCodeListExt2.收益类型_货币.getValue());

        model.addAttribute("ps_T05", DictCodeList.交易方向_申购确认.getValue());
        model.addAttribute("ps_T19", DictCodeList.交易方向_赎回确认.getValue());
        model.addAttribute("ps_T24", DictCodeList.交易方向_赎回到账.getValue());
        model.addAttribute("ps_T66", DictCodeListExt2.交易方向_转换确认.getValue());
        model.addAttribute("type_01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("type_02", DictCodeList.基金类资产类型_非货币基金.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("FINPROD_TYPE_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("collectType_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
        TrdFundInvestTradeConfirmDto dto = new TrdFundInvestTradeConfirmDto();
        String tradeId = this.getParam("tradeId");
        String status = this.getParam("validStatus");
        // String finprodId = this.getParam("finprodId");
        String ps = this.getParam("ps");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String pageModel = getParam("pageModel");
        if (StringUtil.isNotEmpty(tradeId)) {
            // 双击查看反显流程中的数据
            if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_流程中.getValue())
                && !StringUtil.equals(ps, DictCodeList.交易方向_赎回到账.getValue())) {
                dto = trdFundInvestTrade003Service.getBussinessEntityByWorkflowId(workflowId);
                BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("entity_bussRepFinProductInfoDto", JsonUtils.object2Json(bttoDto));
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", workflowId);
                model.addAttribute("workflowId", workflowId);
            }
            else if (StringUtil.equals(ps, DictCodeList.交易方向_赎回到账.getValue())) {
                List<BussTrdTransferOrderBase> bttob = null;
                String isFromCheck = this.getParam("isFromCheck");
                String statusValid = this.getParam("statusValid");
                if (StringUtil.equals(isFromCheck, "true")) {
                    if (StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())) {
                        dto = trdFundInvestTrade003Service.getTrdFundDto(RequestUtil.getPageParam());
                    }
                    else {
                        dto = trdFundInvestTrade003Service.getTrdFundDtoForAccount(tradeId);
                        bttob = trdFundInvestTrade003Service.selectOrdersByBizCode(tradeId);
                        model.addAttribute("single", 1);
                    }
                }
                else {
                    if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_有效.getValue())) {
                        dto = trdFundInvestTrade003Service.getTrdFundDto(RequestUtil.getPageParam());
                        // 赎回费用=支付费用-通道费
                        // dto.setFeeAmtPay(ArithmeticUtil.subtract(dto.getFeeAmtPay(), dto.getChannelAmt()));
                    }
                    else if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_撤销.getValue())) {
                        dto = trdFundInvestTrade003Service.getTrdFundDto(RequestUtil.getPageParam());
                    }
                    else {
                        dto = trdFundInvestTrade003Service.getTrdFundDtoForAccount(tradeId);
                        bttob = trdFundInvestTrade003Service.selectOrdersByBizCode(tradeId);
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
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", dto.getTradeId());
                model.addAttribute("tradeId", dto.getTradeId());
            }
            // 双击查看反显非流程中的数据
            else {
                dto = trdFundInvestTrade003Service.getTrdFundDto(RequestUtil.getPageParam());
                // dto.setFeeAmtPay(ArithmeticUtil.subtract(dto.getFeeAmtPay(), dto.getChannelAmt()));
                Map<String, Object> param = new HashMap<>();
                param.put("finprodId", dto.getFinprodId());
                Map<String, Object> result = trdFundInvestTrade003Service.getPurSpeed(param);
                String speed = (String)result.get("SPEED");
                String calId = (String)result.get("CALID");
                if (StringUtil.equals(speed, "1") && StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)
                    && !DictCodeListExt2.交易方向_转换申请.getValue().equals(dto.getPs())
                    && !DictCodeListExt2.交易方向_转换确认.getValue().equals(dto.getPs())
                ) {
                    Date tradeDate = dto.getTradeDate();
                    List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(tradeDate)),
                        DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15), calId);
                    Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(tradeDate)), 1,
                        holidayList, "1");
                    dto.setTradeDate(tradeDay);
                }
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                    Map<String, Object> accountTradId = new HashMap<>();
                    if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_撤销.getValue())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("tradeId", dto.getpTradeId());
                        map.put("N", DictCodeList.是否_否.getValue());
                        accountTradId = trdFundInvestTrade001Service.getAccountId(map);
                        if (accountTradId == null) {
                            accountTradId = trdFundInvestTrade001Service.selectAccountTrdIdinwfl(dto.getpTradeId());
                        }
                    }
                    else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("tradeId", dto.getTradeId());
                        map.put("N", DictCodeList.是否_否.getValue());
                        accountTradId = trdFundInvestTrade001Service.getAccountId(map);
                        if (accountTradId == null) {
                            accountTradId = trdFundInvestTrade001Service.selectAccountTrdIdinwfl(dto.getTradeId());
                        }
                    }
                    List<BussTrdTransferOrderBase> bttob = new ArrayList<>();
                    if (accountTradId != null) {
                        if (StringUtil.equals((String)accountTradId.get("WORKFLOWID"), "0")) {
                            bttob = trdFundInvestTrade003Service.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
                        }
                        else {
                            bttob = bussSettleOrderCommService.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
                        }
                        if (bttob != null && bttob.size() > 0) {
                            List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                            BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                            BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                            bttodList.add(bttod);
                            model.addAttribute("entity_bussRepFinProductInfoDto",
                                JsonUtils.object2Json(bussTrdTransferOrderService.save(bttodList).get(0)));
                        }
                    }
                }
                if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_认购.getValue())) {
                    dto.setPs(DictCodeList.交易方向_认购确认.getValue());
                    dto.setPrinAmt(dto.getTradeAmt());
                    //认购时，吧申请的add表中的确认日赋值到交易日做前台展示的确认日
                    dto.setTradeDate(dto.getPurCfmDate());
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                    dto.setPs(DictCodeList.交易方向_申购确认.getValue());
                    dto.setPrinAmt(dto.getTradeAmt());
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
                    dto.setPs(DictCodeList.交易方向_赎回确认.getValue());
                    dto.setPrinAmt(dto.getTradeAmt());
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_转换申请.getValue())) {
                    dto.setPs(DictCodeListExt2.交易方向_转换确认.getValue());
                    dto.setPrinAmt(dto.getTradeAmt());
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())
                         && !StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
                    dto.setPs(DictCodeList.交易方向_赎回到账.getValue());
                    dto.setPageModel(GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE);
                    model.addAttribute("single", 1);
                    dto.setbTradeId(dto.getTradeId());
                }
                if (!StringUtil.isNotEmpty(dto.getbTradeId())) {
                    dto.setbTradeId(dto.getTradeId());
                }

                model.addAttribute("tradeId", dto.getTradeId());
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(dto.getTrdInvestPersonInfo()));
                model.addAttribute("businessId", dto.getTradeId());
            }
        }
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = trdFundInvestTrade003Service.getBussinessEntityByWorkflowId(workflowId);
            if (dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size() > 0) {
                BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                model.addAttribute("entity_bussRepFinProductInfoDto", JsonUtils.object2Json(bttoDto));
            }
            if (DictCodeListExt2.交易方向_转换赎回确认.getValue().equals(dto.getPs())) {
                dto.setPs(DictCodeListExt2.交易方向_转换确认.getValue());
            }
            TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
            model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
            model.addAttribute("businessId", workflowId);
            model.addAttribute("workflowId", workflowId);
        }
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        dto.setEntrustId(null);
        model.addAttribute("entity", dto);
        model.addAttribute("fromTodo", !WorkFlowUtil.isNotFromToDoList());
        model.addAttribute("UNIT_CPRICE", dto.getUnitCprice());
        model.addAttribute("isShowType",
            CacheUtil.getSysParam("IS_SHOW_TYPE") == null ? "01" : CacheUtil.getSysParam("IS_SHOW_TYPE"));
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
    public BigDecimal getPrice(@RequestBody Map<String, Object> map) { 
        return trdFundInvestTrade003Service.getPrice(map);
    }

    /**
     * 查询待结转份额
     * 
     * @param map
     * @return
     */
    @RequestMapping("getcarryAmt")
    @ResponseBody
    public BigDecimal getcarryAmt(@RequestBody Map<String, Object> map)
        throws Exception {
        return trdFundInvestTrade003Service.getcarryAmt(map);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdFundInvestTradeConfirmDto dto,
                            BindingResult result) {
        AjaxResult ar = commonCheck(result, dto.getOperateType());
        /*
         * if (dto.getTradeAmt() != null && dto.getProAvailableCash() != null) { if (dto.getTradeAmt().compareTo(dto.getProAvailableCash()) == 1) {
         * ar.setData(this.getMessage("TRD_X_0002") + (String)ar.getData()); } }
         */
        if (StringUtil.isNotEmpty(dto.getCalendarId()) && dto.getTradeDate() != null) {
          //授权管理  isAuthorizationSoftcontrol true:软控  false:硬控
            boolean isAuthorizationSoftcontrol = Boolean.parseBoolean(CacheUtil.getSysParam("IS_AUTHORIZATION_SOFTCONTROL"));
            if (!isAuthorizationSoftcontrol) {
                if (authorizationvalid(dto)){
                    ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                    errorList.add(new ValidMessage(getMessage("TRD_E_0016", "副总裁/总裁助理")));
                    ar.setErrorList(errorList);
                }
            }
            
            boolean flg = dateCalService.isDealCalendarHoliday(dto.getTradeDate(), dto.getCalendarId());
            if (flg == true) {
                ar.setData(this.getMessage("TRD_X_0003") + (String)ar.getData());
            }

            // 校验流程是否已存在 yjm start 20190816
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId(getPageId());
            // parameter.setBusinessDataPk2(BussinessDictCodeList.共同业务类型_基金类资产投资交易.getValue());
            parameter.setBusinessDataPk3(dto.getTradeId());
            if (mstWorkflowCommonService.isInWflProcess(parameter)) {
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage("待确认的数据已存在，请在待办事项中处理。"));
                ar.setErrorList(errorList);
            }
            // 校验流程是否已存在 yjm end 20190816

            //赎回确认时，软控，收款账户和付款账户不能相同
            if(DictCodeList.交易方向_赎回确认.getValue().equals(dto.getPs())){
                if(null!=dto.getBussTrdTransferOrder() && dto.getBussTrdTransferOrder().size()>0){
                    for(BussTrdTransferOrderDto item:dto.getBussTrdTransferOrder()){
                        if(StringUtil.isNotEmpty(item.getPayAcctNo()) && StringUtil.isNotEmpty(item.getRecAcctNo()) ){
                            if(item.getPayAcctNo().equals(item.getRecAcctNo())){
                                List<JoyinMessage> joyinMessages = new ArrayList<>();
                                ar.setJoyinMessages(joyinMessages);
                                joyinMessages.add(new JoyinMessage(this.getMessage("赎回确认时，付款账户和托管账户不能相同。") + ar.getData(), MessageType.警告消息));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ar;
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody TrdFundInvestTradeConfirmDto dto) {
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        boolean isAuthorizationSoftcontrol = Boolean.parseBoolean(CacheUtil.getSysParam("IS_AUTHORIZATION_SOFTCONTROL"));
        if (isAuthorizationSoftcontrol) {
            if (authorizationvalid(dto)){
                JoyinMessage ms = new JoyinMessage("交易本金金额超过副总裁/总裁助理授权额度，是否确认提交!", MessageType.确认消息);
                joyinMessages.add(ms);
                wr.setJoyinMessages(joyinMessages);
            } 
        }
        return wr;
    }
    
    public boolean authorizationvalid(Object value) {
        // 副总裁/总裁助理授权额度检查相关处理
        String counterId = null;
        BigDecimal prinAmt = null;
        String 平安资产类型 = null;
        String windtypeone = null;
        String tradeMarket = null;
        String ps = null;
        // 资产类型改为授权类型 add by renping 2019-08-06  start
        String finprodId = null;
        // 资产类型改为授权类型 add by renping 2019-08-06  end
        
        boolean isNumCheck = true;
        if (value instanceof TrdFundInvestTradeConfirmDto) {
            TrdFundInvestTradeConfirmDto inputDto = (TrdFundInvestTradeConfirmDto) value;

            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getTradeAmt(); 
            if (prinAmt == null || prinAmt.compareTo(BigDecimal.ZERO) <= 0) {
                prinAmt = inputDto.getShareAmt();
            }
            平安资产类型 = DictCodeList.平安资产类型_基金.getValue();
            windtypeone = inputDto.getType6();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            // 资产类型改为授权类型 add by renping 2019-08-06  start
            finprodId = inputDto.getFinprodId();
            // 资产类型改为授权类型 add by renping 2019-08-06  end
            //赎回时不对份额进行授权校验
            if (DictCodeList.交易方向_赎回.getValue().equals(ps)){
                isNumCheck = false;
            }
        }
        // 授权额度检查（当本金金额高于副总裁/总裁助理相应资产的授权额度时，提示消息）
        if (prinAmt != null && isNumCheck) {
            CommonBusinessService commonBusinessService = SpringContextUtil.getBean(CommonBusinessService.class);
            Map<String, Object> params = CommonBusinessService.setParams(counterId, prinAmt, LoginUserUtil.getUserName(),平安资产类型, windtypeone, tradeMarket, ps
                    // 资产类型改为授权类型 add by renping 2019-08-06  start
                    , finprodId , null);
            if (commonBusinessService != null && commonBusinessService.是否超过副总额额度(params)) {
                // 交易本金金额超过副总裁/总裁助理授权额度，无法发起交易!
                return true;
            }
        }
        return false;
    }

    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdFundInvestTradeConfirmDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        if (baseGetBusinessId != null) {
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())
                || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_认购确认.getValue())) {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金申购);
            }
            else {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金赎回);
            }
            dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            dto.setTradeId(IDGenerator.generate());
        }
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(),BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),DictCodeList.平安资产类型_基金.getValue(),
                    (en)->interfRiskRdsService.sendRiskRdsData(dto,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        ProcessParameter<TrdFundInvestTradeConfirmDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdFundInvestTradeConfirmDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementIdForLog"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        // workFlowParameter.setUserTaskListener();
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(getMessage("TrdFundInvestTrade002.tradeId"),
                StringUtil.isNotEmpty(entity.getTotleTradeId()) ? entity.getTotleTradeId() : entity.getTradeId(),
                getMessage("TrdFundInvestTrade002.finprodName"), entity.getFinprodName(),
            getMessage("TrdFundInvestTrade002.tradeDate"), DateUtil.format(entity.getTradeDate())));

        if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_转换确认.getValue())) {
            dto.setTotleTradeId(dto.getTradeId());
            if (codeParameter == null) {
                dto.setTradeId(IDGenerator.generate());
            }
            else {
                dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
        }

        trdFundInvestTrade003Service.prepareEntity(dto, true);

        String msg = trdFundInvestTrade003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
