package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.service;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;

/**
 * 资产系列授信
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeAssetGroup001Service
 * @since 1.0
 */
@Service
public class CeAssetGroup001Service extends CommonExportService<CreditLinesAstGrpDto> {

	 /**
     * 系列代码联想文本框
      * @param map 检索参数
      * @return SelectOptionDto
      */
    public List<SelectOptionDto> getAssetGroupAliasIds(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroup001/getAssetGroupAliasIds",
            map, SelectOptionDto.class);
    }
    
	/**
	 * 画面查询 <功能详细描述>
	 * @param parameters
	 * @return CreditLinesDto
	 * @see
	 * @since 1.0
	 */
	public Page<CreditLinesAstGrpDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroup001/selectForPage",
				parameters, CreditLinesAstGrpDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/ceassetgroup/conf/export_conf.xml")
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
	public Page<CreditLinesAstGrpDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<CreditLinesAstGrpDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
