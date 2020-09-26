
/**  
* @Title: OperationalInfo003Service.java
* @Package com.joyintech.tams.svreport.infomanagement.service
* @Description: TODO(用一句话描述该文件做什么)
* @author gaojian
* @date 2019年7月3日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
    
package com.joyintech.tams.svreport.infomanagement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.report.common.dao.BaseInformationMapper;

/**
* @ClassName: OperationalInfo003Service
* @Description: TODO(这里用一句话描述这个类的作用)
* @author gaojian
* @date 2019年7月3日 
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalInfo003Service  extends CommonExportService<BaseInformationDto> {
	
	/**
	* @Fields field:field:{待维护表查询条件操作对象}
	*/
	    
	@Autowired
	private BaseInformationMapper baseInformationMapper;
	/**
	* @Title: queryConditionPage
	* @Description: 根据表名查询已配置查询条件列表
	* @param @param selectParam
	* @param @return    参数
	* @return Page<BaseInformationDto>    查询条件列表
	* @throws
	*/
	public Page<BaseInformationDto> queryConditionPage(Map<String, Object> selectParam) {
		int pagenum = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_LENGTH).toString());
	    Page<BaseInformationDto> page =
	            super.queryForPage(
	            		pagenum,legth,()->baseInformationMapper.queryConditionPage(selectParam));
		return page;
	}
}
