package com.joyintech.tams.valuation.batch.batchunistart.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.batchunistart.dao.BatchUnistartMapper;
import com.joyintech.tams.common.base.entity.SysBatchUnistart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class BatchUnistartReader extends BaseReader<NeedOperateEntity> {

    /**
     * 数据提取mapper
     */
    @Autowired
    private BatchUnistartMapper batchUnistartMapper;

    @Override
    public List<NeedOperateEntity> createList() {
        // 如果存在已执行的则不处理
        List<SysBatchUnistart> sysBatchUnistartList = batchUnistartMapper.findUnistartUnrOrRun();
        if (sysBatchUnistartList.size() == 0) {
            NeedOperateEntity entity = new NeedOperateEntity();
            List<NeedOperateEntity> list = new ArrayList<>();
            list.add(entity);
            return list;
        }
        return  null;
    }
}
