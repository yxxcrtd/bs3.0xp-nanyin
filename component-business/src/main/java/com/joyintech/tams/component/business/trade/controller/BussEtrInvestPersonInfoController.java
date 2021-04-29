/*
 * 文件名：EtrInvestPersonInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.framework.grid.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.department.dto.SysDepartmentDto;
import com.joyintech.tams.component.business.trade.BussEtrInvestPersonInfoService;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 投资委托人员信息controller
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月19日
 * @see BussEtrInvestPersonInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussEtrInvestPersonInfo")
public class BussEtrInvestPersonInfoController extends BaseController {

    @Autowired(required=false)
    private BussEtrInvestPersonInfoService bussEtrInvestPersonInfoService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String single = this.getParam("single");
        if(StringUtil.isNotEmpty(single)){
            model.addAttribute("single",single);
        }
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussEtrInvestPersonInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("psId", RequestUtil.getParam("psId"));
        model.addAttribute("callPageId", RequestUtil.getParam("callPageId"));
        model.addAttribute("IS_AUTO_GENERATE_NEGATE_DEAL", CacheUtil.getSysParam(BussinessConstant.IS_AUTO_GENERATE_NEGATE_DEAL));
        BussEtrInvestPersonInfoDto entity = JsonUtils.json2Object(this.getParam("entity"), BussEtrInvestPersonInfoDto.class);
        Map<String, SysDepartmentDto> deptMap = CacheUtil.getAppCache(GlobalConstant.SYS_DEPARTMENT_KEY, Map.class);
        if(entity == null){
            entity = new BussEtrInvestPersonInfoDto();
            entity.setRealName(SecurityUtil.getLoginUser().getRealName());
            entity.setInvManagerId(SecurityUtil.getLoginUser().getUserName());
            SysDepartmentDto deptDto = deptMap.get(LoginUserUtil.getDeptCode());
            if (deptDto != null) {
                entity.setRoom(deptDto.getDeptName());
            }
        }else{
            Map<String, Object> map = bussEtrInvestPersonInfoService.getUser(entity.getInvManagerId());
            SysDepartmentDto deptDto = deptMap.get((String)map.get("DEPTCODE"));
            if (deptDto != null) {
                entity.setRoom(deptDto.getDeptName());
            }
            entity.setRealName((String)map.get("REALNAME"));
        }
        
        //获取交易对手下拉框
        HashMap<String, Object> param = new HashMap<>();
//        model.addAttribute("COUNTER_ID_LIST",
//            JsonUtils.object2Json(bussEtrInvestPersonInfoService.getCounterId(param).toArray()));
        model.addAttribute("entity", entity);
        model.addAttribute("innerTrade", getParam("innerTrade"));
        //为了限制债券一级委托新建交易对手类型为市场机构
        model.addAttribute("counterType",getParam("counterType"));
        return "component/trade/BussEtrInvestPersonInfo";
    }
    
    /**
     * 
     * 本方交易员下拉框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getTrader")
    public Page<SelectOptionDto> getTrader(){
    	Map<String, Object> params = RequestUtil.getPageParam();
    	if (StringUtil.isNotEmpty(params.get("callPageId"))
				&& (StringUtil.equals("EtrInvestBondInfo002", params.get("callPageId").toString()) 
						&& !StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), params.get("ps"))
						&& StringUtil.equals(DictCodeListExt1.交易场所_银行间.getValue(), params.get("tradeMarket"))
						&& !StringUtil.isNotEmpty(params.get("chlId")))) {
			return bussEtrInvestPersonInfoService.getCfetsTrader(params);
		}
        return bussEtrInvestPersonInfoService.getTrader(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 交易对手
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCounterId")
    public Page<SelectOptionDto> getCounterId(){
        Map<String, Object> params = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(params.get("callPageId"))
				&& (StringUtil.equals("EtrInvestBondInfo002", params.get("callPageId").toString()) 
						&& !StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), params.get("ps"))
						&& StringUtil.equals(DictCodeListExt1.交易场所_银行间.getValue(), params.get("tradeMarket"))
						&& !StringUtil.isNotEmpty(params.get("chlId")))) {
			//return bussEtrInvestPersonInfoService.getCfetsCounterId(params);
			return bussEtrInvestPersonInfoService.getCounterIdCfets(params);
		}
        if (StringUtil.isEmpty((String) params.get("innerTrade")) || "null".equals(params.get("innerTrade"))) {
            params.put("innerTrade", null);
        }
        else if (Boolean.FALSE.toString().equals(params.get("innerTrade"))) {
            params.put("innerTrade", false);
        }
        else if (Boolean.TRUE.toString().equals(params.get("innerTrade"))) {
            params.put("innerTrade", true);
        }
        return bussEtrInvestPersonInfoService.getCounterId(params);
    }
}
