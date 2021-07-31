/*
 * 文件名：RiskRouterService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.riskrds.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProductConvertible;
import com.joyintech.tams.component.business.riskrds.constant.HeadEnum;
import com.joyintech.tams.component.business.riskrds.constant.ModelConstant;
import com.joyintech.tams.component.business.riskrds.dao.RiskRdsTrialCommonMapper;
import com.joyintech.tams.component.business.riskrds.filter.IRiskModelFilter;
import com.joyintech.tams.component.business.riskrds.send.IRiskSender;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

/**
 * 风控路由服务
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月9日
 * @see RiskRouterService
 * @since 1.0
 */
@Service
public class RiskRouterService {

    /**
     * 调度器
     */
    @Autowired(required = false)
    private IRiskModelFilter riskModelFilter;

    @Autowired
    private RiskRdsTrialCommonMapper trialCommonMapper;

    /**
     * 报文发送器
     */
    @Autowired(required = false)
    private IRiskSender riskSender;

    public static final String INF_RSK_CALC_LIST = "rskCalcList";

    public static final String INF_RSK_LIST = "RSK_LIST";

    /**
     * 调用风控入口
     * 
     * @param infCode 接口代码
     * @param pageId 画面ID
     * @param dto 画面内容实体
     * @return 计算结果
     */
    public <T> AjaxResult execute(String infCode, String pageId, T dto) {
        return execute(infCode, pageId, dto, null);
    }

    /**
     * 调用风控入口
     * 
     * @param infCode 接口代码
     * @param pageId 画面ID
     * @param dtoList 画面内容实体List
     * @return 计算结果
     */
    public <T> AjaxResult execute(String infCode, String pageId, List<T> dtoList) {
        return execute(infCode, pageId, dtoList, null);
    }

