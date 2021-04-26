package com.joyintech.tams.settle.batch.datadown.processor;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.SzdZqbd;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SzdZqbdProcessor
 * @since 1.0
 */
@Service
@Lazy
public class SzdZqbdProcessor extends StlDataDownProcessor<SzdZqbd> {
    @Override
    protected SzdZqbd mapToEntity(Map<String, Object> rowData) {
        SzdZqbd zqbd = BeanUtil.mapToObject(rowData, SzdZqbd.class);
        zqbd.setBy1((String)rowData.get("by"));
        return zqbd;
    }
}
