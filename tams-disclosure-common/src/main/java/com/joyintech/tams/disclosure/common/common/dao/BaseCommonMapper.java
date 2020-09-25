package com.joyintech.tams.disclosure.common.common.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 共通Mapper
 *
 * @author 陈俊安
 * @version 1.0 2016年11月14日
 * @see BaseCommonMapper
 * @since 1.0
 */
@Repository(value="disBaseCommonMapper")
public interface BaseCommonMapper {

    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     * @see
     * @since 1.0
     */
    Date getNowSysDate();

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param batchId 批处理id
     * @see
     * @since 1.0
     */
    void updateBatchInfoToEnd(List<String> batchId);

    /**
     * 批处理执行时，可能会人为中断<br/>
     * 把库里的原来的数据状态设置成中断<br/>
     *
     * @param brk
     *            现状态
     * @param run
     *            原状态
     * @see
     * @since 1.0
     */
    void updatePreBatchDataToEnd(@Param("brk") String brk, @Param("run") String run);

    /**
     * 批处理执行时，可能会人为中断<br/>
     * 把库里的原来的数据状态设置成中断<br/>
     *
     * @param brk
     *            现状态
     * @param run
     *            原状态
     * @see
     * @since 1.0
     */
    void updatePreBatchLogDataToEnd(@Param("brk") String brk, @Param("run") String run);

//    /**
//     * 修改系统参数表某个key对应的值
//     *
//     * @param subsystemCode subsystemCode
//     * @param moduleCode moduleCode
//     * @param paraCode paraCode
//     * @param statusTo statusTo
//     * @see
//     * @since 1.0
//     */
//    void setSysParam(@Param(value = "subsystemCode") String subsystemCode,
//                     @Param(value = "moduleCode") String moduleCode, @Param(value = "paraCode") String paraCode,
//                     @Param(value = "statusTo") String statusTo);

    /**
     *
     * 获取系统参数
     * <功能详细描述>
     * @param subsystemCode 子系统code
     * @param moduleCode code
     * @param interfKeys 主key
     * @return value
     * @see
     * @since 1.0
     */
    String getSysParamString(@Param(value = "subsystemCode") String subsystemCode,
                             @Param(value = "moduleCode") String moduleCode,
                             @Param(value = "paraCode") String interfKeys);


    /**
     * 查询机构下的所有部门代码
     * @param institutionCode 机构代码
     * @return 部门代码列表
     */
    List<String> selectDeptCode(@Param("institutionCode") String institutionCode);
}
