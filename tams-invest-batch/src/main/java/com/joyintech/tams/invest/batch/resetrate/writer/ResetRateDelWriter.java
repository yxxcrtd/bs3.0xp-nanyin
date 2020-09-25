/*
 * 文件名：ResetRateDelWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.resetrate.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.resetrate.dao.ResetRateMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * DelWriter
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月21日
 * @see ResetRateDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ResetRateDelWriter extends BaseWriter<NeedToWriteEntity>{

    @Autowired
    private ResetRateMapper resetRateMapper;
    
    /*@Autowired(required = false)
    private PinganBatchPlatformDateImpl platformDateService;*/
    
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)this.getParams().get(BatchConstant.BATCH_INFO);
        if(null!=batchExecuteInfo){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        //paramMap.put("effDate",DateUtil.format(platformDateService.getSysDate()));
        paramMap.put("effDate",DateUtil.format(batchExecuteInfo.getExecuteDate()));
        paramMap.put("calcType", DictCodeList.利率类型_浮动利率.getValue());
        paramMap.put("isInitial", DictCodeList.是否_否.getValue());
        resetRateMapper.deleteFinCashCalRule(paramMap);
        }
    }

}
