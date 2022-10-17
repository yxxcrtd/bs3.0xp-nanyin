package com.joyintech.tams.disclosure.information.reveal.genreport.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.disclosure.busicommon.genreport.dto.BatchGenResultDto;
import com.joyintech.tams.disclosure.busicommon.genreport.service.BatchGenReportService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dao.RepDetailsShow001Mapper;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;

@Service
@Transactional(rollbackFor = Exception.class)
public class BatchGenReport001Service extends CommonExportService<RepDetailsShow001Dto>{
	
	@Autowired
	private BatchGenReportService batchGenReportService;

	@Autowired
	private RepDetailsShow001Mapper repDetailsShow001Mapper;
	
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
	
	public List<BatchGenResultDto> batchExec(Map<String,Object> params) throws Exception {
		return batchGenReportService.exec(params);
	}

    public RepDetailsShow001Dto getRepScreenList(String repId){
        
        RepDetailsShow001Dto info = repDetailsShow001Mapper.getInfoByRepId(repId);  //查询报表的种类及模板配置等信息
        if(null!=info){
        	List<RepScreen> listScreen  = repDetailsShow001Mapper.getRepScreenList(repId).stream()
            .filter(e -> (!"01".equals(e.getScreenType()) && !"02".equals(e.getScreenType()) ) )
            .collect(Collectors.toList());
        	
            info.setRepScreenList(listScreen); //查询每个报表前台配置的查询参数信息
            
        }
        return info;
    }
}
