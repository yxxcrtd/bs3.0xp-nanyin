/*
 * 文件名：InterfMOPT1001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.imopt1001.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfSendMurexMapper;
import com.joyintech.tams.common.base.entity.InterfSendMurex;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.murex.InterfMurexService;
import com.joyintech.service.interf.murex.imopt1001.dao.InterfMOPT1001Mapper;
import com.joyintech.service.interf.murex.imopt1001.entity.InterfMOPT1001InEntity;
import com.joyintech.service.interf.murex.imopt1001.entity.InterfMOPT1001OutEntity;
import com.joyintech.service.interf.murex.imopt1001.entity.InterfMOPT1001TranOutEntity;

/**
 * 查询交易导入结果信息 接口service
 * 
 * @author changwenbin
 * @version 1.0 2018年11月21日
 * @see InterfMOPT1001Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_MOPT1001)
public class InterfMOPT1001Service extends SendEsbService<InterfMOPT1001InEntity, InterfMOPT1001OutEntity> {

    /**
     * 债券交易导入信息查询Mapper
     */
    @Autowired
    private InterfSendMurexMapper interfSendMurexMapper;

    /**
     * 查询交易导入结果信息Mapper
     */
    @Autowired
    private InterfMOPT1001Mapper interfMOPT1001Mapper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public InterfMOPT1001InEntity preparedLocalData(Object param)
        throws Exception {
        Map<String, Object> dto = (Map<String, Object>)param;
        String key = (String)dto.get(InterfMurexService.KEY);
        InterfSendMurex info = interfSendMurexMapper.selectByPrimaryKey(key);
        if (info == null) {
            return null;
        }
        setDataMap(dto, info.getBusinessType(), info.getSendData());
        return new InterfMOPT1001InEntity(dto);
    }

    /**
     * 设置murex信息到Map
     * 
     * @param dto 目标Map
     * @param type 业务类型：债券、拆借、质押式回购
     * @param data murex信息
     */
    private void setDataMap(Map<String, Object> dto, String type, String data) {
        // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfMOPT1001OutEntity entity)
        throws Exception {
        int result = 0;
        List<InterfMOPT1001TranOutEntity> tranList = entity.getTranlist();
        // 判断 输出entity是否含有明细entity
        if (!StringUtil.isEmptyList(tranList)) {
            // 更新murex导入状态
            for (InterfMOPT1001TranOutEntity tranEntity : tranList) {
                result += interfMOPT1001Mapper.updateInfo(tranEntity);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfMOPT1001OutEntity entity)
        throws Exception {
        // 无需登录业务表
        return 0;
    }

}
