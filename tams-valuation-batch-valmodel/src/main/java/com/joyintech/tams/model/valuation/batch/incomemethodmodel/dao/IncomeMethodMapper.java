package com.joyintech.tams.model.valuation.batch.incomemethodmodel.dao;

import com.joyintech.tams.common.base.entity.ModCurveResult;
import com.joyintech.tams.common.base.entity.ModRiskPremium;
import com.joyintech.tams.common.base.entity.ModStockdivitrateConfig;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收益法估值批处理DAO
 */
@Repository
public interface IncomeMethodMapper {



    /**
     * @Description:根据产品id获取估值模型股息增长率配置中的预期股息增值率
     * @auther: yuzj
     * @date: 2021/7/6
     * @param:
     * @return:
     */
    List<ModStockdivitrateConfig> selectStockIntrateByFinprodIds(List<String> finprodIds);

    /**
     * @Description:批量插入债券估值行情表
     * @auther: yuzj
     * @date: 2021/7/8
     * @param:
     * @return:
     */
    int insertValuationResultPremium(@Param("valuationResultList")List<ModCurveResult> valuationResultList);
    /**
     * @Description:批量删除债券估值行情表
     * @auther: yuzj
     * @date: 2021/7/8
     * @param:
     * @return:
     */
    int deleteValuationResultPremium(@Param("valuationResultList")List<ModCurveResult> valuationResultList);

    void insertMstBondValuationInfo(List<MstBondValuationInfo> listInsert);

    void deleteMstBondValuationInfo(List<MstBondValuationInfo> listInsert);
}
