package com.joyintech.tams.valuation.batch.ptlCashFlow.processor;

import java.util.HashMap;
import java.util.Map;
import com.joyintech.tams.valuation.batch.ptlCashFlow.service.PtlCashFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 自定义现金流账务处理
 */
@Service
@Scope("prototype")
public class PtlCashFlowProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult>{

    /**
     * processor方法的 Service
     */
    @Autowired
    private PtlCashFlowService ptlCashFlowService;

    @Override
    public BookGenerateResult process(NeedOperateEntity items)
            throws Exception {
        // 获取reader方法的返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);


        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = ptlCashFlowService.execute(thisParams);

        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResult();
        }

        result.setProdId(
                acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
