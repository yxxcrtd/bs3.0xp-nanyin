/*
 * 文件名：EtrAgreRepTrade002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LBY
 * 修改时间：2020年9月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etragrereptrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etragrereptrade.dto.EtrAgreRepTrade002Dto;
import com.joyintech.tams.invest.entrust.etragrereptrade.dto.EtrAgreRepTrade003Dto;
import com.joyintech.tams.invest.entrust.etragrereptrade.dto.OnWayRep002Dto;
import com.joyintech.tams.invest.entrust.etragrereptrade.dto.UnOpenQuota002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *  交易所指令交易（债券股票回购基金场内指令交易）
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrAgreRepTrade003Service
 * @since 1.0
 */
@Service
public class EtrAgreRepTrade003Service extends CommonExportService<EtrAgreRepTrade002Dto> {

    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * 分页查询产品持仓信息
     * <功能详细描述>
     * @param param 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    public Page<EtrAgreRepTrade002Dto> findPositionsPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAgreRepTrade/findPositionsPage",
            param, EtrAgreRepTrade002Dto.class);
    }



    /**
     * 查询证券代码联想框文本框
     *
     * @param parameters 画面参数
     * @return 证券代码联想框文本框
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSecAcctId(Map<String, Object> parameters) {
        Page<SelectOptionDto> tempList = restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/getSecAcctId",
            parameters, SelectOptionDto.class);
        return tempList;
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @param pageParam 页面参数
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    public Map<String, String> loadSecNum(Map<String, Object> pageParam) {
        Map<String, String> map = (Map<String, String>)restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/loadSecNum",
                pageParam, Map.class);
        return map;
    }
    public Page<OnWayRep002Dto> findOnWayRepPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAgreRepTrade/findOnWayRepPage",
                param, OnWayRep002Dto.class);
    }

    public Page<UnOpenQuota002Dto> findUnOpenQuotaPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        //TODO 调用非公开行情接口
        return new Page<UnOpenQuota002Dto>();
        /*return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAgreRepTrade/findOnWayRepPage",
                param, OnWayRep002Dto.class);*/
    }


    public Page<EtrAgreRepTrade003Dto> selectEtrEntrust(Map<String, Object> param) {
        param.put("userId", LoginUserUtil.getUserName());
        param.put("sysDate",iPlatformDate.getSysDateStr());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAgreRepTrade/selectEtrEntrust",
                param, EtrAgreRepTrade003Dto.class);
    }
}
