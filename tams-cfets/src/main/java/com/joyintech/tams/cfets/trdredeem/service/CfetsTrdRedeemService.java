/*
 * 文件名：CfetsTrdConfirmService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.trdredeem.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.cfets.trdquery.dao.CfetsTrdQueryMapper;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdQueryRegInfoListRequest;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdQueryRegInfoListResponse;
import com.joyintech.tams.cfets.trdredeem.dao.CfetsTrdRedeemMapper;
import com.joyintech.tams.cfets.trdredeem.dto.CfetsTrdRedeemRegInfoListRequest;
import com.joyintech.tams.cfets.trdredeem.dto.CfetsTrdRedeemRegInfoListResponse;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 银行间交易成交确认
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdRedeemService
 * @since 1.0
 */
@Service("CfetsTrdRedeemService")
public class CfetsTrdRedeemService extends BaseService{
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;
    @Autowired
    private CfetsTrdRedeemMapper cfetsTrdRedeemMapper;
    @Autowired
    private CfetsTrdQueryMapper cfetsTrdQueryMapper;
    /**
     * 赎回登记列表分页查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<CfetsTrdRedeemRegInfoListResponse> queryAllRedeemRegInfoList(CfetsTrdRedeemRegInfoListRequest dto) {
        Map<String, Object> pageParam = BeanUtil.object2Map(dto);
        String regStatus = (String)pageParam.get("regStatus");
        if(StringUtil.isNotEmpty(regStatus)){
            pageParam.put("regStatusList", StringUtil.splitToList(GlobalConstant.COMMA,regStatus));
        }
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        String regAssetType= (String)pageParam.get("regAssetType");
        if(StringUtil.isNotEmpty(regAssetType)){
            List<String> finprodType2List=new ArrayList<>();
            if(StringUtil.equals(regAssetType,"01")){
                finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            }else{
                finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
                finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            }
            pageParam.put("finprodType2List",finprodType2List);
        }
        String secAcctId=(String)pageParam.get("secAcctId");
        if(StringUtil.isNotEmpty(secAcctId)){
            pageParam.put("secAcctIdList", StringUtil.splitToList(GlobalConstant.COMMA,secAcctId));
        }
        if (StringUtil.equals(dto.getSettleSpeed(),
                DictCodeList.清算速度_T_0.getValue())) {
            pageParam.put("settleSpeed","0");
        }
        if (StringUtil.equals(dto.getSettleSpeed(),
                DictCodeList.清算速度_T_1.getValue())) {
            pageParam.put("settleSpeed","1");
        }
        if(StringUtil.equals(dto.getTabType(),"regCur")){
            pageParam.put("sysDate",iPlatformDate.getSysDate());
        }else if(StringUtil.equals(dto.getTabType(),"regPre")){
            if(!StringUtil.isNotEmpty(dto.getStartDate()) && !StringUtil.isNotEmpty(dto.getEndDate())){
                pageParam.put("startDate", DateUtil.addMonthDate(iPlatformDate.getSysDate(),-1));
                pageParam.put("endDate",iPlatformDate.getSysDate());
            }
        }
        pageParam.put("VALID_STATUS_01", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.有效状态_待处理.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.有效状态_撤销.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("DATE_TYPE_0", 0);
        pageParam.put("A_001", DictCodeListExt3.资产类型_资产账户持仓.getValue());
        pageParam.put("cdate",iPlatformDate.getSysDate());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.YEAR);
        pageParam.put("year",year);
        pageParam.put("sort",dto.getPageMode().getSort());
        pageParam.put("direction",dto.getPageMode().getDirection());
        String userId = SecurityUtil.getLoginUser().getUserName();
        if (StringUtils.isNotEmpty(userId)) {
            //判断角色配置
            List<String> roleList=cfetsTrdQueryMapper.selectSysRoleById(userId);
            if(roleList.contains(DictCodeList.角色岗位_领导.getValue())
                    || roleList.contains(DictCodeList.角色岗位_投资处长.getValue())
                    || roleList.contains(DictCodeList.角色岗位_交易处长.getValue())
            ){
                //角色配置为领导、交易处长、投资处长可查看所有数据
            }else if(roleList.contains(DictCodeList.角色岗位_交易员.getValue())){
                //交易员
                pageParam.put("submitUserId", SecurityUtil.getLoginUser().getUserName());
            }else if(roleList.contains(DictCodeList.角色岗位_投资经理.getValue())){
                //投资经理
                pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
            }
            //登记
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> cfetsTrdRedeemMapper.queryAllRedeemRegInfoList(pageParam));

        }else{
            return null;
        }
    }


}
