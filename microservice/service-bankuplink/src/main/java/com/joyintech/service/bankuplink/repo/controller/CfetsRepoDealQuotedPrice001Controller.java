/*
 * 文件名：CfetsRepoDealQuotedPrice001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.repo.controller;

import com.joyintech.background.component.uplink.interbank.business.dto.ResultBaseDto;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.bankuplink.ibu.dto.RepoQuoteInfoDto;
import com.joyintech.service.bankuplink.ibu.service.IbuComponentCommonService;
import com.joyintech.service.bankuplink.ibu.service.IbuComponentReceiveService;
import com.joyintech.service.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.service.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.service.bankuplink.repo.service.CfetsRepoDealQuotedPrice001Service;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月14日
 * @see CfetsRepoDealQuotedPrice001Controller
 * @since 1.0
 */
@RestController("cfetsRepoDealQuotedPrice001_dtx")
@RequestMapping("/CfetsRepoDealQuotedPrice001")
public class CfetsRepoDealQuotedPrice001Controller {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice001Service cfetsRepoDealQuotedPrice001Service;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IbuComponentReceiveService ibuComponentReceiveService;
    
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IbuComponentCommonService ibuComponentCommonService;

    /**
     * 委托指令查询
     * 
     * @param param 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    @PostMapping("queryEntrustOrderList")
    public List<CfetsRepoDealQuotedPrice001Dto> queryEntrustOrderList(@RequestBody Map<String, Object> param) {
        return cfetsRepoDealQuotedPrice001Service.queryEntrustOrderList(param);
    }
    

    /**
     * 根据查询查询符合条件的委托列表
     * 
     * @param param 入参
     * @param page 页数
     * @param length 步长
     * @return 委托列表
     * @see
     * @since 1.0
     */
    @PostMapping("queryEntrustInfoList/{page}/{length}")
    public Page<SelectOptionDto> queryEntrustInfoList(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length){
        return cfetsRepoDealQuotedPrice001Service.queryEntrustInfoList(page,length,param);
    }
    /**
     * 正回购可质押列表查询
     * 
     * @param param 入参
     * @param page 页数
     * @param length 条数
     * @return 可质押
     * @see
     * @since 1.0
     */
    @PostMapping("queryPledgeBondList/{page}/{length}")
    public Page<CfetsRepoDealQuotedPrice003Dto> queryPledgeBondList(@RequestBody Map<String, Object> param,
                                                                    @PathVariable("page") int page,
                                                                    @PathVariable("length") int length) {
        return cfetsRepoDealQuotedPrice001Service.queryPledgeBondList(page, length,param);
    }
    
    /**
     * 质押券明细列表查询
     * 
     * @param param 入参
     * @param page 页数
     * @param length 条数
     * @return 质押券明细列表
     * @see
     * @since 1.0
     */
    @PostMapping("getPledgeBondList/{page}/{length}")
    public Page<CfetsRepoDealQuotedPrice003Dto> getPledgeBondList(@RequestBody Map<String, Object> param,
                                                                    @PathVariable("page") int page,
                                                                    @PathVariable("length") int length) {
        return cfetsRepoDealQuotedPrice001Service.getPledgeBondList(page, length,param);
    }
    
