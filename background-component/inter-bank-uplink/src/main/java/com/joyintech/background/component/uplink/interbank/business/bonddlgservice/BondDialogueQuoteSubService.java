/*
 * 文件名：BondDialogueQuoteSubService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.bonddlgservice;

import java.util.List;

import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.BondDialogueQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.GateWayReturnMsgDto;
import com.joyintech.background.component.uplink.interbank.business.dto.QuoteResultBatDto;
import com.joyintech.background.component.uplink.interbank.business.dto.QuoteResultDto;
import com.joyintech.background.component.uplink.interbank.business.dto.ValidMessageDto;
import com.joyintech.background.component.uplink.interbank.business.dto.local.BondQuoteHisSearchDto;

/**
 * 现券对话报价服务接口
 * @author Jiaoxujin
 * @version 1.0 2020年5月6日
 * @see BondDialogueQuoteSubService
 * @since 1.0
 */
public interface BondDialogueQuoteSubService {

    /**
     * 对话报价查询<br />
     * 根据主键查询对话报价信息
     * @param quoteId String 报价编号
     * @return BondDialogueQuoteDto
     */
    BondDialogueQuoteDto getBondDialogueQuote(String quoteId);

    /**
     * 历史对话报价查询-指定条件<br />
     * 根据传入的不为空的属性进行查询，需支持数据权限
     * @param inputBean BondQuoteLocalSearchDto 现券报价查询条件
     * @return List<BondDialogueQuoteDto>
     */
    List<BondDialogueQuoteDto> getBondDialogueQuoteHisList(BondQuoteHisSearchDto inputBean);

    /**
     * 历史对话报价查询-按指定条件分页查询<br />
     * 根据传入的不为空的变量进行查询，以分页形式返回查询结果，需支持数据权限
     * @param pageNum int 页码，从1开始
     * @param pageSize int 分页大小
     * @param inputBean BondQuoteLocalSearchDto 现券报价查询条件
     * @return Page<BondDialogueQuoteDto>
     */
    Page<BondDialogueQuoteDto> getBondDialogueQuoteHisPageList(int pageNum, int pageSize,
                                                            BondQuoteHisSearchDto inputBean);

    /**
     * 对话报价必须项检验<br />
     * 对数据库或CFETS明确规定必输进行校验
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     * @return ValidMessageDto
     */
    ValidMessageDto validateRequiredItems(BondDialogueQuoteDto inputBean);

    /**
     * 对话报价状态检验<br />
     * 对话报价进行操作时，需校验目前对话报价的报价状态和发送状态是否允许该项操作。
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     * @param needSearch boolean
     * @return ValidMessageDto
     */
    ValidMessageDto validateBondDialogueQuoteStatus(BondDialogueQuoteDto inputBean, boolean needSearch);
    
    /**
     * 对话报价状态检验<br />
     * 对话报价进行操作时，需校验目前对话报价的报价状态和发送状态是否允许该项操作。
     * @param inputList List<BondDialogueQuoteDto> 现券对话报价信息
     * @param needSearch boolean
     * @return List<ValidMessageDto>
     */
    List<ValidMessageDto> validateBondDialogueQuoteStatus(List<BondDialogueQuoteDto> inputList, boolean needSearch);

    /**
     * 批量检验对话报价必须项<br />
     * @param inputList List<BondDialogueQuoteDto> 现券对话报价信息列表
     * @return List<ValidMessageDto>
     */
    List<ValidMessageDto> batchValidateRequiredItems(List<BondDialogueQuoteDto> inputList);

    /**
     * 批量对话报价状态检验<br />
     * @param inputList List<BondDialogueQuoteDto> 现券对话报价信息列表
     * @return List<ValidMessageDto>
     */
    List<ValidMessageDto> batchValidateBondDialogueQuoteStatus(List<BondDialogueQuoteDto> inputList);

    /**
     * 对话报价新增<br />
     * 对话报价信息入库
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     * @param isNew 是否为新增记录（有时更新数据时采取的是先删后插的方式，则为非新增）
     * @return QuoteResultDto
     */
    QuoteResultDto insertBondDialogueQuote(BondDialogueQuoteDto inputBean,  boolean isNew);
    
    /**
     * 对话报价新增<br />
     * 对话报价信息入流程表
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     * @param isNew 是否为新增记录（有时更新数据时采取的是先删后插的方式，则为非新增）
     * @return QuoteResultDto
     */
    QuoteResultDto insertWflBondDialogueQuote(BondDialogueQuoteDto inputBean,  boolean isNew);

    /**
     * 批量对话报价新增<br />
     * 对话报价信息批量入库
     * @param inputList List<BondDialogueQuoteDto> 现券对话报价信息列表
     * @return QuoteResultBatDto
     */
    QuoteResultBatDto batchInsertBondDialogueQuote(List<BondDialogueQuoteDto> inputList);

    /**
     * 根据主键进行对话报价更新<br />
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     * @return QuoteResultDto
     */
    QuoteResultDto updateBondDialogueQuoteByKey(BondDialogueQuoteDto inputBean);

    /**
     * 根据主键进行对话报价删除<br />
     * 根据报价代码删除数据
     * @param quoteId String 报价编号
     * @param deleteHis boolean 是否删除历史表数据
     * @return QuoteResultDto
     */
    QuoteResultDto deleteBondDialogueQuoteByKey(String quoteId, boolean deleteHis);
    
    /**
     * 根据主键进行对话报价删除<br />
     * 根据报价代码删除数据
     * @param quoteId String 报价编号
     * @param deleteHis boolean 是否删除历史表数据
     * @return QuoteResultDto
     */
    QuoteResultDto deleteWflBondDialogueQuoteByKey(String quoteId, boolean deleteHis);

    /**
     * 对话报价状态更新<br />
     * 根据操作类型更新BondDialogueQuoteDto对象的报价状态及发送状态
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     */
    void updateBondDialogueQuoteStatus(BondDialogueQuoteDto inputBean);

    /**
     * 对话报价接口更新<br />
     * 根据操作类型更新对话报价接口反馈信息
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     */
    void updateBondDialogueQuoteInterf(BondDialogueQuoteDto inputBean);

    /**
     * 根据主键进行对话报价回退操作<br />
     * @param inputBean BondDialogueQuoteDto 现券对话报价信息
     * @return QuoteResultDto
     */
    QuoteResultDto restoreBondDialogueQuoteByKey(BondDialogueQuoteDto inputBean);

    /**
     * 更新对话报价信息中的版本号、外部报价编号、请求编号、对手机构代码21位编号<br />
     * 包括对历史表中的数据更新
     * @param inputBean GateWayReturnMsgDto
     * @return QuoteResultDto
     */
    QuoteResultDto updateBondDialogueQuoteVersion(GateWayReturnMsgDto inputBean);

    /**
     * 现券银行间报价根据外部报价编号查询报价编号<br />
     * @param pquoteId String
     * @return String 报价编号
     */
    String getQuoteIdByPquoteId(String pquoteId);
    

    /**
     * 查询流程中数据
     * @param quoteId
     * @return
     */
	BondDialogueQuoteDto getWflBondDialogueQuote(String quoteId);

	/*
	 * 对话报价修改操作
	 */
	QuoteResultDto updateBondDialogueQuote(BondDialogueQuoteDto inputBean, boolean isNew);
}
