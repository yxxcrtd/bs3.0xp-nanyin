package com.joyintech.service.valuation.basedata.marketdata.valinterestratequotationinfo.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.basedata.marketdata.valinterestratequotationinfo.dto.ValInterestRateQuotationInfoDto;

/**
 * 利率行情信息Mapper
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see ValInterestRateQuotationInfoMapper
 * @since 1.0
 */
@Repository
public interface ValInterestRateQuotationInfoMapper {

    /**
     * 根据条件查找用户
     * @param param 查询条件
     * @return 用户信息
     */
    List<ValInterestRateQuotationInfoDto> select(Map<String, Object> param);
    /**
     * 根据逐渐进行查询
     * @param map 查询条件
     * @return ValInterestRateQuotationInfoDto 实体类
     * @see
     * @since 1.0
     */
    ValInterestRateQuotationInfoDto selectByPrimaryKey(Map<String, Object> map);
}
