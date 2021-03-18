/*
 * 文件名：CreditExtendsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.creditmanager.service;

import com.joyintech.tams.common.base.dao.CreditLinesDealMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.batch.creditmanager.dao.CreditExtendsMapper;
import com.joyintech.tams.invest.common.trade.CelinesUpdateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  授信继承任务
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年7月25日
 * @see CreditExtendsService
 * @since 1.0
 */
@Service
public class CreditExtendsService {

    @Autowired
    private CreditExtendsMapper creditExtendsMapper;

    @Autowired
    private CreditLinesDealMapper creditLinesDealMapper;

    @Autowired
    private CelinesUpdateFactory celinesUpdateFactory;



    /**
     * 继承处理
     * <功能详细描述>
     * @param currentDate
     * @see
     * @since 1.0
     */
    public void run(Date currentDate){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("pdate", currentDate);
        //获取需要继承的授信，及跑批当日生效占用额度为0的授信(全量继承)
        List<CreditLines> ptlList = creditExtendsMapper.selectCreditIdForTask(paramMap);
        for(CreditLines ptl : ptlList){
            ptl.setHapCreditAmt(celinesUpdateFactory.extendCreditLinesDeal(ptl.getSeqno(), ptl.getVdate(), ptl.getCreditMstId(),ptl.getCustomerId(),ptl.getInstitutionCode()));
            ptl.setUpdateTime(new Date());
            ptl.setUpdateUser("system");
            creditExtendsMapper.updateLinesByPrimaryKeySelective(ptl);
        }
        //获取需要增量继承的授信
        List<CreditLines> ptlListAdd = creditExtendsMapper.selectCreditIdForTaskAdd(paramMap);
        for(CreditLines ptl : ptlListAdd){
            ptl.setHapCreditAmt(celinesUpdateFactory.extendCreditLinesDealAdd(ptl.getSeqno(), ptl.getVdate(), ptl.getCreditMstId(),ptl.getCustomerId(),ptl.getInstitutionCode()));
            ptl.setUpdateTime(new Date());
            ptl.setUpdateUser("system");
            creditExtendsMapper.updateLinesByPrimaryKeySelective(ptl);
        }
    }


}
