/*
 * 文件名：PaActiveRemind001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.system.remind.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.system.remind.dto.ActiveRemind001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年8月21日
 * @see ActiveRemind001Mapper
 * @since 1.0
 */
@Repository
public interface ActiveRemind001Mapper {
	
	int findDetailCount(Map<String, Object> param);
	
	List<ActiveRemind001Dto> findDetail(@Param("quaryDate")Date quaryDate);
    
}