    /**
     * 逆回购可质押列表查询
     * 
     * @param param 入参
     * @param page 页数
     * @param length 条数
     * @return 可质押
     * @see
     * @since 1.0
     */
    @PostMapping("queryDefyPledgeBondList/{page}/{length}")
    public Page<CfetsRepoDealQuotedPrice003Dto> queryDefyPledgeBondList(@RequestBody Map<String, Object> param,
                                                                    @PathVariable("page") int page,
                                                                    @PathVariable("length") int length) {
        return cfetsRepoDealQuotedPrice001Service.queryDefyPledgeBondList(page, length,param);
    }
    /**
     * 质押券对话报价新增<br />
     * 对话报价信息入库
     * 
     * @param cfetsRepoDealQuotedPrice001Dto 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("insertRepoDealQuote")
    public boolean insertRepoDealQuote(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
        return cfetsRepoDealQuotedPrice001Service.insertRepoDealQuote(cfetsRepoDealQuotedPrice001Dto);
    }
    
    /**
     * 质押券对话报价新增<br />
     * 对话报价信息入库
     * 
     * @param cfetsRepoDealQuotedPrice001Dto 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("insertWflRepoDealQuote")
    public boolean insertWflRepoDealQuote(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
        return cfetsRepoDealQuotedPrice001Service.insertWflRepoDealQuote(cfetsRepoDealQuotedPrice001Dto);
    }
    
    /**
     * 质押券对话报价新增发送<br />
     * 对话报价信息入库
     * 
     * @param cfetsRepoDealQuotedPrice001Dto 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("sendNewRepoDealQuote")
    public boolean sendNewRepoDealQuote(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
        return cfetsRepoDealQuotedPrice001Service.sendNewRepoDealQuote(cfetsRepoDealQuotedPrice001Dto);
    }
    

    /**
     * 质押券报价查询-按指定条件分页查询<br />
     * 根据传入的不为空的变量进行查询，以分页形式返回查询结果，需支持数据权限
     * 
     * @param pageNum int 页码，从1开始
     * @param pageSize int 分页大小
     * @param inputBean BondQuoteSearchDto 现券报价查询条件
     * @param userInfo User 登录用户信息
     * @return Page<BondQuoteExtDto>
     */
    @PostMapping("getDayOfferTableList")
    public List<RepoQuoteInfoDto> getDayOfferTableList(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.getDayOfferTableList(param);
    }
    

    /**
     * 质押券报价查询-按指定条件分页查询<br />
     * 根据传入的不为空的变量进行查询，以分页形式返回查询结果，需支持数据权限
     * 
     * @param pageNum int 页码，从1开始
     * @param pageSize int 分页大小
     * @param inputBean BondQuoteSearchDto 现券报价查询条件
     * @param userInfo User 登录用户信息
     * @return Page<BondQuoteExtDto>
     */
    @PostMapping("getOrderOfferTableList")
    public List<RepoQuoteInfoDto> getOrderOfferTableList(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.getOrderOfferTableList(param);
    }
    /**
     * 对话报价查询
     * 
     * @param param 入参
     * @return 对话报价信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryEntrustInfoByQuoteId")
    public CfetsRepoDealQuotedPrice001Dto queryEntrustInfoByQuoteId(@RequestBody Map<String, Object> param) {
        return cfetsRepoDealQuotedPrice001Service.queryEntrustInfoByQuoteId(param);
    }
    

    /**
     * 质押券对话报价撤销<br />
     * 对话报价信息入库
     * 
     * @param param 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("revert")
    public boolean revert(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.revert(param);
    }
    /**
     * 质押券对话报价成交<br />
     * 对话报价信息入库
     * 
     * @param param 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("deal")
    public boolean deal(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.deal(param);
    }
    
    /**
     * 质押券对话报价修改<br />
     * 对话报价信息入库
     * 
     * @param param 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("modify")
    public boolean modify(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
        return cfetsRepoDealQuotedPrice001Service.modify(cfetsRepoDealQuotedPrice001Dto);
    }
    /**
     * 质押券对话报价拒绝<br />
     * 对话报价信息入库
     * 
     * @param param 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("reject")
    public boolean reject(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.reject(param);
    }
    /**
     * 质押券对话报价回退<br />
     * 
     * @param param 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("restoreDayOff")
    public boolean restoreDayOff(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.restoreDayOff(param);
    }
    /**
     * 质押券对话报价删除<br />
     * 
     * @param param 对话报价信息
     * @return boolean 是否成功
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("deleteDayOff")
    public boolean deleteDayOff(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.deleteDayOff(param);
    }

    /**
     * 历史报价撤销
     * 
     * @param cfetsRepoDealQuotedPrice001Dto 报价对象
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doHisRevoke")
    public boolean doHisRevoke(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
        return cfetsRepoDealQuotedPrice001Service.doHisRevoke(cfetsRepoDealQuotedPrice001Dto);
    }

    /**
     * 根据查询查询符合条件的报价编号
     * 
     * @param param 入参
     * @param page 页数
     * @param length 步长
     * @return 报价编号
     * @see
     * @since 1.0
     */
    @PostMapping("queryCfetsQuoteIdList/{page}/{length}")
    public Page<SuggestDto> queryCfetsQuoteIdList(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length){
        return cfetsRepoDealQuotedPrice001Service.queryCfetsQuoteIdList(page,length,param);
    }
    
