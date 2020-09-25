/*
 * 文件名：EtrLendSpecInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrlendspecinfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrlendspecinfo.dto.EtrLendSpecInfoDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月15日
 * @see EtrLendSpecInfoMapper
 * @since 1.0
 */
@Repository
public interface EtrLendSpecInfoMapper {
    
    /**
     * 
     * 拆借委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrLendSpecInfoDto> selectForPage(Map<String, Object> param);
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
     * 修改/查看信息查询
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrLendSpecInfoDto selectByKey (@Param("entrustId") String entrustId);
}
