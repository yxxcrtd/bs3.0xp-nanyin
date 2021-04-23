/*
 * 文件名：BondRequestQuoteSubService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.bondreqservice;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.BondRequestQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.GateWayReturnMsgDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDetDto;
import com.joyintech.background.component.uplink.interbank.business.dto.QuoteResultBatDto;
import com.joyintech.background.component.uplink.interbank.business.dto.QuoteResultDto;
import com.joyintech.background.component.uplink.interbank.business.dto.ValidMessageDto;
import com.joyintech.background.component.uplink.interbank.business.dto.local.BondQuoteHisSearchDto;

/**
 * 现券请求报价服务接口
 * @author Jiaoxujin
 * @version 1.0 2020年5月6日
 * @see BondRequestQuoteSubService
 * @since 1.0
 */
public interface BondRequestQuoteSubService {

    /**
     * 请求报价查询<br />
     * 根据主键查询请求报价信息
     * @param quoteId String 报价编号
     * @return BondRequestQuoteDto
     */
    BondRequestQuoteDto getBondRequestQuote(String quoteId);
    
    
    /**
     * 请求报价查询<br />
     * 根据主键查询请求报价信息
     * @param quoteId String 报价编号
     * @return BondRequestQuoteDto
     */
    BondRequestQuoteDto getWflBondRequestQuote(String quoteId);
    
    /**
     * 
     * 根据外部请求编号查询请求报价明细信息
     * @param reqId String
     * @return IbuQuoteBondDetDto
     * @see
     * @since 1.0
     */
    IbuQuoteBondDetDto selectQuoteDetByReqId(@Param("reqId")String reqId);

    /**
     * 请求报价查询-指定条件<br />
     * 根据传入的不为空的属性进行查询，需支持数据权限
     * @param inputBean BondQuoteSearchDto 请求报价查询条件
     * @return List<BondRequestQuoteDto>
     */
    List<BondRequestQuoteDto> getBondRequesteQuoteHisList(BondQuoteHisSearchDto inputBean);

    /**
     * 请求报价查询-按指定条件分页查询<br />
     * 根据传入的不为空的变量进行查询，以分页形式返回查询结果，需支持数据权限
     * @param pageNum int 页码，从1开始
     * @param pageSize int 分页大小
     * @param inputBean BondQuoteSearchDto 请求报价查询条件
     * @return Page<BondRequestQuoteDto>
     */
    Page<BondRequestQuoteDto> getBondRequestQuoteHisPageList(int pageNum, int pageSize,
                                                              BondQuoteHisSearchDto inputBean);

    /**
     * 请求报价必须项检验<br />
     * 对数据库或CFETS明确规定必输进行校验
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @return ValidMessageDto
     */
    ValidMessageDto validateRequiredItems(BondRequestQuoteDto inputBean);

    /**
     * 请求报价状态检验<br />
     * 请求报价进行操作时，需校验目前请求报价的报价状态和发送状态是否允许该项操作。
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @return ValidMessageDto
     */
    ValidMessageDto validateBondRequestQuoteStatus(BondRequestQuoteDto inputBean, boolean needSearch);
    
    /**
     * 请求报价状态检验<br />
     * 请求报价进行操作时，需校验目前请求报价的报价状态和发送状态是否允许该项操作。
     * @param inputList List<BondRequestQuoteDto> 现券请求报价信息
     * @return List<ValidMessageDto>
     */
    List<ValidMessageDto> validateBondRequestQuoteStatus(List<BondRequestQuoteDto> inputList, boolean needSearch);

    /**
     * 批量检验请求报价必须项<br />
     * @param inputList List<BondRequestQuoteDto> 现券请求报价信息列表
     * @return List<ValidMessageDto>
     */
    List<ValidMessageDto> batchValidateRequiredItems(List<BondRequestQuoteDto> inputList);

    /**
     * 批量请求报价状态检验<br />
     * @param inputList List<BondRequestQuoteDto> 现券请求报价信息列表
     * @return List<ValidMessageDto>
     */
    List<ValidMessageDto> batchValidateBondRequestQuoteStatus(List<BondRequestQuoteDto> inputList);
    
