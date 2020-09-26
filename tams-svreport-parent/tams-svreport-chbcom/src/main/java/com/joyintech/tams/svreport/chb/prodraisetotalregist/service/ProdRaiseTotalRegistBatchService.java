package com.joyintech.tams.svreport.chb.prodraisetotalregist.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;

/**
 * 功能:募集总量登记批处理
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdRaiseTotalRegistBatchService extends SvrCBBaseService {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProdRaiseTotalCBService prodRaiseTotalCBService;
	
	public void generateRegistData(Date executeDate,List<String> prodIdList) {
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.产品募集总量登记报送);
		// 批处理开始
        logInfoBatchStart();
        prodRaiseTotalCBService.generateRegistData(executeDate, prodIdList);
		logInfoBatchEnd();
	}
	

    /**
     * 功能:数据变更接口统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param executeDate
     * @param prodList
     * @param assetList
     * @throws BusinessException 
     * @see com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService#execute(java.util.Date, java.util.List, java.util.List)
     */
    @Override
    public void execute(Date executeDate, List<String> prodList, List<String> assetList,Map<String,List<String>> param) throws BusinessException {
        try {
            generateRegistData(executeDate,prodList);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
            throw new BusinessException(e);
        }
    }
}
