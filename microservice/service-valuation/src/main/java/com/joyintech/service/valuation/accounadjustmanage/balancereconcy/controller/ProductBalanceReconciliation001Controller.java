/*
 * 文件名：ProductBalanceReconciliation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.balancereconcy.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.accounadjustmanage.balancereconcy.dto.ProductBalanceReconciliation001Dto;
import com.joyintech.service.valuation.accounadjustmanage.balancereconcy.service.ProductBalanceReconciliation001Service;
import com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto.ConfirmPaDto;
import com.joyintech.service.valuation.accounadjustmanage.checkcommon.service.ExecuteService;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品余额表对账
 * 
 * @author yangjian
 * @version 1.0 2018年11月16日
 * @see ProductBalanceReconciliation001Controller
 * @since 1.0
 */
//@RestController(value="serviceProductBalanceReconciliation001Controller")
@RestController("productBalanceReconciliation001Ctl_dtx")
@RequestMapping("ProductBalanceReconciliation001")
public class ProductBalanceReconciliation001Controller extends BaseController {

    /**
     * ValProductInfoQuery001Service
     */
    @Autowired
    private ValNetValueConfirm001Service valNetValueConfirm001Service;

    /**
     * productValuationReconciliation001Service
     */
    @Autowired
    private ProductBalanceReconciliation001Service productBalanceReconciliation001Service;

    /**
     * 对账处理
     */
    @Autowired
    private ExecuteService executeService;

//    /**
//     * 重写init方法，加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//
//        this.setSelectOptionToModel(model, DictCode.对账状态);
//        this.setSelectOptionToModel(model, DictCode.对账结果);
//
//        return super.init(model);
//    }

    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> map,
                                              @PathVariable("page") int page, @PathVariable("length") int length) {
        return valNetValueConfirm001Service.getFinprodId(page,length,map);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ProductBalanceReconciliation001Dto> findPage(@RequestBody Map<String, Object> map,
                                                             @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {

        // 获取分页数据
        Page<ProductBalanceReconciliation001Dto> pageinfo = productBalanceReconciliation001Service.findPage(page,length,map);

        // 关于路径中，["\"]替换为["/"]；
        if (pageinfo != null && pageinfo.getRoot() != null && !pageinfo.getRoot().isEmpty()) {
            for (ProductBalanceReconciliation001Dto dto : pageinfo.getRoot()) {
                dto.setFilePath2(executeService.replaceStringForPath(dto.getFilePath2()));
                dto.setFilePath3(executeService.replaceStringForPath(dto.getFilePath3()));
            }
        }

        return pageinfo;
    }

    /**
     * 余额表对账
     * 
     * @param paDto
     * @param request
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "/doConfirm")
    public AjaxResult doConfirm(@RequestBody List<ConfirmPaDto> paDtoList) {

        if (paDtoList != null && !paDtoList.isEmpty()) {
            // 设置余额表对账类型
            for (ConfirmPaDto dto : paDtoList) {
                dto.setValBalFlag(DictCodeList.文件类型_余额表.getValue());// 估值表余额表标识 - 余额表
                dto.setMyFileType(DictCodeList.文件类型_余额表.getValue());// 本方文件类型
                dto.setOtherFileType(DictCodeList.文件类型_余额表对账文件.getValue());// 对方文件类型
            }
        }

        // 返回值
        String message = "对账成功";

        try {
            // 对账处理
            List<String> errorMsgList = executeService.execute(paDtoList);

            StringBuilder errorMsgSb = null;
            if (errorMsgList != null && !errorMsgList.isEmpty()) {
                errorMsgSb = new StringBuilder();
                for (int i = 0; i < errorMsgList.size(); i++ ) {
                    errorMsgSb.append(i + 1 + "." + errorMsgList.get(i)).append("</br>");
                }
                message = errorMsgSb.toString();

                return new AjaxResult(message, MessageType.错误消息);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("对账失败", MessageType.错误消息);
        }

        return new AjaxResult(message, MessageType.提示消息);
    }

    /**
     * 取消对账操作
     * 
     * @param paDtoList
     * @param request
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "/doCancle")
    public AjaxResult doCancle(@RequestBody List<ConfirmPaDto> paDtoList) {

        if (paDtoList != null && !paDtoList.isEmpty()) {
            // 设置余额表对账类型
            for (ConfirmPaDto dto : paDtoList) {
                dto.setValBalFlag(DictCodeList.文件类型_余额表.getValue());// 估值表余额表标识 - 余额表
            }
        }

        // 执行取消操作
        executeService.doCancle(paDtoList);

        return new AjaxResult("取消成功", MessageType.提示消息);
    }
    
    /**
     * 调整操作
     * 
     * @param paDtoList
     * @param request
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "/doAdjust")
    public AjaxResult doAdjust(@RequestBody List<ConfirmPaDto> paDtoList) {

        if (paDtoList != null && !paDtoList.isEmpty()) {
            // 设置估值表对账类型
            for (ConfirmPaDto dto : paDtoList) {
                dto.setValBalFlag(DictCodeList.文件类型_余额表.getValue());// 估值表余额表标识 - 余额表
            }
        }

        // 执行取消操作
        executeService.doAdjust(paDtoList);

        return new AjaxResult("调整成功", MessageType.提示消息);
    }

}
