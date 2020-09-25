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
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.assets.loanmark.dto.ValLoanMarkquery001Dto;

/**
 * 拆借标的查询-双击查看
 * @author huwei
 * @version 1.0 2018年10月23日
 * @see ValLoanMarkquery002Mapper
 * @since 1.0
 */
@Repository(value="serviceValLoanMarkquery002Mapper")
public interface ValLoanMarkquery002Mapper {
     
    /**
     * 拆借标的查询-双击查看
     * @param finprodId String
     * @param finprodType String
     * @return ValLoanMarkquery001Dto
     * @see
     * @since 1.0
     */
    ValLoanMarkquery001Dto queryLoanMarkInfo(Map<String,Object> params);

}
