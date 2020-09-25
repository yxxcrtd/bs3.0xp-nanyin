package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service;


import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dao.PoiReportModelManageChildren001Mapper;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 报表子表Controller(微服务层)
 *
 * @author kx
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManageChildren001Service extends CommonExportService<PoiReportModelManageChildren001Dto> {


    @Autowired
    private PoiReportModelManageChildren001Mapper poiReportModelManageChildren001Mapper;


    /**
     * 分页查询事件
     *
     * @param params
     * @return
     */
    public Page<PoiReportModelManageChildren001Dto> searchPage(Map<String, Object> params) {
        int page = Integer.parseInt(params.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(params.get(GlobalConstant.PARAM_LENGTH).toString());
        String tabNum = (String) params.get("tabNum");
        if (!StringUtil.isBlank(tabNum)) {
            params.put("tabNumList", StringUtil.splitToList(GlobalConstant.COMMA, tabNum));
        }
        String subType = (String) params.get("subType");
        if (!StringUtil.isBlank(subType)) {
            params.put("subTypeList", StringUtil.splitToList(GlobalConstant.COMMA, subType));
        }
        String subFormType = (String) params.get("subFormType");
        if (!StringUtil.isBlank(subFormType)) {
            params.put("subFormTypeList", StringUtil.splitToList(GlobalConstant.COMMA, subFormType));
        }
        String subTmpType = (String) params.get("subTmpType");
        if (!StringUtil.isBlank(subTmpType)) {
            params.put("subTmpTypeList", StringUtil.splitToList(GlobalConstant.COMMA, subTmpType));
        }
        String subChartType = (String) params.get("subChartType");
        if (!StringUtil.isBlank(subChartType)) {
            params.put("subChartTypeList", StringUtil.splitToList(GlobalConstant.COMMA, subChartType));
        }
        Page<PoiReportModelManageChildren001Dto> pageList = queryForPage(
                page, legth, () -> poiReportModelManageChildren001Mapper.queryForPage(params));
        for (PoiReportModelManageChildren001Dto poiReportModelManageChildren001Dto : pageList.getRoot()) {
            poiReportModelManageChildren001Dto.setSubTypeStr(SysDataUtil.getNameFromDataDict(DictCode.poi模板类型, poiReportModelManageChildren001Dto.getSubType()));
            poiReportModelManageChildren001Dto.setSubFormTypeStr(SysDataUtil.getNameFromDataDict(DictCode.poi表格类型, poiReportModelManageChildren001Dto.getSubFormType()));
            poiReportModelManageChildren001Dto.setSubTmpTypeStr(SysDataUtil.getNameFromDataDict(DictCode.poi表格样式, poiReportModelManageChildren001Dto.getSubTmpType()));
            poiReportModelManageChildren001Dto.setSubWidthStr(SysDataUtil.getNameFromDataDict(DictCode.poi表格宽度, poiReportModelManageChildren001Dto.getSubWidthStr()));
            poiReportModelManageChildren001Dto.setSubChartTypeStr(SysDataUtil.getNameFromDataDict(DictCode.poi图表类型, poiReportModelManageChildren001Dto.getSubChartType()));
            poiReportModelManageChildren001Dto.setStatusStr(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, poiReportModelManageChildren001Dto.getStatus()));
        }
        return pageList;
    }

    /**
     * 获取模板编号下拉框初始化数据
     *
     * @param param
     * @return
     */
    public List<SelectOptionDto> queryTabNumList(Map<String, Object> param) {
        param.put("statusEffective", DictCodeListExt1.维护数据状态_有效.getValue());
        return poiReportModelManageChildren001Mapper.queryPoiIdList(param);
    }

    /**
     * 根据主键删除数据
     *
     * @param dto
     */
    public void doDelete(PoiReportModelManageChildren001Dto dto) {
        List<PoiReportModelManageChildren001Dto> keyList = dto.getSelectedItem();
        // 批量删除poi查询sql管理数据
//        poiReportModelManageChildren001Mapper.deleteByKeyList(keyList);
        for (PoiReportModelManageChildren001Dto poiReportModelManageChildren001Dto : keyList) {
            if (StringUtils.isNotBlank(poiReportModelManageChildren001Dto.getId())) {
                PoiReportModelManageChildren001Dto poiReportModelManageChildren001DtoMine = poiReportModelManageChildren001Mapper.selectById(poiReportModelManageChildren001Dto.getId());
                if (poiReportModelManageChildren001DtoMine != null) {
                    poiReportModelManageChildren001DtoMine.setUpdateUser(CommonUtil.getUserName());
                    poiReportModelManageChildren001DtoMine.setUpdateTime(DateUtil.now());
                    poiReportModelManageChildren001DtoMine.setStatus("D");
                    poiReportModelManageChildren001Mapper.updateByPrimaryKeySelective(poiReportModelManageChildren001DtoMine);
                }
            }
        }
    }


    /**
     * 保存提交
     *
     * @param dto
     */
    public void doSave(PoiReportModelManageChildren001Dto dto) {
        if (dto != null) {
            PoiReportModelManageChildren001Dto poiReportModelManageChildren001Dto = poiReportModelManageChildren001Mapper.selectById(dto.getId());
            if (poiReportModelManageChildren001Dto != null) {
                dto.setUpdateUser(CommonUtil.getUserName());
                dto.setUpdateTime(DateUtil.now());
                dto.setStatus("E");
                if (StringUtils.isBlank(dto.getSubDefualt())) {
                    dto.setSubDefualt("空");
                }
                poiReportModelManageChildren001Mapper.updateByPrimaryKeySelective(dto);
            } else {
                if (StringUtils.isBlank(dto.getSubDefualt())) {
                    dto.setSubDefualt("空");
                }
                dto.setCreateDept(CommonUtil.getDeptCode());
                dto.setCreateUser(CommonUtil.getUserName());
                dto.setCreateTime(DateUtil.now());
                dto.setStatus("E");
                poiReportModelManageChildren001Mapper.insertSelective(dto);
            }
        }
    }

    /**
     * 根据主键查询产品文件产品要素信息
     *
     * @param param
     * @return
     */
    public PoiReportModelManageChildren001Dto selectByPrimaryKey(Map<String, Object> param) {
        String id = param.get("id").toString();
        return poiReportModelManageChildren001Mapper.selectById(id);
    }


}
