package com.joyintech.tams.valuation.batch.threetableslanding.reader;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.batch.threetableslanding.dao.ThreeTableLandingMapper;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValTaskTodoDto;

@Service
@Scope("prototype")
public class ThreeTablesLandingReader extends BaseReader<ValTaskTodoDto> {

    /**
     * 数据提取mapper
     */
    @Autowired
    private ThreeTableLandingMapper mapper;

    @Override
    public List<ValTaskTodoDto> createList() {
        Map<String, Object> params = this.getParams();
        params.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        //根据参数查询对应待处理的账套
        List<ValTaskTodoDto> listDto = mapper.selectValTaskTodo(params);
        return listDto;
    }
}
