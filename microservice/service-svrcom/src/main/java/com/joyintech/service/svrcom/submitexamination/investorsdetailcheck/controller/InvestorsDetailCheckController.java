package com.joyintech.service.svrcom.submitexamination.investorsdetailcheck.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.submitexamination.investorsdetailcheck.dto.InvestorsDetailCheck001Dto;
import com.joyintech.tams.report.submitexamination.investorsdetailcheck.service.InvestorsDetailCheck001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 投资者明细核查服务
 */
@RestController
@RequestMapping("/InvestorsDetailCheck")
public class InvestorsDetailCheckController extends BaseController{

    /**
     * 投资者明细核查service
     */
    @Autowired
    private InvestorsDetailCheck001Service investorsDetailCheck001Service;

    /**
     * 查询列表
     *
     * @return Page
     */
    @PostMapping("/searchTable/{page}/{length}")
    public Page<InvestorsDetailCheck001Dto> searchTable(@RequestBody Map<String, Object> param){
        return investorsDetailCheck001Service.searchTable(param);
    }

}
