/*
 * 文件名：BankPositionCollectDelWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangayng1
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankpositioncollect.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.psbc.bankpositioncollect.dao.BankPositionCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankPositionCollectDelWriter
 * @since 1.0
 */
@Service
public class BankPositionCollectDelWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 科目余额汇总mapper
     */
    @Autowired
    private BankPositionCollectMapper bankPositionCollectMapper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 删除同一任务Id对应的之前的记录
        bankPositionCollectMapper.deleteBalanceByKey(this.getParams());
    }

}
