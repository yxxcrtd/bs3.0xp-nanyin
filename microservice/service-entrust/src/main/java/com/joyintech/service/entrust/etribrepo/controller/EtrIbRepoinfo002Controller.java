package com.joyintech.service.entrust.etribrepo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoinfo002Dto;
import com.joyintech.service.entrust.etribrepo.service.EtrIbRepoinfo002Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 质押式回购投资委托微服务controller
 */
@RestController("EtrIbRepoinfo002Ctr_dtx")
@RequestMapping("EtrIbRepoinfo002")
public class EtrIbRepoinfo002Controller {

    /**
     * 质押式回购投资委托微服务service
     */
    @Autowired
    private EtrIbRepoinfo002Service etrIbRepoinfo002Service;

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
        List<SuggestDto> temp = etrIbRepoinfo002Service.getentrustId(map);
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
        List<SuggestDto> temp = etrIbRepoinfo002Service.getfinprodId(finprodId);
        return temp;
    }
    /**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = etrIbRepoinfo002Service.getfinprodIdForAdd(page,length,param);
        return temp;
    }
    /**
     * 
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @param param 
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                          @PathVariable("length") int length){
        Page<SelectOptionDto> temp = etrIbRepoinfo002Service.getentrustIdAdd(page,length,param);
        return temp;
    }
    /**
     * 
     * 操作前校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrIbRepoinfo002Dto dto){
        return etrIbRepoinfo002Service.check(dto);
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
    public boolean recallDelegate(@RequestBody EtrIbRepoinfo002Dto dto) throws IllegalAccessException, InvocationTargetException{
        return etrIbRepoinfo002Service.recallDelegate(dto);
    }
    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrIbRepoinfo002Service.closeDelegate(entrustId, closeWay);
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
    public EtrIbRepoinfo002Dto selectByKey(String entrustId,String finprodId,String portfolioId) {
        return etrIbRepoinfo002Service.selectByKey(entrustId,finprodId,portfolioId);
    }
    /**
     * 
     * 工作流完成方法
     * <功能详细描述>
     * @param map
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrIbRepoinfo002Dto dto) throws IllegalAccessException, InvocationTargetException{
        etrIbRepoinfo002Service.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrIbRepoinfo002Dto dto){
        etrIbRepoinfo002Service.doInsert(dto, dto.getOperateType());
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
        return etrIbRepoinfo002Service.deleteDelegate(entrustId);
    }
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrIbRepoinfo002Service.getProAvailableCash(param);
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
    public Map<String, Object> getAssetAvailablePosition(@RequestBody Map<String, Object> param) {
        return etrIbRepoinfo002Service.getAssetAvailablePosition(param);
    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    public List<SuggestDto> getSecManageAcctId(@RequestBody Map<String, Object> param) {
        return etrIbRepoinfo002Service.getSecManageAcctId(param);
    }
    
    /**
     * 根据组合ID查询质押库
     *
     * @param portfolioId 组合ID
     * @return 质押库
     */
    @PostMapping("getPledgeStockIdByPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPledgeStockIdByPortfolioId(@RequestBody Map<String, Object> param,
                                                               @PathVariable("page") int page, @PathVariable("length") int length) {
        //String portfolioId = (String) param.get("portfolioId");
        String portfolioId_zqzy = (String) param.get("portfolioId_zqzy");
        if(StringUtil.isNotEmpty(portfolioId_zqzy)){
        	List<String> portfolioIdList = StringUtil.splitToList(",", portfolioId_zqzy);
        	param.put("portfolioIdList", portfolioIdList);
        }
        String portfolioId = (String) param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
        	List<String> portfolioId1 = StringUtil.splitToList(",", portfolioId);
        	param.put("portfolioId1", portfolioId1);
        }
        String parentPortfolioId = (String) param.get("parentPortfolioId");
        if(StringUtil.isNotEmpty(parentPortfolioId)){
        	List<String> parentPortfolioId1 = StringUtil.splitToList(",", parentPortfolioId);
        	param.put("parentPortfolioId1", parentPortfolioId1);
        }
        Page<SelectOptionDto> pages = etrIbRepoinfo002Service.getPledgeStockIdByPortfolioId(page, length,param);
        return pages;
    }
}
