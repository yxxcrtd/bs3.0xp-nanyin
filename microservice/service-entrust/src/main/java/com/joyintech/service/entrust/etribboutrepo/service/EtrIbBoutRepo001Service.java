/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etribboutrepo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etribboutrepo.dao.EtrIbBoutRepo001Mapper;
import com.joyintech.service.entrust.etribboutrepo.dto.EtrIbBoutRepo001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.interf.IPlatformDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbBoutRepo001Service
 * @since 1.0
 */
@Service("backEtrIbBoutRepo001Service")
public class EtrIbBoutRepo001Service extends BaseService{

    @Autowired
    private EtrIbBoutRepo001Mapper etrIbBoutRepo001Mapper;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 指令管理查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrIbBoutRepo001Dto> findZlglPage(int page, int length,
                                                  Map<String, Object> pageParam) {
        pageParam.put("FINPROD_TYPE2_Z", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        pageParam.put("FINPROD_TYPE2_N", DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        pageParam.put("startDate", iPlatformDate.getSysDateStr());
        pageParam.put("endDate", iPlatformDate.getSysDateStr());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        //Page<EtrIbBoutRepo001Dto> result = super.queryForPage(page, length, () -> etrIbBoutRepo001Mapper.findZlglPage(pageParam));
       // 去掉分页
        List<EtrIbBoutRepo001Dto> list =  etrIbBoutRepo001Mapper.findZlglPage(pageParam);
        return new Page<EtrIbBoutRepo001Dto>(list);
    }

    /**
     * 债券质押持仓信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrIbBoutRepo001Dto> findZlglPositionPage(int page, int length,
            Map<String, Object> pageParam) {
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String secAcctId = (String)pageParam.get("secAcctId");
        if(StringUtil.isNotEmpty(secAcctId)){
            pageParam.put("secAcctIdList", StringUtil.splitToList(GlobalConstant.COMMA,secAcctId));
        }
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        pageParam.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        pageParam.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        pageParam.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
        pageParam.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
        pageParam.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
        pageParam.put("POSITION_TYPE_08", DictCodeListExt1.持仓类型_预占用.getValue());
        pageParam.put("POSITION_TYPE_11", DictCodeListExt1.持仓类型_卖空.getValue());
        pageParam.put("POSITION_TYPE_12", DictCodeListExt1.持仓类型_卖空可用.getValue());

        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        pageParam.put("TRADEPLACE01", DictCodeListExt1.交易场所_银行间.getValue());
        Page<EtrIbBoutRepo001Dto> result = super.queryForPage(page, length, () -> etrIbBoutRepo001Mapper.findZlglPositionPage(pageParam));
        return result;
    }

}
