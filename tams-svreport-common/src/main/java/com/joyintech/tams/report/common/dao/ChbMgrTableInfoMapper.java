
/**
 * @Title: ChbMgrTableInfoMapper.java
 * @Package com.joyintech.tams.report.common.dao
 * @Description: 数据维护待维护表基本信息接口
 * @author gaojian
 * @date 2019年7月2日
 * @since V1.0
 * @Co.,Ltd. 安徽兆尹信息科技有限责任公司
 */

package com.joyintech.tams.report.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbMgrTableInfo;

/**
 * @ClassName: ChbMgrTableInfoMapper
 * @Description: 数据维护待维护表基本信息接口
 * @author gaojian
 * @date 2019年7月2日
 *
 */

@Repository
public interface ChbMgrTableInfoMapper {

	/**
	 * @Title: queryAllhbMgrTableInfo
	 * @Description: 查询所有待维护表基本信息
	 * @param
	 * @return List<ChbMgrTableInfo> 待维护表基本信息列表
	 * @throws
	 */

	List<ChbMgrTableInfo> queryAllchbMgrTableInfo(Map<String,Object> selectParam);

	/**
	 * @Title: checkTableExist
	 * @Description: 查询指定表是否存在
	 * @param  指定表名
	 * @return Integer 查询结果条数
	 * @throws
	 */
	Integer checkTableExist(@Param("mgrTableName") String mgrTableName);


	/**
	 * @Title: checkDoubleInfo
	 * @Description: 检查指定表是否已经配置
	 * @param @param mgrTableName
	 * @param @return    参数
	 * @return Integer    查询结果条数
	 * @throws
	 */

	Integer checkDoubleInfo(@Param("mgrTableChiName") String mgrTableChiName);

	/**
	 * @Title: insertData
	 * @Description: 存入一天待维护表信息
	 * @param @param selectParam
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int insertData(Map<String,Object> selectParam);

	/**
	 * @Title: updateTableStatus
	 * @Description: 修改指定表数据状态
	 * @param @param selectParam
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int updateTableStatus(Map<String,Object> selectParam);

	/**
	 * @Title: updateTableInfo
	 * @Description: (修改待维护表中文名称和接口名称)
	 * @param @param selectParam
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int updateTableInfo(Map<String,Object> selectParam);

	String getHeaderNumbyTableId(@Param("mgrTableId") String mgrTableId);

	ChbMgrTableInfo getTableInfobyTableId(@Param("mgrTableId") String mgrTableId);

	ChbMgrTableInfo getTableInfobyChiName(@Param("mgrTableChiName") String mgrTableChiName);

}
