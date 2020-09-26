package com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dao.ValBokBookDetailDelMapper;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dto.ValBokBookDetailDelDto;

/**
 * 账务明细删除批处理 - 读入
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see ValBokBookDetailDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBokBookDetailDelReader extends BaseReader<ValBokBookDetailDelDto> {


    @Autowired
    private ValBokBookDetailDelMapper valBokBookDetailDelMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValBokBookDetailDelDto> createList() {
        //获取task传递参数
        Map<String, Object> params = this.getParams();
        if(MapUtils.isEmpty(params)){
            return null;
        }
        
        List<ValBokBookDetailDelDto> list = new ArrayList<>();
        list.add(new ValBokBookDetailDelDto((List<String>) valBokBookDetailDelMapper.searchBooksets(params)));
        // 返回处理结果
        return list;
    }

}
