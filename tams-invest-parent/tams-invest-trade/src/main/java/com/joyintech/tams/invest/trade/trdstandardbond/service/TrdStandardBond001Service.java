/*
 * 文件名：TrdStandardBond002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.trdstandardbond.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade001Dto;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond001Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrdStandardBond001Service extends CommonExportService<TrdStandardBond001Dto> {

    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/trade/trdstandardbond/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdStandardBond001Dto> selectPageData(Map<String, Object> selectParam) {
        selectParam.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));

        // 多选下拉框值处理
        // 组合代码
        if (selectParam.get("bondCode") != null) {
            selectParam.put("bondCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("bondCode")));
        } else {
            selectParam.put("bondCodeList", new ArrayList<>());
        }
        selectParam.put("plateDate", platformDateImpl.getSysDateStr());
        Page<TrdStandardBond001Dto> page = restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/selectSearchDataList",
            selectParam, TrdStandardBond001Dto.class);
        
        for (TrdStandardBond001Dto dto : page.getRoot()) {
            dto.setBidInOutPrinAmt(dto.getPledgePrinAmt());
        }
        return page;
    }

    /**
     * 债券代码：下拉框
     *
     * @param params 画面参数
     * @return 债券代码：下拉框
     */
    public List<SelectOptionDto> getBondCode(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/getBondCode",
                params, SelectOptionDto.class);
    }

    /**
     * 根据组合代码查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecManageAcctId(Map<String, Object> params) {
        List<SuggestDto> tempList = restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/getSecManageAcctId",
                params, SuggestDto.class);
        List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
        if (!StringUtil.isEmptyList(tempList)) {
            SelectOptionDto selectOptionDto;
            for (SuggestDto suggestDto : tempList) {
                selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(suggestDto.getCode());
                selectOptionDto.setText(suggestDto.getName());
                String parentId = StringUtil.EMPTY;
                if (StringUtil.isNotBlank((String) params.get("portfolioId"))) {
                    parentId = (String) params.get("portfolioId");
                }
                if (StringUtil.isNotBlank((String) params.get("tradePlace"))) {
                    parentId += (String) params.get("tradePlace");
                }
                selectOptionDto.setParentId(parentId);
                resultList.add(selectOptionDto);
            }
        }
        return resultList;
    }
}
