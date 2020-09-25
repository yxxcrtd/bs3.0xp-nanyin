/*
 * 文件名：RskParamReCalcReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：jjpei
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.riskparamcalc.reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.riskparamcalc.dto.RskParamReCalcReaderDto;
import com.joyintech.tams.invest.batch.riskparamcalc.service.RskParamReCalcService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tools.collectors.JoyinCollectors;


/**
 * 批处理-指标参数重新更新-Reader
 * 
 * @author jjpei
 * @version 1.0 2020年4月17日
 * @see RskParamReCalcReader
 * @since 1.0
 */
@Service
@Scope(value = "prototype")
public class RskParamReCalcReader extends BaseReader<RskParamReCalcReaderDto> {

    // 控制台日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 批处理-指标重新计算-Service
     */
    @Autowired
    private RskParamReCalcService dataService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RskParamReCalcReaderDto> createList() {
        Map<String, Object> params = getParams();
        List<String> prodIdListTwo = (List<String>)params.get(BatchConstant.KEY_PRODID_LIST);
        List<String> prodIdList = getProdIdList(prodIdListTwo);
        Date sysDate = (Date) params.get("sysDate");
        // 取得所有实占信息
        List<RskOccupyInfo> opcInfoListTwo = dataService.getOcpInfoList(prodIdList,sysDate);
        List<RskOccupyInfo> opcInfoList = Lists.newArrayList(); 
        opcInfoListTwo.forEach(item->{
            if (StringUtil.equals(item.getPs(), DictCodeList.交易方向_产品认购.getValue())
             && StringUtil.equals(item.getOcpStatus(), "0")) {
                item.setAmount(BigDecimal.ZERO);
                item.setTradeNum(BigDecimal.ZERO);
                opcInfoList.add(item);
            }else {
                if (!StringUtil.isNotEmpty(item.getAmount())) {
                    item.setAmount(BigDecimal.ZERO);
                }
                if (!StringUtil.isNotEmpty(item.getTradeNum())) {
                    item.setTradeNum(BigDecimal.ZERO);
                }
                opcInfoList.add(item);
            }
        });    
        System.out.println();
        // 占用状态实占信息
        List<RskOccupyInfo> ocpInfo = new ArrayList<>();
        // 释放状态实占信息
        List<RskOccupyInfo> rlsOcpInfo = new ArrayList<>();
        // 数据分离（实占的占用和释放分离）
        delivery(opcInfoList, ocpInfo, rlsOcpInfo);
        List<RskParamReCalcReaderDto> resList = new ArrayList<>();
        RskParamReCalcReaderDto dto = new RskParamReCalcReaderDto();
        // 定义实占集合信息
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        // 实占-释放
        calcAmount(ocpInfo, rlsOcpInfo);
        // 加上实占信息 
        dataMapList.addAll(toReaderResult(ocpInfo));
        dto.setDataMapList(dataMapList);
        resList.add(dto);
        return resList;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param prodIdListTwo
     * @return
     * @see
     * @since 1.0
     */
    private List<String> getProdIdList(List<String> prodIdListTwo) {
        List<String> prodIdList = Lists.newArrayList();
        if (StringUtil.isEmptyList(prodIdListTwo)) {
            return null;
        }
        prodIdListTwo.forEach(item->{
            if (item.startsWith("BANK_")) {
                return;
            }
            if (item.startsWith("F16_")) {
                prodIdList.add(item.replace("F16_", ""));
            }else {
                prodIdList.add(item);
            } 
        });
        return prodIdList;
    }

    /**
     * 计算剩余额度
     * 
     * @param ocpInfo 占用状态信息
     * @param rlsInfo 释放状态信息
     * @see
     * @since 1.0
     */
    private  void calcAmount(List<RskOccupyInfo> ocpInfo, List<RskOccupyInfo> rlsInfo) {
    	 if (StringUtil.isEmptyList(ocpInfo) || StringUtil.isEmptyList(rlsInfo)) {
             return;
         } 
    	 Map<String,List<RskOccupyInfo>> ocpInfoMap = 
    	     ocpInfo.stream().collect(JoyinCollectors.groupingBy(item->item.getEventId()));
    	 List<RskOccupyInfo> ocpInfos = Lists.newArrayList();
    	 for (String key : ocpInfoMap.keySet()) {
    	     BigDecimal amount = BigDecimal.ZERO;
    	     BigDecimal num = BigDecimal.ZERO; 
    	     for (RskOccupyInfo ocp : ocpInfoMap.get(key)) {  
    	         amount = CommonUtil.math(amount, ocp.getAmount(), BussinessDictCodeListRds.操作符_加.getValue()); 
                 num = CommonUtil.math(num, ocp.getTradeNum(), BussinessDictCodeListRds.操作符_加.getValue());
                
    	     }
    	     ocpInfoMap.get(key).get(0).setAmount(amount);
    	     ocpInfoMap.get(key).get(0).setTradeNum(num);
    	     ocpInfos.add(ocpInfoMap.get(key).get(0));    
         }
    	 ocpInfo.clear();
         ocpInfo.addAll(ocpInfos);
    	 ocpInfo.forEach(ocp->rlsInfo.forEach(rls->{
    		 if (!StringUtil.equals(ocp.getEventId(), rls.getEventId())) {
                 return;
             }
             // 原实占金额-释放金额
             BigDecimal amount = CommonUtil.math(ocp.getAmount(), rls.getAmount(), BussinessDictCodeListRds.操作符_减.getValue());
             // 原实占数量-释放数量
             BigDecimal num = CommonUtil.math(ocp.getTradeNum(), rls.getTradeNum(), BussinessDictCodeListRds.操作符_减.getValue());
             if (StringUtil.equals(ocp.getPs(), DictCodeList.交易方向_产品认购.getValue())) {
                 ocp.setAmount(amount);
                 ocp.setTradeNum(num);
             }else {
                 ocp.setAmount(amount.max(BigDecimal.ZERO));
                 ocp.setTradeNum(num.max(BigDecimal.ZERO));
             }
    	 }));
    	 List<RskOccupyInfo> ocpInfoss = Lists.newArrayList();
    	 ocpInfo.forEach(ocp-> {
    	     if (ocp.getAmount().compareTo(BigDecimal.ZERO)!=0
    	         || ocp.getTradeNum().compareTo(BigDecimal.ZERO)!=0) {
    	         ocpInfoss.add(ocp);
            }
    	 });
    	 ocpInfo.clear();
    	 ocpInfo.addAll(ocpInfoss);
    }

    /**
     * 转换类型
     * 
     * @param ocpInfo 预占信息
     * @return reader返回类型
     * @see
     * @since 1.0
     */
    private <T> List<Map<String, Object>> toReaderResult(List<T> ocpInfo) {
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        if (!StringUtil.isEmptyList(ocpInfo)) {
            ocpInfo.forEach(info -> dataMapList.add(BeanUtil.object2Map(info)));
        }
        return dataMapList;
    }

    /**
     * 计算剩余额度
     * 
     * @param ocpInfo 占用状态信息
     * @param rlsInfo 释放状态信息
     * @see
     * @since 1.0
     */
    private <T> void calcAmount(List<RskPreOccupyInfo> ocpInfo, List<T> rlsInfo, String op) {
        calcAmount(ocpInfo, rlsInfo, (ocp, rls) -> {
            RskPreOccupyInfo newInfo = new RskPreOccupyInfo();
            BeanUtil.copyPropertiesIgnoreNull(newInfo, rls);
            if (!StringUtil.equals(ocp.getEventId(), newInfo.getEventId())) {
                return;
            }
            // 原预占金额-释放金额
            BigDecimal amount = CommonUtil.math(ocp.getAmount(), newInfo.getAmount(), op);
            ocp.setAmount(amount.max(BigDecimal.ZERO));
            // 原预占数量-释放数量
            BigDecimal num = CommonUtil.math(ocp.getTradeNum(), newInfo.getTradeNum(), op);
            ocp.setTradeNum(num.max(BigDecimal.ZERO));
        });
    }

    /**
     * 计算剩余额度
     * 
     * @param ocpInfo 占用状态信息
     * @param rlsInfo 释放状态信息
     * @see
     * @since 1.0
     */
    private <T> void calcAmount(List<RskPreOccupyInfo> ocpInfo, List<T> rlsInfo,
                                DoHelper<T> helper) {
        if (StringUtil.isEmptyList(ocpInfo) || StringUtil.isEmptyList(rlsInfo) || helper == null) {
            return;
        }
        ocpInfo.forEach(ocp -> rlsInfo.forEach(rls -> helper.consume(ocp, rls)));
    }

    protected interface DoHelper<T> {

        void consume(RskPreOccupyInfo ocpInfo, T other);
    }

    /**
     * 将预占信息中的占用状态和释放状态数据分离
     * 
     * @param infoList 预占信息
     * @param ocpInfo 占用状态信息
     * @param rlsInfo 释放状态信息
     * @see
     * @since 1.0
     */
    private <T> void delivery(List<T> infoList, List<T> ocpInfo, List<T> rlsInfo) {
        try {
            if (StringUtil.isEmptyList(infoList)) {
                return;
            }
            if (ocpInfo == null) {
                ocpInfo = new ArrayList<>();
            }
            if (rlsInfo == null) {
                rlsInfo = new ArrayList<>();
            }
            for (T info : infoList) {
                // 占用状态
                if (StringUtil.equals(BeanUtil.getProperty(info, "ocpStatus"), "0")) { 
                    ocpInfo.add(info);
                }
                // 释放状态
                else {
                    rlsInfo.add(info);
                }
            }
        }
        catch (Exception e) {
            logger.error("预占/实占状态异常", e);
        }
    }
}
