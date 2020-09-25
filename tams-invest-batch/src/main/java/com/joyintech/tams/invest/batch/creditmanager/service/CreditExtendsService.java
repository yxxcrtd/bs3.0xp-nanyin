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

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.CreditLinesDealMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import com.joyintech.tams.common.base.entity.CreditLinesDealKey;
import com.joyintech.tams.invest.batch.creditmanager.dao.CreditExtendsMapper;
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
        //获取新建的授信
        List<CreditLines> ptlList = creditExtendsMapper.selectCreditIdForTask(paramMap);
        for(CreditLines ptl : ptlList){
            paramMap.put("creditMstId", ptl.getCreditMstId());//授信配置编号
            paramMap.put("customerId", ptl.getCustomerId());//授信客户编号
            List<CreditLines> ptlPreList = creditExtendsMapper.selectPreCreditIdForTask(paramMap);
            if(StringUtil.isEmptyList(ptlPreList)){
                continue;
            }
            ptl.setHapCreditAmt(ptlPreList.get(0).getHapCreditAmt());//取最近的一次授信额度
            ptl.setUpdateTime(new Date());
            creditExtendsMapper.updateLinesByPrimaryKeySelective(ptl);

            //查找原交易关联明细信息
            List<CreditLinesDeal> creLineDeal=creditExtendsMapper.selectDealByPrimaryKey(ptlPreList.get(0).getSeqno());
            for(CreditLinesDeal entry:creLineDeal){
                entry.setSeqno(ptl.getSeqno());
                CreditLinesDealKey creKey=new CreditLinesDealKey();
                creKey.setSeqno(ptl.getSeqno());
                creKey.setTradeId(entry.getTradeId());
                creditLinesDealMapper.deleteByPrimaryKey(creKey);
                creditLinesDealMapper.insertSelective(entry);
            }

        }
    }

}
