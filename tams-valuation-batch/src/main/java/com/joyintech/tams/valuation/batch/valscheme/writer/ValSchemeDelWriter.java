/*
 * 文件名：BookAccountGenerateDelWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.valscheme.dao.ValSchemeDelMapper;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeDelResult;

@Service
@Scope("prototype")
public class ValSchemeDelWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private ValSchemeDelMapper valSchemeDelMapper;

    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<ValSchemeDelResult> resultList = (List<ValSchemeDelResult>)items;
        if(resultList != null){
            for(ValSchemeDelResult result : resultList){
                valSchemeDelMapper.deleteValbalTableStatus(result);
                valSchemeDelMapper.deleteValbalTableLog(result);
            }
        }
    }

}
