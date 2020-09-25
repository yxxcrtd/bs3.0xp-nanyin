/*
 * 文件名：MstIndexQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstindexquotationinfo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstindexquotationinfo.dto.MstIndexQuotationInfo001Dto;
import com.joyintech.service.basicdata.mstindexquotationinfo.service.MstIndexQuotationInfoService;
import com.joyintech.tams.common.base.entity.MstExponentQuotation;

/**
 * 指数行情信息查询
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfoController
 * @since 1.0
 */
@RestController("MstIndexQuotationInfoCtr_dtx")
@RequestMapping("/MstIndexQuotationInfo")
public class MstIndexQuotationInfoController  {

    /**
     * 注入Service类
     */
    @Autowired
    private MstIndexQuotationInfoService mstIndexQuotationInfo001Service;

    /**
     * 画面查询方法
     * @param map map
     * @return Page<MstCoinFundQuotationInfo001Dto> 分页实体类结果
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<MstIndexQuotationInfo001Dto> selectPage(@RequestBody Map<String, Object> map,@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return mstIndexQuotationInfo001Service.selectForPage(page,length,map);
    }
    /**
     * 通过主键查询数据
     * <功能详细描述>
     * @param dto dto
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByPrimaryKey")
    public MstExponentQuotation selectByPrimaryKey(@RequestBody MstIndexQuotationInfo001Dto dto) {
        return mstIndexQuotationInfo001Service.selectByPrimaryKey(dto);
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
    public void doComplete(@RequestBody MstIndexQuotationInfo001Dto dto) {
        mstIndexQuotationInfo001Service.doComplete(dto);
    }
}
