/*
 * 文件名：MstCoinFundQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dto.ComCoinFundQuotationInfo001Dto;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.service.ComCoinFundQuotationInfoService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.service.ComCoinFundQuotationInfoImportDataService;

/**
 * 货币型基金行情信息
 * 
 * @author fanshuanghu
 * @version 1.0 2018年7月16日
 * @see ComCoinFundQuotationInfoController
 * @since 1.0
 */
@RestController("serviceComCoinFundQuotationInfoController")
@RequestMapping("/ComCoinFundQuotationInfo")
public class ComCoinFundQuotationInfoController {

    /**
     * service注入
     */
    @Autowired
    private ComCoinFundQuotationInfoService comCoinFundQuotationInfoService;
    
    /**hsgexinghua add by zhangxing1 20200228
     * service注入
     */
    @Autowired
    private ComCoinFundQuotationInfoImportDataService comCoinFundQuotationInfoImportDataService;
    /**
     * service注入
     */
    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    /**
     * 分页查询
     * 
     * @param pageParam 查询参数
     * @return page对象
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ComCoinFundQuotationInfo001Dto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<ComCoinFundQuotationInfo001Dto> pages = comCoinFundQuotationInfoService.selectForPage(page,length,pageParam);
        return pages;
    }
    /**
     * 获取页面信息（修改）
     * @param pageParam 主键
     * @return 基金类行情信息列表
     * @see
     * @since 1.0
     */
    @PostMapping("selectPageInfo")
    public List<ComCoinFundQuotationInfo001Dto> selectPageInfo(@RequestBody Map<String, Object> pageParam){
        return comCoinFundQuotationInfoService.selectPageInfo(pageParam);
    }
    /**
     * 联想文本框service层实现
     * 
     * @param param 检索参数
     * @return SuggestDto
     */
    @PostMapping("/selectForCode")
    public List<SuggestDto> selectForCode(@RequestBody Map<String, Object> param) {
        return comCoinFundQuotationInfoService.selectForCode(param);
    }
    /**
     * data-url 方式的下拉框
     * 
     * @param map 检索参数
     * @return SuggestDto
     */
    @PostMapping("/getManagement")
    public List<SuggestDto> getManagement(@RequestBody Map<String, Object> map) {
        return comCoinFundQuotationInfoService.getManagement(map);
    }
    
    /**
     * 联想下拉框基金代码
     * @param map 查询参数
     * @return List<FinprodSeleOptionDto>金融产品实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFinprod")
    public List<SuggestDto> queryFinprod(@RequestBody Map<String, Object> map) {
        return comCoinFundQuotationInfoService.queryFinprod(map);
    }
    /**
     * 以基金代码获取基金类型和基金管理人名称
     * 
     * @param map 界面参数
     * @return MstCoinFundQuotationInfo001Dto金融产品实体类
     * @see
     * @since 1.0
     */
    @PostMapping("/getManageAndType")
    public ComCoinFundQuotationInfo001Dto getManageAndType(@RequestBody Map<String,Object> map) {
        return comCoinFundQuotationInfoService.getManageAndType((String)map.get("finprodId"));
    }
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param map 业务实体
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/doComplete")
    public void doComplete(@RequestBody ComCoinFundQuotationInfo001Dto map) {
       comCoinFundQuotationInfoService.doComplete(map);
    }

    /**
     * 通过估值日与ID查询
     * @param map
     * @return
     */
    @PostMapping("/selectByIdAndValDate")
    public String selectByIdAndValDate(@RequestBody Map<String,Object>map) {
        return comCoinFundQuotationInfoService.selectByIdAndValDate(map);
    }
    
    @GetMapping("/getIplatDate")
    public String getIplatDate(){
        return commonPlatformDateService.getSysDateStr();
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
        return comCoinFundQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
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
        return this.comCoinFundQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }
    //hsgexinghua add by zhangxing1 20200228 end
}
