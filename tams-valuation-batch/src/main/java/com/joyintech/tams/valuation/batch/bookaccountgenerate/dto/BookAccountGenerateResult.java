/*
 * 文件名：BookAccountGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.bookaccountgenerate.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokVouchDetail;
import com.joyintech.tams.common.base.entity.BokVouchMain;
import com.joyintech.tams.common.base.entity.BokVouchRel;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;


public class BookAccountGenerateResult extends NeedToWriteEntity {
    private List<BokVouchMain> valVouchMain;
    private List<BokVouchDetail> valVouchDetail;
    private List<BokVouchRel> valVouchRel;
    
    private boolean updateHistory;
    
    public BookAccountGenerateResult(List<BokVouchMain> valVouchMain,
                                     List<BokVouchDetail> valVouchDetail,
                                     List<BokVouchRel> valVouchRel,boolean updateHistory) {
        this.valVouchMain = valVouchMain;
        this.valVouchDetail = valVouchDetail;
        this.valVouchRel = valVouchRel;
        this.updateHistory = updateHistory;
    }


    public boolean isUpdateHistory() {
        return updateHistory;
    }

    
    public void setUpdateHistory(boolean updateHistory) {
        this.updateHistory = updateHistory;
    }

    public List<BokVouchMain> getValVouchMain() {
        return valVouchMain;
    }
    
    public void setValVouchMain(List<BokVouchMain> valVouchMain) {
        this.valVouchMain = valVouchMain;
    }
    
    public List<BokVouchDetail> getValVouchDetail() {
        return valVouchDetail;
    }
    
    public void setValVouchDetail(List<BokVouchDetail> valVouchDetail) {
        this.valVouchDetail = valVouchDetail;
    }
    
    public List<BokVouchRel> getValVouchRel() {
        return valVouchRel;
    }
    
    public void setValVouchRel(List<BokVouchRel> valVouchRel) {
        this.valVouchRel = valVouchRel;
    }


}
