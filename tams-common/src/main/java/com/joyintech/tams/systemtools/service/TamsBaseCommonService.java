/*
 * 文件名：BaseCommonService.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年11月16日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.systemtools.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.systemtools.dao.TamsBaseMapper;

/**
 * 共通Service
 *
 * @author qiangchengxi
 * @version 1.0 2016年11月16日
 * @see TamsBaseCommonService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class TamsBaseCommonService extends BaseService {
    /**
     * BaseCommonMapper
     */
    @Autowired
    private TamsBaseMapper tamsBaseMapper;

    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     */
    public String getSysDateString() {
        return DateUtil.format(/*baseCommonMapper.getNowSysDate()*/DateUtil.now());
    }

    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     */
    public Date getSysDate() {
        return DateUtil.now();
    }

    /**
     * 查询机构下的所有部门代码
     * @param institutionCode 机构代码
     * @return 部门代码列表
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<String> selectDeptCode(String institutionCode) {
        return tamsBaseMapper.selectDeptCode(institutionCode);
    }
    
	/**
	 * 获取登录用户的机构类型
	 * @return
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public String getInsitutionType() {
    	String institutionCode = SecurityUtil.getLoginUser().getOrgCode();
    	if(StringUtil.isNotEmpty(institutionCode)){
    		return tamsBaseMapper.selectInsitutionType(institutionCode);
    	}
    	return null;
    }

    /**
     * 查询机构类型
     * @param institutionCode 机构代码
     * @return 机构类型
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public String getInstitutionType(String institutionCode) {
        return tamsBaseMapper.getInstitutionType(institutionCode);
    }
    
    
    /**
     * 查询机构类型
     * @param institutionCode 机构代码
     * @return 机构类型
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public String selectBankCode(String institutionCode) {
        return tamsBaseMapper.selectBankCode(institutionCode);
    }
    
    
    /**
	 * 获取登录用户的机构类型--- 微服务版本
	 * @return
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public String getInsitutionType_ms() {
    	
    	String userName = LoginUserUtil.getUserName();
    	
    	if(StringUtil.isNotEmpty(userName)){
    		return tamsBaseMapper.getInsitutionType_ms(userName);
    	}
    	
    	return null;
    }
    
    /**
	 * 获取登录用户的机构代码--- 微服务版本
	 * @return
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public String getInsitutionCode_ms() {
    	
    	String userName = LoginUserUtil.getUserName();
    	
    	if(StringUtil.isNotEmpty(userName)){
    		return tamsBaseMapper.getInsitutionCode_ms(userName);
    	}
    	
    	return null;
    }
    
    
    /**
	 * 根据用户获取所属机构编码-中债批处理
	 * @return
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public String getInsitutionCodeByUserName(String  userName) {

    	if(StringUtil.isNotEmpty(userName)){
    		return tamsBaseMapper.getInsitutionCode_ms(userName);
    	}
    	
    	return null;
    }
    
    
    /**
	 * 根据用户获取所属机构类型-中债批处理
	 * @return
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public String getInsitutionTypeByUserName(String userName) {
    	if(StringUtil.isNotEmpty(userName)){
    		return tamsBaseMapper.getInsitutionType_ms(userName);
    	}
    	
    	return null;
    }
    
    
       @Transactional(propagation=Propagation.REQUIRES_NEW)
       @DataSource(DataSourceType.OTHER)
       public List<String> getParentInstitution(String institutionCode) {
       	
	       if(StringUtil.isNotEmpty(institutionCode)){
	      		return tamsBaseMapper.getParentInstitution(institutionCode);
	       }
	       return null;
	       
       }

        @Transactional(propagation=Propagation.REQUIRES_NEW)
        @DataSource(DataSourceType.OTHER)
        public String getYHGeneralBankCode() {
            String bankCode =  tamsBaseMapper.getYHGeneralBankCode();
            return bankCode == null ? "" : bankCode ;
        }

        @Transactional(propagation=Propagation.REQUIRES_NEW)
        @DataSource(DataSourceType.OTHER)
        public String getCreateInstByID(String businessId,String dataType) {
            return tamsBaseMapper.getCreateInstByID(businessId,dataType);
        }

}
