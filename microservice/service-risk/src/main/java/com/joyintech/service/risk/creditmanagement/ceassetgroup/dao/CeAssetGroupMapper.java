/*
 * 文件名：CeAssetGroupMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetgroup.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;

/**
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAssetGroupMapper
 * @since 1.0
 */
@Repository
public interface CeAssetGroupMapper {

    List<CreditLinesAstGrpDto> selectForPage(Map<String, Object> param);
    CreditLinesAstGrpDto queryCeAssetGroupBySeqno(Map<String, Object> param);
    /**
     * 资产代码联想下拉框
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getAssetGroupAliasIds(Map<String, Object> map);
    

}
