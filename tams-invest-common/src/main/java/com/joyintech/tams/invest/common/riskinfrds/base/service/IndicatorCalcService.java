/*
 * 文件名：IndicatorCalcService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.base.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicator;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicatorResult;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndividLimit;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IndicatorResult;
import com.joyintech.tams.invest.common.riskinfrds.prod.entity.IPtlIndicator;
import com.joyintech.tams.invest.common.risktrialrds.dict.SupervisionStat;
import com.joyintech.tams.invest.common.risktrialrds.dto.ExprTreeMath;
import com.joyintech.tams.invest.common.risktrialrds.dto.PointDetail;

/**
 * 指标计算服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月19日
 * @see IndicatorCalcService
 * @since 1.0
 */
@Service
public class IndicatorCalcService {

    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 计算指标计算
     * 
     * @param ptlInd 组合关联指标
     * @param indMap 指标信息
     * @param mapData 计算信息
     * @param valMap 参数值
     * @param isOld 是否计算原指标值
     * @see
     * @since 1.0
     */
    public final void calcIndicator(IPtlIndicator ptlInd, Map<String, IIndicator> indMap,
                                    Map<String, Object> mapData,
                                    Map<String, Map<String, Object>> valMap, boolean isOld) {
        Map<String, Set<String>> ptlIndMap = ptlInd.getPtlIndicator();
        Collection<Set<String>> temp = ptlIndMap.values();
        // 指标数量
        int size = 0;
        String assetCode = (String)mapData.get("assetCode");
        for (Set<String> lst : temp) {
            size += lst.size();
        }
        CountDownLatch countDown = new CountDownLatch(size);
        for (Entry<String, Set<String>> item : ptlIndMap.entrySet()) {
            String ptlCode = item.getKey();
            Set<String> indList = item.getValue();
            // 参与计算的参数值
            Map<String, Object> param = valMap.get(ptlCode);
            for (String ind : indList) {
                if (isOld){
                    try {
                        // 指标信息
                        calc(indMap.get(ind), isOld, param, ptlCode, assetCode);
                    } catch (Exception e) {
                        StringBuilder builder = new StringBuilder();
                        param.entrySet().forEach(itemss -> builder.append(itemss.getKey()).append(GlobalConstant.COLON).append(itemss.getValue()).append(GlobalConstant.COMMA));
                        String logStr = builder.toString();
                        logger.info("指标参数详细【{}】", logStr);
                        logger.error("指标计算异常,指标代码：{}", ind);
                    } finally {
                        // 线程计数
                        countDown.countDown();
                    }
                }else {
                    // 添加线程
                    threadPoolTaskExecutor.execute(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                // 指标信息
                                calc(indMap.get(ind), isOld, param, ptlCode, assetCode);
                            } catch (Exception e) {
                                StringBuilder builder = new StringBuilder();
                                param.entrySet().forEach(item -> builder.append(item.getKey()).append(GlobalConstant.COLON).append(item.getValue()).append(GlobalConstant.COMMA));
                                String logStr = builder.toString();
                                logger.info("指标参数详细【{}】", logStr);
                                logger.error("指标计算异常,指标代码：{}", ind);
                            } finally {
                                // 线程计数
                                countDown.countDown();
                            }
                        }

                    });
                }
            }
        }
        try {
            // 等待线程结束
            countDown.await();
        }
        catch (InterruptedException e) {
            logger.error("线程池中断异常", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 指标计算
     * 
     * @param indcator 指标信息
     * @param isOld 是否上次指标值
     * @param param 参数值
     * @param ptlCode 组合代码
     * @param assetCode 资产代码
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    private void calc(IIndicator indcator, boolean isOld, Map<String, Object> param,
                      String ptlCode, String assetCode)
        throws Exception {
        Map<String, IIndicatorResult> resMap = indcator.getIndicatorResult();
        resMap = resMap == null ? new HashMap<>() : resMap;
        IIndicatorResult res = resMap.containsKey(ptlCode) ? resMap.get(
            ptlCode) : new IndicatorResult();
        ExprTreeMath exprTree = new ExprTreeMath(indcator.getExprGrad());
        String exprMath = exprTree.replaceParamByMath(indcator.getExprGrad(), param,
            indcator.getIndType());
        Object val = null;
		try {
			val = exprTree.calc(exprMath);
		} catch (Exception e) {
			logger.error("指标({})计算异常", indcator.getIndName());
		}
        // 原指标计算
        if (isOld) {
            res.setIndicatorOldVal(val);
        }
        // 新指标计算
        else {
            res.setIndicatorVal(val);
            Map<String, IIndividLimit> limitMap = indcator.getIndividLimit(ptlCode);
            // 资产特定阈值信息
            IIndividLimit limit = limitMap == null ? null : limitMap.get(assetCode);
            // 若资产特定阈值信息不存在，则取组合特定阈值信息
            if (limit == null) {
                limit = indcator.getPtlIndividLimit(ptlCode);
            }
            String warnCond = limit != null ? limit.getWarnCond() : indcator.getWarnCond();
            String foulCond = limit != null ? limit.getFoulCond() : indcator.getFoulCond();
            String apprCond = limit != null ? limit.getApproveCond() : indcator.getApproveCond();
            // 调用阈值解析
            PointDetail pointDetail = new PointDetail(foulCond, apprCond, warnCond,
                indcator.getIndType());
            if (val != null) {
                SupervisionStat supStat = pointDetail.check(val);
                res.setIndStatus(changeStatus(supStat));
            }
            else {
                res.setIndStatus(BussinessDictCodeListRds.指标状态_异常_除0.getValue());
            }
            if (limit != null) {
                res.setIndividLimit(limit);
            }
        }
        resMap.put(ptlCode, res);
        indcator.setIndicatorResult(resMap);
    }

    /**
     * 状态值适配
     * 
     * @param supStat 原状态值
     * @return 新状态值
     * @see
     * @since 1.0
     */
    private final String changeStatus(SupervisionStat supStat) {
        if (supStat == SupervisionStat.NORMAL) {
            return BussinessDictCodeListRds.指标状态_通过.getValue();
        }
        if (supStat == SupervisionStat.WARNING) {
            return BussinessDictCodeListRds.指标状态_预警.getValue();
        }
        if (supStat == SupervisionStat.VIOLATION) {
            return BussinessDictCodeListRds.指标状态_禁止.getValue();
        }
        if (supStat == SupervisionStat.APPROVE) {
            return BussinessDictCodeListRds.指标状态_审批.getValue();
        }
        return BussinessDictCodeListRds.指标状态_异常_除0.getValue();
    }

}
