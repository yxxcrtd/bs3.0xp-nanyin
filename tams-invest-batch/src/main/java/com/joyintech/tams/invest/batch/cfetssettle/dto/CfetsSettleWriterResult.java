/*
 * 文件名：ResetBaseRuleInfoReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.cfetssettle.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.TrdBankManualSetDetail;
import com.joyintech.tams.common.base.entity.TrdBankManualSettleFlow;
import com.joyintech.tams.common.base.entity.TrdBankManualSettleInfo;

import java.util.List;

/**
 *  reader结果
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2021年11月19日
 * @see CfetsSettleWriterResult
 * @since 1.0
 */
public class CfetsSettleWriterResult extends NeedToWriteEntity {

    private List<CfetsSettleReaderResult> cfetsSettleReaderResultList;

    public List<CfetsSettleReaderResult> getCfetsSettleReaderResultList() {
        return cfetsSettleReaderResultList;
    }

    public void setCfetsSettleReaderResultList(List<CfetsSettleReaderResult> cfetsSettleReaderResultList) {
        this.cfetsSettleReaderResultList = cfetsSettleReaderResultList;
    }
}
