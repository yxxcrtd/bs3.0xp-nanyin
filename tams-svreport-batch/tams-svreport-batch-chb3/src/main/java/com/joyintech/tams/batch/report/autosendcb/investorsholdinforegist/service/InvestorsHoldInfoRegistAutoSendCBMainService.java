/*
 * 文件名：InvestorsHoldInfoRegistAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月14日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.autosendcb.investorsholdinforegist.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.autosendcb.investorsholdinforegist.dao.InvestorsHoldInfoRegistAutoSendCBMapper;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.cnbond.service.InvestorsHoldInfo001InterfService;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfoDistinctService;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.service.InvestorsHoldInfoRegist001Service;

/**
 * 投资者持有信息数据自动检查报送 业务service
 * @author zuoyinyi
 * @version 1.0 2018年5月14日
 * @see InvestorsHoldInfoRegistAutoSendCBMainService
 * @since 1.0
 */
@Service
public class InvestorsHoldInfoRegistAutoSendCBMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * InvestorsHoldInfoRegist001Service
     */
    @Autowired
    private InvestorsHoldInfoRegist001Service investorsHoldInfoRegist001Service;
    
    @Autowired
    private InvestorsHoldInfo001InterfService investorsHoldInfo001InterfService;

    /**
     * InvestorsHoldInfoRegistAutoSendCBMapper
     */
    @Autowired
    private InvestorsHoldInfoRegistAutoSendCBMapper autoSendCBMapper;
    @Autowired
    private InvestorsHoldInfoDistinctService investorsHoldInfoDistinctService;

    /**
     * 自动检查报送
     *
     * @param holdDate 持有日期
     * @see
     * @since 1.0
     */
    public void autoSendCB(String holdDate) {

        setBatchBeanName(BatchBeanName.投资者持有报送);

        Date batchStartTime = DateUtil.now();

            logInfoBatchStart();
            investorsHoldInfoDistinctService.checkBeforeSubmit();
            // 查询所有未报送数据条数
            logInfo("查询所有未报送数据条数开始");
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("holdDate", holdDate);
            int allUnregistDataCount = autoSendCBMapper.selectAllUnregistDataCount(paramMap);
            logInfo("查询所有未报送数据条数结束，结果条数：" + allUnregistDataCount);

            if (allUnregistDataCount <= 0) {
                logInfoNoHandleData(batchStartTime);
                return;
            }
            
            //add by liubin start  查询未报送数据中根据中债五要素判断是否有重复数据
            logInfo("查询未报送数据中根据中债五要素判断是否有重复数据开始...");
            List<String> repeatHoldInfoData = autoSendCBMapper.selectUnregistDataRepeatDataByFiveElem(paramMap);
            logInfo("查询未报送数据中根据中债五要素判断是否有重复数据开始结束，数据条数："+repeatHoldInfoData.size());
            paramMap.put("repeatHoldInfoData", repeatHoldInfoData);
            //add by liubin end 

            // 查询所有未报送、但对应身份信息未报送数据条数
            logInfo("查询所有未报送、但对应身份信息未报送数据条数开始");
            int allUnregistCheckNgDataCount = autoSendCBMapper.selectAllUnregistCheckNgDataCount(
                paramMap);
            logInfo("查询所有未报送、但对应身份信息未报送数据条数结束，结果条数：" + allUnregistCheckNgDataCount);

            int allUnregistCheckOkDataCount = allUnregistDataCount - allUnregistCheckNgDataCount;
            if (allUnregistCheckOkDataCount <= 0) {
                logInfoNoCheckOKData(batchStartTime, allUnregistDataCount, allUnregistDataCount);
                return;
            }
            //删除之前先将数据入His表 add by yuluan 2019-03-29
            autoSendCBMapper.insertIntoHisAllAlreadySubmitRegistData(paramMap);
            // 删除重复已报送数据
            logInfo("开始删除重复已报送数据");
            autoSendCBMapper.deleteAllAlreadySubmitRegistData(paramMap);
            logInfo("删除重复已报送数据结束");

            int dataCountPerTime = Integer.valueOf(
                CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME));

            logInfo("开始按批次处理持有信息数据，每批次处理数据条数：" + dataCountPerTime);
            paramMap.put("dataCountPerTime", dataCountPerTime);

            // 处理批次
            int handleIndex = 1;
            while (true) {
                // 查询符合条件的待报送数据（前500000条）
                boolean hasData = this.handleDataPerTime(handleIndex, paramMap);
                if (!hasData) {
                    break;
                }
                handleIndex++;
            }

            logInfoAutoSendResult(batchStartTime, allUnregistDataCount,
                allUnregistCheckNgDataCount, allUnregistCheckOkDataCount);

            logInfoBatchEnd();

