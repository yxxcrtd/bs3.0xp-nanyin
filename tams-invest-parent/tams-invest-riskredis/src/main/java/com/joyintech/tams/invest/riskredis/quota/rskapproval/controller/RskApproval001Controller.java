package com.joyintech.tams.invest.riskredis.quota.rskapproval.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskApproval001Dto;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskIsupTrialDto;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.service.RskApprovalPage001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;

/**
 * 委托审批
 * 
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RskApproval001Controller
 * @since 1.0
 */
@Controller
@PageId("RskApproval001")
@RequestMapping("/invest/riskredis/quota/rskapproval/RskApproval001")
public class RskApproval001Controller extends RskApprovalController {

    /**
     * 模糊委托审批service
     */
    @Autowired
    private RskApprovalPage001Service rskApprovalSrc;

    /**
     * 接口日志查询的初始化
     * 
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCodeRds.单位, BussinessDictCodeRds.交易方向,
            BussinessDictCodeRds.交易场所);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        RskApproval001Dto dto = new RskApproval001Dto();
        if (StringUtil.isNotEmpty(workflowId)) {
            dto = rskApprovalSrc.getBussinessEntityByWorkflowId(workflowId);
            if (dto.getPriceAmt() == null || dto.getPriceAmt().compareTo(BigDecimal.ZERO) == 0){
                dto.setPriceAmt(dto.getAllPrice());
            }
            List<RskIsupTrial> clsList = rskApprovalSrc.selectConclusion(dto.getEventId());
            if (!StringUtil.isEmptyList(clsList)) {
                String conclusion = clsList.get(0).getConclusion();
                for (RskIsupTrial en : clsList) {
                    if (StringUtils.equals(en.getSeqNo(), dto.getUuid())) {
                        conclusion = en.getConclusion();
                        break;
                    }
                }
                dto.setConclusion(SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标状态, conclusion));
                if (StringUtil.isEmpty(dto.getUuid())) {
                    dto.setUuid(clsList.get(0).getSeqNo());
                }
            }
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }

    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskIsupTrialDto> findPage() {
        return rskApprovalSrc.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 保存
     * 
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws WorkFlowException
     * @throws Exception 异常
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskApproval001Dto dto, BindingResult br)
        throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskApproval001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RskApproval001Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 修改时为画面所选记录的委托编号。
        params.setBusinessCode(StringUtil.isEmpty(dto.getEntrustCode()) ? dto.getEventIdStr() : dto.getEntrustCode());
        // 业务类型
        String bsType = getBusinessType(dto.getAssetType(), dto.getPs(), dto.getEventType());
        params.setBusinessType(bsType);
        // 设置业务日期
        params.setCrtDate(getSysDate());
        // 业务说明
        params.setComment(
            entity -> SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.共通业务类型, bsType)
                      + GlobalConstant.SPLIT + SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易方向, entity.getPs())
                      + GlobalConstant.SPLIT + DateUtil.format(entity.getStartDate())
                      + GlobalConstant.SPLIT + DateUtil.format(entity.getEndDate()));
        processParameter.setWorkFlowParameter(params);
        String msg = rskApprovalSrc.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
