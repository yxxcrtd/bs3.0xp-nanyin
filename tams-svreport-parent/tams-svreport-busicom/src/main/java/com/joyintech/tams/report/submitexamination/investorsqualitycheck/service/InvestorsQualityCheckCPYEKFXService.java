package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;

/**
 * 产品余额合格率-开放型Service
 * @author wdb
 *
 */
@Service
public class InvestorsQualityCheckCPYEKFXService extends InvestorsQualityCheckBaseService{
    
	@Autowired
	private InvestorsQualityCheckCPYEComService investorsQualityCheckCPYEComService;
	
	
	/**
     * 查询核查对象产品数
     *
     * @param searchDate 查询日期
     * @param orgCodeList 部门代码list
     * @return 合格率记录
     * @see
     * @since 1.0
     */
	@Override
    public InvestorsQualityCheck001Dto cntHGL(Map<String, Object> param) {
        InvestorsQualityCheck001Dto resultDto = new InvestorsQualityCheck001Dto();
        
        HashMap<String, Object> proParam = new HashMap<>();
        //画面输入参数设置-查询日期
        proParam.put("searchDate", param.get("searchDate")); 
        //画面输入参数设置-部门
        String orgCode = (String)param.get("orgCode");
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        proParam.put("orgCodeList", orgCodeList);        
        //设置产品类型 03：开放式净值型,04:开放式非净值型
        List<String> prdTypeList = new ArrayList<String>();  
        prdTypeList.add("03");
        prdTypeList.add("04");        
        proParam.put("prdTypeList", prdTypeList);        
        //设置检查类型
        proParam.put("checkType", "01");
        
        //1、取得对象记录数：											
      	//调用【合格率计算共通.1、核查对象数量取得】   
        BigDecimal checkRecTol = investorsQualityCheckCPYEComService.cntRecNoCom(proParam);
                
        //2、取得错误记录数：									
        //调用【合格率计算共通.2、错误记录取得】
        investorsQualityCheckCPYEComService.searchCPYECom(proParam);
        BigDecimal errPrdQty = (BigDecimal)proParam.get("errPrdQty");    
        
        //3、返回值
        resultDto.setPageType("10");
        resultDto.setUnQualified(proParam.get("errPrdQty").toString());
        resultDto.setTotal(checkRecTol.toString()); 
        
        //检查对象为0的情况下：
        if(checkRecTol.compareTo(BigDecimal.ZERO) == 1) {
        	resultDto.setRate(checkRecTol.subtract(errPrdQty).divide(
        			checkRecTol,2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP).toPlainString());
        }
        return resultDto;
    }
	
		
	/**
     * 查询核查对象产品数
     *
     * @param searchDate 查询日期
     * @param orgCodeList 部门代码list
     * @param prdTypeList 产品类型list
     * @return 余额、数量、余额方向、本币余额
     * @see
     * @since 1.0
     */
	@Override
    public List<InvestorsQualityCheck001Dto> queryErrorRecList(Map<String, Object> param) {
               
        List<InvestorsQualityCheck001Dto> resultList = new ArrayList<InvestorsQualityCheck001Dto>();
        HashMap<String, Object> proParam = new HashMap<>();
        //画面输入参数设置-查询日期
        proParam.put("searchDate", param.get("searchDate")); 
        //画面输入参数设置-部门
        String orgCode = (String)param.get("orgCode");
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        proParam.put("orgCodeList", orgCodeList);        
        //设置产品类型 03：开放式净值型,04:开放式非净值型
        List<String> prdTypeList = new ArrayList<String>();  
        prdTypeList.add("03");
        prdTypeList.add("04");        
        proParam.put("prdTypeList", prdTypeList);        
        //设置检查类型
        proParam.put("checkType", "01");

        //2、取得错误记录数：									
    	//调用【合格率计算共通.2、错误记录取得】								
        resultList = investorsQualityCheckCPYEComService.searchCPYECom(proParam);
        //3、返回值
 
        return resultList;
    }

	@Override
	public void setParamComm(Map<String, Object> param) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Page<InvestorsQualityCheck001Dto> queryPage(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}
}
