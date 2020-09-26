package com.joyintech.tams.report.home.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.home.dto.BusinessHomeDto;

/**
 * @Description 业务首页Mapper
 * @author jiangxiao
 * @version 1.7 2019年5月17日
 * @since 1.7
 */
@Repository
public interface BusinessHomeMapper {

	/**
	 * @Description 业务首页查询：接口文件个数、异常结束个数、正常结束个数、数据接入总数 
	 * @param parmMap
	 * @return List<BusinessHomeDto>
	 * @version 1.7 2019年5月17日
	 */
	List<BusinessHomeDto> selectBusinessHomeInfo(Map<String, Object> parmMap);
	
	/**
	 * @Description 业务首页查询：检查通过、检查不通过、待处理
	 * @param parmMap
	 * @return List<BusinessHomeDto>
	 * @version 1.7 2019年5月17日
	 */
	List<BusinessHomeDto> selectDateCheckInfo(Map<String, Object> parmMap);
	
	/**
	 * @Description 查询产品登记编码
	 * @param parmMap
	 * @return int
	 * @version 1.7 2019年5月17日
	 */
	int selectRegistnum(Map<String, Object> parmMap);
	
	/**
	 * @Description 查询识别标识合并
	 * @param parmMap
	 * @return int
	 * @version 1.7 2019年5月17日
	 */
	int selectMergenum(Map<String, Object> parmMap);
	
	/**
	 * @Description 查询系统配置的接口
	 * @return
	 * @return List<String>
	 * @version 1.7 2019年5月17日
	 */
	List<String> selectInterfType();
	
	/**
	 * @Description 查询菜单是否存在
	 * @return
	 * @return List<String>
	 * @version 1.7 2019年6月27日
	 */
	List<String> selectMenulist(@Param("list")List<String> menulist);
	
	int selectChbEquHold();
	
	int selectChbInvestTrade(@Param("searchDate")String searchDate);
	/**
	 * 查询日期待处理的投资者明细信息个数
	 * @param searchDate
	 * @return
	 */
	int selectChbEqudet(@Param("searchDate")String searchDate);
}
