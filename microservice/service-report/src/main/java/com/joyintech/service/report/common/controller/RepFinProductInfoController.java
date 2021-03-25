/*
 * 文件名：RepFinProductInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.common.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.common.dto.BussRepFinProductInfoDto;
import com.joyintech.service.report.common.service.RepFinProductInfoService;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 产品中债报送业务组件微服务入口
 * 
 * @author zhangliang
 * @version 1.0 2018年10月18日
 * @see RepFinProductInfoController
 * @since 1.0
 */
@RestController("RepFinProductInfoCtl_dtx")
@RequestMapping("/RepFinProductInfo")
public class RepFinProductInfoController {
    
    /**
     * {@linkplain}
     */
    @Autowired(required=false)
    private RepFinProductInfoService repFinProductInfoService;
    
    /**
     * 根据金融产品代码查询中债报送信息
     * 
     * @param finprodId 金融产品代码
     * @return 中债报送信息
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @GetMapping("/select")
    public BussRepFinProductInfoDto select(String finprodId) throws Exception{
        return repFinProductInfoService.select(finprodId);
    }

    /**
     * 根据金融产品代码删除中债报送信息
     * 
     * @param finprodId 金融产品代码
     * @return 是否成功
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/delete")
    public void delete(String finprodId){
        repFinProductInfoService.delete(finprodId);
    }
    
    /**
     * 插入中债报送信息
     * 
     * @param baseRepFinProductInfoDto 中债报送信息
     * @return 是否成功
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insert")
    public void insert(@RequestBody BussRepFinProductInfoDto bussRepFinProductInfoDto) throws Exception{
    	if(StringUtil.equals(bussRepFinProductInfoDto.getIsMerger(), DictCodeList.是否_否.getValue())){
    		repFinProductInfoService.insertMergerNo(bussRepFinProductInfoDto);
    	}
    	else{
            repFinProductInfoService.insert(bussRepFinProductInfoDto);
    	}
    }
    
    /**
     * 更新中债报送信息
     *
     * @param finprodId 金融产品代码
     * @param baseRepFinProductInfoDto 中债报送信息
     * @return 是否成功
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/update")
    public void update(@RequestBody BussRepFinProductInfoDto bussRepFinProductInfoDto) throws Exception{
        repFinProductInfoService.update(bussRepFinProductInfoDto);
    }
    /**
     * 根据行内标识码查询中债报送信息
     * 
     * @param repFinprodInsId 行内标识码
     * @return 中债报送信息
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @GetMapping("/selectRepFinprodId")
    public BussRepFinProductInfoDto selectRepFinprodId(String repFinprodInsId) throws Exception{
        return repFinProductInfoService.selectRepFinprodId(repFinprodInsId);
    }

    @GetMapping("/getIssuePartyId")
    public String getIssuePartyId(String investCode){
        return repFinProductInfoService.getIssuePartyId(investCode);
    }

}
