package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao.InvestorsQualityCheckMJQFEYEComMapper;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;

/**
 * 募集期份额、余额查询共通
 * @author wdb
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestorsQualityCheckMJQFEYEComService extends BaseService<InvestorsQualityCheck001Dto>{
	/**
     * 共通Service
     */
	@Autowired
	private InvestorsQualityCheckMJQFEYEComMapper investorsQualityCheckMJQFEYEComMapper;  

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
    public BigDecimal cntRecTolCom(Map<String, Object> param) {
       
    	//1、取得对象记录数：
    	//2、返回对象产品数
    	return new BigDecimal(investorsQualityCheckMJQFEYEComMapper.searchMJQFEYERecCom(param).size());    										

    }
    
    /**
     * 核查结果-错误记录返回
     *
     * @param searchDate 查询日期
     * @param orgCodeList 部门代码list
     * @param prdTypeList 产品类型list
     * @param checkType 检查类型
     * @return 余额、数量、余额方向、本币余额
     * @see
     * @since 1.0
     */
    public List<InvestorsQualityCheck001Dto> searchMJQCom(Map<String, Object> param) {
    	
    	//变量定义
        List<InvestorsQualityCheck001Dto> resultList = new ArrayList<InvestorsQualityCheck001Dto>();
        List<InvestorsQualityCheck001Dto> searchList = new ArrayList<InvestorsQualityCheck001Dto>(); 
        
        //错误产品数        
        String checkType = (String)param.get("checkType");
        
        //1、抽取错误数据
        searchList = investorsQualityCheckMJQFEYEComMapper.searchMJQFEYERecCom(param);
        
        //2、循环处理【1】的记录，并计算偏离度：
        for (InvestorsQualityCheck001Dto dealDto : searchList) {
        	//变量.偏离度  =  0	
        	BigDecimal deviation = new BigDecimal(0);
        	InvestorsQualityCheck001Dto errDTO1 = new InvestorsQualityCheck001Dto();
        	
        	
        	//当 【入参.检查类型 = 01:余额】时，进行以下处理
    		if("01".equals(checkType)) {
    		//变量.偏离度  = Math.abs(产品端募集总金额 - 投资端募集总金额)  ÷ 产品端募集总金额 * 100%		
    			deviation = dealDto.getPrdRaiseTotalAmt().subtract(dealDto.getInvestRaiseTotalAmt()).abs().divide(
    					dealDto.getPrdRaiseTotalAmt(),5,BigDecimal.ROUND_UP);
    		}else {																											
    		//以外的情况下，进行以下处理																										
    		//变量.偏离度  =	Math.abs(产品端募集总份额 - 投资端募集总份额)  ÷ 产品端募集总份额 * 100%
    			deviation = dealDto.getPrdRaiseTotalQty().subtract(dealDto.getInvestRaiseTotalQty()).abs().divide(
    					dealDto.getPrdRaiseTotalQty(),5,BigDecimal.ROUND_UP);
    		}	
    		
    		
    		//3、根据偏离度做成错误数据记录，并返回														
    		//当变量.偏离度   > 0时，进行以下处理					
    		if(deviation.compareTo(BigDecimal.ZERO) == 1) {
    			//errDTO.产品登记编码		=			此条记录的.产品登记编码			
    			errDTO1.setPrdRegistCode(dealDto.getPrdRegistCode());
				//errDTO.起息日				=			此条记录的.起息日	
    			errDTO1.setPrdVDate(dealDto.getPrdVDate());
    			//errDTO.持有日期			=			此条记录的.持有日期		
    			errDTO1.setHoldDate(dealDto.getHoldDate());    			
    			//errDTO.产品端募集总金额	=			此记录的.产品端募集总金额			
    			errDTO1.setPrdRaiseTotalAmt(dealDto.getPrdRaiseTotalAmt());    			
    			//errDTO.持有端募集总金额	=			此记录的.持有端募集总金额		
    			errDTO1.setInvestRaiseTotalAmt(dealDto.getInvestRaiseTotalAmt());
    			//errDTO.产品端募集总份额	=			此记录的.产品端募集总份额		
    			errDTO1.setPrdRaiseTotalQty(dealDto.getPrdRaiseTotalQty());
    			//errDTO.持有端募集总份额	=			此记录的.持有端募集总份额		
    			errDTO1.setInvestRaiseTotalQty(dealDto.getInvestRaiseTotalQty());  
    			resultList.add(errDTO1);
    		}
    		
        }
       return resultList;
    }

}
