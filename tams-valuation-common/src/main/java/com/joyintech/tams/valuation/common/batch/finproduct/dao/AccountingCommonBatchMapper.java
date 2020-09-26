/*
 * 文件名：AccountingCommonMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.dao;

import com.joyintech.tams.valuation.common.batch.finproduct.entity.SubjectBalance;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SubjectBalanceSearchDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author wangzhiang
 * @version 1.0 2018年5月31日
 * @see AccountingCommonMapper
 * @since 1.0
 */
@Repository
public interface AccountingCommonBatchMapper {

    /**
     * 基础科目性质查询
     *
     * @param groupId 分组代码
     * @param acctStandardId 会计准则代码
     * @return 科目性质列表
     * @see
     * @since 1.0
     */
    List<String> getValSubjectNatureList(@Param("groupId") String groupId, @Param("acctStandardId") String acctStandardId);

    /**
     * 科目余额查询-当前最新余额查询
     *
     * @param searchDto 参数Dto
     * @return 当前表科目余额结果集
     * @see
     * @since 1.0
     */
    List<SubjectBalance> queryCurrentLatestBalance(SubjectBalanceSearchDto searchDto);
}
