/*
 * 文件名：EtrOptionMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：likai
 * 修改时间：2019年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etroption.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.entrust.etroption.dto.EtrOptionDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrlendspecinfo.dto.EtrLendSpecInfoDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author likai
 * @version 1.0 2019年12月23日
 * @see EtrOptionMapper
 * @since 1.0
 */
@Repository
public interface EtrOptionMapper {
    
    /**
     * 
     * 拆借委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrOptionDto> selectForPage(Map<String, Object> param);
    /**
     *
     * 查询页面组合代码联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getentrustId(Map<String, Object> map);
    /**
     *
     * 查询页面合约标的联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getOptionTargetId(Map<String, Object> map);
    /**
     * 
     * 新建页面组合代码联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getentrustIdAdd(Map<String, String> map);
    /**
     *
     * 新建页面合约代码联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getOptionTargetIdadd(Map<String, String> map);
    
    /**
     * 
     * 修改/查看信息查询
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrOptionDto selectByKey(@Param("entrustId") String entrustId);
}
