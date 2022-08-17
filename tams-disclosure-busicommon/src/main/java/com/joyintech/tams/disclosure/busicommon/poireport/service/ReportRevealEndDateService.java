/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.poireport.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.dao.PoiReportMapper;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.HolidayService;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;

/**
 * @author chenxc
 *
 */
@Service
public class ReportRevealEndDateService {

	private static final String 平台日 = "01";
	private static final String 募集结束日 = "03";
	private static final String 开放周期结束日 = "04";
	private static final String 开放周期开始日 = "08";
	private static final String 运作起始日 = "09";
	private static final String 巨额赎回调整日 = "12";
	private static final String 募集上线调整日 = "13";
	private static final String 当期收益分配基准日 = "14";
	private static final String 自动赎回日 = "15";
	private static final String 到期日 = "MDATE";
	private static final String 募集起始日 = "RAISE_STRDATE";
	private static final String 成立日 = "SET_DATE";
	private static final String 起息日 = "VDATE";
	private static final String 清盘日 = "WINDUP_DATE";
	private static final String 周期结束日 = "DUR_ENDDATE";

	@Autowired
	private HolidayService holidayService;

	@Autowired
	private RepProdInfoMapper repProdInfoMapper;

	@Autowired
	private CommonPlatformDateService commonPlatformDateService;

	@Autowired
	private PoiReportMapper poiReportMapper;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, Date> getBaseDate(RepReveal repReveal, String busiId,ReportInfo reportInfo) {
		Map<String, Date> map = new HashMap<>();
		// 获取产品信息
		RepProdInfo prodInfo = repProdInfoMapper.selectByPrimaryKey(busiId);
		map.put(到期日, prodInfo.getMdate());
		map.put(成立日, prodInfo.getSetDate());
		map.put(募集起始日, prodInfo.getRaiseStrdate());
		map.put(募集结束日, prodInfo.getRaiseEnddate());
		map.put(起息日, prodInfo.getVdate());
		map.put(清盘日, prodInfo.getWindupDate());
		map.put(平台日,commonPlatformDateService.getSysDate());

		String rptType = reportInfo.getUri();
		if (rptType.equals(DisReportType.FHJSQD)) { // 分红计算清单，当期收益分配基准日
			Map<String,Object> paraMap = new HashMap<>();
			paraMap.put("prodId", busiId);
			map.put("当期收益分配基准日", repReveal.getEndDate());
		} else if (rptType.equals(DisReportType.DQGG_GM) || rptType.equals(DisReportType.DQGBG_SM)) { // 到期公告
			map.put("当期收益分配基准日", repReveal.getEndDate());
		} else if (rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)
				|| rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX)
				|| rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX)) { // 定期公告-季度/半年度/年度报告指数型和非指数型
			map.put(周期结束日, repReveal.getEndDate());
		}else if (rptType.equals(DisReportType.CPKFQGG)||rptType.equals(DisReportType.CPKFQBG)) { // 产品开发期报告/公告
			map.put("enddate", repReveal.getEndDate());
		}else if (rptType.equals(DisReportType.RKCPJZ_GMXGL)||rptType.equals(DisReportType.RKCPJZ_GMJZXCP)) { // 日开产品
			map.put("", null);
		}else if (rptType.equals(DisReportType.ZQXCPKFGG_GMCP)) { // 周期型产品开放公告（公募产品）、周期型产品开放公告（公募产品-单赎回）
			map.put(运作起始日, repReveal.getEndDate());
		} else if (rptType.equals(DisReportType.ZQXCPKFGG_GMCPDSH)) {
			map.put(自动赎回日, repReveal.getEndDate());
		} else if (rptType.equals(DisReportType.KFJZJFHGG_BNYS) || rptType.equals(DisReportType.KFJZJFHGG_BNYN)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			map.put(当期收益分配基准日, repReveal.getEndDate());
		} else if (rptType.equals(DisReportType.KFJJZGG_GM) || rptType.equals(DisReportType.KFJJZGG_SM)) { // 开放、净值及分红公告（公募&开放频率在半年以上）、开放、净值及分红公告（公募&开放频率在半年（不含）以内）
			map.put(当期收益分配基准日, repReveal.getEndDate());
		} else if (rptType.equals(DisReportType.MZJZGG) || rptType.equals(DisReportType.MZJJBG)) { // 每周净值公告（公募）/（私募）
			map.put(周期结束日, repReveal.getEndDate());
		} else if (rptType.equals(DisReportType.JZJFHBG_SMFB) || rptType.equals(DisReportType.JZJFHBG_SMKF)) { // 净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）
			map.put(当期收益分配基准日, repReveal.getEndDate());
		}

		return map;
	}

	public Date getDisEndDate(RepReveal repReveal, String busiId,ReportInfo reportInfo) {

		Map<String, Date> mapDates = getBaseDate(repReveal,busiId,reportInfo);
		// 获取规则信息
		List<RepRuleEntity> listRepRule = poiReportMapper.getRuleInfo(repReveal.getRepId());

		int days = 0;
		RepRuleEntity repRule = null;
		if (listRepRule != null && listRepRule.size() > 0) {
			repRule = listRepRule.get(0);
		}
		//防止有一些报表没有设置规则（只手工生成） 默认取map.get("endDate");
		if(repRule == null){
			return mapDates.get("enddate");
		}
		days = Integer.parseInt(repRule.getRevealValue());

		Date baseDate = null;

		Date disEndDate = baseDate;
		try {
			baseDate = mapDates.get(repRule.getBasisDateType());
		} catch (Exception e) {
			logger.error("获取基准日错误。", e);
		}
		if ( baseDate==null )
			baseDate = commonPlatformDateService.getSysDate();

		// 披露截止日期
		if (StringUtil.equals(DictCodeListExt1.日期类型_信批_自然日.getValue(), repRule.getRevealDateType())) {
			if (StringUtil.equals(DictCodeListExt2.时点类型_基准日前.getValue(), repRule.getRevealType())) {
				disEndDate = DateUtil.addDay(baseDate, 0 - days);
			} else {
				disEndDate = DateUtil.addDay(baseDate, days);
			}
		} else {
			Date iDate = baseDate;
			for (int i = 0; i < days; i++) {
				if (StringUtil.equals(DictCodeListExt2.时点类型_基准日前.getValue(), repRule.getRevealType())) {
					iDate = holidayService.getPreWorkDate(iDate);
				} else {
					iDate = holidayService.getNextWorkDate(iDate);
				}
			}
			disEndDate = iDate;
		}
		return disEndDate;
	}

}
