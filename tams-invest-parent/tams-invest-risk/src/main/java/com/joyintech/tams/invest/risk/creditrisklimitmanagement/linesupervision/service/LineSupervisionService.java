package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linesupervision.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linesupervision.dto.LineSupervisionDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 额度监测
 * @author gejiaao
 * @version 1.0 2020年03月01日
 * @see LineSupervisionService
 * @since 1.0
 */
@Service
public class LineSupervisionService extends CommonExportService<LineSupervisionDto>
		implements BaseProcessService<LineSupervisionDto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(LineSupervisionDto entity, OperationType type) {
	}
    
	/**
	 * 画面查询 <功能详细描述>
	 * 
	 * @param parameters
	 *            parameters
	 * @return RskSupervisionIndicatorDto
	 * @see
	 * @since 1.0
	 */
	public Page<LineSupervisionDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/LineSupervision/selectForPage",
				parameters, LineSupervisionDto.class);
	}

	/**
	 * 主体名称联想文本框service层实现
	 *
	 * @param map 检索参数
	 * @return SelectOptionDto
	 */
	public Page<SelectOptionDto> getCustomerName(Map<String, Object> map) {
		return restTemplate.postForPage(
				ServiceApplication.RISK_SERVICE_NAME + "/LineSupervision/getCustomerName",
				map, SelectOptionDto.class);
	}

	public List<LineSupervisionDto> getCreditCustomerMst(String creditMstId){
		Map<String, Object> map = new HashMap<>();
		map.put("creditMstId", creditMstId);
		return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/LineSupervision/getCreditCustomerMst",
				map, LineSupervisionDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditrisklimitmanagement/lineSupervision/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	 /**
     * 设置预警与违规格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
    	 if(("vdate".equals(propName)) && value != null){
             cell.setCellValue(DateUtil.format((Date)value));
         }
    	 if(("mdate".equals(propName)) && value != null){
             cell.setCellValue(DateUtil.format((Date)value));
         }
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<LineSupervisionDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<LineSupervisionDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
