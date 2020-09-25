/*
 * 文件名：ValRepurchaseAssetQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.repurchase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.assets.repurchase.dao.ValRepurchaseAssetQuery001Mapper;
import com.joyintech.service.valuation.assets.repurchase.dto.ValRepurchaseAssetQuery001Dto;

/**
 * 回购类资产查询
 * 
 * @author wangqidong
 * @version 1.0 2018年10月19日
 * @see ValRepurchaseAssetQuery001Service
 * @since 1.0
 */
@Service(value="serviceValRepurchaseAssetQuery001Service")
@Transactional(rollbackFor = Exception.class)
public class ValRepurchaseAssetQuery001Service extends CommonExportService<ValRepurchaseAssetQuery001Dto> {

    /**
     * valRepurchaseAssetQuery001Mapper
     */
    @Autowired
    private ValRepurchaseAssetQuery001Mapper valRepurchaseAssetQuery001Mapper;

    /**
     * 资产下拉框获取
     * 
     * @param params params
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAsset(Map<String, Object> params) {
        List<ValRepurchaseAssetQuery001Dto> resultList = valRepurchaseAssetQuery001Mapper.getAsset(
            params);
        List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
        for (ValRepurchaseAssetQuery001Dto single : resultList) { // 组装select节点对象
            SelectOptionDto selectOptionDto = new SelectOptionDto();
            selectOptionDto.setId(single.getFinprodId());
            selectOptionDto.setText(single.getFinprodAbbr() + "(" + single.getFinprodId() + ")");
            selectOptionDtoList.add(selectOptionDto);
        }
        return selectOptionDtoList;
    }

    /**
     * 加载表格
     * 
     * @param params params
     * @return Page<ValRepurchaseAssetQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ValRepurchaseAssetQuery001Dto> findPage(int page, int length, Map<String, Object> params)
        throws Exception {

        // String repAsset = (String)params.get("repAsset");
        String finprodType = (String)params.get("finprodType");

       /* String[] repAssetArr = null;
        if (StringUtil.isNotEmpty(repAsset)) {
            repAssetArr = repAsset.split(",");
        }*/
        String[] finprodTypeArr = {"F02", "F03", "F04", "F05"};
        if (StringUtil.isNotEmpty(finprodType)) {
            finprodTypeArr = finprodType.split(",");
        }
        params.put("finprodTypeArr", finprodTypeArr);
        // params.put("repAssetArr", repAssetArr);

        return super.queryForPageForSelectReturn(page, length, () -> valRepurchaseAssetQuery001Mapper.queryPageList(params));
    }

    /**
     * 导出功能
     * 
     * @param paraMap paraMap
     * @return List<ValRepurchaseAssetQuery001Dto>
     * @see
     * @since 1.0
     */
    public List<ValRepurchaseAssetQuery001Dto> findAll(Map<String, Object> paraMap) {

        String finprodType = (String)paraMap.get("finprodType");
        String[] finprodTypeArr = {"F02", "F03", "F04", "F05"};
        if (StringUtil.isNotEmpty(finprodType)) {
            finprodTypeArr = finprodType.split(",");
        }
        paraMap.put("finprodTypeArr", finprodTypeArr);
        return valRepurchaseAssetQuery001Mapper.queryPageList(paraMap);
    }
}
