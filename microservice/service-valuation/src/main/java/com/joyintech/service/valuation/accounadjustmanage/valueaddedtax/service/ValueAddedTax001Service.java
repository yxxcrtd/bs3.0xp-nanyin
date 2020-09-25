/*
 * 文件名：ValueAddedTax001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.balancetablegeneration.service.BalTableGenerate001Service;
import com.joyintech.service.valuation.accounadjustmanage.bokbalancestatementquery.dto.BokBalanceStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dao.ValueAddedTax001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dto.BooksetInfoDto;
import com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dto.ValueAddedTax001Dto;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.TaxDeclareSumInfoMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.TaxBokBalanceSum;
import com.joyintech.tams.common.base.entity.TaxBokDetail;
import com.joyintech.tams.common.base.entity.TaxDeclareSumInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 增值台账Service
 * 
 * @author ruanhongming
 * @version 1.0 2018年9月21日
 * @see ValueAddedTax001Service
 * @since 1.0
 */
@Service(value="serviceValueAddedTax001Service")
public class ValueAddedTax001Service extends CommonExportService<ValueAddedTax001Dto> {

    /**
     * 余额查询mapper
     */
    @Autowired
    private ValueAddedTax001Mapper valueAddedTax001Mapper;

    /**
     * 账套查询mapper
     */
    @Autowired
    private BokBooksetMapper bokBooksetMapper;
    
    /**
     * 账套查询mapper
     */
    @Autowired
    private BalTableGenerate001Service balTableGenerate001Service;
    
    /**
     * 账套查询mapper
     */
    @Autowired
    private TaxDeclareSumInfoMapper taxDeclareSumInfoMapper;
    
    /**
     * 标的类金融产品查询Mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;

    public Page<ValueAddedTax001Dto> selectForPage(int page, int length,Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("acctSubjectType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return super.queryForPage(page,length,() -> valueAddedTax001Mapper.selectAll(pageParam));
    }
    
    /**
     * 查询页面信息
     * @param pageParam
     * @return
     */
    public List<ValueAddedTax001Dto> selectAllList(Map<String, Object> pageParam) {
        List<ValueAddedTax001Dto> list = valueAddedTax001Mapper.selectAll(
            pageParam);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> pageParam = exportParam.getSelectParam();
        String pageIds = (String)pageParam.get("pageIds");
        if (StringUtil.isNotEmpty(pageIds)) {
            List<String> pageNames = Arrays.asList(pageIds.split(GlobalConstant.COMMA));
            pageParam.replace("pageIds", pageIds, pageNames);
        }
        else {
            pageParam.replace("pageIds", pageIds, null);
        }
        exportParam.config(
            "/com/joyintech/tams/valuation/accounadjustmanage/bokbalancestatementquery/conf/export_conf.xml").select(
                () -> selectAllList(pageParam));
    }

