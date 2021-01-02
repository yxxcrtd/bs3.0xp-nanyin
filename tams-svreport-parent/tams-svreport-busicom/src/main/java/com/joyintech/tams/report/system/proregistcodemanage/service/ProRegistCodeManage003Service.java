package com.joyintech.tams.report.system.proregistcodemanage.service;

import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdresistCodeMapper;
import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage002Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
    			chbPrdresistCodeMapper.insertSelective(record);
            }
    		else if(StringUtil.equals(entity.getOperateType(), "MODIFY")) {
    			record.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
    			record.setUpdateTime(new Date());
    			chbPrdresistCodeMapper.updateByPrimaryKeySelective(record);
    		}
    		
    }
  
    public ProRegistCodeManage002Dto selectInfo(String prodId) {
        return proRegistCodeManage001Mapper.selectInfo(prodId);
    }
    
    @SuppressWarnings("unchecked")
   	public Map<String,Object> selectId(Map<String, Object> pageParam) {
           return proRegistCodeManage001Mapper.selectId(pageParam);
       }
    
    public Map<String,Object> selectCode(Map<String, Object> pageParam) {
           return proRegistCodeManage001Mapper.selectCode(pageParam);
       }
    
}