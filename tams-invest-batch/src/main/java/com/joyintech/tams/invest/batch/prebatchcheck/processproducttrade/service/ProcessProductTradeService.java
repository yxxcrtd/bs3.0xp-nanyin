package com.joyintech.tams.invest.batch.prebatchcheck.processproducttrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.batch.prebatchcheck.processproducttrade.dao.ProcessProductTradeMapper;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 流程中产品交易-批前检查处理serivce
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Service
public class ProcessProductTradeService {
	
	@Autowired
	private ProcessProductTradeMapper processProductTradeMapper;
	
    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    /**
     * 批前检查处理方法
     * @param batchExecuteInfo
     * @param dto
     * @param instCode
     */
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode){
        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            //设置机构信息，多级法人模式下，框架aop会根据该字段对查询结果进行数据隔离
            param.put("PRE_INST_CODE",instCode);
        }
        //设置检查日
        param.put("checkDate",dto.getCheckDate());
        //获取检查结果
        List<SysPrebatchResultDetail> details = processProductTradeMapper.selectProcessProductTradeList(param);
        //检查结果设置批任务信息
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
