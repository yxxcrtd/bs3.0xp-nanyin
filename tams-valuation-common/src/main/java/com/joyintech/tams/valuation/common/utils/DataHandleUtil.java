/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joyintech.tams.valuation.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

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

    // /**
    // * 为了迎合sql批量最多只能一千批量插入，所以将list每900条分割一下，组成list的list
    // *
    // * @param list 集合
    // * @param <T> 泛型
    // * @return 集合
    // * @see
    // * @since 1.0
    // */
    // public static <T> List<List<T>> splitList(List<T> list) {
    // List<List<T>> resultLists = new ArrayList<>();
    // if(StringUtil.isEmptyList(list)) {
    // return resultLists;
    // }
    // List<T> resultList = new ArrayList<>();
    // for (int index = 1; index <= list.size(); index++) {
    // if(index % maxSqlNum == 0) {
    // resultLists.add(resultList);
    // resultList = new ArrayList<>();
    // }
    // resultList.add(list.get(index-1));
    // }
    // if(list.size() % maxSqlNum != 0) {
    // resultLists.add(resultList);
    // }
    // return resultLists;
    // }
}
