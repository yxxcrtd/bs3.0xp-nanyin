/*
 * 文件名：ValIncomeStatisticsQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.bankaccount.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.bankaccount.dao.ValIncomeStatisticsQuery001Mapper;
import com.joyintech.tams.valuation.bankaccount.dto.ValIncomeStatisticsQuery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.common.base.entity.BokBookset;

/**
 * 收入统计查询
 * 
 * @author HuangJian
 * @version 1.0 2018年11月1日
 * @see ValIncomeStatisticsQuery001Service
 * @since 1.0
 */
@Service
public class ValIncomeStatisticsQuery001Service extends CommonExportService<ValIncomeStatisticsQuery001Dto> {

    /**
     * valIncomeStatisticsQuery001Mapper
     */
    @Autowired
    private ValIncomeStatisticsQuery001Mapper valIncomeStatisticsQuery001Mapper;
    
//    /**
//     * SysParaSet001Service
//     */
//    @Autowired
//    private SysParaSet001Service sysParaSet001Service;

    /**
     * 获取账套的名称
     * 
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getBookSetName() {
        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        List<BokBookset> list = valIncomeStatisticsQuery001Mapper.getBookSetName();
        list.forEach(item -> {
            listSelectDto.add(createSelect(item.getBooksetId(), item.getBooksetName()));
        });
        return listSelectDto;
    }

    /**
     * 格式化创建SelectOptionDto对象
     * 
     * @param selectId 下拉Id值
     * @param text 下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text) {
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }

    /**
     * 查询
     * 
     * @param params Map<String, Object>
     * @return Page<ValIncomeStatisticsQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ValIncomeStatisticsQuery001Dto> findPage(Map<String, Object> params)
        throws Exception {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("paraCode", "SUBJECT_NO_LIST");
//        List<SysParaSet001Dto> listSysParaSet001Dto = sysParaSet001Service.getReportInfoList(map);
//        String sujectNoList = listSysParaSet001Dto.get(0).getParamValue();
//        String sujectNoList = CacheUtil.getSysParam("SUBJECT_NO_LIST");
//        String[] sujectNo = null;
//        if (StringUtil.isNotEmpty(sujectNoList)) {
//            sujectNo = sujectNoList.split(",");
//        }
//        params.put("sujectNo", sujectNoList);
//        return super.queryForPage(() -> valIncomeStatisticsQuery001Mapper.findPage(params));
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBankAccountQuery001/findIncomeStatisticsPage",
                params, ValIncomeStatisticsQuery001Dto.class);
    }

//    /**
//     * 设置日期维度
//     * 
//     * @return List<SelectOptionDto>
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getDateDimension() {
//        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//        listSelectDto.add(createSelect("01", "汇总"));
//        listSelectDto.add(createSelect("02", "年"));
//        listSelectDto.add(createSelect("03", "季度"));
//        listSelectDto.add(createSelect("04", "月"));
//        listSelectDto.add(createSelect("05", "日"));
//        return listSelectDto;
//    }
//
//    /**
//     * 设置产品维度
//     * 
//     * @return List<SelectOptionDto>
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getProductDimension() {
//        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//        listSelectDto.add(createSelect("01", "汇总"));
//        listSelectDto.add(createSelect("02", "本金标识"));
//        listSelectDto.add(createSelect("03", "产品"));
//        return listSelectDto;
//    }

}
