/*
 * 文件名：TrdFundDuration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdfundobversion.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdfundobversion.dto.TrdFundObversionDto;
import com.joyintech.tams.invest.period.trdfundobversion.service.TrdFundObversion001Service;
import com.joyintech.workflow.engine.constant.OperationType;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存续期基金折算
 * 
 * @author wangxuelei
 * @version 1.0 2019年10月15日
 * @see TrdFundObversion001Controller
 * @since 1.0
 */
@PageId("TrdFundObversion001")
@RequestMapping("/invest/period/trdfundduration/TrdFundObversion001")
@Controller
public class TrdFundObversion001Controller extends BaseController {
    /**
     * 基金存续期service
     */
    @Autowired
    private TrdFundObversion001Service trdFundDuration001Service;
    /**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * 画面初始化
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.画面数据状态,DictCode.基金类资产类型, DictCode.折算类型);
        model.addAttribute("F01", BussinessDictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("PAGE_DATA_STATUS_05", BussinessDictCodeList.画面数据状态_有效.getValue());
        return super.init(model);
    }
    /**
     * 分页查询 市场代码
     *
     * @return 下拉框集合
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdFundDuration001Service.getFinprodMarketId();
    }
    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return 查询结果
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdFundObversionDto> findPage() {
        return trdFundDuration001Service.findPage(RequestUtil.getPageParam());
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
        return trdFundDuration001Service.export(this.getPageId(),
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
            ValidateGroupDelete.class}) TrdFundObversionDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusValid", dto.getStatusValid());
        paramMap.put("totleTradeId", dto.getTotleTradeId());
//        构建撤销dto
//        查询有效的数据
        TrdFundObversionDto temp = trdFundDuration001Service.findEffectiveData(paramMap);
        String tradeId = temp.getTotleTradeId();
        paramMap.put("busiCode", dto.getTotleTradeId());
        paramMap.put("type","obversion");
        paramMap.put("conversionTime", DateUtil.format(dto.getConversionTime()));
        paramMap.put("finprodMarketId",dto.getFinprodMarketId());
        paramMap.put("POSITION_TYPE03",DictCodeList.持仓类型_实际.getValue());
        paramMap.put("totleTradeId",dto.getTotleTradeId());
        List<FinAssetIntInfo> assetIntInfo = trdFundDuration001Service.selectObversionByTotleTradeId(paramMap);
        temp.setFinAssetIntInfoList(assetIntInfo);
        BaseTotalTradeInfoDto baseTotalTradeInfoDto = trdFundDuration001Service.genFinProductTradeDto(temp);
        temp.setBaseTotalTradeInfoDto(baseTotalTradeInfoDto);
        //        构建流程参数
        ProcessParameter<TrdFundObversionDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdFundObversionDto> params = new WorkFlowParameter<>();
        params.setPageId("TrdFundObversion002");
        params.setElementId(dto.getElementId());
//        模式为撤销
        temp.setMode(OperationType.REVOKE.toString());
        temp.setpTradeId(temp.getTotleTradeId());
        params.setBusinessCode(tradeId+'R');
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产存续期.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdDepoDuration001.finprodName"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketName()
                        + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdFundDuration001.assetType"),
                GlobalConstant.OPEN_PARENTHESIS  + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdFundDuration001.tradeId"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId()
                        + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        String msg = trdFundDuration001Service.start(processParameter, temp);
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
            ValidateGroupDelete.class}) TrdFundObversionDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}
