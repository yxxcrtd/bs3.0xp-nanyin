/*
 * 文件名：InterfService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月10日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.interf.interf.common.dao.InterfCommonMapper;
import com.joyintech.tams.disclosure.interf.interf.common.dto.InterfLog;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.dao.TamsBaseMapper;

/**
 * 接口共通service
 *
 * @author lihuadong
 * @version 1.0 2017年2月10日
 * @see InterfService
 * @since 1.0
 */
@Service(value="disInterfService")
public class InterfService {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 系统参数缓存Mapper
     */
    @Autowired
    private InterfCommonMapper interfCommonMapper;

    /**
     * BaseMapper
     */
    @Autowired
    private TamsBaseMapper baseMapper;

    /**
     * databaseType
     */
    private static String databaseType = null;

    /**
     * Type01 通过 外汇交易中心用户取资管用户
     */
    protected static final String TYPE_01 = "01";

    /**
     * Type 02 通过资管用户 取外汇交易中心用户
     */
    protected static final String TYPE_02 = "02";

    /**
     * Type 03 通过 xfund用户取 资管用户
     */
    protected static final String TYPE_03 = "03";

    /**
     * Type 04 通过资管用户 xfund用户取
     */
    protected static final String TYPE_04 = "04";

    /**
     * 数字格式format 18位
     */
    protected static final String FROMAT_18 = "%018d";

    /**
     * 数字格式format 18位
     */
    protected static final String FROMAT_14 = "%014d";

    /**
     * 数字格式format 8位
     */
    protected static final String FROMAT_8 = "%08d";

    /**
     * 根据系统参数主键查询参数值
     *
     * @param paraCode 参数code
     * @return 参数值
     * @see
     * @since 1.0
     */
    protected String getParaVauleByparaCode(String paraCode) {
        return getSysPara(DisclosureDictCodeList.子系统_接口.getValue(), DisclosureDictCodeList.模块名称_共通参数.getValue(),
            paraCode);
    }

    /**
     * 根据系统参数主键查询参数值
     *
     * @param paraCode 参数code
     * @return 参数值
     * @see
     * @since 1.0
     */
    protected String getSysPara(String sysCode, String moduleCode, String paraCode) {
//        List<String> paraCodes = interfCommonMapper.selectParaByPrimaryKey(sysCode, moduleCode,
//            StringUtil.upperCase(paraCode));
        String paraCodes = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, paraCode);

//        List<String> paraCodes = null;
//        if (CollectionUtils.isEmpty(paraCodes)) {
//            return null;
//        }
        return paraCodes;

    }

//    /**
//     * 根据系统参数主键更新参数值
//     *
//     * @param paraCode 参数code
//     * @param value 参数值
//     * @return 被更新的条数
//     */
//    protected int updateParaVauleByparaCode(String paraCode, String value) {
////        ChbSysPara sysPara = new ChbSysPara();
////        sysPara.setSubsystemCode(DictCodeList.子系统_接口.getValue());
////        sysPara.setModuleCode(DictCodeList.模块名称_共通参数.getValue());
////        sysPara.setParaCode(paraCode);
////        sysPara.setParamValue(value);
//        //sysParaMapper.updateByPrimaryKeySelective(sysPara);
//        //TODO
//        return 0;
//    }

    /**
     * 根据採番名採番
     *
     * @param sequenceType 採番序列类型
     * @param fromat 格式
     * @return 採番值
     * @see
     */
    protected String getSequenceIndexData(DataIndexSequenceType sequenceType, String fromat) {
        Map<String, String> param = new HashMap<>();
        if (StringUtils.isEmpty(databaseType)) {
//            List<String> paraCodes = interfCommonMapper.selectParaByPrimaryKey(
//                DictCodeList.子系统_系统.getValue(), DictCodeList.模块名称_共通参数.getValue(),
//                CommonUtil.COM_PROP_DATABASE_TYPE);
//            String paraCode = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,
//                ReportGlobalConstant.COM_PROP_DATABASE_TYPE);
            //  if (CollectionUtils.isEmpty(paraCodes)) {
//                return null;
//            }
//            databaseType = paraCode;
            databaseType = "Oracle";
        }
        param.put("databaseType", databaseType);
        param.put("sequenceName", sequenceType.getSequenceName());
        Long result = baseMapper.getSequenceIndexData(param);

        String sequenceResult = String.format(fromat, result);
        if (FROMAT_8.equals(fromat)) {
            return sequenceResult;
        }
        // 加在採番后结果的前缀，以保证各个sequence的採番结果不会出现重复的情况
        String shortName = sequenceType.getShortName();
        sequenceResult = shortName + sequenceResult;
        return sequenceResult;
    }

    /**
     * 获取平台日期
     *
     * @return 平台日期
     */
    protected Date getNowSysDate() {
        // return this.baseCommonMapper.getNowSysDate();
        return DateUtil.now();
    }

    /**
     * 储存接口日志
     *
     * @param log 日志
     */
    protected void log(InterfLog log) {
        // 接口流水号
        log.setLogId(this.getSequenceIndexData(DataIndexSequenceType.接口应用, FROMAT_18));
        log.setCreateTime(new Date());
        interfCommonMapper.insertInterfLog(log);
    }

