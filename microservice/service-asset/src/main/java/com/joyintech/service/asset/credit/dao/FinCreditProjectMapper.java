/*
 * 文件名：FinCreditProjectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.credit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.credit.dto.InterfCreditItemInfoDto;

/**
 * 信贷项目信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月15日
 * @see FinCreditProjectMapper
 * @since 1.0
 */
@Repository
public interface FinCreditProjectMapper {

    List<InterfCreditItemInfoDto> queryForPage(Map<String, Object> pageParam);
    
    int update(InterfCreditItemInfoDto dto);
    
    List<SuggestDto> getCashOutNo(@Param("search") String search);
    
}
