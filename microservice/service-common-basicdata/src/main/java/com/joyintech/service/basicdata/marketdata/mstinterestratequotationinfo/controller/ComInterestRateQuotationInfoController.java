/*
 * 文件名：MstInterestRateQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.basicdata.marketdata.mstinterestratequotationinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstinterestratequotationinfo.dto.ComInterestRateQuotationInfoDto;
import com.joyintech.service.basicdata.marketdata.mstinterestratequotationinfo.service.ComInterestRateQuotationInfoService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.marketdata.mstinterestratequotationinfo.service.ComInterestRateQuotationInfoImportDataService;

/**
 * 利率行情信息查询
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月17日
 * @see ComInterestRateQuotationInfoController
 * @since 1.0
 */
@RestController("serviceComInterestRateQuotationInfoController")
@RequestMapping("/ComInterestRateQuotationInfo")
public class ComInterestRateQuotationInfoController {

    /**
     * 注入service类
     */
    @Autowired
    private ComInterestRateQuotationInfoService comInterestRateQuotationInfoService;
    
    /**hsgexinghua add by zhangxing1 20200228
     * service注入
     */
    @Autowired
    private ComInterestRateQuotationInfoImportDataService comInterestRateQuotationInfoImportDataService;
    
    /**
     * 分页查询
     * 
     * @param pageParam 传入的查询条件
     * @return Page<MstInterestRateQuotationInfoDto> page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ComInterestRateQuotationInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return comInterestRateQuotationInfoService.selectForPage(page,length,pageParam);
    }
    
    /**
     * 根据主键查询
     * @param map 查询条件
     * @return MstInterestRateQuotationInfoDto 实体类
     */
    @PostMapping("selectByPrimaryKey")
    public ComInterestRateQuotationInfoDto selectByPrimaryKey(@RequestBody Map<String, Object> map) {
        return comInterestRateQuotationInfoService.selectByPrimaryKey(map);
    }
    
    /**
     * 
     * 工作流完成
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody ComInterestRateQuotationInfoDto entity){
        String type = entity.getOperateType();
        comInterestRateQuotationInfoService.doComplete(entity,type);
    }
    
  //hsgexinghua add by zhangxing1 20200228 start

    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return comInterestRateQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
    }


    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.comInterestRateQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }
    //hsgexinghua add by zhangxing1 20200228 end
}
