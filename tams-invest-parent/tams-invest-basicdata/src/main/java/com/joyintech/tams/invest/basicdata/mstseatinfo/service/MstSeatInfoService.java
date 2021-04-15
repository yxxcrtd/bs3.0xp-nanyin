package com.joyintech.tams.invest.basicdata.mstseatinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstCustomerDto;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstSeatInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class MstSeatInfoService extends CommonExportService<MstSeatInfoDto> implements BaseProcessService<MstSeatInfoDto>{
	/**
	 * 查询
	 * 
	 * @param pageParam
	 *            Map
	 * @return Page类
	 */
	public Page<MstSeatInfoDto> selectForPage(Map<String, Object> pageParam) {
		return restTemplate.postForPage(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSeatInfo/selectForPage",
				pageParam, MstSeatInfoDto.class);
	}
	
	/**
	 * 双击查看详细信息查询 修改页面信息查询
	 * 
	 * @param indexId
	 *            String
	 * @return MstSaleDepartmentDto
	 * @see
	 * @since 1.0
	 */
	public MstSeatInfoDto selectByPrimaryKey(MstSeatInfoDto dto) {
		return restTemplate.postForObject(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSeatInfo/selectByPrimaryKey", dto,
				MstSeatInfoDto.class);
	}
	
	
	/**
	 * 文件导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/basicdata/mstseatinfo/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}
	
	/**
	 * 席位文本框service层实现
	 *
	 * @param map
	 *            Map<String,Object>
	 * @return SuggestDto
	 */
	public List<SuggestDto> fuzzySearchSeatId(Map<String, String> map) {
		return restTemplate.postForList(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSeatInfo/fuzzySearchSeatId", map,
				SuggestDto.class);
	}
	
	/**
	 * 席位文本框service层实现
	 *
	 * @param map
	 *            Map<String,Object>
	 * @return SuggestDto
	 */
	public List<SuggestDto>  selectCounterSeat(Map<String, Object> map) {
		Object object = restTemplate.postForObject(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/InterfSysTrdConfig/selectCounterSeatId", map,
				Object.class);
		List<SuggestDto> resultList = new ArrayList<>();
		if (object instanceof List<?>){
			List<Map<String,Object>> mapList = (List<Map<String,Object>>)object;
			mapList.forEach(item ->{
				SuggestDto dto = new SuggestDto();
				dto.setCode(item.get("counterSeatId").toString());
				dto.setObject(item);
				resultList.add(dto);
			});
		}
		return resultList;
	}

	/**
	 * 导出查询
	 */
	@Override
	public Page<MstSeatInfoDto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}

	
	@Override
	public void doComplete(MstSeatInfoDto entity, OperationType type) {
		entity.setOperateType(type.toString());
		String action = StringUtil.EMPTY;
		if (entity.getOperateType().equals("DELETE")) {
			action = "delete";
		} else if (entity.getOperateType().equals("NEW")) {
			action = "insert";
		} else {
			action = "update";
		}
		restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSeatInfo/" + action, entity, Integer.class);
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
