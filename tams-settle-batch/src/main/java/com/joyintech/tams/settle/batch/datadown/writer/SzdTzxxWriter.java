package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.tams.common.base.dao.SzdTzxxMapper;
import com.joyintech.tams.common.base.entity.SzdTzxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 上中登权益登记通知信息TZXX接口
 * @author meifaxin
 * @date 2021/12/9
 * @see SzdTzxxWriter
 * @since 1.0
 */
@Service
@Lazy
public class SzdTzxxWriter extends StlDataDownWriter<SzdTzxx> {
    @Autowired
    private SzdTzxxMapper szdTzxxMapper;
    @Override
    protected void save(SzdTzxx entity) {
        szdTzxxMapper.insertSelective(entity);
    }
}
