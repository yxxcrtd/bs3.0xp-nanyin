/**
 * @author sjj
 * @version 1.0.0
 * @ClassName CfetsNoSettleTask.java
 * @Description 批前检查 未自动交收
 * @createTime 2021年12月3日 10:57:00
 */
package com.joyintech.tams.invest.batch.prebatchcheck.cfetsnosettle.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CfetsNoSettleTaskService {

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;

    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode) {

        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }
        param.put("checkDate",dto.getCheckDate());
        List<SysPrebatchResultDetail> details = new ArrayList<>();
        SysPrebatchResultDetail sysPrebatchResultDetail =new SysPrebatchResultDetail();

        if (StringUtil.equals(CacheUtil.getSysParam("auto_settle_flag"),"N")){
            sysPrebatchResultDetail.setRemark("系统参数日终自动交收为否，不进行自动交收。");
            details.add(sysPrebatchResultDetail);
        }

        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }

        prebatchCheckCommonService.insertBatchResultDetail(dto,details);
    }

    /**
     * @title 组装
     * @description 
     * @author wj 
     * @updateTime 2021/5/13 11:03 
     * @throws 
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
