/*
 * 文件名：UpdRedisValAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstbondquotationinfo.aop;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplParaRds;
import com.joyintech.tams.invest.common.redisinit.service.RiskRefreshRedisService;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新模块参数逻辑aop
 *
 * @author 裴家俊
 * @version 1.0 2020年6月21日
 * @see UpdRedisValAop
 * @since 1.0
 */
@Component
@Aspect
public class UpdRedisValAop {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(UpdRedisValAop.class);

    @Autowired
    private RiskRefreshRedisService riskRefreshRedisService;

    private static Map<String,String> commonBusinessMap=new HashMap<String, String>(){
        {
            put("MstBondQuotationInfoService", DictCodeList.共通业务类型_债券估值行情信息.getValue());
            put("MstCoinFundQuotationInfo001Service", DictCodeList.共通业务类型_基金类估值行情信息.getValue());
            put("MstNetAssetQuotationInfo001Service", DictCodeList.共通业务类型_计划类资产_净值型_信息.getValue());
            put("MstSharePriceService", DictCodeListExt2.共通业务类型_股票行情信息.getValue());
        }
    };

    @Pointcut("execution(* com.joyintech.tams.invest.basicdata.mstbondquotationinfo.service.MstBondQuotationInfoService.doComplete(..))"
            + "|| execution(* com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.service.MstCoinFundQuotationInfo001Service.doComplete(..)) "
            + "|| execution(* com.joyintech.tams.invest.basicdata.mstnetassetquotationinfo.service.MstNetAssetQuotationInfo001Service.doComplete(..)) "
            + "|| execution(* com.joyintech.tams.invest.basicdata.mstshareprice.service.MstSharePriceService.doComplete(..))")
    protected void completePoint() {

    }

    @AfterReturning("completePoint()")
    public void completePeriodMethod(JoinPoint joinPoint) throws BusinessException {
        if(!CommonUtil.isType(CacheUtil.getSysParam(RiskUtil.PAGE_REDIS_FLAG), BussinessDictCodeListRds.是否_是)||!StringUtil.isNotEmpty(joinPoint.getArgs())||joinPoint.getArgs().length<=1){
            return;
        }
        try {
            logger.info("===========行情数据指标参数更新进入redis开始=============");
            ImplParaRds implParaRds=new ImplParaRds();
            implParaRds.setCommonBusinessType(commonBusinessMap.get(joinPoint.getTarget().getClass().getSimpleName()));
            riskRefreshRedisService.refreshParamRedis(implParaRds);
        } catch (Exception e) {
            logger.error("===========行情数据指标参数更新进入redis异常=============");
        }

    }

}
