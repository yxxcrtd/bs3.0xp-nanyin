package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao.InvestorsQualityCheckCPYEComMapper;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;

/**
 * 产品余额、份额核查共同处理
 * @author wdb
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestorsQualityCheckCPYEComService extends BaseService<InvestorsQualityCheck001Dto>{
	/**
     * 共通Service
     */
	@Autowired
	private InvestorsQualityCheckCPYEComMapper investorsQualityCheckCPYEComMapper;  

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
    public BigDecimal cntRecNoCom(Map<String, Object> param) {
       
    	//1、取得对象记录数：
    	//2、返回对象产品数
    	return investorsQualityCheckCPYEComMapper.cntCPYECom(param);
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
    public List<InvestorsQualityCheck001Dto> searchCPYECom(Map<String, Object> param) {
    	
    	//变量定义
        List<InvestorsQualityCheck001Dto> resultList = new ArrayList<InvestorsQualityCheck001Dto>();
        List<InvestorsQualityCheck001Dto> searchList = new ArrayList<InvestorsQualityCheck001Dto>(); 
        //错误产品数        
        BigDecimal errPrdQty = new BigDecimal(0);
        
        String checkType = (String)param.get("checkType");
        		
        
        //1、抽取错误数据
        searchList = investorsQualityCheckCPYEComMapper.searchCPYECom(param);
        
        //2、以【1】的结果，做成中间处理用map<产品登记编码，list<【1】取出的数据结构>>
        Map<String,List<InvestorsQualityCheck001Dto>> handleMap = new HashMap<String,List<InvestorsQualityCheck001Dto>>();
        for (InvestorsQualityCheck001Dto dealDto : searchList) {
        	//当Map中存在此产品登记编码的记录时
        	if(handleMap.containsKey(dealDto.getPrdRegistCode())) {
        		handleMap.get(dealDto.getPrdRegistCode()).add(dealDto);
        	}else {
        		//当Map中不存在此产品登记编码的记录时
        		List<InvestorsQualityCheck001Dto> hadlePrdDtoList = new ArrayList<InvestorsQualityCheck001Dto>();
        		hadlePrdDtoList.add(dealDto);
        		handleMap.put(dealDto.getPrdRegistCode(), hadlePrdDtoList);        		
        	}     	   
        }
        
        
        // 3、对【2】的map进行主key循环处理
        for(String key:handleMap.keySet()){
	        //新建DTO InvestorsQualityCheck001Dto  errDTO
        	InvestorsQualityCheck001Dto errDTO1 = new InvestorsQualityCheck001Dto();
        	InvestorsQualityCheck001Dto errDTO2 = new InvestorsQualityCheck001Dto();
        	
        	//中间处理变量
        	InvestorsQualityCheck001Dto handleDto1 = new InvestorsQualityCheck001Dto();
            InvestorsQualityCheck001Dto handleDto2 = new InvestorsQualityCheck001Dto();
            InvestorsQualityCheck001Dto handleDtoMax = new InvestorsQualityCheck001Dto();
            
	        //变量.偏离度  =  0	
        	BigDecimal deviation = new BigDecimal(0);
        	BigDecimal deviation1 = new BigDecimal(0);
        	BigDecimal deviation2 = new BigDecimal(0);
        	
        	 
        	//3.1 只存在一条记录
        	if(handleMap.get(key).size() == 1) {
        		handleDto1 = handleMap.get(key).get(0);
        		//3.1.1 [此记录的.业务结束日期  =  此条记录的.持有日期]
        		if(DateUtil.format(handleDto1.getHoldDate(), "yyyy-MM-dd").equals(DateUtil.format(handleDto1.getBusiMDate(), "yyyy-MM-dd"))) {
        			//当 【入参.检查类型 = 01:余额】时，进行以下处理	
        			if("01".equals(checkType)) {
    					//变量.偏离度   设置为两个偏离度的较小值																									
    					//		Math.abs(投资端总金额 - 产品余额)  ÷ 产品余额 * 100%		
        				deviation1 = handleDto1.getInvestBalanceAmt().subtract(handleDto1.getPrdBalanceAmt()).abs().divide(handleDto1.getPrdBalanceAmt());
    					//		Math.abs(投资端总金额 - 实际募集金额)  ÷ 实际募集金额 * 100%				
        				deviation2 = handleDto1.getInvestBalanceAmt().subtract(handleDto1.getPrdRaiseTotalAmt()).abs().divide(handleDto1.getPrdRaiseTotalAmt());
    					//变量.偏离度   				=		（记录一.偏离度 <  记录二.偏离度）？ 记录一.偏离度 ： 记录二偏离度	
        				deviation = deviation1.compareTo(deviation2) == 1 ? deviation2 : deviation1;
        				
        			}else {																							
        				//以外的情况下，进行以下处理																										
        					//变量.偏离度   设置为两个偏离度的较小值																									
        					//Math.abs(投资端总份额 - 产品份额)  ÷ 产品份额 * 100%			
        					deviation1 = handleDto1.getInvestTotalQty().subtract(handleDto1.getPrdTotalQty()).abs().divide(handleDto1.getPrdTotalQty());
        					//Math.abs(投资端总份额 - 募集总份额)  ÷ 实际募集份额 * 100%		
        					deviation2 = handleDto1.getInvestTotalQty().subtract(handleDto1.getPrdRaiseTotalQty()).abs().divide(handleDto1.getPrdRaiseTotalQty());
        					//变量.偏离度   				=		（记录一.偏离度 <  记录二.偏离度）？ 记录一.偏离度 ： 记录二偏离度
        					deviation = deviation1.compareTo(deviation2) == 1 ? deviation2 : deviation1;
        			}
        		}else {																												
        			//3.1.2 其它情况																											
        				//当 【入参.检查类型 = 01:余额】时，进行以下处理
        				if("01".equals(checkType)) {
        				//变量.偏离度  				=		Math.abs(投资端总金额 - 产品金额)  ÷ 产品金额 * 100%		
        					deviation = handleDto1.getInvestBalanceAmt().subtract(handleDto1.getPrdBalanceAmt()).abs().divide(handleDto1.getPrdBalanceAmt());
        				}else {																											
        				//以外的情况下，进行以下处理																										
        				//变量.偏离度  				=		Math.abs(投资端总份额 - 产品份额)  ÷ 产品份额 * 100%	
        					deviation = handleDto1.getInvestTotalQty().subtract(handleDto1.getPrdTotalQty()).abs().divide(handleDto1.getPrdTotalQty());
        				}
        		}
        		
        		//错误记录追加
        		if(deviation.compareTo(BigDecimal.ZERO) == 1) {
        			//错误记录增加
        			errPrdQty = errPrdQty.add(BigDecimal.ONE);        			
	        		//errDTO1.产品登记编码										=			此条记录的.产品登记编码
	        		errDTO1.setPrdRegistCode(handleDto1.getPrdRegistCode());
	            	//errDTO1.持有日期										=			此条记录的.持有日期		
	        		errDTO1.setHoldDate(handleDto1.getHoldDate());
	            	//errDTO1.业务结束日										=			此条记录的.业务结束日			
	        		errDTO1.setBusiMDate(handleDto1.getBusiMDate());        		
	            	//errDTO1.产品端最新产品余额										=			此条记录的.产品余额
	        		errDTO1.setPrdBalanceAmt(handleDto1.getPrdBalanceAmt());
	            	//errDTO1.持有端产品余额										=			此条记录的.投资端总金额
	        		errDTO1.setInvestBalanceAmt(handleDto1.getInvestBalanceAmt());
	            	//errDTO1.产品端最新产品份额										=			此条记录的.产品份额
	        		errDTO1.setPrdTotalQty(handleDto1.getPrdTotalQty());
	            	//errDTO1.持有端产品份额										=			此条记录的.投资端总份额
	        		errDTO1.setInvestTotalQty(handleDto1.getInvestTotalQty());
	            	resultList.add(errDTO1);
        		}
        		
        		
        	//3.1 存在多条记录 	
        	}else if(handleMap.get(key).size() > 1) {
        		handleDto1 = handleMap.get(key).get(0);
        		handleDto2 = handleMap.get(key).get(1);
        		//3.2.1 存在【此记录的.业务结束日期  =  此条记录的.持有日期】的记录																											
				if(DateUtil.format(handleDto1.getHoldDate(), "yyyy-MM-dd").equals(DateUtil.format(handleDto1.getBusiMDate(), "yyyy-MM-dd")) ||
						DateUtil.format(handleDto2.getHoldDate(), "yyyy-MM-dd").equals(DateUtil.format(handleDto2.getBusiMDate(), "yyyy-MM-dd"))){	
        				//当 【入参.检查类型 = 01:余额】时，进行以下处理
					if("01".equals(checkType)) {
        				//变量.偏离度   				=		（记录一.偏离度 <  记录二.偏离度）？ 记录一.偏离度 ： 记录二偏离度																			
        				//记录一：		Math.abs(投资端总金额 - 产品余额)  ÷ 产品余额 * 100%		
						deviation1 = handleDto1.getInvestBalanceAmt().subtract(handleDto1.getPrdBalanceAmt()).abs().divide(handleDto1.getPrdBalanceAmt());
        				//记录二：		Math.abs(投资端总金额 - 产品余额)  ÷ 产品余额 * 100%				
						deviation2 = handleDto2.getInvestBalanceAmt().subtract(handleDto2.getPrdBalanceAmt()).abs().divide(handleDto2.getPrdBalanceAmt());
						
						deviation = deviation1.compareTo(deviation2) == 1 ? deviation2 : deviation1;
					}else {																										
        				//以外的情况下，进行以下处理																										
        				//变量.偏离度   				=		（记录一.偏离度 <  记录二.偏离度）？ 记录一.偏离度 ： 记录二偏离度																			
        				//记录一：		Math.abs(投资端总份额 - 产品份额)  ÷ 产品份额 * 100%	
						deviation1 = handleDto1.getInvestTotalQty().subtract(handleDto1.getPrdTotalQty()).abs().divide(handleDto1.getPrdTotalQty());
        				//记录二：		Math.abs(投资端总份额 - 产品份额)  ÷ 产品份额 * 100%
						deviation2 = handleDto2.getInvestTotalQty().subtract(handleDto2.getPrdTotalQty()).abs().divide(handleDto2.getPrdTotalQty());
						
						deviation = deviation1.compareTo(deviation2) == 1 ? deviation2 : deviation1;
					}
					
					//设置错误记录
					if(deviation.compareTo(BigDecimal.ZERO) == 1) {
						//错误记录增加
						errPrdQty = errPrdQty.add(BigDecimal.ONE); 
		        		if(deviation1.compareTo(BigDecimal.ZERO) == 1) {
		        			//errDTO1.产品登记编码										=			此条记录的.产品登记编码
			        		errDTO1.setPrdRegistCode(handleDto1.getPrdRegistCode());
			            	//errDTO1.持有日期										=			此条记录的.持有日期		
			        		errDTO1.setHoldDate(handleDto1.getHoldDate());
			            	//errDTO1.业务结束日										=			此条记录的.业务结束日			
			        		errDTO1.setBusiMDate(handleDto1.getBusiMDate());        		
			            	//errDTO1.产品端最新产品余额										=			此条记录的.产品余额
			        		errDTO1.setPrdBalanceAmt(handleDto1.getPrdBalanceAmt());
			            	//errDTO1.持有端产品余额										=			此条记录的.投资端总金额
			        		errDTO1.setInvestBalanceAmt(handleDto1.getInvestBalanceAmt());
			            	//errDTO1.产品端最新产品份额										=			此条记录的.产品份额
			        		errDTO1.setPrdTotalQty(handleDto1.getPrdTotalQty());
			            	//errDTO1.持有端产品份额										=			此条记录的.投资端总份额
			        		errDTO1.setInvestTotalQty(handleDto1.getInvestTotalQty());
			        		resultList.add(errDTO1);
		        		}
		        		
		        		if(deviation2.compareTo(BigDecimal.ZERO) == 1) {
		        			//errDTO1.产品登记编码										=			此条记录的.产品登记编码
			        		errDTO2.setPrdRegistCode(handleDto2.getPrdRegistCode());
			            	//errDTO1.持有日期										=			此条记录的.持有日期		
			        		errDTO2.setHoldDate(handleDto2.getHoldDate());
			            	//errDTO1.业务结束日										=			此条记录的.业务结束日			
			        		errDTO2.setBusiMDate(handleDto2.getBusiMDate());        		
			            	//errDTO1.产品端最新产品余额										=			此条记录的.产品余额
			        		errDTO2.setPrdBalanceAmt(handleDto2.getPrdBalanceAmt());
			            	//errDTO1.持有端产品余额										=			此条记录的.投资端总金额
			        		errDTO2.setInvestBalanceAmt(handleDto2.getInvestBalanceAmt());
			            	//errDTO1.产品端最新产品份额										=			此条记录的.产品份额
			        		errDTO2.setPrdTotalQty(handleDto2.getPrdTotalQty());
			            	//errDTO1.持有端产品份额										=			此条记录的.投资端总份额
			        		errDTO2.setInvestTotalQty(handleDto2.getInvestTotalQty());
			        		resultList.add(errDTO2);
		        		}        															            	
	        		}

				//3.2.2 其它情况	
				}else {	
					handleDtoMax = handleDto1.getBusiMDate().after(handleDto2.getBusiMDate()) ? handleDto1 : handleDto2;					
					if("01".equals(checkType)) {																								
        				//当 【入参.检查类型 = 01:余额】时，进行以下处理																										
        				//变量.偏离度   设置为业务日期较大的记录的偏离度																									
        				//Math.abs(投资端总金额 - 产品余额)  ÷ 产品余额 * 100%		
						deviation = handleDtoMax.getInvestBalanceAmt().subtract(handleDtoMax.getPrdBalanceAmt()).abs().divide(handleDtoMax.getPrdBalanceAmt());
					}else {																											
        				//以外的情况下，进行以下处理																										
        				//变量.偏离度   设置为业务日期较大的记录的偏离度																									
        				//Math.abs(投资端总份额 - 产品份额)  ÷ 产品份额 * 100%		
						deviation = handleDtoMax.getInvestTotalQty().subtract(handleDtoMax.getPrdTotalQty()).abs().divide(handleDtoMax.getPrdTotalQty());
					}
					
					//错误记录追加
	        		if(deviation.compareTo(BigDecimal.ZERO) == 1) {
	        			//错误记录增加
						errPrdQty = errPrdQty.add(BigDecimal.ONE);
		        		//errDTO1.产品登记编码										=			此条记录的.产品登记编码
		        		errDTO1.setPrdRegistCode(handleDtoMax.getPrdRegistCode());
		            	//errDTO1.持有日期										=			此条记录的.持有日期		
		        		errDTO1.setHoldDate(handleDtoMax.getHoldDate());
		            	//errDTO1.业务结束日										=			此条记录的.业务结束日			
		        		errDTO1.setBusiMDate(handleDtoMax.getBusiMDate());        		
		            	//errDTO1.产品端最新产品余额										=			此条记录的.产品余额
		        		errDTO1.setPrdBalanceAmt(handleDtoMax.getPrdBalanceAmt());
		            	//errDTO1.持有端产品余额										=			此条记录的.投资端总金额
		        		errDTO1.setInvestBalanceAmt(handleDtoMax.getInvestBalanceAmt());
		            	//errDTO1.产品端最新产品份额										=			此条记录的.产品份额
		        		errDTO1.setPrdTotalQty(handleDtoMax.getPrdTotalQty());
		            	//errDTO1.持有端产品份额										=			此条记录的.投资端总份额
		        		errDTO1.setInvestTotalQty(handleDtoMax.getInvestTotalQty());
		            	resultList.add(errDTO1);
				
	        		}								
        		}				
        	}       	        																			       	
        }

        //3、返回错误记录
        //错误记录数设置
        param.put("errPrdQty", errPrdQty);
        return resultList;
    }

}
