package com.joyintech.tams.svreport.chb.datacheck.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubCheck;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.service.TotalCheckBatchService;

/**
 * 功能:数据检查批处理
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbDataCheckRegistBatchService  extends SvrCBBaseService {

    @Autowired
    private BeforeSubCheck beforeSubCheck;
    
    @Autowired
    private TotalCheckBatchService totalCheckBatchService;
    
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
        beforeSubCheck.checkBeforeSub(batchDate);
        // 总体核查
        totalCheckBatchService.autoCheckRegisterReport(batchDate);
		// 批处理结束
		logInfoBatchEnd();
	}

    /**
     * 功能:数据变更接口统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param executeDate
     * @throws BusinessException 
     * @see com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService#execute(java.util.Date, java.util.List, java.util.List)
     */
    @Override
    public void execute(Date executeDate, List<String> prodList, List<String> assetList,Map<String,List<String>> param) throws BusinessException {
        autoCheck(executeDate);
    }

}
