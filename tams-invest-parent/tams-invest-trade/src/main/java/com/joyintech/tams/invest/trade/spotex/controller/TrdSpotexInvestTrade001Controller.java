/*
 * 文件名：TrdSpotexInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.spotex.controller;

import javax.validation.Valid;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeDto;
import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.spotex.service.TrdSpotexInvestTrade001Service;
import com.joyintech.tams.invest.trade.spotex.service.TrdSpotexInvestTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外汇即期交易查询Controller
 * 
 * @author wangguoxian
 * @version 1.0 2019年7月2日
 * @see TrdSpotexInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdSpotexInvestTrade001")
@RequestMapping("/invest/trade/spotex/TrdSpotexInvestTrade001")
public class TrdSpotexInvestTrade001Controller extends BaseController {

    /**
     * TrdSpotexInvestTrade001Service
     */
    @Autowired
    private TrdSpotexInvestTrade001Service trdSpotexInvestTrade001Service;
    
    /**
     * TrdSpotexInvestTrade002Service
     */
    @Autowired
    private TrdSpotexInvestTrade002Service trdSpotexInvestTrade002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.外汇交易类型, DictCode.货币对, DictCode.交易方向,
            DictCode.交易流程状态);
        model.addAttribute("VALID_STATUS_EFFECTIVE",DictCodeListExt1.交易流程状态_有效.getValue());
        return super.init(model);
    }

    /**
     * 分页查询
     * 
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdSpotexInvestTradeDto> findPage()
        throws Exception {
        Page<TrdSpotexInvestTradeDto> resultDto = trdSpotexInvestTrade001Service.findPage(RequestUtil.getPageParam());
        return resultDto;
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
        ValidateGroupDelete.class}) TrdSpotexInvestTradeDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 撤销前check
     *
     * @param inputDto 画面输入参数dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @RequestMapping("checkRevoke")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody TrdSpotexInvestTradeRevokeDto inputDto,
                            BindingResult result) {
        return commonCheck(result, inputDto.getOperateType());
    }
    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody TrdSpotexInvestTradeDto inputDto)
        throws Exception {

        List<TrdSpotexInvestTradeDto> selectedItems = inputDto.getSelectedItem();
        TrdSpotexInvestTradeDto spotexInvestTradeDto = selectedItems.get(0);
        ProcessParameter<TrdSpotexInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdSpotexInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdSpotexInvestTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdSpotexInvestTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_外汇即期投资交易.getValue());
        /** modify by yanya on 20190930 【外汇即期交易】撤销流程中业务描述不正确修复  start
        workFlowParameter.setComment((entity) -> this.getMessage("TrdSpotexInvestTrade.tradeId") + "("
            + this.getMessage("TrdSpotexInvestTrade.tradeDate") + "("
            + DateUtil.format(entity.getTradeDate()) + ")");**/
        workFlowParameter.setComment((entity) -> this.getMessage("TrdSpotexInvestTrade.tradeId") + "("
            + entity.getTradeId() + ")" + "-"
            + this.getMessage("TrdSpotexInvestTrade.curPair") + "("
            + entity.getCurPair() + ")" + "-"
            + this.getMessage("TrdSpotexInvestTrade.tradeDate") + "("
            + DateUtil.format(entity.getTradeDate()) + ")");
        /** modify by yanya on 20190930 【外汇即期交易】撤销流程中业务描述不正确修复  end**/
        String msg = trdSpotexInvestTrade002Service.start(processParameter, spotexInvestTradeDto);

        return new AjaxResult(msg);
    }

    /**
     * 导出
     * 
     * @return 导出结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdSpotexInvestTrade001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
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
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_即期.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdSpotexInvestTrade001Service.getEntrustIdByType(map);
    }
}
