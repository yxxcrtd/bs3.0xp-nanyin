package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdZsmxMapper;
import com.joyintech.tams.common.base.entity.SzdZsmx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdZsmxWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdZsmxWriter extends StlDataDownWriter<SzdZsmx> {
    @Autowired
    private SzdZsmxMapper szdZsmxMapper;
    @Override
    protected void save(SzdZsmx entity) {
        szdZsmxMapper.insertSelective(entity);
    }
}
