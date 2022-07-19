/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.config.prodbonusplanmanage.service;


import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.*;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dao.ProdBonusplanMapper;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;

/**
 * 记账凭证查询service
 *
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see ProdBonusplanManager001Service
 * @since 2.0
 */
@Service(value="serviceProdBonusplanManager001Service")
@Transactional(rollbackFor = Exception.class)
public class ProdBonusplanManager001Service extends CommonExportService<ProdBonusplanDto> {


    @Autowired
    private ProdBonusplanMapper prodBonusplanMapper;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager001Service.class);



    public List<ProdBonusplanDto> searchReportsNew(Map<String,Object> params){
        List<ProdBonusplanDto> result = prodBonusplanMapper.searchReports(params);
        // 遍历构造数据
        if(CollectionUtils.isNotEmpty(result)){
            result.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
                item.setBonusModeName(SysDataUtil.getNameFromDataDict(DictCode.信披_分红方式, item.getBonusMode()));
            });
        }
        return result;
    }
    /**
     * 指标库查询service <一句话功能简述> <功能详细描述>
     *
     * @param params
     * @return
     */
    public Page<ProdBonusplanDto> findByPage(int page, int length, Map<String, Object> params)
    {
        String dataStatus = (String)params.get("dataStatus");
        String[] dataStatusArr = null;
        if(StringUtil.isNotEmpty(dataStatus)){
            dataStatusArr = dataStatus.split(",");
        }
        params.put("dataStatusArr", dataStatusArr);

        String bonusMode = (String)params.get("bonusMode");
        String[] bonusModeArr = null;
        if(StringUtil.isNotEmpty(bonusMode)){
            bonusModeArr = bonusMode.split(",");
        }
        params.put("bonusModeArr", bonusModeArr);

        String prodId = (String)params.get("prodId");
        String[] prodIdArr = null;
        if(StringUtil.isNotEmpty(prodId)){
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);
        Page<ProdBonusplanDto> pages = super.queryForPageForSelectReturn(page, length, () -> prodBonusplanMapper.searchReports(params));
        List<ProdBonusplanDto> list = pages.getRoot();
        // 遍历构造数据
        if(CollectionUtils.isNotEmpty(list)){
            list.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
                item.setBonusModeName(SysDataUtil.getNameFromDataDict(DictCode.信披_分红方式, item.getBonusMode()));

            });
            pages.setRoot(list);
        }
        return pages;
    }

    /**
     * 根据产品代码 查询有效的 分红计划信息
     * @param prodId
     * @return
     */
    public ProdBonusplanDto selectByProdId(String prodId) {

        return prodBonusplanMapper.selectByProdId(prodId,DictCodeListExt3.信披_数据状态_有效.getValue());
    }



}
