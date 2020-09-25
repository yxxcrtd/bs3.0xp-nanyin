/*
 * 文件名：CeAssetGroupManagementController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.controller;

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
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement002Dto;
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagementService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see CeAssetGroupManagementController
 * @since 1.0
 */
@RestController("CeAssetGroupManagementCtl_dtx")
@RequestMapping("/CeAssetGroupManagement")
public class CeAssetGroupManagementController {
    
    /**
     * CeAssetGroupManagementService
     */
    @Autowired
    private CeAssetGroupManagementService ceAssetGroupManagementService;
    
    /**
     * 
     * <一句话功能简述>
     * 查询页面
     * @param param
     * @param page
     * @param length
     * @return 查询结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<CeAssetGroupManagement001Dto> selectForPage(@RequestBody Map<String, Object> param,
                                                            @PathVariable("page") int page,
                                                            @PathVariable("length") int length) throws Exception {
        return ceAssetGroupManagementService.selectForPage(page, length, param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 加载树节点
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetName")
    public List<CeAssetGroupManagement001Dto> getAssetName(@RequestBody Map<String, Object> param) {
        return ceAssetGroupManagementService.getAssetName(param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 查询明细
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getBusinessInfoBySeqno")
    public CeAssetGroupManagement001Dto getBusinessInfoBySeqno(@RequestBody String seqno) {
        return ceAssetGroupManagementService.getBusinessInfoBySeqno(seqno);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 插入
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody CeAssetGroupManagement001Dto dto) {
        ceAssetGroupManagementService.insert(dto);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 更新
     * @param dto
     * @see
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody CeAssetGroupManagement001Dto dto) {
        ceAssetGroupManagementService.update(dto);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 删除
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody CeAssetGroupManagement001Dto dto) {
        return ceAssetGroupManagementService.delete(dto);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetInfo")
    public List<SelectOptionDto> queryAssetInfo(@RequestBody Map<String, Object> map) {
        return ceAssetGroupManagementService.queryAssetInfo(map);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 资产代码/名称,查询页面
     * @param param
     * @param page
     * @param length
     * @return 查询结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("selectAssetInfo")
    public List<CeAssetGroupManagement002Dto> selectAssetInfo(@RequestBody Map<String, Object> param) throws Exception {
        return ceAssetGroupManagementService.selectAssetInfo(param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 插入
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertAssetPool")
    public void insertAssetPool(@RequestBody CeAssetGroupManagement002Dto dto) {
        ceAssetGroupManagementService.insertAssetPool(dto);
    }
    
    
    /**
     * 
     * <一句话功能简述>
     * 删除
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteAssetPool")
    public int deleteAssetPool(@RequestBody CeAssetGroupManagement002Dto dto) {
        return ceAssetGroupManagementService.deleteAssetPool(dto);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 获取资产明细通过seqno
     * @param dto
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetDetailBySeqno/{page}/{length}")
    public Page<CeAssetGroupManagement001Dto> getAssetDetailBySeqno(@RequestBody Map<String, Object> param,
                                                                    @PathVariable("page") int page,
                                                                    @PathVariable("length") int length) throws Exception {
        return ceAssetGroupManagementService.getAssetDetailBySeqno(page, length, param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 查询某系列下存在的资产明细
     * @param param seqno 系列代码
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getExistAssetBySeqno")
    public List<CeAssetGroupManagement002Dto> getExistAssetBySeqno(@RequestBody Map<String, Object> param) {
        return ceAssetGroupManagementService.getExistAssetBySeqno(param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 新建系列名称重复校验
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("checkIsExistByassetpoolName")
    public int checkIsExistByassetpoolName(@RequestBody Map<String, Object> map){
        return ceAssetGroupManagementService.checkIsExistByassetpoolName(map);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("checkIsExistAssetPool")
    public int checkIsExistAssetPool(@RequestBody String finprodId) {
        return ceAssetGroupManagementService.checkIsExistAssetPool(finprodId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加到其他系列中
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("checkIsExistAssetPoolNew")
    public String checkIsExistAssetPoolNew(@RequestBody String finprodId) {
        return ceAssetGroupManagementService.checkIsExistAssetPoolNew(finprodId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 判断是否关联授信客户编号
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("checkisExistId")
    public int checkisExistId(@RequestBody String seqno) {
        return ceAssetGroupManagementService.checkisExistId(seqno);
    }
    
    @PostMapping("checkisExistAssetByCredit")
    public int checkisExistAssetByCredit(@RequestBody String finprodId) {
        return ceAssetGroupManagementService.checkisExistAssetByCredit(finprodId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 判断该资产系列是否存在明细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("checkIsExistDetail")
    public int checkIsExistDetail(@RequestBody String seqno) {
        return ceAssetGroupManagementService.checkIsExistDetail(seqno);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 查询流程中的工作流代码
     * @param pageId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getBusinessWorkflowId")
    public List<CeAssetGroupManagement002Dto> getBusinessWorkflowId(String pageId) {
        return ceAssetGroupManagementService.getBusinessWorkflowId(pageId);
    }

}
