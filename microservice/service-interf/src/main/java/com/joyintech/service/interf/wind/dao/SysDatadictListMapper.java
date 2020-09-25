/*
 * 文件名：SysDatadictListMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.dao;

import java.util.List;

import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据字典项查询
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月8日
 * @see SysDatadictListMapper
 * @since 1.0
 */
@Repository("InterfSysDatadictListMapper")
public interface SysDatadictListMapper {

	/**
	 * 数据字典项查询
	 * 
	 * @param dictCodeList
	 *            字典代码
	 * @return 数据字典项
	 * @see
	 * @since 1.0
	 */
	List<SysDataDict> selectSysDatadictList(@Param("dictCodeList") List<String> dictCodeList);
}
