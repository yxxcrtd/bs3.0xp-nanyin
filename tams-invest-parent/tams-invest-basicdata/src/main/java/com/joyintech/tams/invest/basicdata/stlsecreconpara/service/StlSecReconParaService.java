package com.joyintech.tams.invest.basicdata.stlsecreconpara.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.stlsecreconpara.dto.StlSecReconParaDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对账参数设置
 */
@Service
public class StlSecReconParaService extends CommonExportService<StlSecReconParaDto>
        implements BaseProcessService<StlSecReconParaDto> {

    /**
     * 继承框架doComplete，完成增删改
     * @param entity 业务实体
     * @param type 操作类型
     */
    @Override
    public void doComplete(StlSecReconParaDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        String action = StringUtil.EMPTY;
        // 删除
        if (entity.getOperateType().equals("DELETE")) {
            action = "delete";
            // 插入
        } else if (entity.getOperateType().equals("NEW")) {
            action = "insert";
        } else {
            // 更新
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/" + action, entity, Integer.class);
    }

    /**
     * 分页查询
     *
     * @param pageParam 画面参数
     * @return 分页数据
     */
    public Page<StlSecReconParaDto> findPage(Map<String, Object> pageParam) {
        // 通过微服务调用查询分页数据
        return restTemplate.postForPage(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/selectForPage",
                pageParam, // 画面参数
                StlSecReconParaDto.class);
    }

    /**
     * 查询券商ID的下拉列表
     * @return
     */
    public List<SuggestDto> findAllBrokerIds(Map<String, Object> param) {
        // 通过微服务查询券商ID的下拉列表
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/findAllBrokerIds",
                param, SuggestDto.class);
    }

    /**
     * 查询券商ID的下拉列表-SELECT
     * @return
     */
    public List<SelectOptionDto> findAllBrokerIdsSelect(Map<String, Object> param) {
        // 通过微服务查询券商ID的下拉列表
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/findAllBrokerIdsSelect",
                param, SelectOptionDto.class);
    }


    /**
     * 通过主键查找画面元素
     * @param seqNo 主键序号
     * @return
     */
    public StlSecReconParaDto findEntityByPrimarykey(String seqNo) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("seqNo", seqNo);
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/findEntityByPrimarykey",
                param, StlSecReconParaDto.class);
    }

    /**
     * 查询所有记录
     * @param parameters
     * @return
     */
    public List<StlSecReconParaDto> selectAllList(Map<String, Object> parameters) {
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/selectAllList",
                parameters, StlSecReconParaDto.class);
    }

    /**
     * 文件导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/basicdata/stlsecreconpara/conf/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }

    /**
     * 导出时调用分页
     * {@inheritDoc}
     */
    @Override
    public Page<StlSecReconParaDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if("configDate".equals(propName)){
            if(value instanceof Date){
                cell.setCellValue(DateUtil.format((Date)value));
            }else if (value instanceof Long){
                cell.setCellValue(DateUtil.format(new Date((Long) value)));
            }
        }
    }

    /**
     * 检查dto 是否已存在
     * @return
     */
    public Boolean checkParaIsExist(StlSecReconParaDto dto) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/checkParaIsExist",
                dto, Boolean.class);
    }
}
