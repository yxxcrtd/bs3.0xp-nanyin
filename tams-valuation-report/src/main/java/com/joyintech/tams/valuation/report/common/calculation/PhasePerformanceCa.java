package com.joyintech.tams.valuation.report.common.calculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardCalRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardCalRuleDetail;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.report.common.service.TargetIndexService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 阶段业绩比较基准计算 共通
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-12-28
 */
@Service
@Scope("prototype")
public class PhasePerformanceCa {
   
	/**
     * 共通方法service
     */
    @Autowired
    private  FinProductCommonBatchService finProductCommonService;
	
    /**
     * 阶段业绩比较基准 以及阶段业绩比较基准List
     * @param startDate
     * @param endDate
     * @param prodId
     * @returnMap<BigDecimal, List<BigDecimal>>
     */
	@SuppressWarnings("unchecked")
	public    Map<BigDecimal, List<BigDecimal>>   getPhasePerformance(Date startDate,Date endDate,String prodId){
		//定义返回值
		Map<BigDecimal, List<BigDecimal>>  resultMap=new  HashMap<>();
		List<BigDecimal>  listBig=new ArrayList<>();
		// 调用批处理共通->标的信息查询
	    TargetSearchDto 业绩报酬计算规则 = new TargetSearchDto();
		List<TargetSearchDto> queryCriteria=new ArrayList<>();
		 // 传入[标的_信息]Dto
		业绩报酬计算规则.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
		业绩报酬计算规则.setQueryType(ValDictCodeList.标的_业绩报酬计算规则.getValue());
		业绩报酬计算规则.setFinprodId(prodId);
		业绩报酬计算规则.setCashType2(DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
		业绩报酬计算规则.setEffdateStart(startDate);
		业绩报酬计算规则.setEffdateEnd(endDate);
		queryCriteria.add(业绩报酬计算规则);
		Map<TargetSearchDto, Object> targetInfoMap=finProductCommonService.getTargetInfo(prodId, queryCriteria);
		TargetPerfRewardCalRule targetPerfRewardCalRule=new   TargetPerfRewardCalRule();
		// 标的_业绩报酬计算规则
		  if (!MapUtils.isEmpty(targetInfoMap)){
			  List<TargetPerfRewardCalRule>	targetPerfRewardCalRuleList =(List<TargetPerfRewardCalRule>)targetInfoMap.get(业绩报酬计算规则);
			  if(targetPerfRewardCalRuleList==null||targetPerfRewardCalRuleList.size()==0){
			   //则不进行计算
				  listBig.add(BigDecimal.ZERO);
				  resultMap.put(BigDecimal.ZERO, listBig);
				  return resultMap;
			  }else{
				  targetPerfRewardCalRule=targetPerfRewardCalRuleList.get(targetPerfRewardCalRuleList.size()-1);  
			  }
		  }
		//计算年化的业绩比较基准
        //本次计提日=参数开始日
        Date accrualDate=startDate;
		//上次计提日 = 本次计提日.上一日
        Date lastAccrualDate=TargetIndexService.getBeforeDate(accrualDate);
        //获得开始日到结束日集合大小
        List<Date> listDate = new ArrayList<>();
		if (startDate == endDate) {
			listDate.add(startDate);
		} else {
			listDate = StageValueGrowthRate.getDatesBetweenTwoDate(startDate, endDate);
		}
		//计算业绩比较基准
	    resultMap=calFloatManageFee(targetPerfRewardCalRule, lastAccrualDate, accrualDate, listDate);
		
		
		 return resultMap;
	}
	
	/**
     * 计算阶段业绩比较基准
     * 
     * @param targetPerfRewardRule 业绩报酬规则
     * @param lastAccrualDate 本次计提结束日
     * @param accrualDate 本次计提开始日
     * @return 阶段业绩比较基准map
     */
    private   Map<BigDecimal, List<BigDecimal>>  calFloatManageFee(TargetPerfRewardCalRule targetPerfRewardCalRule,  Date lastAccrualDate, Date accrualDate, List<Date> listDate) {
        Map<BigDecimal, List<BigDecimal>>  resultMap=new HashMap<>();
    	// 基准利率生效日
        Date cdate = null;
        if (DictCodeList.生效规则_期初.getValue().equals(targetPerfRewardCalRule.getActRule())) {
            cdate = lastAccrualDate;
        }
        else if (DictCodeList.生效规则_期末.getValue().equals(targetPerfRewardCalRule.getActRule())) {
            cdate = accrualDate;
        }
        // 基准利率集合 = 调用共通查询：利率行情信息查询（基准利率生效日）
        Map<String, MstIntRateInfo> mapIntRateInfo = finProductCommonService.getValIntRateInfo(cdate, null);

        List<TargetPerfRewardCalRuleDetail> targetPerfRewardRuleDetailList = targetPerfRewardCalRule.getTargetPerfRewardCalRuleDetailList();
        // 集合需要按照层级进行排序
        Collections.sort(targetPerfRewardRuleDetailList, new Comparator<TargetPerfRewardCalRuleDetail>() {

            @Override
            public int compare(TargetPerfRewardCalRuleDetail o1, TargetPerfRewardCalRuleDetail o2) {
                return o1.getLevel() - o2.getLevel();
            }
        });     
        //定义：年化的业绩比较基准 = 0；
        BigDecimal  yearCompareRule=BigDecimal.ZERO;
       //定义阶段化的业绩比较基准=0；
        BigDecimal  claCompareRule=BigDecimal.ZERO;
       //定义：阶段化的业绩比较基准LIST ；
       List<BigDecimal>  listCompareRule=new ArrayList<>(); 
       //业绩比较基准list
       List<BigDecimal>  ycoList=new ArrayList<>(); 
        for (TargetPerfRewardCalRuleDetail targetPerfRewardRuleDetail : targetPerfRewardRuleDetailList) {
        	// 调整系数 = 业绩报酬规则明细.调整系数
            BigDecimal coefficient = targetPerfRewardRuleDetail.getCoefficient();
            // 利差 = 业绩报酬规则明细.利差
            BigDecimal spreadRate = targetPerfRewardRuleDetail.getSpreadRate();
            BigDecimal basicRate = BigDecimal.ZERO;
            if (!StringUtil.isEmpty(targetPerfRewardRuleDetail.getBasicRate()) && mapIntRateInfo != null && mapIntRateInfo.size() > 0) {
                basicRate = mapIntRateInfo.get(targetPerfRewardRuleDetail.getBasicRate()).getIntRate();
            }
            // 业绩比较基准 = 基准利率 * 调整系数 + 利差
            BigDecimal 业绩比较基准 = ArithmeticUtil.doAdd(ArithmeticUtil.multiply(basicRate, coefficient), spreadRate);
            // 业绩报酬规则明细.业绩比较基准 = 业绩比较基准
            targetPerfRewardRuleDetail.setCompareBasis(业绩比较基准);
            //求最小业绩比较基准
            ycoList.add(业绩比较基准);
            //得到业绩比较基准List求标准差
            listCompareRule.add(业绩比较基准);   
        }
        //求最小业绩比较基准=年化的业绩比较基准
        yearCompareRule= Collections.min(ycoList);
        //阶段化的业绩比较基准 = 年化的业绩比较基准/365*(结束日期-开始日期）
   	    claCompareRule=yearCompareRule.divide(new BigDecimal(365),20,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(listDate.size()-1));
        
   	    
        if(listCompareRule==null||listCompareRule.size()==0){
        	listCompareRule.add(BigDecimal.ZERO);
        }
        resultMap.put(claCompareRule, listCompareRule);
        
        return resultMap;
       
    }
    
	
  
    
}
