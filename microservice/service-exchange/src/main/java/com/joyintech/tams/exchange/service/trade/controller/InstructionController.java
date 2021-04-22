package com.joyintech.tams.exchange.service.trade.controller;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.InstructionOpBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.investment.EntrustOpeBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.service.trade.management.service.InvestmentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/service/joyin")
public class InstructionController {

    private Logger logger = LoggerFactory.getLogger(InstructionController.class);

//    @Autowired
//    private JixuRestTemplate jixuRestTemplate;


    @Autowired
    private InvestmentManagement investmentManagement;

    /**
     * 查询指令列表
     *
     * @param pageParam 用户Id
     * @return ResultBean
     */
    @RequestMapping(value = "/instruction/users", method = RequestMethod.POST)
    public ResultBean queryAllInstruction(@RequestBody Map<String, Object> pageParam) {
        return new ResultBean(ErrorCode.SUCCESS, investmentManagement.queryInstructionsFromCache(pageParam));
    }

    /***
     * @Description: 指令接收|放弃接口
     * @Param: [entrustOpeList]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/4/28
     */
    @RequestMapping(value = "/instruction/confirm", method = RequestMethod.POST)
    @CrossOrigin
    public ResultBean acceptOrRefuse(@RequestBody List<InstructionOpBean> instructionOpBeans) {
        List<EntrustOpeBean> entrustList = new ArrayList<>();
        if (Objects.isNull(instructionOpBeans) || instructionOpBeans.size() == 0) {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR.value, "参数不可以为空");
        }
        for (InstructionOpBean instructionOpBean : instructionOpBeans) {
            if (Strings.isNullOrEmpty(instructionOpBean.getInstructionId())) {
                return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR.value, "指令ID不可以为空");
            }
            if (Strings.isNullOrEmpty(instructionOpBean.getRecStatus())) {
                return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR.value, "接收状态不可以为空");

            }
            if (Strings.isNullOrEmpty(instructionOpBean.getTrader())) {
                return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR.value, "交易员不可以为空");
            }
            EntrustOpeBean entrustOpeBean = new EntrustOpeBean();
            entrustOpeBean.setEntrustId(instructionOpBean.getInstructionId());
            entrustOpeBean.setRecStatus(instructionOpBean.getRecStatus());
            entrustOpeBean.setTrader(instructionOpBean.getTrader());
            entrustList.add(entrustOpeBean);
        }
        return investmentManagement.acceptOrRefuse(entrustList);
    }

    /**
     * 从投管重新刷入指令数据
     *
     * @param userId 用户Id
     * @return resultBean
     */
    @RequestMapping(value = "/instruction/flush", method = RequestMethod.POST)
    public ResultBean flushInstructionFromInvest(@RequestParam(value = "user_id") String userId) {
        return investmentManagement.flushInstructionFromInvest();
    }
}