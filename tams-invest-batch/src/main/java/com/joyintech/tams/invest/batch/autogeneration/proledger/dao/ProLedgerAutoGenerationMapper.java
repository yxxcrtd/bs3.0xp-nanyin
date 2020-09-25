/*
 * 文件名：SecPosAutoGenerationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.proledger.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.BenchmarkRate;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.BussFinProductLayeredInfoDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.FinBaseRuleInfoDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.FinFloatMgtExpQuotationDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.FinProductFloatFeeDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.ProLedgerAutoGenerationDto;

/**
 * 产品台账自动生成批处理Mapper
 *
 * @author 刘二通
 * @version 1.0 2018年11月20日
 * @see ProLedgerAutoGenerationMapper
 * @since 1.0
 */
@Repository
public interface ProLedgerAutoGenerationMapper {

    /**
     * 删除产品台账数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int deleteTodayProLedgerData(Map<String, Object> paramMap);

    /**
     * 插入产品台账表
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int insertTodayProLedgerData(ProLedgerAutoGenerationDto proLedgerAutoGenerationDto);

    /**
     * 查询产品台账数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
//    List<ProLedgerAutoGenerationDto> selectTodayProLedgerData(Map<String, Object> paramMap);
    List<ProLedgerAutoGenerationDto> selectTodayProLedgerData(@Param("batchDate")String batchDate,@Param("queryDateStart")String queryDateStart,@Param("productIdList")List<String> productIdList);

    /**
     * 基准类型 = 复合基准时
     * 查询出该基准代码所对应的所有行情信息
     * @return
     * @see
     * @since 1.0
     */
    List<BenchmarkRate> getBenchmarkRate(Map<String, Object> param);
    
    /**
     * 基准来源 = 利率行情时
     * 从利率行情表中取得基准利率
     * @param string 
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getIntRate(Map<String,Object> map);

    List<FinBaseRuleInfo> getFinBaseRuleInfo(Map<String, Object> map);
    
    /**
     * 查询浮动管理费相关信息
     * @param map 参数
     * @return 浮动管理费相关信息
     */
    List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId(Map<String, Object> map);
    
    List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId2(Map<String, Object> map);
    
    List<FinFloatMgtExpQuotationDto> getRuleDetail(Map<String,Object> param);
    
    List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId(Map<String, Object> map);
    
    List<BussBenchMarkLayeredInfoDto> selectByFinprodidList(Map<String, Object> param);
    
    List<FinBaseRuleInfo> getFinBaseRuleInfo2(Map<String, Object> map);
    
    List<BussFinProductLayeredInfoDto> getLayerParam(Map<String, Object> map);

	BigDecimal getRateFroBokPosiProd(Map<String, Object> map);

	FinBaseRuleInfo getLastFinBaseRuleInfo(Map<String, Object> mapLast);

	List<FinBaseRuleInfoDto> getLastFinBaseRuleInfoFD(Map<String, Object> mapLast);
}
