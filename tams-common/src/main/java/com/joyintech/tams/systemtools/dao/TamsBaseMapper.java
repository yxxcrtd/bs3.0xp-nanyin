package com.joyintech.tams.systemtools.dao;


import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.dto.SystemActorRoleDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



/**
 * 基础共通mapper
 *
 * @author Administrator
 * @version 2016年10月22日
 * @see TamsBaseMapper
 * @since
 */
@Repository
public interface TamsBaseMapper {
    /**
     * 根据採番名採番
     *
     * @param param 採番参数
     * @return 採番结果
     * @see
     */
    Long getSequenceIndexData(Map<String, String> param);

    /**
     * 根据机构代码查询登记银行编码
     * <功能详细描述>
     * @return 用户的登记银行编码
     * @param orgCode 机构代码
     * @see
     * @since 1.0
     */
    String selectBankCode(String orgCode);


    List<ChbOrgRegistBankCode> selectBank(String orgCode);
    
    /**
     * 查询机构下的所有部门代码
     * @param institutionCode 机构代码
     * @return 部门代码列表
     */
    List<String> selectDeptCode(@Param("institutionCode") String institutionCode);

    List<SystemActorRoleDto> selectDataModuleByUserName(@Param("userName") String userName);

    String selectSysDatadictValue(@Param("dictCode") String dictCode, @Param("itemCode") String itemCode);

    List<SysDataDict> selectSysDatadictValueList(String dictCode);
    
    String selectSysDatadictItemCode(@Param("dictCode") String dictCode, @Param("itemValue") String itemValue);
    
    String selectSysParamValue(@Param("systemCode") String systemCode, @Param("paramCode") String paramCode);

    List<SysDataDict> queryFirstIndustryBySecondIndustry();
    
    
	/**
	 * 根据机构代码获取机构类型
	 *
	 * @param institutionCode
	 * @return
	 */
	String selectInsitutionType(String institutionCode);


    String getInstitutionType(@Param("institutionCode") String institutionCode);
    
    
    /**
     * 通过用户名获取对应的机构类型
     * @param userName
     * @return
     */
    String getInsitutionType_ms(@Param("userName") String userName);
    
    /**
     * 通过用户名获取对应的机构代码
     * @param userName
     * @return
     */
    String getInsitutionCode_ms(@Param("userName") String userName);
    
    
    /**
     * 获取所有上级机构
     * @param institutionCode
     * @return
     */
    List<String> getParentInstitution(@Param("institutionCode") String institutionCode);

    String getYHGeneralBankCode();

    String getCreateInstByID(@Param("businessId") String businessId,@Param("dataType") String dataType);

    List<ChbOrgRegistBankCode> selectBank(String orgCode);
}