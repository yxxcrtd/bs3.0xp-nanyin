/*
 * 文件名：TrdFundInvestTrade002.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：caojin 修改时间：2020年7月15日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.fund.controller;

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
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.pagemenusplit.PageMenuSplitUtil;
import com.joyintech.tams.invest.trade.common.controller.TrdCommonController;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeDto;
import com.joyintech.tams.invest.trade.fund.service.TrdFundInvestTrade002Service;
import com.joyintech.tams.invest.trade.fund.service.TrdFundInvestTrade003Service;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基金投资多组合交易新建 〈功能详细描述〉
 *
 * @author caojin
 * @version 1.0 2020年7月15日
 * @see TrdFundInvestTrade004Controller
 * @since 1.0
 */
@Controller
@PageId("TrdFundInvestTrade004")
@RequestMapping("/invest/trade/fund/TrdFundInvestTrade004")
public class TrdFundInvestTrade004Controller extends BaseController {
	
	/**
     * 基金投资交易service
     */
    @Autowired
    private TrdFundInvestTrade002Service trdFundInvestTrade002Service;

    @Autowired
    private TradeCommonService tradeCommonService;

    @Autowired
    private TrdFundInvestTrade003Service trdFundInvestTrade003Service;

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
    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    @Autowired
    private TrdCommonController trdCommonController;

