/*
 * 文件名：CommonUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.component.common.utils;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 组件和微服务使用的业务相关工具类
 *
 * @author 何阳阳
 * @version 1.0 2018年10月23日
 * @see CommonUtil
 * @since 1.0
 */
public final class CommonUtil {

    private CommonUtil() {

    }

    /**
     * 现金流代码生成
     * @param busiNo 业务代码
     * @param busiType 业务类型
     * @param branch 分支序号
     * @param busiType2 现金流二级类型
     * @return 现金流代码
     */
    public static String generateCashFlowCode(String busiNo, String busiType, long branch,
                                              String busiType2) {
        final String sepChar = String.valueOf((char)256);

        String tmpStr = StringUtil.joinString(
            Arrays.asList(StringUtil.defaultString(busiNo), StringUtil.defaultString(busiType),
                String.valueOf(branch), StringUtil.defaultString(busiType2)),
            sepChar);

        return EncryptUtil.getInstance().md5(tmpStr);
    }

    /**
     * 获取数据字典明细
     *
     * @param dictCode 数据字典代码
     * @param itemValue 字典明细参数值
     * @return 数据字典明细
     */
    public static DataDictItem getDataDictItemByName(String dictCode, String itemValue) {
        if (StringUtil.isNotEmpty(itemValue)) {
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            if (null != items) {
                return items.stream().filter(
                        item -> StringUtil.equals(item.getItemValue(), itemValue)).findFirst().orElse(
                        null);
            }
        }
        return null;
    }

    /**
     * 判断数据字典明细类型
     *
     * @param type 判断对象
     * @param dict 目标类型
     * @return <b>true:</b> 是目标类型</br>
     *         <b>false:</b>不是目标类型
     * @see
     * @since 1.0
     */
    public static final boolean isType(String type, BaseDictCodeList dict) {
        return StringUtil.equals(type, dict.getValue());
    }

    /**
     * 判断指定的字符串是否是数字
     * @param str
     * @return
     * @see
     * @since 1.0
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.trim().equals(""))
            return false;
        if (str.endsWith("."))  //不想调换复杂的正则表达式，对于[3.]这种数据也认为是合法的数字
            str = str.substring(0,str.length()-1);
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }
}
