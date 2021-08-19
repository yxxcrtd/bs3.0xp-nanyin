/*
 * 文件名：PeriodRdsCommonAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.common.aop;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.redisinit.core.AopBaseService;
import com.joyintech.tams.invest.period.trdfundobversion.service.TrdFundObversion001Service;

/**
 * 存续期提交与撤销时调用风险逻辑aop
 *
 * @author 裴家俊
 * @version 1.0 2020年1月7日
 * @see PeriodRdsCommonAop
 * @since 1.0
 */
@Component
@Aspect
public class PeriodRdsCommonAop extends AopBaseService<InterfRiskRdsDto> {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PeriodRdsCommonAop.class);

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    private List<String> 利率互换_付息_融入券_计息要素维护_已到期资产转让=Arrays.asList("TrdIRSDuration004Service","TrdBondLendTargetDuration002Service","TrdOverdueCalcuPenaltyService","TrdSecOverdueDuration003Service","TrdOptionDuration002Service");

    /**
     * 提交与撤销操作
     */
    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.doComplete(..))")
    protected void completePoint() {

    }

    @AfterReturning("completePoint()")
    public void completePeriodMethod(JoinPoint joinPoint) throws BusinessException {
        if(!CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)||!StringUtil.isNotEmpty(joinPoint.getArgs())||joinPoint.getArgs().length<=1){
            return;
        }
        //利率互换_付息_融入券去除 ,计息要素维护去除
        if(利率互换_付息_融入券_计息要素维护_已到期资产转让.contains(joinPoint.getTarget().getClass().getSimpleName())
            // add by zhangrui1 基金份额折算不进行风控 start
            || StringUtil.equals(TrdFundObversion001Service.class.getSimpleName(), joinPoint.getTarget().getClass().getSimpleName())
            // add by zhangrui1 基金份额折算不进行风控 start
            ){
            return;
        }
        logger.info("==========={}类调用实占或释放接口开始=============",joinPoint.getTarget().getClass().getSimpleName());
        //构造调用场景与交易类型
        InterfRiskRdsDto interfRiskRdsDto = new InterfRiskRdsDto();
        interfRiskRdsDto.setEventType(BussinessDictCodeListRds.事件类型_投资交易.getValue());
        interfRiskRdsDto.setEventStatus(BussinessDictCodeListRds.事件类型_投资交易_存续期.getValue());
        //调用共通方法包括处理封装实体及调用接口逻辑
        completePeriodCommon(joinPoint,interfRiskRdsDto,(param,entry)->interfRiskRdsService.sendRiskRdsData(param, entry));
        logger.info("==========={}类调用实占或释放接口结束=============",joinPoint.getTarget().getClass().getSimpleName());

    }

    /**
     * 构造调用风险实体
     * @param mapTrd
     * @param joinPoint
     * @throws BusinessException
     */
    @Override
    protected void assembleCalEntity(Map<String, Object> mapTrd, JoinPoint joinPoint) throws BusinessException {
        try {
            //债券借贷
            if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdSecLendDuration002Service")){
                if(StringUtil.isNotEmpty(mapTrd.get("trdFinProductDealTotal"))) {
                    WflTrdFinProductDealTotal wflTrdFinProductDealTotal = (WflTrdFinProductDealTotal) JsonUtils.map2entity((Map)mapTrd.get("trdFinProductDealTotal"), WflTrdFinProductDealTotal.class);
                    setBasicTradeInfo(mapTrd, wflTrdFinProductDealTotal.getPs(), wflTrdFinProductDealTotal.getFinprodType2());
                }
            }
            else if(StringUtil.isNotEmpty(mapTrd.get("baseTotalTradeInfoDto"))) {
                    BaseTotalTradeInfoDto baseTotalTradeInfoDto = (BaseTotalTradeInfoDto) JsonUtils.map2entity((Map)mapTrd.get("baseTotalTradeInfoDto"),BaseTotalTradeInfoDto.class);
                    if (StringUtil.isNotEmpty(baseTotalTradeInfoDto) && StringUtil.isNotEmpty(baseTotalTradeInfoDto.getTrdFinProductDealTotal())) {
                        String ps = baseTotalTradeInfoDto.getTrdFinProductDealTotal().getPs();
                        if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdFundDurationNew001Service")){
                            ps = (String) mapTrd.get("ps");
                        }
                        setBasicTradeInfo(mapTrd,ps,baseTotalTradeInfoDto.getTrdFinProductDealTotal().getFinprodType2());
                    }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }

    }

    /**
     * 设置每个交易品种过来的类型(分配的明细列表)
     * @param mapTrd
     * @param joinPoint
     */
    @Override
    protected Object setEachAstType(Map<String,Object> mapTrd,JoinPoint joinPoint){
        //股票
        if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdStockBonus002Service")){
            return mapTrd.get("dividDetailInfoList");
        }
        //债券行权
        if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdBondDuration003Service")){
            return mapTrd.get("trdBondPtlSecExerciseInfos");
        }
        return mapTrd.get("finAssetIntInfoList");
    }

}
