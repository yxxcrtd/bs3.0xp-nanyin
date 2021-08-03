package com.joyintech.service.entrust.etrautomanagementaccount.controller;


import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrautomanagementaccount.dto.EtrAutoManagementAccountDto;
import com.joyintech.service.entrust.etrautomanagementaccount.service.EtrAutoManagementAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController("EtrAutoManagementAccountCtr_dtx")
@RequestMapping("/EtrAutoManagementAccount")
public class EtrAutoManagementAccountController {
    @Autowired
    private EtrAutoManagementAccountService etrAutoManagementAccountService;

    @PostMapping("/findPage/{page}/{length}")
    public Page<EtrAutoManagementAccountDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                           @PathVariable("length") int length)throws Exception {
        return etrAutoManagementAccountService.selectForPage(page,length,pageParam);
    }

    @PostMapping("/selectNewForPage/{page}/{length}")
    public Page<EtrAutoManagementAccountDto> selectNewForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                           @PathVariable("length") int length)throws Exception {
        return etrAutoManagementAccountService.selectNewForPage(page,length,pageParam);
    }

    @PostMapping("/getSelectPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getSelectPortfolioId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                      @PathVariable("length") int length){
        return etrAutoManagementAccountService.getSelectPortfolioId(page,length,param);
    }

    @GetMapping("/getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId(String finprodId){

        return etrAutoManagementAccountService.getFinprodMarketId(finprodId);
    }

    @PostMapping("/getSelectFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getSelectFinprodMarketId(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                          @PathVariable("length") int length){
        return etrAutoManagementAccountService.getSelectFinprodMarketId(page,length,param);
    }

    @PostMapping("/finprodMarketIdChange")
    public List<SelectOptionDto> finprodMarketIdChange(@RequestBody Map<String, Object> param){
        return etrAutoManagementAccountService.finprodMarketIdChange(param);
    }

    @PostMapping("/finprodMarketIdType7Change")
    public List<SelectOptionDto> finprodMarketIdType7Change(@RequestBody Map<String, Object> param){
        return etrAutoManagementAccountService.finprodMarketIdType7Change(param);
    }

    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrFundDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody EtrAutoManagementAccountDto dto) throws Exception {
        etrAutoManagementAccountService.doInsert(dto);
    }


    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody EtrAutoManagementAccountDto dto) throws Exception {
        etrAutoManagementAccountService.doComplete(dto);
    }

    /**
     *
     * 编辑、双击页面查看详情
     * 根据主键查询
     * @param entrustId String
     * @return EtrAutoManagementAccountDto
     * @see
     * @since 1.0
     */
    @GetMapping("/selectByKey")
    public EtrAutoManagementAccountDto selectByKey(String entrustId){

        return etrAutoManagementAccountService.selectByKey(entrustId);
    }


    @PostMapping("/portfolioIdChange")
    public List<EtrAutoManagementAccountDto> portfolioIdChange(@RequestBody Map<String, Object> pageParam)throws Exception {
        return etrAutoManagementAccountService.portfolioIdChange(pageParam);
    }

    @PostMapping("/selectAllData")
    public List<EtrAutoManagementAccountDto> selectAllData(@RequestBody Map<String, Object> pageParam){
        return etrAutoManagementAccountService.selectAllData(pageParam);
    }


    /**
     *
     * 关闭委托
     * <功能详细描述>
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/close")
    public boolean closeWwDelegate(String entrustId,String closeWay){
        return etrAutoManagementAccountService.closeWwDelegate(entrustId, closeWay);
    }
    
    @PostMapping("/getSelectPortfolioIdModelForOld")
    public List<EtrAutoManagementAccountDto> getSelectPortfolioIdModelForOld(@RequestBody Map<String, Object> pageParam)throws Exception {
        return etrAutoManagementAccountService.getSelectPortfolioIdModelForOld(pageParam);
    }
    
    @GetMapping("/selectByKeyForOld")
    public EtrAutoManagementAccountDto selectByKeyForOld(String entrustId){
        return etrAutoManagementAccountService.selectByKey(entrustId);
    }
    
}
