package com.joyintech.tams.invest.batch.creditoccup.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.batch.creditoccup.dto.CreditOccupReaderResult;
import com.joyintech.tams.invest.batch.creditoccup.dto.CreditOccupResult;
import com.joyintech.tams.invest.batch.creditoccup.dto.CreditUsingDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zznn
 * @date 2021/8/10
 */
@Service
@Scope("prototype")
public class CreditOccupProcessor extends BaseProcessor<NeedOperateEntity, CreditOccupResult> {

    @Override
    public CreditOccupResult process(NeedOperateEntity needOperateEntity) throws Exception {
        CreditOccupResult creditOccupResult = new CreditOccupResult();
        // 获取reader方法的返回值
        CreditOccupReaderResult readerResult = (CreditOccupReaderResult) needOperateEntity;
        //额度占用处理
        creditOccupResult.setCreditUsings(handCreditUsingSeries(readerResult.getCreditUsings()));
        return creditOccupResult;
    }

    private List<CreditUsingDto> handCreditUsingSeries(List<CreditUsingDto> creditUsings){
        //实占
        List<CreditUsingDto> sZCreditUsings= new ArrayList<>();
        //预占
        List<CreditUsingDto> yZCreditUsings= new ArrayList<>();
        if(!StringUtil.isEmptyList(creditUsings)){
            creditUsings.stream().forEach(item->{
                if(StringUtil.equals(item.getCreditOccureType(), DictCodeListRsk.授信占用类别_实占.getValue())){
                    item.setStatus(DictCodeListExt1.有效状态_撤销.getValue());
                    sZCreditUsings.add(item);
                }
                else{
                    yZCreditUsings.add(item);
                }
            });
            //实占按委托编号、占用类型求和
            Map<String, Map<String,BigDecimal>> sumByEntrustId =
                    sZCreditUsings.stream()
                            .filter(creditUsingDto -> StringUtil.isNotEmpty(creditUsingDto.getEntrustId()))
                            .collect(Collectors.groupingBy(CreditUsingDto::getEntrustId,
                                    Collectors.groupingBy(CreditUsingDto::getCreditUsingType,
                                    Collectors.mapping(CreditUsingDto::getAmount,
                                            Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));
            if(!StringUtil.isEmptyList(yZCreditUsings)){
                yZCreditUsings.forEach(item->{
                    if(sumByEntrustId.containsKey(item.getTradeId())){
                        if(sumByEntrustId.get(item.getTradeId()).containsKey(item.getCreditUsingType())){
                            item.setAmount(ArithmeticUtil.doAdd(item.getAmount(),sumByEntrustId.get(item.getTradeId()).get(item.getCreditUsingType())));
                        }
                    }
                });
            }
        }
        creditUsings.clear();
        creditUsings.addAll(sZCreditUsings);
        creditUsings.addAll(yZCreditUsings);
        return creditUsings;
    }


}

