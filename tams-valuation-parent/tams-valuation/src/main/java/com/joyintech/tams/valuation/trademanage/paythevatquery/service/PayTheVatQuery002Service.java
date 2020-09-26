/*
 * 文件名：PayTheVatQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.paythevatquery.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dao.PayTheVatQuery002Mapper;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002TableDto;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingDetailMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingMapper;
import com.joyintech.tams.common.base.dao.StlOrdersExtMapper;
import com.joyintech.tams.common.base.dao.StlOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.TrdTradeFeeDetailMapper;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 纳税增值税新建service
 * 
 * @author wangqidong
 * @version 1.0 2019年2月25日
 * @see PayTheVatQuery002Service
 * @since 1.0
 */
@Service
public class PayTheVatQuery002Service extends CommonExportService<PayTheVatQuery002Dto> implements BaseProcessService<PayTheVatQuery002Dto> {

    @Autowired
    private PayTheVatQuery002Mapper payTheVatQuery002Mapper;

    /**
     * 金融产品交易扩展表
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealExtMapper;

    /**
     * 金融产品交易表
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    /**
     * 入账信息表
     */
    @Autowired
    private BokDealAccountingMapper bokDealAccountingMapper;

    /**
     * 入账账务明细表
     */
    @Autowired
    private BokDealAccountingDetailMapper bokDealAccountingDetailMapper;

    /**
     * 交易费用明细表
     */
    @Autowired
    private TrdTradeFeeDetailMapper tgTradeFeeDetailMapper;

    /**
     * 结算指令表
     */
    @Autowired
    private StlOrdersMapper stlOrdersMapper;

    /**
     * 结算指令扩展表
     */
    @Autowired
    private StlOrdersExtMapper stlOrdersExtMapper;

    /**
     * 接口共通
     */
    @Autowired
    private InterfCommonService interfCommonService;
    
    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;

