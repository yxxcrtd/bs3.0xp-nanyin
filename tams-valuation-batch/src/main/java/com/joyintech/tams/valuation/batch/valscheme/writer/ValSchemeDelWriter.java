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

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
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
                //增加一个估值参数，通过参数控制是否修改状态 IS_CHANGE_STATUS_VALNETVALUECONFIRM
                //净值确认下发重新跑批时是否修改确认状态、下发状态 Y:是 N 否
                String isChangeStatusValNetValyeConfirm = CacheUtil.getSysParam("IS_CHANGE_STATUS_VALNETVALUECONFIRM");
                if(StringUtil.isNotBlank(isChangeStatusValNetValyeConfirm)
                    && StringUtil.equals(DictCodeList.是否_否.getValue(),isChangeStatusValNetValyeConfirm)){
                    //是否修改确认状态、下发状态是否的情况，则删除确认状态/下发状态为空的数据
                    valSchemeDelMapper.deleteValbalTableStatusExt(result);
                }else{
                    valSchemeDelMapper.deleteValbalTableStatus(result);
                }
                valSchemeDelMapper.deleteValbalTableLog(result);
            }
        }
    }

}
