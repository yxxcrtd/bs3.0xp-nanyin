package com.joyintech.tams.disclosure.infoCompliance.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.disclosure.infoCompliance.dao.InfoComplianceMapper;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.service.BaseProcessService;
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
        doInsert(dto, null);
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
        param.put("workflowId","workflowId");
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

    public List<UploadFileDto> getFileByBusinessCode(String businessId,String fileType){
        return infoComplianceMapper.getFileByBusinessCode(businessId,fileType);
    }

    /**
     *  流程中需要增加批注,不能进行批量审批
     **/
    @Override
    public String checkForSubmit(FlowEntity entity, boolean isMutiApproval) {
        if (isMutiApproval) {
            return "合规留痕管理不能批量审批";
        }
        return null;
    }
}
