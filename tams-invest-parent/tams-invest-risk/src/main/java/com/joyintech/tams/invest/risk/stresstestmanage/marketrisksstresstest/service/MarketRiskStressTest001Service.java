package com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.service;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketRiskStressTest001Service extends CommonExportService<MarketRiskStressTest001Dto>
		implements BaseProcessService<MarketRiskStressTest001Dto> {

	private String investType = "INVEST_NATURE";
	private String openType = "OPEN_TYPE";
	private String raiseType = "RAISE_TYPE";

	/**
	 * 情景名称下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> querySceneOption(String search){
	    Map<String, Object> param = new HashMap<>();
	    param.put("scene", search);
	    param.put("riskType", DictCodeListRsk.情景风险分类_市场性风险.getValue());
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +"/FlowRiskStressTest001/querySceneOption", param,
               SelectOptionDto.class);
    }
	/**
	 * 压力测试执行
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param FlowRiskStressTest001Dto
	 * @see
	 * @since 1.0
	 */
	 public MarketRiskStressTest001Dto marketRiskStressTest(MarketRiskStressTest001Dto MarketRiskStressTest001Dto){
	      
         return restTemplate.postForObject(
             ServiceApplication.RISK_SERVICE_NAME +
                "/MarketRiskStressTest001/Asset", MarketRiskStressTest001Dto,
                MarketRiskStressTest001Dto.class);
	 }
	 
	 /**
	  * 压力测试存储过程
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @param MarketRiskStressTest001Dto
	  * @see
	  * @since 1.0
	  */
	 public void stressTestLoad(MarketRiskStressTest001Dto dto){
	     Map<String,Object> param = new HashMap<>();
	     param.put("in_test_id", dto.getTestId());//测试代码
	     param.put("sceneId", dto.getSceneId());//情景代码
	     param.put("portfolioSingleName", dto.getPortfolioSingleName());//所选产品
	     param.put("analysisVdate", dto.getAnalysisVdate());//开始日期
	     param.put("analysisMdate", dto.getAnalysisMdate());//结束日期
         restTemplate.postForObject(
             ServiceApplication.RISK_SERVICE_NAME +
                "/MarketRiskStressTest001/stressTestLoad", param,
                Integer.class);
     }
	 
	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param type
	 * @param indId
	 * @param portfName
	 * @param profitType
	 * @param issueMode
	 * @param raiseTypeCond
	 * @param investTypeCond
	 * @return
	 * @see
	 * @since 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TreeNode> getAllPortfolioBySort(String type,String indId,String portfName,String profitType,String issueMode,String raiseTypeCond,String investTypeCond) {

		List<DataDictItem> profitList = new ArrayList<DataDictItem>();
		List<TreeNode> result = new ArrayList<TreeNode>();
		List<TreeNode> treeProList = new ArrayList<TreeNode>();
		if (DictCodeListExt1.分类维度_收益类型.getValue().equals(type)) {
			treeProList = restTemplate.getForList(
					ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskStressTest001/getAllPortfolioByProfit?indId=" + indId+"&portfName="+portfName+"&profitType="+profitType+"&issueMode="+issueMode+"&raiseTypeCond="+raiseTypeCond+"&investTypeCond="+investTypeCond,
					TreeNode.class);
			profitList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.收益类型.getValue());

		} else {
			if (DictCodeListExt1.分类维度_开放类型.getValue().equals(type)) {
				treeProList = restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME
						+ "/FlowRiskStressTest001/getAllPortfolioByOther?fields=" + openType+"&indId=" + indId+"&portfName="+portfName+"&profitType="+profitType+"&issueMode="+issueMode+"&raiseTypeCond="+raiseTypeCond+"&investTypeCond="+investTypeCond, TreeNode.class);
				profitList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.开放类型.getValue());
			}
			if (DictCodeListExt1.分类维度_募集方式.getValue().equals(type)) {
				treeProList = restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME
						+ "/FlowRiskStressTest001/getAllPortfolioByOther?fields=" + raiseType+"&indId=" + indId+"&portfName="+portfName+"&profitType="+profitType+"&issueMode="+issueMode+"&raiseTypeCond="+raiseTypeCond+"&investTypeCond="+investTypeCond, TreeNode.class);
				profitList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.募集方式.getValue());
			}
			if (DictCodeListExt1.分类维度_投资性质.getValue().equals(type)) {
				treeProList = restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME
						+ "/FlowRiskStressTest001/getAllPortfolioByOther?fields=" + investType+"&indId=" + indId+"&portfName="+portfName+"&profitType="+profitType+"&issueMode="+issueMode+"&raiseTypeCond="+raiseTypeCond+"&investTypeCond="+investTypeCond, TreeNode.class);
				profitList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.投资性质.getValue());
			}
		}
		if (profitList.size() > 0) {
			for (DataDictItem map : profitList) {
				TreeNode treeDto = new TreeNode();
				treeDto.setId(map.getItemCode());
				treeDto.setName(map.getItemValue());
				treeDto.setpId("");
				treeDto.setOpen(true);
				result.add(treeDto);
			}
		}
		result.addAll(treeProList);
		return result;

	}






    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(MarketRiskStressTest001Dto entity, OperationType type) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 判断情景名称有无重复
     * <一句话功能简述>
     * <功能详细描述>
     * @param MarketRiskStressTest001Dto
     * @return
     * @see
     * @since 1.0
     */
    public int stressTestNameCount(String testName){
        Map<String,Object> param = new HashMap<>();
        param.put("testName", testName);
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME +"/MarketRiskStressTest001/stressTestNameCount", 
            param,Integer.class);
   }
    
    /**
     * 判断日期是否法定节假日
     * <功能详细描述>
     * @param analysisMdate
     * @return
     * @see
     * @since 1.0
     */
    public String judgeYesOrNoHoliday(String analysisMdate){
        Map<String,Object> param = new HashMap<>();
        param.put("analysisMdate", analysisMdate);
        MarketRiskStressTest001Dto dto = restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME +"/MarketRiskStressTest001/judgeYesOrNoHoliday", 
            param,MarketRiskStressTest001Dto.class);
        return dto.getIsHoliday();
    }
    
    /**
     * 判断情景下有无为准备的因子
     * <功能详细描述>
     * @param sceneId
     * @return
     * @see
     * @since 1.0
     */
    public int countFacType(String sceneId){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneId", sceneId);
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME +"/MarketRiskStressTest001/countFacType", 
            param,Integer.class);
    }
    
}
