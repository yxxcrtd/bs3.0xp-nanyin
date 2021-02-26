/**
 * 
 */
package com.joyintech.service.portfolio.feepay.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.feepay.dao.InvestFeePayMapper;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayFeeDetailDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayPositionDetailDto;
import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigDto;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.product.dto.FinProductExpireBaseTrdDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SubjectBalance;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author malinlin
 */
@Service("backInvestFeePayService")
public class InvestFeePayService extends BaseService<InvestFeePayDto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePayMapper investFeePayMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * {@linkplain}
     */
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstFeeinfoMapper mstFeeinfoMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private MapperDelegateService mapperDelegateService;

    /**
     * {@linkplain}
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    /**
     * {@linkplain}
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;


    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param page page
     * @param length length
     * @param map map
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<InvestFeePayDto> selectForPage(int page, int length, Map<String, Object> map)
        throws Exception {
        map.put("pendingStatus", DictCodeListExt1.有效状态_流程中.getValue());
        map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        map.put("cancleStatus", DictCodeListExt1.有效状态_撤销.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("yesFlag", DictCodeList.是否_是.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("isClean", DictCodeList.是否_否.getValue());
        map.put("TG_BUSI_TYPE", "142");
        map.put("feeType06", DictCodeList.费用品种类型_交易手续费.getValue());
        map.put("feeType07", DictCodeList.费用品种类型_结算服务费.getValue());
        map.put("feeType14", DictCodeList.费用品种类型_佣金.getValue());
//        Page<InvestFeePayDto> pageDto = super.queryForPage(page, length, () -> this.selectForList(map));
        List<InvestFeePayDto> list = selectForList(map);
        sort(list, (String)map.get("key"), (String)map.get("order"),InvestFeePayDto.class);
        Page<InvestFeePayDto> pageDto = new Page<>();
        pageDto.setRecords(list.size());
        if((page-1) * length > list.size()){
            page = 1;
        }
        pageDto.setPage(page);
        pageDto.setRoot(list.subList((page-1) * length, Math.min(list.size(),page * length)));
        return pageDto;
    }
    
    /**
     * 排序
     * @param list  排序的list
     * @param key   排序关键字
     * @param order 升降序
     * @param clazz 类
     * @param <T>
     */
    private <T> void sort(List<T> list, String key, String order, Class<T> clazz) throws Exception {
        if(StringUtil.isEmpty(key)){
            return ;
        }
        if(list == null || list.size() < 2){
            return ;
        }
        Field field = clazz.getDeclaredField(key);
        field.setAccessible(true);
        list.sort((t1,t2) -> lambdaWrapper(order, field, t1, t2));
    }

    /**
     * 包装lambda
     */
    private <T> int lambdaWrapper(String order, Field field, T t1, T t2) {
        try {
            Object o1 = field.get(t1);
            Object o2 = field.get(t2);
            if(o1 == null && o2 == null) {
                return 0;
            }
            if(o1 == null) {
                return "asc".equals (order)  ? 1 : -1;
            }
            if(o2 == null) {
                return "asc".equals (order)  ? -1 : 1;
            }
            if(o1 instanceof Comparable && o2 instanceof Comparable){
                return "asc".equals (order)  ? ((Comparable) o1).compareTo(o2) : ((Comparable) o2).compareTo(o1);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据条件查询数据
     *
     * @param map map
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    private List<InvestFeePayDto> selectForList(Map<String, Object> map) {
        if(StringUtil.equals(map.get("status"),"02")){
            return listAdd(new ArrayList<InvestFeePayDto>(), map);
        }
        List<InvestFeePayDto> list = investFeePayMapper.selectForPage(map);
        if(StringUtil.isEmpty((String)map.get("status"))){
            list = listAdd(list, map);
        }
        return list;
    }

    /**
     * 添加待处理数据
     * @return
     */
    private List<InvestFeePayDto> listAdd(List<InvestFeePayDto> list, Map<String, Object> param) {
        Date batchDate = iPlatformDate.getSysDate();
        if(StringUtil.isNotEmpty((String)param.get("feeType")) && !StringUtil.equals(DictCodeList.费用品种类型_账户维护费.getValue(),param.get("feeType"))){
            return list;
        }
        if(StringUtil.isNotEmpty((String)param.get("paydate"))){
            String payDateStart = (String)param.get("payDateStart");
            String payDateEnd = (String)param.get("payDateEnd");
            if(DateUtil.compareDate(batchDate,DateUtil.parse(payDateStart)) < 0 || DateUtil.compareDate(batchDate,DateUtil.parse(payDateEnd)) > 0){
                return list;
            }
        }
        
        // 生成待办数据 update qz begin
        List<InvestFeePayDto> newlist =  new ArrayList<>();
        // 1 获取需要生成预提交易类标的的配置信息
        List<TrdAccountmfConfigDto> taclists = investFeePayMapper.selectTACInfoByDate(DateUtil.format(batchDate, "yyyy-MM-dd"));
        for(TrdAccountmfConfigDto dto : taclists) {
            String org = dto.getRegistOrg();
        	String secAcctIdStr = (String)param.get("secAcctId");
        	if(StringUtil.isEmpty(secAcctIdStr) || (StringUtil.isNotEmpty(secAcctIdStr) && StringUtil.equals(dto.getSecAcctId(),secAcctIdStr)) ){
        		// 2 获取费用上次支付的计提结束日
        		Map<String, Object> mapPay = new HashMap<String, Object>();
        		mapPay.put("isCancel", DictCodeList.是否_否.getValue());
        		mapPay.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        		mapPay.put("secAcctId", dto.getSecAcctId());
        		mapPay.put("payCycle", DictCodeList.频率_不规则.getValue());
        		mapPay.put("flag", DictCodeList.是否_否.getValue());
        		mapPay.put("feeType1", DictCodeListExt1.一次性费用类型_账户维护费.getValue());
                String lastPayDateStr = investFeePayMapper.queryLastPayDate(mapPay);
        		// 2.1 如果未支付则为计提开始日
        		Date lastPayDate = null;
        		if(StringUtil.isEmpty(lastPayDateStr)) {
        			lastPayDate = dto.getPositionStartDate();
        		}else {
        			lastPayDate = DateUtil.parse(lastPayDateStr);
        		}
                // 3 查询账务明细(大于上次支付日小于等于平台日)
        		Map<String, Object> mapBok = new HashMap<>();
        		mapBok.put("secAcctId",dto.getSecAcctId());
        		mapBok.put("startDate",lastPayDate);
        		mapBok.put("endDate",batchDate);
        		mapBok.put("feeType",DictCodeListExt1.一次性费用类型_账户维护费.getValue());
                List<BokDetailId> bokDetailIds = investFeePayMapper.selectFinDetail2(mapBok);
                if(bokDetailIds != null && bokDetailIds.size() != 0){
                	for(BokDetailId detail : bokDetailIds){
                		// 4 判断是否满足几号
                		Date payDate = DateUtil.setDays(detail.getMdate(), Integer.parseInt(dto.getPayDate()));
                		if(payDate.compareTo(batchDate) > 0) {
                			continue;
                		}
                		
                		// 5 判断待处理是否已在流程中
                		Map<String, Object> map = new HashMap<>();
                		map.put("secAcctId",dto.getSecAcctId());
                		map.put("startDate",detail.getVdate());
                		map.put("endDate",detail.getMdate());
                		int count = investFeePayMapper.selectInFlowDataCountByMap(map);
                		if(count > 0) {
                			continue;
                		}
                        BigDecimal balance = BigDecimal.ZERO;
                        map.put("feeType",DictCodeListExt1.一次性费用类型_账户维护费.getValue());
                        List<BokDetailId> bokDetailIdss = investFeePayMapper.selectFinDetail(map);
                        for(BokDetailId d:bokDetailIdss){
                            BigDecimal tmpBalance =  this.getSubjectBalance(d.getBooksetId(),d.getBokDetailId(),d.getMdate(),d.getMdate(),org);
                            balance = ArithmeticUtil.doAdd(balance, tmpBalance);
                        }
                        //BigDecimal balance =  this.getSubjectBalance(detail.getBooksetId(),detail.getBokDetailId(),detail.getMdate(),detail.getMdate(),org);
                    	InvestFeePayDto feePayDto = new InvestFeePayDto();
                        feePayDto.setSecAcctId(dto.getSecAcctId()); //证券账户
                        feePayDto.setFeeType1(DictCodeList.费用品种类型_账户维护费.getValue());
                        feePayDto.setFeeName(DictCodeList.费用品种类型_账户维护费.getCodeName());
                        feePayDto.setPositionStartDate(detail.getVdate());
                        feePayDto.setPositionEndDate(detail.getMdate());
                        feePayDto.setPayDate(batchDate);
                        feePayDto.setFeeAmt(balance);
                        feePayDto.setEffectStatus(DictCodeListExt1.有效状态_待处理.getValue());
                        newlist.add(feePayDto);
                	}
                }
        	}
        }
        newlist = transform(newlist);
        list.addAll(newlist);
        return list;
        // 生成待办数据 update qz end
        
//        List<InvestFeePayDto> newlist =  new ArrayList<>();
//        //上季度最后一天
//        Date lastDay = getLastDayQuarter(batchDate, -3);
//        //1 获取需要生成预提交易类标的的配置信息
//        List<TrdAccountmfConfigDto> taclists = investFeePayMapper.selectTACInfoByDate(DateUtil.format(batchDate, "yyyy-MM-dd"));
//        for(TrdAccountmfConfigDto dto : taclists) {
//            Date minDate =  DateUtil.addDay(getFirstDayQuarter(dto.getPositionStartDate(), 3), Integer.valueOf(dto.getPayDate()));
//            //平台日大于等于最小支付日
//            if (DateUtil.compareDate(batchDate, minDate) >= 0) {
//                String secAcctIdStr = (String)param.get("secAcctId");
//                if(StringUtil.isEmpty(secAcctIdStr) || (StringUtil.isNotEmpty(secAcctIdStr) && StringUtil.equals(dto.getSecAcctId(),secAcctIdStr)) ){
//                    String lastPayDateStr = this.queryLastPayDate(dto.getSecAcctId(),lastDay);
//                    Date lastPayDate = DateUtil.parse(lastPayDateStr);
//                    if(StringUtil.isEmpty(lastPayDateStr)){
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("secAcctId", dto.getSecAcctId());
//                        map.put("org", this.querySecAcctOrg(dto.getSecAcctId()));
//                        map.put("cdate", DateUtil.getSysDate());
//                        lastPayDate = DateUtil.parse(investFeePayMapper.queryConfigStartDate(map));
//                    }
//                    //判断是否已经生成有效数据
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("secAcctId",dto.getSecAcctId());
//                    map.put("startDate",lastPayDate);
//                    // 上一支付日，所在季度最后一天
//                    Date lastPayDateQuarter = getLastDayQuarter(lastPayDate, 0);
//                    map.put("endDate",DateUtil.addDay(lastPayDateQuarter,1));
//                    if(investFeePayMapper.selectEffectDataCount(map) == 0 && investFeePayMapper.selectInFlowDataCountByMap(map) == 0){
//                        InvestFeePayDto feePayDto = new InvestFeePayDto();
//                        feePayDto.setSecAcctId(dto.getSecAcctId()); //证券账户
//                        feePayDto.setFeeType1(DictCodeList.费用品种类型_账户维护费.getValue());
//                        feePayDto.setFeeName(DictCodeList.费用品种类型_账户维护费.getCodeName());
//                        feePayDto.setPositionStartDate(lastPayDate);
//                        feePayDto.setPositionEndDate(DateUtil.addDay(lastPayDateQuarter,1));
//                        feePayDto.setPayDate(batchDate);
//                        map.put("feeType",DictCodeListExt1.一次性费用类型_账户维护费.getValue());
////                        map.put("startDate",lastPayDate);
//                        List<BokDetailId> bokDetailIds = investFeePayMapper.selectFinDetail(map);
//                        if(bokDetailIds != null && bokDetailIds.size() != 0){
//                            BigDecimal balance = BigDecimal.ZERO;
//                            for(BokDetailId detail : bokDetailIds){
//                                BigDecimal tmpBalance =  this.getSubjectBalance(detail.getBooksetId(),detail.getBokDetailId(),lastPayDateQuarter,lastPayDateQuarter);
//                                balance = ArithmeticUtil.doAdd(balance, tmpBalance);
//                            }
//                            feePayDto.setFeeAmt(balance);
//                            //feePayDto.setActPayAmt(balance);
//                            feePayDto.setEffectStatus(DictCodeListExt1.有效状态_待处理.getValue());
//                            newlist.add(feePayDto);
//                        }
//                    }
//                }
//            }
//        }
//        newlist = transform(newlist);
//        list.addAll(newlist);
//        return list;
    }

    /**
     * 通过证券账户和计提开始日，计提结束日进行分组
     * 取分组后的第一条数据
     * @param listdto  原list
     * @return
     */
    private List<InvestFeePayDto> transform(List<InvestFeePayDto> listdto){
        if(listdto == null || listdto.size() < 2){
            return listdto;
        }
        List<InvestFeePayDto> listnew = new ArrayList<>();
        Map<String, List<InvestFeePayDto>> map = listdto.stream().collect(Collectors.groupingBy(dto -> dto.getSecAcctId() + "$" + DateUtil.format(dto.getPositionStartDate()) +"$"+DateUtil.format(dto.getPositionEndDate()),LinkedHashMap::new,Collectors.toList()));
        for (Map.Entry<String,List<InvestFeePayDto>> entry: map.entrySet()) {
            List<InvestFeePayDto> list = entry.getValue();
            listnew.add(list.get(0));
        }
        return listnew;
    }

    /**
     * 调用公用方法：【科目余额查询 - 期待方向】，获取科目余额信息中的科目余额数据
     * @param booksetId
     * @param happenDate
     * @param bookDate
     * @return
     */
    private BigDecimal getSubjectBalance(String booksetId,String bokDetailId, Date happenDate, Date bookDate ,String org){
        // 塞入要查询的基础科目性质
        List<String> baseSubjectNatureList = new ArrayList<>();
        if("01".equals(org)){
            baseSubjectNatureList.add("ZZYTFY");
        }else if("02".equals(org)){
            baseSubjectNatureList.add("SQYTFY");
        }
//        baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.预提费用.getValue());

        //设值科目余额查询参数Dto
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetId);
        searchDto.setBokDetailId(bokDetailId);
        searchDto.setHappenDate(happenDate);
        searchDto.setBookDate(bookDate);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        // 调用公用方法：【科目余额查询-期待方向 】,获取科目余额信息
        List<SubjectBalance> balanceList = this.getSubjectBalanceExpectDirection(
                searchDto, DictCodeList.余额方向_贷方.getValue());

        BigDecimal balance = BigDecimal.ZERO;
        if(balanceList != null && !balanceList.isEmpty()){
            balance = balanceList.get(0).getBalance();
        }
        return balance;
    }

    /**
     * 科目余额查询 - 期待方向
     *
     * @param searchDto 科目余额查询参数Dto
     * @param expBalanceDirect 期待余额方向
     * @return List<SubjectBalance> 科目余额信息List
     * @see
     * @since 1.0
     */
    public List<SubjectBalance> getSubjectBalanceExpectDirection(SubjectBalanceSearchDto searchDto,
                                                                 String expBalanceDirect) {

        // 调用共通方法：科目余额查询
        List<SubjectBalance> subjectBalanceList = this.getSubjectLatestBalance(searchDto);

        // 循环科目余额，更新相关信息
        for (SubjectBalance vo : subjectBalanceList) {
            // 获取余额方向
            String actBalanceDirect = vo.getBalFlagHappenDate();
            // 获取科目余额
            BigDecimal actBalance = (vo.getBalance() != null) ? vo.getBalance() : BigDecimal.ZERO;
            // 获取本币科目余额
            BigDecimal bactBalance = (vo.getbAmt() != null) ? vo.getbAmt() : BigDecimal.ZERO;
            // 获取数量余额
            BigDecimal actnum = vo.getNum();

            // 调用共通方法：余额方向处理_期待方向
            Map<String, Object> directionInfo = this.balanceDirectDealExpectDirection(
                    expBalanceDirect, actBalanceDirect, actBalance, actnum);

            // 调用共通方法：余额方向处理_期待方向
            Map<String, Object> directionInfoben = this.balanceDirectDealExpectDirection(
                    expBalanceDirect, actBalanceDirect, bactBalance, actnum);
            // 获取原币返回值余额
            BigDecimal balance = directionInfo.get(
                    "balance") == null ? BigDecimal.ZERO : (BigDecimal)directionInfo.get("balance");
            // 获取本币返回值余额
            BigDecimal benbalance = directionInfoben.get(
                    "balance") == null ? BigDecimal.ZERO : (BigDecimal)directionInfoben.get("balance");
            // 获取返回值数量
            BigDecimal num = directionInfo.get(
                    "num") == null ? BigDecimal.ZERO : (BigDecimal)directionInfo.get("num");

            // 塞入科目余额信息中
            vo.setBalance(balance);// 原币科目余额
            vo.setbAmt(benbalance);// 本币科目余额
            vo.setNum(num);// 数量余额
            vo.setBalFlagHappenDate(expBalanceDirect);// 余额方向
        }

        return subjectBalanceList;
    }

    /**
     * 科目余额查询共通--不需要考虑分支特殊处理
     *
     * @return List<SubjectBalance> 结果集
     * @param searchDto 科目余额查询参数Dto
     * @see
     * @since 1.0
     */
    public List<SubjectBalance> getSubjectLatestBalance(SubjectBalanceSearchDto searchDto) {
        // 定义传递参数
        // 若分支序号为空，则查询默认的分支序号
        if (searchDto.getBranch() == null) {
            searchDto.setBranch(BookConstant.DEFALUT_BRANCH); // 默认分支号
        }
        List<SubjectBalance> list = investFeePayMapper.queryCurrentLatestBalance(searchDto);
        return list;
    }

    /**
     * 公用处理：余额方向处理_期待方向
     *
     * @param expBalanceDirect 期待余额方向
     * @param actBalanceDirect 实际余额方向
     * @param actBalance 实际余额
     * @param actnum 实际数量
     * @return 余额、数量
     * @see
     * @since 1.0
     */
    public Map<String, Object> balanceDirectDealExpectDirection(String expBalanceDirect,
                                                                String actBalanceDirect,
                                                                BigDecimal actBalance,
                                                                BigDecimal actnum) {
        // 余额
        BigDecimal balance = BigDecimal.ZERO;
        // 数量
        BigDecimal num = BigDecimal.ZERO;
        // 空指针排除
        actBalance = actBalance == null ? BigDecimal.ZERO : actBalance;
        actnum = actnum == null ? BigDecimal.ZERO : actnum;

        if (expBalanceDirect.equals(DictCodeList.余额方向_借方.getValue())) {
            if (actBalanceDirect.equals(DictCodeList.余额方向_借方.getValue())) {
                balance = actBalance;
                num = actnum;
            }
            if (actBalanceDirect.equals(DictCodeList.余额方向_贷方.getValue())) {
                balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
            }
        }

        if (expBalanceDirect.equals(DictCodeList.余额方向_贷方.getValue())) {
            if (actBalanceDirect.equals(DictCodeList.余额方向_贷方.getValue())) {
                balance = actBalance;
                num = actnum;
            }
            if (actBalanceDirect.equals(DictCodeList.余额方向_借方.getValue())) {
                balance = ArithmeticUtil.multiply(actBalance, new BigDecimal(-1));
                num = ArithmeticUtil.multiply(actnum, new BigDecimal(-1));
            }
        }

        // 定义返回值
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("balance", balance);// 余额
        params.put("num", num);// 数量

        return params;
    }

    /**
     * 获取月份
     * @param date
     * @return
     */
    public String getMonth(Date date){
        return String.valueOf(DateUtil.getMonth(DateUtil.format(date, "yyyy-MM-dd")));
    }

    /**
     * 上季度第一天日期
     * @param date
     * @return
     */
    public Date getFirstDayOfQuarter(Date date) throws ParseException {
        //取得日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-3); // 日历减3个月,即上季度
        return DateUtil.getFirstDayOfQuarter(calendar.getTime());
    }

    /**
     * 取得季度最后一天日期
     * @param date 日期
     * @return
     */
    public Date getFirstDayQuarter(Date date, int addMonths){
        //取得日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonths); // 月份改变
        int year = calendar.get(Calendar.YEAR);// 年份
        int remainder = calendar.get(Calendar.MONTH) % 3;
        int month = calendar.get(Calendar.MONTH) - remainder + 1;
        String mmdd = String.format("%02d", month) + "-01";// 月/日
        String lastDate = year + "-" + mmdd;
        return DateUtil.parse(lastDate) ;
    }

    /**
     * 取得季度最后一天日期
     * @param date 日期
     * @return
     */
    public Date getLastDayQuarter(Date date, int addMonths){
        //取得日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonths); // 月份改变
        int year = calendar.get(Calendar.YEAR);// 年份
        int mouth = calendar.get(Calendar.MONTH) + 1;// 月份
        String mmdd = "";// 月/日
        // 根据月份,判断是哪个季度
        switch( mouth){
            case 1: case 2: case 3:
                mmdd = "03-31"; // 第一季度最后一天是03-31
                break;
            case 4: case 5: case 6:
                mmdd = "06-30"; // 第二季度最后一天是06-30
                break;
            case 7: case 8: case 9:
                mmdd = "09-30";// 第三季度最后一天是09-30
                break;
            case 10: case 11: case 12:
                mmdd = "12-31";// 第四季度最后一天是12-31
                break;
        }
        String lastDate = year + "-" + mmdd;
        return DateUtil.parse(lastDate) ;
    }

    /**
     * 获取季度第一个月
     * @param date
     * @return
     */
    public int getNowQuarterly(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int remainder = calendar.get(Calendar.MONTH) % 3;
        int month = calendar.get(Calendar.MONTH) - remainder + 1;
        return month;
    }
    /**
     * 证券账户联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String param) {
        return investFeePayMapper.getSecAcctId(param);
    }

    /**
     * 查询证券账户下产品关联的费用（不包含已有付费计划）
     * 
     * @param map 参数
     * @return 费用信息
     */
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map) {
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("chargeType_01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("chargeType_02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType", DictCodeList.现金流类型_费用支付.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("feeType06", DictCodeList.费用品种类型_交易手续费.getValue());
        map.put("feeType07", DictCodeList.费用品种类型_结算服务费.getValue());
        map.put("feeType14", DictCodeList.费用品种类型_佣金.getValue());
        return investFeePayMapper.queryFeeInfoList(map);
    }

    /**
     * 查询证券账户下产品关联的费用
     * 
     * @param map 参数
     * @return 费用信息
     */
    public List<SelectOptionDto> queryFeeInfoListEx(Map<String, Object> map) {
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType", DictCodeList.现金流类型_费用支付.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("profitType02", DictCodeList.收益类型_收益型.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("feeType06", DictCodeList.费用品种类型_交易手续费.getValue());
        map.put("feeType07", DictCodeList.费用品种类型_结算服务费.getValue());
        map.put("feeType14", DictCodeList.费用品种类型_佣金.getValue());
        return investFeePayMapper.queryFeeInfoListEx(map);
    }
    // /**
    // * 查询组合下产品关联的费用
    // * @param map 参数
    // * @return 费用信息
    // */
    // public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map){
    // map.put("cashType1_tax", DictCodeListExt1.现金流一级类型_税额.getValue());
    // map.put("cashType1_fee", DictCodeListExt1.现金流一级类型_费用.getValue());
    // map.put("relationProd", DictCodeList.关联范围_产品.getValue());
    // map.put("chargeType", DictCodeList.收费类型_不规则费用.getValue());
    // return investFeePayMapper.queryFeeInfoList(map);
    // }

    /**
     * 查询费用待支付费用
     * 
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param regTrustOrg 登记托管机构
     * @return 待支付费用
     */
    public BigDecimal queryUnpayPosition(String secAcctId, String feeType, String cdate, String regTrustOrg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        map.put("feeType", feeType);
        map.put("cdate", cdate);
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("org", regTrustOrg);
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_经手费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_经手费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_结算费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_账户维护费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_账户维护费.getValue());
        }
        return investFeePayMapper.queryUnpayPosition(map);
    }

    public BigDecimal queryPayTradeAmt(String secAcctId, String org, String cdate, String startDate,String feeType,String confirmFlag) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        map.put("cdate", cdate);
        map.put("feeType", feeType);
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("userId",SecurityUtil.getLoginUser().getUid());
        map.put("authorityType", CacheUtil.getSysParam("AUTHORITY_TYPE"));
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("org", org);
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_经手费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_经手费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_结算费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_账户维护费.getValue())) {
            BigDecimal balance = BigDecimal.ZERO;
            if(StringUtil.isNotEmpty(secAcctId) && StringUtil.isNotEmpty(startDate) && StringUtil.isNotEmpty(cdate)) {
                map.put("startDate", DateUtil.parse(startDate));
                if (StringUtil.isNotEmpty(confirmFlag) && StringUtil.equals(confirmFlag, "confirm") ) {
                    map.put("endDate", DateUtil.parse(cdate));
                }
                List<BokDetailId> bokDetailIds = investFeePayMapper.selectFinDetail(map);
                for (BokDetailId detail : bokDetailIds) {
                    BigDecimal tmpBalance = this.getSubjectBalance(detail.getBooksetId(), detail.getBokDetailId(), DateUtil.parse(cdate), DateUtil.parse(cdate),org);
                    balance = ArithmeticUtil.doAdd(balance, tmpBalance);
                }
            }
            return balance;
        }
        BigDecimal pay = investFeePayMapper.queryPayFeeAmtByPosition(map);
        BigDecimal payed = BigDecimal.ZERO;
