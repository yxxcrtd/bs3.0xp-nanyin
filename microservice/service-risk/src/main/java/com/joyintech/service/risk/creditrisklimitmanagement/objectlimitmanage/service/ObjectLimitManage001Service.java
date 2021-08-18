package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dao.ObjectLimitManageMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto.QuotaCreditConfInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto.QuotaImpBaseDto;
import com.joyintech.tams.common.base.dao.CreditConfInfoMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.CreditTagInfoMapper;
import com.joyintech.tams.common.base.entity.CreditConfInfo;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;


/**
 * 项目额度管理
 * @author chenlei1
 * @version 1.0 2020年03月05日
 * @see ObjectLimitManage001Service
 * @since 1.0
 */
@Service
public class ObjectLimitManage001Service extends BaseService<ObjectLimitManageInfoDto> {
  /**
     * 平台日期
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    
	@Autowired
	private CreditTagInfoMapper creditTagInfoMapper;

	@Autowired
	private CreditLinesMapper creditLinesMapper;
	
	@Autowired
	private CreditConfInfoMapper creditConfInfoMapper;
	
	@Autowired
	private ObjectLimitManageMapper objectLimitManageMapper;

 
	@Autowired(required = false)
	private BaseGetBussinessId baseGetBussinessId;
    /**
     * 画面查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<ObjectLimitManageInfoDto> selectForPage(int page, int length,Map<String, Object> param) 
        throws Exception {
	     param.put("ptDate", iPlatformDate.getSysDate());
        return super.queryForPage(page, length, () -> objectLimitManageMapper.selectForPage(param));
    }

    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public Page<SelectOptionDto> getCustomerId(Map<String, Object> map, int page, int length) {
    	return this.queryForPageForSelectReturn(page, length,()->objectLimitManageMapper.getCustomerId(map));
    }
    
    
    public List<SuggestDto> getCustomerName(Map<String, Object> param) {
		return objectLimitManageMapper.getApporId(param);
	}
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    /*
    public void update(LimitManageInfoDto entity) {
    	if(null!=entity){
    		if(StringUtil.isNotEmpty(entity.getCreditAmt())){
        		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
    		//objectLimitManageMapper.updateByPrimaryKeySelective(entity);
    		if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
        		for(CreditLines dto:entity.getCreditQuoteItem()){
        			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
        				dto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
        	    	}
        			dto.setVdate(entity.getVdate());
        			dto.setMdate(entity.getMdate());
        			dto.setRemark(entity.getRemark());
        			dto.setPflsh(entity.getPflsh());
        			dto.setCustomerId(entity.getCustomerId());
        			dto.setParentCustomerId(entity.getCustomerId());
        			if(StringUtil.isNotEmpty(dto.getSeqno())){
        				//如果授信额度为空则删除
						if (!StringUtil.isNotEmpty(dto.getCreditAmt())){
							//objectLimitManageMapper.deleteByPrimaryKey(dto.getSeqno());
						}else {
							//objectLimitManageMapper.updateByPrimaryKeySelective(dto);
						}
        			}
        			else{
        				//dto.setSeqno(getQuotePrimaryKey());
        				dto.setHapCreditAmt(new BigDecimal(0));
        				dto.setCreditFlag(DictCodeList.是否_是.getValue());
            			//objectLimitManageMapper.insert(dto);
        			}
        		}
        		
        	}
    	}
    }*/
   
    public List<SuggestDto> getApporId(Map<String, Object> param) {
		return objectLimitManageMapper.getApporId(param);
	}
    
    
    
    
    
    /**
     * 企业额度导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     */
    public List<ExcelValidDto> importObjectData(List<List<String>> dataList,List<List<String>> tempList,String seqId,String impType) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("主体代码");
            headList.add("主体名称");
            headList.add("业务分类");
            headList.add("审批编号");
            headList.add("生效日");
            headList.add("终止期限");
            headList.add("项目投资额度");
            headList.add("最长投资期限");
            headList.add("审批意见");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
            	//标签管理 
                List<CreditTagInfo> tagEntities = new ArrayList<CreditTagInfo>();
                //额度分项集合 
                List<CreditConfInfo> configEntities = new ArrayList<CreditConfInfo>();
                
                List<ObjectLimitManageInfoDto> entities = new ArrayList<>(dataList.size());
                
