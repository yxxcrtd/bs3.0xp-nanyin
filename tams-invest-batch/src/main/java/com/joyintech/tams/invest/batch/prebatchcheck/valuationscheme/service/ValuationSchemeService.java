package com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.service;

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
import com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.dao.ValuationSchemeMapper;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Service
public class ValuationSchemeService {
	
	@Autowired
	private ValuationSchemeMapper valuationSchemeMapper;
	
    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    private void setResultDetail(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, SysPrebatchResultDetail item) {

        item.setResultSeqNo(dto.getSeqNo());
        item.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        item.setTaskId(batchExecuteInfo.getTaskId());

        item.setSeqNo(IDGenerator.generate());
        item.setCreateUser(dto.getCreateUser());
        item.setUpdateUser(dto.getUpdateUser());
    }
    
	public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode) {
		Map<String,Object> param = new HashMap<>();
		//1. 多级法人模式下需根据机构代码进行数据隔离
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }
		//2. 传入检查日期
        param.put("checkDate",dto.getCheckDate());
        //3. 执行查询
        List<SysPrebatchResultDetail> details = valuationSchemeMapper.selectValuationSchemeList(param);
        //4. 对查询结果，组个设置参数
        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }
        //5. 调用共通进行处理
        prebatchCheckCommonService.insertBatchResultDetail(dto,details);
        
	}
}
