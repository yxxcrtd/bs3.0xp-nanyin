package com.joyintech.tams.disclosure.busicommon.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dto.DisAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.dto.DisOwnerEquityChange001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisValProfitQuery001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisValProfitQuery001Dto;

/**
 * list转换map
 * @author Dongxiangyang
 *
 */
public class ListconMap {

	
	/**
	 * 资产资产报表数据list转为List
	 * @param list
	 * @return
	 */
	public static  Map<String,Object>  listToMap(List<DisAssetLiabilityStatementQuery001Dto> list,Map<String,Object> map){
		//负债合计,需要取上次数据
		BigDecimal fzqmamt = map.get("fzqmamt")!=null?(BigDecimal)map.get("fzqmamt"):BigDecimal.ZERO;
		BigDecimal fzncamt = map.get("fzncamt")!=null?(BigDecimal)map.get("fzncamt"):BigDecimal.ZERO;
		//资产合计,需要取上次数据
		BigDecimal zcqmamt = map.get("zcqmamt")!=null?(BigDecimal)map.get("zcqmamt"):BigDecimal.ZERO;
		BigDecimal zcncamt = map.get("zcncamt")!=null?(BigDecimal)map.get("zcncamt"):BigDecimal.ZERO;
		//负债和权益合计,需要取上次数据
		BigDecimal fzqyqmamt = map.get("fzqyqmamt")!=null?(BigDecimal)map.get("fzqyqmamt"):BigDecimal.ZERO;
		BigDecimal fzqyncamt = map.get("fzqyncamt")!=null?(BigDecimal)map.get("fzqyncamt"):BigDecimal.ZERO;
		//循环list
		for(DisAssetLiabilityStatementQuery001Dto dto:list){
			if(dto.getItemName().equals("银行存款")){
				map.put("yhckqm", dto.getEndingBalance());
				map.put("yhcknc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());

			}else if(dto.getItemName().equals("结算备付金")){
				map.put("zsbfjqm", dto.getEndingBalance());
				map.put("zsbfjnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("存出保证金")){
				map.put("ccbzjqm", dto.getEndingBalance());
				map.put("ccbzjnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("存放同业款项")){
				map.put("cftykxqm", dto.getEndingBalance());
				map.put("cftykxnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("拆出资金")){
				map.put("cczjqm", dto.getEndingBalance());
				map.put("cczjnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("交易性金融资产")){
				map.put("jrxjrzcqm", dto.getEndingBalance());
				map.put("jrxjrzcnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("买入返售金融资产")){
				map.put("mrfsjrzcqm", dto.getEndingBalance());
				map.put("mrfsjrzcnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("衍生金融资产")){
				map.put("ysjrzcqm", dto.getEndingBalance());
				map.put("ysjrzcnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("可供出售金融资产")){
				map.put("kgcsjrzcqm", dto.getEndingBalance());
				map.put("kgcsjrzcnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应收款项类投资")){
				map.put("yskxltzqm", dto.getEndingBalance());
				map.put("yskxltznc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("持有至到期投资")){
				map.put("cyzdqtzqm", dto.getEndingBalance());
				map.put("cyzdqtznc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应收利息")){
				map.put("yslxqm", dto.getEndingBalance());
				map.put("yslxnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应收证券清算款")){
				map.put("yszjqskqm", dto.getEndingBalance());
				map.put("yszjqsknc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应收股利")){
				map.put("ysglqm", dto.getEndingBalance());
				map.put("ysglnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应收申购款")){
				map.put("yssgkqm", dto.getEndingBalance());
				map.put("yssgknc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("递延所得税资产")){
				map.put("ytsdszcqm", dto.getEndingBalance());
				map.put("ytsdszcnc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("其他资产")){
				map.put("itczqm", dto.getEndingBalance());
				map.put("itcznc", dto.getInitialBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
				zcncamt = zcncamt.add(dto.getInitialBalance());
			}
			//负债和所有者权益
			else if(dto.getItemName().equals("拆入资金")){
				map.put("crzjqm", dto.getEndingBalance());
				map.put("crzjnc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("交易性金融负债")){
				map.put("jyxjrfzqm", dto.getEndingBalance());
				map.put("yxjrfznc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("衍生金融负债")){
				map.put("ysjrfzqm", dto.getEndingBalance());
				map.put("ysjrfznc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("卖出回购金融资产款")){
				map.put("mchgjrzckqm", dto.getEndingBalance());
				map.put("mchgjrzcknc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付管理人报酬")){
				map.put("yfglrbcqm", dto.getEndingBalance());
				map.put("yfglrbcnc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付托管费")){
				map.put("yftgfqm", dto.getEndingBalance());
				map.put("yftgfnc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付销售服务费")){
				map.put("yfxsfwfqm", dto.getEndingBalance());
				map.put("yfxsfwfnc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付利息")){
				map.put("yflxqm", dto.getEndingBalance());
				map.put("yflxnc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付交易费用")){
				map.put("yfjyfyqm", dto.getEndingBalance());
				map.put("yfjyfync", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付赎回款")){
				map.put("yfshkqm", dto.getEndingBalance());
				map.put("yfshknc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付证券清算款")){
				map.put("yfzjqskqm", dto.getEndingBalance());
				map.put("yfzjqsknc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应付利润")){
				map.put("yflrqm", dto.getEndingBalance());
				map.put("yflrnc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("应交税费")){
				map.put("yjsfqm", dto.getEndingBalance());
				map.put("yjsfnc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("递延所得税负债")){
				map.put("tysdsfzqm", dto.getEndingBalance());
				map.put("tysdsfznc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("其他负债")){
				map.put("qtfzqm", dto.getEndingBalance());
				map.put("qtfznc", dto.getInitialBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
				fzncamt = fzncamt.add(dto.getInitialBalance());
				
			}else if(dto.getItemName().equals("实收资本")){
				map.put("sszbqm", dto.getEndingBalance());
				map.put("sszbnc", dto.getInitialBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
				fzqyncamt = fzqyncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("资本公积")){
				map.put("zbgjqm", dto.getEndingBalance());
				map.put("zbgjnc", dto.getInitialBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
				fzqyncamt = fzqyncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("未分配利润")){
				map.put("wfplrqm", dto.getEndingBalance());
				map.put("wfplrnc", dto.getInitialBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
				fzqyncamt = fzqyncamt.add(dto.getInitialBalance());
			}else if(dto.getItemName().equals("其它综合收益")){
				map.put("qtzhsyqm", dto.getEndingBalance());
				map.put("qtzhsync", dto.getInitialBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
				fzqyncamt = fzqyncamt.add(dto.getInitialBalance());
			}
		}
		
		map.put("fzqmamt", fzqmamt);
		map.put("fzncamt", fzncamt);
		map.put("zcqmamt", zcqmamt);
		map.put("zcncamt", zcncamt);
		map.put("fzqyqmamt", fzqyqmamt.add(fzqmamt));
		map.put("fzqyncamt", fzqyncamt.add(fzncamt));
		
		return map;
	}

	
	
	
	
	/**
	 * @paramList<DisOwnerEquityChange001Dto>
	 * @return map
	 * 所有者权益表listTomap
	 */
	public  static Map<String,Object>  ownListToMap(List<DisOwnerEquityChange001Dto> list){
		//定义返回Map
		Map<String,Object>   map=new HashMap<>();
		//循环参数
		for(DisOwnerEquityChange001Dto dto:list){
		if(dto.getProject().equals("一、期初所有者权益（产品净值）")){
			map.put("qcsszb", dto.gettIncomeFund());
			map.put("qcwfplr", dto.gettUnQuarterProfit());
			map.put("qcsyzqyhj", dto.gettOwnerEquityTot());
			map.put("qcsszb1", dto.getyIncomeFund());
			map.put("qcwfplr1", dto.getyUnQuarterProfit());
			map.put("qcsyzqyhj1", dto.getyOwnerEquityTot());
		}else if(dto.getProject().equals("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）")){
			map.put("jysszb", dto.gettIncomeFund());
			map.put("jywfplr", dto.gettUnQuarterProfit());
			map.put("jysyzqyhj", dto.gettOwnerEquityTot());
			map.put("jysszb1", dto.getyIncomeFund());
			map.put("jywfplr1", dto.getyUnQuarterProfit());
			map.put("jysyzqyhj1", dto.getyOwnerEquityTot());
		}else  if(dto.getProject().equals("三、本期产品份额交易产生的产品净值变动数")){
			map.put("fesszb", dto.gettIncomeFund());
			map.put("fewfplr", dto.gettUnQuarterProfit());
			map.put("fesyzqyhj", dto.gettOwnerEquityTot());
			map.put("fesszb1", dto.getyIncomeFund());
			map.put("fewfplr1", dto.getyUnQuarterProfit());
			map.put("fesyzqyhj1", dto.getyOwnerEquityTot());
		}else if(dto.getProject().equals("1.产品认申购")){
			map.put("sgsszb", dto.gettIncomeFund());
			map.put("sgwfplr", dto.gettUnQuarterProfit());
			map.put("sgsyzqyhj", dto.gettOwnerEquityTot());
			map.put("sgsszb1", dto.getyIncomeFund());
			map.put("sgwfplr1", dto.getyUnQuarterProfit());
			map.put("sgsyzqyhj1", dto.getyOwnerEquityTot());
		}else if(dto.getProject().equals("2.产品赎回")){
			map.put("shsszb", dto.gettIncomeFund());
			map.put("shwfplr", dto.gettUnQuarterProfit());
			map.put("shsyzqyhj", dto.gettOwnerEquityTot());
			map.put("shsszb1", dto.getyIncomeFund());
			map.put("shwfplr1", dto.getyUnQuarterProfit());
			map.put("shsyzqyhj1", dto.getyOwnerEquityTot());
		}else if(dto.getProject().equals("四、本期向产品份额持有人分配利润产生的产品净值变动")){
			map.put("bqsszb", dto.gettIncomeFund());
			map.put("bqwfplr", dto.gettUnQuarterProfit());
			map.put("bqsyzqyhj", dto.gettOwnerEquityTot());
			map.put("bqsszb1", dto.getyIncomeFund());
			map.put("bqwfplr1", dto.getyUnQuarterProfit());
			map.put("bqsyzqyhj1", dto.getyOwnerEquityTot());
		}else if(dto.getProject().equals("五、期末所有者权益（产品净值）")){
			map.put("qmsszb", dto.gettIncomeFund());
			map.put("qmwfplr", dto.gettUnQuarterProfit());
			map.put("qmsyzqyhj", dto.gettOwnerEquityTot());
			map.put("qmsszb1", dto.getyIncomeFund());
			map.put("qmwfplr1", dto.getyUnQuarterProfit());
			map.put("qmsyzqyhj1", dto.getyOwnerEquityTot());
		}		
		}
		return map;
	}
	
	/**
	 * 所有者权益集合转换
	 * @param list
	 * @return
	 */
	public static Map<String,Object> ownValListToMap(List<DisValProfitQuery001Dto> list){
		//定义返回变量
		Map<String,Object>  map=new  HashMap<String,Object>();
		//循环参数塞值
		for(DisValProfitQuery001Dto dto:list){
			if(dto.getProject().equals("期初所有者权益实收资本")){
				map.put("qcsszb", dto.getThisTermAmt());
				map.put("qcsszb1", dto.getLastTermAmt());
			}else if(dto.getProject().equals("期初所有者权益未分配利润")){
				map.put("qcwfplr", dto.getThisTermAmt());
				map.put("qcwfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("期初所有者权益所有者权益合计")){
				map.put("qcsyzqyhj", dto.getThisTermAmt());
				map.put("qcsyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期经营活动实收资本")){
				map.put("jysszb", dto.getThisTermAmt());
				map.put("jysszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期经营活动未分配利润")){
				map.put("jywfplr", dto.getThisTermAmt());
				map.put("jywfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期经营活动所有者权益合计")){
				map.put("jysyzqyhj", dto.getThisTermAmt());
				map.put("jysyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品申购款实收资本")){
				map.put("sgsszb", dto.getThisTermAmt());
				map.put("sgsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品申购款未分配利润")){
				map.put("sgwfplr", dto.getThisTermAmt());
				map.put("sgwfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品申购款所有者权益合计")){
				map.put("sgsyzqyhj", dto.getThisTermAmt());
				map.put("sgsyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品赎回款实收资本")){
				map.put("shsszb", dto.getThisTermAmt());
				map.put("shsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品赎回款未分配利润")){
				map.put("shwfplr", dto.getThisTermAmt());
				map.put("shwfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品赎回款所有者权益合计")){
				map.put("shsyzqyhj", dto.getThisTermAmt());
				map.put("shsyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期产品份额交易实收资本")){
				map.put("fesszb", dto.getThisTermAmt());
				map.put("fesszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期产品份额交易未分配利润")){
				map.put("fewfplr", dto.getThisTermAmt());
				map.put("fewfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期产品份额交易所有者权益合计")){
				map.put("bqsyzqyhj", dto.getThisTermAmt());
				map.put("fesyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期向产品份额持有人实收资本")){
				map.put("bqsszb", dto.getThisTermAmt());
				map.put("bqsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期向产品份额持有人未分配利润")){
				map.put("bqwfplr", dto.getThisTermAmt());
				map.put("bqwfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期向产品份额持有人所有者权益合计")){
				map.put("bqsyzqyhj", dto.getThisTermAmt());
				map.put("bqsyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("期末所有者权益实收资本")){
				map.put("qmsszb", dto.getThisTermAmt());
				map.put("qmsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("期末所有者权益未分配利润")){
				map.put("qmwfplr", dto.getThisTermAmt());
				map.put("qmwfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("期末所有者权益所有者权益合计")){
				map.put("qmsyzqyhj", dto.getThisTermAmt());
				map.put("qmsyzqyhj1",dto.getLastTermAmt());
			}
			// 增加[其他综合收益]列  zhangyf 20190529 start
			else if("期初所有者权益其他综合收益".equals(dto.getProject())){
				map.put("qcqtzhsy", dto.getThisTermAmt());
				map.put("qcqtzhsy1",dto.getLastTermAmt());
			}else if("本期经营活动其他综合收益".equals(dto.getProject())){
				map.put("jyqtzhsy", dto.getThisTermAmt());
				map.put("jyqtzhsy1",dto.getLastTermAmt());
			}else if("产品申购款其他综合收益".equals(dto.getProject())){
				map.put("sgqtzhsy", dto.getThisTermAmt());
				map.put("sgqtzhsy1",dto.getLastTermAmt());
			}else if("产品赎回款其他综合收益".equals(dto.getProject())){
				map.put("shqtzhsy", dto.getThisTermAmt());
				map.put("shqtzhsy1",dto.getLastTermAmt());
			}else if("本期产品份额交易其他综合收益".equals(dto.getProject())){
				map.put("bqfecyqtzhsy", dto.getThisTermAmt());
				map.put("bqfecyqtzhsy1",dto.getLastTermAmt());
			}else if("本期向产品份额持有人其他综合收益".equals(dto.getProject())){
				map.put("bqcyrqtzhsy", dto.getThisTermAmt());
				map.put("bqcyrqtzhsy1",dto.getLastTermAmt());
			}else if("期末所有者权益其他综合收益".equals(dto.getProject())){
				map.put("qosyzqtzhsy", dto.getThisTermAmt());
				map.put("qosyzqtzhsy1",dto.getLastTermAmt());
			}
			// 增加[其他综合收益]列  zhangyf 20190529 start
		}
		return map;
	}
	
	/**
	 * 利润表List转换Map
	 * @param list
	 * @return map
	 */
	
	public static Map<String,Object> valListToMap(List<DisValProfitQuery001Dto> list){
		//定义返回变量
		Map<String,Object>  map=new  HashMap<String,Object>();
		//循环参数塞值
		for(DisValProfitQuery001Dto dto:list){
			if(dto.getProject().equals("一、收入")){
				map.put("bqsr", dto.getThisTermAmt());
				map.put("sqsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("1.利息收入")){
				map.put("bqlxsr", dto.getThisTermAmt());
				map.put("sqlxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("其中：存款利息收入")){
				map.put("bqcklxsr", dto.getThisTermAmt());
				map.put("sqcklxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("拆出资金利息收入")){
				map.put("bqcczzlxsr", dto.getThisTermAmt());
				map.put("sqcczzlxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("债券利息收入")){
				map.put("bqzqlxsr", dto.getThisTermAmt());
				map.put("sqzqlxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("买入返售金融资产收入")){
				map.put("bqmrfszcsr", dto.getThisTermAmt());
				map.put("sqmrfszcsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("其他利息收入")){
				map.put("bqqtlxsr", dto.getThisTermAmt());
				map.put("sqqtlxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("2.投资收益（损失以“-”号填列）")){
				map.put("bqtzsyss", dto.getThisTermAmt());
				map.put("sqtzsyss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("公允价值损益类-股票投资收益")){
				map.put("bqgjs", dto.getThisTermAmt());
				map.put("sqgjs", dto.getLastTermAmt());
			}else if(dto.getProject().equals("公允价值损益类-基金投资收益")){
				map.put("bqjyxjrzc", dto.getThisTermAmt());
				map.put("sqjyxjrzc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("衍生工具收益")){
				map.put("bqysgjsy", dto.getThisTermAmt());
				map.put("sqysgjsy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("债券投资收益")){
				map.put("bqkgcsjrzc", dto.getThisTermAmt());
				map.put("sqkgcsjrzc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("资产支持证券投资收益")){
				map.put("bqjyxjrfz", dto.getThisTermAmt());
				map.put("sqjyxjrfz", dto.getLastTermAmt());
			}else if(dto.getProject().equals("应收款项类投资")){
				map.put("bqyskxltz", dto.getThisTermAmt());
				map.put("sqyskxltz", dto.getLastTermAmt());
			}else if(dto.getProject().equals("持有到期投资")){
				map.put("bqcydqtz", dto.getThisTermAmt());
				map.put("sqcydqtz", dto.getLastTermAmt());
			}else if(dto.getProject().equals("股利收益")){
				map.put("bqqttzsy", dto.getThisTermAmt());
				map.put("sqqttzsy", dto.getLastTermAmt());
			}
			else if(dto.getProject().equals("3.公允价值变动收益（损失以“-”号填列）")){
				map.put("bqgyjzbdsy", dto.getThisTermAmt());
				map.put("sqgyjzbdsy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("4.汇兑收益（损失以“-”号填列）")){
				map.put("bqhdsyss", dto.getThisTermAmt());
				map.put("sqhdsyss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("5.其他收入（损失以“-”号填列）")){
				map.put("bqqtsrss", dto.getThisTermAmt());
				map.put("sqqtsrss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("减：二、费用")){
				map.put("bqjefy", dto.getThisTermAmt());
				map.put("sqjefy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("1.管理人报酬")){
				map.put("bqglrbc", dto.getThisTermAmt());
				map.put("sqglrbc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("2.托管费")){
				map.put("bqtgf", dto.getThisTermAmt());
				map.put("sqtgf", dto.getLastTermAmt());
			}else if(dto.getProject().equals("3.销售服务费")){
				map.put("bqxsfwf", dto.getThisTermAmt());
				map.put("sqxsfwf", dto.getLastTermAmt());
			}else if(dto.getProject().equals("4.交易费用")){
				map.put("bqjyfy", dto.getThisTermAmt());
				map.put("sqjyfy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("5.利息支出")){
				map.put("bqlxzc", dto.getThisTermAmt());
				map.put("sqlxzc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("其中：卖出回购金融资产支出")){
				map.put("bqmchgzczc", dto.getThisTermAmt());
				map.put("sqmchgzczc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("6.其他费用")){
				map.put("bqqtfy", dto.getThisTermAmt());
				map.put("sqqtfy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("7.增值税及附加")){
				map.put("bqzzsjfj", dto.getThisTermAmt());
				map.put("sqzzsjfj", dto.getLastTermAmt());
			}else if(dto.getProject().equals("三、利润总额（亏损总额以“-”号填列）")){
				map.put("bqlrzeksze", dto.getThisTermAmt());
				map.put("sqlrzeksze", dto.getLastTermAmt());
			}else if(dto.getProject().equals("减：资产减值损失")){
				map.put("bqjzcjzss", dto.getThisTermAmt());
				map.put("sqjzcjzss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("四、净利润（净亏损以“-”号填列）")){
				map.put("bqjlrjks", dto.getThisTermAmt());
				map.put("sqjlrjks", dto.getLastTermAmt());
			}else if(dto.getProject().equals("资产支持证券利息收入")){
				map.put("bqzczczjlxsr", dto.getThisTermAmt());
				map.put("sqzczczjlxsr", dto.getLastTermAmt());
			}
		}
		
		return map;
	}
	
	
	/**
	 * List<DisValProfitQuery001Dto> 转换资产负债list
	 * @param vallist
	 * @return
	 */
	public  static  List<DisAssetLiabilityStatementQuery001Dto> valToAssetList(List<DisValProfitQuery001Dto>  vallist){
		//定义返回值
		List<DisAssetLiabilityStatementQuery001Dto> list=new ArrayList<DisAssetLiabilityStatementQuery001Dto>();
		for(DisValProfitQuery001Dto dto:vallist){
			DisAssetLiabilityStatementQuery001Dto  assetDto=new DisAssetLiabilityStatementQuery001Dto();
			assetDto.setItemName(dto.getProject());
			assetDto.setEndingBalance(dto.getThisTermAmt());
			assetDto.setInitialBalance(dto.getLastTermAmt());
			list.add(assetDto);
		}
		
		return  list;
	}
	
	
	public  static  List<DisAssetLiabilityStatementQuery001Dto> ownToAssetList( List<DisOwnerEquityChange001Dto>  ownlist){
		//定义返回值
		List<DisAssetLiabilityStatementQuery001Dto> list=new ArrayList<DisAssetLiabilityStatementQuery001Dto>();
		for(DisOwnerEquityChange001Dto dto:ownlist){
			DisOwnerEquityChange001Dto  ownDto=new DisOwnerEquityChange001Dto();
			ownDto.setProject(dto.getProject());
			
		}
		
		return  list;
	}
	
	
	/**  新增一体化财务三表  add  by  dongxiangyang 20200211 start */
	/**
	 * 资产资产报表数据list转为List
	 * @param list
	 * @return
	 */
	public static  Map<String,Object>  listToMapYth(List<DisAssetLiabilityStatementQuery001Dto> list,Map<String,Object> map){
		//负债合计,需要取上次数据
		BigDecimal fzqmamt = map.get("fzqmamt")!=null?(BigDecimal)map.get("fzqmamt"):BigDecimal.ZERO;
		//资产合计,需要取上次数据
		BigDecimal zcqmamt = map.get("zcqmamt")!=null?(BigDecimal)map.get("zcqmamt"):BigDecimal.ZERO;
		//负债和权益合计,需要取上次数据
		BigDecimal fzqyqmamt = map.get("fzqyqmamt")!=null?(BigDecimal)map.get("fzqyqmamt"):BigDecimal.ZERO;
		//循环list
		for(DisAssetLiabilityStatementQuery001Dto dto:list){
			if(dto.getItemName().equals("银行存款")){
				map.put("yhckqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("结算备付金")){
				map.put("zsbfjqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("存出保证金")){
				map.put("ccbzjqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("资金同业款项")){
				map.put("zktykxqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("以公允价值计量且其变动计入当期损益的金融资产")){
				map.put("ygydqsyqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("以公允价值计量且其变动计入其他综合收益的金融资产")){
				map.put("ygyzhsyqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("以摊余成本计量的金融资产")){
				map.put("ytycbjrqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("买入返售金融资产")){
				map.put("mrfsjrzcqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("衍生金融资产")){
				map.put("ysjrzcqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应收利息")){
				map.put("yslxqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应收证券清算款")){
				map.put("yszjqskqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应收股利")){
				map.put("ysglqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应收申购款")){
				map.put("yssgkqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("其他资产")){
				map.put("itczqm", dto.getEndingBalance());
				zcqmamt = zcqmamt.add(dto.getEndingBalance());
			}
			//负债和所有者权益
			else if(dto.getItemName().equals("拆入资金")){
				map.put("crzjqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("以公允价值计量且其变动计入当期损益的金融负债")){
				map.put("ygyjrfzqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("衍生金融负债")){
				map.put("ysjrfzqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("卖出回购金融资产款")){
				map.put("mchgjrzckqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付管理人报酬")){
				map.put("yfglrbcqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付托管费")){
				map.put("yftgfqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付销售服务费")){
				map.put("yfxsfwfqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付利息")){
				map.put("yflxqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付交易费用")){
				map.put("yfjyfyqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付赎回款")){
				map.put("yfshkqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付证券清算款")){
				map.put("yfzjqskqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应付利润")){
				map.put("yflrqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("应交税费应付")){
				map.put("yjsfqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("其他负债")){
				map.put("qtfzqm", dto.getEndingBalance());
				fzqmamt = fzqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("实收资本")){
				map.put("sszbqm", dto.getEndingBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("资本公积")){
				map.put("zbgjqm", dto.getEndingBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("未分配利润")){
				map.put("wfplrqm", dto.getEndingBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
			}else if(dto.getItemName().equals("其它综合收益")){
				map.put("qtzhsyqm", dto.getEndingBalance());
				fzqyqmamt = fzqyqmamt.add(dto.getEndingBalance());
			}
		}
		
		map.put("fzqmamt", fzqmamt);
		map.put("zcqmamt", zcqmamt);
		map.put("fzqyqmamt", fzqyqmamt.add(fzqmamt));
		
		return map;
	}
	/**
	 * 利润表List转换Map
	 * @param list
	 * @return map
	 */
	
	public static Map<String,Object> valListToMapYth(List<DisValProfitQuery001Dto> list){
		//定义返回变量
		Map<String,Object>  map=new  HashMap<String,Object>();
		//循环参数塞值
		for(DisValProfitQuery001Dto dto:list){
			if(dto.getProject().equals("一、收入")){
				map.put("bqsr", dto.getThisTermAmt());
				map.put("sqsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("1.利息收入")){
				map.put("bqlxsr", dto.getThisTermAmt());
				map.put("sqlxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("债券利息收入")){
				map.put("bqzqlxsr", dto.getThisTermAmt());
				map.put("sqzqlxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("资金同业款收入")){
				map.put("bqzjtyksr", dto.getThisTermAmt());
				map.put("sqzjtyksr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("买入返售利息收入")){
				map.put("bqmrfslxsr", dto.getThisTermAmt());
				map.put("sqmrfslxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("计划类资产利息收入")){
				map.put("bqzhlzclxsr", dto.getThisTermAmt());
				map.put("sqzhlzclxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("活期存款利息收入")){
				map.put("bqhqcklxsr", dto.getThisTermAmt());
				map.put("sqhqcklxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("债券借贷借出费收入")){
				map.put("bqzqjdjcfsr", dto.getThisTermAmt());
				map.put("sqzqjdjcfsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("利息收入增值税备抵")){
				map.put("bqlxsrzzsbd", dto.getThisTermAmt());
				map.put("sqlxsrzzsbd", dto.getLastTermAmt());
			}else if(dto.getProject().equals("其他利息收入")){
				map.put("bqqtlxsr", dto.getThisTermAmt());
				map.put("sqqtlxsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("2.投资收益")){
				map.put("bqtzsyss", dto.getThisTermAmt());
				map.put("sqtzsyss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("股票投资收益")){
				map.put("bqgjs", dto.getThisTermAmt());
				map.put("sqgjs", dto.getLastTermAmt());
			}else if(dto.getProject().equals("基金投资收益")){
				map.put("bqjyxjrzc", dto.getThisTermAmt());
				map.put("sqjyxjrzc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("衍生工具收益")){
				map.put("bqysgjsy", dto.getThisTermAmt());
				map.put("sqysgjsy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("债券投资收益")){
				map.put("bqkgcsjrzc", dto.getThisTermAmt());
				map.put("sqkgcsjrzc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("计划类投资红利收入")){
				map.put("bqjhltzhlsr", dto.getThisTermAmt());
				map.put("sqjhltzhlsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("计划类投资收益")){
				map.put("bqjhltzsy", dto.getThisTermAmt());
				map.put("sqjhltzsy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("以公允价值计量且其变动计入当期损益的金融负债价差收益")){
				map.put("bqygdqsyjrfz", dto.getThisTermAmt());
				map.put("sqygdqsyjrfz", dto.getLastTermAmt());
			}else if(dto.getProject().equals("投资收益增值税备抵")){
				map.put("bqtzsyzzsbd", dto.getThisTermAmt());
				map.put("sqtzsyzzsbd", dto.getLastTermAmt());
			}else if(dto.getProject().equals("红利收入增值税备抵")){
				map.put("bqhlsrzzsbd", dto.getThisTermAmt());
				map.put("sqhlsrzzsbd", dto.getLastTermAmt());
			}
			else if(dto.getProject().equals("3.公允价值变动损益")){
				map.put("bqgyjzbdsy", dto.getThisTermAmt());
				map.put("sqgyjzbdsy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("4.汇兑损益")){
				map.put("bqhdsyss", dto.getThisTermAmt());
				map.put("sqhdsyss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("5.其他收入")){
				map.put("bqqtsrss", dto.getThisTermAmt());
				map.put("sqqtsrss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("二、支出")){
				map.put("bqerzcsr", dto.getThisTermAmt());
				map.put("sqerzcsr", dto.getLastTermAmt());
			}else if(dto.getProject().equals("1.利息支出")){
				map.put("bqlxzc", dto.getThisTermAmt());
				map.put("sqlxzc", dto.getLastTermAmt());
			}
			else if(dto.getProject().equals("2.费用")){
				map.put("bqjefy", dto.getThisTermAmt());
				map.put("sqjefy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("管理人报酬")){
				map.put("bqglrbc", dto.getThisTermAmt());
				map.put("sqglrbc", dto.getLastTermAmt());
			}else if(dto.getProject().equals("托管费")){
				map.put("bqtgf", dto.getThisTermAmt());
				map.put("sqtgf", dto.getLastTermAmt());
			}else if(dto.getProject().equals("销售服务费")){
				map.put("bqxsfwf", dto.getThisTermAmt());
				map.put("sqxsfwf", dto.getLastTermAmt());
			}else if(dto.getProject().equals("交易费用")){
				map.put("bqjyfy", dto.getThisTermAmt());
				map.put("sqjyfy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("代销费")){
				map.put("bqdxf", dto.getThisTermAmt());
				map.put("sqdxf", dto.getLastTermAmt());
			}
			else if(dto.getProject().equals("外包服务费")){
				map.put("bqwbfwf", dto.getThisTermAmt());
				map.put("sqwbfwf", dto.getLastTermAmt());
			}else if(dto.getProject().equals("推介费")){
				map.put("bqtjf", dto.getThisTermAmt());
				map.put("sqtjf", dto.getLastTermAmt());
			}else if(dto.getProject().equals("投顾费")){
				map.put("bqtgf1", dto.getThisTermAmt());
				map.put("sqtgf1", dto.getLastTermAmt());
			}
			else if(dto.getProject().equals("其他费用")){
				map.put("bqqtfy", dto.getThisTermAmt());
				map.put("sqqtfy", dto.getLastTermAmt());
			}else if(dto.getProject().equals("3.其他支出")){
				map.put("bqqtzc", dto.getThisTermAmt());
				map.put("sqqtzc", dto.getLastTermAmt());
			}
			else if(dto.getProject().equals("4.税金及附加")){
				map.put("bqzzsjfj", dto.getThisTermAmt());
				map.put("sqzzsjfj", dto.getLastTermAmt());
			}else if(dto.getProject().equals("5.资产减值损失")){
				map.put("bqjzcjzss", dto.getThisTermAmt());
				map.put("sqjzcjzss", dto.getLastTermAmt());
			}else if(dto.getProject().equals("三、利润总额")){
				map.put("bqlrzeksze", dto.getThisTermAmt());
				map.put("sqlrzeksze", dto.getLastTermAmt());
			}
		}
		
		return map;
	}
	/**
	 * 所有者权益集合转换
	 * @param list
	 * @return
	 */
	public static Map<String,Object> ownValListToMapYth(List<DisValProfitQuery001Dto> list,String proftiType){
		//定义返回变量
		Map<String,Object>  map=new  HashMap<String,Object>();
		//循环参数塞值
		for(DisValProfitQuery001Dto dto:list){
			if(dto.getProject().equals("期初所有者权益实收资本")){
				map.put("qcsszb", dto.getThisTermAmt());
				map.put("qcsszb1", dto.getLastTermAmt());
			}else if(dto.getProject().equals("期初所有者权益未分配利润")){
				map.put("qcwfplr", dto.getThisTermAmt());
				map.put("qcwfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("期初所有者权益所有者权益合计")){
				map.put("qcsyzqyhj", dto.getThisTermAmt());
				map.put("qcsyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期经营活动实收资本")){
				map.put("jysszb", dto.getThisTermAmt());
				map.put("jysszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期经营活动未分配利润")){
				map.put("jywfplr", dto.getThisTermAmt());
				map.put("jywfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期经营活动所有者权益合计")){
				map.put("jysyzqyhj", dto.getThisTermAmt());
				map.put("jysyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品认申购款实收资本")){
				map.put("sgsszb", dto.getThisTermAmt());
				map.put("sgsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品认申购款未分配利润")){
				if(proftiType.equals(DictCodeList.收益类型_净值型.getValue())){
					map.put("sgwfplr", dto.getThisTermAmt());
					map.put("sgwfplr1",dto.getLastTermAmt());
				}else{
					map.put("sgwfplr", BigDecimal.ZERO);
					map.put("sgwfplr1",BigDecimal.ZERO);
				}
			}else if(dto.getProject().equals("产品认申购款所有者权益合计")){
				map.put("sgsyzqyhj", dto.getThisTermAmt());
				map.put("sgsyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品赎回款实收资本")){
				map.put("shsszb", dto.getThisTermAmt());
				map.put("shsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("产品赎回款未分配利润")){
				if(proftiType.equals(DictCodeList.收益类型_净值型.getValue())){
					map.put("shwfplr", dto.getThisTermAmt());
					map.put("shwfplr1",dto.getLastTermAmt());
				}else{
					map.put("shwfplr", BigDecimal.ZERO);
					map.put("shwfplr1",BigDecimal.ZERO);
				}
		
			}else if(dto.getProject().equals("产品赎回款所有者权益合计")){
				map.put("shsyzqyhj", dto.getThisTermAmt());
				map.put("shsyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期产品份额交易实收资本")){
				map.put("fesszb", dto.getThisTermAmt());
				map.put("fesszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期产品份额交易未分配利润")){
				if(proftiType.equals(DictCodeList.收益类型_净值型.getValue())){
					map.put("fewfplr", dto.getThisTermAmt());
					map.put("fewfplr1",dto.getLastTermAmt());
				}else{
					map.put("fewfplr", BigDecimal.ZERO);
					map.put("fewfplr1",BigDecimal.ZERO);
				}
				
			}else if(dto.getProject().equals("本期产品份额交易所有者权益合计")){
				map.put("fesyzqyhj", dto.getThisTermAmt());
				map.put("fesyzqyhj1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期向产品份额持有人实收资本")){
				map.put("bqsszb", dto.getThisTermAmt());
				map.put("bqsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("本期向产品份额持有人未分配利润")){
				if(dto.getThisTermAmt().compareTo(BigDecimal.ZERO)>0){
					map.put("bqwfplr",dto.getThisTermAmt().multiply(new BigDecimal(-1)));
				}else{
					map.put("bqwfplr",dto.getThisTermAmt());
				}
				map.put("bqwfplr1",dto.getLastTermAmt().multiply(new BigDecimal(-1)));
			}else if(dto.getProject().equals("本期向产品份额持有人所有者权益合计")){
				if(dto.getThisTermAmt().compareTo(BigDecimal.ZERO)>0){
					map.put("bqsyzqyhj", dto.getThisTermAmt().multiply(new BigDecimal(-1)));
				}else{
					map.put("bqsyzqyhj", dto.getThisTermAmt());
				}
				map.put("bqsyzqyhj1",dto.getLastTermAmt().multiply(new BigDecimal(-1)));
			}else if(dto.getProject().equals("期末所有者权益实收资本")){
				map.put("qmsszb", dto.getThisTermAmt());
				map.put("qmsszb1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("期末所有者权益未分配利润")){
				map.put("qmwfplr", dto.getThisTermAmt());
				map.put("qmwfplr1",dto.getLastTermAmt());
			}else if(dto.getProject().equals("期末所有者权益所有者权益合计")){
				map.put("qmsyzqyhj", dto.getThisTermAmt());
				map.put("qmsyzqyhj1",dto.getLastTermAmt());
			}
			// 增加[其他综合收益]列  zhangyf 20190529 start
			else if("期初所有者权益其他综合收益".equals(dto.getProject())){
				map.put("qcqtzhsy", dto.getThisTermAmt());
				map.put("qcqtzhsy1",dto.getLastTermAmt());
			}else if("本期经营活动其他综合收益".equals(dto.getProject())){
				map.put("jyqtzhsy", dto.getThisTermAmt());
				map.put("jyqtzhsy1",dto.getLastTermAmt());
			}else if("产品认申购款其他综合收益".equals(dto.getProject())){
				map.put("sgqtzhsy", dto.getThisTermAmt());
				map.put("sgqtzhsy1",dto.getLastTermAmt());
			}else if("产品赎回款其他综合收益".equals(dto.getProject())){
				map.put("shqtzhsy", dto.getThisTermAmt());
				map.put("shqtzhsy1",dto.getLastTermAmt());
			}else if("本期产品份额交易其他综合收益".equals(dto.getProject())){
				map.put("bqfecyqtzhsy", dto.getThisTermAmt());
				map.put("bqfecyqtzhsy1",dto.getLastTermAmt());
			}else if("本期向产品份额持有人其他综合收益".equals(dto.getProject())){
				map.put("bqcyrqtzhsy", dto.getThisTermAmt());
				map.put("bqcyrqtzhsy1",dto.getLastTermAmt());
			}else if("期末所有者权益其他综合收益".equals(dto.getProject())){
				map.put("qosyzqtzhsy", dto.getThisTermAmt());
				map.put("qosyzqtzhsy1",dto.getLastTermAmt());
			}
			// 增加[其他综合收益]列  zhangyf 20190529 start
		}
		return map;
	}
	
	/**  新增一体化财务三表  add  by  dongxiangyang 20200211 end */

}
