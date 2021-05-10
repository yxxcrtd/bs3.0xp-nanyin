package com.joyintech.tams.valuation.basicdata.asset.option.service; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luo
 * 修改时间：2019-12-19
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.option.dto.FinOptionTargetDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FinOptionTargetService extends CommonExportService<FinOptionTargetDto> implements BaseProcessService<FinOptionTargetDto> {

    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Autowired
    private FinProductAddMapper finProductAddMapper;

    @Override
    public void doComplete(FinOptionTargetDto entity, OperationType type) {
        String action;
        FinProductAdd finProductAdd = new FinProductAdd();
        FinProductType finProductType = new FinProductType();
        finProductType.setFinprodId(entity.getTargetId());
        finProductType.setBranch(0l);
        if (OperationType.DELETE.equals(type)) {
            finProductTypeMapper.deleteByPrimaryKey(finProductType);
            // add by zwq on 2021/03/06 start   将页面的数据来源删除
            finProductAddMapper.deleteByPrimaryKey(entity.getTargetId());
            // add by zwq on 2021/03/06 end
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            finProductType.setType8(DictCodeListExt3.平安资产类型_期权.getValue());
            FinProductTypeConfig finProductTypeConfig = new FinProductTypeConfig();
            finProductTypeConfig.setConfigType(DictCodeListExt2.配置类型_无标的投管资产类型映射行内资产分类.getValue());
            finProductTypeConfig.setInputValue1(DictCodeListExt3.平安资产类型_期权.getValue());
//            finProductTypeConfig = restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME + "/core/getAssetFiveType",
//                    finProductTypeConfig, FinProductTypeConfig.class);
            finProductType.setType9(finProductTypeConfig.getOutputValue2());
            finProductType.setType10(finProductTypeConfig.getOutputValue3());
            finProductType.setType11(finProductTypeConfig.getOutputValue4());
            finProductType.setType12(finProductTypeConfig.getOutputValue5());
            finProductTypeMapper.insertSelective(finProductType);

            // add by zwq on 2021/03/06 start   将页面的数据来源字段入表
            finProductAdd.setFinprodId(entity.getTargetId());
            finProductAdd.setCreateUser(entity.getCreateUser());
            finProductAdd.setCreateTime(entity.getCreateTime());
            finProductAdd.setUpdateTime(entity.getUpdateTime());
            finProductAdd.setUpdateUser(entity.getUpdateUser());
            finProductAdd.setDataSource(entity.getDataSource());
            finProductAddMapper.insert(finProductAdd);
            // add by zwq on 2021/03/06 end
            action = "insert";
        }
        else {
            // add by zwq on 2021/03/06 start   将页面的数据来源字段更新
            finProductAdd.setFinprodId(entity.getTargetId());
            finProductAdd.setCreateUser(entity.getCreateUser());
            finProductAdd.setCreateTime(entity.getCreateTime());
            finProductAdd.setUpdateTime(entity.getUpdateTime());
            finProductAdd.setUpdateUser(entity.getUpdateUser());
            finProductAdd.setDataSource(entity.getDataSource());
            finProductAddMapper.updateByPrimaryKey(finProductAdd);
            // add by zwq on 2021/03/06 end
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinOptionTarget/" + action, entity, Integer.class);
    }


    public List<SuggestDto> getFinprodcodeList(String param) {
        return restTemplate.getForList(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME +
            "/FinOptionTarget/getFinprodcodeList?getFinprodcodeList=" + param,
            SuggestDto.class);
    }

    /**
     * 根据id获取期权标的信息
     * @param code
     * @return
     */
    public FinOptionTargetDto getFinprodcodebyId(String code){
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME +"/FinOptionTarget/getFinprodcodebyId?code=" + code,
            FinOptionTargetDto.class);
    }



    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/valuation/basicdata/asset/option/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"vdate") || StringUtil.equals(propName,"mdate")){
            if(value instanceof Date){
                cell.setCellValue(DateUtil.format((Date)value));
            }else if (value instanceof Long){
                cell.setCellValue(DateUtil.format(new Date((Long) value)));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinOptionTargetDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 分页获取期权标的信息
     * @param pageParam
     * @return
     */
    public Page<FinOptionTargetDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinOptionTarget/findPage",
            pageParam, FinOptionTargetDto.class);
    }

    public List<FinOptionTargetDto> selectForList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinOptionTarget/findList",
                pageParam, FinOptionTargetDto.class);
    }
    /**
     * 新增操作时 保存上传附件 （不涉及指令表操作）
     */
    @Override
    public void doInsert(FinOptionTargetDto entity, OperationType type){
        restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinOptionTarget/doInsert",
            entity, Integer.class);
    }

    /**
     * 更新操作时 更新上传附件 （不涉及指令表操作）
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinOptionTargetDto entity){
        restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinOptionTarget/doInsert",
            entity, Integer.class);
    }

}
