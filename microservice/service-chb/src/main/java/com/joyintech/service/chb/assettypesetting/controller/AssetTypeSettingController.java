package com.joyintech.service.chb.assettypesetting.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.chb.assettypesetting.dto.AssetTypeSetting001Dto;
import com.joyintech.tams.svreport.chb.assettypesetting.service.AssetTypeSetting001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/AssetTypeSetting")
public class AssetTypeSettingController {

    @Autowired
    private AssetTypeSetting001Service assetTypeSetting001Service;

    /**
     * 分页查询
     * @param param
     * @return
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<AssetTypeSetting001Dto> selectForPage(@RequestBody Map<String, Object> param) {
        return assetTypeSetting001Service.selectForPage(param);
    }

    /**
     * 点击左侧列表数据事件
     * @param param
     * @return
     */
    @PostMapping("selectById/{page}/{length}")
    public Page<AssetTypeSetting001Dto> selectById(@RequestBody Map<String, Object> param) {
        return assetTypeSetting001Service.selectById(param);
    }

    /**
     * 新建保存
     * @param dto
     * @return
     */
    @PostMapping("doSave")
    public Integer doSave(@RequestBody AssetTypeSetting001Dto dto) {
        return assetTypeSetting001Service.doSave(dto);
    }

    /**
     * 删除(全删全插)
     * @param dto
     * @return
     */
    @PostMapping("doDelete")
    public Integer doDelete(@RequestBody AssetTypeSetting001Dto dto) {
        return assetTypeSetting001Service.doDelete(dto);
    }

    /**
     * 查询Entity
     * @param typeuuid
     * @return
     */
    @PostMapping("selectEntityBySearchMap")
    public AssetTypeSetting001Dto selectEntityBySearchMap(@RequestBody String typeuuid) {
        return assetTypeSetting001Service.selectEntityBySearchMap(typeuuid);
    }

    /**
     * 新建校验
     * @param param
     * @return
     */
    @PostMapping("isConfilct")
    public AssetTypeSetting001Dto isConfilct(@RequestBody Map<String, Object> param) {
        return assetTypeSetting001Service.isConfilct(param);
    }

    /**
     *
     * @param param
     * @return
     */
    @PostMapping("findExPage")
    public List<AssetTypeSetting001Dto> findExPage(@RequestBody Map<String, Object> param) {
        return assetTypeSetting001Service.findExPage(param);
    }

    /**
     *
     * @param dto
     * @return
     */
    @PostMapping("doExDelete")
    public Integer doExDelete(@RequestBody AssetTypeSetting001Dto dto) {
        return assetTypeSetting001Service.doExDelete(dto);
    }
}