//    /**
//     * 接口启动流程
//     *
//     * @param params 流程参数
//     * @param userId 用户
//     * @param title 标题
//     * @throws WorkFlowException WorkFlowException
//     */
//    protected void startWorkFlow(WorkFlowParameter<?> params, String userId, String title)
//        throws WorkFlowException {
//    	WorkFlowUtil.startProcess(params, null, null, userId, OperationType.NEW, false, null);
//    }

//    /**
//     * 他系统用户映射
//     *
//     * @param userId 映射用户
//     * @param type 映射系统
//     * @return 用户信息
//     * @see
//     * @since 1.0
//     */
//    protected InterfUserMaaperDto convertUser(String userId, String type) {
//        Map<String, String> param = new HashMap<>();
//        // 映射用户
//        param.put("userId", userId);
//        // 映射系统
//        param.put("type", type);
//        List<InterfUserMaaperDto> result = interfCommonMapper.selectInterfUserMaaper(param);
//        if (CollectionUtils.isEmpty(result)) {
//            return null;
//        }
//        else {
//            return result.get(0);
//        }
//    }
//
//    /**
//     * 获取默认用户信息
//     *
//     * @return 用户信息
//     * @see
//     * @since 1.0
//     */
//    protected InterfUserMaaperDto getdefaultUser() {
//        Map<String, String> param = new HashMap<>();
//        // 是否默认
//        param.put("default", DictCodeList.是否_是.getValue());
//        List<InterfUserMaaperDto> result = interfCommonMapper.selectInterfUserMaaper(param);
//        if (CollectionUtils.isEmpty(result)) {
//            return new InterfUserMaaperDto();
//        }
//        else {
//            return result.get(0);
//        }
//    }

    /**
     * 打印日志基础信息
     *
     * @param log 日志信息
     * @param interfType 接口类型
     * @param taskStatus 接口状态
     * @param isWarning 是否需要预警
     * @param errMessage 错误信息
     * @param flag 结束标识
     * @see
     * @since 1.0
     */
    protected void printLog(InterfLog log, String interfType, String taskStatus, String isWarning,
                            String errMessage, Boolean flag) {
        log.setTaskStatus(taskStatus);
        log.setIsneedwarning(isWarning);
        log.setInterfType(interfType);
        log.setErrMessage(errMessage);
        this.log(log);
        if (flag) {
            log.setInterfType(DisclosureDictCodeList.接口类型_结束.getValue());
            this.log(log);
        }
    }

    /**
     * 生成日志
     *
     * @param log 日志信息
     * @param interfType 接口类型
     * @param taskStatus 接口状态
     * @param isWarning 是否需要预警
     * @param errMessage 错误信息
     * @param flag 结束标识
     * @return
     * @see
     * @since 1.0
     */
    protected List<InterfLog> createLogList(InterfLog log, String interfType, String taskStatus,
                                            String isWarning, String errMessage, Boolean flag) {
        List<InterfLog> interfLogList = new ArrayList<InterfLog>();

        InterfLog newLog = new InterfLog();
        try {
            BeanUtil.copyProperties(newLog, log);
        }
        catch (Exception e) {
        }
        // 接口流水号
        newLog.setLogId(this.getSequenceIndexData(DataIndexSequenceType.接口应用, FROMAT_18));
        newLog.setCreateTime(new Date());
        newLog.setTaskStatus(taskStatus);
        newLog.setIsneedwarning(isWarning);
        newLog.setInterfType(interfType);
        newLog.setErrMessage(errMessage);
        interfLogList.add(newLog);

        if (flag) {
            InterfLog endLog = new InterfLog();
            try {
                BeanUtil.copyProperties(endLog, newLog);
            }
            catch (Exception e) {
            }
            endLog.setLogId(this.getSequenceIndexData(DataIndexSequenceType.接口应用, FROMAT_18));
            endLog.setCreateTime(new Date());
            endLog.setInterfType(DisclosureDictCodeList.接口类型_结束.getValue());
            interfLogList.add(endLog);
        }

        return interfLogList;
    }

    /**
     * 分批登录日志数据
     *
     * @param interfLogList 日志数据
     * @see
     * @since 1.0
     */
    protected void insertInterfLogList(List<InterfLog> interfLogList) {
        if (StringUtil.isEmptyList(interfLogList)) {
            return;
        }

        int maxDataCountOneTime = 100;

        // 报送数据
        int dataCount = interfLogList.size();
        if (dataCount <= maxDataCountOneTime) {
            interfCommonMapper.insertInterfLogList(interfLogList);
        }
        else {
            // 分批处理
            List<InterfLog> newDataList = new ArrayList<InterfLog>();
            newDataList.addAll(interfLogList);

            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                interfCommonMapper.insertInterfLogList(
                    newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                interfCommonMapper.insertInterfLogList(newDataList);
            }
        }
    }
}
