/*
 * 文件名：ProductRdsCommonAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年4月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.common.aop;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.redisinit.core.AopBaseService;
import com.joyintech.workflow.engine.constant.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 产品端提交与撤销时调用风险逻辑aop
 *
 * @author 裴家俊
 * @version 1.0 2020年4月21日
 * @see ProductRdsCommonAop
 * @since 1.0
 */
@Component
@Aspect
public class ProductRdsCommonAop extends AopBaseService<InterfRiskRdsDto> {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ProductRdsCommonAop.class);

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    /**
     * 提交与撤销操作
     */
    @Pointcut("execution(* com.joyintech.tams.invest.product.productduration..*.*.doComplete(..))"
         + "|| execution(* com.joyintech.tams.invest.product.feepay..*.*.doComplete(..)) "
        + "|| execution(* com.joyintech.tams.invest.product.paythevatquery..*.*.doComplete(..))")
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

    /**
     * 构造调用风险实体
     * @param mapTrd
     * @param joinPoint
     * @throws BusinessException
     */
    @Override
    protected void assembleCalEntity(Map<String, Object> mapTrd, JoinPoint joinPoint) throws BusinessException {
        try {
            mapTrd.put(InterfUtil.finprodType2, DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
            //若有其它特殊的实体构造情况，在这里处理(主要处理交易方向与金融产品分类)
            //todo
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

}
