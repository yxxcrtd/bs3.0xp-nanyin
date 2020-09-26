/*
 * 文件名：AccountingCommonBatchService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：gushi
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListTableId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.batch.finproduct.dao.AccountingCommonBatchMapper;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SubjectBalance;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 账务类共通方法
 * 
 * @author zhangwei
 * @version 1.0 2018年11月10日
 * @see AccountingCommonBatchService
 * @since 1.0
 */
@Service
public class AccountingCommonBatchService {

    /**
     * 全局变量：底层科目级别
     */
    private final Long BOTTOM_SUBJECT_LEVEL = 99L;

    /**
     * 金额常量
     */
    private final Integer NUMBER_MONEY = 2;

    /**
     * 默认精度
     */
    private final Integer DEFALUT_SCALE = 12;

    /**
     * 数据库数据获取
     */
    @Autowired
    private AccountingCommonBatchMapper accountingCommonBatchMapper;

    /**
     * 公用处理：科目余额汇总处理
     *
     * @param balance 子余额明细
     * @param subjectLevel subjectLevel
     * @param subjectNo 科目号
     * @param balanceMap 科目余额map
     * @param subjectMap 科目map
     * @param isleaf 是否叶子节点：Y/N
     * @see
     * @since 1.0
     */
    public void collectSubjectBalance(SubjectBalance balance, String subjectNo, Long subjectLevel,
                                      Map<String, SubjectBalance> balanceMap, Map<String, BokSubject> subjectMap,
                                      String isleaf) {
        // 定义 变量.科目信息
        BokSubject subject = new BokSubject();
        if (balance == null) {
            return;
        }
        String fSubjectId;
        // if（入参.子余额明细.科目级别 = 全局变量【底层科目级别】）
        if (this.BOTTOM_SUBJECT_LEVEL.equals(subjectLevel)) {
            // 变量.科目信息 = 入参.科目MAP.get(入参.子余额明细.上级科目号)
            subject = subjectMap.get(balance.getFsubjectId());
            // 变量.科目信息.上级科目号 = 入参.子余额明细.上级科目号
            fSubjectId = balance.getFsubjectId();
        }
        else {
            subject = subjectMap.get(subjectNo);
            fSubjectId = subject.getFsubjectId();
        }

        // if(参数.是否叶子节点 = "是" && 入参.子余额明细.场景代码 in
        // && 入参.子余额明细.场景代码 in ("产品-已实现损益结转",
        // "产品-本期利润已实现结转",
        // "产品-未实现损益结转",
        // "产品-未分配利润结转",
        // "产品-损益平准金结转",
        // "产品-本期利润未实现结转")

        if (DictCodeList.是否_是.getValue().equals(isleaf)
            && (DictCodeListTableId.会计场景代码_产品_已实现损益结转.getValue().equals(balance.getTableId())
                || DictCodeListTableId.会计场景代码_产品_未实现损益结转.getValue().equals(balance.getTableId())
                || DictCodeListTableId.会计场景代码_产品_未分配利润结转.getValue().equals(balance.getTableId())
                || DictCodeListTableId.会计场景代码_产品_损益平准金结转.getValue().equals(balance.getTableId())
                || DictCodeListTableId.会计场景代码_产品_本期利润未实现结转.getValue().equals(balance.getTableId())
                || DictCodeListTableId.会计场景代码_产品_本期利润已实现结转.getValue().equals(balance.getTableId()))) {
            // 入参.子余额明细.结转金额 = 子余额明细.科目余额
            balance.setoCcyKnotsAmt(balance.getBalance());
        }
        // if(参数.是否叶子节点 = "是"
        // && 入参.子余额明细.场景代码 in ("产品(净值型)-认购申购交易" or "产品(净值型)-红利再投"）)
        if (DictCodeList.是否_是.getValue().equals(isleaf)
            && (DictCodeListTableId.会计场景代码_产品_净值型_认购申购交易.getValue().equals(balance.getTableId())
                || DictCodeListTableId.会计场景代码_产品_净值型_红利再投.getValue().equals(balance.getTableId()))) {
            // 入参.子余额明细.申购未实现平准金 = 子余额明细.科目余额
            balance.setPurOUlamt(balance.getBalance());
        }
        // if(参数.是否叶子节点 = "是"
        // && 入参.子余额明细.场景代码 in ("产品(净值型)-赎回到期"）)
        if (DictCodeList.是否_是.getValue().equals(isleaf)
            && DictCodeListTableId.会计场景代码_产品_净值型_赎回到期.getValue().equals(balance.getTableId())) {
            // 入参.子余额明细.申购未实现平准金 = 子余额明细.科目余额
            balance.setRedOUlamt(balance.getBalance());
        }
        // if（科目余额MAP.get(科目号+入参.子余额明细.原币种) == null）
        if (balanceMap.get(subjectNo + balance.getOCcy()) == null) {
            // 变量.本科目余额明细
            SubjectBalance dto = new SubjectBalance();
            dto.setBooksetId(balance.getBooksetId());
            dto.setBookDate(balance.getBookDate());
            dto.setSubjectNo(subjectNo);
            dto.setFsubjectId(fSubjectId);
            dto.setSubjectLevel(subjectLevel);
            dto.setOCcy(balance.getOCcy());
            // 调用共通方法获取科目余额、方向、数量
            Map<String, Object> balanceDeal = this.balanceDirectDealBalance(subject.getBalFlag(),
                balance.getBalFlagHappenDate(), balance.getBalance(), balance.getNum());
            // 设值科目余额
            dto.setBalance(new BigDecimal(balanceDeal.get("balance").toString()));
            // 设值余额数量
            dto.setNum(new BigDecimal(balanceDeal.get("num").toString()));
            // 设值余额方向
            dto.setBalFlagHappenDate(balanceDeal.get("balDirect").toString());
            // 当日发生额
            dto.setTdyOAmt(new BigDecimal(balanceDeal.get("balance").toString()));
            // 当日发生额 方向
            dto.setTdyAmtFlag(balanceDeal.get("balDirect").toString());

            // if（入参.子余额明细.余额方向 = "借方"）
            if (DictCodeList.余额方向_借方.getValue().equals(balance.getBalFlagHappenDate())) {
                // 借方余额 = 子余额明细.科目余额
                dto.setdAmt(balance.getBalance());
                // 原币结转余额借方 = 子余额明细.结转金额
                dto.setoCcyKnotsAmtD(balance.getoCcyKnotsAmt());
                // 原币当日发生额借方 = 子余额明细.科目余额
                dto.setTdyODAmt(balance.getBalance());
                // 原币结转当日发生额借方 = 子余额明细.结转金额
                dto.setTdyOCoDAmt(balance.getoCcyKnotsAmt());
                // 原币申购未实现平准金借 = 子余额明细.申购未实现平准金
                dto.setTdyPurODUlamt(balance.getPurOUlamt());
                // 原币赎回未实现平准金借 = 子余额明细.赎回未实现平准金
                dto.setTdyRedODUlamt(balance.getRedOUlamt());
            }
            else {
                // 贷方余额 = 子余额明细.科目余额
                dto.setcAmt(balance.getBalance());
                // 原币结转余额贷方 = 子余额明细.结转金额
                dto.setoCcyKnotsAmtC(balance.getoCcyKnotsAmt());
                // 原币当日发生额贷方 = 子余额明细.科目余额
                dto.setTdyOCAmt(balance.getBalance());
                // 原币结转当日发生额贷方 = 子余额明细.结转金额
                dto.setTdyOCoCAmt(balance.getoCcyKnotsAmt());
                // 原币申购未实现平准金贷 = 子余额明细.申购未实现平准金
                dto.setTdyPurOCUlamt(balance.getPurOUlamt());
                // 原币赎回未实现平准金贷 = 子余额明细.赎回未实现平准金
                dto.setTdyRedOCUlamt(balance.getRedOUlamt());
            }
            // 调用共通方法获取科目余额、方向、数量
            // 参数 变量.科目信息.余额方向 子余额明细.余额方向 子余额明细.科目余额 0
            balanceDeal = this.balanceDirectDealBalance(subject.getBalFlag(), balance.getBalFlagHappenDate(),
                balance.getoCcyKnotsAmt(), BigDecimal.ZERO);
            // 原币结转余额 <- 上述返回.科目余额
            dto.setoCcyKnotsAmt(new BigDecimal(balanceDeal.get("balance").toString()));
            // 结转余额方向 <- 上述返回.余额方向
            dto.setBalCoFlag(balanceDeal.get("balDirect").toString());
            // 原币结转当日发生额 <- 子余额明细.结转金额
            dto.setTdyOCoAmt(new BigDecimal(balanceDeal.get("balance").toString()));
            // 结转当日发生额方向 <- 上述返回.余额方向
            dto.setTdyCoFlag(balanceDeal.get("balDirect").toString());
            // 是否叶子节点
            dto.setIsleaf(isleaf);
            // 存入map
            balanceMap.put(subjectNo + balance.getOCcy(), dto);
        }
        else {
            SubjectBalance dto = balanceMap.get(subjectNo + balance.getOCcy());
            // 调用共通方法获取科目余额、方向、数量
            Map<String, Object> balanceDeal = this.balanceDirectDealAccrualAddBalance(subject.getBalFlag(),
                dto.getBalFlagHappenDate(), dto.getBalance(), dto.getNum(), balance.getBalFlagHappenDate(),
                balance.getBalance(), balance.getNum());
            // 设值科目余额
            dto.setBalance(new BigDecimal(balanceDeal.get("balance").toString()));
            // 设值余额数量
            dto.setNum(new BigDecimal(balanceDeal.get("num").toString()));
            // 设值余额方向
            dto.setBalFlagHappenDate(balanceDeal.get("balDirect").toString());
            // if（入参.子余额明细.余额方向 = "借方"）
            if (DictCodeList.余额方向_借方.getValue().equals(balance.getBalFlagHappenDate())) {
                // 借方余额 += 子余额明细.科目余额
                dto.setdAmt(ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getdAmt(), balance.getBalance()));
                // 原币结转余额借方 += 子余额明细.结转金额
                dto.setoCcyKnotsAmtD(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getoCcyKnotsAmtD(), balance.getoCcyKnotsAmt()));
                // 原币当日发生额借方 += 子余额明细.科目余额
                dto.setTdyODAmt(ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyODAmt(), balance.getBalance()));
                // 原币结转当日发生额借方 += 子余额明细.结转金额
                dto.setTdyOCoDAmt(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyOCoDAmt(), balance.getoCcyKnotsAmt()));
                // 原币申购未实现平准金借 += 子余额明细.申购未实现平准金
                dto.setTdyPurODUlamt(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyPurODUlamt(), balance.getPurOUlamt()));
                // 原币赎回未实现平准金借 += 子余额明细.赎回未实现平准金
                dto.setTdyRedODUlamt(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyRedODUlamt(), balance.getRedOUlamt()));
            }
            else {
                // 贷方余额 += 子余额明细.科目余额
                dto.setcAmt(ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getcAmt(), balance.getBalance()));
                // 原币结转余额贷方 += 子余额明细.结转金额
                dto.setoCcyKnotsAmtC(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getoCcyKnotsAmtC(), balance.getoCcyKnotsAmt()));
                // 原币当日发生额贷方 += 子余额明细.科目余额
                dto.setTdyOCAmt(ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyOCAmt(), balance.getBalance()));
                // 原币结转当日发生额贷方 += 子余额明细.结转金额
                dto.setTdyOCoCAmt(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyOCoCAmt(), balance.getoCcyKnotsAmt()));
                // 原币申购未实现平准金贷 += 子余额明细.申购未实现平准金
                dto.setTdyPurOCUlamt(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyPurOCUlamt(), balance.getPurOUlamt()));
                // 原币赎回未实现平准金贷 += 子余额明细.赎回未实现平准金
                dto.setTdyRedOCUlamt(
                    ArithmeticUtil.doAdd(NUMBER_MONEY, null, dto.getTdyRedOCUlamt(), balance.getRedOUlamt()));
            }
            if (balance.getoCcyKnotsAmt() != null 
            		&& BigDecimal.ZERO.compareTo(balance.getoCcyKnotsAmt()) != 0) {
                // 结转余额方向
                String balCoFlag = dto.getBalCoFlag();
                // 原币结转余额
                BigDecimal oCcyKnotsAmt = dto.getoCcyKnotsAmt();
                // 变量.本科目余额明细.结转余额方向为空时 借方
                if (StringUtil.isEmpty(balCoFlag)) {
                    balCoFlag = DictCodeList.借贷方向_借.getValue();
                }
                // 变量.变量.本科目余额明细.原币结转余额为空时 0
                if (oCcyKnotsAmt == null) {
                    oCcyKnotsAmt = BigDecimal.ZERO;
                }

                // 调用共通方法获取科目余额、方向、数量
                // 参数 ： 变量.科目信息.余额方向 变量.本科目余额明细.结转余额方向 变量.本科目余额明细.结转余额 0 子余额明细.结转金额 子余额明细.余额方向
                // 0
                balanceDeal = this.balanceDirectDealAccrualAddBalance(subject.getBalFlag(), balCoFlag, oCcyKnotsAmt,
                    BigDecimal.ZERO, balance.getBalFlagHappenDate(), balance.getoCcyKnotsAmt(), BigDecimal.ZERO);
                // 原币结转余额 <- 上述返回.科目余额
                dto.setoCcyKnotsAmt(new BigDecimal(balanceDeal.get("balance").toString()));
                // 结转余额方向 <- 上述返回.余额方向
                dto.setBalCoFlag(balanceDeal.get("balDirect").toString());
                // 结转当日发生额方向
                String tdyCoFlag = dto.getTdyCoFlag();
                // 原币结转当日发生额
                BigDecimal tdyOCoAmt = dto.getTdyOCoAmt();
                // 变量.本科目余额明细.结转当日发生额方向为空时 借方
                if (StringUtil.isEmpty(tdyCoFlag)) {
                    tdyCoFlag = DictCodeList.借贷方向_借.getValue();
                }
                // 变量.本科目余额明细.原币结转当日发生额为空时 0
                if (tdyOCoAmt == null) {
                    tdyOCoAmt = BigDecimal.ZERO;
                }
                // 调用共通方法获取科目余额、方向、数量
                // 参数 ： 变量.科目信息.余额方向 变量.本科目余额明细.原币结转当日发生额 变量.本科目余额明细.原币结转当日发生额方向 0 子余额明细.科目余额
                // 子余额明细.余额方向 0
                balanceDeal = this.balanceDirectDealAccrualAddBalance(subject.getBalFlag(), tdyCoFlag, tdyOCoAmt,
                    BigDecimal.ZERO, balance.getBalFlagHappenDate(), balance.getBalance(), BigDecimal.ZERO);

                // 原币结转当日发生额 <- 上述返回.科目余额
                dto.setTdyOCoAmt(new BigDecimal(balanceDeal.get("balance").toString()));
                // 结转当日发生额方向 <- 上述返回.余额方向
                dto.setTdyCoFlag(balanceDeal.get("balDirect").toString());

            }
            // 当日发生额方向
            String tdyAmtFlag = dto.getTdyAmtFlag();
            // 原币当日发生额
            BigDecimal tdyOAmt = dto.getTdyOAmt();
            // 变量.本科目余额明细.当日发生额方向为空时 借方
            if (StringUtil.isEmpty(tdyAmtFlag)) {
                tdyAmtFlag = DictCodeList.借贷方向_借.getValue();
            }
            // 变量.本科目余额明细.原币当日发生额为空时 0
            if (tdyOAmt == null) {
                tdyOAmt = BigDecimal.ZERO;
            }
            // 调用共通方法获取科目余额、方向、数量
            // 参数 ： 变量.科目信息.余额方向 变量.本科目余额明细.原币当日发生额 变量.本科目余额明细.原币当日发生额方向 0 子余额明细.科目余额
            // 子余额明细.余额方向 0
            balanceDeal = this.balanceDirectDealAccrualAddBalance(subject.getBalFlag(), tdyAmtFlag, tdyOAmt,
                BigDecimal.ZERO, balance.getBalFlagHappenDate(), balance.getBalance(), BigDecimal.ZERO);
            // 当日发生额
            dto.setTdyOAmt(new BigDecimal(balanceDeal.get("balance").toString()));
            // 当日发生额 方向
            dto.setTdyAmtFlag(balanceDeal.get("balDirect").toString());
            // 更新map
            balanceMap.put(subjectNo + balance.getOCcy(), dto);
        }
        // 上级科目不为空，回调
        if (StringUtil.isNotEmpty(fSubjectId)) {
            BokSubject fsubject = subjectMap.get(fSubjectId);
            collectSubjectBalance(balance, fSubjectId, fsubject.getSubjectLevel(), balanceMap, subjectMap,
                DictCodeList.是否_否.getValue());
        }
    }

    /**
     * 公用处理：余额方向处理_余额
     *
     * @param subBalanceDirect 科目余额方向
     * @param actBalanceDirect 实际余额方向
     * @param actBalance 实际余额
     * @param actnum 实际数量
     * @return 余额、数量、余额方向
     * @see
     * @since 1.0
     */
    public Map<String, Object> balanceDirectDealBalance(String subBalanceDirect, String actBalanceDirect,
                                                        BigDecimal actBalance, BigDecimal actnum) {
        // 余额
        BigDecimal balance = BigDecimal.ZERO;
        // 数量
        BigDecimal num = BigDecimal.ZERO;
        // 余额方向
        String balDirect = "";
        // 空指针排除
        actBalance = actBalance == null ? BigDecimal.ZERO : actBalance;
        actnum = actnum == null ? BigDecimal.ZERO : actnum;

        if (!subBalanceDirect.equals(DictCodeList.余额方向_借贷双方.getValue())) {
            if (actBalanceDirect.equals(subBalanceDirect)) {
                balance = actBalance;
                num = actnum;
            }
            else {
                balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
            }
            balDirect = subBalanceDirect;
        }
        else {
            if (actBalanceDirect.equals(DictCodeList.余额方向_贷方.getValue())) {
                if (actBalance.compareTo(BigDecimal.ZERO) < 0) {
                    balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                    num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
                    // 余额方向 = "借方"
                    balDirect = DictCodeList.余额方向_借方.getValue();
                }
                else {
                    balance = actBalance;
                    num = actnum;
                    // 余额方向 = "贷方"
                    balDirect = DictCodeList.余额方向_贷方.getValue();
                }
                // balDirect = actBalanceDirect;

            }
            if (actBalanceDirect.equals(DictCodeList.余额方向_借方.getValue())) {
                if (actBalance.compareTo(BigDecimal.ZERO) < 0) {
                    balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                    num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
                    // 余额方向 = "贷方"
                    balDirect = DictCodeList.余额方向_贷方.getValue();
                }
                else {
                    balance = actBalance;
                    num = actnum;
                    // 余额方向 = "借方"
                    balDirect = DictCodeList.余额方向_借方.getValue();
                }
                // balDirect = actBalanceDirect;

            }
        }

        // 定义返回值
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("balance", balance);// 余额
        params.put("num", num);// 数量
        params.put("balDirect", balDirect);// 余额方向
        return params;
    }

    /**
     * 公用处理：余额方向处理_发生额加余额
     *
     * @param subBalanceDirect 科目余额方向
     * @param actBalanceDirect 实际余额方向
     * @param actBalance 实际余额
     * @param actnum 实际数量
     * @param accrualDirect 发生额方向
     * @param accrual 发生额
     * @param accnum 发生数量
     * @return 余额、数量、余额方向
     * @see
     * @since 1.0
     */
    public Map<String, Object> balanceDirectDealAccrualAddBalance(String subBalanceDirect, String actBalanceDirect,
                                                                  BigDecimal actBalance, BigDecimal actnum,
                                                                  String accrualDirect, BigDecimal accrual,
                                                                  BigDecimal accnum) {
        // 余额
        BigDecimal balance = BigDecimal.ZERO;
        // 数量
        BigDecimal num = BigDecimal.ZERO;
        // 余额方向
        String balDirect = "";
        // 空指针排除
        actBalance = actBalance == null ? BigDecimal.ZERO : actBalance;
        actnum = actnum == null ? BigDecimal.ZERO : actnum;
        accrual = accrual == null ? BigDecimal.ZERO : accrual;
        accnum = accnum == null ? BigDecimal.ZERO : accnum;

        if (!subBalanceDirect.equals(DictCodeList.余额方向_借贷双方.getValue())) {
            if (actBalanceDirect.equals(subBalanceDirect)) {
                balance = actBalance;
                num = actnum;
            }
            else {
                balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
            }
            balDirect = subBalanceDirect;
            if (balDirect.equals(accrualDirect)) {
                balance = ArithmeticUtil.doAdd(DEFALUT_SCALE, RoundingMode.HALF_UP, balance, accrual);
                num = ArithmeticUtil.doAdd(DEFALUT_SCALE, RoundingMode.HALF_UP, num, accnum);
            }
            else {
                balance = ArithmeticUtil.subtract(balance, accrual);
                num = ArithmeticUtil.subtract(num, accnum);
            }
        }
        else {
            // 参数.实际余额方向 = 参数.发生额方向
            if (actBalanceDirect.equals(accrualDirect)) {
                // 余额 = 参数.实际余额 + 参数.发生额
                balance = ArithmeticUtil.doAdd(DEFALUT_SCALE, RoundingMode.HALF_UP, actBalance, accrual);
                // 数量余额 = 参数.实际数量 + 参数.发生数量
                num = ArithmeticUtil.doAdd(DEFALUT_SCALE, RoundingMode.HALF_UP, actnum, accnum);
                // 余额方向 = 参数.实际余额方向
                balDirect = actBalanceDirect;
            }
            else {
                if (actBalance.compareTo(accrual) >= 0) {
                    balance = ArithmeticUtil.subtract(actBalance, accrual);
                    num = ArithmeticUtil.subtract(actnum, accnum);
                    balDirect = actBalanceDirect;
                }
                else {
                    balance = ArithmeticUtil.subtract(accrual, actBalance);
                    num = ArithmeticUtil.subtract(accnum, actnum);
                    balDirect = accrualDirect;
                }
            }
        }

        // 定义返回值
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("balance", balance);// 余额
        params.put("num", num);// 数量
        params.put("balDirect", balDirect);// 余额方向
        return params;
    }

    /**
     * 公用方法：账务类共通方法-基础科目性质查询
     *
     * @param groupId 分组代码 【必填项】
     * @param acctStandardId 会计准则代码 【非必填项】
     * @return 科目性质列表
     * @see
     * @since 1.0
     */
    public List<String> getValSubjectNatureListWithAcctStandardId(String groupId, String acctStandardId) {
        return accountingCommonBatchMapper.getValSubjectNatureList(groupId,acctStandardId);
    }

    /**
     * 科目余额查询 - 期待方向
     *
     * @param searchDto 科目余额查询参数Dto
     * @param expBalanceDirect 期待余额方向
     * @return List<SubjectBalance> 科目余额信息List
     * @see
     * @since 1.0
     */
    public List<SubjectBalance> getSubjectBalanceExpectDirection(SubjectBalanceSearchDto searchDto,
                                                                 String expBalanceDirect) {

        // 调用共通方法：科目余额查询
        List<SubjectBalance> subjectBalanceList = this.getSubjectLatestBalance(searchDto);

        // 循环科目余额，更新相关信息
        for (SubjectBalance vo : subjectBalanceList) {
            // 获取余额方向
            String actBalanceDirect = vo.getBalFlagHappenDate();
            // 获取科目余额
            BigDecimal actBalance = (vo.getBalance() != null) ? vo.getBalance() : BigDecimal.ZERO;
            // 获取本币科目余额
            BigDecimal bactBalance = (vo.getbAmt() != null) ? vo.getbAmt() : BigDecimal.ZERO;
            // 获取数量余额
            BigDecimal actnum = vo.getNum();

            // 调用共通方法：余额方向处理_期待方向
            Map<String, Object> directionInfo = this.balanceDirectDealExpectDirection(
                    expBalanceDirect, actBalanceDirect, actBalance, actnum);

            // 调用共通方法：余额方向处理_期待方向
            Map<String, Object> directionInfoben = this.balanceDirectDealExpectDirection(
                    expBalanceDirect, actBalanceDirect, bactBalance, actnum);
            // 获取原币返回值余额
            BigDecimal balance = directionInfo.get(
                    "balance") == null ? BigDecimal.ZERO : (BigDecimal)directionInfo.get("balance");
            // 获取本币返回值余额
            BigDecimal benbalance = directionInfoben.get(
                    "balance") == null ? BigDecimal.ZERO : (BigDecimal)directionInfoben.get("balance");
            // 获取返回值数量
            BigDecimal num = directionInfo.get(
                    "num") == null ? BigDecimal.ZERO : (BigDecimal)directionInfo.get("num");

            // 塞入科目余额信息中
            vo.setBalance(balance);// 原币科目余额
            vo.setbAmt(benbalance);// 本币科目余额
            vo.setNum(num);// 数量余额
            vo.setBalFlagHappenDate(expBalanceDirect);// 余额方向
        }

        return subjectBalanceList;
    }

    /**
     * 科目余额查询共通--不需要考虑分支特殊处理
     *
     * @return List<SubjectBalance> 结果集
     * @param searchDto 科目余额查询参数Dto
     * @see
     * @since 1.0
     */
    public List<SubjectBalance> getSubjectLatestBalance(SubjectBalanceSearchDto searchDto) {
        // 定义传递参数
        // 若分支序号为空，则查询默认的分支序号
        if (searchDto.getBranch() == null) {
            searchDto.setBranch(BookConstant.DEFALUT_BRANCH); // 默认分支号
        }
        List<SubjectBalance> list = accountingCommonBatchMapper.queryCurrentLatestBalance(searchDto);
        return list;
    }

    /**
     * 公用处理：余额方向处理_期待方向
     *
     * @param expBalanceDirect 期待余额方向
     * @param actBalanceDirect 实际余额方向
     * @param actBalance 实际余额
     * @param actnum 实际数量
     * @return 余额、数量
     * @see
     * @since 1.0
     */
    public Map<String, Object> balanceDirectDealExpectDirection(String expBalanceDirect,
                                                                String actBalanceDirect,
                                                                BigDecimal actBalance,
                                                                BigDecimal actnum) {
        // 余额
        BigDecimal balance = BigDecimal.ZERO;
        // 数量
        BigDecimal num = BigDecimal.ZERO;
        // 空指针排除
        actBalance = actBalance == null ? BigDecimal.ZERO : actBalance;
        actnum = actnum == null ? BigDecimal.ZERO : actnum;

        if (expBalanceDirect.equals(DictCodeList.余额方向_借方.getValue())) {
            if (actBalanceDirect.equals(DictCodeList.余额方向_借方.getValue())) {
                balance = actBalance;
                num = actnum;
            }
            if (actBalanceDirect.equals(DictCodeList.余额方向_贷方.getValue())) {
                balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
            }
        }

        if (expBalanceDirect.equals(DictCodeList.余额方向_贷方.getValue())) {
            if (actBalanceDirect.equals(DictCodeList.余额方向_贷方.getValue())) {
                balance = actBalance;
                num = actnum;
            }
            if (actBalanceDirect.equals(DictCodeList.余额方向_借方.getValue())) {
                balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
            }
        }

        // 定义返回值
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("balance", balance);// 余额
        params.put("num", num);// 数量

        return params;
    }

    /**
     * 科目余额汇总查询 （相当于根据科目类别对所有的科目余额进行余额和数量的总处理）
     *
     * @return resultMap 余额Map(币种，科目余额)
     * @param searchDto 科目余额查询参数Dto
     * @param expectedBalFalg --期待余额方向
     * @param bccySum 是否原币汇总 Y:是  N:否
     * @see 从账务类共通方法移植过来
     * @since 1.0
     */
    public Map<String, BigDecimal> getSubjectBalanceCollection(SubjectBalanceSearchDto searchDto,
                                                               String expectedBalFalg,String bccySum) {
        // 查询账务明细当前表
        searchDto.setBranchHandleFlag("01");
        searchDto.setTaskPri(null);// 批处理优先级设置为null
        List<SubjectBalance> list = this.getSubjectLatestBalance(searchDto);

        // 定义 出参.余额Map
        Map<String, BigDecimal> resultMap = new HashMap<>();

        //如果传值为NULL，默认汇总币种为原币
        bccySum = bccySum == null ? DictCodeList.是否_是.getValue() : bccySum;
        // list1中不存在同一key后面不同值的情况，可直接放入map中
        for (SubjectBalance item : list) {
            //定义 变量.汇总币种 =科目余额DTO.原币
            String sumCcy =item.getOCcy();
            //IF '否“ == 参数.是否原币汇总
            if(DictCodeList.是否_否.getValue().equals(bccySum)){
                //变量.汇总币种 = 科目余额DTO.本位币
                sumCcy = item.getbCcy();
            }

            // 定义 变量.科目余额 = 出参.余额Map.get(币种) != null
            BigDecimal subjectBalance = (resultMap.get(sumCcy) != null) ? resultMap.get(
                    sumCcy) : null;
            // 定义 变量.方向是否一致flag，true一致，false不一致
            boolean balFlag = item.getBalFlagHappenDate().equals(expectedBalFalg);
            // if（变量.科目余额 != null)
            if (subjectBalance != null) {
                if(DictCodeList.是否_否.getValue().equals(bccySum)){
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ：本币科目余额 * -1）
                    subjectBalance = balFlag ? com.joyintech.calculation.util.ArithmeticUtil.doAdd(subjectBalance,
                            item.getbAmt()) : com.joyintech.calculation.util.ArithmeticUtil.doAdd(subjectBalance,
                            com.joyintech.calculation.util.ArithmeticUtil.multiply(item.getbAmt(), new BigDecimal("-1")));
                }else{
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ： 科目余额 * -1）
                    subjectBalance = balFlag ? com.joyintech.calculation.util.ArithmeticUtil.doAdd(subjectBalance,
                            item.getBalance()) : com.joyintech.calculation.util.ArithmeticUtil.doAdd(subjectBalance,
                            com.joyintech.calculation.util.ArithmeticUtil.multiply(item.getBalance(), new BigDecimal("-1")));

                }
            }
            else {
                if(DictCodeList.是否_否.getValue().equals(bccySum)){
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ： 本币科目余额 * -1）
                    subjectBalance = balFlag ? item.getbAmt() : com.joyintech.calculation.util.ArithmeticUtil.multiply(
                            item.getbAmt(), new BigDecimal("-1"));
                }
                else{
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ： 科目余额 * -1）
                    subjectBalance = balFlag ? item.getBalance() : com.joyintech.calculation.util.ArithmeticUtil.multiply(
                            item.getBalance(), new BigDecimal("-1"));
                }
            }

            // 更新 resultMap
            resultMap.put(sumCcy, subjectBalance);
        }
        // 返回 出参.余额Map
        return resultMap;
    }
}
