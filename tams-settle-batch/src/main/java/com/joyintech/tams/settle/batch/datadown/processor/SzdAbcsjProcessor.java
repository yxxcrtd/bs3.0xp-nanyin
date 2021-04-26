package com.joyintech.tams.settle.batch.datadown.processor;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.SzdAbcsj;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdAbcsjProcessor
 * @since 1.0
 */
@Service
@Lazy
public class SzdAbcsjProcessor extends StlDataDownProcessor<SzdAbcsj> {
    @Override
    protected SzdAbcsj mapToEntity(Map<String, Object> rowData) {
        SzdAbcsj abcsj = BeanUtil.mapToObject(rowData, SzdAbcsj.class);
        abcsj.setBy1((String)rowData.get("by"));
        return abcsj;
    }
}
