package com.joyintech.tams.model.valuation.batch.incomemethodmodel.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ProductAssetsIncomeMethodReaderDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.service.ProductAssetsIncomeMethodReaderService;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Description:收益法估值批处理Reader
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class ProductAssetsIncomeMethodReader extends BaseReader <ProductAssetsIncomeMethodReaderDto>{
    /**
     *  收益法估值批处理readerService
     */
    @Autowired
    private ProductAssetsIncomeMethodReaderService productAssetsIncomeMethodReaderService;

    private StepExecution stepExecution;

    @Override
    public List<ProductAssetsIncomeMethodReaderDto> createList() {
        List<ProductAssetsIncomeMethodReaderDto> result = productAssetsIncomeMethodReaderService.createList(getParams(),stepExecution);
        return result;
    }
}
