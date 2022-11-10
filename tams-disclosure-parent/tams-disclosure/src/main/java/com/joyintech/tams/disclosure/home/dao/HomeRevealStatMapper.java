/*
 * 文件名：HomePageSeachDataMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.home.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.disclosure.home.dto.DisHomePageStat;
import com.joyintech.tams.disclosure.home.dto.HomePageStatsDto;


/**
 * 主页查询各种数据所用的Mapper
 * 〈功能详细描述〉
 * @author chenxc
 * @version 1.0 2022-10-31
 * @see HomeRevealStatMapper
 * @since 1.0
 */
@Repository
public interface HomeRevealStatMapper {

	int selectCountsByCondition(@Param("condition")String condition);
	
	List<RepInfo> selectRepInfoByHomePageType(@Param("homePageType")String homePageType);
	
	int insert(DisHomePageStat record);

	int delete(DisHomePageStat record);
	
	DisHomePageStat select ( @Param("repId") String repId,@Param("statDate") Date statDate);
	
	List<HomePageStatsDto> selectHomePageStat(@Param("repId") String repId,@Param("sDate") Date statDate,@Param("eDate") Date eDate);

	int selectRevealedData(@Param("repId") String repId,@Param("revealEndDate") Date revealEndDate);
}
