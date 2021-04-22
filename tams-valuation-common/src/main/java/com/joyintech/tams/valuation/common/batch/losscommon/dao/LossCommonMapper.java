package com.joyintech.tams.valuation.common.batch.losscommon.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokStatLossPosiiton;
import com.joyintech.tams.common.base.entity.TrdAssetLoss;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2020年7月24日
 * @see LossCommonMapper
 * @since 1.0
 */
@Repository
public interface LossCommonMapper {

    /**
     * 根据金融产品代码以及查询日查询减值流水
     *
     * @param param 採番参数
     * @return 採番结果
     * @see
     */
    TrdAssetLoss getTrdAssetLoss(Map<String, Object> param);
    
    /**
     * 插入统计分析明细表（资产减值）
     * @param valBokSubjectDetail 批处理用账务明细
     * @see
     * @since 1.0
     */
    void insertBokStatLossPosiiton(List<BokStatLossPosiiton> bokStatLossPosiiton);
    /**
     * 统计分析明细表（资产减值）
     */
    int deleteBokStatLossPosiiton(Map<String, Object> param);
    
    /**
     * P3阶段查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param serDate
     * @param finprodId
     * @return P3阶段数量
     * @see
     * @since 1.0
     */
    Long getP3num(Map<String, Object> param);

}
