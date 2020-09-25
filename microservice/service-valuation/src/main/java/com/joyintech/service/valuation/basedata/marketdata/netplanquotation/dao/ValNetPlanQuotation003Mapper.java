/*
 * 文件名：ValNetPlanQuotation003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation003Dto;

/**
 * 计划类资产净值信息
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月16日
 * @see ValNetPlanQuotation003Mapper
 * @since 1.0
 */
@Repository(value="serviceValNetPlanQuotation003Mapper")
public interface ValNetPlanQuotation003Mapper {
	/**
	 * 通过资产代码获取资产名称
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param finprodId
	 * @return
	 */
	String getFinprodAbbr(@Param("finprodId") String finprodId);
	
	/**
	 * 新增
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return
	 */
	int insert(ValNetPlanQuotation003Dto dto);
	/**
	 * 更新
	 * @param dto
	 * @return
	 */
	int updateByPrimaryKeySelective(ValNetPlanQuotation003Dto dto);
	/**
	 * 新增检验数据唯一性
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param finprodId
	 * @param valueDate
	 * @return
	 */
	List<ValNetPlanQuotation003Dto> searchList(Map<String, Object> map);
	/**
	 * 根据finprodId和valueDate查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param finprodId
	 * @param valueDate
	 * @return
	 */
	 ValNetPlanQuotation003Dto  findAllById(Map<String, Object> map);
    /**
     * 新建检验资产代码是否输入正确
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     */
	List<ValNetPlanQuotation003Dto> searchfinprodId(@Param("finprodId") String finprodId);
	
	/**
     * 删除    
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     */
    int deleteByPrimaryKey(@Param("finprodId")String finprodId,@Param("valueDate")String valueDate);
	
}
