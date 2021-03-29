package com.joyintech.tams.svreport.chb.monthreport.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;
import com.joyintech.tams.common.svreport.chb.constant.ProdConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthFeePositionMapper;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthPrdPositionMapper;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportDetailMapper;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportTemplateMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthFeePosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthPrdPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportPrdConstants;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportTemplate;
import com.joyintech.tams.svreport.chb.monthreport.dto.PrdSaletargetDealDetail;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 银行月度报表生成产品端数据处理类
 * 
 * @author yuluan
 ** @version 1.0 2019年2月20日
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class MonthReportProdService {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChbMonthReportTemplateMapper chbMonthReportTemplateMapper;
	
	
	@Autowired
    private MonthReportServiceUtil monthReportServiceUtil;
	
	@Autowired
	private ChbMonthPrdPositionMapper chbMonthPrdPositionMapper;

	@Autowired
	private ChbMonthFeePositionMapper chbMonthFeePositionMapper;

	@Autowired
	private ChbMonthReportDetailMapper chbMonthReportDetailMapper;

	@Value("${application.reportSelectOrDeleteCount}")
	public int reportSelectOrDeleteCount;
	
	
	private final String  calcProdcounts = "calcProdcounts";
	private final String  Prodamount = "Prodamount";
	private final String  calcDurationprodcounts = "calcDurationprodcounts";
	private String  Prodnetamount = "Prodnetamount";
	private final String  Balance = "Balance";
	private final String  Customerprofit = "Customerprofit";

	

	public int genMonthPrdPosition(ChbMonthReport report) {
		ChbMonthReport param;
		//客户收益取值来源 01 从产品头寸取 02从产品交易表取
		String intamttype=CacheUtil.getSysParam("SVR_CHB_MONTHREPORT_INTAMT_TYPE");
		int ret = 0;
		try {
			param = (ChbMonthReport) report.clone();
			param.setIntamttype(intamttype);
			String exchangeSource=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE);
			//参数传递 汇率取值 用于净值币种折算
			param.setStatus(exchangeSource);
			
			String monthReport=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_CHBMONTH_REPORT);
			param.setMonthReport(monthReport);
			
			
			//本月净募集金额控制字段
			String cleanamtSource=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_CLEANAMT_SOURCE);
			param.setCleanamttype(cleanamtSource);
			 
			
			// 批量插入
			int singalCount = reportSelectOrDeleteCount;
			List<String> allProdList = param.getProdids();
			int totalCount = param.getProdids().size();
			if (totalCount == 0) {
				// 先删后插
				param.setProdids(null);
				ret += chbMonthPrdPositionMapper.deleteByModel(param);
				ret += chbMonthPrdPositionMapper.genPosition(param);
			} else if (totalCount == 1) {
				param.setProdid(param.getProdids().get(0));
				param.setProdids(null);
				// 先删后插
				ret += chbMonthPrdPositionMapper.deleteByModel(param);
				ret += chbMonthPrdPositionMapper.genPosition(param);
			} else if (totalCount > 1) {
				int loopCount = (int) Math.ceil((double) totalCount / (double) singalCount);
				for (int i = 0; i < loopCount; i++) {
					param.setProdids(allProdList.subList(i * singalCount,
							(i + 1) * singalCount > totalCount ? totalCount : (i + 1) * singalCount));
					ret += chbMonthPrdPositionMapper.deleteByModel(param);
					ret += chbMonthPrdPositionMapper.genPosition(param);
				}
			}
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return ret;
	}

	public int genMonthFeePosition(ChbMonthReport report) {
		ChbMonthReport param;
		int ret = 0;
		try {
			param = (ChbMonthReport) report.clone();
			// 批量插入
			int singalCount = reportSelectOrDeleteCount;
			List<String> allProdList = param.getProdids();
			int totalCount = param.getProdids() == null ? 0 : param.getProdids().size();
			if (totalCount == 0) {
				param.setProdids(null);
				// 先删后插
				ret += chbMonthFeePositionMapper.deleteByModel(param);
				ret += chbMonthFeePositionMapper.genPosition(param);
			} else if (totalCount == 1) {
				param.setProdid(param.getProdids().get(0));
				param.setProdids(null);
				// 先删后插
				ret += chbMonthFeePositionMapper.deleteByModel(param);
				ret += chbMonthFeePositionMapper.genPosition(param);
			} else if (totalCount > 1) {
				int loopCount = (int) Math.ceil((double) totalCount / (double) singalCount);
				for (int i = 0; i < loopCount; i++) {
					param.setProdids(allProdList.subList(i * singalCount,
							(i + 1) * singalCount > totalCount ? totalCount : (i + 1) * singalCount));
					ret += chbMonthFeePositionMapper.deleteByModel(param);
					ret += chbMonthFeePositionMapper.genPosition(param);
				}
			}
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return ret;
	}

	public void genReport(ChbMonthReport report,boolean ifTrigger) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(report.getEnddate());
		int year = calendar.get(Calendar.YEAR);// 获取年份
		int month = calendar.get(Calendar.MONTH) + 1;// 获取月份
		calendar.setTime(report.getStatusDate());
//		if (calendar.get(Calendar.DATE) != 1) { // 每月1日生成上月的数据
//			return;
//		}
		logger.info("生成银行月度报表-产品端：" + year + "年" + month + "月");
		logger.debug("开始日期:" + sdf.format(report.getStartdate()) + "----结束日期:" + sdf.format(report.getEnddate()));
		logger.debug("生成月报产品头寸：");
		genMonthPrdPosition(report);

		logger.debug("生成月报费用头寸：");
		genMonthFeePosition(report);

		logger.debug("生成月报明细数据：");
		genMonthPrd(report,ifTrigger);

	}

	@SuppressWarnings("deprecation")
	public void genMonthPrd(final ChbMonthReport report,boolean ifTrigger) {
		//获取系统参数是否邮储个性化 001 邮储个性化 
		String isPsbc=CacheUtil.getSysParam("CUSTOMER_BANK_CODE");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		Date firstday = report.getStartdate();
		Date lastday = report.getEnddate();

		
        /****update by leipeng 20200606  多法人改造.主表由数据实时生成**/

//		ChbMonthReport chbMonthReport = new ChbMonthReport();
//		chbMonthReport.setTermid(sdf.format(firstday));
//
//		// 报表ID
//		chbMonthReport.setReportuuid(UUID.randomUUID().toString());
//
//		// 报表类型
//		chbMonthReport.setReporttype(ChbMonthReportPrdConstants.REPORT_TYPE_PROD);
//
//		// 起止日期
//		chbMonthReport.setStartdate(firstday);
//		chbMonthReport.setEnddate(lastday);
//
//		// 数据日期：批处理日期
//		chbMonthReport.setStatusDate(report.getStatusDate());
//
//		// 报表频率：月报
//		chbMonthReport.setReportfrequ(ChbMonthReportPrdConstants.REPORT_FREQU_M);
//
//		// 发行机构代码取值
//		chbMonthReport.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
//		chbMonthReport.setIssuePartyName(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_NAME));
//
//		// 用户信息
//		chbMonthReport.setOrgCode(CommonUtil.getOrgCode());
//		chbMonthReport.setCreateDept(CommonUtil.getDeptCode());
//		chbMonthReport.setCreateUser(CommonUtil.getUserName());
//        chbMonthReport.setUpdateUser(CommonUtil.getUserName());
//
//		// 数据状态
//		chbMonthReport.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
//		chbMonthReport.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());

        /****update by leipeng 20200606  多法人改造.主表由数据实时生成**/

		Map<String,ChbMonthReport> resultinits = new HashMap<>();
		//生成主表数据
		ChbMonthReport chbMonthReport = monthReportServiceUtil.buildNewReport(report, ifTrigger);
		//主表详情数据
		Map<String, ChbMonthReportDetail> result = new HashMap<String, ChbMonthReportDetail>();
		//公用模板
		Map<String, ChbMonthReportDetail> resultTmp = new HashMap<String, ChbMonthReportDetail>();

		// 查询产品月报需要统计的项
		List<ChbMonthReportTemplate> listKey = chbMonthReportTemplateMapper.select("PROD");
//		for (ChbMonthReportTemplate k : listKey) {
//			ChbMonthReportDetail detail = new ChbMonthReportDetail();
//			detail.setTermid(sdf.format(firstday));
//			detail.setReporttype(ChbMonthReportPrdConstants.REPORT_TYPE_PROD);
//			detail.setPropertycode(k.getPropertycode());
//			resultTmp.put(k.getPropertycode(), detail);
//		}
		if (listKey.size() != 58) {
			logger.error("月报模板配置错误，请检查");
			return;
		}
		
		//为主表填入所有统计项
		DetailListBuild(listKey, sdf.format(firstday), result);
		/**
		 * 分支机构汇总类
		 */
		Map<String,Map<String,ChbMonthReportDetail>> deptMap = new HashMap();

		
		ChbMonthPrdPosition prod = new ChbMonthPrdPosition();
		prod.setStartdate(firstday);
		prod.setEnddate(lastday);
		prod.setProdids(report.getProdids());
		//修改查询逻辑.一定会查出产品对应机构信息
		List<ChbMonthPrdPosition> listPrdPosition = chbMonthPrdPositionMapper.select(prod);
		ChbMonthFeePosition fee = new ChbMonthFeePosition();
		fee.setStartdate(firstday);
		fee.setEnddate(lastday);
		fee.setProdids(report.getProdids());
		//修改查询逻辑.一定会查出产品对应机构信息
		List<ChbMonthFeePosition> listFeePosition = chbMonthFeePositionMapper.select(fee);
		
		
		
		/********================********/
		
		
		
		

//		ChbMonthReportDetail line1Sub1 = result.get(ChbMonthReportPrdConstants.GMLCCP); // 1.1
																						// 公募理财产品
		ChbMonthReportDetail line1Sub2 = result.get(ChbMonthReportPrdConstants.SMLCCP); // 1.2
																						// 私募理财产品
		ChbMonthReportDetail line2Sub1 = result.get(ChbMonthReportPrdConstants.GDSYL); // 2.1
																						// 固定收益类
		ChbMonthReportDetail line2Sub2 = result.get(ChbMonthReportPrdConstants.QYL); // 2.2
																						// 权益类
		ChbMonthReportDetail line2Sub3 = result.get(ChbMonthReportPrdConstants.SPJJRYSPL); // 2.3
																							// 商品及金融衍生品类
		ChbMonthReportDetail line2Sub4 = result.get(ChbMonthReportPrdConstants.HHL); // 2.4
																						// 混合类
		ChbMonthReportDetail line3Sub1Sub1 = result.get(ChbMonthReportPrdConstants.FBSJZX); // 3.1.1
																							// 封闭式净值型
		ChbMonthReportDetail line3Sub1Sub2 = result.get(ChbMonthReportPrdConstants.KFSJZX); // 3.1.2
																							// 开放式净值型
		ChbMonthReportDetail line3Sub1Sub2a = result.get(ChbMonthReportPrdConstants.XJGLLCP); // 其中：3.1.2.a
																								// 现金管理类产品
		ChbMonthReportDetail line3Sub1Sub2b = result.get(ChbMonthReportPrdConstants.FBQZBNYSDDQKFCP); // 3.1.2.b
																										// 封闭期在半年以上的定期开放式产品
		ChbMonthReportDetail line3Sub2Sub1 = result.get(ChbMonthReportPrdConstants.FBSFJZX); // 3.2.1
																								// 封闭式非净值型
		ChbMonthReportDetail line3Sub2Sub2 = result.get(ChbMonthReportPrdConstants.KFSFJZX); // 3.2.2
																								// 开放式非净值型
		ChbMonthReportDetail line4Sub1Sub1 = result.get(ChbMonthReportPrdConstants.FBSSGYYN); // 4.1.1
																								// 3个月（含）以内
		ChbMonthReportDetail line4Sub1Sub2 = result.get(ChbMonthReportPrdConstants.FBSSLGY); // 4.1.2
																								// 3-6个月(含)
		ChbMonthReportDetail line4Sub1Sub3 = result.get(ChbMonthReportPrdConstants.FBSLSGY); // 4.1.3
																								// 6-12个月（含）
		ChbMonthReportDetail line4Sub1Sub4 = result.get(ChbMonthReportPrdConstants.FBSYSN); // 4.1.4
																							// 1-3年（含）
		ChbMonthReportDetail line4Sub1Sub5 = result.get(ChbMonthReportPrdConstants.FBSSNYS); // 4.1.5
																								// 3年以上
		ChbMonthReportDetail line4Sub2Sub1 = result.get(ChbMonthReportPrdConstants.KFSMR); // 4.2.1
																							// 每日
		ChbMonthReportDetail line4Sub2Sub2 = result.get(ChbMonthReportPrdConstants.KFSQT); // 4.2.2
																							// 7天（含）以内
		ChbMonthReportDetail line4Sub2Sub3 = result.get(ChbMonthReportPrdConstants.KFSQTYGY); // 4.2.3
																								// 7天-1个月（含）
		ChbMonthReportDetail line4Sub2Sub4 = result.get(ChbMonthReportPrdConstants.KFSYSY); // 4.2.4
																							// 1-3个月（含）
		ChbMonthReportDetail line4Sub2Sub5 = result.get(ChbMonthReportPrdConstants.KFSSLY); // 4.2.5
																							// 3-6个月(含)
		ChbMonthReportDetail line4Sub2Sub6 = result.get(ChbMonthReportPrdConstants.KFSLSY); // 4.2.6
																							// 6-12个月（含）
		ChbMonthReportDetail line4Sub2Sub7 = result.get(ChbMonthReportPrdConstants.KFSYSN); // 4.2.7
																							// 1-3年（含）
		ChbMonthReportDetail line4Sub2Sub8 = result.get(ChbMonthReportPrdConstants.KFSSNYS); // 4.2.8
																								// 3年以上
		ChbMonthReportDetail line5Sub1 = result.get(ChbMonthReportPrdConstants.ZRR); // 5.1
																						// 自然人
		ChbMonthReportDetail line5Sub2 = result.get(ChbMonthReportPrdConstants.FRHQTZZ); // 5.2
																							// 法人或其他组织
		ChbMonthReportDetail line5Sub2Sub1 = result.get(ChbMonthReportPrdConstants.FJRJG); // 其中：5.2.1
																							// 非金融机构
		ChbMonthReportDetail line5Sub2Sub2 = result.get(ChbMonthReportPrdConstants.YHLJRJG); // 5.2.2
																								// 银行类金融机构
		ChbMonthReportDetail line5Sub2Sub3 = result.get(ChbMonthReportPrdConstants.QTJRJG); // 5.2.3
																							// 其他金融机构
		ChbMonthReportDetail line6Sub1 = result.get(ChbMonthReportPrdConstants.RMB); // 6.1
																						// 人民币
		ChbMonthReportDetail line6Sub2 = result.get(ChbMonthReportPrdConstants.MY); // 6.2
																					// 美元
		ChbMonthReportDetail line6Sub3 = result.get(ChbMonthReportPrdConstants.OY); // 6.3
																					// 欧元
		ChbMonthReportDetail line6Sub4 = result.get(ChbMonthReportPrdConstants.QTBZ); // 6.4
																						// 其他币种
		ChbMonthReportDetail line7Sub1 = result.get(ChbMonthReportPrdConstants.BBLCCP); // 7.1
																						// 保本理财产品（不含结构性存款）
		ChbMonthReportDetail line7Sub2 = result.get(ChbMonthReportPrdConstants.JRTYZSLCCP); // 7.2
																							// 金融同业专属理财产品

		ChbMonthReportDetail lineSum1Sub3 = result.get(ChbMonthReportPrdConstants.SUM_MJFS); // 合计:1.按募集方式划分
		ChbMonthReportDetail lineSum2Sub5 = result.get(ChbMonthReportPrdConstants.SUM_TZXZ); // 合计:2.按投资性质划分
		ChbMonthReportDetail lineSum3Sub3 = result.get(ChbMonthReportPrdConstants.SUM_YZMS); // 合计:3.按产品运作模式划分
		ChbMonthReportDetail lineSum4Sub1Sub6 = result.get(ChbMonthReportPrdConstants.SUM_FBSCP); // 合计:4.1
																									// 封闭式产品
		ChbMonthReportDetail lineSum4Sub2Sub9 = result.get(ChbMonthReportPrdConstants.SUM_KFSCP); // 合计:4.2
																									// 开放式产品
		ChbMonthReportDetail lineSum4Sub3 = result.get(ChbMonthReportPrdConstants.SUM_QX); // 合计:4.按期限划分
		ChbMonthReportDetail lineSum5Sub3 = result.get(ChbMonthReportPrdConstants.SUM_TZZCYQK); // 合计:5.投资者持有情况
		ChbMonthReportDetail lineSum6Sub5 = result.get(ChbMonthReportPrdConstants.SUM_BZ); // 合计:6.按产品币种划分
		List<ChbPrdPeriodYield>  yieldlist=chbMonthPrdPositionMapper.selectPrciceByProdAndDate(lastday);
		Map<String,ChbPrdPeriodYield> yieldmap=new HashMap<String,ChbPrdPeriodYield>();
		for(ChbPrdPeriodYield single:yieldlist){
			yieldmap.put(single.getProdId(), single);
		}
		// 汇率
		Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();

		for (ChbMonthPrdPosition pos : listPrdPosition) {
			if (pos.getCleanamtsum() == null)
				pos.setCleanamtsum(BigDecimal.ZERO);
			if (pos.getDirtyamtsum() == null)
				pos.setDirtyamtsum(BigDecimal.ZERO);
			if (pos.getCustomeramt() == null)
				pos.setCustomeramt(BigDecimal.ZERO);
			if (pos.getEndamtsumOth() == null)
				pos.setEndamtsumOth(BigDecimal.ZERO);
			if (pos.getPayamtsum() == null)
				pos.setPayamtsum(BigDecimal.ZERO);
			if (pos.getClosetermday() == null)
				pos.setClosetermday(BigDecimal.ZERO);
			if (pos.getVdate() == null) {
				logger.error("产品信息错误：起息日为空（" + pos.getProdid() + ")");
				continue;
			}
			
			/**
			 * 数据是否包含机构并且当前是否是联社模式
			 */
			if(null!=pos.getInstitutionCode()&&!resultinits.containsKey(pos.getInstitutionCode())&&CommonUtil.isBAmodel()){
				//各机构信息新建并保存
				report.setOrgCode(pos.getInstitutionCode());
				resultinits.put(pos.getInstitutionCode(), monthReportServiceUtil.buildNewReport(report, ifTrigger));
				//各机构对应的详情信息保存
				Map<String, ChbMonthReportDetail> Instresult = new HashMap<String, ChbMonthReportDetail>();

				//为主表填入所有统计项
				DetailListBuild(listKey, sdf.format(firstday), Instresult);
				
				deptMap.put(pos.getInstitutionCode(), Instresult);
			}
			Map<String,Boolean> flag = new HashMap<>();
			
			
			//修改组装数据类型
			flagType(1, flag, pos);
			

			if (ProdConstants.PROFITTYPE_FBBFDSY.equals(pos.getProfitType())) { // 非保本

				// 先保留本币，统计第六组，按币种划分
				// 第31行
				if (ProdConstants.CCY_CNY.equals(pos.getCcy())) { // 人民币 非保本
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line6Sub1.calcProdcounts(pos.getProdid());
//					line6Sub1.setProdamount(line6Sub1.getProdamount().add(pos.getDirtyamtsum()));
//					line6Sub1.setProdnetamount(line6Sub1.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line6Sub1.calcDurationprodcounts(pos.getProdid());
//					line6Sub1.setBalance(line6Sub1.getBalance().add(pos.getEndamtsumOth()));
					

					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.RMB);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.RMB);
 
				}

				// 第32行
				else if (ProdConstants.CCY_USD.equals(pos.getCcy())) { // 美元 非保本
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line6Sub2.calcProdcounts(pos.getProdid());
//					line6Sub2.setProdamount(line6Sub2.getProdamount().add(pos.getDirtyamtsum()));
//					line6Sub2.setProdnetamount(line6Sub2.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line6Sub2.calcDurationprodcounts(pos.getProdid());
//					line6Sub2.setBalance(line6Sub2.getBalance().add(pos.getEndamtsumOth()));
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.MY);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.MY);
					
				}

				// 第33行 6.3 欧元
				else if (ProdConstants.CCY_EUR.equals(pos.getCcy())) { // 欧元 非保本
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line6Sub3.calcProdcounts(pos.getProdid());
//					line6Sub3.setProdamount(line6Sub3.getProdamount().add(pos.getDirtyamtsum()));
//					line6Sub3.setProdnetamount(line6Sub3.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line6Sub3.calcDurationprodcounts(pos.getProdid());
//					line6Sub3.setBalance(line6Sub3.getBalance().add(pos.getEndamtsumOth()));
					
					

					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.OY);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.OY);
					
					
				}

				// 第34行 6.4 其他币种
				else { // 其他币种 非保本
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line6Sub4.calcProdcounts(pos.getProdid());
//					line6Sub4.setProdamount(line6Sub4.getProdamount().add(pos.getDirtyamtsum()));
//					line6Sub4.setProdnetamount(line6Sub4.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line6Sub4.calcDurationprodcounts(pos.getProdid());
//					line6Sub4.setBalance(line6Sub4.getBalance().add(pos.getEndamtsumOth()));
//					
				
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.QTBZ);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.QTBZ);
					
				}

				// 将币种转换成人民币，统计其它行
				if (!ProdConstants.CCY_CNY.equals(pos.getCcy())) {// 外币
					BigDecimal exchangeRate = ChbCcyConvertUtil.changeCnyMoney(pos.getCcy(), BigDecimal.ONE,
							lastday, rateMap);
					if (exchangeRate == null)
						exchangeRate = BigDecimal.ZERO;
					pos.setDirtyamtsum(pos.getDirtyamtsum().multiply(exchangeRate));
					pos.setCleanamtsum(pos.getCleanamtsum().multiply(exchangeRate));
					
					if(!yieldmap.containsKey(pos.getProdid())){
						pos.setEndamtsumOth(pos.getEndamtsumOth().multiply(exchangeRate));
					}
					pos.setCustomeramt(pos.getCustomeramt().multiply(exchangeRate));
				}

				/**与币种不同.重新编译**/
				
				flagType(2, flag, pos);
				
				
				// 第1行 1.1 公募理财产品
				if (ProdConstants.RAISEMODE_GM.equals(pos.getRaisemode())) { // 公募
//					// 第1列 发行产品只数
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0) {
//						line1Sub1.calcProdcounts(pos.getProdid());
//					}
//					// 第2列 募集金额
//					line1Sub1.setProdamount(line1Sub1.getProdamount().add(pos.getDirtyamtsum()));
//					// 第3列 净募集金额
//					line1Sub1.setProdnetamount(line1Sub1.getProdnetamount().add(pos.getCleanamtsum()));
//					// 第4列 存续产品只数
//					if ("01".equals(pos.getDurationflag())) { // 存续状态：存续
//						line1Sub1.calcDurationprodcounts(pos.getProdid());
//					}
//					// 第5列 期末余额
//					line1Sub1.setBalance(line1Sub1.getBalance().add(pos.getEndamtsumOth()));
//					// 第6列 客户收益
//					line1Sub1.setCustomerprofit(line1Sub1.getCustomerprofit().add(pos.getCustomeramt()));
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.GMLCCP);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.GMLCCP);
					
					
				}

				// 第二行 1.2 私募理财产品
				else {
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line1Sub2.calcProdcounts(pos.getProdid());
//					line1Sub2.setProdamount(line1Sub2.getProdamount().add(pos.getDirtyamtsum()));
//					line1Sub2.setProdnetamount(line1Sub2.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line1Sub2.calcDurationprodcounts(pos.getProdid());
//					line1Sub2.setBalance(line1Sub2.getBalance().add(pos.getEndamtsumOth()));
//					line1Sub2.setCustomerprofit(line1Sub2.getCustomerprofit().add(pos.getCustomeramt()));
//					
//					

					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.SMLCCP);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.SMLCCP);
					
				}

				// 第三行 2.1 固定收益类
				if (ProdConstants.IVSTORIENTATION_GDSY.equals(pos.getIvstorientation())) { // 固定收益类
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line2Sub1.calcProdcounts(pos.getProdid());
//					line2Sub1.setProdamount(line2Sub1.getProdamount().add(pos.getDirtyamtsum()));
//					line2Sub1.setProdnetamount(line2Sub1.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line2Sub1.calcDurationprodcounts(pos.getProdid());
//					line2Sub1.setBalance(line2Sub1.getBalance().add(pos.getEndamtsumOth()));
//					line2Sub1.setCustomerprofit(line2Sub1.getCustomerprofit().add(pos.getCustomeramt()));
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.GDSYL);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.GDSYL);
					
				}
				// 第四行 2.2 权益类
				else if (ProdConstants.IVSTORIENTATION_QYL.equals(pos.getIvstorientation())) { // 权益类
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line2Sub2.calcProdcounts(pos.getProdid());
//					line2Sub2.setProdamount(line2Sub2.getProdamount().add(pos.getDirtyamtsum()));
//					line2Sub2.setProdnetamount(line2Sub2.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line2Sub2.calcDurationprodcounts(pos.getProdid());
//					line2Sub2.setBalance(line2Sub2.getBalance().add(pos.getEndamtsumOth()));
//					line2Sub2.setCustomerprofit(line2Sub2.getCustomerprofit().add(pos.getCustomeramt()));
					
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.QYL);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.QYL);
				}

				// 第五行 2.3 商品及金融衍生品类
				else if (ProdConstants.IVSTORIENTATION_SPJRP.equals(pos.getIvstorientation())) { // 商品及金融衍生品类
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line2Sub3.calcProdcounts(pos.getProdid());
//					line2Sub3.setProdamount(line2Sub3.getProdamount().add(pos.getDirtyamtsum()));
//					line2Sub3.setProdnetamount(line2Sub3.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line2Sub3.calcDurationprodcounts(pos.getProdid());
//					line2Sub3.setBalance(line2Sub3.getBalance().add(pos.getEndamtsumOth()));
//					line2Sub3.setCustomerprofit(line2Sub3.getCustomerprofit().add(pos.getCustomeramt()));
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.SPJJRYSPL);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.SPJJRYSPL);
				}

				// 第六行 2.4 混合类
				else if (ProdConstants.IVSTORIENTATION_HHL.equals(pos.getIvstorientation())) { // 混合类
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line2Sub4.calcProdcounts(pos.getProdid());
//					line2Sub4.setProdamount(line2Sub4.getProdamount().add(pos.getDirtyamtsum()));
//					line2Sub4.setProdnetamount(line2Sub4.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line2Sub4.calcDurationprodcounts(pos.getProdid());
//					line2Sub4.setBalance(line2Sub4.getBalance().add(pos.getEndamtsumOth()));
//					line2Sub4.setCustomerprofit(line2Sub4.getCustomerprofit().add(pos.getCustomeramt()));
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.HHL);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.HHL);
				}
				
				
				/**第三类表格.重新编译**/
				
				flagType(1, flag, pos);
				
				

				// 第七行 3.1.1 封闭式净值型
				if (ProdConstants.OPERATEMODE_FBSJZX.equals(pos.getOperateMode())) { // 封闭式净值型
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line3Sub1Sub1.calcProdcounts(pos.getProdid());
//					line3Sub1Sub1.setProdamount(line3Sub1Sub1.getProdamount().add(pos.getDirtyamtsum()));
//					line3Sub1Sub1.setProdnetamount(line3Sub1Sub1.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line3Sub1Sub1.calcDurationprodcounts(pos.getProdid());
//					line3Sub1Sub1.setBalance(line3Sub1Sub1.getBalance().add(pos.getEndamtsumOth()));
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBSJZX);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBSJZX);
					
				}

				// 第8行 3.1.2 开放式净值型
				else if (ProdConstants.OPERATEMODE_KFSJZX.equals(pos.getOperateMode())) { // 开放式净值型
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line3Sub1Sub2.calcProdcounts(pos.getProdid());
//					line3Sub1Sub2.setProdamount(line3Sub1Sub2.getProdamount().add(pos.getDirtyamtsum()));
//					line3Sub1Sub2.setProdnetamount(line3Sub1Sub2.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line3Sub1Sub2.calcDurationprodcounts(pos.getProdid());
//					line3Sub1Sub2.setBalance(line3Sub1Sub2.getBalance().add(pos.getEndamtsumOth()));
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSJZX);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSJZX);

					// 第9行 3.1.2.a 现金管理类产品
					if ("01".equals(pos.getCashmanacate())) { // 现金管理类产品 非保本
																// 开放期限=每日
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line3Sub1Sub2a.calcProdcounts(pos.getProdid());
//						line3Sub1Sub2a.setProdamount(line3Sub1Sub2a.getProdamount().add(pos.getDirtyamtsum()));
//						line3Sub1Sub2a.setProdnetamount(line3Sub1Sub2a.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line3Sub1Sub2a.calcDurationprodcounts(pos.getProdid());
//						line3Sub1Sub2a.setBalance(line3Sub1Sub2a.getBalance().add(pos.getEndamtsumOth()));
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.XJGLLCP);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.XJGLLCP);
					}

					// 第10行 3.1.2.b 封闭期在半年以上的定期开放式产品
					if ("02".equals(pos.getOpenduration())) { // 封闭期在半年以上的定期开放式产品
																// 非保本 开放期限=每日
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line3Sub1Sub2b.calcProdcounts(pos.getProdid());
//						line3Sub1Sub2b.setProdamount(line3Sub1Sub2b.getProdamount().add(pos.getDirtyamtsum()));
//						line3Sub1Sub2b.setProdnetamount(line3Sub1Sub2b.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line3Sub1Sub2b.calcDurationprodcounts(pos.getProdid());
//						line3Sub1Sub2b.setBalance(line3Sub1Sub2b.getBalance().add(pos.getEndamtsumOth()));
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBQZBNYSDDQKFCP);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBQZBNYSDDQKFCP);
					}
				}

				// 第11行 3.2.1 封闭式非净值型
				else if (ProdConstants.OPERATEMODE_FBSFJZX.equals(pos.getOperateMode())) { // 封闭式非净值型
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line3Sub2Sub1.calcProdcounts(pos.getProdid());
//					line3Sub2Sub1.setProdamount(line3Sub2Sub1.getProdamount().add(pos.getDirtyamtsum()));
//					line3Sub2Sub1.setProdnetamount(line3Sub2Sub1.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line3Sub2Sub1.calcDurationprodcounts(pos.getProdid());
//					line3Sub2Sub1.setBalance(line3Sub2Sub1.getBalance().add(pos.getEndamtsumOth()));
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBSFJZX);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBSFJZX);
				}

				// 第12行 3.2.2 开放式非净值型
				else if (ProdConstants.OPERATEMODE_KFSFJZX.equals(pos.getOperateMode())) { // 开放式非净值型
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line3Sub2Sub2.calcProdcounts(pos.getProdid());
//					line3Sub2Sub2.setProdamount(line3Sub2Sub2.getProdamount().add(pos.getDirtyamtsum()));
//					line3Sub2Sub2.setProdnetamount(line3Sub2Sub2.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line3Sub2Sub2.calcDurationprodcounts(pos.getProdid());
//					line3Sub2Sub2.setBalance(line3Sub2Sub2.getBalance().add(pos.getEndamtsumOth()));
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSFJZX);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSFJZX);
				}

				if ((ProdConstants.OPERATEMODE_FBSJZX.equals(pos.getOperateMode())
						|| ProdConstants.OPERATEMODE_FBSFJZX.equals(pos.getOperateMode()))
			         	&&pos.getClosetermday()!=null) { // 封闭式
					/**add  4.1 封闭式产品 ，重写各分支判断逻辑：    by  dongxiangyang  start*/
					// 第13行 4.1.1 3个月（含）以内
					if(StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())
							&&pos.getClosetermday().compareTo(new BigDecimal(90))<0){

						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBSSGYYN);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBSSGYYN);
					}

					// 第14行 4.1.2 3-6个月(含)
					else if (StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())
							&&pos.getClosetermday().compareTo(new BigDecimal(90))>=0
					        &&pos.getClosetermday().compareTo(new BigDecimal(184))<=0) { // 封闭式
																						// 3-6个月(含)
																						// 非保本
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBSSLGY);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBSSLGY);
						
					}
					// 第15行
					else if (StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())
							&&pos.getClosetermday().compareTo(new BigDecimal(184))>0
							&&pos.getClosetermday().compareTo(new BigDecimal(366))<=0) { // 封闭式
																						// 6-12个月（含）
																						// 非保本
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBSLSGY);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBSLSGY);
					}

					// 第16行
					else if (StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())
							&&pos.getClosetermday().compareTo(new BigDecimal(366))>0
							&&pos.getClosetermday().compareTo(new BigDecimal(1096))<=0) { // 封闭式
																						// 1-3年（含）
																						// 非保本
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBSYSN);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBSYSN);
					}

					// 第17行产品收益标识_非保本浮动收益
					else if (StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())
							&&pos.getClosetermday().compareTo(new BigDecimal(1096))>0
							) { // 封闭式
																						// 3年以上
																						// 非保本

						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FBSSNYS);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FBSSNYS);
					}
					/**add  4.1 封闭式产品 ，重写各分支判断逻辑：    by  dongxiangyang  end*/

				}

				else if (ProdConstants.OPERATEMODE_KFSJZX.equals(pos.getOperateMode())
						|| ProdConstants.OPERATEMODE_KFSFJZX.equals(pos.getOperateMode())) { // 开放式
					// 第18行
					if (ProdConstants.TERMTYPE_U1D.equals(pos.getTermtype())) { // 开放式
																				// 每日
																				// 非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub1.calcProdcounts(pos.getProdid());
//						line4Sub2Sub1.setProdamount(line4Sub2Sub1.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub1.setProdnetamount(line4Sub2Sub1.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub1.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub1.setBalance(line4Sub2Sub1.getBalance().add(pos.getEndamtsumOth()));
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSMR);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSMR);
					}

					// 第19行
					else if (ProdConstants.TERMTYPE_U7D.equals(pos.getTermtype())) { // 开放式
																						// 7天（含）以内
																						// 非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub2.calcProdcounts(pos.getProdid());
//						line4Sub2Sub2.setProdamount(line4Sub2Sub2.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub2.setProdnetamount(line4Sub2Sub2.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub2.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub2.setBalance(line4Sub2Sub2.getBalance().add(pos.getEndamtsumOth()));
						
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSQT);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSQT);
					}

					// 第20行
					else if (ProdConstants.TERMTYPE_U1M.equals(pos.getTermtype())) { // 开放式
																						// 7天-1个月（含）
																						// 非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub3.calcProdcounts(pos.getProdid());
//						line4Sub2Sub3.setProdamount(line4Sub2Sub3.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub3.setProdnetamount(line4Sub2Sub3.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub3.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub3.setBalance(line4Sub2Sub3.getBalance().add(pos.getEndamtsumOth()));
						
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSQTYGY);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSQTYGY);
					}

					// 第21行
					else if (ProdConstants.TERMTYPE_U3M.equals(pos.getTermtype())) { // 开饭式
																						// 1-3个月（含）
																						// 非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub4.calcProdcounts(pos.getProdid());
//						line4Sub2Sub4.setProdamount(line4Sub2Sub4.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub4.setProdnetamount(line4Sub2Sub4.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub4.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub4.setBalance(line4Sub2Sub4.getBalance().add(pos.getEndamtsumOth()));
						
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSYSY);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSYSY);
					}

					// 第22行
					else if (ProdConstants.TERMTYPE_U6M.equals(pos.getTermtype())) { // 开放式
																						// 3-6个月(含)非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub5.calcProdcounts(pos.getProdid());
//						line4Sub2Sub5.setProdamount(line4Sub2Sub5.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub5.setProdnetamount(line4Sub2Sub5.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub5.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub5.setBalance(line4Sub2Sub5.getBalance().add(pos.getEndamtsumOth()));
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSSLY);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSSLY);
						
					}

					// 第23行
					else if (ProdConstants.TERMTYPE_U12M.equals(pos.getTermtype())) { // 开放式
																						// 6-12个月（含）
																						// 非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub6.calcProdcounts(pos.getProdid());
//						line4Sub2Sub6.setProdamount(line4Sub2Sub6.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub6.setProdnetamount(line4Sub2Sub6.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub6.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub6.setBalance(line4Sub2Sub6.getBalance().add(pos.getEndamtsumOth()));
						
						
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSLSY);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSLSY);
					}

					// 第24行
					else if (ProdConstants.TERMTYPE_U3Y.equals(pos.getTermtype())) { // 开放式
																						// 1-3年（含）
																						// 非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub7.calcProdcounts(pos.getProdid());
//						line4Sub2Sub7.setProdamount(line4Sub2Sub7.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub7.setProdnetamount(line4Sub2Sub7.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub7.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub7.setBalance(line4Sub2Sub7.getBalance().add(pos.getEndamtsumOth()));
						
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSYSN);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSYSN);
					}

					// 第25行
					else if (ProdConstants.TERMTYPE_B3Y.equals(pos.getTermtype())) { // 开放式
																						// 3年以上
																						// 非保本
//						if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//								&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//							line4Sub2Sub8.calcProdcounts(pos.getProdid());
//						line4Sub2Sub8.setProdamount(line4Sub2Sub8.getProdamount().add(pos.getDirtyamtsum()));
//						line4Sub2Sub8.setProdnetamount(line4Sub2Sub8.getProdnetamount().add(pos.getCleanamtsum()));
//						if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//							line4Sub2Sub8.calcDurationprodcounts(pos.getProdid());
//						line4Sub2Sub8.setBalance(line4Sub2Sub8.getBalance().add(pos.getEndamtsumOth()));
						
						buildAmt(pos, flag, result, ChbMonthReportPrdConstants.KFSSNYS);
						buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.KFSSNYS);
					}
				}
			if(!"001".equals(isPsbc)){
				
				
				flagType(3, flag, pos);
				/**add  5、投资者持有情况  处理分支改造：    by  dongxiang statr*/
				// 第26行
				if (ProdConstants.ORDIDENTIFIED_ZRR.equals(pos.getOrdIdentified())
				&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.1

					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.ZRR);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.ZRR);
					
				}
				// 第28行  5.2.1 非金融机构
				else if (ProdConstants.ORDIDENTIFIED_FJRJG.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.1
					// 非金融机构
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.FJRJG);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.FJRJG);
				}
				
				// 第29行  5.2.2 银行类金融机构
				else if (ProdConstants.ORDIDENTIFIED_YHJRJG.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.2
					// 银行类金融机构
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.YHLJRJG);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.YHLJRJG);
				}
				
				// 第30行  5.2.7 其他金融机构
				else if (ProdConstants.ORDIDENTIFIED_QTJRJG.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.3
					// 其他金融机构
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.QTJRJG);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.QTJRJG);
				}

				//    5.2.3 保险业金融机构
				else if (ProdConstants.ORDIDENTIFIED_BXJRJG.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.3
					// 保险业金融机构
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.BXJRJG);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.BXJRJG);
				}
				// 5.2.4 信托公司
				else if (ProdConstants.ORDIDENTIFIED_XTGS.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.3
					// 信托公司
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.XTGS);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.XTGS);
				}
				//    5.2.5 证券公司
				else if (ProdConstants.ORDIDENTIFIED_ZQGS.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.3
					// 信托公司
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.ZQGS);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.ZQGS);
				}
				//  5.2.6 基金公司
				else if (ProdConstants.ORDIDENTIFIED_JJGS.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.3
					// 基金公司
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.JJGS);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.JJGS);
				}
				//  5.2.8 金融机构资产管理产品
				else if (ProdConstants.ORDIDENTIFIED_JRJGZCGLCP.equals(pos.getOrdIdentified())
						&&StringUtil.equals(DictCodeList.产品收益标识_非保本浮动收益.getValue(),pos.getProfitType())) { // 5.2.3
					// 金融机构资产管理产品
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.JRJGZCGLCP);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.JRJGZCGLCP);
				}
				//5.2 法人或其他组织 求和
				result.get(ChbMonthReportPrdConstants.FRHQTZZ).setBalance(
						result.get(ChbMonthReportPrdConstants.FJRJG).getBalance().add(
								result.get(ChbMonthReportPrdConstants.YHLJRJG).getBalance().add(
										result.get(ChbMonthReportPrdConstants.QTJRJG).getBalance()).add(
										result.get(ChbMonthReportPrdConstants.BXJRJG).getBalance()).add(
										result.get(ChbMonthReportPrdConstants.XTGS).getBalance()).add(
										result.get(ChbMonthReportPrdConstants.ZQGS).getBalance()).add(
										result.get(ChbMonthReportPrdConstants.JJGS).getBalance()).add(
										result.get(ChbMonthReportPrdConstants.JRJGZCGLCP).getBalance())));
				//update by leipeng 银行模式下报错修改
				if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){
					deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.FRHQTZZ).setBalance(
							deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.FJRJG).getBalance().add(
									deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.YHLJRJG).getBalance().add(
											deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.QTJRJG).getBalance()).add(
													deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.BXJRJG).getBalance()).add(
															deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.XTGS).getBalance()).add(
																	deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.ZQGS).getBalance()).add(
																			deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.JJGS).getBalance()).add(
																					deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.JRJGZCGLCP).getBalance())));
				}

			// 法人或其他组织=第28行＋29行＋30行
			/**add  5、投资者持有情况  处理分支改造：    by  dongxiang    end*/
			}

			} // 非保统计结束

			// 第35行 7.1 保本理财产品（不含结构性存款）
			else { // 保本产品
				flagType(1, flag, pos);
				if (pos.getConPrdTraget() == null) { // 结构性产品挂钩标的
//					if (pos.getVdate().compareTo(pos.getEnddate()) <= 0
//							&& pos.getVdate().compareTo(pos.getStartdate()) >= 0)
//						line7Sub1.calcProdcounts(pos.getProdid());
//					line7Sub1.setProdamount(line7Sub1.getProdamount().add(pos.getDirtyamtsum()));
//					line7Sub1.setProdnetamount(line7Sub1.getProdnetamount().add(pos.getCleanamtsum()));
//					if ("01".equals(pos.getDurationflag())) // 存续状态：存续
//						line7Sub1.calcDurationprodcounts(pos.getProdid());
//					line7Sub1.setBalance(line7Sub1.getBalance().add(pos.getEndamtsumOth()));
					
					
					buildAmt(pos, flag, result, ChbMonthReportPrdConstants.BBLCCP);
					buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.BBLCCP);
				}
			}
			
			
			flagType(1, flag, pos);
           /**add  7、附注   处理分支改造     by  dongxiangyang   start*/
			//增加 【7.1 从母行划转的产品】
			if (ProdConstants.SALETARGET_CMHHZCP.equals(pos.getIsTransfer())) { // 金融理财产品
				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.CMHHZCP);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.CMHHZCP);
			}

			// 第36行 7.2 金融同业专属理财产品
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())) { // 金融理财产品
				
				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.JRTYZSLCCP);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.JRTYZSLCCP);
			}
			//其中：7.2.1 银行类金融机构
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())
			&&ProdConstants.ORDIDENTIFIED_YHJRJG.equals(pos.getOrdIdentified())) { // 银行类金融机构

				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.TYZS_YHLJRJG);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.TYZS_YHLJRJG);
			}
			// 7.2.2 保险业金融机构
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())
					&&ProdConstants.ORDIDENTIFIED_BXJRJG.equals(pos.getOrdIdentified())) { // 保险业金融机构

				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.TYZS_BXJRJG);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.TYZS_BXJRJG);
			}
			//  7.2.3 信托公司
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())
					&&ProdConstants.ORDIDENTIFIED_XTGS.equals(pos.getOrdIdentified())) { // 信托公司

				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.TYZS_XTGS);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.TYZS_XTGS);
			}
			//  7.2.4 证券公司
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())
					&&ProdConstants.ORDIDENTIFIED_ZQGS.equals(pos.getOrdIdentified())) { // 证券公司

				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.TYZS_ZQGS);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.TYZS_ZQGS);
			}
			//   7.2.5 基金公司
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())
					&&ProdConstants.ORDIDENTIFIED_JJGS.equals(pos.getOrdIdentified())) { // 基金公司

				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.TYZS_JJGS);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.TYZS_JJGS);
			}
			//     7.2.6 其他金融机构
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())
					&&ProdConstants.ORDIDENTIFIED_QTJRJG.equals(pos.getOrdIdentified())) { // 其他金融机构

				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.TYZS_QTJRJG);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.TYZS_QTJRJG);
			}
			// 7.2.7 金融机构资产管理产品
			if (ProdConstants.SALETARGET_JRTY.equals(pos.getSaleTarget())
					&&ProdConstants.ORDIDENTIFIED_JRJGZCGLCP.equals(pos.getOrdIdentified())) { // 金融机构资产管理产品

				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.TYZS_JRJGZCGLCP);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.TYZS_JRJGZCGLCP);
			}

			flagType(2, flag, pos);
			// 7.3  养老理财产品
			if (ProdConstants.IS_YLLCCP.equals(pos.getIsYllccp())) { // 金融机构资产管理产品
				buildAmt(pos, flag, result, ChbMonthReportPrdConstants.YLLCCP);
				buildAmt(pos, flag, deptMap.get(pos.getInstitutionCode()), ChbMonthReportPrdConstants.YLLCCP);
			}
			/**add  7、附注   处理分支改造     by  dongxiangyang   end*/

		} // 产品头寸循环结束

		// 第7列 本期银行收益
		for (ChbMonthFeePosition pos : listFeePosition) { // 费用头寸

			// ChbMonthReportDetail line1Sub1 =
			// result.get(ChbMonthReportPrdConstants.GMLCCP); // 1.1 公募理财产品
			// ChbMonthReportDetail line2 =
			// result.get(ChbMonthReportPrdConstants.SMLCCP); // 1.2 私募理财产品
			// ChbMonthReportDetail line3 =
			// result.get(ChbMonthReportPrdConstants.GDSYL ); // 2.1 固定收益类
			// ChbMonthReportDetail line2Sub2 =
			// result.get(ChbMonthReportPrdConstants.QYL ); // 2.2 权益类
			// ChbMonthReportDetail line2Sub3 =
			// result.get(ChbMonthReportPrdConstants.SPJJRYSPL ); // 2.3
			// 商品及金融衍生品类
			// ChbMonthReportDetail line2Sub4 =
			// result.get(ChbMonthReportPrdConstants.HHL ); // 2.4 混合类

			if (ProdConstants.PROFITTYPE_FBBFDSY.equals(pos.getProfitType())) {

				// 将币种转换成人民币
				if (!ProdConstants.CCY_CNY.equals(pos.getCcy())) {// 外币
					BigDecimal exchangeRate = ChbCcyConvertUtil.changeCnyMoney(pos.getCcy(), BigDecimal.ONE,
							lastday, rateMap);
					if (exchangeRate == null)
						exchangeRate = BigDecimal.ONE;
					pos.setBankamt(pos.getBankamt().multiply(exchangeRate));
//					pos.setBankamt(pos.getBankamt().multiply(exchangeRate));
//					pos.setBankamt(pos.getBankamt().multiply(exchangeRate));
//					pos.setBankamt(pos.getBankamt().multiply(exchangeRate));
				}

				// 第一行
				if (ProdConstants.RAISEMODE_GM.equals(pos.getRaisemode())) { // 募集方式－公募
//					line1Sub1.setBankprofit(line1Sub1.getBankprofit().add(pos.getBankamt()));
					
					result.get(ChbMonthReportPrdConstants.GMLCCP).setBankprofit(result.get(ChbMonthReportPrdConstants.GMLCCP).getBankprofit().add(pos.getBankamt()));
					if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){
						deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.GMLCCP).
						setBankprofit(deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.GMLCCP).getBankprofit().add(pos.getBankamt()));
					}

				}

				// 第二行
				if (ProdConstants.RAISEMODE_SM.equals(pos.getRaisemode())) { // 募集方式－私募
//					line1Sub2.setBankprofit(line1Sub2.getBankprofit().add(pos.getBankamt()));
					
					result.get(ChbMonthReportPrdConstants.SMLCCP).setBankprofit(result.get(ChbMonthReportPrdConstants.SMLCCP).getBankprofit().add(pos.getBankamt()));
					if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){
						deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.SMLCCP).
							setBankprofit(deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.SMLCCP).getBankprofit().add(pos.getBankamt()));
					}
				}

				if (ProdConstants.IVSTORIENTATION_GDSY.equals(pos.getIvstorientation())) { // 投资性质－固定收益类
//					line2Sub1.setBankprofit(line2Sub1.getBankprofit().add(pos.getBankamt())); // 第三行
					
					result.get(ChbMonthReportPrdConstants.GDSYL).setBankprofit(result.get(ChbMonthReportPrdConstants.GDSYL).getBankprofit().add(pos.getBankamt()));
					if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){

					deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.GDSYL).
							setBankprofit(deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.GDSYL).getBankprofit().add(pos.getBankamt()));
					}
					
				} else if (ProdConstants.IVSTORIENTATION_QYL.equals(pos.getIvstorientation())) { // 投资性质－权益类
//					line2Sub2.setBankprofit(line2Sub2.getBankprofit().add(pos.getBankamt())); // 第四行
					
					result.get(ChbMonthReportPrdConstants.QYL).setBankprofit(result.get(ChbMonthReportPrdConstants.QYL).getBankprofit().add(pos.getBankamt()));
					if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){

					deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.QYL).
							setBankprofit(deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.QYL).getBankprofit().add(pos.getBankamt()));
					}
					
				} else if (ProdConstants.IVSTORIENTATION_SPJRP.equals(pos.getIvstorientation())) { // 投资性质－商品及金融衍生品类
//					line2Sub3.setBankprofit(line2Sub3.getBankprofit().add(pos.getBankamt())); // 第五行
					
					result.get(ChbMonthReportPrdConstants.SPJJRYSPL).setBankprofit(result.get(ChbMonthReportPrdConstants.SPJJRYSPL).getBankprofit().add(pos.getBankamt()));
					if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){

					deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.SPJJRYSPL).
							setBankprofit(deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.SPJJRYSPL).getBankprofit().add(pos.getBankamt()));
					}
				} else if (ProdConstants.IVSTORIENTATION_HHL.equals(pos.getIvstorientation())) { // 投资性质－混合类
//					line2Sub4.setBankprofit(line2Sub4.getBankprofit().add(pos.getBankamt())); // 第六行
					
					result.get(ChbMonthReportPrdConstants.HHL).setBankprofit(result.get(ChbMonthReportPrdConstants.HHL).getBankprofit().add(pos.getBankamt()));
					if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){

					deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.HHL).
							setBankprofit(deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.HHL).getBankprofit().add(pos.getBankamt()));
					}
					// 7.3  养老理财产品
				}

				if (ProdConstants.IS_YLLCCP.equals(pos.getIsYllccp())) { // 金融机构资产管理产品
					result.get(ChbMonthReportPrdConstants.YLLCCP).setBankprofit(result.get(ChbMonthReportPrdConstants.YLLCCP).getBankprofit().add(pos.getBankamt()));
					if(null!=deptMap&&null!=deptMap.get(pos.getInstitutionCode())){
						deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.YLLCCP).
								setBankprofit(deptMap.get(pos.getInstitutionCode()).get(ChbMonthReportPrdConstants.YLLCCP).getBankprofit().add(pos.getBankamt()));
					}
				}
			}
		}
		
		//邮储个性化取值中的月报数据暂不处理多法人
		if("001".equals(isPsbc)){
			//邮储个性化取值
			PrdSaletargetDealDetail qmodel=new PrdSaletargetDealDetail();
			String exchangeSource=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE);
			SimpleDateFormat nsdf=new SimpleDateFormat("yyyyMMdd");
			qmodel.setDealdate(nsdf.format(lastday));
			qmodel.setRateflag(exchangeSource);
			PrdSaletargetDealDetail resultDetail=chbMonthReportDetailMapper.getInvestorHoldings(qmodel);
			// 第26行
			if(resultDetail!=null){
				line5Sub1.setBalance(resultDetail.getNaturalpersonqty());
				// 第28行
				line5Sub2Sub1.setBalance(resultDetail.getNonfinancialinstitution());
				// 第29行
				line5Sub2Sub2.setBalance(resultDetail.getBisrinqty());
				// 第30行
				line5Sub2Sub3.setBalance(resultDetail.getOficrinqty());
			}else{
				line5Sub1.setBalance(BigDecimal.ZERO);
				// 第28行
				line5Sub2Sub1.setBalance(BigDecimal.ZERO);
				// 第29行
				line5Sub2Sub2.setBalance(BigDecimal.ZERO);
				// 第30行
				line5Sub2Sub3.setBalance(BigDecimal.ZERO);
			}
			// 第27行
			line5Sub2.setBalance(
					line5Sub2Sub1.getBalance().add(line5Sub2Sub2.getBalance().add(line5Sub2Sub3.getBalance())));
		}
		
		
