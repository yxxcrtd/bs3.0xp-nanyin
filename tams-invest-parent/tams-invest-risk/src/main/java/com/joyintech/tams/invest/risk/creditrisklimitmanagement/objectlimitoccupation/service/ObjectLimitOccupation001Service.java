package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service;

import java.util.Map;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;

/**
 * 
 * 主体额度占用调整
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see ObjectLimitOccupation001Service
 * @since 1.0
 */
@Service
public class ObjectLimitOccupation001Service extends CommonExportService<ObjectLimitOccupationDto>{

	@Autowired
    private IPlatformDate iPlatformDate;
	
	/**
	 * 画面查询 <功能详细描述>
	 * 
	 * @param parameters
	 *            parameters
	 * @return RskSupervisionIndicatorDto
	 * @see
	 * @since 1.0
	 */
	public Page<ObjectLimitOccupationDto> selectForPage(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitOccupation/selectForPage",
				param, ObjectLimitOccupationDto.class);
	}

	/**
	 * 主体名称联想文本框
	 * 
	 * @return
	 * @throws Exception
	 */
	public Page<SelectOptionDto> getObject(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME
    			+ "/ObjectLimitOccupation/getObject",
    			param, SelectOptionDto.class);				
	}
	
	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditrisklimitmanagement/objectLimitOccupation/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<ObjectLimitOccupationDto> selectPageData(Map<String, Object> selectParam) {
		try {
			String platDate = iPlatformDate.getSysDateStr();
			selectParam.put("ptDate", platDate);
			Page<ObjectLimitOccupationDto> pageList= selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    public Page<SelectOptionDto> getUserName(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitOccupation/getUserName", map,
        		SelectOptionDto.class);
    }
    
	/**
	 * 主体历史画面查询 <功能详细描述>
	 * 
	 * @param parameters
	 *            parameters
	 * @return RskSupervisionIndicatorDto
	 * @see
	 * @since 1.0
	 */
	public Page<ObjectLimitOccupationDto> selectHistory(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitOccupation/selectHistory",
				param, ObjectLimitOccupationDto.class);
	}
}
