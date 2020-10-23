package com.joyintech.service.disclosure.information.config.dispoiquery.controller;


import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import com.joyintech.tams.disclosure.information.config.dispoiquery.service.DisPoiQuery001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * poi查询sql管理Controller（微服务层）
 *
 * @author kx
 */
@RestController("backDisPoiQuery001Controller")
@RequestMapping("/DisPoiQuery001")
public class DisPoiQuery001Controller extends BaseController {

    @Autowired
    private DisPoiQuery001Service disPoiQuery001Service;

    /**
     * 分页查询事件
     *
     * @param param
     * @return
     */
    @PostMapping("/searchPage/{page}/{length}")
    public Page<DisPoiQuery001Dto> searchPage(@RequestBody Map<String, Object> param) {
        return disPoiQuery001Service.searchPage(param);
    }


    /**
     * 获取关联编号下拉框初始化数据
     *
     * @return
     */
    @PostMapping("/queryPoiIdList")
    public List<SelectOptionDto> queryPoiIdList(@RequestBody Map<String, Object> param) {
        return disPoiQuery001Service.queryPoiIdList(param);
    }

    /**
     * 根据主键查询poi查询sql
     *
     * @param param
     * @return
     */
    @PostMapping("/selectById")
    public DisPoiQuery001Dto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
        return disPoiQuery001Service.selectByPrimaryKey(param);
    }

    /**
     * 根据主键删除数据
     *
     * @param dto
     */
    @PostMapping("/doDelete")
    public void doDelete(@RequestBody DisPoiQuery001Dto dto) {
        disPoiQuery001Service.doDelete(dto);
    }

    /**
     * 保存提交
     * @param dto
     */
    @PostMapping("/doSave")
    public void doSave(@RequestBody DisPoiQuery001Dto dto){
        disPoiQuery001Service.doSave(dto);
    }


}
