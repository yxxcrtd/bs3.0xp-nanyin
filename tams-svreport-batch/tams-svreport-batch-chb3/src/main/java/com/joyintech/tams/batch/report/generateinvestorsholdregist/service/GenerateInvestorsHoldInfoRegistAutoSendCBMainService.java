/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.report.generateinvestorsholdregist.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.generateinvestorsholdregist.dao.GenerateInvestorsHoldInfoRegistAutoSendCBMapper;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 投资者持有信息登记数据自动生成批处理 业务service
 * @author zuoyinyi
 * @version 1.0 2018年5月21日
 * @see GenerateInvestorsHoldInfoRegistAutoSendCBMainService
 * @since 1.0
 */
@Service
public class GenerateInvestorsHoldInfoRegistAutoSendCBMainService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 投资者持有信息登记数据自动生成批处理 业务mapper
     */
    @Autowired
    private GenerateInvestorsHoldInfoRegistAutoSendCBMapper mapper;

    /**
     * 自动检查报送
     * @param executeDate 
     *
     * @see
     * @since 1.0
     */
    public void generateRegistData(String executeDateStr) {
        try {
            Map<String,Object> paramMap = new HashMap<>();
            Date executeDate = DateUtil.parse(executeDateStr+ " "+CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.INVESTORS_COUNT_END_TIME_POINT));
            paramMap.put("isJoinDelayDataToday", CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.IS_JOIN_DELAYDATA_TODAY));
            paramMap.put("executeDate", executeDate);
            paramMap.put("holdDate", DateUtil.parse(executeDateStr));
            List<InvestorsHoldInfoRegist001Dto> resultData = mapper.selectBusiData(paramMap);
            List<InvestorsHoldInfoRegist001Dto> updateDataList = new ArrayList<>();
            List<InvestorsHoldInfoRegist001Dto> insertDataList = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(resultData)){
                Map<String,BigDecimal> rateMap= new HashMap<>();
                for (InvestorsHoldInfoRegist001Dto dto : resultData) {
                    if(StringUtil.equals(dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue())){
                        dto.setCnyAmt(dto.getAmount());
                    }
                    else{
                        String key = DateUtil.format(dto.getHoldDate()) + dto.getCcy();
                        if(rateMap.containsKey(key)){
                            dto.setCnyAmt(ArithmeticUtil.multiply(rateMap.get(key),dto.getAmount()));
                        }
                        else{
                            Map<String,Object> temp = new HashMap<>();
                            temp.put("CCY_ORIGINAL",dto.getCcy());
                            temp.put("HOLDDATE",executeDate);
                            temp.put("CCY_CONVERT",DictCodeListExt1.币种_中债_人民币.getValue());
                            temp.put("pricetype",CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE));
                            BigDecimal rate = mapper.getRateNearByDate(temp);
                            dto.setCnyAmt(ArithmeticUtil.multiply(rate,dto.getAmount()));
                            rateMap.put(key, rate);
                        }
                    }
                     // 报送时点通用方法
                     // 当前批次报送时点集合
                    Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
                    List<Date> dateList;
                    dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_投资者持有信息登记.getValue(),
                    		DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, executeDate);
                    if (CollectionUtils.isNotEmpty(dateList)) {
                        dto.setChbSubmitDeadline(dateList.get(0));
                        dto.setSubmitDeadline(dateList.get(1));
                    }
                    dto.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
                    dto.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
                    dto.setRegistSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
                    dto.setHoldDate(DateUtil.parse(executeDateStr));
                    dto.setStatusDate(dto.getUpdateTime());
                    //区分已存在的数据和不存在的数据
                    //是否存在登记数据
                    if(mapper.selectIsExist(dto)!=null){
                        //是否存在报送或手工处理的数据
                        dto.setFlag("01");
                        Map<String, Object> map = mapper.selectIsExist(dto);
                        if(map==null){
                          //准备更新处理新建未报送的数据
                            updateDataList.add(dto);
                        }
                        else{
                            //存在报送或者手工处理的数据时
                            BigDecimal amount = (BigDecimal)map.get("AMOUNT");
                            BigDecimal qty = (BigDecimal)map.get("QTY");
                            if(dto.getAmount().compareTo(amount)==0 && dto.getQty().compareTo(qty)==0){
                                //无 变化时，舍弃该数据
                                continue;
                            }
                            else{
                                dto.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
                                dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_投资者持有信息登记.getValue(),
                                		DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, DateUtil.now());
                                if (CollectionUtils.isNotEmpty(dateList)) {
                                    dto.setChbSubmitDeadline(dateList.get(0));
                                    dto.setSubmitDeadline(dateList.get(1));
                                }
                                //准备插入处理修改未报送的数据
                                insertDataList.add(dto);
                            }
                        
                        }
                    }
                    else{
                        //准备插入处理新建未报送的数据
                        insertDataList.add(dto);
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(updateDataList)){
                //批量更新
                mapper.update(updateDataList);
            }
            if(CollectionUtils.isNotEmpty(insertDataList)){
                //批量插入
                mapper.insert(insertDataList);
            }
        }
        catch (Exception e) {
            logger.error("投资者持有信息登记数据自动生成批处理Error",e);
            throw e;
        }
    }
}
