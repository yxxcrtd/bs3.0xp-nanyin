/*
 * 文件名：RepFinProductBalanceController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.balance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.report.balance.dto.RepFinProductBalanceDto;
import com.joyintech.tams.invest.report.balance.service.RepFinProductBalanceService;

/**
 *  产品余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月25日
 * @see RepFinProductBalanceController
 * @since 1.0
 */
@Controller
@PageId("RepFinProductBalance")
@RequestMapping("/invest/report/balance/RepFinProductBalance")
public class RepFinProductBalanceController extends BaseController{

   
    @Autowired
    private RepFinProductBalanceService repFinProductBalanceService;
    
    @Autowired
    private IPlatformDate iPlatformDate;
    
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.销售部门,DictCode.处理模式};
    
    @Override
    public String init(Model model){
        //初始化基本信息画面数据字典
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("iPlatformDate", iPlatformDate.getSysDate());
        return super.init(model);
    }
    
    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepFinProductBalanceDto> selectAll()
        throws Exception {
        Page<RepFinProductBalanceDto> page = repFinProductBalanceService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return repFinProductBalanceService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 组合联动资产  联想下拉框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodIdByPortfolioId")
    public List<SelectOptionDto> getFinprodIdByPortfolioId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repFinProductBalanceService.getFinprodIdByPortfolioId(map);
    }
    
}
