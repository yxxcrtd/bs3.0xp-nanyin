package com.joyintech.service.valuation.trademanage.accountcurrentrate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate001Dto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.service.AccountCurrentRate001Service;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.service.AccountCurrentRateService;

/**
 * 账户活期利率查询-controller
 * 
 * @author huwei
 * @version 1.0 2018年10月11日
 * @see AccountCurrentRate001Controller
 * @since 1.0
 */
@RestController(value="serviceAccountCurrentRate001Controller")
@RequestMapping("/AccountCurrentRate001")
public class AccountCurrentRate001Controller extends BaseController {

    /**
     * accountCurrentRate001Service
     */
    @Autowired
    private AccountCurrentRate001Service accountCurrentRate001Service;

    /**
     * accountCurrentRateService
     */
    @Autowired
    private AccountCurrentRateService accountCurrentRateService;

    /**
     * 查询按钮的查询方法
     * 
     * @return Page<AccountCurrentRate001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<AccountCurrentRate001Dto> findPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return accountCurrentRate001Service.queryAccountCurrentRateList(page, length, param);
    }

    /**
     * 下拉框获取：账户
     * 
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getAccount")
    public List<SuggestDto> getAccount(@RequestBody Map<String, Object> param)
        throws Exception {
        return accountCurrentRate001Service.getAccount(param);
    }

    /**
     * 根据条件查询所有
     * 
     * @param params Map
     * @return List<AccountCurrentRate001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findAll")
    public List<AccountCurrentRate001Dto> findAll(@RequestBody Map<String, Object> params)
        throws Exception {
        return accountCurrentRate001Service.findAll(params);
    }

}
