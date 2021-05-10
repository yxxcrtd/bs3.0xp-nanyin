
package com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;

/**
 *  预提费用配置
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001Mapper
 * @since 1.0
 */
public interface ValTrdAccountmfConfig001Mapper {
    
	/**
     * 页面查询
     * @param params
     * @return 预提费用配置信息
     * @see
     * @since 1.0
     */
    List<ValTrdAccountmfConfig001Dto> queryAllList(Map<String, Object> params);
    
    /**
     * 检索账套代码列表（只取理财账套）
     * 
     * @param param 过滤参数
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetId(Map<String, Object> params);

}
