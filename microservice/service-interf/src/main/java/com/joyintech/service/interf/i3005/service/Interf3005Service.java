/*
 * 文件名：Interf3005Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i3005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.StlDvpOrdersMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.entity.StlDvpOrders;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.i3005.entity.Interf3005InEntity;
import com.joyintech.service.interf.i3005.entity.Interf3005OutArrEntity;
import com.joyintech.service.interf.i3005.entity.Interf3005OutEntity;
import com.joyintech.service.interf.order.dto.CusdepOrderDto;

/**
 * 接口3005-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月13日
 * @see Interf3005Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_3005)
public class Interf3005Service extends SendEsbService<Interf3005InEntity, Interf3005OutEntity> {

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
     * 指令信息
     */
    private static final String ORDERDTO = "orderDto";

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf3005InEntity preparedLocalData(Object param)
        throws Exception {
        CusdepOrderDto dto = (CusdepOrderDto)param;
        // 参数为空时
        if (dto == null || StringUtil.isEmpty(dto.getOrderNo())) {
            throw new InterfException("指令编号参数为空");
        }
        setServiceInfo(ORDERDTO, dto);
        Interf3005InEntity inEntity = new Interf3005InEntity();
        inEntity.setSysSeqNo(dto.getOrderNo());
        inEntity.setQueryType("2");
        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf3005OutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf3005OutEntity entity)
        throws Exception {
        List<Interf3005OutArrEntity> itemList = entity.getTranItemList();
        if (StringUtil.isEmptyList(itemList)) {
            return 0;
        }
        CusdepOrderDto dto = (CusdepOrderDto)getServiceInfo(ORDERDTO);
        boolean isDVP = dto.isDvp();
        int result = 0;
        // DVP划款
        if (isDVP) {
            // 更新处理状态
            for (Interf3005OutArrEntity item : itemList) {
                result += updateDvpOrder(item.getSeqNo(), item.getStatusDesc());
            }
        }
        else {
            // 更新处理状态
            for (Interf3005OutArrEntity item : itemList) {
                result += updatePayOrder(item.getSeqNo(), item.getStatusDesc());
            }
        }
        return result;
    }

    /**
     * 更新DVP划款指令状态
     * 
     * @param orderNo 指令编码
     * @param status 处理状态
     * @return 更新行数
     */
    private int updateDvpOrder(String orderNo, String status) {
        String retStatus = changeStatusType(status);
        if (StringUtil.isEmpty(retStatus)) {
            return 0;
        }
        StlDvpOrders dvpInfo = new StlDvpOrders();
        dvpInfo.setOrderId(orderNo);
        // 处理状态
        dvpInfo.setOrderRetStatus(retStatus);
        return dvpOrderMapper.updateByPrimaryKeySelective(dvpInfo);
    }

    /**
     * 更新划款指令状态
     * 
     * @param orderNo 指令编码
     * @param status 处理状态
     * @return 更新行数
     */
    private int updatePayOrder(String orderNo, String status) {
        String retStatus = changeStatusType(status);
        if (StringUtil.isEmpty(retStatus)) {
            return 0;
        }
        StlPayOrders payInfo = new StlPayOrders();
        payInfo.setOrderId(orderNo);
        // 处理状态
        payInfo.setOrderRetStatus(retStatus);
        return orderMapper.updateByPrimaryKeySelective(payInfo);
    }

    /**
     * 数据字典转换
     * 
     * @param status 指令处理状态
     * @return 转换后的值
     */
    private String changeStatusType(String status) {
        if (InterfDictCodeList.托管指令处理状态_成功.getValue().equals(status)) {
            return "01";
        }
        else if (InterfDictCodeList.托管指令处理状态_失败.getValue().equals(status)) {
            return "02";
        }
        else if (InterfDictCodeList.托管指令处理状态_处理中.getValue().equals(status)) {
            return "03";
        }
        else {
            return null;
        }
    }
}
