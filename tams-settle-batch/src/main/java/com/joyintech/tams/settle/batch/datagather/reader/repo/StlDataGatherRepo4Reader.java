package com.joyintech.tams.settle.batch.datagather.reader.repo;

import com.joyintech.tams.constant.DictCodeList;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 获取债券质押式协议回购到期交易数据
 * @author 何阳阳
 * @date 2020/7/30
 * @see StlDataGatherRepo4Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherRepo4Reader extends StlDataGatherRepo3Reader {
    @Override
    protected List<String> getPSList() {
        return Arrays.asList(DictCodeList.交易方向_到期.getValue());
    }
}
