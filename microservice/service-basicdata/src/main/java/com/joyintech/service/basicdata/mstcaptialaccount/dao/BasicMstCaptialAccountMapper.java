/*
 * 文件名：MstCaptialAccountMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstcaptialaccount.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstcaptialaccount.dto.MstCaptialAccountDto;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see BasicMstCaptialAccountMapper
 * @since 1.0
 */
@Repository
public interface BasicMstCaptialAccountMapper {
    /**
     * 
     * 模糊查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return List<MstCaptialAccountDto>
     * @see
     * @since 1.0
     */
    List<MstCaptialAccountDto> select(Map<String, Object> pageParam);
    List<MstCaptialAccountDto> selectCheck(Map<String, Object> pageParam);
    
    /**
     * 联想下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAcctName(Map<String, Object> map);
    
    /**
     * 
     * 删除
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(MstCaptialAccountDto dto);
    
    /**
     * 
     * 新建
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int insert(MstCaptialAccountDto dto);
    
    /**
     * 
     * 修改
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int updateByPrimaryKeySelective(MstCaptialAccountDto dto);
    
    /**
     * 
     * 根据网点账号与币种查询详细信息
     * <功能详细描述>
     * @param map map
     * @return MstCaptialAccountDto
     * @see
     * @since 1.0
     */
    MstCaptialAccountDto selectDetail(Map<String, Object> map);
    
    /**
     * 
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int checkCodeIsUse(MstCaptialAccountDto dto);
    
    /**
     * 
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item List<MstCaptialAccountDto>
     * @return List<MstCaptialAccountDto>
     * @see
     * @since 1.0
     */
    List<MstCaptialAccountDto> checkAllCodeIsUse(List<MstCaptialAccountDto> item);

    int updateTitle(Map<String, Object> map);

    int getUniqueAcctNoPayNo(Map<String, Object> map);
    
}
