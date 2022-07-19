package com.joyintech.tams.disclosure.rptdata.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.disclosure.rptdata.dao.BonusReportMapper;
import com.joyintech.tams.disclosure.rptdata.dto.BonusPlanDto;
import com.joyintech.tams.disclosure.rptdata.dto.BonusReportDto;

@Service(value="bonusReportService")
public class BonusReportDataService implements IReportDataService{
	
	@Autowired
	private BonusReportMapper bonusReportMapper;
	
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(BonusReportDataService.class);

	@Override
	public List<Map<String,Object>> queryDatas(Map<String, Object> paraMap) {
	
		List<Map<String,Object>> listResult = new ArrayList<>();
		
		List<BonusReportDto> listReport = bonusReportMapper.selectBonusreport(paraMap);
        
        for ( BonusReportDto report:listReport ) {

        	//上一分配基准日
        	if ( "01".equals(report.getIssuingWay()) ) {  //开放式
        		BonusPlanDto plan = bonusReportMapper.selectLastBonusPlan(paraMap);
        		if ( plan==null  ) { //第一个计划
        			report.setLastSettDate(report.getEDate());
        		} else {
        			report.setLastSettDate(plan.getSettDate());
        		}
        	} else {
        		report.setLastSettDate(report.getEDate());
        	}
        	
        	try {
	        	//运行天数
	        	if ( report.getLastSettDate().compareTo(report.getEDate())==0 ){
	        		report.setRunDays( DateUtil.getBetweenDayNumber(report.getSettDate(),report.getLastSettDate())+1);
	        	} else {
	        		report.setRunDays( DateUtil.getBetweenDayNumber(report.getSettDate(),report.getLastSettDate()));
	        	}
	        	
	        	//可供分配利润
	        	report.setDistributableProfit(report.getAssetsValue().subtract(report.getCapital()));
	
	        	//业绩比较基准收益
	        	report.setAchievementBasisProfit(report.getCapital().multiply(report.getAchievementBasisProp()));
	        	
	        	//超出收益
	        	report.setExcessProfit(report.getDistributableProfit().subtract(report.getAchievementBasisProfit()));
	        	
	        	//业绩报酬
	        	report.setFloatFee(report.getExcessProfit().multiply(report.getExcessAchievementProp()));
	        	
	        	//应分配利润
	        	report.setDistributedProfit(report.getDistributableProfit().subtract(report.getFloatFee()));
	        	
	        	//单位分红
	        	report.setUnitProfit(report.getDistributedProfit().divide(report.getCapital(),4,RoundingMode.HALF_UP));
	        	
	        	//年化收益率
	        	report.setYearYield(report.getUnitProfit().multiply(new BigDecimal(365)).divide(  new BigDecimal(report.getRunDays()),4,RoundingMode.HALF_UP  ));
	        	
	        	listResult.add(CollectionUtils.bean2Map(report));
        	} catch (Exception e ) {
        		logger.error("报表数据错误",e);
        		e.printStackTrace();
        	}
        }
        
        return  listResult;
	}


	
}
