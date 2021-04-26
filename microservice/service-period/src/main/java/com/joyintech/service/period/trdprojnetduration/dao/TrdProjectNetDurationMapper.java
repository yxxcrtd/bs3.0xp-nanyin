package com.joyintech.service.period.trdprojnetduration.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdprojnetduration.dto.TrdProjectNetDurationDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TrdProjectNetDurationMapper {
    /**
     *
     * 获取代办数据
     * <功能详细描述>
     * @param param 系统参数
     * @return 返回结果
     * @see
     * @since 1.0
     */
    TrdProjectNetDurationDto findEffectiveData(Map<String, Object> param);
    /**
     * 查询画面
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<TrdProjectNetDurationDto> findPage(Map<String, Object> param);
    /**
     *
     * 市场代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    Map<String, Object> finprodMarketIdLink(Map<String, Object> map);
    /**
     *
     * 组合代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    Map<String, Object> portfolioIdLink(Map<String, Object> map);
    /**
     *
     * 计算登记份额
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    Map<String, Object> calRegistShare(Map<String, Object> map);
    /**
     * 统计是否存在后续交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    int countFollowDeal(Map<String, Object> map);
    /**
     *
     * 现金分红流程中
     * <功能详细描述>
     * @param param 系统参数
     * @return 返回结果
     * @see
     * @since 1.0
     */
    TrdProjectNetDurationDto findCashDividend(Map<String, Object> param);
    /**
     * 查询利息明细信息
     * params 查询参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params);

    List<SelectOptionDto> getBusiCode(Map<String, Object> param);
}
