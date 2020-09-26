/*
 * 文件名：BookCommonBatchService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.batch.bookcommon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.valuation.common.service.BookAccountCommonService;

/**
 * 记账公共类
 * 
 * @version 1.0 2018年11月9日
 * @see BookCommonBatchService
 * @since 1.0
 */
@Service
public class BookCommonBatchService {
    
    @Autowired
    private BookAccountCommonService bookAccountCommonService;
    
    /**
     * 组装账务明细(资产类型+资产代码)
     * @param busiId 资产代码
     * @param bokDetailId 账务明细代码
     * @param busiDetailType 资产类型
     * @return
     * @see
     * @since 1.0
     */
    public BokDetailId getValBokDetailId(String busiId, String bokDetailId,
                                            String busiDetailType,String ccy) {
        BokDetailId valBokDetailId = new BokDetailId();
        valBokDetailId.setBusiId(busiId);
        valBokDetailId.setBusiDetailType(busiDetailType);
        valBokDetailId.setCcy(ccy);
        if (bokDetailId == null) {
            valBokDetailId.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(valBokDetailId));
        }
        else {
            valBokDetailId.setBokDetailId(bokDetailId);
        }
        return valBokDetailId;
    }
    
    /**
     * 组装账务明细(资产类型+投资目的+资产代码)
     * 同valuation-batch项目中BookCommonLogic.getValBokDetailIdWithInvAim一致。
     * 
     * @param busiId 资产代码
     * @param bokDetailId 账务明细代码
     * @param busiDetailType 资产类型
     * @param invAim 投资目的
     * @return 账务明细代码
     * @see
     * @since 1.0
     */
     public BokDetailId getValBokDetailIdWithInvAim(String busiId, String bokDetailId,
                                             String busiDetailType,String invAim) {
         BokDetailId valBokDetailId = new BokDetailId();
         valBokDetailId.setBusiId(busiId); //资产代码
         valBokDetailId.setBusiDetailType(busiDetailType); //资产类型
         valBokDetailId.setInvAim(invAim); //投资目的
         if (bokDetailId == null) {
             valBokDetailId.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(valBokDetailId));
         }
         else {
             valBokDetailId.setBokDetailId(bokDetailId);
         }
         return valBokDetailId;
     }
     
     /**
      * 组装账务明细(资产类型+分支序号+资产代码)
      * 
      * @param busiId 资产代码
      * @param bokDetailId 账务明细代码
      * @param busiDetailType 资产类型
      * @param invAim 投资目的
      * @return 账务明细代码
      * @see
      * @since 1.0
      */
      public BokDetailId getValBokDetailIdWithBranchNo(String busiId, String bokDetailId,
                                              String busiDetailType,Long branchNo) {
          BokDetailId valBokDetailId = new BokDetailId();
          valBokDetailId.setBusiId(busiId); //资产代码
          valBokDetailId.setBusiDetailType(busiDetailType); //资产类型
          valBokDetailId.setBranch(branchNo);//投资目的
          if (bokDetailId == null) {
              valBokDetailId.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(valBokDetailId));
          }
          else {
              valBokDetailId.setBokDetailId(bokDetailId);
          }
          return valBokDetailId;
      }

}
