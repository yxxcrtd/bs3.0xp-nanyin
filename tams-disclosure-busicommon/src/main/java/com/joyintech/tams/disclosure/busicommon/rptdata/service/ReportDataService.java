package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.report.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.utils.echarts.EchartsException;
import com.joyintech.tams.utils.echarts.EchartsUtils;

/**
 * 报表数据获取服务总分发入口服务
 * 
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @see
 * @since 1.0
 */
@Service
public class ReportDataService {

	@Autowired
	private BonusReportDataService bonusReportDataService;

	@Autowired
	private SettReportDataService settReportDataService;

	@Autowired
	private MatureReportDataService matureReportDataService;

	@Autowired
	private NostdReportDataService nostdReportDataService;

	@Autowired
	private DurationReportDataService durationReportDataService;

	@Autowired
	private ReportFxgg001Service reportFxgg001Service;

	@Autowired
	private ReportZjxsjg001Service reportZjxsjg001Service;

	@Autowired
	private ReportBclgg001Service reportBclgg001Service;

	@Autowired
	private Report1Cpnfl001Service report1Cpnfl001Service;

	@Autowired
	private Report1Cpnfl002Service report1Cpnfl002Service;

	@Autowired
	private Report1Flncp001Service report1Flncp001Service;

	@Autowired
	private Report1Flncp002Service report1Flncp002Service;

	@Autowired
	private Report1Cpnys001Service report1Cpnys001Service;

	@Autowired
	private Report1Ysncp001Service report1Ysncp001Service;

	@Autowired
	private ReportJesh001Service reportJesh001Service;

	@Autowired
	private ReportDlxsjgmdgg001Service reportDlxsjgmdgg001Service;

	@Autowired
	private ReportKfqgg001Service reportKfqgg001Service;

	@Autowired
	private ReportRkcpjz001Service reportRkcpjz001Service;

	@Autowired
	private ReportZqxkfgg001Service reportZqxkfgg001Service;

	@Autowired
	private ReportKfqzjfhgg001Service reportKfqzjfhgg001Service;

	@Autowired
	private ReportKfjzgg001Service reportKfjzgg001Service;

	@Autowired
	private ReportMzjz001Service reportMzjz001Service;

	@Autowired
	private ReportJzfhbg001Service reportJzfhbg001Service;

