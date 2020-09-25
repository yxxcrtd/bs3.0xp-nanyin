/*
 * 文件名：InterfRiskRdsController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.riskrds.controller;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口组件Controller
 * 
 * @author 裴家俊
 * @version 1.0 2020年1月5日
 * @see InterfRiskRdsController
 * @since 1.0
 */
@Controller
@RequestMapping("/riskrds/interf")
public class InterfRiskRdsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    /**
     * 风险接口统一调用入口(画面端)
     * @param dataMap
     * @param eventType  事件类型 0精确委托 1交易 2 拆分 3模糊委托
     * @param infCode    接口代码 RSK001预占 RSK002风险试算 RSK003实占
     * @param assetType  资产大类(对应投管为数据字典平安资产类型)
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("doInfData/{eventType}/{infCode}/{assetType}")
    public String doInfData(@RequestBody Map<String, Object> dataMap, @PathVariable("eventType") String eventType, @PathVariable("infCode") String infCode, @PathVariable("assetType") String assetType)
        throws IOException {
        try {
            if(CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)) {
                logger.info("==========前端交易或委托类转换接口:{} start==========", eventType + infCode);
                if (!StringUtil.isNotEmpty(dataMap) || dataMap.isEmpty()) {
                    throw new NullPointerException("请求报文为空");
                }
                if (StringUtil.isEmpty(eventType) || StringUtil.isEmpty(infCode)/*||StringUtil.isEmpty(assetType)*/) {
                    throw new NullPointerException("请求参数为空");
                }
                InterfRiskRdsDto interfRiskRdsDto = new InterfRiskRdsDto();
                interfRiskRdsDto.setEventType(eventType);
                interfRiskRdsDto.setInfCode(infCode);
                interfRiskRdsDto.setAssetType(assetType);
                logger.info("==========调用共通方法sendRiskRdsDataCommon==========");
                return JsonUtils.object2Json(interfRiskRdsService.sendRiskRdsDataCommon(dataMap, interfRiskRdsDto));
            }else {
                logger.info("==========系统参数中未开启风险试算开关==========");
                return null;
            }
        }
        catch (Exception e) {
            logger.info("==========交易或委托类转换接口 Exception==========\r\n {}", e.getMessage());
            return null;
        }
    }
    
    /**
     * 风险接口统一调用入口(批量画面端)
     * @param dataMap
     * @param eventType  事件类型 0精确委托 1交易 2 拆分 3模糊委托
     * @param infCode    接口代码 RSK001预占 RSK002风险试算 RSK003实占
     * @param assetType  资产大类(对应投管为数据字典平安资产类型)
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("doInfBatchData/{eventType}/{infCode}/{assetType}")
    public List<String> doInfBatchData(@RequestBody Map<String, Object> dataMap, @PathVariable("eventType") String eventType, @PathVariable("infCode") String infCode, @PathVariable("assetType") String assetType)
            throws IOException {
        try {
            if(CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)) {
                logger.info("==========前端交易或委托类转换接口:{} start==========", eventType + infCode);
                if (!StringUtil.isNotEmpty(dataMap) || dataMap.isEmpty()) {
                    throw new NullPointerException("请求报文为空");
                }
                if (StringUtil.isEmpty(eventType) || StringUtil.isEmpty(infCode)) {
                    throw new NullPointerException("请求参数为空");
                }
                InterfRiskRdsDto interfRiskRdsDto = new InterfRiskRdsDto();
                interfRiskRdsDto.setEventType(eventType);
                interfRiskRdsDto.setInfCode(infCode);
                interfRiskRdsDto.setAssetType(assetType);
                logger.info("==========调用共通方法sendRiskRdsDataCommon==========");
                List<String> retList=new ArrayList<>();
                List<Map<String, Object>> listMap=interfRiskRdsService.getBatchAllocation(dataMap,interfRiskRdsDto);
                //多组合循环调用单组合
                for(Map<String, Object> mapEntry:listMap){
                    retList.add(JsonUtils.object2Json(interfRiskRdsService.sendRiskRdsDataCommon(mapEntry, interfRiskRdsDto)));
                }
                return retList;
            }else {
                logger.info("==========系统参数中未开启风险试算开关==========");
                return null;
            }
        }
        catch (Exception e) {
            logger.info("==========交易或委托类转换接口 Exception==========\r\n {}", e.getMessage());
            return null;
        }
    }
}
