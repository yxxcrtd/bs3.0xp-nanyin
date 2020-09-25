/*
 * 文件名：ProductLedgerService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年9月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.riskmonitorindicators.service; 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody; 
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.poc.riskmonitorindicators.dao.RiskMonitorIndicatorsMapper;
import com.joyintech.service.report.poc.riskmonitorindicators.dto.RiskMonitorIndicatorsDto;
import com.joyintech.service.report.poc.riskmonitorindicators.dto.RiskTemporaryDataDto;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 风险监测指标Service
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月11日
 * @see RiskMonitorIndicatorsService
 * @since 1.0
 */
@Service
public class RiskMonitorIndicatorsService extends BaseService<RiskMonitorIndicatorsDto>{

    @Autowired
    private RiskMonitorIndicatorsMapper riskMonitorIndicatorsMapper;
    
    public Page<RiskMonitorIndicatorsDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
        List<RiskMonitorIndicatorsDto> list = new ArrayList<>();
        RiskMonitorIndicatorsDto dto1 = new RiskMonitorIndicatorsDto();
        dto1.setProdId("zuhemingcheng001");//组合代码
        dto1.setCombinationname("组合名称001");//组合名称
        dto1.setManager("chanping001");;//产品经理
        dto1.setProdduration(BigDecimal.valueOf(0.038356164384));//产品久期
        dto1.setAssetduration(BigDecimal.valueOf(-0.012521254608));//资产久期
        dto1.setSecduration(BigDecimal.valueOf(0.657670000000));//债券久期
        dto1.setDurationmismatch(BigDecimal.valueOf(-0.050877418992));//久期错配
        dto1.setTermmismatch(BigDecimal.valueOf(-0.050877418992));//期限错配
        dto1.setLeverageratio(BigDecimal.valueOf(100));//杠杆率%
        dto1.setCleancashflownextday(BigDecimal.valueOf(8107875197.80));//未来隔夜净现金流
        dto1.setCleancashflowsevenday(BigDecimal.valueOf(-117368270.30));//未来7日净现金流
        dto1.setCleancashflowonemonth(BigDecimal.valueOf(0.657670000000));//未来1个月净现金流
//        dto1.setOpenhighliquidityratio();//开放式高流动性资产占比%
        dto1.setHighliquidityratio(BigDecimal.valueOf(0.00));//高流动性资产占比%
//        dto1.setSinglecustomerconcentration(BigDecimal.valueOf(0.657670000000));//单一最大认购客户集中度
        dto1.setSingleassetconcentration(BigDecimal.valueOf(87.51));//单一最大资产集中度
        dto1.setSinglefinancierconcentration(BigDecimal.valueOf(99.82));//单一最大融资人集中度
        dto1.setNoopensecconcentration(BigDecimal.valueOf(0.00));//非公开债券集中度
//        dto1.setSinglejoinratio(BigDecimal.valueOf(0.657670000000));//单一关联方单笔交易占资本净额比例%
//        dto1.setSinglenonstandardratio(BigDecimal.valueOf(0.657670000000));//单一机构非标占资本净额比例%
//        dto1.setNonstandardratioclean(BigDecimal.valueOf(0.657670000000));//非标资产占产品净资产比例%
//        dto1.setNonstandardratiowhole(BigDecimal.valueOf(0.657670000000));//非标资产占全行总资产比例%
        dto1.setFloatingpl(BigDecimal.valueOf(0.00));//浮动盈亏金额
        dto1.setProdManager("产品经理001"); 
        RiskMonitorIndicatorsDto dto2 = new RiskMonitorIndicatorsDto();
        dto2.setProdId("zuhemingcheng002");//组合代码
        dto2.setCombinationname("组合名称002");//组合名称
        dto2.setManager("chanping002");;//产品经理
        dto2.setProdduration(BigDecimal.valueOf(0.038356164384));//产品久期
        dto2.setAssetduration(BigDecimal.valueOf(0.144048706240));//资产久期
        dto2.setSecduration(BigDecimal.valueOf(0.657670000000));//债券久期
        dto2.setDurationmismatch(BigDecimal.valueOf(0.105692541857));//久期错配
        dto2.setTermmismatch(BigDecimal.valueOf(0.105692541857));//期限错配
        dto2.setLeverageratio(BigDecimal.valueOf(100));//杠杆率%
        dto2.setCleancashflownextday(BigDecimal.valueOf(-1000265753.42));//未来隔夜净现金流
        dto2.setCleancashflowsevenday(BigDecimal.valueOf(10567123.29));//未来7日净现金流
        dto2.setCleancashflowonemonth(BigDecimal.valueOf(0.657670000000));//未来1个月净现金流
//                dto2.setOpenhighliquidityratio();//开放式高流动性资产占比%
        dto2.setHighliquidityratio(BigDecimal.valueOf(0.00));//高流动性资产占比%
//                dto2.setSinglecustomerconcentration(BigDecimal.valueOf(0.657670000000));//单一最大认购客户集中度
        dto2.setSingleassetconcentration(BigDecimal.valueOf(64.44));//单一最大资产集中度
        dto2.setSinglefinancierconcentration(BigDecimal.valueOf(64.44));//单一最大融资人集中度
        dto2.setNoopensecconcentration(BigDecimal.valueOf(0.00));//非公开债券集中度
//                dto2.setSinglejoinratio(BigDecimal.valueOf(0.657670000000));//单一关联方单笔交易占资本净额比例%
//                dto2.setSinglenonstandardratio(BigDecimal.valueOf(0.657670000000));//单一机构非标占资本净额比例%
//                dto2.setNonstandardratioclean(BigDecimal.valueOf(0.657670000000));//非标资产占产品净资产比例%
//                dto2.setNonstandardratiowhole(BigDecimal.valueOf(0.657670000000));//非标资产占全行总资产比例%
        dto2.setFloatingpl(BigDecimal.valueOf(0.00));//浮动盈亏金额
        dto2.setProdManager("产品经理001");
        RiskMonitorIndicatorsDto dto3 = new RiskMonitorIndicatorsDto();
        dto3.setProdId("zuhemingcheng003");//组合代码
        dto3.setCombinationname("组合名称003");//组合名称
        dto3.setManager("chanping003");;//产品经理
        dto3.setProdduration(BigDecimal.valueOf(0.038356164384));//产品久期
        dto3.setAssetduration(BigDecimal.valueOf(0.557838348764));//资产久期
        dto3.setSecduration(BigDecimal.valueOf(0.657670000000));//债券久期
        dto3.setDurationmismatch(BigDecimal.valueOf(0.519482184380));//久期错配
        dto3.setTermmismatch(BigDecimal.valueOf(0.519482184380));//期限错配
        dto3.setLeverageratio(BigDecimal.valueOf(100));//杠杆率%
        dto3.setCleancashflownextday(BigDecimal.valueOf(561325585.16));//未来隔夜净现金流
        dto3.setCleancashflowsevenday(BigDecimal.valueOf(-117368270.30));//未来7日净现金流
        dto3.setCleancashflowonemonth(BigDecimal.valueOf(0.657670000000));//未来1个月净现金流
//                dto3.setOpenhighliquidityratio();//开放式高流动性资产占比%
        dto3.setHighliquidityratio(BigDecimal.valueOf(0.00));//高流动性资产占比%
//                dto3.setSinglecustomerconcentration(BigDecimal.valueOf(0.657670000000));//单一最大认购客户集中度
        dto3.setSingleassetconcentration(BigDecimal.valueOf(37.58));//单一最大资产集中度
        dto3.setSinglefinancierconcentration(BigDecimal.valueOf(100));//单一最大融资人集中度
        dto3.setNoopensecconcentration(BigDecimal.valueOf(28.68));//非公开债券集中度
//                dto3.setSinglejoinratio(BigDecimal.valueOf(0.657670000000));//单一关联方单笔交易占资本净额比例%
//                dto3.setSinglenonstandardratio(BigDecimal.valueOf(0.657670000000));//单一机构非标占资本净额比例%
//                dto3.setNonstandardratioclean(BigDecimal.valueOf(0.657670000000));//非标资产占产品净资产比例%
//                dto3.setNonstandardratiowhole(BigDecimal.valueOf(0.657670000000));//非标资产占全行总资产比例%
        dto3.setFloatingpl(BigDecimal.valueOf(2280640.66));//浮动盈亏金额
        dto3.setProdManager("产品经理003");
        list.add(dto1);
        list.add(dto2);
        list.add(dto3);
        List<RiskMonitorIndicatorsDto> list1 = new ArrayList<>(); 
        if (StringUtil.isNotEmpty(pageParam.get("prodId"))) {
            List<String> prodIds = StringUtil.splitToList(null, pageParam.get("prodId").toString());
            for (int i = 0; i < 3; i++ ) { 
                if (prodIds.size() > i) {
                    list.get(i).setCombinationname(prodIds.get(i));
                    list1.add(list.get(i));
                } 
            } 
        }else {
            list1.addAll(list);
        }
        if (StringUtil.isNotEmpty(pageParam.get("manager"))) {
            List<String> managers = StringUtil.splitToList(null, pageParam.get("manager").toString());
            for (RiskMonitorIndicatorsDto dto : list1) {
                dto.setProdManager(managers.get(0));
            }
        }
        Page<RiskMonitorIndicatorsDto> pageList = new Page<>(list1); 
        return pageList;
        
    }
    
    public Page<RiskMonitorIndicatorsDto> selectForDB(int page, int length, Map<String, Object> pageParam) throws Exception {
        boolean isProdId = false; 
        if (StringUtil.isNotEmpty(pageParam.get("searchdate"))) {
            pageParam.put("searchDate", pageParam.get("searchdate").toString());
            pageParam.put("searchDateAddMonth",DateUtil.format(DateUtil.addMonthDateStr(pageParam.get("searchdate").toString(), 1), DateUtil.DEFAULT_DATE_FORMAT_EN) );
        }
        if (StringUtil.isNotEmpty(pageParam.get("prodId"))) {
            pageParam.put("prodIds", StringUtil.splitToList(null, pageParam.get("prodId").toString()));
            isProdId = true;
        }
        String statisticalMethod = ""; 
        List<String> managerList = new ArrayList<>(); 
        if (StringUtil.isNotEmpty(pageParam.get("manager"))) {
            managerList = StringUtil.splitToList(null, pageParam.get("manager").toString());
            pageParam.put("managers", managerList);
            if (StringUtil.isNotEmpty(pageParam.get("statisticalMethod"))) {
                statisticalMethod = pageParam.get("statisticalMethod").toString();
            }else {
                statisticalMethod = "01";
            }
        }
        String yesOrNo = "";
        if (StringUtil.isNotEmpty(pageParam.get("yesOrNo"))) {
            yesOrNo = pageParam.get("yesOrNo").toString();
        }else{
            yesOrNo = "N";
        }  
        Page<RiskMonitorIndicatorsDto> dataPage1 = new Page<RiskMonitorIndicatorsDto>();
        Page<RiskMonitorIndicatorsDto> dataPage = super.queryForPage(page, length, () -> riskMonitorIndicatorsMapper.selectForPage(pageParam));
        Map<String,RiskTemporaryDataDto> result = new HashMap<>(); 
        if (!StringUtil.isEmptyList(dataPage.getRoot())) { 
                dataPage.getRoot().forEach(item->{
                    RiskTemporaryDataDto dataDto = new RiskTemporaryDataDto();   
                    dataDto.setProdId(item.getProdId());
                    dataDto.setProdManager(item.getManager());  
                    result.put(item.getProdId(), dataDto);
                });  
        } 
        if (isProdId) {
            dataPage1 = dataPage;
            if ("N".equals(yesOrNo)) { 
                getRiskTemporaryData(result,pageParam);
            }else {
                getRiskTemporaryPierceData(result,pageParam);  
            }   
        }else if(!isProdId && !StringUtil.isNotEmpty(pageParam.get("manager"))) {  
            RiskMonitorIndicatorsDto dtoData = new RiskMonitorIndicatorsDto();
            List<RiskMonitorIndicatorsDto> list = new ArrayList<>();
            BigDecimal cleancashflownextda = BigDecimal.ZERO; 
            BigDecimal cleancashflowsevenda = BigDecimal.ZERO; 
            BigDecimal cleancashflowonemont = BigDecimal.ZERO;
            if (!StringUtil.isEmptyList(dataPage.getRoot())) { 
               for (RiskMonitorIndicatorsDto item : dataPage.getRoot()) {
                   dtoData = item; 
                   cleancashflownextda = ArithmeticUtil.doAdd(cleancashflownextda,item.getCleancashflownextday());
                   cleancashflowsevenda = ArithmeticUtil.doAdd(cleancashflowsevenda,item.getCleancashflowsevenday());
                   cleancashflowonemont = ArithmeticUtil.doAdd(cleancashflowonemont,item.getCleancashflowonemonth());
                   dtoData.setCleancashflownextday(cleancashflownextda);
                   dtoData.setCleancashflowsevenday(cleancashflowsevenda);
                   dtoData.setCleancashflowonemonth(cleancashflowonemont);
               } 
            } 
            dtoData.setProdId("root");
            dtoData.setCombinationname("");
            dtoData.setManager("");
            list.add(dtoData); 
            dataPage1.setRoot(list);
            if ("N".equals(yesOrNo)) { 
                getRiskTemporaryDataByOne(result,pageParam);
            }else {
                getRiskTemporaryDataPierceByOne(result,pageParam);  
            }   
        }else if (!isProdId && StringUtil.isNotEmpty(pageParam.get("manager"))){
            if ("02".equals(statisticalMethod)) {  
                List<RiskMonitorIndicatorsDto> list = new ArrayList<>();
                for (String manager : managerList) {
                    RiskMonitorIndicatorsDto dtoData = new RiskMonitorIndicatorsDto(); 
                    BigDecimal cleancashflownextda = BigDecimal.ZERO; 
                    BigDecimal cleancashflowsevenda = BigDecimal.ZERO; 
                    BigDecimal cleancashflowonemont = BigDecimal.ZERO;
                    if (!StringUtil.isEmptyList(dataPage.getRoot())) { 
                       Map<String, List<RiskMonitorIndicatorsDto>> riskMonitorMap = dataPage.getRoot().stream().collect(JoyinCollectors.groupingBy(item->item.getProdManager()));
                       if (StringUtil.isNotEmpty(riskMonitorMap) && StringUtil.isNotEmpty(riskMonitorMap.get(manager))) { 
                           for (RiskMonitorIndicatorsDto item : riskMonitorMap.get(manager)) {
                               dtoData = item; 
                               cleancashflownextda = ArithmeticUtil.doAdd(cleancashflownextda,item.getCleancashflownextday());
                               cleancashflowsevenda = ArithmeticUtil.doAdd(cleancashflowsevenda,item.getCleancashflowsevenday());
                               cleancashflowonemont = ArithmeticUtil.doAdd(cleancashflowonemont,item.getCleancashflowonemonth());
                               dtoData.setCleancashflownextday(cleancashflownextda);
                               dtoData.setCleancashflowsevenday(cleancashflowsevenda);
                               dtoData.setCleancashflowonemonth(cleancashflowonemont);
                           } 
                       }
                    } 
                    dtoData.setProdId("root");
                    dtoData.setProdManager(manager);
                    dtoData.setCombinationname("");
                    list.add(dtoData);  
                    List<String> managers = new ArrayList<>();
                    managers.add(manager);
                    pageParam.put("managers", managers);
                    if ("N".equals(yesOrNo)) { 
                        getRiskTemporaryDataByOne(result,pageParam);
                        result.put(manager, result.get("root")); 
                    }else {
                        getRiskTemporaryDataPierceByOne(result,pageParam);  
                        result.put(manager, result.get("root"));
                    } 
                    dataPage1.setRoot(list); 
                }  
            }else if ("01".equals(statisticalMethod)) {
                dataPage1 = dataPage;
                if ("N".equals(yesOrNo)) { 
                    getRiskTemporaryData(result,pageParam);
                }else {
                    getRiskTemporaryPierceData(result,pageParam);  
                }
            }
        }  
        if (!StringUtil.isEmptyList(dataPage1.getRoot())) {
             for(RiskMonitorIndicatorsDto dto:dataPage1.getRoot()){
                 RiskTemporaryDataDto data =null; 
                if (isProdId) {
                    data =  result.get(dto.getProdId());
                }else if (!isProdId && !StringUtil.isNotEmpty(statisticalMethod)) {
                    data =  result.get("root"); 
                }else if (!isProdId && "01".equals(statisticalMethod)) {
                    data =  result.get(dto.getProdId());  
                }else if (!isProdId && "02".equals(statisticalMethod)) {
                    data =  result.get(dto.getProdManager());
                }
                if(StringUtil.isNotEmpty(data)){
                    //产品久期
                    dto.setProdduration(data.getProductDuration());
                    //资产久期
                    dto.setAssetduration(data.getAssetDuration());
                    //债券久期
                    dto.setSecduration(data.getSecDuration());
                    //久期错配
                    dto.setDurationmismatch(data.getDurationmismatch());
                    //期限错配
                    dto.setTermmismatch(data.getTermmismatch()); 
                    //杠杆率%
                    dto.setLeverageratio(data.getLeverageratio());
                    //开放式高流动性资产占比% 
                    dto.setOpenhighliquidityratio(data.getOpenHighMobilityAsset()); 
                    //高流动性资产占比% 
                    dto.setHighliquidityratio(data.getHighMobilityAsset()); 
                    //单一最大认购客户集中度
                    dto.setSinglecustomerconcentration(data.getSingleCustomerConcentration());
                    //单一最大资产集中度
                    dto.setSingleassetconcentration(data.getMaxAssetsCD());
                    //单一最大融资人集中度
                    dto.setSinglefinancierconcentration(data.getLargestFinancierCD());
                    //非公开债券集中度
                    dto.setNoopensecconcentration(StringUtil.isNotEmpty(data.getNonPublicBondCD())?data.getNonPublicBondCD() : BigDecimal.ZERO);
                    //单一最大认购客户集中度
                    dto.setSinglejoinratio(BigDecimal.ZERO);
                    //单一机构非标占资本净额比例%
                    if (StringUtil.isNotEmpty(data.getSingleNonStandardrRatio())) {
                        dto.setSinglenonstandardratio(data.getSingleNonStandardrRatio());
                    }
                    //非标资产占产品净资产比例  
                    if (StringUtil.isNotEmpty(data.getNetAassetsRatio())) {
                        dto.setNonstandardratioclean(data.getTotalAassetsRatio());
                    }
                    //非标资产占全行总资产比例 
                    if (StringUtil.isNotEmpty(data.getTotalAassetsRatio())) {
                        dto.setNonstandardratiowhole(data.getNetAassetsRatio());
                    }  
                    //单一关联方单笔交易占资本净额比例
                    if (StringUtil.isNotEmpty(data.getSinglejoinratio())) {
                        dto.setSinglejoinratio(data.getSinglejoinratio());
                    } 
                    //浮动盈亏金额
                    dto.setFloatingpl(StringUtil.isNotEmpty(data.getTdyFloatIngpl())? data.getTdyFloatIngpl() : BigDecimal.ZERO);   
                 } 
              }  
            } 
        return dataPage1;
    }
 
    /**
     * 查询穿透前的数据
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    public void getRiskTemporaryDataByOne(Map<String,RiskTemporaryDataDto> result,Map<String, Object> pageParam) { 
        RiskTemporaryDataDto dto = new RiskTemporaryDataDto(); 
        Map<String, BigDecimal> assetDurationMap =new HashMap<>();
        Map<String, BigDecimal> ssDurationMap =new HashMap<>();
        List<String> prodIdList =new ArrayList<>();
        // 查询资产久期 
        List<RiskTemporaryDataDto> assetDurationList = riskMonitorIndicatorsMapper.queryAssetDuration(pageParam);
        if (!CollectionUtils.isEmpty(assetDurationList)) {
            BigDecimal assetDurationMol = BigDecimal.ZERO;
            BigDecimal assetDurationDen = BigDecimal.ZERO; 
            for(RiskTemporaryDataDto dataDto: assetDurationList) {
                assetDurationMol = ArithmeticUtil.doAdd(assetDurationMol,dataDto.getAssetDurationMol());
                assetDurationDen = ArithmeticUtil.doAdd(assetDurationDen,dataDto.getAssetDurationDen());
                assetDurationMap.put(dataDto.getProdId(), dataDto.getAssetDuration());
                ssDurationMap.put(dataDto.getProdId(), dataDto.getSsDuration());
                prodIdList.add(dataDto.getProdId());
            }
            dto.setAssetDuration(ArithmeticUtil.divide(assetDurationMol, assetDurationDen, 16));
        } 
        // 查询产品久期
        Map<String, BigDecimal> productDurationMap =new HashMap<>();
        BigDecimal productDuration = calculateProDurationSummary(pageParam,prodIdList,productDurationMap);
        if (StringUtil.isNotEmpty(productDuration)) {
            dto.setProductDuration(productDuration);
        }   
        
        // 查询债券久期
        List<RiskTemporaryDataDto> secDurationList = riskMonitorIndicatorsMapper.querySecDuration(pageParam); 
        if (!CollectionUtils.isEmpty(secDurationList)) {    
                BigDecimal secDurationMol = BigDecimal.ZERO;
                BigDecimal secDurationDen = BigDecimal.ZERO; 
                for(RiskTemporaryDataDto dataDto: secDurationList) {
                    secDurationMol = ArithmeticUtil.doAdd(secDurationMol,dataDto.getSecDurationMol());
                    secDurationDen = ArithmeticUtil.doAdd(secDurationDen,dataDto.getSecDurationDel());
                }      
                dto.setSecDuration(ArithmeticUtil.divide(secDurationMol, secDurationDen, 16));
        } 
         
        //久期错配 
        //期限错配
        if (StringUtil.isNotEmpty(assetDurationMap) && StringUtil.isNotEmpty(productDurationMap)){ 
            BigDecimal durationmismatch = BigDecimal.ZERO;
            BigDecimal termmismatch = BigDecimal.ZERO; 
            for (String asset : assetDurationMap.keySet()) {
               BigDecimal assetDuration = assetDurationMap.get(asset);
               BigDecimal ssDuration = ssDurationMap.get(asset);
               BigDecimal proDuration = productDurationMap.get(asset);
               durationmismatch=ArithmeticUtil.doAdd(durationmismatch,ArithmeticUtil.subtract(assetDuration, ArithmeticUtil.doAdd(proDuration,ssDuration))); 
               termmismatch=ArithmeticUtil.doAdd(termmismatch,ArithmeticUtil.subtract(assetDuration, proDuration)); 
            }
            dto.setDurationmismatch(durationmismatch);
            dto.setTermmismatch(termmismatch);
        } 
    
        // 杠杆率
        // 查询产品的总资产、净资产 
        Map<String, BigDecimal> totalAssetMap =new HashMap<>();
        Map<String, BigDecimal> netAssetMap =new HashMap<>();
        List<RiskTemporaryDataDto> totalAssetList = riskMonitorIndicatorsMapper.queryTotalAsset(pageParam);
        if (!CollectionUtils.isEmpty(totalAssetList)) {
            BigDecimal totalAssetMol = BigDecimal.ZERO;
            BigDecimal netAssetDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : totalAssetList) { 
                totalAssetMol = ArithmeticUtil.doAdd(totalAssetMol,dataDto.getTotalAsset());
                netAssetDen = ArithmeticUtil.doAdd(netAssetDen,dataDto.getNetAsset()); 
                totalAssetMap.put(dataDto.getProdId(), dataDto.getTotalAsset());
                netAssetMap.put(dataDto.getProdId(), dataDto.getNetAsset());
            } 
            dto.setTotalAsset(totalAssetMol);  
            dto.setNetAsset(netAssetDen);
            dto.setLeverageratio(ArithmeticUtil.multiply(ArithmeticUtil.divide(totalAssetMol, netAssetDen, 16),new BigDecimal(100)));
        } 
        
        // 查询开放式高流动性资产  
        Map<String, BigDecimal> openHighMobilityMap =new HashMap<>();
        List<RiskTemporaryDataDto> openHighMobilityAssetList = riskMonitorIndicatorsMapper.queryOpenHighMobilityAsset(pageParam); 
        if (!CollectionUtils.isEmpty(openHighMobilityAssetList)) {
            BigDecimal openHighMobilityMol = BigDecimal.ZERO;
            BigDecimal openHighMobilityDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : openHighMobilityAssetList) { 
                openHighMobilityMol = ArithmeticUtil.doAdd(openHighMobilityMol,dataDto.getOpenHighMobilityMol());
                openHighMobilityDen = ArithmeticUtil.doAdd(openHighMobilityDen,dataDto.getOpenHighMobilityDen()); 
                openHighMobilityMap.put(dataDto.getProdId(), dataDto.getOpenHighMobilityDen());
            }
            dto.setOpenHighMobilityAsset(ArithmeticUtil.multiply(ArithmeticUtil.divide(openHighMobilityMol, openHighMobilityDen, 4),new BigDecimal(100)));
        }
         
        // 查询-高流动性资产 
        List<RiskTemporaryDataDto> highMobilityAssetList = riskMonitorIndicatorsMapper.queryHighMobilityAsset(pageParam);
        if (!CollectionUtils.isEmpty(highMobilityAssetList)) {
            BigDecimal highMobilityMol = BigDecimal.ZERO;
            BigDecimal highMobilityDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : highMobilityAssetList) { 
                highMobilityMol = ArithmeticUtil.doAdd(highMobilityMol,dataDto.getHighMobilityMol());
                highMobilityDen = ArithmeticUtil.doAdd(highMobilityDen,dataDto.getHighMobilityDen()); 
            }
            dto.setHighMobilityAsset(ArithmeticUtil.multiply(ArithmeticUtil.divide(highMobilityMol, highMobilityDen, 4),new BigDecimal(100)));
        }
        // 查询-单一最大资产集中度  
        List<RiskTemporaryDataDto> maxAssetsCDList = riskMonitorIndicatorsMapper.queryMaxAssetsCD(pageParam);
        if (!CollectionUtils.isEmpty(maxAssetsCDList)) {
            BigDecimal maxAssetsCD = BigDecimal.ZERO; 
            BigDecimal minAssetsCD = BigDecimal.ZERO; 
            BigDecimal netAssetDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : maxAssetsCDList) { 
//                maxAssetsCD = ArithmeticUtil.doAdd(maxAssetsCD,dataDto.getMaxAssetsCD());
                minAssetsCD = dataDto.getMaxAssetsCD();
                if (maxAssetsCD.compareTo(minAssetsCD) <= 0) {
                    maxAssetsCD = minAssetsCD;
                }
                netAssetDen = ArithmeticUtil.doAdd(netAssetDen,netAssetMap.get(dataDto.getProdId())); 
            }
            dto.setMaxAssetsCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(maxAssetsCD, netAssetDen, 4),new BigDecimal(100)));
        }
        // 查询-单一最大融资人集中度 
        RiskTemporaryDataDto largestFinancierCD = riskMonitorIndicatorsMapper.queryLargestFinancierCDSummary(pageParam);
        BigDecimal netAssetDen = BigDecimal.ZERO; 
        if (StringUtil.isNotEmpty(largestFinancierCD)) { 
            for (RiskTemporaryDataDto dataDto : totalAssetList) {  
                netAssetDen = ArithmeticUtil.doAdd(netAssetDen,dataDto.getNetAsset());  
            }   
            dto.setLargestFinancierCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(largestFinancierCD.getLargestFinancierCD(), netAssetDen, 4),new BigDecimal(100)));
        }  
        // 查询-非公开债券集中度 
        List<RiskTemporaryDataDto> nonPublicBondCDList = riskMonitorIndicatorsMapper.queryNonPublicBondCD(pageParam);
        if (!CollectionUtils.isEmpty(nonPublicBondCDList)) {
            BigDecimal nonPublicBondCD = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : nonPublicBondCDList) { 
                nonPublicBondCD = ArithmeticUtil.doAdd(nonPublicBondCD,dataDto.getNonPublicBondCD()); 
            } 
            dto.setNonPublicBondCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(nonPublicBondCD, netAssetDen, 4),new BigDecimal(100))); 
        }
        List<RiskTemporaryDataDto> mobilityAssetSummaryList = riskMonitorIndicatorsMapper.queryMobilityAssetSummary(pageParam);
        BigDecimal mobilityAssetSummary = BigDecimal.ZERO; 
        if (!CollectionUtils.isEmpty(mobilityAssetSummaryList)) {
            for (RiskTemporaryDataDto dataDto : mobilityAssetSummaryList) {
                mobilityAssetSummary = ArithmeticUtil.doAdd(mobilityAssetSummary,dataDto.getMobilityAssetSummary());
            }
        }
        
        RiskTemporaryDataDto singleNonStandardrRatio = riskMonitorIndicatorsMapper.querySingleNonStandardrRatio(pageParam);
        if (StringUtil.isNotEmpty(singleNonStandardrRatio)) {
            dto.setSingleNonStandardrRatio(ArithmeticUtil.multiply(ArithmeticUtil.divide(singleNonStandardrRatio.getSingleNonStandardrRatio(), netAssetDen, 4),new BigDecimal(100)));
        }
       
        // 查询-非标资产占产品净资产比例 - 非标资产占全行总资产比例 
        List<RiskTemporaryDataDto> assetsRatioList = riskMonitorIndicatorsMapper.queryAssetsRatio(pageParam); 
        if (!CollectionUtils.isEmpty(assetsRatioList)) {
            BigDecimal netAassetsRatio = BigDecimal.ZERO; 
            BigDecimal totalAassetsRatio = BigDecimal.ZERO;  
            for (RiskTemporaryDataDto dataDto : assetsRatioList) { 
                netAassetsRatio = ArithmeticUtil.doAdd(netAassetsRatio,dataDto.getNetAassetsRatio());
                totalAassetsRatio = ArithmeticUtil.doAdd(totalAassetsRatio,dataDto.getTotalAassetsRatio());
//                netAssetDen = ArithmeticUtil.doAdd(netAssetDen,netAssetMap.get(dataDto.getProdId()));   
            } 
            dto.setNetAassetsRatio(ArithmeticUtil.multiply(ArithmeticUtil.divide(netAassetsRatio, netAssetDen, 4),new BigDecimal(100)));
            dto.setTotalAassetsRatio(ArithmeticUtil.multiply(ArithmeticUtil.divide(totalAassetsRatio, mobilityAssetSummary, 4),new BigDecimal(100))); 
        }
        // 查询-浮动盈亏金额 
        List<RiskTemporaryDataDto> tdyFloatIngplList = riskMonitorIndicatorsMapper.queryTdyFloatIngpl(pageParam); 
        if (!CollectionUtils.isEmpty(tdyFloatIngplList)) {
            BigDecimal tdyFloatIngplMol = BigDecimal.ZERO; 
            BigDecimal tdyFloatIngplDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : tdyFloatIngplList) { 
                tdyFloatIngplMol = ArithmeticUtil.doAdd(tdyFloatIngplMol,dataDto.getTdyFloatIngplMol()); 
                tdyFloatIngplDen = ArithmeticUtil.doAdd(tdyFloatIngplDen,dataDto.getTdyFloatIngplDen()); 
            } 
            dto.setTdyFloatIngpl(ArithmeticUtil.divide(tdyFloatIngplMol, tdyFloatIngplDen, 2));   
        }
        result.put("root", dto); 
    }
    
    /**
     * 查询穿透后合并为一条数据
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    public void getRiskTemporaryDataPierceByOne(Map<String,RiskTemporaryDataDto> result,Map<String, Object> pageParam) { 
        RiskTemporaryDataDto dto = new RiskTemporaryDataDto(); 
        Map<String, BigDecimal> assetDurationMap =new HashMap<>();
        Map<String, BigDecimal> ssDurationMap =new HashMap<>();
        List<String> prodIdList =new ArrayList<>();
        // 查询资产久期 
        List<RiskTemporaryDataDto> assetDurationList = riskMonitorIndicatorsMapper.queryAssetDurationPierce(pageParam);
        if (!CollectionUtils.isEmpty(assetDurationList)) {
            BigDecimal assetDurationMol = BigDecimal.ZERO;
            BigDecimal assetDurationDen = BigDecimal.ZERO; 
            for(RiskTemporaryDataDto dataDto: assetDurationList) {
                assetDurationMol = ArithmeticUtil.doAdd(assetDurationMol,dataDto.getAssetDurationMol());
                assetDurationDen = ArithmeticUtil.doAdd(assetDurationDen,dataDto.getAssetDurationDen());
                assetDurationMap.put(dataDto.getProdId(), dataDto.getAssetDuration());
                ssDurationMap.put(dataDto.getProdId(), dataDto.getSsDuration());
                prodIdList.add(dataDto.getProdId());
            }
            dto.setAssetDuration(ArithmeticUtil.divide(assetDurationMol, assetDurationDen, 16));
        } 
        // 查询产品久期
        Map<String, BigDecimal> productDurationMap =new HashMap<>();
        BigDecimal productDuration = calculateProDurationSummary(pageParam,prodIdList,productDurationMap);
        if (StringUtil.isNotEmpty(productDuration)) {
            dto.setProductDuration(productDuration);
        }   
        
        // 查询债券久期
        List<RiskTemporaryDataDto> secDurationList = riskMonitorIndicatorsMapper.querySecDurationPierce(pageParam); 
        if (!CollectionUtils.isEmpty(secDurationList)) {    
            BigDecimal secDurationMol = BigDecimal.ZERO;
            BigDecimal secDurationDen = BigDecimal.ZERO; 
            for(RiskTemporaryDataDto dataDto: secDurationList) {
                secDurationMol = ArithmeticUtil.doAdd(secDurationMol,dataDto.getSecDurationMol());
                secDurationDen = ArithmeticUtil.doAdd(secDurationDen,dataDto.getSecDurationDel());
            }      
            dto.setSecDuration(ArithmeticUtil.divide(secDurationMol, secDurationDen, 16));
        } 
         
        //久期错配 
        //期限错配
        if (StringUtil.isNotEmpty(assetDurationMap) && StringUtil.isNotEmpty(productDurationMap)){ 
            BigDecimal durationmismatch = BigDecimal.ZERO;
            BigDecimal termmismatch = BigDecimal.ZERO; 
            for (String asset : assetDurationMap.keySet()) {
               BigDecimal assetDuration = assetDurationMap.get(asset);
               BigDecimal ssDuration = ssDurationMap.get(asset);
               BigDecimal proDuration = productDurationMap.get(asset);
               durationmismatch=ArithmeticUtil.doAdd(durationmismatch,ArithmeticUtil.subtract(assetDuration, ArithmeticUtil.doAdd(proDuration,ssDuration))); 
               termmismatch=ArithmeticUtil.doAdd(termmismatch,ArithmeticUtil.subtract(assetDuration, proDuration)); 
            }
            dto.setDurationmismatch(durationmismatch);
            dto.setTermmismatch(termmismatch);
        } 
    
        // 杠杆率
        // 查询产品的总资产、净资产 
        Map<String, BigDecimal> totalAssetMap =new HashMap<>();
        Map<String, BigDecimal> netAssetMap =new HashMap<>();
        List<RiskTemporaryDataDto> totalAssetList = riskMonitorIndicatorsMapper.queryTotalAssetPierce(pageParam);
        if (!CollectionUtils.isEmpty(totalAssetList)) {
            BigDecimal totalAssetMol = BigDecimal.ZERO; 
            BigDecimal netAssetDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : totalAssetList) { 
                totalAssetMol = ArithmeticUtil.doAdd(totalAssetMol,dataDto.getTotalAsset());
                netAssetDen = ArithmeticUtil.doAdd(netAssetDen,dataDto.getNetAsset()); 
                totalAssetMap.put(dataDto.getProdId(), dataDto.getTotalAsset());
                netAssetMap.put(dataDto.getProdId(), dataDto.getNetAsset());
            } 
            dto.setTotalAsset(totalAssetMol);  
            dto.setNetAsset(netAssetDen);
            dto.setLeverageratio(ArithmeticUtil.multiply(ArithmeticUtil.divide(totalAssetMol, netAssetDen, 16),new BigDecimal(100)));
        } 
        
        // 查询开放式高流动性资产  
        Map<String, BigDecimal> openHighMobilityMap =new HashMap<>();
        List<RiskTemporaryDataDto> openHighMobilityAssetList = riskMonitorIndicatorsMapper.queryOpenHighMobilityAssetPierce(pageParam); 
        if (!CollectionUtils.isEmpty(openHighMobilityAssetList)) {
            BigDecimal openHighMobilityMol = BigDecimal.ZERO;
            BigDecimal openHighMobilityDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : openHighMobilityAssetList) { 
                openHighMobilityMol = ArithmeticUtil.doAdd(openHighMobilityMol,dataDto.getOpenHighMobilityMol());
                openHighMobilityDen = ArithmeticUtil.doAdd(openHighMobilityDen,dataDto.getOpenHighMobilityDen()); 
                openHighMobilityMap.put(dataDto.getProdId(), dataDto.getOpenHighMobilityDen());
            }
            dto.setOpenHighMobilityAsset(ArithmeticUtil.multiply(ArithmeticUtil.divide(openHighMobilityMol, openHighMobilityDen, 4),new BigDecimal(100)));
        }
         
        // 查询-高流动性资产 
        List<RiskTemporaryDataDto> highMobilityAssetList = riskMonitorIndicatorsMapper.queryHighMobilityAssetPierce(pageParam);
        if (!CollectionUtils.isEmpty(highMobilityAssetList)) {
            BigDecimal highMobilityMol = BigDecimal.ZERO;
            BigDecimal highMobilityDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : highMobilityAssetList) { 
                highMobilityMol = ArithmeticUtil.doAdd(highMobilityMol,dataDto.getHighMobilityMol());
                highMobilityDen = ArithmeticUtil.doAdd(highMobilityDen,dataDto.getHighMobilityDen()); 
            }
            dto.setHighMobilityAsset(ArithmeticUtil.multiply(ArithmeticUtil.divide(highMobilityMol, highMobilityDen, 4),new BigDecimal(100)));
        }
        // 查询-单一最大资产集中度  
        List<RiskTemporaryDataDto> maxAssetsCDList = riskMonitorIndicatorsMapper.queryMaxAssetsCDPierce(pageParam);
        if (!CollectionUtils.isEmpty(maxAssetsCDList)) {
            BigDecimal maxAssetsCD = BigDecimal.ZERO; 
            BigDecimal minAssetsCD = BigDecimal.ZERO; 
            BigDecimal netAssetDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : maxAssetsCDList) { 
//                maxAssetsCD = ArithmeticUtil.doAdd(maxAssetsCD,dataDto.getMaxAssetsCD());
                minAssetsCD = dataDto.getMaxAssetsCD();
                if (maxAssetsCD.compareTo(minAssetsCD) <= 0) {
                    maxAssetsCD = minAssetsCD;
                }
                netAssetDen = ArithmeticUtil.doAdd(netAssetDen,netAssetMap.get(dataDto.getProdId())); 
            }
            dto.setMaxAssetsCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(maxAssetsCD, netAssetDen, 4),new BigDecimal(100)));
        }
        // 查询-单一最大融资人集中度 
        RiskTemporaryDataDto largestFinancierCD = riskMonitorIndicatorsMapper.queryLargestFinancierCDSummaryPierce(pageParam);
        BigDecimal netAssetDen = BigDecimal.ZERO; 
        if (StringUtil.isNotEmpty(largestFinancierCD)) { 
            for (RiskTemporaryDataDto dataDto : totalAssetList) {  
                netAssetDen = ArithmeticUtil.doAdd(netAssetDen,dataDto.getNetAsset());  
            }   
            dto.setLargestFinancierCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(largestFinancierCD.getLargestFinancierCD(), netAssetDen, 4),new BigDecimal(100)));
        }  
        // 查询-非公开债券集中度 
        List<RiskTemporaryDataDto> nonPublicBondCDList = riskMonitorIndicatorsMapper.queryNonPublicBondCDPierce(pageParam);
        if (!CollectionUtils.isEmpty(nonPublicBondCDList)) {
            BigDecimal nonPublicBondCD = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : nonPublicBondCDList) { 
                nonPublicBondCD = ArithmeticUtil.doAdd(nonPublicBondCD,dataDto.getNonPublicBondCD()); 
            } 
            dto.setNonPublicBondCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(nonPublicBondCD, netAssetDen, 4),new BigDecimal(100))); 
        }
        List<RiskTemporaryDataDto> mobilityAssetSummaryList = riskMonitorIndicatorsMapper.queryMobilityAssetSummaryPierce(pageParam);
        BigDecimal mobilityAssetSummary = BigDecimal.ZERO; 
        if (!CollectionUtils.isEmpty(mobilityAssetSummaryList)) {
            for (RiskTemporaryDataDto dataDto : mobilityAssetSummaryList) {
                mobilityAssetSummary = ArithmeticUtil.doAdd(mobilityAssetSummary,dataDto.getMobilityAssetSummary());
            }
        }
        RiskTemporaryDataDto singleNonStandardrRatio = riskMonitorIndicatorsMapper.querySingleNonStandardrRatioPierce(pageParam);
        if (StringUtil.isNotEmpty(singleNonStandardrRatio)) {
            dto.setSingleNonStandardrRatio(ArithmeticUtil.multiply(ArithmeticUtil.divide(singleNonStandardrRatio.getSingleNonStandardrRatio(), netAssetDen, 4),new BigDecimal(100)));
        }
        // 查询-非标资产占产品净资产比例 - 非标资产占全行总资产比例 
        List<RiskTemporaryDataDto> assetsRatioList = riskMonitorIndicatorsMapper.queryAssetsRatioPierce(pageParam); 
        if (!CollectionUtils.isEmpty(assetsRatioList)) {
            BigDecimal netAassetsRatio = BigDecimal.ZERO; 
            BigDecimal totalAassetsRatio = BigDecimal.ZERO;  
            for (RiskTemporaryDataDto dataDto : assetsRatioList) { 
                netAassetsRatio = ArithmeticUtil.doAdd(netAassetsRatio,dataDto.getNetAassetsRatio());
                totalAassetsRatio = ArithmeticUtil.doAdd(totalAassetsRatio,dataDto.getTotalAassetsRatio());
            } 
            dto.setNetAassetsRatio(ArithmeticUtil.multiply(ArithmeticUtil.divide(netAassetsRatio, netAssetDen, 4),new BigDecimal(100)));
            dto.setTotalAassetsRatio(ArithmeticUtil.multiply(ArithmeticUtil.divide(totalAassetsRatio, mobilityAssetSummary, 4),new BigDecimal(100))); 
        }
        // 查询-浮动盈亏金额 
        List<RiskTemporaryDataDto> tdyFloatIngplList = riskMonitorIndicatorsMapper.queryTdyFloatIngplPierce(pageParam); 
        if (!CollectionUtils.isEmpty(tdyFloatIngplList)) {
            BigDecimal tdyFloatIngplMol = BigDecimal.ZERO; 
            BigDecimal tdyFloatIngplDen = BigDecimal.ZERO; 
            for (RiskTemporaryDataDto dataDto : tdyFloatIngplList) { 
                tdyFloatIngplMol = ArithmeticUtil.doAdd(tdyFloatIngplMol,dataDto.getTdyFloatIngplMol()); 
                tdyFloatIngplDen = ArithmeticUtil.doAdd(tdyFloatIngplDen,dataDto.getTdyFloatIngplDen()); 
            } 
            dto.setTdyFloatIngpl(ArithmeticUtil.divide(tdyFloatIngplMol, tdyFloatIngplDen, 2));   
        }
        result.put("root", dto);  
    }
    
    /**
     * 查询穿透前的数据
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    public void getRiskTemporaryData(Map<String,RiskTemporaryDataDto> result,Map<String, Object> pageParam) {  
         List<String> prodList = new ArrayList<>(result.keySet());
        // 查询产品久期 
        Map<String,BigDecimal> productDurationMap = calculateProDuration(pageParam,prodList);
        // 查询债券久期
        List<RiskTemporaryDataDto> secDurationList = riskMonitorIndicatorsMapper.querySecDuration(pageParam); 
        // 查询资产久期
        List<RiskTemporaryDataDto> assetDurationList = riskMonitorIndicatorsMapper.queryAssetDuration(pageParam); 
        // 查询产品的总资产、净资产 
        List<RiskTemporaryDataDto> totalAssetList = riskMonitorIndicatorsMapper.queryTotalAsset(pageParam); 
        // 查询开放式高流动性资产 
        List<RiskTemporaryDataDto> openHighMobilityAssetList = riskMonitorIndicatorsMapper.queryOpenHighMobilityAsset(pageParam);
        // 查询现金 
        List<RiskTemporaryDataDto> cashList = riskMonitorIndicatorsMapper.queryCash(pageParam);
        // 查询-高流动性资产 
        List<RiskTemporaryDataDto> highMobilityAssetList = riskMonitorIndicatorsMapper.queryHighMobilityAsset(pageParam);
        // 查询-单一最大资产集中度  
        List<RiskTemporaryDataDto> maxAssetsCDList = riskMonitorIndicatorsMapper.queryMaxAssetsCD(pageParam);
        // 查询-单一最大融资人集中度 
        List<RiskTemporaryDataDto> largestFinancierCDList = riskMonitorIndicatorsMapper.queryLargestFinancierCD(pageParam);
        // 查询-非公开债券集中度 
        List<RiskTemporaryDataDto> nonPublicBondCDList = riskMonitorIndicatorsMapper.queryNonPublicBondCD(pageParam); 
        // 查询-单一关联方单笔交易占资本净额比例
        List<RiskTemporaryDataDto> singlejoinratioList = riskMonitorIndicatorsMapper.querySinglejoinratio(pageParam); 
        // 查询-浮动盈亏金额 
        List<RiskTemporaryDataDto> tdyFloatIngplList = riskMonitorIndicatorsMapper.queryTdyFloatIngpl(pageParam); 
        Map<String,List<RiskTemporaryDataDto>> secDurationMap = secDurationList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> assetDurationMap = assetDurationList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> totalAssetMap = totalAssetList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> openHighMobilityAssetMap = openHighMobilityAssetList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> cashMap = cashList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> highMobilityAssetMap = highMobilityAssetList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> maxAssetsCDMap = maxAssetsCDList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> largestFinancierCDMap = largestFinancierCDList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> nonPublicBondCDMap = nonPublicBondCDList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> tdyFloatIngplMap = tdyFloatIngplList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> singlejoinratioMap = singlejoinratioList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        for (String prodresultKey : result.keySet()){
            RiskTemporaryDataDto dto = result.get(prodresultKey); 
            //债券久期 
            if (StringUtil.isNotEmpty(secDurationMap)) {
                List<RiskTemporaryDataDto> list1 = secDurationMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list1)) {
                    dto.setSecDuration(list1.get(0).getSecDuration());
                } 
            }
            //产品久期 
            if (StringUtil.isNotEmpty(productDurationMap)) {
                BigDecimal productDuration = productDurationMap.get(prodresultKey);
                if (StringUtil.isNotEmpty(productDuration)) {
                    dto.setProductDuration(productDuration); 
                } 
            }
            //资产久期
            if (StringUtil.isNotEmpty(assetDurationMap)) { 
                List<RiskTemporaryDataDto> list1 = assetDurationMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list1)) {
                    dto.setAssetDuration(list1.get(0).getAssetDuration()); 
                    dto.setSsDuration(list1.get(0).getSsDuration()); 
                } 
            }
            //久期错配
            if (StringUtil.isNotEmpty(dto.getAssetDuration()) && StringUtil.isNotEmpty(dto.getProductDuration())){ 
                dto.setDurationmismatch(ArithmeticUtil.subtract(dto.getAssetDuration(), ArithmeticUtil.doAdd(dto.getProductDuration(),dto.getSsDuration()))); 
            }
            //期限错配
            if (StringUtil.isNotEmpty(dto.getAssetDuration()) && StringUtil.isNotEmpty(dto.getProductDuration())){ 
                dto.setTermmismatch(ArithmeticUtil.subtract(dto.getAssetDuration(), dto.getProductDuration())); 
            } 
            //杠杆率%
            //TODO
            if (StringUtil.isNotEmpty(totalAssetMap)) {
                List<RiskTemporaryDataDto> list1 = totalAssetMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list1)) {
                    dto.setNetAsset(list1.get(0).getNetAsset());
                    dto.setTotalAsset(list1.get(0).getTotalAsset());
                    dto.setLeverageratio(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getTotalAsset(), dto.getNetAsset(), 4),new BigDecimal(100))); 
                } 
            } 

            //开放式高流动性资产占比% 
            if (StringUtil.isNotEmpty(openHighMobilityAssetMap)) {
                List<RiskTemporaryDataDto> list2 = openHighMobilityAssetMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list2)){
                    dto.setOpenHighMobilityAsset(list2.get(0).getOpenHighMobilityAsset());
                    dto.setOpenHighMobilityAsset(ArithmeticUtil.multiply(dto.getOpenHighMobilityAsset(),new BigDecimal(100)));
                } 
            } 
            if (StringUtil.isNotEmpty(cashMap)) {
                List<RiskTemporaryDataDto> list3 = cashMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list3)){
                    dto.setCash(list3.get(0).getCash());
                }
            } 
            //高流动性资产占比%
            if (StringUtil.isNotEmpty(highMobilityAssetMap)) {
                List<RiskTemporaryDataDto> list5 = highMobilityAssetMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list5)){
                    dto.setHighMobilityAsset(list5.get(0).getHighMobilityAsset());
                    dto.setHighMobilityAsset(ArithmeticUtil.multiply(dto.getHighMobilityAsset(),new BigDecimal(100)));
                }
            }
            //单一最大认购客户集中度
            dto.setSingleCustomerConcentration(BigDecimal.ZERO);
            //单一最大资产集中度
            if (StringUtil.isNotEmpty(maxAssetsCDMap)) {
                List<RiskTemporaryDataDto> list7 = maxAssetsCDMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list7)){
                    dto.setMaxAssetsCD(list7.get(0).getMaxAssetsCD()); 
                    dto.setMaxAssetsCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getMaxAssetsCD(), dto.getNetAsset(), 4),new BigDecimal(100)));
                }
            }
            //单一最大融资人集中度
            if (StringUtil.isNotEmpty(largestFinancierCDMap)) {
                List<RiskTemporaryDataDto> list8 = largestFinancierCDMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list8)){
                    dto.setLargestFinancierCD(list8.get(0).getLargestFinancierCD());
                    dto.setLargestFinancierCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getLargestFinancierCD(), dto.getNetAsset(), 4),new BigDecimal(100)));
                }
            }
             
            //非公开债券集中度
            if (StringUtil.isNotEmpty(nonPublicBondCDMap)) {
                List<RiskTemporaryDataDto> list9 = nonPublicBondCDMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list9)){
                    dto.setNonPublicBondCD(list9.get(0).getNonPublicBondCD()); 
                    dto.setNonPublicBondCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getNonPublicBondCD(), dto.getNetAsset(), 4),new BigDecimal(100)));
                }
            } 
            //单一关联方单笔交易占资本净额比例
            if (StringUtil.isNotEmpty(singlejoinratioMap)) {
                List<RiskTemporaryDataDto> list10 = singlejoinratioMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list10)){
                    dto.setSinglejoinratio(list10.get(0).getSinglejoinratio());                  
                }
            }  
            //浮动盈亏金额
            if (StringUtil.isNotEmpty(tdyFloatIngplMap)) {
                List<RiskTemporaryDataDto> list11 = tdyFloatIngplMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list11)){
                    dto.setTdyFloatIngpl(list11.get(0).getTdyFloatIngpl()); 
                }
            }
            result.put(prodresultKey, dto);
        }   
    }
    /**
     * 查询穿透后的数据
     * @param pageParam
     * @see
     * @since 1.0
     */
    public void getRiskTemporaryPierceData(Map<String,RiskTemporaryDataDto> result,Map<String, Object> pageParam) { 
        List<String> prodList = new ArrayList<>(result.keySet());
        // 查询产品久期 
        Map<String,BigDecimal> productDurationMap = calculateProDuration(pageParam,prodList);
        // 查询债券久期
        List<RiskTemporaryDataDto> secDurationList = riskMonitorIndicatorsMapper.querySecDurationPierce(pageParam); 
        // 查询资产久期
        List<RiskTemporaryDataDto> assetDurationList = riskMonitorIndicatorsMapper.queryAssetDurationPierce(pageParam); 
        // 查询产品的总资产、净资产 
        List<RiskTemporaryDataDto> totalAssetList = riskMonitorIndicatorsMapper.queryTotalAssetPierce(pageParam); 
        // 查询开放式高流动性资产 
        List<RiskTemporaryDataDto> openHighMobilityAssetList = riskMonitorIndicatorsMapper.queryOpenHighMobilityAssetPierce(pageParam);
        // 查询-高流动性资产 
        List<RiskTemporaryDataDto> highMobilityAssetList = riskMonitorIndicatorsMapper.queryHighMobilityAssetPierce(pageParam);
        // 查询-单一最大资产集中度  
        List<RiskTemporaryDataDto> maxAssetsCDList = riskMonitorIndicatorsMapper.queryMaxAssetsCDPierce(pageParam);
        // 查询-单一最大融资人集中度 
        List<RiskTemporaryDataDto> largestFinancierCDList = riskMonitorIndicatorsMapper.queryLargestFinancierCDPierce(pageParam);
        // 查询-非公开债券集中度 
        List<RiskTemporaryDataDto> nonPublicBondCDList = riskMonitorIndicatorsMapper.queryNonPublicBondCDPierce(pageParam); 
        // 查询-单一关联方单笔交易占资本净额比例
        List<RiskTemporaryDataDto> singlejoinratioList = riskMonitorIndicatorsMapper.querySinglejoinratio(pageParam);
        // 查询-浮动盈亏金额 
        List<RiskTemporaryDataDto> tdyFloatIngplList = riskMonitorIndicatorsMapper.queryTdyFloatIngplPierce(pageParam); 
        Map<String,List<RiskTemporaryDataDto>> secDurationMap = secDurationList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> assetDurationMap = assetDurationList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> totalAssetMap = totalAssetList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> openHighMobilityAssetMap = openHighMobilityAssetList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> highMobilityAssetMap = highMobilityAssetList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> maxAssetsCDMap = maxAssetsCDList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> largestFinancierCDMap = largestFinancierCDList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> nonPublicBondCDMap = nonPublicBondCDList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> tdyFloatIngplMap = tdyFloatIngplList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        Map<String,List<RiskTemporaryDataDto>> singlejoinratioMap = singlejoinratioList.stream().collect(JoyinCollectors.groupingBy(item->item.getProdId()));
        for (String prodresultKey : result.keySet()){
            RiskTemporaryDataDto dto = result.get(prodresultKey); 
            //债券久期 
            if (StringUtil.isNotEmpty(secDurationMap)) {
                List<RiskTemporaryDataDto> list1 = secDurationMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list1)) {
                    dto.setSecDuration(list1.get(0).getSecDuration());
                } 
            }
            //产品久期 
            if (StringUtil.isNotEmpty(productDurationMap)) {
                BigDecimal productDuration = productDurationMap.get(prodresultKey);
                if (StringUtil.isNotEmpty(productDuration)) {
                    dto.setProductDuration(productDuration); 
                } 
            }
            //资产久期
            if (StringUtil.isNotEmpty(assetDurationMap)) { 
                List<RiskTemporaryDataDto> list1 = assetDurationMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list1)) {
                    dto.setAssetDuration(list1.get(0).getAssetDuration()); 
                    dto.setSsDuration(list1.get(0).getSsDuration()); 
                } 
            }
            //久期错配
            if (StringUtil.isNotEmpty(dto.getAssetDuration()) && StringUtil.isNotEmpty(dto.getProductDuration())){ 
                dto.setDurationmismatch(ArithmeticUtil.subtract(dto.getAssetDuration(), ArithmeticUtil.doAdd(dto.getProductDuration(),dto.getSsDuration()))); 
            }
            //期限错配
            if (StringUtil.isNotEmpty(dto.getAssetDuration()) && StringUtil.isNotEmpty(dto.getProductDuration())){ 
                dto.setTermmismatch(ArithmeticUtil.subtract(dto.getAssetDuration(), dto.getProductDuration())); 
            } 
            //杠杆率%
            //TODO
            if (StringUtil.isNotEmpty(totalAssetMap)) {
                List<RiskTemporaryDataDto> list1 = totalAssetMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list1)) {
                    dto.setNetAsset(list1.get(0).getNetAsset());
                    dto.setTotalAsset(list1.get(0).getTotalAsset());
                    dto.setLeverageratio(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getTotalAsset(), dto.getNetAsset(), 4),new BigDecimal(100))); 
                } 
            } 

            //开放式高流动性资产占比% 
            if (StringUtil.isNotEmpty(openHighMobilityAssetMap)) {
                List<RiskTemporaryDataDto> list2 = openHighMobilityAssetMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list2)){
                    dto.setOpenHighMobilityAsset(list2.get(0).getOpenHighMobilityAsset());
                    dto.setOpenHighMobilityAsset(ArithmeticUtil.multiply(dto.getOpenHighMobilityAsset(),new BigDecimal(100)));
                } 
            }  
            //高流动性资产占比%
            if (StringUtil.isNotEmpty(highMobilityAssetMap)) {
                List<RiskTemporaryDataDto> list5 = highMobilityAssetMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list5)){
                    dto.setHighMobilityAsset(list5.get(0).getHighMobilityAsset());
                    dto.setHighMobilityAsset(ArithmeticUtil.multiply(dto.getHighMobilityAsset(),new BigDecimal(100)));
                }
            }
            //单一最大认购客户集中度
            dto.setSingleCustomerConcentration(BigDecimal.ZERO);
            //单一最大资产集中度
            if (StringUtil.isNotEmpty(maxAssetsCDMap)) {
                List<RiskTemporaryDataDto> list7 = maxAssetsCDMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list7)){
                    dto.setMaxAssetsCD(list7.get(0).getMaxAssetsCD()); 
                    dto.setMaxAssetsCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getMaxAssetsCD(), dto.getNetAsset(), 4),new BigDecimal(100)));
                }
            }
            //单一最大融资人集中度
            if (StringUtil.isNotEmpty(largestFinancierCDMap)) {
                List<RiskTemporaryDataDto> list8 = largestFinancierCDMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list8)){
                    dto.setLargestFinancierCD(list8.get(0).getLargestFinancierCD());
                    dto.setLargestFinancierCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getLargestFinancierCD(), dto.getNetAsset(), 4),new BigDecimal(100)));
                }
            }
             
            //非公开债券集中度
            if (StringUtil.isNotEmpty(nonPublicBondCDMap)) {
                List<RiskTemporaryDataDto> list9 = nonPublicBondCDMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list9)){
                    dto.setNonPublicBondCD(list9.get(0).getNonPublicBondCD()); 
                    dto.setNonPublicBondCD(ArithmeticUtil.multiply(ArithmeticUtil.divide(dto.getNonPublicBondCD(), dto.getNetAsset(), 10),new BigDecimal(100)));
                }
            } 
          //单一关联方单笔交易占资本净额比例
            if (StringUtil.isNotEmpty(singlejoinratioMap)) {
                List<RiskTemporaryDataDto> list10 = singlejoinratioMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list10)){
                    dto.setSinglejoinratio(list10.get(0).getSinglejoinratio());                  
                }
            } 
            //浮动盈亏金额
            if (StringUtil.isNotEmpty(tdyFloatIngplMap)) {
                List<RiskTemporaryDataDto> list11 = tdyFloatIngplMap.get(prodresultKey);
                if (!StringUtil.isEmptyList(list11)){
                    dto.setTdyFloatIngpl(list11.get(0).getTdyFloatIngpl()); 
                }
            }
            result.put(prodresultKey, dto);
        }   
    }
    
    /**
     * 计算查询产品久期 
     *
     * @param param 参数map
     * @return 计算结果
     * @see
     * @since 1.0
     */ 
    @SuppressWarnings("all")
    public BigDecimal calculateProDurationSummary(Map<String, Object> param,List<String> prodIdList,Map<String, BigDecimal> productDurationMap){ 
        Map<String, Object> map = new HashMap<>();
        map.putAll(param);
        map.put("prodIds", prodIdList);
        List<RiskTemporaryDataDto> riskTemporaryDataList = riskMonitorIndicatorsMapper.queryProductDuration(map); 
        BigDecimal summaryData = BigDecimal.ZERO;
        BigDecimal proDuration = BigDecimal.ZERO; 
        for (RiskTemporaryDataDto dto : riskTemporaryDataList) {
            BigDecimal dailyData = BigDecimal.ZERO;
            BigDecimal amassData = BigDecimal.ZERO; 
            if ("02".equals(dto.getIsDuration())) {
                dailyData = ArithmeticUtil.doAdd(dailyData,ArithmeticUtil.multiply(dto.getProductDuration(),dto.getMatchamt()));  
                if (StringUtil.isNotEmpty(dto.getProductDuration())) {
                    amassData = ArithmeticUtil.doAdd(amassData,dto.getMatchamt()); 
                } 
            }else  if ("01".equals(dto.getIsDuration())) {
                if (dto.getHugeRedRatio().compareTo(BigDecimal.ZERO)==1 && dto.getSaleMin().compareTo(BigDecimal.ZERO)==1) {
                     BigDecimal matchamt = dto.getMatchamt(); 
                     BigDecimal dailyDataSs = BigDecimal.ZERO;
                     amassData = ArithmeticUtil.doAdd(amassData,dto.getMatchamt());
                     int days = 1 ;
                     while(true) {
                         dailyDataSs = ArithmeticUtil.multiply(matchamt,ArithmeticUtil.subtract(BigDecimal.ONE, dto.getHugeRedRatio()));
                         if (dto.getSaleMin().compareTo(dailyDataSs) != -1) {
                             dailyData = ArithmeticUtil.doAdd(dailyData,ArithmeticUtil.multiply(
                                 ArithmeticUtil.divide(new BigDecimal(days), new BigDecimal(365), 14),dto.getMatchamt()));  
                             break;
                         }
                         matchamt = dailyDataSs;
                         days++;
                    }
                }
            }   
            summaryData = ArithmeticUtil.doAdd(summaryData,amassData);  
            proDuration = ArithmeticUtil.doAdd(proDuration,ArithmeticUtil.multiply(amassData,ArithmeticUtil.divide(dailyData, amassData, 16))); 
            productDurationMap.put(dto.getProdId(), ArithmeticUtil.divide(dailyData, amassData, 16));
        }
        return ArithmeticUtil.divide(proDuration,summaryData,16); 
    }
    
    
    /**
     * 计算查询产品久期 
     *
     * @param param 参数map
     * @return 计算结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("all")
    public Map<String, BigDecimal> calculateProDuration(Map<String, Object> param,List<String> prodIdList){
        Map<String, Object> map = new HashMap<>();
        map.putAll(param);
        map.put("prodIds", prodIdList);
        Map<String,BigDecimal>  result =new HashMap<String, BigDecimal>();  
        List<RiskTemporaryDataDto> riskTemporaryDataList = riskMonitorIndicatorsMapper.queryProductDuration(map); 
        for (RiskTemporaryDataDto dto : riskTemporaryDataList) {
            BigDecimal dailyData = BigDecimal.ZERO;
            BigDecimal amassData = BigDecimal.ZERO; 
            if ("02".equals(dto.getIsDuration())) {
                dailyData = ArithmeticUtil.doAdd(dailyData,ArithmeticUtil.multiply(dto.getProductDuration(),dto.getMatchamt()));  
                if (StringUtil.isNotEmpty(dto.getProductDuration())) {
                    amassData = ArithmeticUtil.doAdd(amassData,dto.getMatchamt()); 
                } 
            }else  if ("01".equals(dto.getIsDuration())) {
                if (dto.getHugeRedRatio().compareTo(BigDecimal.ZERO)==1 && dto.getSaleMin().compareTo(BigDecimal.ZERO)==1) {
                     BigDecimal matchamt = dto.getMatchamt(); 
                     BigDecimal dailyDataSs = BigDecimal.ZERO;
                     amassData = ArithmeticUtil.doAdd(amassData,dto.getMatchamt());
                     int days = 1 ;
                     while(true) {
                         dailyDataSs = ArithmeticUtil.multiply(matchamt,ArithmeticUtil.subtract(BigDecimal.ONE, dto.getHugeRedRatio()));
                         if (StringUtil.isNotEmpty(dailyDataSs) && dto.getSaleMin().compareTo(dailyDataSs) != -1) { 
                             dailyData = ArithmeticUtil.doAdd(dailyData,ArithmeticUtil.multiply(
                                 ArithmeticUtil.divide(new BigDecimal(days), new BigDecimal(365), 14),dto.getMatchamt()));  
                             break;
                         }
                         matchamt = dailyDataSs;
                         days++;
                    }
                }
            }  
            result.put(dto.getProdId(), ArithmeticUtil.divide(dailyData, amassData, 16)); 
        }
        return result; 
    }
    
    /**
     * 产品代码联想下拉框
     *
     * @param param 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param){
        return riskMonitorIndicatorsMapper.queryProdIdInfo(param);
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
        return riskMonitorIndicatorsMapper.queryManagerInfo(param);
    }
 
}
