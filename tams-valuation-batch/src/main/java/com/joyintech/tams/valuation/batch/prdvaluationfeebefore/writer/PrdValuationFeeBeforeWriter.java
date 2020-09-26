/*
 * 文件名：PrdValuationFeeBeforeWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdvaluationfeebefore.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokStatBokPositionMapper;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.common.base.entity.BokStatBokPositionKey;
import com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dto.BokstatbokpositionWriterDto;

/**
 * (产品估值计算-费前) - writer
 * 
 * @author yangjian
 * @version 1.0 2018年8月15日
 * @see PrdValuationFeeBeforeWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdValuationFeeBeforeWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * valStatBokPositionMapper
     */
    @Autowired
    private BokStatBokPositionMapper valStatBokPositionMapper;

    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        @SuppressWarnings("unchecked")
        List<BokstatbokpositionWriterDto> resultList = (List<BokstatbokpositionWriterDto>)items;

        // 循环进行数据更新
        for (BokstatbokpositionWriterDto dto : resultList) {

            // 获取账务汇总表（账套）
            BokStatBokPosition valStatBokPosition = dto.getBokStatBokPosition();

            // 账务汇总表（账套）不为空时,且主键不为空
            if (valStatBokPosition != null && valStatBokPosition.getBookDate() != null && valStatBokPosition.getHappenDate() != null && valStatBokPosition.getBooksetId() != null) {

                // 定义帐套资产负债主键实体类
                BokStatBokPositionKey keyVo = new BokStatBokPositionKey();
                keyVo.setBooksetId(valStatBokPosition.getBooksetId());// 账套
                keyVo.setHappenDate(valStatBokPosition.getHappenDate());// 发生日
                keyVo.setBookDate(valStatBokPosition.getBookDate());// 入账日
                keyVo.setLayeringId(valStatBokPosition.getLayeringId());//分层代码

                // 根据主键获取实体类
                BokStatBokPosition positionOld = valStatBokPositionMapper.selectByPrimaryKey(keyVo);

                // 若表中存该数据，进行删除
                if (positionOld != null) {
                    valStatBokPositionMapper.deleteByPrimaryKey(keyVo);
                }

                // 插入当前数据
                valStatBokPositionMapper.insert(valStatBokPosition);
            }
        }
    }

}
