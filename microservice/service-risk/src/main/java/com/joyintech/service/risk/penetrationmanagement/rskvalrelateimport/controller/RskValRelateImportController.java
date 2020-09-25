/*
 * 文件名：RskValRelateImportController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImportDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.service.RskValRelateImportService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2018年12月20日
 * @see RskValRelateImportController
 * @since 1.0
 */
@RestController("RskValRelateImportCtl_dtx")
@RequestMapping("/RskValRelateImport")
public class RskValRelateImportController {
    
    /**
     * RskValRelateImportService
     */
    @Autowired
    private RskValRelateImportService rskValRelateImportService;
    
    /**
     * 
     * 画面查询方法
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<RskValRelateImportDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskValRelateImportService.selectForPage(page, length, param);
    }
    
    /**
     * <一句话功能简述> 指标结构映射联想下拉框
     * 
     * @param map
     * @return 指标结构映射信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryStructMapId")
    public List<SelectOptionDto> queryStructMapId() {
        return rskValRelateImportService.queryStructMapId();
    }
    
    /**
     * <一句话功能简述> 汇总科目映射联想下拉框
     * 
     * @param map
     * @return 汇总科目映射信息
     * @see
     * @since 1.0
     */
    @PostMapping("querySumsubMapId")
    public List<SelectOptionDto> querySumsubMapId() {
        return rskValRelateImportService.querySumsubMapId();
    }
    
    /**
     * <一句话功能简述> 资产科目映射联想下拉框
     * 
     * @param map
     * @return 资产科目映射信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetSubMapId")
    public List<SelectOptionDto> queryAssetSubMapId() {
        return rskValRelateImportService.queryAssetSubMapId();
    }
    
    /**
     * 
     * <一句话功能简述>
     * 新建插入操作
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.insert(dto);
    }
    
    /**
     * <一句话功能简述> 更新操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.update(dto);
    }
    
    /**
     * <一句话功能简述> 删除操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.delete(dto);
    }
    
    /**
     * <一句话功能简述> 编辑按钮按下，查询相关信息
     * 
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectInfoById")
    public RskValRelateImportDto selectInfoById(@RequestBody String ptlId) {
        return rskValRelateImportService.selectInfoById(ptlId);
    }
    
    /**
     * <一句话功能简述> 批量删除操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteList")
    public int deleteList(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.deleteList(dto);
    }
    
    /**
     * <一句话功能简述> 组合名称重复校验
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("checkGroupName")
    public int checkGroupName(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.checkGroupName(dto);
    }

}
