package com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2019年11月29日
 * @see ValWithholdingAmortizeSet001Mapper
 * @since 1.0
 */
@Repository("serviceValWithholdingAmortizeSet001Mapper")
public interface ValWithholdingAmortizeSet001Mapper {

    /**
     * 查询数据
     * 
     * @param pageParam
     * @return list
     */
    List<ValWithholdingAmortizeSet001Dto> selectList(Map<String, Object> params);

    /**
     * 账套代码
     * 
     * @param pageParam
     * @return list
     */
    List<SelectOptionDto> getBooksetIdForSelect(Map<String, Object> pageParam);
    /**
     * check
     * 
     * @param param
     * @return list
     */
    List<SelectOptionDto> check(Map<String, Object> params);

}
