/*
 * 文件名：DepoAcctGroupInfoEntity.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：caoguo 修改时间：2016年12月5日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.lendbookdetail.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokSubjectDetail;

/**
 * RepoPledgePositionProcess处理结果
 * 
 * @author wudebao
 * @version 1.0 2018年5月3日
 * @see LendBookDetailProcessResult
 * @since 1.0
 */
public class LendBookDetailProcessResult extends NeedToWriteEntity { 

    //账务明细（当前表）list
    private List<BokBookdetailCurrent> listValBokBookdetailCurrent = new ArrayList<BokBookdetailCurrent>();
    //明细科目list
    private List<BokSubjectDetail> listValBokSubjectDetail = new ArrayList<BokSubjectDetail>();

    //账务明细（当前表）list 取值
    public List<BokBookdetailCurrent> getListValBokBookdetailCurrent() {
       return listValBokBookdetailCurrent;
    }

    //账务明细（当前表）list 设值
    public void setListValBokBookdetailCurrent(List<BokBookdetailCurrent> listValBokBookdetailCurrent) {
       this.listValBokBookdetailCurrent = listValBokBookdetailCurrent;
    }

    //明细科目list 取值
    public List<BokSubjectDetail> getListValBokSubjectDetail() {
       return listValBokSubjectDetail;
    }

    //明细科目list 设值
    public void setListValBokSubjectDetail(List<BokSubjectDetail> listValBokSubjectDetail) {
       this.listValBokSubjectDetail = listValBokSubjectDetail;
    }

}
