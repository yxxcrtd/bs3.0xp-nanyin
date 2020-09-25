package com.joyintech.tams.invest.period.trdsecovertransfer.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration001Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration003Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration003Service;
import com.joyintech.tams.invest.period.trdsecovertransfer.service.TrdSecOverTransfer001Service;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus002Dto;
import com.joyintech.workflow.engine.WorkflowContext;
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
import java.util.Map;

@Controller
@PageId("TrdSecOverTransfer001")
@RequestMapping("/invest/period/trdsecovertransfer/TrdSecOverTransfer001")
public class TrdSecOverTransfer001Controller extends BaseController {
    /**
     * 业务service
     */
    @Autowired
    private TrdSecOverTransfer001Service trdSecOverTransfer001Service;
    /**
     * 业务service
     */
    @Autowired
    private TrdSecOverdueDuration003Service trdSecOverdueDuration003Service;
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.有效状态);
        return super.init(model);
    }

    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdSecOverdueDuration003Dto> findPage() {
        return trdSecOverTransfer001Service.findPage(RequestUtil.getPageParam());
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
        return trdSecOverTransfer001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }
    /**
     * 撤销前的数据检查
     *
     * @param dto 画面输入参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Validated({ValidateGroupDelete.class}) @RequestBody TrdSecOverdueDuration003Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *
     * TODO
     * @return AjaxResult
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("doRevoke")
    public AjaxResult revoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdSecOverdueDuration003Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessMark pk =  WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
        pk.setBusinessDataPk2(null);
        pk.setBusinessDataPk3(null);
        TrdSecOverdueDuration003Dto resDto = trdSecOverdueDuration003Service.getBussinessEntityByPk(pk);
        //构建撤销dto
        resDto.setpTradeId(dto.getTotleTradeId());
        resDto.setBusiCode(dto.getTotleTradeId());
        resDto.setTotleTradeId(dto.getTotleTradeId()+'R');
        resDto.setTradeStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        resDto.setOperateType(dto.getOperateType());
        //构建流程参数
        ProcessParameter<TrdSecOverdueDuration003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdSecOverdueDuration003Dto> params = new WorkFlowParameter<>();
        params.setPageId("TrdSecOverdueDuration003");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTotleTradeId());
        //模式为撤销
        params.setBusinessType(DictCodeListExt3.共通业务类型_已到期资产转让.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> getMessage("TrdSecOverTransfer001.portfolioId") + "("
                + entity.getPortfolioId() + ")-" +getMessage("TrdSecOverTransfer001.transferDate") + "("
                + DateUtil.format(entity.getTransferDate()) + ")-"+ getMessage("TrdSecOverTransfer001.finprodId") + "("+entity.getFinprodId()+")");
        processParameter.setWorkFlowParameter(params);
        String msg = trdSecOverdueDuration003Service.start(processParameter, resDto);
        return new AjaxResult(msg);
    }
}
