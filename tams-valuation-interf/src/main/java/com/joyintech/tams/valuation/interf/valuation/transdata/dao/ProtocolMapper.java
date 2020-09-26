/*
 * 文件名：ProtocolMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.transdata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ValInterfTemplate;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvServerInfoEntity;

/**
 * 协议相关的DAO 
 * 
 * @author 王波
 * @version 1.0 2018年6月20日
 * @see ProtocolMapper
 * @since 1.0
 */
@Repository
public interface ProtocolMapper {
    /**
     * 获取服务信息
     *
     * @return
     */
    public List<ValRecvServerInfoEntity> getValServerInfo(Map<String, Object> params);

	public List<ValInterfTemplate> queryTempListByInterfList(Map<String, Object> params);
}
