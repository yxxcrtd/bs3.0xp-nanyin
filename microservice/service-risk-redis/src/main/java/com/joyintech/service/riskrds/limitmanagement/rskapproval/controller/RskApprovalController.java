package com.joyintech.service.riskrds.limitmanagement.rskapproval.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.rskapproval.dto.RskIsupTrialDto;
import com.joyintech.service.riskrds.limitmanagement.rskapproval.service.RskApprovalPageService;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;

/**
 * 风险审批
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月14日
 * @see RskApprovalController
 * @since 1.0
 */
@RestController("RskApprovalController_dtx")
@RequestMapping("/RskApproval")
public class RskApprovalController {

    /**
     * 接口日志管理Service
     */
    @Autowired
    private RskApprovalPageService approvalService;

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskIsupTrialDto> selectPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                         @PathVariable("length") int length) {
        return approvalService.selectForPage(page, length, param);
    }

    /**
     * 取得试算结论
     * 
     * @param eventId 业务编号
     * @return 试算结论
     * @see
     * @since 1.0
     */
    @GetMapping("selectConclusion/{eventId}")
    public List<RskIsupTrial> selectConclusion(@PathVariable("eventId") String eventId) {
        return approvalService.selectInfo(eventId);
    }

    /**
     * 审批预占信息
     * 
     * @param dto 预占信息
     * @return 结果
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/approvalPreOcp")
    public int approvalPreOcp(@RequestBody RskPreOccupyInfo dto) {
        return approvalService.approvalPreOcp(dto);
    }

    /**
     * 审批预占信息
     * 
     * @param dto 预占信息
     * @return 结果
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/approvalOcp")
    public int approvalOcp(@RequestBody RskOccupyInfo dto) {
        return approvalService.approvalOcp(dto);
    }

    /**
     * 审批预占信息
     * 
     * @param dto 预占信息
     * @return 结果
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/approvalDimEtr")
    public int approvalDimEtr(@RequestBody RskDimEntrustInfo dto) {
        return approvalService.approvalDimEtr(dto);
    }
}
