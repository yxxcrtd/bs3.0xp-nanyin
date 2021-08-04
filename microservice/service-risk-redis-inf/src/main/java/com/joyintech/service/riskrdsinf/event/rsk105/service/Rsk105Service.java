/*
 * 文件名：Rsk105Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.riskrdsinf.event.rsk105.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.redis.common.entity.IValue;
import com.joyintech.redis.msg.entity.MessageBody.Operation;
import com.joyintech.service.riskrdsinf.common.service.OccupyReleasService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.constant.InfColumns;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk005.entity.Rsk005InEntity;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval001Dto;
import com.joyintech.service.riskrdsinf.process.service.RskApproval001Service;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOccupyRelParam;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOcpRelParamItem;
import com.joyintech.tams.invest.common.riskinfrds.occupy.service.OccupyRelParamService;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamValService;


/**
 * 批量预占释放接口-service
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月1日
 * @see Rsk105Service
 * @since 1.0
 */
@Service
@INF("RSK105")
public class Rsk105Service extends OccupyReleasService<Rsk005InEntity> {

    /**
     * 试算列表
     */
    public static final String DATALIST = "RSK_LIST";

    @Autowired
    private RskApproval001Service processSrc;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void saveData(Map<String, Object> mapDatas, boolean isApprove, String user)
        throws InterfException {
        try {
            // 取得批量处理对象
            List<Map> dataList = InfMapValUtil.getList(mapDatas, DATALIST);
            dataList.forEach(map -> {
                RskPreOccupyInfo record = InfMapValUtil.mapDataToObject(map,
                    RskPreOccupyInfo.class);
                // 组合代码
                record.setProdId((String)map.get(PORTFOLIOCODE));
                // 预占到期日
                record.setOccupyDate(
                    record.getOccupyDate() == null ? record.getEventDate() : record.getOccupyDate());
                // 委托代码
                record.setEntrustCode(
                    record.getEntrustCode() == null ? record.getEventId() : record.getEntrustCode());
                // 报文流水号
                record.setSeqNo(getSeqNo());
                // 报文流水号
                getBusiService().savePreOcpInfo(record, isApprove, STATUS_RELEASE, user);
            });
        }
        catch (Exception e) {
            getLogger().error("预占释放信息保存异常", e);
            throw new InterfException("预占释放信息保存异常");
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected Map<String, Map<String, Rsk005InEntity>> handle(ClientData request, ClientData response)
        throws InterfException {
        // 指标参数计算明细
        List<RskIndicatorParaCalcdetail> detailList = new ArrayList<>();
        // 取得批量处理对象
        List<Map> dataList = InfMapValUtil.getList(request.getBody(), DATALIST);
        // 参数值读写Service
        final IndicatorParamValService paramValSrv = getParamValSrv();
        // 预占参数信息
        List<IOccupyRelParam> listIOccupyRel = new ArrayList<>();
        // 取得所有指标参数的值
        Map<String, Map<String, IValue<Object>>> paraValMapSum = new HashMap<>();
        // 需要删除的预占编号列表
        List<String> fields = new ArrayList<>();
        boolean isPreOcp = true;
        handleByLock(()->{
            Map<String, IOccupyRelParam> ocpInfoMap = getOcpInfoList(dataList);
            long cUUID = 0L;
            for (Map map : dataList) {
                long temp = System.currentTimeMillis();
                if (temp <= cUUID) {
                    temp = cUUID + 1;
                }
                cUUID = temp;
                map.put("uuid", String.valueOf(temp));
                ClientData res = new ClientData();
                res.setBody(map);
                // 汇率转换
                exchangeCcy(res);
                Map<String, Object> mapData = new HashMap<>();
                // 取得报文体
                mapData.putAll(map);
                // 从报文体取得预占/实占编号
                String occupyCode = (String)mapData.get(EVENT_ID);
                // 从Redis取得预占/实占信息
                IOccupyRelParam ocpRelParam = ocpInfoMap.get(
                    occupyCode + GlobalConstant.COLON + getOccupyType());
                // 若预占/实占信息不存在
                if (ocpRelParam == null) {
                    response.setHeadField(InfColumns.COM_STDREMARK.fieldName(),
                        (getOccupyType() == PRE_OCCUPY ? "预占" : "实占") + "对应的信息不存在!");
                    continue;
                }
                // 没有任何风险特征值也存实占编号
                if (CollectionUtils.isEmpty(ocpRelParam.getItems())) {
                    if (!isRevoke(mapData)) {
                        listIOccupyRel.add(ocpRelParam);
                    }
                    // 撤销的场合，直接删除预占实占信息
                    else {
                        fields.add(occupyCode + GlobalConstant.COLON + getOccupyType());
                    }
                    continue;
                }
                // 净值
                calcAmount(mapData, () -> ocpRelParam.getNetPrice());
                // 预占/实占信息相关参数列表
                Map<String, IOcpRelParamItem> items = ocpRelParam.getItems();
                // 构造取参数值的条件
                Map<String, Map<String, IValue<Object>>> hashList = makeHashList(items, mapData, true);
                IOccupyRelParam preOcpParam2 = getPreOcpInfo(ocpRelParam, isPreOcp, hashList, mapData);
                List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
                // 取得所有指标参数的值
                Map<String, Map<String, IValue<Object>>> paraValMap = paramValSrv.getParamVal(
                    hashList);
                mergeMap(paraValMap, paraValMapSum, true);
                // 若指标参数值未初始化到Redis则抛出异常
                if (CollectionUtils.isEmpty(paraValMap)) {
                    throw new InterfException("指标参数值不存在");
                }
                // 计算参数值、预占/实占额度
                calcParamVal(paraValMap, mapData, items, details, preOcpParam2);

                details.forEach(single -> {
                    // 业务编号
                    single.setTradeId(occupyCode);
                    // 业务类型
                    single.setTradeType((String)mapData.get(EVENT_TYPE));
                    // 设置监督日期
                    single.setSupDate(DateUtil.parse(mapData.get("eventDate").toString()));
                    // 设置时间戳
                    Object obj = mapData.get("uuid");
                    single.setDotimestamp(DateUtil.format(new Date(new Long(obj.toString())),
                        DateUtil.DEFAULT_DATETIME_FORMAT_SSS));
                    // 设置UUID
                    single.setUuid(IDGenerator.generate());
                    // 创建人
                    single.setCreateUser(getInfId());
                    // 更新人
                    single.setUpdateUser(getInfId());
                });

                mergeMap(paraValMapSum, paraValMap, false);
                detailList.addAll(details);

                if (!isRevoke(mapData, isPreOcp, ocpRelParam) || isDiffOcp(mapData)
                    // 或者如果是委托完成时
                    || StringUtil.isNotEmpty(mapData.get(ETRCOMPLETEFLG))) {
                    listIOccupyRel.add(ocpRelParam);
                }
                // 撤销的场合，直接删除预占实占信息
                else {
                    fields.add(occupyCode + GlobalConstant.COLON + getOccupyType());
                }
            }
            // 更新参数值信息
            paramValSrv.setParamVal(paraValMapSum);
            if (!CollectionUtils.isEmpty(fields)) {
                getOcpService().deleteInfoFromRedis(RedisKeyEnum.预占实占信息.key(),
                    fields.toArray(new String[fields.size()]));
            }
            if (!StringUtil.isEmptyList(listIOccupyRel)) {
                getOcpService().saveToRedis(
                    listIOccupyRel.toArray(new IOccupyRelParam[listIOccupyRel.size()]));
            }
            return null;
        });
        saveData(request.getBody(), false, getInfId());
        // 参数计算明细
        if (!StringUtil.isEmptyList(detailList)) {
            saveDb(Operation.W,
                detailList.toArray(new RskIndicatorParaCalcdetail[detailList.size()]));
        }
        return null;
    }

    /**
     * 将newMap内容覆盖到targetMap中去
     * 
     * @param targetMap 目标MAP
     * @param newMap 变化MAP
     */
    protected void mergeMap(final Map<String, Map<String, IValue<Object>>> targetMap,
                            Map<String, Map<String, IValue<Object>>> newMap, boolean isOverExist) {
        if (CollectionUtils.isEmpty(newMap)) {
            return;
        }
        newMap.entrySet().forEach(en -> {
            String key = en.getKey();
            Map<String, IValue<Object>> temp = targetMap.getOrDefault(key, new HashMap<>());
            en.getValue().entrySet().forEach(valEn -> {
                String key2 = valEn.getKey();
                if (!isOverExist || (temp.containsKey(key2) && isOverExist)) {
                    temp.put(key2, valEn.getValue());
                }
            });
            if (!CollectionUtils.isEmpty(temp)) {
                targetMap.put(key, temp);
            }
        });
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
            Rsk005InEntity info = InfMapValUtil.mapDataToObject(item, Rsk005InEntity.class);
            if (checkErr(info, response)) return true;
        }

        return false;
    }

    @SuppressWarnings("rawtypes")
    protected Map<String, IOccupyRelParam> getOcpInfoList(List<Map> dataList) {
        OccupyRelParamService ocpService = getOcpService();
        List<String> fields = new ArrayList<>();
        dataList.forEach(map -> fields.add((String)map.get(EVENT_ID) + GlobalConstant.COLON + getOccupyType()));
        return ocpService.getMapFromRedis(RedisKeyEnum.预占实占信息.key(), fields);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk005InEntity obj, ClientData response) {
        RskApproval001Dto dto = new RskApproval001Dto();
        dto.setEventId(obj.getEventId());
        if (processSrc.isInWorkflow(dto)) {
            doException(response, "风险审批流程中的交易不允许撤销", null);
            return true;
        }
        return false;
    }
}
