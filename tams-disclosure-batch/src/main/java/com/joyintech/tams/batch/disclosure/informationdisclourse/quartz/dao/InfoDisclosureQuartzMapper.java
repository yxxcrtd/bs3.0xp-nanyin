/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.dto.RepJobDetail;


/**
 * 投资者持有信息根据产品到期信息及导入信息自动生成批处理mapper
 * @author wangmin
 * @version 1.0 2018年9月25日
 * @see InfoDisclosureQuartzMapper
 * @since 1.0
 */
@Repository
public interface InfoDisclosureQuartzMapper {
	/**
	 * 查询传入日期中是否有持有信息
	 * @param generateDate
	 * @return
	 */
	List<RepJobDetail>  selectJobByDate(@Param("Date") Date execDate);
	
	
	
	
	
}
