package com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto.ProPeoplebankcodeManagement001Dto;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto.ProPeoplebankcodeManagement001DtoKey;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;

/**
 * 人行产品资产池代码管理Mapper
 * 
 * @author kx
 *
 */
public interface ProPeoplebankcodeManagement001DtoMapper {

	int deleteByPrimaryKey(ProPeoplebankcodeManagement001DtoKey key);

	int insert(ProPeoplebankcodeManagement001Dto record);

	int insertSelective(ProPeoplebankcodeManagement001Dto record);

	ProPeoplebankcodeManagement001Dto selectByPrimaryKey(ProPeoplebankcodeManagement001Dto key);

	int updateByPrimaryKeySelective(ProPeoplebankcodeManagement001Dto record);

	int updateByPrimaryKey(ProPeoplebankcodeManagement001Dto record);

	/**
	 * 查询列表数据
	 * 
	 * @param param
	 * @return
	 */

	List<ProPeoplebankcodeManagement001Dto> queryForPage(Map<String, Object> param);
	
	/**
	 * 导出功能需要导出查询条件的所有内容
	 * 
	 * @param param
	 * @return
	 */

	List<ProPeoplebankcodeManagement001Dto> queryReportList(Map<String, Object> param);
	
	/**
	 * 获取内部产品下拉框初始化数据
	 * 
	 * @param param
	 * @return
	 */
	List<SelectOptionDto> queryInsideProdIdList(Map<String, Object> param);
	
	/**
     * 查询在同一代码类型下是否有重复的内部产品代码
     * @param list 产品list
     * @param orgCode 机构代码
     * @return list
     * @see
     * @since 1.0
     */
    List<String> getProPeopleBankExcelList(@Param("list") List<ProPeoplebankcodeManagement001Dto> list,
                                                       @Param("statusEffective")String statusEffective);
}