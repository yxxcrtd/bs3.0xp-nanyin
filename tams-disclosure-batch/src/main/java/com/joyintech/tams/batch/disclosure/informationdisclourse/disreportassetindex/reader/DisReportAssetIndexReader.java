package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dao.DisReportAssetIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;
@Service
@Scope("prototype")
public class DisReportAssetIndexReader extends BaseReader<DisReportAssetIndexResult> {
	
	
	@Autowired
	private DisReportAssetIndexMapper disReportAssetIndexMapper;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<DisReportAssetIndexResult> createList() {
		
		SysBatchExecInfo batchinfo = this.getBatchExecuteInfo();
		SysBatchgrpExecInfo execinfo = this.getBatchExecuteDetail();
		//获取需要自动披露的披露列表
		List<DisReportAssetIndexResult> revealList = new ArrayList<DisReportAssetIndexResult>();
		logger.info("reder开始执行");
		
		Map<String,Object> map = new HashMap<>();
		map.put("startDate", execinfo.getStartDate());
		map.put("endDate", execinfo.getEndDate());
		map.put("prodid", execinfo.getOtherParam1());
		
//		disReportAssetIndexMapper.updateRepoAssetIndex(map);
		
		//获取需要处理的指标信息
		revealList = disReportAssetIndexMapper.getDisReportAssetIndexVal(map);
		
		
		logger.info("reder执行结束,并且listSize"+revealList.size());
		return revealList;
	}
	

}
