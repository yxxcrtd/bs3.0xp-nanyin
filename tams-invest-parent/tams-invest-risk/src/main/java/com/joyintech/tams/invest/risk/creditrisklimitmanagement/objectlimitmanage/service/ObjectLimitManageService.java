package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtilForRisk;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see ObjectLimitManageService
 * @since 1.0
 */
@Service
public class ObjectLimitManageService extends CommonExportService<ObjectLimitManageInfoDto>
		implements BaseProcessService<ObjectLimitManageInfoDto> {

   
    @Autowired(required = false)
    private ObjectLimitManageService objectLimitManageService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(ObjectLimitManageInfoDto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	           
	        }
	        else {
	            action = "update";
	        }
	        restTemplate.postForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage002/" + action, entity,
	            Integer.class);

	}
	
	 /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
	public Page<SelectOptionDto> getLimitLabel(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage001/getLimitLabel",
            map, SelectOptionDto.class);
    }
    
	/**
	 * 画面查询 <功能详细描述>
	 * 
	 * @param parameters
	 *            parameters
	 * @return RskSupervisionIndicatorDto
	 * @see
	 * @since 1.0
	 */
	public Page<ObjectLimitManageInfoDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage001/selectForPage",
				parameters, ObjectLimitManageInfoDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditrisklimitmanagement/objectlimitmanage/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	 /**
     * 设置预警与违规格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
    	 if(("vdate".equals(propName)) && value != null){
             cell.setCellValue(DateUtil.format((Date)value));
         }
    	 if(("mdate".equals(propName)) && value != null){
             cell.setCellValue(DateUtil.format((Date)value));
         }
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<ObjectLimitManageInfoDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<ObjectLimitManageInfoDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
     * 根据授权额度编号查询额度信息
     * @param seqno 额度编号
     * @return 额度信息
     */
   public ObjectLimitManageInfoDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage002/queryCeLineBySeqno",
            map, ObjectLimitManageInfoDto.class);
    }
	
   /**
    * 获取额度标签
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */
	public Page<SelectOptionDto> getCreditTagInfoList(Map<String, Object> parameters) {
		
		Page<SelectOptionDto> tempList = restTemplate.postForPage(
               ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage002/getCreditTagInfoList",
               parameters, SelectOptionDto.class);
       return tempList;
   }
	
   public boolean checkTag(ObjectLimitManageInfoDto dto){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagName", dto.getCreditTagName());
		map.put("tagId", dto.getCreditTagId());
		return restTemplate.postForObject(
				ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage002/checkTag",map,
				Boolean.class);	
	}
   
   /**
    * 客户代码联想文本框service层实现
     *
     * @param map 检索参数
     * @return SuggestDto
     */
	public Page<SelectOptionDto> getCustomerId(Map<String, Object> map) {
       return restTemplate.postForPage(
           ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage001/getCustomerId",
           map, SelectOptionDto.class);
   }
	
	

    public List<SuggestDto> getApporId(String apporId){
        Map<String, Object> map = new HashMap<>();
        map.put("apporId", apporId);
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME+"/ObjectLimitManage001/getApporId",
            map, SuggestDto.class);
    }
    public List<SuggestDto> getCustomerName(String customerName){
        Map<String, Object> map = new HashMap<>();
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        map.put("customerName", customerName);
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME+"/ObjectLimitManage001/getCustomerName",
            map, SuggestDto.class);
    }
    
    
    public List<ExcelValidDto> importData(String fileName,String impType) {
     	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtilForRisk.getDataList(fileName));
    	param.put("tempList", ExcelUtilForRisk.getTempList(fileName));
    	param.put("userId", CommonUtil.getUid());
    	param.put("impType", impType);
		BussinessCodeParameter idParam = new BussinessCodeParameter();
	//	idParam.setCommonBusinessType(DictCodeList.风险管理_质押式回购导入白名单);
    //	String seqId = baseGetBusinessId.buildBussinessCode(idParam);
    // 	param.put("seqId", seqId);
    	return restTemplate.postForList(
    			ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitManage001/importData", 
	       		param,
	       		ExcelValidDto.class);
    }
}
