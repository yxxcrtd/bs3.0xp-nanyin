/*
 * 文件名：ChbDeadLineMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年12月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.common.svreport.chb.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年12月16日
 * @see ChbInterfConfigMapper
 * @since 1.0
 */
@Repository
public interface ChbInterfConfigMapper {
	/**
	 * 更新接口接入数据的最近更新时间
	 * @param interfId,newUpdateTime
	 * @return
	 */
	int updateLastupdatetime(@Param("interfId")String interfId,@Param("newUpdateTime")Date newUpdateTime);

    /**
     * 更新接口接入数据的最近更新时间
     * @param interfId
     * @return
     */
    int updateBySysdate(@Param("interfId")String interfId);
	
	/**
	 * 根据interfId查询接口接入数据的最近更新时间
	 * @param interfId,newUpdateTime
	 * @return
	 */
	Date queryUpdateTime(@Param("interfId")String interfId);
	
	
	/**
	 * 根据interfId查询接口接入数据的最近更新时间
	 * @param interfId,newUpdateTime
	 * @return
	 */
	Date queryUpdateTimeEx(@Param("interfId")String interfId,@Param("InstitutionCode")String InstitutionCode);

    int deleteByPrimaryKey(String interfId);

    int insert(ChbInterfConfigDto record);

    int insertSelective(ChbInterfConfigDto record);

    ChbInterfConfigDto selectByPrimaryKey(String interfId);

    int updateByPrimaryKeySelective(ChbInterfConfigDto record);

    int updateByPrimaryKey(ChbInterfConfigDto record);

    /**
     * 功能:通过interfId查询配置信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月8日
     * @param dataType
     * @return 
     * ChbInterfConfig
     */
    ChbInterfConfigDto selectByInterfId(String interfId);
    
    
    /**
     * 功能:通过interfId查询配置信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月8日
     * @param dataType
     * @return 
     * ChbInterfConfig
     */
    ChbInterfConfigDto selectByInterfIdEx(@Param("interfId")String interfId,@Param("InstitutionCode")String InstitutionCode);
    
    /**
	 * 更新接口接入数据的最近更新时间-联社数据
	 * @param interfId,newUpdateTime
	 * @return
	 */
	int updateLastupdatetimeEx(@Param("interfId")String interfId,@Param("newUpdateTime")Date newUpdateTime,@Param("InstitutionCode")String InstitutionCode);
    
	
	/**
	 * 更新完毕后检查当前接口中最小的分支机构时间,如果都跑过.将config表中的lastupdatetime更新为 add表中的最小时间
	 * @param interfId,newUpdateTime
	 * @return
	 */
	int updateLastupdatetimeExt(@Param("interfId")String interfId,@Param("newUpdateTime")Date newUpdateTime);
}
