/*
 * 文件名：TrdRepoTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdrepotrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade001Dto;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTradeRevokeDto;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade001Service;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
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
 * 回购投资交易管理
 * 〈功能详细描述〉
 *
 * @author 裴家俊
 * @version 1.0 2018年12月18日
 * @see TrdRepoTrade001Controller
 * @since 1.0
 */
@PageId("TrdRepoTrade001")
@RequestMapping("/invest/trade/trdrepotrade/TrdRepoTrade001")
@Controller
public class TrdRepoTrade001Controller extends BaseController {

    /**
     * TrdRepoTrade001Service
     */
    @Autowired
    private TrdRepoTrade001Service trdRepoTrade001Service;

    /**
     * TrdRepoTrade002Service
     */
    @Autowired
    private TrdRepoTrade002Service trdRepoTrade002Service;

    @Override
    public String init(Model model) {
        String fTradeId = this.getParam("fTradeId");
        String entrustId = this.getParam("entrustId");
        this.setSelectOptionToModel(model, DictCode.回购拆借方向, DictCode.回购类型,
                DictCode.交易流程状态);
        // 回购类型_质押式协议回购
        model.addAttribute("TARGET_BACK_TYPE_H01", DictCodeList.回购类型_质押式协议回购.getValue());
        // 回购拆借方向_正回购
        model.addAttribute("BACK_LEND_DIRECTION_03", DictCodeList.回购拆借方向_正回购.getValue());
        if(StringUtils.isNotBlank(fTradeId)){
            model.addAttribute("fTradeId",fTradeId);
        }
        if(StringUtils.isNotBlank(entrustId)){
            model.addAttribute("entrustId",entrustId);
        }
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
    @RequestMapping("selectForPage")
    @ResponseBody
    public Page<TrdRepoTrade001Dto> selectForPage() throws Exception {
        Page<TrdRepoTrade001Dto> trdRepoTrade001DtoPage = trdRepoTrade001Service.selectForPage(RequestUtil.getPageParam());
        return trdRepoTrade001DtoPage;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        return trdRepoTrade001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

    /**
     * 导出正回购邮件模版excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("exportMailModel")
    public ResponseEntity<byte[]> exportMailModel()
            throws Exception {
        return trdRepoTrade001Service.exportMailModel(RequestUtil.getPageParam());
    }

    /**
     * 撤销前check
     *
     * @param inputDto 画面输入参数dto
     * @param result   BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @RequestMapping("checkRevoke")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody TrdRepoTradeRevokeDto inputDto,
                            BindingResult result) {
        return commonCheck(result, inputDto.getOperateType());
    }

    /**
     * 撤销
     *
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody TrdRepoTrade002Dto inputDto)
            throws Exception {

        ProcessParameter<TrdRepoTrade002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdRepoTrade002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdRepoTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdRepoTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_质押式回购交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdRepoTrade002.tradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                DictCodeListExt1.交易场所_银行间.getValue().equals(inputDto.getTradeMarket())?"":getMessage("TrdRepoTrade002.repoType") + GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(DictCode.回购类型, entity.getRepoType()) + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdRepoTrade002.tradeDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS));

        String msg = trdRepoTrade002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }

    /**
     * 获取委托代码
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntrustIdByType")
    public List<SuggestDto> getEntrustIdByType() {
        String entrustId = getParam("param");
        Map<String, Object> map = new HashMap<>();
        map.put("param", entrustId);
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_债券质押式正回购.getValue(),
                DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdRepoTrade001Service.getEntrustIdByType(map);
    }

}
