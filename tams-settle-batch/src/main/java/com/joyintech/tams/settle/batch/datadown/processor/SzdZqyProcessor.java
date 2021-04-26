package com.joyintech.tams.settle.batch.datadown.processor;

import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.SzdZqy;

/**
 * @author 陈卓
 * @date 2020/11/26
 * @see SzdZqyProcessor
 * @since 1.0
 */
@Service
@Lazy
public class SzdZqyProcessor extends StlDataDownProcessor<SzdZqy> {

    @Override
    protected SzdZqy mapToEntity(Map<String, Object> rowData) {
        return BeanUtil.mapToObject(rowData, SzdZqy.class);
    }
}
