/*
 * 文件名：BookDetailCompareIntoReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.reader;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto.BokBokBookdetailCurrent;

/**
 * 账务明细比较合并批处理 - 读入
 * 
 * @author yangjian
 * @version 1.0 2018年5月7日
 * @see BookDetailCompareUpdateReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailCompareUpdateReader extends BaseReader<BokBokBookdetailCurrent> {

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<BokBokBookdetailCurrent> createList() {
        List<BokBokBookdetailCurrent> list = new ArrayList<BokBokBookdetailCurrent>();
        if (ValDictCodeList.是否启用倒起息_启用.getValue().equals(CacheUtil.getSysParam(BookConstant.是否启用倒起息))) {
            list.add(new BokBokBookdetailCurrent());
        }
        return list;
    }

}
