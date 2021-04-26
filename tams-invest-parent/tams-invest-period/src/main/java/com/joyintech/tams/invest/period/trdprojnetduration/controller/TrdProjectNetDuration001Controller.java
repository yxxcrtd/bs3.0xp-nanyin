package com.joyintech.tams.invest.period.trdprojnetduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdplannetduration.dto.TrdPlanNetDuration001Dto;
import com.joyintech.tams.invest.period.trdprojnetduration.dto.TrdProjectNetDurationDto;
import com.joyintech.tams.invest.period.trdprojnetduration.service.TrdProjectNetDuration001Service;
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

@PageId("TrdProjectNetDuration001")
@RequestMapping("/invest/period/trdprojnetduration/TrdProjectNetDuration001")
@Controller
public class TrdProjectNetDuration001Controller extends BaseController {
    /**
     * 基金存续期service
     */
    @Autowired
    private TrdProjectNetDuration001Service trdProjectNetDuration001Service;
    /**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.画面数据状态
                ,DictCode.分红方式,DictCode.行内资产二级分类);
        model.addAttribute("BONUS_MODE_02", DictCodeList.分红方式_红利再投.getValue());
        model.addAttribute("BONUS_MODE_01", DictCodeList.分红方式_现金分红.getValue());
        model.addAttribute("PS_T11",DictCodeList.交易方向_现金分红.getValue());
        model.addAttribute("PS_T12",DictCodeList.交易方向_红利再投.getValue());
        model.addAttribute("PS_T13", DictCodeList.交易方向_除权登记.getValue());
        model.addAttribute("PAGE_DATA_STATUS_05", DictCodeListExt1.画面数据状态_有效.getValue());
        return super.init(model);
    }

    /**
     * 获取利息明细列表
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo() {
        return trdProjectNetDuration001Service.findAssetIntInfo(RequestUtil.getParameters(getRequest()));
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
        return trdProjectNetDuration001Service.getPortfolioId();
    }
    /**
     * 分页查询 市场代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdProjectNetDuration001Service.getFinprodMarketId();
    }
    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdProjectNetDurationDto> findPage() {
        return trdProjectNetDuration001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return trdProjectNetDuration001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping("getBusiCode")
    public Page<SelectOptionDto> getBusiCode() {
        return trdProjectNetDuration001Service.getBusiCode();
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdProjectNetDurationDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusValid", dto.getStatusValid());
        paramMap.put("totleTradeId", dto.getTotleTradeId());
        TrdProjectNetDurationDto temp = trdProjectNetDuration001Service.findEffectiveData(paramMap);
        paramMap.put("busiCode", dto.getTotleTradeId());
        temp.setBussTrdTransferOrder(periodCommonService.selectPayOrders(temp.getTotleTradeId()));
        temp.setFinAssetIntInfoList(trdProjectNetDuration001Service.findAssetIntInfo(paramMap));
        temp.setBaseTotalTradeInfoDto(trdProjectNetDuration001Service.genFinProductTradeDto(temp));
        ProcessParameter<TrdProjectNetDurationDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdProjectNetDurationDto> params = new WorkFlowParameter<>();
        params.setPageId("TrdProjectNetDuration002");
        params.setElementId(dto.getElementId());
//        模式为撤销
        temp.setMode(OperationType.REVOKE.toString());
        temp.setpTradeId(temp.getTotleTradeId());
        temp.setTotleTradeId(temp.getTotleTradeId()+'R');
        params.setBusinessCode(temp.getTotleTradeId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产存续期.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(getMessage("TrdPlanNetDuration001.finprodMarketName") + GlobalConstant.OPEN_PARENTHESIS+temp.getFinprodMarketName() +
                GlobalConstant.CLOSE_PARENTHESIS, getMessage("TrdPlanNetDuration001.assetType") + GlobalConstant.OPEN_PARENTHESIS +
                DictCodeListExt3.平安资产类型_权益类资产.getCodeName() + GlobalConstant.CLOSE_PARENTHESIS, getMessage("TrdPlanNetDuration001.tradeId") + GlobalConstant.OPEN_PARENTHESIS + temp.getTotleTradeId() +
                GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        String msg = trdProjectNetDuration001Service.start(processParameter, temp);
        return new AjaxResult(msg);
    }
    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdProjectNetDurationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}
