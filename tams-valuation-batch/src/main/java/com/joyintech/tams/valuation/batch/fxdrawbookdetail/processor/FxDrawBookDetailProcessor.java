package com.joyintech.tams.valuation.batch.fxdrawbookdetail.processor;
/**
 * @author yanya
 * @create 2019-12-12 9:56
 */

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.fxdrawbookdetail.service.FxDrawBookDetailProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OverdueInterestCalBookProcessor
 * @Description: 逾期罚息计提账务处理
 * @Author yanya
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Service
@Scope("prototype")
public class FxDrawBookDetailProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private FxDrawBookDetailProcessorService fxDrawBookDetailProcessorService;

    /**
     * process方法
     * @param item
     * @return
     * @throws Exception
     */
    @Override
    public BookGenerateResultWithBokSummary process(NeedOperateEntity items) throws Exception {

        //获取reader方法的返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult) items;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        //调用service方法进行逻辑逻辑
        BookGenerateResultWithBokSummary result = fxDrawBookDetailProcessorService.execute(thisParams);

        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResultWithBokSummary();
        }

        result.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
