/*
 * 文件名：InterfCusdep1020service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icusdep1020.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.eai.data.CompositeData;
import com.joyintech.tams.common.base.dao.PtlCapacctInfoMapper;
import com.joyintech.tams.common.base.dao.StlCapacctInfoMapper;
import com.joyintech.tams.common.base.dao.StlDvpOrdersMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.entity.PtlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlDvpOrders;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.icusdep1020.entity.InterfCusdep1020InEntity;
import com.joyintech.service.interf.icusdep1020.entity.InterfCusdep1020OutEntity;
import com.joyintech.service.interf.icusdep1020.entity.PayAcctInfo;
import com.joyintech.service.interf.order.dto.CusdepOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 接口CUSDEP1020-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月23日
 * @see InterfCusdep1020service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_CUSDEP1020)
public class InterfCusdep1020service extends SendEsbService<InterfCusdep1020InEntity, InterfCusdep1020OutEntity> {

    /**
     * DVP划款查询Mapper
     */
    @Autowired
    private StlDvpOrdersMapper dvpOrderMapper;

    /**
     * 划款指令查询
     */
    @Autowired
    private StlPayOrdersMapper orderMapper;

    /**
     * 清算账户查询Mapper
     */
    @Autowired
    private StlCapacctInfoMapper stlAcctInfoMapper;

    /**
     * 组合清算账户查询Mapper
     */
    @Autowired
    private PtlCapacctInfoMapper ptlAcctInfoMapper;

    /**
     * 指令信息
     */
    private static final String ORDERDTO = "orderDto";

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfCusdep1020InEntity preparedLocalData(Object param)
        throws Exception {
        CusdepOrderDto dto = (CusdepOrderDto)param;
        // 参数为空时
        if (dto == null || StringUtil.isEmpty(dto.getOrderNo())) {
            throw new InterfException("指令编号参数为空");
        }
        boolean isDVP = dto.isDvp();
        // 本方资金账户
        String myAcctNo = null;
        // 对手方资金账户
        String counterAcctNo = null;
        // 币种
        String ccy = null;
        // 组合代码
        String portfolioId = null;
        // 收支方向
        String ps = null;
        // 指令编号
        String orderNo = dto.getOrderNo();
        setServiceInfo(ORDERDTO, dto);
        InterfCusdep1020InEntity inEntity = new InterfCusdep1020InEntity();
        inEntity.setDVP(isDVP);
        if (isDVP) {
            // 取得DVP划款信息
            StlDvpOrders dvpInfo = dvpOrderMapper.selectByPrimaryKey(orderNo);
            // 指令信息不存在时
            if (dvpInfo == null) {
                throw new InterfException(orderNo + "对应的指令不存在");
            }
            inEntity.setPaDvpPay(dvpInfo);
            myAcctNo = dvpInfo.getMyAcctNo();
            counterAcctNo = dvpInfo.getCounterAcctNo();
            ccy = dvpInfo.getCcy();
            portfolioId = dvpInfo.getPortfolioId();
            ps = dvpInfo.getPs();
        }
        else {
            // 取得划款信息
            StlPayOrders payInfo = orderMapper.selectByPrimaryKey(orderNo);
            // 指令信息不存在时
            if (payInfo == null) {
                throw new InterfException(orderNo + "对应的指令不存在");
            }
            inEntity.setPaPay(payInfo);
            myAcctNo = payInfo.getMyAcctNo();
            counterAcctNo = payInfo.getCounterAcctNo();
            ccy = payInfo.getCcy();
            portfolioId = payInfo.getPortfolioId();
            ps = payInfo.getPs();
        }

        // 本方资金账户信息
        PayAcctInfo myAcct = getAcctInfo(myAcctNo, ccy, portfolioId);
        // 对方资金账户信息
        PayAcctInfo heAcct = getAcctInfo(counterAcctNo, ccy, portfolioId);
        // 当收付方向为收时，本方账户为收款方，对方账户为付款方
        if (DictCodeListExt1.收支方向_收入.getValue().equals(ps)) {
            inEntity.setPayerInfo(heAcct);
            inEntity.setPayeeInfo(myAcct);
        }
        // 当收付方向为付时，本方账户为付款方，对方账户为收款方
        else {
            inEntity.setPayerInfo(myAcct);
            inEntity.setPayeeInfo(heAcct);
        }
        // 设置系统编号
        inEntity.setTransferSysFlag(getConsumerId());
        return inEntity;
    }

    /**
     * 取得账户信息
     * 
     * @param acctNo 账号
     * @param ccy 币种
     * @param folioId 组合代码
     * @return 账户信息
     * @see
     * @since 1.0
     */
    private PayAcctInfo getAcctInfo(String acctNo, String ccy, String folioId) {
        PayAcctInfo acctInfo = new PayAcctInfo();
        // 取得清算表账户信息
        StlCapacctInfo acct = new StlCapacctInfo();
        acct.setAcctNo(acctNo);
        acct.setCcy(ccy);
        acct = stlAcctInfoMapper.selectByPrimaryKey(acct);
        acctInfo.setAcctInfo(acct);
        // 从组合清算账户表取得账户类型信息
        PtlCapacctInfo ptl = new PtlCapacctInfo();
        ptl.setAcctNo(acctNo);
        ptl.setCcy(ccy);
        ptl.setPortfolioId(folioId);
        ptl = ptlAcctInfoMapper.selectByPrimaryKey(ptl);
        if (ptl != null) {
            acctInfo.setAcctType(ptl.getAcctType());
        }
        return acctInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(CompositeData rspData, boolean sendOK, boolean receiveOK) {
        CusdepOrderDto dto = (CusdepOrderDto)getServiceInfo(ORDERDTO);
        if (dto.isDvp()) {
            // 更新DVP划款指令状态
            updateDvpOrder(dto.getOrderNo(), sendOK, receiveOK);
        }
        else {
            // 更新划款指令状态
            updatePayOrder(dto.getOrderNo(), sendOK, receiveOK);
        }
        super.updateStatus(rspData, sendOK, receiveOK);
    }

    /**
     * 更新DVP划款指令状态
     * 
     * @param orderNo 指令编码
     * @param sendOK 发送状态
     * @param receiveOK 指令接收状态
     */
    private void updateDvpOrder(String orderNo, boolean sendOK, boolean receiveOK) {
        StlDvpOrders dvpInfo = new StlDvpOrders();
        dvpInfo.setOrderId(orderNo);
        // 发送时间
        dvpInfo.setSendDate(new Date());
        // 发送状态
        dvpInfo.setSendStatus(
            sendOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        // 指令接收状态
        dvpInfo.setOrderRecStatus(
            receiveOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        dvpOrderMapper.updateByPrimaryKeySelective(dvpInfo);
    }

    /**
     * 更新划款指令状态
     * 
     * @param orderNo 指令编码
     * @param sendOK 发送状态
     * @param receiveOK 指令接收状态
     */
    private void updatePayOrder(String orderNo, boolean sendOK, boolean receiveOK) {
        StlPayOrders payInfo = new StlPayOrders();
        payInfo.setOrderId(orderNo);
        // 发送时间
        payInfo.setSendDate(new Date());
        // 发送状态
        payInfo.setSendStatus(
            sendOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        // 指令接收状态
        payInfo.setOrderRecStatus(
            receiveOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        orderMapper.updateByPrimaryKeySelective(payInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfCusdep1020OutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfCusdep1020OutEntity entity)
        throws Exception {
        return 0;
    }
}
