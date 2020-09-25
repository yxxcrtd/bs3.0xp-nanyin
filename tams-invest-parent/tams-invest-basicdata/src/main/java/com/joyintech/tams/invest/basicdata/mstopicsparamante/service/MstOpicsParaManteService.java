/*
 * 文件名：MstOpicsParaMante001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstopicsparamante.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.basicdata.mstopicsparamante.dto.MstOpicsParaManteDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * OPICS参数维护业务层 
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaManteService
 * @since 1.0
 */
@Service
@Transactional
public class MstOpicsParaManteService extends CommonExportService<MstOpicsParaManteDto> implements BaseProcessService<MstOpicsParaManteDto> {

    /**
     * 查询按钮的查询方法
     * @param pageParam map
     * @return Page
     * @see
     * @since 1.0
     */
    public Page<MstOpicsParaManteDto> selectOpicsParaManteInfo(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstOpicsParaMante/selectOpicsParaManteInfo",
            pageParam, MstOpicsParaManteDto.class);
    }

    /**
     * 通过主键查询方法 
     * @param param map
     * @return MstOpicsParaManteDto
     * @see
     * @since 1.0
     */
    public MstOpicsParaManteDto selectByPrimaryKey(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstOpicsParaMante/selectByPrimaryKey",
            param, MstOpicsParaManteDto.class);
    }

    /**
     * doComplete{@inheritDoc}
     */
    @Override
    public void doComplete(MstOpicsParaManteDto entity, OperationType type) {
//        if (OperationType.DELETE.equals(type)) {
//            OpicsParamMapper.deleteByPrimaryKey(entity);
//        }
//        else if (OperationType.NEW.equals(type)) {
//            OpicsParamMapper.insert(entity);
//        }
//        else {
//            OpicsParamMapper.updateByPrimaryKeySelective(entity);
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("entity", entity);
//        map.put("type", type);
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstOpicsParaMante/doComplete/" + type, entity, Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> parm = exportParam.getSelectParam();
        parm.put("MST18", DictCodeList.共通业务类型_OPICS参数维护.getValue());
        exportParam.config("/com/joyintech/tams/invest/basicdata/mstopicsparamante/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MstOpicsParaManteDto> selectPageData(Map<String, Object> selectParam) {
        return selectOpicsParaManteInfo(selectParam);
    }
}