    /**
     * 查询
     * 
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<PayTheVatQuery002TableDto> search(Map<String, Object> pageParam)
        throws Exception {
        // 产品多选
        if (StringUtil.isNotEmpty(pageParam.get("booksetId"))) {
            String booksetId = (String)pageParam.get("booksetId");
            String[] booksetIdArr = null;
            booksetIdArr = booksetId.split(",");
            pageParam.put("booksetIdArr", booksetIdArr);
        }
        String taxidenType = (String)pageParam.get("taxidenType");
        if(DictCodeList.交易方向_税费退税.getValue().equals(taxidenType)){
            // 设置一个标志位，随便赋值即可，表示是退税操作
            pageParam.put("taxRefundFlag", DictCodeList.是否_是.getValue());
        }
        // 查询开始日时去除撤销状态的数据
        pageParam.put("tradeStatus", DictCodeListExt1.交易状态_撤销.getValue());
        List<PayTheVatQuery002TableDto> listDto = payTheVatQuery002Mapper.search(pageParam);
        if(!CollectionUtils.isEmpty(listDto)){
            // 汇总行
            PayTheVatQuery002TableDto dto = new PayTheVatQuery002TableDto();
            dto.setBooksetId("汇总行");
            for(PayTheVatQuery002TableDto item : listDto){
                dto.setInterestIncome(ArithmeticUtil.doAdd(dto.getInterestIncome(),item.getInterestIncome()));
                dto.setDividendIncome(ArithmeticUtil.doAdd(dto.getDividendIncome(),item.getDividendIncome()));
                dto.setDifbetIncome(ArithmeticUtil.doAdd(dto.getDifbetIncome(),item.getDifbetIncome()));
                dto.setAppreciationTax(ArithmeticUtil.doAdd(dto.getAppreciationTax(),item.getAppreciationTax()));
                dto.setConstructionTax(ArithmeticUtil.doAdd(dto.getConstructionTax(),item.getConstructionTax()));
                dto.setEducationTax(ArithmeticUtil.doAdd(dto.getEducationTax(),item.getEducationTax()));
                dto.setPlaceeducationTax(ArithmeticUtil.doAdd(dto.getPlaceeducationTax(),item.getPlaceeducationTax()));
                dto.setAdditionalTax(ArithmeticUtil.doAdd(dto.getAdditionalTax(),item.getAdditionalTax()));
            }
            listDto.add(dto);
        }
        return listDto;
    }

    /**
     * 校验缴税/退税日期
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<PayTheVatQuery002Dto> checkdate(Map<String, Object> pageParam) {
        // 产品多选
        if (StringUtil.isNotEmpty(pageParam.get("booksetId"))) {
            String booksetId = (String)pageParam.get("booksetId");
            String[] booksetIdArr = null;
            booksetIdArr = booksetId.split(",");
            pageParam.put("booksetIdArr", booksetIdArr);
        }
        List<PayTheVatQuery002Dto> payTheVatQuery002Dto = payTheVatQuery002Mapper.checkdate(
            pageParam);
        return payTheVatQuery002Dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PayTheVatQuery002Dto dto, OperationType type) {
        // TODO Auto-generated method stub
        try {
            this.docommit(dto);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int docommit(PayTheVatQuery002Dto dto)
        throws ParseException {
        int counts = 0;
        List<PayTheVatQuery002TableDto> listdto = dto.getListpayTheVatQuery002TableDto();
        for (PayTheVatQuery002TableDto dtolist : listdto) {
            // 汇总行跳过
            if(StringUtil.isEmpty(dtolist.getBooksetName())){
                continue;
            }
            // 初始化dto参数值
            // 交易编号生成
            dto.setTradeId(
                interfCommonService.getTradeid(dto.getTaxidenType(), dto.getCreateTime(), "A"));
            // 指令编号生成
            dto.setOrderId(
                interfCommonService.getTradeid(dto.getTaxidenType(), dto.getCreateTime(), "B"));
            // 账套代码
            dto.setBooksetId(dtolist.getBooksetId());
            dto.setTradeStatus("01");
            // 创建人
            dto.setCreateUser(SecurityUtil.getLoginUser().getUserName());
            // 金融产品代码
            List<PayTheVatQuery002Dto> dto1 = payTheVatQuery002Mapper.findCashid(
                dtolist.getBooksetId().toString(), dtolist.getAcctSubjectType().toString()
                ,DictCodeListExt1.现金流二级类型_税额.getValue());
            dto.setFinprodId(dto1.get(0).getCashId());
            // 交易金额 = 增值税+增值税附加
            dto.setTradeAmt(ArithmeticUtil.doAdd(dtolist.getAppreciationTax(),dtolist.getAdditionalTax()));
            // 账务明细代码
            dto.setBokDetailid(payTheVatQuery002Mapper.findbokDetailid(dtolist.getBooksetId(),
                dto.getFinprodId()));
            // 本方资金账户
            List<PayTheVatQuery002Dto> dto2 = payTheVatQuery002Mapper.findacctNo(
                dtolist.getBooksetId(), DictCodeListExt1.资金账户类型_产品托管户.getValue());
            dto.setAcctNo(dto2.get(0).getAcctNo());
            dto.setBusiType(DictCodeList.业务类型_增值税交易.getValue());
            if (DictCodeList.交易方向_税费缴税.getValue().equals(dto.getTaxidenType())) {
                dto.setOrderType(DictCodeListExt1.指令类型_增值税缴纳_缴纳日_增值税缴纳.getValue());
                dto.setPayment1(dto.getPayTaxMDate());
                dto.setPayment2(dto.getPayTaxVDate());
                dto.setPaymentStart(dtolist.getTaxStartDate());
            }
            else {
                dto.setOrderType(DictCodeListExt1.指令类型_增值税缴纳_退税日_退税.getValue());
                dto.setPayment1(dto.getRefundTaxMDate());
                dto.setPayment2(dto.getRefundTaxVDate());
                dto.setPaymentStart(dtolist.getTaxRefundStartDate());
            }

            // 入金融产品交易扩展表(核算主体类别不同入库不同)
            if ("F16".equals(dtolist.getAcctSubjectType())) {
                dto.setPortfolioId(dtolist.getBooksetId());
                dto.setProdId(null);
            }
            else {
                dto.setPortfolioId(null);
                dto.setProdId(dtolist.getBooksetId());
            }
            // 本位币
            dto.setStandardCcy(dtolist.getStandardCcy());

            // 入金融产品交易扩展表
            counts += trdProductDealExtMapper.insertSelective(trdProductDealExt(dto));
            // 入金融产品交易表
            counts += trdProductDealMapper.insertSelective(trdProductDeal(dto));
            // 入入账信息表
            counts += bokDealAccountingMapper.insertSelective(bokDealAccounting(dto));
            // 入账务明细表
            counts += bokDealAccountingDetailMapper.insertSelective(bokDealAccountingDetail(dto));
            // 入结算指令扩展表
            counts += stlOrdersExtMapper.insertSelective(stlOrdersExt(dto));
            // 入结算指令表
            counts += stlOrdersMapper.insertSelective(stlOrders(dto));

            // 入交易费用明细表表(TAX_TYPE数据字典,分6条数据分别存储金额数据)
            // TAX0102利息收入
            dto.setFeeAmt(dtolist.getInterestIncome());
            dto.setFeeType(DictCodeList.增值税类型_利息收入.getValue());
            counts += tgTradeFeeDetailMapper.insertSelective(tgTradeFeeDetail(dto));

            // TAX0202红利收入
            dto.setFeeAmt(dtolist.getDividendIncome());
            dto.setFeeType(DictCodeList.增值税类型_红利收入.getValue());
            counts += tgTradeFeeDetailMapper.insertSelective(tgTradeFeeDetail(dto));

            // TAX0302价差收入
            dto.setFeeAmt(dtolist.getDifbetIncome());
            dto.setFeeType(DictCodeList.增值税类型_价差收入.getValue());
            counts += tgTradeFeeDetailMapper.insertSelective(tgTradeFeeDetail(dto));

            // ALT0102城建税附加
            dto.setFeeAmt(dtolist.getConstructionTax());
            dto.setFeeType(DictCodeList.附加税类型_城建税.getValue());
            counts += tgTradeFeeDetailMapper.insertSelective(tgTradeFeeDetail(dto));

            // ALT0202教育税附加
            dto.setFeeAmt(dtolist.getEducationTax());
            dto.setFeeType(DictCodeList.附加税类型_教育税.getValue());
            counts += tgTradeFeeDetailMapper.insertSelective(tgTradeFeeDetail(dto));

            // ALT0302地方教育费附加
            dto.setFeeAmt(dtolist.getPlaceeducationTax());
            dto.setFeeType(DictCodeList.附加税类型_地方教育税.getValue());
            counts += tgTradeFeeDetailMapper.insertSelective(tgTradeFeeDetail(dto));
        }
        return counts;
    }

    /**
     * 金融产品交易扩展表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public TrdProductDealAdd trdProductDealExt(PayTheVatQuery002Dto dto) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        trdProductDealExt.setTradeId(dto.getTradeId());
        trdProductDealExt.setPortfolioId(dto.getPortfolioId());
//        trdProductDealExt.setProdId(dto.getProdId());
        trdProductDealExt.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDealExt.setCreateTime(new Date());
        trdProductDealExt.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDealExt.setUpdateTime(new Date());
        return trdProductDealExt;

    }

    /**
     * 金融产品交易表
     * 
     * @param dto
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public TrdProductDeal trdProductDeal(PayTheVatQuery002Dto dto)
        throws ParseException {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(dto.getTradeId());
        trdProductDeal.setFinprodId(dto.getFinprodId());
        trdProductDeal.setBusiType(DictCodeList.业务类型_增值税交易.getValue());
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_税额.getValue());
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        trdProductDeal.setTradeAmt(dto.getTradeAmt());
        trdProductDeal.setMdate(DateUtil.parseDate(dto.getPayment1(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(dto.getPaymentStart())) {
            trdProductDeal.setVdate(DateUtil.parseDate(dto.getPaymentStart(), "yyyy-MM-dd"));
        }
        else {
            trdProductDeal.setVdate(null);
        }
        trdProductDeal.setSettleDate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        trdProductDeal.setTradeDate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        trdProductDeal.setCcy(dto.getStandardCcy());
        trdProductDeal.setPs(dto.getTaxidenType());
        trdProductDeal.setTradeStatus(dto.getTradeStatus());
        trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
        trdProductDeal.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDeal.setCreateTime(new Date());
        trdProductDeal.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDeal.setUpdateTime(new Date());
        return trdProductDeal;
    }

    /**
     * 交易费用明细表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public TrdTradeFeeDetail tgTradeFeeDetail(PayTheVatQuery002Dto dto) {
    	TrdTradeFeeDetail tgTradeFeeDetail = new TrdTradeFeeDetail();
        tgTradeFeeDetail.setTradeId(dto.getTradeId());
        tgTradeFeeDetail.setFeeType(dto.getFeeType());
        tgTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        tgTradeFeeDetail.setFeeAmt(dto.getFeeAmt());
        tgTradeFeeDetail.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        tgTradeFeeDetail.setCreateTime(new Date());
        tgTradeFeeDetail.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        tgTradeFeeDetail.setUpdateTime(new Date());
        return tgTradeFeeDetail;
    }

    /**
     * 结算指令表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public StlOrders stlOrders(PayTheVatQuery002Dto dto) {
        StlOrders stlOrders = new StlOrders();
        stlOrders.setOrderId(dto.getOrderId());
        stlOrders.setBusiType(DictCodeList.业务类型_增值税交易.getValue());
        if (DictCodeList.交易方向_税费缴税.getValue().equals(dto.getTaxidenType())) {
            stlOrders.setOrderType(DictCodeListExt1.指令类型_增值税缴纳_缴纳日_增值税缴纳.getValue());
        }
        else {
            stlOrders.setOrderType(DictCodeListExt1.指令类型_增值税缴纳_退税日_退税.getValue());
        }
        stlOrders.setSettleDate(DateUtil.parse(dto.getPayment2()));
        stlOrders.setMyAcctNo(dto.getAcctNo());
        stlOrders.setPs(DictCodeListExt1.收付方向_支出.getValue());
        stlOrders.setTradeId(dto.getTradeId());
        stlOrders.setOrderStatus(DictCodeListExt1.交易状态_初始.getValue());
        stlOrders.setSettleStatus(DictCodeList.成功失败标志_成功.getValue());
        stlOrders.setSettleAmt(dto.getTradeAmt());
        stlOrders.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrders.setCreateTime(new Date());
        stlOrders.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrders.setUpdateTime(new Date());
        return stlOrders;
    }

    /**
     * 结算指令扩展表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public StlOrdersExt stlOrdersExt(PayTheVatQuery002Dto dto) {
        StlOrdersExt stlOrdersExt = new StlOrdersExt();
        stlOrdersExt.setOrderId(dto.getOrderId());
        stlOrdersExt.setIsTrade(DictCodeList.是否_是.getValue());
        stlOrdersExt.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrdersExt.setCreateTime(new Date());
        stlOrdersExt.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrdersExt.setUpdateTime(new Date());
        return stlOrdersExt;
    }

    /**
     * 入账信息表
     * 
     * @param dto
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public BokDealAccounting bokDealAccounting(PayTheVatQuery002Dto dto)
        throws ParseException {
        BokDealAccounting bokDealAccounting = new BokDealAccounting();
        bokDealAccounting.setBooksetId(dto.getBooksetId());
        bokDealAccounting.setBusiNo(dto.getTradeId());
        bokDealAccounting.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        bokDealAccounting.setIsAccounted(DictCodeList.是否_否.getValue());
        BokBooksetDate bokBooksetDate = new BokBooksetDate();
        bokBooksetDate = bokBooksetDateMapper.selectByPrimaryKey(dto.getBooksetId());
        if(StringUtil.isNotEmpty(bokBooksetDate)){
            bokDealAccounting.setBooksetDate(bokBooksetDate.getBooksetDate());
        }
        bokDealAccounting.setBookDate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        bokDealAccounting.setHappenDate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        bokDealAccounting.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccounting.setCreateTime(new Date());
        bokDealAccounting.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccounting.setUpdateTime(new Date());
        return bokDealAccounting;
    }

    /**
     * 入账账务明细表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BokDealAccountingDetail bokDealAccountingDetail(PayTheVatQuery002Dto dto) {
        BokDealAccountingDetail bokDealAccountingDetail = new BokDealAccountingDetail();
        bokDealAccountingDetail.setBooksetId(dto.getBooksetId());
        bokDealAccountingDetail.setBusiNo(dto.getTradeId());
        bokDealAccountingDetail.setBokDetailId(dto.getBokDetailid());
        bokDealAccountingDetail.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        bokDealAccountingDetail.setBusiType(DictCodeList.业务类型_增值税交易.getValue());
        bokDealAccountingDetail.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccountingDetail.setCreateTime(new Date());
        bokDealAccountingDetail.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccountingDetail.setUpdateTime(new Date());
        return bokDealAccountingDetail;
    }

}
