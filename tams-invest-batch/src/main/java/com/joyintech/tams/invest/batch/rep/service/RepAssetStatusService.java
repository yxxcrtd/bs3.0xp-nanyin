/*
 * 文件名：RepAssetStatusService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.rep.service;

import com.joyintech.tams.invest.batch.rep.dto.RepAssetStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.batch.rep.dao.RepAssetStatusMapper;

/**
 *  中债资产报送状态
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月15日
 * @see RepAssetStatusService
 * @since 1.0
 */
@Service
public class RepAssetStatusService {

    @Autowired
    private RepAssetStatusMapper repAssetStatusMapper;
    
    public void run(){
        RepAssetStatusDto dto = new RepAssetStatusDto();

        repAssetStatusMapper.updateRepAssetStatus(dto);
    }
}
