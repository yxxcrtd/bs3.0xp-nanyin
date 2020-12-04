/*
 * 文件名：ValManualVoucher003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dao.ValManualVoucher003Mapper;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.service.BookAccountCommonService;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 手工凭证新建Service
 * 
 * @author weipeng
 * @version 1.0 2018年7月11日
 * @see ValManualVoucher003Service
 * @since 1.0
 */
@Service(value="serviceValManualVoucher003Service")
@Transactional(rollbackFor = Exception.class)
public class ValManualVoucher003Service extends CommonExportService<ValManualVoucher003Dto> implements BaseProcessService<ValManualVoucher003Dto> {

    @Autowired
    private ValManualVoucher003Mapper valManualVoucher003Mapper;

    @Autowired
    private BokBussDetsubMapper bokBussDetsubMapper;

    @Autowired
    private BookAccountCommonService batchComService;

    @Autowired
    private TrdVouchDealMapper trdVouchDealMapper;

    @Autowired
    private SystemToolService systemToolService;

    @Autowired
    private TrdVouchDealDetailMapper trdVouchDealDetailMapper;

    @Autowired
    private BokDetailIdMapper bokDetailIdMapper;

    @Autowired
    private BokDealAccountingMapper bokDealAccountingMapper;

    @Autowired
    private BokDealAccountingDetailMapper bokDealAccountingDetailMapper;

    @Autowired
    private BokBookdetailAllMapper bokBookdetailAllMapper;

    @Autowired
    private BokBooksetMapper bokBooksetMapper;

    @Autowired
    private BokVouchMainMapper bokVouchMainMapper;

    @Autowired
    private BokVouchDetailMapper bokVouchDetailMapper;

    @Autowired
    private BokVouchRelMapper bokVouchRelMapper;

    // @Autowired
    // private FinCashInfoMapper finCashInfoMapper;

    @Autowired
    private FinTradeProductMapper finTradeProductMapper;

    @Autowired
    private BokAcctLayeringInfoMapper bokAcctLayeringInfoMapper;

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;

    public ValManualVoucher001Dto getBySeqno(String seqno) {
        return valManualVoucher003Mapper.getBySeqno(seqno);
    }

    /**
     * 科目号下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public ValManualVoucher003SubjectDto getSubjectNo(String booksetId, String subjectNo,
                                                      String busiId, String finprodType,String ccy) {//modify by dongjun on 20181211
        // 获取模板账套代码
        String booksetIdTmpl = null;
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
        if (bokBookset != null) {
            booksetIdTmpl = bokBookset.getBooksetIdTmpl();
        }
        // 查询科目信息
        ValManualVoucher003SubjectDto subjectdto = valManualVoucher003Mapper.getSubjectNo(
            booksetIdTmpl, subjectNo);
        /**modify by dongjun on 20181211 start **/
        if(subjectdto == null){
            subjectdto = new ValManualVoucher003SubjectDto();
        }
        /**modify by dongjun on 20181211 end **/
        // 查询现金流信息
        // FinCashInfo cashInfo = finCashInfoMapper.selectByPrimaryKey(busiId);
        // if (cashInfo != null) {
        // subjectdto.setBusiId(cashInfo.getCashId());
        // subjectdto.setCashType2(cashInfo.getCashType2());
        // subjectdto.setCashType2Name(
        // systemToolService.getNameFromDataDict(DictCode.现金流二级类型, cashInfo.getCashType2()));
        // }
        // if 画面.业务类型 IN“"债券质押式正回购",“债券质押式逆回购","债券买断式正回购","债券买断式逆回购"”
        if (DictCodeListExt1.金融产品类型_债券买断式正回购.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_债券质押式正回购.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_拆入.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_拆出.getValue().equals(finprodType)
            //|| DictCodeListExt1.金融产品类型_同业借款.getValue().equals(finprodType)
            ) {
            // 科目尾号 = 资产代码对应的期限品种
            FinTradeProductKey key = new FinTradeProductKey();
            key.setFinprodId(busiId);
            //【一体化修改】：begin========================================================================= yangjian 2018/1/4
            //【一体化修改】：针对估值一体化变更【34：交易类金融产品表去除金融产品类型的主键】
            //key.setFinprodType(finprodType);
            //【一体化修改】：end========================================================================= yangjian 2018/1/4
            key.setBranch(0L);
            // 获取交易类标的信息
            FinTradeProduct finTradeProduct = finTradeProductMapper.selectByPrimaryKey(key);
            if (finTradeProduct != null) {
                // 获取对应期限品种传入前台
                subjectdto.setTermType(finTradeProduct.getTermType());
            }
            subjectdto.setTermTypeFlag(DictCodeList.是否_是.getValue());
            subjectdto.setFinprodType(finprodType);
        }
        
