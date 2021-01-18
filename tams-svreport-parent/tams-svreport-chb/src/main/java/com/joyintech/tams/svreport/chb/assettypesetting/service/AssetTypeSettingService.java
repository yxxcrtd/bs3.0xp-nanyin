package com.joyintech.tams.svreport.chb.assettypesetting.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.common.base.entity.ChbAssetTypeMapping;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.assettypesetting.dto.AssetTypeSetting001Dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AssetTypeSettingService extends BaseService<AssetTypeSetting001Dto> {

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    public Page<AssetTypeSetting001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/findPage",pageParam,
                AssetTypeSetting001Dto.class);
    }

    /**
     * 点击左侧列表数据事件
     * @param pageParam
     * @return
     */
    public Page<AssetTypeSetting001Dto> selectById(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/selectById",pageParam,
                AssetTypeSetting001Dto.class);
    }

    /**
     * 新建保存
     * @param dto
     * @throws Exception
     */
    public void doSave(AssetTypeSetting001Dto dto) throws Exception {
        dto.setCreateUser(LoginUserUtil.getUserName());//创建人
        dto.setUpdateUser(LoginUserUtil.getUserName());//更新人
        dto.setStatus(DictCodeList.数据状态_修改.getValue());//状态
        restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/doSave", dto, Integer.class);
    }

    /**
     *
     * @param dto
     * @throws Exception
     */
    public void doDelete(AssetTypeSetting001Dto dto) throws Exception{
        restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/doDelete", dto, Integer.class);
    }

    /**
     * 查询Entity
     * @param typeuuid
     * @return
     */
    public AssetTypeSetting001Dto selectEntityBySearchMap(String typeuuid) {
        return restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/selectEntityBySearchMap", typeuuid, AssetTypeSetting001Dto.class);

    }

    /**
     * 新建校验
     * @param map
     * @return
     */
    public AssetTypeSetting001Dto isConfilct(Map<String, Object> map) {
        return restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/isConfilct", map, AssetTypeSetting001Dto.class);
    }

    /**
     * 删除右侧列表数据
     * @param dto
     */
    public void doExDelete(AssetTypeSetting001Dto dto) {
        restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/doExDelete", dto, Integer.class);
    }

    /**
     *
     * @param pageParam
     * @return
     */
    public List<AssetTypeSetting001Dto> findExPage(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetTypeSetting/findExPage",pageParam,
                AssetTypeSetting001Dto.class);
    }

}
