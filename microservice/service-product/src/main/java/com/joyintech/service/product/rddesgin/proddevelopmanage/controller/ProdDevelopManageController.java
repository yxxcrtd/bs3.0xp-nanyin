/*
 * 文件名：ProdDevelopManageController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.rddesgin.proddevelopmanage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.proddevelopmanage.dto.ProdDevelopManageDto;
import com.joyintech.service.product.rddesgin.proddevelopmanage.service.ProdDevelopManageService;

/**
 * 微服务：产品研发方案管理Controller
 * 
 * @author zhangJin
 * @version 1.0 2019年10月23日
 * @see ProdDevelopManageController
 * @since 1.0
 */
@RestController("prodDevelopManageController_dtx")
@RequestMapping("/ProdDevelopManage")
public class ProdDevelopManageController {

    /**
     * ProdDevelopManageService
     */
    @Autowired
    private ProdDevelopManageService prodDevelopManageService;

    /**
     * 画面查询方法
     * 
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<ProdDevelopManageDto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return prodDevelopManageService.selectForPage(page, length, pageParam);
    }

    /**
     * 获取系列编号下拉框
     * 
     * @param param 模糊查询
     * @param page 页数
     * @param length 条数
     * @return 下拉框
     * @see
     * @since 1.0
     */
    @PostMapping("/getProdDesignSeries/{page}/{length}")
    public Page<SelectOptionDto> getProdDesignSeries(@RequestBody Map<String, Object> pageParam,
                                                     @PathVariable("page") int page,
                                                     @PathVariable("length") int length) {
        return prodDevelopManageService.getProdDesignSeries(page, length, pageParam);
    }

    /**
     * 根据系列编号获取结果信息
     * 
     * @param pageParam 系列编号
     * @return 结果信息
     * @see
     * @since 1.0
     */
    @PostMapping("/getProdResultInfos")
    public ProdDevelopManageDto getProdResultInfos(@RequestBody Map<String, Object> pageParam) {
        return prodDevelopManageService.getProdResultInfos(pageParam);
    }

    /**
     * 根据流水号查询研发方案详情
     * 
     * @param seqNo 流水号
     * @return 研发方案详情
     * @see
     * @since 1.0
     */
    @GetMapping("getEntityBySeqNo")
    public ProdDevelopManageDto getEntityBySeqNo(String seqNo) {
        return prodDevelopManageService.getEntityBySeqNo(seqNo);
    }

    /**
     * 工作流完成操作
     * 
     * @param entity ProdDevelopManageDto
     * @param type 类型
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/doComplete/{type}")
    public void doComplete(@RequestBody ProdDevelopManageDto entity,
                           @PathVariable("type") String type) {
        prodDevelopManageService.doComplete(entity, type);
    }
}
