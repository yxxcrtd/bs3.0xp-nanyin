///*
// * 文件名：ClassifyTotalInfoAutoSendCBMainService.java
// * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：中信银行资管系统
// * 修改人：刘二通
// * 修改时间：2017年11月14日
// * 修改内容：新建
// * 系统名称：中债报送系统
// */
//
//package com.joyintech.tams.report.batch.autosendcb.classifytotalinfo.service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.joyintech.submitmanagement.classifytotalinforegist.dto.ClassifyTotalInfoRegist001Dto;
//import com.joyintech.submitmanagement.classifytotalinforegist.service.ClassifyTotalInfoRegist001Service;
//import com.joyintech.tams.report.batch.autosendcb.common.service.AutoSendCBBaseService;
//import com.joyintech.tams.report.common.constant.BatchBeanName;
//import com.joyintech.tams.report.common.constant.DictCodeList;
//import com.joyintech.tams.report.common.dto.ExcelValidDto;
//import com.joyintech.tams.report.framework.util.DateUtil;
//import com.joyintech.tams.report.framework.util.StringUtil;
//import com.joyintech.tams.report.framework.util.collectors.JoyinCollectors;
//
///**
// * 分类总量数据自动检查报送 业务service
// *
// * @author 刘二通
// * @version 1.0 2017年11月14日
// * @see ClassifyTotalInfoAutoSendCBMainService
// * @since 1.0
// */
//@Service
//public class ClassifyTotalInfoAutoSendCBMainService extends AutoSendCBBaseService {
//
//    /**
//     * 日志
//     */
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    /**
//     * 分类总量信息登记Service
//     */
//    @Autowired
//    private ClassifyTotalInfoRegist001Service classifyTotalInfoRegist001Service;
//
//    /**
//     * 自动检查报送
//     *
//     * @see
//     * @since 1.0
//     */
//    public void autoSendCB() {
//
//        setBatchBeanName(BatchBeanName.分类总量数据自动检查报送);
//
//        Date batchStartTime = DateUtil.now();
//
//        try {
//            logInfoBatchStart();
//
//            logInfoAutoCheckStart();
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("statusListStr", DictCodeList.报送数据状态_新建.getValue() + StringUtil.STR_COMMA
//                                       + DictCodeList.报送数据状态_修改.getValue());
//            String keyListStr = classifyTotalInfoRegist001Service.getKeyList(param);
//            if (StringUtil.isBlank(keyListStr)) {
//                logInfoNoHandleData(batchStartTime);
//                return;
//            }
//
//            List<String> keyList = StringUtil.splitToList(StringUtil.STR_COMMA, keyListStr);
//            keyList.remove(StringUtil.EMPTY);
//            int keyCount = keyList.size();
//
//            List<ExcelValidDto> validRstList = classifyTotalInfoRegist001Service.checkData(
//                keyListStr, "2");
//            logInfoAutoCheckResult(keyCount, validRstList.size());
//
//            logInfoAutoSendStart();
//            if (keyCount == validRstList.size()) {
//                logInfoNoCheckOKData(batchStartTime, keyCount, validRstList.size());
//                return;
//            }
//
//            Map<String, String> errorDataKeyMap = validRstList.stream().collect(
//                JoyinCollectors.toMap(x -> x.getValidKey(), x -> x.getValidKey()));
//
//            // 剔除检查不通过的数据(主键：产品登记编码)
//            List<String> sendKeyList = new ArrayList<String>();
//            if (errorDataKeyMap.size() > 0) {
//                for (String dataKey : keyList) {
//                    String[] sendKey = StringUtil.split(dataKey, "|");
//                    if (!errorDataKeyMap.containsKey(sendKey[0])) {
//                        sendKeyList.add(dataKey);
//                    }
//                }
//            }
//            else {
//                sendKeyList.addAll(keyList);
//            }
//
//            if (StringUtil.isEmptyList(sendKeyList)) {
//                logInfoNoCheckOKData(batchStartTime, keyCount, validRstList.size());
//                return;
//            }
//
//            String sendKeyListStr = StringUtil.joinString(sendKeyList, StringUtil.STR_COMMA);
//
//            Map<String, Map<String, ClassifyTotalInfoRegist001Dto>> dataMap = classifyTotalInfoRegist001Service.getSendList(
//                sendKeyListStr, param);
//
//            // 循环遍历
//            Iterator<Map.Entry<String, Map<String, ClassifyTotalInfoRegist001Dto>>> entries = dataMap.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<String, Map<String, ClassifyTotalInfoRegist001Dto>> entry = entries.next();
//                Map<String, ClassifyTotalInfoRegist001Dto> mapMain = entry.getValue();
//                String registBankCode = entry.getKey();
//
//                classifyTotalInfoRegist001Service.sendReport(mapMain, registBankCode);
//            }
//
//            logInfoAutoSendResult(batchStartTime, keyCount, validRstList.size(),
//                sendKeyList.size());
//
//            logInfoBatchEnd();
//        }
//        catch (Exception e) {
//            logInfo(e.getMessage());
//            insertResultLogException(batchStartTime);
//            throw e;
//        }
//    }
//}