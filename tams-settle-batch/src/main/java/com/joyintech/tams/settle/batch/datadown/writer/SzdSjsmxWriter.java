package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdSjsmxMapper;
import com.joyintech.tams.common.base.entity.SzdSjsmx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdSjsmxWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdSjsmxWriter extends StlDataDownWriter<SzdSjsmx> {
    @Autowired
    private SzdSjsmxMapper szdSjsmxMapper;
    @Override
    protected void save(SzdSjsmx entity) {
        szdSjsmxMapper.insertSelective(entity);
    }
}
