/*
 * 文件名：TrdSpotexInvestTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.spotex.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.common.controller.TrdCommonController;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeDto;
import com.joyintech.tams.invest.trade.spotex.service.TrdSpotexInvestTrade002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
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
 * 外汇即期交易新建Controller
 * 
 * @author wangguoxian
 * @version 1.0 2019年7月8日
 * @see TrdSpotexInvestTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdSpotexInvestTrade002")
@RequestMapping("/invest/trade/spotex/TrdSpotexInvestTrade002")
public class TrdSpotexInvestTrade002Controller extends BaseController {
	

    @Autowired
    private TradeCommonService tradeCommonService;

    /**
     * TrdSpotexInvestTrade002Service
     */
    @Autowired
    private TrdSpotexInvestTrade002Service trdSpotexInvestTrade002Service;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired(required = false)
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    @Autowired
    private TrdCommonController trdCommonController;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.外汇交易类型, DictCode.货币对, DictCode.币种,DictCode.交易方向,
            DictCode.清算速度, DictCode.我方角色, DictCode.外汇交易模式, DictCode.结算方式, DictCode.持有目的);
        model.addAttribute("EXC_DEAL_TYPE_FGN_CY", DictCodeListExt2.外汇交易类型_外币对.getValue());
        model.addAttribute("EXC_DEAL_TYPE_RMB_FX", DictCodeListExt2.外汇交易类型_人民币外汇交易.getValue());
        model.addAttribute("DEAL_MODE_01", DictCodeListExt2.外汇交易模式_竞价交易.getValue());
        model.addAttribute("DEAL_MODE_OTC", DictCodeListExt2.外汇交易模式_询价交易.getValue());
        model.addAttribute("DEAL_MODE_MATCH", DictCodeListExt2.外汇交易模式_撮合交易.getValue());
        model.addAttribute("SETT_MODE_BIL_SETT", DictCodeListExt2.结算方式_双边全额清算.getValue());
        model.addAttribute("SETT_MODE_CENT_SETT", DictCodeListExt2.结算方式_集中净额清算.getValue());
        model.addAttribute("OUR_ROLE_TAKER", DictCodeListExt2.我方角色_发起方.getValue());
        model.addAttribute("OUR_ROLE_MAKER", DictCodeListExt2.我方角色_报价方.getValue());
        model.addAttribute("JPY_CNY", DictCodeList.货币对_JPY_CNY.getValue());
        model.addAttribute("CCY_CNY", DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("CCY_USD", DictCodeList.币种_美元_USD.getValue());
        model.addAttribute("ACCOUNT_SPEED_T0", DictCodeList.清算速度_T_0.getValue());
        model.addAttribute("ACCOUNT_SPEED_T1", DictCodeList.清算速度_T_1.getValue());
        model.addAttribute("ACCOUNT_SPEED_T2", DictCodeListExt2.清算速度_T_2.getValue());
        model.addAttribute("COUNTER_TYPE_01", DictCodeListExt1.交易对手类型_市场机构.getValue());
        //----add by jjpei 20190718--------交易监控风险试算-----------start--------
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        //----add by jjpei 20190718--------交易监控风险试算-----------end--------
        
        String tradeId = this.getParam("tradeId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String validStatus = this.getParam("validStatus");
        TrdSpotexInvestTradeDto dto = new TrdSpotexInvestTradeDto();
        if (StringUtil.isNotEmpty(tradeId)) {
            // 双击查看反显流程中的数据
            if (StringUtil.equals(validStatus, DictCodeListExt1.交易流程状态_流程中.getValue())) {
                dto = trdSpotexInvestTrade002Service.getBussinessEntityByWorkflowId(workflowId);
                if (dto.getBussTrdTransferOrder() != null
                    && dto.getBussTrdTransferOrder().size() > 0) {
                    List<BussTrdTransferOrderDto> bttoDto = dto.getBussTrdTransferOrder();
                    model.addAttribute("entityList",
                        JsonUtils.object2Json(bttoDto));
                }
                TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
                model.addAttribute("riskTrialRetDto",
                    JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
                model.addAttribute("businessId", workflowId);
            }
            // 双击查看反显非流程中的数据
            else {
                dto = trdSpotexInvestTrade002Service.getDealInfo(tradeId,validStatus);
                model.addAttribute("tradeId", dto.getTradeId());
                model.addAttribute("entity_trdInvestPersonInfo",
                    JsonUtils.object2Json(dto.getTrdInvestPersonInfo()));
                model.addAttribute("businessId", dto.getTradeId());
            }
        }
        // 待办事项中查看
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = trdSpotexInvestTrade002Service.getBussinessEntityByWorkflowId(workflowId);
            if (!StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
                List<BussTrdTransferOrderDto> bttoDto = dto.getBussTrdTransferOrder();
                model.addAttribute("entityList",
                    JsonUtils.object2Json(bttoDto));
            }
            TrdInvestPersonInfo002Dto tipiDto = dto.getTrdInvestPersonInfo();
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(tipiDto));
            model.addAttribute("businessId", workflowId);
        }
        // 从委托场合点击交易录单
        else {
            String entrustId = this.getParam("entrustId");
            model.addAttribute("businessId", entrustId);
            if (StringUtil.isNotEmpty(entrustId)) {
                BaseEtrInvestInfoDto entrustDto = trdSpotexInvestTrade002Service.getEtrustDto(
                    entrustId);
                if (entrustDto != null) {
                    EtrEntrust eedto = entrustDto.getEtrEntrust();
                    EtrEntrustAdd etrAddDto = entrustDto.getEtrEntrustAdd();
                    if (eedto != null) {
                        // 基本要素信息由委托带出
                        // 委托代码
                        dto.setEntrustId(entrustId);
                        // 组合代码
                        dto.setPortfolioId(eedto.getPortfolioId());
                        // 外汇交易类型
                        dto.setExcDealType(etrAddDto.getExcDealType());
                        // 货币对
                        dto.setCurPair(etrAddDto.getCurPair());
                        // 交易方向
                        dto.setPs(eedto.getPs());
                        // 清算速度
                        dto.setSettleSpeed(etrAddDto.getSettleSpreed());
                        // 金融要素信息由投资委托带出
                        // 即期汇率
                        dto.setUnitCprice(eedto.getPrice());
                        // 交易货币
                        dto.setDealCur(eedto.getCcy());
                        // 交易货币金额
                        dto.setPrinAmt(eedto.getEntrustAmt());
                        // 对应货币
                        dto.setCcy(etrAddDto.getCorCcy());
                        // 对应货币金额
                        dto.setTradeAmt(eedto.getEntrustAmt());
                        // 人员信息
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
                }
                dto.setEntrustId(entrustId);
            }
        }
        //----add by zxjjpei 获取工作流id 20200325 start----
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }
        //----add by zxjjpei 获取工作流id 20200325 end----
        model.addAttribute("FINPROD_TYPE_F11", DictCodeListExt1.金融产品类型_投管用_即期.getValue());
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
        model.addAttribute("entity", dto);
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        model.addAttribute("PINAN_ASSET_TYPE_F13",DictCodeListExt2.平安资产类型_外汇即期.getValue());
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(map);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdSpotexInvestTradeDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
       /* if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
    		String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
    		if(StringUtil.isNotEmpty(str)){
    			AjaxResult res= new AjaxResult();
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
        // 货币对
        String curPair = dto.getCurPair();
        // 交易货币
        String dealCur = dto.getDealCur();
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
            noBaseCurPos = dto.getCorProAvailableCash();

        }
        else if (StringUtil.isNotEmpty(curPair) && curPair.indexOf(dealCur) > 0){
            // 交易货币是非基准货币
            noBaseCurAmt = dto.getPrinAmt();
            noBaseCurPos = dto.getProAvailableCash();
            // 对应货币是基准货币
            baseCurAmt = dto.getTradeAmt();
            baseCurPos = dto.getCorProAvailableCash();
        }

        // 当交易方向为买入时，非基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getFinTradeProduct().getExcPs(), DictCodeList.交易方向_买入.getValue())) {
            if (noBaseCurAmt != null && noBaseCurPos != null && noBaseCurAmt.compareTo(noBaseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                    this.getMessage("TRD_SPOTEX_002", this.getMessage("TrdSpotexInvestTrade002.t01"),
                        this.getMessage("TrdSpotexInvestTrade002.noBaseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }

        // 当交易方向为卖出时，基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getFinTradeProduct().getExcPs(), DictCodeList.交易方向_卖出.getValue())) {
            if (baseCurAmt != null && baseCurPos != null && baseCurAmt.compareTo(baseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                    this.getMessage("TRD_SPOTEX_002", this.getMessage("TrdSpotexInvestTrade002.t02"),
                        this.getMessage("TrdSpotexInvestTrade002.baseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }

        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * 新建/修改流程
     * @param dto 画面入参
     * @param br 校验结果
     * @return 新建/修改结果状态
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(PAGE_SAVE)
    @ResponseBody
    public AjaxResult save(@RequestBody TrdSpotexInvestTradeDto dto,BindingResult br) throws Exception{
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        
        
        if(baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇即期投资交易));
            dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            dto.setTradeId(IDGenerator.generate());
        }
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(), DictCodeListExt2.平安资产类型_外汇即期.getValue(),
                    (en)->interfRiskRdsService.sendRiskRdsData(dto,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        ProcessParameter<TrdSpotexInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdSpotexInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
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
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_外汇即期投资交易.getValue());
        // 业务说明：“交易编号：” + 上述取得的交易编号+“货币对：”+画面.货币对+“交易日：”+画面.交易日
        workFlowParameter.setComment((entity) -> this.getMessage("TrdSpotexInvestTrade.tradeId") + "("
            + entity.getTradeId() + ")" + "-"
            + this.getMessage("TrdSpotexInvestTrade.curPair") + "("
            + entity.getCurPair() + ")" + "-"
            + this.getMessage("TrdSpotexInvestTrade.tradeDate") + "("
            + DateUtil.format(entity.getTradeDate()) + ")");
        prepareEntity(dto);
        String msg = trdSpotexInvestTrade002Service.start(processParameter, dto);
        
        //授信占用额度
//        trdCommonController.delLinesDeal(dto.getRiskCreditListCommonDto(),dto.getTradeId());
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 处理外汇即期投资交易自定义Dto
     * @param dto
     * @see
     * @since 1.0
     */
    private void prepareEntity(TrdSpotexInvestTradeDto dto) {
        
        
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
        return trdSpotexInvestTrade002Service.getProAvailableCash(param);
    }
    
    /**
     * 获取下一个工作日
     *
     * @param params 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    @RequestMapping("getNextWorkDay")
    @ResponseBody
    public String getNextWorkDay(@RequestParam Map<String, Object> params) {
        return trdSpotexInvestTrade002Service.getNextWorkDay(params);
    }
    
    /**
     * 划款指令生成实体类
     * 
     * @param dto 外汇掉期投资交易实体类
     * @return 金融产品交易实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntity")
    public List<TrdProductDeal> getEntity() {
        Map<String, Object> map = RequestUtil.getPageParam();
        TrdSpotexInvestTradeDto dto = BeanUtil.mapToObject(map,
            TrdSpotexInvestTradeDto.class);
        TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto = new TrdInvestPersonInfo002Dto();
        dto.setTrdInvestPersonInfo(trdInvestPersonInfo002Dto);
        if(map.get("trdInvestPersonInfo[contactInfo]") != null) {
            dto.getTrdInvestPersonInfo().setContactInfo(
                    map.get("trdInvestPersonInfo[contactInfo]").toString());
        }
        if(map.get("trdInvestPersonInfo[counterId]") != null) {
            dto.getTrdInvestPersonInfo().setCounterId(
                    map.get("trdInvestPersonInfo[counterId]").toString());
        }
        if(map.get("trdInvestPersonInfo[counterTrader]") != null) {
            dto.getTrdInvestPersonInfo().setCounterTrader(
                    map.get("trdInvestPersonInfo[counterTrader]").toString());
        }
        if(map.get("trdInvestPersonInfo[trader]") != null) {
            dto.getTrdInvestPersonInfo().setTrader(
                    map.get("trdInvestPersonInfo[trader]").toString());
        }


//        dto.getTrdInvestPersonInfo().setIsOutTrade(
//            map.get("trdInvestPersonInfo[isOutTrade]").toString());
        return trdSpotexInvestTrade002Service.getEntity(dto);
    }
}
