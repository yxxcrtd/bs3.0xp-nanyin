package com.joyintech.service.chb3.submitmaintenance.equityinfo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo001Service;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo002Service;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper;

/**
 * 投资者身份信息维护服务
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @since 1.0
 */
@RestController
@RequestMapping("/EquityInfo")
public class EquityInfoController {

    /**
     * dao
     */
    @Autowired
    private EquityInfo001Service equityInfo001Service;
    
    @Autowired
    private EquityInfo002Service equityInfo002Service;
    
    /**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
  
    /**
     * 查询列表
     *
     * @return Page
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EquityInfo001Dto> search(@RequestBody Map<String, Object> param) {
        return equityInfo001Service.searchForPage(param);
    }



    /**
     * 将选择的数据导出成excel
     * 查询
     * @return 返回需要的格式
     * @see
     * @since 1.0
     */
    @PostMapping(value = "queryExportSelectList")
    public List<EquityInfo001Dto> queryExportSelectList(@RequestBody String exportKeys) {
        return this.equityInfo001Service.queryExportSelectList(exportKeys);
    }

    /**
     * checkDeleteData <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("checkDeleteData")
    public List<ValidMessage> checkDeleteData(@RequestBody List<EquityInfo001Dto> list){
        List<ValidMessage> errorList = equityInfo001Service.checkDeleteData(list);
        return errorList;
    }

    /**
     * 删除 <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("doDelete")
    public void doDelete(@RequestBody List<EquityInfo001Dto> list) {
        equityInfo001Service.doDelete(list);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param fileName 文件名
     * @return 校验结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	@PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody  Map<String, Object> param) {
    	List<List<String>> dataList = (List<List<String>>) param.get("dataList");
    	List<List<String>> tempList = (List<List<String>>) param.get("tempList");
    	String bankCode =  null!=param.get("bankCode")?param.get("bankCode").toString():"";
    	String deptCode =  null!=param.get("deptCode")?param.get("deptCode").toString():"";
    	String orgCode =  null!=param.get("orgCode")?param.get("orgCode").toString():"";
        return this.equityInfo001Service.importData(dataList,tempList,bankCode,deptCode,orgCode);
    }

    /**
     * 导入代销数据
     *
     * @param fileName 文件名
     * @return 校验结果
     * @see
     * @since 1.0
     */
    @PostMapping("importChbData")
    @SuppressWarnings("unchecked")
    public List<ExcelValidDto> importChbData(@RequestBody Map<String, Object> param){
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
    	List<List<String>> dataTypeList = (List<List<String>>) param.get("dataTypeList");
    	String bankCode =  null!=param.get("bankCode")?param.get("bankCode").toString():"";
    	String deptCode =  null!=param.get("deptCode")?param.get("deptCode").toString():"";
    	String orgCode =  null!=param.get("orgCode")?param.get("orgCode").toString():"";
        return this.equityInfo001Service.importChbData(dataList,tempList,dataTypeList,bankCode,deptCode,orgCode);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody List<List<String>> dataList) {
        return equityInfo001Service.checkConfirmMsgForImportData(dataList);
    }

    /**
     * 导入代销数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
	@PostMapping("checkConfirmMsgForChbImportData")
    public AjaxResult checkConfirmMsgForChbImportData(@RequestBody Map<String, Object> param) {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        return equityInfo001Service.checkConfirmMsgForChbImportData(dataList,tempList);
    }

    /**
     * 修改的check <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping( "checkEdit")
    public AjaxResult checkEdit(@RequestBody EquityInfo001Dto dto) {
        // 反馈状态是发送成功不能修改
        AjaxResult wb = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<String> subListUp = new ArrayList<>();
        subListUp.add(dto.getIndentifyMark());

        try {
            EquityInfoRegist001Mapper equityInfoRegist001Mapper = SpringContextUtil.getBean(
                EquityInfoRegist001Mapper.class);
            List<String> submitFeedbackStatusList = equityInfoRegist001Mapper.getSubmitFeedbackStatusList(
                subListUp);
            if (CollectionUtils.isNotEmpty(submitFeedbackStatusList)) {
                for (int i = 0; i < submitFeedbackStatusList.size(); i++ ) {
                    if ("01".equals(submitFeedbackStatusList.get(i))
                        || "02".equals(submitFeedbackStatusList.get(i))
                        || "05".equals(submitFeedbackStatusList.get(i))
                        || StringUtil.isBlank(submitFeedbackStatusList.get(i))) {
                        errorList.add(
                            new ValidMessage("该数据存在中债报送流程中的数据，不允许修改，请稍后再试！"));
                    }
                }
            }
        }
        catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }

        if (CollectionUtils.isNotEmpty(errorList)) {
            wb.setErrorList(errorList);
        }
        return wb;
    }
    
    /**
     * 获取entity实例
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping( "getEntity")
    public EquityInfo001Dto getEntity(@RequestBody Map<String, Object> map) {
        return equityInfo002Service.getEntity(map);
    }
    
    /**
     * 判断该数据是否已经报送完成
     * <功能详细描述>
     * @param dto 待校验数据
     * @return 标记
     * @see
     * @since 1.0
     */
    @PostMapping( "confirmSubmit")
    public String confirmSubmit(@RequestBody EquityInfo001Dto dto) {
    	return equityInfo002Service.confirmSubmit(dto);
    }
    
    /**
     * 
     * check
     * <功能详细描述>
     * @param dto 待校验数据
     * @return jiaoyanjieguo
     * @see
     * @since 1.0
     */
   @PostMapping("doCheck")
   public AjaxResult doCheck(@RequestBody EquityInfo001Dto dto){
    	return equityInfo002Service.checkOrSave(dto,false);
   }
   /**
    * 
    * check
    * <功能详细描述>
    * @param dto 待校验数据
    * @return jiaoyanjieguo
    * @see
    * @since 1.0
    */
   @PostMapping("doSave")
   public AjaxResult doSave(@RequestBody EquityInfo001Dto dto){
       return  equityInfo002Service.checkOrSave(dto,true);
   }
   
   @PostMapping("queryForEquityInfoPage")
   public List<EquityInfo001Dto> queryForEquityInfoPage(@RequestBody Map<String, Object> param){
   		return equityInfo001Service.queryForEquityInfoPage(param);
   }
   
   @PostMapping("queryExpSelectEquityInfo")
   public List<EquityInfo001Dto> queryExpSelectEquityInfo(@RequestBody List<EquityInfo001Dto> eList){
   	return equityInfo001Service.queryExpSelectEquityInfo(eList);
   }
}
