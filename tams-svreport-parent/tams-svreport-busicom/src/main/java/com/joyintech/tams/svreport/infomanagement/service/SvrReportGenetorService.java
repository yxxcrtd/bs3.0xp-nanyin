package com.joyintech.tams.svreport.infomanagement.service;
 
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

 
/**
 * 功能:报表生成器
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月8日
 * @修改记录：
 */
@Service
public class SvrReportGenetorService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 功能:基础数据变动自动生成相关报表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月8日
     * @param businessDate 业务日期
     * @param dataType 数据类型，详细参考InterfConstants.code
     */
    public synchronized void generateReport(Date businessDate,String dataType,
            List<String> prodList,List<String> assetList) {
        logger.debug("【基础数据变动自动生成相关报表】开始，开始日期{}，数据类型{},产品{}，资产{}！",
                DateUtil.format(businessDate),dataType,StringUtil.isEmptyList(prodList)?"":StringUtil.joinString(prodList, GlobalConstant.COMMA)
                ,StringUtil.isEmptyList(assetList)?"":StringUtil.joinString(assetList, GlobalConstant.COMMA));
        Date startDate = new Date();
        Long startTime = startDate.getTime();
        // 获取配置信息
        ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(dataType);
        // 获取配置执行的批处理bean
        List<String> listBean = StringUtil.splitToList(GlobalConstant.SEMICOLON,interfConfig.getRefReport());
        // 优先级3200的批处理
        List<String> batchNameFirstList = new ArrayList<String>();
        // 报送前检查的批处理
        List<String> batchNameThirdList = new ArrayList<String>();
        // 优先级3250的批处理
        List<String> batchNameSecondList = new ArrayList<String>();
        logger.debug("【基础数据变动自动生成相关报表】批处理调动按优先级分类！");
        // 根据优先级分批处理
        if (!CollectionUtils.isEmpty(listBean)){
            for (String taskBeanName : listBean){
                // 批处理是否存在
                boolean isExist = false;
                // 按优先级触发，产品头寸、资产头寸先处理
                for (BatchBeanName beanName : BatchBeanName.values()){
                    if (beanName.getBeanName().equals(taskBeanName)) {
                        isExist = true;
                        if (BatchBeanName.产品头寸批处理.getBeanName().equals(taskBeanName)
                                || BatchBeanName.资产头寸批处理.getBeanName().equals(taskBeanName)
                                || BatchBeanName.产品费用头寸批处理.getBeanName().equals(taskBeanName)) {
                            batchNameFirstList.add(beanName.getBatchRealBean());
                        } else if (BatchBeanName.数据检查.getBeanName().equals(taskBeanName)) {
                            batchNameThirdList.add(beanName.getBatchRealBean());
                        } else {
                            batchNameSecondList.add(beanName.getBatchRealBean());
                        }
                    }
                } 
                // 配置错误提示
                if (!isExist){
                    logger.info("【基础数据变动自动生成相关报表】批处理配置错误,不存在批处理：{}！",taskBeanName);
                }
            }
        }
        logger.debug("【基础数据变动自动生成相关报表】处理优先级3200批，开始！");
        doBatch(batchNameFirstList, businessDate, prodList, assetList);
        logger.debug("【基础数据变动自动生成相关报表】处理优先级3200批，结束！");
        logger.debug("【基础数据变动自动生成相关报表】处理报送前检查批，开始！");
        doBatch(batchNameThirdList, businessDate, prodList, assetList);
        logger.debug("【基础数据变动自动生成相关报表】处理报送前检查批，结束！");
        logger.debug("【基础数据变动自动生成相关报表】处理优先级3250批，开始！");
        doBatch(batchNameSecondList, businessDate, prodList, assetList);
        logger.debug("【基础数据变动自动生成相关报表】处理优先级3250批，结束！");
        Date endDate = new Date();
        Long endTime = endDate.getTime();
        Long timeCost = (endTime - startTime);
        logger.debug("【基础数据变动自动生成相关报表】结束,耗时：{}毫秒！",timeCost);
    }

    /**
     * 功能:复制list
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param srcList
     * @return 
     * List<String>
     */
    private List<String> copyList(List<String> srcList){
        if (!CollectionUtils.isEmpty(srcList)){
            List<String> destList = new ArrayList<String>(Arrays.asList(new String[srcList.size()]));
            Collections.copy(destList, srcList);
            return destList;
        } else {
            return new ArrayList<String>();
        }
    }
    
    /**
     * 功能:创建线程池执行批处理
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月10日
     * @param batchNameList 执行的批处理Bean名称
     * @param businessDate 业务日期
     * @param prodList 产品代码
     * @param assetList 资产代码
     * void
     */
    private void doBatch(List<String> batchNameList,Date businessDate,
            List<String> prodList,List<String> assetList){
        // 创建批处理的线程池
        // 创建一个线程池
        int poolSize = 5;
        // 根据批处理的大小创建线程池
        if (batchNameList.size() < poolSize) {
            poolSize = batchNameList.size();
        }
        if (poolSize <= 0){
            return;
        }
        ExecutorService taskPoolFirst = new JoyinThreadPoolExecutor(poolSize);
        List<Callable<Boolean>> tasksFirstList = new ArrayList<>();
        // 创建批处理的任务
        for (int i = 0;i<batchNameList.size();i++) {
            String taskBeanName = batchNameList.get(i);
            tasksFirstList.add(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    Boolean res = true;
                    // 执行批处理
                    if ("fail".equals(doBatchByStep(taskBeanName,businessDate,
                            copyList(prodList),copyList(prodList)))) {
                        res = false;
                    }
                    return res;
                }
            });
        }
        // 为线程池添加任务
        List<Future<Boolean>> taskResult;
        try {
            taskResult = taskPoolFirst.invokeAll(tasksFirstList);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            // 任务结束，停止线程池的所有线程
            taskPoolFirst.shutdown();
            Thread.currentThread().interrupt();
            return ;
        }
        // 获取任务执行结果
        try {
            for (Future<Boolean> res : taskResult) {
                res.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
            return ;
        } finally{
            // 任务结束，停止线程池的所有线程
            taskPoolFirst.shutdown();
        }
    }
    
    /**
     * 功能:执行批处理
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月10日
     * @param taskBean 执行的bean
     * @param executeDate 执行日期
     * @param prodList 产品list
     * @param assetList 资产list
     * @return 
     * String
     */
    private String doBatchByStep(String taskBean,Date executeDate,List<String> prodList,List<String> assetList){
        // 获取对应批处理的bean
        Object bean = null;
        try{
            bean = SpringContextUtil.getBean(Introspector.decapitalize(taskBean));
        }
        catch (BeansException e){
            logger.error("批处理{}执行失败，失败原因:找不到Bean:{}!" ,taskBean,taskBean,e.getMessage(),e);
            return "fail";
        }
        if (bean instanceof SvrCBBaseService) {
            SvrCBBaseService chbReportRegistCBBaseService = (SvrCBBaseService) bean;
            Date startDate = new Date();
            Long startTime = startDate.getTime();
            logger.debug("{}:批处理开始,开始时间：{}！", taskBean,
                    DateUtil.format(startDate, DateUtil.DATETIME_FORMAT_WITH_SSS));
            try {
                // 执行对应批处理
                chbReportRegistCBBaseService.execute(executeDate, prodList, assetList,null);
            } catch (BusinessException e) {
                logger.error("批处理{}执行失败，失败原因:{}",taskBean,e.getMessage(), e);
                return "fail";
            }
            Date endDate = new Date();
            Long endTime = endDate.getTime();
            Long timeCost = (endTime - startTime) ;
            logger.debug("{}:批处理结束,结束时间：{},耗时：{}毫秒！", taskBean,
                    DateUtil.format(endDate, DateUtil.DATETIME_FORMAT_WITH_SSS), timeCost);
        } else {
            logger.error("批处理{}执行失败，失败原因:Bean:{}不是对应的报表登记批处理！",taskBean,bean.getClass().getName());
            return "fail";
        }
        return "success";
    }
    
}