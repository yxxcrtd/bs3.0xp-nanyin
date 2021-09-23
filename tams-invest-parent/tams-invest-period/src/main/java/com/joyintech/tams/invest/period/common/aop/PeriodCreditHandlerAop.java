package com.joyintech.tams.invest.period.common.aop;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
public class PeriodCreditHandlerAop {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.check(..))")
    protected void checkPoint(){
    }

    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.doComplete(..))")
    protected void completePoint() {
    }

    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.doInsert(..))")
    protected void insertPoint() {
    }

    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.doBack(..))")
    protected void backPoint() {
    }

    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.doUpdate(..))")
    protected void updatePoint() {
    }

    @AfterReturning(pointcut = "checkPoint()", returning = "ajaxResult")
    public void creditOccupyCheck(JoinPoint joinPoint, AjaxResult ajaxResult) throws BusinessException {
        if (ajaxResult != null && !StringUtil.isEmptyList(ajaxResult.getErrorList())){
            return;
        }
        if (ajaxResult != null){
            try {
                Object[] args = joinPoint.getArgs();
                AjaxResult ar =  periodCommonService.executeOccupyCreditLines(args[0], DictCodeListExt1.交易状态_初始.getValue(),"check");
                if (ar != null && !StringUtil.isEmptyList(ar.getErrorList())){
                    ajaxResult.setErrorList(ar.getErrorList());
                }
                if (ar != null && StringUtil.isNotEmpty(ar.getData())){
                    if (StringUtil.equals(ajaxResult.getData().toString(),"请确认是否要提交?")
                            || StringUtil.equals(ajaxResult.getData().toString(),"请确认是否要保存?")){
                        ajaxResult.setData(ar.getData());
                    }else {
                        ajaxResult.setData(ar.getData() + String.valueOf(ajaxResult.getData()));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @After("insertPoint()")
    public void creditOccupyOrReleaseInsert(JoinPoint joinPoint)throws BusinessException {
        if(!StringUtil.isNotEmpty(joinPoint.getArgs())){
            return;
        }
        try {
            Object[] args = joinPoint.getArgs();
            OperationType operationType = (OperationType) args[1];
            periodCommonService.executeOccupyCreditLines(args[0], DictCodeListExt1.交易状态_初始.getValue(),"doInsert");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @After("updatePoint()")
    public void creditOccupyMethodUpdate(JoinPoint joinPoint) throws BusinessException {
        if(!StringUtil.isNotEmpty(joinPoint.getArgs())){
            return;
        }
        try {
            Object[] args = joinPoint.getArgs();
            Map<String,Object> mapTrd= JsonUtils.object2Map(args[0]);

            if (StringUtil.isNotEmpty(mapTrd.get("operateType"))&& mapTrd.get("operateType").equals("submit")) {
                periodCommonService.executeOccupyCreditLines(args[0], DictCodeListExt1.交易状态_初始.getValue(),"doUpdate");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @After("backPoint()")
    public void creditReleaseMethodBack(JoinPoint joinPoint) throws BusinessException {
        if(!StringUtil.isNotEmpty(joinPoint.getArgs())){
            return;
        }
        try {
            Object[] args = joinPoint.getArgs();
            Map<String,Object> mapTrd= JsonUtils.object2Map(args[0]);
            periodCommonService.executeOccupyCreditLines(args[0], DictCodeListExt1.交易状态_撤销.getValue(),"doBack");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 卖出生效释放授信、买入撤销生效释放授信和卖出撤销生效占用授信
     */
    @After("completePoint()")
    public void creditOccupyOrReleaseMethod(JoinPoint joinPoint) throws BusinessException {
        if(!StringUtil.isNotEmpty(joinPoint.getArgs())){
            return;
        }
        try {
            Object[] args = joinPoint.getArgs();
            OperationType operationType = (OperationType) args[1];
            if (operationType.equals(OperationType.REVOKE)||operationType.equals(OperationType.DELETE)) {
                periodCommonService.executeOccupyCreditLines(args[0], DictCodeListExt1.交易状态_撤销.getValue(),"doComplete");
            }else {
                periodCommonService.executeOccupyCreditLines(args[0], DictCodeListExt1.交易状态_初始.getValue(),"doComplete");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
