/*
 * 文件名：InfoDiscAutoConfirmMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoconfirm.dao.InfoDiscAutoConfirmMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealReadResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealResult;



/**
 * 自动披露批处理mapper
 * @author wangmin
 * @version 1.0 2018年12月8日
 * @see InfoDiscAutoConfirmMapper
 * @since 1.0
 */
@Repository
public interface InfoDiscAutoRevealMapper {
	/**
	 * 获取所有未披露、已确认的自动披露数据
	 * @param revealStatus 披露状态
	 * @param confirmStatus 确认状态
	 * @return返回查询结果
	 */
	List<InfoDiscAutoRevealReadResult> getRepRevealList(@Param("revealStatus") String revealStatus,@Param("confirmStatus") String confirmStatus);
	//@Param("revealChannel") String revealChannel);
	/**
	 * 自动披露更新状态
	 * @param infoDiscAutoRevealResult 更新内容
	 * @return
	 */
	int updateRevealStatusById(InfoDiscAutoRevealResult infoDiscAutoRevealResult);
}
