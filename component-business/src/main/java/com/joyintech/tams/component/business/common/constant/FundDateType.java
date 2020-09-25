/*
 * 文件名：FundDateType.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.common.constant;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * WIND_中国货币市场基金份额结转方式Entity.收益分配份额结转日期类型代码枚举值如下
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月20日
 * @see FundDateType
 * @since 1.0
 */
public enum FundDateType {

    每月第一个工作日("640001001","1", DictCodeListExt1.日期类型_几号.getValue()),
    每月第二个工作日("640001002","2",DictCodeListExt1.日期类型_几号.getValue()),
    每月倒数第二个工作日("640001998","2",DictCodeListExt1.日期类型_倒数第几工作日.getValue()),
    每月最后一个工作日("640001999","1",DictCodeListExt1.日期类型_倒数第几工作日.getValue()),
    每月1日("640002001","1",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月5日("640002005","5",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月6日("640002006","6",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月8日("640002008","8",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月10日("640002010","10",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月11日("640002011","11",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月15日("640002015","15",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月16日("640002016","16",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月18日("640002018","18",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月19日("640002019","19",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月20日("640002020","20",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月21日("640002021","21",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月22日("640002022","22",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月23日("640002023","23",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月25日("640002025","25",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月26日("640002026","26",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每月28日("640002028","28",DictCodeListExt1.日期类型_第几工作日.getValue()),
    每日("640003000","",""),
    ;
    
    /**
     * 对应于数据字典code
     */
    private String value;
    
    /**
     * 日期值
     */
    private String dayValue;
    
    /**
     * 日期类型
     */
    private String dayType;

    FundDateType(String value,String dayValue,String dayType) {
        this.value = value;
        this.dayValue = dayValue;
        this.dayType = dayType;
    }
    
    /**
     * 获取 value
     *
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    
    /**
     * 获取 dayValue
     * @return dayValue.
     */
    public String getDayValue() {
        return dayValue;
    }

    
    /**
     * 设置 dayValue
     * @param dayValue dayValue
     */
    public void setDayValue(String dayValue) {
        this.dayValue = dayValue;
    }

    
    /**
     * 获取 dayType
     * @return dayType.
     */
    public String getDayType() {
        return dayType;
    }

    
    /**
     * 设置 dayType
     * @param dayType dayType
     */
    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    
    
    
}
