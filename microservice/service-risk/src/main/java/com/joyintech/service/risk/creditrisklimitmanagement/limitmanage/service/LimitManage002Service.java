package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dao.CreditItemInfoDaoMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dao.LimitManageMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfoDto;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.CreditTagInfoMapper;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;


/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManage002Service
 * @since 1.0
 */
@Service("backLimitManage002Service")
public class LimitManage002Service extends BaseService<CreditItemInfoDto> {
	
	@Autowired
	private LimitManageMapper limitManageMapper;
	
	@Autowired
	private CreditLinesMapper creditLinesMapper;
	
	@Autowired(required = false)
	private BaseGetBussinessId baseGetBussinessId;
	
	@Autowired
	private CreditItemInfoDaoMapper creditItemInfoDaoMapper;
	
	@Autowired
	private CreditTagInfoMapper creditTagInfoMapper;
	
	  /**
     * 平台日期
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
	
    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SelectOptionDto> getCustomerId(Map<String, Object> map) {
        return limitManageMapper.getCustomerId(map);
    }
    
    @SuppressWarnings("rawtypes")
	public List<TreeNode> getAllCeOrgQuote(String rank) {
        return limitManageMapper.getAllCeOrgQuote(rank);
    }
    
    @SuppressWarnings("rawtypes")
   	public List<TreeNode> getParentCeOrgQuote(String rank) {
           return limitManageMapper.getParentCeOrgQuote(rank);
       }
    
/*    public Page<CreditConfInfoDto> getTreeCeOrgQuote(int page, int length,Map<String, Object> param) 
            throws Exception {
            return super.queryForPage(page, length, () -> limitManageMapper.getTreeCeOrgQuote(param));
        }*/
    
    /**
     * 查询额度信息
     * @param map
     */
    public LimitManageInfoDto queryCeOrgBySeqno(Map<String, Object> map ) {
    	LimitManageInfoDto retCre = limitManageMapper.queryCeOrgBySeqno(map);
/*    	List<CreditLines> retMxList=limitManageMapper.queryCeOrgByPflshAndCustomId(retCre.getPflsh(),retCre.getCustomerId());
    	retCre.setCreditLabelItem(retMxList);*/
    	return retCre;
    }
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param name
     */
    public int searchRepeatByVmDate(Map<String, Object> param ) {
    		return limitManageMapper.searchRepeatByVmDate(param);
    }
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param name
     */
    public int searchRepeatByVmDateArea(Map<String, Object> param ) {
    		return limitManageMapper.searchRepeatByVmDateArea(param);
    }
    /**
     * 新建编辑场合时查询批复号是否重复
     * @param name
     */
    public int searchRepeatByPflsh(String pflsh,String seqno,String creditMstId ) {
    		return limitManageMapper.searchRepeatByPflsh(pflsh,seqno,creditMstId);
    }
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(LimitManageInfoDto entity){
    	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
    	}
    	entity.setHapCreditAmt(new BigDecimal(0));
    	entity.setCreditFlag(DictCodeList.是否_是.getValue());
    	entity.setCreditType("04");
    	
/*        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_额度管理);
        String pfh=baseGetBussinessId.buildBussinessCode(codeParameter);*/


