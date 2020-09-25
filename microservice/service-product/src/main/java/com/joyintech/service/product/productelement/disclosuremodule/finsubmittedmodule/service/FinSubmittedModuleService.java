/*
 * 文件名：FinSubmittedModule001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PrdDeclareSchemeMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dao.FinSubmittedModuleMapper;
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dto.FinSubmittedModuleDto;

/**
 * 申报方案Service
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModuleService
 * @since 1.0
 */
@Service("backFinSubmittedModuleService")
@Transactional
public class FinSubmittedModuleService extends BaseService<FinSubmittedModuleDto> {

    /**
     * 申报附件方案Mapper
     */
    @Autowired
    private FinSubmittedModuleMapper finSubmittedModule001Mapper;

    /**
     * 申报方案Mapper
     */
    @Autowired
    private PrdDeclareSchemeMapper prdDeclareSchemeMapper;

    /**
     * 申报附件方案名称查询事件 根据查询框输入的参数进行树节点的初始化
     * 
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    public List<FinSubmittedModuleDto> getSchemeName(Map<String,Object> map) {
       return finSubmittedModule001Mapper.getSchemeName(map);
    }

    /**
     * 插入操作
     *
     * @param dto 选中参数
     * @see
     * @since 1.0
     */
    public int insert(FinSubmittedModuleDto dto) {
        return prdDeclareSchemeMapper.insertSelective(dto);
    }

    /**
     * 删除操作
     *
     * @param dto 选中参数
     * @see
     * @since 1.0
     */
    public int delete(FinSubmittedModuleDto dto) {
        int i = 0;
        // 删除申报附件方案表信息
        i=i+finSubmittedModule001Mapper.delete(dto);
        // 删除申报方案表信息
        i=i+prdDeclareSchemeMapper.deleteByPrimaryKey(dto.getSchemeId());
        return i;
    }

    /**
     * 查询申报附件方案代码和申报附件方案名称
     * 
     * @param param 申报附件方案代码
     * @return 申报附件方案代码和申报附件方案名称
     * @see
     * @since 1.0
     */
    public FinSubmittedModuleDto selectScheme(String param) {
        return finSubmittedModule001Mapper.selectScheme(param);
    }

    /**
     * 申报附件方案编辑事件
     * 
     * @param dto 编辑参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public int update(FinSubmittedModuleDto dto) {
        return finSubmittedModule001Mapper.update(dto);
    }

    /**
     * 删除验证
     *
     * @param schemeId 申报附件方案编码
     * @return 结果
     * @see
     * @since 1.0
     */
    public String deleteCheck(String schemeId) {
        return finSubmittedModule001Mapper.deleteCheck(schemeId);
    }

    /**
     * 查询申报附件方案名称
     *
     * @param schemeName 申报附件方案名称
     * @return 结果
     * @see
     * @since 1.0
     */
    public String selectSchemeName(Map<String,Object> map) {
        return finSubmittedModule001Mapper.selectSchemeName(map);
    }
}
