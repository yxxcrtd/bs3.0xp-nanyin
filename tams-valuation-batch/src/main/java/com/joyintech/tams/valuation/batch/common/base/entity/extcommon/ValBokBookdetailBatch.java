/*
 * 文件名：BokPositionMmfBanBatch.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年5月22日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.common.base.entity.extcommon;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokSubjectDetail;
import com.joyintech.tams.valuation.batch.common.base.entity.BatchOperationInterface;
import com.joyintech.tams.valuation.batch.common.base.spring.annotation.BaseEntityClassName;

/**
 * 批处理用账务明细
 * 
 * @author wangzhiang
 * @version 1.0 2017年5月22日
 * @see BokKeepaccountDetailBatch
 * @since 1.0
 */
@BaseEntityClassName(value = BokBookdetailCurrent.class)
public class ValBokBookdetailBatch extends BokBookdetailCurrent implements BatchOperationInterface {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 9189270944520436959L;

    /**
     * 明细科目
     */
    private BokSubjectDetail valBokSubjectDetail;

    /**
     * 业务明细科目
     */
    private BokBussDetsub valBokBussDetsub;

    /**
     * 科目类别
     */
    private String subjectType;

    
    /**
     * 获取 subjectType
     * @return subjectType.
     */
    public String getSubjectType() {
        return subjectType;
    }


    
    /**
     * 设置 subjectType
     * @param subjectType subjectType
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }


    /**
     * 获取 valBokBussDetsub
     * @return valBokBussDetsub.
     */
    public BokBussDetsub getValBokBussDetsub() {
        return valBokBussDetsub;
    }

    
    /**
     * 设置 valBokBussDetsub
     * @param valBokBussDetsub valBokBussDetsub
     */
    public void setValBokBussDetsub(BokBussDetsub valBokBussDetsub) {
        this.valBokBussDetsub = valBokBussDetsub;
    }

    /**
     * 获取 valBokSubjectDetail
     * 
     * @return valBokSubjectDetail.
     */
    public BokSubjectDetail getValBokSubjectDetail() {
        return valBokSubjectDetail;
    }

    /**
     * 设置 valBokSubjectDetail
     * 
     * @param valBokSubjectDetail valBokSubjectDetail
     */
    public void setValBokSubjectDetail(BokSubjectDetail valBokSubjectDetail) {
        this.valBokSubjectDetail = valBokSubjectDetail;
    }

}
