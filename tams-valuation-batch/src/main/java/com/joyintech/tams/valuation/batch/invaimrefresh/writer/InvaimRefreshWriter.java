/*
 * 文件名：InvaimRefreshWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.invaimrefresh.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokDealAccountingDetailMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingMapper;
import com.joyintech.tams.common.base.dao.BokDetailIdMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.valuation.batch.invaimrefresh.dto.DealInvaimInfoDto;
import com.joyintech.tams.valuation.batch.invaimrefresh.dto.InvaimRefreshProcessResultDto;

/**
 * 投资目的刷新 - writer
 * 
 * @author yangjian
 * @version 1.0 2018年9月8日
 * @see InvaimRefreshWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InvaimRefreshWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private BokDetailIdMapper bokDetailIdMapper;

    @Autowired
    private BokDealAccountingDetailMapper bokDealAccountingDetailMapper;
    
    @Autowired
    private BokDealAccountingMapper bokDealAccountingMapper;

    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值
        List<InvaimRefreshProcessResultDto> resultList = (List<InvaimRefreshProcessResultDto>)items;

        // 循环进行数据处理
        for (InvaimRefreshProcessResultDto dto : resultList) {
            // 获取需要更新的数据
            List<DealInvaimInfoDto> changeList = dto.getDealInvaimIntoDtoList();

            // 循环进行数据更新
            for (DealInvaimInfoDto changeDto : changeList) {

                // 1.金融产品交易表，投资目的修改
                TrdProductDeal deal = new TrdProductDeal();
                deal.setTradeId(changeDto.getTradeId());// 交易编号
                deal.setInvAim(changeDto.getChangeInvaim());// 投资目的
                trdProductDealMapper.updateByPrimaryKeySelective(deal);

                // 2.账务明细代码表，数据新增
                // 根据主键[账套代码][账务明细代码],判断账务明细代码表数据是否已存在
                BokDetailId bokDetailIdNew = new BokDetailId();
                bokDetailIdNew.setBooksetId(changeDto.getBooksetId());// 账套代码
                bokDetailIdNew.setBokDetailId(changeDto.getChangeBookDetailId());// 账务明细代码
                bokDetailIdNew.setBusiId(changeDto.getFinprodId());// 业务明细代码
                bokDetailIdNew.setBusiDetailType(changeDto.getFinprodType());// 业务明细类型
                bokDetailIdNew.setInvAim(changeDto.getChangeInvaim());// 投资目的
                bokDetailIdNew.setTradeId(changeDto.getTradeId());// 交易编号
                bokDetailIdNew.setDetailSubjectNoTail(changeDto.getFinprodId());// 四级科目尾号
                bokDetailIdNew.setDetailSubnameTail(changeDto.getFinprodAbbr());// 四级科目尾号名称
                bokDetailIdNew.setBranch(new Long(0));//分支序号默认为0
                BokDetailId bokDetailIdOld = bokDetailIdMapper.selectByPrimaryKey(bokDetailIdNew);
                // 只有不存在的时候,账务明细代码表才插入新的
                if (bokDetailIdOld == null) {
                    bokDetailIdMapper.insert(bokDetailIdNew);
                }

                // 3.入账账务明细表,账务明细代码修改
                BokDealAccountingDetail detail = new BokDealAccountingDetail();
                detail.setBooksetId(changeDto.getBooksetId());// 账套代码
                detail.setBusiNo(changeDto.getTradeId());// 业务编号
                detail.setBusiTable(changeDto.getBusiTable());// 业务表
                detail.setBokDetailId(changeDto.getChangeBookDetailId());// 账务明细代码
                bokDealAccountingDetailMapper.updateByPrimaryKeySelective(detail);
                
                // 4.入账信息表，投资目的修改 【针对杭州2018/12/08分支版本修改】
                BokDealAccounting BokDealAccounting = new BokDealAccounting();
                BokDealAccounting.setBooksetId(changeDto.getBooksetId());// 账套代码
                BokDealAccounting.setBusiNo(changeDto.getTradeId());// 业务编号
                BokDealAccounting.setBusiTable(changeDto.getBusiTable());// 业务表
                BokDealAccounting.setInvAim(changeDto.getChangeInvaim());//投资目的
                bokDealAccountingMapper.updateByPrimaryKeySelective(BokDealAccounting);

            }
        }

    }

}