	public List<Map<String, Object>> queryTestDatas(Map<String, Object> params) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("${prodName}", "得利宝8号");
		dataMap.put("${prodId}", "20170001");
		dataMap.put("${yield}", 0.03);
		List<Map<String, String>> mylist = new ArrayList<Map<String, String>>();
		List<Map<String, String>> ivstOrientation = new ArrayList<Map<String, String>>();
		List<Map<String, String>> feelist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 5; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("aa", "债券" + i);
			map.put("bb", i * 100 + "");
			map.put("cc", 0 - i * 100 + "");
			map.put("dd", "测试" + i);
			mylist.add(map);
		}
		dataMap.put("mylist", mylist);
		dataMap.put("ivstOrientation", ivstOrientation);
		dataMap.put("feelist", feelist);

		// 生成统计图
		Map<String, Object> configMap = new HashMap<>();

		configMap.put("series.type", "bar");
		// 标题
		configMap.put("title.text", "收益");
		configMap.put("title.subtext", "产品A收益");
		configMap.put("title.left", "center");
		configMap.put("title.top", "");
		configMap.put("title.textStyle.fontSize", "14");
		configMap.put("title.textStyle.fontWeight", "normal");
		configMap.put("title.textStyle.color", "#595959");
		configMap.put("legend.left", "center");
		configMap.put("legend.top", "auto");
		configMap.put("legend.bottom", "1%");
		configMap.put("xAxis.name", "日期");
		configMap.put("xAxis.type", "category");
		configMap.put("xAxis.boundaryGap", "");
		configMap.put("xAxis.axisLabel.rotate", "");
		configMap.put("yAxis.name", "值");
		configMap.put("yAxis.type", "value");
		configMap.put("yAxis.position", "left");

		List<List<String>> dataList = new ArrayList<>();

		// 图例赋值
		List<String> single = new ArrayList<>();
		single.add("日期");
		single.add("七日年化收益率");
		single.add("业绩比较基准");
		dataList.add(single);

		List<String> row1 = new ArrayList<>();
		row1.add("2019-04-01");
		row1.add("3.9634");
		row1.add("3.6");
		dataList.add(row1);

		List<String> row2 = new ArrayList<>();
		row2.add("2019-04-02");
		row2.add("3.9671");
		row2.add("3.6");
		dataList.add(row2);

		List<String> row3 = new ArrayList<>();
		row3.add("2019-04-03");
		row3.add("3.9675");
		row3.add("3.6");
		dataList.add(row3);

		List<String> row4 = new ArrayList<>();
		row4.add("2019-04-04");
		row4.add("3.8501");
		row4.add("3.6");
		dataList.add(row4);

		List<String> row5 = new ArrayList<>();
		row5.add("2019-04-05");
		row5.add("3.819");
		row5.add("3.6");
		dataList.add(row5);

		List<String> row6 = new ArrayList<>();
		row6.add("2019-04-06");
		row6.add("3.8134");
		row6.add("3.6");
		dataList.add(row6);

		// 创建图片
		String echartsCmdPath = "D:\\devtools\\phantomjs-2.1.1-windows\\bin\\"; // 从系统参数里面获取
		String echartsFilePathName = "e:\\poitest\\temp\\prodYieldLine";

		try {
			EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, echartsFilePathName,
					EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);
		} catch (EchartsException e) {
			e.printStackTrace();
		}

		Map<String, Object> prodYieldChart = new HashMap<String, Object>();
		prodYieldChart.put("width", 350);
		prodYieldChart.put("height", 150);
		prodYieldChart.put("type", "png");
		prodYieldChart.put("content", echartsFilePathName + ".png");
		dataMap.put("${@image:prod_yield_line}", prodYieldChart);

		List<Map<String, Object>> listTest = new ArrayList<>();
		listTest.add(dataMap);
		return listTest;
	}

	public List<Map<String, Object>> queryDatas(Map<String, Object> params) {

		String rptType = "";
		IReportDataService service = null;

		if (params.get("uri") != null) {
			rptType = params.get("uri").toString();
		} else {
			return new ArrayList<>();
		}

//		Date td = DateUtil.parse("2022-02-10");
//		params.put("genDate",td);
//		params.put("prodId","LEO-001");

		if (rptType.equals(DisReportType.TEST)) {
			return queryTestDatas(params);
		}

		// 分红计算清单
		if (rptType.equals(DisReportType.FHJSQD)) {
			service = bonusReportDataService;
		} else if (rptType.equals(DisReportType.QSBG)) { // 清算报告
			service = settReportDataService;
		} else if (rptType.equals(DisReportType.DQGG_GM) || rptType.equals(DisReportType.DQGBG_SM)) { // 到期公告
			service = matureReportDataService;
		} else if (rptType.equals(DisReportType.TZTZFBZCGG)) { // 非标调整公告
			service = nostdReportDataService;
		} else if (rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)
				|| rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX)
				|| rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX)
		) { // 定期公告-季度/半年度/年度报告指数型和非指数型
			service = durationReportDataService;
		} else if (rptType.equals(DisReportType.FXGG)) { // 发行公告
			service = reportFxgg001Service;
		} else if (rptType.equals(DisReportType.CPBCLGG_GM) || rptType.equals(DisReportType.CPBCLBG_SM)) { // 产品不成立公告、报告
			service = reportBclgg001Service;
		} else if (rptType.equals(DisReportType.ZJCPXSJGGG)) { // 增加产品销售机构公告
			service = reportZjxsjg001Service;
		} else if (rptType.equals(DisReportType.DCPDFLMB1_GM) || rptType.equals(DisReportType.DCPDFLMB1_SM)) { // 单产品多费率模板1
			service = report1Cpnfl001Service;
		} else if (rptType.equals(DisReportType.DCPDFLMB2_GM) || rptType.equals(DisReportType.DCPDFLMB2_SM)) { // 单产品多费率模板2
			service = report1Cpnfl002Service;
		} else if (rptType.equals(DisReportType.DFLDCPMB1_GM) || rptType.equals(DisReportType.DFLDCPMB1_SM)) { // 单费率多产品模板1
			service = report1Flncp001Service;
		} else if (rptType.equals(DisReportType.DFLDCPMB2_GM) || rptType.equals(DisReportType.DFLDCPMB2_SM)) { // 单费率多产品模板2
			service = report1Flncp002Service;
		} else if (rptType.equals(DisReportType.DCPDYSTZMB_GM) || rptType.equals(DisReportType.DCPDYSTZMB_SM)) { // 单产品多要素调整模板
			service = report1Cpnys001Service;
		} else if (rptType.equals(DisReportType.DYSDCPTZMB_GM) || rptType.equals(DisReportType.DYSDCPTZMB)) { // 单要素多产品调整模板
			service = report1Ysncp001Service;
		} else if (rptType.equals(DisReportType.JESHGG_GM) || rptType.equals(DisReportType.JESHGBG_SM)) { // 巨额赎回模板
			service = reportJesh001Service;
		} else if (rptType.equals(DisReportType.DLXSJGMDGG)) { // 代理销售机构名单公告
			service = reportDlxsjgmdgg001Service;
		} else if (rptType.equals(DisReportType.CPKFQGG) || rptType.equals(DisReportType.CPKFQBG)) { // 产品开放期公告（公募）、产品开放期报告（私募）
			service = reportKfqgg001Service;
		} else if (rptType.equals(DisReportType.RKCPJZ_GMXGL) || rptType.equals(DisReportType.RKCPJZ_GMJZXCP)) { // 日开产品净值公告（公募净值型产品）、日开产品净值公告（公募现管类产品）
			service = reportRkcpjz001Service;
		} else if (rptType.equals(DisReportType.ZQXCPKFGG_GMCP) || rptType.equals(DisReportType.ZQXCPKFGG_GMCPDSH)) { // 周期型产品开放公告（公募产品）、周期型产品开放公告（公募产品-单赎回）
			service = reportZqxkfgg001Service;
		} else if (rptType.equals(DisReportType.KFJZJFHGG_BNYS) || rptType.equals(DisReportType.KFJZJFHGG_BNYN)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			service = reportKfqzjfhgg001Service;
		} else if (rptType.equals(DisReportType.KFJJZGG_GM) || rptType.equals(DisReportType.KFJJZGG_SM)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			service = reportKfjzgg001Service;
		} else if (rptType.equals(DisReportType.MZJZGG) || rptType.equals(DisReportType.MZJJBG)) { // 每周净值公告（公募）/（私募）
			service = reportMzjz001Service;
		} else if (rptType.equals(DisReportType.JZJFHBG_SMFB) || rptType.equals(DisReportType.JZJFHBG_SMKF)) { // 净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）
			service = reportJzfhbg001Service;
		}

		return service.queryDatas(params);
	}

	public RepReveal getRepReveal(Map<String, Object> params) {
		RepReveal repReveal = new RepReveal();

		String rptType = "";
		if (params.get("uri") != null)
			rptType = params.get("uri").toString();

		//repReveal.setRepId(repId);
		//repReveal.setProdid(prodId);
		//repReveal.setStartDate();
		//repReveal.setEndDate(endDate);

		// 分红计算清单
		if (rptType.equals(DisReportType.FHJSQD)) {
			repReveal = bonusReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.QSBG)) { // 清算报告
			repReveal = settReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DQGG_GM) || rptType.equals(DisReportType.DQGBG_SM)) { // 到期公告
			repReveal = matureReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.TZTZFBZCGG)) { // 非标调整公告
			repReveal = nostdReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)
				|| rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX)
				|| rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX)
		) { // 定期公告-季度/半年度/年度报告指数型和非指数型
			repReveal = durationReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.FXGG)) { // 发行公告
			repReveal = reportFxgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.CPBCLGG_GM) || rptType.equals(DisReportType.CPBCLBG_SM)) { // 产品不成立公告、报告
			repReveal = reportBclgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.ZJCPXSJGGG)) { // 增加产品销售机构公告
			repReveal = reportZjxsjg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DCPDFLMB1_GM) || rptType.equals(DisReportType.DCPDFLMB1_SM)) { // 单产品多费率模板1
			repReveal = report1Cpnfl001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DCPDFLMB2_GM) || rptType.equals(DisReportType.DCPDFLMB2_SM)) { // 单产品多费率模板2
			repReveal = report1Cpnfl002Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DFLDCPMB1_GM) || rptType.equals(DisReportType.DFLDCPMB1_SM)) { // 单费率多产品模板1
			repReveal = report1Flncp001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DFLDCPMB2_GM) || rptType.equals(DisReportType.DFLDCPMB2_SM)) { // 单费率多产品模板2
			repReveal = report1Flncp002Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DCPDYSTZMB_GM) || rptType.equals(DisReportType.DCPDYSTZMB_SM)) { // 单产品多要素调整模板
			repReveal = report1Cpnys001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DYSDCPTZMB_GM) || rptType.equals(DisReportType.DYSDCPTZMB)) { // 单要素多产品调整模板
			repReveal = report1Ysncp001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.JESHGG_GM) || rptType.equals(DisReportType.JESHGBG_SM)) { // 巨额赎回模板
			repReveal = reportJesh001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DLXSJGMDGG)) { // 代理销售机构名单公告
			repReveal = reportDlxsjgmdgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.CPKFQGG) || rptType.equals(DisReportType.CPKFQBG)) { // 产品开放期公告（公募）、产品开放期报告（私募）
			repReveal = reportKfqgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.RKCPJZ_GMXGL) || rptType.equals(DisReportType.RKCPJZ_GMJZXCP)) { // 日开产品净值公告（公募净值型产品）、日开产品净值公告（公募现管类产品）
			repReveal = reportRkcpjz001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.ZQXCPKFGG_GMCP) || rptType.equals(DisReportType.ZQXCPKFGG_GMCPDSH)) { // 周期型产品开放公告（公募产品）、周期型产品开放公告（公募产品-单赎回）
			repReveal = reportZqxkfgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.KFJZJFHGG_BNYS) || rptType.equals(DisReportType.KFJZJFHGG_BNYN)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			repReveal = reportKfqzjfhgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.KFJJZGG_GM) || rptType.equals(DisReportType.KFJJZGG_SM)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			repReveal = reportKfjzgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.MZJZGG) || rptType.equals(DisReportType.MZJJBG)) { // 每周净值公告（公募）/（私募）
			repReveal = reportMzjz001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.JZJFHBG_SMFB) || rptType.equals(DisReportType.JZJFHBG_SMKF)) { // 净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）
			repReveal = reportJzfhbg001Service.getRepReveal(params);
		}

		String repId = (String) params.get("repid").toString();
		repReveal.setRepId(repId);

		if (!StringUtil.isNotEmpty(repReveal.getProdid())) {
			if (StringUtil.isNotEmpty((params.get("prodid")))) {
				repReveal.setProdid(params.get("prodid").toString());
			}
		}

		return repReveal;
	}

	/**
	 * 统一处理报表的时间：包括开始日期何结束日期
	 *
	 * @param params
	 */
	public void collateReportDateParams(Map<String, Object> params) {
		String repId = (String) params.get("repid");

		String rptType = "";

		if (params.get("uri") != null)
			rptType = params.get("uri").toString();

		RepReveal repReveal = new RepReveal();
		// 分红计算清单
		if (rptType.equals(DisReportType.FHJSQD)) {
			repReveal = bonusReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.QSBG)) { // 清算报告
			repReveal = settReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DQGG_GM) || rptType.equals(DisReportType.DQGBG_SM)) { // 到期公告
			repReveal = matureReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.TZTZFBZCGG)) { // 非标调整公告
			repReveal = nostdReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)
				|| rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX)
				|| rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX)
		) { // 定期公告-季度/半年度/年度报告指数型和非指数型
			repReveal = durationReportDataService.getRepReveal(params);
		} else if (rptType.equals(DisReportType.FXGG)) { // 发行公告
			repReveal = reportFxgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.CPBCLGG_GM) || rptType.equals(DisReportType.CPBCLBG_SM)) { // 产品不成立公告、报告
			repReveal = reportBclgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.ZJCPXSJGGG)) { // 增加产品销售机构公告
			repReveal = reportZjxsjg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DCPDFLMB1_GM) || rptType.equals(DisReportType.DCPDFLMB1_SM)) { // 单产品多费率模板1
			repReveal = report1Cpnfl001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DCPDFLMB2_GM) || rptType.equals(DisReportType.DCPDFLMB2_SM)) { // 单产品多费率模板2
			repReveal = report1Cpnfl002Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DFLDCPMB1_GM) || rptType.equals(DisReportType.DFLDCPMB1_SM)) { // 单费率多产品模板1
			repReveal = report1Flncp001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DFLDCPMB2_GM) || rptType.equals(DisReportType.DFLDCPMB2_SM)) { // 单费率多产品模板2
			repReveal = report1Flncp002Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DCPDYSTZMB_GM) || rptType.equals(DisReportType.DCPDYSTZMB_SM)) { // 单产品多要素调整模板
			repReveal = report1Cpnys001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DYSDCPTZMB_GM) || rptType.equals(DisReportType.DYSDCPTZMB)) { // 单要素多产品调整模板
			repReveal = report1Ysncp001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.JESHGG_GM) || rptType.equals(DisReportType.JESHGBG_SM)) { // 巨额赎回模板
			repReveal = reportJesh001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.DLXSJGMDGG)) { // 代理销售机构名单公告
			repReveal = reportDlxsjgmdgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.CPKFQGG) || rptType.equals(DisReportType.CPKFQBG)) { // 产品开放期公告（公募）、产品开放期报告（私募）
			repReveal = reportKfqgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.RKCPJZ_GMXGL) || rptType.equals(DisReportType.RKCPJZ_GMJZXCP)) { // 日开产品净值公告（公募净值型产品）、日开产品净值公告（公募现管类产品）
			repReveal = reportRkcpjz001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.ZQXCPKFGG_GMCP) || rptType.equals(DisReportType.ZQXCPKFGG_GMCPDSH)) { // 周期型产品开放公告（公募产品）、周期型产品开放公告（公募产品-单赎回）
			repReveal = reportZqxkfgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.KFJZJFHGG_BNYS) || rptType.equals(DisReportType.KFJZJFHGG_BNYN)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			repReveal = reportKfqzjfhgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.KFJJZGG_GM) || rptType.equals(DisReportType.KFJJZGG_SM)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			repReveal = reportKfjzgg001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.MZJZGG) || rptType.equals(DisReportType.MZJJBG)) { // 每周净值公告（公募）/（私募）
			repReveal = reportMzjz001Service.getRepReveal(params);
		} else if (rptType.equals(DisReportType.JZJFHBG_SMFB) || rptType.equals(DisReportType.JZJFHBG_SMKF)) { // 净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）
			repReveal = reportJzfhbg001Service.getRepReveal(params);
		}
		//params.put("startDate",repReveal.getStartDate());  //报表的开始日期
		params.put("reportDate", repReveal.getEndDate());  //报表的结束日期
	}
}

