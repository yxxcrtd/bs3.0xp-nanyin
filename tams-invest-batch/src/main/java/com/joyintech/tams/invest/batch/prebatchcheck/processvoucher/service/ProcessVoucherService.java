package com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.service;

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
import com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.dao.ProcessVoucherMapper;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 流程中手工凭证
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Service
public class ProcessVoucherService {

	@Autowired
	private ProcessVoucherMapper processVoucherMapper;
	
    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    /**
     * 执行方法
     * @param batchExecuteInfo
     * @param dto
     * @param instCode
     */
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode){
        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 机构代码，框架用该字段进行数据隔离
            param.put("PRE_INST_CODE",instCode);
        }
        //设置检查日期
        param.put("checkDate",dto.getCheckDate());
        //执行检查
        List<SysPrebatchResultDetail> details = processVoucherMapper.selectProcessVoucherList(param);
        //错误信息设置批信息
        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }
        
        // 判断结果集
        prebatchCheckCommonService.insertBatchResultDetail(dto,details);
    }
    
    /**
     * 将错误结果  组装成入表数据
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