                Map <String,String> box= new HashMap<String, String>();
                List<ExcelValidDto> checkResult = convertToOBEntity(dataList, tagEntities,configEntities,entities,box);
                if(checkResult.isEmpty()) {
                	insertCredit(tagEntities,configEntities,entities,seqId,impType);
                }
                return checkResult;
            }
        }
        return null;
    }
    
    
    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为QuotaImpTYDto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @param box
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToOBEntity(List<List<String>> dataList,
    		                                    List<CreditTagInfo> tagEntities,List<CreditConfInfo> configEntities,
                                                List<ObjectLimitManageInfoDto> entities, Map<String, String> box) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        //标签set集合
        Set<String> tagSet = new HashSet<String>();
        
        Map<String,CreditConfInfo> confMap = new HashMap<String,CreditConfInfo>();
        
        Map<String,Boolean> existMap = new HashMap<String,Boolean>();
    
     	// 验证消息
        List<String> message = new ArrayList<String>();
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    	//设置授信额度表
        for (int i = 0; i < dataList.size(); i++ ) {
        	   List<String> list = dataList.get(i);
        	      //主体代码校验
               String customerId = null;
               List<String> customerIds = objectLimitManageMapper.queryCustomerIdByCode(list.get(0));
               String customerIdError = checkCustomerId(list.get(0),customerIds);
               if (StringUtil.isNotEmpty(customerIdError)) {
                   message.add(customerIdError);
               }else {
                 customerId = customerIds.get(0);
               };
               
               //主体名称校验
               String customerNameError = checkcustomerName(list.get(1));
               if (StringUtil.isNotEmpty(customerNameError)) {
                   message.add(customerNameError);
               };
               
           	   //生效日校验
               String mdateError = checkMdate(list.get(4));
               if (StringUtil.isNotEmpty(mdateError)) {
                   message.add(mdateError);
               };
             
           	   //生效日校验
               String busiError = checkBusi(list.get(2));
               if (StringUtil.isNotEmpty(busiError)) {
                   message.add(busiError);
               };
               
               Date vdate = DateUtil.parse(list.get(4));
               
               
	            CreditTagInfo cti =new CreditTagInfo();
	       		cti.setCreditTagId(IDGenerator.generate());
	       	    String term = list.get(5);
	    	    String termError = checkTermTag(term,cti);
                if (StringUtil.isNotEmpty(termError)) {
                    message.add(termError);
                };
/*	       		cti.setEndTermValue();
	       		cti.setEndTermUnit(list[5]);*/
	       		cti.setApproId(list.get(3));
	       		cti.setBusiType(list.get(2));
	       		tagEntities.add(cti);  
	       		
	       		
	       		CreditConfInfo cci =new CreditConfInfo();
	    		cci.setCreditMstId(IDGenerator.generate());
	    		cci.setCreditTagId(cti.getCreditTagId());
	       	    String termc = list.get(7);
	    	    String termcError = checkTermConf(termc,cci);
                if (StringUtil.isNotEmpty(termcError)) {
                    message.add(termcError);
                };
	    		configEntities.add(cci);

	               
	           	ObjectLimitManageInfoDto cl =new ObjectLimitManageInfoDto();
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
                cl.setSeqno(baseGetBussinessId.buildBussinessCode(codeParameter));
	    		cl.setCreditMstId(cci.getCreditMstId());
	    		cl.setCustomerId(customerId);
	    		cl.setCustomerName(list.get(1));
	    		cl.setVdate(vdate);
	    		BigDecimal days=calDays(cti.getEndTermValue(),cti.getEndTermUnit(),vdate);
	        	Calendar c = Calendar.getInstance();
	    		c.setTime(vdate);
	    		c.add(Calendar.DATE,days.intValue());
	    		cl.setMdate(c.getTime());
	    		cl.setCreditFlag(DictCodeList.是否_是.getValue());
	    		cl.setHapCreditAmt(new BigDecimal(0));
	    	   	if(StringUtil.isNotEmpty(list.get(6))){
	    	   		cl.setCreditAmt(new BigDecimal(list.get(6)).multiply(new BigDecimal(100000000)));
	        	}
	            cl.setRemark(list.get(8));
		    	cl.setTimeUpdate(new Date());//数据日期
			    entities.add(cl);
			    
			    if (CollectionUtils.isNotEmpty(message)) {
	                checkResult.add(new ExcelValidDto(list.get(i), String.valueOf(i+2),
	                    StringUtil.joinString(message, GlobalConstant.COMMA)));
	            } else {
	            	existMap.put(customerId, true);
	            }
        }
      
        return checkResult;
    }
    
    

    public void insertCredit(List<CreditTagInfo> tagEntities,List<CreditConfInfo> configEntities,List<ObjectLimitManageInfoDto> entities,String seqId,String impType){
    
    	//额度标签管理
    	for(CreditTagInfo tagInfo : tagEntities){   
       		creditTagInfoMapper.insert(tagInfo);   

    	}
    	//额度分项表管理
    	for(CreditConfInfo  confInfoDto : configEntities){
    	
    		creditConfInfoMapper.insert(confInfoDto);
    	}

    	//授信额度管理
    	for(ObjectLimitManageInfoDto et : entities){
    	
		    objectLimitManageMapper.insert(et);
    	}
    	
    }
    
   
    
    public String checkCustomerId(String insitituteCode,List<String> customerIds){
    	
    	if(StringUtil.isEmpty(insitituteCode)){
    		return "主体名称为空";
    	}
    	
    	if(!Pattern.compile("^[0-9A-Z]+$").matcher(insitituteCode).matches()){
    		return "主体代码非数字或字母";
    	}
    	if(insitituteCode.contains(" ")){
    		return "主体代码含有空格";
    	}
    	
    	if(customerIds.size() == 0){
    		return "主体未定义";
    	}
    	if(customerIds.size() > 1){
    		return "同一组织机构代码查询出多个市场机构";
    	}
    	
    	return null;
    }
    
    
    public String checkcustomerName(String customerName){
    	
    	if(StringUtil.isEmpty(customerName)){
    		return "主体名称为空";
    	}
    	if(customerName.contains(" ")){
    		return "主体名称含有空格";
    	}
    	return null;
    }
    
    
    public String checkMdate(String mdate){
    	if(StringUtil.isEmpty(mdate)){
    		return "生效日为空";
    	}
    	
    	if(mdate.contains(" ")){
    		return "生效日含有空格";
    	}

    	if(!DateUtil.isDateStr(mdate,DateUtil.DEFAULT_DATE_FORMAT_EN)){
    		return "生效日期格式有问题";
    	}
    	
    	return null;
    }
    
    public String checkBusi(String busi){
    	if(StringUtil.isEmpty(busi)){
    		return "业务分类为空";
    	}
    	
    	if(busi.contains(" ")){
    		return "业务分类含有空格";
    	}
 	
		int count= objectLimitManageMapper.checkBusi(busi);
		if(count==0)
		{
			return "不是有效的业务分类";
		}
    	return null;
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
        	days=BigDecimal.valueOf(DateUtil.getBetweenDayNumber(pDate, c.getTime()));  		   		
    	}
        if(DictCodeListRsk.期限种类_年.getValue().equals(termUnit)){
        	int termInt=term.intValue();//取整数
        	Calendar c = Calendar.getInstance();
    		c.setTime(pDate);
    		c.add(Calendar.YEAR, termInt);//获取年份
    		//取小数
    		BigDecimal point=ArithmeticUtil.subtract(term, new BigDecimal(termInt)).multiply(new BigDecimal(12));
    		c.add(Calendar.MONTH, point.intValue());   		
            days=BigDecimal.valueOf(DateUtil.getBetweenDayNumber(pDate, c.getTime()));   
    	}    	
        return days;
    }
    
    
  public String checkTermConf(String term,CreditConfInfo confInfoDto){
    	
    	if(StringUtils.isBlank(term)){
    		return null;
    	}
    	Map<String,String> unitmap = new HashMap<String,String>();
    	unitmap.put("年","Y");
    	unitmap.put("月","M");
    	unitmap.put("周","W");
    	unitmap.put("日","D");
    	
    	Set<String> set = new HashSet<String>();
    	set.add("25");
    	set.add("5");
    	set.add("75");
    	set.add("");
    	String termUnit= term.substring(term.length()-1,term.length());
    	
    	if(!unitmap.containsKey(termUnit)){
    		return "期限单位不正确";
    	}
    	//设置期限单位
    	confInfoDto.setTermUnit(unitmap.get(termUnit));
    	
    	String termNum = term.substring(0,term.length()-1).trim();
    	
    	String point = StringUtils.substringAfter(".",termNum);
    	
    	if((!Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$").matcher(termNum).matches())
    			|| !(set.contains(point)&&"年".equals(termUnit))){
    		return "期限格式不正确";
    	}
    	
    	//设置期限
    	confInfoDto.setTerm(new BigDecimal(termNum));
		
    	return null;
    	
    }
  
   public String checkTermTag(String term,CreditTagInfo creditTagInfo){
  	
  	if(StringUtils.isBlank(term)){
  		return null;
  	}
  	Map<String,String> unitmap = new HashMap<String,String>();
  	unitmap.put("年","Y");
  	unitmap.put("月","M");
  	unitmap.put("周","W");
  	unitmap.put("日","D");
  	
  	Set<String> set = new HashSet<String>();
  	set.add("25");
  	set.add("5");
  	set.add("75");
  	set.add("");
  	String termUnit= term.substring(term.length()-1,term.length());
  	
  	if(!unitmap.containsKey(termUnit)){
  		return "期限单位不正确";
  	}
  	//设置期限单位
  	creditTagInfo.setEndTermUnit(unitmap.get(termUnit));
  	
  	String termNum = term.substring(0,term.length()-1).trim();
  	
  	String point = StringUtils.substringAfter(".",termNum);
  	
  	if((!Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$").matcher(termNum).matches())
  			|| !(set.contains(point)&&"年".equals(termUnit))){
  		return "期限格式不正确";
  	}
  	
  	//设置期限
  	creditTagInfo.setEndTermValue(new BigDecimal(termNum));
		
  	return null;
  	
  }
}
