/*
 * 文件名：FinProductRevalDocumentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.controller;

import java.util.List;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.PrdFileKey;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.FinProductFileInfoDto;
import com.joyintech.service.product.common.service.FinProductRevalDocumentService;

/**
 * 信息披露文档组件
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月19日
 * @see FinProductRevalDocumentController
 * @since 1.0
 */
@RestController("backFinProductRevalDocumentController_dtx")
@RequestMapping("/FinProductRevalDocument")
public class FinProductRevalDocumentController {
    /**
     * finProductRevalDocumentService
     */
    @Autowired(required=false)
    private FinProductRevalDocumentService finProductRevalDocumentService;
    /**
     * 根据主键删除
     * @param key 信息披露文档主键
     * @return 删除记录
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(@RequestBody PrdFileKey key) {
        return finProductRevalDocumentService.deleteByPrimaryKey(key);
    }
    /**
     * 根据金融产品代码删除
     * @param finprodId 金融产品代码
     * @return 删除记录
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public int delete(String finprodId) {
        return finProductRevalDocumentService.delete(finprodId);
    }

    /**
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/queryForList")
    public List<FinProductFileInfoDto> queryForList(String finprodId) {
        return finProductRevalDocumentService.queryForList(finprodId);
    }

    /**
     * @param fileType
     * @return
     */
    @GetMapping("/queryByType")
    public List<SelectOptionDto> queryByType(String fileType, String initValue) {
        return finProductRevalDocumentService.queryForListByFileType(fileType, initValue);
    }
}
