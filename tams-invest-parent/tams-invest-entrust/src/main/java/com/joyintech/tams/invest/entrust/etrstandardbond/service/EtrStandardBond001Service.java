/*
 * 文件名：TrdStandardBond002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etrstandardbond.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond001Dto;

@Service
public class EtrStandardBond001Service extends CommonExportService<EtrStandardBond001Dto> {

    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/entrust/etrstandardbond/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<EtrStandardBond001Dto> selectPageData(Map<String, Object> selectParam) {

        // 多选下拉框值处理
        if (StringUtil.isNotEmpty(selectParam.get("portfolioId"))) {
            selectParam.put("portfolioId", StringUtil.splitToList(",", (String)selectParam.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(selectParam.get("tradePlace"))) {
            selectParam.put("tradePlace", StringUtil.splitToList(",", (String)selectParam.get("tradePlace")));
        }
        if (StringUtil.isNotEmpty(selectParam.get("secAcctId"))) {
            selectParam.put("secAcctId", StringUtil.splitToList(",", (String)selectParam.get("secAcctId")));
        }
        selectParam.put("plateDate", platformDateImpl.getSysDateStr());
        selectParam.put("userId", SecurityUtil.getLoginUser().getUserName());
        Page<EtrStandardBond001Dto> page = restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/selectSearchDataList",
            selectParam, EtrStandardBond001Dto.class);
        
        return page;
    }
    
    // 出库操作
    public Long outSave(EtrStandardBond001Dto dto) {
        Long insertCount = 0L;
        if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
            insertCount += restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/outSave",
                    dto, Long.class);
        }
        return insertCount;
    }

    /**
     * 根据组合代码查询证券托管户：联想框文本框
     *
     * @param parameters 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecManageAcctId(Map<String, Object> map) {
        // 查询证券账户 union all 通道数据
        List<SuggestDto> tempList = restTemplate.postForList(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/getSecManageAcctId",
                map, SuggestDto.class);
        
        List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
        if (!StringUtil.isEmptyList(tempList)) {
            SelectOptionDto selectOptionDto;
            for (SuggestDto suggestDto : tempList) {
                selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(suggestDto.getCode());
                selectOptionDto.setText(suggestDto.getName());
                resultList.add(selectOptionDto);
            }
        }
        
        return resultList;
    }
    
    /**
    * 查询登录用户是否满足指定的角色岗位 
    */
   public Long selectUserRolePosList(Map<String, Object> param){
       param.put("userId", SecurityUtil.getLoginUser().getUserName());
       return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/selectUserRolePosList",
           param, Long.class);
   }
}
