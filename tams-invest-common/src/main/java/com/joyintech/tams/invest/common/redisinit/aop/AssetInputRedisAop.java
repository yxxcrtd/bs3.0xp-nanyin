/*
 * 文件名：AssetInputRedisAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.redisinit.aop;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 资产新增或修改时同步进redis逻辑aop
 *
 * @author 裴家俊
 * @version 1.0 2020年1月7日
 * @see AssetInputRedisAop
 * @since 1.0
 */
@Component
@Aspect
public class AssetInputRedisAop {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AssetInputRedisAop.class);

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 平台日
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    @Pointcut("execution(* com.joyintech.tams.common.base.dao.FinProductMapper.insert(..))")
    protected void afterPoint() {

    }

    @After("afterPoint()")
    public void afterAssetMethod(JoinPoint joinPoint){
        if(!StringUtil.isNotEmpty(joinPoint.getArgs())){
            return;
        }
        Object[] args = joinPoint.getArgs();
        FinProduct finProduct = (FinProduct) args[0];
        if(StringUtils.equals("99",finProduct.getModelType())){
            return;
        }

        if (CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)) {
            threadPoolTaskExecutor.execute(()->{
                try {
                    Thread.sleep(6000);
                    logger.info("===========资产进入redis开始=============");
                    ImplCond cond = new ImplCond();
                    cond.setDel(false);
//                    Object[] args = joinPoint.getArgs();
//                    FinProduct finProduct = (FinProduct) args[0];
                    if (StringUtil.isNotEmpty(finProduct) && StringUtil.isNotEmpty(finProduct.getFinprodId())) {
                        cond.setAssetIdArray(finProduct.getFinprodId());
                        // 设置平台时间
                        cond.setPlateDate(iPlatformDate.getSysDate());
                        // 更新Redis
                        redisRriter.dealAssetData(cond);
                        logger.info("===========资产进入redis结束=============");
                    }
                }catch (Exception e){
                    //异常无需抛出，在内部处理掉
                    logger.error("资产进入redis异常",e);
                }
            });

        }
    }
}