    /**
     * 通过账套获取对应资产名称和资产代码
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinProduct getFinProductByBookset(BokBalanceStatementQuery001Dto dto) {
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(dto.getBooksetId());
//        FinProductKey key = new FinProductKey();
        String finprodId =bokBookset.getAcctSubjectId();
        // 账套的核算主体代码关联金融产品标的表的金融产品代码
//        key.setFinprodId(bokBookset.getAcctSubjectId());
        // 金融产品类型=理财产品
//        key.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finProductMapper.selectByPrimaryKey(finprodId);
    }
    
    /**
     * 通过账套获取对应资产名称和资产代码
     * 
     * @param String
     * @return
     * @see
     * @since 1.0
     */
    public FinProduct getFinProductByBookset(String booksetId) {
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
//        FinProductKey key = new FinProductKey();
        String finprodId = bokBookset.getAcctSubjectId();
        // 账套的核算主体代码关联金融产品标的表的金融产品代码
//        key.setFinprodId(bokBookset.getAcctSubjectId());
//        // 金融产品类型=理财产品
//        key.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finProductMapper.selectByPrimaryKey(finprodId);
    }
    
    
    /**
     * 导出估值表excel文件
     * 
     * @param dto
     * @return
     * @throws IOException 
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params) throws IOException {
        String booksetId = (String)params.get("booksetId");
        String balDate = (String)params.get("balDate");
        FinProduct finprod = this.getFinProductByBookset(booksetId);
        // 获取导出文件
        ExportFileDto exportFileDto = balTableGenerate001Service.createExcel(
            booksetId, balDate,
            finprod.getFinprodId(), finprod.getFinprodName());
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }
    
   /**
    * 纳税申报表生成共通处理-单产品维度
    * @param year 年份
    * @param declare 征期
    * @param statDimen 统计维度
    * @param list 账套信息
    * @throws ParseException 
    */
    public void sigleProduct(String year,String declareDate,String statDimen,List<BooksetInfoDto> list) throws ParseException{
        //遍历账套信息
        for(BooksetInfoDto dto:list){
            //根据征期获取征期开始日和征期结束日
            Map<String,Object> map = getBeginEndDate(year,declareDate);
            //纳税申报信息
            TaxDeclareSumInfo  taxDeclareSumInfo = new TaxDeclareSumInfo();
//            SimpleDateFormat sim=new SimpleDateFormat("MM-dd"); 
            //账套结束日
            Date endDate = dto.getEndDate();
            //征期开始日
            Date decDateStart = (Date)map.get("startDate");
            //征期结束日
            Date decDateEnd = (Date)map.get("endDate");
            //账套结束日(查询) < 征期开始日
            if(endDate.before(decDateStart)){
                return;
            }
            //账套结束日 < 征期结束日
            if(endDate.before(decDateEnd)){
                //征期结束日=账套结束日
                map.put("endDate", dto.getEndDate());
            }
            //取上一征期
            String foreDeclar = getForeDelcareDate(declareDate);
            //上一征期
            map.put("foreDeclar", foreDeclar);
            //账套代码
            map.put("booksetId", dto.getBooksetId());
            
            //上一征期纳税申报表信息
            List<TaxDeclareSumInfo> listTax = valueAddedTax001Mapper.quryTaxDeclarSumInfoFore(map);
            if(listTax == null || listTax.size() == 0){
                TaxDeclareSumInfo dtoInfo = new TaxDeclareSumInfo();
                listTax = new ArrayList<TaxDeclareSumInfo>();
                listTax.add(dtoInfo);
            }
            //本年度持有期间收益税额
            BigDecimal revenueTax = BigDecimal.ZERO;
            //本年度免税收入_36号文件
            BigDecimal file36 = BigDecimal.ZERO;
            //本年度免税收入_46号文件
            BigDecimal file46 = BigDecimal.ZERO;
            //本年度免税收入_70号文件
            BigDecimal file70 = BigDecimal.ZERO;
            //本年度免税收入_国债及地方债
            BigDecimal sec = BigDecimal.ZERO;
            //确认标识：发生计税
            map.put("taxPType", DictCodeList.增值税_确认类型_发生计税.getValue());
            //最新计税流水信息
            List<TaxBokDetail> listTaxInfo = valueAddedTax001Mapper.quryTaxBokDetail(map);
            if(listTaxInfo!=null&&listTaxInfo.size()>0){
                for(TaxBokDetail tbd:listTaxInfo){
                    if(tbd.getCalcType().equals(DictCodeList.计税方式_简易计税.getValue())){
                        //增值税账务明细流水.收入类型 in (利息收入,红利收入)
                        if(tbd.getVatType().equals(DictCodeList.税类型_利息收入.getValue())||
                            tbd.getVatType().equals(DictCodeList.税类型_红利收入.getValue())){
                            //本年度持有期间收益税额 = 本年度持有期间收益税额 + 计税余额发生额
                            if(tbd.getBalAmt()!=null){
                                revenueTax = revenueTax.add(tbd.getBalAmt());
                            }else {
                                revenueTax = revenueTax.add(BigDecimal.ZERO);
                            }
                        }
                    }else if(tbd.getCalcType().equals(DictCodeList.计税方式_免税.getValue())){
                        if(tbd.getFeeType().equals("01")){//增值税账务明细流水.免税收入类型 = 免税收入_36号文件
                            if(tbd.getHappenAmt()!=null){
                                file36 = file36.add(tbd.getHappenAmt());
                            }else {
                                file36 = file36.add(BigDecimal.ZERO);
                            }
                        }else if(tbd.getFeeType().equals("02")){//增值税账务明细流水.免税收入类型 = 免税收入_46号文件
                            if(tbd.getHappenAmt()!=null){
                                file46 = file46.add(tbd.getHappenAmt());
                            }else {
                                file46 = file46.add(BigDecimal.ZERO);
                            }
                        }else if(tbd.getFeeType().equals("03")){//增值税账务明细流水.免税收入类型 = 免税收入_70号文件
                            if(tbd.getHappenAmt()!=null){
                                file70 = file70.add(tbd.getHappenAmt());
                            }else {
                                file70 = file70.add(BigDecimal.ZERO);
                            }
                        }else if(tbd.getFeeType().equals("04")){//增值税账务明细流水.免税收入类型 = 免税收入_国债及地方债
                            if(tbd.getHappenAmt()!=null){
                                sec = sec.add(tbd.getHappenAmt());
                            }else {
                                sec = sec.add(BigDecimal.ZERO);
                            }
                        }
                    }
                }
            }
            //收入类型：价差收入
            map.put("vatType", DictCodeList.税类型_价差收入.getValue());
            //最新计税余额信息
            List<TaxBokBalanceSum> listBalanceInfo = valueAddedTax001Mapper.quryTaxBokBalanceSum(map);
            //上一征期利息及红利收入增值税
            BigDecimal foreB1 = listTax.get(0).getB1();
            //利息及红利收入增值税
            BigDecimal b1 = BigDecimal.ZERO;
            if(foreB1!=null){
                b1 = revenueTax.subtract(foreB1);
            }else {
                b1 = revenueTax; 
            }
            //共通处理小数位
            b1 = ArithmeticUtil.round(b1, NumberEnum.MONEY);
            taxDeclareSumInfo.setB1(b1);
            
            //应税利息及红利收入-不含税
            BigDecimal a1 = BigDecimal.ZERO;
            if(b1.compareTo(BigDecimal.ZERO)!=0){
                a1 = b1.divide(new BigDecimal("0.03"), 2, BigDecimal.ROUND_HALF_UP);
            }
            //金额小数位数处理
            a1 = ArithmeticUtil.round(a1, NumberEnum.MONEY);
            taxDeclareSumInfo.setA1(a1);
            //金融商品转让增值税
            BigDecimal b2 = BigDecimal.ZERO;
            BigDecimal taxAmtSum = BigDecimal.ZERO;
            if(listBalanceInfo!=null&&listBalanceInfo.size()>0){
                taxAmtSum = listBalanceInfo.get(0).getTaxAmtSum();
            }
            if(taxAmtSum!=null){
                b2 = taxAmtSum;
            }
            //金融商品转让损益
            BigDecimal a2 = BigDecimal.ZERO;
            b2 = ArithmeticUtil.round(b2, NumberEnum.MONEY);
            taxDeclareSumInfo.setB2(b2);
            if(b2.compareTo(BigDecimal.ZERO)!=0){
                a2 = b2.divide(new BigDecimal("0.03"), 2, BigDecimal.ROUND_HALF_UP);
            }
            //金额小数位数处理
            a2 = ArithmeticUtil.round(a2, NumberEnum.MONEY);
            taxDeclareSumInfo.setA2(a2);
            //金融商品转让入账增值税
            BigDecimal b3 = BigDecimal.ZERO;
            BigDecimal taxAmt = BigDecimal.ZERO;
            if(listBalanceInfo!=null&&listBalanceInfo.size()>0){
                taxAmt = listBalanceInfo.get(0).getTaxAmt();
            }
            if(taxAmt!=null){
                b3 = taxAmtSum;
            }
            b3 = ArithmeticUtil.round(b3, NumberEnum.MONEY);
            taxDeclareSumInfo.setB3(b3);
            //上期.本年度免税收入_36号文件
            BigDecimal foreFile36 = listTax.get(0).getFree36file();
            if(foreFile36!=null){
                file36 = file36.subtract(foreFile36);
            }
            taxDeclareSumInfo.setFree36file(file36);
            //上期.本年度免税收入_46号文件
            BigDecimal foreFile46 = listTax.get(0).getFree46file();
            if(foreFile46!=null){
                file46 = file46.subtract(foreFile46);
            }
            taxDeclareSumInfo.setFree46file(file46);
            //上期.本年度免税收入_70号文件
            BigDecimal foreFile70 = listTax.get(0).getFree70file();
            if(foreFile70!=null){
                file70 = file70.subtract(foreFile70);
            }
            taxDeclareSumInfo.setFree70file(file70);
            //上期.本年度免税收入_国债及地方债
            BigDecimal foreSec = listTax.get(0).getFreeSec();
            if(foreSec!=null){
                sec = sec.subtract(foreSec);
            }
            taxDeclareSumInfo.setFreeSec(sec);
            //本期应交增值税（c1）产品划转到银行增值税
            BigDecimal c1 = BigDecimal.ZERO;
            c1 = b1.add(b3);
            taxDeclareSumInfo.setC1(c1);
            //本期应交附加税费（c2）产品划转到银行附加税费
            BigDecimal c2 = BigDecimal.ZERO;
            c2 = c1.multiply(new BigDecimal("0.12"));
            //金额小数位数处理
            c2 = ArithmeticUtil.round(c2, NumberEnum.MONEY);
            taxDeclareSumInfo.setC2(c2);
            //本期应交增值税（c3）银行划转到税局金融商品转让增值税   
            BigDecimal c3 = BigDecimal.ZERO;
            taxDeclareSumInfo.setC3(c3);
            //本期应交增值税（c4）银行划转到税局增值税   
            BigDecimal c4 = BigDecimal.ZERO;
            taxDeclareSumInfo.setC4(c4);
            //本期应交附加税费（c5）银行划转到税局附加税费   
            BigDecimal c5 = BigDecimal.ZERO;
            taxDeclareSumInfo.setC5(c5);
            //本期留存收益变动额（e1）  
            BigDecimal e1 = BigDecimal.ZERO;
            taxDeclareSumInfo.setE1(e1);
            //期初留存收益（e2）
            BigDecimal e2 = BigDecimal.ZERO;
            taxDeclareSumInfo.setE2(e2);
            //期末留存收益（e3）   
            BigDecimal e3 = BigDecimal.ZERO;
            taxDeclareSumInfo.setE3(e3);
            //收益标识
            taxDeclareSumInfo.setSignFlag(dto.getSignFlag());
            //生成流水号
            String seqId = IDGenerator.generate();
            taxDeclareSumInfo.setSeqId(seqId);
            //征期
            taxDeclareSumInfo.setDeclareDate(declareDate);
            //年份
            taxDeclareSumInfo.setDeclareYear(year);
            //汇总维度
            taxDeclareSumInfo.setDeclareType(statDimen);
            //账套代码
            taxDeclareSumInfo.setSubjectNature(dto.getBooksetId());
            //创建人
            taxDeclareSumInfo.setCreateUser("admin");
            //创建时间
            taxDeclareSumInfo.setCreateTime(new Date());
            //创建部门
            taxDeclareSumInfo.setCreateDept("dept");
            //更新时间
            taxDeclareSumInfo.setUpdateTime(new Date());
            //更新人
            taxDeclareSumInfo.setUpdateUser("admin");
            //查询数据库中是否已有该条信息
            int num = valueAddedTax001Mapper.checkSigleProduct(taxDeclareSumInfo);
            if(num>0){//数据库中已存在该条信息
                valueAddedTax001Mapper.deleteSigleProduct(taxDeclareSumInfo);
            }
            //单产品纳税申报信息入库
            taxDeclareSumInfoMapper.insert(taxDeclareSumInfo);
        }
    }
    
