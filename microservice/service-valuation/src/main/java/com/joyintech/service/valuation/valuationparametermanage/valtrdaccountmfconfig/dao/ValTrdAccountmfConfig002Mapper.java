
package com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;
import com.joyintech.system.base.entity.SysWflBusinessList;

/**
 *  预提费用配置
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig002Mapper
 * @since 1.0
 */
public interface ValTrdAccountmfConfig002Mapper {
    
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
    
	/**
     * 查询已有的预提费用配置信息
     * @param dto
     * @return 预提费用配置信息
     * @see
     * @since 1.0
     */
    List<ValTrdAccountmfConfig001Dto> duplicateCheck(ValTrdAccountmfConfig001Dto dto);
    
	/**
     * 获取流程中的新建和修改的预提费用配置流程Id
     * @param params
     * @return 预提费用配置流程Id
     * @see
     * @since 1.0
     */
    List<SysWflBusinessList> getworkflowId(Map<String, Object> params);

}
