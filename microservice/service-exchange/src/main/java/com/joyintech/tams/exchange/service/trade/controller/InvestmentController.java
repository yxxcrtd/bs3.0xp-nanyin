package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.InstructionPage;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.investment.BusinessDepartmentBean;
import com.joyintech.tams.exchange.common.bean.investment.CapitalAccountBean;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.InstructionPledge;
import com.joyintech.tams.exchange.service.trade.management.service.InvestmentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/joyin")
public class InvestmentController {

    @Autowired
    private InvestmentManagement investmentManagement;


    /**
     * 处理审核指令
     *
     * @param orderId            订单号
     * @param instructionsStatus 指令状态
     * @return ResultBean
     */
    @RequestMapping(value = "/instruction/examine", method = RequestMethod.POST)
    public ResultBean handleExamine(@RequestParam(value = "eventId") String orderId,
                                    @RequestParam(value = "approveResult") String instructionsStatus,
                                    @RequestParam(value = "approveReason", required = false) String approveReason) {
        return investmentManagement.handleExamine(orderId, instructionsStatus, approveReason);
    }

    /**
     * 处理指令变更
     *
     * @param instructionId 订单号
     * @param status        状态
     * @return ResultBean
     */
    @RequestMapping(value = "/instructionChange", method = RequestMethod.POST)
    public ResultBean instructionChange(@RequestParam(value = "entrustId") String instructionId,
                                        @RequestParam(value = "entrustStatus") String status) {
        return investmentManagement.instructionChange(instructionId, status);
    }

    /**
     * 查询账户资金
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/queryCapitalAccount", method = RequestMethod.GET)
    public List<CapitalAccountBean> queryCapitalAccount(@RequestParam(value = "portfolioId", defaultValue = "") String portfolioId,
                                                        @RequestParam(value = "acctNo", defaultValue = "") String acctNo,
                                                        @RequestParam(value = "busiAcctNo", defaultValue = "") String busiAcctNo,
                                                        @RequestParam(value = "acctType", defaultValue = "") String acctType) {
        return investmentManagement.queryCapitalAccount(portfolioId, acctNo, busiAcctNo, acctType);
    }

    /**
     * 查询营业部
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/queryBusinessDepartment", method = RequestMethod.GET)
    public List<BusinessDepartmentBean> queryBusinessDepartment(@RequestParam(value = "stockExchangeNo", defaultValue = "") String stockExchangeNo) {
        return investmentManagement.queryBusinessDepartment(stockExchangeNo);
    }

    /**
     * 指令撤销
     *
     * @param instructionId     指令id
     * @param instructionStatus 指令状态
     * @return
     */
    @RequestMapping(value = "/instruction/cancel", method = RequestMethod.POST)
    public ResultBean cancelInstruction(@RequestParam(value = "instruction_Id") String instructionId,
                                        @RequestParam(value = "entrustStatus", required = false) String instructionStatus) {
        return investmentManagement.cancelInstruction(instructionId, instructionStatus);
    }


    /**
     * 指令分发
     *
     * @param instructionPage 指令及质押券列表
     * @return
     */
    @RequestMapping(value = "/instruction/distribute", method = RequestMethod.POST)
    public ResultBean appointInstruction(@RequestBody InstructionPage<Instruction, InstructionPledge> instructionPage) throws Exception {
        return investmentManagement.appointInstruction(instructionPage);
    }
}
