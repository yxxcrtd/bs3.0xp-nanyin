/*
 * 文件名：VersionSwitchService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：曹锦
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.versionswitch.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.service.basicdata.versionswitch.dao.VersionSwitchMapper;

/**
 * 版本切换service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author caojin
 * @version 1.0 2020年5月23日
 * @see VersionSwitchService
 * @since 1.0
 */
@Service("backVersionSwitchService")
@Transactional(rollbackFor = Exception.class)
public class VersionSwitchService {

    @Autowired
    private VersionSwitchMapper mstYieldCurveInfo001Mapper;

    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public String selectVersionType() throws Exception {
    	return mstYieldCurveInfo001Mapper.selectVersionType();
    }
    
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public void updateVersionType(String versionType) throws Exception {
    	Map<String, Object> pageParam = new HashMap<String, Object>();
    	String type1 = "";
    	String type2 = "";
    	String type3 = "";
    	if(versionType.equals("01")){
    		type1 = "01";
    		type2 = "02";
    		type3 = "03";
    	}else if(versionType.equals("02")){
    		type1 = "02";
    		type2 = "01";
    		type3 = "03";
    	}else if(versionType.equals("03")){
    		type1 = "03";
    		type2 = "01";
    		type3 = "02";
    	}
    	pageParam.put("type1", type1);
    	pageParam.put("type2", type2);
    	pageParam.put("type3", type3);
    	mstYieldCurveInfo001Mapper.updateVersionType1(pageParam);
    	mstYieldCurveInfo001Mapper.updateVersionType2(pageParam);
    }
   
}
