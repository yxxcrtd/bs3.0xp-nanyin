/*
 * 文件名：Rsk101Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.riskrdsinf.event.rsk101.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.constant.InfColumns;
import com.joyintech.service.riskrdsinf.core.constant.InfDictCodeList;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk001.entity.Rsk001InEntity;
import com.joyintech.service.riskrdsinf.event.rsk001.service.Rsk001Service;
import com.joyintech.service.riskrdsinf.event.rsk105.service.Rsk105Service;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;


/**
 * 批量预占接口-service
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月1日
 * @see Rsk101Service
 * @since 1.0
 */
@Service
@INF("RSK101")
public class Rsk101Service extends Rsk001Service {

    public static final String DATALIST = "RSK_LIST";

    @Autowired
    private Rsk105Service releaseSrc;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Map<String, Map<String, Rsk001InEntity>> handle(ClientData request,
                                                              ClientData response)
        throws InterfException {
        Map<String, Object> body = request.getBody();
        // 批量列表
        List<Map> mapList = InfMapValUtil.getList(body, DATALIST);
        // 存储处理过的数据，当出现回滚时需要释放这些预占
        List<Map> rlsList = new ArrayList<>();
        // 计算结果
        List resultList = new ArrayList<>();
        ClientData rlsData = new ClientData();
        rlsData.setHead(new HashMap<>());
        rlsData.getHead().putAll(request.getHead());
        // 外层加工的
        body.forEach((k, v) -> {
            if (!(v instanceof List)) {
                rlsData.setBodyField(k, v);
            }
        });
        // 选出每组正反同步委托/交易相同的参数并存到缓存
        if (isSync(body)) pickSameParam(mapList, body);
        try {
            response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
            for (Map item : mapList) {
                ClientData rsp = new ClientData();
                ClientData res = new ClientData();
                res.setHead(request.getHead());
                // 外层加工的
                body.forEach((k, v) -> {
                    if (!(v instanceof List)) {
                        item.put(k, v);
                    }
                });
                res.setBody(item);
                super.handle(res, rsp);
                String rskStatus = (String)response.getBodyField(RSKSTATUS);
                String kriStatus = (String)rsp.getBodyField(RSKSTATUS);
                String type = BussinessDictCodeListRds.运算符_大于等于.getValue();
                response.setBodyField(RSKSTATUS,
                    CommonUtil.compareStr(rskStatus, kriStatus, type) ? rskStatus : kriStatus);
                Object obj = rsp.getBodyField(RSK_LIST);
                if (obj instanceof List) {
                    resultList.addAll((List<Map<String, Object>>)obj);
                }
                // 如果失败，或者异常。弹堆

                // modify by etliu 20210831 start
                // 增加软控硬控开关
                String institutionCode = "";
                if(MultilevelDataAuthorityUtil.isEnabled()){
                    institutionCode =  MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName());
                }
                String isSoft = CacheUtil.getSpecialSysParam("IS_RISKMATH_SOFTCONTROL", institutionCode);
                if (BussinessDictCodeListRds.指标状态_禁止.getValue().equals(
                    rsp.getBodyField(RSKSTATUS)) && "02".equals(isSoft)) {
                    // modify by etliu 20210831 end
                    rollbackStackToRedis(rlsData, rlsList);
                    response.setBodyField(RSKSTATUS, rsp.getBodyField(RSKSTATUS));
                    doException(response, res.getBodyField(EVENT_ID) + "委托超限，所有委托回滚", null);
                    break;
                }
                else {
                    // 暂存
                    rlsList.add(item);
                }
            }
        }
        catch (Exception e) {
            // 回滚redis的信息
            rollbackStackToRedis(rlsData, rlsList);
            doException(response, "额度占用接口处理详细-批量", e);
            response.setBodyField(RSKSTATUS, InfDictCodeList.信息码_拒绝.getValue());
            response.setBodyField("message",
                "额度占用接口处理详细-批量" + "【" + StringUtil.substring(e.getMessage(), 0, 60) + "】");
        }
        finally {
            // 计算结果
            response.setBodyField(RSK_LIST, resultList);
        }
        return null;
    }

    /**
     * 释放占用的额度
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void rollbackStackToRedis(ClientData request, List<Map> rlsList) {
        request.setHeadField(InfColumns.COM_INFID.fieldName(),
            BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue());
        rlsList.forEach(rls -> rls.put("revokeFlg", BussinessDictCodeListRds.是否_是.getValue()));
        request.setBodyField(DATALIST, rlsList);
        releaseSrc.execute(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk001InEntity obj, ClientData response) {
        String ps = obj.getPs();
        String infAssetNoNeedPs = InterfCacheUtil.getSysParam(INF_ASSET_NONEED_PS);
        if (StringUtil.contains(infAssetNoNeedPs, ps)) {
            return false;
        }
        if (StringUtil.isEmpty(obj.getAssetCode())
            && (StringUtil.isEmpty(obj.getMarketCode()) || StringUtil.isEmpty(obj.getTradePlace()))
            && StringUtil.isEmpty(obj.getAssetType())) {
            doException(response, "资产大类、资产代码、以及市场代码和交易场所不能同时为空", null);
            return true;
        }
        return false;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected boolean checkRequestErr(ClientData request, ClientData response) {
        // 取得报文体
        Map<String, Object> bodyMap = request.getBody();
        // 试算列表
        List<Map> mapList = new ArrayList<>();
        try {
            mapList = InfMapValUtil.getList(bodyMap, DATALIST);
        }
        catch (InterfException e) {
            getLogger().error("处理异常", e);
            return true;
        }
        for (Map item : mapList) {
            Rsk001InEntity info = InfMapValUtil.mapDataToObject(item, Rsk001InEntity.class);
            if (checkErr(info, response))
                return true;
        }
        return false;
    }

}
