package com.joyintech.tams.invest.trade.bondtostock.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.bondtostock.dto.TrdBondToStock001Dto;
import com.joyintech.tams.invest.trade.bondtostock.service.TrdBondToStock001Service;
import com.joyintech.tams.invest.trade.bondtostock.service.TrdBondToStock002Service;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 可转债转股
 *
 * @author 丁志鹏
 * @version 1.0 2019年11月14日
 * @see TrdBondToStock001Controller 可转债转股管理画面001
 * @since 1.0
 */
@Controller
@PageId("TrdBondToStock001")
@RequestMapping("/invest/trade/bondtostock/TrdBondToStock001")
public class TrdBondToStock001Controller extends BaseController {
    @Autowired
    private TrdBondToStock001Service trdBondToStock001Service;
    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     *  业务service
     */
    @Autowired
    private TrdBondToStock002Service trdBondToStock002Service;

    /**
     * 划款指令组件
     */
    @Autowired
    private BussSettleOrderCommService bussSettleOrderCommService;
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.有效状态,DictCode.交易场所);
        return super.init(model);
    }
    /**
     * 分页查询
     *
     * @return Page<TrdBondToStock001Dto>泛型的page实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdBondToStock001Dto> findPage() {
        return trdBondToStock001Service.findPage(RequestUtil.getPageParam());
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
        return trdBondToStock001Service.export(this.getPageId(), RequestUtil.getPageParam());
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
    public AjaxResult check(@Validated({ValidateGroupDelete.class}) @RequestBody TrdBondToStock001Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
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
    AjaxResult doRevoke(@RequestBody TrdBondToStock001Dto inputDto)
            throws Exception {
        //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm start 20191015
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("TrdBondToStock002");
        parameter.setBusinessDataPk1(inputDto.getBusiCode());
        if (!mstWorkflowCommonService.isProcessData(parameter)){
            inputDto = trdBondToStock002Service.findEffectData(inputDto.getBusiCode());
            //划款指令
            if (inputDto != null && StringUtil.isEmptyList(inputDto.getBussTrdTransferOrder())){
                List<BussTrdTransferOrderBase> orderDtolist = bussSettleOrderCommService.selectOrdersByBizCode(inputDto.getBusiCode()) ;
                if (!StringUtil.isEmptyList(orderDtolist)){
                    List<BussTrdTransferOrderDto> bussTrdTransferOrderDtoList = new ArrayList<BussTrdTransferOrderDto>();
                    BussTrdTransferOrderDto bussTrdTransferOrderDto = new BussTrdTransferOrderDto();
                    BeanUtil.copyPropertiesIgnoreNull(bussTrdTransferOrderDto, orderDtolist.get(0));
                    bussTrdTransferOrderDtoList.add(bussTrdTransferOrderDto);
                    inputDto.setBussTrdTransferOrder(bussTrdTransferOrderDtoList);
                }
            }
        }
        //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm end 20191015
        ProcessParameter<TrdBondToStock001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondToStock001Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdBondToStock002");
        processParameter.setOperationType(OperationType.REVOKE);
        workFlowParameter.setPageId("TrdBondToStock002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getBusiCode());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_可转债转股交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdBondToStock001.tradeId") + GlobalConstant.COLON + entity.getBusiCode(),
                getMessage("TrdBondToStock001.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
                getMessage("TrdBondToStock001.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));
        String msg = trdBondToStock002Service.start(processParameter, inputDto);
        return new AjaxResult(msg);
    }
}
