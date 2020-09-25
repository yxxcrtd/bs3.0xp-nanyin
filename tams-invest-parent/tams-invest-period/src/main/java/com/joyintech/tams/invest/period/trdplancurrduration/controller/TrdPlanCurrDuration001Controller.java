/*
 * 文件名：TrdPlanCurrDuration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人："??????é1?"
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdplancurrduration.controller;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdplancurrduration.dto.TrdPlanCurrDuration001Dto;
import com.joyintech.tams.invest.period.trdplancurrduration.service.TrdPlanCurrDuration001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 计划类货币型存续期
 * 〈功能详细描述〉
 * @author "丁志鹏"
 * @version 1.0 2019年1月29日
 * @see TrdPlanCurrDuration001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdPlanCurrDuration001")
@RequestMapping("/invest/period/trdplancurrduration/TrdPlanCurrDuration001")
public class TrdPlanCurrDuration001Controller extends BaseController{
	/**
	 * 存续期service
	 */
	@Autowired
	private TrdPlanCurrDuration001Service trdPlanCurrDuration001Service;
    /**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;
	/**
	 * {@inheritDoc}
	 */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, BussinessDictCode.画面数据状态
                ,BussinessDictCode.分红方式);
        model.addAttribute("PAGE_DATA_STATUS_05", BussinessDictCodeList.画面数据状态_有效.getValue());
        model.addAttribute("BONUS_MODE_02", BussinessDictCodeList.分红方式_红利再投.getValue());
        model.addAttribute("BONUS_MODE_01", BussinessDictCodeList.分红方式_现金分红.getValue());
        model.addAttribute("PS_T11",BussinessDictCodeList.交易方向_现金分红.getValue());
        model.addAttribute("PS_T12",BussinessDictCodeList.交易方向_红利再投.getValue());
        model.addAttribute("PS_T13", BussinessDictCodeList.交易方向_除权登记.getValue());
        return super.init(model);
    }
    /**
     * 获取所有组合代码
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public Page<SelectOptionDto> getPortfolioId() {
        return trdPlanCurrDuration001Service.getPortfolioId();
    }
    /**
     * 分页查询 市场代码
     *
     * @return 下拉框集合
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdPlanCurrDuration001Service.getFinprodMarketId();
    }
    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return 查询结果
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdPlanCurrDuration001Dto> findPage() {
        return trdPlanCurrDuration001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 导出
     *
     * @return 导出的文件流
     * @throws Exception 文件到处异常
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdPlanCurrDuration001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    /**
     * 撤销
     *
     * @return 撤销的入口
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdPlanCurrDuration001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusValid", dto.getStatusValid());
        paramMap.put("totleTradeId", dto.getTotleTradeId());
        paramMap.put("busiCode", dto.getTotleTradeId());
//        构建撤销dto
//        查询有效的数据
        TrdPlanCurrDuration001Dto temp = trdPlanCurrDuration001Service.findEffectiveData(paramMap);
//      查询利息信息
        dto.setFinAssetIntInfoList(trdPlanCurrDuration001Service.findAssetIntInfo(paramMap));
        temp.setBussTrdTransferOrder(periodCommonService.selectPayOrders(temp.getTotleTradeId()));
        temp.setFinAssetIntInfoList(trdPlanCurrDuration001Service.findAssetIntInfo(paramMap));
        temp.setBaseTotalTradeInfoDto(trdPlanCurrDuration001Service.genFinProductTradeDto(temp));
//        构建流程参数
        ProcessParameter<TrdPlanCurrDuration001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdPlanCurrDuration001Dto> params = new WorkFlowParameter<>();
        params.setPageId("TrdPlanCurrDuration002");
        params.setElementId(dto.getElementId());
//        模式为撤销
        temp.setMode(OperationType.REVOKE.toString());
        temp.setpTradeId(temp.getTotleTradeId());
        temp.setTotleTradeId(temp.getTotleTradeId()+'R');
        params.setBusinessCode(temp.getTotleTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_存续期.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdDepoDuration001.finprodName"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketName()
                        + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdFundDuration001.tradeId"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId()
                        + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        String msg = trdPlanCurrDuration001Service.start(processParameter, temp);
        return new AjaxResult(msg);
    }
    /**
     * 撤销check
     *
     * @return 返回check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdPlanCurrDuration001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}
