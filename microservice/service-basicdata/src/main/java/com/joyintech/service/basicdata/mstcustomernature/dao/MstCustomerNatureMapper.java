/*
 * 文件名：MstCustomerNatureMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstcustomernature.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstcustomernature.dto.MstCustomerNatureDto;

/**
 * 客户性质
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huwei
 * @version 1.0 2018年8月16日
 * @see MstCustomerNatureMapper
 * @since 1.0
 */
@Repository
public interface MstCustomerNatureMapper {

    /**
     * 客服性质管理查询按钮的查询方法
     * 
     * @param param map
     * @return 用户信息
     */
    List<MstCustomerNatureDto> selectCustomerInfo(Map<String, Object> param);

    /**
     * 客户名称的联想文本框的查询方法 
     * 
     * @param param map
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getCustomerName(Map<String, Object> param);
    /**
     * 客户代码的联想文本框的查询方法 
     * 
     * @param param map
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getCustomerId(Map<String, Object> param);
    /**
     * 客户名称的联想文本框的查询方法 
     * 
     * @param param map
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getName(Map<String, Object> param);

    /**
     * 客服性质配置查询按钮的查询方法
     * 
     * @param param map
     * @return 用户信息
     */
    List<MstCustomerNatureDto> searchMstCustomerNature(Map<String, Object> param);
    
    /**
     * 通过主键修改方法
     * @param record MstCustomerNatureDto
     * @return int
     */
    int updateByPrimaryKey(MstCustomerNatureDto record);

    /**
     * 通过主键修改客户性质方法
     * @param record MstCustomerNatureDto
     * @return int
     */
    int updateCustomerCharaterByPrimaryKey(MstCustomerNatureDto record);

    /**
     * 通过主键删除客户性质方法
     * 
     * @param record MstCustomerNatureDto
     * @return int
     */
    int deleteCustomerCharaterByPrimaryKey(MstCustomerNatureDto record);

    /**
     * 通过主键查询方法 <一句话功能简述> <功能详细描述>
     * 
     * @param param map
     * @return MstCustomerNatureDto
     * @see
     * @since 1.0
     */
    MstCustomerNatureDto selectByPrimaryKey(Map<String, Object> param);
    
    String getIssuer(@Param("linkId1") String linkId1);

	String checkIsFinLicenceStr(String customerId);

	int checkwflstatus(Map<String, Object> param);
}
