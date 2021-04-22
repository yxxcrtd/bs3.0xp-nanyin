package com.joyintech.tams.invest.trade.projectnet.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.projectnet.dto.TrdProjectNetInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.projectnet.dto.TrdProjectNetInvestTradeDto;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade001Service;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade002Service;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade003Service;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageId("TrdProjectNetInvestTrade001")
@Controller
@RequestMapping("/invest/trade/projectnet/TrdProjectNetInvestTrade001")
public class TrdProjectNetInvestTrade001Controller extends BaseController {

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
    @Autowired
    private TrdProjectNetInvestTrade001Service trdProjectNetInvestTrade001Service;
    @Autowired
    private TrdProjectNetInvestTrade002Service trdProjectNetInvestTrade002Service;
    @Autowired
    private TrdProjectNetInvestTrade003Service trdProjectNetInvestTrade003Service;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.交易流程状态);
        this.setSelectOptionToModel(model, DictCode.计划类资产一级分类);
        /*添加资产二级-四级分类的数据字典值 zhangpuyu 2019/7/31 start*/
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类);
        /*添加资产二级-四级分类的数据字典值 zhangpuyu 2019/7/31 end*/
        model.addAttribute("ps_T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("ps_T06", DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("ps_T05", DictCodeList.交易方向_申购确认.getValue());
        model.addAttribute("ps_T19", DictCodeList.交易方向_赎回确认.getValue());
        model.addAttribute("ps_T24", DictCodeList.交易方向_赎回到账.getValue());
        model.addAttribute("ps_mr",DictCodeList.交易方向_买入.getValue());
        model.addAttribute("ps_mc",DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("BUS_TYPE", DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());

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
    public Page<TrdProjectNetInvestTradeDto> findPage()
            throws Exception {
        return trdProjectNetInvestTrade001Service.findPage(RequestUtil.getPageParam());
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
            ValidateGroupDelete.class}) TrdProjectNetInvestTradeDto dto, BindingResult result) {
        //撤销时校验
        if (StringUtil.equals(dto.getOperateType(), "revoke")){
            //校验是否存在待确认的流程 yjm start 20190904
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("TrdProjectNetInvestTrade003");
            parameter.setBusinessDataPk3(dto.getSelectedItem().get(0).getTradeId());
            if (mstWorkflowCommonService.isInWflProcess(parameter)){
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage("存在后续交易，请先撤销流程中待确认的数据。"));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
            //校验是否存在待确认的流程 yjm end 20190904
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
            ValidateGroupDelete.class}) TrdProjectNetInvestTradeDto dto, BindingResult result) {
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
            ValidateGroupDelete.class}) TrdProjectNetInvestTradeDto entity, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        TrdProjectNetInvestTradeDto dto = entity.getSelectedItem().get(0);
        ProcessParameter<TrdProjectNetInvestTradeDto> processParameter = new ProcessParameter<TrdProjectNetInvestTradeDto>();
        WorkFlowParameter<TrdProjectNetInvestTradeDto> params = new WorkFlowParameter<TrdProjectNetInvestTradeDto>();
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())
                || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
            dto = trdProjectNetInvestTrade002Service.getTrdFundDto(dto.getTradeId());
            if (dto.getSettleSpeed()!=null){
                if(dto.getSettleSpeed()==0){
                    dto.setSettleSpreed(DictCodeList.清算速度_T_0.getValue());
                }else if(dto.getSettleSpeed()==1){
                    dto.setSettleSpreed(DictCodeList.清算速度_T_1.getValue());
                }
            }
            processParameter.setPageId("TrdPProjectNetInvestTrade002");
            params.setPageId("TrdProjectNetInvestTrade002");
        }else if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())
                || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回到账.getValue())){
            TrdProjectNetInvestTradeConfirmDto confirmDto = trdProjectNetInvestTrade003Service.getTrdFundDto(dto.getTradeId());
            BeanUtil.copyPropertiesIgnoreNull(dto, confirmDto);
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                Map<String, Object> accountTradId = new HashMap<>();
                Map<String, Object> map = new HashMap<>();
                map.put("tradeId", dto.getTradeId());
                map.put("N", DictCodeList.是否_否.getValue());
                accountTradId = trdProjectNetInvestTrade001Service.getAccountId(map);
                if (accountTradId == null) {
                    accountTradId = trdProjectNetInvestTrade001Service.selectAccountTrdIdinwfl(
                            dto.getTradeId());
                }
                List<BussTrdTransferOrderBase> bttob = new ArrayList<>();
                if (accountTradId != null) {
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
                        dto.setBussTrdTransferOrder(bussTrdTransferOrderService.save(bttodList));
                    }
                }
            }
            Map<String, Object> map = trdProjectNetInvestTrade003Service.getTradeRefrence(
                    dto.getTradeId());
            if (map.get("feeAmtPay") != null) {
                dto.setRedemptionFee(new BigDecimal((String)map.get("feeAmtPay")));
            }
            if (map.get("channalFee") != null) {
                dto.setChannelFee(new BigDecimal((String)map.get("channalFee")));
            }
            if (map.get("hostingFee") != null) {
                dto.setHostingFee(new BigDecimal((String)map.get("hostingFee")));
            }
            processParameter.setPageId("TrdProjectNetInvestTrade003");
            params.setPageId("TrdProjectNetInvestTrade003");
        }
        params.setElementId(this.getParam("elementId"));
        params.setBusinessCode(item -> item.getTradeId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
        // 设置流程业务说明, entity即传入的dto
        // “交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日
        params.setComment((item) -> WorkFlowUtil.concatComment(
                getMessage("TrdPlanNetInvestTrade002.tradeId"), item.getTradeId(),
                getMessage("TrdPlanNetInvestTrade002.finprodName"),
                item.getFinprodName(),
                getMessage("TrdPlanNetInvestTrade002.tradeDate"),
                DateUtil.format(item.getTradeDate())));
        processParameter.setWorkFlowParameter(params);
        dto.setOperateType(entity.getOperateType());
        dto.setbTradeId(dto.getTradeId());
        String msg = trdProjectNetInvestTrade001Service.start(processParameter,dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId() {
        return trdProjectNetInvestTrade001Service.getFinprodMarketId();
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
        return trdProjectNetInvestTrade001Service.export(this.getPageId(),
                param);
    }
}
