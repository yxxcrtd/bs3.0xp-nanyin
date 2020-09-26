/*
 * 文件名：DepoAcctGroupInfoEntity.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：caoguo 修改时间：2016年12月5日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.lendbookdetail.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
/**
 * Process共通结果集，用来更新账务明细、科目明细、利率表的数据存储结构
 * 
 * @author wudebao
 * @version 1.0 2018年5月3日
 * @see BooklProcessCommResult
 * @since 1.0
 */
public class BooklProcessCommResult extends NeedToWriteEntity { 

    //账务明细（当前表）list
    private List<BookGenerateResult> listBookGenerateResult = new ArrayList<BookGenerateResult>();

    //账务明细（当前表）list 取值
    public List<BookGenerateResult> getListBookGenerateResult() {
       return listBookGenerateResult;
    }

    //账务明细（当前表）list 设置
    public void setListBookGenerateResult(List<BookGenerateResult> listBookGenerateResult) {
       this.listBookGenerateResult = listBookGenerateResult;
    }

}
