package com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;
import com.joyintech.framework.select.SuggestDto;

/**
 * 
 * 单笔资产额度校验规则
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupationDto
 * @since 1.0
 */
@Service
public class SingleLimitOccupation001Service extends CommonExportService<SingleLimitOccupationDto>{

    
	/**
	 * 画面查询 <功能详细描述>
	 * 
	 * @param parameters
	 *            parameters
	 * @return RskSupervisionIndicatorDto
	 * @see
	 * @since 1.0
	 */
	public Page<SingleLimitOccupationDto> selectForPage(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/SingleLimitOccupation/selectForPage",
				param, SingleLimitOccupationDto.class);
	}

	 /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
	public Page<SelectOptionDto> getLimitLabel(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/SingleLimitOccupation/getLimitLabel",
            map, SelectOptionDto.class);
    }
	
	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditrisklimitmanagement/singlelimitoccupation/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<SingleLimitOccupationDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<SingleLimitOccupationDto> pageList= selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 资产代码的联想文本框
	 * @param finprodMarketId
	 * @return
	 */
	public Page<SelectOptionDto> getFinprodMarketIds(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME
    			+ "/SingleLimitOccupation/getFinprodMarketIds",
    			param, SelectOptionDto.class);				
	}
	
}