//            logInfoAutoCheckStart();
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("statusListStr", DictCodeList.报送数据状态_新建.getValue() + GlobalConstant.COMMA
//                                       + DictCodeList.报送数据状态_修改.getValue());
//            Map<String,Object> map = new HashMap<>();
//            map.put("sendStatus", DictCodeList.报送状态_未报送.getValue());
//            map.put("status", DictCodeList.报送数据状态_新建.getValue() + GlobalConstant.COMMA
//                    + DictCodeList.报送数据状态_修改.getValue());
//            List<InvestorsHoldInfoRegist001Dto> allNotSendData = investorsHoldInfoRegist001Service.selectAllList(map);
//            if (CollectionUtils.isEmpty(allNotSendData)) {
//                logInfoNoHandleData(batchStartTime);
//                return;
//            }
//
//            int keyCount = allNotSendData.size();
//
//            // add by etliu 20180818 start
//            map.put("batchExeDataList", allNotSendData);
//            // add by etliu 20180818 end
//
//            List<ExcelValidDto> validRstList = investorsHoldInfoRegist001Service.sendBeforeCheck(map,true);
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
//            // 剔除检查不通过的数据(主键：识别标识)
//            List<InvestorsHoldInfoRegist001Dto> sendKeyList = new ArrayList<>();
//            if (errorDataKeyMap.size() > 0) {
//                for (InvestorsHoldInfoRegist001Dto dto : allNotSendData) {
//                    String validKey = generateKeys(dto);
//                    if (!errorDataKeyMap.containsKey(validKey)) {
//                        sendKeyList.add(dto);
//                    }
//                }
//            }
//            else {
//                sendKeyList.addAll(allNotSendData);
//            }
//
//            if (StringUtil.isEmptyList(sendKeyList)) {
//                logInfoNoCheckOKData(batchStartTime, keyCount, validRstList.size());
//                return;
//            }
//
//            // delete by etliu 20180818 start
////            param.put("investList", sendKeyList);
//            // delete by etliu 20180818 end
//            // add by etliu 20180818 start
//            param.put("batchExeNeedSendDataList", sendKeyList);
//            // add by etliu 20180818 end
//
//            Map<String, List<InvestorsHoldInfoRegist001Dto>> registMap = investorsHoldInfoRegist001Service.getSubmitList(
//                param);
//            Iterator<Map.Entry<String, List<InvestorsHoldInfoRegist001Dto>>> entries = registMap.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<String, List<InvestorsHoldInfoRegist001Dto>> entry = entries.next();
//                List<InvestorsHoldInfoRegist001Dto> registList = entry.getValue();
//                investorsHoldInfoRegist001Service.sendInvestorsHoldCHB(registList, true);
//            }
//
//            logInfoAutoSendResult(batchStartTime, keyCount, validRstList.size(),
//                sendKeyList.size());
//
//            logInfoBatchEnd();
    }

    /**
     * 单批次处理持有信息数据
     *
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    private boolean handleDataPerTime(int handleIndex, Map<String, Object> paramMap) {
        logInfo("处理开始，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean hasData = true;

        // 清空数据报送临时表
        logInfo("开始清空数据报送临时表");
        autoSendCBMapper.truncateSubTempTable();
        logInfo("清空数据报送临时表结束");

        // 将待处理数据的报送流水号插入临时表
        logInfo("开始将待处理数据的登记序号插入临时表");
        autoSendCBMapper.insertUnregistCheckOkDataToTempTable(paramMap);
        logInfo("将待处理数据的登记序号插入临时表结束");

        // 查询待报送数据
        logInfo("开始查询待报送数据");
        List<InvestorsHoldInfoRegist001Dto> unregistDataList = autoSendCBMapper.selectAllUnregistCheckOkData();
        logInfo("查询待报送数据结束，数据条数：" + unregistDataList.size());
        if (StringUtil.isEmptyList(unregistDataList)) {
            logInfo("已无待报送数据，处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return false;
        }

        // 将待报送数据批量更新报送状态
        logInfo("开始将待报送数据批量更新报送状态");
        autoSendCBMapper.updateSubmitStatus();
        logInfo("将待报送数据批量更新报送状态结束");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("batchExeNeedSendDataList", unregistDataList);

        Map<String, List<InvestorsHoldInfoRegist001Dto>> registMap = investorsHoldInfoRegist001Service.getSubmitList(
            param);
        Iterator<Map.Entry<String, List<InvestorsHoldInfoRegist001Dto>>> entries = registMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<InvestorsHoldInfoRegist001Dto>> entry = entries.next();
            List<InvestorsHoldInfoRegist001Dto> registList = entry.getValue();
            investorsHoldInfoRegist001Service.sendInvestorsHoldCHB(registList, true);
        }

        unregistDataList.clear();
        logInfo("处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return hasData;
    }

    /**
     *
     * 主键分解重组
     * @param validKey 主键
     * @see
     * @since 1.0
     */
    private String generateKeys(InvestorsHoldInfoRegist001Dto dto){
        if(dto!=null){
            return "产品登记编码 "+dto.getPrdRegistCode()+GlobalConstant.COMMA
                    +"识别标识 "+dto.getIndentifyMark()+GlobalConstant.COMMA
                    +"持有日期 "+DateUtil.format(dto.getHoldDate(),"yyyy-MM-dd")+GlobalConstant.COMMA
                    +"币种  "+dto.getCcy()+GlobalConstant.COMMA
                    +"登记银行代码  "+dto.getRegistBankCode();
        }
        return null;
    }
}
