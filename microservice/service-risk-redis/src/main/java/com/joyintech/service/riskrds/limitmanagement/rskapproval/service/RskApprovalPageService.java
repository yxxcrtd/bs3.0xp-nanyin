package com.joyintech.service.riskrds.limitmanagement.rskapproval.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.rskapproval.dao.RskApprovalPageMapper;
import com.joyintech.service.riskrds.limitmanagement.rskapproval.dto.RskIsupTrialDto;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

/**
 * 风险审批审批
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月14日
 * @see RskApprovalPageService
 * @since 1.0
 */
@Service("RskApprovalService")
public class RskApprovalPageService extends BaseService<RskIsupTrialDto> {

    /**
     * 委托审批mapper
     */
    @Autowired
    private RskApprovalPageMapper rskApprovalMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param param 查询参数
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<RskIsupTrialDto> selectForPage(int page, int length, Map<String, Object> param) {
        return super.queryForPage(page, length, () -> rskApprovalMapper.selectIsupTrial(param));
    }

    /**
     * 取得试算结论
     * 
     * @param eventId 业务编号
     * @return 试算结论
     * @see
     * @since 1.0
     */
    public List<RskIsupTrial> selectInfo(String eventId) {
        return rskApprovalMapper.selectIsupTrialCls(eventId);
    }

    /**
     * 预占审批
     * 
     * @param dto 预占信息
     * @return 结果
     * @see
     * @since 1.0
     */
    public int approvalPreOcp(RskPreOccupyInfo dto) {
        dto.setWflStatus(BussinessDictCodeListRds.有效状态_有效.getValue());
        return rskApprovalMapper.updateRskPreOcp(dto);
    }

    /**
     * 实占审批
     * 
     * @param info 实占信息
     * @return 结果
     * @see
     * @since 1.0
     */
    public int approvalOcp(RskOccupyInfo info) {
        info.setWflStatus(BussinessDictCodeListRds.有效状态_有效.getValue());
        return rskApprovalMapper.updateRskOcp(info);
    }

    /**
     * 模糊委托审批
     * 
     * @param info 模糊委托信息
     * @return 结果
     * @see
     * @since 1.0
     */
    public int approvalDimEtr(RskDimEntrustInfo info) {
        info.setWflStatus(BussinessDictCodeListRds.有效状态_有效.getValue());
        return rskApprovalMapper.updateRskDimEtr(info);
    }
}
