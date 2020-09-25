/*
 * 文件名：SystemFeedback001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.svrcom.system.debtsubmitinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo001Service;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.system.debtsubmitinfo.dto.DebtSubmitInfo001Dto;
import com.joyintech.tams.report.system.debtsubmitinfo.service.DebtSubmitInfo001Service;

/**
 * 中债系统报送信息反馈服务
 * 〈功能详细描述〉
  * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see DebtSubmitInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/DebtSubmitInfo")
public class DebtSubmitInfoController {
    /**
     * 报送信息反馈Service
     */
    @Autowired
    private DebtSubmitInfo001Service debtSubmitInfo001Service;

    /**
     * 投资者身份信息反馈Service
     */
    @Autowired
    private  EquityInfo001Service equityInfo001Service;
    
    /**
     * 投资者持有信息反馈Service
     */
    @Autowired
    private InvestorsHoldInfo001Service investorsHoldInfo001Service;
    
    /**
    *
    * 取得查询数据
    *
    * @return 存在于查询条件的数据
    */
    @PostMapping("findPage/{page}/{length}")
    public Page<DebtSubmitInfo001Dto> searchInfo(@RequestBody Map<String, Object> param) {
        return debtSubmitInfo001Service.searchInfo(param);
    }
    
    /**
    *
    * 取得查询数据Detail
    *
    * @return 存在于查询条件的数据
    */
    @PostMapping("findPageDetail/{page}/{length}")
    public Page<DebtSubmitInfo001Dto> viewDetailPage(@RequestBody Map<String, Object> param) {
        return debtSubmitInfo001Service.viewDetailPage(param);
    }
    
	/**
	 * 查询持有信息登记中对应的中债反馈状态为中债退回的数据(选中)
	 * @param map
	 * @return
	 */
    @PostMapping("selectInvestHoldInfoFeedBackData")
    public List<InvestorsHoldInfo001Dto> selectInvestHoldInfoFeedBackData(@RequestBody Map<String, Object> param) {
        return investorsHoldInfo001Service.selectInvestHoldInfoFeedBackData(param);
    }
    
	/**
	 * 查询身份信息登记中的中债反馈信息为中债退回的数据(选中)
	 * @param map
	 * @return
	 */
    @PostMapping("selectEquityInfoFeedBackData")
    public List<EquityInfo001Dto> selectEquityInfoFeedBackData(@RequestBody Map<String, Object> param) {
        return equityInfo001Service.selectFeedBackData(param);
    }
    
	/**
	 * 查询持有信息登记中对应的中债反馈状态为中债退回的数据(全部)
	 * @param map
	 * @return
	 */
    @PostMapping("selectAllInvestHoldInfoFeedBackData")
    public List<InvestorsHoldInfo001Dto> selectAllInvestHoldInfoFeedBackData(@RequestBody Map<String, Object> param) {
        return investorsHoldInfo001Service.selectAllInvestHoldInfoFeedBackData(param);
    }
    
	/**
	 * 查询身份信息登记中的中债反馈信息为中债退回的数据(全部)
	 * @param map
	 * @return
	 */
    @PostMapping("selectAllEquityInfoFeedBackData")
    public List<EquityInfo001Dto> selectAllEquityInfoFeedBackData(@RequestBody Map<String, Object> param) {
        return equityInfo001Service.selectAllFeedBackData(param);
    }
    
    
}
