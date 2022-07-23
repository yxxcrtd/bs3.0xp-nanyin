package com.joyintech.tams.disclosure.rptdata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.common.DurationUtil;
import com.joyintech.tams.disclosure.rptdata.dao.DurationReportMapper;
import com.joyintech.tams.disclosure.rptdata.dto.DurationReportDto;

/**
 * 存续期报告-数据获取服务
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @since 1.0
 */
@Service
public class DurationReportDataService implements IReportDataService {

	@Autowired
	private DurationReportMapper durationReportMapper;
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(DurationReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();

		try {
			DurationReportDto reportDto = new DurationReportDto();

			//报告参数整理
			int year = 2022;
			int quarter = 2;
			Date currDate = new Date();
			Date reportDate = new Date();
			try {
				year = Integer.parseInt(paraMap.get("year").toString());
				quarter= Integer.parseInt(paraMap.get("quarter").toString());
				currDate = (Date)(paraMap.get("platformDate"));
				reportDate = DurationUtil.getQuarterEnd(year, quarter);
				paraMap.put("reportDate", reportDate);
				paraMap.put("prodId",paraMap.get("pprodid"));   //定期报告维度：母产品或者单产品
			} catch (Exception e ) {
				logger.error("报表参数错误.");
				e.printStackTrace();
			}

			//报表基本产品信息
			try {
				reportDto= durationReportMapper.selectProdInfo(paraMap);
				reportDto.setDuration(DurationUtil.getQuarterDuration(year, quarter, reportDto.geteDate()));
			} catch (Exception e) {
				logger.error("报表产品基本信息错误.");
				e.printStackTrace();				
			}
			
			//报表时间信息
			try {
				reportDto.setYear(year);
				reportDto.setQuarter(quarter);
				reportDto.setReportDate(reportDate);
				reportDto.setGenDate(currDate);
			} catch ( Exception e ) {
				logger.error("报表区间数据错误.");
				e.printStackTrace();
			}
			
			//财务指标
			try {
				List<Map<String,Object>> listFinIndex = durationReportMapper.selectFinIndex(paraMap);
				//只有一条数据
				if ( listFinIndex!=null && listFinIndex.size()>0 )
					reportDto.setFinIndex(listFinIndex.get(0));
				else
					reportDto.setFinIndex(new HashMap<>());
			} catch (Exception e) {
				logger.error("报表财务指标信息错误.");
				e.printStackTrace();				
			}
			
			//TODO:业绩基准
			//
			
			//报告指标
			try {
				List<Map<String,Object>> listRepIndex = durationReportMapper.selectReportIndex (paraMap);  //一般只有一条
				if ( listRepIndex!=null && listRepIndex.size()>0 ) {
					Map<String,Object> idx = listRepIndex.get(0);
					reportDto.setProdOperating(idx.get("jdbb_tzclhyzfx").toString());
					reportDto.setProdRisk(idx.get("jdbb_ldxfxfx").toString());
				} else {
					reportDto.setProdOperating("");
					reportDto.setProdRisk("");
				}
					
			} catch (Exception e) {
				logger.error("报表指标参数信息错误.");
				e.printStackTrace();				
			}
			
			//产品资产组合
			try {
				List<Map<String,Object>> listAssetComb = durationReportMapper.selectProdAssetComb (paraMap);
				if ( listAssetComb!=null && listAssetComb.size()>0 )
					reportDto.setProdAssetComb(listAssetComb.get(0));
				else
					reportDto.setProdAssetComb(new HashMap<>());
			} catch (Exception e) {
						logger.error("报表产品资产组合信息错误.");
						e.printStackTrace();				
			}
			
			try {
				//产品资产明细
				List<Map<String,Object>> listAssetDetail = durationReportMapper.selectProdAssetDetail (paraMap);
				reportDto.setListAssetsDetail(listAssetDetail);
			}  catch (Exception e) {
				logger.error("报表产品资产投资明细信息错误.");
				e.printStackTrace();				
			}
			
			//产品投资账户
			try {
				List<Map<String,Object>> listInvestAccts = durationReportMapper.selectAccts (paraMap);
				reportDto.setListAccts(listInvestAccts);
			}  catch (Exception e) {
				logger.error("报表投资账户信息错误.");
				e.printStackTrace();				
			}
			
			//关联交易
			try {
				List<Map<String,Object>> listRelDeal = durationReportMapper.selectRelDeals (paraMap);
				if ( listRelDeal!=null && listRelDeal.size()>0 ) {
					reportDto.setRepRelDeal(listRelDeal.get(0));
				} else {
					reportDto.setRepRelDeal(new HashMap<>());
				}
				
			} catch (Exception e) {
				logger.error("报表关联交易信息错误.");
				e.printStackTrace();				
			}
			
			listResult.add(CollectionUtils.bean2Map(reportDto));
		} catch (Exception e) {
			logger.error("定期报表数据查询错误", e);
			e.printStackTrace();
		}

		return listResult;
	}

}