    /**
     * 请求报价新增<br />
     * 请求报价信息入库
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @param isNew boolean
     * @param isDuishouRespByJet boolean
     * @return QuoteResultDto
     */
    QuoteResultDto insertBondRequestQuote(BondRequestQuoteDto inputBean, 
                                          boolean isNew, boolean isDuishouRespByJet);

    /**
     * 请求报价新增<br />
     * 请求报价信息入库
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @param isNew boolean
     * @param isDuishouRespByJet boolean
     * @param insertHist boolean 是否向历史表插入数据
     * @return QuoteResultDto
     */
    QuoteResultDto insertBondRequestQuote(BondRequestQuoteDto inputBean, 
                                          boolean isNew, boolean isDuishouRespByJet, boolean insertHist);
    
    
    /**
     * 请求报价新增<br />
     * 请求报价信息入库
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @param isNew boolean
     * @param isDuishouRespByJet boolean
     * @return QuoteResultDto
     */
    QuoteResultDto insertWflBondRequestQuote(BondRequestQuoteDto inputBean, 
                                          boolean isNew, boolean isDuishouRespByJet);

    /**
     * 批量请求报价新增<br />
     * 请求报价信息批量入库
     * @param inputList List<BondRequestQuoteDto> 现券请求报价信息列表
     * @return QuoteResultBatDto
     */
    QuoteResultBatDto batchInsertBondRequestQuote(List<BondRequestQuoteDto> inputList);

    /**
     * 根据主键进行请求报价更新<br />
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @return QuoteResultDto
     */
    QuoteResultDto updateBondRequestQuoteByKey(BondRequestQuoteDto inputBean);

    /**
     * 根据主键进行请求报价删除<br />
     * 根据报价代码删除数据
     * @param inputBean BondRequestQuoteDto
     * @param deleteHis boolean
     * @param isDuishouResp boolean
     * @return QuoteResultDto
     */
    QuoteResultDto deleteBondRequestQuoteByKey(
                                               BondRequestQuoteDto inputBean, 
                                               boolean deleteHis, boolean isDuishouResp);

    /**
     * 请求报价状态更新<br />
     * 根据操作类型更新BondRequestQuoteDto对象中的报价状态及发送状态
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @param detIdList List<String>
     * @param isDuishouResp boolean
     * @return BondRequestQuoteDto
     */
    void updateBondRequestQuoteStatus(BondRequestQuoteDto inputBean, List<String> detIdList, boolean isDuishouResp);

    /**
     * 请求报价接口更新<br />
     * 根据操作类型更新请求报价接口反馈信息
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @return BondRequestQuoteDto
     */
    void updateBondRequestQuoteInterf(BondRequestQuoteDto inputBean);
    
    /**
     * 根据主键进行请求报价回退操作<br />
     * @param inputBean BondDialogueQuoteDto 现券请求报价信息
     * @return QuoteResultDto
     */
    QuoteResultDto restoreBondRequesteQuoteByKey(BondRequestQuoteDto inputBean);
    
    /**
     * 更新请求报价信息中的版本号、外部报价编号、请求编号、对手机构代码21位编号<br />
     * 包括对历史表中的数据更新
     */
    QuoteResultDto updateBondRequestQuoteVersion(GateWayReturnMsgDto inputBean);
    
    /**
     * 现券银行间报价根据外部报价编号查询报价编号<br />
     * @param pquoteId String
     * @return String 报价编号
     */
    String getQuoteIdByPquoteId(String pquoteId);
    
    /**
     * 根据主键进行请求报价回退操作<br />
     * @param inputBean BondRequestQuoteDto 现券请求报价信息
     * @return QuoteResultDto
     */
    QuoteResultDto restoreBondRequestQuoteByKey(BondRequestQuoteDto inputBean);
    
    /**
     * 根据主键进行对话报价删除<br />
     * 根据报价代码删除数据
     * @param quoteId String 报价编号
     * @param deleteHis boolean 是否删除历史表数据
     * @return QuoteResultDto
     */
    QuoteResultDto deleteWflBondRequestQuoteByKey(String quoteId, boolean deleteHis);
    
}
