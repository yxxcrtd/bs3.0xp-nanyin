/*
 * 文件名：ProdFactorInfo001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：liuertong
 * 修改时间：2017年12月15日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */

package com.joyintech.tams.report.submitmaintenance.prodfactorinfo.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.xml.sax.SAXException;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.ReadConfigurationUtil;
import com.joyintech.tams.report.common.TxtParseUtil;
import com.joyintech.tams.report.common.XmlParseUtil;
import com.joyintech.tams.common.base.dao.ChbPrdBaseinfoMapper;
import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.service.SvrBaseCommonService;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dao.ProdFactorInfo001Mapper;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dto.ProdFacotrInfo002Dto;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dto.ProdFactorInfo001Dto;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author 刘二通
 * @version 1.0 2017年12月15日
 * @see ProdFactorInfo001Service
 * @since 1.0
 */
@Service
public class ProdFactorInfo001Service extends CommonExportService<ProdFactorInfo001Dto> {

    /**
     * 上游接口文件编码格式
     */
    @Value("${application.reportInterfFileEncoding}")
    public String reportInterfFileEncoding;

    /**
     * 投资者报送表常量
     */
    private static final String CHB_PRD_BASEINFO = "CHB_PRD_BASEINFO";

    /**
     * 百
     */
    private static final BigDecimal NUMBER_BAI = new BigDecimal(100);

    /**
     * 开发计划mapper
     */
    @Autowired
    private ProdFactorInfo001Mapper prodFactorInfo001Mapper;

    /**
     * svrBaseCommonService
     */
    @Autowired
    private SvrBaseCommonService svrBaseCommonService;
    /**
     * 上游接口解析分割符
     */
    @Value("${application.reportInterfSplitCharacter}")
    private String reportInterfSplitCharacter;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    /**
//     * 个人产品发行登记表基础mapper
//     */
//    @Autowired
//    private ChbPrdIssueSubMapper chbPrdIssueSubMapper;
//
//    /**
//     * 个人产品申报登记表
//     */
//    @Autowired
//    private ChbPrdDeclareSubMapper chbPrdDeclareSubMapper;
//
//    /**
//     * 机构和私人银行产品发行登记基础mapper
//     */
//    @Autowired
//    private ChbPrdOrgDeclareSubMapper chbPrdOrgDeclareSubMapper;

