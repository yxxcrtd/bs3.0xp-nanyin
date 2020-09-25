/*
 * 文件名：SecAssetStatisticsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.positionstatistics.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.report.poc.positionstatistics.dao.PositionStatisticsMapper;
import com.joyintech.service.report.poc.positionstatistics.dto.PositionStatisticsDto;

/**
 * 头寸统计表微服务Service
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月6日
 * @see PositionStatisticsService
 * @since 1.0
 */
@Service
public class PositionStatisticsService extends BaseService<PositionStatisticsDto>{
    @Autowired
    private PositionStatisticsMapper positionStatisticsMapper;
    
  
    /**
     * 查询头寸数据
     */
    public List<PositionStatisticsDto> selectForPage(Map<String, Object> param) {
        List<PositionStatisticsDto> list = new ArrayList<PositionStatisticsDto>();
        PositionStatisticsDto model = new PositionStatisticsDto();
        //初始或model
        model.setProdestablish(BigDecimal.ZERO);
        model.setNhgexpire(BigDecimal.ZERO);
        model.setZhgsq(BigDecimal.ZERO);
        model.setCcexpire(BigDecimal.ZERO);
        model.setCrsq(BigDecimal.ZERO);
        model.setAssetsale(BigDecimal.ZERO);
        model.setAssetexpire(BigDecimal.ZERO);
        model.setAssetpayments(BigDecimal.ZERO);
        model.setCalclr(BigDecimal.ZERO);
        model.setProdexpire(BigDecimal.ZERO);
        model.setNhgsq(BigDecimal.ZERO);
        model.setZhgexpire(BigDecimal.ZERO);
        model.setCcsq(BigDecimal.ZERO);
        model.setCrexpire(BigDecimal.ZERO);
        model.setAssetbuy(BigDecimal.ZERO);
        model.setCalclc(BigDecimal.ZERO);
        model.setCleancashflow(BigDecimal.ZERO);
        list = positionStatisticsMapper.selectForPage(param);
        String searchDate = "";
        for(int i=0; i<30; i++){
            if((String)param.get("searchDateFuture") == null || (String)param.get("searchDateFuture") == ""){
                searchDate = (String)param.get("searchDate");
            }
            else{
                searchDate = (String)param.get("searchDateFuture");
            }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date datesearch = null;
        try {
            datesearch = format.parse(searchDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        String searchDateFuture = DateUtil.addDay(DateUtil.format(datesearch),1);
        param.put("searchDateFuture", searchDateFuture);
        param.put("searchDate", (String)param.get("searchDate"));
        List<PositionStatisticsDto> listfuture =  positionStatisticsMapper.selectForPageFuture(param);
        list.addAll(listfuture);
        }
        for(PositionStatisticsDto dto : list){
            model.setProdestablish(model.getProdestablish().add(dto.getProdestablish()==null?BigDecimal.ZERO:dto.getProdestablish()));
            model.setNhgexpire(model.getNhgexpire().add(dto.getNhgexpire()==null?BigDecimal.ZERO:dto.getNhgexpire()));
            model.setZhgsq(model.getZhgsq().add(dto.getZhgsq()==null?BigDecimal.ZERO:dto.getZhgsq()));
            model.setCcexpire(model.getCcexpire().add(dto.getCcexpire()==null?BigDecimal.ZERO:dto.getCcexpire()));
            model.setCrsq(model.getCrsq().add(dto.getCrsq()==null?BigDecimal.ZERO:dto.getCrsq()));
            model.setAssetsale(model.getAssetsale().add(dto.getAssetsale()==null?BigDecimal.ZERO:dto.getAssetsale()));
            model.setAssetexpire(model.getAssetexpire().add(dto.getAssetexpire()==null?BigDecimal.ZERO:dto.getAssetexpire()));
            model.setAssetpayments(model.getAssetpayments().add(dto.getAssetpayments()==null?BigDecimal.ZERO:dto.getAssetpayments()));
            model.setCalclr(model.getCalclr().add(dto.getCalclr()==null?BigDecimal.ZERO:dto.getCalclr()));
            model.setProdexpire(model.getProdexpire().add(dto.getProdexpire()==null?BigDecimal.ZERO:dto.getProdexpire()));
            model.setNhgsq(model.getNhgsq().add(dto.getNhgsq()==null?BigDecimal.ZERO:dto.getNhgsq()));
            model.setZhgexpire(model.getZhgexpire().add(dto.getZhgexpire()==null?BigDecimal.ZERO:dto.getZhgexpire()));
            model.setCcsq(model.getCcsq().add(dto.getCcsq()==null?BigDecimal.ZERO:dto.getCcsq()));
            model.setCrexpire(model.getCrexpire().add(dto.getCrexpire()==null?BigDecimal.ZERO:dto.getCrexpire()));
            model.setAssetbuy(model.getAssetbuy().add(dto.getAssetbuy()==null?BigDecimal.ZERO:dto.getAssetbuy()));
            model.setCalclc(model.getCalclc().add(dto.getCalclc()==null?BigDecimal.ZERO:dto.getCalclc()));
            model.setCleancashflow(model.getCleancashflow().add(dto.getCleancashflow()==null?BigDecimal.ZERO:dto.getCleancashflow()));
        }
        model.setCdate("合计");
        list.add(model);
        return list;
    }
    
    /**
     * 理财经理联想下拉框
     *
     * @param param 参数map
     * @return 理财经理信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryManagerInfo(@RequestBody Map<String, Object> param){
        return positionStatisticsMapper.queryManagerInfo(param);
    }
    
}
