/*
 * 文件名：BatchDispatchReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchdispatch.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchReaderDto;


/**
 * 整体调度批处理 - reader
 * 
 * @author qiangchengxi
 * @version 1.0 2018年4月27日
 * @see BatchDispatchReaderStep3
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchDispatchReaderStep3 extends BaseReader<BatchDispatchReaderDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BatchDispatchReaderDto> createList() {
        List<BatchDispatchReaderDto> result = new ArrayList<>();
        BatchDispatchReaderDto dto = new BatchDispatchReaderDto();
        String detaiSeqNo = (String) this.getParams().get("detailSeqNo");
        SysBatchgrpExecInfo sysBatchgrpExecInfo = new SysBatchgrpExecInfo();
        BeanUtil.copyPropertiesIgnoreNull(sysBatchgrpExecInfo, this.getBatchExecuteDetail());
        
        sysBatchgrpExecInfo.setSeqNo(detaiSeqNo);
//        sysBatchgrpExecInfo.setStartDate(this.getBatchExecuteDetail().getStartDate());
//        sysBatchgrpExecInfo.setEndDate(this.getBatchExecuteDetail().getEndDate());
//        sysBatchgrpExecInfo.setDetailName(this.getBatchExecuteDetail().getDetailName());
        sysBatchgrpExecInfo.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
//        sysBatchgrpExecInfo.setSystemCode(BookConstant.VAL);
//        sysBatchgrpExecInfo.setOtherParam3(this.getBatchExecuteDetail().getOtherParam3());
//        sysBatchgrpExecInfo.setBatchSeqNo(this.getBatchExecuteDetail().getBatchSeqNo());
//        sysBatchgrpExecInfo.setPrdGroupSeqNo(this.getBatchExecuteDetail().getPrdGroupSeqNo());
        sysBatchgrpExecInfo.setCreateTime(new Date());
        sysBatchgrpExecInfo.setUpdateTime(new Date());
        dto.setSysBatchgrpExecInfo(sysBatchgrpExecInfo);
        dto.setDetailSeqNo(detaiSeqNo);
        result.add(dto);
        return result;
    }
}
