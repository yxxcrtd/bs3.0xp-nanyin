/*
 * 文件名：RskAuthorityManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dto.MstCustomerInfoAddDto;
import com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dto.SeniorExecutiveInfDto;
import com.joyintech.tams.common.base.entity.CreditLines;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author qiangchengxi
 * @version 1.0 2018年11月30日
 * @see AgencyDetailsInf002Mapper
 * @since 1.0
 */
@Repository
public interface AgencyDetailsInf002Mapper {

	/**
	 * 获取关联机构信息 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> getRelateOrgInf(Map<String, Object> map);

	/**
	 * 获取关联机构信息 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<MstCustomerInfoAddDto> getMstCustomerInfoAdd(Map<String, Object> map);

	/**
	 * 获取关联机构信息 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SeniorExecutiveInfDto> getCorrelationInf(Map<String, Object> map);

	int deleteByPrimaryKey(String seqno);

	int insertSelective(SeniorExecutiveInfDto record);

	int updateByPrimaryKeySelective(SeniorExecutiveInfDto record);

}
