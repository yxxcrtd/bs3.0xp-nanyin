package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.SettReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.SettReportDto;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

/**
 * 清算报告-数据获取服务
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @see 
 * @since 1.0
 */
@Service
public class SettReportDataService implements IReportDataService {

//	@Autowired
//	private RepProdInfoMapper repProdInfoMapper;

	@Autowired
	private SettReportMapper settReportMapper;
	
	@Autowired
	private HolidayService holidayService;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(SettReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#,##0.00");
		SettReportDto settReportDto = new SettReportDto();
		try {
			String prodId = (paraMap.get("prodid") == null ? null : paraMap.get("prodid").toString());
			if ( prodId==null ) {
				prodId = (paraMap.get("pprodid") == null ? null : paraMap.get("pprodid").toString());
			}
			Date reportDate = new Date();
			if ( paraMap.get("reportDate") != null ) {
				reportDate = ((Date)paraMap.get("reportDate"));
			} else {
				if ( paraMap.get("platformDate") != null ) {
					reportDate = holidayService.getPreWorkDate((Date)paraMap.get("platformDate"));
				}
			}

			//reportDate = DateUtil.parse("2023-03-01");
			
			// 查找产品
			Map<String, BigDecimal> mapBalance = new HashMap<String, BigDecimal>();

			List<SettReportDto> list = settReportMapper.selectProdInfo(prodId);
			if ( list.size()>0 ) 
				settReportDto = list.get(0);
			
			if (settReportDto.getBonusPlanId() != null) {
				
				Map<String,Object> paras = new HashMap<>();
				paras.put("prodId",prodId);
				paras.put("reportDate",reportDate);
				
				List<BokBalance> listBalance = settReportMapper.selectBokBalance(paras);

				// 将list转诚map,空值统一转化为0
				mapBalance = listBalance.stream().map(x -> {
					if (x.getoAmt() == null)
						x.setoAmt(BigDecimal.ZERO);
					return x;
				}).collect(Collectors.toMap(BokBalance::getSubjectNo, BokBalance::getoAmt, (key1, key2) -> key2));
				settReportDto.setReportDate(reportDate);
//				settReportDto.setProdId(prodInfo.getProdId());
//				settReportDto.setProdName(prodInfo.getProdName());
//				settReportDto.setProdRe(prodInfo.getProdRe());
//				settReportDto.seteDate(prodInfo.getSetDate());
//				settReportDto.setTrustName(prodInfo.getAcctChnname());
				settReportDto.setmDate(holidayService.getNextWorkDate(settReportDto.getmDate()));

				//签字日期
				try {
					Date signDate = holidayService.getLastWordDateOfWeek( holidayService.getNextWorkDate(settReportDto.getmDate()) );
					settReportDto.setSignDate(signDate);  //产品到期日+1工作日所在周的最后一个工作日
				} catch( Exception e ) {
					logger.error("获取签字日期错误.");
				}
				
				BigDecimal sumAssetAmt = BigDecimal.ZERO;
				// 银行存款
				BigDecimal amt = mapBalance.get("100201");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setDeposit(df.format(amt)); // 银行存款
					sumAssetAmt = sumAssetAmt.add(amt);
				} else {
					settReportDto.setDeposit("-");  
				}
				//settReportDto.setDeposit(mapBalance.get("100201")==null?"-":mapBalance.get("100201").toString()); // 银行存款
				
				// 保证金
				amt = mapBalance.get("103106");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setGuarantyAmt(df.format(amt)); //保证金
					sumAssetAmt = sumAssetAmt.add(amt);
				} else {
					settReportDto.setGuarantyAmt("-");  
				}
				//settReportDto.setGuarantyAmt(mapBalance.get("103106")); // 保证金

				// 应计利息
				amt = mapBalance.get("10020601");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setIntAmt(df.format(amt)); //利息
					sumAssetAmt = sumAssetAmt.add(amt);
				} else {
					settReportDto.setIntAmt("-");  
				}
				//settReportDto.setIntAmt(mapBalance.get("10020601")); // 应计利息
				
				// 资产合计
				if ( sumAssetAmt!=null &&  sumAssetAmt.compareTo(BigDecimal.ZERO)!=0 ) {
					settReportDto.setSumAssetAmt(df.format(sumAssetAmt.setScale(2,BigDecimal.ROUND_DOWN))); // 银行存款
				} else {
					settReportDto.setSumAssetAmt("-");  
				}
				//settReportDto.setSumAssetAmt(amt.toString());

