/*
 * 文件名：EtrBrowDepoInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrbrowdepoinfo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.service.entrust.etrbrowdepoinfo.service.EtrBrowDepoInfoService;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存款委托Controller
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月19日
 * @see EtrBrowDepoInfoController
 * @since 1.0
 */
@RestController("EtrBrowDepoInfoCtr_dtx")
@RequestMapping("/EtrBrowDepoInfo")
public class EtrBrowDepoInfoController {

    /**
     * 自定义Service
     */
    @Autowired
    private EtrBrowDepoInfoService etrBrowDepoInfoService;

    /**
     *
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<EtrBrowDepoInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<EtrBrowDepoInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page,
                                                  @PathVariable("length") int length)throws Exception {
        return etrBrowDepoInfoService.selectForPage(page,length,pageParam);
    }

    /**
     * 组合代码联想文本框service层实现
     *
     * @param param Map<String, Object>
     * @return List<SuggestDto>
     */
    @PostMapping("/getPortfolioId")
    public List<SuggestDto> getPortfolioId(@RequestBody Map<String, Object> param) {
        return etrBrowDepoInfoService.getPortfolioId(param);
    }

    /**
     * 资产代码联想文本框service层实现
     *
     * @param finprodId 检索参数
     * @return SuggestDto
     */
    @GetMapping("/getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId(String finprodId) {
        return etrBrowDepoInfoService.getFinprodMarketId(finprodId);
    }

    /**
     *
     * 操作前校验
     * <功能详细描述>
     * @param dto EtrBrowDepoInfoDto
     * @return List<ValidMessage>
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrBrowDepoInfoDto dto){
        return etrBrowDepoInfoService.check(dto);
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
    public boolean recallDelegate(@RequestBody EtrBrowDepoInfoDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrBrowDepoInfoService.recallDelegate(dto);
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
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrBrowDepoInfoService.closeDelegate(entrustId, closeWay);
    }

    /**
     *
     * 删除投资委托
     * <功能详细描述>
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("delete")
    public boolean deleteDelegate(String entrustId) {
        return etrBrowDepoInfoService.deleteDelegate(entrustId);
    }

    /**
     *
     * 新建页面资产代码联想下拉框
     * <功能详细描述>
     * @param map 输入参数
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getSelectFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getSelectFinprodMarketId(@RequestBody Map<String, Object> map,@PathVariable("page") int page,
                                                          @PathVariable("length") int length){
        return etrBrowDepoInfoService.getSelectFinprodMarketId(page,length,map);
    }

    /**
     *
     * 新建页面组合代码联想下拉框
     * <功能详细描述>
     * @param portfolioId 输入参数
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getSelectPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getSelectPortfolioId(@RequestBody Map<String, Object> map,@PathVariable("page") int page,
                                                      @PathVariable("length") int length){
        return etrBrowDepoInfoService.getSelectPortfolioId(page,length,map);
    }

    /**
     *
     * 查询通道
     * @param getChannelId 输入参数
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getChannelId/{page}/{length}")
    public Page<SelectOptionDto> getChannelId(@RequestBody Map<String, Object> map,@PathVariable("page") int page,
                                                      @PathVariable("length") int length){
        return etrBrowDepoInfoService.getChannelId(page,length,map);
    }

    /**
     *
     * 查询通道可用现金
     * @param getChannelAvailableCash 输入参数
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getChannelAvailableCash/{page}/{length}")
    public BigDecimal getChannelAvailableCash(@RequestBody String portfolio_id,String chl_finprod_id,@PathVariable("page") int page,
                                              @PathVariable("length") int length){
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("portfolio_id",portfolio_id);
        map.put("chl_finprod_id",chl_finprod_id);
        return etrBrowDepoInfoService.getChannelAvailableCash(page,length,map);
    }
    /**
     *
     *
     * 工作流完成操作
     * @param dto EtrBrowDepoInfoDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody EtrBrowDepoInfoDto dto) throws IllegalAccessException, InvocationTargetException {
        etrBrowDepoInfoService.doComplete(dto);
    }

    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrBrowDepoInfoDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody EtrBrowDepoInfoDto dto) throws IllegalAccessException, InvocationTargetException {
        etrBrowDepoInfoService.doInsert(dto);
    }

    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrBrowDepoInfoDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doDelete")
    public void doDelete(@RequestBody EtrBrowDepoInfoDto dto) throws IllegalAccessException, InvocationTargetException {
        etrBrowDepoInfoService.doDelete(dto);
    }
    /**
     *
     * 编辑、双击页面查看详情
     * 根据主键查询
     * @param entrustId String
     * @param finprodId String
     * @param portfolioId String
     * @return EtrBrowDepoInfoDto
     * @see
     * @since 1.0
     */
    @GetMapping("/selectByKey")
    public EtrBrowDepoInfoDto selectByKey(String entrustId,String finprodId, String portfolioId){

        return etrBrowDepoInfoService.selectByKey(entrustId, finprodId, portfolioId);
    }
    /**
     *
     * 根据金融产品代码获取委托金额
     * <功能详细描述>
     * @param finprodId String
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @GetMapping("/selectEntrustAmt")
    public BigDecimal selectEntrustAmt(String finprodId){
        return etrBrowDepoInfoService.selectEntrustAmt(finprodId);
    }
    /**
     *
     * 根据金融产品代码获取发行规模
     * <功能详细描述>
     * @param finprodId String
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @GetMapping("/selectIssueAmt")
    public BigDecimal selectIssueAmt(String finprodId){
        return etrBrowDepoInfoService.selectIssueAmt(finprodId);
    }

    /**
     *
     * 查询上传文件
     * <功能详细描述>
     * @param businessId businessId
     * @return List<FileArray>
     * @see
     * @since 1.0
     */
    @GetMapping("/selectFileByBusinessId")
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId){
        return etrBrowDepoInfoService.selectFileByBusinessId(businessId);
    }

    /**
     *
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto PtlCashPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    public Map<String,Object> getProAvailableCash(@RequestBody Map<String,Object> param) {
        return etrBrowDepoInfoService.getProAvailableCash(param);
    }
    /**
     *
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto PtlSecPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto) {
        return etrBrowDepoInfoService.getAssetAvailablePosition(dto);
    }
}
