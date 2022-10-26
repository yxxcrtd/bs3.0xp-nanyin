package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.DisBonusList;
import com.joyintech.tams.common.base.entity.RepFeeTrd;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportJzfhbg001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportRkcpjz001Mapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.BonusReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.MatureReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.BonusReportDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.MatureReportDto;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

/**
 * 到期公告-数据获取服务
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @see 
 * @since 1.0
 */
@Service
public class MatureReportDataService implements IReportDataService {

	@Autowired
	private MatureReportMapper matureReportMapper;
	
	@Autowired
	private BonusReportMapper bonusReportMapper;
	
	@Autowired
	private HolidayService holidayService;

	@Autowired
	private ReportRkcpjz001Mapper reportRkcpjz001Mapper;
	
    @Autowired
    private ReportJzfhbg001Mapper reportJzfhbg001Mapper;
    
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(MatureReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();

		MatureReportDto matureReportDto = new MatureReportDto();
		try {
//			String prodId = (paraMap.get("portnum") == null ? null : paraMap.get("portnum").toString());
//			Date genDate = (paraMap.get("endDate") == null ? new Date()
//					: DateUtil.parse(paraMap.get("endDate").toString()));
			//判断是普通产品还是子产品
			if (StringUtil.equals("batch", paraMap.get("opttype"))) {
				RepProdInfo info=reportRkcpjz001Mapper.getPProdInfo(paraMap);
				if(StringUtil.isNotEmpty(info)){
					paraMap.put("pprodId",info.getpProdId());
					paraMap.put("prodId",info.getProdId());
				}else {
					RepProdInfo info2=reportRkcpjz001Mapper.getPProdId(paraMap);
					paraMap.put("pprodid",info2.getpProdId());
					paraMap.put("prodId",info2.getProdId());
				}
			}

			List<BonusReportDto> listBonusReport = matureReportMapper.selectBonusPlan(paraMap);

			if ( listBonusReport!=null && listBonusReport.size()>0 ) {   //取最新的分红计划，做到期信息
				BonusReportDto dto = listBonusReport.get(0);
				matureReportDto.setReportTitle(dto.getReportTitle());
				matureReportDto.setProdName(dto.getProdName());
				matureReportDto.setProdId(dto.getProdId());
				matureReportDto.setProdRe(dto.getProdRe());
				matureReportDto.seteDate(dto.geteDate());
				
				if ( "02".equals(dto.getBonusMode()) ) {  //分红方式==到期兑付
					matureReportDto.setmDate( dto.getSettDate() );
				} else {
					matureReportDto.setmDate(dto.getSettDate());
				}
				
				//签字日期：收益分配基准日+1工作日
				matureReportDto.setGenDate(matureReportDto.getmDate());
				
	        	//期限天数
				matureReportDto.setRunDays( DateUtil.getBetweenDayNumber(matureReportDto.geteDate(),matureReportDto.getmDate()));
				
			}

			//累计净值：分红计算清单：历史累计分红
			BigDecimal accuValue = BigDecimal.ONE;
			//参考年化收益率：分红计算清单：本期清单年化收益率
			BigDecimal yearYield = BigDecimal.ZERO;
			//客户收益：分红计算清单：历史累计应分配金额
			BigDecimal custProfit = BigDecimal.ZERO;
        	Date settDate = DateUtil.parse("2000-01-01");

            //取分红计算清单，指定产品，指定分红日期，有效数据有且仅有一条,二期变更：chenxc
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Date profitBaseDate = ()searchParam.get("baseDate");
        	Map<String,Object> searchParam = new HashMap<>();
            searchParam.put("prodId", paraMap.get("prodId"));
            searchParam.put("profitBaseDate",matureReportDto.getmDate());
            List<DisBonusList> listBonus = reportJzfhbg001Mapper.selectBonusList(searchParam);
            
            if ( listBonus.size()>0 ) { 
	        	searchParam.put("settDate", sdf.format(listBonus.get(0).getLastProfitBaseDate()));
	        	RepProdIndex prodIndexGz = reportJzfhbg001Mapper.getIndexBySettDate(searchParam);
//            	for ( DisBonusList bonus:listBonus ) {
//            		accuValue = accuValue.add(bonus.getUnitBonus());
//            		custProfit = custProfit.add(bonus.getDisBonusMoney());
//    	        	if ( bonus.getProfitBaseDate().compareTo(settDate)>0 ) { //取最近的分红计划的年化收益率
//    	        		settDate = bonus.getProfitBaseDate();
//    	        		yearYield = bonus.getYield(); 
//    	        	}
//            	}
            	accuValue = prodIndexGz.getUnitValueT().add( listBonus.get(0).getUnitBonus());
            	yearYield = listBonus.get(0).getYield();
            }
//			for ( BonusReportDto report:listBonusReport ) {  //计算每次分红的累计值
//	        	//上一分配基准日
//	        	if ( "01".equals(report.getIssuingWay()) ) {  //开放式
//	    			Map<String,Object> map = new HashMap<>();
//	    			map.put("prodId", paraMap.get("prodId"));
//	    			map.put("reportDate", report.getSettDate());
//	        		DisProdBonusplan plan = bonusReportMapper.selectLastBonusPlan(map);
//	        		if ( plan==null  ) { //第一个计划
//	        			report.setLastSettDate(report.geteDate());
//	        		} else {
//	        			report.setLastSettDate(plan.getSettDate());
//	        		}
//	        	} else {
//	        		report.setLastSettDate(report.geteDate());
//	        	}
//	        	
//	        	//运行天数
//	        	if ( report.getLastSettDate().compareTo(report.geteDate())==0 ){
//	        		report.setRunDays( DateUtil.getBetweenDayNumber(report.getLastSettDate(),report.getSettDate()));
//	        	} else {
//	        		report.setRunDays( DateUtil.getBetweenDayNumber(report.getLastSettDate(),report.getSettDate()));
//	        	}
//	        	
//	        	//可供分配利润
//	        	report.setDistributableProfit(report.getAssetsValue().subtract(report.getCapital()));
//	
//	        	//业绩比较基准收益
//				BigDecimal abProfit = 
//				report.getCapital().multiply(report.getAchievementBasisProp())
//				.multiply(new BigDecimal(365))
//				.divide(new BigDecimal(report.getRunDays()),20,BigDecimal.ROUND_DOWN);
//				report.setAchievementBasisProfit(
//						report.getCapital().multiply(report.getAchievementBasisProp())
//						.multiply(new BigDecimal(365))
//						.divide(new BigDecimal(report.getRunDays()),2,BigDecimal.ROUND_DOWN) );
//	        	
//	        	//超出收益
//				BigDecimal esProfit = report.getDistributableProfit().subtract(abProfit);
//	        	report.setExcessProfit(report.getDistributableProfit().subtract(report.getAchievementBasisProfit()));
//	        	
//	        	//业绩报酬
//				BigDecimal floatFee = esProfit.multiply(report.getExcessAchievementProp());
//	        	report.setFloatFee(report.getExcessProfit().multiply(report.getExcessAchievementProp()).setScale(2, BigDecimal.ROUND_DOWN));
//	        	
//	        	//应分配利润
//	        	report.setDistributedProfit(report.getDistributableProfit().subtract(report.getFloatFee()));
//	        	//单位分红
//				report.setUnitProfit(
//						report.getDistributedProfit().divide(report.getCapital(), 4, BigDecimal.ROUND_DOWN));
//
//				// 年化收益率
//				BigDecimal ddProfit = report.getDistributableProfit().subtract(floatFee);
//				report.setYearYield(ddProfit.multiply(new BigDecimal(365))
//						.divide( new BigDecimal(report.getRunDays()).multiply(report.getCapital()), 4, BigDecimal.ROUND_DOWN));
//
//	        	accuValue = accuValue.add(report.getUnitProfit());
//	        	custProfit = custProfit.add(report.getDistributedProfit());
//	        	if ( report.getSettDate().compareTo(settDate)>0 ) { //取最近的分红计划的年化收益率
//	        		settDate = report.getSettDate();
//	        		yearYield = report.getYearYield(); 
//	        	}
//			}
        	
        	
			matureReportDto.setSumValueAmt(accuValue);
			matureReportDto.setRefYearYield(yearYield.multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN).toPlainString() );
			matureReportDto.setCustProfit(custProfit);
			
			//查询所有相关费用，通过主产品查找，如果是子产品id，还要找到主产品id
			List<RepFeeTrd> listFee = matureReportMapper.selectFees(paraMap);
			matureReportDto.setPurFee(BigDecimal.ZERO);
			matureReportDto.setRedemFee(BigDecimal.ZERO);
			for ( RepFeeTrd fee:listFee ) {
				if ("01".equals(fee.getFeeType()) ) {
					matureReportDto.setTrustFee(fee.getAccrual()==null?BigDecimal.ZERO : fee.getAccrual().setScale(2,BigDecimal.ROUND_DOWN));
				} else if ("02".equals(fee.getFeeType()) ) {
					matureReportDto.setMgrFee(fee.getAccrual()==null?BigDecimal.ZERO : fee.getAccrual().setScale(2,BigDecimal.ROUND_DOWN));
				} else if ("03".equals(fee.getFeeType()) ) {
					matureReportDto.setFloatFee(fee.getAccrual()==null?BigDecimal.ZERO : fee.getAccrual().setScale(2,BigDecimal.ROUND_DOWN));
				} else if ("04".equals(fee.getFeeType()) ) {
					matureReportDto.setSaleFee(fee.getAccrual()==null?BigDecimal.ZERO : fee.getAccrual().setScale(2,BigDecimal.ROUND_DOWN));
				} else if ("05".equals(fee.getFeeType()) ) {
					matureReportDto.setPurFee(fee.getAccrual()==null?BigDecimal.ZERO : fee.getAccrual().setScale(2,BigDecimal.ROUND_DOWN));
				} else if ("06".equals(fee.getFeeType()) ) {
					matureReportDto.setRedemFee(fee.getAccrual()==null?BigDecimal.ZERO : fee.getAccrual().setScale(2,BigDecimal.ROUND_DOWN));
				}
			}
			listResult.add(CollectionUtils.bean2Map(matureReportDto));
			
			//费用为null或者等于0 ，用“-”代替
			if ( matureReportDto.getTrustFee()==null || matureReportDto.getTrustFee().equals(BigDecimal.ZERO) ) {
				listResult.get(0).put("trustFeeStr", "-");
			} else {
				listResult.get(0).put("trustFeeStr", matureReportDto.getTrustFee().toPlainString());
			}
			if ( matureReportDto.getMgrFee()==null || matureReportDto.getMgrFee().equals(BigDecimal.ZERO) ) {
				listResult.get(0).put("mgrFeeStr", "-");
			} else {
				listResult.get(0).put("mgrFeeStr", matureReportDto.getMgrFee().toPlainString());
			}
			if ( matureReportDto.getFloatFee()==null || matureReportDto.getFloatFee().equals(BigDecimal.ZERO) ) {
				listResult.get(0).put("floatFeeStr", "-");
			} else {
				listResult.get(0).put("floatFeeStr", matureReportDto.getFloatFee().toPlainString());
			}			
			if ( matureReportDto.getSaleFee()==null || matureReportDto.getSaleFee().equals(BigDecimal.ZERO) ) {
				listResult.get(0).put("saleFeeStr", "-");
			} else {
				listResult.get(0).put("saleFeeStr", matureReportDto.getSaleFee().toPlainString());
			}
			if ( matureReportDto.getPurFee()==null || matureReportDto.getPurFee().equals(BigDecimal.ZERO) ) {
				listResult.get(0).put("purFeeStr", "-");
			} else {
				listResult.get(0).put("purFeeStr", matureReportDto.getPurFee().toPlainString());
			}
			if ( matureReportDto.getRedemFee()==null || matureReportDto.getRedemFee().equals(BigDecimal.ZERO) ) {
				listResult.get(0).put("redemFeeStr", "-");
			} else {
				listResult.get(0).put("redemFeeStr", matureReportDto.getRedemFee().toPlainString());
			}
		} catch (Exception e) {
			logger.error("到期报表数据错误", e);
			listResult.get(0).put("queryErrorMsg","到期报表数据错误："+e.getStackTrace());
		}
		if (listResult!=null && listResult.size()>0 ) {
			listResult.get(0).put("endDate_xx","");
			//listResult.get(0).put("floatFee_xx","");
			listResult.get(0).put("disDate_xx","");
			listResult.get(0).put("signDate_xx","");
			//listResult.get(0).put("trustFee_xx","");
			listResult.get(0).put("issuingWay_xx","");
			listResult.get(0).put("reportDate_xx","");
			listResult.get(0).put("repProdType_xx","");
//			listResult.get(0).put("prodName_xx","");
//			listResult.get(0).put("runDays_xx","");
//			listResult.get(0).put("mDate_xx","");
//			listResult.get(0).put("eDate_xx","");
//			listResult.get(0).put("reportTitle_xx","");
			//listResult.get(0).put("saleFee_xx","");
			//listResult.get(0).put("mgrFee_xx","");
			listResult.get(0).put("prodTruster_xx","");
			listResult.get(0).put("prodManager_xx","");
//			listResult.get(0).put("prodRe_xx","");
//			listResult.get(0).put("genDate_xx","");
			listResult.get(0).put("startDate_xx","");
		}
		return listResult;
	}
	
	public RepReveal getRepReveal(Map<String, Object> paraMap) {
		RepReveal repReveal = new RepReveal();
		repReveal.setRepId(paraMap.get("repId").toString());
		repReveal.setProdid(paraMap.get("prodId").toString());
		
		Date eDate = (Date)(paraMap.get("platformDate"));
		List<BonusReportDto> listBonusReport = matureReportMapper.selectBonusPlan(paraMap);
		if ( listBonusReport!=null && listBonusReport.size()>0 ) {   //取最新的分红计划，做到期信息
			BonusReportDto dto = listBonusReport.get(0);
			//eDate =holidayService.getNextWorkDate(dto.getSettDate());
			eDate =dto.getSettDate();
		}
		Map<String, Object> param = new HashMap<>();
		param.put("prodId", repReveal.getProdid());
		RepProdInfo info=reportRkcpjz001Mapper.getProdId(param);
		if(StringUtil.isNotEmpty(info)){
			repReveal.setProdid(info.getpProdId());
		}
		repReveal.setEndDate(eDate);
		return repReveal;
	}

}