        /** modify by dongjun on 2018/12/10 start **/
        List<String> acctNoA03 = new ArrayList<>();
        acctNoA03.add("22330101");
        acctNoA03.add("30030201");
        acctNoA03.add("30030202");
        acctNoA03.add("30030203");
        acctNoA03.add("30030301");
        acctNoA03.add("30030401");
        acctNoA03.add("30030501");
        acctNoA03.add("30030601");
        acctNoA03.add("30030701");
        acctNoA03.add("30030801");
        if(StringUtil.equals("10020101", subjectdto.getSubjectNo()) && ccy != null){
           String acctNo = valManualVoucher003Mapper.getAcctNo(booksetId, DictCodeListExt1.资金账户类型_产品托管户.getValue(),ccy);
           subjectdto.setAcctCode(acctNo);
        }else if (subjectdto.getSubjectNo() != null && ccy != null && acctNoA03.contains(subjectdto.getSubjectNo())){
            String acctNo = valManualVoucher003Mapper.getAcctNo(booksetId, DictCodeListExt1.资金账户类型_待清算款.getValue(),ccy);
            subjectdto.setAcctCode(acctNo);
        }     
        /** modify by dongjun on 2018/12/10 end **/
        return subjectdto;
    }

    /**
     * 科目号下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getSubjectNoPullDw(String booksetId) {
        // 获取模板账套代码
        String booksetIdTmpl = null;
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
        if (bokBookset != null) {
            booksetIdTmpl = bokBookset.getBooksetIdTmpl();
        }
        return valManualVoucher003Mapper.getSubjectNoPullDw(booksetIdTmpl);
    }

    /**
     * 获取资金账户下拉框
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctId(String booksetId, String param) {
        return valManualVoucher003Mapper.getAcctId(booksetId, param);
    }

    /**
     * 获取分支序号下拉框
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBranch(String booksetId, String finprodType, String subjectNo,
                                           String finprodId, String param) {
        String branchType = null;
        String booksetIdTmpl = null;
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
        if (bokBookset != null) {
            booksetIdTmpl = bokBookset.getBooksetIdTmpl();
        }
        // 查询基础科目属性
        BokSubject bokSubject = valManualVoucher003Mapper.getSubjectNo(booksetIdTmpl, subjectNo);
        if(DictCodeListExt1.金融产品类型_债券买断式正回购.getValue().equals(finprodType)){
            finprodType = DictCodeListExt1.金融产品类型_债券.getValue();
        }
        // 基础科目性质=“其他应收款-本金”
        if (DictCodeBaseSubjectNatrue.其他应收款_本金.getValue().equals(
            bokSubject.getBaseSubjectNature())) {
            branchType = DictCodeList.分支类型_应收本金.getValue();
        }
        else if (DictCodeBaseSubjectNatrue.其他应收款_利息.getValue().equals(
            bokSubject.getBaseSubjectNature())) {
            branchType = DictCodeList.分支类型_应收利息.getValue();
        }
        return valManualVoucher003Mapper.getBranch(finprodType, branchType, finprodId);
    }

    /**
     * 获取详情中列表信息
     * 
     * @param bookId 凭证编号
     * @return 详情中列表信息
     * @throws Exception 异常
     */
    public List<ValManualVoucher003AddDto> searchList(String bookId) {
        return valManualVoucher003Mapper.searchList(bookId);
    }

    /**
     * 将数据格式化，为录入表格
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ValManualVoucher003AddDto initTableDto(ValManualVoucher003AddValidatorDto dto) {
        ValManualVoucher003AddDto returndto = new ValManualVoucher003AddDto();
        return returndto;
    }

    /**
     * 获取资产代码下拉列表
     * 
     * @param finprodType 业务类型
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBusiId(String finprodType, String booksetId, String param) {
        if (DictCodeListExt1.金融产品类型_债券.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_存款.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_基金.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_计划类资产.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_同业借款.getValue().equals(finprodType)) {
        	/* return queryForSuggest(() -> {
        		 return valManualVoucher003Mapper.getFinProduct(finprodType, booksetId);
             }, param, null);*/
            
            //多级法人改造 add by hxy start 20200618
            List<SuggestDto> returnList = new ArrayList<SuggestDto>();
            //公用资产
            List<SuggestDto> ListPub = valManualVoucher003Mapper.getFinProductPub(finprodType, booksetId);
            returnList.addAll(ListPub);
            //非公用资产
            List<SuggestDto> ListPrivate = valManualVoucher003Mapper.getFinProduct(finprodType, booksetId);
            returnList.addAll(ListPrivate);
            return queryForSuggest(() -> {
            	return returnList;
            }, param, null);
            //多级法人改造 add by hxy end 20200618
        }
        else if (DictCodeListExt1.金融产品类型_拆入.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_拆出.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_债券质押式正回购.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_债券买断式正回购.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_债券借贷融入.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_债券借贷融出.getValue().equals(finprodType)
                 || DictCodeListExt3.金融产品类型_期权.getValue().equals(finprodType)
        ) {
           /* return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getFinTradeProduct(finprodType, booksetId);
            }, param, null);*/
            
        	 //多级法人改造 add by hxy start 20200618
            List<SuggestDto> returnList = new ArrayList<SuggestDto>();
            //公用资产
            List<SuggestDto> ListPub = valManualVoucher003Mapper.getFinTradeProductPub(finprodType, booksetId);
            returnList.addAll(ListPub);
            //非公用资产
            List<SuggestDto> ListPrivate = valManualVoucher003Mapper.getFinTradeProduct(finprodType, booksetId);
            returnList.addAll(ListPrivate);
            return queryForSuggest(() -> {
            	return returnList;
            }, param, null);
            //多级法人改造 add by hxy end 20200618
            
            
            
        }
        else if (DictCodeListExt1.金融产品类型_融入债券.getValue().equals(finprodType)) {
           /* return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getFinProduct(DictCodeListExt1.金融产品类型_债券.getValue(),
                    booksetId);
            }, param, null);*/
            
            //多级法人改造 add by hxy start 20200618
        	return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getFinProductPub(DictCodeListExt1.金融产品类型_债券.getValue(),
                    booksetId);
            }, param, null);
            //多级法人改造 add by hxy end 20200618
            
            
        }
        else if (DictCodeListExt1.金融产品类型_税额.getValue().equals(finprodType)) {

            // 根据【账套代码】获取对应的【核算主体代码】【核算主体类别】，获取对应的【核算主体类别信息】作为【资产代码下拉列表】
            return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getAcctSubjectInfoByBooksetId(booksetId);
            }, param, null);

        }
        else if (DictCodeListExt1.金融产品类型_固定管理费.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_浮动管理费.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_托管费.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_销售费.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_代销费.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_外包服务费.getValue().equals(finprodType)
                 || DictCodeListExt1.金融产品类型_交易费用.getValue().equals(finprodType)) {

            // return queryForSuggest(() -> {
            // return this.getCashInfoName(valManualVoucher003Mapper.getFinCashInfo(finprodType,
            // booksetId));
            // }, param, null);

            // 调用方法：获取核算标的信息（ 账套代码）
            return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getAcctLayeringInfo(booksetId, null);
            }, param, null);

        }
        else if (DictCodeListExt1.金融产品类型_理财产品.getValue().equals(finprodType)) {
            // 调用方法：获取核算标的信息（ 账套代码，金融产品类型）
            List<String> listFinprodType = new ArrayList<String>();
            listFinprodType.add(DictCodeListExt1.金融产品类型_理财产品.getValue());
            listFinprodType.add(DictCodeListExt1.金融产品类型_理财子产品.getValue());
            return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getAcctLayeringInfo(booksetId, listFinprodType);
            }, param, null);
        }
        if (DictCodeListExt1.金融产品类型_投资组合.getValue().equals(finprodType)) {
            List<String> listFinprodType = new ArrayList<String>();
            listFinprodType.add(finprodType);
            return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getAcctLayeringInfo(booksetId, listFinprodType);
            }, param, null);
        }
        else if (DictCodeListExt1.金融产品类型_浮动管理费.getValue().equals(finprodType)) {
            BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
            // if 核算方式=“收益型”
            if (DictCodeList.收益类型_收益型.getValue().equals(bokBookset.getAcctMode())) {
                /*return queryForSuggest(() -> {
                    return valManualVoucher003Mapper.getFinProduct(null, booksetId);
                }, param, null);*/
            	  //多级法人改造 add by hxy start 20200618
                List<SuggestDto> returnList = new ArrayList<SuggestDto>();
                //公用资产
                List<SuggestDto> ListPub = valManualVoucher003Mapper.getFinProductPub(null, booksetId);
                returnList.addAll(ListPub);
                //非公用资产
                List<SuggestDto> ListPrivate = valManualVoucher003Mapper.getFinProduct(null, booksetId);
                returnList.addAll(ListPrivate);
                return queryForSuggest(() -> {
                	return returnList;
                }, param, null);
                //多级法人改造 add by hxy end 20200618
                
            }
            else {
                return queryForSuggest(() -> {
                    return valManualVoucher003Mapper.getAcctLayeringInfo(booksetId, null);
                }, param, null);
            }
        }
        else if (DictCodeListExt1.金融产品类型_其他.getValue().equals(finprodType)) {
            List<SuggestDto> productList = new ArrayList<SuggestDto>();
            //List<SuggestDto> finproduct = valManualVoucher003Mapper.getFinProduct(null, booksetId);
            //多级法人改造 add by hxy start 20200618
            List<SuggestDto> finproduct = new ArrayList<SuggestDto>();
            //公用资产
            List<SuggestDto> ListPub =  valManualVoucher003Mapper.getFinProductPub(null, booksetId);
            finproduct.addAll(ListPub);
            //非公用资产
            List<SuggestDto> ListPrivate =  valManualVoucher003Mapper.getFinProduct(null, booksetId);
            finproduct.addAll(ListPrivate);
            //多级法人改造 add by hxy end 20200618
            if (CollectionUtils.isNotEmpty(finproduct)) {
                productList.addAll(finproduct);
            }
            
            
            /*List<SuggestDto> fintradproduct = valManualVoucher003Mapper.getFinTradeProduct(null,
                booksetId);*/
            //多级法人改造 add by hxy start 20200618
            List<SuggestDto> fintradproduct = new ArrayList<SuggestDto>();
            //公用资产
            List<SuggestDto> ListtradePub = valManualVoucher003Mapper.getFinTradeProductPub(null,booksetId);
            fintradproduct.addAll(ListtradePub);
            //非公用资产
            List<SuggestDto> ListtradePrivate = valManualVoucher003Mapper.getFinTradeProduct(null,booksetId);
            fintradproduct.addAll(ListtradePrivate);
            //多级法人改造 add by hxy end 20200618
            
            if (CollectionUtils.isNotEmpty(fintradproduct)) {
                productList.addAll(fintradproduct);
            }
            // List<SuggestDto> fincashinfo =
            // this.getCashInfoName(valManualVoucher003Mapper.getFinCashInfo(null, booksetId));
            // if (CollectionUtils.isNotEmpty(fincashinfo)) {
            // productList.addAll(fincashinfo);
            // }
            List<SuggestDto> capacctinfo = valManualVoucher003Mapper.getBooksetCapacctInfo(null,
                booksetId);
            if (CollectionUtils.isNotEmpty(capacctinfo)) {
                productList.addAll(capacctinfo);
            }
            return queryForSuggest(() -> {
                return productList;
            }, param, null);
        }
        // 1.26 业务类型 = “资金账户”
        else if(DictCodeListExt1.金融产品类型_资金账户.getValue().equals(finprodType)){
            return queryForSuggest(() -> {
                return valManualVoucher003Mapper.getBooksetCapacctInfo(finprodType,
                    booksetId);
            }, param, null);
        }
        /** add by yanya on 20200106 增加通道费手工凭证处理 **/
        else if(DictCodeListExt1.一次性费用类型_通道费.getValue().equals(finprodType)) {
            //1） 查询账套核算主体分类属性值
            //定义 变量.账套信息DTO =	BokBooksetMapper.selectByPrimaryKey(画面选择.账套代码)
            BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
            //2) 资产代码、业务明细类型查询参数
            //IF  变量.账套信息DTO == 数据字典：金融产品类型.通道
            if(bokBookset != null && DictCodeListExt1.金融产品类型_通道.getValue().equals(bokBookset.getAcctSubjectType())) {
                //调用初始化的1.2
                //参数.账套代码 = 画面选择.账套代码
                return queryForSuggest(() -> {
                    return valManualVoucher003Mapper.getAcctSubjectInfoByBooksetId(booksetId);
                }, param, null);
            }
            else {
                //调用初始化的2.1
                return queryForSuggest(() -> {
                    return valManualVoucher003Mapper.getFinProduct(DictCodeListExt1.金融产品类型_通道.getValue(), booksetId);
                }, param, null);
            }

        }
        // else if (DictCodeListExt1.金融产品类型_交易费用.getValue().equals(finprodType)) {
        // List<DataDictItem> feeTypeList = (List<DataDictItem>)
        // CacheUtil.getDictCode(DictCode.一次性费用类型.getValue())
        // .getItems();
        // List<SuggestDto> productList = new ArrayList<SuggestDto>();
        // if (CollectionUtils.isNotEmpty(feeTypeList)) {
        // feeTypeList.forEach(x->{
        // SuggestDto dto = new SuggestDto();
        // dto.setCode(x.getItemCode());
        // dto.setName(x.getItemValue());
        // dto.setShortName(x.getItemValue());
        // dto.setHidCode(x.getItemValue());
        // productList.add(dto);
        // });
        // }
        // return queryForSuggest(() -> {
        // return productList;
        // }, param, null);
        // }

        return queryForSuggest(() -> {
            return new ArrayList<SuggestDto>();
        }, param, null);
    }

