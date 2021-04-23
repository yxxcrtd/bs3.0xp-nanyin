/*
 * 文件名：MstCaptialAccountMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstsaledepartment.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstCustomerDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto2;

/**
 * 
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author fuxj
 * @version 1.0 2020年3月18日
 * @see BasicMstSaleDepartmentMapper
 * @since 1.0
 */
@Repository
public interface BasicMstSaleDepartmentMapper {
    /**
     * 
     * 模糊查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return List<MstCaptialAccountDto>
     * @see
     * @since 1.0
     */
	List<MstSaleDepartmentDto2> selectForInterf(Map<String, Object> pageParam); 
    

	
	/********************************************************************************************/
	/**
	 * 查询信息
	 * 
	 * @param param
	 *            查询参数
	 * @return 返回查询结果
	 * @see
	 * @since 1.0
	 */
	List<MstSaleDepartmentDto> selectForPage(Map<String, Object> param);

	/**
	 * 查看、修改、唯一性验证信息
	 */
	MstSaleDepartmentDto selectByPrimaryKey(MstSaleDepartmentDto dto);

	/**
	 * 券商
	 * 
	 * @return List<SuggestDto>
	 */
	List<SelectOptionDto> getMstCustomerInfo(Map<String, Object> map);

	/**
	 * 获取营业部名称
	 * 
	 * @return List<SuggestDto>
	 */
	List<SuggestDto> getMstSaleDepartmentName(Map<String, String> map);
	
	/**
	 * 获取席位号
	 * @param map
	 * @return
	 */
	List<SelectOptionDto> getSeatInfoByCustomer(Map<String, Object> map);

	/**
	 * 删除
	 * 
	 * @param dto
	 *            删除参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	int delete(MstSaleDepartmentDto dto);

	/**
	 * 插入事件
	 * 
	 * @param dto
	 *            插入参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	int insert(MstSaleDepartmentDto dto);

	/**
	 * 修改事件
	 * 
	 * @param dto
	 *            修改参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	int update(MstSaleDepartmentDto dto);
	


	//add by huanghao 20201117 获取券商详情 start
	/**
	 * 查询券商详情
	 * @param map
	 * @return
	 */
	List<MstCustomerDto> getMstCustomerForTradeMarket(Map<String, Object> map);
	//add by huanghao 20201117 获取券商详情 end
}
