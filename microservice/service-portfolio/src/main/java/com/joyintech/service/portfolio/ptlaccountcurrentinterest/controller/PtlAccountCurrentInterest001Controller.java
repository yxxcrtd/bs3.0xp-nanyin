/*
 * 文件名：PtlAccountCurrentInterest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlaccountcurrentinterest.controller;

import java.util.Date;
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

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.service.portfolio.ptlaccountcurrentinterest.service.PtlAccountCurrentInterest001Service;

/**
 * 账户活期利率
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest001Controller
 * @since 1.0
 */
@RestController("ptlAccountCurrentInterest001Controller_dtx")
@RequestMapping("/ptlaccountcurrentinterest")
public class PtlAccountCurrentInterest001Controller {
    /**
     * 注入Service
     */
    @Autowired
    private PtlAccountCurrentInterest001Service ptlAccountCurrentInterest001Service;
    /**
     * 获取网点号
     * @param param 请求参数
     * @return 下拉框实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("getBusiAcctNo/{page}/{length}")
    public Page<SelectOptionDto> getBusiAcctNo(@RequestBody Map<String,Object> param, @PathVariable("page") int page, @PathVariable("length") int length){
        return ptlAccountCurrentInterest001Service.getBusiAcctNo( page, length, param);
    }
    /**
     * 分页查询
     * @param map 请求参数
     * @param page 页数
     * @param length 长度
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<PtlAccountCurrentInterest001Dto> selectForPage(@RequestBody Map<String, Object> map,
                                                               @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<PtlAccountCurrentInterest001Dto> pages =  ptlAccountCurrentInterest001Service.selectForPage(page,length,map);
        return pages;
    }
    /**
     * 获取利率
     * @return 利率
     * @see
     * @since 1.0
     */
    @GetMapping("getcurrentRate")
    public List<SelectOptionDto> getcurrentRate(){
        return ptlAccountCurrentInterest001Service.getcurrentRate();
    }
    /**
     * 流程处理类
     * @param dto 账户实体类
     * @see
     * @since 1.0
     */
    @PostMapping("doCompelete")
    @Transactional
    @DTXTransaction
    public void doCompelete(@RequestBody PtlAccountCurrentInterest001Dto dto){
        if(dto.getOperateType().equals("DELETE")){
            ptlAccountCurrentInterest001Service.delete(dto);
        }
        else if(dto.getOperateType().equals("NEW")){
            ptlAccountCurrentInterest001Service.insert(dto);
        }
        else{
            ptlAccountCurrentInterest001Service.update(dto);
        }
    }
    /**
     * 获取初始化信息
     * @param map 请求参数
     * @return 实体类
     * @see
     * @since 1.0
     */
    @PostMapping("getInitInfo")
    public PtlAccountCurrentInterest001Dto getInitInfo(@RequestBody Map<String,Object> map){
        return ptlAccountCurrentInterest001Service.getInitInfo(map);
    }
    /**
     * 获取最近的有效日
     * @param acctNo 账号
     * @return 最近的生效日
     * @see
     * @since 1.0
     */
    @GetMapping("getLastEffDate")
    public Date getLastEffDate(String acctNo){
        return ptlAccountCurrentInterest001Service.getLastEffDate(acctNo);
    }
    /**
     * 
     * 统计存在的信息
     * <功能详细描述>
     * @param dto 待校验dto
     * @return 统计结果
     * @see
     * @since 1.0
     */
    @PostMapping("countExistsData")
    public int countExistsData(@RequestBody PtlAccountCurrentInterest001Dto dto){
    	return ptlAccountCurrentInterest001Service.countExistsData(dto);
    }
    /**
     * 分页查询
     * @param map 请求参数
     * @param page 页数
     * @param length 长度
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("ratePage/{page}/{length}")
    public Page<PtlAccountCurrentInterest001Dto> ratePage(@RequestBody Map<String, Object> map,
                                                          @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return ptlAccountCurrentInterest001Service.ratePage(page, length, map);
    }

    /**
     * 根据金融产品代码查询生效日最新的活期利率信息
     * @param map
     * @return
     */
    @PostMapping("getInitInfoLastFinprodId")
    public PtlAccountCurrentInterest001Dto getInitInfoLastFinprodId(@RequestBody Map<String,Object> map){
        return ptlAccountCurrentInterest001Service.getInitInfoLastFinprodId(map);
    }
}