    /**
     * 纳税申报表生成共通处理-统计维度
     * @param year 年份
     * @param declareDate 征期
     * @param statDimen 统计维度
     * @param list 账套代码
     * @throws ParseException 
     */
    public void statisticalDimension(String year,String declareDate,String statDimen,List<String> list) throws ParseException{
        //其他维度纳税申报信息
        TaxDeclareSumInfo taxDeclareSumInfo = new TaxDeclareSumInfo();
        //根据征期获取征期开始日和征期结束日
        Map<String,Object> map = getBeginEndDate(year,declareDate);
        //账套代码转化为数组
        Object[] booksetIds = null;
        if(list!=null&&list.size()>0){
            booksetIds = list.toArray();
        }
        map.put("booksetIds", booksetIds);
        //年份
        map.put("declareYear", year);
        //征期
        map.put("declareDate",declareDate);
        if(statDimen.equals("02")){//保本
           map.put("signFlag", DictCodeListExt1.收益标识_保本浮动收益型.getValue());
        }else if(statDimen.equals("03")){//非保本
            map.put("signFlag", DictCodeListExt1.收益标识_非保本浮动收益型.getValue());
        }
        //查询维度
//        map.put("declareType", statDimen);
        //根据查询维度查询汇总金额
        TaxDeclareSumInfo sumTaxInfo = valueAddedTax001Mapper.sumTaxDeclareSumInfo(map);
        //取上一征期
        String foreDeclar = getForeDelcareDate(declareDate);
        //上一征期
        map.put("foreDeclar", foreDeclar);
        //上一征期纳税申报表信息
        List<TaxDeclareSumInfo> listTax = valueAddedTax001Mapper.quryTaxDeclarSumInfoFore(map);
        if(listTax == null || listTax.size() == 0){
            TaxDeclareSumInfo dtoInfo = new TaxDeclareSumInfo();
            listTax = new ArrayList<TaxDeclareSumInfo>();
            listTax.add(dtoInfo);
        }
        //应税利息及红利收入-不含税
        BigDecimal a1 = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            a1 = sumTaxInfo.getA1();
        }
        taxDeclareSumInfo.setA1(a1);
        //利息及红利收入增值税
        BigDecimal b1 = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            b1 = sumTaxInfo.getB1();
        }
        taxDeclareSumInfo.setB1(b1);
        //金融商品转让损益
        BigDecimal a2 = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            a2 = sumTaxInfo.getA2();
        }
        taxDeclareSumInfo.setA2(a2);
        //金融商品转让增值税
        BigDecimal b2 = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            b2 = sumTaxInfo.getB2();
        }
        taxDeclareSumInfo.setB2(b2);
        //金融商品转让入账增值税
        BigDecimal b3 = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            b3 = sumTaxInfo.getB3();
        }
        taxDeclareSumInfo.setB3(b3);
        //免税收入_36号文件
        BigDecimal free36file = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            free36file = sumTaxInfo.getFree36file();
        }
        taxDeclareSumInfo.setFree36file(free36file);
        //免税收入_46号文件
        BigDecimal free46file = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            free46file = sumTaxInfo.getFree46file();
        }
        taxDeclareSumInfo.setFree46file(free46file);
        //免税收入_70号文件
        BigDecimal free70file = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            free70file = sumTaxInfo.getFree70file();
        }
        taxDeclareSumInfo.setFree70file(free70file);
        //国债及地方债
        BigDecimal freeSec = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            freeSec = sumTaxInfo.getFreeSec();
        }
        taxDeclareSumInfo.setFreeSec(freeSec);
        //本期应交增值税_产品划转到银行增值税c1
        BigDecimal c1 = BigDecimal.ZERO;
        if(sumTaxInfo!=null){
            c1 = sumTaxInfo.getC1();
        }
        c1 = ArithmeticUtil.round(c1, NumberEnum.MONEY);
        taxDeclareSumInfo.setC1(c1);
        //本期应交附加税费（c2）产品划转到银行附加税费
        BigDecimal c2 = BigDecimal.ZERO;
        c2 = c1.multiply(new BigDecimal("0.12"));
        //金额小数位数处理
        c2 = ArithmeticUtil.round(c2, NumberEnum.MONEY); 
        taxDeclareSumInfo.setC2(c2);
        //本期应交增值税_银行划转到税局金融商品转让增值税c3
        BigDecimal c3 = BigDecimal.ZERO;
        //暂定
        c3=b2;
        taxDeclareSumInfo.setC3(c3);
        //本期应交增值税_银行划转到税局增值税c4
        BigDecimal c4 = BigDecimal.ZERO;
        c4 = b1.add(c3);
        taxDeclareSumInfo.setC4(c4);
        //本期应交增值税_银行划转到税局金融商品转让增值税c5
        BigDecimal c5 = BigDecimal.ZERO;
        c5 = c4.multiply(new BigDecimal("0.12"));
        c5 = ArithmeticUtil.round(c5, NumberEnum.MONEY); 
        taxDeclareSumInfo.setC5(c5);
        //本期留存收益变动额e1
        BigDecimal e1 = BigDecimal.ZERO;
        e1 = c1.add(c2).subtract(c4.add(c5));        
        taxDeclareSumInfo.setE1(e1);
        //期初留存收益e2
        BigDecimal e2 = BigDecimal.ZERO;
        if(listTax!=null){
            e2 = listTax.get(0).getE3();
            if(e2 == null){
                e2 = BigDecimal.ZERO;
            }
        }
        taxDeclareSumInfo.setE2(e2);
        //期末留存收益e3
        BigDecimal e3 = BigDecimal.ZERO;
        e3 = e2.add(e1);
        taxDeclareSumInfo.setE3(e3);
        //收益标识
