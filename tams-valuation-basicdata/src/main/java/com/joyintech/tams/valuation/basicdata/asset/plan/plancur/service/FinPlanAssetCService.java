/*
 * 文件名：FinPlanAssetCService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.plan.plancur.service;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.plan.plancur.dto.FinPlanAssetCDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年1月7日
 * @see FinPlanAssetCService
 * @since 1.0
 */
@Service
public class FinPlanAssetCService extends CommonExportService<FinPlanAssetCDto>
    implements BaseProcessService<FinPlanAssetCDto>{
    @Autowired(required=false)
    private  FinPlanAssetCService finPlanAssetCService;
    /**
     * 资产管理计划中债信息组件
     */
//    @Autowired(required=false)
//    private BussRepAssetMngProdInfoService bussRepAssetMngProdInfoService;
    /**
     * 共通--资产
     */
    //@Autowired(required=false)
    //private AssetCommonService assetCommonService;
    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    /**
     * 投管接口共通组件
     */
//    @Autowired
//    private InvestInterfCommService investInterfCommService;
    
    @Override
    public void doInsert(FinPlanAssetCDto entity, OperationType type) {
        updateFiles(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinPlanAssetCDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        entity.setBranch(Long.valueOf(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE)));
        FinProduct finProduct = new FinProduct();

        //金融产品代码（估值核算）
        finProduct.setFinprodAbbr(finProduct.getFinprodName());
        finProduct.setFinprodType2(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        entity.setFinprodType2(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        BeanUtil.copyPropertiesIgnoreNull(finProduct,entity);
//        finProduct.setProfitType(DictCodeList.收益类型_净值型.getValue());
//        finProduct.setTermDays((dto.getMdate().getTime()-dto.getVdate().getTime())/1000/60/60/24);
        if (entity.getMdate() != null) {
            finProduct.setTermDays((entity.getMdate().getTime()-entity.getVdate().getTime())/1000/60/60/24);
        }
//        finProduct.setBlnArea(DictCodeList.境内外_境内.getValue());
//        entity.setBussRepAssetMngProdInfoDto(bussRepAssetMngProdInfoService.
//            getBussRepAssetMngProdInfoDto(entity.getBussRepAssetMngProdInfo()));
       /* update by zhuyongkang 20191009 start*/
//        entity.setBussParticipantInfoDto(assetCommonService.enBussParticipantInfoDto(finProduct,
//            entity.getBussParticipantInfoDto(),entity.getBranch()));
        /* update by zhuyongkang 20191009 end*/
        // entity.setSystemCode("SYS");
//        //add by dongzhiling start at 2019-02-22 :生成委外组合
//        PtlPortfolio ptlPortfolio = new PtlPortfolio();
//        ptlPortfolio.setPortfolioId(finProduct.getFinprodMarketId());
//        ptlPortfolio.setPortfolioType(DictCodeListExt1.组合类型_委外组合.getValue());
//        ptlPortfolio.setPortfolioName(finProduct.getFinprodName());
//        ptlPortfolio.setVdate(finProduct.getVdate());
//        ptlPortfolio.setMdate(finProduct.getMdate());
//        ptlPortfolio.setProfitType(DictCodeListExt2.收益类型_货币.getValue());
//        entity.setPtlPortfolio(ptlPortfolio);
        //add end
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinPlanAssetC/" + action, entity,
            Integer.class);

        // 推送数据到第三方接口

        //1.计划类要素信息

//        Map<String, Object> invMap =  JsonUtils.object2Map(entity);
//        invMap.put("pageId","FinPlanTargetAsset002S017");
//        investInterfCommService.doInvestInterf(invMap);

        //2.利率信息

//        invMap.put("pageId","FinPlanTargetAsset002S018");
//        investInterfCommService.doInvestInterf(invMap);

    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<FinPlanAssetCDto> selectForPage(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinPlanAssetC/findPage",
            map, FinPlanAssetCDto.class);
    }

    public List<FinPlanAssetCDto> selectForList(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinPlanAssetC/selectForList",
                map, FinPlanAssetCDto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        String isShowType = (String) exportParam.getSelectParam().get("isShowType");
		if (StringUtil.equals(isShowType, "01")) {
			exportParam.config("/com/joyintech/tams/valuation/basicdata/asset/plan/plancur/conf/export_conf.xml")
					.dataSource(DataSourceType.MASERT);
		} else {
			exportParam.config("/com/joyintech/tams/valuation/basicdata/asset/plan/plancur/conf/export_conf002.xml")
					.dataSource(DataSourceType.MASERT);
		}
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinPlanAssetCDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getIssuerId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
            + "/FinPlanAssetC/getIssuer", map,
            SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getManagerId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
            + "/FinPlanAssetC/getManager", map,
            SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getTrusteeId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
            + "/FinPlanAssetC/getTruster", map,
            SelectOptionDto.class);
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
    public String getFinprodId(String finprodMarketId){
      return  restTemplate.getForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinPlanAssetC/getFinprodId?finprodMarketId="+finprodMarketId,String.class
        );
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 end*/
    /*增加资产代码重复校验 add by zhuyongkang 20191021 start*/
    /**
     * 查询市场代码
     *
     * @param finprodMarketId 市场代码
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Integer selectFinprodMarketId(String finprodMarketId) {
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinPlanAssetC/selectFinprodMarketId?finprodMarketId="
                        + finprodMarketId,
                Integer.class);
    }
    /*增加资产代码重复校验 add by zhuyongkang 20191021 end*/
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinPlanAssetCDto getBasicInfoByFinprodId(String finprodId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinPlanAssetC/getBasicInfoByFinprodId", param,
            FinPlanAssetCDto.class);
    }
    private void updateFiles(FinPlanAssetCDto entity) {
        //List<SysUploadFileInfo> files = entity.getFileArray();
//        if(files.size()>0) {
//            List<String> fileNo = new ArrayList<>();
//            files.forEach(sysUploadFileInfo -> {
//                fileNo.add(sysUploadFileInfo.getSeqNo());
//            });
//            fileAttachmentProcess.updateBussinessNo(fileNo, entity.getFjId());
//        }
    }

    @Override
    public void doUpdate(FinPlanAssetCDto entity) {
        //updateFiles(entity);
    }

    @Override
    public void doDelete(String workflowId) {
        FinPlanAssetCDto finPlanProfitAssetDto = finPlanAssetCService.getBussinessEntityByWorkflowId(workflowId);
        List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(finPlanProfitAssetDto.getFjId());
        if(files.size()>0) {
            files.forEach(uploadFile -> {
                fileAttachmentProcess.remove(uploadFile.getFileNo());
            });
        }
    }
    /**
     * 获取所有资产id
     * <功能详细描述>
     * @param search
     * @param finprodType2
     * @param profitType
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetId(String search, String finprodType2, String profitType){
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinPlanAssetC/getAssetId?search="
                        + search+"&finprodType2="+finprodType2+"&profitType="+profitType+"&child="+ RequestUtil.getParam("child"),
                SuggestDto.class);
    }

    public boolean isDealCalendarHoliday(Date date, String calId) {
        if (date == null || StringUtil.isEmpty(calId)) {
            return false;
        }
        String dateStr = DateUtil.format(date);
        return this.isDealCalendarHoliday(dateStr, calId);
    }
    /**
     * 判断日期参数是否交易日历的节假日
     *
     * @param dateStr 参数日期
     * @param calId 交易日历id
     * @return 是否交易日历的节假日判断结果
     */
    public boolean isDealCalendarHoliday(String dateStr, String calId) {
        if (StringUtil.isEmpty(dateStr) || StringUtil.isEmpty(calId)) {
            return false;
        }

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dateStr", dateStr);
        param.put("calId", calId);
        Integer dataCount =  restTemplate.postForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinPlanAssetC/isDealCalendarHoliday", param,
                Integer.class);
        if (dataCount > 0) {
            return true;
        }
        return false;
    }
}
