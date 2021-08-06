package com.joyintech.tams.model.valuation.batch.incomemethodmodel.dao;

import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.AssetsMakePriceResultDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ValBondValuationInfoResultDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 收益法估值批处理DAO
 */
@Repository
public interface IncomeMethodMapper {

    /** @MethodName: 删除交易员序列数据
    * @Description: TODO
    * @Param: []
    * @Return: void
    * @Author: wangxuelei
    * @Date: 2020/1/6 0006
    **/
    void insertBondValuation(ValBondValuationInfoResultDto dto);

    void insertMakePrice(AssetsMakePriceResultDto dto);

    /**
     * @Description:获取五级分类集合
     * @auther: yuzj
     * @date: 2021/7/5
     * @param: str1:资产类型
     * @param: str2:证券品种
     * @param: strList:证券品种细分类集合
     * @return:
     */
    List<String> selectFiveTypeList(@Param("str1")String str1,@Param("str2")String str2,@Param("assetsFiveTypeList")List<String> assetsFiveTypeList);

    /**
     * @Description:根据产品id获取估值模型股息增长率配置中的预期股息增值率
     * @auther: yuzj
     * @date: 2021/7/6
     * @param:
     * @return:
     */
    String selectStockIntrateByFinprodId(String finprodId);

    /**
     * @Description:资产定价数据查询
     * @auther: yuzj
     * @date: 2021/7/6
     * @param:
     * @return:
     */
    AssetsMakePriceResultDto selectAssetsMakePriceByFinprodId(@Param("finprodId")String finprodId,@Param("baseDateStr")String baseDateStr,@Param("valueModelMethod")String valueModelMethod);

    /**
     * @Description:根据条件删除债券估值行情表
     * @auther: yuzj
     * @date: 2021/7/6
     * @param:
     * @return:
     */
    void deleteValuationInfoResult(ValBondValuationInfoResultDto valBondValuationInfoResultDto);
    /**
     * @Description:债券估值行情表插入
     * @auther: yuzj
     * @date: 2021/7/6
     * @param:
     * @return:
     */
    void insertValuationInfoResult(ValBondValuationInfoResultDto valBondValuationInfoResultDto);


    /**
     * @Description:根据条件删除资产定价表
     * @auther: yuzj
     * @date: 2021/7/6
     * @param:
     * @return:
     */
    void deleteMakePriceResult(AssetsMakePriceResultDto assetsMakePriceResultDto);
    /**
     * @Description:资产定价表插入
     * @auther: yuzj
     * @date: 2021/7/6
     * @param:
     * @return:
     */
    void insertMakePriceResult(AssetsMakePriceResultDto assetsMakePriceResultDto);
    /**
     * @Description:批量插入债券估值行情表
     * @auther: yuzj
     * @date: 2021/7/8
     * @param:
     * @return:
     */
    int insertValuationResultPremium(@Param("valuationResultList")List<ValBondValuationInfoResultDto> valuationResultList);
    /**
     * @Description:批量删除债券估值行情表
     * @auther: yuzj
     * @date: 2021/7/8
     * @param:
     * @return:
     */
    int deleteValuationResultPremium(@Param("valuationResultList")List<ValBondValuationInfoResultDto> valuationResultList);

    /**
     * @Description:批量插入资产定价表
     * @auther: yuzj
     * @date: 2021/7/8
     * @param:
     * @return:
     */
    int insertMakePriceResultPremium(@Param("makePriceResultList")List<AssetsMakePriceResultDto> makePriceResultList);
    /**
     * @Description:批量删除资产定价表
     * @auther: yuzj
     * @date: 2021/7/8
     * @param:
     * @return:
     */
    int deleteMakePriceResultPremium(@Param("makePriceResultList")List<AssetsMakePriceResultDto> makePriceResultList);
    


}
