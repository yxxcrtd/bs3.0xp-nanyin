/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joyintech.tams.model.valuation.batch.common.utils;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.model.valuation.batch.common.constants.GlobalConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据处理共通
 * 
 * @author wangzhiang
 */
public class DataHandleUtil {

    public static int DEFAULT = 200;

    /**
     * 产品数大于1000时，将产品做分组处理 <一句话功能简述> <功能详细描述>
     * 
     * @param prdList 产品List
     * @return List<List<String>>
     * @see
     * @since 1.0
     */
    public static <T> List<List<T>> splitList(List<T> prdList) {
        int count = CacheUtil.getAppCache(GlobalConstant.MAX_SQL_NUM,
            Integer.class) != null ? CacheUtil.getAppCache(GlobalConstant.MAX_SQL_NUM,
                Integer.class) : DEFAULT;
        return splitList(prdList, count);
    }

    /**
     * 产品数大于1000时，将产品做分组处理 <一句话功能简述> <功能详细描述>
     * 
     * @param prdList 产品List
     * @return List<List<String>>
     * @see
     * @since 1.0
     */
    public static <T> List<List<T>> splitList(List<T> prdList, int count) {
        if (count == 0) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        if (prdList == null || prdList.isEmpty()) {
            return result;
        }
        if (prdList.size() <= count) {
            result.add(prdList);
        }
        else {
            int part = prdList.size() / count;// 分批数
            for (int i = 0; i < part + 1; i++ ) {
                if (count * (i + 1) <= prdList.size()) {
                    List<T> subIds = prdList.subList(count * i, count * (i + 1));
                    result.add(subIds);
                    if (count * (i + 1) == prdList.size()) {
                        break;
                    }
                }
                else {
                    List<T> subIds = prdList.subList(count * i, prdList.size());
                    result.add(subIds);
                }
            }
        }

        return result;
    }

}
