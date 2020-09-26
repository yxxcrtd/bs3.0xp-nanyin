/*
 * 文件名：ValBokBalanceWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ranhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.valfilegendel.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.valfilegendel.dao.ValFileGenDelMapper;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 估值表生成
 * 
 * @author yangzhi
 * @version 1.0 2018年5月28日
 * @see ValFileGenDelWriter
 * @since 1.0
 */
@Service
public class ValFileGenDelWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValFileGenDelMapper valFileGenDelMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        Map<String,Object> param = new HashMap<>();
        param.putAll(this.getParams());
        param.put("acctSubjectType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        //valFileGenDelMapper.deleteValFileGen(param);

        if (DictCodeList.文件类型_余额表.getValue().equals(param.get(DictCode.文件类型.getValue()))) {
            valFileGenDelMapper.deleteBalTableData(param);
        } else if (DictCodeList.文件类型_估值表.getValue().equals(param.get(DictCode.文件类型.getValue()))) {
            valFileGenDelMapper.deleteValTableData(param);
        }
        valFileGenDelMapper.deleteTableLog(param);
        valFileGenDelMapper.deleteTableStatus(param);
    }
}
