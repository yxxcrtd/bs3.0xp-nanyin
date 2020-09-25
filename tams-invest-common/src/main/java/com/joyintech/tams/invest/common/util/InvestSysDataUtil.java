package com.joyintech.tams.invest.common.util;

import java.util.List;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;

/**
 * @program: tams-invest-parent
 * @description: 数据字典工具类
 * @author: 王泽慧
 * @create: 2019-03-17 15:53
 **/
public class InvestSysDataUtil {
	/**
     * 获取数据字典明细
     * 
     * @param dictCode 数据字典代码
     * @param itemCode 字典明细参数值
     * @return 数据字典明细
     */
    public static DataDictItem getDataDictItemByName(String dictCode, String itemValue) {
    	if (StringUtil.isNotEmpty(itemValue)) {
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            if (null != items) {
                return items.stream().filter(
                    item -> StringUtil.equals(item.getItemValue(), itemValue)).findFirst().orElse(null);
            }
        }
        return null;
    }
}
