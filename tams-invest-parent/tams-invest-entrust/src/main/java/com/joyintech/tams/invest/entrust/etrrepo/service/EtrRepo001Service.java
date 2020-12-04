/*
 * 文件名：EtrRepo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrrepo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.entrust.etrrepo.dto.EtrRepoDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询service
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月24日
 * @see EtrRepo001Service
 * @since 1.0
 */
@Service
public class EtrRepo001Service extends CommonExportService<EtrRepoDto>{


    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * 项目类委托分页查询 <功能详细描述>
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrRepoDto> selectForPage(Map<String, Object> param) {
        if (StringUtil.isNotEmpty((String)param.get("entrustDate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)param.get("entrustDate"));
            param.put("startDate", dateList.get(0));
            param.put("endDate", dateList.get(2));
        }
//        if(StringUtil.isNotEmpty((String)param.get("ccy"))){
//            List<String> dateList = StringUtil.splitToList(",", (String)param.get("ccy"));
//            param.put("ccyList", dateList);
//        }
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/findPage", param,
            EtrRepoDto.class);
    }

    /**
     * 组合代码联想文本框 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getentrustId(String param) {
        User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("param", param);
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrRepo/getentrustId" , map,
            SuggestDto.class);
    }
    /**
     * 关闭委托 <功能详细描述>
     *
     * @param entrustId
     * @param closeWay
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay, String entrustStatus) {
        boolean res = restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/close?entrustId=" + entrustId
                                         + "&closeWay=" + closeWay,
            boolean.class);
        
        
     	//处理投资指令授信 20200526 start
     	// 已撤回/已退回 的不做授信处理
     	if (! DictCodeList.委托状态_已撤回.getValue().equals(entrustStatus) && ! DictCodeList.委托状态_已退回.getValue().equals(entrustStatus)) {
             dealCloseEtrIsNeedOccupyCre(entrustId);
     	}
         //处理投资指令授信 20200526 end
         return res;
    }

    /**
     * 撤回委托 <功能详细描述>
     *
     * @param entity
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrRepoDto dto) {
        Boolean res = restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/recall", dto, boolean.class);
        
        //处理投资指令授信 20200526 start
        dealCloseEtrIsNeedOccupyCre(dto.getEntrustId());
        //处理投资指令授信 20200526 end
        return res ;
    }
    /**
     * 校验方法
     *
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrRepoDto dto) {
        dto.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
        return restTemplate.postForList(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/check", dto,
            ValidMessage.class);
    }
    /**
     *
     * 查询授权额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selectLimitAmt(EtrRepoDto dto){
        Map<String, Object> map = new HashMap<>();
        map.put("userName", SecurityUtil.getLoginUser().getUserName());
        map.put("assetType", DictCodeList.平安资产类型_质押式回购.getValue());
        map.put("assetSecType", dto.getAssetType1());
        map.put("tradeMarket", dto.getTradeMarket());
        map.put("ps",dto.getPs());
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/common/SelectLimitAmt/selectLimitAmt", map,
            BigDecimal.class);
    }
    /**
     * 导出
     * 
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/entrust/etrrepo/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    @Override
    public Page<EtrRepoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"transactioncom")){
            cell.setCellValue(value+"%");
        }
    }
    
    // 指令授信 start
    
    /**
     * 关闭/撤回指令授信处理
     * @param entrustId
     */
    public void dealCloseEtrIsNeedOccupyCre(String entrustId) {
		Map<String, Object> map = commonBusinessService.selectByEntrustId(entrustId);
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setProdid((String)map.get("portfolioId"));
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        TrdProductDeal trdProductDeal = (TrdProductDeal) BeanUtil.mapToObject(map,TrdProductDeal.class);
        trdProductDeal.setpTradeId((String)map.get("entrustId"));
        trdProductDeal.setTradeAmt((BigDecimal)map.get("entrustAmt"));
        trdProductDeal.setPrinAmt((BigDecimal)map.get("entrustAmt")); 
        trdProductDeal.setTradeDate((Date)map.get("entrustDate"));
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        inputDto.setTrdProductDeal(trdProductDeal);
        
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getrTradeId());
        trdProductDealAdd.setPortfolioId((String)map.get("portfolioId"));
//        trdProductDealAdd.setCreditSource((String)map.get("creditSource"));
        inputDto.setTrdProductDealAdd(trdProductDealAdd);

        AllTradeCommonUtil.do投资指令授信(inputDto);
    }
    // 指令授信 end
}
