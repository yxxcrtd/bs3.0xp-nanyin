/*
 * 文件名：CePreOccupancyManagementController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.creditmanagement.preoccupancymanagement.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.creditmanagement.preoccupancymanagement.dto.CePreOccupancyManagementDto;
import com.joyintech.service.risk.creditmanagement.preoccupancymanagement.service.CePreOccupancyManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 预占用授信
 *
 * @author 裴家俊
 * @version 1.0 2019年01月24日
 * @see CePreOccupancyManagementController
 * @since 1.0
 */
@RestController("CePreOccupancyManagementCtl_dtx")
@RequestMapping("/CePreOccupancyManagement")
public class CePreOccupancyManagementController {

    /**
     * cePreOccupancyManagementService
     */
    @Autowired
    private CePreOccupancyManagementService cePreOccupancyManagementService;

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CePreOccupancyManagementDto> selectForPage(@RequestBody Map<String, Object> param,
                                                           @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return cePreOccupancyManagementService.selectForPage(page, length, param);
    }

    /**
     * 客户代码联想
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/getCustomerId")
    public List<SelectOptionDto> getCustomerId(@RequestBody Map<String, Object> map) {
        return cePreOccupancyManagementService.getCustomerId(map);
    }


    /**
     * 额度品种子
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/getAllCeOrgQuote")
    public List<TreeNode> getAllCeOrgQuote(String rank) {
        return cePreOccupancyManagementService.getAllCeOrgQuote(rank);
    }

    /**
     * 额度品种父
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/getParentCeOrgQuote")
    public List<TreeNode> getParentCeOrgQuote(String rank) {
        return cePreOccupancyManagementService.getParentCeOrgQuote(rank);
    }

    /**
     * 额度信息获取
     *
     * @param indId 指标代码
     * @return PrdValParam
     */
    @PostMapping("queryCeOrgBySeqno")
    public CePreOccupancyManagementDto queryCeOrgBySeqno(@RequestBody Map<String, Object> map) {
        return cePreOccupancyManagementService.queryCeOrgBySeqno(map);
    }

    /**
     * 新建编辑场合时查询生效及到期日是否重复
     *
     * @param Map
     */
    @PostMapping("/searchRepeatByVmDate")
    public int searchRepeatByVmDate(@RequestBody Map<String, Object> param) {
        return cePreOccupancyManagementService.searchRepeatByVmDate(param);
    }

    /**
     * 新建编辑场合时查询生效及到期日是否重复
     *
     * @param Map
     */
    @PostMapping("/searchRepeatByVmDateArea")
    public int searchRepeatByVmDateArea(@RequestBody Map<String, Object> param) {
        return cePreOccupancyManagementService.searchRepeatByVmDateArea(param);
    }

    /**
     * 新建编辑场合时查询批复号是否重复
     *
     * @param Map
     */
    @GetMapping("/searchRepeatByPflsh")
    public int searchRepeatByPflsh(String pflsh, String seqno) {
        return cePreOccupancyManagementService.searchRepeatByPflsh(pflsh, seqno);
    }

    /**
     * 插入
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody CePreOccupancyManagementDto dto) throws Exception {
        cePreOccupancyManagementService.insert(dto);
    }

    /**
     * 更新
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
	@Transactional
	@DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody CePreOccupancyManagementDto dto) throws Exception {
        cePreOccupancyManagementService.update(dto);
    }

    /**
     * 删除
     *
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     */
	@Transactional
	@DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody CePreOccupancyManagementDto dto) throws Exception {
        return cePreOccupancyManagementService.delete(dto);
    }
}
