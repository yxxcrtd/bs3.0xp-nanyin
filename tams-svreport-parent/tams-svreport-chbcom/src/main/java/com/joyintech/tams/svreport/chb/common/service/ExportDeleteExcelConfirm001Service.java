/*
 * 文件名：EquityInfoRegist002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：导出删除模板共通页面
 * 修改人：Administrator
 * 修改时间：2019年5月30日
 * 修改内容：新建
 * 系统名称：中债报送监管系统
 */
 
package com.joyintech.tams.svreport.chb.common.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chb.assetinforeg.dao.AssetInfoRegist001Mapper;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.service.AssetInfoRegist001Service;
import com.joyintech.tams.svreport.chb.astvalueregist.dao.ChbAstValueRegist001Mapper;
import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;
import com.joyintech.tams.svreport.chb.astvalueregist.service.ChbAstValueRegist001Service;
import com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper;
import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;
import com.joyintech.tams.svreport.chb.botastpositionregist.service.ChbBotAstPositionRegist001Service;
import com.joyintech.tams.svreport.chb.common.dto.ChbDeleteExcelExportDetailDto;
import com.joyintech.tams.svreport.chb.common.dto.ChbDeleteExcelExportDto;
import com.joyintech.tams.svreport.chb.investtradereg.dao.InvestTradeRegist001Mapper;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;
import com.joyintech.tams.svreport.chb.investtradereg.service.InvestTradeRegist001Service;
import com.joyintech.tams.svreport.chb.proddurationreg.dao.ProdDurationReg001Mapper;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.svreport.chb.proddurationreg.service.ProdDurationReg001Service;


