package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.cfets.etrib.dao.CfetsEtrIbBondinfoMapper;
import com.joyintech.tams.cfets.etrib.dao.WebSocketMapper;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosiInfoRequestDto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosiPage001RequestDto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosinfo001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("WebSocketService")
@Transactional
public class PositionSocketService extends BaseService {

    @Autowired
    private WebSocketMapper webSocketMapper;

    /**
     * 头寸余额变动同步数据
     * @param dataMap
     */
    public void insertOrUpdate(BaseMap dataMap) {
        int count = webSocketMapper.select(dataMap);
        System.out.println(count);
        if (count == 0){
            webSocketMapper.insert(dataMap);
        }else{
            webSocketMapper.update(dataMap);
        }
    }
}
