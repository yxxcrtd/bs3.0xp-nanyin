/*
 * 文件名：OperationalInfo001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：
 * 修改时间：2019年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.svreport.infomanagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.ChbMgrTableInfo;
import com.joyintech.tams.common.base.entity.FilterInformationDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;


/**
 * @Description 业务数据维护业务逻辑层
 * @author gaojian
 * @version 1.7 2019年5月8日
 * @since 1.7
 */
/**
* @ClassName: OperationalInfoMService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author gaojian
* @date 2019年7月2日 
*
*/
    
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalInfoMService extends BaseService<FilterInformationDto>{
	/**
	 * 日志管理
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OperationalInfo001Service operationalInfo001Service;

	@Autowired
	private OperationalInfo004Service operationalInfo004Service;

	// 资产要素表中文名
//	private static final String CHB_ASSET_INFO_CN = "资产基本信息";
	/**
	 * 获取所有基础表信息
	 * 
	 * @return 所有基础表信息
	 */
	public List<BaseInformationDto> queryAllTableInfo() {
    	return restTemplate.getForList(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryAllTableInfo",
        		BaseInformationDto.class);
	}
	
	/**
	* @Title: queryAllBaseInformation
	* @Description: 查询所有待维护表查询条件集合
	* @param @return    参数
	* @return List<BaseInformationDto> 查询条件集合
	* @throws
	*/
	public List<BaseInformationDto> queryAllBaseInformation() {
    	return restTemplate.getForList(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryAllBaseInformation",
        		BaseInformationDto.class);
	}
    
    /**
     * 根据数据类型查询基础表信息
     * @param interfType 数据类型
     * @return 基础表信息
     */
    public List<BaseInformationDto> queryTableIdByInterfType(String interfType) {
    	return restTemplate.getForList(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryTableIdByInterfType?interfType="+interfType,
        		BaseInformationDto.class);
    }

	/**
	 * 根据用户ID获取已保存方案信息
	 * 
	 * @return 已保存方案信息
	 */
	public Page<FilterInformationDto> queryFilterInfoByUserId(Map<String, Object> param) {
		param.put("userId", CommonUtil.getUid());
		return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryFilterInfoByUserId",param,
        		FilterInformationDto.class);
	}

	/**
	 * 根据基础表代码获取字段代码和名字
	 * 
	 * @param tableId 基础表代码
	 * @return 字段代码和名字组成的Json字符串
	 */
	public String queryColumnByTableId(String tableId) {
    	return restTemplate.getForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryColumnByTableId?tableId="+tableId,
        		String.class);
	}

	/**
	 * 根据基础表代码获取字段代码和类型
	 * 
	 * @param tableId 基础表代码
	 * @return 字段代码和类型组成的Json字符串
	 */
	public String queryColumnTypeByTableId(String tableId) {
    	return restTemplate.getForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryColumnTypeByTableId?tableId="+tableId,
        		String.class);
	}

	/**
	 * 根据前台传入的条件动态查询结果
	 * 
	 * @param map 前台条件组成的map
	 * @return 动态结果
	 */
	@SuppressWarnings("unchecked")
	public String queryData(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryData",param,
        		String.class);
	}

	/**
	 * 查询多选框参数
	 * 
	 * @param param 表名和字段名
	 * @return 多选框参数
	 */
	public String queryColumnInfoList(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryColumnInfoList",param,
        		String.class);
	}

	/**
	 * 获取字段信息
	 * 
	 * @param param 页面传入信息
	 * @return 字段信息
	 */
	public String queryColmnInfo(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryColmnInfo",param,
        		String.class);
	}

	/**
	 * 查询方案名是否重复
	 * 
	 * @param param 页面数据
	 * @return 方案名是否重复
	 */
	public boolean checkSave(Map<String, Object> param) {
		param.put("userId", CommonUtil.getUid());
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/checkSave",param,
        		Boolean.class);
	}

	/**
	 * 保存方案
	 * 
	 * @param param 页面参数
	 * @return 是否保存成功
	 */
	public boolean savePlan(Map<String, Object> param) {
		param.put("userId", CommonUtil.getUid());
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/savePlan",param,
        		Boolean.class);
	}
	
	/**
	 * 修改方案名称
	 * 
	 * @param param 页面参数
	 * @return 是否修改成功
	 */
	public boolean updatePlanName(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/updatePlanName",param,
        		Boolean.class);
	}

	/**
	 * 根据用户ID获取已保存方案
	 * 
	 * @param param 页面参数
	 * @return 已保存方案
	 */
	public String queryPlanByfilterId(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryPlanByfilterId",param,
        		String.class);
	}
    
    /**
     * 获取具体表所有日期类型的字段
     * @param param 页面参数
     * @return 所有日期类型字段
     */
    public String queryDateColumnList(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryDateColumnList",param,
        		String.class);
    }
    
    
    /**
     * 获取具体表所有时间类型的字段
     * @param param 页面参数
     * @return 所有时间类型字段
     */
    public String queryTimeColumnList(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryTimeColumnList",param,
        		String.class);
    }
    
    /**
	 * 检查传入的参数是不是一张表的，作为错误信息表导出前检查
	 * @return 是否是同一张表
	 */
    public boolean checkIsSingleTable(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/checkIsSingleTable",param,
        		Boolean.class);
    }

    
    /**
     * 根据页面传来的条件删除数据
     * @return 是否删除成功
     */
    @SuppressWarnings("unchecked")
	public int deleteData(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/deleteData",param,
        		Integer.class);
    }

    /**
     *  数据删除成功之后触发报表连接器
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
	public int linkSvrReportAfterDelete(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/linkSvrReportAfterDelete",param,
        		Integer.class);
    }
	 /**
     * 导入文件之前检查
     *
     * @param fileName 导入的文件绝对路径
     * @return 检查结果
     * @see
     * @since 1.0
     */
	 public List<ExcelValidDto> checkConfirmMsgForImportData(String fileName) {
		 if (!fileName.toLowerCase().endsWith("xlsx")) {
			 List<ExcelValidDto> checkResult = new ArrayList<>();
			 checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "请导入2007以上版本的excel文件！"));
			 return checkResult;
		 }
		 Map<String,Object> param = new HashMap<>();
		 param.put("fileName", fileName);
		 List<List<String>> dataList = null;
		 File file = new File(fileName);
		 XSSFWorkbook xssfWorkBook = null;
		 // 获取导入表名
		 String tableName = null;
		 try {
			 xssfWorkBook = new XSSFWorkbook(new FileInputStream(file));
			 XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(0);
			 tableName = xssfSheet.getSheetName();
		 } catch (FileNotFoundException e1) {
			 logger.error(e1.getMessage(),e1);
		 } catch (IOException e1) {
			 logger.error(e1.getMessage(),e1);
		 }

		 try {
		     Integer headerNum = operationalInfo001Service.selectHeaderNumByChiName(tableName);
//			 if (headerNum == 2) {
				 dataList = ExcelUtil.readToList(
						 //FileNameUtil.getUploadFilePath(fileName), null, null, 0, 1, 1);
				 fileName, null, null, 0, headerNum - 1, headerNum - 1);
//			 } else {
//				 dataList = ExcelUtil.readToList(
//						 //FileNameUtil.getUploadFilePath(fileName), null, null, 0, 0, 0);
//						 fileName, null, null, 0, 0, 0);
//			 }

		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
		 param.put("tableName", tableName);
		 param.put("dataList", dataList);
		 return restTemplate.postForList(
				 SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/checkConfirmMsgForImportData",
				 param,
				 ExcelValidDto.class);
	 }
    
    /**
     * 数据导入
     * @param fileName 文件名
     * @return 导入成功条数
     */
	public AjaxResult importData(String fileName) {
//        String CHB_ASSET_INFO_CN = operationalInfo001Service.getAssetInfoCn();
		Map<String,Object> param = new HashMap<>();
		param.put("fileName", fileName);
		List<List<String>> dataList = null;
		try {
			dataList = ExcelUtil.readToList(
					FileNameUtil.getUploadFilePath(fileName), null, null, 0, 0, 0);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		File file = new File(fileName);
		XSSFWorkbook xssfWorkBook = null;
		// 获取导入表名
		String tableName = null;
		try {
			xssfWorkBook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(0);
			tableName = xssfSheet.getSheetName();
		} catch (FileNotFoundException e1) {
			logger.error(e1.getMessage(),e1);
		} catch (IOException e1) {
			logger.error(e1.getMessage(),e1);
		}
		try {
		    int headerNum = operationalInfo001Service.selectHeaderNumByChiName(tableName);
//			if (CHB_ASSET_INFO_CN.equals(tableName)) {
			dataList = ExcelUtil.readToList(
//					FileNameUtil.getUploadFilePath(fileName)
			        fileName
					, null, null, 0, headerNum - 1, headerNum - 1);
//			} else {
//				dataList = ExcelUtil.readToList(
//						FileNameUtil.getUploadFilePath(fileName), null, null, 0, 0, 0);
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		param.put("userName", CommonUtil.getUserName());
		param.put("tableName", tableName);
		param.put("dataList", dataList);

		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/importData",
				param,
				SvrAjaxResult.class);
	}
    
    /**
     * 连接报表生成器
     * @param fileName
     */
    public int linkSvrReport(String fileName) {
    	Map<String,Object> param = new HashMap<>();
    	param.put("fileName", fileName);
    	List<List<String>> dataList = null;
        try {
            dataList = ExcelUtil.readToList(
                FileNameUtil.getUploadFilePath(fileName), null, null, 0, 0, 0);
        } catch (FileNotFoundException e) {
        	logger.error(e.getMessage(),e);
        	e.printStackTrace();
        }
        File file = new File(fileName);
		// 获取导入表名
		String tableId = null;
		try(XSSFWorkbook xssfWorkBook = new XSSFWorkbook(new FileInputStream(file))) {
			XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(0);
			tableId = xssfSheet.getSheetName();
		} catch (FileNotFoundException e1) {
			logger.error(e1.getMessage(),e1);
		} catch (IOException e1) {
			logger.error(e1.getMessage(),e1);
		}
		param.put("tableId", tableId);
    	param.put("dataList", dataList);
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/linkSvrReport",
        		param,
        		Integer.class);
    }
    /**
     * 删除指定方案
     * @param param
     * @return  删除方案的名称
     */
    public String deleteFilter(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/deleteFilter",
        		param,
        		String.class);
    }
    
    /**
     * 根据页面条件获取对应字段类型
     * @param param 页面参数
     * @return 字段类型
     */
    public String queryColumnTypeByCondition(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryColumnTypeByCondition",
        		param,
        		String.class);
    }
    
    /**
     * 根据页面条件获取对应字段名称
     * @param param 页面参数
     * @return 字段名称
     */
    public String queryColumnNameByCondition(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryColumnNameByCondition",
        		param,
        		String.class);
    }

	public ResponseEntity<byte[]> export(Map<String, Object> param) {
		return operationalInfo001Service.export(param);
	}
	
	
	/**
	* @Title: searchPage
	* @Description: 查询待维护表基本信息
	* @param @param selectParam
	* @param @return    无
	* @return Page<ChbMgrTableInfo> 待维护表基本信息列表
	* @throws
	*/
	public Page<ChbMgrTableInfo> searchPage(Map<String, Object> selectParam) {
		return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/searchPage",
				selectParam, ChbMgrTableInfo.class);
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
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/checkData",
        		selectParam,
        		SvrAjaxResult.class);
	}
	
	/**
	* @Title: saveData
	* @Description: 存入待维护表信息
	* @param @param selectParam    参数
	* @return void    返回类型
	* @throws
	*/
	    
	public void saveData(Map<String, Object> selectParam) {
		restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/saveData",
        		selectParam,
        		void.class);
	}
	
	/**
	* @Title: updateTableStatus
	* @Description: 修改待维护表状态未有效
	* @param @return    参数
	* @return AjaxResult    返回类型
	* @throws
	*/
	public Integer updateTableStatus(Map<String, Object> selectParam) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/updateTableStatus",
        		selectParam,
        		Integer.class);
	}
	
	/**
	* @Title: queryConditionPage
	* @Description: 根据表名查询已配置查询条件列表
	* @param @param selectParam
	* @param @return    参数
	* @return Page<BaseInformationDto>    查询条件列表
	* @throws
	*/
	public Page<BaseInformationDto> queryConditionPage(Map<String, Object> selectParam) {
		return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryConditionPage",
				selectParam, BaseInformationDto.class);
	}
	
	
	/**
	* @Title: queryDataResultPage
	* @Description: 根据表名查询已配置查询结果列表
	* @param @param selectParam
	* @param @return    参数
	* @return Page<ChbInfoMgrResultConfig>    查询结果列表
	* @throws
	*/
	public Page<ChbInfoMgrResultConfig> queryDataResultPage(Map<String, Object> selectParam) {
		return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/queryDataResultPage",
				selectParam, ChbInfoMgrResultConfig.class);
	}

	/**
	 * @Title: queryDataResultPage
	 * @Description: 根据表名查询已配置查询结果列表
	 * @param @param selectParam
	 * @param @return    参数
	 * @return Page<ChbInfoMgrResultConfig>    查询结果列表
	 * @throws
	 */
	public List<ChbInfoMgrResultConfig> queryDataResultList(Map<String, Object> selectParam) {
		return operationalInfo004Service.queryDataResultList(selectParam);
	}
	
	/**
	* @Title: check
	* @Description: (数据检查)
	* @param @param selectParam
	* @param @return    参数
	* @return AjaxResult    返回类型
	* @throws
	*/
	public List<ExcelValidDto> check(Map<String, Object> selectParam) {
		return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/check",
				selectParam, ExcelValidDto.class);
	}
	
	/**
	* @Title: savePlanData
	* @Description: (保存查询条件和结果方案)
	* @param @param selectParam
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	public String savePlanData(Map<String, Object> selectParam) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalInfo/savePlanData",
        		selectParam,
        		String.class);
	}
}
