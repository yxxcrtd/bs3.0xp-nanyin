/**
 * @author zznn
 * @version 1.0.0
 * @ClassName RegdataNoconfirmTask.java
 * @Description 批前检查 当日始终存在待匹配确认数据
 * @createTime 2021年11月29日 10:57:00
 */
package com.joyintech.tams.invest.batch.prebatchcheck.regdatanoconfirm.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.batch.prebatchcheck.regdatanoconfirm.dao.RegdataNoconfirmMapper;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegdataNoconfirmService {

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;

    @Autowired
    private RegdataNoconfirmMapper regdataNoconfirmMapper;

    /**
     * 执行
     * @param batchExecuteInfo
     * @param dto
     * @param instCode
     */
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode) {

        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }
        param.put("checkDate",dto.getCheckDate());
        List<SysPrebatchResultDetail> details = new ArrayList<>();
        List<SysPrebatchResultDetail> details2 = regdataNoconfirmMapper.listSysPrebatchResultDetail(param);
        if(!StringUtil.isEmptyList(details2)){
            details.addAll(details2);
        }

        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }

        prebatchCheckCommonService.insertBatchResultDetail(dto,details);

    }

    /**
     * 组装
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
