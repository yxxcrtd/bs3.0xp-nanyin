/*
 * 文件名：TrdInvestPersonInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.trade.TrdInvestPersonInfo002Service;
import com.joyintech.tams.component.business.trade.dto.PreTrdRepoTrade001Dto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 投资交易人员信息controller
 * 〈功能详细描述〉
 * @author 杨智
 * @version 1.0 2018年9月19日
 * @see PreTrdBondTrdInvestPersonInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/PreTrdBondTrdInvestPersonInfo")
public class PreTrdBondTrdInvestPersonInfoController extends BaseController {

    /**
     * trdInvestPersonInfo002Service
     */
    @Autowired
    private TrdInvestPersonInfo002Service trdInvestPersonInfo002Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {

        String entityStr = this.getParam("entity");
        PreTrdRepoTrade001Dto entity = JsonUtils.json2Object(entityStr, PreTrdRepoTrade001Dto.class);
        if(entity == null){
            entity = new PreTrdRepoTrade001Dto();
            entity.setTrader(LoginUserUtil.getUserName());

        }
        if(StringUtils.isEmpty(entity.getTrader())) {
        	entity.setTrader(LoginUserUtil.getUserName());
        }
        HashMap<String, Object> param = new HashMap<>();
//        model.addAttribute("COUNTER_ID_LIST",
//            JsonUtils.object2Json(trdInvestPersonInfo002Service.selectCounterId(param).toArray()));
        model.addAttribute("entity", entity);

        // add by wangguoxian 增加交易对手类型传参 20190713 end
        return "component/trade/PreTrdBondTrdInvestPersonInfo";
    }
    
    /**
     * 
     * 查询对手方交易员
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectCounterId")
    @ResponseBody
    public Page<SelectOptionDto> selectCounterId(){
        Map<String, Object> params = RequestUtil.getPageParam();
        if (StringUtil.isEmpty((String) params.get("innerTrade")) || "null".equals(params.get("innerTrade"))) {
            params.put("innerTrade", null);
        }
        else if (Boolean.FALSE.toString().equals(params.get("innerTrade"))) {
            params.put("innerTrade", false);
        }
        else if (Boolean.TRUE.toString().equals(params.get("innerTrade"))) {
            params.put("innerTrade", true);
        }
        if (StringUtil.isEmpty((String) params.get("counterType")) || "null".equals(params.get("counterType"))) {
            params.put("counterType",null);
        }
        
        return trdInvestPersonInfo002Service.selectCounterId(params);
    }
}
