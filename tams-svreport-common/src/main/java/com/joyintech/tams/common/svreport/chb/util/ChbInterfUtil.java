package com.joyintech.tams.common.svreport.chb.util;

import java.util.Date;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.dao.ChbInterfConfigMapper;
import com.joyintech.tams.systemtools.util.CommonUtil;

public class ChbInterfUtil {

    /**
     * 
     * 
     * @param interfId 接口代码
     * @param newUpdateTime 最近更新时间
     * @return  List<Date>
     * @see
     * @since 1.0
     */
    public static void updateLastupdatetime(String interfId, Date newUpdateTime) {
    	ChbInterfConfigMapper chbInterfUpdatetimeMapper =  SpringContextUtil.getBean(
        		ChbInterfConfigMapper.class);
        //节假日通用方法

         chbInterfUpdatetimeMapper.updateLastupdatetime(interfId, newUpdateTime);
    }

    /**
     * 
     * 
     * @param interfId 接口代码
     * @param newUpdateTime 最近更新时间
     * @return  List<Date>
     * @see
     * @since 1.0
     */
    public static Date queryUpdateTime(String interfId) {
    	ChbInterfConfigMapper chbInterfUpdatetimeMapper =  SpringContextUtil.getBean(
        		ChbInterfConfigMapper.class);
    	Date rs = chbInterfUpdatetimeMapper.queryUpdateTime(interfId);
        //节假日通用方法
    	if(rs==null){
    		rs = DateUtil.parse("1900-01-01");
    	}
         return rs;
    }
    
    /**
     * 功能:根据接口id查询接口配置信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param interfId
     * @return 
     * ChbInterfConfigDto
     */
    public static ChbInterfConfigDto queryInterfConfigByInterfid(String interfId){
        ChbInterfConfigMapper chbInterfConfigMapper =  SpringContextUtil.getBean(
                ChbInterfConfigMapper.class);
        return chbInterfConfigMapper.selectByInterfId(interfId);
    }
    
    /**
     * 跟新接口配置中文件时间
     * 
     * @param interfId 接口代码
     * @return  List<Date>
     * @see
     * @since 1.0
     */
    public static void updateInterfTimeBySysdate(String interfId) {
        ChbInterfConfigMapper chbInterfUpdatetimeMapper =  SpringContextUtil.getBean(
                ChbInterfConfigMapper.class);
        // 更新配置时间
        chbInterfUpdatetimeMapper.updateBySysdate(interfId);
    }
	
	/**
     * 
     * 
     * @param interfId 接口代码
     * @param newUpdateTime 最近更新时间
     * @return  List<Date>
     * @see
     * @since 1.0
     */
    public static void updateLastupdatetime(String interfId, Date newUpdateTime,String InstitutionCode) {
    	ChbInterfConfigMapper chbInterfUpdatetimeMapper =  SpringContextUtil.getBean(
        		ChbInterfConfigMapper.class);
        //节假日通用方法
         ChbInterfConfigDto dto= null;
         //当联社模式
         if(CommonUtil.isBAmodel()){
        	 //如果是联社模式,并且非总行.则单独更新所属机构数据
         	if(StringUtil.isNotEmpty(InstitutionCode)){
         		//更新当前机构的更新时间
         		chbInterfUpdatetimeMapper.updateLastupdatetimeEx(interfId, newUpdateTime,InstitutionCode);
         		//获取当前接口最小的那个机构的更新日期填入config表
         		chbInterfUpdatetimeMapper.updateLastupdatetimeExt(interfId, newUpdateTime);

         	}else{
         		//如果是联社模式,并且是总行,则同时更新config表和add表
         		chbInterfUpdatetimeMapper.updateLastupdatetime(interfId, newUpdateTime);
         		//联社模式直接将当前机构下所有机构的add表时间全部更新
         		chbInterfUpdatetimeMapper.updateLastupdatetimeEx(interfId, newUpdateTime,null);
         	}
         }else{
        	 //非联社模式仅更新config表
        	 chbInterfUpdatetimeMapper.updateLastupdatetime(interfId, newUpdateTime);
         }

    }

    
    
    /**
     * 
     * 
     * @param interfId 接口代码
     * @param newUpdateTime 最近更新时间
     * @return  List<Date>
     * @see
     * @since 1.0
     */
    public static Date queryUpdateTime(String interfId,String InstitutionCode) {
    	ChbInterfConfigMapper chbInterfUpdatetimeMapper =  SpringContextUtil.getBean(
        		ChbInterfConfigMapper.class);
    	Date rs = null;
    	 //当联社模式.且机构编码不为空时
        if(CommonUtil.isBAmodel()&&StringUtil.isNotEmpty(InstitutionCode)){
        	rs = chbInterfUpdatetimeMapper.queryUpdateTimeEx(interfId,InstitutionCode);
        }else{
        	rs = chbInterfUpdatetimeMapper.queryUpdateTime(interfId);
        }
        //节假日通用方法
    	if(rs==null){
    		rs = DateUtil.parse("1900-01-01");
    	}
         return rs;
    }
    
    /**
     * 功能:根据接口id查询接口配置信息 -文件模式
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param interfId
     * @return 
     * ChbInterfConfigDto
     */
    public static ChbInterfConfigDto queryInterfConfigByInterfid(String interfId,String InstitutionCode){
        ChbInterfConfigMapper chbInterfConfigMapper =  SpringContextUtil.getBean(
                ChbInterfConfigMapper.class);
        ChbInterfConfigDto dto= null;
        //当联社模式.且机构编码不为空时
        if(CommonUtil.isBAmodel()&&StringUtil.isNotEmpty(InstitutionCode)){
        	dto = chbInterfConfigMapper.selectByInterfIdEx(interfId,InstitutionCode);
        }else{
        	dto = chbInterfConfigMapper.selectByInterfId(interfId);
        }
        
        return dto;
    }

}
