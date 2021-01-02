package com.joyintech.tams.invest.batch.prdopenplanupdate.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.service.PrdOpenPlanUpdateReaderService;

/**
 *  批处理-市场日历接入后更新产品开放计划 reader
 */
@Service
@Scope("prototype")
public class PrdOpenPlanUpdateReader extends BaseReader<PrdOpenPlanUpdateDto>{

	@Autowired
	private PrdOpenPlanUpdateReaderService prdOpenPlanUpdateReaderService;
	
	@Override
	public List<PrdOpenPlanUpdateDto> createList() {
		return prdOpenPlanUpdateReaderService.execute(this.getParams());
	}

}
