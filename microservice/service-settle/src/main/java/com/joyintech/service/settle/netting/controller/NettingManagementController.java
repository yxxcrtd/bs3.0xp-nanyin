package com.joyintech.service.settle.netting.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.settle.dvporder.service.DvpOrdersManagementService;
import com.joyintech.service.settle.netting.service.NettingManagementService;
import com.joyintech.service.settle.netting.service.TransferMessageService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.netting.TransferMessageDto;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-06 17:32
 */

@RestController
@RequestMapping("/NettingManagement")
public class NettingManagementController extends BaseController {

    @Autowired
    private NettingManagementService nettingManagementService;

    @Autowired
    private TransferMessageService transferMessageService;




    @PostMapping("findNettingTableData/{page}/{length}")
    public Page<NettingManagementDto> findNettingTableData(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                           @PathVariable("length") int length) throws Exception {
        //Page<NettingManagementDto> page2 =  nettingManagementService.findNettingTableData(page, length, param);
        return nettingManagementService.findNettingTableData(page, length, param);
    }


    @PostMapping("findTransferMessage/{page}/{length}")
    public Page<TransferMessageDto> findTransferMessage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                        @PathVariable("length") int length) throws Exception {
        return transferMessageService.findTransferMessage(page, length, param);
    }

    /**
     * 查询划款信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findTradeData")
    public List<NettingManagementDto> findTradeData(@RequestBody Map<String, Object> params) throws Exception {
        return nettingManagementService.findTradeData(params);
    }

    @PostMapping("findDvpOrdersByOrderId")
    public NettingOrdersDto findDvpOrdersByOrderId(@RequestBody String orderId) throws Exception {
        return nettingManagementService.findDvpOrdersByOrderId(orderId);
    }

    @PostMapping("findMatchedNum")
    public int findMatchedNum(@RequestBody List<NettingManagementDto> tradeDatas) throws Exception {
        return nettingManagementService.findMatchedNum(tradeDatas);
    }

    @PostMapping("findOrderIdByTotleTradeId")
    public String findOrderIdByTotleTradeId(@RequestBody String TOTLE_TRADE_ID) throws Exception
    {
        return nettingManagementService.findOrderIdByTotleTradeId(TOTLE_TRADE_ID);
    }

    @PostMapping("updateStlDvpOrdersStatus")
    public int updateStlDvpOrdersStatus(@RequestBody Map<String, Object> param){
        return nettingManagementService.updateStlDvpOrdersStatus(param);
    }

    @PostMapping("updateStlDvpTradeMatchStatus")
    public int updateStlDvpTradeMatchStatus(@RequestBody Map<String, Object> param){
        return nettingManagementService.updateStlDvpTradeMatchStatus(param);
    }
}
