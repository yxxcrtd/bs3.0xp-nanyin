package com.joyintech.tams.valuation.component.batch.service;

import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParamsExample;
import com.joyintech.tams.valuation.component.batch.dao.ModBatchParams2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 王波
 * @date 2021-07-22
 * @see ModParamsService
 * @since 1.0
 */
@Service
public class ModParamsService {

    @Autowired
    private ModBatchParams2Mapper modValBatchParamsMapper;

    /**
     * 查询参数列表
     * @param busiDate
     * @return
     */
    public List<ModValBatchParams> selectAllParamsByDate(Date busiDate) {
        // 查询条件
        ModValBatchParamsExample example = new ModValBatchParamsExample();
        ModValBatchParamsExample.Criteria criteria = example.createCriteria();
        criteria.andExecDateEqualTo(busiDate);

        return modValBatchParamsMapper.selectByExample(example);
    }

    /**
     * 根据批处理执行编号查询批处理执行条件
     * @param batchExecSeqno
     * @return
     */
    public List<ModValBatchParams> selectByBatchExecSeqno(String batchExecSeqno) {
        ModValBatchParamsExample example = new ModValBatchParamsExample();
        ModValBatchParamsExample.Criteria criteria = example.createCriteria();
        criteria.andBatchExecSeqnoEqualTo(batchExecSeqno);
        return modValBatchParamsMapper.selectByExample(example);
    }
}
