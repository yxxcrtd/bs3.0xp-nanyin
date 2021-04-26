package com.joyintech.tams.settle.batch.datadown.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.settle.batch.datadown.dto.DataToWriteEntity;
import com.joyintech.tams.settle.batch.datadown.dto.StepContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/7/2
 * @see StlDataDownWriter
 * @since 1.0
 */
public abstract class StlDataDownWriter<T> extends BaseWriter<NeedToWriteEntity> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        StepContext stepContext = StepContext.get(getStepName());
        for (NeedToWriteEntity item : items) {
            DataToWriteEntity entity = (DataToWriteEntity)item;
            try {
                save((T)entity.getEntity());
            }
            catch (Exception e) {
                stepContext.errorIncrement(BeanUtil.getProperty(entity.getEntity(), "dataFile"));
//                throw e;  //写入失败则忽略此条数据
            }
        }
    }

    private String getStepName() {
        String className = getClass().getSimpleName();
        return StringUtil.replace(className, "Writer", StringUtils.EMPTY);
    }

    protected abstract void save(T entity);
}
