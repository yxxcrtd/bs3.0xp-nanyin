package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dao;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.AssetLossResult;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.FinProductProjectValuation;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.MstBondQuotationInfoDto;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据推送批处理DAO
 */
@Repository
public interface DataPushValuatIoninfoMapper {


    /**
     * @Description:根据跑批日，获取估值行情列表信息
     * @auther: yuzj
     * @date: 2021/7/13
     * @param: batchDateStr:跑批日
     * @return: 估值行情列表
     */
    List<ValuationBatchSynDto> selectValuationInfoList(@Param("batchDateStr")String batchDateStr,@Param("finprodIdList") List<String> finprodIdList);

    //债券估值行情信息
    int deleteBondByConditions(MstBondQuotationInfoDto record);

    int insertSelectiveBondQuotationInfo(MstBondValuationInfo record);

    //项目类资产行情
    int deleteFinProductProjectByConditions(FinProductProjectValuation record);

    int insertSelectiveFinProject(FinProductProjectValuation record);


    //场外金融产品估值
    int deleteFinProductByConditions(FinProductValuation record);

    int insertSelectiveFinProduct(FinProductValuation record);

    FinProduct selectByPrimaryKey(String finprodId);
}
