/*
 * 文件名：RskValAssetCodeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.controller;

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

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMap;
import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCodeService;

/**
 *  资产科目映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RskValAssetCodeController
 * @since 1.0
 */
@RestController("RskValAssetCodeCtl_dtx")
@RequestMapping("/RskValAssetCode")
public class RskValAssetCodeController {

    @Autowired
    private RskValAssetCodeService rskValAssetCodeService;
    
    
    @PostMapping("/selectAll")
    List<RskValtblAssetSubMapDto> selectAll(@RequestBody Map<String,Object> map){
        return rskValAssetCodeService.selectAll(map);
    }
    
    /**
     * 初始化查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    /*@PostMapping("selectForPage/{page}/{length}")
    public Page<RskValtblAssetSubMapDto> selectForPage(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return rskValAssetCodeService.selectForPage(page, length, param);
    }*/
    
    /**
     * 初始化明细查询
     * <功能详细描述>
     * @param id
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPageDtl/{page}/{length}")
    public Page<RskValtblAssetSubMapDtlDto> findPageDtl(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return rskValAssetCodeService.findPageDtl(page, length, param);
    }
    
    @GetMapping("selectDtlList")
    public List<RskValtblAssetSubMapDtlDto> selectDtl(String id){
        return rskValAssetCodeService.selectDtlList(id);
    }
    /**
     * 资产科目映射修改初始化
     * <功能详细描述>
     * @param id
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectAssetCodeByPrimaryKey")
    public RskValtblAssetSubMap selectAssetCodeByPrimaryKey(String id){
        return rskValAssetCodeService.selectAssetCodeByPrimaryKey(id);
    }
    /**
     * 资产科目映射保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("saveAssetCode")
    public void saveAssetCode(@RequestBody RskValtblAssetSubMapDto dto){
         rskValAssetCodeService.saveAssetCode(dto);
    }
    
    /**
     * 删除
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public void delete(@RequestBody RskValtblAssetSubMapDto dto){
        rskValAssetCodeService.delete(dto);
    }

    /**
     * 删除明细
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteDtl")
    public void deleteDtl(@RequestBody RskValtblAssetSubMapDtlDto dto){
        rskValAssetCodeService.deleteDtl(dto);
    }
    
    /**
     * 资产科目映射明细保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("saveAssetCodeDtl")
    public void saveAssetCodeDtl(@RequestBody RskValtblAssetSubMapDtlDto dto){
         rskValAssetCodeService.saveAssetCodeDtl(dto);
    }
    
    /**
     * 查询目标科目代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selecctRskValtblSubjectCodeList")
    public List<RskValtblSubject> selecctRskValtblSubjectCodeList(String subType){
        return rskValAssetCodeService.selecctRskValtblSubjectCodeList(subType);
    }
    
    /**
     * 判断映射名称是否重复
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selecctCountByName")
    public int selecctCountByName(@RequestBody Map<String,Object> params){
    	String id = (String) params.get("id");
    	String name = (String) params.get("name");
        return rskValAssetCodeService.selecctCountByName(id,name);
    }
    
    /**
     * 关联组合数
     * <功能详细描述>
     * @param assetSubMapId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getCountByPtl")
    public int getCountByPtl(String assetSubMapId){
        return rskValAssetCodeService.getCountByPtl(assetSubMapId);
    }
}
