package com.joyintech.tams.invest.riskredis.quota.rskapproval.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskApproval004Dto;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskIsupTrialDto;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.service.RskApprovalPage004Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;

/**
 * 实占批量审批
 * 
 * @author chaowei
 * @version 1.0 2020年5月20日
 * @see RskApproval004Controller
 * @since 1.0
 */
@Controller
@PageId("RskApproval004")
@RequestMapping("/invest/riskredis/quota/rskapproval/RskApproval004")
public class RskApproval004Controller extends RskApprovalController {

    /**
     * 实占批量审批service
     */
    @Autowired
    private RskApprovalPage004Service rskApprovalSrc;


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
        RskApproval004Dto dto = new RskApproval004Dto();
        if (StringUtil.isNotEmpty(workflowId)) {
            dto = rskApprovalSrc.getBussinessEntityByWorkflowId(workflowId);
            if (dto.getPriceAmt() == null || dto.getPriceAmt().compareTo(BigDecimal.ZERO) == 0){
                dto.setPriceAmt(dto.getNetPrice());
            }
            List<RskIsupTrial> clsList = new ArrayList<RskIsupTrial>();
            for(Map rskMap : dto.getRsklist()) {
               String eventId= (String)rskMap.get("eventId");
                if(StringUtil.equals(DictCodeListExt1.交易方向_债转股.getValue(),(String)rskMap.get("ps"))) {
                    eventId = eventId+"T01";
                }

                List<RskIsupTrial> tmpList = rskApprovalSrc.selectConclusion(eventId);
                if (!StringUtil.isEmptyList(tmpList)) {
                    clsList.addAll(tmpList);
                    rskMap.put("uuid", tmpList.get(0).getSeqNo());
                }
            }


            if (!StringUtil.isEmptyList(clsList)) {
                String conclusion = clsList.get(0).getConclusion();
                for (RskIsupTrial en : clsList) {
                    if (StringUtils.equals(en.getSeqNo(), dto.getUuid())) {
                        conclusion = en.getConclusion();
                        break;
                    }
                }
                dto.setConclusion(SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标状态, conclusion));
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
    @RequestMapping("selectForList")
    public Page<RskIsupTrialDto> selectForList() {
        return rskApprovalSrc.selectForList(RequestUtil.getPageParam());
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
        ValidateGroupEdit.class}) RskApproval004Dto dto, BindingResult br)
        throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskApproval004Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RskApproval004Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 修改时为画面所选记录的委托编号。
        params.setBusinessCode(dto.getOrderID());
        // 业务类型
        String bsType = getBusinessType(dto.getAssetType(), dto.getPs(), dto.getEventType());
        params.setBusinessType(bsType);
        // 设置业务日期
        params.setCrtDate(getSysDate());
        // 业务说明
        params.setComment(
            entity -> SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.共通业务类型, bsType)
                      + (StringUtil.isNotEmpty(entity.getAssetCode()) ? 
          		            (GlobalConstant.SPLIT + entity.getAssetName()
                      + GlobalConstant.SPLIT + GlobalConstant.OPEN_PARENTHESIS + entity.getAssetCode() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                      + GlobalConstant.SPLIT + DateUtil.format(entity.getEventDate()));
        processParameter.setWorkFlowParameter(params);
        String msg = rskApprovalSrc.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
