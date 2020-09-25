package com.joyintech.tams.invest.product.nolimit.occupy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.nolimit.occupy.dto.OccupyQuery001Dto;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 非标占比查询
 */
@Service
public class OccupyQuery001Service extends CommonExportService<OccupyQuery001Dto> {

	/**
	 * 全部查询
	 * @param param
	 * @return
	 */
	public List<OccupyQuery001Dto> selectForPageByAll(Map<String, Object> param){
		return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/OccupyQuery/selectForPageByAll",
				param, OccupyQuery001Dto.class);
	}
	
	/**
	 * 按产品系列查询
	 * @param param
	 * @return
	 */
	public Page<OccupyQuery001Dto> selectForPageByProdSeries(Map<String, Object> param){
		return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/OccupyQuery/selectForPageByProdSeries",
				param, OccupyQuery001Dto.class);
	}
	
	@Override
	public Page<OccupyQuery001Dto> selectPageData(Map<String, Object> selectParam) {
		if(StringUtil.equals(selectParam.get("queryCaliber"), String.valueOf(DictCodeListExt3.查询口径_全部查询.getValue()))) {
			Page<OccupyQuery001Dto> page = new Page<>();
			page.setTotal(1);
			page.setRecords(1);
			page.setRoot(selectForPageByAll(selectParam));
			return page;
		}
		return selectForPageByProdSeries(selectParam);
	}
	
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		if(StringUtil.equals(exportParam.getSelectParam().get("queryCaliber"), 
				String.valueOf(DictCodeListExt3.查询口径_全部查询.getValue()))) {
			exportParam.config("/com/joyintech/tams/invest/product/nolimit/occupy/conf/export_conf.xml")
			.dataSource(DataSourceType.MASERT);
		}else {
			exportParam.config("/com/joyintech/tams/invest/product/nolimit/occupy/conf/export_conf_2.xml")
			.dataSource(DataSourceType.MASERT);
		}
	}
}
