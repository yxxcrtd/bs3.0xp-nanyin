package com.joyintech.tams.common.base.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



/**
 * 基础共通mapper
 *
 * @author Administrator
 * @version 2016年10月22日
 * @see BaseMapper
 * @since
 */
@Repository
public interface BaseMapper {
    /**
     * 根据採番名採番
     *
     * @param param 採番参数
     * @return 採番结果
     * @see
     */
    Long getSequenceIndexData(Map<String, String> param);

//    /**
//     * 根据机构代码查询所有子机构代码，包括当前机构本身
//     * @param orgCode 机构代码
//     * @return 机构代码列表
//     */
//    List<String> selectOrgCode(@Param("orgCode") String orgCode);
    /**
     * 根据机构代码查询登记银行编码
     * <功能详细描述>
     * @return 用户的登记银行编码
     * @param orgCode 机构代码
     * @see
     * @since 1.0
     */
    String selectBankCode(String orgCode);

//    /**
//     * 根据机构代码查找其名称
//     *
//     * @param myOrgCode 机构代码
//     * @return 名称
//     * @see
//     * @since 1.0
//     */
//    List<SelectOptionDto> getSelfItem(@Param("myOrgCode") String myOrgCode);

//    /**
//     * 查询其子类机构
//     * @param myOrgCode 机构代码
//     * @return 存储了子类机构的List
//     * @see
//     * @since 1.0
//     */
//    List<SelectOptionDto> getChildCodeList(@Param("myOrgCode") String myOrgCode);
//    /**
//     * 根据机构代码查询所有子机构代码，包括当前机构本身
//     * @param orgCode 机构代码
//     * @return 机构代码列表
//     */
//    List<String> selectDeptCode(@Param("deptCode") String deptCode);

    /**
     * oracle通过表明获取表结构
     * @param tableName 表明
     * @return 表结构list
     * @see
     * @since 1.0
     */
    List<String> getTableColumnList(@Param("tableName") String tableName);
    
    /**
     * 功能:根据表明清空表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月7日
     * @return 
     * int
     */
    int deleteAllData(@Param("tableName") String tableName);
}