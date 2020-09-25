package com.joyintech.tams.invest.trade.option.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeDto;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.option.dto.TrdOptionTrade001Dto;
import com.joyintech.tams.invest.trade.option.dto.TrdOptionTrade002Dto;
import com.joyintech.tams.invest.trade.option.dto.TrdOptionTradeRevokeDto;
import com.joyintech.tams.invest.trade.option.service.TrdOptionTrade001Service;
import com.joyintech.tams.invest.trade.option.service.TrdOptionTrade002Service;
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
import java.util.List;

/**
 * 期权交易查询
 */
@Controller
@PageId("TrdOptionTrade001")
@RequestMapping("/invest/trade/option/TrdOptionTrade001")
public class TrdOptionTrade001Controller extends BaseController {
    @Autowired
    private TrdOptionTrade001Service trdOptionTrade001Service;

    @Autowired
    private TrdOptionTrade002Service trdOptionTrade002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.结构类型, DictCode.行权方式, DictCode.交易流程状态);
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return Page<TrdOptionTrade001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdOptionTrade001Dto> findPage() throws Exception {
        return trdOptionTrade001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 合约标的下拉框
     *
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("selectFinprodIdAbbr")
    @ResponseBody
    public List<SelectOptionDto> selectFinprodIdAbbr(){
        return trdOptionTrade001Service.selectFinprodIdAbbr(RequestUtil.getPageParam());
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
        return trdOptionTrade001Service.export(this.getPageId(), RequestUtil.getPageParam());
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
    public AjaxResult check(@Valid @RequestBody TrdOptionTradeRevokeDto inputDto,
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
    AjaxResult doRevoke(@RequestBody TrdOptionTrade002Dto inputDto)
            throws Exception {

        ProcessParameter<TrdOptionTrade002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdOptionTrade002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdOptionTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdOptionTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTotleTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_期权交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdOptionTrade001.totleTradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdOptionTrade001.contractType") + GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(DictCode.结构类型, entity.getContractType()) + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdOptionTrade001.tradeDate")+ GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = trdOptionTrade002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
}
