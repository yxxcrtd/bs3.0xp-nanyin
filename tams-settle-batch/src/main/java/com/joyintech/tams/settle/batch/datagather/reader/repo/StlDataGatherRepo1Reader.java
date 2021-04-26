package com.joyintech.tams.settle.batch.datagather.reader.repo;

import com.joyintech.tams.constant.DictCodeList;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 获取债券质押式回购首期交易数据
 * @author 何阳阳
 * @date 2020/7/20
 * @see StlDataGatherRepo1Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherRepo1Reader extends StlDataGatherRepoBaseReader {
    @Override
    protected List<String> getPSList() {
        return Arrays.asList(DictCodeList.交易方向_买入.getValue());
    }
}
