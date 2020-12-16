package com.joyintech.tams.report.system.proregistcodemanage.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdresistCodeMapper;
import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.common.base.entity.ChbPrdresistCodeKey;
import com.joyintech.tams.report.common.TxtParseUtil;
import com.joyintech.tams.report.common.XmlParseUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage002Dto;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
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
    	    record.setOperateMode(entity.getOperateMode());
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
    
    @SuppressWarnings("unchecked")
	public Map<String,Object> selectId(Map<String, Object> pageParam) {
        return proRegistCodeManage001Mapper.selectId(pageParam);
    }
    
    @SuppressWarnings("unchecked")
   	public Map<String,Object> selectCode(Map<String, Object> pageParam) {
           return proRegistCodeManage001Mapper.selectCode(pageParam);
       }
    
}