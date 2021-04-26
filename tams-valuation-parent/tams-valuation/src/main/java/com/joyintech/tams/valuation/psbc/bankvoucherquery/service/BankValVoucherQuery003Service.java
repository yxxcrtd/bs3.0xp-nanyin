/*
 * 文件名：ValVoucherQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */


package com.joyintech.tams.valuation.psbc.bankvoucherquery.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.common.base.dao.BokVouchMainMapper;
import com.joyintech.tams.common.base.entity.BokVouchMain;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 记账凭证详情查看service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery003Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BankValVoucherQuery003Service extends CommonExportService<ValVoucherQuery002Dto> {
	
    @Autowired
    private BokVouchMainMapper valVouchMainMapper;
    
    public BokVouchMain getByVouchNum(String vouchNum){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery003/getByVouchNum",vouchNum, BokVouchMain.class);
//        return valVouchMainMapper.selectByPrimaryKey(vouchNum);
    }
    
    public boolean updateVouch(String vouchNum, String vouchRemark){
        Map<String , Object> params = new HashMap<String , Object>();
        params.put("vouchNum", vouchNum);
        params.put("vouchRemark", vouchRemark);
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery003/updateVouch",params, boolean.class);
        
//        BokVouchMain valVouchMain = valVouchMainMapper.selectByPrimaryKey(vouchNum);
//        if(valVouchMain == null){
//            return false;
//        }
//        valVouchMain.setVouchRemark(vouchRemark);
//        int count = valVouchMainMapper.updateByPrimaryKey(valVouchMain);
//        return count==1;
    }
}