/**
 * 导出删除模板共通逻辑
 * @author 魏玉强
 * @version 1.7.0 2019年5月30日
 * @see ExportDeleteExcelConfirm001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExportDeleteExcelConfirm001Service extends CommonExportService<ChbDeleteExcelExportDto> {
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AssetInfoRegist001Mapper assetInfoRegist001Mapper;
    
    @Autowired
    private InvestTradeRegist001Mapper investTradeRegist001Mapper;
    
    @Autowired
    private AssetInfoRegist001Service assetInfoRegist001Service;
    
    @Autowired
    private InvestTradeRegist001Service investTradeRegist001Service;

    @Autowired
	private ChbAstValueRegist001Mapper chbAstValueRegist001Mapper;
    
    @Autowired
	private ChbBotAstPositionRegist001Mapper chbBotAstPositionRegist001Mapper;
    
    @Autowired
    private ChbBotAstPositionRegist001Service chbBotAstPositionRegist001Service;
    
    @Autowired
    private ChbAstValueRegist001Service chbAstValueRegist001Service;
    @Autowired
    private ProdDurationReg001Mapper prodDurationReg001Mapper;
    @Autowired
    private ProdDurationReg001Service prodDurationReg001Service;
    
    /**
     * 功能:处理查询前的参数
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @param pageId 查询的原页面id
     * @param pageParam 查询参数
     * @return 处理后的参数
     * void
     */
    public Map<String, Object> dealSearchParam(String pageId,Map<String, Object> pageParam) {
        Map<String, Object>  resultParam = new HashMap<String,Object>();
        // 删除原因
        String deleteReason = (String) pageParam.get("remark");
        // 选择行数据导出时
        if ("exportSelect".equals(pageParam.get("export_method_flg"))) {
            String keyList = pageParam.get("expKeys").toString();
            try {
                // 这里根据配置的pageid设置相应的属性
                // TODO
                if ("SvrAssetInfoRegist001".equals(pageId)){
                    List<AssetInfoRegist001Dto> assetInfoRegList = JsonUtils.json2List(keyList,
                            AssetInfoRegist001Dto.class);
                    // 将资产要素转换成导出的删除信息
                    ChbDeleteExcelExportDto deleteInfo = new ChbDeleteExcelExportDto();
                    convertAssetInfoToDeletList(deleteInfo,assetInfoRegList,deleteReason);
                    resultParam.put("deleteInfo", deleteInfo);
                } else  if ("SvrInvestTradeRegist001".equals(pageId)){
                    List<InvestTradeRegist001Dto> investTradeRegList = JsonUtils.json2List(keyList,
                            InvestTradeRegist001Dto.class);
                    // 将资产要素转换成导出的删除信息
                    ChbDeleteExcelExportDto deleteInfo = new ChbDeleteExcelExportDto();
                    convertInvestTradeToDeletList(deleteInfo,investTradeRegList,deleteReason, false);
                    resultParam.put("deleteInfo", deleteInfo);
                } else  if ("SvrChbAstValueRegist001".equals(pageId)){
                    List<ChbAstValueRegist001Dto> regList = JsonUtils.json2List(keyList,
                    		ChbAstValueRegist001Dto.class);
                    // 将资产估值转换成导出的删除信息
                    ChbDeleteExcelExportDto deleteInfo = new ChbDeleteExcelExportDto();
                    convertChbAstValueToDeletList(deleteInfo,regList,deleteReason);
                    resultParam.put("deleteInfo", deleteInfo);
                } else  if ("SvrChbBotAstPositionRegist001".equals(pageId)){
                    List<ChbBotAstPositionRegist001Dto> regList = JsonUtils.json2List(keyList,
                    		ChbBotAstPositionRegist001Dto.class);
                    // 将底层持仓转换成导出的删除信息
                    ChbDeleteExcelExportDto deleteInfo = new ChbDeleteExcelExportDto();
                    convertChbBotAstPositionToDeletList(deleteInfo,regList,deleteReason);
                    resultParam.put("deleteInfo", deleteInfo);
                } else  if ("SvrProdDurationReg001".equals(pageId)){
                    List<ProdDurationReg001Dto> regList = JsonUtils.json2List(keyList,
                            ProdDurationReg001Dto.class);
                    // 将底层持仓转换成导出的删除信息
                    ChbDeleteExcelExportDto deleteInfo = new ChbDeleteExcelExportDto();
                    convertChbProdDurationToDeletList(deleteInfo,regList,deleteReason);
                    resultParam.put("deleteInfo", deleteInfo);
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        } else {// 没有选中行数据导出时导出删除未报送的数据
            // 查询参数条件
            // 数据状态
            String status = DictCodeListExt2.报表数据状态_删除.getValue();
            // 报送状态
            String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();
            resultParam.put("status", status);
            resultParam.put("sendStatus", sendStatus);
        }
        return resultParam;
    }
    
    /**
     * 功能:根据底层持仓信息生成要导出的模板信息
     * @since 1.7.0
     * @version 1.7.0
     * @param deleteInfo 删除信息
     * @param assetInfoRegList 资产要素登记信息
     * @param deleteReason 删除原因
     */
    private void convertChbBotAstPositionToDeletList(ChbDeleteExcelExportDto deleteInfo,
			List<ChbBotAstPositionRegist001Dto> regList, String deleteReason) {
    	List<ChbDeleteExcelExportDetailDto> deleteDetailList = new ArrayList<>();
    	regList.forEach(item -> {
            ChbDeleteExcelExportDetailDto detail = new ChbDeleteExcelExportDetailDto();
            // 第十列：对应资管及委外资产行内资产/负债编码
            detail.setCol10((String)item.getPlanAssetId());
            // 第十一列：行内资产负债编码
            detail.setCol11((String)item.getAssetCode());
            // 第十二列：持仓日期
            detail.setCol12(DateUtil.format(item.getPosDate()));
            // 第十三列：删除原因
            detail.setCol13(deleteReason);
            // 技术主键:登记序号
            detail.setSeqno(item.getRegistSeqno());
            deleteDetailList.add(detail);
        });
        deleteInfo.setDetaiList(deleteDetailList);
        if (CollectionUtils.isNotEmpty(regList)){
            if (StringUtil.isNotEmpty(regList.get(0).getIssuePartyId())){
                // 发行机构代码
                deleteInfo.setIssuePartyId(regList.get(0).getIssuePartyId());
            }
        }
        // 删除类型
        deleteInfo.setDeletType(DictCodeListExt2.删除类型_底层资产信息.getValue()+" "+DictCodeListExt2.删除类型_底层资产信息.getCodeName());
	}

    

    /**
     * 功能:根据资产估值信息生成要导出的模板信息
     * @since 1.7.0
     * @version 1.7.0
     * @param deleteInfo 删除信息
     * @param assetInfoRegList 资产要素登记信息
     * @param deleteReason 删除原因
     */
	private void convertChbAstValueToDeletList(ChbDeleteExcelExportDto deleteInfo,
			List<ChbAstValueRegist001Dto> regList, String deleteReason) {
		List<ChbDeleteExcelExportDetailDto> deleteDetailList = new ArrayList<>();
    	regList.forEach(item -> {
            ChbDeleteExcelExportDetailDto detail = new ChbDeleteExcelExportDetailDto();
            // 第八列：行内资产负债编码
            detail.setCol8(item.getAssetCode());
            // 第九列：估值日期
            detail.setCol9(DateUtil.format(item.getValDate()));
            // 第十三列：删除原因
            detail.setCol13(deleteReason);
            // 技术主键:登记序号
            detail.setSeqno(item.getRegistSeqno());
            deleteDetailList.add(detail);
        });
        deleteInfo.setDetaiList(deleteDetailList);
        if (CollectionUtils.isNotEmpty(regList)){
            if (StringUtil.isNotEmpty(regList.get(0).getIssuePartyId())){
                // 发行机构代码
                deleteInfo.setIssuePartyId(regList.get(0).getIssuePartyId());
            }
        }
        // 删除类型
        deleteInfo.setDeletType(DictCodeListExt2.删除类型_资产估值信息.getValue()+" "+DictCodeListExt2.删除类型_资产估值信息.getCodeName());
	}

	/**
     * 功能:根据资产要素信息生成要导出的模板信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @param deleteInfo 删除信息
     * @param assetInfoRegList 资产要素登记信息
     * @param deleteReason 删除原因
     * void
     */
    private void convertAssetInfoToDeletList(ChbDeleteExcelExportDto deleteInfo,
            List<AssetInfoRegist001Dto> assetInfoRegList,String deleteReason) {
        List<ChbDeleteExcelExportDetailDto> deleteDetailList = new ArrayList<>();
        assetInfoRegList.forEach(item -> {
            ChbDeleteExcelExportDetailDto detail = new ChbDeleteExcelExportDetailDto();
            // 第三列：行内资产负债编码
            detail.setCol3(item.getAssetCode());
            // 第十三列：删除原因
            detail.setCol13(deleteReason);
            // 技术主键:登记序号
            detail.setSeqno(item.getRegistSeqno());
            deleteDetailList.add(detail);
        });
        deleteInfo.setDetaiList(deleteDetailList);
        if (CollectionUtils.isNotEmpty(assetInfoRegList)){
            if (StringUtil.isNotEmpty(assetInfoRegList.get(0).getIssuePartyId())){
                // 发行机构代码
                deleteInfo.setIssuePartyId(assetInfoRegList.get(0).getIssuePartyId());
            }
        }
        // 删除类型
        deleteInfo.setDeletType(DictCodeListExt2.删除类型_资产要素明细信息.getValue()+" "+DictCodeListExt2.删除类型_资产要素明细信息.getCodeName());
    }

    
    /**
     * 功能:根据交易信息生成要导出的模板信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @param deleteInfo 删除信息
     * @param assetInfoRegList 资产要素登记信息
     * @param deleteReason 删除原因
     * @param isSearch 是否来自画面查询
     * void
     */
    private void convertInvestTradeToDeletList(ChbDeleteExcelExportDto deleteInfo,
            List<InvestTradeRegist001Dto> investTradeRegList,String deleteReason, boolean isSearch) {
        List<ChbDeleteExcelExportDetailDto> deleteDetailList = new ArrayList<>();
        investTradeRegList.forEach(item -> {
            ChbDeleteExcelExportDetailDto detail = new ChbDeleteExcelExportDetailDto();
            // 第四列：行产品登记编码
            detail.setCol4(item.getProdRegCode());
            // add by zhangliang 20201101 补充产品申报登记编码 start
            if (StringUtil.isEmpty(detail.getCol4())){
                detail.setCol4(item.getProdRegistCode());
            }
            // add by zhangliang 20201101 补充产品申报登记编码 end
            // 第五列：行内交易编码
            detail.setCol5(item.getTradeCode());
            // 第六列：资金流动类型
            if (StringUtils.isNotEmpty(item.getCurAssetType())){
                detail.setCol6(item.getCurAssetType());
                if (isSearch){
                    detail.setCol6(SvrDataUtil.getZZDictCodeAndName(DictCode.资金流动类型,item.getCurAssetType()));
                }
                //detail.setCol6(item.getCurAssetType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资金流动类型,item.getCurAssetType()));
            }
            // 第七列：行内资产/负债编码
            detail.setCol7(item.getAssetCode());
            // 技术主键:登记序号
            detail.setSeqno(item.getRegistSeqno());
         // 第十三列：删除原因
            detail.setCol13(deleteReason);
            deleteDetailList.add(detail);
        });
        deleteInfo.setDetaiList(deleteDetailList);
        if (CollectionUtils.isNotEmpty(investTradeRegList)){
            if (StringUtil.isNotEmpty(investTradeRegList.get(0).getRegistBankCode())){
                // 发行机构代码
                deleteInfo.setIssuePartyId(investTradeRegList.get(0).getRegistBankCode());
            }
        }
        // 删除类型
        deleteInfo.setDeletType(DictCodeListExt2.删除类型_交易信息.getValue()+" "+DictCodeListExt2.删除类型_交易信息.getCodeName());
    }
    /**
     * 功能:根据交易信息生成要导出的模板信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @param deleteInfo 删除信息
     * @param assetInfoRegList 资产要素登记信息
     * @param deleteReason 删除原因
     * void
     */
    private void convertChbProdDurationToDeletList(ChbDeleteExcelExportDto deleteInfo,
    		List<ProdDurationReg001Dto> investTradeRegList,String deleteReason) {
        List<ChbDeleteExcelExportDetailDto> deleteDetailList = new ArrayList<>();
        investTradeRegList.forEach(item -> {
            ChbDeleteExcelExportDetailDto detail = new ChbDeleteExcelExportDetailDto();
            // 第一列：产品登记编码
            detail.setCol1(item.getProdRegistCode());
            // 第二列：业务结束日
            detail.setCol2(item.getMdate());
            // 第十三列：删除原因
            detail.setCol13(deleteReason);
            // 技术主键:登记序号
            detail.setSeqno(item.getRegistSeqno());
            deleteDetailList.add(detail);
        });
        deleteInfo.setDetaiList(deleteDetailList);
        if (CollectionUtils.isNotEmpty(investTradeRegList)){
            if (StringUtil.isNotEmpty(investTradeRegList.get(0).getIssuePartyId())){
                // 发行机构代码
                deleteInfo.setIssuePartyId(investTradeRegList.get(0).getIssuePartyId());
            }
        }
        // 删除类型
        deleteInfo.setDeletType(DictCodeListExt2.删除类型_产品存续期信息.getValue()+" "+DictCodeListExt2.删除类型_产品存续期信息.getCodeName());
    }
    /**
     * 功能:导出excel
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @param pageId 查询的原页面id
     * @param param 查询参数
     * @return 
     * @see com.joyintech.framework.export.CommonExportService#export(java.lang.String, java.util.Map)
     */
    @Override
    public ResponseEntity<byte[]> export(String pageId, Map<String, Object> param) {
        // 处理查询参数
        Map<String, Object> selectParam = dealSearchParam(pageId,param);
        // 删除原因
        String deleteReason = (String) param.get("remark");
        // 删除信息
        ChbDeleteExcelExportDto dataInfo =  (ChbDeleteExcelExportDto) selectParam.get("deleteInfo");
        // 不选数据导出全部（根据查询条件查询）
        if (dataInfo == null){
            // TODO：各自实现自己的导出方法
            dataInfo = searchByCondition(pageId,selectParam,deleteReason);
        }
        Map<String, Object> dataParam = new HashMap<String, Object>();
        // 删除信息为空则直接返回空数据
        if (dataInfo != null){
            // 发行机构
            dataParam.put("issuePartyId", dataInfo.getIssuePartyId());
            // 删除类型
            dataParam.put("deleteType", dataInfo.getDeletType());
            // 明细数据
            if (CollectionUtils.isNotEmpty(dataInfo.getDetaiList())){
                dataParam.put("detailList", dataInfo.getDetaiList());
            }
        }
        // 导出文件
        File outFile = TisUtil.createFile(dataParam, "ChbDeletInfoReport", pageId);
        // 导出文件名
        String exportFileName = pageId;
        com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(pageId);
        if(null != page) {
            exportFileName = SpringContextUtil.getTextValue(pageId)+ GlobalConstant.SPLIT
                    + "删除数据"+ GlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                    + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT +GlobalConstant.FILE_POSTFIX_XLS;
        }
        // 是否更新报送状态为手工报送
        String isUpdateSendStatus = (String) param.get("flag");
        if (DictCodeList.是否_中债_是.getValue().equals(isUpdateSendStatus)){
            // TODO：各自实现自己的手工处理方法
            dealDeleteDataSendStatus(dataInfo,param);
        }
        return ExportUtil.export(exportFileName, outFile, true);
    }
    
    /**
     * 功能:根据删除数据更新报送状态
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @param dataInfo 
     * void
     */
    public void dealDeleteDataSendStatus(ChbDeleteExcelExportDto dataInfo,Map<String, Object> pageParam) {
        if (dataInfo == null || CollectionUtils.isEmpty(dataInfo.getDetaiList())){
            return ;
        }
        // 删除类型01 产品存续期信息  02 资产要素明细信息  03 交易信息 04 资产估值信息 05 底层资产信息
        String deleteType = dataInfo.getDeletType();
        if (StringUtil.isNotEmpty(deleteType)){
            if (deleteType.contains(DictCodeListExt2.删除类型_资产要素明细信息.getValue())){
                if (dataInfo != null && CollectionUtils.isNotEmpty(dataInfo.getDetaiList())){
                    // 设置keyList
                    if (pageParam.get("expKeys") != null){
                        pageParam.put("keyList", pageParam.get("expKeys"));
                    } else { // 设置查询参数
                        // 数据状态
                        String status = DictCodeListExt2.报表数据状态_删除.getValue();
                        // 报送状态
                        String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();
                        pageParam.put("status", status);
                        pageParam.put("submitStatus", sendStatus);
                    }
                    assetInfoRegist001Service.updateSumStaus(pageParam);
                }
            } else if (deleteType.contains(DictCodeListExt2.删除类型_交易信息.getValue())){
                List<InvestTradeRegist001Dto> investTradeList = new ArrayList<>();
                if (dataInfo != null && CollectionUtils.isNotEmpty(dataInfo.getDetaiList())){
                    // 设置keyList
                    if (pageParam.get("expKeys") != null){
                        pageParam.put("keyList", pageParam.get("expKeys"));
                    } else { // 设置查询参数
                        // 数据状态
                        String status = DictCodeListExt2.报表数据状态_删除.getValue();
                        // 报送状态
                        String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();
                        pageParam.put("status", status);
                        pageParam.put("sendStatus", sendStatus);
                    }
                    investTradeRegist001Service.updateSumStaus(pageParam);
                }
            } else if (deleteType.contains(DictCodeListExt2.删除类型_资产估值信息.getValue())){
                if (dataInfo != null && CollectionUtils.isNotEmpty(dataInfo.getDetaiList())){
                	// 设置keyList
                    if (pageParam.get("expKeys") != null){
                        pageParam.put("keyList", pageParam.get("expKeys"));
                        pageParam.put("flag", "02");
                    } else { // 设置查询参数
                        // 数据状态
                        String status = DictCodeListExt2.报表数据状态_删除.getValue();
                        // 报送状态
                        String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();
        				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
        				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
                    }
                    chbAstValueRegist001Service.updateSumStaus(pageParam);
                }
            } else if (deleteType.contains(DictCodeListExt2.删除类型_底层资产信息.getValue())){
                if (dataInfo != null && CollectionUtils.isNotEmpty(dataInfo.getDetaiList())){
                	// 设置keyList
                    if (pageParam.get("expKeys") != null){
                        pageParam.put("keyList", pageParam.get("expKeys"));
                        pageParam.put("flag", "02");
                    } else { // 设置查询参数
                        // 数据状态
                        String status = DictCodeListExt2.报表数据状态_删除.getValue();
                        // 报送状态
                        String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();
        				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
        				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
                    }
                    chbBotAstPositionRegist001Service.updateSumStaus(pageParam);
                }
            }
            else if (deleteType.contains(DictCodeListExt2.删除类型_产品存续期信息.getValue())){
                if (dataInfo != null && CollectionUtils.isNotEmpty(dataInfo.getDetaiList())){
                    // 设置keyList
                    if (pageParam.get("expKeys") != null){
                        pageParam.put("keyList", pageParam.get("expKeys"));
                    } else { // 设置查询参数
                        // 数据状态
                        String status = DictCodeListExt2.报表数据状态_删除.getValue();
                        // 报送状态
                        String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();
                        pageParam.put("status", status);
                        pageParam.put("submitStatus", sendStatus);
                    }
                    prodDurationReg001Service.updateSumStaus(pageParam);
                }
            }
        }
        
    }
    

    /**
     * 功能:根据查询条件查询信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @param pageId 原页面id
     * @param selectParam 查询参数
     * @param deleteReason 删除原因
     * @return 
     * ChbDeleteExcelExportDto
     */
    public ChbDeleteExcelExportDto searchByCondition(String pageId,Map<String,
            Object> selectParam,String deleteReason) {
        // 根据pageId分开处理
        ChbDeleteExcelExportDto deleteInfo = new ChbDeleteExcelExportDto();
        if ("SvrAssetInfoRegist001".equals(pageId)){
            selectParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("status")));
            selectParam.put("submitStatusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("sendStatus")));
            List<AssetInfoRegist001Dto> assetInfoList = assetInfoRegist001Mapper.selectAssetInfoByPage(selectParam);
            convertAssetInfoToDeletList(deleteInfo,assetInfoList,deleteReason);
        } else if ("SvrInvestTradeRegist001".equals(pageId)){
            selectParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("status")));
            selectParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("sendStatus")));
            List<InvestTradeRegist001Dto> investTradeList = investTradeRegist001Mapper.selectAllData(selectParam);
            convertInvestTradeToDeletList(deleteInfo,investTradeList,deleteReason, true);
        } else if ("SvrChbAstValueRegist001".equals(pageId)){
            selectParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("status")));
            selectParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("sendStatus")));
            List<ChbAstValueRegist001Dto> investTradeList = chbAstValueRegist001Mapper.searchPage(selectParam);
            convertChbAstValueToDeletList(deleteInfo,investTradeList,deleteReason);
        } else if ("SvrChbBotAstPositionRegist001".equals(pageId)){
            selectParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("status")));
            selectParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("sendStatus")));
            List<ChbBotAstPositionRegist001Dto> investTradeList = chbBotAstPositionRegist001Mapper.searchPage(selectParam);
            convertChbBotAstPositionToDeletList(deleteInfo,investTradeList,deleteReason);
        }else if ("SvrProdDurationReg001".equals(pageId)){
            selectParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("status")));
            selectParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, (String) selectParam.get("sendStatus")));
            List<ProdDurationReg001Dto> prodDurationRegList = prodDurationReg001Mapper.selectAllData(selectParam);
            convertChbProdDurationToDeletList(deleteInfo,prodDurationRegList,deleteReason);
        }
        return deleteInfo;
    }

}
