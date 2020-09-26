/**  
 * @Project     tams-svreport-chb3  
 * @File        ProShareCheckMService.java  
 * @Package     com.joyintech.tams.report.queryanalysis.prosharecheck.service  
 * @Version     V1.0  
 * @Date        2019年12月30日 下午1:53:43  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.tams.report.queryanalysis.prosharecheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.queryanalysis.prosharecheck.dto.ProShareCheck001Dto;

/**  
 * @Description TODO  
 * @ClassName   ProShareCheckMService  
 * @Date        2019年12月30日 下午1:53:43  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Service
public class ProShareCheckMService  extends CommonExportService<ProShareCheck001Dto>{
	
	public Page<ProShareCheck001Dto> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/ProShareCheck/searchPage",param,
	       		ProShareCheck001Dto.class);
	}

	
	public List<ProShareCheck001Dto> setDataList(Map<String, Object> param) {
		return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/ProShareCheck/setDataList",param,
        		ProShareCheck001Dto.class);
	}
}
