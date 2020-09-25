package com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.controller;

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
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dto.FinSubmittedModuleDetailDto;
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.service.FinSubmittedModuleDetailService;

/**
 * @program: service
 * @description: FinSubmittedModuleDetailController
 * @author: liuwei
 * @create: 2018-11-05 10:52
 **/
@RestController("backFinSubmittedModuleDetailController_dtx")
@RequestMapping("/FinSubmittedModuleDetail")
public class FinSubmittedModuleDetailController {
    /**
     * 净值型产品分红方案Service
     */
    @Autowired
    private FinSubmittedModuleDetailService finSubmittedModule002Service;

    /**
     * 插入
     * @param dto 页面选中的数据
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody FinSubmittedModuleDetailDto dto){
        return finSubmittedModule002Service.insert(dto);
    }

    /**
     * 删除申报附件方案表信息
     *
     * @param schemeId 申报附件方案代码
     * @return 结果
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("delete")
    public int delete(String schemeId) {
        return finSubmittedModule002Service.delete(schemeId);
    }

    /**
     * 表格查询操作
     * 查询附件类型、模板类型和备注等数据（根据申报附件方案代码查询）
     * @param map 查询参数
     * @return 附件类型、模板类型和备注
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinSubmittedModuleDetailDto> selectForPage(@RequestBody Map<String, Object> map,
                                                       @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finSubmittedModule002Service.selectForPage(page, length, map);
    }

    /**
     * 查询附件类型、模板类型和备注等数据（根据申报附件方案流水号查询）
     *
     * @param seqno 流水号
     * @return 附件类型、模板类型和备注
     * @see
     * @since 1.0
     */
    @GetMapping("selectDocTpl")
    public FinSubmittedModuleDetailDto selectDocTpl(String seqno) {
        return finSubmittedModule002Service.selectDocTpl(seqno);
    }
    /**
     * 根据点击行的附件、模板类型和申报方案代码查询对应的流水号
     *
     * @param dto 查询参数
     * @return 流水号
     * @see
     * @since 1.0
     */
    @PostMapping("selectSeqno")
    public String selectSeqno(FinSubmittedModuleDetailDto dto) {
        return finSubmittedModule002Service.selectSeqno(dto);
    }

    /**
     * 附件明细表格查询操作
     *
     * @param map 查询参数
     * @return 文件名和业务编码
     * @throws Exception 异常
     * @see
     * @since 1.0
     *//*
    @PostMapping("selectFile/{page}/{length}")
    public Page<FinSubmittedModule002Dto> selectFile(@RequestBody Map<String, Object> map,
                                                        @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finSubmittedModule002Service.selectFile(page, length, map);
    }*/

    /**
     * 根据业务编码删除文件信息
     *
     * @param businessId 业务编码
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFile")
    public int  deleteFile(String businessId) {
        return finSubmittedModule002Service.deleteFile(businessId);
    }

    /**
     * 查询上传文件信息流水号
     * 在上传时校验该模板是否已上传过文件
     * @param businessId 业务编码
     * @return 流水号
     * @see
     * @since 1.0
     */
    @GetMapping("selectSeqNo")
    public String selectSeqNo(String businessId) {
        return finSubmittedModule002Service.selectSeqNo(businessId);
    }

    /**
     * 查询同样的附件模板是否上传过
     *
     * @param dto 附件模板
     * @return 流水号
     * @see
     * @since 1.0
     */
    @PostMapping("selectByDocAndTpl")
    public String selectByDocAndTpl(@RequestBody FinSubmittedModuleDetailDto dto) {
        return finSubmittedModule002Service.selectByDocAndTpl(dto);
    }

    /**
     * 验证该附件模板是否被理财产品使用
     *
     * @param schemeId 附件模板
     * @return 流水号
     * @see
     * @since 1.0
     */
    @GetMapping("selectFinProdId")
    public int selectFinProdId(String schemeId) {
        return finSubmittedModule002Service.selectFinProdId(schemeId).size();
    }
}
