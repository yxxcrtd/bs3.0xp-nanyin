package com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dto.FinSubmittedModuleDto;
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.service.FinSubmittedModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: service
 * @description: FinSubmittedModuleController
 * @author: liuwei
 * @create: 2018-11-05 10:51
 **/
@RestController("backFinSubmittedModuleController_dtx")
@RequestMapping("/FinSubmittedModule")
public class FinSubmittedModuleController {
    /**
     * 净值型产品分红方案Service
     */
    @Autowired
    private FinSubmittedModuleService finSubmittedModule001Service;

    /**
     * 申报附件方案名称查询事件 根据查询框输入的参数进行树节点的初始化
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @PostMapping("/getSchemeName")
    List<FinSubmittedModuleDto> getSchemeName(@RequestBody Map<String,Object> map){
        return finSubmittedModule001Service.getSchemeName(map);
    }

    /**
     * 插入
     * @param dto 页面选中的数据
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody FinSubmittedModuleDto dto){
        return finSubmittedModule001Service.insert(dto);
    }

    /**
     * 删除
     * @param dto 页面选中的数据
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinSubmittedModuleDto dto){
        return finSubmittedModule001Service.delete(dto);
    }

    /**
     * 查询申报附件方案代码和申报附件方案名称
     *
     * @param param 申报附件方案代码
     * @return 申报附件方案代码和申报附件方案名称
     * @see
     * @since 1.0
     */
    @GetMapping("selectScheme")
    public FinSubmittedModuleDto selectScheme(String param){
        return finSubmittedModule001Service.selectScheme(param);
    }

    /**
     * 申报附件方案编辑事件
     *
     * @param dto 编辑参数
     * @return 结果
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody FinSubmittedModuleDto dto) {
        return finSubmittedModule001Service.update(dto);
    }

    /**
     * 删除验证
     *
     * @param schemeId 编辑参数
     * @return 结果
     * @see
     * @since 1.0
     */
    @GetMapping("deleteCheck")
    public String deleteCheck(String schemeId) {
        return finSubmittedModule001Service.deleteCheck(schemeId);
    }

    /**
     * 查询申报附件方案名称
     *
     * @param schemeName 申报附件方案名称
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("selectSchemeName")
    public String selectSchemeName(@RequestBody Map<String,Object> map) {
        return finSubmittedModule001Service.selectSchemeName(map);
    }

}
