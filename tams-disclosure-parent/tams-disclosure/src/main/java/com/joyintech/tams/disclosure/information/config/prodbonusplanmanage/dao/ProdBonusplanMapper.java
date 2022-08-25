/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see 
 * @since 1.0
 */
@Repository
public interface ProdBonusplanMapper {

	int deleteByPrimaryKey(String bonusPlanId);

	int insert(ProdBonusplanDto record);

	int insertSelective(ProdBonusplanDto record);

	int insertBasicByBasicInfo(ProdBonusplanDto record);

	ProdBonusplanDto selectByPrimaryKey(String bonusPlanId);

	int updateByPrimaryKeySelective(ProdBonusplanDto record);

	int updateByPrimaryKey(ProdBonusplanDto record);

	List<ProdBonusplanDto> searchReports(Map<String, Object> params);

	/**
	 * 设置主键
	 * @return
	 */
	String setId();

	ProdBonusplanDto selectByBonusPlanId(String bonusPlanId);

	/**
	 *  根据产品代码搜索产品信息
	 * @param prodId 产品代码
	 * @param dataStatus 数据状态
	 * @return
	 */
	ProdBonusplanDto selectByProdId(@Param("prodId") String prodId, @Param("dataStatus")String dataStatus);


	ProdBonusplanDto selectByCondition(ProdBonusplanDto record);

	List<SelectOptionDto> getAllPrdInfoSelect2(Map<String, Object> param);

	int selectByProdId2(@Param("prodId") String prodId, @Param("dataStatus")String dataStatus);

	List<ProdBonusplanDto> selectBonusPlanList(@Param("list") List<ProdBonusplanDto> list);
}
