package com.joyintech.service.svrcom.infomanagement.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.ChbMgrTableInfo;
import com.joyintech.tams.common.base.entity.FilterInformationDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfo001Service;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfo002Service;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfo003Service;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfo004Service;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see OperationalInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/OperationalInfo")
public class OperationalInfoController {

	/**
	 * 资产要素service
	 */
    @Autowired
    private OperationalInfo001Service operationalInfo001Service;
    
    @Autowired
    private OperationalInfo002Service operationalInfo002Service;
    
    @Autowired
    private OperationalInfo003Service operationalInfo003Service;
    
    @Autowired
    private OperationalInfo004Service operationalInfo004Service;
    
    @SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());

    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @GetMapping("/queryAllTableInfo")
	public List<BaseInformationDto>  queryAllTableInfo() {
		return operationalInfo001Service.queryAllTableInfo();
	}
    
    /**
	* @Title: queryAllBaseInformation
	* @Description: 查询所有待维护表查询条件集合
	* @param @return    参数
	* @return List<BaseInformationDto> 查询条件集合
	* @throws
	*/
    @GetMapping("/queryAllBaseInformation")
	public List<BaseInformationDto>  queryAllBaseInformation() {
		return operationalInfo001Service.queryAllBaseInformation();
	}

    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @GetMapping("/queryTableIdByInterfType")
	public List<BaseInformationDto>  queryTableIdByInterfType(String interfType) {
		return operationalInfo001Service.queryTableIdByInterfType(interfType);
	}

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryFilterInfoByUserId/{page}/{length}")
    public Page<FilterInformationDto> queryFilterInfoByUserId(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryFilterInfoByUserId(param);
    }

    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @GetMapping("/queryColumnByTableId")
	public String  queryColumnByTableId(String tableId) {
		return operationalInfo001Service.queryColumnByTableId(tableId);
	}

    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @GetMapping("/queryColumnTypeByTableId")
	public String  queryColumnTypeByTableId(String tableId) {
		return operationalInfo001Service.queryColumnTypeByTableId(tableId);
	}

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryData")
    public String queryData(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryData(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryColumnInfoList")
    public String queryColumnInfoList(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryColumnInfoList(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryColmnInfo")
    public String queryColmnInfo(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryColmnInfo(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/checkSave")
    public boolean checkSave(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.checkSave(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/savePlan")
    public boolean savePlan(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.savePlan(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/updatePlanName")
    public boolean updatePlanName(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.updatePlanName(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryPlanByfilterId")
    public String queryPlanByfilterId(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryPlanByfilterId(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryDateColumnList")
    public String queryDateColumnList(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryDateColumnList(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryTimeColumnList")
    public String queryTimeColumnList(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryTimeColumnList(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/checkIsSingleTable")
    public boolean checkIsSingleTable(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.checkIsSingleTable(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/deleteData")
    public int deleteData(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.deleteData(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/linkSvrReportAfterDelete")
    public int linkSvrReportAfterDelete(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.linkSvrReportAfterDelete(param);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("/checkConfirmMsgForImportData")
    public List<ExcelValidDto> checkConfirmMsgForImportData(@RequestBody Map<String, Object> param) {
        return operationalInfo001Service.checkConfirmMsgForImportData((String)param.get("tableName"),(String)param.get("fileName"),(List<List<String>>) param.get("dataList"));
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("/importData")
    public AjaxResult importData(@RequestBody Map<String, Object> param) {
        return operationalInfo001Service.importData((String)param.get("tableName"),(String)param.get("userName"),(List<List<String>>) param.get("dataList"));
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("/linkSvrReport")
    public int linkSvrReport(@RequestBody Map<String, Object> param) {
        return operationalInfo001Service.linkSvrReport((String)param.get("tableId"),(List<List<String>>) param.get("dataList"));
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("/deleteFilter")
    public String deleteFilter(@RequestBody Map<String, Object> param) {
        return operationalInfo001Service.deleteFilter(param);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("/queryColumnTypeByCondition")
    public String queryColumnTypeByCondition(@RequestBody Map<String, Object> param) {
        return operationalInfo001Service.queryColumnTypeByCondition(param);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("/queryColumnNameByCondition")
    public String queryColumnNameByCondition(@RequestBody Map<String, Object> param) {
        return operationalInfo001Service.queryColumnNameByCondition(param);
    }
    
    /**
    * @Title: searchPage
    * @Description: 分页查询待维护表基本信息
    * @param @param param 页面参数
    * @param @return 待维护表基本信息
    * @return Page<ChbMgrTableInfo> 待维护表基本信息列表
    * @throws
    */
    @PostMapping("/searchPage/{page}/{length}")
    public Page<ChbMgrTableInfo>  searchPage(@RequestBody Map<String, Object> param){
        return operationalInfo002Service.queryPage(param);
    }
    
    /**
    * @Title: checkData
    * @Description: 检查前台传入的待维护表名是否合理
    * @param @param selectParam
    * @param @return    参数
    * @return AjaxResult    返回类型
    * @throws
    */
    @PostMapping("/checkData")
    public AjaxResult checkData(@RequestBody Map<String, Object> selectParam) {
    	return operationalInfo002Service.checkData(selectParam);
    }
    
    /**
    * @Title: saveData
    * @Description: 存入待维护表信息
    * @param @param selectParam    参数
    * @return void    返回类型
    * @throws
    */
    @PostMapping("/saveData")
    public void saveData(@RequestBody Map<String, Object> selectParam) {
    	operationalInfo002Service.saveData(selectParam);
    }
    
    /**
    * @Title: updateTableStatus
    * @Description: 修改指定表状态为有效
    * @param @param selectParam
    * @param @return    参数
    * @return Integer    返回类型
    * @throws
    */
    @PostMapping("/updateTableStatus")
    public Integer updateTableStatus(@RequestBody Map<String, Object> selectParam) {
    	return operationalInfo002Service.updateTableStatus(selectParam);
    }
    
    /**
	* @Title: queryConditionPage
	* @Description: 根据表名查询已配置查询条件列表
	* @param @param selectParam
	* @param @return    参数
	* @return Page<BaseInformationDto>    查询条件列表
	* @throws
	*/
    @PostMapping("/queryConditionPage/{page}/{length}")
    public Page<BaseInformationDto> queryConditionPage(@RequestBody Map<String, Object> selectParam) {
    	return operationalInfo003Service.queryConditionPage(selectParam);
    }
    
    /**
	* @Title: queryDataResultPage
	* @Description: 根据表名查询已配置查询结果列表
	* @param @param selectParam
	* @param @return    参数
	* @return Page<ChbInfoMgrResultConfig>    查询结果列表
	* @throws
	*/
    @PostMapping("/queryDataResultPage/{page}/{length}")
    public Page<ChbInfoMgrResultConfig> queryDataResultPage(@RequestBody Map<String, Object> selectParam) {
    	return operationalInfo004Service.queryDataResultPage(selectParam);
    }
    
    /**
    * @Title: check
    * @Description: (数据检查)
    * @param @param selectParam
    * @param @return    参数
    * @return AjaxResult    返回类型
    * @throws
    */
    @PostMapping("/check")
    public List<ExcelValidDto> check(@RequestBody Map<String, Object> selectParam) {
    	return operationalInfo004Service.check(selectParam);
    }
    
    /**
    * @Title: savePlanData
    * @Description: (保存查询条件和结果方案)
    * @param @param selectParam
    * @param @return    参数
    * @return String    返回类型
    * @throws
    */
    @PostMapping("/savePlanData")
    public String savePlanData(@RequestBody Map<String, Object> selectParam) {
    	return operationalInfo004Service.savePlanData(selectParam);
    }
}
