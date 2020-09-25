/*
 * 文件名：ValVoucherQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.voucherquery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery002Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.service.ValVoucherQuery002Service;

/**
 * 模板示例<br>
 * 凭证详情查看
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery002Controller
 * @since 2.0
 */
@RestController(value="serviceValVoucherQuery002Controller")
@RequestMapping("ValVoucherQuery002")
public class ValVoucherQuery002Controller extends BaseController {

    /**
     * ValVoucherQuery002Service
     */
    @Autowired
    private ValVoucherQuery002Service valAccountVoucher002Service;

//    /**
//     * 重写init方法，加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String busVouchnum = this.getParam("busVouchnum");
//        model.addAttribute("busVouchnum", busVouchnum);
//        String vouchNum = this.getParam("vouchNum");
//        model.addAttribute("vouchNum", vouchNum);
//        String tableId = this.getParam("tableId");
//        model.addAttribute("tableId", tableId);
//        String booksetId = this.getParam("booksetId");
//        model.addAttribute("booksetId", booksetId);
//        String bookDate = this.getParam("bookDate");
//        model.addAttribute("bookDate", bookDate);
//        String subjectNo = this.getParam("subjectNo");
//        model.addAttribute("subjectNo", subjectNo);
//        
//        model.addAttribute("booksetName", this.getParam("booksetName"));
//        model.addAttribute("booksetName", this.getParam("booksetName"));
//        model.addAttribute("offsetFlagFmt", this.getParam("offsetFlagFmt"));
//        model.addAttribute("tableIdFmt", this.getParam("tableIdFmt"));
//        model.addAttribute("vouchRemark", this.getParam("vouchRemark"));
//        
//        return super.init(model);
//    }

    /**
     * 根据凭证编号bookId获取对应相同编号的所有数据
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValVoucherQuery002Dto> searchList(@RequestBody Map<String , Object> params) {
        // 业务凭证编号
        String busVouchnum = (String)params.get("busVouchnum");
        // 凭证编号
        String vouchNum = (String)params.get("vouchNum");
        if (!StringUtil.isEmpty(vouchNum) && !vouchNum.equals(busVouchnum)) {
        	busVouchnum = valAccountVoucher002Service.selectBusVouchNumByVouchNum(vouchNum);
        }
//        String tableId = this.getParam("tableId");
        String booksetId = (String)params.get("booksetId");
//        String bookDate = this.getParam("bookDate");
//        String subjectNo = this.getParam("subjectNo");
        String bookDate = null;
        String subjectNo = null;
        String tableId = null;
        return valAccountVoucher002Service.searchList(busVouchnum,tableId,booksetId,bookDate,subjectNo);
    }

}
