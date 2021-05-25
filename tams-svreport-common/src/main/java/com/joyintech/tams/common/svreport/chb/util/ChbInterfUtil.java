package com.joyintech.tams.common.svreport.chb.util;

import java.util.Date;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.dao.ChbInterfConfigMapper;

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

}
