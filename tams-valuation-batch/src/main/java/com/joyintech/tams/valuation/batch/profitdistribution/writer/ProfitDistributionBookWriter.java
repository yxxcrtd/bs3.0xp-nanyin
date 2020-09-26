/*
 * 文件名：ProfitDistributionBookWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitdistribution.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokStatBokPositionMapper;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionResult;

/**
 * 利润分配批处理writer
 * @author weipeng
 * @version 1.0 2018年8月29日
 * @see ProfitDistributionBookWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitDistributionBookWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 记账凭证生成类
     */
    @Autowired
    private BookAccountGenLogic bookAccountGenLogic;
    
    @Autowired
    private BokStatBokPositionMapper bokStatBokPositionMapper;

    /**
     * 记账凭证数据写入数据库(针对账务明细当前表) {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<ProfitDistributionResult> resultList = (List<ProfitDistributionResult>)items;

        // 循环记账凭证list，对每个记账凭证进行操作
        for (ProfitDistributionResult result : resultList) {
            // 插入记账凭证主表信息 和 插入记账凭证明细表信息
            bookAccountGenLogic.insertKeepAccount(result.getBookGenerateResult());
            //更新统计分析账套汇总表
            List<BokStatBokPosition> listBokStatBokPosition = result.getListBokStatBokPosition();
            for(BokStatBokPosition item : listBokStatBokPosition){
                // 更新入库
                bokStatBokPositionMapper.updateByPrimaryKey(item);
            }
        }

    }

}
