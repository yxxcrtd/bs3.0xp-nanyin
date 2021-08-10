package com.jixu.adapter.common.func;

import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/** 
 * @ClassName 		MyJobFactory
 * @author  		Pan Wei 
 * @Date 			2017年5月12日 下午11:46:39 	
 * @Description		[]  
 */

public class MyJobFactory extends AdaptableJobFactory {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    
    /**
     * 用于自动执行导出job
     */
    protected Object createJobInstance(TriggerFiredBundle bundle) throws InstantiationException {
        //调用父类的方法
        Object jobInstance = null;
        try {
            jobInstance = super.createJobInstance(bundle);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InstantiationException();
        }
        //进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
