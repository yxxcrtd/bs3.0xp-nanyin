package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;

/**
 * 募集期余额合格率
 * @author wdb
 *
 */
@Service
public class InvestorsQualityCheckMJQYEService extends InvestorsQualityCheckBaseService{
    
	@Autowired
	private InvestorsQualityCheckMJQFEYEComService investorsQualityCheckMJQFEYEComService;
	
	
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
        List<InvestorsQualityCheck001Dto> resultList = new ArrayList<InvestorsQualityCheck001Dto>();
        //1、取得对象记录数：											
      	//调用【合格率计算共通.1、核查对象数量取得】
         
        BigDecimal checkRecTol = investorsQualityCheckMJQFEYEComService.cntRecTolCom(param);
        
        //2、取得错误记录数：									
        //调用【合格率计算共通.2、错误记录取得】         
        //设置检查类型
        param.put("checkType", "01");
        resultList = investorsQualityCheckMJQFEYEComService.searchMJQCom(param);
        BigDecimal errPrdQty = new BigDecimal(resultList.size());
        
               
        
        //3、返回值
        resultDto.setPageType("7");
        resultDto.setUnQualified(errPrdQty.toString());
        resultDto.setTotal(checkRecTol.toString()); 
        if(checkRecTol.compareTo(BigDecimal.ZERO) == 1) {
        	resultDto.setRate(checkRecTol.subtract(errPrdQty).divide(checkRecTol,2,BigDecimal.ROUND_DOWN).toString());
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
        //1、取得错误记录列表：											
		//调用【合格率计算共通.2、错误记录取得】    
        //设置检查类型
        param.put("checkType", "01");

        //2、取得错误记录数：									
    	//调用【合格率计算共通.2、错误记录取得】								
        resultList = investorsQualityCheckMJQFEYEComService.searchMJQCom(param);
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
