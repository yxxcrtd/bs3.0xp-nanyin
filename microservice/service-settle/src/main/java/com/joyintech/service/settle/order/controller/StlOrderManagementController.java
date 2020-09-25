/*
 * 文件名：BussSettleOrderCommController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.settle.order.service.StlOrderManagementService;
import com.joyintech.tams.invest.common.dto.settle.order.StlOrderManagementDto;

@RestController
@RequestMapping("/StlOrderManagement")
public class StlOrderManagementController {

    @Autowired
    private StlOrderManagementService stlOrderManagementService;

    /**
     * 画面查询方法-划款指令数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findOrderData/{page}/{length}")
    public Page<StlOrderManagementDto> findOrderData(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                     @PathVariable("length") int length) {
        return stlOrderManagementService.findOrderData(page, length, param);
    }
    /**
     * 投管接口（交易清算状态输入接口）
     * 
     * @param param 参数
     * @return 是否成功
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insertInfPayOrdersStatus")
    public String insertInfPayOrdersStatus(@RequestBody Map<String, String> param) {
        return stlOrderManagementService.insertInfPayOrdersStatus(param);
    }
    /**
     * 投管接口（交易发送接口时更新指令发送/接收状态）
     * 
     * @param param 参数
     * @return 是否成功
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insertInfPayOrdersSendStatus")
    public String insertInfPayOrdersSendStatus(@RequestBody Map<String, String> param) {
        return stlOrderManagementService.insertInfPayOrdersStatus(param);
    }
}