//    /**
//     * 匹配数据字典，取得费用类金融产品下拉框显示
//     *
//     * @param fincashinfo
//     * @return
//     * @see
//     * @since 1.0
//     */
    @SuppressWarnings("unused")
//    private List<SuggestDto> getCashInfoName(List<SuggestDto> fincashinfo) {
//        if (CollectionUtils.isEmpty(fincashinfo)) {
//            return fincashinfo;
//        }
//        // 设置下拉框显示
//        Map<String, String> itemValueMap = systemToolService.getDataDictItems(DictCode.现金流二级类型);
//        fincashinfo.forEach(item -> {
//            item.setName(itemValueMap.get(item.getName()));
//            item.setShortName(itemValueMap.get(item.getName()));
//        });
//        return fincashinfo;
//    }

    public int deleteVouch(ValManualVoucher001Dto para) {
    	int count=0;
    	String booksetId =para.getBooksetId(); 
    	String vouchNum=para.getBookId(); 
    	String tradeId=para.getTradeId(); 
    	
    	count+=trdVouchDealMapper.deleteByPrimaryKey(tradeId);
    	count+=valManualVoucher003Mapper.deleteTrdVouchDealDetail(tradeId, booksetId);
        BokDealAccountingDetailKey bokDealAccountingDetailKey = new BokDealAccountingDetailKey();
        bokDealAccountingDetailKey.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        bokDealAccountingDetailKey.setBooksetId(booksetId);
        bokDealAccountingDetailKey.setBusiNo(tradeId);
        count+=bokDealAccountingDetailMapper.deleteByPrimaryKey(bokDealAccountingDetailKey);
        BokDealAccountingKey bokDealAccountingKey = new BokDealAccountingKey();
        BeanUtil.copyPropertiesIgnoreNull(bokDealAccountingKey, bokDealAccountingDetailKey);
        count+=bokDealAccountingMapper.deleteByPrimaryKey(bokDealAccountingKey);
        count+=valManualVoucher003Mapper.deleteBokBookdetailAll(tradeId, booksetId);
        count+=bokVouchMainMapper.deleteByPrimaryKey(vouchNum);
        count+=valManualVoucher003Mapper.deleteBokVouchDetail(tradeId, vouchNum);
        count+=valManualVoucher003Mapper.deleteBokVouchRel(vouchNum);
        return count;
    }

    /**
     * 执行提交按钮处理
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public void doSubmit(ValManualVoucher003Dto dto) {

        // 手工凭证交易登录
        TrdVouchDeal deal = this.insertTradVouchDeal(dto);
        // 入账信息表
        this.insertBokDealAccounting(deal);
        // 凭证主表
        this.insertBokVouchMain(deal, dto.getBranch());
        int i = 0;
        // 查询手工凭证批处理信息
        SysBatchTask taskinfo = valManualVoucher003Mapper.getTaskInfo("manualVoucherTask");
        for (ValManualVoucher003AddDto item : dto.getListAddDto()) {
            // 账务明细代码设置
            this.setBokDetailId(deal, item, dto);
            // 手工凭证交易明细登录
            this.insertTrdVouchDealDetail(deal, item);
            // 账务明细代码表登录
            // 入账账务明细表
            this.insertBokDetailId(deal, item);
            this.insertBokDealAccountingDetail(deal, item, dto.getFinprodType());
            // 账务明细（历史表）
            BokBookdetailAll bookdetailAll = this.insertbokBookdetailAll(deal, item, taskinfo);
            // 业务明细科目
            this.insertBokBussDetsub(deal, item);
            // 凭证子表
            BokVouchDetail vouchDetail = this.insertBokVouchDetail(deal, item, ++i);
            // 凭证账务明细关联表
            this.insertBokVouchRel(bookdetailAll, vouchDetail);
        }
    }

    /**
     * 手工凭证交易表登录
     *
     * @param dto
     * @see
     * @since 1.0
     */
    private TrdVouchDeal insertTradVouchDeal(ValManualVoucher003Dto dto) {
        TrdVouchDeal deal = new TrdVouchDeal();
        // 交易编号
        // deal.setTradeId(dBUtil.getSequenceIndexData(DataIndexSequenceType.手工凭证交易编号));

        // 调用共通生成交易编号(在提交流程的ValManualVoucher003.doSave方法中已经生成交易编号，故直接赋值)
        // deal.setTradeId(interfCommonService.getTradeid("T28", dto.getBookDate(), "A"));
        deal.setTradeId(dto.getSeqno());
        
        //记账顺序  add by chufengjie 20190525
        deal.setAccountOrder(dto.getAccountOrder());
        
        // 账套代码
        deal.setBooksetId(dto.getBooksetId());
        // 分支序号
        deal.setBranch(dto.getBranch());
        // 分录类型
        deal.setRecordType(dto.getRecordType());
        // 记账日期
        deal.setBookDate(dto.getBookDate());
        // 冲销标识
        deal.setOffsetFlag(dto.getOffsetFlag());
        // 业务明细代码---若校验时已经查过，则直接赋值，否则去查核算主体信息
        if (StringUtil.isNotEmpty(dto.getBusiDetailId())) {
            deal.setBusiId(dto.getBusiDetailId());
        }
        else {
            deal.setBusiId(this.getBusiDetailId(dto.getBooksetId(), dto.getBusiDetailType(),
                dto.getBusiId(), dto.getFinprodType()));
        }
        // 业务明细类型 = 画面隐藏的业务明细代码
//        deal.setBusiDetailType(dto.getFinprodType());
        deal.setBusiDetailType(dto.getBusiDetailType());
        // 业务类型
        deal.setBusiType(DictCodeList.业务类型_手工凭证交易.getValue());
        // 凭证编号
        // BokBookdetailCurrent entity = new BokBookdetailCurrent();
        // // TODO:
        // entity.setTableId("0");
        // entity.setTradeId(deal.getTradeId());
        // entity.setOffsetFlag(deal.getOffsetFlag());
        // entity.setBooksetId(deal.getBooksetId());
        deal.setBookId(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
        // 凭证类型
        deal.setBookType(DictCodeList.凭证类型_手工.getValue());
        // 摘要
        deal.setBookRemark(dto.getBookRemark());
        // 原始交易编号
        deal.setpTradeId(dto.getCashId());
        // 投资目的
        // 基础科目性质查询-摊余成本对应科目
        List<String> ac_kmcx = valManualVoucher003Mapper.getValSubjectNatureList(
            DictCodeListExt1.分组代码_摊余成本对应科目.getValue());
        // 基础科目性质查询-公允价值对应科目
        List<String> fvtpl_kmcx = valManualVoucher003Mapper.getValSubjectNatureList(
            DictCodeListExt1.分组代码_公允价值对应科目.getValue());
        // 基础科目性质查询-综合收益对应科目
        List<String> fvoci_kmcx = valManualVoucher003Mapper.getValSubjectNatureList(
            DictCodeListExt1.分组代码_综合收益对应科目.getValue());
        String invAim = null;
        // if 画面.业务类型 = “债券”
        if (DictCodeListExt1.金融产品类型_债券.getValue().equals(dto.getFinprodType())) {
            for (ValManualVoucher003AddDto item : dto.getListAddDto()) {
                if (ac_kmcx.contains(item.getBaseSubjectNature())) {
                    invAim = DictCodeListExt1.投资目的_摊余成本类.getValue();
                    break;
                }
                else if (fvtpl_kmcx.contains(item.getBaseSubjectNature())) {
                    invAim = DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue();
                    break;
                }
                else if (fvoci_kmcx.contains(item.getBaseSubjectNature())) {
                    invAim = DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue();
                    break;
                }
                else{
                    invAim = dto.getInvAim();
                }
            }
        }
        // else if 画面.业务类型=“融入债券”
        else if(DictCodeListExt1.金融产品类型_融入债券.getValue().equals(dto.getFinprodType())){
            // 投资目的=“卖空”
            invAim = DictCodeListExt1.投资目的_卖空.getValue();
        }
        deal.setInvAim(invAim);
        for (ValManualVoucher003AddDto item : dto.getListAddDto()) {
            item.setInvAim(invAim);
        }
        deal.setFinprodType(dto.getFinprodType());
        // 登录
        trdVouchDealMapper.insert(deal);
        return deal;
    }

    /**
     * 手工凭证交易明细登录
     * 
     * @see
     * @since 1.0
     */
    private void insertTrdVouchDealDetail(TrdVouchDeal deal, ValManualVoucher003AddDto detailDto) {
        TrdVouchDealDetail detail = new TrdVouchDealDetail();
        // 流水
        detail.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
        // 交易编号
        detail.setTradeId(deal.getTradeId());
        // 账套代码
        detail.setBooksetId(deal.getBooksetId());
        // 分支序号
        detail.setBranch(detailDto.getBranch());
        // 科目号
        detail.setSubjectNo(detailDto.getSubjectNo());
        // 科目名称
        detail.setSubjectName(detailDto.getSubjectName());
        // 科目尾号
        detail.setDetailSubjectNoTail(detailDto.getDetailSubjectNo());
        // 尾号名称
        detail.setDetailSubnameTail(detailDto.getDetailSubjectName());
        // 金额
        String cdFlag = DictCodeList.借贷方向_贷.getValue();
        if (detailDto.getcAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getcAmt()) == 0) {
            cdFlag = DictCodeList.借贷方向_借.getValue();
        }
        detail.setoHappenAmt(cdFlag.equals(
            DictCodeList.借贷方向_借.getValue()) ? detailDto.getdAmt() : detailDto.getcAmt());
        // 借贷方向
        detail.setCdFlag(cdFlag);
        // 币种
        detail.setCcy(detailDto.getCcy());
        // 数量
        detail.setHappenNumber(detailDto.getHappenNumber());
        // 账务明细代码
        detail.setBokDetailId(detailDto.getBokDetailId());
        // 业务明细代码
        detail.setBusiId(detailDto.getBusiDetailId());
        // 业务明细类型
        detail.setBusiDetailType(detailDto.getBusiDetailType());
        // 投资目的
        detail.setInvAim(deal.getInvAim());
        // 证券托管户代码不赋值 delete by QuJianZhong 20181212 start
        // 证券托管户代码
        // detail.setSecManageAcctId(detailDto.getAcctCode());
        // 证券托管户代码不赋值 delete by QuJianZhong 20181212 end
        //本币种
        BokBookset bookset = bokBooksetMapper.selectByPrimaryKey(deal.getBooksetId());
        if (bookset != null) {
        	detail.setBCcy(bookset.getStandardCcy());

            //本币金额
            if (detailDto.getCcy().equals(bookset.getStandardCcy())){
                if (detailDto.getcAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getcAmt()) == 0) {
                    detail.setbHappenAmt(detailDto.getdAmt());
                }else{
                    detail.setbHappenAmt(detailDto.getcAmt());
                }
            }else{
                if(detailDto.getBcHappenAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getBcHappenAmt()) == 0){
                    detail.setbHappenAmt(detailDto.getBdHappenAmt());
                }else{
                    detail.setbHappenAmt(detailDto.getBcHappenAmt());
                }

            }
        }

        // 登录
        trdVouchDealDetailMapper.insert(detail);
    }

    /**
     * 账务明细代码登录
     *
     * @param deal
     * @param detailDto
     * @see
     * @since 1.0
     */
    private void insertBokDetailId(TrdVouchDeal deal, ValManualVoucher003AddDto detailDto) {
        // 判断记录是否存在
        BokDetailIdKey key = new BokDetailIdKey();
        key.setBooksetId(deal.getBooksetId());
        key.setBokDetailId(detailDto.getBokDetailId());
        BokDetailId bokDetailId = bokDetailIdMapper.selectByPrimaryKey(key);
        if (bokDetailId != null) {
            return;
        }
        bokDetailId = new BokDetailId();
        // 账套代码
        bokDetailId.setBooksetId(deal.getBooksetId());
        // 账务明细代码
        bokDetailId.setBokDetailId(detailDto.getBokDetailId());
        // 业务明细代码
        bokDetailId.setBusiId(detailDto.getBusiDetailId());
        // 业务明细类型
        bokDetailId.setBusiDetailType(detailDto.getBusiDetailType());
        // 投资目的
        bokDetailId.setInvAim(deal.getInvAim());
        // 证券管理户代码
        // 交易编号
        // 四级科目尾号
        bokDetailId.setDetailSubjectNoTail(detailDto.getDetailSubjectNo());
        // 四级科目尾号名称
        bokDetailId.setDetailSubnameTail(detailDto.getDetailSubjectName());
        // 分支序号
        bokDetailId.setBranch(detailDto.getBranch());
        //币种
        bokDetailId.setCcy(detailDto.getCcy());
        // 登录
        bokDetailIdMapper.insert(bokDetailId);

    }

    /**
     * 入账信息表登录
     * 
     * @see
     * @since 1.0
     */
    private void insertBokDealAccounting(TrdVouchDeal deal) {
        BokDealAccounting accounting = new BokDealAccounting();
        // 账套代码
        accounting.setBooksetId(deal.getBooksetId());
        // 业务编号
        accounting.setBusiNo(deal.getTradeId());
        // 业务表
        accounting.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 业务类型
        // accounting.setBusiType(DictCodeList.业务类型_手工凭证交易.getValue());
        // 录入日
        // BokBooksetDate valBokBooksetDate =
        // bokBooksetDateMapper.selectByPrimaryKey(deal.getBooksetId());
        // if (valBokBooksetDate != null) {
        // accounting.setBooksetDate(valBokBooksetDate.getBooksetDate());
        // }
        accounting.setBooksetDate(deal.getBookDate());
        // 入账日
        accounting.setBookDate(deal.getBookDate());
        // 实际发生日
        accounting.setHappenDate(deal.getBookDate());
        // 账务明细代码
        // 是否已入账
        accounting.setIsAccounted(DictCodeList.是否_否.getValue());
        // 入账凭证编号
        accounting.setBookId(deal.getBookId());
        accounting.setInvAim(deal.getInvAim());
        // 登录
        bokDealAccountingMapper.insert(accounting);
    }

    /**
     * 入账账务明细表登录
     *
     * @param deal
     * @param detailDto
     * @see
     * @since 1.0
     */
    private void insertBokDealAccountingDetail(TrdVouchDeal deal,
                                               ValManualVoucher003AddDto detailDto,
                                               String finprodType) {
        BokDealAccountingDetailKey key = new BokDealAccountingDetailKey();
        key.setBooksetId(deal.getBooksetId());
        key.setBusiNo(deal.getTradeId());
        key.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        BokDealAccountingDetail accountingDetail = null;
        accountingDetail = bokDealAccountingDetailMapper.selectByPrimaryKey(key);
        if (accountingDetail != null) {
            return;
        }
        accountingDetail = new BokDealAccountingDetail();
        // 账套代码
        accountingDetail.setBooksetId(deal.getBooksetId());
        // 业务编号
        accountingDetail.setBusiNo(deal.getTradeId());
        // 业务表
        accountingDetail.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 业务类型
        accountingDetail.setBusiType(DictCodeList.业务类型_手工凭证交易.getValue());
        // 账务明细代码
        accountingDetail.setBokDetailId(detailDto.getBokDetailId());
        // 登录
        bokDealAccountingDetailMapper.insert(accountingDetail);
    }

    /**
     * 账务明细（历史表）登录
     *
     * @param deal
     * @param detailDto
     * @see
     * @since 1.0
     */
    private BokBookdetailAll insertbokBookdetailAll(TrdVouchDeal deal,
                                                    ValManualVoucher003AddDto detailDto,
                                                    SysBatchTask taskinfo) {
        BokBookdetailAll detaiall = new BokBookdetailAll();
        // 流水
        detaiall.setSeqno(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
        // 账套代码
        detaiall.setBooksetId(deal.getBooksetId());
        // TODO:场景代码
        detaiall.setTableId("0");
        // 交易编号
        detaiall.setTradeId(deal.getTradeId());
        // 业务明细代码
        detaiall.setBusiId(deal.getBusiId());
        if (taskinfo != null) {
            // 任务代码
            detaiall.setTaskId(taskinfo.getTaskId());
            // 任务优先级
            detaiall.setTaskPri(taskinfo.getTaskPri());
        }
        // 发生日期
        detaiall.setHappenDate(deal.getBookDate());
        // 入账日期
        detaiall.setBookDate(deal.getBookDate());
        // 账套日期
        // BokBooksetDate valBokBooksetDate =
        // bokBooksetDateMapper.selectByPrimaryKey(deal.getBooksetId());
        // if (valBokBooksetDate != null) {
        // detaiall.setBooksetDate(valBokBooksetDate.getBooksetDate());
        // }
        detaiall.setBooksetDate(deal.getBookDate());
        // 在途日期
        // 基础科目性质
        detaiall.setBaseSubjectNature(detailDto.getBaseSubjectNature());
        // 账务明细代码
        detaiall.setBokDetailId(detailDto.getBokDetailId());
        // 分支序号
        detaiall.setBranch(detailDto.getBranch());
        // 科目号
        detaiall.setSubjectNo(detailDto.getSubjectNo());
        // 四级科目号
        if (StringUtil.isNotEmpty(detailDto.getDetailSubjectNo())) {
            detaiall.setDetailSubjectNo(detailDto.getSubjectNo() + detailDto.getDetailSubjectNo());
        }
        // 借贷方向
        String cdFlag = DictCodeList.借贷方向_贷.getValue();
        if (detailDto.getcAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getcAmt()) == 0) {
            cdFlag = DictCodeList.借贷方向_借.getValue();
        }
        detaiall.setCdFlag(cdFlag);
        // 原币
        detaiall.setoCcy(detailDto.getCcy());
        // 原币发生额
        detaiall.setoHappenAmt(cdFlag.equals(
            DictCodeList.借贷方向_借.getValue()) ? detailDto.getdAmt() : detailDto.getcAmt());
        // 本位币
        BokBookset bookset = bokBooksetMapper.selectByPrimaryKey(deal.getBooksetId());
        if (bookset != null) {
            detaiall.setbCcy(bookset.getStandardCcy());

            // 本位币发生额
       /* detaiall.setbHappenAmt(cdFlag.equals(
            DictCodeList.借贷方向_借.getValue()) ? detailDto.getdAmt() : detailDto.getcAmt());*/
            if (detailDto.getCcy().equals(bookset.getStandardCcy())) {
                if (detailDto.getcAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getcAmt()) == 0) {
                    detaiall.setbHappenAmt(detailDto.getdAmt());
                } else {
                    detaiall.setbHappenAmt(detailDto.getcAmt());
                }
            } else {
                if (detailDto.getBcHappenAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getBcHappenAmt()) == 0) {
                    detaiall.setbHappenAmt(detailDto.getBdHappenAmt());
                } else {
                    detaiall.setbHappenAmt(detailDto.getBcHappenAmt());
                }
            }
        }

		// 发生数量
        detaiall.setHappenNumber(detailDto.getHappenNumber());
        // 结息日
        // 凭证编号
        detaiall.setBookId(deal.getBookId());
        // 凭证说明
        detaiall.setBookRemark(deal.getBookRemark());
        // 冲正标识
        detaiall.setOffsetFlag(deal.getOffsetFlag());
        // 冲正日期
        // 反向流水号
        // 凭证类型
        detaiall.setBookType(DictCodeList.凭证类型_手工.getValue());
        // 处理序列
        detaiall.setBookOrder(new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.处理序列)));
        // 余额方向（发生日）
        // 原币余额（发生日）
        // 本位币余额（发生日）
        // 数量余额（发生日）
        // 余额方向（入账日）
        // 原币余额（入账日）
        // 本位币余额（入账日）
        // 数量余额（入账日）
        // 换汇日期
        // 折算汇率
        // 0金额是否处理  <- 数据字典：是否.是
        detaiall.setIsexeZeroAmt(DictCodeList.是否_是.getValue());
        bokBookdetailAllMapper.insert(detaiall);
        return detaiall;
    }

    /**
     * 业务明细科目登录
     * 
     * @see
     * @since 1.0
     */
    private void insertBokBussDetsub(TrdVouchDeal deal, ValManualVoucher003AddDto detailDto) {
        // 如果四级科目尾号为空，不登录
        if (StringUtils.isEmpty(detailDto.getDetailSubjectNo())) {
            return;
        }
        BokBussDetsubKey key = new BokBussDetsubKey();
        key.setBooksetId(deal.getBooksetId());
        String detailSubjectNo = detailDto.getSubjectNo()
                                 + (detailDto.getDetailSubjectNo() == null ? "" : detailDto.getDetailSubjectNo());
        key.setDetailSubjectNo(detailSubjectNo);
        // 查询是否有过四级科目号和账套对应的记录
        BokBussDetsub valBokBussDetsub = bokBussDetsubMapper.selectByPrimaryKey(key);
        if (valBokBussDetsub != null) {
            return;
        }
        valBokBussDetsub = new BokBussDetsub();
        // 账套代码
        valBokBussDetsub.setBooksetId(deal.getBooksetId());
        // 四级科目号
        valBokBussDetsub.setDetailSubjectNo(detailSubjectNo);
        // 四级科目名称
        String detailSubjectName = detailDto.getSubjectName()
                                   + (detailDto.getDetailSubjectName() == null ? "" : detailDto.getDetailSubjectName());
        valBokBussDetsub.setDetailSubjectName(detailSubjectName);
        // 四级科目号尾号
        valBokBussDetsub.setDetailSubjectNoTail(detailDto.getDetailSubjectNo());
        // 危害名称
        valBokBussDetsub.setDetailSubnameTail(detailDto.getDetailSubjectName());
        // 上级科目号
        valBokBussDetsub.setFsubjectId(detailDto.getSubjectNo());
        // 登录
        bokBussDetsubMapper.insert(valBokBussDetsub);
    }

    /**
     * 凭证主表登录
     * 
     * @param deal
     * @see
     * @since 1.0
     */
    private void insertBokVouchMain(TrdVouchDeal deal, Long branch) {
        String bookDateStr = DateUtil.format(deal.getBookDate());
        String yearStr = DateUtil.getYearStr(bookDateStr);
        String monthStr = String.valueOf(DateUtil.getMonth(bookDateStr));
        int maxBookAccountNum = Integer.valueOf(
            batchComService.getMaxBookAccountNum(yearStr, monthStr, deal.getBooksetId()));
        int num = 8;
        maxBookAccountNum = maxBookAccountNum + 1;
        // boolean updateflag = false;
        // if (maxBookAccountNum > 999999) {
        // num = 8;
        // updateflag = true;
        // }
        BokVouchMain vouchmain = new BokVouchMain();
        // 凭证编号
        vouchmain.setVouchNum(deal.getBookId());
        // 业务凭证编号
        String padBookAccountNum = batchComService.padBookAccountNum(
            String.valueOf(maxBookAccountNum), num);
        String busVouchnum = DateUtil.format(deal.getBookDate(), "yyyyMMdd") + padBookAccountNum;
        vouchmain.setBusVouchnum(busVouchnum);
        // 账套代码
        vouchmain.setBooksetId(deal.getBooksetId());
        // 交易编号
        vouchmain.setTradeId(deal.getTradeId());
        // 入账日期
        vouchmain.setBookDate(deal.getBookDate());
        // 合并场景代码
        vouchmain.setComTableId("0");
        // 凭证说明
        vouchmain.setVouchRemark(deal.getBookRemark());
        // 冲正标识
        vouchmain.setOffsetFlag(deal.getOffsetFlag());
        // 年份
        vouchmain.setVouchYear(Long.valueOf(yearStr));
        // 月份
        vouchmain.setVouchMonth(Long.valueOf(monthStr));
        // 编号位数
        vouchmain.setNum((long)num);
        // 凭证类型
        vouchmain.setBookType(DictCodeList.凭证类型_手工.getValue());
        // 登录
        // if (updateflag) {
        // batchComService.updateHistory(yearStr, monthStr);
        // }
        bokVouchMainMapper.insert(vouchmain);
    }

    /**
     * 凭证子表登录
     * 
     * @param deal
     * @param detailDto
     * @see
     * @since 1.0
     */
    private BokVouchDetail insertBokVouchDetail(TrdVouchDeal deal,
                                                ValManualVoucher003AddDto detailDto,
                                                int busVouchsubnum) {
        BokVouchDetail vouchdetail = new BokVouchDetail();
        // 凭证子表编号
        vouchdetail.setVouchSubnum(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
        // 凭证编号
        vouchdetail.setVouchNum(deal.getBookId());
        // 业务凭证子表编号
        vouchdetail.setBusVouchsubnum(String.valueOf(busVouchsubnum));
        // 发生日期
        vouchdetail.setHappenDate(deal.getBookDate());
        // 场景代码
        vouchdetail.setTableId("0");
        // 借贷方向
        String cdFlag = DictCodeList.借贷方向_贷.getValue();
        if (detailDto.getcAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getcAmt()) == 0) {
            cdFlag = DictCodeList.借贷方向_借.getValue();
        }
        vouchdetail.setCdFlag(cdFlag);
        // 科目号
        vouchdetail.setSubjectNo(detailDto.getSubjectNo());
        // 发生额
        vouchdetail.setHappenAmt(cdFlag.equals(
            DictCodeList.借贷方向_借.getValue()) ? detailDto.getdAmt() : detailDto.getcAmt());
        // 发生数量
        vouchdetail.setHappenNumber(detailDto.getHappenNumber());
        // 业务明细代码
        vouchdetail.setBusiId(detailDto.getBusiDetailId());
        // 币种
        vouchdetail.setCcy(detailDto.getCcy());
        // 交易编号
        vouchdetail.setTradeId(deal.getTradeId());
        // 四级科目号
        if (StringUtil.isNotEmpty(detailDto.getDetailSubjectNo())) {
            vouchdetail.setDetailSubjectNo(
                detailDto.getSubjectNo() + detailDto.getDetailSubjectNo());
        }
        // 凭证类型
        vouchdetail.setBookType(DictCodeList.凭证类型_手工.getValue());
        //本币种
        BokBookset bookset = bokBooksetMapper.selectByPrimaryKey(deal.getBooksetId());
        if (bookset != null) {
        	vouchdetail.setbCcy(bookset.getStandardCcy());
            //本币金额
            if (detailDto.getCcy().equals(bookset.getStandardCcy())){
                if (detailDto.getcAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getcAmt()) == 0) {
                    vouchdetail.setbHappenAmt(detailDto.getdAmt());
                }else{
                    vouchdetail.setbHappenAmt(detailDto.getcAmt());
                }
            }else{
                if(detailDto.getBcHappenAmt() == null || BigDecimal.ZERO.compareTo(detailDto.getBcHappenAmt()) == 0){
                    vouchdetail.setbHappenAmt(detailDto.getBdHappenAmt());
                }else{
                    vouchdetail.setbHappenAmt(detailDto.getBcHappenAmt());
                }
            }
        }

        // 登录
        bokVouchDetailMapper.insert(vouchdetail);
        return vouchdetail;
    }

    /**
     * 凭证账务明细关联表登录
     * 
     * @param bookdetailAll
     * @param vouchDetail
     * @see
     * @since 1.0
     */
    private void insertBokVouchRel(BokBookdetailAll bookdetailAll, BokVouchDetail vouchDetail) {
        BokVouchRel vouchRel = new BokVouchRel();
        // 凭证子表编号
        vouchRel.setVouchSubnum(vouchDetail.getVouchSubnum());
        // 账务明细流水号
        vouchRel.setSeqno(bookdetailAll.getSeqno());
        // 凭证编号
        vouchRel.setVouchNum(vouchDetail.getVouchNum());
        // 登录
        bokVouchRelMapper.insert(vouchRel);

    }

    /**
     * 调用共通生成账务明细代码
     * 
     * @param deal
     * @param detailDto
     * @see
     * @since 1.0
     */
    public void setBokDetailId(TrdVouchDeal deal, ValManualVoucher003AddDto detailDto,
                               ValManualVoucher003Dto dto) {
        // 业务明细代码
        String busiId = null;
        // 业务明细类型
        String busiDetailType = null;
        // 投资目的
        String invAim = null;
        // 分支序号
        Long branch = null;
        SuggestDto rstdto = null;
        // 基础科目性质=“现金” 或者 “清算款”
        if (DictCodeBaseSubjectNatrue.现金.getValue().equals(detailDto.getBaseSubjectNature())
            || DictCodeBaseSubjectNatrue.清算款.getValue().equals(detailDto.getBaseSubjectNature())
        ||DictCodeBaseSubjectNatrue.清算款.getValue().equals(detailDto.getBaseSubjectNature())

        ) {
            rstdto = valManualVoucher003Mapper.getFinprodType(detailDto.getAcctCode(),
                deal.getBooksetId(), detailDto.getBranch());
            // 业务明细代码： 资金账户
            busiId = detailDto.getAcctCode();
            // 业务明细类型：资金账户类型
            busiDetailType = rstdto.getHidCode();
        }
        //add by xr “F20  对应的活期存款应计利息 时取对应账户 资金账户类型_券商资金账户
        else if (dto.getFinprodType().equals(DictCodeListExt1.金融产品类型_资金账户.getValue())
                || dto.getFinprodType().equals(DictCodeListExt1.资金账户类型_券商资金账户.getValue())
                &&  (DictCodeBaseSubjectNatrue.应计利息.getValue().equals(detailDto.getBaseSubjectNature())||
                DictCodeBaseSubjectNatrue.利息收入.getValue().equals(detailDto.getBaseSubjectNature()))
        ){
            rstdto = valManualVoucher003Mapper.getFinprodType(deal.getBusiId(),
                    deal.getBooksetId(), detailDto.getBranch());
            // 业务明细代码： 资金账户
            busiId = deal.getBusiId();
            // 业务明细类型：资金账户类型
            busiDetailType = rstdto.getHidCode();
        }
        else {
            if (DictCodeListExt1.金融产品类型_债券.getValue().equals(dto.getFinprodType()) ||
                DictCodeListExt1.金融产品类型_融入债券.getValue().equals(dto.getFinprodType())) {
                // 业务明细代码： 1画面.资产代码
                busiId = dto.getBusiId();
                // 业务明细类型：画面.隐藏的业务明细类型
                busiDetailType = deal.getBusiDetailType();
                // 投资目的：变量.投资目的
                invAim = deal.getInvAim();
            }
            else {
                // 业务明细代码：画面.资产代码对应的业务明细代码
                busiId = deal.getBusiId();
                // 业务明细类型：画面.隐藏的业务明细类型
                busiDetailType = dto.getBusiDetailType();
                /** 2020年4月29日 weipeng:上农商出现生产问题，手工凭证新建计划类资产时，账务明细代码生成有误，带入了分支序号 start**/
                // 只有理财子产品需要分支序号
                if(DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(dto.getBusiDetailType())){
                    // 分支序号：分支序号
                    branch = dto.getBranch();
                }
                /** 2020年4月29日 weipeng:上农商出现生产问题，手工凭证新建计划类资产时，账务明细代码生成有误，带入了分支序号 end**/
            }
        }
        String bokDetailId = null;
        // 资金账户类型取得
        BokDetailId valBokDetailId = new BokDetailId();
        valBokDetailId.setBooksetId(deal.getBooksetId());
        valBokDetailId.setBusiDetailType(busiDetailType);
        valBokDetailId.setInvAim(invAim);
        valBokDetailId.setBusiId(busiId);
        valBokDetailId.setBranch(branch);
        valBokDetailId.setCcy(detailDto.getCcy());
        bokDetailId = batchComService.genMd5BokDetailId(valBokDetailId);
        // 给DTO设置
        detailDto.setBokDetailId(bokDetailId);
        detailDto.setBusiDetailId(busiId);
        detailDto.setBusiDetailType(busiDetailType);
    }

    /**
     * 调用共通生成账务明细代码
     * 
     * @param deal
     * @param detailDto
     * @see
     * @since 1.0
     */
