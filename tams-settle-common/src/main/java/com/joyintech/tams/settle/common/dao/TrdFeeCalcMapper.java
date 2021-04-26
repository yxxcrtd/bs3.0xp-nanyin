package com.joyintech.tams.settle.common.dao;

import com.joyintech.tams.common.base.entity.TrdFeeConfig;
import com.joyintech.tams.common.base.entity.TrdFeeConfigDetail;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * 交易所费用计算mapper
 *
 * @author 王波
 * @date 2020/7/8
 * @see TrdFeeCalcMapper
 * @since 1.0
 */
@Repository
public interface TrdFeeCalcMapper {
    /**
     * 交易费用配置查询
     * @param planSearchInfo 方案查询信息
     * @return
     */
    public List<TrdFeeConfig> getTrdFeeConfig(Map<String, Object> searchParam);


    /**
     * 根据费用配置流水号，查询交易费用配置明细
     * @param seqno
     * @return
     */
    public List<TrdFeeConfigDetail> getTrdFeeConfigDetail(String seqno);
}
