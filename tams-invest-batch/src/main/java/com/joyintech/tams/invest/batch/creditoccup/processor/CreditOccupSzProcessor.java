package com.joyintech.tams.invest.batch.creditoccup.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.batch.creditoccup.dto.CreditOccupReaderResult;
import com.joyintech.tams.invest.batch.creditoccup.dto.CreditOccupResult;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesInputDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实占处理过程
 * @author zznn
 * @date 2021/8/11
 */
@Service
@Scope("prototype")
public class CreditOccupSzProcessor extends BaseProcessor<NeedOperateEntity, CreditOccupResult> {
    @Override
    public CreditOccupResult process(NeedOperateEntity needOperateEntity) throws Exception {
        // 获取reader方法的返回值
        CreditOccupReaderResult readerResult = (CreditOccupReaderResult) needOperateEntity;
        List<CreditLinesInputDto> creditLinesInputs = readerResult.getCreditLinesInputs();
        if(!StringUtil.isEmptyList(creditLinesInputs)){
            AllTradeCommonUtil.handleBeforeTradeComplete(creditLinesInputs, DictCodeListRsk.授信占用类别_实占.getValue());
        }
        return null;
    }
}

