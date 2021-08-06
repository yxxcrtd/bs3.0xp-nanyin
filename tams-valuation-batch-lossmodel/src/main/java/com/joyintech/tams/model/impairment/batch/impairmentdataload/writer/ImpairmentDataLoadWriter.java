package com.joyintech.tams.model.impairment.batch.impairmentdataload.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModEclResultProcess;
import com.joyintech.tams.model.impairment.batch.common.constants.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.common.utils.DataHandleUtil;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImpairmentDataLoadWriter extends BaseWriter<ImpairmentDataLoadStep1ResultDto> {
    /**
     * Mapper
     */
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;

    @Override
    public void write(List<? extends ImpairmentDataLoadStep1ResultDto> items) throws Exception {
        List<ImpairmentDataLoadStep1ResultDto> resultList = (List<ImpairmentDataLoadStep1ResultDto>) items;
        List<ModConfigUseage> listModConfigUseage = new ArrayList<ModConfigUseage>();
        List<ModEclResultProcess> listModEclResultProcess = new ArrayList<ModEclResultProcess>();
        //清除缓存中评级信息数据
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class) != null) {
            CacheUtil.clearAppCache(GlobalConstant.RATING_INFO);
        }

        if (resultList != null && resultList.size() > 0) {
            for (ImpairmentDataLoadStep1ResultDto dto : resultList) {
                if (dto.getModEclResultProcess() != null && StringUtil.isNotEmpty(dto.getModEclResultProcess().getSeqno())) {
                    listModEclResultProcess.add(dto.getModEclResultProcess());
                }
                if (dto.getModConfigUseage() != null && StringUtil.isNotEmpty(dto.getModConfigUseage().getSeqno())) {
                    listModConfigUseage.add(dto.getModConfigUseage());
                }
            }
            insertModConfigUseage(listModConfigUseage);
            insertModEclResultProcess(listModEclResultProcess);
        }
    }

    /**
     * 插入结果集
     * @param listModConfigUseage
     */
    private void insertModConfigUseage(List<ModConfigUseage> listModConfigUseage) {
        if (listModConfigUseage == null || listModConfigUseage.isEmpty()) {
            return;
        }
        // 定义一个固定大小插入存放集合
        List<List<ModConfigUseage>> list = DataHandleUtil.splitList(listModConfigUseage);
        for (List<ModConfigUseage> listInsert : list) {
            if (CollectionUtils.isNotEmpty(listInsert)) {
                impairmentDataLoadMapper.insertModConfigUseage(listInsert);
            }
        }
    }

    /**
     * 插入结果集
     * @param listModEclResultProcess
     */
    private void insertModEclResultProcess(List<ModEclResultProcess> listModEclResultProcess) {
        if (listModEclResultProcess == null || listModEclResultProcess.isEmpty()) {
            return;
        }
        // 定义一个固定大小插入存放集合
        List<List<ModEclResultProcess>> list = DataHandleUtil.splitList(listModEclResultProcess);
        for (List<ModEclResultProcess> listInsert : list) {
            if (CollectionUtils.isNotEmpty(listInsert)) {
                impairmentDataLoadMapper.insertModEclResultProcess(listInsert);
            }
        }
    }

}
