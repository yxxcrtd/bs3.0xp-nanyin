package com.joyintech.tams.batch.svreport.chb.proddurationreg.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.chb.proddurationreg.service.ProdDurationRegRegistBatchService;

/**
 * 功能:产品存续期登记自动生成批处理入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ProdDurationRegCBMainService extends AutoSendCBBaseService {

    @Autowired
	private ProdDurationRegRegistBatchService prodDurationRegRegistBatchService;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void generateDurationRegData(Date batchDate,List<String> prodIdList) throws BatchException {
        prodDurationRegRegistBatchService.generateDurationRegData(batchDate, prodIdList);
    }

}