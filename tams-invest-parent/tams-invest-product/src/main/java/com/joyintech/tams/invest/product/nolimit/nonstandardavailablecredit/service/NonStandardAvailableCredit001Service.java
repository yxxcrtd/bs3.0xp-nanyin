package com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMain001Dto;
import com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.dto.NonStandardAvailableCredit001Dto;

/**
 * 非标可用额度查询
 * 
 * @author dongzhikang
 * @version 1.0 2019年12月10日
 * @see
 * @since 1.0
 */

@Service
public class NonStandardAvailableCredit001Service 
                          extends CommonExportService<NonStandardAvailableCredit001Dto> {
	/**
	 * 非标可用额度主界面查询
	 * 
	 * @author dongzhikang
	 * @version 1.0 2019年12月10日
	 * @see
	 * @since 1.0
	 */
	public  Page<NonStandardAvailableCredit001Dto> selectForPage(Map<String, Object> parameters) {
		
		 return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/NonStandardAvailableCredit/selectForPage", parameters,
				 NonStandardAvailableCredit001Dto.class);
	}
	
	 /**
	  * 导出
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/nolimit/nonstandardavailablecredit/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    /**
	  *  
    * {@inheritDoc}
    */
    @Override
    public Page<NonStandardAvailableCredit001Dto> selectPageData(Map<String, Object> selectParam) {
    	return this.selectForPage(selectParam);
    }
}
