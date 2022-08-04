package com.joyintech.tams.disclosure.infoCompliance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCode;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceCommentDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance001Service;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance002Service;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("InfoCompliance002")
@RequestMapping("/disclosure/infoCompliance/infoCompliance002")
public class InfoCompliance002Controller extends BaseController {


    @Autowired
    private InfoCompliance002Service infoCompliance002Service;

    @Autowired
    private IPlatformDate iPlatformDate;


    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.信披类型);
        this.setSelectOptionToModel(model,DictCode.信批状态);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String seqno = getParam("seqno");
        String viewFlag = getParam("viewFlag");

        InfoComplianceDto dto = null;
        Map<String,String> paramMap = new HashMap<String,String>();
        if(StringUtil.isNotEmpty(seqno)){
            paramMap.put("seqno",seqno);
            dto = infoCompliance002Service.queryInfoCompliance(paramMap);
        } else if(StringUtil.isNotEmpty(workflowId)){
            paramMap.put("workflowId",workflowId);
            dto = infoCompliance002Service.queryInfoCompliance(paramMap);
        }

        if(dto != null){
            model.addAttribute("seqno", dto.getSeqno());
        }
        model.addAttribute("entity", dto);

        model.addAttribute("viewFlag",viewFlag);
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    AjaxResult save(@RequestBody InfoComplianceDto dto){
        ProcessParameter<InfoComplianceDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<InfoComplianceDto> params = new WorkFlowParameter<>();
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
        params.setBusinessType("COMPLIANCE");
        params.setComment((entity) -> WorkFlowUtil.concatComment(
                "合规审核留痕新建 " + DateUtil.format(iPlatformDate.getSysDate(),"yyyy-MM-dd")));
        processParameter.setWorkFlowParameter(params);
        String msg = infoCompliance002Service.start(processParameter,dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping(value = "/getCommentList", method = RequestMethod.POST)
    List<Map<String,Object>> getCommentList(){
        String seqno = this.getParam("seqno");
        List<Map<String,Object>> commentList = new ArrayList<Map<String, Object>>();
        //获取审批意见记录
        List<InfoComplianceCommentDto> comments = infoCompliance002Service.getInfoComplianceComment(seqno);
        for(InfoComplianceCommentDto commentDto:comments){
            Map<String,String> param = new HashMap<String,String>();
            param.put("businessId",seqno);
            param.put("seqnoComment",commentDto.getSeqno());
            param.put("fileType","03");
            List<UploadFileDto> fileList = infoCompliance002Service.getFileByBusinessCode(param);
            Map<String,Object> commentMap = new HashMap<String,Object>();
            commentMap.put("comment",commentDto.getCommentRemark());
            commentMap.put("fileList",fileList);
            commentList.add(commentMap);
        }
        return commentList;
    }

}
