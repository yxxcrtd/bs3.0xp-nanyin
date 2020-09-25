package com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMain001Dto;
/**
 * @program: NonstProUplimitMain001Service
 * @description: 非标比例上限维护
 * @author: cxq
 * @create: 2019-12-28
 **/
@Service
public class NonstProUplimitMain001Service extends CommonExportService<NonstProUplimitMain001Dto>{
	/**
	 * 产品系列下拉框查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
    public Page<SelectOptionDto> selectPrdFamily(Map<String, Object> param)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/NonstProUplimitMain/selectPrdFamily", param,
            SelectOptionDto.class);
    }
	/**
	 * 	画面查询方法
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page<NonstProUplimitMain001Dto> selectForPage(Map<String, Object> param)
			 {
		 return restTemplate.postForPage(
		            ServiceApplication.PRODUCT_SERVICE_NAME + "/NonstProUplimitMain/selectForPage", param,
		            NonstProUplimitMain001Dto.class);
	}
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/nolimit/nonstProUplimitMain/conf/export_conf.xml").dataSource(
            		DataSourceType.MASERT);
    }
    
    @Override
    public Page<NonstProUplimitMain001Dto> selectPageData(Map<String, Object> selectParam) {
    	return this.selectForPage(selectParam);
    }
    //查询是否有重复数据
	public Integer checkData(NonstProUplimitMain001Dto temp) {
		 return restTemplate.postForObject( 
				 ServiceApplication.PRODUCT_SERVICE_NAME + "/NonstProUplimitMain/selectPageData", temp,
				 Integer.class);
	}

}
