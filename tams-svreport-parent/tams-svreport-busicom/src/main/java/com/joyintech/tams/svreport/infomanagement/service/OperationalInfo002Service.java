
/**  
* @Title: OperationalInfo002Service.java
* @Package com.joyintech.tams.svreport.infomanagement.service
* @Description: 待维护表基本信息业务逻辑层
* @author gaojian
* @date 2019年7月2日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
    
package com.joyintech.tams.svreport.infomanagement.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.ChbMgrTableInfo;
import com.joyintech.tams.report.common.dao.BaseInformationMapper;
import com.joyintech.tams.report.common.dao.ChbMgrTableInfoMapper;

/**
* @ClassName: OperationalInfo002Service
* @Description: 待维护表基本信息业务逻辑层
* @author gaojian
* @date 2019年7月2日 
*
*/

@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalInfo002Service extends CommonExportService<ChbMgrTableInfo>{
	
	/**
	* @Fields field:field:{待维护表数据库操作对象}
	*/
	    
	@Autowired
	private ChbMgrTableInfoMapper chbMgrTableInfoMapper;
	
	
	
	/**
	* @Title: queryPage
	* @Description: 分页查询待维护表基本信息
	* @param @param selectParam
	* @param @return  页面参数
	* @return Page<ChbMgrTableInfo> 待维护表基本信息列表
	* @throws
	*/
	    
	public Page<ChbMgrTableInfo> queryPage(Map<String,Object> selectParam) { 
		dealParam(selectParam);
		int pagenum = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_LENGTH).toString());
	    Page<ChbMgrTableInfo> page =
	            super.queryForPage(
	            		pagenum,legth,()->chbMgrTableInfoMapper.queryAllchbMgrTableInfo(selectParam));
	    dealResult(page);
	    return page;
	}
	
	
	
	
	
	/**
	* @Title: dealParam
	* @Description: 参数处理
	* @param @param selectParam    参数
	* @return void    返回类型
	* @throws
	*/
	    
	private void dealParam(Map<String, Object> selectParam) {
		String statusList = (String) selectParam.get("dataStatus");
		if (StringUtil.isNotEmpty(statusList)) {
			selectParam.put("statusList", StringUtil.splitToList(",",statusList));
		}
	}
	
	 /**
	* @Title: dealResult
	* @Description: 对查询结果进行处理
	* @param @param page 查询结果
	* @return void 无
	* @throws
	*/
	    
	private void dealResult(Page<ChbMgrTableInfo> page) {
		 List<ChbMgrTableInfo> infoList = page.getRoot();
		 for (ChbMgrTableInfo info : infoList) {
			 if (StringUtil.equals("E", info.getStatus())) {
				 info.setStatus("有效");
			 } else {
				 info.setStatus("删除");
			 }
		 }
	 }
	
	/**
	* @Title: checkData
	* @Description: 检查前台传入的待维护表名是否合理
	* @param @param selectParam
	* @param @return    参数
	* @return AjaxResult    返回类型
	* @throws
	*/
	public AjaxResult checkData(Map<String, Object> selectParam) {
		String mgrTableName = (String) selectParam.get("mgrTableName");
		String mgrTableChiName=(String)selectParam.get("mgrTableChiName");
		int tableCount = chbMgrTableInfoMapper.checkTableExist(mgrTableName);
		int doubleCount = chbMgrTableInfoMapper.checkDoubleInfo(mgrTableChiName);
		AjaxResult ajaxResult = new AjaxResult();
		if (tableCount == 0) {
			ajaxResult.setData("noTable");
		}
		if (doubleCount != 0) {
			ajaxResult.setData("doubleTable");
		}
		return ajaxResult;
	}
	
	/**
	* @Title: saveData
	* @Description: 存入一条待维护表信息
	* @param @param selectParam    参数
	* @return void    返回类型
	* @throws
	*/
	    
	public void saveData(Map<String, Object> selectParam) {
		String mgrtableID = UUID.randomUUID().toString();
		selectParam.put("mgrtableID", mgrtableID);
		chbMgrTableInfoMapper.insertData(selectParam);
	}
	
	
	/**
	* @Title: updateTableStatus
	* @Description: 修改指定表状态为有效
	* @param @param selectParam
	* @param @return    参数
	* @return Integer    返回类型
	* @throws
	*/
	    
	public Integer updateTableStatus(Map<String, Object> selectParam) {
		return chbMgrTableInfoMapper.updateTableStatus(selectParam);
	}
}