//        taxDeclareSumInfo.setSignFlag("纳税汇总");
        //汇总纳税
//        taxDeclareSumInfo.setSubjectNature("纳税汇总");
        //生成流水号
        String seqId = IDGenerator.generate();
        taxDeclareSumInfo.setSeqId(seqId);
        //征期
        taxDeclareSumInfo.setDeclareDate(declareDate);
        //年份
        taxDeclareSumInfo.setDeclareYear(year);
        //汇总维度
        taxDeclareSumInfo.setDeclareType(statDimen);
        //账套代码
        taxDeclareSumInfo.setSubjectNature(""); 
//        if(list==null || list.size()==0){
//            taxDeclareSumInfo.setSubjectNature(""); 
//        }else {
//            taxDeclareSumInfo.setSubjectNature(list.get(0));  
//        }
        //创建人
        taxDeclareSumInfo.setCreateUser("admin");
        //创建时间
        taxDeclareSumInfo.setCreateTime(new Date());
        //创建部门
        taxDeclareSumInfo.setCreateDept("dept");
        //更新时间
        taxDeclareSumInfo.setUpdateTime(new Date());
        //更新人
        taxDeclareSumInfo.setUpdateUser("admin");
        //查询数据库中是否已有该条信息
        int num = valueAddedTax001Mapper.checkSigleProduct(taxDeclareSumInfo);
        if(num>0){//数据库中已存在该条信息
            valueAddedTax001Mapper.deleteSigleProduct(taxDeclareSumInfo);
        }
        //纳税申报信息入库
        taxDeclareSumInfoMapper.insert(taxDeclareSumInfo);
    }
    
    
    /**
     * 查询征期开始结束时间
     * @param declare 征期
     * @return
     * @throws ParseException
     */
    public Map<String,Object> getBeginEndDate(String year,String declare) throws ParseException{
        Map<String,Object> map = new HashMap<String,Object>();
//        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd"); 
        //征期开始日
        String start = year+"-"+"01-01";
        String end1 = year+"-"+"03-31";
        String end2 = year+"-"+"06-30";
        String end3 = year+"-"+"09-30";
        String end4 = year+"-"+"12-31";
        //征期
//        Date dateStart = sim.parse(start); 
//        Date dateEnd1 = sim.parse(end1); 
//        Date dateEnd2 = sim.parse(end2); 
//        Date dateEnd3 = sim.parse(end3);
//        Date dateEnd4 = sim.parse(end4); 
        if(declare.equals("01")){//第一征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate", DateUtil.parseDate(end1, "yyyy-MM-dd"));
        }else if(declare.equals("02")){//第二征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate", DateUtil.parseDate(end2, "yyyy-MM-dd"));
        }else if(declare.equals("03")){//第三征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate",  DateUtil.parseDate(end3, "yyyy-MM-dd"));
        }else if(declare.equals("04")){//第四征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate", DateUtil.parseDate(end4, "yyyy-MM-dd"));
        }
        return map;
    } 
    
    /**
     * 根据当前征期查询上一征期
     * @param declareDate
     * @return
     */
    public String getForeDelcareDate(String declareDate){
        String foreDeclareDate = "";
        if(declareDate.equals("01")){
            foreDeclareDate = "0";
        }else if(declareDate.equals("02")){
            foreDeclareDate = "01";
        }else if(declareDate.equals("03")){
            foreDeclareDate = "02";
        }else if(declareDate.equals("04")){
            foreDeclareDate = "03";
        }
        return foreDeclareDate;
    }
    
    /**
     * 校验是否价税分离
     * @param params
     * @return
     * @throws ParseException
     */
    public String checkTaxDeclareSumInfo(Map<String, Object> params) throws ParseException{
        String msg = "";
        //金融产品类型_理财产品
        params.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        //核算主体类别_理财产品
        params.put("acctSubjectType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        //现金流二级类型_税额
        params.put("cashType2", DictCodeListExt1.现金流二级类型_税额.getValue());
        //年份
        String year = (String)params.get("year");
        //征期
        String declarDate = (String)params.get("declarDate");
        Map<String,Object> map = getDelcareBeginEndDate(year,declarDate);
        Date startDate = (Date)map.get("startDate");
        params.put("declareStart", startDate);
        List<BooksetInfoDto> list = valueAddedTax001Mapper.checkPriceTaxSparation(params);
        for(BooksetInfoDto dto:list){
            //账套结束日
            Date endDate = dto.getEndDate();
            //当前日期
            Date booksetDate = dto.getBooksetDate();
            Date booksetDateAdd = addOneDay(booksetDate);
            if(endDate.compareTo(booksetDateAdd)!=0&& booksetDate.before(startDate)){//存在价税分离
                msg = "01";
                return msg;
            }
        }
        return msg;
    }
    
    /**
     * 生成
     * @param params
     * @return
     * @throws ParseException
     */
    public String buildTaxDeclareSumInfo(Map<String, Object> params) throws ParseException{
        String msg = "";
        //金融产品类型_理财产品
        params.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        //核算主体类别_理财产品
        params.put("acctSubjectType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        //现金流二级类型_税额
        params.put("cashType2", DictCodeListExt1.现金流二级类型_税额.getValue());
        //年份
        String year = (String)params.get("data[yearDate]");
        //征期
        String declarDate = (String)params.get("data[declarDate]");
        //汇总维度
        String sumDimen = (String)params.get("data[sumDimen]");
        Map<String,Object> map = getDelcareBeginEndDate(year,declarDate);
        Date startDate = (Date)map.get("startDate");
        params.put("declareStart", startDate);
        BooksetInfoDto paraDto = new BooksetInfoDto();
        paraDto.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        paraDto.setCashType2(DictCodeListExt1.现金流二级类型_税额.getValue());
        paraDto.setAcctSubjectType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        paraDto.setDeclareStart(startDate);
        //完成价税分离
        List<BooksetInfoDto> listD = new ArrayList<BooksetInfoDto>();
        List<BooksetInfoDto> list = valueAddedTax001Mapper.checkPriceTaxSparation(params);
        List<String> booksetList = new ArrayList<String>();
        for(BooksetInfoDto dto:list){
            //账套结束日
            Date endDate = dto.getEndDate();
            //当前日期
            Date booksetDate = dto.getBooksetDate();
            Date booksetDateAdd = addOneDay(booksetDate);
            if(endDate.compareTo(booksetDateAdd)!=0&& booksetDate.before(startDate)){
            }else {
                listD.add(dto);
                booksetList.add(dto.getBooksetId());
            }
        }
        if(sumDimen!=null){
            if(sumDimen.equals("04")){//单产品
                sigleProduct(year,declarDate,sumDimen,listD);  
            }else {
                statisticalDimension(year,declarDate,sumDimen,booksetList);
            }
        }else {//默认全产品
            sumDimen = "04";
            statisticalDimension(year,declarDate,sumDimen,booksetList);
        }
        return msg;
    }
    
    /**
     * 根据征期和年份获取时间
     * @param year 年份
     * @param declare 征期
     * @return
     * @throws ParseException
     */
    public Map<String,Object> getDelcareBeginEndDate(String year,String declare) throws ParseException{
        Map<String,Object> map = new HashMap<String,Object>();
//        SimpleDateFormat sim=new SimpleDateFormat("MM-dd"); 
        //征期开始日
        String start = year+"-"+"01-01";
        String end1 = year+"-"+"03-31";
        String end2 = year+"-"+"06-30";
        String end3 = year+"-"+"09-30";
        String end4 = year+"-"+"12-31";
//        //征期
//        Date dateStart = sim.parse(start); 
//        Date dateEnd1 = sim.parse(end1); 
//        Date dateEnd2 = sim.parse(end2); 
//        Date dateEnd3 = sim.parse(end3);
//        Date dateEnd4 = sim.parse(end4); 
        if(declare.equals("01")){//第一征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate", DateUtil.parseDate(end1, "yyyy-MM-dd"));
        }else if(declare.equals("02")){//第二征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate", DateUtil.parseDate(end2, "yyyy-MM-dd"));
        }else if(declare.equals("03")){//第三征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate", DateUtil.parseDate(end3, "yyyy-MM-dd"));
        }else if(declare.equals("04")){//第四征期
            map.put("startDate", DateUtil.parseDate(start, "yyyy-MM-dd"));
            map.put("endDate", DateUtil.parseDate(end4, "yyyy-MM-dd"));
        }
        return map;
    } 
    
    /**
     * 增加一天
     * @param date
     * @return
     */
    public Date addOneDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,1);
        date =cal.getTime();
        return date;
    }
    
    public Page<ValueAddedTax001Dto> selectSum(Map<String, Object> pageParam) {
        String sumDimen = (String)pageParam.get("sumDimen");
        if(sumDimen==null||sumDimen.equals("")){//默认全产品
            sumDimen = "01";
            pageParam.put("sumDimen", sumDimen);
        }
        if(sumDimen.equals("01")){
            pageParam.put("name", "全产品");
        }else if(sumDimen.equals("02")){
            pageParam.put("name", "保本");
        }else if(sumDimen.equals("03")){
            pageParam.put("name", "非保本");
        }else{
            return new Page<ValueAddedTax001Dto>(new ArrayList<ValueAddedTax001Dto>());     
        }
        return new Page<ValueAddedTax001Dto>(          
            valueAddedTax001Mapper.selectSum(pageParam));
    }
}
