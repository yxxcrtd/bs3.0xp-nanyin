package com.joyintech.tams.disclosure.infoCompliance.service;

import com.google.json.JsonSanitizer;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.*;
import com.joyintech.tams.disclosure.infoCompliance.dao.InfoComplianceMapper;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceCommentDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.constant.ProcessType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.entity.WorkflowEntity;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.service.BusinessService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class InfoCompliance002Service extends BaseService<InfoComplianceDto> implements BaseProcessService<InfoComplianceDto> {

    @Autowired
    private InfoComplianceMapper infoComplianceMapper;

    private ProcessType processType = ProcessType.SAVE;


    @Override
    public void doInsert(InfoComplianceDto dto, OperationType type) {
        //如果是提交按钮,则数据状态设置为待复核
        if("submit".equals(dto.getOperationType())){
            dto.setInfoStatus("01");
        }
        Map<String,String> param = new HashMap<>();
        param.put("seqno",dto.getSeqno());
        //删除旧数据
        infoComplianceMapper.deleteInfoCompliance(param);
        //保存新数据
        infoComplianceMapper.saveInfoCompliance(dto);
        if (CollectionUtils.isNotEmpty(dto.getUploadFileList())) {
            for (UploadFileDto  file : dto.getUploadFileList()){
                UploadFileDto sysFile = new UploadFileDto();
                sysFile.setBusinessId(dto.getSeqno());
                sysFile.setSeqNo(file.getFileNo());
                sysFile.setFileType("01");
                infoComplianceMapper.updateUplodFile(sysFile);
            }
        }

        if (CollectionUtils.isNotEmpty(dto.getUploadFileList2())) {
            for (UploadFileDto  file : dto.getUploadFileList2()){
                UploadFileDto sysFile = new UploadFileDto();
                sysFile.setBusinessId(dto.getSeqno());
                sysFile.setSeqNo(file.getFileNo());
                sysFile.setFileType("02");
                infoComplianceMapper.updateUplodFile(sysFile);
            }
        }

    }

    /**
     *  流程中保存按钮
     * */
    @Override
    public void doUpdate(InfoComplianceDto dto){
        //从待办下发起提交和保存,或者是退回后在发起提交()
        doInsert(dto, null);
        //复核人向下流转流程
        //保存修订意见
        if(StringUtil.isNotEmpty(dto.getComment())){
            String infoSeqno = dto.getSeqno();
            InfoComplianceCommentDto commentDto = new InfoComplianceCommentDto(IDGenerator.generate(),infoSeqno,dto.getComment());
            infoComplianceMapper.saveInfoComplianceComment(commentDto);
            //修订意见与修订附件关联
            infoComplianceMapper.relationFileAndComment(commentDto);
        }

    }

    @Override
    public void doBack(InfoComplianceDto entity) {
        if (StringUtil.isBlank(entity.getComment())) {
            WorkFlowUtil.showMessage("请填写修订意见。", MessageType.提示消息);
            return;
        }
        //退回删除已上传,未关联批注的附件
        infoComplianceMapper.deleteUploadFile(entity.getSeqno());
    }

    @Override
    public void doComplete(InfoComplianceDto dto, OperationType operationType) {
        //审核状态设置为 审核通过
        dto.setInfoStatus("02");
        infoComplianceMapper.updateInfoCompliance(dto);
    }

    //流程作废
    public void doDelete(String workflowId) {
        Map<String,String> param = new HashMap<>();
        param.put("workflowId",workflowId);
        infoComplianceMapper.deleteInfoCompliance(param);
    }

    public InfoComplianceDto queryInfoCompliance(Map<String,String> paramMap){
        return infoComplianceMapper.queryInfoCompliance(paramMap);
    }

    public void updateInfoCompliance(InfoComplianceDto dto){
        infoComplianceMapper.updateInfoCompliance(dto);
    }

    public String selectProcInstIid(String seqNo){
        return infoComplianceMapper.selectProcInstIid(seqNo);
    }

    public List<UploadFileDto> getFileByBusinessCode(Map<String,String> param){
        return infoComplianceMapper.getFileByBusinessCode(param);
    }

    /**
     *  流程中需要增加批注,不能进行批量审批
     **/
    @Override
    public String checkForSubmit(FlowEntity entity, boolean isMutiApproval)  {
        if (isMutiApproval) {
            return "合规留痕管理不能批量审批";
        }
        try{
            if(ProcessType.SUBMIT.equals(entity.getProcessType()) || ProcessType.BACK.equals(entity.getProcessType())){
                FlowEntity flowEntity = WorkflowContext.getProcessService().getRuntimeEntity(entity.getProcessInstanceId());
                BusinessService<WorkflowEntity> businessService = WorkflowContext.getBusinessService(flowEntity.getBusinessService());
                Class<?> clz = WorkFlowUtil.getClassByBusinessServiceName(flowEntity.getBusinessService());
                ProcessMark businessEntity = WorkFlowUtil.getBusinessObjectFromRequest(clz, true);
                Object a = JsonUtils.OBJECT_MAPPER.readValue(JsonSanitizer.sanitize(businessEntity.getBusinessData()), GenericsUtils.getSuperClassGenricType(ProxyUtil.getTargetClass(businessService.getClass())));
                InfoComplianceDto infoDto = (InfoComplianceDto)a;

                String firstNodeId = infoComplianceMapper.getFirstNodeId(entity.getWorkflowId());
                if(!entity.getCurNodeId().equals(firstNodeId)){
                    //非第一个节点提交,需要修订意见
                    if(StringUtil.isEmpty(infoDto.getComment())){
                        return "修订意见不能为空!";
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<InfoComplianceCommentDto> getInfoComplianceComment(String infoSeqno){
        return infoComplianceMapper.getInfoComplianceComment(infoSeqno);
    }
}
