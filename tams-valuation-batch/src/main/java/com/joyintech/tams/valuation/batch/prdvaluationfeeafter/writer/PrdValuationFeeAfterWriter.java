/*
 * 文件名：PrdValuationFeeAfterWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdvaluationfeeafter.writer;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokStatBokPositionMapper;
import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.common.base.entity.BokStatBokPositionKey;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.FinProductValuationKey;
import com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dto.BokstatbokpositionWriterDto;

/**
 * (产品估值计算-费后) - writer
 * 
 * @author yangjian
 * @version 1.0 2018年8月15日
 * @see PrdValuationFeeAfterWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdValuationFeeAfterWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * valStatBokPositionMapper
     */
    @Autowired
    private BokStatBokPositionMapper valStatBokPositionMapper;
    
    /**
     * FinProductValuationMapper
     */
    @Autowired
    private FinProductValuationMapper finProductValuationMapper;

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
                keyVo.setLayeringId(valStatBokPosition.getLayeringId());
                // 根据主键获取实体类
                BokStatBokPosition positionOld = valStatBokPositionMapper.selectByPrimaryKey(keyVo);

                // 若表中存该数据，进行更新
                if (positionOld != null) {
                    //设置要更新实体类
                    BokStatBokPosition updatePosition = new BokStatBokPosition();
                    updatePosition.setBooksetId(valStatBokPosition.getBooksetId());// 账套
                    updatePosition.setHappenDate(valStatBokPosition.getHappenDate());// 发生日
                    updatePosition.setBookDate(valStatBokPosition.getBookDate());// 入账日
                    
                    //以下为要更新的值
                    updatePosition.setNetAssetValue(valStatBokPosition.getNetAssetValue());//资产净值
                    updatePosition.setNetUnitValue(valStatBokPosition.getNetUnitValue());//单位净值
                    updatePosition.setpYield(valStatBokPosition.getpYield());//万份收益
                    updatePosition.setTdyYield(valStatBokPosition.getTdyYield());//当日年化收益率
                    updatePosition.setYieldTerm(valStatBokPosition.getYieldTerm());//周期年化收益率
                    updatePosition.setYield7(valStatBokPosition.getYield7());//当日7日年化收益率
                    
                    //费后更新操作添加分层产品代码条件 leipeng 20181105
                    updatePosition.setLayeringId(valStatBokPosition.getLayeringId());
                    
                    //进行更新
                    valStatBokPositionMapper.updateByPrimaryKeySelective(updatePosition);
                }
            }
            // 获取场外估值行情信息
            FinProductValuation finProductValuation = dto.getFinProductValuation();
            if (finProductValuation != null
            		&& StringUtils.isNotEmpty(finProductValuation.getFinprodId())) {
            	FinProductValuationKey key = new FinProductValuationKey();
            	key.setFinprodId(finProductValuation.getFinprodId());
//            	key.setFinprodType(finProductValuation.getFinprodType());
            	key.setValDate(finProductValuation.getValDate());
            	FinProductValuation old = finProductValuationMapper.selectByPrimaryKey(key);
            	// 若表中存该数据，进行更新
                if (old != null) {
                	finProductValuationMapper.updateByPrimaryKeySelective(finProductValuation);
                } else {
                	finProductValuationMapper.insert(finProductValuation);
                }
            }
        }

    }
    
    
    
    
}
