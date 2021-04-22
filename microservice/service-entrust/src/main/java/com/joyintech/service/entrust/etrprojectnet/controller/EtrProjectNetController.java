package com.joyintech.service.entrust.etrprojectnet.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.service.entrust.etrprojectnet.service.EtrProjectNetService;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *  权益类资产
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see EtrProjectNetController
 * @since 1.0
 */
@RestController("EtrProjectNetCtr_dtx")
@RequestMapping("/EtrProjectNet")
public class EtrProjectNetController {
    @Autowired
    private EtrProjectNetService etrProjectNetService;
    /**
     *
     * 计划类净值委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrProjectNetDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return etrProjectNetService.selectForPage(page,length,param);
    }
    /**
     *
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustId")
    public List<SuggestDto> getentrustId(@RequestBody Map<String, Object> map)
            throws Exception {
        List<SuggestDto> temp = etrProjectNetService.getentrustId(map);
        return temp;
    }
    /**
     *
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getfinprodId")
    public List<SuggestDto> getfinprodId(String finprodId)
            throws Exception {
        List<SuggestDto> temp = etrProjectNetService.getfinprodId(finprodId);
        return temp;
    }

    /**
     *
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, String> map, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        Page<SelectOptionDto> temp = etrProjectNetService.getfinprodIdForAdd(page,length,map);
        return temp;
    }
    /**
     *
     * 获取新建界面的组合代码联想下拉框框
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    @ResponseBody
    Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> temp = etrProjectNetService.getentrustIdAdd(page,length,map);
        return temp;
    }
    /**
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrProjectNetDto dto){
        return etrProjectNetService.check(dto);
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
    public boolean recallDelegate(@RequestBody EtrProjectNetDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrProjectNetService.recallDelegate(dto);
    }
    /**
     * 关闭委托
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrProjectNetService.closeDelegate(entrustId, closeWay);
    }
    /**
     *
     * 工作流完成方法
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrProjectNetDto dto) throws IllegalAccessException, InvocationTargetException{
        etrProjectNetService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrProjectNetDto dto){
        etrProjectNetService.doInsert(dto, dto.getOperateType());
    }
    /**
     *
     * 删除投资委托
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("delete")
    public boolean deleteDelegate(String entrustId) {
        return etrProjectNetService.deleteDelegate(entrustId);
    }

    /**
     *
     * 通过主键查询数据
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectByKey")
    public EtrProjectNetDto selectByKey(String entrustId) {
        return etrProjectNetService.selectByKey(entrustId);
    }
    /**
     *
     *
     * 根据资产代码查询资产类型
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("searchType")
    public Map<String, String> searchType(String finprodId){
        return etrProjectNetService.searchType(finprodId);
    }
    /**
     *
     * 组合可用现金查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    @ResponseBody
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrProjectNetService.getProAvailableCash(param);
    }
    /**
     *
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto) {
        return etrProjectNetService.getAssetAvailablePosition(dto);
    }


    /**
     * 获取交易对手名称
     * @param counterId
     * @return
     */
    @GetMapping("getCounterName")
    public String getCounterName(String counterId){
        return etrProjectNetService.getCounterName(counterId);
    }
}
