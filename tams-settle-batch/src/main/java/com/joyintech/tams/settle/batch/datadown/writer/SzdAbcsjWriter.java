package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdAbcsjMapper;
import com.joyintech.tams.common.base.entity.SzdAbcsj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdAbcsjWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdAbcsjWriter extends StlDataDownWriter<SzdAbcsj> {
    @Autowired
    private SzdAbcsjMapper szdAbcsjMapper;
    @Override
    protected void save(SzdAbcsj entity) {
        szdAbcsjMapper.insertSelective(entity);
    }
}
