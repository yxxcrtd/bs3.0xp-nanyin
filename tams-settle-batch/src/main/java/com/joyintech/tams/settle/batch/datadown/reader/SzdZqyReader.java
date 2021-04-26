package com.joyintech.tams.settle.batch.datadown.reader;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.settle.batch.datadown.dao.StlDataDownMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * 上中登Zqd Reader
 * @author 陈卓
 * @date 2020/11/26
 * @see SzdZqyReader
 * @since 1.0
 */
@Service
@Lazy
public class SzdZqyReader extends StlDataDownReader {
    @Autowired
    private StlDataDownMapper stlDataDownMapper;
    @Override
    protected boolean canProcess(String fileName) {
        return StringUtil.startsWithIgnoreCase(fileName, "zqy");
    }

    @Override
    protected int removeTempData(Date importDate, String dataFile, Set<String> portfolioId) {
        return stlDataDownMapper.removeTempDataZQD(importDate, dataFile, portfolioId);
    }
}
