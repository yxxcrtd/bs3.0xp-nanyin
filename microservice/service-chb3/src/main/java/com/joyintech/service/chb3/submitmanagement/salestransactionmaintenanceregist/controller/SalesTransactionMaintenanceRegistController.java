/*
 * 文件名：EquityInfoRegist.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.chb3.submitmanagement.salestransactionmaintenanceregist.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.ChbBatchParaMapper;
import com.joyintech.tams.common.base.entity.ChbBatchPara;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.service.SalesTransactionMaintenanceRegist001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 投资者身份信息登记服务
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @since 1.0
 */
@RestController
@RequestMapping("/SalesTransactionMaintenanceRegist")
public class SalesTransactionMaintenanceRegistController {
    /**
     * 001Service
     */
    @Autowired
    private SalesTransactionMaintenanceRegist001Service salesTransactionMaintenanceRegist001Service;

    @Autowired
    private ChbBatchParaMapper chbBatchParaMapper;
    /**
     *
     * 查询功能
     * <功能详细描述>
     * @return 存储了查询结果的list
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SalesTransactionMaintenanceRegist001Dto> search(@RequestBody Map<String, Object> param){
        return salesTransactionMaintenanceRegist001Service.searchForPage(param);
    }
    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @PostMapping("doUpdate")
    public void doUpdate(@RequestBody Map<String,Object> map){
    	String upKey = (String)map.get("updateKeys");//删除的主键
    	salesTransactionMaintenanceRegist001Service.updateSumStaus(upKey);
    }

    /**
     *取消手工处理功能
     * @return 提示信息
     * @param map 条件map
     * @see
     * @since 1.0
     */
    @PostMapping("cancelMannulAll")
    public void cancelMannulAll(@RequestBody Map<String,Object> map) {
    		salesTransactionMaintenanceRegist001Service.cancelMannulAll(map);
    	
    }
    
    /**
     *取消手工处理功能
     * @return 提示信息
     * @see
     * @since 1.0
     */
    @PostMapping("cancelMannul")
    public void cancelMannul(@RequestBody String upKey) {
    		salesTransactionMaintenanceRegist001Service.cancelMannul(upKey);
    	
    }
    /**
     * checkIsNotSubmit
     * @return 提示信息
     * @see
     * @since 1.0
     */
    @PostMapping("checkIsNotSubmit")
    public List<ValidMessage> checkIsNotSubmit(@RequestBody String upKey) {
    	return salesTransactionMaintenanceRegist001Service.checkIsNotSubmit(upKey);
    }

    /**
     * 数据检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String,Object> map) {
        String keyList;
        Map<String, Object> param = map;
        if(!param.containsKey("checkKeys")){
            keyList=salesTransactionMaintenanceRegist001Service.getKeyList(param);
        }
        else{
            keyList=(String)param.get("checkKeys");
        }
        if (StringUtil.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
            return checkResult;
        }
        return salesTransactionMaintenanceRegist001Service.checkData(keyList);
    }
    
    /**
     * 将选择的数据导出成excel
     * 查询数据
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("queryExportSelectRegistList")
    public List<SalesTransactionMaintenanceRegist001Dto> queryExportSelectRegistList(@RequestBody String exportKeys){
        return salesTransactionMaintenanceRegist001Service.queryExportSelectRegistList(exportKeys);
    }

    /**
     * 将选择的数据导出成excel
     * 查询数据
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("queryExportRegistDelList")
    public List<SalesTransactionMaintenanceRegist001Dto> queryExportRegistDelList(@RequestBody Map<String,Object> paramMap){
        return salesTransactionMaintenanceRegist001Service.queryExportRegistDelList(paramMap);
    }
    
    @PostMapping("queryForInfoRegist")
    public List<SalesTransactionMaintenanceRegist001Dto> queryForInfoRegist(@RequestBody Map<String,Object> param){
    	return salesTransactionMaintenanceRegist001Service.queryForInfoRegist(param);
    }
    @PostMapping("queryExportListByKeys")
    public List<SalesTransactionMaintenanceRegist001Dto> queryExportListByKeys(@RequestBody List<SalesTransactionMaintenanceRegist001Dto> equityRegistList){
    	return salesTransactionMaintenanceRegist001Service.queryExportListByKeys(equityRegistList);
    }
    
    @PostMapping("checkIfInBatchByTaskBeanName")
    public boolean checkIfInBatchByTaskBeanName(@RequestBody String beanname) {
    	return salesTransactionMaintenanceRegist001Service.checkIfInBatchByTaskBeanName(beanname);
    }
    
    @PostMapping("insert")
    public void insert(@RequestBody ChbBatchPara record){
    	chbBatchParaMapper.insert(record);
    }
    
}