//            investFeePayMapper.queryPayed(map); 
        if (pay != null && payed != null) {
            return pay.subtract(payed);
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 查询是否有小于当前日期的待处理的计划
     * 
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    public int checkUndoPlanExists(String cashId, String cashNum) {
        return investFeePayMapper.checkUndoPlanExists(cashId, cashNum);
    }

    /**
     * 根据现金流代码和到期日查询具体付费计划
     * 
     * @param map 参数
     * @return 付费计划
     */
    public InvestFeePayDto queryPendingFeeInfoByCashId(Map<String, Object> map) {
        map.put("pendingStatus", DictCodeListExt1.有效状态_流程中.getValue());
        map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType2fix", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        map.put("cashType2trust", DictCodeListExt1.现金流二级类型_托管费.getValue());
        map.put("cashType2sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("cashType2float", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("cashType2wf", DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("acctType_trust", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        map.put("acctType1_fix", DictCodeListExt1.资金账户类型_产品管理费账户.getValue());
        map.put("acctType1_trust", DictCodeListExt1.资金账户类型_产品托管费账户.getValue());
        map.put("acctType1_sale", DictCodeListExt1.资金账户类型_产品销售费账户.getValue());
        map.put("isClean", DictCodeList.是否_否.getValue());
        map.put("businessType", DictCodeList.共通业务类型_产品费用支付管理.getValue());

        return investFeePayMapper.queryPendingFeeInfoByCashId(map);
    }

    /**
     * 查询待支付费用
     * 
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param finprodId 金融产品代码
     * @param branch 分支序号
     * @return 待支付费用
     */
    public BigDecimal queryFeeAmtByFeeType(String feeType, String cdate, String finprodId, String branch) {
        return investFeePayMapper.queryFeeAmtByFeeType(feeType, cdate, finprodId, branch);
    }
    
    /**
     * 组合费用明细列表
     * @param secAcctId
     * @param org
     * @param cdate
     * @param feeType
     * @return
     * @see
     * @since 1.0
     */
    public List<InvestFeePayPositionDetailDto> queryPayFeeInfoList(String secAcctId, String org, String cdate,String startDate, String feeType,String confirmFlag) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        map.put("cdate", cdate);
        map.put("feeType", feeType);
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("org", org);
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_经手费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_经手费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_结算费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_账户维护费.getValue())) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("feeType",DictCodeListExt1.一次性费用类型_账户维护费.getValue());
            map2.put("secAcctId",secAcctId);
            map2.put("startDate",DateUtil.parse(startDate));
            if (StringUtil.isNotEmpty(confirmFlag) && StringUtil.equals(confirmFlag, "confirm") ) {
                map2.put("endDate",DateUtil.parse(cdate));
            }
            List<BokDetailId> bokDetailIds= investFeePayMapper.selectFinDetail(map2);
            List<InvestFeePayPositionDetailDto> feeList = new ArrayList<>();

            Map<String, List<BokDetailId>> transformMap = bokDetailIds.stream().collect(Collectors.groupingBy(dto -> dto.getBusiId(),LinkedHashMap::new,Collectors.toList()));
            for(Map.Entry<String, List<BokDetailId>> entry : transformMap.entrySet()){
                BigDecimal balance = BigDecimal.ZERO;
                InvestFeePayPositionDetailDto feePay = new InvestFeePayPositionDetailDto();
                feePay.setPtlId(entry.getKey());
                List<BokDetailId> list = entry.getValue();
                for(BokDetailId detail : list){
                    BigDecimal tmpBalance =  this.getSubjectBalance(detail.getBooksetId(),detail.getBokDetailId(),DateUtil.parse(cdate),DateUtil.parse(cdate),org);
                    balance = ArithmeticUtil.doAdd(balance, tmpBalance);
                    feePay.setPortFolioName(detail.getBusiDetailType());
                }
                feePay.setPayAmt(balance);
                feePay.setActPayAmt(balance);
                feePay.setAdjustAmt(BigDecimal.ZERO);
                feePay.setSecAcctId(secAcctId);
                feeList.add(feePay);
            }

