/*
 * 文件名：TrdPlanCcyInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.planccy.controller;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import com.joyintech.tams.invest.trade.planccy.dto.TrdPlanCcyInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.planccy.dto.TrdPlanCcyInvestTradeDto;
import com.joyintech.tams.invest.trade.planccy.service.TrdPlanCcyInvestTrade001Service;
import com.joyintech.tams.invest.trade.planccy.service.TrdPlanCcyInvestTrade002Service;
import com.joyintech.tams.invest.trade.planccy.service.TrdPlanCcyInvestTrade003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 计划类货币交易查询
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月7日
 * @see TrdPlanCcyInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdPlanCcyInvestTrade001")
@RequestMapping("/invest/trade/planccy/TrdPlanCcyInvestTrade001")
public class TrdPlanCcyInvestTrade001Controller extends BaseController {

    /**
     * 
     */
    @Autowired
    private TrdPlanCcyInvestTrade001Service trdPlanCcyInvestTrade001Service;
    
    @Autowired
    private TrdPlanCcyInvestTrade002Service trdPlanCcyInvestTrade002Service;
    
    @Autowired
    private TrdPlanCcyInvestTrade003Service trdPlanCcyInvestTrade003Service;
    
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;

    /**
     * 划款指令业务组件service
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 交易基础组件调用Service
     */
    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    /**
     * 查询界面初始化
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.交易流程状态);
        /*添加资产二级-四级分类的数据字典值 lishuping 2019/7/29 start*/
        this.setSelectOptionToModel(model, DictCode.计划类资产一级分类,DictCode.行内资产二级分类,
                DictCode.行内资产三级分类,DictCode.行内资产四级分类);
        /*添加资产二级-四级分类的数据字典值 lishuping 2019/7/29 end*/
        model.addAttribute("ps_T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("ps_T06", DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("ps_T05", DictCodeList.交易方向_申购确认.getValue());
        model.addAttribute("ps_T19", DictCodeList.交易方向_赎回确认.getValue());
        model.addAttribute("ps_T24", DictCodeList.交易方向_赎回到账.getValue());
        model.addAttribute("ps_mr", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("ps_mc", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    
    /**
     * 分页查询
     * 
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdPlanCcyInvestTradeDto> findPage()
        throws Exception {
        return trdPlanCcyInvestTrade001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 按钮点击时的check
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
        ValidateGroupDelete.class}) TrdPlanCcyInvestTradeDto dto, BindingResult result) {
        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            if (trdCommonBaseService.isAlreadyRevoked(dto.getSelectedItem().get(0).getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        //撤销时校验
        if (StringUtil.equals(dto.getOperateType(), "revoke")){
            //校验是否存在待确认的流程 yjm start 20190906
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("TrdPlanCcyInvestTrade003");
            parameter.setBusinessDataPk3(dto.getSelectedItem().get(0).getTradeId());
            if (mstWorkflowCommonService.isInWflProcess(parameter)){
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage("存在后续交易，请先撤销流程中待确认的数据。"));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
            //校验是否存在待确认的流程 yjm end 20190906
            // 确认提醒 TISHKBS-1113【组合资金账户头寸查询】内转交易撤销提醒 add by hpp 2020-09-17 start
            if (result.getErrorCount() == 0) {
                AjaxResult ajaxResult = new AjaxResult();
                String confirmMsg = "";
                String changeLineMark = "<br/>";

                //根据交易编号查询另一笔未撤销的转仓交易信息
                String tradeId = dto.getSelectedItem().get(0).getTradeId();
//                TrdProductDeal res = trdCommonBaseService.selectZCDeal(tradeId);
//                if(StringUtil.isNotEmpty(res)){
//                    confirmMsg = "此交易为转仓交易，撤销后需要将业务编号为["+
//                            res.getTradeId()+"]的反向转仓交易也撤销掉，请确认是否继续？";
//                }else{
//                    confirmMsg = "请确认是否撤销？";
//                }
//                if (StringUtil.isNotBlank(confirmMsg)) {
//                    ajaxResult.setData(confirmMsg);
//                }
//                if (ajaxResult != null) {
//                    return ajaxResult;
//                }
                TrdProductDeal trdProductDeal = trdCommonBaseService.selectZCDealByTid(tradeId, false);
                // 查询到 卖出的转仓交易  说明 点击的是买入。
                if(trdProductDeal!=null&& StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),trdProductDeal.getPs())){
                    ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                    errorList.add(new ValidMessage(this.getMessage("内部转仓只能撤销卖出交易,买入自动撤销！")));
                    ajaxResult.setErrorList(errorList);
                    return ajaxResult;
                }
            }
            // 确认提醒 TISHKBS-1113【组合资金账户头寸查询】内转交易撤销提醒 add by hpp 2020-09-17 end
        }else if(StringUtil.equals(dto.getOperateType(), "confirm")){
        	if(trdPlanCcyInvestTrade001Service.isCheckConfirm(dto.getTradeId())){
        		ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage("不允许操作！"));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
        	}
        	if(result.getErrorCount() == 0){// 确认时校验 add by yangwenjun 20201124
                WflValidInputParameter parameter = new WflValidInputParameter();
                parameter.setPageId("TrdPlanCcyInvestTrade003");
                parameter.setBusinessDataPk3(dto.getTradeId());
                if (mstWorkflowCommonService.isInWflProcess(parameter)) {
                    ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                    errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
                    AjaxResult ar = new AjaxResult();
                    ar.setErrorList(errorList);
                    return ar;
                }
            }
        }

        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check2")
    public AjaxResult check2(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdPlanCcyInvestTradeConfirmDto dto, BindingResult result) {
        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            if (trdCommonBaseService.isAlreadyRevoked(dto.getSelectedItem().get(0).getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 撤销
     * <功能详细描述>
     * @param entity
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdPlanCcyInvestTradeDto entity, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        TrdPlanCcyInvestTradeDto dto = entity.getSelectedItem().get(0);
        ProcessParameter<TrdPlanCcyInvestTradeDto> processParameter = new ProcessParameter<TrdPlanCcyInvestTradeDto>();
        WorkFlowParameter<TrdPlanCcyInvestTradeDto> params = new WorkFlowParameter<TrdPlanCcyInvestTradeDto>();
        processParameter.setWorkFlowParameter(params);
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue()) ||
        		StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
            dto = trdPlanCcyInvestTrade002Service.getTrdFundDto(dto.getTradeId());
            if(StringUtil.isNotEmpty(dto.getSettleSpeed())){
                if(dto.getSettleSpeed()==0){
                    dto.setSettleSpreed(DictCodeList.清算速度_T_0.getValue());
                }else if(dto.getSettleSpeed()==1){
                    dto.setSettleSpreed(DictCodeList.清算速度_T_1.getValue());
                }
            }
            processParameter.setPageId("TrdPlanCcyInvestTrade002");
            params.setPageId("TrdPlanCcyInvestTrade002");
        }else if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
            //YUCHUBS-647 交易查询时费用为空 guyunfei 20190910 start
            String feeType = "";
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())){
                feeType = DictCodeListExt1.一次性费用类型_申购费.getValue();
            }else{
                feeType = DictCodeListExt1.一次性费用类型_赎回费.getValue();
            }
            TrdPlanCcyInvestTradeConfirmDto confirmDto = trdPlanCcyInvestTrade003Service.getTrdFundDto(dto.getTradeId(),feeType);
            //YUCHUBS-647 交易查询时费用为空 guyunfei 20190910 end
            BeanUtil.copyPropertiesIgnoreNull(dto, confirmDto);
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                Map<String, Object> accountTradId = new HashMap<>();
                Map<String, Object> map = new HashMap<>();
                map.put("tradeId", dto.getTradeId());
                map.put("N", DictCodeList.是否_否.getValue());
                accountTradId = trdPlanCcyInvestTrade001Service.getAccountId(map);
                if (accountTradId == null) {
                    accountTradId = trdPlanCcyInvestTrade001Service.selectAccountTrdIdinwfl(
                        dto.getTradeId());
                }
                List<BussTrdTransferOrderBase> bttob = new ArrayList<>();
                if (accountTradId != null) {
                    if (StringUtil.equals((String)accountTradId.get("WORKFLOWID"), "0")) {
                        bttob = trdPlanCcyInvestTrade003Service.selectOrdersByBizCode(
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
                        dto.setBussTrdTransferOrder(bussTrdTransferOrderService.save(bttodList));
                    }
                }
            }
//            Map<String, Object> map = trdPlanCcyInvestTrade003Service.getTradeRefrence(
//                dto.getTradeId());
//            if (map.get("feeAmtPay") != null) {
//                dto.setRedemptionFee(new BigDecimal((String)map.get("feeAmtPay")));
//            }
//            if (map.get("channalFee") != null) {
//                dto.setChannelFee(new BigDecimal((String)map.get("channalFee")));
//            }
//            if (map.get("hostingFee") != null) {
//                dto.setHostingFee(new BigDecimal((String)map.get("hostingFee")));
//            }
            processParameter.setPageId("TrdPlanCcyInvestTrade003");
            params.setPageId("TrdPlanCcyInvestTrade003");
        }
        params.setElementId(this.getParam("elementId"));
        params.setBusinessCode(item -> item.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_交易.getValue());
        // 设置流程业务说明, entity即传入的dto
        // “交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日
        params.setComment((item) -> WorkFlowUtil.concatComment(
            getMessage("TrdPlanCcyInvestTrade002.tradeId"), item.getTradeId(),
            getMessage("TrdPlanCcyInvestTrade002.finprodName"),
            item.getFinprodName(),
            getMessage("TrdPlanCcyInvestTrade002.tradeDate"),
            DateUtil.format(item.getTradeDate())));
        processParameter.setWorkFlowParameter(params);
        dto.setOperateType(entity.getOperateType());
//        dto.setbTradeId(dto.getTradeId());
        String msg = trdPlanCcyInvestTrade001Service.start(processParameter,dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 文件导出 <功能详细描述>
     * 
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return trdPlanCcyInvestTrade001Service.export(this.getPageId(),
        		param);
    }

    /**
     * 获取委托代码
     * @return 联想文本框实体类集合
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntrustIdByType")
    public List<SuggestDto> getEntrustIdByType() {
        String entrustId = getParam("param");
        Map<String, Object> map = new HashMap<>();
        map.put("param", entrustId);
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_计划类资产.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdPlanCcyInvestTrade001Service.getEntrustIdByType(map);
    }
}
