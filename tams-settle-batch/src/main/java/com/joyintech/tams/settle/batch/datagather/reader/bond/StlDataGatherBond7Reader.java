package com.joyintech.tams.settle.batch.datagather.reader.bond;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
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
 * @see StlDataGatherBond7Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherBond7Reader extends StlDataGatherReader {

    @Override
    protected void setSearchParam(Map<String, Object> searchParam) {
        // 业务类型
        searchParam.put("busiType", "R3S5");
        searchParam.put("finprodOthCodeType", DictCodeListExt3.金融产品其他代码类型_上市流通.getValue());
    }

    @Override
    protected boolean isSecType(String secType) {
        return false;
    }

    @Override
    protected boolean isCreateTrade(){
        return true;
    }
}
