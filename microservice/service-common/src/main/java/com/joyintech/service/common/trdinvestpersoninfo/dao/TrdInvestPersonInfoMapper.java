/*
 * 文件名：TrdInvestPersonInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.trdinvestpersoninfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrdInvestPersonInfoMapper {
    List<SelectOptionDto> getCounterId(Map<String, Object> param);
    
    List<SelectOptionDto> getCounterIdDepat(Map<String, Object> param);
    
    List<SelectOptionDto> getTrader(Map<String, Object> param);
    
    List<SelectOptionDto> getCfetsTrader(Map<String, Object> param);
}
