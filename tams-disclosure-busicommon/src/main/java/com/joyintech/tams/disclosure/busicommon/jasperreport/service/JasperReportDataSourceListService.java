package com.joyintech.tams.disclosure.busicommon.jasperreport.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.disclosure.busicommon.common.calculation.DataSource.CpValueConfig.TbCpValueConfigRepository;
import com.joyintech.tams.disclosure.busicommon.common.calculation.DataSource.MProdTbThree.TbThreeCurrencyDataRepository;
import com.joyintech.tams.disclosure.busicommon.common.calculation.DataSource.NProdTbThree.TbThreeNetTypeDataRepository;
import com.joyintech.tams.disclosure.busicommon.common.service.DisTargetIndexService;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author lxc@jixunet.com
 * @version $Id$
 */

public class JasperReportDataSourceListService implements JRDataSource {
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	int index = -1;
	Map<String, Object> parameters=new HashMap<String, Object>();
	public JasperReportDataSourceListService(String prodId, Date startDate, Date endDate,String reportType) throws Exception {
		this.parameters.put("prodId", prodId);
		this.parameters.put("startDate", startDate);
		this.parameters.put("endDate", endDate);
		this.parameters.put("reportType", reportType);
		fetchData();
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	private void fetchData()  {
		String reportType = String.valueOf(parameters.get("reportType"));
		String startDate = String.valueOf(parameters.get("startDate"));
		String endDate = String.valueOf(parameters.get("endDate"));
		String prodId = String.valueOf(parameters.get("prodId"));
		Connection conn = null;
		try {
			JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean(JdbcTemplate.class);
			conn = jdbcTemplate.getDataSource().getConnection();
			if (ReportGlobalConstant.pAjZ_THREE.equals(reportType)) {
				data = TbThreeNetTypeDataRepository.getNetTypeTbThreeData(DateUtils.parseDate(startDate, "yyyy-MM-dd"),
						DateUtils.parseDate(endDate, "yyyy-MM-dd"), prodId, conn);
			} else if (ReportGlobalConstant.pAzb_THREE.equals(reportType)) {
				data = TbThreeCurrencyDataRepository.getCurrencyTypeTbThreeDate(
						DateUtils.parseDate(startDate, "yyyy-MM-dd"), DateUtils.parseDate(endDate, "yyyy-MM-dd"),
						prodId, conn);
			}
			// 过去五年产品每年净值增长率及其与同期业绩比较基准收益率的比较（图展示）
			else if (ReportGlobalConstant.Cp_ValConfig.equals(reportType)) {

				data = TbCpValueConfigRepository.getTableData(DateUtils.parseDate(startDate, "yyyy-MM-dd"),
						DateUtils.parseDate(endDate, "yyyy-MM-dd"), prodId, conn);

			}
			// 关联交易查询
			else if (ReportGlobalConstant.Trade_Relation.equals(reportType)) {
				data = DisTargetIndexService.printTableTradeRela(DateUtils.parseDate(startDate, "yyyy-MM-dd"),
						DateUtils.parseDate(endDate, "yyyy-MM-dd"), conn);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public boolean next() throws JRException {
		index++;
		if (data != null && index < data.size()) {
			return true;
		}
		return false;
	}

	public Object getFieldValue(JRField jrField) throws JRException {
		return data.get(index).get(jrField.getName());
	}
}
