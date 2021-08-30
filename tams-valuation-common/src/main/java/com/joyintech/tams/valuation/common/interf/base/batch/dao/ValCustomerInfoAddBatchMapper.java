/*
 * 文件名：ValCustomerInfoBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：魏鹏
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAddWithBLOBs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValCustomerInfoAddBatchMapper {

    int insertBatch(List<MstCustomerInfoAddWithBLOBs> list);
    int deleteBatch(List<MstCustomerInfoAddWithBLOBs> listInsert);

}
