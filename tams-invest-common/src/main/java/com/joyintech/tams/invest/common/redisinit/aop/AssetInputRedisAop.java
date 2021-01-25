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

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.common.riskinfrds.param.entity.IParam;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    @Autowired(required = false)
    private IndicatorParamService indicatorParamService;

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
                        // 更新指标参数值
                        if(indicatorParamService!=null) {
                        	String[] keys = {RedisKeyEnum.指标参数配置.key()};
                        	Map<String,Map<String,IParam>> params = indicatorParamService.readAll(Arrays.asList(keys));
                        	ImplCond condp = new ImplCond();
                        	condp.setPlateDate(iPlatformDate.getSysDate());
                        	condp.setDel(false);
                        	condp.setLoadOffset(true);
                            condp.setIsUpdParVal(true);
                            condp.setCondList(Arrays.asList(finProduct.getFinprodId()));
                            Set<String> fields = new HashSet<>();
                        	for(IParam iParam:params.get(RedisKeyEnum.指标参数配置.key()).values()) {
                        		if(iParam.getRedisFieldArr()!=null&&iParam.getRedisFieldArr().length==1&&StringUtil.equals(iParam.getRedisFieldArr()[0], "assetCode")) {
                        			String field = iParam.getField();
                        			field = StringUtil.contains(field, GlobalConstant.COLON)? StringUtil.substring(field, 0, StringUtil.indexOf(field, GlobalConstant.COLON)): field;
                        			fields.add(field);
                        		}
                        	}
                        	for(String field: fields) {
                        		 condp.setParamCodeArray(field);
                                 redisRriter.dealRiskIndParam(condp);
                        	}
                        	
                        }
                        logger.info("===========资产进入redis结束=============");
                    }
                    else {
                    	logger.info("===========资产读取失败=============");
                    	if(StringUtil.isNotEmpty(finProduct)) {
                    		logger.info("===========资产id:"+finProduct.getFinprodId()+"=============");
                    	}
                    	else {
                    		logger.info("===========资产:"+finProduct+"=============");
                    	}
                    }
                }catch (Exception e){
                    //异常无需抛出，在内部处理掉
                    logger.error("资产进入redis异常",e);
                }
            });

        }
    }
}
