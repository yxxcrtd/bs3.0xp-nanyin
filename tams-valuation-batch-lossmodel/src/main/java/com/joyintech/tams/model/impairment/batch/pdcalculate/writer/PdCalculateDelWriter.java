/*
 * 文件名：ValuationBatchApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.model.impairment.batch.pdcalculate.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dao.PdCalculateMapper;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:PD计算批处理 step1 (先删除)
 * @auther: yuzj
 * @date: 2021/7/14
 * @param:
 * @return:
 */
@Service
@Scope("prototype")
public class PdCalculateDelWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 引入PD计算批处理Mapper
     */
    @Autowired
    private PdCalculateMapper pdCalculateMapper;
    @Autowired
    private ModParamsService modParamsService;

    /**
     * @Description:删除跑批日的-减值结果过程数据
     * @auther: yuzj
     * @date: 2021/7/14
     * @param:
     * @return:
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        Date plateDate = (Date)this.getParams().get("PLATE_DATE");//平台日
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)this.getParams().get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }
        pdCalculateMapper.deletePdCalculateResult(plateDate,finprodIdList);

    }

}
