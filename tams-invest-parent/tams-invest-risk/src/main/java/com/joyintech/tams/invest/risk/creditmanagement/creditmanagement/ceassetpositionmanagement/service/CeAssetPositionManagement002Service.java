/*
 * 文件名：CePreOccupancyManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
//import com.joyintech.component.business.common.service.CeLinesUpdates;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.dto.CeAssetPositionManagement001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月8日
 * @see CeAssetPositionManagement002Service
 * @since 1.0
 */
@Service
public class CeAssetPositionManagement002Service extends CommonExportService<CeAssetPositionManagement001Dto>{
	
	@Autowired
	private CeLinesUpdate ceLinesUpdate;
	
	/**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/creditmanagement/ceassetpositionmanagement/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 导出模板
     */
    @Override
    public Page<CeAssetPositionManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        Page<CeAssetPositionManagement001Dto> page = new Page<>();
        List<CeAssetPositionManagement001Dto> list = new ArrayList<>();
        page.setRoot(list);
        return page;
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param fileName
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> importData(String fileName) throws Exception {
        //记录错误信息
        List<ExcelValidDto> errorMessage = new ArrayList<ExcelValidDto>();
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(fileName)) {
            ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName),
                null);
            List<List<String>> dataList = excelUtil.readToList(0, 0, 0);
            //记录正确数据
            List<List<String>> correctList = new ArrayList<List<String>>();
            //记录错误数据
            List<List<String>> errortList = new ArrayList<List<String>>();
            //检查读出信息是否为空
            if (null != dataList) {
                for(int i=2; i<dataList.size(); i++){
                    String errorStr = "";
                    //检验该资产类型是否存在
                    if(dataList.get(i).size()>2){
                        if(this.check0(dataList.get(i).get(0),dataList.get(i).get(1)) > 0) {
                            errorStr = getMessage("RSK_W_0048");
                        }
                    }
                    //检验资产代码是否已存在
//                    if (this.check1(dataList.get(i).get(1)) != 1) {
//                        errorStr = errorStr+";"+getMessage("RSK_W_0037", dataList.get(i).get(1) + ",");
//                    }
                    if (StringUtil.isEmpty(errorStr)) {
                        if ("Y".equals(CacheUtil.getSysParam(BussinessConstant.ISCREDIT))) {
                            TrdProductDeal trdProductDeal = new TrdProductDeal();
                            // 交易方向只有买入
                            trdProductDeal.setPs("T01");
                            // 通过市场代码查询资产代码
                            String assetId = this.getAssetIdByMarketId(dataList.get(i).get(1));
                            // 金融产品代码,资产代码
                            trdProductDeal.setFinprodId(assetId);
                            // 交易日
                            trdProductDeal.setTradeDate(DateUtil.parse(dataList.get(0).get(3)));
                            // 本金金额
                            BigDecimal bd = new BigDecimal(dataList.get(i).get(2));
                            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                            trdProductDeal.setPrinAmt(bd);
                            trdProductDeal.setShareAmt(bd.divide(BigDecimal.valueOf(100)));
                            // 有效状态,初始
                            trdProductDeal.setTradeStatus("01");
                            AllTradeCommonInputDto inputDto =new AllTradeCommonInputDto();
                            inputDto.setTrdProductDeal(trdProductDeal);
                            List<CreditLinesDealCommon> creditlist = ceLinesUpdate.isNeedOccupyCre(
                            		inputDto, false, false, false);
                            if (!StringUtil.isEmptyList(creditlist)
                                && creditlist.get(0).getErrMessage() != null) {
                                errorStr = creditlist.get(0).getErrMessage();
                                errorMessage.add(
                                    new ExcelValidDto(dataList.get(i).get(1), i + "", errorStr));
                            }
                        }
                    }
                    if(StringUtil.isNotEmpty(errorStr)){
                        errorMessage.add(new ExcelValidDto(dataList.get(i).get(1),i+"",errorStr));
//                        excelValidDto.setErrorInfo(errorStr);
//                        excelValidDto.setRowNum(i+"");
//                        excelValidDto.setValidKey(dataList.get(i).get(0));
                        errortList.add(dataList.get(i));
                    }
                    else{
                        if(dataList.get(i).size()>2){
                            dataList.get(i).add(3, i + "");
                            correctList.add(dataList.get(i));
                        }
                    }
                }
            }
            //数据入库
