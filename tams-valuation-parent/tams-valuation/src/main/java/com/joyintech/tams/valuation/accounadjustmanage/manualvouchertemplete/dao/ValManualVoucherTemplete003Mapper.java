/*
 * 文件名：ValManualVoucher003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ValManualVoucherTmpDetail;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003AddDto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003SubjectDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 手工凭证模板新建Mapper
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月9日
 * @see ValManualVoucherTemplete003Mapper
 * @since 1.0
 */
@Repository
public interface ValManualVoucherTemplete003Mapper {

    ValManualVoucherTemplete001Dto getByTmpCode(@Param("tmpCode") String tmpCode);

    /**
     * 获取列表信息
     *
     * @param tmpCode 模板编号
     * @return 列表信息
     * @throws Exception 异常
     */
    List<ValManualVoucherTemplete003AddDto> searchList(String tmpCode);

    /**
     *
     * 科目号查询
     *
     * @param para
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> querySubjectNo(Map<String, Object> para);

    /**
     * 手工凭证模板表信息删除
     *
     * @param tmpCode
     * @return
     */
    int deleteByPrimaryKey(String tmpCode);

    /**
     * 手工凭证模板明细表信息删除
     *
     * @param tmpCode
     * @return
     */
    int deleteByTmpCode(String tmpCode);

    /**
     * 获取科目信息
     *
     * @return
     * @see
     * @since 1.0
     */
    ValManualVoucherTemplete003SubjectDto getSubjectNo(@Param("subjectNo") String subjectNo);

    /**
     * 通过模板编号获取明细表所有信息
     *
     * @return
     * @see
     * @since 1.0
     */
    ValManualVoucherTmpDetail selectByTmpCode(@Param("tmpCode") String tmpCode, @Param("subjectNo") String subjectNo);

    /**
     * 校验模板名称是否存在
     *
     * @return
     * @see
     * @since 1.0
     */
    int checkByTmpName(@Param("tmpName") String tmpName,@Param("tmpCode") String tmpCode);

    int queryPdSequences();

}
