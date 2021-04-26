package com.joyintech.tams.settle.batch.datagather.reader.bond;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.tams.constant.DictCodeListExt3;

/**
 * 债券相关交易取值逻辑
 * @author 何阳阳  
 * @date 2020/7/17
 * @see StlDataGatherBond5Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherBond5Reader extends StlDataGatherBondBaseReader {
    @Override
    protected List<String> getPSList() {
        return Arrays.asList(DictCodeListExt3.交易方向_配债.getValue());
    }
}
