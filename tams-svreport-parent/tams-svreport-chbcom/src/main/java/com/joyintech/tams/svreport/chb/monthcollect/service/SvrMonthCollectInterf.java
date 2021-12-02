package com.joyintech.tams.svreport.chb.monthcollect.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.DictCode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.svreport.chb.datacheck.beforeinsert.service.ChbCommonCheckService;
import com.joyintech.tams.svreport.chb.monthcollect.dao.SvrMonthCollectInterfMapper;
import com.joyintech.tams.svreport.chb.monthcollect.dto.ChbMonthCollect;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrMonthCollectInterf extends BaseService{
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SvrMonthCollectInterfMapper svrMonthCollectInterfMapper;

    /**
     * 入库前校验用Service
     */
    @Autowired
    private ChbCommonCheckService chbCommonCheckService;

    public void generateData(Date batchDate,Date batchDateStart ,Date batchDateEnd) {
    	Date batchEndDate = getFirstDayOfNextMonth(DateUtil.format(batchDateEnd));
		if(svrMonthCollectInterfMapper.checkAllMonth(DateUtil.format(batchDateStart),DateUtil.format(batchEndDate)) == 0){
			logger.info("请先执行明细汇总批处理，再执行月报生成批处理！");
			return;
		}
		if(svrMonthCollectInterfMapper.checkSubmission(DateUtil.format(batchDateStart),DateUtil.format(batchEndDate)) != 0){
			logger.info("已报送的月报，不再生成报送数据！");
			return;
		}
		if(svrMonthCollectInterfMapper.checkLastDay(DateUtil.format(batchDateEnd)) == 0){
			logger.info("本月的明细信息不全，请确认数据是否完善，并请先执行明细汇总批处理！");
		}
		if(svrMonthCollectInterfMapper.checkHold(DateUtil.format(batchDateEnd)) == 0){
			logger.info("当月月末的持有信息不存在，请确认后重新跑批！");
		}
    	//1、开始检数据
    	logger.info("销售月报每月明细汇总信息检索开始------->>>>>>");
		Map<String,Map<String,Map<String,ChbMonthCollect>>> AllMap = this.getList(batchDate,batchDateStart,batchDateEnd);
    	logger.info("销售月报每月明细汇总信息检索结束-------<<<<<<");

    	if(AllMap.size() == 0){
    		logger.info("没有检索到数据,批处理结束!");
    		return;
    	}
//
//		// 调用check逻辑
//		logger.debug("销售结算资金交易信息登记数据检查开始------检查前数据个数：" + list.size());
//		List<ChbDayCollect> checkList = chbCommonCheckService.checkData(list, ChbCheckTableInfo.理财业务销售数据基础汇总表SUM);
//		logger.debug("销售结算资金交易信息登记数据检查结束------检查后数据个数：" + list.size());
//
//		if(StringUtil.isEmptyList(checkList)){
//			logger.info("检查后的数据全部不合规，批处理结束");
//			return;
//		}
		//更新表
    	logger.info("销售月报每月明细汇总信息入库开始------->>>>>>");
    	dbData(AllMap,batchDate,batchDateStart ,batchDateEnd);
    	logger.info("销售月报每月明细汇总信息入库结束-------<<<<<<");
    }
    
    /**
     * 先删后插
     */
    private void dbData(Map<String,Map<String,Map<String,ChbMonthCollect>>> AllMap,Date batchDate,Date batchDateStart ,Date batchDateEnd) {
    	if (AllMap.size() != 0) {
			//获取下个月的第一天
			Date nextMonFirDay = getFirstDayOfNextMonth(DateUtil.format(batchDate));
			//中债报送截止日
			Date chbSubmitDeadline = new Date();
			List<Date> dateList = null;
			Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
			dateList = ChbDeadlineUtil.getChbEndDate(
					DictCodeListExt2.报表名称_销售信息月度统计表.getValue(), "01", dateMap, nextMonFirDay);
			if (!CollectionUtils.isEmpty(dateList)) {
				//获取中债截止日
				chbSubmitDeadline = dateList.get(0);
			}
			int index=DateUtil.format(batchDate).lastIndexOf("-");
			String termid = DateUtil.format(batchDate).substring(0, index);
			int termidIndex=termid.lastIndexOf("-");
			String termidYYYY = termid.substring(0, termidIndex);
			String termidMM = termid.substring(termidIndex+1);
			String reportName = "销售数据月度统计表_"+ termidYYYY + "年" + termidMM + "月";
			//清除当月月报信息 若已报送则停止跑批
			logger.debug("当月汇总数据清除开始----------");
			//查询要删除的reportUUID
			List<String> list = svrMonthCollectInterfMapper.selectReportuuid(termid);
			if(list.size() > 0 && !list.isEmpty()) {
			    // 根据reportuuid删除明细表
				svrMonthCollectInterfMapper.deleteDetail(list);
				//根据期数删除月报主表
				svrMonthCollectInterfMapper.deleteReport(termid);
			}
			logger.debug("当月汇总数据清除结束----------");

            logger.debug("销售月报每月明细汇总信息插入数据：插入开始----------");
			String bankCode ="C88888";

			for(String key:AllMap.keySet()){
            //以投资者分类插入
			if(key.equals("holderType")) {
				String reporttype = "1_1";
				String reportuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
				svrMonthCollectInterfMapper.insertIntoReport(reporttype,DateUtil.format(chbSubmitDeadline),reportuuid,termid,reportName,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
				for (String flag : AllMap.get("holderType").keySet()) {
					for(String A : AllMap.get("holderType").get(flag).keySet()){
						String detailuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
						ChbMonthCollect detail = AllMap.get("holderType").get(flag).get(A);
						detail.setReportuuid(reportuuid);
						detail.setDetailuuid(detailuuid);
						detail.setReporttype("1_1");
						if(detail.getInstitutionCode().equals(bankCode)){
							detail.setIsConsign(DictCodeList.是否_中债_否.getValue());
						}else{
							detail.setIsConsign(DictCodeList.是否_中债_是.getValue());
						}
						svrMonthCollectInterfMapper.insertIntoDetail(detail);
					}
				}
			}
			//以地区分类插入
			if(key.equals("area")) {
				String reporttype = "1_2";
				String reportuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
				svrMonthCollectInterfMapper.insertIntoReport(reporttype,DateUtil.format(chbSubmitDeadline),reportuuid,termid,reportName,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
				for (String flag : AllMap.get("area").keySet()) {
					for(String A : AllMap.get("area").get(flag).keySet()){
						String detailuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
						ChbMonthCollect detail = AllMap.get("area").get(flag).get(A);
						detail.setReportuuid(reportuuid);
						detail.setDetailuuid(detailuuid);
						detail.setReporttype("1_2");
						if(detail.getInstitutionCode().equals(bankCode)){
							detail.setIsConsign(DictCodeList.是否_中债_否.getValue());
						}else{
							detail.setIsConsign(DictCodeList.是否_中债_是.getValue());
						}
						svrMonthCollectInterfMapper.insertIntoDetail(detail);
					}
				}
			}
			//以渠道插入
			if(key.equals("institution")) {
				String reporttype = "1_3";
				String reportuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
				svrMonthCollectInterfMapper.insertIntoReport(reporttype,DateUtil.format(chbSubmitDeadline),reportuuid,termid,reportName,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
				for (String flag : AllMap.get("institution").keySet()) {
					for(String A : AllMap.get("institution").get(flag).keySet()){
						String detailuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
						ChbMonthCollect detail = AllMap.get("institution").get(flag).get(A);
						detail.setReportuuid(reportuuid);
						detail.setDetailuuid(detailuuid);
						detail.setReporttype("1_3");
						if(detail.getInstitutionCode().equals(bankCode)){
							detail.setIsConsign(DictCodeList.是否_中债_否.getValue());
						}else{
							detail.setIsConsign(DictCodeList.是否_中债_是.getValue());
						}
						svrMonthCollectInterfMapper.insertIntoDetail(detail);
					}
				}
			}
            logger.debug("销售月报每月明细汇总信息插入数据：插入结束----------");
		}
    	}
	}

	/**
	 * 检索并处理数据
	 * @param batchDate
	 * @param batchDateStart
	 * @param batchDateEnd
	 * @return
	 */
	public Map<String,Map<String,Map<String,ChbMonthCollect>>> getList(Date batchDate,Date batchDateStart ,Date batchDateEnd) {
		Date batchEndDate = this.getFirstDayOfNextMonth(DateUtil.format(batchDateEnd));
		//汇总  总、净销售金额
		List<ChbMonthCollect> listAmountTotal = svrMonthCollectInterfMapper.selectAmountTotal(DateUtil.format(batchDateStart),DateUtil.format(batchEndDate));
			//获取check表的
			List<ChbMonthCollect> registList = svrMonthCollectInterfMapper.selectHolderNumCheck(DateUtil.format(batchDateEnd));
			//报送表去除check表中识别标识相等的数据后再与check表合并
			List<ChbMonthCollect> List = svrMonthCollectInterfMapper.selectHolderNumExcheck(registList,DateUtil.format(batchDateEnd));

			listAmountTotal.stream().forEach(item -> {
				List.stream().forEach(item1 ->{
					if (item.getPrdRegistCode().equals(item1.getPrdRegistCode()) && item.getInstitutionCode().equals(item1.getInstitutionCode())
							&& item.getAreaCode().equals(item1.getAreaCode()) && item.getHolderType().equals(item1.getHolderType())
							&& item.getCcy().equals(item1.getCcy())){
						item.setBalanceShares(item1.getBalanceShares());
						item.setBalanceAmt(item1.getBalanceAmt());
						item.setCnyAmt(item1.getCnyAmt());
					}
				});
			});
			listAmountTotal = this.currencyExchange(listAmountTotal);

        //取得银行月报信息
		List<ChbMonthCollect >chbPrdPosition = svrMonthCollectInterfMapper.selectChbPrdPosition(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));

		//以投资者类型汇总
		List<ChbMonthCollect >typeHolderNum = svrMonthCollectInterfMapper.typeHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> holderTypeCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getHolderType();
			if (holderTypeCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setInstitutionCode(a.getInstitutionCode());
				bb.setCcy(a.getCcy());
				bb.setIsConsign(a.getIsConsign());
				bb.setAreaCode(a.getAreaCode());
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setHolderType(a.getHolderType());
				bb.setSaleAmountTotal(a.getValueAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getBalanceAmt());
				bb.setBalanceShares(a.getBalanceShares());
				holderTypeCollect.put(falg, bb);
			}else{
				if(a.getSaleAmountTotal() != null) {
					holderTypeCollect.get(falg).setSaleAmountTotal(holderTypeCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
				}
				if(a.getValueAmountTotal() != null){
				holderTypeCollect.get(falg).setValueAmountTotal(holderTypeCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
				}
				if(List.size() != 0) {
					if (a.getBalanceAmt() != null) {
						if(holderTypeCollect.get(falg).getBalanceAmt() != null) {
							holderTypeCollect.get(falg).setBalanceAmt(holderTypeCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
						}else{
							holderTypeCollect.get(falg).setBalanceAmt(a.getBalanceAmt());
						}
					}
					if (a.getBalanceShares() != null) {
						if(holderTypeCollect.get(falg).getBalanceShares() != null) {
							holderTypeCollect.get(falg).setBalanceShares(holderTypeCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
						}else{
							holderTypeCollect.get(falg).setBalanceShares(a.getBalanceShares());
						}
					}
				}
			}
		}
		//赋值投资者数量
		for(String key :holderTypeCollect.keySet()){
			for(int m = 0; m < typeHolderNum.size();m++) {
				String flag = typeHolderNum.get(m).getPrdRegistCode() + ":" + typeHolderNum.get(m).getHolderType();
				if(flag.equals(key)){
					holderTypeCollect.get(flag).setHolderNum(typeHolderNum.get(m).getHolderNum());
				}
			}
		}
		//转换成以产品为key的父级map
		Map<String,Map<String,ChbMonthCollect>> holderTypeCollectMap = new HashMap<String,Map<String,ChbMonthCollect>>();
		for(String key: holderTypeCollect.keySet()){
			int index=key.lastIndexOf(":");
			String proId = key.substring(0, index);
			if(holderTypeCollectMap.get(proId) == null){
				Map<String,ChbMonthCollect> holderTypeMap = new HashMap<String,ChbMonthCollect>();
				holderTypeMap.put(key,holderTypeCollect.get(key));
				holderTypeCollectMap.put(proId,holderTypeMap);
			}
			else {
				holderTypeCollectMap.get(proId).put(key,holderTypeCollect.get(key));
			}
		}
		//处理最大行值
		for(int i =0;i < chbPrdPosition.size() ;i++){
			for(String key :holderTypeCollectMap.keySet()){
				if(chbPrdPosition.get(i).getPrdRegistCode().equals(key)){
					BigDecimal SaleAmountTotalAll = new BigDecimal("0");
					BigDecimal SaleAmountTotalMax = new BigDecimal("0");
					String SaleAmountFlag = "";
					BigDecimal ValueAmountTotalAll = new BigDecimal("0");
					BigDecimal ValueAmountTotalMax = new BigDecimal("0");
					String ValueAmountFlag = "";
					BigDecimal BalanceAmtAll = new BigDecimal("0");
					BigDecimal BalanceAmtMax = new BigDecimal("0");
					String BalanceAmtFlag = "";
					for(String flag:holderTypeCollectMap.get(key).keySet()){
						BigDecimal SaleAmountTotal = holderTypeCollectMap.get(key).get(flag).getSaleAmountTotal();
						BigDecimal ValueAmountTota = holderTypeCollectMap.get(key).get(flag).getValueAmountTotal();
						BigDecimal BalanceAmt = holderTypeCollectMap.get(key).get(flag).getBalanceAmt();
						if(SaleAmountTotal != null) {
							SaleAmountTotalAll = SaleAmountTotalAll.add(SaleAmountTotal);
						}
						if(ValueAmountTota != null) {
							ValueAmountTotalAll = ValueAmountTotalAll.add(ValueAmountTota);
						}
						if(BalanceAmt != null) {
							BalanceAmtAll = BalanceAmtAll.add(BalanceAmt);
						}
						if(SaleAmountTotalMax.compareTo(SaleAmountTotal) == 1){}
						else{
							SaleAmountTotalMax = SaleAmountTotal;
							SaleAmountFlag = flag;
						}
						if(ValueAmountTotalMax.compareTo(ValueAmountTota) == 1){}
						else{
							ValueAmountTotalMax = ValueAmountTota;
							ValueAmountFlag = flag;
						}
						if(BalanceAmt != null) {
							if (BalanceAmtMax.compareTo(BalanceAmt) == 1) {
							} else {
								BalanceAmtMax = BalanceAmt;
								BalanceAmtFlag = flag;
							}
						}
					}
					//处理总销售金额
					if(chbPrdPosition.get(i).getDirtyamtsum() == null){
						chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
					}
					if(SaleAmountFlag != null && SaleAmountFlag != "") {
						BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
						holderTypeCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
					}
					//处理净销售金额
					if(chbPrdPosition.get(i).getCleanamtsum() == null){
						chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
					}
					if(ValueAmountFlag != null && ValueAmountFlag != "") {
						BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
						holderTypeCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
					}
					//处理余额
					if(BalanceAmtFlag != null && BalanceAmtFlag != "") {
						if(chbPrdPosition.get(i).getEndamtsumOth() == null){
							chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
						}
						BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
						holderTypeCollectMap.get(key).get(BalanceAmtFlag).setSaleAmountTotal(BalanceAmtDiff);
					}
				}
			}
		}


		//以地区汇总
		List<ChbMonthCollect >areaHolderNum = svrMonthCollectInterfMapper.areaHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> areaCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getAreaCode();
			if (areaCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setInstitutionCode(a.getInstitutionCode());
				bb.setCcy(a.getCcy());
				bb.setIsConsign(a.getIsConsign());
				bb.setAreaCode(a.getAreaCode());
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setHolderType(a.getHolderType());
				bb.setSaleAmountTotal(a.getValueAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getBalanceAmt());
				bb.setBalanceShares(a.getBalanceShares());
				areaCollect.put(falg, bb);
			}else{
				if(a.getSaleAmountTotal() != null) {
					areaCollect.get(falg).setSaleAmountTotal(areaCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
				}
				if(a.getValueAmountTotal() != null){
					areaCollect.get(falg).setValueAmountTotal(areaCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
				}
				if(List.size() != 0) {
					if (a.getBalanceAmt() != null) {
						if(areaCollect.get(falg).getBalanceAmt() != null) {
							areaCollect.get(falg).setBalanceAmt(areaCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
						}else{
							areaCollect.get(falg).setBalanceAmt(a.getBalanceAmt());
						}
					}
					if (a.getBalanceShares() != null) {
						if(areaCollect.get(falg).getBalanceShares() != null) {
						areaCollect.get(falg).setBalanceShares(areaCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
						}else{
							areaCollect.get(falg).setBalanceShares(a.getBalanceShares());
						}
					}
				}
			}
		}
		//赋值投资者数量
		for(String key :areaCollect.keySet()){
			for(int m = 0; m < areaHolderNum.size();m++) {
				String flag = areaHolderNum.get(m).getPrdRegistCode() + ":" + areaHolderNum.get(m).getHolderType();
				if(flag.equals(key)){
					areaCollect.get(flag).setHolderNum(areaHolderNum.get(m).getHolderNum());
				}
			}
		}
		//转换成以产品为key的父级map
		Map<String,Map<String,ChbMonthCollect>> areaCollectMap = new HashMap<String,Map<String,ChbMonthCollect>>();
		for(String key: areaCollect.keySet()){
			int index=key.lastIndexOf(":");
			String proId = key.substring(0, index);
			if(areaCollectMap.get(proId) == null){
				Map<String,ChbMonthCollect> areaMap = new HashMap<String,ChbMonthCollect>();
				areaMap.put(key,areaCollect.get(key));
				areaCollectMap.put(proId,areaMap);
			}
			else {
				areaCollectMap.get(proId).put(key,areaCollect.get(key));
			}
		}
		//处理最大行值
		for(int i =0;i < chbPrdPosition.size() ;i++){
			for(String key :areaCollectMap.keySet()){
				if(chbPrdPosition.get(i).getPrdRegistCode().equals(key)){
					BigDecimal SaleAmountTotalAll = new BigDecimal("0");
					BigDecimal SaleAmountTotalMax = new BigDecimal("0");
					String SaleAmountFlag = "";
					BigDecimal ValueAmountTotalAll = new BigDecimal("0");
					BigDecimal ValueAmountTotalMax = new BigDecimal("0");
					String ValueAmountFlag = "";
					BigDecimal BalanceAmtAll = new BigDecimal("0");
					BigDecimal BalanceAmtMax = new BigDecimal("0");
					String BalanceAmtFlag = "";
					for(String flag:areaCollectMap.get(key).keySet()){
						BigDecimal SaleAmountTotal = areaCollectMap.get(key).get(flag).getSaleAmountTotal();
						BigDecimal ValueAmountTota = areaCollectMap.get(key).get(flag).getValueAmountTotal();
						BigDecimal BalanceAmt = areaCollectMap.get(key).get(flag).getBalanceAmt();
						if(SaleAmountTotal != null) {
							SaleAmountTotalAll = SaleAmountTotalAll.add(SaleAmountTotal);
						}
						if(ValueAmountTota != null) {
							ValueAmountTotalAll = ValueAmountTotalAll.add(ValueAmountTota);
						}
						if(BalanceAmt != null) {
							BalanceAmtAll = BalanceAmtAll.add(BalanceAmt);
						}
						if(SaleAmountTotalMax.compareTo(SaleAmountTotal) == 1){}
						else{
							SaleAmountTotalMax = SaleAmountTotal;
							SaleAmountFlag = flag;
						}
						if(ValueAmountTotalMax.compareTo(ValueAmountTota) == 1){}
						else{
							ValueAmountTotalMax = ValueAmountTota;
							ValueAmountFlag = flag;
						}
						if(BalanceAmt != null) {
							if (BalanceAmtMax.compareTo(BalanceAmt) == 1) {
							} else {
								BalanceAmtMax = BalanceAmt;
								BalanceAmtFlag = flag;
							}
						}
					}
					//处理总销售金额
					if(chbPrdPosition.get(i).getDirtyamtsum() == null){
						chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
					}
					if(SaleAmountFlag != null && SaleAmountFlag != "") {
						BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
						areaCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
					}
					//处理净销售金额
					if(chbPrdPosition.get(i).getCleanamtsum() == null){
						chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
					}
					if(ValueAmountFlag != null && ValueAmountFlag != "") {
						BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
						areaCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
					}
					//处理余额
					if(BalanceAmtFlag != null && BalanceAmtFlag != ""){
						if(chbPrdPosition.get(i).getEndamtsumOth() == null){
							chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
						}
						BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
						areaCollectMap.get(key).get(BalanceAmtFlag).setSaleAmountTotal(BalanceAmtDiff);
					}
				}
			}
		}

		//以分销渠道汇总
		List<ChbMonthCollect >channelHolderNum = svrMonthCollectInterfMapper.channelHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> institutionCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getInstitutionCode();
			if (institutionCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setInstitutionCode(a.getInstitutionCode());
				bb.setCcy(a.getCcy());
				bb.setIsConsign(a.getIsConsign());
				bb.setAreaCode(a.getAreaCode());
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setHolderType(a.getHolderType());
				bb.setSaleAmountTotal(a.getValueAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getBalanceAmt());
				bb.setBalanceShares(a.getBalanceShares());
				institutionCollect.put(falg, bb);
			}else{
				if(a.getSaleAmountTotal() != null) {
					institutionCollect.get(falg).setSaleAmountTotal(institutionCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
				}
				if(a.getValueAmountTotal() != null){
					institutionCollect.get(falg).setValueAmountTotal(institutionCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
				}
				if(List.size() != 0) {
					if (a.getBalanceAmt() != null) {
						if(institutionCollect.get(falg).getBalanceAmt() != null) {
							institutionCollect.get(falg).setBalanceAmt(institutionCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
						}else{
							institutionCollect.get(falg).setBalanceAmt(a.getBalanceAmt());
						}
					}
					if (a.getBalanceShares() != null) {
						if(institutionCollect.get(falg).getBalanceShares() != null) {
							institutionCollect.get(falg).setBalanceShares(institutionCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
						}else{
							institutionCollect.get(falg).setBalanceShares(a.getBalanceShares());
						}
					}
				}
			}
		}
		//赋值投资者数量
		for(String key :institutionCollect.keySet()){
			for(int m = 0; m < channelHolderNum.size();m++) {
				String flag = channelHolderNum.get(m).getPrdRegistCode() + ":" + channelHolderNum.get(m).getHolderType();
				if(flag.equals(key)){
					institutionCollect.get(flag).setHolderNum(channelHolderNum.get(m).getHolderNum());
				}
			}
		}
		//转换成以产品为key的父级map
		Map<String,Map<String,ChbMonthCollect>> institutionCollectMap = new HashMap<String,Map<String,ChbMonthCollect>>();
		for(String key: institutionCollect.keySet()){
			int index=key.lastIndexOf(":");
			String proId = key.substring(0, index);
			if(institutionCollectMap.get(proId) == null){
				Map<String,ChbMonthCollect> channelMap = new HashMap<String,ChbMonthCollect>();
				channelMap.put(key,institutionCollect.get(key));
				institutionCollectMap.put(proId,channelMap);
			}
			else {
				institutionCollectMap.get(proId).put(key,institutionCollect.get(key));
			}
		}
		//处理最大行值
		for(int i =0;i < chbPrdPosition.size() ;i++){
			for(String key :institutionCollectMap.keySet()){
				if(chbPrdPosition.get(i).getPrdRegistCode().equals(key)){
					BigDecimal SaleAmountTotalAll = new BigDecimal("0");
					BigDecimal SaleAmountTotalMax = new BigDecimal("0");
					String SaleAmountFlag = "";
					BigDecimal ValueAmountTotalAll = new BigDecimal("0");
					BigDecimal ValueAmountTotalMax = new BigDecimal("0");
					String ValueAmountFlag = "";
					BigDecimal BalanceAmtAll = new BigDecimal("0");
					BigDecimal BalanceAmtMax = new BigDecimal("0");
					String BalanceAmtFlag = "";
					for(String flag:institutionCollectMap.get(key).keySet()){
						BigDecimal SaleAmountTotal = institutionCollectMap.get(key).get(flag).getSaleAmountTotal();
						BigDecimal ValueAmountTota = institutionCollectMap.get(key).get(flag).getValueAmountTotal();
						BigDecimal BalanceAmt = institutionCollectMap.get(key).get(flag).getBalanceAmt();
						if(SaleAmountTotal != null) {
							SaleAmountTotalAll = SaleAmountTotalAll.add(SaleAmountTotal);
						}
						if(ValueAmountTota != null) {
							ValueAmountTotalAll = ValueAmountTotalAll.add(ValueAmountTota);
						}
						if(BalanceAmt != null) {
							BalanceAmtAll = BalanceAmtAll.add(BalanceAmt);
						}
						if(SaleAmountTotalMax.compareTo(SaleAmountTotal) == 1){}
						else{
							SaleAmountTotalMax = SaleAmountTotal;
							SaleAmountFlag = flag;
							}
						if(ValueAmountTotalMax.compareTo(ValueAmountTota) == 1){}
						else{
							ValueAmountTotalMax = ValueAmountTota;
							ValueAmountFlag = flag;
						}
						if(BalanceAmt != null) {
							if (BalanceAmtMax.compareTo(BalanceAmt) == 1) {
							} else {
								BalanceAmtMax = BalanceAmt;
								BalanceAmtFlag = flag;
							}
						}
						}
					//处理总销售金额
					if(chbPrdPosition.get(i).getDirtyamtsum() == null){
						chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
					}
					if(SaleAmountFlag != null && SaleAmountFlag != "") {
						BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
						institutionCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
					}
					//处理净销售金额
					if(chbPrdPosition.get(i).getCleanamtsum() == null){
						chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
					}
					if(ValueAmountFlag != null && ValueAmountFlag != "") {
						BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
						institutionCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
					}
					//处理余额
					if(BalanceAmtFlag != null && BalanceAmtFlag != "") {
						if(chbPrdPosition.get(i).getEndamtsumOth() == null){
							chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
						}
						BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
						institutionCollectMap.get(key).get(BalanceAmtFlag).setSaleAmountTotal(BalanceAmtDiff);
					}
					}
				}
			}


       //处理后组装成新的总map
		Map<String,Map<String,Map<String,ChbMonthCollect>>> AllMap= new HashMap<String,Map<String,Map<String,ChbMonthCollect>>>();
		AllMap.put("holderType",holderTypeCollectMap);
		AllMap.put("area",areaCollectMap);
		AllMap.put("institution",institutionCollectMap);
		return AllMap;
	}

	/**
	 * 汇率转换
	 * @param listAmountTotal
	 * @return
	 */
	public List<ChbMonthCollect> currencyExchange (List<ChbMonthCollect> listAmountTotal){
		if (!listAmountTotal.isEmpty() && listAmountTotal.size() != 0) {
			for (int i = 0; i < listAmountTotal.size(); i++) {
				String ccy = listAmountTotal.get(i).getCcy();
				if(ccy.equals("EUR") || ccy.equals("USD")){
					//获取欧元或者美元兑人民币的汇率
					BigDecimal exchangeCNY = svrMonthCollectInterfMapper.getCNYExchange(ccy);
					if(exchangeCNY != null) {
						//余额
						if(listAmountTotal.get(i).getBalanceAmt() != null) {
							listAmountTotal.get(i).setBalanceAmt(exchangeCNY.multiply(listAmountTotal.get(i).getBalanceAmt()));
						}
						//总销售金额
						listAmountTotal.get(i).setSaleAmountTotal(exchangeCNY.multiply(listAmountTotal.get(i).getSaleAmountTotal()));
						//净销售金额
						listAmountTotal.get(i).setValueAmountTotal(exchangeCNY.multiply(listAmountTotal.get(i).getValueAmountTotal()));
					}
				}
				if(!ccy.equals("EUR") && !ccy.equals("USD") && !ccy.equals("CNY")){
					//获取其他货币兑美元
					BigDecimal exchangeUSD = svrMonthCollectInterfMapper.getUSDExchange(ccy);
					//获取美元兑人民币
					BigDecimal USDExchangeCNY = svrMonthCollectInterfMapper.USDExchangeCNY();
					if(exchangeUSD != null && USDExchangeCNY != null) {
						//余额
						if(listAmountTotal.get(i).getBalanceAmt() != null) {
							listAmountTotal.get(i).setBalanceAmt((exchangeUSD.multiply(listAmountTotal.get(i).getBalanceAmt())).multiply(USDExchangeCNY));
						}
						//总销售金额
						listAmountTotal.get(i).setSaleAmountTotal((exchangeUSD.multiply(listAmountTotal.get(i).getSaleAmountTotal())).multiply(USDExchangeCNY));
						//净销售金额
						listAmountTotal.get(i).setValueAmountTotal((exchangeUSD.multiply(listAmountTotal.get(i).getValueAmountTotal())).multiply(USDExchangeCNY));
					}
				}
			}
		}
		return listAmountTotal;
	}
	/**

	 * 获取指定日期下个月的第一天
	 * @param dateStr
	 * @return
	 */
	public static Date getFirstDayOfNextMonth(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			calendar.add(Calendar.MONTH, 1);
			return calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