//            List<CeAssetPositionManagement001Dto> listall = new ArrayList<CeAssetPositionManagement001Dto>();
            if (correctList.size() >0) {
                List<CeAssetPositionManagement001Dto> listall = new ArrayList<CeAssetPositionManagement001Dto>();
//                CeAssetPositionManagement001Dto ceAssetPositionManagement001Dto = new CeAssetPositionManagement001Dto();
                
                //释放上次占用额度按照组合代码删除，无需循环
                if("Y".equals(CacheUtil.getSysParam(BussinessConstant.ISCREDIT))){
                	//更新占用授信额度
                	this.updateLinesByPtlId(dataList.get(0).get(1));
                	//删除上次组合
                	this.deleteByPtlid(dataList.get(0).get(1),DateUtil.parse(dataList.get(0).get(3)));
                }
            	for (List<String> correct : correctList) {
                	TrdProductDeal trdProductDeal=new TrdProductDeal();
                    //通过市场代码查询资产代码
                    String assetId = this.getAssetIdByMarketId(correct.get(1));
                    
                    //交易方向只有买入
            		trdProductDeal.setPs("T01");
            		//金融产品代码,资产代码
            		trdProductDeal.setFinprodId(assetId);
            		//交易日
            		trdProductDeal.setTradeDate(DateUtil.parse(dataList.get(0).get(3)));
            		//本金金额
            		BigDecimal bd=new BigDecimal(correct.get(2));
            		bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP); 
            		trdProductDeal.setPrinAmt(bd);
            		trdProductDeal.setShareAmt(bd.divide(BigDecimal.valueOf(100)));
            		//有效状态,初始
            		trdProductDeal.setTradeStatus("01");
            		AllTradeCommonInputDto inputDto =new AllTradeCommonInputDto();
                    inputDto.setTrdProductDeal(trdProductDeal);
                    //只验证授信额度是否足够
                    List<CreditLinesDealCommon> creditlist11=ceLinesUpdate.isNeedOccupyCre(inputDto,false,true,false);
                    if (StringUtil.isEmptyList(creditlist11)) {
                        errorMessage.add(new ExcelValidDto(correct.get(1), correct.get(3), "授信额度不足，该数据不导入"));
                    }
                    else {
                        for(CreditLinesDealCommon creditLinesDealcommon:creditlist11){
                            CeAssetPositionManagement001Dto ceAssetPositionManagement001Dto = new CeAssetPositionManagement001Dto();
                            ceAssetPositionManagement001Dto.setFinprodId(assetId);
                            ceAssetPositionManagement001Dto.setValDate(DateUtil.parse(dataList.get(0).get(3)));
                            ceAssetPositionManagement001Dto.setPtlId(dataList.get(0).get(1));
                            //因以下字段在数据库中为必填项，临时设值 
                            ceAssetPositionManagement001Dto.setSeqno(creditLinesDealcommon.getSeqno());
                            ceAssetPositionManagement001Dto.setCreditOccureType("01");
                            ceAssetPositionManagement001Dto.setTradeAmt(bd);
                            ceAssetPositionManagement001Dto.setAssetPosition(new BigDecimal(correct.get(2)));
                            listall.add(ceAssetPositionManagement001Dto);
                        }
                    }
                }
            	if (listall.size() >0) {
            		this.updateCeAssetPositionManagement(listall);
            	}
            }
        }
        return errorMessage;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param string
     * @param parse
     * @see
     * @since 1.0
     */
    private int deleteByPtlid(String ptlid, Date valdate) {
        Map<String, Object> map = new HashMap<>();
        map.put("ptlid", ptlid);
        map.put("valdate", DateUtil.format(valdate));
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetPositionManagement/deleteByPtlid",
            map, Integer.class);
        
    }
    
    /**
     * 更新占用授信额度
     * <功能详细描述>
     * @param string
     * @param parse
     * @see
     * @since 1.0
     */
    private int updateLinesByPtlId(String ptlid) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetPositionManagement/updateLinesByPtlId",
            ptlid, Integer.class);
        
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param string
     * @return
     * @see
     * @since 1.0
     */
    private String getAssetIdByMarketId(String marketId) {
        return restTemplate.getForObject(ServiceApplication.RISK_SERVICE_NAME
                                         + "/CeAssetPositionManagement/getAssetIdByMarketId?marketId="
                                         + marketId,
            String.class);
    }

    /**
     * <一句话功能简述>
     * 校验资产类型是否匹配
     * @param string
     * @return
     * @see
     * @since 1.0
     */
    public int check0(String assetType, String assetCode) {
        return restTemplate.getForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetPositionManagement/check0?assetType="
                                         + assetType + "&assetCode=" + assetCode,
            Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param list
     * @see
     * @since 1.0
     */
    public int updateCeAssetPositionManagement(List<CeAssetPositionManagement001Dto> list) {
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME
                                          + "/CeAssetPositionManagement/updateCeAssetPositionManagement",
            list, Integer.class);
    }

    /**
     * <一句话功能简述> 校验资产代码是否已存在
     * 
     * @param assetCode 资产代码
     * @return 校验结果
     * @see
     * @since 1.0
     */
    public int check1(String assetCode) {
        return restTemplate.getForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetPositionManagement/check1?assetCode="
                                         + assetCode,
            Integer.class);
    }

    /**
     * <一句话功能简述> 委外组合代码联想下拉框 
     * 
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryGroupIdInfo(Map<String, Object> parameters) {
        parameters.put("loginUserName", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetPositionManagement/queryGroupIdInfo",
            parameters, SelectOptionDto.class);
    }
    
    

}
