
package com.joyintech.tams.valuation.feetrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeManagementDto;

/**
 * @version 1.0 20200121
 * @see FeeTradeManagement001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class FeeTradeManagement001Service extends CommonExportService<FeeTradeManagementDto>{
	
	@Autowired
	private FeeTradeManagement002Service feeTradeManagement002Service;
	
	public Page<FeeTradeManagementDto> findPage(Map<String, Object> map) {
		Page<FeeTradeManagementDto> page = restTemplate.postForPage(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/findPage",
					map,FeeTradeManagementDto.class
		);
		List<FeeTradeManagementDto> list = page.getRoot();
		for(FeeTradeManagementDto dto : list) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("bookSetId", dto.getBookSetId());
			param.put("finprodId", dto.getSubBookSetId());
			param.put("feeEndDate", DateUtil.format(dto.getFeeEndDate()));
			param.put("feeType", dto.getFeeType());
			dto.setShouldPayAmt(feeTradeManagement002Service.getShouldPayAmt(param));
		}
		page.setRoot(list);
		return page;
	}
	/**
     * 
     * 文件导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/valuation/feetrade/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FeeTradeManagementDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
