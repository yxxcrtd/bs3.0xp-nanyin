package com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.service;

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
import com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.dao.ProcessAssetTradeMapper;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 流程中投资交易service
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Service
public class ProcessAssetTradeService {
	
	@Autowired
	private ProcessAssetTradeMapper processAssetTradeMapper;
	
    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    /**
     * @param batchExecuteInfo
     * @param dto
     * @param instCode
     */
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode){
        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            //设置机构代码，框架中aop会根据该字段进行机构隔离
            param.put("PRE_INST_CODE",instCode);
        }
        //设置跑批日期
        param.put("checkDate",dto.getCheckDate());
        //执行查询
        List<SysPrebatchResultDetail> details = processAssetTradeMapper.selectProcessAssetTradeList(param);
        //对查询结果进行统一设置
        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }
        
        //调用公共接口，判断结果集
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