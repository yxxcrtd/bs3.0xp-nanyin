package com.joyintech.tams.valuation.valapi.intercept;
/**
 * @author yanya
 * @create 2021-02-02 9:13
 */

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiMethod;
import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiParameter;
import com.joyintech.tams.valuation.valapi.tracker.ValInterfApiTracker;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @ClassName ValInterfApiInterceptor
 * @Description: 调用估值api时拦截器
 * @Author yanya
 * @Date 2021/2/3
 * @Version V1.0
 **/
public class ValInterfApiInterceptor implements MethodInterceptor {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //获取执行方法
        Method method = methodInvocation.getMethod();
        if(method.isAnnotationPresent(ValInterfApiMethod.class)) {//加了@ValInterfApiMethod，被拦截
            Object[] methodParams = methodInvocation.getArguments();
            if(methodParams != null && methodParams.length > 0) {
                Boolean[] parameterAnnotations = getMethodParameterNamesByAnnotation(method,  methodParams.length);
                int index = 1;//当前对象参数 index
                for(Object obj : methodParams) {
                    if(obj != null) {
                        logger.info("调用估值API【"+method.getName()+"】，参数为：" + obj.toString());
                    }
                    else {
                        logger.info("调用估值API【"+method.getName()+"】，参数为空！");
                    }
                    //参数校验：参数中不能为空，注意：此处不校验业务逻辑，具体的业务逻辑在各自方法中校验
                    ValInterfApiTracker.trackValApi(index, obj, parameterAnnotations[index-1]);
                    index++;
                }
                return methodInvocation.proceed();
            }
            else {
                logger.info("调用估值API【"+method.getName()+"】，无参数！");
                return methodInvocation.proceed();
            }
        }
        else {
            return methodInvocation.proceed();
        }
    }

    /**
     * 获取给 "方法参数" 进行注解的值
     * @param method 要获取参数名的方法
     * @param length 参数长度
     * @return 是否判定为空的注解
     */
    public static Boolean[] getMethodParameterNamesByAnnotation(Method method, int length) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Boolean[] parameterNames = new Boolean[length];
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
           for(int i=0;i<length;i++) {
               parameterNames[i] = true;
           }
        } else {
            int i = 0;
            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                boolean flag = true;
                for (Annotation annotation : parameterAnnotation) {
                    if (annotation instanceof ValInterfApiParameter) {
                        flag = false;
                        break;
                    }
                }
                parameterNames[i] = flag;
                i++;
            }
        }
        return parameterNames;
    }

}
