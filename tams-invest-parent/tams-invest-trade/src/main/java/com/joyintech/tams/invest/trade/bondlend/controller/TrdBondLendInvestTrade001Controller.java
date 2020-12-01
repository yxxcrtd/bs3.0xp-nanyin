/*
 * 文件名：TrdBondInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.bondlend.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.bondlend.dto.TrdBondLendInvestTradeDto;
import com.joyintech.tams.invest.trade.bondlend.dto.TrdBondLendInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.bondlend.service.TrdBondLendInvestTrade001Service;
import com.joyintech.tams.invest.trade.bondlend.service.TrdBondLendInvestTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券借贷交易管理
 *
 * @author 裴家俊
 * @version 1.0 2019年6月25日
 * @see TrdBondLendInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdBondLendInvestTrade001")
@RequestMapping("/invest/trade/bondlend/TrdBondLendInvestTrade001")
public class TrdBondLendInvestTrade001Controller extends BaseController {

    @Autowired
    private TrdBondLendInvestTrade001Service trdBondLendInvestTrade001Service;

    @Autowired
    private TrdBondLendInvestTrade002Service trdBondLendInvestTrade002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易流程状态, DictCode.回购拆借方向);
        return super.init(model);
    }
    /**
     * 分页查询
     *
     * @return Page<TrdBondLendInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdBondLendInvestTradeDto> findPage()
        throws Exception {
        Page<TrdBondLendInvestTradeDto> pageRet = trdBondLendInvestTrade001Service.findPage(RequestUtil.getPageParam());
        return  pageRet;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     *
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdBondLendInvestTrade001Service.export(this.getPageId(), RequestUtil.getPageParam());
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
    public AjaxResult check(@Valid @RequestBody TrdBondLendInvestTradeRevokeDto inputDto,
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
    AjaxResult doRevoke(@RequestBody TrdBondLendInvestTradeDto inputDto)
        throws Exception {

        ProcessParameter<TrdBondLendInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondLendInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdBondLendInvestTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdBondLendInvestTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_债券借贷交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("TrdBondLendInvestTrade002.tradeId") + GlobalConstant.COLON + entity.getTradeId(),
            getMessage("TrdBondLendInvestTrade002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
            getMessage("TrdBondLendInvestTrade002.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));

        String msg = trdBondLendInvestTrade002Service.start(processParameter, inputDto);

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
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_债券借贷融入.getValue(),
                DictCodeListExt1.金融产品类型_债券借贷融出.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdBondLendInvestTrade001Service.getEntrustIdByType(map);
    }
}
