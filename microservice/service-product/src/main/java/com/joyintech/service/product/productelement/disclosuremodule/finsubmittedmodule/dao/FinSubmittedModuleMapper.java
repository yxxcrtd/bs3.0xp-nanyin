/*
 * 文件名：FinSubmittedModule001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dto.FinSubmittedModuleDto;
/**
 * 申报方案mapper
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModuleMapper
 * @since 1.0
 */
@Repository
public interface FinSubmittedModuleMapper {

    /**
     * 申报附件方案名称查询事件
     * 根据查询框输入的参数进行树节点的初始化
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    List<FinSubmittedModuleDto> getSchemeName(Map<String,Object> map);

    /**
     * 申报附件方案表删除操作
     * 
     * @param entity 删除参数
     * @return 结果
     * @see
     * @since 1.0
     */
    int delete(FinSubmittedModuleDto entity);

    /**
     * 删除验证
     * 检查选中的附件模板是否被产品使用
     * @param schemeId 申报附件方案代码
     * @return 申报附件方案代码
     * @see
     * @since 1.0
     */
    String deleteCheck(@Param("schemeId") String schemeId);

    /**
     * 查询申报附件方案代码和申报附件方案名称
     * 
     * @param param 申报附件方案代码
     * @return 申报附件方案代码和申报附件方案名称
     * @see
     * @since 1.0
     */
    FinSubmittedModuleDto selectScheme( String param);

    /**
     * 申报附件方案编辑事件
     * 
     * @param dto 编辑参数
     * @return 结果
     * @see
     * @since 1.0
     */
    int update(FinSubmittedModuleDto dto);

    /**
     * 查询申报附件方案名称
     *
     * @param map 申报附件方案名称
     * @return 结果
     * @see
     * @since 1.0
     */
    String selectSchemeName(Map<String,Object> map);
}
