package com.jixu.adapter.service.export;

import java.util.HashMap;
import java.util.List;

/** 
 * @ClassName 		ExportDataService
 * @author  		Pan Wei 
 * @Date 			2017年4月25日 下午10:54:30 	
 * @Description		[]  
 */

public interface ExportDataService {
	public String getSourceName();
	
	public List<HashMap<String, Object>> genExportData(String[] params);
	
	//返回该导出数据源提供了哪些字段
	public Class<?> getReturnClassModel();
}
