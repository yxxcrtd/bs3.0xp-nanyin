/*
 * 文件名：HomePageSeachDataMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.home.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.disclosure.home.dto.InfoAreaDto;
import com.joyintech.tams.disclosure.home.dto.RepFtpPathDto;
import com.joyintech.tams.disclosure.home.dto.SubmitInformationRegistrationDto;


/**
 * 主页查询各种数据所用的Mapper
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年9月19日
 * @see DisclosureHomeMapper
 * @since 1.0
 */
@Repository
public interface DisclosureHomeMapper {

    /**
     * 根据需求参数查询报送信息登记
     * <功能详细描述>
     * @param parmMap 存储了所需的参数
     * @return 返回存储有数据的List
     * @see
     * @since 1.0
     */
    List<SubmitInformationRegistrationDto> searchReportInfo(Map<String, Object> parmMap);

    /**
     * <一句话功能简述>
     * 查询信息表区域的数据
     * @param parmMap 参数Map
     * @return 返回存储有数据的List
     * @see
     * @since 1.0
     */
    List<InfoAreaDto> searchInfoArea(Map<String, Object> parmMap);

    /**
     * <一句话功能简述>
     * 查询可批量导出模板区域数据
     * @return 返回存储有数据的List
     * @see
     * @since 1.0
     */
    List<InfoAreaDto> searcheBatchxportTemplates(Map<String, Object> parmMap);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<RepFtpPath> selectAllpath();

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<RepFtpPathDto> queryYpl(Map<String, Object> parmMap);

    /**
     * 查询待披露
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<RepFtpPathDto> queryDpl(Map<String, Object> parmMap);

    /**
     * 查询预计披露
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<RepFtpPathDto> queryYjpl(Map<String, Object> parmMap);

    /**
     * 查询用户菜单权限
     * 
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    List<String> queryRolePageId(@Param("userName") String userName);

    /**
     * 披露生成失败
     * @param parmMap
     * @return
     */
	List<RepFtpPathDto> queryYplFail(Map<String, Object> parmMap);

}
