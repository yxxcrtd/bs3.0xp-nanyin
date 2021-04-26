package com.joyintech.tams.settle.batch.datadown.reader;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.settle.batch.datadown.dao.StlDataDownMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdZsmxReader
 * @since 1.0
 */
@Service
@Lazy
public class SzdZsmxReader extends StlDataDownReader {
    @Autowired
    private StlDataDownMapper stlDataDownMapper;
    @Override
    protected boolean canProcess(String fileName) {
        return StringUtil.startsWithIgnoreCase(fileName, "zsmx");
    }

    @Override
    protected int removeTempData(Date importDate, String dataFile, Set<String> portfolioIdList) {
        return stlDataDownMapper.removeTempDataZSMX(importDate, dataFile, portfolioIdList);
    }
}
