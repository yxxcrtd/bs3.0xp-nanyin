package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.cfets.etrib.dao.WebSocketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("WebSocketService")
@Transactional
public class PositionSocketService {

    @Autowired
    private WebSocketMapper webSocketMapper;

    /**
     * 头寸余额变动同步数据
     * @param dataMap
     */
    public void insertOrUpdate(BaseMap dataMap) {
        int count = webSocketMapper.select(dataMap);
        if (count == 0){
            webSocketMapper.insert(dataMap);
        }else{
            webSocketMapper.update(dataMap);
        }
    }
}
