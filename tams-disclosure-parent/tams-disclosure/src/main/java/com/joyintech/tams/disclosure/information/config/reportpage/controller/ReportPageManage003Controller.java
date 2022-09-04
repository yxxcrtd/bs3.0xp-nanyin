/*
 * 文件名：ReportPageManage002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.information.config.reportpage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCode;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceCommentDto;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance002Service;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportStatusDto;
import com.joyintech.tams.disclosure.information.config.reportpage.service.ReportPageManage002Service;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月6日
 * @see ReportPageManage003Controller
 * @since 1.0
 */
@PageId("ReportPageManage003")
@Controller
@RequestMapping("/disclosure/information/reportpage/ReportPageManage003")
public class ReportPageManage003Controller extends BaseController {
    
    @Autowired
    private ReportPageManage002Service reportPageManage002Service;

    @Autowired
    private PlatformDate platformDate;

    @Autowired
    private InfoCompliance002Service infoCompliance002Service;
    
    /**
     * 模板启用,禁用界面
     */
    private Logger logger = LoggerFactory.getLogger(ReportPageManage003Controller.class);
    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.启用禁用);
        String repId = this.getParam("repId");
        String workflowType = this.getParam("workflowType");
        String workflowId = this.getParam("workflowId");

        String startRepId = repId;
        String offRepId = repId;

        //默认的启用或者禁用模板
        List<SelectOptionDto> reportInfos = reportPageManage002Service.getReportInfo();
        model.addAttribute("REPORT_NAME",JsonUtils.objectArray2Json(reportInfos.toArray()));

        //流程中
        if(StringUtil.isNotEmpty(workflowId)){
            Map<String,String> map = new HashMap<String,String>();
            map.put("workflowId",workflowId);
            ReportStatusDto entity = reportPageManage002Service.selectReportInfo(map);
            if(null != entity) {
                workflowType = entity.getWorkflowType();
                startRepId = entity.getStartRepId();
                offRepId = entity.getOffRepId();
                model.addAttribute("entity",entity);
                //关联上传文件的businessId
                model.addAttribute("businessId",entity.getSeqno());

                model.addAttribute("comments", getCommentList(entity.getSeqno()));
            }

            // 设置默认驳回节点ID
            String processInstanceId = WorkflowContext.getWorkFlowService().getProcessInstanceId(workflowId);
            ActivityImpl activity = WorkflowContext.getProcessService().getFirstTaskDefinition(processInstanceId);
            if(null != activity) {
                model.addAttribute("defaultNodeId", activity.getId());
            }
        }

        //如果是启用界面,则处查询出可以禁用的模板
        if("01".equals(workflowType)){
            //禁用报表下拉选项
            List<SelectOptionDto> reportInfos2 = new ArrayList<SelectOptionDto>();
            for(SelectOptionDto dto: reportInfos){
                //启用报表id,不能包含在禁用报表列中
                if(!dto.getId().equals(repId)){
                    reportInfos2.add(dto);
                }
            }
            //可以禁用的模板
            model.addAttribute("repId",startRepId);
            model.addAttribute("REPORT_NAME2",JsonUtils.objectArray2Json(reportInfos2.toArray()));
        } else {
            //禁用界面,禁用模板选项包含全部
            model.addAttribute("repId",offRepId);
            model.addAttribute("REPORT_NAME2",JsonUtils.objectArray2Json(reportInfos.toArray()));
        }

        model.addAttribute("startRepId",startRepId);
        model.addAttribute("offRepId",offRepId);
        //启用禁用:01启用;02禁用
        model.addAttribute("workflowType",workflowType);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ReportStatusDto dto, BindingResult bindingResult) throws Exception{
        String msg = StringUtil.EMPTY;
        String operateType = dto.getOperateType();
        Date effDate = DateUtil.parseDate(dto.getEffDateStr(),"yyyy-MM-dd HH:mm");

        //生效日必须大于平台日
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sysDate = sdf.format(platformDate.getSysDate());
        String effDateStr = sdf.format(effDate);
        if (effDateStr.compareTo(sysDate) < 0 ){
            msg = "生效日必须大于等于平台日";
            return new AjaxResult(msg, MessageType.错误消息);
        }
        //判断模板是否存在流程中的数据
        int repInFlow = reportPageManage002Service.getInfoStatusInFlow(dto);
        if(repInFlow > 0){
            msg = "模板已经在启用/禁用流程中";
            return new AjaxResult(msg, MessageType.错误消息);
        }

        //判断是否有待生效的启用禁用
        int count = reportPageManage002Service.getInfoStatusAfterSysDate(dto);
        if(count > 0 ){
            msg = "模板已经存在大于当前日期待生效的流程";
            return new AjaxResult(msg, MessageType.错误消息);
        }
        //流程已经在生效日之前已经生效
        if("01".equals(dto.getStartOff())){
            dto.setRepStatus("02");
            int startCount = reportPageManage002Service.getInfoStatusBeforeEffdate(dto);
            if(startCount > 0){
                msg = "启用流程已生效,不能再次启用";
                return new AjaxResult(msg, MessageType.错误消息);
            }
        }

        if("02".equals(dto.getStartOff()) || ("01".equals(dto.getStartOff()) && StringUtil.isNotEmpty(dto.getOffRepId()))){
            dto.setRepId(dto.getOffRepId());
            dto.setRepStatus("04");
            int startCount = reportPageManage002Service.getInfoStatusBeforeEffdate(dto);
            if(startCount > 0){
                msg = "禁用流程已生效,不能再次禁用";
                return new AjaxResult(msg, MessageType.错误消息);
            }
        }

        if("01".equals(dto.getStartOff())){
            String startRepId=dto.getStartRepId();
            //根据启用模板的ID找到是否有同名的信披报表

        }

        if("01".equals(dto.getWorkflowType())){
            msg = reportPageManage002Service.checkStatus(dto);
            if(StringUtil.isNotBlank(msg)){
                return new AjaxResult(msg, MessageType.错误消息);
            }
        }
        return commonCheck(bindingResult, operateType);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    AjaxResult save(@RequestBody ReportStatusDto dto){
        ProcessParameter<ReportStatusDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ReportStatusDto> params = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(params);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        } else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        //如果是从新建页面保存,则设置seqno
        if(StringUtils.isEmpty(dto.getSeqno())){
            dto.setSeqno(IDGenerator.generate());
        }

        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType("REPSTARTOFF");
        Date sysDate = platformDate.getSysDate();
        params.setComment((entity) -> WorkFlowUtil.concatComment(
                "模板启用禁用"+":"+ DateUtil.format(sysDate,"yyyy-MM-dd")));
        processParameter.setWorkFlowParameter(params);
        String msg = reportPageManage002Service.start(processParameter,dto);
        return new AjaxResult(msg);
    }

    List<Map<String,Object>> getCommentList(String seqno){
        List<Map<String,Object>> commentList = new ArrayList<Map<String, Object>>();
        //获取审批意见记录
        List<InfoComplianceCommentDto> comments = infoCompliance002Service.getInfoComplianceComment(seqno);
        for(InfoComplianceCommentDto commentDto:comments){
            Map<String,String> param = new HashMap<String,String>();
            param.put("businessId",seqno);
            param.put("seqnoComment",commentDto.getSeqno());
            Map<String,Object> commentMap = new HashMap<String,Object>();
            commentMap.put("comment",commentDto.getCommentRemark());
            commentMap.put("createUser",commentDto.getCreateUser());
            commentMap.put("createTime", commentDto.getCreateTime());
            commentList.add(commentMap);
        }
        return commentList;
    }
}
