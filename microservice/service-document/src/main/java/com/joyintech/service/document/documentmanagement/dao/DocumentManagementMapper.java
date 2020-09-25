/*
 * 文件名：DocumentManagementMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.document.documentmanagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.document.documentmanagement.dto.DocumentManagementDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月16日
 * @see DocumentManagementMapper
 * @since 1.0
 */
@Component
public interface DocumentManagementMapper {

    /**
     * 组合代码的联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> param);

    /**
     * 所属部门的下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getDeptInstitutionCode(Map<String, Object> param);

    /**
     * 分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<DocumentManagementDto> selectForPage(Map<String, Object> param);

    /**
     * 附件详情查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<DocumentManagementDto> selectForDetails(Map<String, Object> param);

    /**
     * 删除文件信息表
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int delete(DocumentManagementDto dto);

    /**
     * 共通业务类型
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBusinessType(Map<String, Object> param);
    /**
     * 查询清算账户表
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch1(Map<String, Object> param);
    /**
     * 查询 证券账户管理表
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch2(Map<String, Object> param);
    /**
     * 查询交易对手管理表
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch3(Map<String, Object> param);
    /**
     * 查询债券类资产
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch4(Map<String, Object> param);
    /**
     * 查询存款类资产
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch5(Map<String, Object> param);
    /**
     * 查询基金类资产
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch6(Map<String, Object> param);
    /**
     * 查询计划类资产净值型
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch7(Map<String, Object> param);
    /**
     * 查询计划类资产利率型
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch8(Map<String, Object> param);
    /**
     * 查询项目类资产
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch9(Map<String, Object> param);
    /**
     * 查询信息披露模板管理
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch10(Map<String, Object> param);
    /**
     * 查询 申报附件方案管理
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch11(Map<String, Object> param);
    /**
     * 查询产品费用支付管理
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch12(Map<String, Object> param);
    /**
     * 查询 债券投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch13(Map<String, Object> param);
    /**
     * 查询拆借投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch14(Map<String, Object> param);
    /**
     * 查询存款投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch15(Map<String, Object> param);
    /**
     * 查询基金投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch16(Map<String, Object> param);
    /**
     * 查询计划类资产净值型投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch17(Map<String, Object> param);
    /**
     * 查询计划类资产利率型投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch18(Map<String, Object> param);
    /**
     * 查询 回购投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch19(Map<String, Object> param);
    /**
     * 查询项目类资产投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch20(Map<String, Object> param);
    
    /**
     * 查询债券投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch21(Map<String, Object> param);
    /**
     * 查询拆借投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch22(Map<String, Object> param);
    /**
     * 查询 存款投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch23(Map<String, Object> param);
    /**
     * 查询 基金类资产投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch24(Map<String, Object> param);
    /**
     * 查询计划类资产净值型投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch25(Map<String, Object> param);
    /**
     * 查询计划类资产利率型投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch26(Map<String, Object> param);
    /**
     * 查询回购投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch27(Map<String, Object> param);
    
    /**
     * 查询项目类资产投资交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch28(Map<String, Object> param);
    /**
     * 查询产品要素管理
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch29(Map<String, Object> param);
    /**
     * 计划类货币型标的
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch30(Map<String, Object> param);
    /**
     *  查询通道管理
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch31(Map<String, Object> param);
    /**
     *查询通道费用支付
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch32(Map<String, Object> param);
    /**
     * 计划类资产（货币型）投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch33(Map<String, Object> param);
    /**
     * 查询买断式回购投资委托
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch34(Map<String, Object> param);
    /**
     * 查询计划类资产（货币型）交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch35(Map<String, Object> param);
    /**
     * 查询买断式回购交易
     * @param param
     * @return
     */
    List<DocumentManagementDto> selectForPageBranch36(Map<String, Object> param);

}
