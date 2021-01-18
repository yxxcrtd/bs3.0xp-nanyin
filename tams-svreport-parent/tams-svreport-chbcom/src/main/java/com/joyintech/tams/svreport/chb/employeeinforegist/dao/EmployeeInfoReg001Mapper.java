package com.joyintech.tams.svreport.chb.employeeinforegist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.chb.employeeinforegist.dto.EmployeeInfoReg001Dto;
import org.springframework.stereotype.Repository;


/**
 * @author lingxq
 * @version 1.0 2020年11月3日
 * @see EmployeeInfoReg001Mapper
 * @since 1.0
 */
@Repository
public interface EmployeeInfoReg001Mapper {

	 /**
     * 查询所有的待处理数据
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    List<EmployeeInfoReg001Dto>  selectAllData(Map<String, Object> pageParam);
    
    /**
     * 批量更新中债反馈状态表为中债通过
     * @param list 报送状态
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateSubmitStatusToOk(List<String> list);
    
    /**
     * 功能:将已报送数据移入履历表
     * @since 1.7.0
     * @version 1.7.0
     * @author：yuluan 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int insertReportHis(@Param("registList")List<EmployeeInfoReg001Dto> registList);
    /**
     * 功能:删除登记表中的已报送数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：yuluan 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int deleteLastSubmitRegist(@Param("registList")List<EmployeeInfoReg001Dto> registList);
    /**
     * 批量更新报送状态表为中债通过
     * @param list 报送状态
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<EmployeeInfoReg001Dto> employeeInfoRegList);
    /**
     * 接口报送更新(更新所有检查通过的数据)
     * @param sendStatus 报送状态
     * @see
     * @since 1.0
     */
	int updateAllInterfSubmitAndSeqBatch(String sendStatus);
	 /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @param list
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<EmployeeInfoReg001Dto> registList);
    /**
     * 功能:删除履历表中的最新已报送数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：yuluan 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int deleteLastSubmitHis(@Param("registList")List<EmployeeInfoReg001Dto> registList);

	 /**
    * 取消手工报送更新登记表
    * @param list EquityInfoRegist001Dto
    * @return 更新条数
    * @see
    * @since 1.0
    */
	int updateEmployeeInfoRegist(@Param("list")List<String> registList);
	
	
	/**
	 * 查询未报送的数据
	 * @param registList
	 * @param string
	 * @return
	 */
	List<String> selectRegistSeqnoList(@Param("list")List<String> registList, @Param("flag")String string);

	/**
	 * 选择导出查询
	 * @param dataList
	 * @return
	 */
    List<EmployeeInfoReg001Dto>  selectAllDataByList(@Param("keyList")List<EmployeeInfoReg001Dto>  dataList);
}
