/*
 * 文件名：MstSecuritiesAccountMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.fundsecuritiesaccount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.fundsecuritiesaccount.dto.FundSecuritiesAccountDto;

/**
 * 基金账户管理mapper
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月11日
 * @see FundSecuritiesAccountMapper
 * @since 1.0
 */
@Repository
public interface FundSecuritiesAccountMapper {
    /**
     * 
     * 模糊查询
     * <功能详细描述>
     * @param param pageParam
     * @return List
     * @see
     * @since 1.0
     */
    List<FundSecuritiesAccountDto> select(Map<String, Object> pageParam);
    /**
     * 基金账户账号联想下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    List<SuggestDto> getSecAcctId(@Param("secAcctId")String secAcctId);
    /**
     * 基金管理人联想下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    List<SuggestDto> getSecManager(@Param("manager")String manager);
    /**
     * 基金管理人下拉框检索
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    List<SelectOptionDto> getSecManagerSelect(@Param("manager")String manager);
    
    
    /**
     * 根据基金账户账号查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    FundSecuritiesAccountDto selectBySecAcctId(@Param("secAcctId")String secAcctId);
    /**
     * 检查基金账户账号是否用过
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    int checkBySecAcctId(FundSecuritiesAccountDto dto);
    /**
     * 检查基金账户名称是否用过
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    
    int checkBySecAcctName(FundSecuritiesAccountDto dto);
    /**
     * 检查资金结算账户是否存在
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    int checkByTrdAcctId(FundSecuritiesAccountDto dto);
    /**
     * 
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int checkCodeIsUse(FundSecuritiesAccountDto dto);
    
    /**
     * 
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item List<FundSecuritiesAccountDto>
     * @return int
     * @see
     * @since 1.0
     */
    List<FundSecuritiesAccountDto> checkAllCodeIsUse(List<FundSecuritiesAccountDto> item);
//    /**
//     * 
//     * 上传
//     * <功能详细描述>
//     * @param dto FileDto dto
//     * @return int
//     * @see
//     * @since 1.0
//     */
//    int insertIntoFile(FileDto dto);
//    
//    /**
//     * 
//     * 查询上传文件
//     * <功能详细描述>
//     * @param businessId businessId
//     * @return List<FileArray>
//     * @see
//     * @since 1.0
//     */
//    List<FileDto> selectFileByBusinessId(@Param("businessId")String businessId);
//    
//    /**
//     * 
//     * 删除上传文件
//     * <功能详细描述>
//     * @param businessId businessId
//     * @return int
//     * @see
//     * @since 1.0
//     */
//    int deleteFileByBusinessId(@Param("businessId")String businessId);
}
