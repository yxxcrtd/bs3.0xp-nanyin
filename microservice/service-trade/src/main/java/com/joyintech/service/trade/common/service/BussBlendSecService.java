/*
 * 文件名：BussBlendSecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.common.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.common.dao.BussBlendSecMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 融入劵管理共通组件-微服务Service
 */
@Service
public class BussBlendSecService {

    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * BussBlendSecMapper
     */
    @Autowired
    private BussBlendSecMapper bussBlendSecMapper;

    /**
     * 债券代码：下拉框
     *
     * @param param 画面参数
     * @return 债券代码：下拉框
     */
    public List<SelectOptionDto> getBondCode(Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("windtypeone", StringUtil.splitToList(GlobalConstant.COMMA, (String) param.get("bussRepoSec.bondType")));
        return bussBlendSecMapper.getBondCode(param);
    }

    /**
     * 通过entrustId/bizCode检索已融入券信息
     *
     * @param params 画面参数
     * @return List<BussRepoSecDto>
     */
    public BussRepoSecDto selectBussRepoSec(Map<String, Object> params) {
        BussRepoSecDto bussRepoSecDto = new BussRepoSecDto();
        String ps = (String) params.get("ps");
        List<PledgeBondInfoDto> pledgeList = new ArrayList<>();
        if (DictCodeList.回购拆借方向_正回购.getValue().equals(ps)) {
            params.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
            params.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
            params.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
            params.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
            params.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
            params.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
            params.put("plateDate", platformDateImpl.getSysDateStr());
            pledgeList = bussBlendSecMapper.selectBussRepoSecSellRepo(params);
        } else if (DictCodeList.回购拆借方向_逆回购.getValue().equals(ps)) {
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
            params.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            pledgeList = bussBlendSecMapper.selectBussRepoSecAntiRepo(params);
        }
        BigDecimal totalPrinAmt = BigDecimal.ZERO;
        for (PledgeBondInfoDto pbi : pledgeList) {
            totalPrinAmt = ArithmeticUtil.doAdd(totalPrinAmt, ArithmeticUtil.multiply(pbi.getPledgePrin(), pbi.getPledgeRatio()).divide(new BigDecimal("100")));
        }
        bussRepoSecDto.setPledgeBondList(pledgeList);
        bussRepoSecDto.setTotalPrinAmt(totalPrinAmt);
        return bussRepoSecDto;
    }

    /**
     * 根据画面条件检索可融入券信息
     *
     * @param params 画面参数
     * @return List<PledgeBondInfoDto>
     */
    public List<PledgeBondInfoDto> selectAvaBlendBondDetailList(Map<String, Object> params) {
        String ps = (String) params.get("ps");
        String bondType = (String) params.get("bondType");
        if(StringUtil.isNotEmpty(bondType)){
            params.put("bondTypeArr", StringUtil.split(bondType,","));
        }
        List<PledgeBondInfoDto> resultList = new ArrayList<>();
        if (DictCodeList.回购拆借方向_逆回购.getValue().equals(ps)||DictCodeList.回购拆借方向_融出.getValue().equals(ps)) {
            params.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
            params.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
            params.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
            params.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
            params.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("repRegistOrg", StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("repRegistOrg")));
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            resultList = bussBlendSecMapper.selectAvaBlendBondDetailListSellRepo(params);
        } else if (DictCodeList.回购拆借方向_正回购.getValue().equals(ps)) {
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("repRegistOrg", StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("repRegistOrg")));
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            resultList = bussBlendSecMapper.selectAvaBlendBondDetailListAntiRepo(params);
        }
        return resultList;
    }


}
