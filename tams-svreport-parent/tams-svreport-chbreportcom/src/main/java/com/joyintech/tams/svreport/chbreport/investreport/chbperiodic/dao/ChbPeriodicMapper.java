package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbInvestOperationMapper
 * @Description: 非标投资运行报告
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Repository
public interface ChbPeriodicMapper {

    /**
     * 查询产品下拉框
     *
     * @param param
     * @return
     */
    List<SelectOptionDto> queryPrdInfoList(Map<String, Object> param);

    /**
     * 查询定期报告中产品相关信息
     *
     * @param pageParam
     * @return
     */
    List<ChbPeriodicPrdInfo> qryChbPeriodicPrdInfo(Map<String, Object> pageParam);

    /**
     * 查询定期报告中净值、存续规模及收益相关信息
     *
     * @param pageParam
     * @return
     */
    List<ChbPeriodicIndex> qryChbPeriodicIndex(Map<String, Object> pageParam);

    /**
     * 查询定期报告中净值、存续规模及收益相关信息--本期净值收益
     *
     * @param pageParam
     * @return
     */
    BigDecimal qryChbPeriodicIndexValuePercent(Map<String, Object> pageParam);

    /**
     * 查询定期报告中收益分配相关信息
     *
     * @param pageParam
     * @return
     */
    List<ChbPeriodicBonusDeal> qryChbPeriodicBonusDeal(Map<String, Object> pageParam);

    /**
     * 查询定期报告中资产相关信息
     *
     * @param pageParam
     * @return
     */
    List<ChbPeriodicAssetInfo> qryChbPeriodicAssetInfo(Map<String, Object> pageParam);

    /**
     * 查询定期报告中期末产品资产持仓情况
     *
     * @param pageParam
     * @return
     */
    List<ChbPeriodicPosition> qryChbPeriodicPosition(Map<String, Object> pageParam);

    /**
     * 查询定期报告中期末产品持有的前十项资产
     *
     * @param pageParam
     * @return
     */
    List<ChbPeriodicPositionTop10> qryChbPeriodicPositionTop10(Map<String, Object> pageParam);


}
