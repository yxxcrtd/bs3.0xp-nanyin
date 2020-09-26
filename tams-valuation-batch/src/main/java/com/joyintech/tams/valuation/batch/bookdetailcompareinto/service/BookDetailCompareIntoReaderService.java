/*
 * 文件名：BookDetailCompareIntoReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dao.BookDetailCompareIntoMapper;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto.BokBokBookdetailCurrent;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto.ValBokBookdetailAllExt;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto.ValBokBookdetailCurrentExt;

/**
 * 账务明细比较合并批处理 - reader相关业务处理
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see BookDetailCompareIntoReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailCompareIntoReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailCompareIntoMapper bookDetailCompareIntoMapper;

    /**
     * 获取数据库数据
     * 
     * @param param 传递参数
     * @return List<BookDetailCompareIntoReadResult>
     * @see
     * @since 1.0
     */
    public List<BokBokBookdetailCurrent> execute(Map<String, Object> param) {

        // 定义reader返回list
        List<BokBokBookdetailCurrent> resultList = new ArrayList<BokBokBookdetailCurrent>();

        // 定义查询结果集：MAP_A
        Map<String, List<ValBokBookdetailCurrentExt>> MAP_A = new HashMap<>();
        Map<String, List<ValBokBookdetailAllExt>> MAP_B = new HashMap<>();

        // 1、查询账务明细（当前表）表待比对数据
        List<ValBokBookdetailCurrentExt> curList = bookDetailCompareIntoMapper.queryValBokBookdetailCurrentDetail(param);
        // 账务明细（当前表）加入mapA
        if (curList != null && curList.size() > 0) {
            for (ValBokBookdetailCurrentExt cur : curList) {
                if (MAP_A.get(cur.getBooksetId()) == null) {
                    List<ValBokBookdetailCurrentExt> putList = new ArrayList<ValBokBookdetailCurrentExt>();
                    putList.add(cur);
                    MAP_A.put(cur.getBooksetId(), putList);
                }
                else {
                    MAP_A.get(cur.getBooksetId()).add(cur);
                }
            }
        }

        // 2.查询账务明细（历史表）表待比对数据
        List<ValBokBookdetailAllExt> bookList = bookDetailCompareIntoMapper.queryValBokBookdetailAllDetail(param);
        // 账务明细（当前表）加入mapA
        if (bookList != null && bookList.size() > 0) {
            for (ValBokBookdetailAllExt book : bookList) {
                if (MAP_B.get(book.getBooksetId()) == null) {
                    List<ValBokBookdetailAllExt> putList = new ArrayList<ValBokBookdetailAllExt>();
                    putList.add(book);
                    MAP_B.put(book.getBooksetId(), putList);
                }
                else {
                    MAP_B.get(book.getBooksetId()).add(book);
                }
            }
        }
        for (Entry<String, List<ValBokBookdetailCurrentExt>> entry : MAP_A.entrySet()) {
            BokBokBookdetailCurrent rst = new BokBokBookdetailCurrent();
            // 账套代码（由凭证编号改成按账套代码并发）
            rst.setBookId(entry.getKey());
            // 当前表明细
            rst.setListValBokBookdetailCurrent(MAP_A.get(entry.getKey()));
            // 历史表明细
            rst.setListValBokBookdetailAll(MAP_B.get(entry.getKey()));
            resultList.add(rst);
        }

        return resultList;
    }

}
