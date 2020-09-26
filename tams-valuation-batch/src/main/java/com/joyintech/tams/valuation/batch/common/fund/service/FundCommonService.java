package com.joyintech.tams.valuation.batch.common.fund.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstCounterAcct;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.batch.common.fund.dao.FundCommonMapper;
import com.joyintech.tams.valuation.common.service.BookAccountCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 基金核算共通方法
 * 
 * @author wujie
 * @version 1.0 2019年11月20日
 * @see FundCommonService
 * @since 1.0
 */
@Service
public class FundCommonService {
	@Autowired
    private  FundCommonMapper fundCommonMapper;
    
    @Autowired
    private BookAccountCommonService bookAccountCommonService;
	/**
     * 调用公用方法：【认购确认(利息)账务明细代码处理】
     *
     * @param tradeId 交易编号
     * @param booksetIdNow 账套代码
     * @return BokDetailId DTO(账务明细代码)
     * @see
     * @since 1.0
     */
    public BokDetailId accountDetailProcessing(String tradeId, String booksetIdNow) {
        BokDetailId bokDetailId = new BokDetailId();
        // 定义 变量.资金账户信息
        MstCounterAcct mstCounterAcct = new MstCounterAcct();
        Map<String,Object> parmsMap = new HashMap<String,Object>();
        parmsMap.put("tradeId",tradeId);
        parmsMap.put("booksetIdNow",booksetIdNow);
        parmsMap.put("ps",  DictCodeListExt2.交易方向_认购.getValue());
        // 2.1. 查询认购确认交易对应的认购交易
        mstCounterAcct = fundCommonMapper.querySubscriptionConfirmationDeal(parmsMap);
        if(mstCounterAcct!=null){
        	bokDetailId.setBusiId(mstCounterAcct.getAcctNo());
            bokDetailId.setBusiDetailType(mstCounterAcct.getAcctType());
            bokDetailId.setCcy(mstCounterAcct.getCcy());
        }else{
        	return null;
        }
        bokDetailId.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));
        return  bokDetailId;
    }

    /**
     * 调用公用方法：【基金转换_账务明细代码处理】
     *
     * @param tradeId 交易编号
     * @return BokDetailId DTO(账务明细代码)
     * @see
     * @since 1.0
     */
    public BokDetailId getFundBokDetailId(String tradeId) {
        //1.1 定义变量
        Map<String,Object> parmsMap = new HashMap<String,Object>();
        parmsMap.put("tradeId", tradeId);
        //2.1. 查询对应的标的信息
        List<FinProduct> list = fundCommonMapper.queryTargetInfo(parmsMap);
        //定义 变量.账务明细代码DTO	BokDetailId
        BokDetailId bokDetailId = new BokDetailId();
        //IF 变量.标的信息 IS NOT NULL
        if(!StringUtil.isEmptyList(list)) {
            //定义 变量.标的信息
            FinProduct finProduct = list.get(0);
            //变量.账务明细代码DTO.业务明细代码 = 	变量.标的信息.金融产品代码
            bokDetailId.setBusiId(finProduct.getFinprodId());
            //变量.账务明细代码DTO.业务明细类型 = 	变量.标的信息.金融产品类型
            bokDetailId.setBusiDetailType(finProduct.getFinprodType());
            //变量.账务明细代码DTO.四级科目尾号 = 	变量.标的信息.金融产品代码
            bokDetailId.setDetailSubjectNoTail(finProduct.getFinprodId());
            //变量.账务明细代码DTO.四级科目尾号名称 =  变量.标的信息.金融产品名称
            bokDetailId.setDetailSubnameTail(finProduct.getFinprodName());
            //变量.账务明细代码DTO.账务明细代码 = bookAccountCommonService.genMd5BokDetailId(变量.账务明细代码DTO)
            bokDetailId.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));
        }
        else {
            return null;
        }
        return  bokDetailId;
    }
}
