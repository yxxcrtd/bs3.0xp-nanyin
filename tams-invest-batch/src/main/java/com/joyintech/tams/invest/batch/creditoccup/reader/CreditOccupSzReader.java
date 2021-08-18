package com.joyintech.tams.invest.batch.creditoccup.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.creditoccup.dto.CreditOccupReaderResult;
import com.joyintech.tams.invest.batch.creditoccup.service.CreditOccupReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zznn
 * @date 2021/8/10
 */
@Service
@Scope("prototype")
public class CreditOccupSzReader extends BaseReader<CreditOccupReaderResult> {

    @Autowired
    private CreditOccupReaderService creditOccupReaderService;

    @Override
    public List<CreditOccupReaderResult> createList() {
        return creditOccupReaderService.execute2(this.getParams());
    }
}

