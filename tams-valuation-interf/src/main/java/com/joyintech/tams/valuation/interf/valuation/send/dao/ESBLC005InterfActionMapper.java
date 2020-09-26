/*
 * 文件名：InterfTestDao.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.interf.valuation.send.dto.ESB448103Dto;
import com.joyintech.tams.valuation.interf.valuation.send.dto.ESBLC005Dto;
import com.joyintech.tams.valuation.interf.valuation.send.dto.SendPageResult;


@Repository
public interface ESBLC005InterfActionMapper {

	List<ESBLC005Dto> queryLC005(SendPageResult sendPageResult);

    
}