    	if(StringUtil.isNotEmpty(entity.getCreditLabelItem())){
    		
		  for(CreditItemInfoDto dto:entity.getCreditLabelItem()){			  
			  BigDecimal days=calDays(dto.getTerm(),dto.getTermUnit(),iPlatformDate.getSysDate());
			  dto.setDays(days);
		  }
		  calCreditAmtAdd(entity.getCreditLabelItem());
		  
		  for(CreditItemInfoDto dto:entity.getCreditLabelItem()){
			String se=IDGenerator.generate();
			LimitManageInfoDto cldto=new LimitManageInfoDto();
			//CreditItemInfoDto  cldto=new CreditItemInfoDto();
			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
				cldto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
	    	}
		    try{
                cldto.setTimeUpdate(sdf.parse(dto.getUpTime()));//数据日期
    			cldto.setVdate(sdfYMD.parse(dto.getUpTime()));
            }catch(ParseException e){
            	e.printStackTrace();
            }

			
			cldto.setMdate(dto.getMdate());
			cldto.setRemark(dto.getRemark());
			//cldto.setPflsh(entity.getPflsh());
			cldto.setHapCreditAmt(new BigDecimal(0));
			cldto.setCustomerId(entity.getCustomerId());
			cldto.setCreditFlag(DictCodeList.是否_是.getValue());
			cldto.setParentCustomerId(entity.getCustomerId());
			cldto.setSeqno(dto.getCreditMstId());
			cldto.setCreditMstId(se);
			cldto.setTerm(dto.getTerm());
			cldto.setCcy(entity.getCcy());
			cldto.setPflsh(entity.getPflsh());
			cldto.setCreditAmtAdd(dto.getCreditAmtAdd());

			//cldto.setCreditType("01");
			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
				cldto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
	    	}
			cldto.setCreditType("04");
			cldto.setUpTime(dto.getUpTime());
			limitManageMapper.insert(cldto);	
/*		    try{
                cldto.setTimeUpdate(sdf.parse(dto.getUpTime()));//数据日期
            }catch(ParseException e){
            	e.printStackTrace();
            }
			limitManageMapper.update(cldto);	*/

			
			dto.setCreditMstId(se);

			CreditTagInfo cti=new CreditTagInfo();
			cti.setCreditType(dto.getCreditType());
			cti.setCreditUsingType("04");
			cti.setCreditTagId(IDGenerator.generate());
			cti.setRiskRatio(dto.getRiskRatio());
            cti.setOrgType(dto.getOrgType());
			
			dto.setCreditTagId(cti.getCreditTagId());
			//dto.setTermUnit(dto.getTermUnit());
			creditItemInfoDaoMapper.insert(dto);
			
