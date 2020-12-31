/*
 * 文件名：TrdFxswapInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdfxswapinvesttrade.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.trdfxswapinvesttrade.dto.TrdFxswapInvestTrade001Dto;
import com.joyintech.tams.invest.trade.trdfxswapinvesttrade.service.TrdFxswapInvestTrade001Service;
import com.joyintech.tams.invest.trade.trdfxswapinvesttrade.service.TrdFxswapInvestTrade002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 外汇掉期交易查询
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdFxswapInvestTrade001")
@RequestMapping("/invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade001")
public class TrdFxswapInvestTrade001Controller extends BaseController {

    /*
     * 注入service
     */
    @Autowired
    private TrdFxswapInvestTrade001Service trdFxswapInvestTrade001Service;

    /*
     * 注入service
     */
    @Autowired
    private TrdFxswapInvestTrade002Service trdFxswapInvestTrade002Service;

    /**
     * 初始化方法 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易流程状态, DictCode.币种,
            DictCode.外汇掉期期限类型, DictCode.货币对, DictCode.外汇期限, DictCode.外汇交易类型, DictCode.我方角色,
            DictCode.外汇交易模式, DictCode.结算方式);
        model.addAttribute("TRADE_STATUS", DictCodeListExt1.交易流程状态_有效.getValue());
        return super.init(model);
    }

    /**
     * 获取委托代码
     * 
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntrustId")
    public List<SuggestDto> getEntrustId() {
        String param = getParam("param");
        return trdFxswapInvestTrade001Service.getEntrustId(param);
    }

    /**
     * 获取组合代码
     * 
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return trdFxswapInvestTrade001Service.getPortfolioId(map);
    }

    /**
     * 分页查询
     * 
     * @return Page<TrdFxswapInvestTrade001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdFxswapInvestTrade001Dto> findPage()
        throws Exception {
        return trdFxswapInvestTrade001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 画面导出功能实现
     * 
     * @return ResponseEntity 确认的实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdFxswapInvestTrade001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 删除按钮点击时的check
     * 
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdFxswapInvestTrade001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除操作
     * 
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdFxswapInvestTrade001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdFxswapInvestTrade001Dto> processParameter = new ProcessParameter<TrdFxswapInvestTrade001Dto>();
        WorkFlowParameter<TrdFxswapInvestTrade001Dto> params = new WorkFlowParameter<TrdFxswapInvestTrade001Dto>();
        params.setPageId("TrdFxswapInvestTrade002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getTradeId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_外汇掉期投资交易.getValue());
        // 业务说明：“交易编号：” + “交易日：”+画面.交易日
        params.setComment(entity -> this.getMessage("TrdFxswapInvestTrade001.tradeId") + "("
                                    + entity.getTradeId() + ")" + "-"
                                    + this.getMessage("TrdFxswapInvestTrade001.tradeDate") + "("
                                    + DateUtil.format(entity.getTradeDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = trdFxswapInvestTrade002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new TrdFxswapInvestTrade001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
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
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_掉期.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdFxswapInvestTrade001Service.getEntrustIdByType(map);
    }
}
