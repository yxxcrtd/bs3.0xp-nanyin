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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dto.ComCoinFundQuotationInfo001Dto;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.service.ComCoinFundQuotationInfoService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;

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
     * @return SelectOptionDto
     */
    @PostMapping("/selectForCode")
    public List<SelectOptionDto> selectForCode(@RequestBody Map<String, Object> param) {
        return comCoinFundQuotationInfoService.selectForCode(param);
    }
    /**
     * data-url 方式的下拉框
     * 
     * @param map 检索参数
     * @return SelectOptionDto
     */
    @PostMapping("/getManagement")
    public List<SelectOptionDto> getManagement(@RequestBody Map<String, Object> map) {
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
    public List<SelectOptionDto> queryFinprod(@RequestBody Map<String, Object> map) {
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
     * <功能详细描述>
     * @param finprodId id
     * @param valDate 估值日期
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByIdAndValDate")
    public String selectByIdAndValDate(@RequestBody Map<String,Object>map) {
        return comCoinFundQuotationInfoService.selectByIdAndValDate(map);
    }
    
    @GetMapping("/getIplatDate")
    public String getIplatDate(){
        return commonPlatformDateService.getSysDateStr();
    }
}
