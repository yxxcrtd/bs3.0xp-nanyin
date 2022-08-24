package com.joyintech.service.disclosure.information.manage.estimatereveal.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.manage.estimatereveal.dao.EstimateReveal001Mapper;
import com.joyintech.service.disclosure.information.manage.estimatereveal.dto.EstimateReveal001Dto;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;

@Service(value="serviceEstimateReveal001Service")
public class EstimateReveal001Service extends CommonExportService<EstimateReveal001Dto> {

	@Autowired
	private EstimateReveal001Mapper estimateReveal001Mapper;
	
	/**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
	public Page<EstimateReveal001Dto> findPage(int page, int length, Map<String, Object> params) {

		String prodId = (String)params.get("prodId");
        String[] prodIdArr = null;
        if(StringUtil.isNotEmpty(prodId)){
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);
        
        String jobId = (String)params.get("jobId");
        String[] jobIdArr = null;
        if(StringUtil.isNotEmpty(jobId)){
            jobIdArr = jobId.split(",");
        }
        params.put("jobIdArr", jobIdArr);
        
        String repType = (String)params.get("repType");
        String[] repTypeArr = null;
        if(StringUtil.isNotEmpty(repType)){
        	repTypeArr = repType.split(",");
        }
        params.put("repTypeArr", repTypeArr);
        
        params.put("status", DisclosureDictCodeList.数据状态_有效.getValue());

        if(StringUtil.isNotEmpty(params.get("exportFlag"))){
            List<EstimateReveal001Dto> list=estimateReveal001Mapper.findPage(params);
            Page<EstimateReveal001Dto>result=new Page<EstimateReveal001Dto>();
            if(CollectionUtils.isNotEmpty(list)){
                result.setRoot(list);
            }
            return result;
        }else{
            return super.queryForPageForSelectReturn(page, length, ()-> estimateReveal001Mapper.findPage(params));
        }
	}

}