    /**
     * 界面初始化
     */
    @Override
    public String init(Model model) {

        setSelectOptionToModel(model, DictCode.基金类资产类型, DictCode.交易场所, DictCode.行内资产二级分类, DictCode.行内资产三级分类, DictCode.行内资产四级分类, // 新增资产二三四级类型 王贵奇 2019/7/30
                DictCode.交易方向, DictCode.清算速度, DictCode.币种, DictCode.交易所基金类型);

        // 场内基金改造 add by renping 20191117 start
        model.addAttribute("ps_T52", DictCodeListExt2.交易方向_认购.getValue());
        model.addAttribute("COUNTER_PARTY_ID_SHANGHAI",
            BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
        model.addAttribute("COUNTER_PARTY_ID_SHENZHEN",
            BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
        model.addAttribute("TRADE_PLACE_03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_06", DictCodeListExt1.交易场所_OTC场外.getValue());
        model.addAttribute("ETF_TYPE_03", DictCodeListExt2.ETF类型_跨境ETF.getValue());
        model.addAttribute("ETF_TYPE_04", DictCodeListExt2.ETF类型_债券ETF.getValue());
        model.addAttribute("ETF_TYPE_05", DictCodeListExt2.ETF类型_黄金ETF.getValue());
        model.addAttribute("ETF_TYPE_06", DictCodeListExt2.ETF类型_货币ETF.getValue());
        model.addAttribute("TRM_FUND_TYPE_05", DictCodeListExt2.交易所基金类型_实时申赎货币基金.getValue());
        model.addAttribute("collectType_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
        // 场内基金改造 add by renping 20191117 end
        model.addAttribute("ps_T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("ps_T06", DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("T65", DictCodeListExt2.交易方向_转换申请.getValue());
        model.addAttribute("ps_mr", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("ps_mc", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("type_01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("type_02", DictCodeList.基金类资产类型_非货币基金.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("FINPROD_TYPE_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));

        TrdFundInvestTradeDto dto = new TrdFundInvestTradeDto();
        String tradeId = this.getParam("tradeId");
        String status = this.getParam("validStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(tradeId)) {
            // 双击查看反显流程中的数据
            if (StringUtil.equals(status, DictCodeListExt1.交易流程状态_流程中.getValue())) {
                dto = trdFundInvestTrade002Service.getBussinessEntityByWorkflowId(workflowId);
                //买入卖出时，页面交易金额显示为交易本金，联动出划款金额为交易金额
                if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(), dto.getPs())||StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), dto.getPs())){
                	dto.setTradeAmt(dto.getPrinAmt());
                }
                // dto.setChlId(dto.getChannelId());
                if (dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size() > 0) {
                    BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                    model.addAttribute("entity_bussRepFinProductInfoDto", JsonUtils.object2Json(bttoDto));
                }
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                model.addAttribute("workflowId", workflowId);
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", workflowId);
            }
            // 双击查看反显非流程中的数据
            else {
                dto = trdFundInvestTrade002Service.getTrdFundDto(RequestUtil.getPageParam());
                if (dto.getSettleSpeed() != null) {
                    if (dto.getSettleSpeed() == 0) {
                        dto.setSettleSpreed(DictCodeList.清算速度_T_0.getValue());
                    }
                    else if (dto.getSettleSpeed() == 1) {
                        dto.setSettleSpreed(DictCodeList.清算速度_T_1.getValue());
                    }
                    else if (dto.getSettleSpeed() == 2) {
                        dto.setSettleSpreed(DictCodeList.清算速度_T_2.getValue());
                    }
                }
                model.addAttribute("tradeId", dto.getTradeId());
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(dto.getTrdInvestPersonInfo()));
                model.addAttribute("businessId", dto.getTradeId());
            }
        }
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = trdFundInvestTrade002Service.getBussinessEntityByWorkflowId(workflowId);
          //买入卖出时，页面交易金额显示为交易本金，联动出划款金额为交易金额
            if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(), dto.getPs())||StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), dto.getPs())){
            	dto.setTradeAmt(dto.getPrinAmt());
            }
            // dto.setChlId(dto.getChannelId());
            if (dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size() > 0) {
                BussTrdTransferOrderDto bttoDto = dto.getBussTrdTransferOrder().get(0);
                model.addAttribute("entity_bussRepFinProductInfoDto", JsonUtils.object2Json(bttoDto));
            }else{
            	model.addAttribute("tradeId", dto.getTradeId());
            }
            TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
            model.addAttribute("businessId", workflowId);
            model.addAttribute("workflowId", workflowId);
        }
        else {
            String entrustId = this.getParam("entrustId");
            if (StringUtil.isNotEmpty(entrustId)) {
                BaseEtrInvestInfoDto entrustDto = trdFundInvestTrade002Service.getEtrustDto(entrustId);
                if (entrustDto != null) {
                    EtrEntrust eedto = entrustDto.getEtrEntrust();
                    if (eedto != null) {
                        dto.setChlId(eedto.getChlId());
                        dto.setFinprodId(eedto.getFinprodId());
                        dto.setPortfolioId(eedto.getPortfolioId());
                        dto.setPs(eedto.getPs());
                        dto.setPrinAmt(eedto.getEntrustAmt());
                        dto.setTradeAmt(eedto.getEntrustAmt());
                        dto.setShareAmt(eedto.getEntrustNum());
                        dto.setFinprodMarketId(entrustDto.getFinprodMarketId());
                        dto.setUnitCprice(eedto.getPrice());
                        dto.setrFinprodId(entrustDto.getEtrEntrustAdd().getrFinprodId());
                        dto.setChangeToFinprodMarketId(entrustDto.getChangeToFinprodMarketId());
                        dto.setChangeToFinprodName(entrustDto.getChangeToFinprodName());
                        TrdInvestPersonInfo002Dto trdInvestPersonInfo = new TrdInvestPersonInfo002Dto();
                        trdInvestPersonInfo.setCounterTrader(eedto.getCounterTrader());
                        trdInvestPersonInfo.setIsOutTrade(eedto.getIsOutTrade());
                        trdInvestPersonInfo.setCounterId(eedto.getCounterId());
                        trdInvestPersonInfo.setContactInfo(eedto.getContactInfo());
                        if (entrustDto.getEtrDistributeList() != null && entrustDto.getEtrDistributeList().size() > 0) {
                            trdInvestPersonInfo.setTrader(entrustDto.getEtrDistributeList().get(0).getTrader());
                        }
                        model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(trdInvestPersonInfo));
                        model.addAttribute("entrustId", entrustId);
                    }
                    // 场内基金改造 add by renping 20191117 start
                    EtrEntrustAdd addDto = entrustDto.getEtrEntrustAdd();
                    if (addDto != null) {
                        dto.setSecManageAcctId(addDto.getSecAcctId());
                    }
                    // 场内基金改造 add by renping 20191117 end
                }
                dto.setEntrustId(entrustId);
            }
        }

        if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_转换赎回申请.getValue())) {
            dto.setPs(DictCodeListExt2.交易方向_转换申请.getValue());
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("entity", dto);
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end

        // add by etliu 20210501 银行间场内场外拆分 start
        model.addAttribute("parentPageId", this.getParam("parentPageId"));
        PageMenuSplitUtil.handlePageSelectDictList(model, DictCode.交易场所, this.getParam("parentPageId"));
        // add by etliu 20210501 银行间场内场外拆分 end

        return super.init(model);
    }

    /**
     * 资产代码联想下拉框 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId")
    @ResponseBody
    Page<SelectOptionDto> getFinprodId() {
        return trdFundInvestTrade002Service.getFinprodId(RequestUtil.getPageParam());
    }

//    资产推荐方 新增 zhuyongkang 20191023 start
    /**
     * 构建推荐方下拉框
     *
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("getAssetRecommandOrg")
    @ResponseBody
    public Page<SelectOptionDto> getAssetRecommandOrg() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return trdFundInvestTrade002Service.getAssetRecommandOrg(param);
    }
//    资产推荐方 新增 zhuyongkang 20191023 start

    /**
     * 查询资产管理人 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("selectManeger")
    @ResponseBody
    public Map<String, String> selectManeger(@RequestBody Map<String, Object> param) {
        return trdFundInvestTrade002Service.selectManeger((String)param.get("issuer"));
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdFundInvestTradeDto dto, BindingResult result) {

        // huwenbin 指令接收交易录单验证数据已存在流程中
        /*
         * if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){ String str = tradeCommonService.check(dto.getEntrustId(),
         * this.getPageId()); if(StringUtil.isNotEmpty(str)){ AjaxResult res= new AjaxResult(); res.setMessage(str, MessageType.错误消息); return res; } }
         */

        AjaxResult ar = commonCheck(result, dto.getOperateType());
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        ar.setJoyinMessages(joyinMessages);
        if (DictCodeList.交易方向_买入.getValue().equals(dto.getPs()) || DictCodeList.交易方向_申购.getValue().equals(dto.getPs())) {
            if (StringUtil.isNotEmpty(dto.getChlId()) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(dto.getPayType())) {
                if (dto.getTradeAmtAndFee() != null
                    && (dto.getChannelAvailableCash() == null
                        || dto.getTradeAmtAndFee().compareTo(dto.getChannelAvailableCash()) > 0)) {
                    joyinMessages.add(new JoyinMessage(this.getMessage("TRD_C_0004",getMessage("invest.channelId")) + ar.getData(), MessageType.警告消息));
                }
            }
            else {
                if (dto.getTradeAmt() != null && dto.getProAvailableCash() != null) {
                    if (dto.getTradeAmt().compareTo(dto.getProAvailableCash()) == 1) {
                        joyinMessages.add(new JoyinMessage(this.getMessage("TRD_X_0002") + ar.getData(), MessageType.警告消息));
                    }
                }
                //多组合验证
                else{
                    if(dto.getPortfolioId().equals("dzh")){
                        for(BussTrdTransfenpeiDto feipeiDto : dto.getBussTrdTransfenpeiDto()){
                            if (feipeiDto.getTradeAmt() != null && feipeiDto.getBussTrdAvailableCashQueryavalibleCash() != null) {
                                if (feipeiDto.getTradeAmt().compareTo(feipeiDto.getBussTrdAvailableCashQueryavalibleCash()) == 1) {
                                    joyinMessages.add(new JoyinMessage(this.getMessage("TRD_X_0002") + ar.getData(), MessageType.警告消息));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (StringUtil.isNotEmpty(dto.getCalendarId()) && dto.getTradeDate() != null) {
            boolean flg = dateCalService.isDealCalendarHoliday(dto.getTradeDate(), dto.getCalendarId());
            if (flg == true) {
                joyinMessages.add(new JoyinMessage(this.getMessage("TRD_X_0003") + ar.getData(), MessageType.警告消息));
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
    public AjaxResult save(@RequestBody TrdFundInvestTradeDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        List<TrdFundInvestTradeDto> dtoList = new ArrayList<>();
        List<BussTrdTransfenpeiDto> fenpeiList = dto.getBussTrdTransfenpeiDto();//组合分配
        List<BussTrdTransferOrderDto> orderList = dto.getBussTrdTransferOrder();//划款指令
        for(int i = 0; i < fenpeiList.size(); i++) {
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                    || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())){
                if(!(fenpeiList.get(i).getTradeAmt().compareTo(BigDecimal.ZERO) == 1)){
                    continue;
                }
            }else if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                    || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())){
                if(!(fenpeiList.get(i).getShareAmt().compareTo(BigDecimal.ZERO) == 1)){
                    continue;
                }
            }
            TrdFundInvestTradeDto tempDto = new TrdFundInvestTradeDto();
            BeanUtil.copyProperties(tempDto, dto);
            List<BussTrdTransferOrderDto> tmpOrderList = new ArrayList<BussTrdTransferOrderDto>();
            tempDto.setEntrustId(fenpeiList.get(i).getEntrustId());//委托代码
            tempDto.setPortfolioId(fenpeiList.get(i).getPortfolioId());//组合代码
            tempDto.setTradeAmt(fenpeiList.get(i).getTradeAmt());//交易金额
            tempDto.setShareAmt(fenpeiList.get(i).getShareAmt());//交易份额
            tempDto.setPrinAmt(fenpeiList.get(i).getPrinAmt());
            tempDto.setFpriceAmt(fenpeiList.get(i).getFpriceAmt());
            tempDto.setChannelAmt(fenpeiList.get(i).getChannelFee());//通道费
            tempDto.setTradeAmtAndFee(fenpeiList.get(i).getTradeAmtAndFee());//划款金额
            tempDto.setCommissionFee(fenpeiList.get(i).getCommissionAmt());//佣金
//            tempDto.setShareAmt(fenpeiList.get(i).getShareAmt());//单位净值
//            tempDto.setCarryAmt(fenpeiList.get(i).getShareAmt());//可结转金额
            tempDto.setIntAmt(fenpeiList.get(i).getIntAmt());
            if(!StringUtil.isEmptyList(orderList)){
                tmpOrderList.add(orderList.get(i));
                tempDto.setBussTrdTransferOrder(tmpOrderList);
            }
            BussinessCodeParameter codeParameter = null;
            if (baseGetBusinessId != null) {
                codeParameter = new BussinessCodeParameter();
                // 场内基金改造 modify by renping 20191117 start
                if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_认购.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                        // 场内基金改造 modify by renping 20191117 end
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金申购);
                }
                else {
                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金赎回);
                }
                tempDto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
            else {
                tempDto.setTradeId(IDGenerator.generate());
            }
            dtoList.add(tempDto);
        }

        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar =interfRiskRdsService.checkBatchRiskRds(dtoList,new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(), BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue(), DictCodeList.平安资产类型_基金.getValue(),
                    (sm,en)->interfRiskRdsService.sendRiskRdsData(sm,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        ProcessParameter<TrdFundInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdFundInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        workFlowParameter.setUserTaskListener(TrdFundInvestTrade002Service.class);
        processParameter.setPageId("TrdFundInvestTrade002");
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId("TrdFundInvestTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(getMessage("TrdFundInvestTrade002.tradeId"),
                StringUtil.isNotEmpty(entity.getTotleTradeId()) ? entity.getTotleTradeId() : entity.getTradeId(),
                getMessage("TrdFundInvestTrade002.finprodName"), GlobalConstant.SPLIT, entity.getFinprodName(),
            getMessage("TrdFundInvestTrade002.tradeDate"), DateUtil.format(entity.getTradeDate())));
//        if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_转换申请.getValue())) {
//            dto.setTotleTradeId(dto.getTradeId());
//            if (codeParameter == null) {
//                dto.setTradeId(IDGenerator.generate());
//            }
//            else {
//                dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
//            }
//        }


        String totleTradeId = IDGenerator.generate();
        for (TrdFundInvestTradeDto trdFundInvestTradeDto : dtoList) {
            trdFundInvestTrade002Service.prepareEntity(trdFundInvestTradeDto);
            trdFundInvestTradeDto.setTotleTradeId(totleTradeId);
        }

//        String msg = trdFundInvestTrade002Service.start(processParameter, dto);
        String msg = trdFundInvestTrade002Service.start(processParameter, dtoList.toArray(new TrdFundInvestTradeDto[]{}));
        
        //授信占用额度
//        trdCommonController.delLinesDeal(dto.getRiskCreditListCommonDto(),dto.getTradeId());
        return new AjaxResult(msg);
    }

        /**
     * 组合可用现金查询 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return trdFundInvestTrade002Service.getProAvailableCash(param);
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
        return trdFundInvestTrade002Service.getPtlSecPosition(dto);
    }

    /**
     * 通道：联想框文本框
     *
     * @param params 画面参数
     * @return 通道：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getChannel")
    @ResponseBody
    public List<SelectOptionDto> getChannel(@RequestParam Map<String, Object> params) {
        return trdFundInvestTrade002Service.getChannel(params);
    }

    // 场内基金改造 add by renping 20191117 start
    /**
     * 获取佣金配置信息
     *
     * @param dto 画面参数 
     * @return 佣金配置信息
     * @see
     * @since 1.0
     */
    @RequestMapping("getCommissionFeeInfo")
    @ResponseBody
    public TrdTradeFeeDetail getCommissionFeeInfo(@RequestBody TrdFundInvestTradeDto dto) {
        TrdTradeFeeDetail trdTradeFeeDetail = trdFundInvestTrade002Service.getCommissionFeeInfo(
            dto);
        return trdTradeFeeDetail;
    }
    // 场内基金改造 add by renping 20191117 end
}
