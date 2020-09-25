package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dao.DisReportProdIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
@Service
@Scope("prototype")
public class DisReportProdIndexWriter extends BaseWriter<NeedToWriteEntity> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DisReportProdIndexMapper disReportProdIndexMapper;
	
    @SuppressWarnings("unchecked")
	@Override
	public synchronized void write(List<? extends NeedToWriteEntity> items) throws Exception {
    	// 获取process返回组合的list值
    	List<DisReportProdIndexIntoResult>resultList = (List<DisReportProdIndexIntoResult>) items;

    			// 循环返回值list
    			for (DisReportProdIndexIntoResult result : resultList) {
    				// 获取产品指标数据list
    				List<DisReportProdIndexResult> repProdIndexList = result.getRepProdIndexList();

    				// 循环进行数据插入
    				if (repProdIndexList != null && !repProdIndexList.isEmpty()) {
    					for (DisReportProdIndexResult dto : repProdIndexList) {
    						int  i = disReportProdIndexMapper.updateRepoprodIndexALL(dto);
    					}
    				}
    			}
	}

}
