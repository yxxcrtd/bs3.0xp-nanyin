/*
 * 文件名：DocumentManagementController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.document.documentmanagement.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.document.documentmanagement.dto.DocumentManagementDto;
import com.joyintech.service.document.documentmanagement.service.DocumentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 文档管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月16日
 * @see DocumentManagementController
 * @since 1.0
 */
@RestController("DocumentManagementCtr_dtx")
@RequestMapping("/DocumentManagement")
public class DocumentManagementController {
    @Autowired
    private DocumentManagementService documentManagementService;
    /**
     * 组合代码查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length) {
        return documentManagementService.getPortfolioId(page, length,param);
    }
    /**
     * 
     * 所属部门查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getDeptInstitutionCode/{page}/{length}")
    public Page<SelectOptionDto> getDeptInstitutionCode(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length) {
        return documentManagementService.getDeptInstitutionCode(page, length,param);
    }
    /**
     * 
     * 共通业务类型
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getBusinessType/{page}/{length}")
    public Page<SelectOptionDto> getBusinessType(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length) {
        return documentManagementService.getBusinessType(page, length,param);
    }
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<DocumentManagementDto> selectForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length) {
        return documentManagementService.selectForPage(page, length,param);
    }
    /**
     * 附件详情查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectForDetails/{page}/{length}")
    public Page<DocumentManagementDto> selectForDetails(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length) {
        return documentManagementService.selectForDetails(page, length,param);
    }
    /**
     * 上传文件
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody DocumentManagementDto dto) {
        return documentManagementService.insert(dto);
    }
    /**
     * 删除文件
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody DocumentManagementDto dto) {
        return documentManagementService.delete(dto);
    }
}
