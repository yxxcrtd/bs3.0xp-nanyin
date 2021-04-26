package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdSjsjgMapper;
import com.joyintech.tams.common.base.entity.SzdSjsjg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdSjsjgWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdSjsjgWriter extends StlDataDownWriter<SzdSjsjg> {
    @Autowired
    private SzdSjsjgMapper szdSjsjgMapper;
    @Override
    protected void save(SzdSjsjg entity) {
        szdSjsjgMapper.insertSelective(entity);
    }
}
