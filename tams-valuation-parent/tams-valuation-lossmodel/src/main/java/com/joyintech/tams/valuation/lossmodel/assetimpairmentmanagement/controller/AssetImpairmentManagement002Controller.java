package com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.service.AssetImpairmentManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 */
@Controller
@PageId("AssetImpairmentManagement002")
@RequestMapping("/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement002")
public class AssetImpairmentManagement002Controller extends BaseController {

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;

    @Autowired
    private AssetImpairmentManagement002Service assetImpairmentManagement002Service;

    /**
     * 页面初始化
     * @param model
     * @return
     */
    @Override
    public String init(Model model) {
        String seqNo = getParam("seqNo");
        String operateType = getParam("operateType");
        AssetImpairmentManagement002Dto dto = new AssetImpairmentManagement002Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);

        if (StringUtils.isNotEmpty(seqNo)) {
            dto = assetImpairmentManagement002Service.findByPrimaryKey(seqNo);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            dto = assetImpairmentManagement002Service.getBussinessEntityByWorkflowId(workflowId);
            Map<String, Object> map = new HashMap<>();
            map.put("workflowId", workflowId);
            String status = assetImpairmentManagement002Service.getFirstActionType(map);
            model.addAttribute("actionStatus",status);
        }
        dto.setOperateType(operateType);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        model.addAttribute("sysDate", morenDate);

        //页面显示百分数
        dto.setPreProspectivePd(ArithmeticUtil.multiply(dto.getPreProspectivePd(), new BigDecimal(100)));
        dto.setProspectivePd(ArithmeticUtil.multiply(dto.getProspectivePd(), new BigDecimal(100)));
        dto.setLgd(ArithmeticUtil.multiply(dto.getLgd(), new BigDecimal(100)));

        super.setSelectOptionToModel(model, DictCode.减值阶段);
        super.setSelectOptionToModel(model, DictCode.是否);
        super.setSelectOptionToModel(model, DictCode.交易方向);
        super.setSelectOptionToModel(model, DictCode.资产五级分类);
        return super.init(model);
    }

    /**
     * 业务编号：下拉框
     *
     * @param
     * @return 业务编号：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBusiNoForImpairment")
    public List<SelectOptionDto> getBusiNoForImpairment(@RequestParam Map<String, Object> params) throws Exception {
        params.put("currentDate", DateUtil.format(DateUtil.platDateFromCache(), "yyyy-MM-dd"));
        return assetImpairmentManagement002Service.getBusiNoForImpairment(params);
    }

    /**
     * 资产代码：下拉框 资产减值管理
     *
     * @param
     * @return 业务编号：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodIdForImpairment")
    public List<SelectOptionDto> getFinprodIdForImpairment(@RequestParam Map<String, Object> params) throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return assetImpairmentManagement002Service.getFinprodIdForImpairment(params);
    }

    /**
     * 产品代码：下拉框 资产减值管理
     *
     * @param
     * @return 业务编号：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdIdForImpairment")
    public List<SelectOptionDto> getProdIdForImpairment(@RequestParam Map<String, Object> params) throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return assetImpairmentManagement002Service.getProdIdForImpairment(params);
    }

    /**
     *
     * 保存时，check操作
     * <功能详细描述>
     * @param dto MstSharePriceDto
     * @param result result
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) AssetImpairmentManagement002Dto dto, BindingResult result) {
        AjaxResult ar = commonCheck(result, dto.getOperateType());
        return ar;
    }

    /**
     * 新建或修改 并启动流程
     *
     * @param dto
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody AssetImpairmentManagement002Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<AssetImpairmentManagement002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<AssetImpairmentManagement002Dto> workFlowParameter = new WorkFlowParameter<>();

        if(!StringUtil.isNotEmpty(dto.getSeqNo())){
            String code = dBUtil.getSequenceIndexData(DataIndexSequenceType.处理序列);
            dto.setSeqNo(code);
        }

        try {
            workFlowParameter.setPageId(this.getPageId());
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
            workFlowParameter.setBusinessCode(dto.getSeqNo());
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_资产减值管理.getValue());
            workFlowParameter.setComment(
                    entity -> WorkFlowUtil.concatComment(
                            "组合代码" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getProdId(),
                            "资产代码" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getAssetId(),
                            "资产简称" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getAssetName(),
                            "交易日期" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getDecDateFmt()
                    ));
            processParameter.setWorkFlowParameter(workFlowParameter);

            assetImpairmentManagement002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0005");
        }
        catch (Exception e) {
            this.getLogger().error("启动资产减值管理流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }

}