//    public String getBokDetailId(TrdVouchDeal deal, ValManualVoucher003AddDto detailDto,
//                                 String finprodType) {
//        // 业务明细代码
//        String busiId = null;
//        // 业务明细类型
//        String busiDetailType = null;
//        // 投资目的
//        String invAim = null;
//        // 分支序号
//        Long branch = null;
//        SuggestDto rstdto = null;
//        // 基础科目性质=“现金” 或者 “清算款”
//        if (DictCodeBaseSubjectNatrue.现金.getValue().equals(detailDto.getBaseSubjectNature())
//            || DictCodeBaseSubjectNatrue.清算款.getValue().equals(detailDto.getBaseSubjectNature())) {
//            rstdto = valManualVoucher003Mapper.getFinprodType(detailDto.getAcctCode(),
//                deal.getBooksetId(), detailDto.getBranch());
//            // 业务明细代码： 资金账户
//            busiId = detailDto.getAcctCode();
//            // 业务明细类型：资金账户类型
//            busiDetailType = rstdto.getHidCode();
//        }
//        else {
//            if (DictCodeListExt1.金融产品类型_债券.getValue().equals(finprodType)) {
//                // 业务明细代码： 1画面.资产代码
//                busiId = deal.getBusiId();
//                // 业务明细类型：画面.隐藏的业务明细类型
//                busiDetailType = deal.getBusiDetailType();
//                // 投资目的：变量.投资目的
//                invAim = deal.getInvAim();
//            }
//            else {
//                // 业务明细代码：画面.资产代码对应的业务明细代码
//                busiId = deal.getBusiId();
//                // 业务明细类型：画面.隐藏的业务明细类型
//                busiDetailType = detailDto.getBusiDetailType();
//                // 分支序号：分支序号
//                branch = detailDto.getBranch();
//            }
//        }
//        String bokDetailId = null;
//        // 账务明细代码取得
//        BokDetailId valBokDetailId = new BokDetailId();
//        valBokDetailId.setBooksetId(deal.getBooksetId());
//        valBokDetailId.setBusiDetailType(busiDetailType);
//        valBokDetailId.setInvAim(invAim);
//        valBokDetailId.setBusiId(busiId);
//        valBokDetailId.setBranch(branch);
//        bokDetailId = batchComService.genMd5BokDetailId(valBokDetailId);
//        return bokDetailId;
//    }

    /**
     * 获取页面资产代码对应的业务明细代码
     * 
     * @param booksetId 账套代码
     * @param busiDetailType 业务明细类型
     * @param busiId 资产代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    public String getBusiDetailId(String booksetId, String busiDetailType, String busiId,
                                  String finprodType) {
        if (DictCodeListExt1.金融产品类型_固定管理费.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_浮动管理费.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_托管费.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_销售费.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_代销费.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_外包服务费.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_交易费用.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_理财产品.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_投资组合.getValue().equals(finprodType)) {
            // 业务明细代码：画面.资产代码对应的业务明细代码
            BokAcctLayeringInfoKey key = new BokAcctLayeringInfoKey();
            key.setBooksetId(booksetId);
            key.setBusiDetailType(busiDetailType);
            key.setLayeringId(busiId);
            BokAcctLayeringInfo bokAcctLayeringInfo = bokAcctLayeringInfoMapper.selectByPrimaryKey(
                key);
            if (bokAcctLayeringInfo == null) {
                return "";
            }
            return bokAcctLayeringInfo.getBusiId();
        }
        else if (DictCodeListExt1.金融产品类型_浮动管理费.getValue().equals(finprodType)) {
            BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
            // if 核算方式=“收益型”
            if (!DictCodeList.收益类型_收益型.getValue().equals(bokBookset.getAcctMode())) {
                // 业务明细代码：画面.资产代码对应的业务明细代码
                BokAcctLayeringInfoKey key = new BokAcctLayeringInfoKey();
                key.setBooksetId(booksetId);
                key.setBusiDetailType(busiDetailType);
                key.setLayeringId(busiId);
                BokAcctLayeringInfo bokAcctLayeringInfo = bokAcctLayeringInfoMapper.selectByPrimaryKey(
                    key);
                if (bokAcctLayeringInfo == null) {
                    return "";
                }
                return bokAcctLayeringInfo.getBusiId();
            }
        }
        // 否则返回页面的资产代码
        return busiId;
    }

    /**
     * 流程审核完成 {@inheritDoc}
     */
    @Override
    public void doComplete(ValManualVoucher003Dto entity, OperationType type) {
        // 新建操作
        if (OperationType.NEW.equals(type)) {
//          doSubmit(entity);
      	 restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucher003/doSubmit",
      			 entity, Integer.class);
        }
    }

    /**modify by dongjun on 20181214 start **/
    /**
     * 
     * 手工凭证科目号联想模糊查询
     * 
     * @param para
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> querySubctNo(Map<String, Object> para){
        return valManualVoucher003Mapper.querySubjectNo(para);
    }
    /**modify by dongjun on 20181214 end **/
    
    /**
     * 获取投资目的下拉框
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public BokDetailId getInvAim(Map<String, Object> params) {
        return valManualVoucher003Mapper.getInvAim(params);
    }
}
