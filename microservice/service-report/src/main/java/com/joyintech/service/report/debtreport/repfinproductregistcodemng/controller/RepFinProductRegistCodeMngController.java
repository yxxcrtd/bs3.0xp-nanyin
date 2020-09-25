/*
 * 文件名：RepFinProductRegistCodeMngController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductregistcodemng.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repfinproductregistcodemng.dto.RepFinProductRegistCodeMngDto;
import com.joyintech.service.report.debtreport.repfinproductregistcodemng.service.RepFinProductRegistCodeMngService;
import com.joyintech.service.report.informationdisclosureform.repfinproductexpiration.dto.RepFinProductExpirationDto;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月19日
 * @see RepFinProductRegistCodeMngController
 * @since 1.0
 */
@RestController("RepFinProductRegistCodeMngCtl_dtx")
@RequestMapping("/RepFinProductRegistCodeMng")
public class RepFinProductRegistCodeMngController {
    
    /**
     * 自定义service
     */
    @Autowired
    private RepFinProductRegistCodeMngService repFinProductRegistCodeMngService;
    
    /**
     * 
     * 产品名称下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param){
        return repFinProductRegistCodeMngService.getFinprodMarketId(param);
    }
    
    /**
     * 
     * 产品登记编码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getPordRegistCode")
    public List<SelectOptionDto> getPordRegistCode(@RequestBody Map<String, Object> param){
        return repFinProductRegistCodeMngService.getPordRegistCode(param);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param pageParam  Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RepFinProductRegistCodeMngDto> selectAll(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductRegistCodeMngService.selectForPage(page, length, pageParam);
    }
    
    /**
     * 
     * 产品代码在理财产品排期表里面不存在
     * <功能详细描述>
     * @param finprodMarketId String
     * @return int
     * @see
     * @since 1.0
     */
    @GetMapping("/check1")
    public int check1(String finprodMarketId){
        return repFinProductRegistCodeMngService.check1(finprodMarketId);
    }
    
    /**
     * 
     * 产品登记编码在理财产品排期表里面已存在
     * <功能详细描述>
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    @GetMapping("/check2")
    public int check2(String prodRegistCode){
        return repFinProductRegistCodeMngService.check2(prodRegistCode);
    }
    
    /**
     * 
     * 产品代码在理财产品排期表的产品登记编码和该行的产品登记编码不一样 
     * <功能详细描述>
     * @param finprodMarketId String
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    @GetMapping("/check3")
    public int check3(String finprodMarketId, String prodRegistCode){
        return repFinProductRegistCodeMngService.check3(finprodMarketId, prodRegistCode);
    }
    
    /**
     * 
     * 更新理财产品排期
     * <功能详细描述>
     * @param paPrdIssueSchedule PaPrdIssueSchedule
     * @return int
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updatePaPrdIssueSchedule")
    public int updatePaPrdIssueSchedule(@RequestBody List<PaPrdIssueSchedule> paPrdIssueSchedule){
        return repFinProductRegistCodeMngService.updatePaPrdIssueSchedule(paPrdIssueSchedule);
    }
    
    /**
     * 
     * 更新产品申报报送信息
     * <功能详细描述>
     * @param repProdDeclare RepProdDeclare
     * @return int
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updateRepProdDeclare")
    public int updateRepProdDeclare(@RequestBody List<RepProdDeclare> repProdDeclare){
        return repFinProductRegistCodeMngService.updateRepProdDeclare(repProdDeclare);
    }
    
    /**
     * 
     * 更新产品发行报送信息
     * <功能详细描述>
     * @param repProdIssue RepProdIssue
     * @return int
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updateRepProdIssue")
    public int updateRepProdIssue(@RequestBody List<RepProdIssue> repProdIssue){
        return repFinProductRegistCodeMngService.updateRepProdIssue(repProdIssue);
    }

}
