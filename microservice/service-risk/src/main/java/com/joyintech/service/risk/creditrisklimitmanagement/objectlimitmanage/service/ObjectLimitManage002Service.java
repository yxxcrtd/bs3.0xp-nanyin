package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dao.ObjectLimitManageMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.tams.common.base.dao.CreditConfInfoMapper;
import com.joyintech.tams.common.base.dao.CreditTagInfoMapper;
import com.joyintech.tams.common.base.entity.CreditConfInfo;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;


/**
 * 项目额度管理
 * @author chenlei1
 * @version 1.0 2020年03月05日
 * @see ObjectLimitManage002Service
 * @since 1.0
 */
@Service
public class ObjectLimitManage002Service extends BaseService{
	
	@Autowired
	private CreditTagInfoMapper creditTagInfoMapper;

/*	@Autowired
	private CreditLinesMapper creditLinesMapper;*/
	
	@Autowired
	private CreditConfInfoMapper creditConfInfoMapper;
	
	@Autowired
	private ObjectLimitManageMapper objectLimitManageMapper;
	
	@Autowired(required = false)
	private BaseGetBussinessId baseGetBussinessId;
   
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(ObjectLimitManageInfoDto entity) {
    	if(null!=entity){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    		CreditTagInfo cti =new CreditTagInfo();
    		cti.setCreditTagId(IDGenerator.generate());
    		cti.setEndTermValue(entity.getEndTermValue());
    		cti.setEndTermUnit(entity.getEndTermUnit());
    		cti.setApproId(entity.getApproId());
    		cti.setBusiType(entity.getBusiType());
    		creditTagInfoMapper.insert(cti);
    		
    		CreditConfInfo cci =new CreditConfInfo();
    		cci.setCreditMstId(IDGenerator.generate());
    		cci.setCreditTagId(cti.getCreditTagId());
    		cci.setTerm(entity.getTerm());
    		cci.setTermUnit(entity.getTermUnit());
    		creditConfInfoMapper.insert(cci);
    		
    		ObjectLimitManageInfoDto cl =new ObjectLimitManageInfoDto();
    		cl.setSeqno(entity.getSeqno());
    		cl.setCreditMstId(cci.getCreditMstId());
    		cl.setCustomerId(entity.getCustomerId());
    		cl.setVdate(entity.getVdate());
    		BigDecimal days=calDays(entity.getEndTermValue(),entity.getEndTermUnit(),entity.getVdate());
        	Calendar c = Calendar.getInstance();
    		c.setTime(entity.getVdate());
    		c.add(Calendar.DATE,days.intValue());
    		cl.setMdate(c.getTime());
    		cl.setCreditFlag(DictCodeList.是否_是.getValue());
    		cl.setHapCreditAmt(new BigDecimal(0));
    	   	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    	   		cl.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
            cl.setRemark(entity.getRemark());
		    try{
		    	cl.setTimeUpdate(sdf.parse(entity.getUpTime()));//数据日期
            }catch(ParseException e){
            	e.printStackTrace();
            }
		    objectLimitManageMapper.insert(cl);
    	}
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(ObjectLimitManageInfoDto entity) {
    	if(null!=entity){
    		CreditTagInfo cti =new CreditTagInfo();
    		cti.setEndTermValue(entity.getEndTermValue());
    		cti.setEndTermUnit(entity.getEndTermUnit());
    		cti.setApproId(entity.getApproId());
    		cti.setBusiType(entity.getBusiType());
    		creditTagInfoMapper.updateByPrimaryKeySelective(cti);
    		
    		CreditConfInfo cci =new CreditConfInfo();
    		cci.setCreditMstId(IDGenerator.generate());
    		cci.setCreditTagId(cti.getCreditTagId());
    		cci.setTerm(entity.getTerm());
    		cci.setTermUnit(entity.getTermUnit());
    		creditConfInfoMapper.updateByPrimaryKeySelective(cci);
    		
    		ObjectLimitManageInfoDto cl =new ObjectLimitManageInfoDto();
    		cl.setCreditMstId(entity.getCreditMstId());
    		cl.setCreditMstId(entity.getCreditMstId());
    		cl.setCustomerId(entity.getCustomerId());
    		cl.setVdate(entity.getVdate());
    		cl.setMdate(entity.getMdate());
    	   	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    	   		cl.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
            cl.setRemark(entity.getRemark());
            objectLimitManageMapper.updateByPrimaryKeySelective(cl);
    	}
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
    public int delete(ObjectLimitManageInfoDto dto) {
    	Map<String, Object> param=new HashMap<String, Object>();
    	if(null!=dto){
    		CreditTagInfo cti =new CreditTagInfo();
    		//cti.setCreditTagId(IDGenerator.generate());
    		creditTagInfoMapper.deleteByPrimaryKey(dto.getCreditTagId());
    		
    		CreditConfInfo cci =new CreditConfInfo();
    		//cci.setCreditMstId(IDGenerator.generate());
    		//cci.setCreditTagId(cti.getCreditTagId());
    		creditConfInfoMapper.deleteByPrimaryKey(dto.getCreditMstId());
    		
    		ObjectLimitManageInfoDto cl =new ObjectLimitManageInfoDto();
    		//cl.setSeqno(entity.getSeqno());
    		//cl.setCreditMstId(cci.getCreditMstId());
    		//cl.setCreditMstId(cci.getCreditMstId());
    		objectLimitManageMapper.deleteByPrimaryKey(dto.getSeqno());
    	}
    	 return 0;
    }

    /**
     * 查询额度信息
     * @param map
     */
    public ObjectLimitManageInfoDto queryCeLineBySeqno(Map<String, Object> map ) {
    	String seqno=(String) map.get("seqno");
    	ObjectLimitManageInfoDto retCre = objectLimitManageMapper.selectByPrimaryKey(seqno);
    	return retCre;
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

}
