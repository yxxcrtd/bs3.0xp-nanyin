package com.joyintech.service.basicdata.assetdeclarationcodemaintenance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.assetdeclarationcodemaintenance.dto.AssetDeclarationCodeMaintenanceDto;

@Repository
public interface AssetDeclarationCodeMaintenanceMapper {
	/**
	 * 查询信息
	 * 
	 * @param param
	 *            查询参数
	 * @return 返回查询结果
	 * @see
	 * @since 1.0
	 */
	List<AssetDeclarationCodeMaintenanceDto> selectForPage(Map<String, Object> param);

	/**
	 * 
	 */
	AssetDeclarationCodeMaintenanceDto selectByPrimaryKey(AssetDeclarationCodeMaintenanceDto dto);

	/**
	 * 
	 */
	AssetDeclarationCodeMaintenanceDto selectByUniqueKey(AssetDeclarationCodeMaintenanceDto dto);

	/**
	 * 资产代码联想下拉框
	 * 
	 * @return List<SuggestDto>
	 */
	List<SelectOptionDto> getFinprodIds(Map<String, Object> map);

	/**
	 * 根据资产代码获取资产类型
	 * 
	 * @return List<SuggestDto>
	 */
	List<SelectOptionDto> getfinprodType(Map<String, Object> map);

	/**
	 * 资产申报代码维护表删除
	 * 
	 * @param dto
	 *            删除参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	int delete(AssetDeclarationCodeMaintenanceDto dto);

	/**
	 * 资产申报代码维护表的插入事件
	 * 
	 * @param dto
	 *            插入参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	int insert(AssetDeclarationCodeMaintenanceDto dto);

	/**
	 * 资产申报代码维护表的修改事件
	 * 
	 * @param dto
	 *            修改参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	int update(AssetDeclarationCodeMaintenanceDto dto);

	/**
	 * 查询信息
	 *
	 * @param param
	 *            查询参数
	 * @return 返回查询结果
	 * @see
	 * @since 1.0
	 */
	List<AssetDeclarationCodeMaintenanceDto> selectAssetDeclarationList(Map<String, Object> param);

}
