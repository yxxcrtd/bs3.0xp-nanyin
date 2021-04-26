package com.joyintech.tams.settle.batch.datacreate.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.settle.batch.datacreate.dto.StlDataCreateWriteDto;
import com.joyintech.tams.settle.batch.datacreate.service.StlDataCreateService;

/**
 * 生成结算数据文件
 * @author 陈卓
 * @date 2020/11/24
 * @see StlDataCreateWriter
 * @since 1.0
 */
@Service
@Lazy
public class StlDataCreateWriter extends BaseWriter<NeedToWriteEntity> {
	
    @Autowired
    private StlDataCreateService stlDataCreateService;

    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        for (NeedToWriteEntity item : items) {
            if(item instanceof StlDataCreateWriteDto) {
            	StlDataCreateWriteDto stlDataCreateWriteDto = (StlDataCreateWriteDto)item;
            	stlDataCreateService.writeData(stlDataCreateWriteDto);
            }
        }
    }
}
