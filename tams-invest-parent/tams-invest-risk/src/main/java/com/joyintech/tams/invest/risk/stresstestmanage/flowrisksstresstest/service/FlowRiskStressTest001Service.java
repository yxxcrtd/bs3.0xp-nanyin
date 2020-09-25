package com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.service;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowRiskStressTest001Service extends CommonExportService<FlowRiskStressTest001Dto>
		implements BaseProcessService<FlowRiskStressTest001Dto> {

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
	    param.put("riskType", DictCodeListRsk.情景风险分类_流动性风险.getValue());
		param.put("effStatus", "01");
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
	 public FlowRiskStressTest001Dto flowRiskStressTest(FlowRiskStressTest001Dto FlowRiskStressTest001Dto){
	      
         return restTemplate.postForObject(
             ServiceApplication.RISK_SERVICE_NAME +
                "/FlowRiskStressTest001/Asset", FlowRiskStressTest001Dto,
                FlowRiskStressTest001Dto.class);
	 }
	 
	 /**
	  * 压力测试存储过程
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @param FlowRiskStressTest001Dto
	  * @see
	  * @since 1.0
	  */
	 public void stressTestLoad(FlowRiskStressTest001Dto dto){
	     Map<String,Object> param = new HashMap<>();
	     param.put("in_test_id", dto.getTestId());//测试代码
	     param.put("sceneId", dto.getSceneId());//情景代码
	     param.put("portfolioSingleName", dto.getPortfolioSingleName());//所选产品
		 param.put("in_object_ids", dto.getPortfolioSingleName());//所选产品
	     param.put("analysisVdate", dto.getAnalysisVdate());//开始日期
	     param.put("analysisMdate", dto.getAnalysisMdate());//结束日期
         restTemplate.postForObject(
             ServiceApplication.RISK_SERVICE_NAME +
                "/FlowRiskStressTest001/stressTestLoad", param,
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
		List<String> portfNameLis = new ArrayList<>();
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
	 * 组合名称下拉框查询
	 * @param param
	 * @return
	 */
	public List<SelectOptionDto> selectProdSelectOpt(@RequestBody Map<String, Object> param){
        
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +
            "/FlowRiskStressTest001/selectProdSelectOpt",param,
            SelectOptionDto.class);
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FlowRiskStressTest001Dto entity, OperationType type) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 判断情景名称有无重复
     * <一句话功能简述>
     * <功能详细描述>
     * @param FlowRiskStressTest001Dto
     * @return
     * @see
     * @since 1.0
     */
    public int stressTestNameCount(String testName){
        Map<String,Object> param = new HashMap<>();
        param.put("testName", testName);
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME +"/FlowRiskStressTest001/stressTestNameCount", 
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
        FlowRiskStressTest001Dto dto = restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME +"/FlowRiskStressTest001/judgeYesOrNoHoliday", 
            param,FlowRiskStressTest001Dto.class);
        return dto.getIsHoliday();
    }
    
}
