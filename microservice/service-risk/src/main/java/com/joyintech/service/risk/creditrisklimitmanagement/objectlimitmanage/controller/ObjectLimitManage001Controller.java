/*
 * 文件名：CeOrg001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.service.ObjectLimitManage001Service;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;


/**
 * 项目额度管理
 * @author chenlei1
 * @version 1.0 2020年03月05日
 * @see ObjectLimitManage001Controller
 * @since 1.0
 */
@RestController("ObjectLimitManage001Ctl_dtx")
@RequestMapping("/ObjectLimitManage001")
public class ObjectLimitManage001Controller {

	/**
	 * objectLimitManage001Service
	 */
	@Autowired
	private ObjectLimitManage001Service objectLimitManage001Service;
	
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("getCustomerId/{page}/{length}")
	public Page<SelectOptionDto> getCustomerId(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
		return objectLimitManage001Service.getCustomerId(map, page, length);
	}
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ObjectLimitManageInfoDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return objectLimitManage001Service.selectForPage(page, length, param);
    }

/*	*//**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     *//*
	@Transactional
	@DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody RskSupervisionIndicator002Dto dto) throws Exception {
        return objectLimitManage001Service.delete(dto);
    }*/
    

    @PostMapping("/getApporId")
    public List<SuggestDto> getApporId(@RequestBody Map<String, Object> param) {
        return objectLimitManage001Service.getApporId(param);
    }
    
    @PostMapping("/getCustomerName")
    public List<SuggestDto> getCustomerName(@RequestBody Map<String, Object> param) {
        return objectLimitManage001Service.getCustomerName(param);
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
	@PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
    	List<ExcelValidDto> checkResult = new ArrayList<>();
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String seqId = param.get("seqId")==null?null:param.get("seqId").toString();
		String impType = param.get("impType")==null?null:param.get("impType").toString();
		if("05".equals(impType)){
		   return this.objectLimitManage001Service.importObjectData(dataList,tempList,seqId,impType);
		}
		checkResult.add(new ExcelValidDto("导入类型", String.valueOf(1),"导入类型不存在"));
	    return checkResult;
		
    }
}
