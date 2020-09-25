/*
 * 文件名：FinProductCommonBatchService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.common.service;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.disclosure.busicommon.common.constants.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.common.dao.DisFinProductCommonMapper;
import com.joyintech.tams.disclosure.busicommon.common.dto.FinProduct;


/**
 * 标的类共通方法
 * 
 * @author wangzhiang
 * @version 1.0 2018年5月31日
 * @see FinProductCommonBatchService
 * @since 1.0
 */
@Service
public class DisFinProductCommonBatchService {
	/**
     * 标的类共同mapper
     */
    @Autowired
    private DisFinProductCommonMapper finProductCommonMapper;
    
    /**
     * 核算主体查询 <功能详细描述>
     * 
     * @param finprodId
     * @param finprodType
     * @param exerciseDate
     * @return
     * @see
     * @since 1.0
     */
    public FinProduct queryAcctSubject(String booksetId) {
        return finProductCommonMapper.queryAcctSubject(booksetId,
            DictCodeListExt1.金融产品类型_理财产品.getValue());
    }
}
