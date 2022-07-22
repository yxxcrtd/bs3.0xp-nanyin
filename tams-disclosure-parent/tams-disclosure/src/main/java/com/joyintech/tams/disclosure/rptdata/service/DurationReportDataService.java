package com.joyintech.tams.disclosure.rptdata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.DisProdAsset;
import com.joyintech.tams.common.base.entity.RepAssetsComb;
import com.joyintech.tams.common.base.entity.RepIndexSub;
import com.joyintech.tams.common.base.entity.RepInvestAccount;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.common.base.entity.RepRelDeal;
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
			//报告参数
			int year = (int)paraMap.get("year");
			int quarter= (int)paraMap.get("quarter");
			Date currDate = (Date)(paraMap.get("platformDate"));
			
			//报表基本参数
			DurationReportDto reportDto= durationReportMapper.selectProdInfo(paraMap);
			
			//报表区间
			reportDto.setDuration(DurationUtil.getQuarterDuration(year, quarter, reportDto.geteDate()));
			reportDto.setReportDate(DurationUtil.getQuarterEnd(year, quarter));
			reportDto.setGenDate(currDate);
			
			//财务指标	
			List<RepProdIndex> listFinIndex = durationReportMapper.selectFinIndex(paraMap);
			reportDto.setListFinIndex(listFinIndex);
			
			//TODO:业绩基准
			//
			
			//报告指标
			List<RepIndexSub> listRepIndex = durationReportMapper.selectReportIndex (paraMap);
			for ( RepIndexSub idx:listRepIndex ) {
				if ( "jdbb_tzclhyzfx".equals(idx.getPriId() ) ) {
					reportDto.setProdOperating(idx.getIndexValue());
				}
				else if ( "jdbb_ldxfxfx".equals(idx.getPriId()) ) {
					reportDto.setProdRisk(idx.getIndexValue());
				}
			}
			
			//产品资产组合
			List<DisProdAsset> listAssetComb = durationReportMapper.selectProdAssetComb (paraMap);
			reportDto.setProdAssetComb(listAssetComb.get(0));
			
			//产品资产明细
			List<RepAssetsComb> listAssetDetail = durationReportMapper.selectProdAssetDetail (paraMap);
			reportDto.setListAssetsDetail(listAssetDetail);
			
			//产品投资账户
			List<RepInvestAccount> listInvestAccts = durationReportMapper.selectAccts (paraMap);
			reportDto.setListAccts(listInvestAccts);
			
			//关联交易
			List<RepRelDeal> listRelDeal = durationReportMapper.selectRelDeals (paraMap);
			reportDto.setListRelDeal(listRelDeal);
			
			listResult.add(CollectionUtils.bean2Map(reportDto));
		} catch (Exception e) {
			logger.error("到期报表数据错误", e);
			e.printStackTrace();
		}

		return listResult;
	}

}
