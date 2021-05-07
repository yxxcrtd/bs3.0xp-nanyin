package com.joyintech.tams.svreport.position.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.position.dao.ChbPrdPositionMapper;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 产品头寸批处理操作
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdPosition001Service  extends SvrCBBaseService{

    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private ChbPrdPositionMapper chbPrdPositionMapper;
	
	@Autowired
	private SqlSessionTemplate template;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    

//	@Value("${application.reportSelectOrDeleteCount}")
//	public  int reportSelectOrDeleteCount;
	
	/**
	 * 头寸处理
	 * @param batchDate
	 * @param prodIdList
	 */
	public void generatePositionData(Date batchDate,List<String> prodIdList) {
		//1、查询倒起息交易信息
		logger.debug("头寸数据批处理:----开始------" + df.format(new Date()));
		HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("batchDate", batchDate);
		paraMap.put("prodIdList", prodIdList);
//		template.select("com.joyintech.tams.svreport.position.dao.ChbPrdPositionMapper.queryMinSettdate",paraMap,
//                new ResultHandler() {
//            @Override
//            public void handleResult(ResultContext resultContext) {
//            	minSettdateList.add((ChbPrdDeal) resultContext.getResultObject());
//            }
//        });
		
		//查询最小跑批日
		Date minSettdate = chbPrdPositionMapper.queryMinSettdate(batchDate, prodIdList);
		
    	//2、删除跑批日头寸重新生成
		for (Date d = minSettdate; d.compareTo(batchDate) <= 0;) {
			delPosition(d,prodIdList);
            //生成头寸
    		calPosition(d,prodIdList);
			d = DateUtil.addDay(d, 1);
		}
		logger.debug("头寸数据批处理:----结束------" + df.format(new Date()));
	}
	
	/**
	 * 批量删除
	 * @param batchDate
	 * @param prodIdList
	 */
	private void delPosition(Date batchDate, List<String> prodIdList) {
		logger.debug("头寸数据批量删除:----开始------" + df.format(new Date()));
		int singalCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?
										   "100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		int totalCount = null == prodIdList ? 0 : prodIdList.size();
		if(totalCount > 0){
			if(totalCount<=singalCount){
				chbPrdPositionMapper.deletePosition(batchDate,prodIdList);
			}
			else{
				for(int i = 0; i < totalCount/singalCount ; i++){
					chbPrdPositionMapper.deletePosition(batchDate,prodIdList.subList(i*singalCount, (i+1)*singalCount));
//					prodIdList.removeAll(prodIdList.subList(0, singalCount));
				}
	            if(CollectionUtils.isNotEmpty(prodIdList)){
	            	chbPrdPositionMapper.deletePosition(batchDate,prodIdList.subList((totalCount/singalCount)*singalCount, totalCount));
	            }
			}
		}else{
			chbPrdPositionMapper.deletePosition(batchDate,null);
		}
		logger.debug("头寸数据批量删除:----结束------" + df.format(new Date()));
	}

	/**
	 * 头寸数据前期处理
	 * @param bookDate
	 * @param prodIdList
	 */
	private void calPosition(Date bookDate,List<String> prodIdList) { 
		List<ChbPrdPosition> totalList = new ArrayList<ChbPrdPosition>();
		String dealDimension = SvrDataUtil.getSysParam("SVR_PRD_DEAL_DIMENSION");
		logger.debug("查找上一日头寸数据并处理（移动平均计算）数据:----开始------" + df.format(new Date()));
		//3、查询上一日且份额不为0的头寸，继续生成今日头寸
//		List<ChbPrdPosition> ystPositionList = chbPrdPositionMapper.selectByDate(DateUtil.addDay(bookDate, -1),prodIdList);
		HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("cdate", DateUtil.addDay(bookDate, -1));
		paraMap.put("prodIdList", prodIdList);
		List<ChbPrdPosition> ystPositionList = new ArrayList<ChbPrdPosition>();
		template.select("com.joyintech.tams.svreport.position.dao.ChbPrdPositionMapper.selectByDate",paraMap,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	ystPositionList.add((ChbPrdPosition) resultContext.getResultObject());
            }
        });
		//系统参数  产品成本算法
		String prdcostalg = SvrDataUtil.getSysParam("SVR_PRD_COST_ALG");
		Map<String, List<ChbPrdPosition>> positionMap=ystPositionList.stream().collect(
				 JoyinCollectors.groupingBy(
              x -> x.getProdId()+GlobalConstant.UNDER_LINE+x.getIndentifyMark()
              +GlobalConstant.UNDER_LINE+x.getIssuePartyId()+GlobalConstant.UNDER_LINE+x.getCcy()
              +GlobalConstant.UNDER_LINE+x.getBuyplace()+GlobalConstant.UNDER_LINE+x.getCustomerType2()
              ));
		//查询今日发生交易
		/**add by dongxiangyang  日均余额算法  20201217 */
		Map<String, List<ChbPrdPosition>> positionMapDate=ystPositionList.stream().collect(
				JoyinCollectors.groupingBy(
						x -> x.getProdId()+GlobalConstant.UNDER_LINE+x.getIndentifyMark()
								+GlobalConstant.UNDER_LINE+x.getIssuePartyId()+GlobalConstant.UNDER_LINE+x.getCcy()
								+GlobalConstant.UNDER_LINE+x.getBuyplace()+GlobalConstant.UNDER_LINE+x.getCustomerType2()
								+GlobalConstant.UNDER_LINE+DateUtil.format(DateUtil.addDay(x.getCdate(), -1))
				));
		/**add by dongxiangyang  日均余额算法  20201217 */
		//先处理赎回类交易（赎回、提前赎回、到期）
		List<ChbPrdDeal> redDealList=queryDealData(bookDate,prodIdList ,new ArrayList<String>(Arrays.asList(
    		    DictCodeListExt2.产品存续期登记交易类型_存续期赎回.getValue(),
    		    DictCodeListExt2.产品存续期登记交易类型_提前赎回.getValue(),
				DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue())));
		for(ChbPrdDeal redDeal:redDealList){
			String dealkey= redDeal.getProdId()+GlobalConstant.UNDER_LINE+redDeal.getIndentifyMark()
            +GlobalConstant.UNDER_LINE+redDeal.getIssuePartyId()+GlobalConstant.UNDER_LINE+redDeal.getCcy()
            +GlobalConstant.UNDER_LINE+redDeal.getBuyPlace()+GlobalConstant.UNDER_LINE+redDeal.getCustomerType2();
			if(positionMap.containsKey(dealkey)){
				ChbPrdPosition valuePosition=positionMap.get(dealkey).get(0);
				//赎回份额
				valuePosition.setTdaddQty(redDeal.getTradeQty().negate());
				//赎回金额
				valuePosition.setTdaddAmt(redDeal.getTradePrince().negate());
				if ("02".equals(prdcostalg)) {
					valuePosition.setTdaddCost(redDeal.getTradeCost().negate());
				} else {
					// 赎回成本 = (昨日剩余成本/昨日剩余份额) * 今日赎回份额
					if (BigDecimal.ZERO.compareTo(valuePosition.getBalanceQty()) != 0) {
						if (redDeal.getTradeQty().compareTo(valuePosition.getBalanceQty()) > 0) {
							// 如果赎回份额大于昨日头寸剩余份额，则
							// 当日新增成本 = {昨日头寸剩余成本+[（赎回份额-昨日头寸剩余份额）*赎回金额/赎回份额]}*-1
							valuePosition.setTdaddCost(valuePosition.getBalanceAmt()
									.add(redDeal.getTradeQty().subtract(valuePosition.getBalanceQty())
											.multiply(redDeal.getTradePrince())
											.divide(redDeal.getTradeQty(), 14, RoundingMode.HALF_UP))
									.setScale(2, RoundingMode.HALF_UP).negate());
						} else {
							// 赎回成本 = (昨日剩余成本/昨日剩余份额) * 今日赎回份额
							valuePosition.setTdaddCost(valuePosition.getBalanceAmt()
									.divide(valuePosition.getBalanceQty(), 14, RoundingMode.HALF_UP)
									.multiply(redDeal.getTradeQty()).setScale(2, RoundingMode.HALF_UP).negate());
						}
					} else {
						valuePosition.setTdaddCost(redDeal.getTradePrince().negate());
					}
				}
				//剩余份额
				valuePosition.setBalanceQty(valuePosition.getBalanceQty().add(valuePosition.getTdaddQty()));
				//剩余成本
				valuePosition.setBalanceAmt(valuePosition.getBalanceAmt().add(valuePosition.getTdaddCost()));
				//给初始值防止后面空指针
				valuePosition.setTdaddIntamt(BigDecimal.ZERO);
				if ("02".equals(prdcostalg)) {
					valuePosition.setTdaddIntamt(redDeal.getIntAmt());
				} else {
					// 份额强减(份额不为0，金额为0) 利息不计算
					if (valuePosition.getTdaddAmt().compareTo(BigDecimal.ZERO) != 0) {
						// 赎回收益 = abs(金额) - abs(成本)
						valuePosition.setTdaddIntamt(
								valuePosition.getTdaddAmt().abs().subtract(valuePosition.getTdaddCost().abs()));
					}
				}
				//累计收益
				valuePosition.setTotalIntamt(valuePosition.getTotalIntamt().add(valuePosition.getTdaddIntamt()));
				
			}else {
				// 如果未找到初始头寸，则认为第一天开仓，并进行了申购赎回，则要对赎回进行特殊处理
				ChbPrdPosition valuePosition = buildInitPosition(redDeal,redDeal.getSettdateDate());
    			List<ChbPrdPosition> positionList=new ArrayList<ChbPrdPosition>();
    			//赎回份额
				valuePosition.setTdaddQty(redDeal.getTradeQty().negate());
				//赎回金额
				valuePosition.setTdaddAmt(redDeal.getTradePrince().negate());
				if("02".equals(prdcostalg)){
					valuePosition.setTdaddCost(redDeal.getTradeCost().negate());
				}else{
					//当日新增成本=赎回金额取反
					valuePosition.setTdaddCost(redDeal.getTradePrince().negate());
				}
				//剩余份额
				valuePosition.setBalanceQty(valuePosition.getBalanceQty().add(valuePosition.getTdaddQty()));
				//剩余成本
				valuePosition.setBalanceAmt(valuePosition.getBalanceAmt().add(valuePosition.getTdaddCost()));
				//给初始值防止后面空指针
				valuePosition.setTdaddIntamt(BigDecimal.ZERO);
				if ("02".equals(prdcostalg)) {
					valuePosition.setTdaddIntamt(redDeal.getIntAmt());
				} else {
					if (valuePosition.getTdaddAmt().compareTo(BigDecimal.ZERO) != 0) {
						// 赎回收益 = abs(金额) - abs(成本)
						valuePosition.setTdaddIntamt(
								valuePosition.getTdaddAmt().abs().subtract(valuePosition.getTdaddCost().abs()));
					}
				}
				//累计收益
				valuePosition.setTotalIntamt(valuePosition.getTotalIntamt().add(valuePosition.getTdaddIntamt()));
    			positionList.add(valuePosition);
    			positionMap.put(dealkey, positionList);
			}
		}
		//再单独处理强减，份额参与计算，其他全为0
		List<ChbPrdDeal> sdDealList=queryDealData(bookDate,prodIdList ,new ArrayList<String>(Arrays.asList(
    		    DictCodeListExt2.产品存续期登记交易类型_份额减弱.getValue())));
    	for(ChbPrdDeal sdDeal:sdDealList){
    		String dealkey= sdDeal.getProdId()+GlobalConstant.UNDER_LINE+sdDeal.getIndentifyMark()
            +GlobalConstant.UNDER_LINE+sdDeal.getIssuePartyId()+GlobalConstant.UNDER_LINE+sdDeal.getCcy()
            +GlobalConstant.UNDER_LINE+sdDeal.getBuyPlace()+GlobalConstant.UNDER_LINE+sdDeal.getCustomerType2();
    		if(positionMap.containsKey(dealkey)){
    			ChbPrdPosition valuePosition=positionMap.get(dealkey).get(0);
				sdDeal.setTradeQty(sdDeal.getTradeQty().negate());
				sdDeal.setTradePrince(sdDeal.getTradePrince().negate());
				sdDeal.setTradeCost(sdDeal.getTradeCost()==null?BigDecimal.ZERO:sdDeal.getTradeCost().negate());
				sdDeal.setIntAmt(BigDecimal.ZERO);
				updatePosition(prdcostalg, sdDeal, valuePosition);
    		}
    	}
    	//再处理认申购、红利再投
    	List<ChbPrdDeal> sgDealList=queryDealData(bookDate,prodIdList ,new ArrayList<String>(
    			Arrays.asList(DictCodeListExt2.产品存续期登记交易类型_募集期认购.getValue(),
	    		   DictCodeListExt2.产品存续期登记交易类型_存续期申购.getValue(),
	    		   DictCodeListExt2.产品存续期登记交易类型_收益再投资.getValue())));
    	Map<String,String> rgMap= new HashMap<String,String>();
    	for(ChbPrdDeal sgDeal:sgDealList){
    		String dealkey= sgDeal.getProdId()+GlobalConstant.UNDER_LINE+sgDeal.getIndentifyMark()
            +GlobalConstant.UNDER_LINE+sgDeal.getIssuePartyId()+GlobalConstant.UNDER_LINE+sgDeal.getCcy()
            +GlobalConstant.UNDER_LINE+sgDeal.getBuyPlace()+GlobalConstant.UNDER_LINE+sgDeal.getCustomerType2();
    		if(positionMap.containsKey(dealkey)){
    			ChbPrdPosition valuePosition=positionMap.get(dealkey).get(0);
    			updatePosition(prdcostalg, sgDeal, valuePosition);
    			if(rgMap.containsKey(dealkey)&&"02".equals(dealDimension)){
    				valuePosition.setCustCounts(valuePosition.getCustCounts().add(sgDeal.getCustCounts()));
    			}
    		}else{
    			ChbPrdPosition position = convertTdPosition(sgDeal,sgDeal.getSettdateDate());
    			rgMap.put(dealkey, dealkey);
    			List<ChbPrdPosition> positionList=new ArrayList<ChbPrdPosition>();
    			positionList.add(position);
    			positionMap.put(dealkey, positionList);
    		}
    	}
    	//再处理份额强增
    	List<ChbPrdDeal> suDealList=queryDealData(bookDate,prodIdList ,new ArrayList<String>(Arrays.asList(
    		    DictCodeListExt2.产品存续期登记交易类型_份额增强.getValue())));
    	for(ChbPrdDeal suDeal:suDealList){
    		String dealkey= suDeal.getProdId()+GlobalConstant.UNDER_LINE+suDeal.getIndentifyMark()
            +GlobalConstant.UNDER_LINE+suDeal.getIssuePartyId()+GlobalConstant.UNDER_LINE+suDeal.getCcy()
            +GlobalConstant.UNDER_LINE+suDeal.getBuyPlace()+GlobalConstant.UNDER_LINE+suDeal.getCustomerType2();
    		if(positionMap.containsKey(dealkey)){
    			ChbPrdPosition valuePosition=positionMap.get(dealkey).get(0);
    			suDeal.setIntAmt(BigDecimal.ZERO);
				updatePosition(prdcostalg, suDeal, valuePosition);
    		}
    	}
    	//再处理中间付息(现金分红)
    	List<ChbPrdDeal> fxDealList=queryDealData(bookDate,prodIdList ,new ArrayList<String>(Arrays.asList(
    		    DictCodeListExt2.产品存续期登记交易类型_收益分配.getValue())));
    	for(ChbPrdDeal fxDeal:fxDealList){
    		String dealkey= fxDeal.getProdId()+GlobalConstant.UNDER_LINE+fxDeal.getIndentifyMark()
            +GlobalConstant.UNDER_LINE+fxDeal.getIssuePartyId()+GlobalConstant.UNDER_LINE+fxDeal.getCcy()
            +GlobalConstant.UNDER_LINE+fxDeal.getBuyPlace()+GlobalConstant.UNDER_LINE+fxDeal.getCustomerType2();
    		if(positionMap.containsKey(dealkey)){
    			ChbPrdPosition valuePosition=positionMap.get(dealkey).get(0);
    			if(valuePosition.getTdaddIntamt()==null){
    				valuePosition.setTdaddIntamt(BigDecimal.ZERO);
    			}
    			valuePosition.setTdaddIntamt(valuePosition.getTdaddIntamt().add(fxDeal.getIntAmt()));
				//累计收益
    			valuePosition.setTotalIntamt(valuePosition.getTotalIntamt().add(fxDeal.getIntAmt()));
    		}
    	}

    	//更新新增字段字段
		/**add by dongxiangyang  日均余额算法  20201217 */
		//计算日均余额
		if(StringUtil.isNotEmpty(positionMap)){
			for(String  dealkey:positionMap.keySet()){
				ChbPrdPosition pos = positionMap.get(dealkey).get(0);
				List<String> list=new ArrayList<String>();
				list.add(pos.getProdId());
				//前一日的key
				String dealkeydate= pos.getProdId()+GlobalConstant.UNDER_LINE+pos.getIndentifyMark()
						+GlobalConstant.UNDER_LINE+pos.getIssuePartyId()+GlobalConstant.UNDER_LINE+pos.getCcy()
						+GlobalConstant.UNDER_LINE+pos.getBuyplace()+GlobalConstant.UNDER_LINE+pos.getCustomerType2()
						+GlobalConstant.UNDER_LINE+com.joyintech.framework.util.DateUtil.format(DateUtil.addDay(pos.getCdate(), -1));
				//前一日的pos
				ChbPrdPosition posdate=new  ChbPrdPosition();
				//昨日日均余额
				BigDecimal dayVqty=BigDecimal.ZERO;
				//获取产品起息日
				Date  vdate=chbPrdPositionMapper.getVdate(pos.getProdId());
				if(positionMapDate.containsKey(dealkeydate)){
					posdate = positionMapDate.get(dealkeydate).get(0);
					dayVqty=posdate.getRaiseamt();
				}
				//先处理赎回类交易（赎回、提前赎回、到期）
				List<ChbPrdDeal> dList=queryDealData(bookDate,list ,new ArrayList<String>(Arrays.asList(
						DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue())));
				if(dList!=null&&dList.size()>0){
					pos.setRaiseamt(dayVqty);
				}else{
					pos.setRaiseamt((dayVqty.multiply(
							new BigDecimal(DateUtil.getBetweenDayNumber(vdate,bookDate))
					).add(pos.getBalanceQty())).divide(
							new BigDecimal(DateUtil.getBetweenDayNumber(vdate,DateUtil.addDay(bookDate, 1)))
							,14,BigDecimal.ROUND_HALF_UP
					));
				}
			}
		}
		/**add by dongxiangyang  日均余额算法  20201217 */
    	// 查询交易信息
        List<ChbPrdDeal> dealList = chbPrdPositionMapper.selectPrdDeal(bookDate,prodIdList);
        for (ChbPrdDeal chbPrdDeal : dealList) {
        	String dealkey= chbPrdDeal.getProdId()+GlobalConstant.UNDER_LINE+chbPrdDeal.getIndentifyMark()
            +GlobalConstant.UNDER_LINE+chbPrdDeal.getIssuePartyId()+GlobalConstant.UNDER_LINE+chbPrdDeal.getCcy()
            +GlobalConstant.UNDER_LINE+chbPrdDeal.getBuyPlace()+GlobalConstant.UNDER_LINE+chbPrdDeal.getCustomerType2(); 
        	if(positionMap.containsKey(dealkey)){
	        	ChbPrdPosition pos = positionMap.get(dealkey).get(0);
	            BigDecimal prinamt = chbPrdDeal.getTradePrince();  //交易表存储的已是本息和
	            BigDecimal qty =chbPrdDeal.getTradeQty();
	            if(pos.getRaiseamt()==null){
	            	pos.setRaiseamt(BigDecimal.ZERO);
	            }
	            if(pos.getCustamt()==null){
	            	 pos.setCustamt(BigDecimal.ZERO);
	            }
	            if(pos.getCustqty()==null){
	            	 pos.setCustqty(BigDecimal.ZERO);
	            }
	            if (DictCodeListExt2.产品存续期登记交易类型_募集期认购.getValue().equals(chbPrdDeal.getTradeType())||
	                DictCodeListExt2.产品存续期登记交易类型_存续期申购.getValue().equals(chbPrdDeal.getTradeType())) {
//	                pos.setRaiseamt(pos.getRaiseamt().add(prinamt));
					//产品头寸修改,原逻辑废除 leipeng 20201226
	            } else if(DictCodeListExt2.产品存续期登记交易类型_存续期赎回.getValue().equals(chbPrdDeal.getTradeType())){
	                pos.setCustamt(pos.getCustamt().add(prinamt));
	                pos.setCustqty(pos.getCustqty().add(qty));
	            }else if(DictCodeListExt2.产品存续期登记交易类型_收益分配.getValue().equals(chbPrdDeal.getTradeType())){
	                pos.setCustamt(pos.getCustamt().add(prinamt));
	                pos.setCustqty(pos.getCustqty().add(qty));
	            } else if (DictCodeListExt2.产品存续期登记交易类型_份额减弱.getValue().equals(chbPrdDeal.getTradeType()) ){
	                pos.setCustamt(pos.getCustamt().add(prinamt));
	                pos.setCustqty(pos.getCustqty().add(qty));
	            } else if (DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(chbPrdDeal.getTradeType()) ){
	                pos.setCustamt(pos.getCustamt().add(prinamt));
	                pos.setCustqty(pos.getCustqty().add(qty));
	                //添加是到期交易的标识
	                pos.setIsEndDay(DictCodeList.是否_是.getValue());
	            }
	            //提前赎回，在数据接口已按存续期赎回接入，此处代码继续保留
	            else if (DictCodeListExt2.产品存续期登记交易类型_提前赎回.getValue().equals(chbPrdDeal.getTradeType()) ){  
	                pos.setCustamt(pos.getCustamt().add(prinamt));
	                pos.setCustqty(pos.getCustqty().add(qty));
	            } else { //收益再投资,份额强增，不影响头寸表的统计字段
	                ;
	            }
        	}
        }
        //将map转回list
        for(String key : positionMap.keySet()){
        	ChbPrdPosition tdyPosition=positionMap.get(key).get(0);
        	if(tdyPosition.getTdaddQty()==null){
        		tdyPosition.setTdaddQty(BigDecimal.ZERO);
			}

    		if(tdyPosition.getTdaddAmt()==null){
    			tdyPosition.setTdaddAmt(BigDecimal.ZERO);
    		}
    		if(tdyPosition.getTdaddCost()==null){
    			tdyPosition.setTdaddCost(BigDecimal.ZERO);
    		}
    		if(tdyPosition.getTdaddIntamt()==null){
    			tdyPosition.setTdaddIntamt(BigDecimal.ZERO);
    		}
			//20201230  leipeng 如果不是到期交易.则每日进行计算.如果是到期交易.则保持昨日数据
    		if(StringUtil.isEmpty(tdyPosition.getIsEndDay())){
				//key.实际募集 = (key.昨日实际募集 * (生成日-起息日) + 累计份额 ) / (生成日-起息日+1))
				BigDecimal raiseamt = tdyPosition.getRaiseamt().multiply(new BigDecimal(DateUtil.getBetweenDayNumber(tdyPosition.getvDate(),tdyPosition.getCdate()))).add(tdyPosition.getBalanceQty());
				tdyPosition.setRaiseamt(raiseamt.divide(new BigDecimal(DateUtil.getBetweenDayNumber(tdyPosition.getvDate(),tdyPosition.getCdate())).add(BigDecimal.ONE),8,BigDecimal.ROUND_HALF_UP));
			}

        	totalList.add(tdyPosition);
        }
		if(!StringUtil.isEmptyList(totalList)){
			logger.debug("头寸数据入库:----开始------" + df.format(new Date()));
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	    	//批量插入mapper
			ChbPrdPositionMapper batchMapper =  session.getMapper(ChbPrdPositionMapper.class);
			try{
				int count = 0;
				for(ChbPrdPosition pos : totalList){
					pos.setCreateUser(CommonUtil.getUserName());
					batchMapper.insertPos(pos);
	        		count++;
	        		if(count % 1000==0){
	            		session.commit();
	            		session.clearCache();
	        		}
	        	}	
				session.commit();
	    		session.clearCache();
			}
//			catch (Exception e) {
//	            logger.debug("批量插入头寸数据出错:" + e.getMessage());
//				session.rollback();
//	        } 
			finally {
	    		session.close();
	    	}
			logger.debug("头寸数据入库:----结束------" + df.format(new Date()));
		}
	}

	private void updatePosition(String prdcostalg, ChbPrdDeal sgDeal, ChbPrdPosition valuePosition) {
		if(valuePosition.getTdaddQty()==null){
			valuePosition.setTdaddQty(BigDecimal.ZERO);
		}
		if(valuePosition.getTdaddAmt()==null){
			valuePosition.setTdaddAmt(BigDecimal.ZERO);
		}
		if(valuePosition.getTdaddCost()==null){
			valuePosition.setTdaddCost(BigDecimal.ZERO);
		}
		if(valuePosition.getTdaddIntamt()==null){
			valuePosition.setTdaddIntamt(BigDecimal.ZERO);
		}
		//申购份额
		valuePosition.setTdaddQty(valuePosition.getTdaddQty().add(sgDeal.getTradeQty()));
		//申购金额
		valuePosition.setTdaddAmt(valuePosition.getTdaddAmt().add(sgDeal.getTradePrince()));
		//申购成本 
		if("02".equals(prdcostalg)){
			valuePosition.setTdaddCost(valuePosition.getTdaddCost().add(sgDeal.getTradeCost()==null?BigDecimal.ZERO:sgDeal.getTradeCost()));
		}else{
			valuePosition.setTdaddCost(valuePosition.getTdaddCost().add(sgDeal.getTradePrince()));
		}
		//新增利息
		valuePosition.setTdaddIntamt(valuePosition.getTdaddIntamt().add(sgDeal.getIntAmt()));
		//累计收益
		valuePosition.setTotalIntamt(valuePosition.getTotalIntamt().add(sgDeal.getIntAmt()));
		//剩余份额
		valuePosition.setBalanceQty(valuePosition.getBalanceQty().add(sgDeal.getTradeQty()));
		//剩余成本
		if("02".equals(prdcostalg)){
			valuePosition.setBalanceAmt(valuePosition.getBalanceAmt().add(sgDeal.getTradeCost()==null?BigDecimal.ZERO:sgDeal.getTradeCost()));
		}else{
			valuePosition.setBalanceAmt(valuePosition.getBalanceAmt().add(sgDeal.getTradePrince()));
		}
	}

	/**
	 * 高效查询交易数据
	 * @param bookDate
	 * @param indentifyMark
	 * @param ccy
	 * @param issuePartyId
	 * @param prodIdList
	 * @return
	 */
	private List<ChbPrdDeal> queryDealData(Date bookDate,List<String> prodIdList,
			                               String indentifyMark,
			                               String ccy,
			                               String issuePartyId,
			                               List<String> dealtype,
			                               String buyplace) {
		HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bookDate", bookDate);
		paraMap.put("prodIdList", prodIdList);
		paraMap.put("indentifyMark", indentifyMark);
		paraMap.put("ccy", ccy);
		paraMap.put("issuePartyId", issuePartyId);
		paraMap.put("dealtypes", dealtype);
		List<ChbPrdDeal> purList = new ArrayList<ChbPrdDeal>();
		template.select("com.joyintech.tams.svreport.position.dao.ChbPrdPositionMapper.queryDealList",paraMap,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	purList.add((ChbPrdDeal) resultContext.getResultObject());
            }
        });
		return purList;
	}
	/**
	 * 根据日期和交易类型查询
	 * @param bookDate
	 * @param prodIdList
	 * @param dealtype
	 * @return
	 */
	private List<ChbPrdDeal> queryDealData(Date bookDate,List<String> prodIdList, List<String> dealtype) {
		return queryDealData(bookDate,prodIdList,null,null,null,dealtype,null);
	}
	
	/**
	 * 构建初始头寸
	 * @param purDeal
	 * @param bookdate
	 * @return
	 */
	private ChbPrdPosition convertTdPosition(ChbPrdDeal purDeal, Date bookdate) {
		String dealDimension = SvrDataUtil.getSysParam("SVR_PRD_DEAL_DIMENSION");
		ChbPrdPosition model = new ChbPrdPosition();
		model.setCdate(bookdate);
		model.setProdId(purDeal.getProdId());
		model.setIssuePartyId(purDeal.getIssuePartyId());
		model.setIndentifyMark(purDeal.getIndentifyMark());
		model.setCcy(purDeal.getCcy());
		model.setTdaddAmt(purDeal.getTradePrince());
		model.setTdaddCost(purDeal.getTradePrince());
		model.setTdaddIntamt(BigDecimal.ZERO);
		model.setTdaddQty(purDeal.getTradeQty());
		model.setBalanceAmt(purDeal.getTradePrince());
		model.setBalanceQty(purDeal.getTradeQty());
		model.setTotalIntamt(BigDecimal.ZERO);
		model.setTotalUnitbonus(BigDecimal.ZERO);
		model.setOrgCode(purDeal.getOrgCode());
		model.setDeptCode(purDeal.getDeptCode());
		model.setCreateTime(bookdate);
		model.setCreateUser(CommonUtil.getUserName());
		model.setBuyplace(purDeal.getBuyPlace());
		model.setCustomerType2(purDeal.getCustomerType2());

		model.setvDate(purDeal.getvDate());
		if("02".equals(dealDimension)){
			model.setCustCounts(purDeal.getCustCounts());
		}else{
			model.setCustCounts(BigDecimal.ONE);
		}
		if("01".equals(purDeal.getIsagency())){
			model.setConsignamt(purDeal.getTradePrince());
		}
		return model;
	}
	/**
	 * 构建初始头寸
	 * @param redDeal
	 * @param bookdate
	 * @return
	 */
	private ChbPrdPosition buildInitPosition(ChbPrdDeal redDeal, Date bookdate) {
		String dealDimension = SvrDataUtil.getSysParam("SVR_PRD_DEAL_DIMENSION");
		ChbPrdPosition model = new ChbPrdPosition();
		model.setCdate(bookdate);
		model.setProdId(redDeal.getProdId());
		model.setIssuePartyId(redDeal.getIssuePartyId());
		model.setIndentifyMark(redDeal.getIndentifyMark());
		model.setCcy(redDeal.getCcy());
		model.setTdaddAmt(BigDecimal.ZERO);
		model.setTdaddCost(BigDecimal.ZERO);
		model.setTdaddIntamt(BigDecimal.ZERO);
		model.setTdaddQty(BigDecimal.ZERO);
		model.setBalanceAmt(BigDecimal.ZERO);
		model.setBalanceQty(BigDecimal.ZERO);
		model.setTotalIntamt(BigDecimal.ZERO);
		model.setTotalUnitbonus(BigDecimal.ZERO);
		model.setOrgCode(redDeal.getOrgCode());
		model.setDeptCode(redDeal.getDeptCode());
		model.setCreateTime(bookdate);
		model.setCreateUser(CommonUtil.getUserName());
		model.setBuyplace(redDeal.getBuyPlace());
		model.setCustomerType2(redDeal.getCustomerType2());
		if("02".equals(dealDimension)){
			model.setCustCounts(redDeal.getCustCounts());
		}else{
			model.setCustCounts(BigDecimal.ONE);
		}
		if("01".equals(redDeal.getIsagency())){
			model.setConsignamt(redDeal.getTradePrince());
		}
		return model;
	}
    /**
     * 功能:数据变更接口统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param executeDate
     * @param prodList
     * @param assetList
     * @throws BusinessException 
     * @see com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService#execute(java.util.Date, java.util.List, java.util.List)
     */
    @Override
    public void execute(Date executeDate, List<String> prodList, List<String> assetList,Map<String,List<String>> param) throws BusinessException {
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.产品头寸批处理);
        logInfoBatchStart();
        generatePositionData(executeDate, prodList);
        logger.debug("产品头寸批处理");
        logInfoBatchEnd();
    }

}
