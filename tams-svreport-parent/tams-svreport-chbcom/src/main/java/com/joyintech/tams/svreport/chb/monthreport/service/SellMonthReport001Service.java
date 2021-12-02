
package com.joyintech.tams.svreport.chb.monthreport.service;


import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.monthreport.dao.SellMonthReportDetailMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReportDetail;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubCheck;
import com.joyintech.tams.svreport.chb.monthreport.dao.SellMonthReportMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReport;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SellMonthReport001Service extends CommonExportService<SellMonthReport> {
	@Autowired
    private SellMonthReportMapper sellMonthReportMapper;
	@Autowired
	private SellMonthReportDetailMapper sellMonthReportDetailMapper;
	@Autowired
	private CheckReport001Mapper checkReport001Mapper;
	@Autowired
	private ChbCheckRuleoutMapper chbCheckRuleoutMapper;
	@Autowired
	private BeforeSubCheck beforeSubCheck;
	@Autowired
    private MonthReportRegistBatchService monthReportRegistBatchService;

	/**
	 * 短sql最大一次最大插入条数
	 */
	@Value("${application.reportSelectOrDeleteCountForShenFen}")
	public  int reportSelectOrDeleteCountForShenFen;
	private static final String IS_CHECK_SUCESS="01";
    private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<SellMonthReport> searchPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		//将发送状态改为空
		pageParam.put("sendStatus", "");
		int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
		Page<SellMonthReport> page = queryForPage(
				pagenum,legth,()->sellMonthReportMapper.searchPage(pageParam));
		return page;
	}

	/**
	 * 处理查询前的参数 <功能详细描述>
	 *
	 * @param pageParam
	 * @see
	 * @since 1.0
	 */
	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<SellMonthReport> astValueList = JsonUtils.json2List(keyList,
						SellMonthReport.class);
				pageParam.put("reportList", astValueList);
			} catch (IOException e) {
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 查询参数条件
			// 数据状态
			String dataStatus = (String) pageParam.get("dataStatus");
			// 报送状态
			String sendStatus = (String) pageParam.get("sendStatus");
			// 反馈状态
			String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");


			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(dataStatus)) {
				pageParam.put("dataStatusList", StringUtil.splitToList(GlobalConstant.COMMA, dataStatus));
			}
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(sendStatus)) {
				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
			}
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList",
						StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
			}

		}
	}

	public ResponseEntity<byte[]> download(String pageId, Map<String, Object> selectParam,String datas) throws IOException {
		// 导出文件
		List<File> sourceFiles = new ArrayList<>();
		List<SellMonthReport> dataList=new ArrayList<SellMonthReport>();
		dataList= com.joyintech.framework.util.JsonUtils.json2List(datas,SellMonthReport.class);
		SellMonthReport report = dataList.get(0);
		String reportuuid = report.getReportuuid();
		String reportName = report.getReportName();
		String termid = report.getTermid();
		Map<String,Object> data  = new HashMap<String,Object>();
		List<SellMonthReportDetail> tempList=new ArrayList<SellMonthReportDetail>();
		List<SellMonthReport> dateTempList=new ArrayList<SellMonthReport>();
		String fileName = reportName +".xls";
		//查询当前期数的reportuuid主键
		List<SellMonthReport> reportuuidList = sellMonthReportDetailMapper.selectReportuuid(termid);
		if(reportuuidList.size() != 0){
		for(SellMonthReport monthReport :reportuuidList) {
				//分投资者分类
				if(monthReport.getReporttype().equals("1_1")) {
					tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							detail.setSaleAmountTotal(saleAmountTotal.divide(oneW));
							detail.setValueAmountTotal(valueAmountTotal.divide(oneW));
							if(balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW));
							}
						}
					}
					data.put("dataList1", tempList);
				}
				//分地区
			if(monthReport.getReporttype().equals("1_2")) {
				tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
				if (!tempList.isEmpty()) {
					for (SellMonthReportDetail detail : tempList) {
						BigDecimal oneW = new BigDecimal("10000");
						BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
						BigDecimal valueAmountTotal = detail.getValueAmountTotal();
						BigDecimal balanceAmt = detail.getBalanceAmt();
						detail.setSaleAmountTotal(saleAmountTotal.divide(oneW));
						detail.setValueAmountTotal(valueAmountTotal.divide(oneW));
						if(balanceAmt != null) {
							detail.setBalanceAmt(balanceAmt.divide(oneW));
						}
					}
				}
				data.put("dataList2", tempList);
			}
				//分销售渠道
			if(monthReport.getReporttype().equals("1_3")) {
				tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
				if (!tempList.isEmpty()) {
					for (SellMonthReportDetail detail : tempList) {
						BigDecimal oneW = new BigDecimal("10000");
						BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
						BigDecimal valueAmountTotal = detail.getValueAmountTotal();
						BigDecimal balanceAmt = detail.getBalanceAmt();
						detail.setSaleAmountTotal(saleAmountTotal.divide(oneW));
						detail.setValueAmountTotal(valueAmountTotal.divide(oneW));
						if(balanceAmt != null) {
							detail.setBalanceAmt(balanceAmt.divide(oneW));
						}
					}
				}
				data.put("dataList3", tempList);
				//查询时间
				dateTempList = sellMonthReportDetailMapper.selectReportDate(reportuuid);
				data.put("data", dateTempList);
			}
		}
		}
		 else{
			data.put("data", dateTempList);
			data.put("dataList1", tempList);
			data.put("dataList2", tempList);
			data.put("dataList3", tempList);
		}
		File depositfile = TisUtil.createFile(data, "pbc/" +"sellMonthReport_export", fileName);
		sourceFiles.add(depositfile);
		File outFile = null;
		outFile = sourceFiles.get(0);
		return ExportUtil.export(outFile.getName(), outFile, true);
	}


	public String priview(String pageid,Map<String, Object> selectParam,String datas) throws Exception {
		String reportuuid = selectParam.get("reportuuid").toString();
		String termid = selectParam.get("termid").toString();
		String reporttype = selectParam.get("reporttype").toString();
		Map<String,Object> data  = new HashMap<String,Object>();
		List<SellMonthReportDetail> tempList=new ArrayList<SellMonthReportDetail>();
		List<SellMonthReport> dateTempList=new ArrayList<SellMonthReport>();
		String fileName = "销售月度统计表"+ DateUtil.format(new Date(), "yyyy-MM-dd")+".xls";
		//查询当前期数的reportuuid主键
		List<SellMonthReport> reportuuidList = sellMonthReportDetailMapper.selectReportuuid(termid);
		if(reportuuidList.size() != 0){
			for(SellMonthReport monthReport :reportuuidList) {
				//分投资者分类
				if(reporttype.equals("1_1")) {
					tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							detail.setSaleAmountTotal(saleAmountTotal.divide(oneW));
							detail.setValueAmountTotal(valueAmountTotal.divide(oneW));
							if(balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW));
							}
						}
					}
					data.put("dataList1", tempList);
				}
				//分地区
				if(reporttype.equals("1_2")) {
					tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							detail.setSaleAmountTotal(saleAmountTotal.divide(oneW));
							detail.setValueAmountTotal(valueAmountTotal.divide(oneW));
							if(balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW));
							}
						}
					}
					data.put("dataList2", tempList);
				}
				//分销售渠道
				if(reporttype.equals("1_3")) {
					tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							detail.setSaleAmountTotal(saleAmountTotal.divide(oneW));
							detail.setValueAmountTotal(valueAmountTotal.divide(oneW));
							if (balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW));
							}
						}
					}
					data.put("dataList3", tempList);
				}
					//查询时间
					dateTempList = sellMonthReportDetailMapper.selectReportDate(reportuuid);
					data.put("data", dateTempList);
			}
		}
		else{
			data.put("data", dateTempList);
			data.put("dataList1", tempList);
			data.put("dataList2", tempList);
			data.put("dataList3", tempList);
		}
		File depositfile = TisUtil.createFile(data, "pbc/" +"sellMonthReport_export", fileName);
		return this.priView(depositfile, true);
	}
	//预览导出
	public String priView( File downloadFile, boolean deleteAfterExport){
		String rs =  XlsxToHtml.readExcelToHtmlThree(downloadFile,true);
		if (deleteAfterExport) {
			downloadFile.delete();
		}
		return rs;
	}

	/**
	 * 手工处理
	 * <一句话功能简述> <功能详细描述>
	 * @see
	 * @since 1.0
	 */
	public void updateSumStaus(Map<String, Object> map) {
		dealSearchParam(map);
		List<SellMonthReport> chbRegistList  = new ArrayList<SellMonthReport>();
		if(map.get("flag") != null && "01".equals(map.get("flag"))){
			//按条件查询全部
			chbRegistList = sellMonthReportMapper.searchPage(map);
		}else{
			chbRegistList = (List<SellMonthReport>)map.get("reportList");
		}
		Iterator<SellMonthReport> it=chbRegistList.iterator();
		while(it.hasNext()){
			SellMonthReport reportList =  it.next();
			if(!reportList.getSendStatus().equals("01")){
				it.remove();
			}
		}
		if (!chbRegistList.isEmpty() && chbRegistList.size() != 0) {
			for (int i = 0 ; i < chbRegistList.size() ; i++) {
				String termid = chbRegistList.get(i).getTermid();
				sellMonthReportMapper.updateInterfSubmitAndSeqBatch(termid);
			}
			}
	}

	/**
	 * 已手工处理的数据将报送状态改为未报送
	 *
	 * @see
	 * @since 1.0
	 */
	public void cancelMannul(Map<String, Object> map) {
		dealSearchParam(map);
		List<SellMonthReport> list = new ArrayList<SellMonthReport>();
		if(map.get("flag") != null && "01".equals(map.get("flag"))){
			list = sellMonthReportMapper.searchPage(map);
		}else{
			list = (List<SellMonthReport>) map.get("reportList");
		}
		if (!list.isEmpty() && list.size() != 0) {
			for (int i = 0 ; i < list.size() ; i++) {
				String termid = list.get(i).getTermid();
				sellMonthReportMapper.cancelMannultRegist(termid);
			}
		}
	}

    public Map<Object, Object> createData(Map<String, Object> selectParam){
    	dealSearchParam(selectParam);
    	Map<Object,Object> data  = new HashMap<Object,Object>();
    	@SuppressWarnings("unchecked")
		List<SellMonthReport> dataList =  (List<SellMonthReport>) selectParam.get("reportList");// 查询参数列表
    	String reportuuid = "";
    	if(dataList!=null&&dataList.size()>0){
    		reportuuid =dataList.get(0).getReportuuid();
    	}else{
    		reportuuid =selectParam.get("reportuuid").toString();
    	}
    	// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
    	String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
		SellMonthReport monthreport = sellMonthReportMapper.selectByPrimaryKey(reportuuid);
//    	if(monthreport==null)
    	data.put("report", monthreport);
    	data.put("termname", monthreport.getTermname());
    	selectParam.put("termid", monthreport.getTermid());
    	selectParam.put("reportuuid", reportuuid);
		List<SellMonthReportDetail> detailList  = sellMonthReportDetailMapper.selectByModel(selectParam);
		List<String> propertyCodeList=sellMonthReportDetailMapper.selectAllPropertyCode();
		Map<String,String>map =new HashMap<String,String>();
		for(String propertyCode:propertyCodeList){
			data.put(propertyCode, map);
		}
		for(SellMonthReportDetail detail : detailList){
			dealProdData(detail,isShowZero);
			data.put(detail.getPrdRegistCode(), divide10000(detail));
		}
    	return  data;
    }

	public SellMonthReportDetail divide10000(SellMonthReportDetail detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		if(detail.getSaleAmountTotal()!=null)
			detail.setSaleAmountTotal(detail.getSaleAmountTotal().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getValueAmountTotal()!=null)
			detail.setValueAmountTotal(detail.getValueAmountTotal().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getBalanceAmt()!=null)
			detail.setValueSharesTotal(detail.getBalanceAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getBalanceShares()!=null)
			detail.setValueSharesTotal(detail.getBalanceShares().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getSaleSharesTotal()!=null)
			detail.setValueSharesTotal(detail.getSaleSharesTotal().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getValueSharesTotal()!=null)
			detail.setValueSharesTotal(detail.getValueSharesTotal().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		return detail;

	}
    /**
     * @Description 导出预览为零的数据显示空还是显示零
     * @param detail
     * @version 1.7 2019年7月4日
     * @param isShowZero 
     */
    private void dealProdData(SellMonthReportDetail detail, String isShowZero) {
    	// 显示成零
		if("01".equals(isShowZero)){
			if(detail.getHolderNum()==null){
				detail.setHolderNum(BigDecimal.ZERO);
			}
			if(detail.getSaleAmountTotal()==null){
				detail.setSaleAmountTotal(BigDecimal.ZERO);
			}
			if(detail.getValueAmountTotal()==null){
				detail.setValueAmountTotal(BigDecimal.ZERO);
			}
			if(detail.getBalanceAmt()==null){
				detail.setBalanceAmt(BigDecimal.ZERO);
			}
			if(detail.getBalanceShares()==null){
				detail.setBalanceShares(BigDecimal.ZERO);
			}
			if(detail.getSaleSharesTotal()==null){
				detail.setSaleSharesTotal(BigDecimal.ZERO);
			}
			if(detail.getValueSharesTotal()==null){
				detail.setValueSharesTotal(BigDecimal.ZERO);
			}

		} else if("02".equals(isShowZero)){

			if(detail.getHolderNum()!=null&&(BigDecimal.ZERO).compareTo(detail.getHolderNum())==0){
				detail.setHolderNum(null);
			}
			if(detail.getSaleAmountTotal()!=null&&(BigDecimal.ZERO).compareTo(detail.getSaleAmountTotal())==0){
				detail.setSaleAmountTotal(null);
			}
			if(detail.getValueAmountTotal()!=null&&(BigDecimal.ZERO).compareTo(detail.getValueAmountTotal())==0){
				detail.setValueAmountTotal(null);
			}
			if(detail.getBalanceAmt()!=null&&(BigDecimal.ZERO).compareTo(detail.getBalanceAmt())==0){
				detail.setBalanceAmt(null);
			}
			if(detail.getBalanceShares()!=null&&(BigDecimal.ZERO).compareTo(detail.getBalanceShares())==0){
				detail.setBalanceShares(null);
			}
			if(detail.getSaleSharesTotal()!=null&&(BigDecimal.ZERO).compareTo(detail.getSaleSharesTotal())==0){
				detail.setSaleSharesTotal(null);
			}
			if(detail.getValueSharesTotal()!=null&&(BigDecimal.ZERO).compareTo(detail.getValueSharesTotal())==0){
				detail.setValueSharesTotal(null);
			}
		}
	}
}
