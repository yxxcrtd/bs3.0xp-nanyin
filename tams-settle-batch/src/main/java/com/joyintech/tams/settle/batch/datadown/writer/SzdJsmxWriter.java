package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdJsmxMapper;
import com.joyintech.tams.common.base.entity.SzdJsmx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author 何阳阳
 * @date 2020/7/6
 * @see SzdJsmxWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdJsmxWriter extends StlDataDownWriter<SzdJsmx> {

    @Autowired
    private SzdJsmxMapper szdJsmxMapper;

    @Override
    protected void save(SzdJsmx entity) {
        szdJsmxMapper.insertSelective(entity);
    }
}
