package com.joyintech.tams.svreport.chb.monthcollect.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chb.datacheck.beforeinsert.service.ChbCommonCheckService;
import com.joyintech.tams.svreport.chb.monthcollect.dao.SvrMonthCollectInterfMapper;
import com.joyintech.tams.svreport.chb.monthcollect.dto.ChbMonthCollect;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    	//1、开始检数据
    	logger.info("销售月报每月明细汇总信息检索开始------->>>>>>");
		List<ChbMonthCollect>list = this.getList(batchDate,batchDateStart,batchDateEnd);
    	logger.info("销售月报每月明细汇总信息检索结束-------<<<<<<");

    	if(StringUtil.isEmptyList(list)){
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
    	dbData(list,batchDate,batchDateStart ,batchDateEnd);
    	logger.info("销售月报每月明细汇总信息入库结束-------<<<<<<");
    }
    
    /**
     * 先删后插
     */
    private void dbData(List<ChbMonthCollect> list,Date batchDate,Date batchDateStart ,Date batchDateEnd) {
    	
    	if (StringUtil.isNotEmpty(list)) {
			//清除当月月报信息 若已报送则停止跑批
			logger.debug("当月汇总数据清除开始----------");
			svrMonthCollectInterfMapper.deleteDetail(DateUtil.format(batchDate));
			svrMonthCollectInterfMapper.deleteReport(DateUtil.format(batchDate));
			logger.debug("当月汇总数据清除结束----------");

            logger.debug("销售月报每月明细汇总信息插入数据：插入开始----------");
			for(ChbMonthCollect sub : list){
				//创建人
				sub.setCreateUser(CommonUtil.getUserName());
				//更新人
				sub.setUpdateUser(CommonUtil.getUserName());
				//创建部门
				sub.setCreateDept(CommonUtil.getDeptCode());
				//创建机构
				sub.setOrgCode(CommonUtil.getOrgCode());
				if(!sub.getHolderType().isEmpty()){
					sub.setReporttype("1_1分投资者类型");
				}else if(!sub.getInstitutionCode().isEmpty() && !sub.getIsConsign().isEmpty()){
					sub.setReporttype("1_3分销售渠道");
				}else if(!sub.getAreaCode().isEmpty()){
					sub.setReporttype("1_2分地区类型");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				sub.setChbSubmitDeadline(getFirstDayOfNextMonth(batchDate));
				sub.setReportfrequ("M-月报");
				sub.setReportName("销售数据月度统计表_");
				svrMonthCollectInterfMapper.insertBatchData(sub);
			}
            logger.debug("销售月报每月明细汇总信息插入数据：插入结束----------");
		}
	}

	/**
	 *检查整个月汇总是否有值
	 * @param batchDateStart
	 * @param batchDateEnd
	 * @return
	 */
	public int checkAllMonth(Date batchDateStart,Date batchDateEnd){
		return svrMonthCollectInterfMapper.checkAllMonth(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
	}

	/**
	 *检查月末日汇总
	 * @param batchDateEnd
	 * @return
	 */
	public int checkLastDay(Date batchDateEnd){
		return svrMonthCollectInterfMapper.checkLastDay(DateUtil.format(batchDateEnd));
	}

	/**
	 *检查月末日持有
	 * @param batchDateEnd
	 * @return
	 */
	public int checkHold(Date batchDateEnd){
		return svrMonthCollectInterfMapper.checkHold(DateUtil.format(batchDateEnd));
	}

	/**
	 * 检查数据是否已报送
	 * @param batchDateStart
	 * @param batchDateEnd
	 * @return
	 */
	public int checkSubmission(Date batchDateStart,Date batchDateEnd){
		return svrMonthCollectInterfMapper.checkSubmission(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
	}

	/**
	 * 检索并处理数据
	 * @param batchDate
	 * @param batchDateStart
	 * @param batchDateEnd
	 * @return
	 */
	public List<ChbMonthCollect> getList(Date batchDate,Date batchDateStart ,Date batchDateEnd) {
		//汇总  总、净销售金额
		List<ChbMonthCollect> listAmountTotal = svrMonthCollectInterfMapper.selectAmountTotal(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
//		//判断是否为三合一 生成持有余额、投资者数量信息
//		if(DictCodeListExt2.是否对三合一人员进行统计报送_是.getValue().equals("1")){//是三合一
			//获取check表的
			List<ChbMonthCollect> registList = svrMonthCollectInterfMapper.selectHolderNumCheck(DateUtil.format(batchDateEnd));
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
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setHolderType(a.getHolderType());
				bb.setSaleAmountTotal(a.getValueAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getValueAmountTotal());
				bb.setBalanceShares(a.getValueAmountTotal());
				holderTypeCollect.put(falg, bb);
			}else{
				holderTypeCollect.get(falg).setSaleAmountTotal(holderTypeCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
				holderTypeCollect.get(falg).setValueAmountTotal(holderTypeCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
				holderTypeCollect.get(falg).setBalanceAmt(holderTypeCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
				holderTypeCollect.get(falg).setBalanceShares(holderTypeCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
			}
		}
		//赋值投资者数量
			for(int m = 0; m < typeHolderNum.size();m++){
				String flag = typeHolderNum.get(m).getPrdRegistCode() + ":" + typeHolderNum.get(m).getHolderType();
				if(holderTypeCollect.get(flag) != null){
					holderTypeCollect.get(flag).setHolderNum(typeHolderNum.get(m).getHolderNum());
				}
			}


		//以地区汇总
		List<ChbMonthCollect >areaHolderNum = svrMonthCollectInterfMapper.areaHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> areaCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getAreaCode();
			if (areaCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setAreaCode(a.getAreaCode());
				bb.setSaleAmountTotal(a.getValueAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getValueAmountTotal());
				bb.setBalanceShares(a.getValueAmountTotal());
				areaCollect.put(falg, bb);
			}else{
				areaCollect.get(falg).setSaleAmountTotal(areaCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
				areaCollect.get(falg).setValueAmountTotal(areaCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
				areaCollect.get(falg).setBalanceAmt(areaCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
				areaCollect.get(falg).setBalanceShares(areaCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
			}
		}
		//赋值投资者数量
		for(int m = 0; m < areaHolderNum.size();m++){
			String flag = areaHolderNum.get(m).getPrdRegistCode() + ":" + areaHolderNum.get(m).getAreaCode();
			if(areaCollect.get(flag) != null){
				areaCollect.get(flag).setHolderNum(areaHolderNum.get(m).getHolderNum());
			}
		}

		//以分销渠道汇总
		List<ChbMonthCollect >channelHolderNum = svrMonthCollectInterfMapper.channelHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> institutionCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getInstitutionCode();
			if (institutionCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setCnyAmt(a.getCnyAmt());
				bb.setIsConsign(a.getIsConsign());
				bb.setInstitutionCode(a.getInstitutionCode());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setSaleAmountTotal(a.getValueAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getValueAmountTotal());
				bb.setBalanceShares(a.getValueAmountTotal());
				institutionCollect.put(falg, bb);
			}else{
				institutionCollect.get(falg).setSaleAmountTotal(institutionCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
				institutionCollect.get(falg).setValueAmountTotal(institutionCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
				institutionCollect.get(falg).setBalanceAmt(institutionCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
				institutionCollect.get(falg).setBalanceShares(institutionCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
			}
		}
		//赋值投资者数量
		for(int m = 0; m < channelHolderNum.size();m++){
			String flag = channelHolderNum.get(m).getPrdRegistCode() + ":" + channelHolderNum.get(m).getInstitutionCode();
			if(institutionCollect.get(flag) != null){
				institutionCollect.get(flag).setHolderNum(channelHolderNum.get(m).getHolderNum());
			}
		}
//		分类后组装成新的List
		List<ChbMonthCollect> list = null;
		for (ChbMonthCollect value : holderTypeCollect.values()) {
			list.add(holderTypeCollect.get(value));
		}
		for (ChbMonthCollect value : areaCollect.values()) {
			list.add(areaCollect.get(value));
		}
		for (ChbMonthCollect value : institutionCollect.values()) {
			list.add(institutionCollect.get(value));
		}



		return list;
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
						listAmountTotal.get(i).setBalanceAmt(exchangeCNY.multiply(listAmountTotal.get(i).getBalanceAmt()));
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
						listAmountTotal.get(i).setBalanceAmt((exchangeUSD.multiply(listAmountTotal.get(i).getBalanceAmt())).multiply(USDExchangeCNY));
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
	 * 获取指定日期下个月的第7天
	 * @param batchDate
	 * @return
	 */
	public static String getFirstDayOfNextMonth(Date batchDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(String.valueOf(batchDate));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			calendar.add(Calendar.MONTH, 1);
			Date newDate = addDate(calendar.getTime(), 6);
			return sdf.format(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date addDate(Date date,long day) throws ParseException {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time+=day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}

}
