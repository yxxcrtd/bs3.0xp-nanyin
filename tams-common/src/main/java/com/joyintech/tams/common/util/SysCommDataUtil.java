//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.joyintech.tams.common.util;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;

import java.util.List;

public class SysCommDataUtil {
    public SysCommDataUtil() {
    }

    public static String getNameFromDataDict(BaseDictCode dictCode, String itemCode) {
        List<? extends DataDictItem> dataDictItemList = getDataDictItem(dictCode.getValue());
        if ( dataDictItemList!=null  )
	        return (String)dataDictItemList.stream().filter((item) -> {
	            return StringUtil.equals(item.getItemCode(), itemCode);
	        }).map(DataDictItem::getItemValue).findFirst().orElse(itemCode);
        else
        	return "";
    }

    public static List<? extends DataDictItem> getDataDictItem(String dictCode) {
        DataDict dict = CacheUtil.getDictCode(dictCode);
        return dict != null ? dict.getItems() : null;
    }

    public static DataDictItem getDataDictItem(String dictCode, String itemCode) {
        if (StringUtil.isNotEmpty(itemCode)) {
            List<? extends DataDictItem> items = getDataDictItem(dictCode);
            if (null != items) {
                return (DataDictItem)items.stream().filter((item) -> {
                    return StringUtil.equals(item.getItemCode(), itemCode);
                }).findFirst().orElse(null);
            }
        }

        return null;
    }


    /**
     *  根据 字典名称查找字典值
     * @param dictCode
     * @param
     * @return
     */
    public static String getCodeFromDataDictByName(BaseDictCode dictCode, String itemName) {
        List<? extends DataDictItem> dataDictItemList = getDataDictItem(dictCode.getValue());
        if ( dataDictItemList!=null )
	        return (String)dataDictItemList.stream().filter((item) -> {
	            return StringUtil.equals(item.getItemValue(), itemName);
	        }).map(DataDictItem::getItemCode).findFirst().orElse(null);
        else
        	return "";
    }
}
