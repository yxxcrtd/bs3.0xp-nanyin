package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdZqbdMapper;
import com.joyintech.tams.common.base.entity.SzdZqbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdZqbdWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdZqbdWriter extends StlDataDownWriter<SzdZqbd> {

    @Autowired
    private SzdZqbdMapper szdZqbdMapper;

    @Override
    protected void save(SzdZqbd entity) {
        szdZqbdMapper.insertSelective(entity);
    }
}