    /**
     * 产品基本信息基础mapper
     */
    @Autowired
    private ChbPrdBaseinfoMapper chbPrdBaseinfoMapper;

//    /**
//     * 个人说很棒登记Mapper
//     */
//    @Autowired
//    private PerProDeclaReg001Mapper perProDeclaReg001Mapper;
//
//    /**
//     * 个人发行登记
//     */
//    @Autowired
//    private PerProDisRegist001Mapper perProDisRegist001Mapper;

//    /**
//     * 机构和私人银行发行登记
//     */
//    @Autowired
//    private InsAndPriBanksReg001Mapper insAndPriBanksReg001Mapper;
//
//    /**
//     * 个人发行登记
//     */
//    @Autowired
//    private PerProDisRegist001Service perProDisRegist001Service;
//
//    /**
//     * 查询 <功能详细描述>
//     *
//     * @param param 页面参数
//     * @return 查询的结果
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    public Page<ProdFactorInfo001Dto> searchForPage(Map<String, Object> param)
//        throws Exception {
//        setSelectItem(param, false);
//        Page<ProdFactorInfo001Dto> page = super.queryForPage(ProdFactorInfo001Mapper.class,
//            "queryForPage", param);
//        setDictCodeShow(page.getRoot());
////        cnBondUpInterfTaskService.execute();
//        return page;
//    }
//
//    /**
//     * 查询所有内容，不分页
//     * <p>
//     * 导出功能需要导出查询条件的所有内容
//     * </p>
//     *
//     * @param param 画面查询参数
//     * @return 查询结果列表
//     */
//    public List<ProdFactorInfo001Dto> queryExportList(Map<String, Object> param) {
//        setSelectItem(param, true);
//        List<ProdFactorInfo001Dto> pageList = prodFactorInfo001Mapper.queryForPage(param);
//        setDictCodeShow(pageList);
//        return pageList;
//    }

//    /**
//     * 格式化导出结果 <功能详细描述>
//     *
//     * @param pageList 查询到的结果
//     * @see
//     * @since 1.0
//     */
//    public void setDictCodeShow(List<ProdFactorInfo001Dto> pageList) {
//        for (ProdFactorInfo001Dto dto : pageList) {
//            dto.setStatusName(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, dto.getStatus()));
//            dto.setIssuingStatusName(
//                SysDataUtil.getNameFromDataDict(DictCode.发行状态, dto.getIssuingStatus()));
//            dto.setDeclareStatusName(
//                SysDataUtil.getNameFromDataDict(DictCode.报备状态, dto.getDeclareStatus()));
//            dto.setProfitType(
//                SysDataUtil.getZZDictCodeAndName(DictCode.产品收益类型, dto.getProfitType()));
//            dto.setTerm(SysDataUtil.getZZDictCodeAndName(DictCode.产品期限, dto.getTerm()));
//            dto.setInvestArae(
//                SysDataUtil.getZZDictCodeAndName(DictCode.资金投向地区, dto.getInvestArae()));
//            dto.setFinanSerModel(
//                SysDataUtil.getZZDictCodeAndName(DictCode.理财业务服务模式, dto.getFinanSerModel()));
//            dto.setOperateMode(
//                SysDataUtil.getZZDictCodeAndName(DictCode.产品运作模式, dto.getOperateMode()));
//            dto.setBookMode(SysDataUtil.getZZDictCodeAndName(DictCode.会计核算方式, dto.getBookMode()));
//            dto.setCmmode(SysDataUtil.getZZDictCodeAndName(DictCode.产品资产配置方式, dto.getCmmode()));
//            dto.setPmmode(SysDataUtil.getZZDictCodeAndName(DictCode.产品管理模式, dto.getPmmode()));
//            dto.setPricedMode(
//                SysDataUtil.getZZDictCodeAndName(DictCode.产品定价方式, dto.getPricedMode()));
//            dto.setInvAssetType(
//                SysDataUtil.getZZDictCodeAndName(DictCode.投资资产类型, dto.getInvAssetType()));
//            dto.setIsYield(SysDataUtil.getZZDictCodeAndName(DictCode.有无标识, dto.getIsYield()));
//            dto.setIsYieldMeasBasis(
//                SysDataUtil.getCodeFromDataDictByName(DictCode.是否, dto.getIsYieldMeasBasis()));
//            dto.setRaiseCcy(SysDataUtil.getZZDictCodeAndName(DictCode.币种, dto.getRaiseCcy()));
//            dto.setCashPrinCcy(
//                SysDataUtil.getZZDictCodeAndName(DictCode.币种, dto.getCashPrinCcy()));
//            dto.setCashProfCcy(
//                SysDataUtil.getZZDictCodeAndName(DictCode.币种, dto.getCashProfCcy()));
//            dto.setRiskLevel(
//                SysDataUtil.getZZDictCodeAndName(DictCode.产品风险等级, dto.getRiskLevel()));
//            dto.setTermRightFlag(
//                SysDataUtil.getZZDictCodeAndName(DictCode.有无标识, dto.getTermRightFlag()));
//            dto.setCusRedeemFlag(
//                SysDataUtil.getZZDictCodeAndName(DictCode.有无标识, dto.getCusRedeemFlag()));
//            dto.setContMode(SysDataUtil.getZZDictCodeAndName(DictCode.合作模式, dto.getContMode()));
//            dto.setInvestPrinDate(
//                SysDataUtil.getZZDictCodeAndName(DictCode.投资本金到账日, dto.getInvestPrinDate()));
//            dto.setInvestProfDate(
//                SysDataUtil.getZZDictCodeAndName(DictCode.投资收益到账日, dto.getInvestProfDate()));
//            dto.setCreditFlag(
//                SysDataUtil.getZZDictCodeAndName(DictCode.有无标识, dto.getCreditFlag()));
//            dto.setCreditMode(
//                SysDataUtil.getZZDictCodeAndName(DictCode.产品增信形式, dto.getCreditMode()));
//            dto.setCreditType(
//                SysDataUtil.getZZDictCodeAndName(DictCode.产品增信机构类型, dto.getCreditType()));
//            dto.setGradeType(SysDataUtil.getZZDictCodeAndName(DictCode.层级类别, dto.getGradeType()));
//            dto.setHolidayOnFlag(
//                SysDataUtil.getZZDictCodeAndName(DictCode.是否, dto.getHolidayOnFlag()));
//            dto.setInvestCountry(
//                SysDataUtil.getZZDictCodeAndName(DictCode.产品投资国家或地区, dto.getInvestCountry()));
//            dto.setIsConstracte(
//                SysDataUtil.getZZDictCodeAndName(DictCode.是否, dto.getIsConstracte()));
//            dto.setOpenBuss(SysDataUtil.getZZDictCodeAndName(DictCode.开放期业务, dto.getOpenBuss()));
//            dto.setOpenCircle(
//                SysDataUtil.getZZDictCodeAndName(DictCode.规律开放周期, dto.getOpenCircle()));
//            dto.setOpenMode(SysDataUtil.getZZDictCodeAndName(DictCode.开放模式, dto.getOpenMode()));
//            dto.setRiskType(SysDataUtil.getZZDictCodeAndName(DictCode.投资者风险偏好, dto.getRiskType()));
//            dto.setInvestorType(
//                SysDataUtil.getZZDictCodeAndName(DictCode.投资者类型, dto.getInvestorType()));
//            dto.setIsIntact(SysDataUtil.getZZDictCodeAndName(DictCode.是否, dto.getIsIntact()));
//            dto.setConPrdTraget(
//                SysDataUtil.getZZDictCodeAndName(DictCode.结构性产品挂钩标的, dto.getConPrdTraget()));
//            if (dto.getHighYield() != null) {
//                dto.setHighYieldStr(dto.getHighYield().multiply(NUMBER_BAI).setScale(5) + "%");
//            }
//            if (dto.getLowYield() != null) {
//                dto.setLowYieldStr(dto.getLowYield().multiply(NUMBER_BAI).setScale(5) + "%");
//            }
//            if (dto.getExpAvgYield() != null) {
//                dto.setExpAvgYieldStr(dto.getExpAvgYield().multiply(NUMBER_BAI).setScale(5) + "%");
//            }
//            if (dto.getTruFeeRate() != null) {
//                dto.setTruFeeRateStr(dto.getTruFeeRate().multiply(NUMBER_BAI).setScale(5) + "%");
//            }
//            if (dto.getSaleFeeRate() != null) {
//                dto.setSaleFeeRateStr(dto.getSaleFeeRate().multiply(NUMBER_BAI).setScale(5) + "%");
//            }
//            String[] codeList = StringUtil.split(dto.getSaleArea(), StringUtil.STR_COMMA);
//            String areaString = new String();
//            if (codeList.length > 0) {
//                for (int m = 0; m < codeList.length; m++ ) {
//                    if (m < codeList.length - 1) {
//                        areaString = areaString + SysDataUtil.getZZDictCodeAndName(DictCode.产品销售区域,
//                            codeList[m]) + StringUtil.STR_COMMA;
//                    }
//                    else {
//                        areaString = areaString + SysDataUtil.getZZDictCodeAndName(DictCode.产品销售区域,
//                            codeList[m]);
//                    }
//                }
//            }
//            dto.setSaleArea(areaString);
//        }
//    }
//
//    /**
//     * 设置查询参数 <功能详细描述>
//     *
//     * @param param 查询参数
//     * @param flag 是否导出
//     * @see
//     * @since 1.0
//     */
//    public void setSelectItem(Map<String, Object> param, Boolean flag) {
//        if (flag) {
//            String keys = (String)param.get("keys");
//            param.put("keys", StringUtil.splitToList(StringUtil.STR_COMMA, keys));
//        }
//        // 数据状态
//        String status = (String)param.get("status");
//        param.put("statusList", StringUtil.splitToList(StringUtil.STR_COMMA, status));
//        // 收益类型
//        String profitType = (String)param.get("profitType");
//        param.put("profitTypeList", StringUtil.splitToList(StringUtil.STR_COMMA, profitType));
//        // 投资者类型
//        String investorType = (String)param.get("investorType");
//        param.put("investorTypeList", StringUtil.splitToList(StringUtil.STR_COMMA, investorType));
//        // 发行要素维护完整
//        String isIntact = (String)param.get("isIntact");
//        param.put("isIntactList", StringUtil.splitToList(StringUtil.STR_COMMA, isIntact));
//        // 报备状态
//        String declareStatus = (String)param.get("declareStatus");
//        param.put("declareStatusList",
//            StringUtil.splitToList(StringUtil.STR_COMMA, declareStatus));
//        // 发行状态
//        String issuingStatus = (String)param.get("issuingStatus");
//        param.put("issuingStatusList",
//            StringUtil.splitToList(StringUtil.STR_COMMA, issuingStatus));
//        // 机构代码
//        String orgCode = (String)param.get("orgCode");
//        if (orgCode == null || orgCode.isEmpty()) {
//            List<String> orgCodeList = this.getOrgCodeListForSearch();
//            param.put("orgCodeList", orgCodeList);
//        }
//        else {
//            param.put("orgCodeList", StringUtil.splitToList(StringUtil.STR_COMMA, orgCode));
//        }
//    }
//
//    /**
//     * 根据参数list进行删除 <功能详细描述>
//     *
//     * @param dto 参数
//     * @see
//     * @since 1.0
//     */
//    public void delList(ProdFactorInfo001Dto dto) {
//        List<String> keyList = new ArrayList<String>();
//        Map<String, Object> map = new HashMap<String, Object>();
//        for (ProdFactorInfo001Dto entity : dto.getDtoList()) {
//            keyList.add(entity.getProdId() + '|' + entity.getOrgCode() + '|' + entity.getStatus());
//            entity.setIssuePartyId(getUserBankCode());
//            formatToCode(entity);
//            // 7.2.1投资者业务类型为“一般个人客户专属”或者“高资产净值客户专属”
//            if (entity.getInvestorType() != null) {
//                if (entity.getInvestorType().equals(DictCodeList.投资者类型_一般个人客户.getValue())
//                    || entity.getInvestorType().equals(DictCodeList.投资者类型_高资产净值客户.getValue())) {
//                    entity.setStatus(DictCodeList.报送数据状态_删除.getValue());
//                    // 判断产品在个人申报登记中是否存在数据
//                    // 查询表中是否已存在该数据
//                    ProdFactorInfo001Dto key1 = new ProdFactorInfo001Dto();
//                    key1.setProdInsId(entity.getProdInsId());
//                    key1.setIssuePartyId(entity.getIssuePartyId());
//                    List<ProdFactorInfo001Dto> sd = prodFactorInfo001Mapper.selectByPrimaryKey1(
//                        key1);
//                    if (!sd.isEmpty()) {
//                        ChbPrdDeclareSub record1 = new ChbPrdDeclareSub();
//                        BeanUtil.copyPropertiesIgnoreNull(record1, entity);
//                        if (sd.size() == 1) {
//                            if (sd.get(0).getSendStatus().equals("01")) {
//                                prodFactorInfo001Mapper.delete1(key1);
//                            }
//                            else if (sd.get(0).getSendStatus().equals("02")
//                                     || "03".equals(sd.get(0).getSendStatus())) {
//                                // 向个人申报登记表中插入删除未报送数据（行内报送截止日与中债报送截止日根据报送时点共通获取）
//                                record1.setChbSubmitDeadline(ChbDeadlineUtil.getChbEndDateNoMap(
//                                    "01", "01", "03", dto.getRaiseVdate()).get(0));
//                                record1.setSubmitDeadline(ChbDeadlineUtil.getChbEndDateNoMap("01",
//                                    "01", "03", dto.getRaiseVdate()).get(0));
//                                record1.setRegistSeqno(
//                                    this.getSequenceIndexData(DataIndexSequenceType.产品管理));
//                                record1.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                chbPrdDeclareSubMapper.insertSelective(record1);
//                            }
//                        }
//                        else if (sd.size() == 2) {
//                            // 将未报送的数据更新为删除（行内报送截止日与中债报送截止日根据报送时点共通获取）
//                            record1.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                            prodFactorInfo001Mapper.updateDtoEByPrimaryKey1(record1);
//                        }
//                    }
//                    // 判断产品在个人发行登记中是否存在数据
//                    ProdFactorInfo001Dto key2 = new ProdFactorInfo001Dto();
//                    key2.setProdId(entity.getProdId());
//                    key2.setIssuePartyId(entity.getIssuePartyId());
//                    List<ProdFactorInfo001Dto> sd2 = prodFactorInfo001Mapper.selectByPrimaryKey2(
//                        key2);
//                    if (!sd2.isEmpty()) {
//                        ChbPrdIssueSub record2 = new ChbPrdIssueSub();
//                        BeanUtil.copyPropertiesIgnoreNull(record2, entity);
//                        record2.setProdRegistCode(entity.getRegCode());
//                        if (sd2.size() == 1) {
//                            if (sd2.get(0).getSendStatus().equals("01")) {
//                                prodFactorInfo001Mapper.delete2(key2);
//                            }
//                            else if (sd2.get(0).getSendStatus().equals("02")
//                                     || "03".equals(sd.get(0).getSendStatus())) {
//                                // 向个人发行登记表中插入删除未报送数据（行内报送截止日与中债报送截止日根据报送时点共通获取）
//                                record2.setChbSubmitDeadline(ChbDeadlineUtil.getChbEndDateNoMap(
//                                    "02", "01", "03", dto.getRaiseVdate()).get(0));
//                                record2.setSubmitDeadline(ChbDeadlineUtil.getChbEndDateNoMap("02",
//                                    "01", "03", dto.getRaiseVdate()).get(0));
//                                record2.setRegistSeqno(
//                                    this.getSequenceIndexData(DataIndexSequenceType.产品管理));
//                                record2.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                chbPrdIssueSubMapper.insertSelective(record2);
//                            }
//                        }
//                        else if (sd2.size() == 2) {
//                            // 将未报送的数据更新为删除（行内报送截止日与中债报送截止日根据报送时点共通获取）
//                            record2.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                            prodFactorInfo001Mapper.updateDtoEByPrimaryKey2(record2);
//                        }
//                    }
//
//                }
//                else {
//                    // 投资者业务类型不为“一般个人客户专属”或者“高资产净值客户专属”
//                    entity.setStatus(DictCodeList.报送数据状态_删除.getValue());
//                    // 判断产品在机构及私人银行发行登记中是否存在数据
//                    ProdFactorInfo001Dto key3 = new ProdFactorInfo001Dto();
//                    key3.setProdId(entity.getProdId());
//                    key3.setIssuePartyId(entity.getIssuePartyId());
//                    List<ProdFactorInfo001Dto> sd3 = prodFactorInfo001Mapper.selectByPrimaryKey3(
//                        key3);
//                    if (!sd3.isEmpty()) {
//                        ChbPrdOrgDeclareSub record3 = new ChbPrdOrgDeclareSub();
//                        BeanUtil.copyPropertiesIgnoreNull(record3, entity);
//                        record3.setProdRegistCode(entity.getRegCode());
//                        if (sd3.size() == 1) {
//                            if (sd3.get(0).getSendStatus().equals("01")) {
//                                prodFactorInfo001Mapper.delete3(key3);
//                            }
//                            else if (sd3.get(0).getSendStatus().equals("02")) {
//                                // 向机构及私人银行发行登记表中插入删除未报送数据（行内报送截止日与中债报送截止日根据报送时点共通获取）
//                                record3.setChbSubmitDeadline(ChbDeadlineUtil.getChbEndDateNoMap(
//                                    "03", "02", "03", dto.getRaiseVdate()).get(0));
//                                record3.setSubmitDeadline(ChbDeadlineUtil.getChbEndDateNoMap("03",
//                                    "02", "03", dto.getRaiseVdate()).get(0));
//                                record3.setRegistSeqno(
//                                    this.getSequenceIndexData(DataIndexSequenceType.产品管理));
//                                record3.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                chbPrdOrgDeclareSubMapper.insertSelective(record3);
//                            }
//                        }
//                        else if (sd3.size() == 2) {
//                            // 将未报送的数据更新为删除（行内报送截止日与中债报送截止日根据报送时点共通获取）
//                            record3.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                            prodFactorInfo001Mapper.updateDtoEByPrimaryKey3(record3);
//                        }
//                    }
//                }
//
//            }
//            map.put("keyList", keyList);
//            map.put("nowDate", svrBaseCommonService.getSysDate());
//            prodFactorInfo001Mapper.deleListE(map);
//        }
//    }

//    /**
//     * 导入文件
//     * <p>
//     * 解析excel文件并进行验证
//     * </p>
//     *
//     * @param fileName 导入的文件名名
//     * @return 导入结果
//     * @throws Exception 异常
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public List<ExcelValidDto> importData(String fileName)
//        throws Exception {
//        if (StringUtil.isNotEmpty(fileName)) {
//            List<List<String>> dataList = ExcelUtil.readToList(
//                FileNameUtil.getUploadFilePath(fileName), null, null, 0, 1, 0);
//            if (null != dataList) {
//                List<ProdFactorInfo001Dto> entities = new ArrayList<>(dataList.size());
//                // check导入的数据
//                List<ExcelValidDto> checkResult = convertToEntity(dataList, entities);
//
//                if (checkResult.isEmpty()) {
//                    List<ChbPrdDeclareSub> entities1 = new ArrayList<>(dataList.size());
//                    List<ChbPrdIssueSub> entities2 = new ArrayList<>(dataList.size());
////                    List<PerProDisRegist001Dto> entities21 = new ArrayList<>(dataList.size());
//                    List<ChbPrdOrgDeclareSub> entities3 = new ArrayList<>(dataList.size());
//                    entities.forEach(item -> {
//                        if (item.getInvestorType() != null) {
//                            // 投资者业务类型为“一般个人客户专属”或者“高资产净值客户专属” : 个人申报
//                            if (item.getInvestorType().equals(DictCodeList.投资者类型_一般个人客户.getValue())
//                                || item.getInvestorType().equals(
//                                    DictCodeList.投资者类型_高资产净值客户.getValue())) {
//                                ChbPrdDeclareSub record1 = new ChbPrdDeclareSub();
//                                BeanUtil.copyPropertiesIgnoreNull(record1, item);
//                                record1.setRegistSeqno(
//                                    this.getSequenceIndexData(DataIndexSequenceType.产品管理));
//                                entities1.add(record1);
//                                // 发行要素维护完整 ： 个人发行
//                                ChbPrdIssueSub record2 = new ChbPrdIssueSub();
//                                BeanUtil.copyPropertiesIgnoreNull(record2, item);
//                                record2.setProdRegistCode(item.getRegCode());
//                                record2.setRegistSeqno(
//                                    this.getSequenceIndexData(DataIndexSequenceType.产品管理));
//                                entities2.add(record2);
//                                PerProDisRegist001Dto record21 = new PerProDisRegist001Dto();
//                                BeanUtil.copyPropertiesIgnoreNull(record21, record2);
//                                record21.setProdId(item.getProdInsId());
//                                entities21.add(record21);
//                            }
//                            else {
//                                // 中债机构和私人银行产品发行
//                                ChbPrdOrgDeclareSub record3 = new ChbPrdOrgDeclareSub();
//                                BeanUtil.copyPropertiesIgnoreNull(record3, item);
//                                record3.setRegistSeqno(
//                                    this.getSequenceIndexData(DataIndexSequenceType.产品管理));
//                                entities3.add(record3);
//                            }
//                        }
//                    });
//                    // 数据检查 result1： 个人申报 ；result2 ： 个人发行； result3： 中债机构和私人银行产品发行
//                    SubCheckResult result1 = new SubCheckResult();
//                    SubCheckResult result2 = new SubCheckResult();
//                    SubCheckResult result3 = new SubCheckResult();
//                    if (entities1.size() > 0) {
//                        try {
//                            result1 = SubCheckUtil.checkObjDataList(SubReportClass.一般个人产品申报登记,
//                                entities1, "prodInsId", false, 2);
//                        }
//                        catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        List<ExcelValidDto> checkResult1 = result1.getValidResultList();
//                        if (checkResult1.isEmpty()) {
//                            entities.forEach(item -> {
//                                ChbPrdBaseinfo item1 = new ChbPrdBaseinfo();
//                                BeanUtil.copyPropertiesIgnoreNull(item1, item);
//                                item1.setStatus(DictCodeList.维护数据状态_有效.getValue());
//                                item1.setIsIntact(DictCodeList.是否_中债_否.getValue());
//                                // item1.setExpAvgYield(item1.getExpAvgYield().divide(new
//                                // BigDecimal(100)));
//                                // item1.setSaleFeeRate(item1.getSaleFeeRate().divide(new
//                                // BigDecimal(100)));
//                                // item1.setTruFeeRate(item1.getTruFeeRate().divide(new
//                                // BigDecimal(100)));
//                                // item1.setLowYield(item1.getLowYield().divide(new
//                                // BigDecimal(100)));
//                                // item1.setHighYield(item1.getHighYield().divide(new
//                                // BigDecimal(100)));
//                                this.setRate(item1, null, null, null);
//                                // 查询表中是否已存在该数据
//                                ChbPrdBaseinfoKey key = new ChbPrdBaseinfoKey();
//                                key.setProdId(item1.getProdId());
//                                key.setOrgCode(item1.getOrgCode());
//                                List<ProdFactorInfo001Dto> sd = prodFactorInfo001Mapper.selectByPrimaryKey(
//                                    key);
//                                if (!sd.isEmpty()) {
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey(item1);
//                                }
//                                else {
//                                    chbPrdBaseinfoMapper.insertSelective(item1);
//                                }
//
//                            });
//                            entities1.forEach(item -> {
//                                // item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                item.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                // item.setSaleFeeRate(item.getSaleFeeRate().divide(new
//                                // BigDecimal(100)));
//                                // item.setTruFeeRate(item.getTruFeeRate().divide(new
//                                // BigDecimal(100)));
//                                // item.setLowYield(item.getLowYield().divide(new
//                                // BigDecimal(100)));
//                                // item.setHighYield(item.getHighYield().divide(new
//                                // BigDecimal(100)));
//                                this.setRate(null, item, null, null);
//                                ProdFactorInfo001Dto key = new ProdFactorInfo001Dto();
//                                key.setProdInsId(item.getProdInsId());
//                                key.setIssuePartyId(item.getIssuePartyId());
//                                List<ProdFactorInfo001Dto> sd = prodFactorInfo001Mapper.selectByPrimaryKey1(
//                                    key);
//                                // key.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                // 1.若无数据，则为新建状态登记数据，insert edit by wm
//                                if (StringUtil.isEmptyList(sd)) {
//                                    item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品申报登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_新建.getValue(),
//                                        item.getRaisePstrdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    chbPrdDeclareSubMapper.insertSelective(item);
//                                }
//                                // 只有一个数据，并且报送状态为未报送，则为新建状态登记数据，update
//                                else if (sd.size() == 1 && DictCodeList.报送状态_未报送.getValue().equals(
//                                    sd.get(0).getSendStatus())) {
//                                    item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品申报登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_新建.getValue(),
//                                        item.getRaisePstrdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey1(item);
//                                }
//                                // 只有一个数据，并且已报送，则登记数据状态为修改，并且insert
//                                else if (sd.size() == 1
//                                         && !DictCodeList.报送状态_未报送.getValue().equals(
//                                             sd.get(0).getSendStatus())) {
//                                    item.setStatus(DictCodeList.报送数据状态_修改.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品申报登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_修改.getValue(),
//                                        item.getRaisePstrdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    chbPrdDeclareSubMapper.insertSelective(item);
//                                }
//                                // 有两条数据，则登记数据状态为修改，并update
//                                else if (sd.size() == 2) {
//                                    item.setStatus(DictCodeList.报送数据状态_修改.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品申报登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_修改.getValue(),
//                                        item.getRaisePstrdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey1(item);
//                                }
//                                // if (!sd.isEmpty()) {
//                                // List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                // DictCodeList.报表名称_个人产品申报登记.getValue(),
//                                // DictCodeList.细分类型_个人.getValue(),
//                                // DictCodeList.报送数据状态_修改.getValue(),
//                                // item.getRaisePstrdate());
//                                // item.setChbSubmitDeadline(dateList.get(0));
//                                // item.setSubmitDeadline(dateList.get(1));
//                                // prodFactorInfo001Mapper.updateDtoEByPrimaryKey1(item);
//                                // }
//                                // else {
//                                // List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                // DictCodeList.报表名称_个人产品申报登记.getValue(),
//                                // DictCodeList.细分类型_个人.getValue(),
//                                // DictCodeList.报送数据状态_新建.getValue(),
//                                // item.getRaisePstrdate());
//                                // item.setChbSubmitDeadline(dateList.get(0));
//                                // item.setSubmitDeadline(dateList.get(1));
//                                // chbPrdDeclareSubMapper.insertSelective(item);
//                                // }
//
//                            });
//                        }
//                        else {
//                            return checkResult1;
//                        }
//
//                    }
//                    if (entities2.size() > 0) {
//                        try {
//                            result2 = SubCheckUtil.checkObjDataList(SubReportClass.一般个人产品发行登记,
//                                entities2, "prodInsId", false, 2);
//                        }
//                        catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        List<ExcelValidDto> checkResult2 = result2.getValidResultList();
//                        List<ExcelValidDto> excelValidList = perProDisRegist001Service.checkDataBySelf(
//                            entities21);
//                        if (StringUtil.isNotEmptyList(excelValidList)) {
//                            if (StringUtil.isNotEmptyList(checkResult2)) {
//                                for (ExcelValidDto dto : checkResult2) {
//                                    for (ExcelValidDto excelValidDto : excelValidList) {
//                                        if (StringUtil.equals(dto.getValidKey(),
//                                            excelValidDto.getValidKey())) {
//                                            if (StringUtil.isEmpty(dto.getErrorInfo())) {
//                                                dto.setErrorInfo(excelValidDto.getErrorInfo());
//                                            }
//                                            else {
//                                                dto.setErrorInfo(dto.getErrorInfo() + "<br/>"
//                                                                 + excelValidDto.getErrorInfo());
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            else {
//                                checkResult2 = excelValidList;
//                            }
//                        }
//                        if (checkResult2.isEmpty()) {
//                            entities2.forEach(item -> {
//                                // item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                item.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                // item.setExpMinYield(item.getExpMinYield().divide(new
//                                // BigDecimal(100)));
//                                // item.setExpMaxYield(item.getExpMaxYield().divide(new
//                                // BigDecimal(100)));
//                                // item.setExpAvgYield(item.getExpAvgYield().divide(new
//                                // BigDecimal(100)));
//                                this.setRate(null, null, item, null);
//                                ProdFactorInfo001Dto key = new ProdFactorInfo001Dto();
//                                key.setProdId(item.getProdId());
//                                key.setIssuePartyId(item.getIssuePartyId());
//                                // key.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                List<ProdFactorInfo001Dto> sd = prodFactorInfo001Mapper.selectByPrimaryKey2(
//                                    key);
//                                if (StringUtil.isEmptyList(sd)) {
//                                    item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品发行登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_新建.getValue(), item.getRaiseVdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    chbPrdIssueSubMapper.insertSelective(item);
//                                }
//                                // 只有一个数据，并且报送状态为未报送，则为新建状态登记数据，update
//                                else if (sd.size() == 1 && DictCodeList.报送状态_未报送.getValue().equals(
//                                    sd.get(0).getSendStatus())) {
//                                    item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品发行登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_新建.getValue(), item.getRaiseVdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey2(item);
//                                }
//                                // 只有一个数据，并且已报送，则登记数据状态为修改，并且insert
//                                else if (sd.size() == 1
//                                         && !DictCodeList.报送状态_未报送.getValue().equals(
//                                             sd.get(0).getSendStatus())) {
//                                    item.setStatus(DictCodeList.报送数据状态_修改.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品发行登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_修改.getValue(), item.getRaiseVdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    chbPrdIssueSubMapper.insertSelective(item);
//                                }
//                                // 有两条数据，则登记数据状态为修改，并update
//                                else if (sd.size() == 2) {
//                                    item.setStatus(DictCodeList.报送数据状态_修改.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_个人产品发行登记.getValue(),
//                                        DictCodeList.细分类型_个人.getValue(),
//                                        DictCodeList.报送数据状态_修改.getValue(), item.getRaiseVdate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey2(item);
//                                }
//                                // if (!sd.isEmpty()) {
//                                // List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                // DictCodeList.报表名称_个人产品发行登记.getValue(),
//                                // DictCodeList.细分类型_个人.getValue(),
//                                // DictCodeList.报送数据状态_修改.getValue(),
//                                // item.getRaiseVdate());
//                                // item.setChbSubmitDeadline(dateList.get(0));
//                                // item.setSubmitDeadline(dateList.get(1));
//                                // prodFactorInfo001Mapper.updateDtoEByPrimaryKey2(item);
//                                // }
//                                // else {
//                                // List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                // DictCodeList.报表名称_个人产品发行登记.getValue(),
//                                // DictCodeList.细分类型_个人.getValue(),
//                                // DictCodeList.报送数据状态_新建.getValue(),
//                                // item.getRaiseVdate());
//                                // item.setChbSubmitDeadline(dateList.get(0));
//                                // item.setSubmitDeadline(dateList.get(1));
//                                // chbPrdIssueSubMapper.insertSelective(item);
//                                // }
//                                ChbPrdBaseinfo item1 = new ChbPrdBaseinfo();
//                                BeanUtil.copyPropertiesIgnoreNull(item1, item);
//                                item1.setStatus(DictCodeList.维护数据状态_有效.getValue());
//                                item1.setIsIntact(DictCodeList.是否_中债_是.getValue());
//                                prodFactorInfo001Mapper.updateDtoEByPrimaryKey(item1);
//                            });
//                        }
//                    }
//                    if (entities3.size() > 0) {
//                        try {
//                            result3 = SubCheckUtil.checkObjDataList(SubReportClass.机构和私人银行产品发行登记,
//                                entities3, "prodInsId", false, 2);
//                        }
//                        catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        List<ExcelValidDto> checkResult3 = result3.getValidResultList();
//                        if (checkResult3.isEmpty()) {
//                            entities3.forEach(item -> {
//                                // item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                item.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                // item.setExpMinYield(item.getExpMinYield().divide(new
//                                // BigDecimal(100)));
//                                // item.setExpMaxYield(item.getExpMaxYield().divide(new
//                                // BigDecimal(100)));
//                                // item.setExpAvgYield(item.getExpAvgYield().divide(new
//                                // BigDecimal(100)));
//                                // item.setSaleFeeRate(item.getSaleFeeRate().divide(new
//                                // BigDecimal(100)));
//                                // item.setTruFeeRate(item.getTruFeeRate().divide(new
//                                // BigDecimal(100)));
//                                this.setRate(null, null, null, item);
//                                ProdFactorInfo001Dto key = new ProdFactorInfo001Dto();
//                                key.setProdId(item.getProdId());
//                                key.setIssuePartyId(item.getIssuePartyId());
//                                // key.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                                List<ProdFactorInfo001Dto> sd = prodFactorInfo001Mapper.selectByPrimaryKey3(
//                                    key);
//                                if (StringUtil.isEmptyList(sd)) {
//                                    item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(),
//                                        DictCodeList.细分类型_机构.getValue(),
//                                        DictCodeList.报送数据状态_新建.getValue(),
//                                        item.getRaisePenddate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    chbPrdOrgDeclareSubMapper.insertSelective(item);
//                                }
//                                // 只有一个数据，并且报送状态为未报送，则为新建状态登记数据，update
//                                else if (sd.size() == 1 && DictCodeList.报送状态_未报送.getValue().equals(
//                                    sd.get(0).getSendStatus())) {
//                                    item.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(),
//                                        DictCodeList.细分类型_机构.getValue(),
//                                        DictCodeList.报送数据状态_新建.getValue(),
//                                        item.getRaisePenddate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey3(item);
//                                }
//                                // 只有一个数据，并且已报送，则登记数据状态为修改，并且insert
//                                else if (sd.size() == 1
//                                         && !DictCodeList.报送状态_未报送.getValue().equals(
//                                             sd.get(0).getSendStatus())) {
//                                    item.setStatus(DictCodeList.报送数据状态_修改.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(),
//                                        DictCodeList.细分类型_机构.getValue(),
//                                        DictCodeList.报送数据状态_修改.getValue(),
//                                        item.getRaisePenddate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    chbPrdOrgDeclareSubMapper.insertSelective(item);
//                                }
//                                // 有两条数据，则登记数据状态为修改，并update
//                                else if (sd.size() == 2) {
//                                    item.setStatus(DictCodeList.报送数据状态_修改.getValue());
//                                    List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                        DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(),
//                                        DictCodeList.细分类型_机构.getValue(),
//                                        DictCodeList.报送数据状态_修改.getValue(),
//                                        item.getRaisePenddate());
//                                    item.setChbSubmitDeadline(dateList.get(0));
//                                    item.setSubmitDeadline(dateList.get(1));
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey3(item);
//                                }
//                                // if (!sd.isEmpty()) {
//                                // List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                // DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(),
//                                // DictCodeList.细分类型_机构.getValue(),
//                                // DictCodeList.报送数据状态_修改.getValue(),
//                                // item.getRaisePenddate());
//                                // item.setChbSubmitDeadline(dateList.get(0));
//                                // item.setSubmitDeadline(dateList.get(1));
//                                // prodFactorInfo001Mapper.updateDtoEByPrimaryKey3(item);
//                                // }
//                                // else {
//                                // List<Date> dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                                // DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(),
//                                // DictCodeList.细分类型_机构.getValue(),
//                                // DictCodeList.报送数据状态_新建.getValue(),
//                                // item.getRaisePenddate());
//                                // item.setChbSubmitDeadline(dateList.get(0));
//                                // item.setSubmitDeadline(dateList.get(1));
//                                // chbPrdOrgDeclareSubMapper.insertSelective(item);
//                                // }
//                            });
//                            entities.forEach(item -> {
//                                ChbPrdBaseinfo item1 = new ChbPrdBaseinfo();
//                                BeanUtil.copyPropertiesIgnoreNull(item1, item);
//                                item1.setStatus(DictCodeList.维护数据状态_有效.getValue());
//                                // item1.setExpAvgYield(item1.getExpAvgYield().divide(new
//                                // BigDecimal(100)));
//                                // item1.setSaleFeeRate(item1.getSaleFeeRate().divide(new
//                                // BigDecimal(100)));
//                                // item1.setTruFeeRate(item1.getTruFeeRate().divide(new
//                                // BigDecimal(100)));
//                                // item1.setLowYield(item1.getLowYield().divide(new
//                                // BigDecimal(100)));
//                                // item1.setHighYield(item1.getHighYield().divide(new
//                                // BigDecimal(100)));
//                                this.setRate(item1, null, null, null);
//                                // 查询表中是否已存在该数据
//                                ChbPrdBaseinfoKey key = new ChbPrdBaseinfoKey();
//                                key.setProdId(item1.getProdId());
//                                key.setOrgCode(item1.getOrgCode());
//                                List<ProdFactorInfo001Dto> sd = prodFactorInfo001Mapper.selectByPrimaryKey(
//                                    key);
//                                if (!sd.isEmpty()) {
//                                    prodFactorInfo001Mapper.updateDtoEByPrimaryKey(item1);
//                                }
//                                else {
//                                    chbPrdBaseinfoMapper.insertSelective(item1);
//                                }
//                            });
//                        }
//                        else {
//                            return checkResult3;
//                        }
//                    }
//
//                }
//                return checkResult;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * excel数据转为java对象
//     * <p>
//     * 将excel导入的数据，按行转为EquiteBaseInfo001Dto对象，同时验证各字段值
//     * </p>
//     *
//     * @param dataList excel导入的数据
//     * @param entities 转化后的列表
//     * @return 验证信息
//     * @throws Exception 异常信息
//     */
//    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,
//                                                List<ProdFactorInfo001Dto> entities)
//        throws Exception {
//        List<ExcelValidDto> checkResult = new ArrayList<>();
//        if (StringUtil.isEmptyList(dataList)) {
//            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
//                this.getMessage("COMMON_FILE_E_0016")));
//            return checkResult;
//        }
//
//        String[] fieldNames = new String[] {"prodName", "regCode", "prodId", "prodBrand",
//            "prodStage", "raisePstrdate", "raisePenddate", "saleAmtStr", "raiseAmtPlan", "pmmode",
//            "mname", "investPrinDate", "investProfDate", "term", "investorType", "cusRedeemFlag",
//            "termRightFlag", "creditFlag", "creditType", "creditMode", "raiseCcy", "cashPrinCcy",
//            "cashProfCcy", "riskType", "riskLevel", "isYieldMeasBasis", "isYield", "lowYield",
//            "highYield", "expAvgYield", "profitType", "operateMode", "cmmode", "approverId",
//            "approverName", "designerId", "designerName", "managerId", "managerName",
//            "liaisonName", "liaisonTel", "liaisonMobile", "liaisonEmail", "investArae",
//            "investCountry", "contMode", "cooperationName", "finanSerModel", "bookMode",
//            "pricedMode", "invAssetType", "conPrdTraget", "invTypeRat", "saleArea", "saleFeeRate",
//            "inDepName", "inDepCode", "outDepCtry", "outDepName", "truFeeRate", "raiseVdate",
//            "raiseEnddate", "prdVdate", "prdMdate", "isConstracte", "gradeType", "yieldComment",
//            "openMode", "openCircle", "openCircleDays", "ruleComment", "firstOpenDay",
//            "holidayOnFlag", "avgOpenCount", "openBuss", "openBussRemark", "remark"};
//        for (int i = 0; i < dataList.size(); i++ ) {
//            List<String> list = dataList.get(i);
//            int cellIndex = 0;
//            if (null == list || list.size() != 77) {
//                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
//                    this.getMessage("COMMON_FILE_E_0016")));
//                break;
//            }
//            ProdFactorInfo001Dto ProdFactorInfo = new ProdFactorInfo001Dto();
//            for (String fieldName : fieldNames) {
//                Field field = ReflectionUtils.findField(ProdFactorInfo.getClass(), fieldName);
//                field.setAccessible(true);
//                Type type = field.getGenericType();
//                String value = list.get(cellIndex++ );
//                if ("java.math.BigDecimal".equals(type.getTypeName())) {
//                    field.set(ProdFactorInfo,
//                        (value == null || "".equals(value)) ? null : new BigDecimal(value));
//                }
//                else if ("java.lang.Integer".equals(type.getTypeName())) {
//                    field.set(ProdFactorInfo,
//                        (value == null || "".equals(value)) ? null : Integer.valueOf(value));
//                }
//                else if ("java.lang.Short".equals(type.getTypeName())) {
//                    field.set(ProdFactorInfo,
//                        (value == null || "".equals(value)) ? null : Short.valueOf(value));
//                }
//                else if ("java.util.Date".equals(type.getTypeName())) {
//                    field.set(ProdFactorInfo, DateUtil.parse(value));
//                }
//                else {
//                    field.set(ProdFactorInfo, value);
//                }
//            }
//            ProdFactorInfo.setOrgCode(this.getUserFromSession().getOrgCode());
//            ProdFactorInfo.setIssuePartyId(this.getUserBankCode());
//
//            if (ProdFactorInfo.getLowYield() != null) {
//                ProdFactorInfo.setExpMinYield(
//                    ProdFactorInfo.getLowYield().multiply(NUMBER_BAI).setScale(5));
//                ProdFactorInfo.setLowYield(
//                    ProdFactorInfo.getLowYield().multiply(NUMBER_BAI).setScale(5));
//            }
//            if (ProdFactorInfo.getHighYield() != null) {
//                ProdFactorInfo.setExpMaxYield(
//                    ProdFactorInfo.getHighYield().multiply(NUMBER_BAI).setScale(5));
//                ProdFactorInfo.setHighYield(
//                    ProdFactorInfo.getHighYield().multiply(NUMBER_BAI).setScale(5));
//            }
//            if (ProdFactorInfo.getExpAvgYield() != null) {
//                ProdFactorInfo.setExpAvgYield(
//                    ProdFactorInfo.getExpAvgYield().multiply(NUMBER_BAI).setScale(5));
//            }
//            if (ProdFactorInfo.getSaleFeeRate() != null) {
//                ProdFactorInfo.setSaleFeeRate(
//                    ProdFactorInfo.getSaleFeeRate().multiply(NUMBER_BAI).setScale(5));
//            }
//            if (ProdFactorInfo.getTruFeeRate() != null) {
//                ProdFactorInfo.setTruFeeRate(
//                    ProdFactorInfo.getTruFeeRate().multiply(NUMBER_BAI).setScale(5));
//            }
//            ProdFactorInfo.setMdate(ProdFactorInfo.getPrdMdate());
//            ProdFactorInfo.setVdate(ProdFactorInfo.getPrdVdate());
//            ProdFactorInfo.setProdInsId(ProdFactorInfo.getProdId());
//
//            String[] codeList = StringUtil.split(ProdFactorInfo.getSaleArea(),
//                StringUtil.STR_COMMA);
//            String areaString = new String();
//            if (codeList.length > 0) {
//                for (int m = 0; m < codeList.length; m++ ) {
//                    if (m < codeList.length - 1) {
//                        areaString = areaString + SysDataUtil.getZZDictCodeFromName(
//                            DictCode.产品销售区域, codeList[m]) + StringUtil.STR_COMMA;
//                    }
//                    else {
//                        areaString = areaString + SysDataUtil.getZZDictCodeFromName(
//                            DictCode.产品销售区域, codeList[m]);
//                    }
//                }
//            }
//            ProdFactorInfo.setSaleArea(areaString);
//            formatToCode(ProdFactorInfo);
//            entities.add(ProdFactorInfo);
//        }
//        return checkResult;
//    }
//
//    /**
//     * 导入将数据字典转为code值
//     *
//     * @param ProdFactorInfo 数据对象
//     * @see
//     * @since 1.0
//     */
//    public void formatToCode(ProdFactorInfo001Dto ProdFactorInfo) {
//        ProdFactorInfo.setProfitType(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品收益类型, ProdFactorInfo.getProfitType()));
//        ProdFactorInfo.setTerm(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品期限, ProdFactorInfo.getTerm()));
//        ProdFactorInfo.setInvestArae(
//            SysDataUtil.getZZDictCodeFromName(DictCode.资金投向地区, ProdFactorInfo.getInvestArae()));
//        ProdFactorInfo.setFinanSerModel(SysDataUtil.getZZDictCodeFromName(DictCode.理财业务服务模式,
//            ProdFactorInfo.getFinanSerModel()));
//        ProdFactorInfo.setOperateMode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品运作模式, ProdFactorInfo.getOperateMode()));
//        ProdFactorInfo.setBookMode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.会计核算方式, ProdFactorInfo.getBookMode()));
//        ProdFactorInfo.setCmmode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品资产配置方式, ProdFactorInfo.getCmmode()));
//        ProdFactorInfo.setPmmode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品管理模式, ProdFactorInfo.getPmmode()));
//        ProdFactorInfo.setPricedMode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品定价方式, ProdFactorInfo.getPricedMode()));
//        ProdFactorInfo.setInvAssetType(
//            SysDataUtil.getZZDictCodeFromName(DictCode.投资资产类型, ProdFactorInfo.getInvAssetType()));
//        ProdFactorInfo.setIsYield(
//            SysDataUtil.getZZDictCodeFromName(DictCode.是否, ProdFactorInfo.getIsYield()));
//        ProdFactorInfo.setIsYieldMeasBasis(
//            SysDataUtil.getZZDictCodeFromName(DictCode.是否, ProdFactorInfo.getIsYieldMeasBasis()));
//        ProdFactorInfo.setRaiseCcy(
//            SysDataUtil.getZZDictCodeFromName(DictCode.币种, ProdFactorInfo.getRaiseCcy()));
//        ProdFactorInfo.setCashPrinCcy(
//            SysDataUtil.getZZDictCodeFromName(DictCode.币种, ProdFactorInfo.getCashPrinCcy()));
//        ProdFactorInfo.setCashProfCcy(
//            SysDataUtil.getZZDictCodeFromName(DictCode.币种, ProdFactorInfo.getCashProfCcy()));
//        ProdFactorInfo.setRiskLevel(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品风险等级, ProdFactorInfo.getRiskLevel()));
//        ProdFactorInfo.setTermRightFlag(
//            SysDataUtil.getZZDictCodeFromName(DictCode.有无标识, ProdFactorInfo.getTermRightFlag()));
//        ProdFactorInfo.setCusRedeemFlag(
//            SysDataUtil.getZZDictCodeFromName(DictCode.有无标识, ProdFactorInfo.getCusRedeemFlag()));
//        ProdFactorInfo.setContMode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.合作模式, ProdFactorInfo.getContMode()));
//        ProdFactorInfo.setInvestPrinDate(SysDataUtil.getZZDictCodeFromName(DictCode.投资本金到账日,
//            ProdFactorInfo.getInvestPrinDate()));
//        ProdFactorInfo.setInvestProfDate(SysDataUtil.getZZDictCodeFromName(DictCode.投资收益到账日,
//            ProdFactorInfo.getInvestProfDate()));
//        ProdFactorInfo.setCreditFlag(
//            SysDataUtil.getZZDictCodeFromName(DictCode.有无标识, ProdFactorInfo.getCreditFlag()));
//        ProdFactorInfo.setCreditMode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品增信形式, ProdFactorInfo.getCreditMode()));
//        ProdFactorInfo.setCreditType(
//            SysDataUtil.getZZDictCodeFromName(DictCode.产品增信机构类型, ProdFactorInfo.getCreditType()));
//        ProdFactorInfo.setGradeType(
//            SysDataUtil.getZZDictCodeFromName(DictCode.层级类别, ProdFactorInfo.getGradeType()));
//        ProdFactorInfo.setHolidayOnFlag(
//            SysDataUtil.getZZDictCodeFromName(DictCode.是否, ProdFactorInfo.getHolidayOnFlag()));
//        ProdFactorInfo.setInvestCountry(SysDataUtil.getZZDictCodeFromName(DictCode.产品投资国家或地区,
//            ProdFactorInfo.getInvestCountry()));
//        ProdFactorInfo.setIsConstracte(
//            SysDataUtil.getZZDictCodeFromName(DictCode.是否, ProdFactorInfo.getIsConstracte()));
//        ProdFactorInfo.setOpenBuss(
//            SysDataUtil.getZZDictCodeFromName(DictCode.开放期业务, ProdFactorInfo.getOpenBuss()));
//        ProdFactorInfo.setOpenCircle(
//            SysDataUtil.getZZDictCodeFromName(DictCode.规律开放周期, ProdFactorInfo.getOpenCircle()));
//        ProdFactorInfo.setOpenMode(
//            SysDataUtil.getZZDictCodeFromName(DictCode.开放模式, ProdFactorInfo.getOpenMode()));
//        ProdFactorInfo.setRiskType(
//            SysDataUtil.getZZDictCodeFromName(DictCode.投资者风险偏好, ProdFactorInfo.getRiskType()));
//        ProdFactorInfo.setInvestorType(
//            SysDataUtil.getZZDictCodeFromName(DictCode.投资者类型, ProdFactorInfo.getInvestorType()));
//        ProdFactorInfo.setIsIntact(
//            SysDataUtil.getZZDictCodeFromName(DictCode.是否, ProdFactorInfo.getIsIntact()));
//        ProdFactorInfo.setConPrdTraget(SysDataUtil.getZZDictCodeFromName(DictCode.结构性产品挂钩标的,
//            ProdFactorInfo.getConPrdTraget()));
//    }
//
//    /**
//     * 产品是否已发生产品交易或费用交易 <功能详细描述>
//     *
//     * @param dto ProdFactorInfo001Dto
//     * @return 否已发生产品交易或费用交易
//     * @see
//     * @since 1.0
//     */
//    public boolean isDeal(ProdFactorInfo001Dto dto) {
//        Boolean flag = true;
//        int count = prodFactorInfo001Mapper.isDeal(dto);
//        if (count == 0) {
//            flag = false;
//        }
//        return flag;
//    }
//
//    /**
//     * 获取机构下所有产品 <功能详细描述>
//     *
//     * @return 本机构下所有产品
//     * @see
//     * @since 1.0
//     */
//    public List<ProdFactorInfo001Dto> getAllProdList() {
//        List<ProdFactorInfo001Dto> list = prodFactorInfo001Mapper.getAllProdList();
//        return list;
//    }

    /**
     * @param in 输入流
     * @param type 来源系统
     * @param tmpPath 本地存放路径
     * @param suffix 文件类型
     * @param monitorInfoDto 读取上游文件监控信息dto
     * @return 处理成功失败
     * @see
     * @since 1.0
     */
    public String getPrdBaseInfoByInterf(InputStream in, String type, String tmpPath,
                                         String suffix, ChbFileReadMonitorInfo monitorInfoDto){
        List<Map<String, String>> infoList = null;
        if ("xml".equals(suffix)) {
            try {
                infoList = XmlParseUtil.parse(in);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
        }
        else if ("txt".equals(suffix)) {
            try {
                infoList = TxtParseUtil.parse(in, reportInterfFileEncoding, reportInterfSplitCharacter);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
        }
        boolean success = true;

        // 解析成功或失败的标识
        if (!StringUtil.isEmptyList(infoList)) {
            if(monitorInfoDto!=null)monitorInfoDto.setFileDataCount(BigDecimal.valueOf(infoList.size()));
            if (infoList.size() <= 1000) {
                success = excutePrdBaseInfoByStep(infoList, type, null);
            }
            else {
                // 创建一个线程池
                ExecutorService service = new JoyinThreadPoolExecutor(20);
                List<Callable<Boolean>> tasks = new ArrayList<>();
                if (!StringUtil.isEmptyList(infoList)) {
                    int start = 0;
                    int end = 0;
                    int size = infoList.size();
                    int max = (int)Math.ceil((double)size / 1000);
                    for (int i = 0; i < max; i++ ) {
                        start = end;
                        end = Math.min(size, 1000 * (i + 1));
                        List<Map<String, String>> subList = infoList.subList(start, end);
                        tasks.add(new Callable<Boolean>() {

                            @Override
                            public Boolean call() {
                                return excutePrdBaseInfoByStep(subList, type, tmpPath);
                            }
                        });
                    }
                    // 为线程池添加任务
                    List<Future<Boolean>> taskResult;
                    try {
                        taskResult = service.invokeAll(tasks);
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage(),e);
                        Thread.currentThread().interrupt();
                        return "fail";
                    }
                    for (Future<Boolean> res : taskResult) {
                        try {
                            success = success && res.get();
                        } catch (InterruptedException | ExecutionException e) {
                            logger.error(e.getMessage(),e);
                            Thread.currentThread().interrupt();
                            return "fail";
                        }
                    }
                    // 任务结束，停止线程池的所有线程
                    service.shutdown();
                }
            }
        }
        if (success) {
            return "sucess";
        }
        return "fail";
    }

    /**
     * 将map转换为entity格式
     *
     * @param infoList 传入的listMap
     * @param entities 转换后的lis
     * @param listCloumn 表列名
     * @param interType 数据来源
     * @see
     * @since 1.0
     */
    private void convertMapToEntity(List<Map<String, String>> infoList,
                                    List<ChbPrdBaseinfo> entities, List<String> listCloumn,
                                    String interType) {
        // 获取产品表的列
        // List<String> listCloumn = prodFactorInfo001Mapper.getTableColumnList(CHB_PRD_BASEINFO);
        for (Map<String, String> map : infoList) {
            ChbPrdBaseinfo chbPrdBaseinfo = new ChbPrdBaseinfo();
            for (String string : listCloumn) {
                String value = map.get(string);
                Field field = ReflectionUtils.findField(chbPrdBaseinfo.getClass(),
//                	StringUtil.dealString(StringUtil.lowerCase(string), false));
                    	this.subBlank(StringUtil.lowerCase(string)));
//                if(field==null){
//                	System.out.println(string);
//                	continue;
//                }
                field.setAccessible(true);
                Type type = field.getGenericType();
                if ("java.math.BigDecimal".equals(type.getTypeName())) {
                    try {
                        field.set(chbPrdBaseinfo,
                            (value == null || "".equals(value)) ? BigDecimal.ZERO : new BigDecimal(
                                value));
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return ;
                    }
                }
                else if ("java.lang.Integer".equals(type.getTypeName())) {
                    try {
                        field.set(chbPrdBaseinfo,
                            (value == null || "".equals(value)) ? 0 : Integer.valueOf(value));
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return ;
                    }
                }
                else if ("java.lang.Short".equals(type.getTypeName())) {
                    try {
                        field.set(chbPrdBaseinfo,
                            (value == null || "".equals(value)) ? 0 : Short.valueOf(value));
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return ;
                    }
                }
                else if ("java.util.Date".equals(type.getTypeName())) {
                    if (value != null) {
                        try {
                            field.set(chbPrdBaseinfo, DateUtil.parse(value));
                        } catch ( IllegalAccessException e) {
                            logger.error(e.getMessage(),e);
                            return ;
                        }
                    }
                }
                else {
                    try {
                        field.set(chbPrdBaseinfo, (value == null || "".equals(value)) ? "" : value);
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return ;
                    }
                }
            }
            chbPrdBaseinfo.setDataSource(interType);
            // 若行内标识码为空，则将产品Id赋值给行内标识码
            if (StringUtil.isEmpty(chbPrdBaseinfo.getProdInsId())) {
                chbPrdBaseinfo.setProdInsId(chbPrdBaseinfo.getProdId());
            }
            if (chbPrdBaseinfo.getHighYieldIssue() != null) {
                chbPrdBaseinfo.setHighYieldIssue(
                    chbPrdBaseinfo.getHighYieldIssue().multiply(NUMBER_BAI).setScale(5));
            }
            if (chbPrdBaseinfo.getLowYieldIssue() != null) {
                chbPrdBaseinfo.setLowYieldIssue(
                    chbPrdBaseinfo.getLowYieldIssue().multiply(NUMBER_BAI).setScale(5));
            }
            if (chbPrdBaseinfo.getLowYield() != null) {
                // chbPrdBaseinfo.setExpMinYield(
                // chbPrdBaseinfo.getLowYield().multiply(NUMBER_BAI).setScale(5));
                chbPrdBaseinfo.setLowYield(
                    chbPrdBaseinfo.getLowYield().multiply(NUMBER_BAI).setScale(5));
            }
            if (chbPrdBaseinfo.getHighYield() != null) {
                // chbPrdBaseinfo.setExpMaxYield(
                // chbPrdBaseinfo.getHighYield().multiply(NUMBER_BAI).setScale(5));
                chbPrdBaseinfo.setHighYield(
                    chbPrdBaseinfo.getHighYield().multiply(NUMBER_BAI).setScale(5));
            }
            if (chbPrdBaseinfo.getExpAvgYield() != null) {
                chbPrdBaseinfo.setExpAvgYield(
                    chbPrdBaseinfo.getExpAvgYield().multiply(NUMBER_BAI).setScale(5));
            }
            if (chbPrdBaseinfo.getSaleFeeRate() != null) {
                chbPrdBaseinfo.setSaleFeeRate(
                    chbPrdBaseinfo.getSaleFeeRate().multiply(NUMBER_BAI).setScale(5));
            }
            if (chbPrdBaseinfo.getTruFeeRate() != null) {
                chbPrdBaseinfo.setTruFeeRate(
                    chbPrdBaseinfo.getTruFeeRate().multiply(NUMBER_BAI).setScale(5));
            }
            chbPrdBaseinfo.setMdate(chbPrdBaseinfo.getPrdMdate());
            chbPrdBaseinfo.setVdate(chbPrdBaseinfo.getPrdVdate());
            entities.add(chbPrdBaseinfo);
        }
    }

	/**
     * <ul>
     * <li>1.调用共通方法解析传入的接口文件</li>
     * <li>2.遍历解析后的数据，逐条插入验证（暂定）</li>
     * <li>3.验证中债需要验证的模块，通过则赋有效状态；不通过，则显示待处理状态</li>
     * <li>4.验证通过后，判断是否新增数据
     * <ul>
     * <li>4.1 若为新增数据，则直接insert</li>
     * <li>4.2 若非新增数据，则需读取是否覆盖开关</li>
     * <li>4.2.1 若开关打开，只更新数据配置项中的字段 4.2.2</li>
     * </ul>
     * </li>
     * <li>若开关关闭，且数据库中为接口维护，则全量更新；若数据库中为手动维护，则不更新</li>此条规则去掉
     * </ul>
     *
     * @param infoList 需要解析的数据
     * @param interType 数据来源
     * @param tmpPath 存放路径
     * @return true处理成功 false处理失败
     * @see
     * @since 1.0
     */
    public boolean excutePrdBaseInfoByStep(List<Map<String, String>> infoList, String interType,
                                           String tmpPath){
        // 读取配置文件中配置项
        String[] result = ReadConfigurationUtil.readPropertiesByGetResourceAsStream(
            CHB_PRD_BASEINFO + "." + interType).split(StringUtil.SPACE);
        if (!StringUtil.isEmptyList(infoList)) {
            List<String> listCloumn = prodFactorInfo001Mapper.getTableColumnList(CHB_PRD_BASEINFO);
            // ProdFacotrInfo002Dto prodFacotrInfo002Dto = null;
            List<ChbPrdBaseinfo> entities = new ArrayList<>(infoList.size());
            // 将map转换为dto
            this.convertMapToEntity(infoList, entities, listCloumn, interType);
            // 逻辑操作
            ProdFacotrInfo002Dto prodFacotrInfo002Dto = this.checkPrdBaseinfoData(entities,
                listCloumn, result);
            // 维护表中无该产品信息，插入维护表
            if (!StringUtil.isEmptyList(prodFacotrInfo002Dto.getBaseListN())) {
                prodFactorInfo001Mapper.insertBatchPrd(prodFacotrInfo002Dto.getBaseListN());
            }
            // 维护表中有产品信息，更新维护表
            if (!StringUtil.isEmptyList(prodFacotrInfo002Dto.getBaseListO())) {
                prodFactorInfo001Mapper.updateBatchByPrimaryKey(
                    prodFacotrInfo002Dto.getBaseListO());
            }
//            // 当满足个人申报数据校验，并且登记表中无数据时,插入个人申报登记
//            if (StringUtil.isNotEmptyList(prodFacotrInfo002Dto.getDecalList())) {
//                perProDeclaReg001Mapper.insertBatchDeclare(prodFacotrInfo002Dto.getDecalList());
//            }
//            // 当满足个人申报数据校验，并且登记表中有数据时,更新个人申报登记
//            if (StringUtil.isNotEmptyList(prodFacotrInfo002Dto.getDecalListO())) {
//                perProDeclaReg001Mapper.updateBatchDeclare(prodFacotrInfo002Dto.getDecalListO());
//            }
//            // 当满足个人发行数据校验，并且登记表中无数据时,插入个人发行登记
//            if (StringUtil.isNotEmptyList(prodFacotrInfo002Dto.getIssueList())) {
//                perProDisRegist001Mapper.insertBatchIssue(prodFacotrInfo002Dto.getIssueList());
//            }
//            // 当满足个人发行数据校验，并且登记表中有数据时,更新个人发行登记
//            if (StringUtil.isNotEmptyList(prodFacotrInfo002Dto.getIssueListO())) {
//                perProDisRegist001Mapper.updateBatchIssue(prodFacotrInfo002Dto.getIssueList());
//            }
//            // 当满足机构和私人银行发行数据校验，并且登记表中无数据时,插入机构和私人银行发行登记
//            if (StringUtil.isNotEmptyList(prodFacotrInfo002Dto.getOrgList())) {
//                insAndPriBanksReg001Mapper.insertBatchInsAndBank(
//                    prodFacotrInfo002Dto.getOrgList());
//            }
//            // 当满足机构和私人银行发行数据校验，并且登记表中有数据时,更新机构和私人银行发行登记
//            if (StringUtil.isNotEmptyList(prodFacotrInfo002Dto.getOrgListO())) {
//                insAndPriBanksReg001Mapper.updateBatchInsAndBank(
//                    prodFacotrInfo002Dto.getOrgListO());
//            }
            // 数据解析完成，并导入数据库成功后，将ftp文件修改名称
            return (!StringUtil.isEmptyList(prodFacotrInfo002Dto.getBaseListN())
            		||!StringUtil.isEmptyList(prodFacotrInfo002Dto.getBaseListO()));
        }

        return false;
    }

    /**
     * 导入数据校验
     *
     * @param entities 转换后的lis
     * @param listCloumn 表列名
     * @param result 数据来源
     * @return 各种list集合
     * @see
     * @since 1.0
     */
    private ProdFacotrInfo002Dto checkPrdBaseinfoData(List<ChbPrdBaseinfo> entities,
                                                      List<String> listCloumn, String[] result) {
        // 查询维护表个人产品信息
        List<ChbPrdBaseinfo> listBase = prodFactorInfo001Mapper.getAllProdListByKeys(entities);
        Map<String, List<ChbPrdBaseinfo>> baseMap = listBase.stream().collect(
            JoyinCollectors.groupingBy(x -> x.getProdInsId() + GlobalConstant.UNDER_LINE
//                                            + StringUtil.getNotNullString(
//                                                x.getProdId())
					            		    + x.getProdId()
                                            + GlobalConstant.UNDER_LINE + x.getOrgCode()));
        ProdFacotrInfo002Dto prodFacotrInfo002Dto = new ProdFacotrInfo002Dto();
//        for (ChbPrdBaseinfo chbPrdBaseinfo : entities) {
//            if ("一般个人客户".equals(chbPrdBaseinfo.getInvestorType())
//                || "高资产净值客户".equals(
//                    chbPrdBaseinfo.getInvestorType())) {
//                this.doPersonalSubmitSingle(chbPrdBaseinfo, null, null, baseMap,
//                    prodFacotrInfo002Dto, result, null);
//            }
//            else {
//                this.doOrgSubmitSingle(chbPrdBaseinfo, null, baseMap, prodFacotrInfo002Dto,
//                    result, null);
//            }
//        }
        for (ChbPrdBaseinfo chbPrdBaseinfo : entities) {
        	doSubmitSingle(chbPrdBaseinfo, baseMap, prodFacotrInfo002Dto,
                    result);
        }
        return prodFacotrInfo002Dto;
    }

    private void doSubmitSingle(ChbPrdBaseinfo chbPrdBaseinfo,  Map<String, List<ChbPrdBaseinfo>> baseMap,
			ProdFacotrInfo002Dto prodFacotrInfo002Dto, String[] result) {
		// TODO Auto-generated method stub
        // 基础信息list
        List<ChbPrdBaseinfo> baseListN = prodFacotrInfo002Dto.getBaseListN();
        List<ChbPrdBaseinfo> baseListO = prodFacotrInfo002Dto.getBaseListO();
        String key = chbPrdBaseinfo.getProdInsId() + GlobalConstant.UNDER_LINE
            + (chbPrdBaseinfo.getProdId()==null?"":chbPrdBaseinfo.getProdId())
            + GlobalConstant.UNDER_LINE + chbPrdBaseinfo.getOrgCode();
        // 首先校验申报登记
        if (StringUtil.isEmptyList(baseMap.get(key))) {
            baseListO.add(chbPrdBaseinfo);
            chbPrdBaseinfo.setStatus(chbPrdBaseinfo.getStatus());
            // 删除维护表处理
            if (!StringUtil.isEmptyList(baseMap.get(key))) {
                ChbPrdBaseinfo chbPrdBaseinfoTemp = new ChbPrdBaseinfo();
                chbPrdBaseinfoTemp.setAgent(chbPrdBaseinfo.getAgent());
                chbPrdBaseinfoTemp.setProdId(chbPrdBaseinfo.getProdId());
                chbPrdBaseinfoTemp.setOrgCode(chbPrdBaseinfo.getOrgCode());
                chbPrdBaseinfoTemp.setProdInsId(chbPrdBaseinfo.getProdInsId());
                chbPrdBaseinfoTemp.setStatus(chbPrdBaseinfo.getStatus());
                chbPrdBaseinfoTemp.setUpdateUser("sysBatch");
                baseListO.add(chbPrdBaseinfoTemp);
            }
            else {
                baseListN.add(chbPrdBaseinfo);
            }
        }
        prodFacotrInfo002Dto.setBaseListN(baseListN);
        prodFacotrInfo002Dto.setBaseListO(baseListO);
	}
//
//	/**
//     * 个人产品校验
//     *
//     * @param chbPrdBaseinfo 传入信息
//     * @param declMap 已报送申报登记
//     * @param issMap 已报送发行登记
//     * @param baseMap 产品基础信息
//     * @param prodFacotrInfo002Dto 结果集
//     * @param result 返回
//     * @param bankCodeMap 登记银行编码集合
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    public void doPersonalSubmitSingle(ChbPrdBaseinfo chbPrdBaseinfo,
//                                       Map<String, List<ChbPrdDeclareSub>> declMap,
//                                       Map<String, List<ChbPrdIssueSub>> issMap,
//                                       Map<String, List<ChbPrdBaseinfo>> baseMap,
//                                       ProdFacotrInfo002Dto prodFacotrInfo002Dto, String[] result,
//                                       Map<String, List<ChbSysOrganization>> bankCodeMap)
//        throws Exception {
//        // 基础信息list
//        List<ChbPrdBaseinfo> baseListN = prodFacotrInfo002Dto.getBaseListN();
//        List<ChbPrdBaseinfo> baseListO = prodFacotrInfo002Dto.getBaseListO();
//        String key = chbPrdBaseinfo.getProdInsId() + GlobalConstant.UNDER_LINE
//                     + (chbPrdBaseinfo.getProdId()==null?"":chbPrdBaseinfo.getProdId())
//                     + GlobalConstant.UNDER_LINE + chbPrdBaseinfo.getOrgCode();
//        String issKey = chbPrdBaseinfo.getProdId() + GlobalConstant.UNDER_LINE
//                        + chbPrdBaseinfo.getOrgCode()+ GlobalConstant.UNDER_LINE;
//        String declKey = chbPrdBaseinfo.getProdInsId() + GlobalConstant.UNDER_LINE
//                         + chbPrdBaseinfo.getOrgCode()+ GlobalConstant.UNDER_LINE;
//        String registBankCode = "";
//        if (!StringUtil.isEmptyList(bankCodeMap.get(chbPrdBaseinfo.getOrgCode()))) {
//            registBankCode = bankCodeMap.get(chbPrdBaseinfo.getOrgCode()).get(
//                0).getRegistBankCode();
//        }
//        // 首先校验申报登记
//        prodFacotrInfo002Dto.setBaseListN(baseListN);
//        prodFacotrInfo002Dto.setBaseListO(baseListO);
//    }

//    /**
//     * 机构和私人银行产品校验
//     *
//     * @param chbPrdBaseinfo 传入信息
//     * @param orgMap 机构和私人银行发行登记
//     * @param baseMap 产品基础信息
//     * @param prodFacotrInfo002Dto 结果集
//     * @param result 返回
//     * @param bankCodeMap 登记银行编码集合
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    public void doOrgSubmitSingle(ChbPrdBaseinfo chbPrdBaseinfo,
//                                  Map<String, List<ChbPrdOrgDeclareSub>> orgMap,
//                                  Map<String, List<ChbPrdBaseinfo>> baseMap,
//                                  ProdFacotrInfo002Dto prodFacotrInfo002Dto, String[] result,
//                                  Map<String, List<ChbSysOrganization>> bankCodeMap)
//        throws Exception {
//    	// 需要插入的申报登记信息
//        List<ChbPrdOrgDeclareSub> orgList = prodFacotrInfo002Dto.getOrgList();
//        ChbPrdOrgDeclareSub chbPrdOrgDeclareSub = null;
//        List<ChbPrdOrgDeclareSub> orgListO = prodFacotrInfo002Dto.getOrgListO();
//        // 基础信息list
//        List<ChbPrdBaseinfo> baseListN = prodFacotrInfo002Dto.getBaseListN();
//        List<ChbPrdBaseinfo> baseListO = prodFacotrInfo002Dto.getBaseListO();
//        String key = chbPrdBaseinfo.getProdInsId() + GlobalConstant.UNDER_LINE
//            + (chbPrdBaseinfo.getProdId()==null?"":chbPrdBaseinfo.getProdId())
//            + GlobalConstant.UNDER_LINE + chbPrdBaseinfo.getOrgCode();
//        String orgKey = chbPrdBaseinfo.getProdId() + GlobalConstant.UNDER_LINE
//                        + chbPrdBaseinfo.getOrgCode()+ GlobalConstant.UNDER_LINE ;
//        String registBankCode = "";
//        if (StringUtil.isEmptyList(bankCodeMap.get(chbPrdBaseinfo.getOrgCode()))) {
//            registBankCode = bankCodeMap.get(chbPrdBaseinfo.getOrgCode()).get(
//                0).getRegistBankCode();
//        }
//        // 首先校验申报登记
//        if ("D".equals(chbPrdBaseinfo.getStatus())) {
//            if (StringUtil.isEmptyList(baseMap.get(key))) {
//                baseListO.add(chbPrdBaseinfo);
//                chbPrdBaseinfo.setStatus("D");
//                chbPrdOrgDeclareSub = new ChbPrdOrgDeclareSub();
//                // BeanUtil.copyPropertiesIgnoreNull(chbPrdOrgDeclareSub, chbPrdBaseinfo);
//                chbPrdOrgDeclareSub.setStatus("D");
////                this.setRate(chbPrdBaseinfo, null, null, null);
//                // 首先校验申报登记
//                // 删除维护表处理
//                if (StringUtil.isEmptyList(baseMap.get(key))) {
//                    ChbPrdBaseinfo chbPrdBaseinfoTemp = new ChbPrdBaseinfo();
//                    chbPrdBaseinfoTemp.setAgent(chbPrdBaseinfo.getAgent());
//                    chbPrdBaseinfoTemp.setProdId(chbPrdBaseinfo.getProdId());
//                    chbPrdBaseinfoTemp.setOrgCode(chbPrdBaseinfo.getOrgCode());
//                    chbPrdBaseinfoTemp.setProdInsId(chbPrdBaseinfo.getProdInsId());
//                    chbPrdBaseinfoTemp.setStatus("D");
//                    chbPrdBaseinfoTemp.setUpdateUser("sysBatch");
//                    baseListO.add(chbPrdBaseinfoTemp);
//                }
//                else {
//                    baseListN.add(chbPrdBaseinfo);
//                }
//            }
//        }
//        else {
//            chbPrdOrgDeclareSub = new ChbPrdOrgDeclareSub();
//            BeanUtil.copyPropertiesIgnoreNull(chbPrdOrgDeclareSub, chbPrdBaseinfo);
//            chbPrdOrgDeclareSub.setCreateUser("sysBatch");
//            chbPrdOrgDeclareSub.setCreateTime(DateUtil.now());
//            chbPrdOrgDeclareSub.setUpdateUser("sysBatch");
//            chbPrdOrgDeclareSub.setUpdateTime(DateUtil.now());
//            chbPrdOrgDeclareSub.setIssuePartyId(registBankCode);
//            chbPrdOrgDeclareSub.setProdRegistCode(chbPrdBaseinfo.getRegCode());
//            //发行字段
//            chbPrdOrgDeclareSub.setExpMaxYield(chbPrdBaseinfo.getHighYieldIssue());
//            chbPrdOrgDeclareSub.setExpMinYield(chbPrdBaseinfo.getLowYieldIssue());
//            chbPrdOrgDeclareSub.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//
//            chbPrdOrgDeclareSub.setRegistSeqno(getSequenceIndexData(DataIndexSequenceType.中债登记管理));
//            List<String> list = SubCheckUtil.checkObjData(SubReportClass.机构和私人银行产品发行登记,
//                chbPrdOrgDeclareSub).getAllErrorMsgList();
//            this.setRate(null, null, null, chbPrdOrgDeclareSub);
//            if (StringUtil.isEmptyList(list)) {
//                chbPrdBaseinfo.setStatus(DictCodeList.维护数据状态_待处理.getValue());
//            }
//            else {
//                this.getPrdBaseinfoBySet(chbPrdBaseinfo, result, baseMap.get(key));
//                chbPrdBaseinfo.setStatus(DictCodeList.维护数据状态_有效.getValue());
//                // chbPrdOrgDeclareSub = new ChbPrdOrgDeclareSub();
//                // BeanUtil.copyPropertiesIgnoreNull(chbPrdOrgDeclareSub, chbPrdBaseinfo);
////                chbPrdOrgDeclareSub.setSendStatus(DictCodeList.报送状态_未报送.getValue());
//                List<Date> dateList = new ArrayList<Date>();
//                if (orgMap != null
//                    && (StringUtil.isNotEmpty(
//                        orgMap.get(orgKey + DictCodeList.报送状态_接口报送.getValue()))
//                        || StringUtil.isNotEmpty(
//                            orgMap.get(orgKey + DictCodeList.报送状态_手工报送.getValue())))) {
//                    chbPrdOrgDeclareSub.setStatus(DictCodeList.报送数据状态_修改.getValue());
//                    dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                        DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(), DictCodeList.细分类型_机构.getValue(),
//                        DictCodeList.报送数据状态_修改.getValue(), chbPrdBaseinfo.getRaiseEnddate());
//                    // List<Date> dateList1 = ChbDeadlineUtil.getChbEndDateNoMap(
//                    // DictCodeList.报表名称_个人产品发行登记.getValue(), DictCodeList.细分类型_机构.getValue(),
//                    // type, chbPrdBaseinfo.getRaiseEnddate());
//                    if (StringUtil.isEmptyList(dateList)) {
//                        chbPrdOrgDeclareSub.setChbSubmitDeadline(dateList.get(0));
//                        chbPrdOrgDeclareSub.setSubmitTime(dateList.get(1));
//                    }
//                    if (orgMap != null && StringUtil.isNotEmpty(
//                        orgMap.get(orgKey + DictCodeList.报送状态_未报送.getValue()))) {
//                        chbPrdOrgDeclareSub.setRegistSeqno(
//                            orgMap.get(orgKey + DictCodeList.报送状态_未报送.getValue()).get(
//                                0).getRegistSeqno());
//                        orgListO.add(chbPrdOrgDeclareSub);
//                    }
//                    else {
//                        // chbPrdOrgDeclareSub.setRegistSeqno(
//                        // getSequenceIndexData(DataIndexSequenceType.中债登记管理));
//                        orgList.add(chbPrdOrgDeclareSub);
//                    }
//                }
//                else {
//                    chbPrdOrgDeclareSub.setStatus(DictCodeList.报送数据状态_新建.getValue());
//                    dateList = ChbDeadlineUtil.getChbEndDateNoMap(
//                        DictCodeList.报表名称_机构和私人银行产品发行登记.getValue(), DictCodeList.细分类型_机构.getValue(),
//                        DictCodeList.报送数据状态_新建.getValue(), chbPrdBaseinfo.getRaiseEnddate());
//                    // chbPrdOrgDeclareSub.setRegistSeqno(
//                    // getSequenceIndexData(DataIndexSequenceType.中债登记管理));
//                    if (StringUtil.isEmptyList(dateList)) {
//                        chbPrdOrgDeclareSub.setChbSubmitDeadline(dateList.get(0));
//                        chbPrdOrgDeclareSub.setSubmitTime(dateList.get(1));
//                    }
//                    if(orgMap != null && StringUtil.isNotEmpty(
//                        orgMap.get(orgKey + DictCodeList.报送状态_未报送.getValue()))){
//                        orgListO.add(chbPrdOrgDeclareSub);
//                    }
//                    else{
//                        orgList.add(chbPrdOrgDeclareSub);
//                    }
//                }
//            }
//            if (!StringUtil.isEmptyList(baseMap.get(key))) {
//                baseListO.add(chbPrdBaseinfo);
//            }
//            else {
//                baseListN.add(chbPrdBaseinfo);
//            }
//        }
//        prodFacotrInfo002Dto.setBaseListN(baseListN);
//        prodFacotrInfo002Dto.setBaseListO(baseListO);
//    }
//
//    /**
//     * 根据配置项获取需要更新字段
//     *
//     * @param result 配置数据
//     * @param chbPrdBaseinfo 传入数值
//     * @param chbPrdBaseinfo1 赋值
//     * @throws IllegalAccessException 异常
//     * @throws IllegalArgumentException 异常
//     * @see
//     * @since 1.0
//     */
//    public void dtoToSet(String[] result, ChbPrdBaseinfo chbPrdBaseinfo,
//                         ChbPrdBaseinfo chbPrdBaseinfo1)
//        throws IllegalArgumentException, IllegalAccessException {
//        if (result != null && result.length > 1) {
//            for (int i = 1; i < result.length; i++ ) {
//                Field field = ReflectionUtils.findField(chbPrdBaseinfo.getClass(),
//                    StringUtil.dealString(StringUtil.lowerCase(result[i]), false));
//                field.setAccessible(true);
//                Field field1 = ReflectionUtils.findField(chbPrdBaseinfo1.getClass(),
//                    StringUtil.dealString(StringUtil.lowerCase(result[i]), false));
//                field1.setAccessible(true);
//                field1.set(chbPrdBaseinfo1,
//                    field.get(StringUtil.dealString(StringUtil.lowerCase(result[i]), false)));
//            }
//        }
//    }
//
//    /**
//     * 汇率%换算为小数
//     *
//     * @param chbPrdBaseinfo 产品基本信息
//     * @param chbPrdDeclareSub 个人申购
//     * @param chbPrdIssueSub 个人发行
//     * @param chbPrdOrgDeclareSub 机构发行
//     * @see
//     * @since 1.0
//     */
//    private void setRate(ChbPrdBaseinfo chbPrdBaseinfo, ChbPrdDeclareSub chbPrdDeclareSub,
//                         ChbPrdIssueSub chbPrdIssueSub, ChbPrdOrgDeclareSub chbPrdOrgDeclareSub) {
//        if (chbPrdBaseinfo != null) {
//            if (chbPrdBaseinfo.getExpAvgYield() != null) {
//                chbPrdBaseinfo.setExpAvgYield(chbPrdBaseinfo.getExpAvgYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdBaseinfo.getSaleFeeRate() != null) {
//                chbPrdBaseinfo.setSaleFeeRate(chbPrdBaseinfo.getSaleFeeRate().divide(NUMBER_BAI));
//            }
//            if (chbPrdBaseinfo.getTruFeeRate() != null) {
//                chbPrdBaseinfo.setTruFeeRate(chbPrdBaseinfo.getTruFeeRate().divide(NUMBER_BAI));
//            }
//            if (chbPrdBaseinfo.getLowYield() != null) {
//                chbPrdBaseinfo.setLowYield(chbPrdBaseinfo.getLowYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdBaseinfo.getHighYield() != null) {
//                chbPrdBaseinfo.setHighYield(chbPrdBaseinfo.getHighYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdBaseinfo.getHighYieldIssue() != null) {
//                chbPrdBaseinfo.setHighYieldIssue(
//                    chbPrdBaseinfo.getHighYieldIssue().divide(NUMBER_BAI));
//            }
//            if (chbPrdBaseinfo.getLowYieldIssue() != null) {
//                chbPrdBaseinfo.setLowYieldIssue(
//                    chbPrdBaseinfo.getLowYieldIssue().divide(NUMBER_BAI));
//            }
//        }
//        if (chbPrdDeclareSub != null) {
//            if (chbPrdDeclareSub.getSaleFeeRate() != null) {
//                chbPrdDeclareSub.setSaleFeeRate(
//                    chbPrdDeclareSub.getSaleFeeRate().divide(NUMBER_BAI));
//            }
//            if (chbPrdDeclareSub.getTruFeeRate() != null) {
//                chbPrdDeclareSub.setTruFeeRate(
//                    chbPrdDeclareSub.getTruFeeRate().divide(NUMBER_BAI));
//            }
//            if (chbPrdDeclareSub.getLowYield() != null) {
//                chbPrdDeclareSub.setLowYield(chbPrdDeclareSub.getLowYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdDeclareSub.getHighYield() != null) {
//                chbPrdDeclareSub.setHighYield(chbPrdDeclareSub.getHighYield().divide(NUMBER_BAI));
//            }
//        }
//        if (chbPrdIssueSub != null) {
//            if (chbPrdIssueSub.getExpMinYield() != null) {
//                chbPrdIssueSub.setExpMinYield(chbPrdIssueSub.getExpMinYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdIssueSub.getExpMaxYield() != null) {
//                chbPrdIssueSub.setExpMaxYield(chbPrdIssueSub.getExpMaxYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdIssueSub.getExpAvgYield() != null) {
//                chbPrdIssueSub.setExpAvgYield(chbPrdIssueSub.getExpAvgYield().divide(NUMBER_BAI));
//            }
//        }
//        if (chbPrdOrgDeclareSub != null) {
//            if (chbPrdOrgDeclareSub.getExpMinYield() != null) {
//                chbPrdOrgDeclareSub.setExpMinYield(
//                    chbPrdOrgDeclareSub.getExpMinYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdOrgDeclareSub.getExpMaxYield() != null) {
//                chbPrdOrgDeclareSub.setExpMaxYield(
//                    chbPrdOrgDeclareSub.getExpMaxYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdOrgDeclareSub.getExpAvgYield() != null) {
//                chbPrdOrgDeclareSub.setExpAvgYield(
//                    chbPrdOrgDeclareSub.getExpAvgYield().divide(NUMBER_BAI));
//            }
//            if (chbPrdOrgDeclareSub.getSaleFeeRate() != null) {
//                chbPrdOrgDeclareSub.setSaleFeeRate(
//                    chbPrdOrgDeclareSub.getSaleFeeRate().divide(NUMBER_BAI));
//            }
//            if (chbPrdOrgDeclareSub.getTruFeeRate() != null) {
//                chbPrdOrgDeclareSub.setTruFeeRate(
//                    chbPrdOrgDeclareSub.getTruFeeRate().divide(NUMBER_BAI));
//            }
//        }
//    }
//
//    /**
//     * 根据设置查看需要更新内容
//     *
//     * @param chbPrdBaseinfo 传入信息
//     * @param result 配置数组
//     * @param chbPrdCheck 校验
//     * @throws IllegalAccessException 异常
//     * @throws IllegalArgumentException 异常
//     * @see
//     * @since 1.0
//     */
//    private void getPrdBaseinfoBySet(ChbPrdBaseinfo chbPrdBaseinfo, String[] result,
//                                     List<ChbPrdBaseinfo> chbPrdCheck)
//        throws IllegalArgumentException, IllegalAccessException {
//        if (StringUtil.isNotEmptyList(chbPrdCheck)) {
//            if (DictCodeList.数据来源_EXCEL导入.getValue().equals(chbPrdCheck.get(0).getDataSource())
//                || DictCodeList.数据来源_系统新建.getValue().equals(chbPrdCheck.get(0).getDataSource())) {
//                this.dtoToSet(result, chbPrdBaseinfo, chbPrdCheck.get(0));
//                BeanUtil.copyPropertiesIgnoreNull(chbPrdBaseinfo, chbPrdCheck);
//            }
//        }
//
//    }

    private String subBlank(String str) {
		// TODO Auto-generated method stub
		if(str==null){
			return null;
		}
		String[] strarray = str.split("_");
		if(strarray.length == 0){
			return null;
		}
		String result = strarray[0];
        for (int i = 1; i < strarray.length; i++)
           result = result + (new StringBuilder()).append(Character.toUpperCase(strarray[i].charAt(0))).append(strarray[i].substring(1)).toString();
		return result;
	}
}
