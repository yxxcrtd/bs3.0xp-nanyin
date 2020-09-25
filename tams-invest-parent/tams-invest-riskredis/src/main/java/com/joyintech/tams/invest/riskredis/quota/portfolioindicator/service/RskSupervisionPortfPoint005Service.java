package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPtlPortfolioDto;



/**
 * 组合包成分 
 * @author 裴家俊
 * @version 1.0 2019年03月18日
 * @see RskSupervisionPortfPoint005Service
 * @since 1.0
 */
@Service
public class RskSupervisionPortfPoint005Service extends CommonExportService<RskPtlPortfolioDto>{


	/**
	 * 画面查询 <功能详细描述>
	 * @param parameters
	 * @return RskPtlPortfolioDto
	 * @throws Exception 
	 * @see
	 * @since 1.0
	 */
	public Page<RskPtlPortfolioDto> selectForPage(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForPage(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/selectPortElementForPage",
				parameters, RskPtlPortfolioDto.class);
	}

	 /**
     * 设置日期格式
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
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/riskredis/quota/portfolioindicator/conf/export_portf_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<RskPtlPortfolioDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<RskPtlPortfolioDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
