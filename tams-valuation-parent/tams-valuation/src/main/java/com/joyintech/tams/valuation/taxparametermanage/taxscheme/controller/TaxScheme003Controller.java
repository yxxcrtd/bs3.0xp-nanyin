/*
 * 文件名：TaxScheme003.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme003Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme001Service;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme003Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 税务方案 - 增值税则表
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme003Controller
 * @since 1.0
 */
@Controller
@PageId("TaxScheme003")
@RequestMapping("/valuation/taxparametermanage/taxscheme/TaxScheme003")
public class TaxScheme003Controller extends BaseController {

    @Autowired
    private TaxScheme001Service taxScheme001Service;

    @Autowired
    private TaxScheme003Service taxScheme003Service;

    /**
     * 税务方案 - 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String schId = getParam("schId");
        String seqNo = getParam("seqNo");
        String operateType = getParam("operateType");
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        TaxScheme003Dto dto = new TaxScheme003Dto();
        if (StringUtils.isNotEmpty(seqNo)) { // 查看 、 修改
            dto = taxScheme003Service.findByPrimaryKey(seqNo);
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = taxScheme003Service.getBussinessEntityByWorkflowId(workflowId);

            seqNo = dto.getSeqNo(); // 避免流程，再生成主键
        }

        if (StringUtils.isEmpty(seqNo) || "copy".equals(operateType)) { // 新建 、复制
            dto.setSeqNo(IDGenerator.generate()); // 主键是 Sequence，这里仅供流程参考
            dto.setSchId(schId);
        }
        dto.setOperateType(operateType);
        model.addAttribute("entity", dto);

        super.setSelectOptionToModel(model, DictCode.增值税资产分类);
        super.setSelectOptionToModel(model, DictCode.本金收益特征);
        super.setSelectOptionToModel(model, DictCode.增值税类型2); // 收入类型
        super.setSelectOptionToModel(model, DictCode.计税方式); // 计税方式 - 应税标识
        super.setSelectOptionToModel(model, DictCode.免税收入类型);

        return super.init(model);
    }

    /**
     * 提交/保存 check
     * 
     * @param dto
     * @param result
     * @param operateType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TaxScheme003Dto dto,
                            BindingResult bindingResult)
        throws Exception {
        String msg = StringUtils.EMPTY;
        String operateType = dto.getOperateType();

        if (!bindingResult.hasErrors()) { // 基础验证通过
            // 日期检查：生效日必须大于等于平台日。
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // Date effDate = sdf.parse(dto.getEffDate());
            // Date sysDate = sdf.parse(DateUtil.getSysDate());
            // if (effDate.before(sysDate)) {
            // msg = "生效日必须大于等于平台日。";
            // return new AjaxResult(msg, MessageType.错误消息);
            // }

            // 收入类型为贷款业务收入、应税标识为免税、本金收益特征为保本时，免税收入类型为必填项
            // if(DictCodeList.增值税类型2_贷款业务收入.getValue().equals(dto.getIncomeType())
            // && DictCodeList.计税方式_免税.getValue().equals(dto.getTaxableSign())
            // && DictCodeListExt1.本金收益特征_保本.getValue().equals(dto.getCapiIncomeFeature())) {
            // if(StringUtils.isEmpty(dto.getFeeType())) {
            // msg = "免税收入类型不能为空!";
            // return new AjaxResult(msg, MessageType.错误消息);
            // }
            // }

            // 唯一性验证：资产分类+本金收益特征+收入类型+生效日组成唯一性数据(同方案下)
            Map<String, Object> params = Maps.newHashMap();
            params.put("schId", dto.getSchId());
            params.put("assetClassify", dto.getAssetClassify());
            params.put("capiIncomeFeature", dto.getCapiIncomeFeature());
            params.put("incomeType", dto.getIncomeType());
            params.put("effDate", dto.getEffDate());
            if (GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {// 修改时忽略本身
                params.put("owner", dto.getSeqNo());
            }else{
                params.put("owner","");
            }
//            long count = taxScheme001Service.findVatTaxByPage(params).getRecords();
            List<TaxScheme003Dto> checkResultList = taxScheme003Service.checkVatTax(params);
//            if (count > 0) {
          if (!checkResultList.isEmpty()) {
                msg = "税则表中已存在资产分类_本金收益特征_收入类型_生效日相同的数据，请确保数据唯一性。";
                return new AjaxResult(msg, MessageType.错误消息);
            }

        }

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 新建或修改 并启动流程
     * 
     * @param dto
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody TaxScheme003Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<TaxScheme003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme003Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSeqNo());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment("增值税则表", entity.getSeqNo()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            taxScheme003Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }

}
