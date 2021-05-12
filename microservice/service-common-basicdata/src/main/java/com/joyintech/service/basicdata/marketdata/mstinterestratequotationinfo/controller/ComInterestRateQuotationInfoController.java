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
import com.joyintech.tams.constant.DictCodeListExt1;
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
    
    @PostMapping("/selectForList")
    public List<ComInterestRateQuotationInfoDto> selectForList(@RequestBody Map<String, Object> pageParam) throws Exception{
        return comInterestRateQuotationInfoService.selectForList(pageParam);
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

    /**
     *
     * 根据利率名称找到利率名称Id
     */
    @PostMapping("/getIntRateId")
    public String getIntRateId(@RequestBody String intRateName){
        return comInterestRateQuotationInfoService.getIntRateId(intRateName);
    }

    // add by zwq 20210222 增加基准利率行情导入功能 start
    /**
     *
     * 插入数据
     * @param equityList 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/mergeInfoList")
    public void mergeInfoList(@RequestBody List<ComInterestRateQuotationInfoDto> equityList){
        for (ComInterestRateQuotationInfoDto dto:equityList){
            dto.setIntRateType(DictCodeListExt1.基准类型_2_利率.getValue());
            dto.setInputType(DictCodeListExt1.录入方式_系统导入.getValue());
        }
        comInterestRateQuotationInfoService.mergeInfoList(equityList);
    }
    // add by zwq 20210222 增加基准利率行情导入功能 end

}
