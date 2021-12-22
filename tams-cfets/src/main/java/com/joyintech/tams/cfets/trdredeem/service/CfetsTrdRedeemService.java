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
        String effectiveStatus = (String)pageParam.get("effectiveStatus");
        if(StringUtil.isNotEmpty(effectiveStatus)){
            pageParam.put("effectiveStatusList",StringUtil.splitToList(GlobalConstant.COMMA,effectiveStatus));
        }
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
       /* if(StringUtil.equals(dto.getTabType(),"regCur")){
            //有效未交收或流程中
            List<String> statusList=new ArrayList<>();
            statusList.add(DictCodeListExt1.有效状态_有效.getValue());
            statusList.add(DictCodeListExt1.有效状态_流程中.getValue());
            pageParam.put("statusList",statusList);

        }else if(StringUtil.equals(dto.getTabType(),"regPre")){
            //有效已交收或撤销
            List<String> statusList=new ArrayList<>();
            statusList.add(DictCodeListExt1.有效状态_有效.getValue());
            statusList.add( DictCodeListExt1.有效状态_撤销.getValue());
            pageParam.put("statusList",statusList);
        }*/
        pageParam.put("DELIVERY_STATUS_01", DictCodeListExt3.交收状态_未交收.getValue());
        pageParam.put("DELIVERY_STATUS_02", DictCodeListExt3.交收状态_已交收.getValue());
        pageParam.put("DELIVERY_STATUS_04", DictCodeListExt3.交收状态_交收失败.getValue());


        pageParam.put("VALID_STATUS_01", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.有效状态_撤销.getValue());
        pageParam.put("VALID_STATUS_04", DictCodeListExt1.有效状态_流程中.getValue());

        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("DATE_TYPE_0", 0);
        pageParam.put("A_001", DictCodeListExt3.资产类型_资产账户持仓.getValue());
        pageParam.put("cdate",iPlatformDate.getSysDate());
        pageParam.put("sort",dto.getPageMode().getSort());
        pageParam.put("direction",dto.getPageMode().getDirection());
        String userId = SecurityUtil.getLoginUser().getUserName();
        if (StringUtils.isNotEmpty(userId)) {
            //判断角色配置
            List<String> roleList=cfetsTrdQueryMapper.selectSysRoleById(userId);
            if(roleList.contains(DictCodeList.角色岗位_领导.getValue())
            ){
                //角色配置为领导可查看所有数据
            }else if(roleList.contains(DictCodeList.角色岗位_交易员.getValue())){
                //交易员
                pageParam.put("submitUserId", SecurityUtil.getLoginUser().getUserName());
            }
            //登记
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> cfetsTrdRedeemMapper.queryAllRedeemRegInfoList(pageParam));

        }else{
            return null;
        }
    }


}
