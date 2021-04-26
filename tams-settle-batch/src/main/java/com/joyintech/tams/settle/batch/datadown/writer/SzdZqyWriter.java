package com.joyintech.tams.settle.batch.datadown.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.SzdZqyMapper;
import com.joyintech.tams.common.base.entity.SzdZqy;

/**
 * @author 陈卓
 * @date 2020/11/26
 * @see SzdZqyWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdZqyWriter extends StlDataDownWriter<SzdZqy> {

    @Autowired
    private SzdZqyMapper szdZqyMapper;

    @Override
    protected void save(SzdZqy entity) {
    	szdZqyMapper.insertSelective(entity);
    }
}