//            for(BokDetailId detail : bokDetailIds){
//                BigDecimal tmpBalance =  this.getSubjectBalance(detail.getBooksetId(),detail.getBokDetailId(),DateUtil.parse(cdate),DateUtil.parse(cdate));
//                InvestFeePayPositionDetailDto feePay = new InvestFeePayPositionDetailDto();
//                feePay.setPayAmt(tmpBalance);
//                feePay.setActPayAmt(tmpBalance);
//                feePay.setAdjustAmt(BigDecimal.ZERO);
//                feePay.setPtlId(detail.getBusiId());
//                feePay.setPortFolioName(detail.getBusiDetailType());
//                feePay.setSecAcctId(secAcctId);
//                feeList.add(feePay);
//            }
            return feeList;
        }
        List<InvestFeePayPositionDetailDto> feeList = investFeePayMapper.queryPayFeeInfoList(map);
        return feeList;
    }

    /**
     * 费用明细查看
     * @param feeType 费用类型
     * @param startDate 计提开始日
     * @param enddate 计提结束日
     * @param secAcctId 证券账户代码
     * @param ptlId 组合代码
     * @return 费用明细
     */
    public List<InvestFeePayPositionDetailDto> queryFeePositionDetail(String feeType, String startDate, String enddate, 
                                                                      String secAcctId, String ptlId,String confirmFlag,String tradeId,String org) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("feeType", feeType);
        map.put("startDate", startDate);
        map.put("enddate", enddate);
        map.put("secAcctId", secAcctId);
        map.put("ptlId", ptlId);
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("IS_CANCEL_N", DictCodeListExt1.是否_否.getValue());
        map.put("FINPROD_TYPE2_C11", DictCodeListExt1.金融产品类型_交易费用.getValue());
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_经手费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_经手费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_结算费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算费.getValue());
        }else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_账户维护费.getValue())) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("feeType",DictCodeListExt1.一次性费用类型_账户维护费.getValue());
            map2.put("secAcctId",secAcctId);
            map2.put("startDate",DateUtil.parse(startDate));
            if (StringUtil.isNotEmpty(confirmFlag) && StringUtil.equals(confirmFlag, "confirm") ) {
                map2.put("endDate", DateUtil.parse(enddate));
            }
            if(StringUtil.isNotEmpty(tradeId)){
                String type = this.inputDataType(tradeId);
                String typeWfl = investFeePayMapper.inputWflDataType(tradeId);
                if(StringUtil.equals(DictCodeListExt1.生成方式_批处理生成.getValue(),type) || StringUtil.equals(DictCodeListExt1.生成方式_批处理生成.getValue(),typeWfl)){
                    map2.put("endDate", DateUtil.parse(enddate));
                }
            }
            map2.put("ptlId", ptlId);
            List<BokDetailId> bokDetailIds= investFeePayMapper.selectFinDetail(map2);
            List<InvestFeePayPositionDetailDto> feeList = new ArrayList<>();
            for(BokDetailId detail : bokDetailIds){
                BigDecimal tmpBalance =  this.getSubjectBalance(detail.getBooksetId(),detail.getBokDetailId(),DateUtil.parse(enddate),DateUtil.parse(enddate),org);
                InvestFeePayPositionDetailDto feePay = new InvestFeePayPositionDetailDto();
                feePay.setPtlId(ptlId);
                feePay.setPortFolioName(detail.getBusiDetailType());
                feePay.setTradeType(DictCodeListExt1.金融产品类型_预提费用.getValue());
                feePay.setPs(DictCodeListExt1.金融产品类型_交易费用.getValue());
                feePay.setAssetCode(ptlId);
                feePay.setAssetName(detail.getBusiDetailType());
                feePay.setTradeAmt(tmpBalance);
                feePay.setTradeDate(enddate);
                feePay.setFeeType(DictCodeListExt1.一次性费用类型_账户维护费.getValue());
                feePay.setPayAmt(tmpBalance);
                feePay.setSecAcctId(secAcctId);
                feeList.add(feePay);
            }
            return feeList;
        }
        return investFeePayMapper.queryFeePositionDetail(map);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeeDetailByTradeId(String tradeId) {
        return investFeePayMapper.queryFeeDetailByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询组合交易
     * 
     * @param tradeId 交易编号
     * @return 组合交易
     */
    public List<PtlTrade> queryPtlTradeByTradeId(String tradeId) {
        return investFeePayMapper.queryPtlTradeByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    public List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTradeId(String tradeId) {
        return investFeePayMapper.queryTrdTradeFeeDetailByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlId(Map<String, Object> map) {
        // map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return investFeePayMapper.queryFeePositionByPtlId(map);
    }

    /**
     * 根据交易编号查询费用明细信息(保护焊分层)
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlLayeredId(Map<String, Object> map) {
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());

        List<InvestFeePayFeeDetailDto> list = investFeePayMapper.queryFeePositionByPtlLayeredId(map);

        return list;
    }

    /**
     * 获取产品的规则类型
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public String queryPayCycleByCashId(Map<String, Object> map) {
        String payCycle = investFeePayMapper.queryPayCycleByCashId(map);

        return payCycle;
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlId1(Map<String, Object> map) {
        // map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        // TODO
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        return investFeePayMapper.queryFeePositionByPtlId(map);
    }

    /**
     * 查询费用上次计提结束日
     * 
     * @param ptlId 组合代码
     * @param feeType 费用类型
     * @return 上次计提结束日
     */
    public String queryLastPayDate(String secAcctId, String feeType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("secAcctId", secAcctId);
        map.put("feeType", feeType);
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("flag", DictCodeList.是否_否.getValue());
        map.put("cdate", DateUtil.getSysDate());
        map.put("TG_BUSI_TYPE", "142");
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_经手费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_经手费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeListExt1.费用品种类型_结算费.getValue())){
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_账户维护费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_账户维护费.getValue());
        }
        String lastPayDate = investFeePayMapper.queryLastPayDate(map);
        return lastPayDate;
    }

    /**
     * 查询费用上次计提结束日
     *
     * @return 上次计提结束日
     */
    public String queryLastPayDate(String secAcctId,Date lastDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("secAcctId", secAcctId);
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("flag", DictCodeList.是否_否.getValue());
        map.put("cdate", DateUtil.format(lastDate));
        map.put("feeType1", DictCodeListExt1.一次性费用类型_账户维护费.getValue());
        String lastPayDate = investFeePayMapper.queryLastPayDate(map);
        return lastPayDate;
    }

    /**
     * 账户维护费 查询费用上次计提结束日
     *
     * @return 上次计提结束日
     */
    public String queryStartDate(String secAcctId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        map.put("org", this.querySecAcctOrg(secAcctId));
        map.put("finprodType", DictCodeListExt1.金融产品类型_预提费用.getValue());
        map.put("cdate", DateUtil.getSysDate());
        String lastPayDate = investFeePayMapper.queryStartDate(map);
        return StringUtil.isNotEmpty(lastPayDate) ?  lastPayDate : investFeePayMapper.queryConfigStartDate(map);
    }

    /**
     * 查询累计未结清费用
     * 
     * @param ptlId 组合代码
     * @param feeId 费用代码
     * @param payDate 支付日期
     * @param finprodId 金融产品代码
     * @return 累计未结清费用
     */
    public BigDecimal queryUnCleanFee(String ptlId, String feeId, String payDate, String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ptlId", ptlId);
        map.put("feeId", feeId);
        map.put("finprodId", finprodId);
        map.put("payDate", payDate);
        map.put("isClean", DictCodeList.是否_否.getValue());
        return investFeePayMapper.queryUnCleanFee(map);
    }

    /**
     * 查询 计提开始日
     * 
     * @param finprodId 金融产品代码
     * @param cdate 计算日
     * @return 累计未结清费用
     */
    public Date queryAccrStrDate(String finprodId, String cdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cdate", cdate);
        map.put("CASH_TYPE_1_03", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("CASH_TYPE_2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return investFeePayMapper.queryAccrStrDate(map);
    }

    /**
     * 查询产品剩余现金
     * 
     * @param ptlId 组合代码
     * @param payDate 支付日期
     * @return 剩余现金
     */
    public BigDecimal calResiPrincipal(String ptlId, String payDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        map.put("payDate", payDate);
        return investFeePayMapper.calResiPrincipal(map);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型
     * 
     * @param finprodId 现金流代码
     * @param feeId 现金流序号
     * @return 待处理的计划个数
     */
    public String selectCashType2(String finprodId, String feeId) {
        return investFeePayMapper.selectCashType2(finprodId, feeId);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型(分层)
     * 
     * @param finprodId 现金流代码
     * @param feeId 现金流序号
     * @return 待处理的计划个数
     */
    public String selectLayyerdCashType2(String finprodId, String feeId) {
        return investFeePayMapper.selectLayyerdCashType2(finprodId, feeId);
    }

    public Integer checkpayAmt(Map<String, Object> map) {
        /*
         * map.put("pendingStatus", DictCodeListExt1.有效状态_待处理.getValue()); map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
         * map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue()); map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
         * map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue()); map.put("cashType2fix", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
         * map.put("cashType2trust", DictCodeListExt1.现金流二级类型_托管费.getValue()); map.put("cashType2sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
         * map.put("cashType2float", DictCodeListExt1.现金流二级类型_浮动管理费.getValue()); map.put("cashType2wf", DictCodeListExt1.现金流二级类型_外包服务费.getValue());
         * map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue()); map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
         * map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue()); map.put("profitType", DictCodeList.收益类型_收益型.getValue()); map.put("feeType04",
         * DictCodeList.费用品种类型_浮动差额中收.getValue()); map.put("platDate", iPlatformDate.getSysDate()); map.put("noFlag", DictCodeList.是否_否.getValue());
         * map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue()); map.put("ps", DictCodeList.交易方向_产品付费.getValue()); map.put("acctType_trust",
         * DictCodeListExt1.资金账户类型_产品托管户.getValue()); map.put("acctType1_fix", DictCodeListExt1.资金账户类型_产品管理费账户.getValue()); map.put("acctType1_trust",
         * DictCodeListExt1.资金账户类型_产品托管费账户.getValue()); map.put("acctType1_sale", DictCodeListExt1.资金账户类型_产品销售费账户.getValue()); map.put("isClean",
         * DictCodeList.是否_否.getValue()); map.put("relationProd", DictCodeList.关联范围_产品.getValue()); map.put("relationOther", DictCodeList.关联范围_其他.getValue());
         * map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue()); map.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
         * map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue()); map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue()); map.put("cashType",
         * DictCodeList.现金流类型_费用支付.getValue()); map.put("payCycle", DictCodeList.频率_不规则.getValue()); map.put("feeType04",
         * DictCodeList.费用品种类型_浮动差额中收.getValue()); map.put("profitType02", DictCodeList.收益类型_收益型.getValue());
         */
        map.put("chargeType_01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("ps_C11", DictCodeList.交易方向_产品付费.getValue());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("feeType_03", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeType_04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("feeType_12", DictCodeList.费用品种类型_浮动管理费.getValue());
        return investFeePayMapper.checkpayAmt(map);
    }

    public Integer checkpayAmtrule(Map<String, Object> map) {
        map.put("pendingStatus", DictCodeListExt1.有效状态_待处理.getValue());
        map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType2fix", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        map.put("cashType2trust", DictCodeListExt1.现金流二级类型_托管费.getValue());
        map.put("cashType2sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("cashType2float", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("cashType2wf", DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("acctType_trust", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        map.put("acctType1_fix", DictCodeListExt1.资金账户类型_产品管理费账户.getValue());
        map.put("acctType1_trust", DictCodeListExt1.资金账户类型_产品托管费账户.getValue());
        map.put("acctType1_sale", DictCodeListExt1.资金账户类型_产品销售费账户.getValue());
        map.put("isClean", DictCodeList.是否_否.getValue());
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType", DictCodeList.现金流类型_费用支付.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("profitType02", DictCodeList.收益类型_收益型.getValue());
        map.put("feeType_03", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeType_04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("feeType_12", DictCodeList.费用品种类型_浮动管理费.getValue());
        return investFeePayMapper.checkpayAmtrule(map);
    }

    public InvestFeePayDto selectFeeByTradeId(Map<String, Object> map) {
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        return investFeePayMapper.selectFeeByTradeId(map);
    }

    /**
     * 新建产品费用支付时，根据费用id找到fin_cash_info的cash_id <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public String selectCashId(Map<String, Object> map) {
        return investFeePayMapper.selectCashId(map);
    }

    /**
     * 查询费用信息 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public MstFeeinfo selectFeeId(Map<String, Object> map) {
        return mstFeeinfoMapper.selectByPrimaryKey((String)map.get("feeId"));
    }

    /**
     * 查询产品分层产品
     * 
     * @param finprodId 母产品id
     * @return 子产品列表
     */
    public List<PrdLayerParam> queryPrdLayerListByPid(String finprodId) {
        return investFeePayMapper.queryPrdLayerListByPid(finprodId);
    }

    public String insertBussFlowData(TrdCommonWflParam trdCommonWflParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonWflParam.getWflStlPayOrdersDtos(),
            trdCommonWflParam.getWflTrdFinProductDealTotal(), trdCommonWflParam.getWflPaTrdFinDealTotal(),
            trdCommonWflParam.getWflTrdProductDeals(), trdCommonWflParam.getWflDealAdds(), trdCommonWflParam.getWflPaDeals());
        return "Success";
    }

    public String doComplete(TrdCommonParam trdCommonParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonParam.getTrdTradeFeeDetails(), trdCommonParam.getPtlTrades(),
            trdCommonParam.getTrdFinProductDealTotal());
        return "Success";
    }

    public String insertBatchData(FinProductExpireBaseTrdDto expireBaseTrdDto) {
        mapperDelegateService.insertAutoClassifyBatch(expireBaseTrdDto.getTrdProductDealList(),
            expireBaseTrdDto.getTrdProductDealAddList(), expireBaseTrdDto.getPaTrdProductDealList(),
            expireBaseTrdDto.getTrdTradeFeeDetailList());
        return "Success";
    }

    public String doCompleteInsert(TrdCommonParam trdCommonParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonParam.getPaTrdFinProductDealTotal(),
            trdCommonParam.getTrdProductDeals(), trdCommonParam.getDealAdds(), trdCommonParam.getPaDeals(),
            trdCommonParam.getTrdTradeFeeDetails(), trdCommonParam.getPtlTrades(), trdCommonParam.getTrdFinProductDealTotal(),
            trdCommonParam.getStlPayOrdersDtos());
        return "Success";
    }

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    public String doCompleteRevoke(InvestFeePayDto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(entity.getTradeId());

        // 原始汇总交易的状态改为已撤销
        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
        trdFinProductDealTotalRevoke.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        trdFinProductDealTotalRevoke.setIsCancel(DictCodeList.是否_是.getValue());
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotalRevoke);
        // 原始交易的状态改为已撤销

        trdFinProductDealTotal.setpTradeId(trdFinProductDealTotal.getTotleTradeId());
        trdFinProductDealTotal.setTotleTradeId(
            trdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        TrdFinProductDealTotal trdFinProductDealTotalR = trdFinProductDealTotalMapper.selectByPrimaryKey(trdFinProductDealTotal.getTotleTradeId());
        if (StringUtil.isNotEmpty(trdFinProductDealTotalR)) {
            trdFinProductDealTotalMapper.updateByPrimaryKey(trdFinProductDealTotal); 
        }else {
            trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);
        }
        

        PaTrdFinProductDealTotal patrdFinProductDealTotal = paTrdFinProductDealTotalMapper.selectByPrimaryKey(entity.getTradeId());

        // 原始汇总交易的状态改为已撤销
        if (patrdFinProductDealTotal != null) {
            patrdFinProductDealTotal.setTotleTradeId(
                patrdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            paTrdFinProductDealTotalMapper.insert(patrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易
        List<TrdProductDeal> trdProductDealList = investFeePayMapper.queryTrdProductDealByTotleTradeId(entity.getTradeId());

        if (!trdProductDealList.isEmpty()) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                /**金融产品交易表**/
                TrdProductDeal trdProductDealRevoke = new TrdProductDeal();
                trdProductDealRevoke.setTradeId(trdProductDeal.getTradeId());
                trdProductDealRevoke.setIsCancel(DictCodeList.是否_是.getValue());
                trdProductDealMapper.updateByPrimaryKeySelective(trdProductDealRevoke);
                
                trdProductDeal.setpTradeId(trdProductDeal.getTradeId());
                trdProductDeal.setTradeId(trdProductDeal.getTradeId()+BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                trdProductDeal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
                trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                trdProductDealMapper.insert(trdProductDeal);

                /**金融产品扩展表**/
                PaTrdProductDeal paTrdProductDeal = paTrdProductDealMapper.selectByPrimaryKey(trdProductDeal.getTradeId());
                if (paTrdProductDeal != null) {
                    paTrdProductDeal.setTradeId(
                        paTrdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    paTrdProductDealMapper.insert(paTrdProductDeal);
                }
                
                /**交易附属表 **/
                TrdProductDealAdd trdProductDealAdd = trdProductDealAddMapper.selectByPrimaryKey(trdProductDeal.getTradeId());
                if (trdProductDealAdd != null) {
                    trdProductDealAdd.setTradeId(trdProductDealAdd.getTradeId()
                                                + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    trdProductDealAddMapper.insert(trdProductDealAdd);
                }
            }
        }

        // 划款指令表
        List<StlPayOrders> stlPayOrdersList = investFeePayMapper.queryStlPayOrdersByTradeId(entity.getTradeId());

        if (!stlPayOrdersList.isEmpty()) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                stlPayOrders.setOrderId(stlPayOrders.getOrderId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setpTradeId(stlPayOrders.getOrderId());
                stlPayOrders.setTradeId(entity.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
                
                stlPayOrdersMapper.insert(stlPayOrders);
            }
        }

        // 组合交易表
        List<PtlTrade> ptlTradeList = this.queryPtlTradeByTradeId(entity.getTradeId());
        if (!ptlTradeList.isEmpty()) {
            for (PtlTrade ptlTrade : ptlTradeList) {
                ptlTrade.setBusiNo(ptlTrade.getBusiNo() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                ptlTrade.setCreateUser(entity.getCreateUser());
                ptlTrade.setCreateTime(new Date());
                ptlTrade.setUpdateUser(entity.getCreateUser());
                ptlTrade.setUpdateTime(new Date());
                ptlTradeMapper.insert(ptlTrade);
            }
        }

        return "Success";
    }
    
    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return investFeePayMapper.selectInFlowDataCount(tradeId) > 0;
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isAlreadyRevoked(String tradeId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tradeId", tradeId);
        paramMap.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        paramMap.put("TRADE_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        return investFeePayMapper.selectRevokedDataCount(paramMap) > 0;
    }
    
    /** 根据汇总交易编号划款指令信息
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     */
    public List<StlPayOrders> queryStlPayOrdersByTradeId(String tradeId){
        return investFeePayMapper.queryStlPayOrdersByTradeId(tradeId);
    }
    
    /**
     * 查询证券账户登记托管机构
     * @param secAcctId 证券账户
     * @return 登记托管机构
     * @see
     * @since 1.0
     */
    public String querySecAcctOrg(String secAcctId) {
        return investFeePayMapper.querySecAcctOrg(secAcctId);
    }
    
    /**
     * 根据汇总交易编号查询金融产品交易
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> queryTrdProductDealByTotleTradeId(String tradeId) {
        // 根据汇总交易编号查询金融产品交易
        return investFeePayMapper.queryTrdProductDealByTotleTradeId(tradeId);
    }
    
    /**
     * 根据组合id查询产品信息
     * @param ptlId 组合id
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public InvestFeePayDto queryByPtlId(String ptlId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        return investFeePayMapper.queryByPtlId(map);
    }
    
    public Page<InvestFeePayFeeDetailDto> selectDetail(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        return this.queryForPageForSelectReturn(page,length,() -> investFeePayMapper.selectDetail(pageParam));
    }

    /**
     * 账户维护费，流程生效后更新
     * @param entity
     * @return
     */
    public void updateTrdAccountmfConfigDto(InvestFeePayDto entity) {
        TrdAccountmfConfigDto dto = new TrdAccountmfConfigDto();
        dto.setSecAcctId(entity.getSecAcctId());
        dto.setRegistOrg(entity.getRegTrustOrg());
        dto.setPositionStartDate(entity.getStartDate());
        dto.setActPositionEndDate(entity.getEndDate());
        investFeePayMapper.updateTrdAccountmfConfigDto(dto);
    }

    /**
     * 查询数据的录入方式
     * @param tradeId
     * @return
     */
    public String inputDataType(String tradeId) {
        return investFeePayMapper.inputDataType(tradeId);
    }

    public List<SelectOptionDto> getPortFolioName(Map<String, Object> map) {
        return investFeePayMapper.getPortFolioName(map);
    }

    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public List<InvestFeePayDto> getBusinessData(WflValidInputParameter parameter) {
        parameter.setSeachMode("01");
        List<InvestFeePayDto> list = investFeePayMapper.getBusinessData(parameter);
        return list;
    }
}
