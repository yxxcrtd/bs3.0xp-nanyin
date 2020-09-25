/*
 * 文件名：RskAuthorityManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.rskauthoritymanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.rskauthoritymanage.dto.RskAuthorityManageDto;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年11月30日
 * @see RskAuthorityManageMapper
 * @since 1.0
 */
@Repository
public interface RskAuthorityManageMapper {

    /**
     * 用户下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getUser(Map<String, Object> param);

    /**
     * 页面查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<RskAuthorityManageDto> select(Map<String, Object> param);

    /**
     * 查询页面
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    RskAuthorityManageDto selectByDto(RskAuthorityManageDto dto);

    /**
     * 根据用户名查询数据
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    List<RskAuthorityManageDto> getDtoByUserName(Map<String,Object> map);

    /**
     * 根据用户名查询真实的名字
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    String selectRealNameByUserName(String userName);

    /**
     * 在流程中查询数据
     * <功能详细描述>
     * @param parameter
     * @return
     * @see
     * @since 1.0
     */
    List<RskAuthorityManageDto> getBusinessData(WflValidInputParameter parameter);

    /**
     * 检查是否有流程中的资产交易数据
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    List<String> selectAssetTrade(Map<String,Object> map);

	List<SelectOptionDto> getExistUser(Map<String, Object> param);
	/**
	 * 根据画面选择的用户查询数据库
	 * @param userName
	 * @return
	 */
	List<RskAuthorityManageDto> selectByUserName(String userName);

}
