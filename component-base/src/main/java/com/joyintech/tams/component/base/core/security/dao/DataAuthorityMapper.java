/*
 * 文件名：DataAuthorityMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2019年1月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core.security.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 何阳阳
 * @version 1.0 2019年1月31日
 * @see DataAuthorityMapper
 * @since 1.0
 */
@Repository
public interface DataAuthorityMapper {

	String selectConfig(@Param("paraCode") String paraCode);

	List<Map<String, String>> selectDeptCodeByUsername(@Param("username") String username);

	List<String> findAllSubDeptInstitution(@Param("deptCode") String deptCode);
}
