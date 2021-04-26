package com.joyintech.tams.settle.batch.datadown.processor;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.SzdSjsjg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdSjsjgProcessor
 * @since 1.0
 */
@Service
@Lazy
public class SzdSjsjgProcessor extends StlDataDownProcessor<SzdSjsjg> {
    @Override
    protected SzdSjsjg mapToEntity(Map<String, Object> rowData) {
        return BeanUtil.mapToObject(rowData, SzdSjsjg.class);
    }
}
