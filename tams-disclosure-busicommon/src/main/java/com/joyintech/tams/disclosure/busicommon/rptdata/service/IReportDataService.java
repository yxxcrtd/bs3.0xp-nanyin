/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface IReportDataService {

	public List<Map<String,Object>> queryDatas(Map<String,Object> paraMap );
}
