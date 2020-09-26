package com.joyintech.tams.valuation.batch.fxdealbookdetail.processor;
/**
 * @author yanya
 * @create 2019-12-12 14:08
 */

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.fxdealbookdetail.service.FxDealBookDetailProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FxDealBookDetailProcessor
 * @Description: 罚息交易账务处理
 * @Author yanya
 * @Date 2019/12/24
 * @Version V1.0
 **/
@Service
@Scope("prototype")
public class FxDealBookDetailProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Autowired
    private FxDealBookDetailProcessorService fxDealBookDetailProcessorService;

    /**
     * 逻辑处理 {@inheritDoc}
     */
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
        BookGenerateResult serviceDealResult = fxDealBookDetailProcessorService.execute(thisParams);

        //当为null时候，给予初始化
        if(serviceDealResult == null){
            serviceDealResult = new BookGenerateResult();
        }

        serviceDealResult.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return serviceDealResult;
    }
}
