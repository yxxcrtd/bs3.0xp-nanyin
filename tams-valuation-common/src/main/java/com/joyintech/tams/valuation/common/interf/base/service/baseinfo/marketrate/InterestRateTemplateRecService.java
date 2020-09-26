package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.marketrate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.valuation.common.interf.base.dto.InterestInfoRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValIntrate;

/**
 * 行情信息-利率行情
 * 
 * @author dongxiangyang
 * @version 1.0 2018年7月05日
 * @see InterestRateTemplateRecService
 * @since 1.0
 */

@Service
@Scope("prototype")
public class InterestRateTemplateRecService {
	 @Autowired
	    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
	 
	 @SuppressWarnings({"unchecked", "rawtypes"})
	 public InterestInfoRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO, TemplateOrderInfoReaderResult acctEntity){
		    // 定义返回变量
		 InterestInfoRecCommonResult  result=new InterestInfoRecCommonResult();
		 
		 List<ValIntrate> valIntrateList=new ArrayList<ValIntrate>();
		  
		 //解析模板后的list以及日志list
	        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
	     
	        //文件路径设置  TODO 此部分处理待以后从画面入口的时        候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
	        // 1、文件读取解析 进行数据有效性的check,并出进入日志表的数据结构
	        returnResult= templateAnalysisForXMLService.execute(acctEntity);
	        valIntrateList=returnResult.getT(ValIntrate.class);
	        
	        //解析出来的dto转换为表dto
	        Map<String,Object> map=this.handleValIntrate(valIntrateList);
	        if (!map.isEmpty()){
	        	result.setListvalIntRateQuote(( List<MstIntRateInfo>)map.get("ValIntRateQuote"));
	        }
	        //日志
	        result.setListValInterflog(returnResult.getValInterflog());
	        
		 return result;
		 
	 }
	 /**
	     * <一句话功能简述>
	     * <功能详细描述>
	     * @param valList 
	     * @return Map<String,Object>
	     * @see
	     * @since 1.0
	     */
	 public Map<String,Object> handleValIntrate(List<ValIntrate> valList){
		 Map<String, Object> resultMap = new HashMap<>();
		 List<MstIntRateInfo> listvalIntRateQuote=new ArrayList<MstIntRateInfo>();
		 
		 for(ValIntrate li:valList){
			 MstIntRateInfo  valIntRateQuote=this.getValIntRateQuote(li);
			 listvalIntRateQuote.add(valIntRateQuote);
		 }
		 resultMap.put("ValIntRateQuote", listvalIntRateQuote);
		 return resultMap;
	 }
	 
	 
	 
	 
	 /**
	     * <一句话功能简述>
	     * <功能详细描述>
	     * @param dto 
	     * @return InterestInfoRecCommonResult
	     * @see
	     * @since 1.0
	     */
	 
	 /*private InterestInfoRecCommonResult  valIntrateToResult(List<ValIntrate> valList){
		 
		 InterestInfoRecCommonResult  result=new InterestInfoRecCommonResult();
		 List<ValIntRateQuote> valIntRateQuoteList=new ArrayList<ValIntRateQuote>();
		 
		 //循环list
		 for(ValIntrate li : valList){
			 ValIntRateQuote valIntRateQuote=getValIntRateQuote(li);
			 valIntRateQuoteList.add(valIntRateQuote);
		 }
		 result.setListvalIntRateQuote(valIntRateQuoteList);
		
		 return result;
	 }*/
	 
	 
	 /**
	     * <一句话功能简述>
	     * <功能详细描述>
	     * @param dto
	     * @return ValIntRateQuote
	     * @see
	     * @since 1.0
	     */
	 private MstIntRateInfo getValIntRateQuote(ValIntrate valIntrate){
		 
		 MstIntRateInfo valIntRateQuote=new MstIntRateInfo();
		 //行情代码=利率代码
		 valIntRateQuote.setIntRateId(valIntrate.getIntRateId());
		 //生效日
		 valIntRateQuote.setEffDate(valIntrate.getEffDate());
		 //发布日
		 valIntRateQuote.setRelaeseDate(valIntrate.getRelaeseDate());
		 //更新时间
		 valIntRateQuote.setUpdateTime(valIntrate.getUpdateTime());
		 //创建人
		 valIntRateQuote.setCreateUser(valIntrate.getCreateUser());
		 //创建部门
		 valIntRateQuote.setCreateDept(valIntrate.getCreateDept());
		 //创建时间
		 valIntRateQuote.setCreateTime(valIntrate.getCreateTime());
		 //更新人
		 valIntRateQuote.setUpdateUser(valIntrate.getUpdateUser());
		 //利率
		 valIntRateQuote.setIntRate(valIntrate.getIntRate());
		 
		 return valIntRateQuote;
	 }

}
