package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dao;

import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf001Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-06-30
 * @see RiskPremiumAdjConf001Mapper
 * @since 1.0
 */
@Repository(value="serviceRiskPremiumAdjConf001Mapper")
public interface RiskPremiumAdjConf001Mapper {
    /**
     * 查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     */
    List<RiskPremiumAdjConf001Dto> queryList(Map<String, Object> params);

    /**
     * 主键查询
     *
     * @return 主键查询
     * @see
     * @since 1.0
     */
    RiskPremiumAdjConf002Dto findByPrimaryKey(Map<String, Object> params);

    int updateByPrimaryKeySelective(RiskPremiumAdjConf002Dto record);

}
