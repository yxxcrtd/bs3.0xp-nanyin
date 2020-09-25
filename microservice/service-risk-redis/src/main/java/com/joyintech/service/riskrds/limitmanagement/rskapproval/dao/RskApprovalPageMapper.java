package com.joyintech.service.riskrds.limitmanagement.rskapproval.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.riskrds.limitmanagement.rskapproval.dto.RskIsupTrialDto;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;

/**
 * 风险审批
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月9日
 * @see RskApprovalPageMapper
 * @since 1.0
 */
@Repository
public interface RskApprovalPageMapper {

    /**
     * 查询指标计算结果
     * 
     * @param pageParam 查询条件
     * @return 指标计算结果列表
     * @see
     * @since 1.0
     */
    List<RskIsupTrialDto> selectIsupTrial(Map<String, Object> param);

    /**
     * 查询试算结论
     * 
     * @param eventId 业务编号
     * @return 试算结论
     * @see
     * @since 1.0
     */
    List<RskIsupTrial> selectIsupTrialCls(@Param("eventId") String eventId);

    /**
     * 更新预占信息审批状态
     * 
     * @param info 预占信息
     * @return 结果
     * @see
     * @since 1.0
     */
    int updateRskPreOcp(RskPreOccupyInfo info);

    /**
     * 更新实占信息审批状态
     * 
     * @param info 实占信息
     * @return 结果
     * @see
     * @since 1.0
     */
    int updateRskOcp(RskOccupyInfo info);

    /**
     * 更新模糊委托审批状态
     * 
     * @param info 模糊委托信息
     * @return 结果
     * @see
     * @since 1.0
     */
    int updateRskDimEtr(RskDimEntrustInfo info);
}
