/*
 * 文件名：DelayVerification001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitexamination.delayverification.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification001Dto;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification002Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年9月25日
 * @see DelayVerification001Mapper
 * @since 1.0
 */
@Repository
public interface DelayVerification001Mapper {

    /**
     * 延时报送检查未报送检查
     * @param param 查询条件
     * @return 存储了查询数据的DTO
     * @see
     * @since 1.0
     */
    List<DelayVerification001Dto> searchDate(Map<String, Object> param);
    /**
     * 延时报送检查未报送检查
     * @param param 查询条件
     * @return 存储了查询数据的DTO
     * @see
     * @since 1.0
     */
    List<DelayVerification001Dto> searchDatePbc(Map<String, Object> param);
    /**
     * 延时报送检查未报送检查统计全部数据
     * @param param 查询条件
     * @return 存储了查询数据的DTO
     * @see
     * @since 1.0
     */
    int countTotalData(Map<String, Object> param);
    /**
     * 延时报送检查未报送检查 统计不合格数据
     * @param param 查询条件
     * @return 存储了查询数据的DTO
     * @see
     * @since 1.0
     */
    int countUnquaData(Map<String, Object> param);
//    /**
//     * 查询系统中对于颜色的配置
//     * <功能详细描述>
//     * @return 保存了颜色的配置map
//     * @see
//     * @since 1.0
//     */
//    List<String> readConfig();
    
    List<DelayVerification002Dto> searchTablePbc(Map<String, Object> param);
    
    List<DelayVerification002Dto> searchTable(Map<String, Object> param);
    
}
