package com.joyintech.tams.settle.batch.datadown.processor;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.SzdJsmx;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/7/6
 * @see SzdJsmxProcessor
 * @since 1.0
 */
@Service
@Lazy
public class SzdJsmxProcessor extends StlDataDownProcessor<SzdJsmx> {

    @Override
    protected SzdJsmx mapToEntity(Map<String, Object> rowData) {
        return BeanUtil.mapToObject(rowData, SzdJsmx.class);
    }
}
