package com.joyintech.tams.valuation.batch.accountmaintfeebook.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.dto.AccountMaintFeeBookResult;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.service.AccountMaintFeeBookReaderService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 账户维护费每日计提 reader
 * @author hpp
 *
 */
@Service
@Scope("prototype")
public class AccountMaintFeeBookReader extends BaseReader<AccountMaintFeeBookResult>{
	/**
	 * 记账公共类
	 */
    @Autowired
	private BookCommonLogic bookCommonLogic;
    
    @Autowired 
    private AccountMaintFeeBookReaderService accountMaintFeeBookReaderService;

	@Override
	public List<AccountMaintFeeBookResult> createList() {

        // 变量定义
        List<AccountMaintFeeBookResult> listAccountMaintFeeBookResult = new ArrayList<AccountMaintFeeBookResult>();

        Map<String, Object> serviceParamMap = new HashMap<String, Object>();
        serviceParamMap.put("BATCHINFO", this.getBatchExecuteInfo());
        serviceParamMap.put("BATCHDEL", this.getBatchExecuteDetail());
        serviceParamMap.put("BATCHTASK", this.getBatchTask());
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        serviceParamMap.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        serviceParamMap.put("taskId", this.getBatchExecuteInfo().getTaskId());
        serviceParamMap.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        serviceParamMap.put("productIdList",this.getService().getProductIdList());
        if (this.getParams() != null) {
            this.getParams().putAll(serviceParamMap);
        }
        // 获取reader返回值
        listAccountMaintFeeBookResult = accountMaintFeeBookReaderService.execute(
            serviceParamMap);

        // 返回处理结果
        return listAccountMaintFeeBookResult;
	}

}
