/*
 * 文件名：MstProjectCatInfo002Service.java
 * 版权：Copyright 2017-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.637
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.projectcatinfo.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import org.springframework.stereotype.Service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.framework.util.SpringContextUtil;

/**
 * 项目类资产估值行情信息
 * 
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.637
 * @see MstProjectCatInfo002Service
 * @since 1.0
 */
@Service
public class MstProjectCatInfo002Service extends BaseService<MstProjectCatInfoDto> implements BaseProcessService<MstProjectCatInfoDto> {

    /**
     * 根据业务主键查询明细信息
     * 
     * @param pageParam 业务主键查询条件
     * @return 明细信息
     * @see
     * @since 1.0
     */
    public MstProjectCatInfoDto selectInfo(Map<String, Object> pageParam) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstProjectCatInfo/selectInfo", pageParam,
            MstProjectCatInfoDto.class);
    }

    @Override
    public void doComplete(MstProjectCatInfoDto dto, OperationType type) {
        String action = "update";
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        dto.setFinprodId(dto.getFinProdMarketId());
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstProjectCatInfo/" + action, dto,
            Integer.class);
    }

    @Override
    public String generateTitle(MstProjectCatInfoDto entity, OperationType type) {
        return SpringContextUtil.getTextValue("MstProjectCatInfo002.finprodId") + ":"
               + entity.getFinProdMarketId() + "-"
               + SpringContextUtil.getTextValue("MstProjectCatInfo002.finProdName") + ":"
               + entity.getFinProdName() + "-"
               + SpringContextUtil.getTextValue("MstProjectCatInfo002.valDate") + ":"
               + DateUtil.format(entity.getValDate());
    }

    /**
     * 新建界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     *
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssetCodeList(Map<String, Object> map) {
        List<SelectOptionDto> temp = restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstProjectCatInfo/getAssetCodeList", map,
                SelectOptionDto.class);

        List<SelectOptionDto> result = new ArrayList<>();
        /*SelectOptionDto itemAdd = null;*/
        for (SelectOptionDto dto : temp){
            /*itemAdd = new SelectOptionDto();*/
            dto.setId(dto.getId());
            dto.setText(dto.getText()+"("+dto.getId()+")");
            result.add(dto);
        }


        return result;
    }

    /**
     * 通过资产代码查询资产名称和管理人 完成资产代码画面联动
     *
     * @param map 查询参数
     * @return dto
     * @see
     * @since 1.0
     */
    public MstProjectCatInfoDto selectNameAndFundManager(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                        + "/MstNetAssetQuotationInfo/selectNameAndFundManager",
                map, MstProjectCatInfoDto.class);
    }
}