    /**
     * 调用风控入口
     * 
     * @param infCode 接口代码
     * @param pageId 画面ID
     * @param dtoList 画面内容实体List
     * @param consumer 报文头处理接口
     * @return 计算结果
     */
    public <T> AjaxResult execute(String infCode, String pageId, List<T> dtoList,
                                  Consumer<Map<String, Object>> consumer) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return null;
        }
        if (isSingleInf(infCode)) {
            throw new BusinessException("批量处理时需使用批量接口");
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (null != riskModelFilter) {
            // 解析构建批量报文体
            dtoList.forEach(dto -> addItem(infCode, mapList, riskModelFilter.filter(pageId, dto)));
        }
        if (CollectionUtils.isEmpty(mapList)) {
            return null;
        }
        Map<String, Object> body = new HashMap<>();
        body.put(getListKey(infCode), mapList);
        return execute(infCode, body, consumer);
    }

    /**
     * 调用风控入口
     * 
     * @param infCode 接口代码
     * @param body 报文体
     * @param consumer 报文头处理接口
     * @return 计算接口
     */
    public AjaxResult execute(String infCode, Map<String, Object> body,
                              Consumer<Map<String, Object>> consumer) {
        // 报文体不为空则发送报文
        if (body != null) {
            // 创建报文头
            Map<String, Object> headParam = createHead(infCode);
            // 报文头处理接口处理
            if (consumer != null) {
                consumer.accept(headParam);
            }
            // 发送报文返回结果
            return riskSender.send(headParam, body);
        }
        return null;
    }

    /**
     * 调用风控入口
     * 
     * @param infCode 接口代码
     * @param pageId 画面ID
     * @param dto 画面内容实体
     * @param consumer 报文头处理接口
     * @return 计算结果
     */
    public <T> AjaxResult execute(String infCode, String pageId, T dto,
                                  Consumer<Map<String, Object>> consumer) {
        List<Map<String, Object>> list = riskModelFilter.filter(pageId, dto);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 非批量接口
        else if (list.size() == 1 && null != list.get(0) && isSingleInf(infCode)) {
            return execute(infCode, list.get(0), consumer);
        }
        // 批量接口
        Map<String, Object> body = new HashMap<>();
        // 转换批量接口
        infCode = changeToBatchInf(infCode);
        // 生成报文体
        body.put(getListKey(infCode), list);
        return execute(infCode, body, consumer);
    }

    /**
     * 取得 批量报文主键
     * 
     * @param infCode 接口代码
     * @return 批量报文主键
     */
    protected String getListKey(String infCode) {
        return isClacList(infCode) ? INF_RSK_CALC_LIST : INF_RSK_LIST;
    }

    /**
     * 是否批量试算
     * 
     * @param infCode 接口代码
     * @return true：是
     */
    protected boolean isClacList(String infCode) {
        return CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_风险试算批量接口);
    }

    private void addItem(String infCode, List<Map<String, Object>> mapList,
                         List<Map<String, Object>> map) {
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        map.forEach(item -> {
            // 共通字段处理
            putCommon(infCode, item);
            if (!CollectionUtils.isEmpty(item)) {
                mapList.add(item);
            }
        });
    }

    /**
     * 设置一些共通字段
     * 
     * @param infCode 接口代码
     * @param map 报文体
     */
    private void putCommon(String infCode, Map<String, Object> map) {
        // 预警时是否确认
        map.put(ModelConstant.INF_ISWARNNEEDCONFIRM, DictCodeList.是否_否.getValue());
        // 审批时是否确认
        map.put(ModelConstant.INF_ISAPPRNEEDCONFIRM, DictCodeList.是否_否.getValue());

        // 释放需要的字段
        if (CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占释放接口)
            || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占释放批量接口)
            || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_预占释放接口)
            || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_预占释放批量接口)) {
            map.put(ModelConstant.INF_REVOKEFLG, DictCodeList.是否_是.getValue());
        }
        if (CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_预占接口)
            || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_预占批量接口)
            || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_预占释放接口)
            || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_预占释放批量接口)) {
            // 事件类型
            map.put(ModelConstant.INF_EVENTTYPE, BussinessDictCodeListRds.事件类型_投资委托.getValue());
        }
        else if (CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占接口)
                 || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占批量接口)
                 || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占释放接口)
                 || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占释放批量接口)) {
            // 事件类型
            map.put(ModelConstant.INF_EVENTTYPE, BussinessDictCodeListRds.事件类型_投资交易.getValue());
        }
    }

    /**
     * 创建报文头信息
     * 
     * @param infCode 接口代码
     * @return 报文头信息
     */
    protected Map<String, Object> createHead(String infCode) {
        Map<String, Object> header = new HashMap<>();
        header.put(HeadEnum.STDPROCODE.field(), infCode);
        header.put(HeadEnum.STDMSGTYPE.field(), "0001");
        String systemCode = StringUtil.defaultString(
            CacheUtil.getSysParam(HeadEnum.STD400BSYS.field()), "RISk");
        header.put(HeadEnum.STD400BSYS.field(), systemCode);
        header.put(HeadEnum.STD400AQID.field(), systemCode);
        // 设置 发起方流水号
        String sysDate = DateUtil.getSysDate();
        sysDate = StringUtil.remove(sysDate, "-");
        long time = System.currentTimeMillis();
        String tail = "00000" + time;
        header.put(HeadEnum.STDTERMTRC.field(), systemCode + sysDate + tail);
        // 设置 发起方系统日期
        String curTime = DateUtil.getCurrentDateTime();
        String curDt = StringUtil.substring(curTime, 0, 10);
        String curhms = StringUtil.substring(curTime, 11);
        header.put(HeadEnum.STD400RBDT.field(), StringUtil.remove(curDt, "-"));
        // 设置 发起方系统时间戳
        header.put(HeadEnum.STD400RSTS.field(), DateUtil.getCurrentDateTime());
        // 设置 发起方系统时间
        header.put(HeadEnum.STD400RTIM.field(), StringUtil.remove(curhms, ":"));
        return header;
    }

    /**
     * 调用风控入口
     * 
     * @param infCode 接口代码
     * @param pageId 画面ID
     * @param dtoList 画面内容实体List
     * @param consumer 报文头处理接口
     * @return 计算结果
     */
    public <T> ClientData executeClient(String infCode, String pageId, List<T> dtoList,
                                  Consumer<Map<String, Object>> consumer) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return null;
        }
        if (isSingleInf(infCode)) {
            throw new BusinessException("批量处理时需使用批量接口");
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        // 解析构建批量报文体
        dtoList.forEach(dto -> addItem(infCode, mapList, riskModelFilter.filter(pageId, dto)));
        if (CollectionUtils.isEmpty(mapList)) {
            return null;
        }
        Map<String, Object> body = new HashMap<>();
        body.put(getListKey(infCode), mapList);
        return executeClient(infCode, body, consumer);
    }

    /**
     * 调用风控入口
     * 
     * @param infCode 接口代码
     * @param body 报文体
     * @param consumer 报文头处理接口
     * @return 计算接口
     */
    public ClientData executeClient(String infCode, Map<String, Object> body,
                              Consumer<Map<String, Object>> consumer) {
        // 报文体不为空则发送报文
        if (body != null) {
            // 创建报文头
            Map<String, Object> headParam = createHead(infCode);
            // 报文头处理接口处理
            if (consumer != null) {
                consumer.accept(headParam);
            }
            // 发送报文返回结果
            return riskSender.clientSend(headParam, body);
        }
        return null;
    }

    private static final Map<String, String> INF_MAP = new HashMap<>();
    static {
        INF_MAP.put(BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),
            BussinessDictCodeListRds.接口系统ID_实占批量接口.getValue());
        INF_MAP.put(BussinessDictCodeListRds.接口系统ID_预占接口.getValue(),
            BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue());
        INF_MAP.put(BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue(),
            BussinessDictCodeListRds.接口系统ID_实占释放批量接口.getValue());
        INF_MAP.put(BussinessDictCodeListRds.接口系统ID_预占释放接口.getValue(),
            BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue());
        INF_MAP.put(BussinessDictCodeListRds.接口系统ID_风险试算接口.getValue(),
            BussinessDictCodeListRds.接口系统ID_风险试算批量接口.getValue());
    }

    /**
     * 是否非批量接口
     * 
     * @param infCode 接口ID
     * @return true : 非批量接口
     */
    public boolean isSingleInf(String infCode) {
        return INF_MAP.containsKey(infCode);
    }

    /**
     * 转化批量接口
     * 
     * @param infCode 接口ID
     * @return 批量接口ID
     */
    public String changeToBatchInf(String infCode) {
        return INF_MAP.get(infCode);
    }

    protected <T> List<Map<String, Object>> changeToMapList(List<T> dtoList) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        // 转化成Map
        dtoList.forEach(dto -> mapList.add(BeanUtil.object2Map(dto)));
        // 设置债转股中的股票代码
        doConFinprodId(mapList);
        return mapList;
    }

    /**
     * 设置债转股的股票代码
     * 
     * @param mapList 报文
     * @param conFinprodId 债转股映射信息
     */
    protected void doConFinprodId(List<Map<String, Object>> mapList) {
        if (!CollectionUtils.isEmpty(mapList)) {
            Map<String, String> conFinprodId = getConFinprodId(mapList);
            if (CollectionUtils.isEmpty(conFinprodId)) {
                return;
            }
            for (Map<String, Object> map : mapList) {
                if (StringUtil.isNotEmpty(conFinprodId.get(map.get(ModelConstant.FINPRODID)))) {
                    map.put(ModelConstant.CONFINPRODID,
                        conFinprodId.get(map.get(ModelConstant.FINPRODID)));
                }
            }
        }
    }

    /**
     * 取得 债转股信息的债券股票对应关系
     * 
     * @param mapList 批量数据
     * @return 债转股信息的债券股票对应关系
     */
    protected Map<String, String> getConFinprodId(List<Map<String, Object>> mapList) {
        if (CollectionUtils.isEmpty(mapList)) {
            return new HashMap<>();
        }
        List<String> finprodIdList = mapList.stream().filter(
            map -> StringUtil.isNotEmpty(map.get(ModelConstant.FINPRODID))
                   && !StringUtil.isNotEmpty(map.get(ModelConstant.CONFINPRODID))
                   && StringUtil.equals(map.get(ModelConstant.INF_PS),
                       DictCodeListExt1.交易方向_债转股.getValue())).map(
                       map -> (String)map.get(ModelConstant.FINPRODID)).collect(
                           Collectors.toList());
        return selectConFinprodId(finprodIdList);
    }

    /**
     * 查询债转股维护信息
     * 
     * @param finprodIdList 债券代码
     * @return 债转股维护信息
     */
    public Map<String, String> selectConFinprodId(List<String> finprodIdList) {
        List<FinProductConvertible> infoList = trialCommonMapper.selectConvertible(finprodIdList);
        if (CollectionUtils.isEmpty(infoList)) {
            return new HashMap<>();
        }
        return infoList.stream().collect(Collectors.toMap(FinProductConvertible::getFinprodId,
            FinProductConvertible::getConFinprodId));
    }
}
