
/**  
* @Title: OperationalInfo004Service.java
* @Package com.joyintech.tams.svreport.infomanagement.service
* @Description: TODO(用一句话描述该文件做什么)
* @author gaojian
* @date 2019年7月3日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
    
package com.joyintech.tams.svreport.infomanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.joyintech.tams.common.base.dao.FilterInformationDetailMapper;
import com.joyintech.tams.common.base.entity.FilterInformationDetailDto;
import com.joyintech.tams.svreport.infomanagement.dao.OperationalInfo004Mapper;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.ColumnTypeDto;
import com.joyintech.tams.report.common.dao.ChbInfoMgrResultConfigMapper;
import com.joyintech.tams.report.common.dao.ChbMgrTableInfoMapper;
import com.joyintech.tams.report.common.dao.ColumnTypeMapper;
import com.joyintech.tams.report.common.dao.BaseInformationMapper;
import com.joyintech.tams.report.common.dto.ExcelValidDto;

/**
* @ClassName: OperationalInfo004Service
* @Description: TODO(这里用一句话描述这个类的作用)
* @author gaojian
* @date 2019年7月3日 
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalInfo004Service extends CommonExportService<ChbInfoMgrResultConfig> {
	
	@Autowired
	private ChbInfoMgrResultConfigMapper chbInfoMgrResultConfigMapper;
	
	@Autowired
	private ColumnTypeMapper columnTypeMapper;
	
	@Autowired
	private ChbMgrTableInfoMapper chbMgrTableInfoMapper;
	
	@Autowired
	private BaseInformationMapper baseInformationMapper;

	@Autowired
	private OperationalInfo004Mapper operationalInfo004Mapper;

	@Autowired
	private FilterInformationDetailMapper filterInformationDetailMapper;

	/*
	 * 资产要素表名
	 */
	private static final String CHB_ASSET_INFO = "CHB_ASSET_INFO";

	/**
	 * 资产要素基准序号
	 */
	private static final Integer BASE_ORDER_NO = 300;
	
	/**
	* @Title: queryDataResultPage
	* @Description: 根据表名查询已配置查询结果列表
	* @param @param selectParam
	* @param @return    参数
	* @return Page<ChbInfoMgrResultConfig>    查询结果列表
	* @throws
	*/
	public Page<ChbInfoMgrResultConfig> queryDataResultPage(Map<String, Object> selectParam) {
		int pagenum = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_LENGTH).toString());
	    selectParam.put("flag", false);
	    Page<ChbInfoMgrResultConfig> page =
	            super.queryForPage(
	            		pagenum,legth,()->chbInfoMgrResultConfigMapper.queryAllchbMgrTableInfo(selectParam));
		return page;
	}


	/**
	 * @Title: queryDataResultPage
	 * @Description: 根据表名查询已配置查询结果列表
	 * @param @param selectParam
	 * @param @return    参数
	 * @return Page<ChbInfoMgrResultConfig>    查询结果列表
	 * @throws
	 */
	public List<ChbInfoMgrResultConfig> queryDataResultList(Map<String, Object> selectParam) {
		selectParam.put("flag", false);
		return chbInfoMgrResultConfigMapper.queryAllchbMgrTableInfo(selectParam);
	}
	
	/**
	* @Title: check
	* @Description: (数据检查)
	* @param @param selectParam
	* @param @return    参数
	* @return AjaxResult    返回类型
	* @throws
	*/
	public List<ExcelValidDto> check(Map<String, Object> param) {
		String mgrTableName = (String) param.get("mgrTableName");
		String conditionData = (String) param.get("conditionData");
		String resultData = (String) param.get("resultData");
		List<BaseInformationDto> baseInformationDtoList = new ArrayList<BaseInformationDto>();
		List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = new ArrayList<ChbInfoMgrResultConfig>();
		List<ColumnTypeDto> columnTypeDtoList = columnTypeMapper.queryColumn(mgrTableName);
		Map<String, String> columnMap = convertListToMap(columnTypeDtoList);
		// 获取map的key值集合
		List<String> keyList = new ArrayList<String>();
		Set<String> set1 = columnMap.keySet();
		Iterator<String> it1 =  set1.iterator();
		while(it1.hasNext()){
		    keyList.add(it1.next());
		}
		// 错误数据集合
		List<ExcelValidDto> result = new ArrayList<ExcelValidDto>();
		if (StringUtil.isNotEmpty(conditionData)) {
			baseInformationDtoList = JSON.parseArray(conditionData, BaseInformationDto.class);
		}
		if (StringUtil.isNotEmpty(resultData)) {
			chbInfoMgrResultConfigList = JSON.parseArray(resultData, ChbInfoMgrResultConfig.class);
		}
		// 查询条件不合理字段
		result.addAll(conditionDataCheck(baseInformationDtoList, keyList, mgrTableName, columnMap));
		result.addAll(resultDataCheck(chbInfoMgrResultConfigList, keyList));
		return result;
	}
	
	
	/**
	* @Title: conditionDataCheck
	* @Description: (查询条件字段合理性检查)
	* @param @param baseInformationDtoList 字段信息
	* @param @param columnMap 数据库中字段信息
	* @param @param mgrTableName 表名
	* @param @return    参数 错误字段集合
	* @return List<ExcelValidDto>    错误字段集合
	* @throws
	*/
	private List<ExcelValidDto> conditionDataCheck(List<BaseInformationDto> baseInformationDtoList, 
			List<String> keyList, String mgrTableName, Map<String, String> columnMap) {
		Map<String, String> baseColumnTypeMap = querybaseColumnType();
		int conditionSize = baseInformationDtoList.size();
		List<ExcelValidDto> result = new ArrayList<ExcelValidDto>();
		// 查询条件的字段名称集合
		List<String> conditionColumnList = new ArrayList<String>();
		// 查询条件的字段中文名称集合
		List<String> conditionColumnNameList = new ArrayList<String>();
		List<String> columnTypeList = new ArrayList<String>();
		columnTypeList.add("T");
		columnTypeList.add("D");
		columnTypeList.add("S");
		columnTypeList.add("U");
		for (int i = 0; i < conditionSize; i ++) {
			String column = baseInformationDtoList.get(i).getBaseColumnId();
			String columnName = baseInformationDtoList.get(i).getBaseColumnName();
			String columnType = baseInformationDtoList.get(i).getBaseColumnType();
			if (!columnTypeList.contains(columnType)) {
				columnType = baseColumnTypeMap.get(columnType);
			}
			// 字段中文名重复检查
			if (conditionColumnNameList.contains(columnName)) {
				ExcelValidDto exc=new ExcelValidDto();
				exc.setCheckResult("01");
				exc.setValidKey(columnName);
				exc.setErrorInfo("查询条件字段中文名称重复");
				result.add(exc);
			}
			// 所填字段是否存在于表中
			if (!keyList.contains(column)) {
				ExcelValidDto exc=new ExcelValidDto();
				exc.setCheckResult("01");
				exc.setValidKey(column);
				exc.setErrorInfo("查询条件字段不存在");
				result.add(exc);
			} else {
				String realColumnType = columnMap.get(column);
				// 字段名重复检查
				if (conditionColumnList.contains(column)) {
					ExcelValidDto exc=new ExcelValidDto();
					exc.setCheckResult("01");
					exc.setValidKey(column);
					exc.setErrorInfo("查询条件字段名称重复");
					result.add(exc);
				}
				// 日期类型检查
				if (StringUtil.equals("D", columnType)) {
					if (!realColumnType.contains("DATE") && !realColumnType.contains("TIMESTAMP")) {
						ExcelValidDto exc=new ExcelValidDto();
						exc.setCheckResult("01");
						exc.setValidKey(column);
						exc.setErrorInfo("查询条件字段不是日期类型");
						result.add(exc);
					}
					
				}
				// UUID类型检查
				if (StringUtil.equals("U", columnType)) {
					int count = columnTypeMapper.queryUUID(mgrTableName, column);
					if (count == 0) {
						ExcelValidDto exc=new ExcelValidDto();
						exc.setCheckResult("01");
						exc.setValidKey(column);
						exc.setErrorInfo("查询条件字段不是UUID");
						result.add(exc);
					}
				}
			}
			conditionColumnList.add(column);
			conditionColumnNameList.add(columnName);
		}
		return result;
	}
	
	/**
	* @Title: resultDataCheck
	* @Description: (查询结果字段检查)
	* @param @param chbInfoMgrResultConfigList
	* @param @param keyList
	* @param @return    参数
	* @return List<ExcelValidDto>    返回类型
	* @throws
	*/
	private List<ExcelValidDto> resultDataCheck(List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList,
			List<String> keyList) {
		List<ExcelValidDto> result = new ArrayList<ExcelValidDto>();
		// 查询结果的字段名称集合
		List<String> resultColumnList = new ArrayList<String>();
		// 查询结果的字段中文名称集合
		List<String> resultColumnNameList = new ArrayList<String>();
		int resultSize = chbInfoMgrResultConfigList.size();
		for (int i = 0; i < resultSize; i ++) {
			String column = chbInfoMgrResultConfigList.get(i).getColumnId();
			String columnName = chbInfoMgrResultConfigList.get(i).getColumnName();
			// 字段中文名重复检查
			if (resultColumnNameList.contains(columnName)) {
				ExcelValidDto exc=new ExcelValidDto();
				exc.setCheckResult("01");
				exc.setValidKey(columnName);
				exc.setErrorInfo("查询结果字段中文名称重复");
				result.add(exc);
			}
			// 所填字段是否存在于表中
			if (!keyList.contains(column)) {
				ExcelValidDto exc=new ExcelValidDto();
				exc.setCheckResult("01");
				exc.setValidKey(column);
				exc.setErrorInfo("查询结果字段不存在");
				result.add(exc);
			} else {
				// 字段名重复检查
				if (resultColumnList.contains(column)) {
					ExcelValidDto exc=new ExcelValidDto();
					exc.setCheckResult("01");
					exc.setValidKey(column);
					exc.setErrorInfo("查询结果字段名称重复");
					result.add(exc);
				}
			}
			resultColumnList.add(column);
			resultColumnNameList.add(columnName);
		}
		return result;
		
	}
	
	
	/**
	* @Title: convertListToMap
	* @Description: (List转MAP)
	* @param @param columnTypeDtoList
	* @param @return    参数
	* @return Map<String,String>    返回类型
	* @throws
	*/
	private Map<String, String> convertListToMap(List<ColumnTypeDto> columnTypeDtoList) {
		Map<String, String> map = new HashedMap<String, String>(16);
		for (ColumnTypeDto columnTypeDto : columnTypeDtoList) {
			map.put(columnTypeDto.getColumnNname(), columnTypeDto.getColumnType());
		}
		return map;
	}
	
	/**
	* @Title: savePlanData
	* @Description: (保存查询条件和结果方案)
	* @param @param selectParam
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	public String savePlanData(Map<String, Object> param) {
		String flag = "success";
		String mgrTableId = (String) param.get("mgrTableId");
		String mgrTableName = (String) param.get("mgrTableName");
		String conditionData = (String) param.get("conditionData");
		String resultData = (String) param.get("resultData");
		Map<String, String> baseColumnTypeMap = querybaseColumnType();
		List<BaseInformationDto> baseInformationDtoList = new ArrayList<BaseInformationDto>();
		List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = new ArrayList<ChbInfoMgrResultConfig>();
		if (StringUtil.isNotEmpty(conditionData)) {
			baseInformationDtoList = JSON.parseArray(conditionData, BaseInformationDto.class);
			baseInformationDtoList.forEach(info -> {
				info.setBaseInformationId(UUID.randomUUID().toString());
			});
		}
		if (StringUtil.isNotEmpty(resultData)) {
			chbInfoMgrResultConfigList = JSON.parseArray(resultData, ChbInfoMgrResultConfig.class);
			chbInfoMgrResultConfigList.forEach(info -> {
				info.setResultId(UUID.randomUUID().toString());
				// 资产要素表特殊处理，需要加上基准序号
				if (CHB_ASSET_INFO.equals(mgrTableName)) {
					info.setOrderNo(BASE_ORDER_NO + info.getOrderNo());
				}
			});
		}
		List<String> columnTypeList = new ArrayList<String>();
		columnTypeList.add("T");
		columnTypeList.add("D");
		columnTypeList.add("S");
		columnTypeList.add("U");
		for (BaseInformationDto baseInformationDto : baseInformationDtoList) {
			baseInformationDto.setBaseTableId(mgrTableId);
			if (!columnTypeList.contains(baseInformationDto.getBaseColumnType())) {
				String columnType = baseColumnTypeMap.get(baseInformationDto.getBaseColumnType());
				baseInformationDto.setBaseColumnType(columnType);
			}
			if (StringUtil.equals("是", baseInformationDto.getBusinesspkFlag()) || StringUtil.equals("Y", baseInformationDto.getBusinesspkFlag())) {
				baseInformationDto.setBusinesspkFlag("Y");
			} else {
				baseInformationDto.setBusinesspkFlag("N");
			}
		} 
		for (ChbInfoMgrResultConfig chbInfoMgrResultConfig : chbInfoMgrResultConfigList) {
			chbInfoMgrResultConfig.setMgrTableName(mgrTableId);
			if (StringUtil.equals("是", chbInfoMgrResultConfig.getShowFlag()) || StringUtil.equals("Y", chbInfoMgrResultConfig.getShowFlag())) {
				chbInfoMgrResultConfig.setShowFlag("Y");
			} else {
				chbInfoMgrResultConfig.setShowFlag("N");
			}
		}
		try {

			String filterId = operationalInfo004Mapper.getFilterIdByTable(mgrTableName);

			// 待维护表数据修改
			chbMgrTableInfoMapper.updateTableInfo(param);
			// 更新查询条件
			baseInformationMapper.deleteQueryCondition(mgrTableId);
			if (baseInformationDtoList.size() > 0) {
				baseInformationMapper.insertBatch(baseInformationDtoList);
			}
			if(StringUtil.isNotEmpty(filterId)){
				List<String> baseInfoIds = operationalInfo004Mapper.getBaseInfoIds(mgrTableName);
				filterInformationDetailMapper.deleteFilterInfoDetail(filterId);
				baseInfoIds.forEach(baseInfoId -> {
					FilterInformationDetailDto filterInformationDetailDto = new FilterInformationDetailDto();
					filterInformationDetailDto.setFilterDetailId(UUID.randomUUID().toString());
					filterInformationDetailDto.setFilterId(filterId);
					filterInformationDetailDto.setBaseInformationId(baseInfoId);
					filterInformationDetailMapper.insertFilterInfoDetail(filterInformationDetailDto);
				});
			}
			// 更新查询结果
			chbInfoMgrResultConfigMapper.deleteQueryResult(mgrTableId);
			if (chbInfoMgrResultConfigList.size() > 0) {
				chbInfoMgrResultConfigMapper.insertBatch(chbInfoMgrResultConfigList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = "fail";
		} 
		return flag;
	}
	
	/**
	* @Title: querybaseColumnType
	* @Description: 字段类型枚举值
	* @param @return    参数
	* @return Map<String,String>    返回类型
	* @throws
	*/
	public Map<String, String> querybaseColumnType() {
		Map<String, String> baseColumnTypeMap = new HashMap<String, String>();
		baseColumnTypeMap.put("日期类型", "D");
		baseColumnTypeMap.put("文本类型", "T");
		baseColumnTypeMap.put("序列", "S");
		baseColumnTypeMap.put("UUID", "U");
		return baseColumnTypeMap;
	}
}
