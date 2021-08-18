/*
 * 文件名：RskRelatedPartyController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.namelistmanage.relatedparty.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.namelistmanage.relatedparty.dto.RskRelatedPartyDto;
import com.joyintech.service.risk.namelistmanage.relatedparty.service.RskRelatedPartyService;
import com.joyintech.tams.common.base.entity.RskRelatedParty;

/**
 * 关联方管理
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月14日
 * @see RskRelatedPartyController
 * @since 1.0
 */
@RestController("RskRelatedPartyCtl_dtx")
@RequestMapping("/RskRelatedParty")
public class RskRelatedPartyController {

    @Autowired
    private RskRelatedPartyService rskRelatedPartyService;

    /**
     * 查询关联方管理记录
     * 
     * @param page 页码
     * @param length 页长
     * @param param 查询条件
     * @return 关联方管理记录
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskRelatedPartyDto> findPage(@PathVariable("page") int page,
                                             @PathVariable("length") int length,
                                             @RequestBody Map<String, Object> param) {
        return rskRelatedPartyService.selectForPage(page, length, param);
    }

    /**
     * 查询关联方名称列表
     * 
     * @param page 页码
     * @param length 页长
     * @param param 查询条件
     * @return 关联方名称列表
     */
    @PostMapping("queryInstituteName/{page}/{length}")
    public Page<SuggestDto> queryInstituteName(@PathVariable("page") int page,
                                               @PathVariable("length") int length,
                                               @RequestBody Map<String, Object> param) {
        return rskRelatedPartyService.selectInstituteName(page, length, param);
    }

    /**
     * 批量登录
     * 
     * @param dtoList 关联方管理记录
     * @return 处理结果
     */
    @PostMapping("insertList")
    public int insertList(@RequestBody List<RskRelatedPartyDto> dtoList) {
        return rskRelatedPartyService.insertList(dtoList);
    }

    @PostMapping("edit")
    public int edit(@RequestBody RskRelatedPartyDto dto) {
        return rskRelatedPartyService.edit(dto);
    }

    /**
     * 批量删除
     * 
     * @param dtoList 社会统一信用代码
     * @return 处理结果
     */
    @PostMapping("deleteList")
    public int deleteList(@RequestBody List<String> dtoList) {
        return rskRelatedPartyService.deleteList(dtoList);
    }

    @PostMapping("queryInfoByPrimaryKey")
    public List<RskRelatedParty> queryInfoByPrimaryKey(@RequestBody List<String> keyList) {
        return rskRelatedPartyService.queryInfoByPrimaryKey(keyList);
    }

    @PostMapping("selectInstituteCode")
    public List<String> selectInstituteCode(@RequestBody List<String> keyList) {
        return rskRelatedPartyService.selectInstituteCode(keyList);
    }
}
