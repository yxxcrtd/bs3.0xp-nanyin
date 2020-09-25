/*
 * 文件名：ValBondassetquery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.loanmark.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.assets.loanmark.dto.ValLoanMarkquery001Dto;

/**
 * 拆借标的查询
 * @author huwei
 * @version 1.0 2018年10月19日
 * @see ValLoanMarkquery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValLoanMarkquery001Mapper")
public interface ValLoanMarkquery001Mapper {
    
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValLoanMarkquery001Dto>
     * @see
     * @since 1.0
     */
    List<ValLoanMarkquery001Dto> queryPageList(Map<String, Object> params);
}
