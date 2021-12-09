package com.joyintech.tams.settle.batch.datadown.processor;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.SzdTzxx;
import com.joyintech.tams.common.base.entity.SzdTzxx;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 上中登权益登记通知信息TZXX接口
 * @author meifaxin
 * @date 2021/12/9
 * @see SzdTzxxProcessor
 * @since 1.0
 */
@Service
@Lazy
public class SzdTzxxProcessor extends StlDataDownProcessor<SzdTzxx> {
    @Override
    protected SzdTzxx mapToEntity(Map<String, Object> rowData) {
        SzdTzxx tzxx = BeanUtil.mapToObject(rowData, SzdTzxx.class);
        tzxx.setBy((String)rowData.get("by"));
        return tzxx;
    }
}