			creditTagInfoMapper.insert(cti);
		  }		
	   }
    	//creditLinesMapper.insert(entity);
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(LimitManageInfoDto entity) {
    	if(null!=entity){
    		if(StringUtil.isNotEmpty(entity.getCreditAmt())){
        		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
    		//creditLinesMapper.updateByPrimaryKeySelective(entity);
    		
    		Map<String,Object> smap=new HashMap<String,Object>();
    		Map<String,Object> pmap=new HashMap<String,Object>();
    		pmap.put("customerId", entity.getCustomerId());
    		pmap.put("pflsh", entity.getPflsh());
    		pmap.put("ptDate", iPlatformDate.getSysDate());

    		List<CreditItemInfoDto> rs=creditItemInfoDaoMapper.getCreditConfig(pmap);
    		
    		
    		if(StringUtil.isNotEmpty(entity.getCreditLabelItem())){
    			  for(CreditItemInfoDto dto:entity.getCreditLabelItem()){			  
    				  BigDecimal days=calDays(dto.getTerm(),dto.getTermUnit(),iPlatformDate.getSysDate());
    				  dto.setDays(days);
    			  }
    			  calCreditAmtAdd(entity.getCreditLabelItem());
    			  
        		for(CreditItemInfoDto dto:entity.getCreditLabelItem()){
        			LimitManageInfoDto cldto=new LimitManageInfoDto();
        			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
        				cldto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
        	    	}
        			
        		    try{
                        cldto.setTimeUpdate(sdf.parse(dto.getUpTime()));//数据日期
            			cldto.setVdate(sdfYMD.parse(dto.getUpTime()));
                    }catch(ParseException e){
                    	e.printStackTrace();
                    }

        			cldto.setMdate(dto.getMdate());
        			//cldto.setVdate(entity.getVdate());
        			//cldto.setMdate(entity.getMdate());
        			cldto.setRemark(dto.getRemark());
        			cldto.setPflsh(entity.getPflsh());
        			cldto.setCustomerId(entity.getCustomerId());
        			cldto.setParentCustomerId(entity.getCustomerId());
        			cldto.setSeqno(dto.getSeqno());
        			cldto.setCreditMstId(dto.getCreditMstId());
        			cldto.setCreditType("04");
        			cldto.setUpTime(dto.getUpTime());
        			cldto.setTerm(dto.getTerm());
        			cldto.setCcy(entity.getCcy());
        			cldto.setCreditAmtAdd(dto.getCreditAmtAdd());
        			
        			CreditTagInfo cti=new CreditTagInfo();
        			cti.setCreditType(dto.getCreditType());
        			cti.setCreditUsingType("04");
        			cti.setCreditTagId(IDGenerator.generate());
        			cti.setRiskRatio(dto.getRiskRatio());
                    cti.setOrgType(dto.getOrgType());       		       			
        			creditTagInfoMapper.insert(cti);
        			
        			
        			if(StringUtil.isNotEmpty(dto.getSeqno())){
        				//如果授信额度为空则删除
						if (!StringUtil.isNotEmpty(dto.getCreditAmt())){
							creditLinesMapper.deleteByPrimaryKey(dto.getSeqno());
						}else {
							creditLinesMapper.updateByPrimaryKeySelective(cldto);
						}
						creditItemInfoDaoMapper.updateByPrimaryKey(dto);
        			}
        			else{
        				cldto.setSeqno(getQuotePrimaryKey());
        				cldto.setHapCreditAmt(new BigDecimal(0));
        				cldto.setCreditFlag(DictCodeList.是否_是.getValue());
        				cldto.setCreditMstId(IDGenerator.generate());
            			creditLinesMapper.insert(cldto);
             			dto.setCreditMstId(cldto.getCreditMstId());
            			
            			dto.setCreditTagId(cti.getCreditTagId());
            			dto.setTermUnit(dto.getTermUnit());
             			creditItemInfoDaoMapper.insert(dto);
        			}        			
        			smap.put(cldto.getSeqno(), cldto);

        		}

        		rs.forEach(et->{
        			if(!smap.containsKey(et.getSeqno())){
        				creditItemInfoDaoMapper.deleteByPrimaryKey(et.getCreditMstId());
        				creditLinesMapper.deleteByPrimaryKey(et.getSeqno());
        			}        			
                 });
        	}
    	}
    }
    
    
    public BigDecimal  calDays(BigDecimal term,String termUnit,Date pDate){ 
    	BigDecimal days=BigDecimal.ZERO;
		if(DictCodeListRsk.期限种类_日.getValue().equals(termUnit)){
			days=term;
    	}
        if(DictCodeListRsk.期限种类_周.getValue().equals(termUnit)){
        	days=ArithmeticUtil.multiply(term, new BigDecimal(7));   		
    	}
        if(DictCodeListRsk.期限种类_月.getValue().equals(termUnit)){
        	Calendar c = Calendar.getInstance();
    		c.setTime(pDate);
    		c.add(Calendar.MONTH, term.intValue());
        	days=BigDecimal.valueOf(getDiffDays(pDate, c.getTime()));
    	}
        if(DictCodeListRsk.期限种类_年.getValue().equals(termUnit)){
        	int termInt=term.intValue();//取整数
        	Calendar c = Calendar.getInstance();
    		c.setTime(pDate);
    		c.add(Calendar.YEAR, termInt);//获取年份
    		//取小数
    		BigDecimal point=ArithmeticUtil.subtract(term, new BigDecimal(termInt)).multiply(new BigDecimal(12));
    		c.add(Calendar.MONTH, point.intValue());   		
            days=BigDecimal.valueOf(getDiffDays(pDate, c.getTime()));
    	}    	
        return days;
    }
    
    public static List<CreditItemInfoDto> calCreditAmtAdd(List<CreditItemInfoDto> dtolist){ 
    	dtolist.sort((o1, o2) -> 
    	    {
	    	if (o1.getCreditType().equals(o2.getCreditType())) {  
	            return o2.getDays().compareTo(o1.getDays());  
	        } else {  
	            return o1.getCreditType().compareTo(o2.getCreditType());  
	        }    			
    	 });
    
  /*  	dtolist.sort(Comparator.comparing(CreditItemInfoDto::getCreditType)
                 .thenComparing(CreditItemInfoDto::getTerm).reversed());*/
    	//Map<String,Object> ct=new HashMap<String,Object>();
    	if(StringUtil.isNotEmpty(dtolist)){
    		/*dtolist.forEach(et->{
    			 //ct.put(et.getCreditType(), et);               
  		         System.out.println("et-getCreditType="+et.getCreditType()+"--et-getTerm="+et.getTerm());	
    		});*/
    		String creditType="";
    		BigDecimal addAmt=BigDecimal.ZERO;
			for(CreditItemInfoDto et:dtolist){
				
				if("".equals(creditType)){
					et.setCreditAmtAdd(et.getCreditAmt());
					addAmt=et.getCreditAmt();
				}
			    if(!"".equals(creditType) && creditType.equals(et.getCreditType()) ){
			    	addAmt=addAmt.add(et.getCreditAmt());
			    	et.setCreditAmtAdd(addAmt);
			    }	
                if(!"".equals(creditType) && !creditType.equals(et.getCreditType()) ){
					addAmt=et.getCreditAmt();
					et.setCreditAmtAdd(et.getCreditAmt());
			    }	
			    creditType=et.getCreditType();
/*System.out.println("et-getCreditType="+et.getCreditType()+"--et-getTerm="+et.getTerm()+"--et-getCreditAmtAdd="+et.getCreditAmtAdd());	
*/			}
    		
    	}
    	return dtolist;
    }
    
    public static void main(String[] args) {
    	List<CreditItemInfoDto> dtolist =new ArrayList<CreditItemInfoDto>();
    	for(int i=0;i<10;i++){
    		CreditItemInfoDto e=new CreditItemInfoDto();
    		e.setCreditType(i+"");
    		e.setTerm(new BigDecimal(100));
    		e.setCreditAmt(new BigDecimal(100));
    		dtolist.add(e);

    		CreditItemInfoDto e1=new CreditItemInfoDto();
    		e1.setCreditType(i+"");
    		e1.setTerm(new BigDecimal(200));
    		e1.setCreditAmt(new BigDecimal(200));
    		dtolist.add(e1);
    	}
    	calCreditAmtAdd(dtolist);
/*
        BigDecimal term=new BigDecimal(2.25);
    	int termInt=term.intValue();//取整数
    	Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, termInt);//获取年份
		//取小数
		BigDecimal point=ArithmeticUtil.subtract(term, new BigDecimal(termInt)).multiply(new BigDecimal(12));
		c.add(Calendar.MONTH, point.intValue()); 
		BigDecimal days1=BigDecimal.valueOf(DateUtil.getDiffDays(new Date(), c.getTime()));  */		   		
    }
    
    private String getQuotePrimaryKey(){
		if(null != baseGetBussinessId) {
			 BussinessCodeParameter codeParameter = new BussinessCodeParameter();
	            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
			return baseGetBussinessId.buildBussinessCode(codeParameter);
		}
		return IDGenerator.generate();
	}
    /**
     * 删除机构授信
     * @param name
     */
    public int delete(LimitManageInfoDto dto) {
    	Map<String, Object> param=new HashMap<String, Object>();
    	param.put("seqno", dto.getSeqno());
    	LimitManageInfoDto retCre = limitManageMapper.queryCeOrgBySeqno(param);
    	return limitManageMapper.deleteOrgMx(retCre.getPflsh());
    }

	/**
	 * 如果当前数据额度已被占用则不允许操作
	 * by nixiukuan 20191024
	 * @param param
	 */
	public Boolean searchIsUse(Map<String, Object> param) {
		int count= limitManageMapper.searchIsUse(param);
		if(count>0)
		{
			return  true;
		}
		return  false;
	}
	
	
    public String getOrgInfo(String customerId) {
    	return limitManageMapper.getOrgInfo(customerId);
    }

    /**
     * 查询额度信息
     * @param map
     */
    public CreditItemInfoDto queryCreditItemInfoBySeqno(Map<String, Object> map ) {
    	CreditItemInfoDto retCre = creditItemInfoDaoMapper.queryCreditItemInfoBySeqno(map);

    	return retCre;
    }

	/**
	 *
	 * @param startDate	开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	private int getDiffDays(Date startDate, Date endDate){
		long diff=startDate.getTime()-endDate.getTime();
		long diffDays=diff/86400000;
		return Math.abs((int)diffDays);
	}
}
