/*
 * 文件名：RepFinProductExpirationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.informationdisclosureform.repfinproductexpiration.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.informationdisclosureform.repfinproductexpiration.dto.RepFinProductExpirationDto;

/**
 * 产品公告到期Mapper
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月14日
 * @see RepFinProductExpirationMapper
 * @since 1.0
 */
@Repository
public interface RepFinProductExpirationMapper {
    
    /**
     * 
     * 页面组合代码下拉框初始化
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectOption(Map<String,Object> map);
    
    /**
     * 
     * 页面化查询
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return List<RepFinProductExpirationDto>
     * @see
     * @since 1.0
     */
    List<RepFinProductExpirationDto> select(Map<String,Object> map);
    
    /**
     * 
     * 根据母产品代码查询投资标的比例
     * <功能详细描述>
     * @param pFinprodId String
     * @return List<PtlGoal>
     * @see
     * @since 1.0
     */
    List<PtlGoal> selectResult(String pFinprodId);
    
    /**
     * 
     * 根据金融产品代码和分支序号查询
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return RepFinProductEstablishDto
     * @see
     * @since 1.0
     */
    RepFinProductExpirationDto selectByFinprodIdAndBranch(Map<String,Object> map);

}