//		
//		// 合计 1.按募集方式划分
//		lineSum1Sub3.setProdcounts(line1Sub1.getProdcounts() + line1Sub2.getProdcounts());
//		lineSum1Sub3.setProdamount(line1Sub1.getProdamount().add(line1Sub2.getProdamount()));
//		lineSum1Sub3.setProdnetamount(line1Sub1.getProdnetamount().add(line1Sub2.getProdnetamount()));
//		lineSum1Sub3.setDurationprodcounts(line1Sub1.getDurationprodcounts() + line1Sub2.getDurationprodcounts());
//		lineSum1Sub3.setBalance(line1Sub1.getBalance().add(line1Sub2.getBalance()));
//		lineSum1Sub3.setCustomerprofit(line1Sub1.getCustomerprofit().add(line1Sub2.getCustomerprofit()));
//		lineSum1Sub3.setBankprofit(line1Sub1.getBankprofit().add(line1Sub2.getBankprofit()));
//
//		// 合计:2.按投资性质划分
//		lineSum2Sub5.setProdcounts(line2Sub1.getProdcounts() + line2Sub2.getProdcounts() + line2Sub3.getProdcounts()
//				+ line2Sub4.getProdcounts());
//		lineSum2Sub5.setProdamount(line2Sub1.getProdamount().add(line2Sub2.getProdamount())
//				.add(line2Sub3.getProdamount()).add(line2Sub4.getProdamount()));
//		lineSum2Sub5.setProdnetamount(line2Sub1.getProdnetamount().add(line2Sub2.getProdnetamount())
//				.add(line2Sub3.getProdnetamount()).add(line2Sub4.getProdnetamount()));
//		lineSum2Sub5.setDurationprodcounts(line2Sub1.getDurationprodcounts() + line2Sub2.getDurationprodcounts()
//				+ line2Sub3.getDurationprodcounts() + line2Sub4.getDurationprodcounts());
//		lineSum2Sub5.setBalance(line2Sub1.getBalance().add(line2Sub2.getBalance()).add(line2Sub3.getBalance())
//				.add(line2Sub4.getBalance()));
//		lineSum2Sub5.setCustomerprofit(line2Sub1.getCustomerprofit().add(line2Sub2.getCustomerprofit())
//				.add(line2Sub3.getCustomerprofit()).add(line2Sub4.getCustomerprofit()));
//		lineSum2Sub5.setBankprofit(line2Sub1.getBankprofit().add(line2Sub2.getBankprofit())
//				.add(line2Sub3.getBankprofit()).add(line2Sub4.getBankprofit()));
//
//		// 合计:3.按产品运作模式划分
//		lineSum3Sub3.setProdcounts(line3Sub1Sub1.getProdcounts() + line3Sub1Sub2.getProdcounts()
//				+ line3Sub2Sub1.getProdcounts() + line3Sub2Sub2.getProdcounts());
//		lineSum3Sub3.setProdamount(line3Sub1Sub1.getProdamount().add(line3Sub1Sub2.getProdamount())
//				.add(line3Sub2Sub1.getProdamount()).add(line3Sub2Sub2.getProdamount()));
//		lineSum3Sub3.setProdnetamount(line3Sub1Sub1.getProdnetamount().add(line3Sub1Sub2.getProdnetamount())
//				.add(line3Sub2Sub1.getProdnetamount()).add(line3Sub2Sub2.getProdnetamount()));
//		lineSum3Sub3.setDurationprodcounts(line3Sub1Sub1.getDurationprodcounts() + line3Sub1Sub2.getDurationprodcounts()
//				+ line3Sub2Sub1.getDurationprodcounts() + line3Sub2Sub2.getDurationprodcounts());
//		lineSum3Sub3.setBalance(line3Sub1Sub1.getBalance().add(line3Sub1Sub2.getBalance())
//				.add(line3Sub2Sub1.getBalance()).add(line3Sub2Sub2.getBalance()));
//		// lineSum3Sub3.setCustomerprofit(line3Sub1Sub1.getCustomerprofit().add(line3Sub1Sub2.getCustomerprofit()).add(line3Sub2Sub1.getCustomerprofit()).add(line3Sub2Sub2.getCustomerprofit()));
//		// lineSum3Sub3.setBankprofit(line3Sub1Sub1.getBankprofit().add(line3Sub1Sub2.getBankprofit()).add(line3Sub2Sub1.getBankprofit()).add(line3Sub2Sub2.getBankprofit()));
//
//		// 合计:4.1 封闭式产品
//		lineSum4Sub1Sub6.setProdcounts(line4Sub1Sub1.getProdcounts() + line4Sub1Sub2.getProdcounts()
//				+ line4Sub1Sub3.getProdcounts() + line4Sub1Sub4.getProdcounts() + line4Sub1Sub5.getProdcounts());
//		lineSum4Sub1Sub6.setProdamount(
//				line4Sub1Sub1.getProdamount().add(line4Sub1Sub2.getProdamount()).add(line4Sub1Sub3.getProdamount())
//						.add(line4Sub1Sub4.getProdamount()).add(line4Sub1Sub5.getProdamount()));
//		lineSum4Sub1Sub6.setProdnetamount(line4Sub1Sub1.getProdnetamount().add(line4Sub1Sub2.getProdnetamount())
//				.add(line4Sub1Sub3.getProdnetamount()).add(line4Sub1Sub4.getProdnetamount())
//				.add(line4Sub1Sub5.getProdnetamount()));
//		lineSum4Sub1Sub6.setDurationprodcounts(line4Sub1Sub1.getDurationprodcounts()
//				+ line4Sub1Sub2.getDurationprodcounts() + line4Sub1Sub3.getDurationprodcounts()
//				+ line4Sub1Sub4.getDurationprodcounts() + line4Sub1Sub5.getDurationprodcounts());
//		lineSum4Sub1Sub6.setBalance(line4Sub1Sub1.getBalance().add(line4Sub1Sub2.getBalance())
//				.add(line4Sub1Sub3.getBalance()).add(line4Sub1Sub4.getBalance()).add(line4Sub1Sub5.getBalance()));
//		// lineSum4Sub1Sub6.setCustomerprofit(line4Sub1Sub1.getCustomerprofit().add(line4Sub1Sub2.getCustomerprofit()).add(line4Sub1Sub3.getCustomerprofit()).add(line4Sub1Sub4.getCustomerprofit()).add(line4Sub1Sub5.getCustomerprofit()));
//		// lineSum4Sub1Sub6.setBankprofit(line4Sub1Sub1.getBankprofit().add(line4Sub1Sub2.getBankprofit()).add(line4Sub1Sub3.getBankprofit()).add(line4Sub1Sub4.getBankprofit()).add(line4Sub1Sub5.getBankprofit()));
//
//		// 合计:4.2 开放式产品
//		lineSum4Sub2Sub9.setProdcounts(line4Sub2Sub1.getProdcounts() + line4Sub2Sub2.getProdcounts()
//				+ line4Sub2Sub3.getProdcounts() + line4Sub2Sub4.getProdcounts() + line4Sub2Sub5.getProdcounts()
//				+ line4Sub2Sub6.getProdcounts() + line4Sub2Sub7.getProdcounts() + line4Sub2Sub8.getProdcounts());
//		lineSum4Sub2Sub9.setProdamount(line4Sub2Sub1.getProdamount().add(line4Sub2Sub2.getProdamount())
//				.add(line4Sub2Sub3.getProdamount()).add(line4Sub2Sub4.getProdamount())
//				.add(line4Sub2Sub5.getProdamount()).add(line4Sub2Sub6.getProdamount())
//				.add(line4Sub2Sub7.getProdamount()).add(line4Sub2Sub8.getProdamount()));
//		lineSum4Sub2Sub9.setProdnetamount(line4Sub2Sub1.getProdnetamount().add(line4Sub2Sub2.getProdnetamount())
//				.add(line4Sub2Sub3.getProdnetamount()).add(line4Sub2Sub4.getProdnetamount())
//				.add(line4Sub2Sub5.getProdnetamount()).add(line4Sub2Sub6.getProdnetamount())
//				.add(line4Sub2Sub7.getProdnetamount()).add(line4Sub2Sub8.getProdnetamount()));
//		lineSum4Sub2Sub9
//				.setDurationprodcounts(line4Sub2Sub1.getDurationprodcounts() + line4Sub2Sub2.getDurationprodcounts()
//						+ line4Sub2Sub3.getDurationprodcounts() + line4Sub2Sub4.getDurationprodcounts()
//						+ line4Sub2Sub5.getDurationprodcounts() + line4Sub2Sub6.getDurationprodcounts()
//						+ line4Sub2Sub7.getDurationprodcounts() + line4Sub2Sub8.getDurationprodcounts());
//		lineSum4Sub2Sub9.setBalance(line4Sub2Sub1.getBalance().add(line4Sub2Sub2.getBalance())
//				.add(line4Sub2Sub3.getBalance()).add(line4Sub2Sub4.getBalance()).add(line4Sub2Sub5.getBalance())
//				.add(line4Sub2Sub6.getBalance()).add(line4Sub2Sub7.getBalance()).add(line4Sub2Sub8.getBalance()));
//		// lineSum4Sub2Sub9.setCustomerprofit(line4Sub2Sub1.getCustomerprofit().add(line4Sub2Sub2.getCustomerprofit()).add(line4Sub2Sub3.getCustomerprofit()).add(line4Sub2Sub4.getCustomerprofit())
//		// .add(line4Sub2Sub5.getCustomerprofit()).add(line4Sub2Sub6.getCustomerprofit()).add(line4Sub2Sub7.getCustomerprofit()).add(line4Sub2Sub8.getCustomerprofit())
//		// );
//		// lineSum4Sub2Sub9.setBankprofit(line4Sub2Sub1.getBankprofit().add(line4Sub2Sub2.getBankprofit()).add(line4Sub2Sub3.getBankprofit()).add(line4Sub2Sub4.getBankprofit())
//		// .add(line4Sub2Sub5.getBankprofit()).add(line4Sub2Sub6.getBankprofit()).add(line4Sub2Sub7.getBankprofit()).add(line4Sub2Sub8.getBankprofit())
//		// );
//
//		// 合计:4.按期限划分
//		lineSum4Sub3.setProdcounts(lineSum4Sub1Sub6.getProdcounts() + lineSum4Sub2Sub9.getProdcounts());
//		lineSum4Sub3.setProdamount(lineSum4Sub1Sub6.getProdamount().add(lineSum4Sub2Sub9.getProdamount()));
//		lineSum4Sub3.setProdnetamount(lineSum4Sub1Sub6.getProdnetamount().add(lineSum4Sub2Sub9.getProdnetamount()));
//		lineSum4Sub3.setDurationprodcounts(
//				lineSum4Sub1Sub6.getDurationprodcounts() + lineSum4Sub2Sub9.getDurationprodcounts());
//		lineSum4Sub3.setBalance(lineSum4Sub1Sub6.getBalance().add(lineSum4Sub2Sub9.getBalance()));
//		// lineSum4Sub3.setCustomerprofit(lineSum4Sub1Sub6.getCustomerprofit().add(lineSum4Sub2Sub9.getCustomerprofit()));
//		// lineSum4Sub3.setBankprofit(lineSum4Sub1Sub6.getBankprofit().add(lineSum4Sub2Sub9.getBankprofit()));
//
//		// 合计:5.投资者持有情况
//		lineSum5Sub3.setProdcounts(line5Sub1.getProdcounts() + line5Sub2.getProdcounts());
//		lineSum5Sub3.setProdamount(line5Sub1.getProdamount().add(line5Sub2.getProdamount()));
//		lineSum5Sub3.setProdnetamount(line5Sub1.getProdnetamount().add(line5Sub2.getProdnetamount()));
//		lineSum5Sub3.setDurationprodcounts(line5Sub1.getDurationprodcounts() + line5Sub2.getDurationprodcounts());
//		lineSum5Sub3.setBalance(line5Sub1.getBalance().add(line5Sub2.getBalance()));
//		// lineSum5Sub3.setCustomerprofit(line5Sub1.getCustomerprofit().add(line5Sub2.getCustomerprofit()));
//		// lineSum5Sub3.setBankprofit(line5Sub1.getBankprofit().add(line5Sub2.getBankprofit()));
//
//		// 合计:6.按产品币种划分
//		lineSum6Sub5.setProdcounts(line6Sub1.getProdcounts() + line6Sub2.getProdcounts() + line6Sub3.getProdcounts()
//				+ line6Sub4.getProdcounts());
//		lineSum6Sub5.setProdamount(line6Sub1.getProdamount().add(line6Sub2.getProdamount())
//				.add(line6Sub3.getProdamount()).add(line6Sub4.getProdamount()));
//		lineSum6Sub5.setProdnetamount(line6Sub1.getProdnetamount().add(line6Sub2.getProdnetamount())
//				.add(line6Sub3.getProdnetamount()).add(line6Sub4.getProdnetamount()));
//		lineSum6Sub5.setDurationprodcounts(line6Sub1.getDurationprodcounts() + line6Sub2.getDurationprodcounts()
//				+ line6Sub3.getDurationprodcounts() + line6Sub4.getDurationprodcounts());
//		lineSum6Sub5.setBalance(line6Sub1.getBalance().add(line6Sub2.getBalance()).add(line6Sub3.getBalance())
//				.add(line6Sub4.getBalance()));
//		// lineSum6Sub5.setCustomerprofit(line6Sub1.getCustomerprofit().add(line6Sub2.getCustomerprofit()).add(line6Sub3.getCustomerprofit()).add(line6Sub4.getCustomerprofit()));
//		// lineSum6Sub5.setBankprofit(line6Sub1.getBankprofit().add(line6Sub2.getBankprofit()).add(line6Sub3.getBankprofit()).add(line6Sub4.getBankprofit()));
//
//		
		
		//主表进行数据汇总计算
		sumDetail(result);
		
		// 遍历result，插入全部的数据
		//删除时上一次已报送数据不能删除
