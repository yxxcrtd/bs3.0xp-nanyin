package com.joyintech.tams.invest.batch.prebatchcheck.capitalposition.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import com.joyintech.tams.invest.batch.prebatchcheck.capitalposition.dao.CapitalPositionMapper;

@Service
public class CapitalPositionService {
	
	@Autowired
	private CapitalPositionMapper CapitalPositionMapper;
	
    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode){
        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }

        param.put("checkDate",dto.getCheckDate());

        List<SysPrebatchResultDetail> details = CapitalPositionMapper.selectCapitalPositionList(param);
        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }
        
        // 判断结果集
        prebatchCheckCommonService.insertBatchResultDetail(dto,details);
    }
    private void setResultDetail(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, SysPrebatchResultDetail item) {

        item.setResultSeqNo(dto.getSeqNo());
        item.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        item.setTaskId(batchExecuteInfo.getTaskId());

        item.setSeqNo(IDGenerator.generate());
        item.setCreateUser(dto.getCreateUser());
        item.setUpdateUser(dto.getUpdateUser());
    }
}
