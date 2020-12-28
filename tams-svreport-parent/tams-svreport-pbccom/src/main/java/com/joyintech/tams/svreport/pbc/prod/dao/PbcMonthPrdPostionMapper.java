package com.joyintech.tams.svreport.pbc.prod.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.pbc.prod.dto.PInterfProCustomDealDto;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;

public interface PbcMonthPrdPostionMapper {

    void insert(PbcMonthPrdPostionDto dto);

    /**
     * 功能:根据产品获取该产品当月的各维度(代码、销售区域、币种、客户二级类型)的交易数据
     *
     * @param reportStartDate 统计开始日
     * @param reportEndDate   统计结束日
     * @param batchDate       统计日
     * @param prodList        产品代码
     * @version 1.6.0
     * @since 1.6.0
     */
    void selectProdDealList(@Param("reportStartDate") String reportStartDate,
                            @Param("reportEndDate") String reportEndDate,
                            @Param("batchDate") String reportStatusDate,
                            @Param("prodList") List<String> prodList);

    /**
     * 功能:根据报表结束日删除该产品头寸数据
     *
     * @param reportEndDate
     * @param prodList
     * @version 1.6.0
     * @since 1.6.0
     */
    void deleteByReportDay(@Param("reportEndDate") Date reportEndDay, @Param("prodList") List<String> prodList);


    int insertSelective(PbcMonthPrdPostionDto record);

    List<PbcMonthPrdPostionDto> selectByDate(@Param("bookDate") String bookDate ,
            @Param("modelType") String modelType);

    List<PbcMonthPrdPostionDto> selectByDatePsbc(@Param("bookDate") String bookDate ,
            @Param("modelType") String modelType);

    /**
     * 获取【分省数据客户类型接口数据】
     *
     * @param bookDate
     * @return
     * @see
     * @since 1.0
     */
    List<PInterfProCustomDealDto> getPInterfProCustomDealList();

    List<PbcMonthPrdPostionDto> selectByDateWithoutCustomer(String bookDate);

    /**
     * 生成人行报表头寸,chenxc added on 2019-05-09
     * 1.修改算法：头寸的剩余份额以上周期累计值＋本周期变动值；期末余额＝剩余份额*最新的产品净值
     * 2.性能优化：原先的查询方法效率低；
     * 3.查询和插入头寸表，方法合并；
     *
     * @param reportStartDate
     * @param reportEndDate
     * @param reportStatusDate
     * @param prodList
     * @param exchangeSource
     * @param days
     * @param dividendReinvestmentFlag
     * @see
     * @since 1.7
     */
    void genPosition(@Param("reportStartDate") Date reportStartDate,
                     @Param("reportEndDate") Date reportEndDate,
                     @Param("batchDate") Date reportStatusDate,
                     @Param("prodList") List<String> prodList,
                     @Param("exchangeSource") String exchangeSource,
                     @Param("days") Integer days,
                     @Param("dividendReinvestmentFlag") String dividendReinvestmentFlag,
                     @Param("jsbankStr")String jsbankStr,
                     @Param("hasbouns")String hasbouns,
                     @Param("isPre")String isPre);

    /**
     * [邮储个性化]生成人行报表头寸,wangyang1 added on 2019-8-26
     *
     * @param reportStartDate
     * @param reportEndDate
     * @param reportStatusDate
     * @param prodList
     * @param exchangeSource
     * @see
     * @since 1.7
     */
    void genPsbcPosition(@Param("reportStartDate") Date reportStartDate,
                         @Param("reportEndDate") Date reportEndDate,
                         @Param("batchDate") Date reportStatusDate,
                         @Param("prodList") List<String> prodList,
                         @Param("exchangeSource") String exchangeSource,
                         @Param("dividendReinvestmentFlag") String dividendReinvestmentFlag,
                         @Param("jsbankStr")String jsbankStr);


    List<PbcMonthPrdPostionDto> selectByDateWithAssetPoolCode(@Param("prodList") List<String> prodList, @Param("bookDate") String bookDate);

    List<PbcMonthPrdPostionDto> selectByDateWithAssetPoolCodeEx(@Param("prodList") List<String> prodList, @Param("bookDate") String bookDate);

}