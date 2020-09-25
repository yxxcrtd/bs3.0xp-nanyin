/*
 * 文件名：IEventItem.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.event.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;

/**
 * 风险事件特征值信息结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月14日
 * @see IEventItem
 * @since 1.0
 */
public interface IEventItem extends Serializable {

    /**
     * 匹配风险特征值
     * 
     * @param mapData 输入数据
     * @param calendar 日历信息
     * @return 匹配结果、true:匹配、false:不匹配
     * @see
     * @since 1.0
     */
    default boolean match(Map<String, Object> mapData, ICalendar calendar) {
        String key = getItemAnoName();
        if (StringUtil.isEmpty(key)) {
            key = getItemCode();
        }
        Object value = mapData.get(key);
        String type = getMatchType();
        if (value instanceof BigDecimal) {
            BigDecimal num = (BigDecimal)value;
            BigDecimal keyNum = new BigDecimal(calcOffset(mapData, calendar));
            return CommonUtil.compareV(num, keyNum, type);
        }
        if (value instanceof Date) {
        	return CommonUtil.compareStr(DateUtil.format((Date) value), calcOffset(mapData, calendar), type);
        }
        return CommonUtil.compareStr(value == null ? "" : value.toString(), calcOffset(mapData, calendar), type);
    }

    default String calcOffset(Map<String, Object> mapData, ICalendar calendar) {
        String value = getItemValue();
        if (!StringUtil.startsWith(value, "${")) {
            return value;
        }
        String key = StringUtil.remove(value, "${");
        key = StringUtil.remove(key, "}");
        Object val = mapData.get(key);
        String valStr = null;
        if (val instanceof String) {
            valStr = (String)val;
        }
        else {
            valStr = val.toString();
        }
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)";
        if (valStr.matches(regex)) {
            int day = getOffsetVal().intValue();
            Date valDate = DateUtil.parse(valStr);
            day = CommonUtil.isType(getOffsetType(), BussinessDictCodeListRds.操作符_加) ? day : -day;
            if (CommonUtil.isType(getOffsetUnit(), BussinessDictCodeListRds.特征值偏移单位_年)) {
                return DateUtil.format(DateUtil.addYear(valDate, day));
            }
            if (CommonUtil.isType(getOffsetUnit(), BussinessDictCodeListRds.特征值偏移单位_月)) {
                return DateUtil.format(DateUtil.addMonths(valDate, day));
            }
            if (calendar != null) {
                return calendar.calcDate(valStr, day, getOffsetType(), getOffsetUnit());
            }
            return DateUtil.format(DateUtil.addDay(valDate, day));
        }
        else {
            BigDecimal res = CommonUtil.math(new BigDecimal(valStr),
                new BigDecimal(getOffsetVal()), getOffsetType());
            return StringUtil.numberToString(res);
        }
    }

    /**
     * 取得 特征值代码
     * 
     * @return 特征值代码
     * @see
     * @since 1.0
     */
    String getItemCode();

    /**
     * 取得 特征值别名
     * 
     * @return 特征值别名
     * @see
     * @since 1.0
     */
    String getItemAnoName();

    /**
     * 取得 具体特征值
     * 
     * @return 具体特征值
     * @see
     * @since 1.0
     */
    String getItemValue();

    /**
     * 取得 匹配方式
     * 
     * @return 匹配方式
     * @see
     * @since 1.0
     */
    String getMatchType();

    /**
     * 取得 特征值偏移量
     * 
     * @return 特征值偏移量
     * @see
     * @since 1.0
     */
    Long getOffsetVal();

    /**
     * 取得 特征值偏移方向
     * 
     * @return 特征值偏移方向
     * @see
     * @since 1.0
     */
    String getOffsetType();

    /**
     * 取得 特征值偏移单位
     * 
     * @return 特征值偏移单位
     * @see
     * @since 1.0
     */
    String getOffsetUnit();

    /**
     * 取得 分组Id
     * 
     * @return 分组Id
     * @see
     * @since 1.0
     */
    String getGroupId();
}
