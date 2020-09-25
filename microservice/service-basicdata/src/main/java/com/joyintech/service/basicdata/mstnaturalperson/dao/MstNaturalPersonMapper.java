/*
 * 文件名：MstNaturalPerson001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstnaturalperson.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年8月14日
 * @see MstNaturalPersonMapper
 * @since 1.0
 */
@Repository
public interface MstNaturalPersonMapper {

    /**
     * 查询 <功能详细描述>
     * 
     * @param param 查询参数
     * @return 查询返回数据
     * @see
     * @since 1.0
     */
    List<MstNaturalPerson001Dto> selectForPage(Map<String, Object> param);

    /**
     * 客户名联想下拉框 <一句话功能简述> <功能详细描述>
     * 
     * @param map 下拉框查询参数
     * @return 查询返回数据
     * @see
     * @since 1.0
     */
    List<SuggestDto> getcName(Map<String, String> map);

    /**
     * 客户身份证联想下拉框 <一句话功能简述> <功能详细描述>
     * 
     * @return 下拉框查询返回参数
     * @see
     * @since 1.0
     */
    List<SuggestDto> getcID(Map<String, String> map);

    /**
     * 删除 <功能详细描述>
     * 
     * @param dto 删除参数
     * @return 删除返回参数
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(MstNaturalPerson001Dto dto);

    /**
     * 删除时检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto 删除检查参数
     * @return 删除检查返回参数
     * @see
     * @since 1.0
     */
    int checkCodeIsUse(MstNaturalPerson001Dto dto);

    /**
     * 批量删除时检查数据是否被使用过 <功能详细描述>
     * 
     * @param item 删除检验参数
     * @return 检验返回结果
     * @see
     * @since 1.0
     */
    int checkAllCodeIsUse(List<MstNaturalPerson001Dto> item);

    /**
     * 双击查看详细信息查询，修改信息查询 <功能详细描述>
     * 
     * @param customerId 选中信息ID
     * @return 选中信息详细数据
     * @see
     * @since 1.0
     */
    MstNaturalPerson001Dto selectDetail(String customerId);

    /**
     * 新建检验数据是否存在 <功能详细描述>
     * 
     * @param dto 新建信息
     * @return 检验结果
     * @see
     * @since 1.0
     */
    int selectByPersonId(MstNaturalPerson001Dto dto);

    /**
     * 查询检验数据是否存在 <功能详细描述>
     * 
     * @param dto 查询信息
     * @return 检验结果
     * @see
     * @since 1.0
     */
    int selectByPidandCid(MstNaturalPerson001Dto dto);

    /**
     * 新建信息加入客户信息表 <功能详细描述>
     * 
     * @param dto 新建信息
     * @return 新建结果
     * @see
     * @since 1.0
     */
    int insertCustomerInfo(MstNaturalPerson001Dto dto);
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 根据主键修改客户信息表
     * @return 返回成功 1/失败 0
     * @see
     * @since 1.0
     */
    int updateByPrimaryKeySelective(MstNaturalPerson001Dto dto);
}
