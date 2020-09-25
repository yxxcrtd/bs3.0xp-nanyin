/*
 * 文件名：BussSettleOrderCommController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.dvporder.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.settle.dvporder.service.DvpOrdersManagementService;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersManagementDto;

@RestController
@RequestMapping("/DvpOrdersManagement")
public class DvpOrdersManagementController {

    @Autowired
    private DvpOrdersManagementService dvpOrdersManagementService;

    /**
     * 查询DVP清算划款信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findDvpTransferTableData/{page}/{length}")
    public Page<DvpOrdersManagementDto> findDvpTransferTableData(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                              @PathVariable("length") int length) throws Exception {
        return dvpOrdersManagementService.findDvpTransferTableData(page, length, param);
    }

    /**
     * 查询划款信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findTransferTableData/{page}/{length}")
    public Page<DvpOrdersManagementDto> findTransferTableData(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                              @PathVariable("length") int length) throws Exception {
        return dvpOrdersManagementService.findTransferTableData(page, length, param);
    }

    /**
     * 查询划款信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findTradeData")
    public List<DvpOrdersManagementDto> findTradeData(@RequestBody Map<String, Object> params) throws Exception {
        return dvpOrdersManagementService.findTradeData(params);
    }

    /**
     * 查询划款信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findDvpOrdersByOrderId")
    public DvpOrdersDto findDvpOrdersByOrderId(@RequestBody String orderId) throws Exception {
        return dvpOrdersManagementService.findDvpOrdersByOrderId(orderId);
    }

    /**
     * 查询划款信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findMatchedNum")
    public int findMatchedNum(@RequestBody List<DvpOrdersManagementDto> tradeDatas) throws Exception {
        return dvpOrdersManagementService.findMatchedNum(tradeDatas);
    }
    
    @PostMapping("updateStlDvpOrdersStatus")
    public int updateStlDvpOrdersStatus(@RequestBody Map<String, Object> param){
		return dvpOrdersManagementService.updateStlDvpOrdersStatus(param);
    }
    
    @PostMapping("updateStlDvpTradeMatchStatus")
    public int updateStlDvpTradeMatchStatus(@RequestBody Map<String, Object> param){
    	return dvpOrdersManagementService.updateStlDvpTradeMatchStatus(param);
    }
    
    @PostMapping("deleteStlDvpOrdersStatus")
    public int deleteStlDvpOrdersStatus(@RequestBody Map<String, Object> param){
        return dvpOrdersManagementService.deleteStlDvpOrdersStatus(param);
    }
    
    @PostMapping("deleteStlDvpTradeMatchStatus")
    public int deleteStlDvpTradeMatchStatus(@RequestBody Map<String, Object> param){
        return dvpOrdersManagementService.deleteStlDvpTradeMatchStatus(param);
    }
}
