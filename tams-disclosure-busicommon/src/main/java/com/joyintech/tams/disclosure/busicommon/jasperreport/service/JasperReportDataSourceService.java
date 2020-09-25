package com.joyintech.tams.disclosure.busicommon.jasperreport.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.busicommon.common.service.DisTargetIndexService;
import com.joyintech.tams.disclosure.busicommon.common.util.ListconMap;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dto.DisAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dto.DisAssetLiabilityStatementQuery001SearchDto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.service.DisAssetLiabilityStatementQuery001Service;
import com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.service.DisOwnerEquityChange001Service;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisValProfitQuery001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.service.DisValProfitQuery001Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author lxc@jixunet.com
 * @version $Id$
 */

public class JasperReportDataSourceService implements JRDataSource {
	private Map<String, Object> data = new HashMap<String, Object>();
	int index = -1;
	Map<String, Object> parameters=new HashMap<String, Object>();
	public JasperReportDataSourceService(String prodId, Date startDate, Date endDate,String reportType) throws Exception {
		this.parameters.put("prodId", prodId);
		this.parameters.put("startDate", startDate);
		this.parameters.put("endDate", endDate);
		this.parameters.put("reportType", reportType);
		fetchData();
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	private void fetchData()  {
		DisTargetIndexService t = new DisTargetIndexService();
		DisAssetLiabilityStatementQuery001Service service = SpringContextUtil
				.getBean(DisAssetLiabilityStatementQuery001Service.class);
		DisOwnerEquityChange001Service oService = SpringContextUtil.getBean(DisOwnerEquityChange001Service.class);
		DisValProfitQuery001Service vService = SpringContextUtil.getBean(DisValProfitQuery001Service.class);
		String reportType = String.valueOf(parameters.get("reportType"));
		String startDate = String.valueOf(parameters.get("startDate"));
		String endDate = String.valueOf(parameters.get("endDate"));
		String prodId = String.valueOf(parameters.get("prodId"));
		Connection conn = null;
		try {
			JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean(JdbcTemplate.class);
			conn = jdbcTemplate.getDataSource().getConnection();
			if (ReportGlobalConstant.pAjZ_TWO.equals(reportType)) {
				data = t.printTargetNetTypeTbTwo(DateUtils.parseDate(startDate, "yyyy-MM-dd"),
						DateUtils.parseDate(endDate, "yyyy-MM-dd"), prodId, conn);
			} else if (ReportGlobalConstant.pAzb_TWO.equals(reportType)) {
				data = t.printTargetCurrencyTypeTbTwo(DateUtils.parseDate(startDate, "yyyy-MM-dd"),
						DateUtils.parseDate(endDate, "yyyy-MM-dd"), prodId, conn);
			}
			// 资产负债表
			else if (ReportGlobalConstant.Balance_Sheet.equals(reportType)) {
				DisAssetLiabilityStatementQuery001SearchDto dto = new DisAssetLiabilityStatementQuery001SearchDto();
				// 账套代码=产品代码
				dto.setBooksetId(prodId);
				// 报表维度=单账套
				dto.setReportDimen("01");
				// 报表时间=结束日
				dto.setReportDate(endDate);
				// 报表类型=日报
				dto.setReportTermType("01");
				// List<AssetLiabilityStatementQuery001Dto> list1=new
				// ArrayList<>();
				// List<AssetLiabilityStatementQuery001Dto> list2=new
				// ArrayList<>();
				// list1=service.findAssetsStatement(dto);
				// list2=service.findLiabilityInterestStatement(dto);
				// //list转map
				// data=ListconMap.listToMap(list1,data);
				// data=ListconMap.listToMap(list2,data);
				List<DisValProfitQuery001Dto> valList = service.findAssetsStatementPz(dto);
				List<DisAssetLiabilityStatementQuery001Dto> assetList = ListconMap.valToAssetList(valList);
				data = ListconMap.listToMap(assetList, data);
				String praManger = t.getPraManger(conn);
				data.put("bankname", praManger);

			}
			// 所有者权益变动表
			else if (ReportGlobalConstant.Owner_Equity.equals(reportType)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("booksetId", prodId);
				map.put("valDate", endDate);
				// map.put("reportTermType",
				// DictCodeListExt1.报表期限类型_季报.getValue());
				// map.put("reportTerm", DictCodeListExt1.季度_第四季度.getValue());
				// map.put("lastDimen", DictCodeListExt1.上期维度_同比.getValue());
				map.put("startDate", startDate);
				String thisTermVdate = DateUtil.addDay(startDate, -1);
				map.put("thisTermVdate", thisTermVdate);
				map.put("thisTermMdate", endDate);
				map.put("lastTermVdate",
						String.valueOf(DateUtil.getYear(thisTermVdate) - 1) + thisTermVdate.substring(4));
				map.put("lastTermMdate", String.valueOf(DateUtil.getYear(endDate) - 1) + endDate.substring(4));
				// List<OwnerEquityChange001Dto> list=oService.selectList(map);
				// data=ListconMap.ownListToMap(list);
				map.put("startDate", startDate);
				// 可配置的所有者权益变动表
				List<DisValProfitQuery001Dto> list2 = oService.selectListPz(map);
				data = ListconMap.ownValListToMap(list2);
				String praManger = t.getPraManger(conn);
				data.put("bankname", praManger);
				// 调用处理list方法
			}
			// 利润表
			else if (ReportGlobalConstant.Val_Profit.equals(reportType)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("booksetId", prodId);
				// map.put("lastDimen", DictCodeListExt1.上期维度_同比.getValue());
				// map.put("reportTermType",
				// DictCodeListExt1.报表期限类型_季报.getValue());
				// map.put("reportTerm",DictCodeListExt1.季度_第四季度.getValue());
				map.put("reportDate", endDate);
				map.put("thisTermVdate", startDate);
				map.put("thisTermMdate", endDate);
				//上期开始日、结束日
				Map<String,Object>  mapDate=getLastDate(startDate, endDate);
				map.put("lastTermVdate",mapDate.get("lastStartDate"));
				map.put("lastTermMdate",mapDate.get("lastEndDate"));
				List<DisValProfitQuery001Dto> list = vService.selectList(map);
				data = ListconMap.valListToMap(list);
				String praManger = t.getPraManger(conn);
				data.put("bankname", praManger);
				if(mapDate.get("lastStartDate")!=null&&mapDate.get("lastEndDate")!=null){
					data.put("lastdateval", DateUtil.format(DateUtil.parse(mapDate.get("lastStartDate").toString()), DateUtil.DEFAULT_DATE_FORMAT_CN)+"-"+
								DateUtil.format(DateUtil.parse(mapDate.get("lastEndDate").toString()), DateUtil.DEFAULT_DATE_FORMAT_CN));

				}
			}
			// 期末金融资产和金融负债的到期期限分析 add by zhangyf 20190424 start
			else if (ReportGlobalConstant.QMJRZCHJRFZDQQXFX.equals(reportType)) {
				data = t.printQMJRZCHJRFZDQQXFX(DateUtils.parseDate(startDate, "yyyy-MM-dd"),
						DateUtils.parseDate(endDate, "yyyy-MM-dd"), prodId, conn);
			}
			// 期末金融资产和金融负债的到期期限分析 add by zhangyf 20190424 end
			/** 新增一体化财务三表 add by dongxiangyang 20200211 start */
			// 一体化资产负债表
			else if (ReportGlobalConstant.Balance_Sheet_YTH.equals(reportType)) {
				DisAssetLiabilityStatementQuery001SearchDto dto = new DisAssetLiabilityStatementQuery001SearchDto();
				// 账套代码=产品代码
				dto.setBooksetId(prodId);
				// 报表维度=单账套
				dto.setReportDimen("01");
				// 报表时间=结束日
				dto.setReportDate(endDate);
				// 报表类型=日报
				dto.setReportTermType("01");
				List<DisValProfitQuery001Dto> valList = service.findAssetsStatementPzYth(dto);
				List<DisAssetLiabilityStatementQuery001Dto> assetList = ListconMap.valToAssetList(valList);
				data = ListconMap.listToMapYth(assetList, data);
				String praManger = t.getPraManger(conn);
				data.put("bankname", praManger);
			}
			// 一体化利润表
						else if (ReportGlobalConstant.Val_Profit_YTH.equals(reportType)) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("booksetId", prodId);
							map.put("reportDate", endDate);
							map.put("thisTermVdate", startDate);
							map.put("thisTermMdate", endDate);
							// 上期开始日、结束日
							Map<String, Object> mapDate = getLastDate(startDate, endDate);
							map.put("lastTermVdate", mapDate.get("lastStartDate"));
							map.put("lastTermMdate", mapDate.get("lastEndDate"));
							List<DisValProfitQuery001Dto> list = vService.selectListYth(map);
							data = ListconMap.valListToMapYth(list);
							String praManger = t.getPraManger(conn);
							data.put("bankname", praManger);
							if (mapDate.get("lastStartDate") != null && mapDate.get("lastEndDate") != null) {
								data.put("lastdateval",
										DateUtil.format(DateUtil.parse(mapDate.get("lastStartDate").toString()),
												DateUtil.DEFAULT_DATE_FORMAT_CN) + "-"
												+ DateUtil.format(DateUtil.parse(mapDate.get("lastEndDate").toString()),
														DateUtil.DEFAULT_DATE_FORMAT_CN));

							}
						}
			// 一体化所有者权益变动表
						else if (ReportGlobalConstant.Owner_Equity_YTH.equals(reportType)) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("booksetId", prodId);
							map.put("valDate", endDate);
							map.put("startDate", startDate);
							String thisTermVdate = DateUtil.addDay(startDate, -1);
							map.put("thisTermVdate", thisTermVdate);
							map.put("thisTermMdate", endDate);
							map.put("lastTermVdate",
									String.valueOf(DateUtil.getYear(thisTermVdate) - 1) + thisTermVdate.substring(4));
							//新增上年参数
							map.put("xzlastTermVdate",
									String.valueOf(DateUtil.getYear(thisTermVdate) - 1) + startDate.substring(4));
							map.put("lastTermMdate", String.valueOf(DateUtil.getYear(endDate) - 1) + endDate.substring(4));
							// List<OwnerEquityChange001Dto> list=oService.selectList(map);
							// data=ListconMap.ownListToMap(list);
							map.put("startDate", startDate);
							// 可配置的所有者权益变动表
							List<DisValProfitQuery001Dto> list2 = oService.selectListPzYth(map);
							//查询收益类型
							String  profitType=t.getProfitType(prodId, conn);
							data = ListconMap.ownValListToMapYth(list2,profitType);
							String praManger = t.getPraManger(conn);
							data.put("bankname", praManger);
							// 调用处理list方法
						}
			
			
			/** 新增一体化财务三表  add by dongxiangyang 20200211 end */
			RepProdInfo RepProdInfo = vService.selectByPrimaryKey(prodId);
			String  praManger=t.getPraManger(conn);
		    data.put("bankname", praManger);
		    String value = startDate;
		    if(StringUtil.isNotEmpty(RepProdInfo)){
		    	if(RepProdInfo.getVdate().compareTo(DateUtil.parse(startDate))>0&&RepProdInfo.getVdate().compareTo(DateUtil.parse(endDate))<0){
		    		value = DateUtil.format(RepProdInfo.getVdate());
		    	}
		    	data.put("prodname", RepProdInfo.getProdName());
		    }
			
		    data.put("dateval", DateUtil.format(DateUtil.parse(value), DateUtil.DEFAULT_DATE_FORMAT_CN)+"-"+DateUtil.format(DateUtil.parse(endDate), DateUtil.DEFAULT_DATE_FORMAT_CN));

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
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
		return data.get(jrField.getName());
	}
	
	
	/**
	 * 获取上一期的开始日和结束日
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public  static  Map<String,Object>   getLastDate(String startDate,String endDate){
		Map<String,Object>  map=new HashMap<String,Object>();
		//计算俩个日期间的天数
		Long  sDate= DateUtil.parse(startDate).getTime();
		Long  eDate=DateUtil.parse(endDate).getTime();
		int  day=(int)((eDate-sDate)/(3600*1000*24));
		if(day>=180){
			//上期开始日
			String  lastStartDate=String.valueOf(DateUtil.getYear(startDate)-1)+startDate.substring(4);
		    //上期结束日
			String  lastEndDate=String.valueOf(DateUtil.getYear(endDate)-1)+endDate.substring(4);
		    map.put("lastStartDate", lastStartDate);
		    map.put("lastEndDate", lastEndDate);
		    return map;
		}else if(day<180&&day>=90){
			//取上一季度的结束日和开始日
			String  mStartDate=startDate.substring(5);
			String  mEndDate=endDate.substring(5);
			if(mEndDate.equals("03-31")){
				mEndDate="12-31";
				mStartDate="10-01";
				//上期开始日
			String  lastStartDate=String.valueOf(DateUtil.getYear(startDate)-1)+"-"+mStartDate;
			//上期结束日
			String  lastEndDate=String.valueOf(DateUtil.getYear(endDate)-1)+"-"+mEndDate;
		    map.put("lastStartDate", lastStartDate);
			map.put("lastEndDate", lastEndDate);
			 return map;
			}else if(mEndDate.equals("06-30")){
				mEndDate="03-31";
				mStartDate="01-01";
				//上期开始日
				String  lastStartDate=String.valueOf(DateUtil.getYear(startDate))+"-"+mStartDate;
				//上期结束日
				String  lastEndDate=String.valueOf(DateUtil.getYear(endDate))+"-"+mEndDate;
			    map.put("lastStartDate", lastStartDate);
				map.put("lastEndDate", lastEndDate);
				 return map;
			}else  if(mEndDate.equals("09-30")){
				mEndDate="06-30";
				mStartDate="04-01";
				//上期开始日
				String  lastStartDate=String.valueOf(DateUtil.getYear(startDate))+"-"+mStartDate;
				//上期结束日
				String  lastEndDate=String.valueOf(DateUtil.getYear(endDate))+"-"+mEndDate;
			    map.put("lastStartDate", lastStartDate);
				map.put("lastEndDate", lastEndDate);
				 return map;
			}else if(mEndDate.equals("12-31")){
				mEndDate="09-30";
				mStartDate="07-01";
				//上期开始日
				String  lastStartDate=String.valueOf(DateUtil.getYear(startDate))+"-"+mStartDate;
				//上期结束日
				String  lastEndDate=String.valueOf(DateUtil.getYear(endDate))+"-"+mEndDate;
			    map.put("lastStartDate", lastStartDate);
				map.put("lastEndDate", lastEndDate);
				return map;
			}
		}else if(day<90&&day>=28){
			//上一个月月末
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.parse(endDate));
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
			calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
			calendar.set(Calendar.HOUR, -12);
			//上期结束日
			String lastEndDate=simpleDateFormat.format(calendar.getTime());
			//上一个月月初
			calendar.setTime(DateUtil.parse(startDate));
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
			//上期开始日
			String  lastStartDate=simpleDateFormat.format(calendar.getTime());
			map.put("lastStartDate", lastStartDate);
			map.put("lastEndDate", lastEndDate);
			return map;
		}
		
		
		
		return  map;
	}
	
}
