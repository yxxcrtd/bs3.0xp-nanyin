package com.joyintech.tams.report.system.proregistcodemanage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdresistCodeMapper;
import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage002Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author tangliang
 * @version 1.0 2017年9月20日
 * @see ProRegistCodeManage001Service
 * @since 1.0
 */
@Service
public class ProRegistCodeManage003Service extends CommonExportService<ProRegistCodeManage002Dto> implements BaseProcessService<ProRegistCodeManage002Dto>{

    /**
     * ProRegistCodeManage001Mapper
     */
    @Autowired
    private ProRegistCodeManage001Mapper proRegistCodeManage001Mapper;
    /**
     * ChbPrdresistCodeMapper
     */
    @Autowired
    private ChbPrdresistCodeMapper chbPrdresistCodeMapper;

    /**
     * InvestorsHoldInfo001Service
     */
    @Autowired
    private InvestorsHoldInfo001Service investorsHoldInfo001Service;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 实现工作流doComplete方法
     */
    public void doComplete(ProRegistCodeManage002Dto entity, OperationType type) {
    	    entity.setOperateType(type.toString());
    	    ChbPrdresistCode record = new ChbPrdresistCode();
    	    record.setProdid(entity.getProdId());
    	    record.setProdname(entity.getProdName());
    	    record.setRegCode(entity.getRegCode());    	    
    	    record.setVdate(entity.getVdate());
    	    record.setMdate(entity.getMdate());
    	    if(StringUtil.equals(entity.getOperateMode(), "封闭式净值型")) {   	    	
    	    	record.setOperateMode("01");
    	    }else if(StringUtil.equals(entity.getOperateMode(), "封闭式非净值型")) {
    	    	record.setOperateMode("02");
    	    }else if(StringUtil.equals(entity.getOperateMode(), "开放式净值型")) {
    	    	record.setOperateMode("03");
    	    }else if(StringUtil.equals(entity.getOperateMode(), "开放式非净值型")) {
    	    	record.setOperateMode("04");
    	    }
    	    record.setOrgCode(CommonUtil.getOrgCode());
        	record.setDeptCode(CommonUtil.getDeptCode());
    		if (StringUtil.equals(entity.getOperateType(), "NEW")) {
    			record.setCreateUser(SecurityUtil.getLoginUser().getUserName());
    			record.setCreateTime(new Date());
    			//
    			 record.setFlag("2");
    			 record.setReg_code(record.getRegCode());
    			 //产品要素维护管理中产品代码不存在 存在则更新数据              
                ChbPrdresistCode dto = chbPrdresistCodeMapper.selectByPrimaryKey(record);
                if (dto != null){
                    chbPrdresistCodeMapper.updateByPrimaryKeySelective(record);
                }
                else{
                    //不存在插入新数据
                    chbPrdresistCodeMapper.insertSelective(record);
                }
    			//
    			
            }
    		else if(StringUtil.equals(entity.getOperateType(), "MODIFY")) {
    			record.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
    			record.setUpdateTime(new Date());
    			chbPrdresistCodeMapper.updateByPrimaryKeySelective(record);
    		}
    		
    }
  
    public ProRegistCodeManage002Dto selectInfo(String prodId) {
    	String svrSystemType=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PRDRESIST_CODE);
    	if(svrSystemType!=null&&svrSystemType.equals("01")){
    		return proRegistCodeManage001Mapper.selectInfo(prodId);
		}else{
			return proRegistCodeManage001Mapper.selectInfoN(prodId);
		}
    }
    
    @SuppressWarnings("unchecked")
   	public Map<String,Object> selectId(Map<String, Object> pageParam) {
           return proRegistCodeManage001Mapper.selectId(pageParam);
       }
    
    public Map<String,Object> selectCode(Map<String, Object> pageParam) {
           return proRegistCodeManage001Mapper.selectCode(pageParam);
       }
    
    
    public AjaxResult checkInsertData(String prodId,String regCode ) {
    	AjaxResult webResult = new AjaxResult();    
    	ChbPrdresistCode record = new ChbPrdresistCode();
    	record.setProdid(prodId);
    	record.setFlag("2");
		record.setReg_code(regCode);
		ChbPrdresistCode dto = chbPrdresistCodeMapper.selectByPrimaryKey(record);
		if(dto != null) {			
                    webResult.setMessage(
                        "产品登记编码已维护，请确认是否覆盖？",
                        MessageType.确认消息);
                }
               /* else {
                    // 防止在进行导入前check时进行了两次的弹框
                    webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
                }*/            
           /* else{
             // 防止在进行导入前check时进行了两次的弹框
             webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }*/
        return webResult;
    }
    
    public AjaxResult checkInsertData1(String regCode ) {
    	AjaxResult webResult = new AjaxResult();    
    	if(!Pattern.compile("^[0-9a-zA-Z]{6}[0-9]{2}[ABC]?[0-9]{6}$").matcher(regCode).matches()){
    		 webResult.setMessage(
                     "产品登记编码格式不符合规范，请确认是否继续操作？",
                     MessageType.确认消息);
        }            
        return webResult;
    }
    
}