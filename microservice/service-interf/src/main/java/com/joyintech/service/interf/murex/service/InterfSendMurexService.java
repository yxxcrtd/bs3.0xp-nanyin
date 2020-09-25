/*
 * 文件名：InterfSendMurexService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfSendMurexMapper;
import com.joyintech.tams.common.base.entity.InterfSendMurex;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;

/**
 * 发送murex查询登录类
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月27日
 * @see InterfSendMurexService
 * @since 1.0
 */
@Service
public class InterfSendMurexService {

    @Autowired
    private InterfSendMurexMapper baseMapper;

    /**
     * 登录发送Murex接口表
     * 
     * @param infoList 发送Murex信息List
     * @return UUID列表
     */
    public List<String> insertMurexInfoList(List<InterfSendMurex> infoList) {
        if (StringUtil.isEmptyList(infoList)) {
            return new ArrayList<>();
        }
        List<String> resultList = new ArrayList<>();
        for (InterfSendMurex info : infoList) {
            String uuid = insertMurexInfo(info);
            if (StringUtil.isNotEmpty(uuid)) {
                resultList.add(uuid);
            }
        }
        return resultList;
    }

    /**
     * 登录发送Murex接口表
     * 
     * @param info 发送Murex信息
     * @return 登录数据的UUID
     */
    public String insertMurexInfo(InterfSendMurex info) {
        if (info == null) {
            return null;
        }
        String uuid = info.getUuid();
        // 如果UUID没有设置或者长度超过32位时，获取新的UUID
        if (StringUtil.isEmpty(uuid) || StringUtil.length(uuid) > 32) {
            uuid = IDGenerator.generate(32);
            info.setUuid(uuid);
        }
        baseMapper.insert(info);
        // 插入数据库
        return uuid;
    }
}
