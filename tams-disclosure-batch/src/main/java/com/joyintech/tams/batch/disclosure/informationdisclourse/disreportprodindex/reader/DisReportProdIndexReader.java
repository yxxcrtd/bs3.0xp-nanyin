package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.reader;

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
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dao.DisReportProdIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
@Service
@Scope("prototype")
public class DisReportProdIndexReader extends BaseReader<DisReportProdIndexResult> {
	
	
	@Autowired
	private DisReportProdIndexMapper disReportProdIndexMapper;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<DisReportProdIndexResult> createList() {
		
		SysBatchExecInfo batchinfo = this.getBatchExecuteInfo();
		SysBatchgrpExecInfo execinfo = this.getBatchExecuteDetail();
		//获取需要自动披露的披露列表
		List<DisReportProdIndexResult> revealList = new ArrayList<DisReportProdIndexResult>();
		logger.info("reder开始执行");
		
		Map<String,Object> map = new HashMap<>();
		map.put("startDate", execinfo.getStartDate());
		map.put("endDate", execinfo.getEndDate());
		map.put("prodid", execinfo.getOtherParam1());
		
		//重置报表指标字段
//		disReportProdIndexMapper.updateRepoprodIndex(map);
		
		//获取需要处理的指标信息
		revealList = disReportProdIndexMapper.getDisReportProdIndexVal(map);
		
		
		logger.info("reder执行结束,并且listSize"+revealList.size());
		return revealList;
	}
	

}
