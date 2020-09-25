package com.joyintech.service.portfolio.ptlcashflowgapanalysis.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlcashflowgapanalysis.dao.PtlCashFlowGapAnalysisMapper;
import com.joyintech.service.portfolio.ptlcashflowgapanalysis.dto.PtlCashFlowGapAnalysisDto;
@Service
public class PtlCashFlowGapAnalysisService extends BaseService<PtlCashFlowGapAnalysisDto>{
	
	@Autowired
	private PtlCashFlowGapAnalysisMapper ptlCashFlowGapAnalysisMapper;
	/**
     * 下拉框实体类列表
     * @param map 下拉框实体类列表
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        List<SelectOptionDto> dto =  ptlCashFlowGapAnalysisMapper.getPortfolioId(map);
        return dto;
    }
	/**
	 * 获取资金账户下拉框中的值
	 * @param map
	 * @return
	 */
	public List<SelectOptionDto> getFundAccount(@RequestBody Map<String,Object> map){
    	List<SelectOptionDto> selectOptDto = ptlCashFlowGapAnalysisMapper.getFundAccount(map);
        return selectOptDto;
    }
	
	 /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<PtlCashFlowGapAnalysisDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
    	//如果汇总维度字段不为空
		pageParam.put("F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
		pageParam.put("T03", DictCodeList.交易方向_行权_回售.getValue());
		pageParam.put("T39", DictCodeList.交易方向_行权_赎回.getValue());
		pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("ORDER_STATUS_01", DictCodeListExt1.指令状态_新建.getValue());
		pageParam.put("ORDER_STATUS_02", DictCodeListExt1.指令状态_撤销.getValue());
    	if(pageParam.get("aggregDimension") != null && pageParam.get("aggregDimension") != ""){
    		//汇总
    		if(StringUtil.isNotEmpty(pageParam.get("portfolioId1"))){
                pageParam.put("portfolioIdList1",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("portfolioId1")));
            }
    		// add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
            if(StringUtil.isNotEmpty(pageParam.get("prodManager1"))){
                pageParam.put("prodManager1", StringUtil.splitToList(null, pageParam.get("prodManager1").toString()));
            }else {
                pageParam.put("prodManager1", null);
            }
            // add by wangyang1 【吉林银行】 增加 产品经理  end 
            int displayAccuracy1 = 0;
            if(StringUtil.isNotEmpty(pageParam.get("displayAccuracy1"))){
            	switch(pageParam.get("displayAccuracy1").toString()){
            	case "01" :
            		displayAccuracy1 = 1;
            		break;
            	case "02" :
            		displayAccuracy1 = 10000;
            		break;
            	case "03" :
            		displayAccuracy1 = 1000000;
            		break;
            	case "04" :
            		displayAccuracy1 = 100000000;
            	}
            }
            pageParam.put("displayAccuracy1",displayAccuracy1);
            //汇总维度为日期型
            if(pageParam.get("aggregDimension").equals("01")){
            	return super.queryForPage(page, length, () -> ptlCashFlowGapAnalysisMapper.selectForDate(pageParam));
            }else {
            	//汇总维度为期限结构
            	return super.queryForPage(page, length, () -> ptlCashFlowGapAnalysisMapper.selectForTerm(pageParam));
            }
    		
    	}else {
    		//明细
    		if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
                pageParam.put("portfolioIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("portfolioId")));
            }
            if(StringUtil.isNotEmpty(pageParam.get("cashType"))){
                pageParam.put("cashTypeList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("cashType")));
            }
            if(StringUtil.isNotEmpty(pageParam.get("ps"))){
                pageParam.put("psList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("ps")));
            }
    		// add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
            if(StringUtil.isNotEmpty(pageParam.get("prodManager"))){
                pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
            } else {
                pageParam.put("prodManager", null);
            }
            // add by wangyang1 【吉林银行】 增加 产品经理  end 
            if(StringUtil.isNotEmpty(pageParam.get("fundAccount"))){
                pageParam.put("fundAccountList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("fundAccount")));
            }
            int displayAccuracy = 0;
            if(StringUtil.isNotEmpty(pageParam.get("displayAccuracy"))){
            	
            	switch(pageParam.get("displayAccuracy").toString()){
            	case "01" :
            		displayAccuracy = 1;
            		break;
            	case "02" :
            		displayAccuracy = 10000;
            		break;
            	case "03" :
            		displayAccuracy = 1000000;
            		break;
            	case "04" :
            		displayAccuracy = 100000000;
            	}
            }
            pageParam.put("displayAccuracy",displayAccuracy);
            List<PtlCashFlowGapAnalysisDto> list = ptlCashFlowGapAnalysisMapper.select(pageParam);
            Page<PtlCashFlowGapAnalysisDto> page1 = new Page<PtlCashFlowGapAnalysisDto>();
            //如果是导出求合计
            if (StringUtil.isNotEmpty(pageParam.get("tab"))){
                String tab = (String)pageParam.get("tab");
                if (StringUtil.equals(tab, "0")){
                    BigDecimal cashFlow = new BigDecimal(0);
                    for (PtlCashFlowGapAnalysisDto dto: list) {
                        cashFlow = ArithmeticUtil.doAdd(cashFlow, dto.getCashFlow());
                    }
                    PtlCashFlowGapAnalysisDto total = new PtlCashFlowGapAnalysisDto();
                    total.setcDateStr("合计");
                    total.setCashFlow(cashFlow);
                    list.add(total);
                    page1.setRoot(list);
                    return page1;
                }
            }   
            return super.queryForPage(page, length, () -> ptlCashFlowGapAnalysisMapper.select(pageParam));
    		
    	}
    }
 
    public List<PtlCashFlowGapAnalysisDto> selectForList(Map<String, Object> pageParam)
        throws Exception {
      //如果汇总维度字段不为空
        pageParam.put("F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("T03", DictCodeList.交易方向_行权_回售.getValue());
        pageParam.put("T39", DictCodeList.交易方向_行权_赎回.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("ORDER_STATUS_01", DictCodeListExt1.指令状态_新建.getValue());
        pageParam.put("ORDER_STATUS_02", DictCodeListExt1.指令状态_撤销.getValue());
        //明细
        if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
            pageParam.put("portfolioIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("portfolioId")));
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if(StringUtil.isNotEmpty(pageParam.get("prodManager"))){
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end 
        if(StringUtil.isNotEmpty(pageParam.get("fundAccount"))){
            pageParam.put("fundAccountList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("fundAccount")));
        }
        int displayAccuracy = 0;
        if(StringUtil.isNotEmpty(pageParam.get("displayAccuracy"))){
            
            switch(pageParam.get("displayAccuracy").toString()){
            case "01" :
                displayAccuracy = 1;
                break;
            case "02" :
                displayAccuracy = 10000;
                break;
            case "03" :
                displayAccuracy = 1000000;
                break;
            case "04" :
                displayAccuracy = 100000000;
            }
        }
        pageParam.put("displayAccuracy",displayAccuracy);
        return  ptlCashFlowGapAnalysisMapper.select(pageParam);
    }
}
