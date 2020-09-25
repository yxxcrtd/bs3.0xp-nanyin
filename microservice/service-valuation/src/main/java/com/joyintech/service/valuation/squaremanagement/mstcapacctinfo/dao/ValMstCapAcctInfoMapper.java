/*
 * 文件名：ValMstCapAcctInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.dto.ValMstCapAcctInfoDto;


/**
 * 清算账务表Mapper
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月23日
 * @see ValMstCapAcctInfoMapper
 * @since 1.0
 */
@Repository(value="serviceValMstCapAcctInfoMapper")
public interface ValMstCapAcctInfoMapper {

    /**
     * 查询清算账户表信息
     * 
     * @param param 查询条件
     * @return 清算账户表信息
     */
    List<ValMstCapAcctInfoDto> queryPageList(Map<String, Object> params);
    
    /**
     * 插入清算账户表
     * 
     * @param param dto
     * @return num
     */
    int insertValMstCapAcctInfo(ValMstCapAcctInfoDto dto);
    
    /**
     * 根据账号删除清算账户信息
     * 
     * @param param acctNo
     * @return num
     */
    int deleteValMatAcctInfoByAcctno(String acctNo);
    
    /**
     * 根据账号查询清算账户表信息
     * 
     * @param param 查询条件
     * @return 清算账户表信息
     */
    ValMstCapAcctInfoDto queryValMstAcctInfoByAcctno(String acctNo);
    
    /**
     * 根据账号修改账户表信息
     * 
     * @param param 查询条件
     * @return 清算账户表信息
     */
    int updateValMstAcctInfoByAcctno(ValMstCapAcctInfoDto record);
    /**
     * 根据账号修查询清算账户信息表中该账号是否已存在
     * 
     * @param param 查询条件
     * @return 清算账户表信息
     */
    int checkValMstAcctInfoByAcctno(String acctNo);
}
