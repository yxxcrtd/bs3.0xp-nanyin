
package com.joyintech.service.entrust.etrstockconnect.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.entrust.etrexchangetrade.dto.EtrExchangeTrade001Dto;
import com.joyintech.service.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.service.entrust.etrstockconnect.service.EtrStockConnect002Service;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 港股通指令
 *
 * @see EtrStockConnect002Controller
 * @since 1.0
 */
@RestController("EtrStockConnect002Ctr_dtx")
@RequestMapping("EtrStockConnect002")
public class EtrStockConnect002Controller {
    @Autowired
    private EtrStockConnect002Service etrStockConnect002Service;

    /**
     *@title: getfinprodIdForAdd
     *@description: 获取新建界面的资产代码联想下拉框
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, String> map, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        Page<SelectOptionDto> temp = etrStockConnect002Service.getfinprodIdForAdd(page,length,map);
        return temp;
    }

    /**
     * 撤回委托 
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("recall")
    public boolean recallDelegate(@RequestBody EtrStockConnectDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrStockConnect002Service.recallDelegate(dto);
    }
    /**
     * 关闭委托 
     *
     * @param entrustId entrustId
     * @param closeWay closeWay
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrStockConnect002Service.closeDelegate(entrustId, closeWay);
    }

    /**
     * 工作流完成方法
     * <功能详细描述>
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrStockConnectDto dto) throws IllegalAccessException, InvocationTargetException{
        etrStockConnect002Service.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrStockConnectDto dto){
        etrStockConnect002Service.doInsert(dto, dto.getOperateType());
    }

    /**
     * 插入和修改时，指令持仓流水 、现金流水处理
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsetOrUpdateForEtrPositionHold")
    public void doInsetOrUpdateForEtrPositionHold(@RequestBody EtrStockConnectDto dto) {
        etrStockConnect002Service.doInsetOrUpdateForEtrPositionHold(dto);
    }

    /**
     * 查询组合证券账户
     */
    @PostMapping("getSecManageAcctId")
    public StlSecAcctInfo getSecManageAcctId(@RequestBody Map<String,Object> param){
        return etrStockConnect002Service.getSecManageAcctId(param);
    }

    /**
     * 组合证券账户查询
     */
    @PostMapping("selectStlSecAcctInfo")
    public StlSecAcctInfo selectStlSecAcctInfo(@RequestBody Map<String,Object> param){
        return etrStockConnect002Service.selectStlSecAcctInfo(param);
    }

    /**
     * 配售代码
     */
    @PostMapping("/getSaleCode")
    public String getSaleCode(@RequestBody Map<String, Object> map){
        String saleCode = etrStockConnect002Service.getSaleCode(map);
        return saleCode;
    }

    /**
     * 获取资产各项信息
     */
    @PostMapping("/getFinProdInfo")
    public  EtrStockConnectDto getFinProdInfo(@RequestBody Map<String, Object> map){
        EtrStockConnectDto dto = etrStockConnect002Service.getFinProdInfo(map);
        return dto;
    }

    /**
     * 参考汇率
     */
    @PostMapping("/getReferenceRate")
    public  EtrStockConnectDto getReferenceRate(@RequestBody  Map<String, Object> map){
        return   etrStockConnect002Service.getReferenceRate(map);
    }


}
