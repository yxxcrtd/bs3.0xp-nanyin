/*
 * 文件名：TrdOutRightRepoTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2019年01月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.buyoutrepo.controller;

import javax.validation.Valid;

import com.joyintech.framework.select.SuggestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTrade001Dto;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTrade002Dto;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTrade005Dto;
import com.joyintech.tams.invest.trade.buyoutrepo.service.TrdOutRightRepoTrade001Service;
import com.joyintech.tams.invest.trade.buyoutrepo.service.TrdOutRightRepoTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("TrdOutRightRepoTrade001")
@RequestMapping("/invest/trade/buyoutrepo/TrdOutRightRepoTrade001")
public class TrdOutRightRepoTrade001Controller extends BaseController {
	
	/**
	 * {@linkplain}
	 */
	@Autowired
	private TrdOutRightRepoTrade001Service trdOutRightRepoTrade001Service;

	/**
	 * {@linkplain}
	 */
	@Autowired
	private TrdOutRightRepoTrade002Service trdOutRightRepoTrade002Service;
	
	
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.回购拆借方向, DictCode.交易流程状态);
		
		return super.init(model);
	}
	
	/**
     * 画面查詢方法
     * 
     * @return 所有數據
     * @throws Exception 異常
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<TrdOutRightRepoTrade001Dto> selectForPage() {
        return trdOutRightRepoTrade001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdOutRightRepoTrade001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
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
    public AjaxResult check(@Valid @RequestBody TrdOutRightRepoTrade005Dto inputDto,
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
    AjaxResult doRevoke(@RequestBody TrdOutRightRepoTrade002Dto inputDto)
            throws Exception {

        ProcessParameter<TrdOutRightRepoTrade002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdOutRightRepoTrade002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdOutRightRepoTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdOutRightRepoTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_买断式回购交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdOutRightRepoTrade001.table.busiId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdOutRightRepoTrade001.table.ps") + GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, entity.getPs()) + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdOutRightRepoTrade001.table.dealDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = trdOutRightRepoTrade002Service.start(processParameter, inputDto);

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
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_债券买断式正回购.getValue(),
                DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdOutRightRepoTrade001Service.getEntrustIdByType(map);
    }

}
