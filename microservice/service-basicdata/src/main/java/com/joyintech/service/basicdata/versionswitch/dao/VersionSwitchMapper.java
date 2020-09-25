/*
 * 文件名：VersionSwitchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caojin
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.versionswitch.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;



/**
 * 版本切换mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author caojin
 * @version 1.0 2020年5月23日
 * @see VersionSwitchMapper
 * @since 1.0
 */
@Repository 
public interface VersionSwitchMapper {

	/**
	 * 查询当前版本
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param Map<String, Object>
	 * @return List<MstYieldCurveInfoDto>
	 * @see
	 * @since 1.0
	 */
	String selectVersionType();
	
	/**
	 * 更新识字字典表默认值1
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param Map<String, Object>
	 * @return List<MstYieldCurveInfoDto>
	 * @see
	 * @since 1.0
	 */
	int updateVersionType1(Map<String, Object> map);
    	
	/**
     * 更新识字字典表默认值0
     * <一句话功能简述>
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<MstYieldCurveInfoDto>
     * @see
     * @since 1.0
     */
	int updateVersionType2(Map<String, Object> map);
    
}
