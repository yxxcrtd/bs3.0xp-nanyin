/*
 * 文件名：InvestorsHoldInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbInvestholdInfoMapper;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfo;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoKey;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;


/**
 * 投资者身份信息保存
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年5月8日
 * @see InvestorsHoldInfo002Service
 * @since 1.0
 */
@Service
public class InvestorsHoldInfo002Service{
    /**
     * 系统dao
     */
    @Autowired
    private ChbInvestholdInfoMapper chbInvestholdInfoMapper;
    /**
     * 001SERVICE
     */
    @Autowired
    private InvestorsHoldInfo001Service investorsHoldInfo001Service;
    /**
     * 根据业务主key获取entity
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public ChbInvestholdInfo getEntityByKey(Map<String, Object> map) {
        ChbInvestholdInfoKey key = new ChbInvestholdInfoKey();
        key.setCcy(map.get("ccy").toString());
        key.setIndentifyMark(map.get("indentifyMark").toString());
//        key.setPrdRegistCode(map.get("prdRegistCode").toString());
        key.setProdid(map.get("prodid").toString());
        key.setRegistBankCode(map.get("registBankCode").toString());
        key.setStatus(map.get("statusCode").toString());
        key.setHoldDate(DateUtil.parse(map.get("holdDate").toString()));
        return chbInvestholdInfoMapper.selectByPrimaryKey(key);
    }

    /**
     * 将数据更新
     * <功能详细描述>
     * @param dto 业务数据
     * @see
     * @since 1.0
     */
    public void doSaveOrUpdate(InvestorsHoldInfo001Dto dto) {
//        if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
//            User userInfo = SecurityUtil.getLoginUser();
//            //TODO
//            dto.setRegistBankCode("test");
//            dto.setOrgCode("test");
//            chbInvestholdInfoMapper.insertSelective(dto);
//        }
//        else{
//            chbInvestholdInfoMapper.updateByPrimaryKeySelective(dto);
//        }
        if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
//            dto.setRegistBankCode(CommonUtil.getUserBankCode()==null?"admin":CommonUtil.getUserBankCode());
//            dto.setRegistBankCode(CommonUtil.getUserBankCode());
//            dto.setOrgCode(CommonUtil.getOrgCode());
//            dto.setDeptCode(CommonUtil.getDeptCode());
            dto.setCreateDept(dto.getDeptCode()!=null?dto.getDeptCode():dto.getOrgCode());
            //新建时判断主键标识是否重复
//            Map<String, Object> param = new HashMap<String,Object>();
//            param.put("REGISTBANKCODE", dto.getRegistBankCode());
//            param.put("PRDREGISTCODE", dto.getPrdRegistCode());
//            param.put("HOLDDATE", dto.getHoldDate());
//            param.put("CCY", dto.getCcy());
//            param.put("INDENTIFYMARK", dto.getIndentifyMark());
        }

        // 产品代码
        if (StringUtil.isEmpty(dto.getProdid())) {
            String prodid = investorsHoldInfo001Service.getProdidByPrdRegistCode(
                dto.getPrdRegistCode());
            dto.setProdid(prodid);
        }

        List<InvestorsHoldInfo001Dto> list = new ArrayList<InvestorsHoldInfo001Dto>();
        list.add(dto);
        investorsHoldInfo001Service.afterCheckDeal(list, null,
            DictCodeListExt2.数据来源_系统新建.getValue(), null, null, null, null);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    public BigDecimal dealCnyAmt(Map<String, Object> pageParam) {
        pageParam.put("HOLDDATE",DateUtil.parse(pageParam.get("HOLDDATE").toString()));
        pageParam.put("CCY_ORIGINAL", pageParam.get("CCY"));
        pageParam.put("CCY_CONVERT", DictCodeListExt1.币种_中债_人民币.getValue());
        BigDecimal amount = new BigDecimal(pageParam.get("amount").toString());
        // 汇率计算规则
        pageParam.put("pricetype",SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE));
        BigDecimal rate = investorsHoldInfo001Service.getRate(pageParam);
        rate = rate==null?BigDecimal.ZERO:rate;
        return ArithmeticUtil.multiply(rate,amount);
    }

}