    /**
     * 超时刷新机制
     * 
     * @param quoteId 报价编号
     * @return 返回结果
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("timeOutQuoteRefreshRepo")
    public ResultBaseDto timeOutQuoteRefreshRepo(String quoteId){
        return ibuComponentReceiveService.timeOutQuoteRefreshRepo(quoteId);
    }
    
    /**
	 * 新增、修改和成交时，指令持仓流水 、现金流水处理
	 * add by amyli 
	 * 20201230
	 * @param dto
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("doInsetOrUpdateForPtlPositionHold")
	public void doInsetOrUpdateForEtrPositionHold(@RequestBody CfetsRepoDealQuotedPrice001Dto dto) {
		cfetsRepoDealQuotedPrice001Service.doInsetOrUpdateForPtlPositionHold(dto);
	}
	
	
	/**
	 * 删除工作流数据
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("doDeleteWflDataForRepo")
	public boolean doDeleteWflDataForRepo(@RequestBody Map<String, Object> param){
		String workflowId = (String)param.get("workflowId");
        if (workflowId != null) {
        	ibuComponentCommonService.deleteWflDataForRepo(workflowId);
            return true;
        }
        return false;
	}

    @PostMapping("selectUsedAmt")
    public BigDecimal selectUsedAmt(@RequestBody Map<String, Object> param){
        return cfetsRepoDealQuotedPrice001Service.selectUsedAmt(param);
    }
    
    @PostMapping("selectDialogueRecord")
    public CfetsRepoDealQuotedPrice001Dto selectDialogueRecord(@RequestBody CfetsRepoDealQuotedPrice001Dto dto) {
    	return cfetsRepoDealQuotedPrice001Service.selectDialogueRecord(dto);
    }

    /**
     * 新建会话  组合分配列表
     * @param map
     * @return
     */
    @PostMapping("selectPortfolioInit")
    public List<PtlAllocationDto> selectPortfolioInit(@RequestBody Map<String,Object> map) {
        return cfetsRepoDealQuotedPrice001Service.selectPortfolioInit(map);
    }

    /**
     * 根据报价编号  查找报价与委托关系
     * @param map
     * @return
     */
    @PostMapping("selectEtrReByQuoteId")
    public List<PtlAllocationDto> selectEtrReByQuoteId(@RequestBody Map<String,Object> map) {
        return cfetsRepoDealQuotedPrice001Service.selectEtrReByQuoteId(map);
    }

    /**
     *  正回购质押券列表 初始化
     * @param map
     * @return
     */
    @PostMapping("selectQuoteCrepPledgeForDzh")
    public List<CfetsRepoDealQuotedPrice003Dto> selectQuoteCrepPledgeForDzh(@RequestBody Map<String,Object> map) {
        return cfetsRepoDealQuotedPrice001Service.selectQuoteCrepPledgeForDzh(map);
    }

    /**
     *  对手方质押券信息
     * @param map
     * @return
     */
    @PostMapping("selectQuoteCrepPledgeForDsf")
    public List<CfetsRepoDealQuotedPrice003Dto> selectQuoteCrepPledgeForDsf(@RequestBody Map<String,Object> map) {
        return cfetsRepoDealQuotedPrice001Service.selectQuoteCrepPledgeForDsf(map);
    }

    @PostMapping("getIsShowDsfPledge")
    public String getIsShowDsfPledge(@RequestBody Map<String,Object> map) {
        return cfetsRepoDealQuotedPrice001Service.getIsShowDsfPledge(map);
    }

    @PostMapping("getQualifiedByentrustIds")
    public List<CfetsRepoDealQuotedPrice001Dto> getQualifiedByentrustIds(@RequestBody Map<String,Object> map){
        return cfetsRepoDealQuotedPrice001Service.getQualifiedByentrustIds(map);
    }
}
