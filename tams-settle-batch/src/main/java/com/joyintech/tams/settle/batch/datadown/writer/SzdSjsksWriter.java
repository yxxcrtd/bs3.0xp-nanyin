package com.joyintech.tams.settle.batch.datadown.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.SzdSjsksMapper;
import com.joyintech.tams.common.base.entity.SzdSjsks;

/**
 * @author 陈卓
 * @date 2020/11/26
 * @see SzdSjsksWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdSjsksWriter extends StlDataDownWriter<SzdSjsks> {

    @Autowired
    private SzdSjsksMapper szdSjsksMapper;

    @Override
    protected void save(SzdSjsks entity) {
    	szdSjsksMapper.insertSelective(entity);
    }
}
