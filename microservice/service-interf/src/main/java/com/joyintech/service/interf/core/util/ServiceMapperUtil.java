/*
 * 文件名：ServiceMapperUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.util;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i150006.service.Interf150006Service;
import com.joyintech.service.interf.i150010.service.Interf150010Service;
import com.joyintech.service.interf.i190011.service.Interf190011Service;
import com.joyintech.service.interf.i291001.service.Interf291001Service;
import com.joyintech.service.interf.i291002.service.Interf291002Service;
import com.joyintech.service.interf.i448108.service.Interf448108Service;
import com.joyintech.service.interf.i449181.service.Interf449181Service;
import com.joyintech.service.interf.i449183.service.Interf449183Service;
import com.joyintech.service.interf.i449188.service.Interf449188Service;
import com.joyintech.service.interf.i449189.service.Interf449189Service;
import com.joyintech.service.interf.i449191.service.Interf449191Service;
import com.joyintech.service.interf.i449192.service.Interf449192Service;
import com.joyintech.service.interf.i449194.service.Interf449194Service;
import com.joyintech.service.interf.i449195.service.Interf449195Service;
import com.joyintech.service.interf.i449283.service.Interf449283Service;
import com.joyintech.service.interf.icms003.service.InterfCms003Service;
import com.joyintech.service.interf.ilc0001.service.InterfLC0001Service;
import com.joyintech.service.interf.ilc0002.service.InterfLC0002Service;
import com.joyintech.service.interf.ilc0003.service.InterfLC0003Service;
import com.joyintech.service.interf.ilc0005.service.InterfLc0005Service;
import com.joyintech.service.interf.ilc0006.service.InterfLc0006Service;
import com.joyintech.service.interf.ilc0007.service.InterfLc0007Service;
import com.joyintech.service.interf.ilc0008.service.InterfLC0008Service;
import com.joyintech.service.interf.ilc0009.service.InterfLc0009Service;
import com.joyintech.service.interf.ilc0010.service.InterfLc0010Service;
import com.joyintech.service.interf.iopdl03.service.InterfOPDL03Service;
import com.joyintech.service.interf.isms004.service.InterfSMS004Service;
import com.joyintech.service.interf.murex.ibond001.service.InterfBond001Service;
import com.joyintech.service.interf.murex.imopt1001.service.InterfMOPT1001Service;
import com.joyintech.service.interf.murex.irepo002.service.InterfRepo002Service;
import com.joyintech.service.interf.murex.lnbr001.service.InterfLnbr001Service;
import com.joyintech.service.interf.opics.service.OpicsBondBaseInfoService;
import com.joyintech.service.interf.opics.service.OpicsBondHeadService;
import com.joyintech.service.interf.opics.service.OpicsBondTradeService;
import com.joyintech.service.interf.opics.service.OpicsBuyBackTradeService;
import com.joyintech.service.interf.opics.service.OpicsService;
import com.joyintech.service.interf.prodquote.service.Interf150005Service;
import com.joyintech.service.interf.tcms001.service.InterfTcms001Service;
import com.joyintech.service.interf.tcms002.service.InterfTcms002Service;
import com.joyintech.service.interf.wind.service.CbindexeodpricesService;
import com.joyintech.service.interf.wind.service.CbondconversionratioService;
import com.joyintech.service.interf.wind.service.CbondcurvecnbdService;
import com.joyintech.service.interf.wind.service.CbondcurveshcService;
import com.joyintech.service.interf.wind.service.CbonddescriptionService;
import com.joyintech.service.interf.wind.service.CbondindustrywindService;
import com.joyintech.service.interf.wind.service.CbondspecialconditionsService;
import com.joyintech.service.interf.wind.service.ChinamutualfunddescriptionService;
import com.joyintech.service.interf.wind.service.CmoneymarketfscarryovermService;
import com.joyintech.service.interf.wind.service.CompintroductionService;
import com.joyintech.service.interf.wind.service.FloatingcouponsrateService;
import com.joyintech.service.interf.wind.service.FxrmbmidrateService;
import com.joyintech.service.interf.wind.service.PayhsrcbondencashyieldService;
import com.joyintech.service.interf.wind.service.PayhsrccbvltService;
import com.joyintech.service.interf.wind.service.PayhsrcholidayService;
import com.joyintech.service.interf.wind.service.PayhsrcinterestrateService;
import com.joyintech.service.interf.wind.service.PayhsrcsecinfoService;
import com.joyintech.service.interf.wind.service.PayhsrcsecratingService;
import com.joyintech.service.interf.wind.service.PayhsrcsecscheduleService;
import com.joyintech.service.interf.wind.service.WindService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 服务注册表工具类
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月16日
 * @see ServiceMapperUtil
 * @since 1.0
 */
