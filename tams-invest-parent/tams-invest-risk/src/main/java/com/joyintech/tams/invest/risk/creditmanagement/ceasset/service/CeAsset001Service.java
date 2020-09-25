package com.joyintech.tams.invest.risk.creditmanagement.ceasset.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;

/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeAsset001Service
 * @since 1.0
 */
@Service
public class CeAsset001Service extends CommonExportService<CreditLinesAstDto>{
	
	 /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SelectOptionDto> getFinprodMarketIds(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAsset001/getFinprodMarketIds",
            map, SelectOptionDto.class);
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
	public Page<CreditLinesAstDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/CeAsset001/selectForPage",
				parameters, CreditLinesAstDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		if("01".equals(CacheUtil.getSysParam(BussinessConstant.CEMODEL))){
			exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/ceasset/conf/export_conf.xml")
			.dataSource(DataSourceType.MASERT);
		}
		else{
		exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/ceasset/conf/export_sec_conf.xml")
				.dataSource(DataSourceType.MASERT);
		}
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
	public Page<CreditLinesAstDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<CreditLinesAstDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
}
