/*
 * 文件名：ValAcctLayeringInfoBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;

@Repository
public interface ValAcctLayeringInfoBatchMapper {

    int insert(List<BokAcctLayeringInfo> list);

    int deleteBatch(List<BokAcctLayeringInfo> list);
    
    int deleteBatchBycondition(List<BokAcctLayeringInfo> list);
    
    List<BokAcctLayeringInfo>  selectByList(List<String> list);
    
    
}
