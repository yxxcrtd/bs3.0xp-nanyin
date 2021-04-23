/*
 * 文件名：BookAccountGenerateProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookaccountgenerate.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokDetailIdMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountDetail;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateReadResult;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateResult;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.common.base.entity.BokVouchDetail;
import com.joyintech.tams.common.base.entity.BokVouchMain;
import com.joyintech.tams.common.base.entity.BokVouchRel;

import groovy.lang.Tuple2;

@Service
@Scope("prototype")
public class BookAccountGenerateProcessor extends BaseProcessor<NeedOperateEntity, BookAccountGenerateResult> {

    @Autowired
    private AccountingCommonService accountingCommonService;

    /** modify by yanya on 20200106 账务明细代码mapper**/
    @Autowired
    private BokDetailIdMapper bokDetailIdMapper;

    @Autowired
    private DBUtil dBUtil;

    @Override
    public BookAccountGenerateResult process(NeedOperateEntity item)
        throws Exception {
        BookAccountGenerateReadResult entity = ((BookAccountGenerateReadResult)item);

        //定义账套参数查询参数项list
        List<String> paramCodeList = new ArrayList<String>();
        paramCodeList.add(ValDictCodeList.账套配置表参数项_交易凭证是否合并.getValue());//交易凭证是否合并
        paramCodeList.add(ValDictCodeList.账套配置表参数项_补账凭证是否合并.getValue());//补账凭证是否合并
        paramCodeList.add(ValDictCodeList.账套配置表参数项_补账分录是否合并.getValue());//补账分录是否合并
        
        //调用公用方法 - 账套参数查询
        Map<String,Object> getValBokBooksetExtMap = accountingCommonService.getValBokBooksetExtForMap(entity.getBooksetId(),paramCodeList);
        
        //获取查询参数 
        String isCombTraVoucValue = (String)getValBokBooksetExtMap.get(ValDictCodeList.账套配置表参数项_交易凭证是否合并.getValue());//交易凭证是否合并
        String isCombSupVoucValue = (String)getValBokBooksetExtMap.get(ValDictCodeList.账套配置表参数项_补账凭证是否合并.getValue());//补账凭证是否合并
        String isCombSupRecValue = (String)getValBokBooksetExtMap.get(ValDictCodeList.账套配置表参数项_补账分录是否合并.getValue());//补账分录是否合并
        
        String bookDateStr = DateUtil.format(entity.getBookDate());
        String yearStr = DateUtil.getYearStr(bookDateStr);
        String monthStr = String.valueOf(DateUtil.getMonth(bookDateStr));
        int maxBookAccountNum = Integer.valueOf(accountingCommonService.getMaxBookAccountNum(
            yearStr, monthStr, entity.getBooksetId()));
        
        //20181123  记账凭证修改   leipeng  start
        int 编号位数 = 8;
        
//        int 编号位数 = 6;
//        boolean 更新历史标志 = false;
//        if (maxBookAccountNum > 999999) {
//            编号位数 = 8;
//            更新历史标志 = true;
//        }
      //20181123  记账凭证修改   leipeng  end
        Map<String, List<BookAccountDetail>> 交易凭证集合 = null;
        Map<String, List<BookAccountDetail>> 非交易凭证集合 = new HashMap<>();
        Map<String, List<BookAccountDetail>> 凭证账务明细关联 = new HashMap<>();
        Map<String, List<BookAccountDetail>> 非交易凭证集合待合并 = null;
        Map<String, Map<String,BookAccountDetail>> 非交易凭证集合临时 = new HashMap<>();
        // IF 【交易凭证是否合并】= 是
        if (StringUtil.equals(isCombTraVoucValue,
            DictCodeList.是否_是.getValue())) {
            交易凭证集合 = entity.getBookAccountDetailList().stream().filter(d->StringUtil.isNotEmpty(d.getTradeId())).collect(
                Collectors.groupingBy(d -> d.getBooksetId() + d.getfTradeId()));
        }
        else {
            交易凭证集合 = entity.getBookAccountDetailList().stream().filter(d->StringUtil.isNotEmpty(d.getTradeId())).collect(
                    Collectors.groupingBy(d -> d.getBooksetId() +d.getBookId()+d.getTradeId()));
        }
        /**add by wujie on 20201113 start remark:清理减值-科目合并**/
        
        List<String> tableList = new ArrayList<String>();
        tableList.add(DictCodeListTableId.会计场景代码_摊余成本类债券_减值清理.getValue());
        tableList.add(DictCodeListTableId.会计场景代码_综合收益类债券_减值清理.getValue());
        
        tableList.add(DictCodeListTableId.会计场景代码_摊余成本类收益型计划类资产_减值清理.getValue());
        tableList.add(DictCodeListTableId.会计场景代码_综合收益类收益型资产_减值清理.getValue());

        /** modify by yanya on 20210106 其他债权减值清理相同科目作合并展示 start**/
        tableList.add(DictCodeListTableId.会计场景代码_摊余成本类其他债权投资_减值清理.getValue());
        tableList.add(DictCodeListTableId.会计场景代码_综合收益类其他债权投资_减值清理.getValue());
        /** modify by yanya on 20210106 其他债权减值清理相同科目作合并展示 end**/

        tableList.add(DictCodeListTableId.会计场景代码_拆放同业_减值清理.getValue());
        tableList.add(DictCodeListTableId.会计场景代码_存放同业_减值清理.getValue());

        /** modify by yanya on 20210106 买断式回购还本派息 处理资产代码，凭证中目前按照债券代码生成，现在处理根据账务明细代码查找回购代码进行展示 start**/
        List<String> 买断式回购还本派息 = new ArrayList<String>();
        买断式回购还本派息.add(DictCodeListTableId.会计场景代码_买断式逆回购_期间债券还本派息.getValue());
        买断式回购还本派息.add(DictCodeListTableId.会计场景代码_买断式正回购_期间债券还本派息.getValue());
        Map<String, BokDetailId> 账务明细代码集合 = new HashMap<>();
        /** modify by yanya on 20210106 买断式回购还本派息 处理资产代码，凭证中目前按照债券代码生成，现在处理根据账务明细代码查找回购代码进行展示 end**/

        for (Iterator<Entry<String, List<BookAccountDetail>>> iterator = 交易凭证集合.entrySet().iterator(); iterator.hasNext();) {
        	Entry<String, List<BookAccountDetail>> entry = iterator.next();
        	if(entry.getValue()==null || entry.getValue().size()==0){
        		continue;
        	}
            /** modify by yanya on 20210106 买断式回购还本派息 处理资产代码，凭证中目前按照债券代码生成，现在处理根据账务明细代码查找回购代码进行展示 start**/
            if(买断式回购还本派息.contains(entry.getValue().get(0).getTableId())) {
                for (BookAccountDetail detail : entry.getValue()) {
                    //账套代码 及 账务明细代码都不为空
                    if(StringUtil.isNotEmpty(detail.getBokDetailId()) && StringUtil.isNotEmpty(detail.getBooksetId())) {
                        if(账务明细代码集合.containsKey(detail.getBooksetId() + detail.getBokDetailId())) {
                            detail.setBusiId(账务明细代码集合.get(detail.getBooksetId() + detail.getBokDetailId()).getBusiId());
                        }
                        else {
                            BokDetailIdKey bokDetailIdKey = new BokDetailIdKey();
                            bokDetailIdKey.setBooksetId(detail.getBooksetId());
                            bokDetailIdKey.setBokDetailId(detail.getBokDetailId());
                            BokDetailId 账务明细代码 = bokDetailIdMapper.selectByPrimaryKey(bokDetailIdKey);
                            if(账务明细代码 != null && StringUtil.isNotEmpty(账务明细代码.getBusiId())) {
                                detail.setBusiId(账务明细代码.getBusiId());
                                账务明细代码集合.put(detail.getBooksetId() + detail.getBokDetailId(), 账务明细代码);
                            }
                        }
                    }
                }
            }
            /** modify by yanya on 20210106 买断式回购还本派息 处理资产代码，凭证中目前按照债券代码生成，现在处理根据账务明细代码查找回购代码进行展示 end**/
        	if(!tableList.contains(entry.getValue().get(0).getTableId())){
        		continue;
        	}
        	Map<String,BookAccountDetail> 交易凭证科目合并集合= new HashMap<>();//key：科目号 + 原币
        	for (BookAccountDetail detail : entry.getValue()) {
        		String key = detail.getSubjectNo() + detail.getoCcy();
        		BookAccountDetail dto = 交易凭证科目合并集合.get(key);
        		if(dto == null){
        			dto = detail;
        		}
        		else{
        			Map<String, Object> balance = null;
        			if(!dto.getCdFlag().equals(detail.getCdFlag()) 
        					&& BigDecimal.ZERO.compareTo(ArithmeticUtil.doSub(dto.getoHappenAmt(),detail.getoHappenAmt()))<0){
        				balance = accountingCommonService.balanceDirectDealAccrualAddBalance(
                                dto.getCdFlag(), //科目余额方向
                                dto.getCdFlag(), //实际余额方向
                                dto.getoHappenAmt(),//实际余额
                                dto.getHappenNumber(), //实际数量
                                detail.getCdFlag(),//发生额方向
                                detail.getoHappenAmt(), //原币发生额
                                detail.getHappenNumber(),//发生数量
                                dto.getbHappenAmt(),//本币实际余额
                                detail.getbHappenAmt());//本币发生额
        			}
        			else{
        				balance = accountingCommonService.balanceDirectDealAccrualAddBalance(
        						detail.getCdFlag(), //科目余额方向
        						detail.getCdFlag(), //实际余额方向
        						detail.getoHappenAmt(),//实际余额
        						detail.getHappenNumber(), //实际数量
                                dto.getCdFlag(),//发生额方向
                                dto.getoHappenAmt(), //原币发生额
                                dto.getHappenNumber(),//发生数量
                                detail.getbHappenAmt(),//本币实际余额
                                dto.getbHappenAmt());//本币发生额
        			}
                    dto.setbHappenAmt((BigDecimal)balance.get("benbalance"));
                    dto.setCdFlag((String)balance.get("balDirect"));
                    dto.setoHappenAmt((BigDecimal)balance.get("balance"));
                    dto.setHappenNumber((BigDecimal)balance.get("num"));
                    dto.setHappenDate(dto.getHappenDate());
                    
                    
                    
        		}
        		if(!DictCodeList.是否_是.getValue().equals(dto.getAccQuaFlag())){
        			dto.setHappenNumber(null);
        		}
        		交易凭证科目合并集合.put(key, dto);
        	}
        	entry.setValue(交易凭证科目合并集合.values().stream().collect(Collectors.toList()));
        }
        /**add by wujie on 20201113 end remark:清理减值-科目合并**/
        
        
        // IF 【补账凭证是否合并】= 是
        if (StringUtil.equals(isCombSupVoucValue,
            DictCodeList.是否_是.getValue())) {
            // 【补账分录是否合并】= 是
            if (StringUtil.equals(isCombSupRecValue,
                DictCodeList.是否_是.getValue())) {
                // modified by wangzhiang 20180819 凭证生成修改KEY start
                非交易凭证集合待合并 = entity.getBookAccountDetailList().stream().filter(
                    d -> StringUtil.isEmpty(d.getTradeId())).collect(
                        Collectors.groupingBy(d -> d.getBooksetId() + d.getBookId()));
                // modified by wangzhiang 20180819 凭证生成修改KEY end
                非交易凭证集合待合并.entrySet().forEach(entry -> {
                    entry.getValue().forEach(dto -> {
                        String key = entry.getKey() + (DateUtil.compareDate(dto.getHappenDate(),
                            dto.getBookDate()) < 0 ? "0" : "1");
                        Map<String,BookAccountDetail> map = 非交易凭证集合临时.get(key);
                        if (map == null) {
                            map = new HashMap<>();
                            map.put(dto.getSubjectNo(), dto);
                            非交易凭证集合临时.put(key, map);
                            ArrayList<BookAccountDetail> list2 = new ArrayList<>();
                            list2.add(dto);
                            凭证账务明细关联.put(key, list2);
                        }
                        else {
                        	String batchSeqno=null;
                        	Map<String, Object> params=this.getParams();
                            if(map.containsKey(dto.getSubjectNo())) {
                                BookAccountDetail detail = map.get(dto.getSubjectNo());
                                String balFlag = accountingCommonService.getBalFlag(dto.getSubjectNo(),
                                    entity.getBooksetId());
                                // modified by wangzhiang start
                                Map<String, Object> balance = accountingCommonService.balanceDirectDealAccrualAddBalance(
                                        balFlag, detail.getCdFlag(), detail.getoHappenAmt(),
                                        detail.getHappenNumber(), dto.getCdFlag(),
                                        dto.getoHappenAmt(), dto.getHappenNumber(),
                                        detail.getbHappenAmt(),dto.getbHappenAmt());
                                
                                
                                detail.setbHappenAmt((BigDecimal)balance.get("benbalance"));
                                detail.setCdFlag((String)balance.get("balDirect"));
                                detail.setoHappenAmt((BigDecimal)balance.get("balance"));
                                detail.setHappenNumber((BigDecimal)balance.get("num"));
                                detail.setHappenDate(dto.getHappenDate());
                                // modified by wangzhiang end
                                //设计变更   modified by chufengjie 20190520 start
//                                Map<String, Object> balancep = accountingCommonService.balanceDirectDealAccrualAddBalance(
//                                        balFlag, detail.getCdFlag(), detail.getbHappenAmt(),
//                                        detail.getHappenNumber(), dto.getCdFlag(),
//                                        dto.getoHappenAmt(), dto.getHappenNumber());
//                                detail.setbHappenAmt((BigDecimal)balancep.get("balance"));
                                //设计变更   modified by chufengjie 20190520 end
                                凭证账务明细关联.get(key).add(dto);
                            }
                            else {
                                非交易凭证集合临时.get(key).put(dto.getSubjectNo(), dto);
                                凭证账务明细关联.get(key).add(dto);
                            }
                        }
                    });
                });
                非交易凭证集合临时.forEach((k,v)->{
                    非交易凭证集合.put(k, new ArrayList<>(v.values()));
                });
            }
            else {
                // 【补账分录是否合并】= 否
                // modified by wangzhiang 20180819 凭证生成修改KEY start
                非交易凭证集合.putAll(entity.getBookAccountDetailList().stream().filter(
                    d -> StringUtil.isEmpty(d.getTradeId())).collect(
                        Collectors.groupingBy(d -> d.getBooksetId() + d.getBookId())));
                // modified by wangzhiang 20180819 凭证生成修改KEY end
            }
        }
        else {
            // IF 【补账凭证是否合并】= 否
            // modified by wangzhiang 20180819 凭证生成修改KEY start
            非交易凭证集合.putAll(entity.getBookAccountDetailList().stream().filter(
                d -> StringUtil.isEmpty(d.getTradeId())).collect(Collectors.groupingBy(
                    d -> d.getBooksetId() + d.getBookId() + DateUtil.format(d.getHappenDate()))));
            // modified by wangzhiang 20180819 凭证生成修改KEY end
        }
//        int 数量 = 交易凭证集合.keySet().size() + 非交易凭证集合.keySet().size();
        //20181123  记账凭证修改   leipeng  start
        //        if (数量 + maxBookAccountNum > 999999) {
//            编号位数 = 8;
//        }
        //20181123  记账凭证修改   leipeng  end
        List<BokVouchDetail> 凭证子表列表 = new ArrayList<>();
        List<BokVouchMain> 凭证主表列表 = new ArrayList<>();
        List<BokVouchRel> 凭证账务明细关联列表 = new ArrayList<>();
        for (Iterator<Entry<String, List<BookAccountDetail>>> iterator = 交易凭证集合.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, List<BookAccountDetail>> entry = iterator.next();
            maxBookAccountNum = maxBookAccountNum + 1;
            String padBookAccountNum = accountingCommonService.padBookAccountNum(
                String.valueOf(maxBookAccountNum), 编号位数);
            String 凭证编号 = dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型);
            String 业务凭证编号 = DateUtil.format(entity.getBookDate(), "yyyyMMdd") + padBookAccountNum;
            BokVouchMain mainDto = new BokVouchMain();
            mainDto.setVouchNum(凭证编号);
            mainDto.setBusVouchnum(业务凭证编号);
            mainDto.setNum((long)编号位数);
            mainDto.setBooksetId(entity.getBooksetId());
            if(StringUtil.equals(isCombTraVoucValue,
                DictCodeList.是否_是.getValue())){
                mainDto.setTradeId(entry.getValue().get(0).getfTradeId());
            }
            else{
                mainDto.setTradeId(entry.getValue().get(0).getTradeId());
            }
            mainDto.setBookDate(entity.getBookDate());
            mainDto.setVouchYear(Long.valueOf(yearStr));
            mainDto.setVouchMonth(Long.valueOf(monthStr));
            mainDto.setBookType(DictCodeList.凭证类型_日终.getValue());
            int i = 0;
            List<String> tableIdList = new ArrayList<>();
            for (BookAccountDetail detail : entry.getValue()) {
                String 凭证子表编号 = dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型);
                String 业务凭证子表编号 = String.valueOf(++i);
                BokVouchDetail detailDto = new BokVouchDetail();
                detailDto.setVouchSubnum(凭证子表编号);
                detailDto.setBusVouchsubnum(业务凭证子表编号);
                detailDto.setVouchNum(凭证编号);
                detailDto.setHappenDate(detail.getHappenDate());
                detailDto.setTableId(detail.getTableId());
                detailDto.setCdFlag(detail.getCdFlag());
                detailDto.setSubjectNo(detail.getSubjectNo());
                detailDto.setHappenAmt(detail.getoHappenAmt());
                detailDto.setBusiId(detail.getBusiId());
                detailDto.setTradeId(detail.getTradeId());
                detailDto.setCcy(detail.getoCcy());
                detailDto.setDetailSubjectNo(detail.getDetailSubjectNo());
                detailDto.setHappenNumber(detail.getHappenNumber());
                detailDto.setBookType(DictCodeList.凭证类型_日终.getValue());
                //设计变更   modified by chufengjie 20190520 start
                detailDto.setbCcy(detail.getbCcy());
                detailDto.setbHappenAmt(detail.getbHappenAmt());
                //设计变更   modified by chufengjie 20190520 end

                // add by zwq on 2020-12-25 start
                detailDto.setSubjectName(detail.getSubjectName());
                detailDto.setDetailSubjectName(detail.getDetailSubjectName());
                // add by zwq on 2020-12-25 end
                
                // IF 数据字典：是否.否 eq 凭证待处理DTO.0金额是否处理 OR 凭证待处理DTO.0金额是否处理 IS NULL
                if(DictCodeList.是否_否.getValue().equals(detail.getIsexeZeroAmt())
                        && detail.getIsexeZeroAmt() == null) {
                	if (BigDecimal.ZERO.compareTo(detailDto.getHappenAmt()) == 0
                            && (BigDecimal.ZERO.compareTo(detailDto.getbHappenAmt()) == 0)
                    ) {
                        continue;
                    }
                }
                
                凭证子表列表.add(detailDto);
          
                if(!tableIdList.contains(detail.getTableId())){
                    tableIdList.add(detail.getTableId());
                }
                BokVouchRel rel = new BokVouchRel();
                rel.setVouchNum(凭证编号);
                rel.setVouchSubnum(凭证子表编号);
                rel.setSeqno(detail.getSeqno());
                凭证账务明细关联列表.add(rel);
            }
            mainDto.setOffsetFlag(entry.getValue().get(0).getOffsetFlag());
            Tuple2<String, String> mergeTableId = accountingCommonService.mergeTableId(tableIdList);
            mainDto.setComTableId(mergeTableId.getFirst());
            mainDto.setVouchRemark(mergeTableId.getSecond());
            if ("CLAWBACKID".equals(entry.getValue().get(0).getTradeId())
            		||"CLAWBACKFLOATID".equals(entry.getValue().get(0).getTradeId())) {
            	mainDto.setVouchRemark(entry.getValue().get(0).getBookRemark());
            }
            凭证主表列表.add(mainDto);
        }
        /** modify by yanya on 20210104 罚息计提场景增加合并处理(本息和计提，生成多笔凭证，这里合并处理) start**/
        Map<String, List<BookAccountDetail>> 非交易凭证集合合并 = new HashMap<>();
        List<String> 非交易合并场景集合 = new ArrayList<String>();
        非交易合并场景集合.add(DictCodeListTableId.会计场景代码_逾期款罚息计提.getValue());
        for (Iterator<Entry<String, List<BookAccountDetail>>> iterator = 非交易凭证集合.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, List<BookAccountDetail>> entry = iterator.next();
            if(entry.getValue() == null || entry.getValue().size() == 0 ) {
                continue;
            }
            if(!非交易合并场景集合.contains(entry.getValue().get(0).getTableId())) {
                非交易凭证集合合并.put(entry.getKey(), entry.getValue());
            }
            else {
                Map<String,BookAccountDetail> 非交易凭证科目合并集合= new HashMap<>();//key：科目号 + 原币
                for (BookAccountDetail detail : entry.getValue()) {
                    String key = detail.getSubjectNo() + detail.getoCcy();
                    BookAccountDetail dto = 非交易凭证科目合并集合.get(key);
                    if(dto == null){
                        dto = detail;
                    }
                    else{
                        Map<String, Object> balance = null;
                        if(!dto.getCdFlag().equals(detail.getCdFlag())
                                && BigDecimal.ZERO.compareTo(ArithmeticUtil.doSub(dto.getoHappenAmt(),detail.getoHappenAmt()))<0){
                            balance = accountingCommonService.balanceDirectDealAccrualAddBalance(
                                    dto.getCdFlag(), //科目余额方向
                                    dto.getCdFlag(), //实际余额方向
                                    dto.getoHappenAmt(),//实际余额
                                    dto.getHappenNumber(), //实际数量
                                    detail.getCdFlag(),//发生额方向
                                    detail.getoHappenAmt(), //原币发生额
                                    detail.getHappenNumber(),//发生数量
                                    dto.getbHappenAmt(),//本币实际余额
                                    detail.getbHappenAmt());//本币发生额
                        }
                        else{
                            balance = accountingCommonService.balanceDirectDealAccrualAddBalance(
                                    detail.getCdFlag(), //科目余额方向
                                    detail.getCdFlag(), //实际余额方向
                                    detail.getoHappenAmt(),//实际余额
                                    detail.getHappenNumber(), //实际数量
                                    dto.getCdFlag(),//发生额方向
                                    dto.getoHappenAmt(), //原币发生额
                                    dto.getHappenNumber(),//发生数量
                                    detail.getbHappenAmt(),//本币实际余额
                                    dto.getbHappenAmt());//本币发生额
                        }
                        dto.setbHappenAmt((BigDecimal)balance.get("benbalance"));
                        dto.setCdFlag((String)balance.get("balDirect"));
                        dto.setoHappenAmt((BigDecimal)balance.get("balance"));
                        dto.setHappenNumber((BigDecimal)balance.get("num"));
                        dto.setHappenDate(dto.getHappenDate());
                    }
                    if(!DictCodeList.是否_是.getValue().equals(dto.getAccQuaFlag())){
                        dto.setHappenNumber(null);
                    }
                    非交易凭证科目合并集合.put(key, dto);
                }
                非交易凭证集合合并.put(entry.getKey(), 非交易凭证科目合并集合.values().stream().collect(Collectors.toList()));
            }
        }
        //for (Iterator<Entry<String, List<BookAccountDetail>>> iterator = 非交易凭证集合.entrySet().iterator(); iterator.hasNext();) {
        for (Iterator<Entry<String, List<BookAccountDetail>>> iterator = 非交易凭证集合合并.entrySet().iterator(); iterator.hasNext();) {
        /** modify by yanya on 20210104 罚息计提场景增加合并处理(本息和计提，生成多笔凭证，这里合并处理) end**/
            Entry<String, List<BookAccountDetail>> entry = iterator.next();
            maxBookAccountNum = maxBookAccountNum + 1;
            String padBookAccountNum = accountingCommonService.padBookAccountNum(
                String.valueOf(maxBookAccountNum), 编号位数);
            String 凭证编号 = dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型);
            String 业务凭证编号 = DateUtil.format(entity.getBookDate(), "yyyyMMdd") + padBookAccountNum;
            BokVouchMain mainDto = new BokVouchMain();
            mainDto.setVouchNum(凭证编号);
            mainDto.setNum((long)编号位数);
            mainDto.setBusVouchnum(业务凭证编号);
            mainDto.setBooksetId(entity.getBooksetId());
            mainDto.setTradeId(entry.getValue().get(0).getTradeId());
            mainDto.setBookDate(entity.getBookDate());
            mainDto.setVouchYear(Long.valueOf(yearStr));
            mainDto.setVouchMonth(Long.valueOf(monthStr));
            mainDto.setBookType(entry.getValue().get(0).getBookType());
            int i = 0;
            List<String> tableIdList = new ArrayList<>();
            for (BookAccountDetail detail : entry.getValue()) {
                String 凭证子表编号 = dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型);
                String 业务凭证子表编号 = String.valueOf(++i);
                BokVouchDetail detailDto = new BokVouchDetail();
                detailDto.setVouchSubnum(凭证子表编号);
                detailDto.setBusVouchsubnum(业务凭证子表编号);
                detailDto.setVouchNum(凭证编号);
                detailDto.setHappenDate(detail.getHappenDate());
                detailDto.setTableId(detail.getTableId());
                detailDto.setCdFlag(detail.getCdFlag());
                detailDto.setSubjectNo(detail.getSubjectNo());
                detailDto.setHappenAmt(detail.getoHappenAmt());
                detailDto.setBusiId(detail.getBusiId());
                detailDto.setTradeId(detail.getTradeId());
                detailDto.setCcy(detail.getoCcy());
                detailDto.setDetailSubjectNo(detail.getDetailSubjectNo());
                detailDto.setHappenNumber(detail.getHappenNumber());
                detailDto.setBookType(detail.getBookType());
                //设计变更   modified by chufengjie 20190520 start
                detailDto.setbCcy(detail.getbCcy());
                detailDto.setbHappenAmt(detail.getbHappenAmt());
                //设计变更   modified by chufengjie 20190520 end

                // add by zwq 2020-12-25 start
                detailDto.setSubjectName(detail.getSubjectName());
                detailDto.setDetailSubjectName(detail.getDetailSubjectName());
                // add by zwq 2020-12-25 end

                // IF 数据字典：是否.否 eq 凭证待处理DTO.0金额是否处理 OR 凭证待处理DTO.0金额是否处理 IS NULL
                if(DictCodeList.是否_否.getValue().equals(detail.getIsexeZeroAmt())
                        && detail.getIsexeZeroAmt() == null) {
                	if (BigDecimal.ZERO.compareTo(detailDto.getHappenAmt()) == 0
                            && (BigDecimal.ZERO.compareTo(detailDto.getbHappenAmt()) == 0)
                    ) {
                        continue;
                    }
                }
                
                凭证子表列表.add(detailDto);
                if (!tableIdList.contains(detail.getTableId())) {
                    tableIdList.add(detail.getTableId());
                }
                List<BookAccountDetail> list2 = 凭证账务明细关联.get(entry.getKey());
                if (list2 != null) {
                    for (BookAccountDetail d : list2) {
                        if (StringUtil.equals(d.getSubjectNo(), detail.getSubjectNo())) {
                            BokVouchRel rel = new BokVouchRel();
                            rel.setVouchNum(凭证编号);
                            rel.setVouchSubnum(凭证子表编号);
                            rel.setSeqno(d.getSeqno());
                            凭证账务明细关联列表.add(rel);
                        }
                    }
                } else {
                    BokVouchRel rel = new BokVouchRel();
                    rel.setVouchNum(凭证编号);
                    rel.setVouchSubnum(凭证子表编号);
                    rel.setSeqno(detail.getSeqno());
                    凭证账务明细关联列表.add(rel);
                }
            }
            mainDto.setOffsetFlag(entry.getValue().get(0).getOffsetFlag());
            mainDto.setVouchRemark(entry.getValue().get(0).getTableName());
            Tuple2<String, String> mergeTableId = accountingCommonService.mergeTableId(tableIdList);
            mainDto.setComTableId(mergeTableId.getFirst());
            mainDto.setVouchRemark(mergeTableId.getSecond());
            if ("CLAWBACKID".equals(entry.getValue().get(0).getTradeId())
            		|| "CLAWBACKFLOATID".equals(entry.getValue().get(0).getTradeId())) {
            	mainDto.setVouchRemark(entry.getValue().get(0).getBookRemark());
            }
            凭证主表列表.add(mainDto);
        }
        BookAccountGenerateResult result = new BookAccountGenerateResult(凭证主表列表, 凭证子表列表,
            凭证账务明细关联列表,true);
        result.setProdId(entity.getBooksetId());
        return result;
    }
}
