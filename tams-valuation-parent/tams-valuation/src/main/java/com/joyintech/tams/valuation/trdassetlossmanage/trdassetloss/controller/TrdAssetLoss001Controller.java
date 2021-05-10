/*
 * 文件名：ValSubjectSet001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.service.TrdAssetLossService;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 估值核算-财务管理-资产减值管理--查询
 *
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLoss001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdAssetLoss001")
@RequestMapping("/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss001")
public class TrdAssetLoss001Controller extends BaseController {

    @Autowired
    private TrdAssetLossService trdAssetLossService;
    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 初始化001查询页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.减值阶段);
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE") == null ? "01" : CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    /**
     *
     * 债券投资委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdAssetLoss001Dto> selectForPage(){
        Page<TrdAssetLoss001Dto> page = trdAssetLossService.selectForPage(
                RequestUtil.getPageParam());
        return page;
    }

    /**
     * 资产名称联想下拉框
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd()
            throws Exception {
        Page<SelectOptionDto> temp = trdAssetLossService.getfinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
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
    public AjaxResult checkRevoke(@Valid @RequestBody TrdAssetLoss001Dto inputDto,
                                  BindingResult result) {
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("TrdAssetLoss002");
        parameter.setBusinessDataPk2(inputDto.getTradeId() + "R");
        //parameter.setBusinessDataPk2("F01");
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(parameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
        if (StringUtil.isNotEmpty(inputDto.getValDate())) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            if (inputDto.getValDate().compareTo(DateUtil.parse(DateUtil.getSysDate())) != 0) {
                errorList.add(new ValidMessage("valDate", "减值行情日必须等于平台日"));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
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
    AjaxResult doRevoke(@RequestBody TrdAssetLoss001Dto inputDto)
            throws Exception {
        String operateType = inputDto.getOperateType();
        String tradeId = inputDto.getTradeId();
        String revokeReason = inputDto.getRevokeReason();
        inputDto = trdAssetLossService.selectByKey(inputDto.getTradeId());
        //划款指令
        inputDto.setRevokeReason(revokeReason);
        inputDto.setTradeId(tradeId + "R");
        inputDto.setOperateType(operateType);
        ProcessParameter<TrdAssetLoss001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdAssetLoss001Dto> workFlowParameter = new WorkFlowParameter<>();

        processParameter.setPageId("TrdAssetLoss002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdAssetLoss002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_资产减值管理.getValue());
        workFlowParameter.setComment(
                entity -> "交易代码："+entity.getTradeId()+"业务类型："+ DictCodeListExt3.共通业务类型_资产减值管理.getCodeName()+"交易日："+DateUtil.getSysDate());
        processParameter.setWorkFlowParameter(workFlowParameter);

        String msg = trdAssetLossService.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = paraMap = this.getRequestMap();
        List<TrdAssetLoss001Dto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = trdAssetLossService.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, TrdAssetLoss001Dto.class);
        }
        File outFile = trdAssetLossService.makeFile(
                "/com/joyintech/tams/valuation/trdassetlossmanage/trdassetloss/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(DateUtil.parse(DateUtil.getSysDate())) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        return paraMap;
    }

}
