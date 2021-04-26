package com.joyintech.tams.settle.batch.datacreate.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.settle.batch.datacreate.dto.StlDataCreateReaderDto;
import com.joyintech.tams.settle.batch.datacreate.service.StlDataCreateService;

/**
 * @author 陈卓
 * @date 2020/11/24
 * @see StlDataCreateReader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataCreateReader extends BaseReader<StlDataCreateReaderDto> {

    @Autowired
    protected StlDataCreateService stlDataCreateService;

    @Override
    public List<StlDataCreateReaderDto> createList() {
        // 批处理执行信息
        SysBatchExecInfo execInfo = this.getBatchExecuteInfo();
        Map<String, Object> searchParam = new HashMap<>();
        searchParam.put("dealDate", execInfo.getExecuteDate());
        List<String> portfolioIdList = (List<String>)this.getParams().get("productIdList");
        if (portfolioIdList != null && portfolioIdList.size() > 0) {
        	searchParam.put("portfolioIdList", portfolioIdList);
        }
        StlDataCreateReaderDto data = new StlDataCreateReaderDto();
        data.setStlDataCreateDtoList(stlDataCreateService.selectStlDownFileData(searchParam));
        data.setTradeDate(DateUtil.format(execInfo.getExecuteDate(), DateUtil.DEFAULT_OTHER_FORMAT));
        List<StlDataCreateReaderDto> result = new ArrayList<StlDataCreateReaderDto>();
        result.add(data);
        return result;
    }
}