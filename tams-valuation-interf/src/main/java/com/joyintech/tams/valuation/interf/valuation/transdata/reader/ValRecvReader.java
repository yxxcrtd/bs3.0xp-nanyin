/*
 * 文件名：ProtocolRecvReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.transdata.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.interf.core.InterfBaseReader;
import com.joyintech.tams.valuation.interf.valuation.transdata.dao.ProtocolMapper;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvServerInfoEntity;

@Service
@Scope("prototype")
public class ValRecvReader extends InterfBaseReader<ValRecvServerInfoEntity>{

    @Autowired
    private ProtocolMapper protocolMapper;
    
    @Override
    public List<ValRecvServerInfoEntity> createList() {
        return protocolMapper.getValServerInfo(this.getParams());
    }

}
