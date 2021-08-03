package com.joyintech.tams.invest.period.common.aop;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PeriodCreditHandlerAop {

    @Autowired
    private PeriodCommonService periodCommonService;


    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.doComplete(..))")
    protected void completePoint() {

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
