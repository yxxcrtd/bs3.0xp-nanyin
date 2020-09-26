/*
 * 文件名：InvaimRefreshProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.invaimrefresh.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.invaimrefresh.dto.DealInvaimInfoDto;
import com.joyintech.tams.valuation.batch.invaimrefresh.dto.InvaimRefreshProcessResultDto;
import com.joyintech.tams.valuation.batch.invaimrefresh.dto.InvaimRefreshReaderResultDto;

/**
 * 投资目的刷新 - reader
 * 
 * @author yangjian
 * @version 1.0 2018年9月8日
 * @see InvaimRefreshProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InvaimRefreshProcessor extends BaseProcessor<NeedOperateEntity, InvaimRefreshProcessResultDto> {

    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    @Override
    public InvaimRefreshProcessResultDto process(NeedOperateEntity items)
        throws Exception {

        // 获取reader方法的返回值
        InvaimRefreshReaderResultDto acctEntity = (InvaimRefreshReaderResultDto)items;

        // 若reader读取的值为空，直接返回空
        if (acctEntity == null || acctEntity.getDealInvaimIntoDtoList() == null || acctEntity.getDealInvaimIntoDtoList().isEmpty()) {
            return null;
        }

        // 获取【获取投资目的需要刷新的交易及投资目的信息】list
        List<DealInvaimInfoDto> dealInvaimIntoDtoList = acctEntity.getDealInvaimIntoDtoList();

        // 定义需要改变的数据list
        List<DealInvaimInfoDto> changeList = new ArrayList<DealInvaimInfoDto>();

        // 循环进行数据处理
        for (DealInvaimInfoDto dto : dealInvaimIntoDtoList) {
            // 获取最终要修改的投资目的
            String changeInvaimNow = dto.getChangeInvaim();
            // 新生产的账务明细代码
            String bokDetailIdNow = null;

            // 【最终需要修改成的投资目的】和【原始交易目的】不一致才进行刷新出来
            if (changeInvaimNow != null && !changeInvaimNow.equals(dto.getOrgDealInvaim())) {
                // 根据最新的投资目的，生成账务明细代码表的记录
                BokDetailId bokDetailIdDto = bookCommonLogic.getValBokDetailIdWithInvAim(dto.getFinprodId(), null, dto.getFinprodType(), changeInvaimNow);
                if (bokDetailIdDto != null) {
                    bokDetailIdNow = bokDetailIdDto.getBokDetailId();
                }

                // 定义需要刷新后的数据
                dto.setChangeBookDetailId(bokDetailIdNow);// 新生成的账务明细代码
                changeList.add(dto);
            }
        }

        // 定义返回值
        InvaimRefreshProcessResultDto returnDto =new InvaimRefreshProcessResultDto();
        if (changeList != null && !changeList.isEmpty()) {
            returnDto.setBooksetId(acctEntity.getBooksetId());
            returnDto.setDealInvaimIntoDtoList(changeList);
        }
        
        returnDto.setProdId(acctEntity.getBooksetId());

        return returnDto;
    }

}
