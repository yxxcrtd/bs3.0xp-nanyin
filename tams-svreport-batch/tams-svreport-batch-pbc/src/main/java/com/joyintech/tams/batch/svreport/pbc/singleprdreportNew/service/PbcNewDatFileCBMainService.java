package com.joyintech.tams.batch.svreport.pbc.singleprdreportNew.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.pbc.classic.pbcNewDat.service.PbcNewPrdDatFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 人行报表dat文件生成批处理
 * @author zhangzhongliang
 * @version 1.7 2019年6月17日
 * @since 1.7
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class PbcNewDatFileCBMainService extends AutoSendCBBaseService{

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PbcNewPrdDatFileService pbcNewPrdDatFileService;
    
	public Map<String, List<String>> generatePbcDatFile(Date batchDate,List<String> prodList) {
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.人行单产品dat文件生成批处理);
		logInfoBatchStart();
		try {
			//生成全部类型的dat文件，产品listProds暂为空
			pbcNewPrdDatFileService.genDataFile(null, prodList, batchDate, null);
			
		} catch (Exception e) {
			logger.error("人行报表dat文件生成批处理Error",e);
			throw e;
		}
		logInfoBatchEnd();
		return mapPara;
	}
}
