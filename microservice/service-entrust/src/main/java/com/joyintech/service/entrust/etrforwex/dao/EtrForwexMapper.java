/*
 * 文件名：EtrForwexMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrforwex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.entrust.etrforwex.dto.EtrForwexDto;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年6月25日
 * @see EtrForwexMapper
 * @since 1.0
 */
@Repository
public interface EtrForwexMapper {
	/**
     * 
     * 页面查询
     * <功能详细描述>
     * @param map 查询参数
     * @return List<EtrForwexDto>
     * @see
     * @since 1.0
     */
    List<EtrForwexDto> select(Map<String,Object> map);
    
    /**
     * 
     * 编辑、双击打开页面详情
     * <功能详细描述>
     * @param entrustId String
     * @return EtrForwexDto
     * @see
     * @since 1.0
     */
    EtrForwexDto selectByKey(@Param("entrustId") String entrustId);
   
}
