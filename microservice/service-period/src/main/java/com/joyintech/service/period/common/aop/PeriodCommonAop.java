/*
 * 文件名：PeriodCommonAop.java
 * 版权：Copyright 2020-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V3.0
 * 修改人：裴家俊
 * 修改时间：2021年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V3.0
 */
 
package com.joyintech.service.period.common.aop;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


/**
 * 存续期查询AOP
 * @author 裴家俊
 * @version 1.0 2021年7月16日
 * @see PeriodCommonAop
 * @since 1.0
 */
@Component
@Aspect
public class PeriodCommonAop {
    
    private static String STATUS_VALID="statusValid";

    private static String STATUS_OTHER="99";

    /**
     * 存续期主画面交易数据查询
     **/
    @Pointcut("execution(* com.joyintech.service.period.trdbondduration.dao.TrdBondDurationMapper.findPage(..))"            //债券
            + "|| execution(* com.joyintech.service.period.trdrepoduration.dao.TrdRepoDurationMapper.findPage(..)) "        //质押式回购
            + "|| execution(* com.joyintech.service.period.trdbuyoutduration.dao.TrdRepoBuyoutDurationMapper.findPage(..)) "  //买断式回购
            + "|| execution(* com.joyintech.service.period.trdseclendduration.dao.TrdSecLendDurationMapper.findPage(..)) "  //债券借贷
            + "|| execution(* com.joyintech.service.period.trdbondlendtargetduration.dao.TrdBondLendTargetDurationMapper.findPage(..)) "  //融入/出券收付息
            + "|| execution(* com.joyintech.service.period.trdlendduration.dao.TrdLendDurationMapper.findPage(..)) "  //拆借
            + "|| execution(* com.joyintech.service.period.trdplanintduration.dao.TrdPlanInterestDurationMapper.findPage(..)) "  //计划类利率型
            + "|| execution(* com.joyintech.service.period.trdprojduration.dao.TrdProjectDurationMapper.findPage(..)) "  //项目类
            + "|| execution(* com.joyintech.service.period.trdstockconnectbonus.dao.TrdStockConnectBonusMapper.findPage(..)) "  //港股通
            + "|| execution(* com.joyintech.service.period.trddepoduration.dao.TrdDepoDurationMapper.findPage(..)) ")       //存款
    protected void findPagePoint() {}

    @Before(value="findPagePoint()")
    public void findPageMethod(JoinPoint  joinPoint){
        if(!CommonUtil.isType(CacheUtil.getSysParam(RiskUtil.IS_DISPLAY_PEND_PERIOD), BussinessDictCodeListRds.是否_否)
                ||!StringUtil.isNotEmpty(joinPoint)){
            return ;
        }
        Object paramOb = joinPoint.getArgs()[0];
        if(paramOb instanceof Map){
            Map paramMap=(Map)paramOb;
            packageMap(paramMap,obj->packageStatusValidMap(obj));
        }else{
            Map paramMap=BeanUtil.objectToMap(paramOb);
            packageMap(paramMap,obj->packageStatusValidMap(obj));
        }
    }

    private  <T> void packageMap(T t,Consumer<T>... consumerList){
        for(Consumer<T> consumer:consumerList){
            consumer.accept(t);
        }
    }

    /**
     * 过滤存续期查询待处理数据
     * @param paramMap
     */
    private void packageStatusValidMap(Map<String,Object> paramMap){
        if(paramMap.get(STATUS_VALID) instanceof List) {
            if (StringUtil.isEmptyList((List)paramMap.get(STATUS_VALID))) {
                paramMap.put(STATUS_VALID, Arrays.asList(DictCodeListExt1.画面数据状态_流程中.getValue(),DictCodeListExt1.画面数据状态_有效.getValue(),DictCodeListExt1.画面数据状态_撤销.getValue()));
            }
        }else{
            if (!StringUtil.isNotEmpty(paramMap.get(STATUS_VALID))) {
                paramMap.put(STATUS_VALID, DictCodeListExt1.画面数据状态_流程中.getValue()+GlobalConstant.COMMA+DictCodeListExt1.画面数据状态_有效.getValue()+GlobalConstant.COMMA+DictCodeListExt1.画面数据状态_撤销.getValue());
            }
        }
    }
}
