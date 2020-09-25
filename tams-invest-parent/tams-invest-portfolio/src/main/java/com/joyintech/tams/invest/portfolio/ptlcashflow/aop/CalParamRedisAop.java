/*
 * 文件名：CalParamRedisAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlcashflow.aop;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.core.AopBaseService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 调用实占接口不算指标逻辑aop
 *
 * @author 裴家俊
 * @version 1.0 2020年4月17日
 * @see CalParamRedisAop
 * @since 1.0
 */
@Component
@Aspect
public class CalParamRedisAop extends AopBaseService<InterfRiskRdsDto> {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(CalParamRedisAop.class);

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    @Pointcut("execution(* com.joyintech.tams.invest.portfolio.ptlcashflow.service.PtlCashFlowService.doComplete(..))")
    protected void completePoint() {

    }

    @After("completePoint()")
    public void completePeriodMethod(JoinPoint joinPoint) throws BusinessException {
        if(!CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)||!StringUtil.isNotEmpty(joinPoint.getArgs())||joinPoint.getArgs().length<=1){
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

    @Override
    protected void assembleCalEntity(Map<String, Object> mapTrd, JoinPoint joinPoint) throws BusinessException{
    	mapTrd.put(BussinessConstantRds.finprodType2, DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
    }

}
