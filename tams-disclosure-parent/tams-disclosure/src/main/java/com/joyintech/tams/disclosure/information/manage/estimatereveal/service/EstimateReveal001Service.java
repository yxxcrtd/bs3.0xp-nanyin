package com.joyintech.tams.disclosure.information.manage.estimatereveal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.manage.estimatereveal.dto.EstimateReveal001Dto;

@Service
public class EstimateReveal001Service extends CommonExportService<EstimateReveal001Dto> {

//	@Autowired
//	private EstimateReveal001Mapper estimateReveal001Mapper;
	
	/**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
	public Page<EstimateReveal001Dto> findPage(Map<String, Object> params) throws Exception{

//		String prodId = (String)params.get("prodId");
//        String[] prodIdArr = null;
//        if(StringUtil.isNotEmpty(prodId)){
//            prodIdArr = prodId.split(",");
//        }
//        params.put("prodIdArr", prodIdArr);
//        
//        String jobId = (String)params.get("jobId");
//        String[] jobIdArr = null;
//        if(StringUtil.isNotEmpty(jobId)){
//            jobIdArr = jobId.split(",");
//        }
//        params.put("jobIdArr", jobIdArr);
//        
//        String repType = (String)params.get("repType");
//        String[] repTypeArr = null;
//        if(StringUtil.isNotEmpty(repType)){
//        	repTypeArr = repType.split(",");
//        }
//        params.put("repTypeArr", repTypeArr);
//        
//        params.put("status", DisclosureDictCodeList.数据状态_有效.getValue());
//        return super.queryForPage(()-> estimateReveal001Mapper.findPage(params));
        
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/EstimateReveal001/findPage",
        		params,EstimateReveal001Dto.class); 
        
        
        
	}

}
