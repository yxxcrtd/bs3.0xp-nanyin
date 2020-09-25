/*
 * 文件名：BondAutoDurDealGenerateDelWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.bond.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dao.BondAutoDurDealGenerateMapper;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * DelWriter
 *
 * @author etliu
 * @version 1.0 2018年12月13日
 * @see BondAutoDurDealGenerateDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BondAutoDurDealGenerateDelWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        autoPeriodCommonService.deleteWflData(DictCodeList.共通业务类型_债券类资产存续期.getValue(),
            DictCodeListExt1.金融产品类型_投管用_债券.getValue());
    }

}
