package com.joyintech.tams.settle.batch.datadown.reader;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.settle.batch.datadown.dao.StlDataDownMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * 上中登权益登记通知信息TZXX接口
 * @author meifaxin
 * @date 2021/12/9
 * @see SzdTzxxReader
 * @since 1.0
 */
@Service
@Lazy
public class SzdTzxxReader extends StlDataDownReader {
    @Autowired
    private StlDataDownMapper stlDataDownMapper;
    @Override
    protected boolean canProcess(String fileName) {
        return StringUtil.startsWithIgnoreCase(fileName, "tzxx");
    }

    @Override
    protected int removeTempData(Date importDate, String dataFile, Set<String> portfolioIdList) {
        return stlDataDownMapper.removeTempDataTZXX(importDate, dataFile, portfolioIdList);
    }
}
