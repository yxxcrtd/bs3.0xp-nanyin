package com.joyintech.tams.model.impairment.batch.eclcalculation.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModEclResultProcess;
import com.joyintech.tams.model.impairment.batch.common.utils.DataHandleUtil;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationResultDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModEclResult;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EclCalculationWriter extends BaseWriter<EclCalculationResultDto> {
    /**
     * Mapper
     */
    @Autowired
    private EclCalculationMapper eclCalculationMapper;
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;

    @Override
    public void write(List<? extends EclCalculationResultDto> items) throws Exception {
        List<EclCalculationResultDto> resultList = (List<EclCalculationResultDto>) items;
        List<ModConfigUseage> listModConfigUseage = new ArrayList<ModConfigUseage>();
        List<ModEclResultProcess> listModEclResultProcess = new ArrayList<ModEclResultProcess>();
        List<ModEclResult> listModEclResult = new ArrayList<ModEclResult>();
        if (resultList != null && resultList.size() > 0) {
            for (EclCalculationResultDto dto : resultList) {
                listModEclResultProcess.add(dto.getModEclResultProcess());
                listModEclResult.add(dto.getModEclResult());
                if (StringUtil.isNotEmpty(dto.getModConfigUseage().getSeqno())) {
                    listModConfigUseage.add(dto.getModConfigUseage());
                }
            }
            insertModConfigUseage(listModConfigUseage);
            insertModEclResultProcess(listModEclResultProcess);
            insertModEclResult(listModEclResult);
        }
    }

    /**
     * 插入结果集
     * @param listModEclResult
     */
    private void insertModEclResult(List<ModEclResult> listModEclResult) {
        if (listModEclResult == null || listModEclResult.isEmpty()) {
            return;
        }
        // 定义一个固定大小插入存放集合
        List<List<ModEclResult>> list = DataHandleUtil.splitList(listModEclResult);
        for (List<ModEclResult> listInsert : list) {
            if (CollectionUtils.isNotEmpty(listInsert)) {
                eclCalculationMapper.insertModEclResult(listInsert);
            }
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
