package com.joyintech.tams.batch.svreport.datacheck.service;

import java.beans.Introspector;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

/**
 * 功能:数据检查批处理入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrDataCheckCBMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SvrDataCheckCBMainService.class);
	/**
	 * 功能:中债报表总体核查入口
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年4月18日
     * @param batchDate 跑批日
	 * void
	 * @throws BatchException 
	 */
	public void autoCheck(Date batchDate){
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.数据检查);
    	// 批处理开始
    	logInfoBatchStart();
    	// 报送前检查
        executeBean("BeforeSubCheck",batchDate,null,null, null);
//        beforeSubCheck.checkBeforeSub(batchDate);
        //人行报送前检查
        executeBean("PbcBeforeSubCheck",batchDate,null,null, null);
//        pbcBeforeSubCheck.checkBeforeSub(batchDate);
        // 总体核查
        executeBean("TotalCheckBatchService",batchDate,null,null, null);
//        totalCheckBatchService.autoCheckRegisterReport(batchDate);
    	// 批处理结束
    	logInfoBatchEnd();
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
    private String executeBean(String taskBean,Date executeDate,List<String> prodList,List<String> assetList,Map<String,List<String>> param){
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
                chbReportRegistCBBaseService.execute(executeDate, prodList, assetList,param);
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
