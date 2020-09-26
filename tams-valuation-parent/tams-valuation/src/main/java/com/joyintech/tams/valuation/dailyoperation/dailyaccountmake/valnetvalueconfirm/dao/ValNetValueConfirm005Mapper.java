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

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm005Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 净值修改
 * @author hechuan
 * @version 1.0 2019年10月9日
 * @see ValNetValueConfirm005Mapper
 * @since 1.0
 */
@Repository
public interface ValNetValueConfirm005Mapper {

	List<SelectOptionDto> findProduct(Map<String,Object> param);
    List<ValNetValueConfirm005Dto> getInfo(Map<String,Object> param);
    List<ValNetValueConfirm005Dto> getInfo1(Map<String,Object> param);
    
}
