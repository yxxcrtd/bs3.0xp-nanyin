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

/**
 * 资金负头寸批前检查service
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Service
public class CapitalPositionService {
	
	@Autowired
	private CapitalPositionMapper CapitalPositionMapper;
	
    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    /**
     * 检查执行方法
     * @param batchExecuteInfo
     * @param dto
     * @param instCode
     */
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode){
        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            //机构代码
            param.put("PRE_INST_CODE",instCode);
        }
        //设置检查日期
        param.put("checkDate",dto.getCheckDate());

        List<SysPrebatchResultDetail> details = CapitalPositionMapper.selectCapitalPositionList(param);
        //错误信息设置批信息
        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }
        
        // 判断结果集
        prebatchCheckCommonService.insertBatchResultDetail(dto,details);
    }
    /**
     * 错误信息关联SysPrebatchResultDto
     * @param batchExecuteInfo
     * @param dto
     * @param item
     */
    private void setResultDetail(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, SysPrebatchResultDetail item) {

        item.setResultSeqNo(dto.getSeqNo());
        item.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        item.setTaskId(batchExecuteInfo.getTaskId());

        item.setSeqNo(IDGenerator.generate());
        item.setCreateUser(dto.getCreateUser());
        item.setUpdateUser(dto.getUpdateUser());
    }
}
