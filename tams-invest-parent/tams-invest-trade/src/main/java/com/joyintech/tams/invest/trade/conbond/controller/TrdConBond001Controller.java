/*
 * 文件名：trdConBond001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBond003Dto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBondDto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBondRevokeDto;
import com.joyintech.tams.invest.trade.conbond.service.TrdConBond001Service;
import com.joyintech.tams.invest.trade.conbond.service.TrdConBond002Service;
import com.joyintech.tams.invest.trade.conbond.service.TrdConBond003Service;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 可转债申购申请
 *
 * @author Zane.Y
 * @version 1.0 2019年11月14日
 * @see trdConBond001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdConBond001")
@RequestMapping("/invest/trade/conbond/TrdConBond001")
public class TrdConBond001Controller extends BaseController {

    @Autowired
    private TrdConBond001Service trdConBond001Service;

    @Autowired
    private TrdConBond002Service trdConBond002Service;
    
    @Autowired
    private TrdConBond003Service trdConBond003Service;

    /**
     * 划款指令组件
     */
    @Autowired
    private BussSettleOrderCommService bussSettleOrderCommService;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易场所,
            DictCode.交易流程状态, DictCode.申购类型, DictCode.申购方式);
        model.addAttribute("PS_T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("PS_T05", DictCodeList.交易方向_申购确认.getValue());
        return super.init(model);
    }
    /**
     * 分页查询
     *
     * @return Page<trdConBondDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdConBondDto> findPage()
        throws Exception {
        return trdConBond001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @param parameter 页面参数
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdConBond001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdConBondRevokeDto dto, BindingResult result) {
        /*//撤销时校验
         if (StringUtil.equals(dto.getOperateType(), "revoke")
             // 流程中数据check 修正 add by renping 20191020 start
             && result.getErrorCount() == 0
             // 流程中数据check 修正 add by renping 20191020 end
             ){

         }*/
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check2")
    public AjaxResult check2(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdConBond003Dto dto, BindingResult result) {
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
    AjaxResult doRevoke(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdConBondDto entity, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        TrdConBondDto dto = entity.getSelectedItem().get(0);
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
        	ProcessParameter<TrdConBondDto> processParameter = new ProcessParameter<TrdConBondDto>();
            WorkFlowParameter<TrdConBondDto> params = new WorkFlowParameter<TrdConBondDto>();
            processParameter.setWorkFlowParameter(params);
        	TrdConBondDto trdConBondDto = trdConBond002Service.getTrdConBondDto(dto.getBusiCode());
        	BeanUtil.copyPropertiesIgnoreNull(dto, trdConBondDto);
            processParameter.setPageId("TrdConBond002");
            params.setPageId("TrdConBond002");
            params.setElementId(this.getParam("elementId"));
            params.setBusinessCode(item -> item.getBusiCode());
            params.setBusinessType(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
            // 设置流程业务说明, entity即传入的dto
            // “交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日
            params.setComment((item) -> WorkFlowUtil.concatComment(
                getMessage("TrdConBond002.tradeId"), item.getBusiCode(),
                getMessage("TrdConBond002.finprodName"),
                GlobalConstant.SPLIT, item.getFinprodName(),
                getMessage("TrdConBond002.tradeDate"),
                DateUtil.format(item.getTradeDate())));
            processParameter.setWorkFlowParameter(params);
            dto.setOperateType(entity.getOperateType());
            dto.setbTradeId(dto.getBusiCode());
            String msg = trdConBond002Service.start(processParameter, dto);
            return new AjaxResult(msg);
        }
        else  {
        	ProcessParameter<TrdConBond003Dto> processParameter = new ProcessParameter<TrdConBond003Dto>();
            WorkFlowParameter<TrdConBond003Dto> params = new WorkFlowParameter<TrdConBond003Dto>();
            processParameter.setWorkFlowParameter(params);
        	TrdConBond003Dto confirmDto = trdConBond003Service.getTrdConBond003Dto(dto.getBusiCode());
            processParameter.setPageId("TrdConBond003");
            params.setPageId("TrdConBond003");
            params.setElementId(this.getParam("elementId"));
            params.setBusinessCode(item -> item.getBusiCode());
            params.setBusinessType(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
            // 设置流程业务说明, entity即传入的dto
            // “交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日
            params.setComment((item) -> WorkFlowUtil.concatComment(
                getMessage("TrdConBond002.tradeId"), item.getBusiCode(),
                getMessage("TrdConBond002.finprodName"),
                GlobalConstant.SPLIT, item.getFinprodName(),
                getMessage("TrdConBond002.tradeDate"),
                DateUtil.format(item.getTradeDate())));
            processParameter.setWorkFlowParameter(params);
            confirmDto.setOperateType(entity.getOperateType());
//            confirmDto.setbTradeId(confirmDto.getBusiCode());
            String msg = trdConBond003Service.start(processParameter, confirmDto);
            return new AjaxResult(msg);
        }
        
    }
}
