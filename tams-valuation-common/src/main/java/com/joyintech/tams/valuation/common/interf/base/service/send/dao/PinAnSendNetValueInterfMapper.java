package com.joyintech.tams.valuation.common.interf.base.service.send.dao;


/*
 * 文件名：InterfTestDao.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.base.service.send.dto.ValNetValueConfirmDto;
import com.joyintech.tams.valuation.common.interf.base.service.send.dto.ValRecvServerInfoEntity;

@Repository
public interface PinAnSendNetValueInterfMapper {
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	Date getOracleSysDate();
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<ValNetValueConfirmDto> queryNetValue(@Param("valDate") String  valdate);
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param valRecvServerInfoEntity
	 * @return
	 * @see
	 * @since 1.0
	 */
	ValRecvServerInfoEntity querySftpServer(ValRecvServerInfoEntity valRecvServerInfoEntity);

	/**
	 * 根据估值日期和账套代码查询估值信息
	 * @param dto
	 * @return
	 */
	ValNetValueConfirmDto querySingleNetValue(ValNetValueConfirmDto dto);

}
