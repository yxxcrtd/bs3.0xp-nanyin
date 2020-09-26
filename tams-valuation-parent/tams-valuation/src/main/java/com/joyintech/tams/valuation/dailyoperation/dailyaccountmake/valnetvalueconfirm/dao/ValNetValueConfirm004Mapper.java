/*
 * 文件名：ValNetValueConfirm004Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：hechuan
 * 修改时间：2019年9月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dao;

import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm004Dto;

/**
 * 
 * 净值修改
 * @author hechuan
 * @version 1.0 2019年10月9日
 * @see ValNetValueConfirm004Mapper
 * @since 1.0
 */
public interface ValNetValueConfirm004Mapper {

	/**
     * 净值修改
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
	int updateNetValue(ValNetValueConfirm004Dto entity);

	/**
     * 统计估值表余额表状态表附属表记录数
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
	int queryNetValueCount(ValNetValueConfirm004Dto entity);

	/**
     * 估值表余额表状态表附属表新增
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
	int insertNetValue(ValNetValueConfirm004Dto entity);
    
    
}
