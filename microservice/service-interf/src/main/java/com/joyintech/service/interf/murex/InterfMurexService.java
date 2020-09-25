/*
 * 文件名：InterfMurexService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dc.eai.data.CompositeData;
import com.joyintech.tams.common.base.dao.InterfSendMurexMapper;
import com.joyintech.tams.common.base.entity.InterfSendMurex;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.SendEsbService;

/**
 * 发送Murex共通类
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月23日
 * @see InterfMurexService
 * @since 1.0
 */
public abstract class InterfMurexService<T, F> extends SendEsbService<T, F> {

    /**
     * 发送Murex信息Mapper
     */
    @Autowired
    private InterfSendMurexMapper interfSendMurexMapper;

    /**
     * 主键
     */
    public static final String KEY = "key";

    /**
     * 发送次数
     */
    private static final String SEND_NUM = "sendNum";

    @Override
    @SuppressWarnings("unchecked")
    public T preparedLocalData(Object param)
        throws Exception {
        Map<String, Object> sendMurex = (Map<String, Object>)param;
        if (sendMurex == null) {
            return null;
        }
        String key = (String)sendMurex.get(KEY);
        InterfSendMurex info = interfSendMurexMapper.selectByPrimaryKey(key);
        setServiceInfo(KEY, key);
        setServiceInfo(SEND_NUM, info == null ? 0 : info.getSendNum());
        return newEntity(sendMurex);
    }

    /**
     * 构建报文Entity
     * 
     * @param sendMurex 查询的发送Murex数据
     * @return 报文Entity
     */
    public abstract T newEntity(Map<String, Object> sendMurex);

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(CompositeData rspData, boolean sendOK, boolean receiveOK) {
        // 更新请求流水号
        InterfSendMurex sendMurex = new InterfSendMurex();
        sendMurex.setUuid((String)getServiceInfo(KEY));
        sendMurex.setBusiSeqNo(getBizSeqNo());
        sendMurex.setSendStatus(
            sendOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        sendMurex.setOrderRecStatus(
            receiveOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        sendMurex.setSendNum((Integer)getServiceInfo(SEND_NUM) + 1);
        interfSendMurexMapper.updateByPrimaryKeySelective(sendMurex);
        super.updateStatus(rspData, sendOK, receiveOK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(F entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(F entity)
        throws Exception {
        // 推送接口无登录业务
        return 0;
    }
}
