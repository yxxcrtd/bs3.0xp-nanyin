/*
 * 文件名：PtlCashFlowService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlmanualrelease.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlmanualrelease.dao.PtlManualReleaseMapper;
import com.joyintech.service.portfolio.ptlmanualrelease.dto.PtlManualRelease001Dto;
import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author chenlei1
 * @version 1.0 2019年12月10日
 * @see PtlManualReleaseService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PtlManualReleaseService extends BaseService<PtlManualRelease001Dto> {
    /**
     * 平台日期
     */
    @Autowired(required=false)
    private PlatformDateImpl platformDateImpl;
    
    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    
	@Autowired
	private PtlManualReleaseMapper ptlManualReleaseMapper;

	/**
	 * 获取组合代码的联想文本框 <功能详细描述>
	 * 
	 * @param param
	 *            param
	 * @param page
	 *            page
	 * @param length
	 *            length
	 * @return SelectOptionDto
	 * @see
	 * @since 1.0
	 */
	public List<SuggestDto> getPortfolioId(Map<String, Object> param) {
		return ptlManualReleaseMapper.getPortfolioId(param);
	}

	/**
	 * 获取组合代码的下拉框 <功能详细描述>
	 * 
	 * @param param
	 *            param
	 * @return SelectOptionDto
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getPortfolioIdToNew(Map<String, Object> param) {
		return ptlManualReleaseMapper.getPortfolioIdToNew(param);
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param param
	 *            param
	 * @param page
	 *            page
	 * @param length
	 *            length
	 * @return SelectOptionDto
	 * @see
	 * @since 1.0
	 */
	/*
	 * public Page<SelectOptionDto> getCounterId(int page, int length,
	 * Map<String, Object> param) { return queryForPageForSelectReturn(page,
	 * length, () -> ptlManualReleaseMapper.getCounterId(param)); }
	 */
	public List<SuggestDto> getCounterId(Map<String, Object> param) {
		return ptlManualReleaseMapper.getCounterId(param);
	}

	/**
	 * 联想文本框service层实现
	 *
	 * @param param
	 *            String
	 * @return SelectOptionDto
	 */
	public List<SuggestDto> getSecAcctId(String param) {
		return ptlManualReleaseMapper.getSecAcctId(param);
	}

	/**
	 * 质押式正回购查询
	 * 
	 * @param pageParam
	 *            Map
	 * @return Page类
	 * @throws Exception
	 *             异常信息
	 */
	public Page<PtlManualRelease001Dto> selectForPageRs(int page, int length, Map<String, Object> param)
			throws Exception {
	     param.put("ptDate", platformDateImpl.getSysDate());

	      /*登记托管机构传参处理 start*/
	        String registOrg = (String)param.get("registOrg");
			 List<String> regList = new ArrayList<String>();
	        if (StringUtil.isBlank(registOrg)) {
	   	      String[] regArr = {DictCodeList.登记托管机构_中央国债登记结算有限责任公司.getValue(), 
		    		 DictCodeList.登记托管机构_上海清算所.getValue()};
		      //regList = Arrays.asList(regArr);
	        }
	        else {
	        	regList.add(registOrg);
	        }
		     param.put("regList", regList);
	       /*登记托管机构传参处理 end*/  

		 /*交易场所传参处理 start*/
		     String tradeMarket = (String)param.get("tradeMarket");
	   		 List<String> tmList = new ArrayList<String>();
	        if (StringUtil.isBlank(tradeMarket)) {
		     String[] tmArr = {DictCodeListExt1.交易场所_银行间.getValue(), 
		    		 DictCodeListExt1.交易场所_上海证券交易所.getValue(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()};
		        tmList = Arrays.asList(tmArr);
	        }
	        else {
	        	tmList.add(tradeMarket);
	        }
		    param.put("tmList", tmList);
	       /*交易场所传参处理 end*/ 
	     
	     // 交易日期
        if (param.get("dataDateStart") == null || StringUtil.isEmpty((String)param.get("dataDateStart"))) {
        	param.replace("dataDateStart", null);
        }
		Page<PtlManualRelease001Dto> pageData = super.queryForPage(page, length,
				() -> ptlManualReleaseMapper.selectForPageRs(param));
		return pageData;
	}

	

	/**
	 * 质押券查询
	 * 
	 * @param pageParam
	 *            Map
	 * @return Page类
	 * @throws Exception
	 *             异常信息
	 */
	public Page<PtlManualRelease001Dto> findPageRepInfo(int page, int length, Map<String, Object> param)
			throws Exception {
	    
		Page<PtlManualRelease001Dto> pageData = super.queryForPage(page, length,
				() -> ptlManualReleaseMapper.findPageRepInfo(param));
		return pageData;
	}
	
	public List<PtlManualRelease001Dto> findPageRepInfoList(Map<String, Object> param)
			throws Exception {	    
		List<PtlManualRelease001Dto> listData =  ptlManualReleaseMapper.findPageRepInfo(param);
		return listData;
	}
	/**
	 * 质押式逆回购查询
	 * 
	 * @param pageParam
	 *            Map
	 * @return Page类
	 * @throws Exception
	 *             异常信息
	 */
	public Page<PtlManualRelease001Dto> selectForPageRb(int page, int length, Map<String, Object> param)
			throws Exception {
	     param.put("ptDate", platformDateImpl.getSysDate());
	     
	     // 交易日期
       if (param.get("dataDateStart") == null || StringUtil.isEmpty((String)param.get("dataDateStart"))) {
       	param.replace("dataDateStart", null);
       }

	     
		/*交易场所传参处理 start*/
	     String tradeMarket = (String)param.get("tradeMarket");
   		 List<String> tmList = new ArrayList<String>();
        if (StringUtil.isBlank(tradeMarket)) {
	     String[] tmArr = {DictCodeListExt1.交易场所_银行间.getValue(), 
	    		 DictCodeListExt1.交易场所_上海证券交易所.getValue(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()};
	        tmList = Arrays.asList(tmArr);
        }
        else {
        	tmList.add(tradeMarket);
        }
	    param.put("tmList", tmList);
       /*交易场所传参处理 end*/ 
	    
		Page<PtlManualRelease001Dto> pageData = super.queryForPage(page, length,
				() -> ptlManualReleaseMapper.selectForPageRb(param));
		/*
		 * if (pageData != null && !StringUtil.isEmptyList(pageData.getRoot()))
		 * { for (PtlManualRelease001Dto dataDto : pageData.getRoot()) { if
		 * (StringUtil.equals(DictCodeList.释放状态_已释放.getValue(),
		 * dataDto.getReleaseStatus())) {
		 * dataDto.setReleaseStatus(DictCodeList.释放状态_已释放.getCodeName()); } else
		 * if (StringUtil.equals(DictCodeList.释放状态_未释放.getValue(),
		 * dataDto.getReleaseStatus())) {
		 * dataDto.setReleaseStatus(DictCodeList.释放状态_未释放.getCodeName()); } } }
		 */
		return pageData;
	}


	/**
	 * 质押式正回购解冻
	 * 
	 * @param pageParam
	 *            Map
	 * @return Page类
	 * @throws Exception
	 *             异常信息
	 */
	public String revokeRs(PtlManualRelease001Dto dto) {
		
		String tradeId = dto.getTradeId();
		if(StringUtil.isNotEmpty(tradeId)){
		  String releasestatus=ptlManualReleaseMapper.getReleaseStatusRs(tradeId);	
		  if(DictCodeList.释放状态_未释放.getValue().equals(releasestatus) || StringUtil.isEmpty(releasestatus)){
              
			  /*增加对实际持仓的释放**start**/
			   Map<String, Object> pm=new HashMap<String, Object>();
			   pm.put("tradeId",tradeId);
			   PtlManualRelease001Dto deal=ptlManualReleaseMapper.selectTradeInfo(pm);		         
	  	    	PtlSecPositionFlowDto pto=new PtlSecPositionFlowDto();		                	
	  	    	pto.setPortfolioId(deal.getPortfolioId());//组合代码上述查询结果.组合代码			
				
		        String secAcctId = deal.getAcctId();
		        if ((StringUtil.isEmpty(secAcctId) || "999999".equals(secAcctId)) && StringUtil.isNotEmpty(deal.getChlId())) {
		            secAcctId = deal.getChlId();
		        }
		        pto.setSecAcctId(StringUtil.isEmpty(secAcctId) ? "999999" : secAcctId);//证券管理账户/通道代码上述查询结果.证券管理账户/通道代码																																											
				pto.setFinprodId(deal.getFinprodId());//金融产品代码	   上述查询结果.金融产品代码																																											
				pto.setBranch(deal.getBranch());//分支序号	 上述查询结果.分支序号																																											
				pto.setHodingType(DictCodeList.持仓类型_实际.getValue());//持仓类型	   上述查询结果.持仓类型																																											
				pto.setCdate(platformDateImpl.getSysDate());//日期		 系统平台日			    				
				pto.setAmount(ArithmeticUtil.multiply(deal.getPrinAmt(),new BigDecimal(-1)));//																																									
				pto.setCcy(deal.getCcy());//币种	 上述查询结果.币种																																											
				pto.setTradeId(tradeId);//交易编号
		        pto.setInvAim(StringUtil.isEmpty(deal.getInvAim()) ? "999999" : deal.getInvAim());
		        
	            basePtlSecPositionInfoService.inertSecPositionFlow(pto);
	            /*增加对实际持仓的释放**end**/
	            
			  //3.1、更新 释放状态 = 已释放																																																								
			  ptlManualReleaseMapper.updateReleaseStatusRs(tradeId);
			  List<PtlManualRelease001Dto> dtolist =ptlManualReleaseMapper.selectForPageRsAddSec(tradeId);
			  dtolist.forEach(idto -> {
				    //dto.getSelectedItem().forEach(idto -> {
						//3.2、更新组合持仓流水
						//按照“FVTPL 公允价值变动计入当期损益-FVOCI公允价值变动计入其他综合收益-AC摊余成本类”顺序回增持仓	
					    BigDecimal amount= idto.getAllPrice();//.multiply(new BigDecimal(10000));	
						 Map<String, BigDecimal> amountMap = new HashMap<>();
						 amountMap.put("amount", amount);
						 //EtrSecPositionFlowDto
						List<PtlSecPositionFlowDto> newetlist=new ArrayList<PtlSecPositionFlowDto>();
						
						Map<String, Object> param=new HashMap<String, Object>();
					    param.put("ptDate", platformDateImpl.getSysDate());
						param.put("secId", idto.getSecId());
						param.put("portfolioId", idto.getPortfolioId());
						param.put("hodingtype", "04");						
		                List<PtlSecPosition> etlist = ptlManualReleaseMapper.selectSecPosition(param);
		                etlist.forEach(etl -> {
		   		        	if(amountMap.get("amount") != null && amountMap.get("amount").compareTo(BigDecimal.ZERO)>0){                       
		                	    	PtlSecPositionFlowDto edto=new PtlSecPositionFlowDto();		                	
				    				edto.setPortfolioId(etl.getPortfolioId());//组合代码上述查询结果.组合代码																																													                	
				    				edto.setSecAcctId(etl.getSecAcctId());//证券管理账户/通道代码上述查询结果.证券管理账户/通道代码																																											
				    				edto.setFinprodId(etl.getFinprodId());//金融产品代码	   上述查询结果.金融产品代码																																											
				    				edto.setBranch(etl.getBranch());//分支序号	 上述查询结果.分支序号																																											
				    				edto.setHodingType(etl.getHodingType());//持仓类型	   上述查询结果.持仓类型																																											
				    				edto.setCdate(platformDateImpl.getSysDate());//日期		 系统平台日																																											
				    				edto.setAmount(ArithmeticUtil.multiply(etl.getAmount().compareTo(amountMap.get("amount"))>0?amountMap.get("amount"):etl.getAmount(), new BigDecimal(-1)));//数量	 （上述查询结果.数量>回增总数量?回增总数量：上述查询结果.数量）*-1																																											
				    				edto.setCcy(etl.getCcy());//币种	 上述查询结果.币种																																											
				    				edto.setTradeId(tradeId);//交易编号
				    				edto.setInvAim(etl.getInvAim());
				    				newetlist.add(edto);
				   				    amountMap.put("amount", amountMap.get("amount").subtract(ArithmeticUtil.multiply(edto.getAmount(), new BigDecimal(-1))));
		   		        	}
		                });
		                
		                //3.3、更新组合持仓																																									
		/*                newetlist.forEach(etl -> {
		                });	*/
		                newetlist.forEach(etl -> {
		                	etl.setHodingType(DictCodeList.持仓类型_质押.getValue());		
		                    basePtlSecPositionInfoService.inertSecPositionFlow(etl);
		                });	

					 });
	           
	          }
		
		}else{
			return Boolean.FALSE.toString();
		}

		return Boolean.TRUE.toString();
	}


	/**
	 * 质押式逆回购解冻
	 * 
	 * @param pageParam
	 *            Map
	 * @return Page类
	 * @throws Exception
	 *             异常信息
	 */
	public String revokeRb(PtlManualRelease001Dto dto) {

		String tradeId = dto.getTradeId();
		if(StringUtil.isNotEmpty(tradeId)){
				
			List<PtlCashPositionFlowDto> newetlist=new ArrayList<PtlCashPositionFlowDto>();			
	
			List<PtlManualRelease001Dto> dtolist =ptlManualReleaseMapper.selectForPageRbAdd(tradeId);
			String  accid =ptlManualReleaseMapper.selectAccId(tradeId);
			dtolist.forEach(idto -> {
			//dto.getSelectedItem().forEach(idto -> {
				String releasestatus=ptlManualReleaseMapper.getReleaseStatusRb(tradeId);
				if(DictCodeList.释放状态_未释放.getValue().equals(releasestatus) || StringUtil.isEmpty(releasestatus)){
					//3.1、更新 释放状态 = 已释放																																																								
					ptlManualReleaseMapper.updateReleaseStatusRb(tradeId);
		
					PtlCashPositionFlowDto ecdto=new PtlCashPositionFlowDto();
					ecdto.setPortfolioId(idto.getPortfolioId()); //组合代码	勾选记录.组合代码																																																	
					ecdto.setAcctId(accid);// 	
					ecdto.setHodingType(DictCodeList.持仓类型_可用.getValue());//持仓类型  可用																																									
					ecdto.setCdate(platformDateImpl.getSysDate());//日期	系统平台日																																													
					ecdto.setAmount(idto.getMtradeAmt());//数量	勾选记录.到期结算金额
					ecdto.setCcy("CNY");//币种	CNY																																													
					ecdto.setOrderId(idto.getTradeId());  //勾选记录.交易编号																																												
					ecdto.setFinprodId(null);//通道金融产品代码	空																																																				
					ecdto.setSplitType(null);//拆分标识	空		
					newetlist.add(ecdto);																																								
	
				 }
		        //3.3、更新组合现金
				newetlist.forEach(etl -> {
		               basePtlCashPositionInfoService.insertCashPositionFlow(etl);
		           });	
			    });
			
		}else{
			return Boolean.FALSE.toString();
		}
		return Boolean.TRUE.toString();
	}
	
	
	public PtlManualRelease001Dto selectTradeInfo(Map<String, Object> param) {
		return ptlManualReleaseMapper.selectTradeInfo(param);
	}
}