				// 管理费
				BigDecimal sumDebtAmt = BigDecimal.ZERO;
				amt = mapBalance.get("220601");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setMgrFee(df.format(amt)); // 银行存款
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setMgrFee("-");  
				}
				//settReportDto.setMgrFee(mapBalance.get("220601")); // 管理费
				
				// 应付管理人业绩报酬
				amt = mapBalance.get("220602");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setFloatFee(df.format(amt));
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setFloatFee("-");  
				}
				// settReportDto.setFloatFee(mapBalance.get("220602"));
				
				// 应付托管费
				amt = mapBalance.get("2207");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setTrustFee(df.format(amt));
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setTrustFee("-");  
				}
				// settReportDto.setTrustFee(mapBalance.get("2207")); // 托管费
				
				//  销售费
				amt = mapBalance.get("2208");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setSaleFee(df.format(amt)); // 银行存款
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setSaleFee("-");  
				}
				//settReportDto.setSaleFee(mapBalance.get("2208")); // 销售费
				
				// 应付赎回款
				amt = mapBalance.get("2203");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setRedemAmt(df.format(amt)); // 银行存款
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setRedemAmt("-");  
				}
				//settReportDto.setPayAmt(mapBalance.get("2203")==null?BigDecimal.ZERO:mapBalance.get("2203")); // 应付款回款
				
				// 税费
				amt = mapBalance.get("2221");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setTaxFee(df.format(amt)); // 银行存款
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setTaxFee("-");  
				}
				//settReportDto.setTaxFee(mapBalance.get("2221")); 
				
				// 应付利润
				amt = mapBalance.get("2232");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setProfit(df.format(amt));
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setProfit("-");  
				}
				//settReportDto.setProfit(mapBalance.get("2232"));
				
				// 其他应付款
				amt = mapBalance.get("2241");
				if ( amt!=null &&  amt.compareTo(BigDecimal.ZERO)!=0 ) {
					amt = amt.setScale(2,BigDecimal.ROUND_DOWN);
					settReportDto.setOtherPayAmt(df.format(amt)); // 银行存款
					sumDebtAmt = sumDebtAmt.add(amt);
				} else {
					settReportDto.setOtherPayAmt("-");  
				}
				//settReportDto.setOtherPayAmt(mapBalance.get("2241"));
				
				settReportDto.setAssetValue("-"); // 资产价值，固定值0

//				settReportDto.setSumDebtAmt(mapBalance.get("220601").add(mapBalance.get("220602"))
//						.add(mapBalance.get("2207")).add(mapBalance.get("2208")).add(mapBalance.get("2203"))
//						.add(mapBalance.get("2221")).add(mapBalance.get("2232")).add(mapBalance.get("2241"))); 
				// 负债合计
				if ( sumDebtAmt!=null &&  sumDebtAmt.compareTo(BigDecimal.ZERO)!=0 ) {
					settReportDto.setSumDebtAmt(df.format(sumDebtAmt.setScale(2,BigDecimal.ROUND_DOWN))); // 银行存款
				} else {
					settReportDto.setSumDebtAmt("-");  
				}
			} 
			listResult.add(CollectionUtils.bean2Map(settReportDto));
		} catch (Exception e) {
			logger.error("清算报表数据错误", e);
			listResult.add(CollectionUtils.bean2Map(settReportDto));
		}
		if (listResult!=null && listResult.size()>0 ) {
			listResult.get(0).put("endDate_xx","");
			listResult.get(0).put("floatFee_xx","");
			listResult.get(0).put("trustName_xx","");
			listResult.get(0).put("assetValue_xx","");
			listResult.get(0).put("disDate_xx","");
			listResult.get(0).put("redemAmt_xx","");
			listResult.get(0).put("trustFee_xx","");
			listResult.get(0).put("mapBalance_xx","");
			listResult.get(0).put("repProdType_xx","");
			listResult.get(0).put("taxFee_xx","");
			listResult.get(0).put("otherPayAmt_xx","");
			listResult.get(0).put("profit_xx","");
			listResult.get(0).put("guarantyAmt_xx","");
			listResult.get(0).put("sumAssetAmt_xx","");
			listResult.get(0).put("intAmt_xx","");
			listResult.get(0).put("reportTitle_xx","");
			listResult.get(0).put("sumDebtAmt_xx","");
			listResult.get(0).put("saleFee_xx","");
			listResult.get(0).put("mgrFee_xx","");
			listResult.get(0).put("prodTruster_xx","");
			listResult.get(0).put("prodManager_xx","");
			listResult.get(0).put("genDate_xx","");
			listResult.get(0).put("deposit_xx","");
			listResult.get(0).put("startDate_xx","");			
			listResult.get(0).put("eDate_xx","");
		}
		return listResult;
	}

	public RepReveal getRepReveal(Map<String, Object> paraMap) {
		
		RepReveal repReveal = new RepReveal();
		repReveal.setRepId(paraMap.get("repId").toString());
		repReveal.setProdid(paraMap.get("prodId").toString());
		
		//结束日是是产品到期日
		Date eDate = new Date();
		List<SettReportDto> list = settReportMapper.selectProdInfo(paraMap.get("prodId").toString());
		if ( list!=null && list.size()>0 ) {
			eDate = holidayService.getNextWorkDate(list.get(0).getmDate());
		} else {
			if ( paraMap.get("platformDate") != null ) {
				eDate = holidayService.getPreWorkDate((Date)paraMap.get("platformDate"));
			}
		}
		repReveal.setEndDate(eDate);
		return repReveal;
	}
}
