/*
 * 文件名：FinDisclosureModuleController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.controller;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.dto.FinDisclosureModuleDto;
import com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.service.FinDisclosureModuleService;

/**
 * 信息披露模板
 * @author wangzehui
 * @version 1.0 2018年10月31日
 * @see FinDisclosureModuleController
 * @since 1.0
 */
@RestController("backFinDisclosureModuleController_dtx")
@RequestMapping("/FinDisclosureModule")
public class FinDisclosureModuleController {
	/**
	 * FinDisclosureModuleService
	 */
    @Autowired
    private FinDisclosureModuleService finDisclosureModuleService;
    /**
     * 根据文件类型查询文件名
     * 
     * @param param 页面参数
     * @return list集合
     */
    @PostMapping("getFileNameOption")
    public List<SuggestDto> getFileNameOption(@RequestBody Map<String, Object> param){
        return finDisclosureModuleService.getFileNameOption(param);
    }
    /**
     * 画面查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinDisclosureModuleDto> selectForPage(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
    	Page<FinDisclosureModuleDto> pageList = finDisclosureModuleService.selectAll(page, length, param);
    	return pageList;
    }
    /**
     * 
     * 通过披露文档代码查询数据
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectByRevealFileId")
    public FinDisclosureModuleDto selectByRevealFileId(@RequestBody Map<String, Object> map){
        return finDisclosureModuleService.selectByRevealFileId(map);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinDisclosureModuleDto dto) throws Exception {
        return finDisclosureModuleService.delete(dto);
    }
    
    /**
     * 插入
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody FinDisclosureModuleDto dto) throws Exception {
        return finDisclosureModuleService.insert(dto);
    }

    @GetMapping("isUsed")
    public String isUsed(String revealFileId) {
        return finDisclosureModuleService.isUsed(revealFileId);
    }
}