//		if(report.getWbsreportuuid()!=null&&!"".equals(report.getWbsreportuuid())){
//			//数据删除时不区分机构.全部删除
//			chbMonthReportDetailMapper.delete(sdf.format(firstday), ChbMonthReportPrdConstants.REPORT_TYPE_PROD,null);
//		}
		
		//删除汇总表明细数据多法人不能生成汇总数据
//		for (ChbMonthReportDetail v : result.values()) {
//			//插入主表的uuid
//			v.setReportuuid(chbMonthReport.getReportuuid());
//			chbMonthReportDetailMapper.insert(v);
//		}
		
		
		//各个分行进行数据汇总计算
		for (Entry<String, Map<String, ChbMonthReportDetail>> map : deptMap.entrySet()) {
			//数据汇总计算
			sumDetail(map.getValue());

			//获取当前机构对应的月报详情
			ChbMonthReport chb = resultinits.get(map.getKey());
			//每个机构分别删除自己的产品，避免出现错误删除的情况
			chbMonthReportDetailMapper.deleteByTermIdEx(sdf.format(firstday), ChbMonthReportPrdConstants.REPORT_TYPE_PROD,chb.getOrgCode());

			//删除汇总表数据
			for (Entry<String,  ChbMonthReportDetail> v : map.getValue().entrySet()) {
				v.getValue().setDetailuuid(IDGenerator.generate(36));
				//插入当前机构的月报ID
				v.getValue().setReportuuid(chb.getReportuuid());
				chbMonthReportDetailMapper.insert(v.getValue());
			}
		}

	}
	

	/**
	 * 数据组装
	 * @param pos 当前pos
	 * @param flag 特殊字段数据处理
	 * @param result 参数
	 * @param code 编号
	 */
	public void buildAmt(ChbMonthPrdPosition pos , Map<String,Boolean> flag,Map<String,ChbMonthReportDetail> result,String  code){
		//银行模式子项为空.直接跳出
		if(null==result){
			return;
		}
		if (null==flag.get(calcProdcounts)||flag.get(calcProdcounts)) {
			result.get(code).calcProdcounts(pos.getProdid());
		}
		if (null==flag.get(Prodamount)||flag.get(Prodamount)) {
			result.get(code).setProdamount(result.get(code).getProdamount().add(pos.getDirtyamtsum()));
		}
		// 第3列 净募集金额

		if (null==flag.get(Prodnetamount)||flag.get(Prodnetamount)) {
			result.get(code).setProdnetamount(result.get(code).getProdnetamount().add(pos.getCleanamtsum()));
		}
		// 第4列 存续产品只数
		if (null==flag.get(calcDurationprodcounts)||flag.get(calcDurationprodcounts)) {
			result.get(code).calcDurationprodcounts(pos.getProdid());
		}
		// 第5列 期末余额
		if (null==flag.get(Balance)||flag.get(Balance)) {
			result.get(code).setBalance(result.get(code).getBalance().add(pos.getEndamtsumOth()));
		}
		// 第6列 客户收益
		if (null==flag.get(Customerprofit)||flag.get(Customerprofit)) {
			result.get(code).setCustomerprofit(result.get(code).getCustomerprofit().add(pos.getCustomeramt()));
		}
		/**add  新增列  by  dongxiangyang  start*/


		//合规新产品期末存续只数//（只）
		if(StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(),pos.getIsFhxgcp())&&
				(null==flag.get(calcDurationprodcounts)||flag.get(calcDurationprodcounts))){
			result.get(code).calcxgdurationprodcounts(pos.getProdid());
		}
		if(StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(),pos.getIsFhxgcp())&&(
				null==flag.get(Balance)||flag.get(Balance))){
			//合规新产品期末余额
			result.get(code).setXgbalance(result.get(code).getXgbalance().add(pos.getEndamtsumOth()));
		}

         //本期兑付金额
		result.get(code).setProdpayamount(result.get(code).getProdpayamount().add(pos.getPayamtsum()));

		/**add  新增列  by  dongxiangyang  end*/

	}
	
	/**
	 * 塞入新建数据
	 * @param listKey
	 * @param termid
	 * @param result
	 */
	public void DetailListBuild( List<ChbMonthReportTemplate> listKey,String termid,Map<String, ChbMonthReportDetail> result){
		for (ChbMonthReportTemplate k : listKey) {
			ChbMonthReportDetail detail = new ChbMonthReportDetail();
			detail.setTermid(termid);
			detail.setReporttype(ChbMonthReportPrdConstants.REPORT_TYPE_PROD);
			detail.setPropertycode(k.getPropertycode());
			result.put(k.getPropertycode(), detail);
		}
	}
	
	
	/**
	 * 表内数据保存列表.利用flag字段进行表字段是否保存的逻辑判断
	 * @param type 表保存类型
	 * @param flag 字段是否取值判断
	 * @param pos  表数据判断来源
	 */
	public void flagType (int type,Map<String,Boolean> flag,ChbMonthPrdPosition pos){		
		flag.clear();
		
		switch (type) {
		case 1:
			//产品编号,产品基本信息由逻辑判断.客户收益不保存
			flag.put(calcDurationprodcounts, "01".equals(pos.getDurationflag()));
			flag.put(calcProdcounts, pos.getVdate().compareTo(pos.getEnddate()) <= 0
					&& pos.getVdate().compareTo(pos.getStartdate()) >= 0);	
			flag.put(Customerprofit, false);

			
			break;
		case 2:
			//产品编号,产品基本信息由逻辑判断
			flag.put(calcDurationprodcounts, "01".equals(pos.getDurationflag()));
			flag.put(calcProdcounts, pos.getVdate().compareTo(pos.getEnddate()) <= 0
					&& pos.getVdate().compareTo(pos.getStartdate()) >= 0);	
			break;
		case 3:
			//仅保存剩余现金
			flag.put(calcProdcounts, false);
			flag.put(Prodamount, false);
			flag.put(Prodnetamount, false);
			flag.put(calcDurationprodcounts, false);
			flag.put(Customerprofit, false);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 合计计算逻辑,直接取原代码提取..此部分入口可传入分支机构或者总行数据
	 * @param result
	 */
	public void sumDetail(Map<String,ChbMonthReportDetail> result){
		

		ChbMonthReportDetail line1Sub1 = result.get(ChbMonthReportPrdConstants.GMLCCP); // 1.1
		// 公募理财产品
		ChbMonthReportDetail line1Sub2 = result.get(ChbMonthReportPrdConstants.SMLCCP); // 1.2
				// 私募理财产品
		ChbMonthReportDetail line2Sub1 = result.get(ChbMonthReportPrdConstants.GDSYL); // 2.1
				// 固定收益类
		ChbMonthReportDetail line2Sub2 = result.get(ChbMonthReportPrdConstants.QYL); // 2.2
				// 权益类
		ChbMonthReportDetail line2Sub3 = result.get(ChbMonthReportPrdConstants.SPJJRYSPL); // 2.3
					// 商品及金融衍生品类
		ChbMonthReportDetail line2Sub4 = result.get(ChbMonthReportPrdConstants.HHL); // 2.4
				// 混合类
		ChbMonthReportDetail line3Sub1Sub1 = result.get(ChbMonthReportPrdConstants.FBSJZX); // 3.1.1
					// 封闭式净值型
		ChbMonthReportDetail line3Sub1Sub2 = result.get(ChbMonthReportPrdConstants.KFSJZX); // 3.1.2
					// 开放式净值型
		ChbMonthReportDetail line3Sub1Sub2a = result.get(ChbMonthReportPrdConstants.XJGLLCP); // 其中：3.1.2.a
						// 现金管理类产品
		ChbMonthReportDetail line3Sub1Sub2b = result.get(ChbMonthReportPrdConstants.FBQZBNYSDDQKFCP); // 3.1.2.b
								// 封闭期在半年以上的定期开放式产品
		ChbMonthReportDetail line3Sub2Sub1 = result.get(ChbMonthReportPrdConstants.FBSFJZX); // 3.2.1
						// 封闭式非净值型
		ChbMonthReportDetail line3Sub2Sub2 = result.get(ChbMonthReportPrdConstants.KFSFJZX); // 3.2.2
						// 开放式非净值型
		ChbMonthReportDetail line4Sub1Sub1 = result.get(ChbMonthReportPrdConstants.FBSSGYYN); // 4.1.1
						// 3个月（含）以内
		ChbMonthReportDetail line4Sub1Sub2 = result.get(ChbMonthReportPrdConstants.FBSSLGY); // 4.1.2
						// 3-6个月(含)
		ChbMonthReportDetail line4Sub1Sub3 = result.get(ChbMonthReportPrdConstants.FBSLSGY); // 4.1.3
						// 6-12个月（含）
		ChbMonthReportDetail line4Sub1Sub4 = result.get(ChbMonthReportPrdConstants.FBSYSN); // 4.1.4
					// 1-3年（含）
		ChbMonthReportDetail line4Sub1Sub5 = result.get(ChbMonthReportPrdConstants.FBSSNYS); // 4.1.5
						// 3年以上
		ChbMonthReportDetail line4Sub2Sub1 = result.get(ChbMonthReportPrdConstants.KFSMR); // 4.2.1
					// 每日
		ChbMonthReportDetail line4Sub2Sub2 = result.get(ChbMonthReportPrdConstants.KFSQT); // 4.2.2
					// 7天（含）以内
		ChbMonthReportDetail line4Sub2Sub3 = result.get(ChbMonthReportPrdConstants.KFSQTYGY); // 4.2.3
						// 7天-1个月（含）
		ChbMonthReportDetail line4Sub2Sub4 = result.get(ChbMonthReportPrdConstants.KFSYSY); // 4.2.4
					// 1-3个月（含）
		ChbMonthReportDetail line4Sub2Sub5 = result.get(ChbMonthReportPrdConstants.KFSSLY); // 4.2.5
					// 3-6个月(含)
		ChbMonthReportDetail line4Sub2Sub6 = result.get(ChbMonthReportPrdConstants.KFSLSY); // 4.2.6
					// 6-12个月（含）
		ChbMonthReportDetail line4Sub2Sub7 = result.get(ChbMonthReportPrdConstants.KFSYSN); // 4.2.7
					// 1-3年（含）
		ChbMonthReportDetail line4Sub2Sub8 = result.get(ChbMonthReportPrdConstants.KFSSNYS); // 4.2.8
						// 3年以上
		ChbMonthReportDetail line5Sub1 = result.get(ChbMonthReportPrdConstants.ZRR); // 5.1
				// 自然人
		ChbMonthReportDetail line5Sub2 = result.get(ChbMonthReportPrdConstants.FRHQTZZ); // 5.2
					// 法人或其他组织
		ChbMonthReportDetail line5Sub2Sub1 = result.get(ChbMonthReportPrdConstants.FJRJG); // 其中：5.2.1
					// 非金融机构
		ChbMonthReportDetail line5Sub2Sub2 = result.get(ChbMonthReportPrdConstants.YHLJRJG); // 5.2.2
						// 银行类金融机构
		ChbMonthReportDetail line5Sub2Sub3 = result.get(ChbMonthReportPrdConstants.QTJRJG); // 5.2.3
					// 其他金融机构


		ChbMonthReportDetail line6Sub1 = result.get(ChbMonthReportPrdConstants.RMB); // 6.1
				// 人民币
		ChbMonthReportDetail line6Sub2 = result.get(ChbMonthReportPrdConstants.MY); // 6.2
			// 美元
		ChbMonthReportDetail line6Sub3 = result.get(ChbMonthReportPrdConstants.OY); // 6.3
			// 欧元
		ChbMonthReportDetail line6Sub4 = result.get(ChbMonthReportPrdConstants.QTBZ); // 6.4
				// 其他币种
		ChbMonthReportDetail line7Sub1 = result.get(ChbMonthReportPrdConstants.BBLCCP); // 7.1
				// 保本理财产品（不含结构性存款）
		ChbMonthReportDetail line7Sub2 = result.get(ChbMonthReportPrdConstants.JRTYZSLCCP); // 7.2
					// 金融同业专属理财产品
		
		ChbMonthReportDetail lineSum1Sub3 = result.get(ChbMonthReportPrdConstants.SUM_MJFS); // 合计:1.按募集方式划分
		ChbMonthReportDetail lineSum2Sub5 = result.get(ChbMonthReportPrdConstants.SUM_TZXZ); // 合计:2.按投资性质划分
		ChbMonthReportDetail lineSum3Sub3 = result.get(ChbMonthReportPrdConstants.SUM_YZMS); // 合计:3.按产品运作模式划分
		ChbMonthReportDetail lineSum4Sub1Sub6 = result.get(ChbMonthReportPrdConstants.SUM_FBSCP); // 合计:4.1
							// 封闭式产品
		ChbMonthReportDetail lineSum4Sub2Sub9 = result.get(ChbMonthReportPrdConstants.SUM_KFSCP); // 合计:4.2
							// 开放式产品
		ChbMonthReportDetail lineSum4Sub3 = result.get(ChbMonthReportPrdConstants.SUM_QX); // 合计:4.按期限划分
		ChbMonthReportDetail lineSum5Sub3 = result.get(ChbMonthReportPrdConstants.SUM_TZZCYQK); // 合计:5.投资者持有情况
		ChbMonthReportDetail lineSum6Sub5 = result.get(ChbMonthReportPrdConstants.SUM_BZ); // 合计:6.按产品币种划分
		
		
		// 合计 1.按募集方式划分
		lineSum1Sub3.setProdcounts(line1Sub1.getProdcounts() + line1Sub2.getProdcounts());
		lineSum1Sub3.setProdamount(line1Sub1.getProdamount().add(line1Sub2.getProdamount()));
		lineSum1Sub3.setProdnetamount(line1Sub1.getProdnetamount().add(line1Sub2.getProdnetamount()));
		lineSum1Sub3.setDurationprodcounts(line1Sub1.getDurationprodcounts() + line1Sub2.getDurationprodcounts());
		lineSum1Sub3.setBalance(line1Sub1.getBalance().add(line1Sub2.getBalance()));
		lineSum1Sub3.setCustomerprofit(line1Sub1.getCustomerprofit().add(line1Sub2.getCustomerprofit()));
		lineSum1Sub3.setBankprofit(line1Sub1.getBankprofit().add(line1Sub2.getBankprofit()));
		/**add  by  dongxiangyang  新增合计  start*/
		lineSum1Sub3.setXgdurationprodcounts(line1Sub1.getXgdurationprodcounts()+(line1Sub2.getXgdurationprodcounts()));
		lineSum1Sub3.setProdpayamount(line1Sub1.getProdpayamount().add(line1Sub2.getProdpayamount()));
		lineSum1Sub3.setXgbalance(line1Sub1.getXgbalance().add(line1Sub2.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/

		// 合计:2.按投资性质划分
		lineSum2Sub5.setProdcounts(line2Sub1.getProdcounts() + line2Sub2.getProdcounts() + line2Sub3.getProdcounts()
				+ line2Sub4.getProdcounts());
		lineSum2Sub5.setProdamount(line2Sub1.getProdamount().add(line2Sub2.getProdamount())
				.add(line2Sub3.getProdamount()).add(line2Sub4.getProdamount()));
		lineSum2Sub5.setProdnetamount(line2Sub1.getProdnetamount().add(line2Sub2.getProdnetamount())
				.add(line2Sub3.getProdnetamount()).add(line2Sub4.getProdnetamount()));
		lineSum2Sub5.setDurationprodcounts(line2Sub1.getDurationprodcounts() + line2Sub2.getDurationprodcounts()
				+ line2Sub3.getDurationprodcounts() + line2Sub4.getDurationprodcounts());
		lineSum2Sub5.setBalance(line2Sub1.getBalance().add(line2Sub2.getBalance()).add(line2Sub3.getBalance())
				.add(line2Sub4.getBalance()));
		lineSum2Sub5.setCustomerprofit(line2Sub1.getCustomerprofit().add(line2Sub2.getCustomerprofit())
				.add(line2Sub3.getCustomerprofit()).add(line2Sub4.getCustomerprofit()));
		lineSum2Sub5.setBankprofit(line2Sub1.getBankprofit().add(line2Sub2.getBankprofit())
				.add(line2Sub3.getBankprofit()).add(line2Sub4.getBankprofit()));
		/**add  by  dongxiangyang  新增合计  start*/
		lineSum2Sub5.setXgdurationprodcounts(line2Sub1.getXgdurationprodcounts()+line2Sub2.getXgdurationprodcounts()
				+line2Sub3.getXgdurationprodcounts()+line2Sub4.getXgdurationprodcounts());
		lineSum2Sub5.setProdpayamount(line2Sub1.getProdpayamount().add(line2Sub2.getProdpayamount())
				.add(line2Sub3.getProdpayamount()).add(line2Sub4.getProdpayamount()));
		lineSum2Sub5.setXgbalance(line2Sub1.getXgbalance().add(line2Sub2.getXgbalance())
				.add(line2Sub3.getXgbalance()).add(line2Sub4.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/
		// 合计:3.按产品运作模式划分
		lineSum3Sub3.setProdcounts(line3Sub1Sub1.getProdcounts() + line3Sub1Sub2.getProdcounts()
				+ line3Sub2Sub1.getProdcounts() + line3Sub2Sub2.getProdcounts());
		lineSum3Sub3.setProdamount(line3Sub1Sub1.getProdamount().add(line3Sub1Sub2.getProdamount())
				.add(line3Sub2Sub1.getProdamount()).add(line3Sub2Sub2.getProdamount()));
		lineSum3Sub3.setProdnetamount(line3Sub1Sub1.getProdnetamount().add(line3Sub1Sub2.getProdnetamount())
				.add(line3Sub2Sub1.getProdnetamount()).add(line3Sub2Sub2.getProdnetamount()));
		lineSum3Sub3.setDurationprodcounts(line3Sub1Sub1.getDurationprodcounts() + line3Sub1Sub2.getDurationprodcounts()
				+ line3Sub2Sub1.getDurationprodcounts() + line3Sub2Sub2.getDurationprodcounts());
		lineSum3Sub3.setBalance(line3Sub1Sub1.getBalance().add(line3Sub1Sub2.getBalance())
				.add(line3Sub2Sub1.getBalance()).add(line3Sub2Sub2.getBalance()));
		/**add  by  dongxiangyang  新增合计  start*/
		lineSum3Sub3.setXgdurationprodcounts(line3Sub1Sub1.getXgdurationprodcounts()+line3Sub1Sub2.getXgdurationprodcounts()
				+line3Sub2Sub1.getXgdurationprodcounts()+line3Sub2Sub2.getXgdurationprodcounts());
		lineSum3Sub3.setProdpayamount(line3Sub1Sub1.getProdpayamount().add(line3Sub1Sub2.getProdpayamount())
		       .add(line3Sub2Sub1.getProdpayamount()).add(line3Sub2Sub2.getProdpayamount()));
		lineSum3Sub3.setXgbalance(line3Sub1Sub1.getXgbalance().add(line3Sub1Sub2.getXgbalance())
				.add(line3Sub2Sub1.getXgbalance()).add(line3Sub2Sub2.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/

		// lineSum3Sub3.setCustomerprofit(line3Sub1Sub1.getCustomerprofit().add(line3Sub1Sub2.getCustomerprofit()).add(line3Sub2Sub1.getCustomerprofit()).add(line3Sub2Sub2.getCustomerprofit()));
		// lineSum3Sub3.setBankprofit(line3Sub1Sub1.getBankprofit().add(line3Sub1Sub2.getBankprofit()).add(line3Sub2Sub1.getBankprofit()).add(line3Sub2Sub2.getBankprofit()));

		// 合计:4.1 封闭式产品
		lineSum4Sub1Sub6.setProdcounts(line4Sub1Sub1.getProdcounts() + line4Sub1Sub2.getProdcounts()
				+ line4Sub1Sub3.getProdcounts() + line4Sub1Sub4.getProdcounts() + line4Sub1Sub5.getProdcounts());
		lineSum4Sub1Sub6.setProdamount(
				line4Sub1Sub1.getProdamount().add(line4Sub1Sub2.getProdamount()).add(line4Sub1Sub3.getProdamount())
						.add(line4Sub1Sub4.getProdamount()).add(line4Sub1Sub5.getProdamount()));
		lineSum4Sub1Sub6.setProdnetamount(line4Sub1Sub1.getProdnetamount().add(line4Sub1Sub2.getProdnetamount())
				.add(line4Sub1Sub3.getProdnetamount()).add(line4Sub1Sub4.getProdnetamount())
				.add(line4Sub1Sub5.getProdnetamount()));
		lineSum4Sub1Sub6.setDurationprodcounts(line4Sub1Sub1.getDurationprodcounts()
				+ line4Sub1Sub2.getDurationprodcounts() + line4Sub1Sub3.getDurationprodcounts()
				+ line4Sub1Sub4.getDurationprodcounts() + line4Sub1Sub5.getDurationprodcounts());
		lineSum4Sub1Sub6.setBalance(line4Sub1Sub1.getBalance().add(line4Sub1Sub2.getBalance())
				.add(line4Sub1Sub3.getBalance()).add(line4Sub1Sub4.getBalance()).add(line4Sub1Sub5.getBalance()));
		/**add  by  dongxiangyang  新增合计  start*/
		lineSum4Sub1Sub6.setXgdurationprodcounts(line4Sub1Sub1.getXgdurationprodcounts()+line4Sub1Sub2.getXgdurationprodcounts()
				+line4Sub1Sub3.getXgdurationprodcounts()+line4Sub1Sub4.getXgdurationprodcounts()+line4Sub1Sub5.getXgdurationprodcounts());
		lineSum4Sub1Sub6.setProdpayamount(line4Sub1Sub1.getProdpayamount().add(line4Sub1Sub2.getProdpayamount())
				.add(line4Sub1Sub3.getProdpayamount()).add(line4Sub1Sub4.getProdpayamount()).add(line4Sub1Sub5.getProdpayamount()));
		lineSum4Sub1Sub6.setXgbalance(line4Sub1Sub1.getXgbalance().add(line4Sub1Sub2.getXgbalance())
				.add(line4Sub1Sub3.getXgbalance()).add(line4Sub1Sub4.getXgbalance()).add(line4Sub1Sub5.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/


		// lineSum4Sub1Sub6.setCustomerprofit(line4Sub1Sub1.getCustomerprofit().add(line4Sub1Sub2.getCustomerprofit()).add(line4Sub1Sub3.getCustomerprofit()).add(line4Sub1Sub4.getCustomerprofit()).add(line4Sub1Sub5.getCustomerprofit()));
		// lineSum4Sub1Sub6.setBankprofit(line4Sub1Sub1.getBankprofit().add(line4Sub1Sub2.getBankprofit()).add(line4Sub1Sub3.getBankprofit()).add(line4Sub1Sub4.getBankprofit()).add(line4Sub1Sub5.getBankprofit()));

		// 合计:4.2 开放式产品
		lineSum4Sub2Sub9.setProdcounts(line4Sub2Sub1.getProdcounts() + line4Sub2Sub2.getProdcounts()
				+ line4Sub2Sub3.getProdcounts() + line4Sub2Sub4.getProdcounts() + line4Sub2Sub5.getProdcounts()
				+ line4Sub2Sub6.getProdcounts() + line4Sub2Sub7.getProdcounts() + line4Sub2Sub8.getProdcounts());
		lineSum4Sub2Sub9.setProdamount(line4Sub2Sub1.getProdamount().add(line4Sub2Sub2.getProdamount())
				.add(line4Sub2Sub3.getProdamount()).add(line4Sub2Sub4.getProdamount())
				.add(line4Sub2Sub5.getProdamount()).add(line4Sub2Sub6.getProdamount())
				.add(line4Sub2Sub7.getProdamount()).add(line4Sub2Sub8.getProdamount()));
		lineSum4Sub2Sub9.setProdnetamount(line4Sub2Sub1.getProdnetamount().add(line4Sub2Sub2.getProdnetamount())
				.add(line4Sub2Sub3.getProdnetamount()).add(line4Sub2Sub4.getProdnetamount())
				.add(line4Sub2Sub5.getProdnetamount()).add(line4Sub2Sub6.getProdnetamount())
				.add(line4Sub2Sub7.getProdnetamount()).add(line4Sub2Sub8.getProdnetamount()));
		lineSum4Sub2Sub9
				.setDurationprodcounts(line4Sub2Sub1.getDurationprodcounts() + line4Sub2Sub2.getDurationprodcounts()
						+ line4Sub2Sub3.getDurationprodcounts() + line4Sub2Sub4.getDurationprodcounts()
						+ line4Sub2Sub5.getDurationprodcounts() + line4Sub2Sub6.getDurationprodcounts()
						+ line4Sub2Sub7.getDurationprodcounts() + line4Sub2Sub8.getDurationprodcounts());
		lineSum4Sub2Sub9.setBalance(line4Sub2Sub1.getBalance().add(line4Sub2Sub2.getBalance())
				.add(line4Sub2Sub3.getBalance()).add(line4Sub2Sub4.getBalance()).add(line4Sub2Sub5.getBalance())
				.add(line4Sub2Sub6.getBalance()).add(line4Sub2Sub7.getBalance()).add(line4Sub2Sub8.getBalance()));

		/**add  by  dongxiangyang  新增合计  start*/
		lineSum4Sub2Sub9.setXgdurationprodcounts(line4Sub2Sub1.getXgdurationprodcounts()+line4Sub2Sub2.getXgdurationprodcounts()
				+line4Sub2Sub3.getXgdurationprodcounts()+line4Sub2Sub4.getXgdurationprodcounts()+line4Sub2Sub5.getXgdurationprodcounts()
		        +line4Sub2Sub6.getXgdurationprodcounts()+line4Sub2Sub7.getXgdurationprodcounts()+line4Sub2Sub8.getXgdurationprodcounts());
		lineSum4Sub2Sub9.setProdpayamount(line4Sub2Sub1.getProdpayamount().add(line4Sub2Sub2.getProdpayamount())
				.add(line4Sub2Sub3.getProdpayamount()).add(line4Sub2Sub4.getProdpayamount()).add(line4Sub2Sub5.getProdpayamount())
		        .add(line4Sub2Sub6.getProdpayamount()).add(line4Sub2Sub7.getProdpayamount()).add(line4Sub2Sub8.getProdpayamount()));
		lineSum4Sub2Sub9.setXgbalance(line4Sub2Sub1.getXgbalance().add(line4Sub2Sub2.getXgbalance())
				.add(line4Sub2Sub3.getXgbalance()).add(line4Sub2Sub4.getXgbalance()).add(line4Sub2Sub5.getXgbalance())
		        .add(line4Sub2Sub6.getXgbalance()).add(line4Sub2Sub7.getXgbalance()).add(line4Sub2Sub8.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/
		// lineSum4Sub2Sub9.setCustomerprofit(line4Sub2Sub1.getCustomerprofit().add(line4Sub2Sub2.getCustomerprofit()).add(line4Sub2Sub3.getCustomerprofit()).add(line4Sub2Sub4.getCustomerprofit())
		// .add(line4Sub2Sub5.getCustomerprofit()).add(line4Sub2Sub6.getCustomerprofit()).add(line4Sub2Sub7.getCustomerprofit()).add(line4Sub2Sub8.getCustomerprofit())
		// );
		// lineSum4Sub2Sub9.setBankprofit(line4Sub2Sub1.getBankprofit().add(line4Sub2Sub2.getBankprofit()).add(line4Sub2Sub3.getBankprofit()).add(line4Sub2Sub4.getBankprofit())
		// .add(line4Sub2Sub5.getBankprofit()).add(line4Sub2Sub6.getBankprofit()).add(line4Sub2Sub7.getBankprofit()).add(line4Sub2Sub8.getBankprofit())
		// );

		// 合计:4.按期限划分
		lineSum4Sub3.setProdcounts(lineSum4Sub1Sub6.getProdcounts() + lineSum4Sub2Sub9.getProdcounts());
		lineSum4Sub3.setProdamount(lineSum4Sub1Sub6.getProdamount().add(lineSum4Sub2Sub9.getProdamount()));
		lineSum4Sub3.setProdnetamount(lineSum4Sub1Sub6.getProdnetamount().add(lineSum4Sub2Sub9.getProdnetamount()));
		lineSum4Sub3.setDurationprodcounts(
				lineSum4Sub1Sub6.getDurationprodcounts() + lineSum4Sub2Sub9.getDurationprodcounts());
		lineSum4Sub3.setBalance(lineSum4Sub1Sub6.getBalance().add(lineSum4Sub2Sub9.getBalance()));
		/**add  by  dongxiangyang  新增合计  start*/
		lineSum4Sub3.setXgdurationprodcounts(lineSum4Sub1Sub6.getXgdurationprodcounts()+(lineSum4Sub2Sub9.getXgdurationprodcounts()));
		lineSum4Sub3.setProdpayamount(lineSum4Sub1Sub6.getProdpayamount().add(lineSum4Sub2Sub9.getProdpayamount()));
		lineSum4Sub3.setXgbalance(lineSum4Sub1Sub6.getXgbalance().add(lineSum4Sub2Sub9.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/
		// lineSum4Sub3.setCustomerprofit(lineSum4Sub1Sub6.getCustomerprofit().add(lineSum4Sub2Sub9.getCustomerprofit()));
		// lineSum4Sub3.setBankprofit(lineSum4Sub1Sub6.getBankprofit().add(lineSum4Sub2Sub9.getBankprofit()));

		// 合计:5.投资者持有情况
		lineSum5Sub3.setProdcounts(line5Sub1.getProdcounts() + line5Sub2.getProdcounts());
		lineSum5Sub3.setProdamount(line5Sub1.getProdamount().add(line5Sub2.getProdamount()));
		lineSum5Sub3.setProdnetamount(line5Sub1.getProdnetamount().add(line5Sub2.getProdnetamount()));
		lineSum5Sub3.setDurationprodcounts(line5Sub1.getDurationprodcounts() + line5Sub2.getDurationprodcounts());
		lineSum5Sub3.setBalance(line5Sub1.getBalance().add(line5Sub2.getBalance()));
		/**add  by  dongxiangyang  新增合计  start*/
		lineSum5Sub3.setXgdurationprodcounts(line5Sub1.getXgdurationprodcounts()+(line5Sub2.getXgdurationprodcounts()));
		lineSum5Sub3.setProdpayamount(line5Sub1.getProdpayamount().add(line5Sub2.getProdpayamount()));
		lineSum5Sub3.setXgbalance(line5Sub1.getXgbalance().add(line5Sub2.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/
		// lineSum5Sub3.setCustomerprofit(line5Sub1.getCustomerprofit().add(line5Sub2.getCustomerprofit()));
		// lineSum5Sub3.setBankprofit(line5Sub1.getBankprofit().add(line5Sub2.getBankprofit()));

		// 合计:6.按产品币种划分
		lineSum6Sub5.setProdcounts(line6Sub1.getProdcounts() + line6Sub2.getProdcounts() + line6Sub3.getProdcounts()
				+ line6Sub4.getProdcounts());
		lineSum6Sub5.setProdamount(line6Sub1.getProdamount().add(line6Sub2.getProdamount())
				.add(line6Sub3.getProdamount()).add(line6Sub4.getProdamount()));
		lineSum6Sub5.setProdnetamount(line6Sub1.getProdnetamount().add(line6Sub2.getProdnetamount())
				.add(line6Sub3.getProdnetamount()).add(line6Sub4.getProdnetamount()));
		lineSum6Sub5.setDurationprodcounts(line6Sub1.getDurationprodcounts() + line6Sub2.getDurationprodcounts()
				+ line6Sub3.getDurationprodcounts() + line6Sub4.getDurationprodcounts());
		lineSum6Sub5.setBalance(line6Sub1.getBalance().add(line6Sub2.getBalance()).add(line6Sub3.getBalance())
				.add(line6Sub4.getBalance()));
		/**add  by  dongxiangyang  新增合计  start*/
		lineSum6Sub5.setXgdurationprodcounts(line6Sub1.getXgdurationprodcounts()+line6Sub2.getXgdurationprodcounts()
				+line6Sub3.getXgdurationprodcounts()+line6Sub4.getXgdurationprodcounts());
		lineSum6Sub5.setProdpayamount(line6Sub1.getProdpayamount().add(line6Sub2.getProdpayamount())
				.add(line6Sub3.getProdpayamount()).add(line6Sub4.getProdpayamount()));
		lineSum6Sub5.setXgbalance(line6Sub1.getXgbalance().add(line6Sub2.getXgbalance())
				.add(line6Sub3.getXgbalance()).add(line6Sub4.getXgbalance()));
		/**add  by  dongxiangyang  新增合计  end*/
		// lineSum6Sub5.setCustomerprofit(line6Sub1.getCustomerprofit().add(line6Sub2.getCustomerprofit()).add(line6Sub3.getCustomerprofit()).add(line6Sub4.getCustomerprofit()));
		// lineSum6Sub5.setBankprofit(line6Sub1.getBankprofit().add(line6Sub2.getBankprofit()).add(line6Sub3.getBankprofit()).add(line6Sub4.getBankprofit()));

		
		
	}
	
}
