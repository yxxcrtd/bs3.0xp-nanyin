package com.joyintech.tams.settle.batch.datagather.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * @author 何阳阳
 * @date 2020/7/20
 * @see StlDataGatherFund1Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherFund1Reader extends StlDataGatherReader {

    Map<String, Object> param = new HashMap<>(2);

    @Override
    protected void setSearchParam(Map<String, Object> searchParam) {
        // 业务类型
    	List<String> busiTypeList = new ArrayList<String>();
    	busiTypeList.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
    	busiTypeList.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
        searchParam.put("busiTypeList", busiTypeList);
        // 交易方向列表
        searchParam.put("psList", Arrays.asList(DictCodeList.交易方向_买入.getValue()
                ,DictCodeList.交易方向_卖出.getValue()));

        param.put("portfolioIdList", searchParam.get("portfolioIdList"));
        param.put("dealDate", searchParam.get("dealDate"));
    }

    @Override
    protected boolean isSecType(String secType) {
        return StringUtil.equals(DictCodeListExt1.证券品种_基金.getValue(), secType);
    }
}
