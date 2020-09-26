/*
 * 文件名：EquityInfoAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2017年12月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.autosendcb.equityinfo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfoDistinctService;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.service.EquityInfoRegist001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 投资者身份信息数据自动检查报送 业务service
 *
 * @author 刘二通
 * @version 1.0 2017年12月9日
 * @see EquityInfoAutoSendCBMainService
 * @since 1.0
 */
@Service
public class EquityInfoAutoSendCBMainService extends AutoSendCBBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * EquityInfoRegist001Service
     */
    @Autowired
    private EquityInfoRegist001Service equityInfoRegist001Service;
    @Autowired
    private EquityInfoDistinctService equityInfoDistinctService;
    

    /**
     * 自动检查报送
     *
     * @see
     * @since 1.0
     */
    public void autoSendCB() {

        setBatchBeanName(BatchBeanName.投资者身份报送);

        Date batchStartTime = DateUtil.now();

            logInfoBatchStart();
            equityInfoDistinctService.checkBeforeSubmit();
            logInfoAutoCheckStart();
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("statusListStr", DictCodeListExt2.报送数据状态_新建.getValue() + GlobalConstant.COMMA
                                       + DictCodeListExt2.报送数据状态_修改.getValue());
//            String keyListStr = equityInfoRegist001Service.getKeyList(param);
            logInfo("getAllUnSubmit查询全量数据开始");
            List<EquityInfoRegist001Dto> list =equityInfoRegist001Service.getAllUnSubmit(param);
            logInfo("getAllUnSubmit查询全量数据结束");
            if (CollectionUtils.isEmpty(list)) {
                logInfoNoHandleData(batchStartTime);
                return;
            }

//            List<String> keyList = StringUtil.splitToList(GlobalConstant.COMMA, keyListStr);
//            keyList.remove(StringUtil.EMPTY);
            int keyCount = list.size();

//            List<ExcelValidDto> validRstList = equityInfoRegist001Service.sendBeforeCheck(
//                keyListStr);
            logInfo("sendAllDataBeforeCheck 报送前检查开始");
            List<ExcelValidDto> validRstList = equityInfoRegist001Service.sendAllDataBeforeCheck(list);
            logInfo("sendAllDataBeforeCheck 报送前检查结束");
            logInfoAutoCheckResult(keyCount, validRstList.size());
            logInfoAutoSendStart();
            if (keyCount == validRstList.size()) {
                logInfoNoCheckOKData(batchStartTime, keyCount, validRstList.size());
                return;
            }

            Map<String, String> errorDataKeyMap = validRstList.stream().collect(
                JoyinCollectors.toMap(x -> x.getValidKey(), x -> x.getValidKey()));

            // 剔除检查不通过的数据(主键：识别标识)
            List<EquityInfoRegist001Dto> sendKeyList = new ArrayList<EquityInfoRegist001Dto>();
//            if (errorDataKeyMap.size() > 0) {
//                for (String dataKey : keyList) {
//                    String[] sendKey = StringUtil.split(dataKey, "|");
//                    if (!errorDataKeyMap.containsKey(sendKey[1])) {
//                        sendKeyList.add(dataKey);
//                    }
//                }
//            }
//            else {
//                sendKeyList.addAll(keyList);
//            }
            if (errorDataKeyMap.size() > 0) {
                for (EquityInfoRegist001Dto equityInfoRegist001Dto : list) {
                    if (!errorDataKeyMap.containsKey(equityInfoRegist001Dto.getIndentifyMark())) {
                        sendKeyList.add(equityInfoRegist001Dto);
                    }
                }
            }
            else {
                sendKeyList.addAll(list);
            }
            if (StringUtil.isEmptyList(sendKeyList)) {
                logInfoNoCheckOKData(batchStartTime, keyCount, validRstList.size());
                return;
            }

//            logInfo("拼接submitKeys处理开始");
//            String sendKeyListStr = StringUtil.joinString(sendKeyList, GlobalConstant.COMMA);
//            param.put("submitKeys", sendKeyListStr);
//            logInfo("拼接submitKeys处理结束");
            //将报送数据按照登记银行代码放入map中
            Map<String,List<EquityInfoRegist001Dto>> registMap = new HashMap<String,List<EquityInfoRegist001Dto>>();
            List<EquityInfoRegist001Dto> equList = null;
            for (EquityInfoRegist001Dto equityInfoRegist001Dto2 : list) {
                if(StringUtil.isEmptyList(registMap.get(equityInfoRegist001Dto2.getBankCode()))){
                    equList = new ArrayList<EquityInfoRegist001Dto>();
                }
                else{
                    equList = registMap.get(equityInfoRegist001Dto2.getBankCode());
                }
                equList.add(equityInfoRegist001Dto2);
                registMap.put(equityInfoRegist001Dto2.getBankCode(), equList);
            }
//            logInfo("getSubmitList处理开始");
//            Map<String, List<EquityInfoRegist001Dto>> registMap = equityInfoRegist001Service.getSubmitList(
//                param);
//            logInfo("getSubmitList处理结束");
            Iterator<Map.Entry<String, List<EquityInfoRegist001Dto>>> entries = registMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, List<EquityInfoRegist001Dto>> entry = entries.next();
                List<EquityInfoRegist001Dto> registList = entry.getValue();
                logInfo("sendEquityHoldCHB处理开始");
                equityInfoRegist001Service.sendEquityHoldCHB(registList, true);
                logInfo("sendEquityHoldCHB处理结束");
            }

            logInfoAutoSendResult(batchStartTime, keyCount, validRstList.size(),
                sendKeyList.size());

            logInfoBatchEnd();
    }
}
