package com.joyintech.tams.settle.batch.datagather.reader.bond;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.settle.batch.datagather.reader.StlDataGatherReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 债券相关交易取值逻辑
 * @author 何阳阳
 * @date 2020/7/17
 * @see StlDataGatherBond4Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherBond4Reader extends StlDataGatherBondBaseReader {
    @Override
    protected List<String> getPSList() {
        return Arrays.asList(DictCodeListExt1.交易方向_债转股.getValue());
    }
}
