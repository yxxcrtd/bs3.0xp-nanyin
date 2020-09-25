/*
 * 文件名：RiskTrialDayTimeReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.risktrialrds.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.RskIndividAssetDel;
import com.joyintech.tams.common.base.entity.RskIndividSpecialStage;
import com.joyintech.tams.common.base.entity.RskIndividUnite;
import com.joyintech.tams.invest.common.risktrialrds.dto.SupervisionParameterDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.Task;
import com.joyintech.tams.invest.common.risktrialrds.service.TaskQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日终日间批处理实体
 * @author 裴家俊
 * @version 1.0 2019年09月02日
 * @see RiskTrialDayTimeReaderResult
 * @since 1.0
 */
public class RiskTrialDayTimeReaderResult extends NeedOperateEntity {

 /*   private List<RskIndividSpecialStage> rskIndividSpecialStageList;

    private List<RskIndividSpecialStage> rskIndividSpecialStageAstList;

    private List<RskIndividUnite> rskIndividUniteList;

    private List<RskIndividAssetDel> rskIndividAssetDelList;

    private List<SupervisionParameterDto> supervisionParameterDtoList;*/

    private Map<String,Object> mapOther=new HashMap<>();

    private Task taskQueue;

    /*public List<RskIndividSpecialStage> getRskIndividSpecialStageList() {
        return rskIndividSpecialStageList;
    }

    public void setRskIndividSpecialStageList(List<RskIndividSpecialStage> rskIndividSpecialStageList) {
        this.rskIndividSpecialStageList = rskIndividSpecialStageList;
    }

    public List<RskIndividSpecialStage> getRskIndividSpecialStageAstList() {
        return rskIndividSpecialStageAstList;
    }

    public void setRskIndividSpecialStageAstList(List<RskIndividSpecialStage> rskIndividSpecialStageAstList) {
        this.rskIndividSpecialStageAstList = rskIndividSpecialStageAstList;
    }

    public List<RskIndividUnite> getRskIndividUniteList() {
        return rskIndividUniteList;
    }

    public void setRskIndividUniteList(List<RskIndividUnite> rskIndividUniteList) {
        this.rskIndividUniteList = rskIndividUniteList;
    }

    public List<RskIndividAssetDel> getRskIndividAssetDelList() {
        return rskIndividAssetDelList;
    }

    public void setRskIndividAssetDelList(List<RskIndividAssetDel> rskIndividAssetDelList) {
        this.rskIndividAssetDelList = rskIndividAssetDelList;
    }

    public List<SupervisionParameterDto> getSupervisionParameterDtoList() {
        return supervisionParameterDtoList;
    }

    public void setSupervisionParameterDtoList(List<SupervisionParameterDto> supervisionParameterDtoList) {
        this.supervisionParameterDtoList = supervisionParameterDtoList;
    }*/

    public Task getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(Task taskQueue) {
        this.taskQueue = taskQueue;
    }

    public Map<String, Object> getMapOther() {
        return mapOther;
    }

    public <T> void setMapOther(String key,List<T> t) {
        this.mapOther.put(key,t);
    }
}
