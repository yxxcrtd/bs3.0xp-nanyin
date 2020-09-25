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
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dao.DisReportProdIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
@Service
@Scope("prototype")
public class DisReportProdIndexDelReader extends BaseReader<DisReportProdIndexResult> {
	
	
	@Autowired
	private DisReportProdIndexMapper disReportProdIndexMapper;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<DisReportProdIndexResult> createList() {
		
		List<DisReportProdIndexResult> revealList = new ArrayList<DisReportProdIndexResult>();
		logger.info("删除数据read开始执行");
		DisReportProdIndexResult result = new DisReportProdIndexResult();
		revealList.add(result);
		return revealList;
	}
	

}
