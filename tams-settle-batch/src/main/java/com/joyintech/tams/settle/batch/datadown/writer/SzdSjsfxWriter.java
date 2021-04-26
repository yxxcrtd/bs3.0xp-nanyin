package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdSjsfxMapper;
import com.joyintech.tams.common.base.entity.SzdSjsfx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdSjsfxWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdSjsfxWriter extends StlDataDownWriter<SzdSjsfx> {
    @Autowired
    private SzdSjsfxMapper szdSjsfxMapper;
    @Override
    protected void save(SzdSjsfx entity) {
        szdSjsfxMapper.insertSelective(entity);
    }
}