@Component
public class ServiceMapperUtil implements ApplicationContextAware {

    /**
     * 上下文实例
     */
    private static ApplicationContext context;

    /**
     * 处理ESB请求服务接口注册表
     */
    private static Map<String, Class<? extends ReceivedEsbService<?, ?>>> doEsbSvrMapper = new HashMap<>();
    static {
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0001.getTransId(), InterfLC0001Service.class); // 产品成立单据查询接口
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0002.getTransId(), InterfLC0002Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0003.getTransId(), InterfLC0003Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0005.getTransId(), InterfLc0005Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0006.getTransId(), InterfLc0006Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0007.getTransId(), InterfLc0007Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0008.getTransId(), InterfLC0008Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0009.getTransId(), InterfLc0009Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_LC0010.getTransId(), InterfLc0010Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_SMS004.getTransId(), InterfSMS004Service.class); // 文件通知接口
        doEsbSvrMapper.put(EsbServiceInfoList.INF_TCMS001.getTransId(),
            InterfTcms001Service.class);
        doEsbSvrMapper.put(EsbServiceInfoList.INF_TCMS002.getTransId(),
            InterfTcms002Service.class);
    }

    /**
     * 产品成立服务接口注册表
     */
    private static Map<String, Class<? extends ProdAliveService<?, ?>>> prodStrSvrMapper = new HashMap<>();
    static {
        prodStrSvrMapper.put(
            DictCodeList.处理模式_普通.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449181Service.class);
        prodStrSvrMapper.put(
            BussinessDictCodeList.处理模式_滚动.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449181Service.class);
        prodStrSvrMapper.put(
            BussinessDictCodeList.处理模式_开放阶梯.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449191Service.class);
        // 零售
        prodStrSvrMapper.put(
            BussinessDictCodeList.处理模式_滚动N天.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449194Service.class);
        prodStrSvrMapper.put(
            BussinessDictCodeList.处理模式_普通N天.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449188Service.class);
        prodStrSvrMapper.put(
                BussinessDictCodeList.处理模式_日添利.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449181Service.class);
        prodStrSvrMapper.put(
                BussinessDictCodeList.处理模式_智能日添利.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449181Service.class);
        prodStrSvrMapper.put(
            BussinessDictCodeList.处理模式_货币型.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449181Service.class);
        prodStrSvrMapper.put(
            BussinessDictCodeList.处理模式_净值型.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449181Service.class);
    }

    /**
     * 产品存续期交易服务接口注册表
     */
    private static Map<String, Class<? extends ProdAliveService<?, ?>>> prodAlvSvrMapper = new HashMap<>();
    static {
        prodAlvSvrMapper.put(
            BussinessDictCodeList.处理模式_日添利.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449183Service.class);
        prodAlvSvrMapper.put(
            BussinessDictCodeList.处理模式_智能日添利.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449283Service.class);
        prodAlvSvrMapper.put(
            BussinessDictCodeList.处理模式_货币型.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449183Service.class);
        prodAlvSvrMapper.put(
            BussinessDictCodeList.处理模式_净值型.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf448108Service.class);
        prodAlvSvrMapper.put(
            BussinessDictCodeList.处理模式_货币型.getValue()/* + DictCodeList.销售部门_统一TA.getValue()*/,
            Interf291001Service.class);
    }

    /**
     * 产品到期服务接口注册表
     */
    private static Map<String, Class<? extends ProdAliveService<?, ?>>> prodEndSvrMapper = new HashMap<>();
    static {
        prodEndSvrMapper.put(
            BussinessDictCodeList.处理模式_滚动N天.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449195Service.class);
        prodEndSvrMapper.put(
            BussinessDictCodeList.处理模式_普通N天.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449189Service.class);
        prodEndSvrMapper.put(
            BussinessDictCodeList.处理模式_开放阶梯.getValue() + DictCodeList.销售部门_零售产品管理部.getValue(),
            Interf449192Service.class);
    }

    /**
     * wind数据接入服务注册
     */
    private static Map<String, Class<? extends WindService<?>>> serviceMapper = new HashMap<>();
    static {
        serviceMapper.put("loadPayhsrcsecinfoTask", PayhsrcsecinfoService.class);
        serviceMapper.put("loadPayhsrcsecscheduleTask", PayhsrcsecscheduleService.class);
        serviceMapper.put("loadChinaMutualFundDescriptionTask",
            ChinamutualfunddescriptionService.class);
        serviceMapper.put("loadPayhsrcinterestrateTask", PayhsrcinterestrateService.class);
        serviceMapper.put("loadPayhsrcsecratingTask", PayhsrcsecratingService.class);
        serviceMapper.put("loadPayhsrcbondencashyieldTask", PayhsrcbondencashyieldService.class);
        serviceMapper.put("loadFloatingcouponsrateTask", FloatingcouponsrateService.class);
        serviceMapper.put("loadFxrmbmidrateTask", FxrmbmidrateService.class);
        serviceMapper.put("loadCbindexeodpricesTask", CbindexeodpricesService.class);
        serviceMapper.put("loadCBondCurveCNBDTask", CbondcurvecnbdService.class);
        serviceMapper.put("loadCBondCurveSHCTask", CbondcurveshcService.class);
        serviceMapper.put("loadCbondconversionratioTask", CbondconversionratioService.class);
        serviceMapper.put("loadPayhsrcholidayTask", PayhsrcholidayService.class);
        serviceMapper.put("loadPayhsrccbvltTask", PayhsrccbvltService.class);
        serviceMapper.put("loadCompintroductionTask", CompintroductionService.class);
        serviceMapper.put("loadCmoneymarketfscarryovermTask",
            CmoneymarketfscarryovermService.class);
        serviceMapper.put("loadCbonddescriptionTask", CbonddescriptionService.class);
        serviceMapper.put("loadCbondspecialconditionsTask", CbondspecialconditionsService.class);
        serviceMapper.put("loadCbondindustrywindTask", CbondindustrywindService.class);
    }

    /**
     * OPICS数据接入服务注册
     */
    private static Map<String, Class<? extends OpicsService>> opicsSvcMapper = new HashMap<>();
    static {
        opicsSvcMapper.put("loadOpicsBondTradeTask", OpicsBondTradeService.class);
        opicsSvcMapper.put("loadOpicsBondBaseInfoTask", OpicsBondBaseInfoService.class);
        opicsSvcMapper.put("loadOpicsBondHeadTask", OpicsBondHeadService.class);
        opicsSvcMapper.put("loadOpicsBuyBackTradeTask", OpicsBuyBackTradeService.class);
    }

    /**
     * 产品端定时任务服务注册
     */
    private static Map<String, Class<? extends ProdAliveService<?, ?>>> pordBatchSvcMap = new HashMap<>();
    static {
        // N天产品成立
        pordBatchSvcMap.put("prodFoundNSearchTask", Interf449188Service.class);
        // N天产品到期
        pordBatchSvcMap.put("prodEndNDaySearchTask", Interf449189Service.class);
        // 定开债基产品存续期
        pordBatchSvcMap.put("prodAliveNetSearchTask", Interf448108Service.class);
        // 智能日添利产品存续期
        pordBatchSvcMap.put("prodAliveDaySearchTask", Interf449283Service.class);
        // 货币型产品存续期
        pordBatchSvcMap.put("prodAliveCnySearchTask", Interf449183Service.class);
    }

    /**
     * 交易端清算端接口服务注册
     */
    private static Map<String, Class<? extends SendEsbService<?, ?>>> tradeSvcMap = new HashMap<>();
    static {
        // 发送OPICS接口
        tradeSvcMap.put("OPDL03", InterfOPDL03Service.class);
        // 质押式回购交易
        tradeSvcMap.put("REPO002", InterfRepo002Service.class);
        // 质押式回购交易
        tradeSvcMap.put("BOND001", InterfBond001Service.class);
        // 拆借交易
        tradeSvcMap.put("LNBR001", InterfLnbr001Service.class);
        // 查询交易导入结果信息
        tradeSvcMap.put("MOPT1001", InterfMOPT1001Service.class);
        // CMS出账信息撤销
        tradeSvcMap.put("CMS003", InterfCms003Service.class);
    }

    /**
     * TA接口服务注册
     */
    private static Map<String, Class<? extends SendEsbService<?, ?>>> taSvcMap = new HashMap<>();
    static {
        // 报价接口（150005）
        taSvcMap.put("TA150005", Interf150005Service.class);
        // 产品信息预创建接口（150006）
        taSvcMap.put("TA150006", Interf150006Service.class);
        // 资管费用信息接口（150010）
        taSvcMap.put("TA150010", Interf150010Service.class);
        // 天天成长当日销售数据统计查询（291001）
        taSvcMap.put("TA291001", Interf291001Service.class);
        // 净值型产品申购赎回确认数据查询（291002）
        taSvcMap.put("TA291002", Interf291002Service.class);
        // 净值分红信息查询接口（190011）
        taSvcMap.put("TA190011", Interf190011Service.class);
    }

    /**
     * 获取指定关键字对应的处理ESB请求服务类
     * 
     * @param key 指定关键字:交易码
     * @return 处理ESB请求服务类
     * @see
     * @since 1.0
     */
    public static ReceivedEsbService<?, ?> getDoEsbService(String key) {
        if (key == null || !doEsbSvrMapper.containsKey(key)) {
            return context.getBean(ReceivedEsbService.class);
        }
        return context.getBean(doEsbSvrMapper.get(key));
    }

    /**
     * 获取指定关键字对应的产品成立服务类
     * 
     * @param key 指定关键字：产品类型（处理模式）
     * @return 产品成立服务类
     * @see
     * @since 1.0
     */
    public static ProdAliveService<?, ?> getProdStrService(String key) {
        if (key == null || !prodStrSvrMapper.containsKey(key)) {
            return context.getBean(ProdAliveService.class);
        }
        return context.getBean(prodStrSvrMapper.get(key));
    }

    /**
     * 获取指定关键字对应的产品存续期交易服务类
     * 
     * @param key 指定关键字：产品类型（处理模式）
     * @return 产品存续期交易服务类
     * @see
     * @since 1.0
     */
    public static ProdAliveService<?, ?> getProdAlvService(String key) {
        if (key == null || !prodAlvSvrMapper.containsKey(key)) {
            return context.getBean(ProdAliveService.class);
        }
        return context.getBean(prodAlvSvrMapper.get(key));
    }

    /**
     * 获取指定关键字对应的产品到期服务类
     * 
     * @param key 指定关键字：产品类型（处理模式）
     * @return 产品到期服务类
     * @see
     * @since 1.0
     */
    public static ProdAliveService<?, ?> getProdEndService(String key) {
        if (key == null || !prodEndSvrMapper.containsKey(key)) {
            return context.getBean(ProdAliveService.class);
        }
        return context.getBean(prodEndSvrMapper.get(key));
    }

    /**
     * 取得指定关键字对应的wind服务类
     * 
     * @param key 指定关键字
     * @return wind服务类
     * @see
     * @since 1.0
     */
    public static WindService<?> getWindService(String key) {
        if (key == null || !serviceMapper.containsKey(key)) {
            return null;
        }
        return context.getBean(serviceMapper.get(key));
    }

    /**
     * 取得指定关键字对应的OPICS服务类
     * 
     * @param key 指定关键字
     * @return OPICS服务类
     * @see
     * @since 1.0
     */
    public static OpicsService getOpicsService(String key) {
        if (key == null || !opicsSvcMapper.containsKey(key)) {
            return null;
        }
        return context.getBean(opicsSvcMapper.get(key));
    }

    /**
     * 获取指定关键字对应的产品Batch处理服务类
     * 
     * @param key 指定关键字：Batch名
     * @return 产品Batch处理服务类
     * @see
     * @since 1.0
     */
    public static ProdAliveService<?, ?> getProdBatchService(String key) {
        if (key == null || !pordBatchSvcMap.containsKey(key)) {
            return null;
        }
        return context.getBean(pordBatchSvcMap.get(key));
    }

    /**
     * 获取指定关键字对应的发送交易指令处理服务类
     * 
     * @param key 指定关键字
     * @return 发送交易指令处理服务类
     * @see
     * @since 1.0
     */
    public static SendEsbService<?, ?> getTradeService(String key) {
        if (key == null || !tradeSvcMap.containsKey(key)) {
            return null;
        }
        return context.getBean(tradeSvcMap.get(key));
    }

    /**
     * 获取指定关键字对应的TA处理服务类
     * 
     * @param key 指定关键字
     * @return TA处理服务类
     * @see
     * @since 1.0
     */
    public static SendEsbService<?, ?> getTAService(String key) {
        if (key == null || !taSvcMap.containsKey(key)) {
            return null;
        }
        return context.getBean(taSvcMap.get(key));
    }

    /**
     * 获取应用句柄
     * 
     * @return 应用句柄
     * @see
     * @since 1.0
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext arg0)
        throws BeansException {
        context = arg0;
    }

}
