package com.joyintech.tams.disclosure.information.reveal.genreport.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.busicommon.genreport.dto.BatchGenResultDto;
import com.joyintech.tams.disclosure.busicommon.genreport.service.BatchGenReportService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;

@Service
@Transactional(rollbackFor = Exception.class)
public class BatchGenReport001Service extends CommonExportService<RepDetailsShow001Dto>{
	
	@Autowired
	private BatchGenReportService batchGenReportService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(BatchGenReport001Service.class);
	/**
     * 
     * 查询对应模板所有的查询条件
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    public List<ProdInfoListEntity> queryProdList(String repId,Date execDate){
    	return batchGenReportService.queryProdList(repId, execDate);
    }
	
	public List<BatchGenResultDto> batchExec(String repId,List<RepProdInfo> listProd,Date execDate) {
		return batchGenReportService.exec(repId, listProd, execDate);
	}

}
