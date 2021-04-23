package com.joyintech.tams.invest.basicdata.mstsaledepartment.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstCustomerDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

@Service
public class MstSaleDepartmentService extends CommonExportService<MstSaleDepartmentDto> {
	/**
	 * 查询
	 * 
	 * @param pageParam
	 *            Map
	 * @return Page类
	 */
	public Page<MstSaleDepartmentDto> selectForPage(Map<String, Object> pageParam) {
		return restTemplate.postForPage(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/selectForPage",
				pageParam, MstSaleDepartmentDto.class);
	}

	/**
	 * 联想文本框service层实现
	 *
	 * @param map
	 *            Map<String,Object>
	 * @return SuggestDto
	 */
	public List<SuggestDto> getMstSaleDepartmentName(Map<String, String> map) {
		return restTemplate.postForList(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/getMstSaleDepartmentName", map,
				SuggestDto.class);
	}

	/**
	 * 双击查看详细信息查询 修改页面信息查询
	 * 
	 * @param indexId
	 *            String
	 * @return MstPerformanceBenchMarkDto
	 * @see
	 * @since 1.0
	 */
	public MstSaleDepartmentDto selectDetail(MstSaleDepartmentDto dto) {
		return restTemplate.postForObject(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/selectByPrimaryKey", dto,
				MstSaleDepartmentDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean save(MstSaleDepartmentDto entity) {
		String action = StringUtil.EMPTY;
		if (entity.getOperateType().equals("delete")) {
			action = "delete";
		} else if (entity.getOperateType().equals("new")) {
			action = "insert";
		} else {
			action = "update";
		}
		restTemplate.postForObject(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/" + action, entity,
				Integer.class);
		return true;
	}

	/**
	 * 文件导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/basicdata/mstsaledepartment/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	/**
	 * 导出查询
	 */
	@Override
	public Page<MstSaleDepartmentDto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}

	/**
	 * 新增
	 * 
	 * @param dto
	 *            MstPerformanceBenchMarkDto
	 * @return int
	 * @see
	 */
	public int insert(MstSaleDepartmentDto dto) {
		return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/insert", dto,
				Integer.class);
	}

	/**
	 * 券商 <功能详细描述>
	 * 
	 * @param dto
	 *            数据条件
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getMstCustomerInfo(Map<String, Object> map) {
		return restTemplate.postForList(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/getMstCustomerInfo", map,
				SelectOptionDto.class);
	}

	/**
	 * 获取席位号 <功能详细描述>
	 * 
	 * @param dto
	 *            数据条件
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getSeatInfoByCustomer(Map<String, Object> map) {
		return restTemplate.postForList(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/getSeatInfoByCustomer", map,
				SelectOptionDto.class);
	}
	
	/**
	 * 获取券商详情
	 * 
	 * @param dto
	 *            数据条件
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public List<MstCustomerDto> getTradeMarketBybrokerId(Map<String, Object> param) {
		return   restTemplate.postForList(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSaleDepartment/getMstCustomerForTradeMarket", param,
				MstCustomerDto.class);
		 
	}

}
