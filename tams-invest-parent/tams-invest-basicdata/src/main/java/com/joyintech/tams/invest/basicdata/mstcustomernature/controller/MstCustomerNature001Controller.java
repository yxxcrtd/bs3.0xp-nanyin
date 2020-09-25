/*
 * 文件名：MstCustomerNature001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.basicdata.mstcustomernature.controller;
import java.util.HashMap;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.basicdata.mstcustomernature.dto.MstCustomerNatureDto;
import com.joyintech.tams.invest.basicdata.mstcustomernature.service.MstCustomerNatureService;
import com.joyintech.tams.constant.DictCode;

/**
 * 客服性质管理 
 *〈一句话功能简述〉 
 *〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年8月16日
 * @see MstCustomerNature001Controller
 * @since 1.0
 */
@Controller
@PageId("MstCustomerNature001")
@RequestMapping("/invest/basicdata/mstcustomernature/MstCustomerNature001")
public class MstCustomerNature001Controller extends BaseController {

    /**
     * 客服性质管理业务类
     */
    @Autowired
    private MstCustomerNatureService mstCustomerNatureService;

    /**
     * 初始化 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.客户性质, DictCode.客户类型);
        return super.init(model);
    }

    /**
     * 客户名称的联想文本框的查询方法 
     * <一句话功能简述> 
     * <功能详细描述>
     * @return list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCustomerName")
    public List<SuggestDto> getCustomerName()
        throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("customerName", super.getRequest().getParameter("param"));
        List<SuggestDto> list = mstCustomerNatureService.getCustomerName(param);;
        return list;
    }

    /**
     * 客服性质管理查询按钮的查询方法
     * @return Page<MstCustomerNatureDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectCustomerInfo")
    public Page<MstCustomerNatureDto> selectCustomerInfo()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        Page<MstCustomerNatureDto> dto = mstCustomerNatureService.selectCustomerInfo(param);
        return dto;
    }

    /**
     * 导出
     * @return ResponseEntity
     * @throws Exception 异常
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        return mstCustomerNatureService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
