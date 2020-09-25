/*
 * 文件名：InterfSendMurexController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.InterfSendMurex;
import com.joyintech.service.interf.core.annotation.ThreadPoolExcuted;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.controller.BaseController;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.murex.InterfMurexService;
import com.joyintech.service.interf.murex.service.InterfSendMurexService;

import net.minidev.json.JSONObject;

/**
 * 发送MUREX信息入口
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月27日
 * @see InterfSendMurexController
 * @since 1.0
 */
@RestController
@ThreadPoolExcuted
public class InterfSendMurexController extends BaseController<Map<String, Object>> {

    @Autowired
    private InterfSendMurexService murexService;

    /**
     * 发送MUREX信息入口
     * 
     * @param param 数据信息
     * @return 保存的数据UUID
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/tzConsumer/sendMurex")
    public List<InterfSendMurex> sendMurex(@RequestBody Map<String, Object> param) {
        setSysDate((String)param.get(InterfConstant.SYS_DATE));
        String caller = (String)param.get(InterfConstant.CALL_MODEL);
        // 取得产品列表
        List<Map<String, Object>> murexList = (List<Map<String, Object>>)param.get(
            InterfConstant.DATA_LIST);
        List<InterfSendMurex> result = saveData(caller, murexList);
        // 调用接口
        excuteService(murexList, true);

        return result;
    }

    /**
     * 数据入库
     * 
     * @param caller 调用接口
     * @param mapList 发送Murex数据列表
     * @return uuid列表
     */
    private List<InterfSendMurex> saveData(String caller, List<Map<String, Object>> mapList) {
        List<InterfSendMurex> infoList = new ArrayList<>();
        Date sendDate = new Date();
        String user = "interf";
        for (Map<String, Object> map : mapList) {
            InterfSendMurex info = new InterfSendMurex();
            String json = JSONObject.toJSONString(map);
            info.setBusinessType(caller);
            info.setSendData(json);
            info.setSendDate(sendDate);
            info.setCreateTime(sendDate);
            info.setUpdateTime(sendDate);
            info.setCreateUser(user);
            info.setUpdateUser(user);
            info.setSendNum(0);
            String uuid = murexService.insertMurexInfo(info);
            map.put(InterfMurexService.KEY, uuid);
            map.put(InterfConstant.CALL_MODEL, caller);
            infoList.add(info);
        }
        return infoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doResult(Map<String, Object> dto, SendEsbService<?, ?> service) {
        // Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SendEsbService<?, ?> getService(Map<String, Object> t) {
        return ServiceMapperUtil.getTradeService((String)t.get(InterfConstant.CALL_MODEL));
    }
}
