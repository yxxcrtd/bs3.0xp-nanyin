/*
 * 文件名：ValVoucherQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */


package com.joyintech.service.valuation.accounadjustmanage.voucherquery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery002Dto;
import com.joyintech.tams.common.base.dao.BokVouchMainMapper;
import com.joyintech.tams.common.base.entity.BokVouchMain;

/**
 * 记账凭证详情查看service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery003Service
 * @since 2.0
 */
@Service(value="serviceValVoucherQuery003Service")
@Transactional(rollbackFor = Exception.class)
public class ValVoucherQuery003Service extends CommonExportService<ValVoucherQuery002Dto> {
	
    @Autowired
    private BokVouchMainMapper valVouchMainMapper;
    
    public BokVouchMain getByVouchNum(String vouchNum){
        return valVouchMainMapper.selectByPrimaryKey(vouchNum);
    }
    
    public boolean updateVouch(String vouchNum, String vouchRemark){
        BokVouchMain valVouchMain = valVouchMainMapper.selectByPrimaryKey(vouchNum);
        if(valVouchMain == null){
            return false;
        }
        valVouchMain.setVouchRemark(vouchRemark);
        int count = valVouchMainMapper.updateByPrimaryKey(valVouchMain);
        return count==1;
    }
}
