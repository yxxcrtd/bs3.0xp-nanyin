package com.joyintech.service.disclosure.information.config.dispoireportmodelmanagechildren.controller;


import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service.PoiReportModelManageChildren001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 报表子表Controller(微服务层)
 *
 * @author kx
 */
@RestController
@RequestMapping("/PoiReportModelManageChildren001")
public class PoiReportModelManageChildren001Controller extends BaseController {

    @Autowired
    private PoiReportModelManageChildren001Service poiReportModelManageChildren001Service;

    /**
     * 分页查询事件
     *
     * @param param
     * @return
     */
    @PostMapping("/searchPage/{page}/{length}")
    public Page<PoiReportModelManageChildren001Dto> searchPage(@RequestBody Map<String, Object> param) {
        return poiReportModelManageChildren001Service.searchPage(param);
    }

    /**
     * 获取模板编号下拉框初始化数据
     *
     * @return
     */
    @PostMapping("/queryTabNumList")
    public List<SelectOptionDto> queryTabNumList(@RequestBody Map<String, Object> param) {
        return poiReportModelManageChildren001Service.queryTabNumList(param);
    }

    /**
     * 根据主键删除数据
     *
     * @param dto
     */
    @PostMapping("/doDelete")
    public void doDelete(@RequestBody PoiReportModelManageChildren001Dto dto) {
        poiReportModelManageChildren001Service.doDelete(dto);
    }

    /**
     * 保存提交
     *
     * @param dto
     */
    @PostMapping("/doSave")
    public void doSave(@RequestBody PoiReportModelManageChildren001Dto dto) {
        poiReportModelManageChildren001Service.doSave(dto);
    }

    /**
     * 根据主键查询poi查询sql
     *
     * @param param
     * @return
     */
    @PostMapping("/selectById")
    public PoiReportModelManageChildren001Dto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
        return poiReportModelManageChildren001Service.selectByPrimaryKey(param);
    }
}
