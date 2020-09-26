package com.joyintech.tams.report.common.service;


import java.util.HashMap;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;

public class SvrCommonExportService <T extends ITableRow> extends CommonExportService<T>  {


    /**
     * excel文件生成之前调用
     */
    @Override
    @SuppressWarnings("unchecked")
    public void beforeExprot() {
        super.beforeExprot();
        Map<String, String> cacheValue = CacheUtil.getAppCache(GlobalConstant.SYS_PARA_KEY, Map.class);
        cacheValue.put(GlobalConstant.EXPORT_EXCEL_TYPE, DictCodeList.是否_中债_否.getValue());
        CacheUtil.putAppCache(GlobalConstant.SYS_PARA_KEY, cacheValue);
    }
    
    /**
     * excel文件生成之前调用
     * @param pageId 画面ID
     * @param total 数据列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public void afterExprot(String pageId, long total) {
        super.afterExprot(pageId,total);
        Map<String, String> cacheValue = CacheUtil.getAppCache(GlobalConstant.SYS_PARA_KEY, Map.class);
        cacheValue.remove(GlobalConstant.EXPORT_EXCEL_TYPE);
        CacheUtil.putAppCache(GlobalConstant.SYS_PARA_KEY, cacheValue);
    }
}
