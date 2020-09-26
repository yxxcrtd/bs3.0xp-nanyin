package com.joyintech.tams.svreport.chb.investtradereg.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.batch.report.common.db.SvrDBUtil;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbPrdDealSub;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.chb.investtrade.dao.MstCounterPartyDtoMapper;
import com.joyintech.tams.svreport.chb.investtrade.dto.MstCounterPartyDto;
import com.joyintech.tams.svreport.chb.investtradereg.dao.InvestTradeRegist001Mapper;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;
import com.joyintech.tams.svreport.interf.chb.assetinfo.service.ChbAssetInfoInterf;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:交易登记批处理
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestTradeRegistBatchService extends SvrCBBaseService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private InvestTradeRegist001Mapper investTradeRegist001Mapper;
    /**
     * 交易对手mapper
     */
    @Autowired
    private MstCounterPartyDtoMapper mstCounterPartyDtoMapper;
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Autowired
    private SvrDBUtil svrDBUtil;
	
	/*股票衍生品资金流动类型为'09', '10'且发生金额为0不生成登记数据 add by gaojian 2019-09-18 begin*/
	private static List<String> derivativesList = new ArrayList<String>();
	
	static {
		derivativesList.add("25");
		derivativesList.add("26");
		derivativesList.add("27");
		derivativesList.add("28");
		derivativesList.add("29");
		derivativesList.add("30");
	}
	/*股票衍生品资金流动类型为'09', '10'且发生金额为0不生成登记数据 add by gaojian 2019-09-18 end*/

    /**
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    private int reportInsertCount;

    /**
     * 数据批量一次查询或者删除条数
     */
    @Value("${application.reportSelectOrDeleteCount}")
    private int reportSelectOrDeleteCount;

	/**
	 * 功能:资产交易登记
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月21日
     * @param executeDate 跑批日
     * @param prodList 产品代码
     * @param tradenoList 交易编号
	 * void
	 * @throws BatchException 
	 */
	@SuppressWarnings("deprecation")
    public void autoRegistInvestTrade(Date executeDate,List<String> prodList,List<String> tradenoList) {
		// 全交易登记
		if (!StringUtil.isEmptyList(tradenoList)
				&& InterfConstants.SVR_TRADENOLIST_ALLDEAL.equals(tradenoList.get(0))){
			tradenoList = null;
		}
		// 通道交易是否穿透報送 01 是 02 否
		String isChlBtm = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_CHL_BTM);
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.投资交易登记报送);
		// 批处理开始
		logInfoBatchStart();
		// 获取数据日期为跑批日的资产要素信息
		String registDate = DateUtil.format(executeDate);
        logger.debug("投资交易信息登记查询数据：开始----------");
        InvestTradeRegistBatchService investTradeRegistBatchService =  SpringContextUtil.getBean(InvestTradeRegistBatchService.class);
		List<InvestTradeRegist001Dto> investTradeRegList = investTradeRegistBatchService.queryTradeByInterfdateBatch(registDate,tradenoList,prodList);
		/*股票衍生品资金流动类型为'09', '10'且发生金额为0不生成登记数据 add by gaojian 2019-09-18 begin*/
		investTradeRegList.removeIf(investTradeReg -> {
			return (derivativesList.contains(investTradeReg.getInvestVariety()) 
					&& (StringUtil.equals("09", investTradeReg.getCurAssetType())
							|| StringUtil.equals("10", investTradeReg.getCurAssetType()))
					&& BigDecimal.ZERO.compareTo(investTradeReg.getAmt()) == 0);
		});
		/*股票衍生品资金流动类型为'09', '10'且发生金额为0不生成登记数据 add by gaojian 2019-09-18 end*/
        logger.debug("投资交易信息登记查询数据：开始----------");
		// 登记插入列表
		List<InvestTradeRegist001Dto> insertDataList = new ArrayList<InvestTradeRegist001Dto>();
        // 登记删除列表
        List<InvestTradeRegist001Dto> deleteDataList = new ArrayList<InvestTradeRegist001Dto>();
		// 报送时点通用方法
		// 当前批次报送时点集合
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
		if (CollectionUtils.isNotEmpty(investTradeRegList)) {
            // 查询所有已登记的资产交易信息
            // 分批处理数据,100个交易查询一次
            List<InvestTradeRegist001Dto> tradeAlreadyRegList = new ArrayList<>();
            for (int i = 0;i < investTradeRegList.size();){
                int end = i + 100;
                if (end > investTradeRegList.size()){
                    end = investTradeRegList.size();
                }
                tradeAlreadyRegList.addAll(investTradeRegist001Mapper.selectAllTradeReg(investTradeRegList.subList(i, end)));
                i = end;
            }
            // 存放已登记的资产要素
            Map<String,InvestTradeRegist001Dto> tradeRegMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(tradeAlreadyRegList)){
                tradeRegMap = tradeAlreadyRegList.stream().collect(Collectors.toMap(p->p.getTradeCode(), p->p,(key1,key2)->key2));
            }
		    
	        List<MstCounterPartyDto> counterList = mstCounterPartyDtoMapper.selectAllTradeCounter();
	        Map<String,String> counterMap = new HashMap<String,String>();
	        Map<String,String> inSidecounterMap = new HashMap<String,String>();
	        if (!CollectionUtils.isEmpty(counterList)){
	            for(MstCounterPartyDto counter : counterList){
	                counterMap.put(counter.getCounterId(),counter.getCounterName());
	                if("05".equals(counter.getCounterType())){//交易对手类型为05的是内部交易对手
	                	inSidecounterMap.put(counter.getCounterId(),counter.getCounterName());
	                }
	            }
	        }
	        String ifrealCounter = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IF_REAL_COUNTER);
	        String virtualCounter = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_VIRTUAL_COUNTER);
			Map<String, BigDecimal> rateMap = new HashMap<>();
			for (InvestTradeRegist001Dto dto : investTradeRegList) {
			    List<Date> dateListUn = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_资产交易信息登记.getValue(),
	                DictCodeListExt2.细分类别_非净值型.getValue(), DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, dto.getDealdate());
		        Map<String, List<Date>> dateMap1 = new HashMap<String, List<Date>>();
		        List<Date> dateListN = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_资产交易信息登记.getValue(),
		                DictCodeListExt2.细分类别_净值型.getValue(), DictCodeListExt2.报送数据状态_新建.getValue(), dateMap1, dto.getDealdate());
					// 非人民币币种折算人民币金額
		        boolean ifysp=false;
		        if("25".equals( dto.getInvestVariety())||"26".equals( dto.getInvestVariety())
		        		||"27".equals( dto.getInvestVariety())||"28".equals( dto.getInvestVariety())
		        		||"29".equals( dto.getInvestVariety())||"30".equals( dto.getInvestVariety())
		        		){
		        	ifysp=true;
		        }
	       
				if (!StringUtil.equals(dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue())) {
					// 此处节约性能可以不计算（接口接入时已经计算过了）
					dto.setCnyAmt(
							ChbCcyConvertUtil.changeCnyMoney(dto.getCcy(), dto.getAmt(), dto.getDealdate(), rateMap));
					// 单位净价、单位全价折算人民币 保留四位小数s
					BigDecimal unitPriceFull=null;
					if(dto.getUnitPriceFull()!=null){
						unitPriceFull = ChbCcyConvertUtil.changeCnyMoney(dto.getCcy(), dto.getUnitPriceFull(), dto.getDealdate(), rateMap);
					}
					BigDecimal unitPriceNet=null;
					if(dto.getUnitPriceNet()!=null){
						unitPriceNet = ChbCcyConvertUtil.changeCnyMoney(dto.getCcy(), dto.getUnitPriceNet(), dto.getDealdate(), rateMap);
					}
    				// 单位净价、单位全价折完为0则置空
					if(unitPriceFull==null){
						dto.setUnitPriceFull(null);
					}else if (BigDecimal.ZERO.compareTo(unitPriceFull) == 0&&!ifysp){
                        dto.setUnitPriceFull(null);
    				} else {
    				    dto.setUnitPriceFull(unitPriceFull.setScale(4,BigDecimal.ROUND_HALF_EVEN));
    				}
					if(unitPriceNet==null){
						 dto.setUnitPriceNet(null);
					}else if (BigDecimal.ZERO.compareTo(unitPriceNet) == 0&&!ifysp){
                        dto.setUnitPriceNet(null);
                    } else {
                        dto.setUnitPriceNet(unitPriceNet.setScale(4,BigDecimal.ROUND_HALF_EVEN));
                    }
				} else {
					dto.setCnyAmt(dto.getAmt());
				}
				// 判斷是否是淨值型產品，设置报送时点
				if (StringUtil.isNotEmpty(dto.getProdType())
						&& (dto.getProdType().equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue()) 
								|| dto.getProdType().equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue()))) {
					if (CollectionUtils.isNotEmpty(dateListN)) {
						dto.setChbSubmitDeadline(dateListN.get(0));
						dto.setSubmitDeadline(dateListN.get(1));
					}
				} else {
					if (CollectionUtils.isNotEmpty(dateListUn)) {
						dto.setChbSubmitDeadline(dateListUn.get(0));
						dto.setSubmitDeadline(dateListUn.get(1));
					}
				}
				// 登记编码
				dto.setRegistSeqno(IDGenerator.generate(36));
				// 数据状态：批处理日期
				dto.setStatusDate(executeDate);
				// 机构代码
				dto.setOrgCode(CommonUtil.getOrgCode());
				// 交易登记日
				dto.setDealRegistDate(executeDate);
                // 创建人
                dto.setCreateUser(CommonUtil.getUserName());
                // 创建时间
                dto.setCreateTime(DateUtil.now());
                // 更新人
                dto.setUpdateUser(CommonUtil.getUserName());
                // 更新时间
                dto.setUpdateTime(DateUtil.now());
                // 创建部门
                dto.setCreateDept(CommonUtil.getDeptCode());
				// 费用交易如果交易编号为空则重新生成
				if (StringUtil.isEmpty(dto.getTradeCode()) 
				        && dto.getTradeType().equals(DictCodeListExt2.投资端交易类型_产品费用交易.getValue())){
					dto.setTradeCode(getTradeProdFeeNo(dto.getDealdate(),"PRDF"));
				}
                // 生成的登记都为未报送
                dto.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
                // 根据基础数据状态设置报送数据状态
                // 库里有已报送才生成删除修改
                if (tradeRegMap.get(dto.getTradeCode())!= null){
                    if (DictCodeListExt1.维护数据状态_删除.getValue().equals(dto.getStatus())){
                        // 如果有删除已报送则不生成删除未报送，否则生成删除未报送
                        if (DictCodeListExt2.报送数据状态_删除.getValue().
                                equals(tradeRegMap.get(dto.getTradeCode()).getStatus())){
                            deleteDataList.add(dto);
                            continue;
                        } else{
                            dto.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
                            deleteDataList.add(dto);
                        }
                    } else if (DictCodeListExt1.维护数据状态_修改.getValue().equals(dto.getStatus())){
                        // 已登记数据是删除状态，业务数据是修改或者新建状态，则生成修改未报送
                        dto.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
                        deleteDataList.add(dto);
                    } else if (DictCodeListExt1.维护数据状态_有效.getValue().equals(dto.getStatus())){
                        // 已登记数据是删除状态，业务数据是修改或者新建状态，则生成修改未报送
                        if (DictCodeListExt2.报送数据状态_删除.getValue().
                                equals(tradeRegMap.get(dto.getTradeCode()).getStatus())){
                            dto.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
                            deleteDataList.add(dto);
                        } else{
                            deleteDataList.add(dto);
                            continue;
                        }
                    } else {
                        logger.info("数据维护状态有误，应为：D|A|M!");
                        continue;
                    }
                } else{
                    if (DictCodeListExt1.维护数据状态_删除.getValue().equals(dto.getStatus())){
                        deleteDataList.add(dto);
                        continue;
                    } else {
                        dto.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
                        deleteDataList.add(dto);
                    }
                }
                if("1".equals(ifrealCounter)&&inSidecounterMap.containsKey(dto.getTradeCounter())){
                	dto.setTradeCounter(virtualCounter);
                }else{
	                // 交易对手
	                List<String> countList = StringUtil.splitToList(GlobalConstant.COMMA, dto.getTradeCounter());
	                if (!StringUtil.isEmptyList(countList)){
	                    List<String> counterNameList = new ArrayList<String>();
	                    for (String counterid: countList){
	                        if (counterMap.get(counterid) != null){
	                            counterNameList.add(counterMap.get(counterid));
	                        }
	                    }
	                    String counterName = StringUtil.joinString(counterNameList, GlobalConstant.COMMA);
	                    // 查不到交易对手则直接取值，ps：交易表中数据接入交易对手可能是资产对应的交易对手（中文名称）
	                    if (!StringUtil.isEmpty(counterName)){
	                        dto.setTradeCounter(counterName);
	                    } else {
	                        // 债券存续期添加交易对手：登记托管机构
	                        if (dto.getInvestVariety() != null 
	                                &&(DictCodeListExt2.投资品种_债券.getValue().equals(dto.getInvestVariety())
	                                        || DictCodeListExt2.投资品种_ABS.getValue().equals(dto.getInvestVariety()))
	                                &&(DictCodeListExt2.投资类型_到期_本金.getValue().equals(dto.getDealtype())
	                                        || DictCodeListExt2.投资类型_到期_利息.getValue().equals(dto.getDealtype())
	                                        || DictCodeListExt2.投资类型_中间还本_本金.getValue().equals(dto.getDealtype())
	                                        || DictCodeListExt2.投资类型_中间还本_利息.getValue().equals(dto.getDealtype())
	                                        || DictCodeListExt2.投资类型_中间付息.getValue().equals(dto.getDealtype()))){
	                            List<String> counterRegNameList = new ArrayList<String>();
	                            for (String counterid: countList){
	                                counterRegNameList.add(SvrDataUtil.getNameFromDataDict(DictCode.登记托管机构_中债, counterid));
	                            }
	                            String counterRegName = StringUtil.joinString(counterRegNameList, GlobalConstant.COMMA);
	                            if(counterRegName!=null && !counterRegName.equals("null")){
	                            	dto.setTradeCounter(counterRegName);
	                            }else{
	                            	dto.setTradeCounter(dto.getTradeCounter());
	                            }
	                        } else{
	                            dto.setTradeCounter(dto.getTradeCounter());
	                        }
	                    }
	                }
                }
                // 根据通道开关设置，处理登记数据是否入库
				if (StringUtil.isNotEmpty(dto.getTradeType())) {
					// 判斷通道交易是否穿透報送，穿透報送底層不穿透報送通道交易
					if (dto.getTradeType().equals(DictCodeListExt2.投资端交易类型_通道交易.getValue())) {
						if (isChlBtm.equals(DictCodeList.是否_中债_否.getValue())) {
							// 准备插入处理修改未报送的数据
							insertDataList.add(dto);
						}
					} else if (dto.getTradeType().equals(DictCodeListExt2.投资端交易类型_资产交易.getValue())
							|| dto.getTradeType().equals(DictCodeListExt2.投资端交易类型_资管计划交易.getValue())) {
						if (StringUtil.isNotEmpty(dto.getChlCpctId())){
							if (isChlBtm.equals(DictCodeList.是否_中债_是.getValue())){
								// 准备更新处理新建未报送
								insertDataList.add(dto);
							}
						} else{
							// 准备更新处理新建未报送
							insertDataList.add(dto);
						}
					} else if (dto.getTradeType().equals(DictCodeListExt2.投资端交易类型_通道費用交易.getValue())) {
						// 合作资产费用交易是否需报送交易登记，根据【合作资产是否穿透报送】开关设定，规则如下：
						// 【合作资产是否穿透报送】为1：是，需要报送合作资产费用交易；
						// 【合作资产是否穿透报送】为2：否，不需要报送合作资产费用交易；
						
						// 通道费交易，跟通道是否穿透报送开关无关  --- 修改 zhangyf 20191028
//						if (isChlBtm.equals(DictCodeList.是否_中债_是.getValue())) {
							// 准备插入处理修改未报送的数据
							insertDataList.add(dto);
//						}
					} else {
						// 准备插入处理修改未报送的数据
						insertDataList.add(dto);
					}
				}
			}
		}
		
		//update by leipeng 插入的数据获取表 内的实际登记编码
		Map<String,String> data = svrDBUtil.getAllRegCode();
		for (InvestTradeRegist001Dto dto : insertDataList) {
		   if(CommonUtil.isBAmodel()){
        	   dto.setRegistBankCode(data.get(dto.getProdId()));
           }
		   //针对资金流动类型为09 投资资产所得收益时，资产计量方式应为空，见附件数据源规范要求  update by leipeng 20200712
		   if(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue().equals(dto.getCurAssetType())){
			   dto.setAssetMethod(null);
		   }
	   	}
	    
	       
		
		insertDB(deleteDataList,insertDataList);
		// 记录日志批处理结束
		logInfoBatchEnd();
	}

    
    /**
     * 功能:查询接入日当天的资产要素
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月18日
     * @param interfDate 跑批日
     * @prodList 产品代码
     * @return 
     * List<ChbAssetInfo>
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<InvestTradeRegist001Dto> queryTradeByInterfdateBatch(String interfDate,
    		List<String> tradenoList,List<String> prodList){
		List<InvestTradeRegist001Dto> tradeList = new ArrayList<InvestTradeRegist001Dto>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("registDate", interfDate);
		param.put("tradenoList", tradenoList);
		param.put("prodList", prodList);
        template.select("com.joyintech.tams.svreport.chb.investtradereg.dao.InvestTradeRegist001Mapper.selectInvestTradeForRegist",
        		param,new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	tradeList.add((InvestTradeRegist001Dto) resultContext.getResultObject());
            }
        });
    	return tradeList;
    }
	/**
	 * 功能:获取交易编号
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月15日
	 * @param dealDate
	 * @param tradeType
	 * @return 
	 * String
	 */
	private String getTradeProdFeeNo(Date dealDate,String tradeType){
		String tradeNo = "";
		tradeNo += "tradeType";
		tradeNo += DateUtil.format(dealDate,"yyyyMMdd");
		tradeNo += investTradeRegist001Mapper.getTradeNo();
		return tradeNo;
	}
	
	/**
	 * 功能:带提交的方式单条处理数据结果，入库
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年5月20日
	 * @param assetList 
	 * void
	 */
	private void insertDB(List<InvestTradeRegist001Dto> deleteDataList,List<InvestTradeRegist001Dto> insertDataList){
	    if (CollectionUtils.isNotEmpty(deleteDataList)) {
            // 插入前先删除未报送的登记数据
            logger.debug("投资交易信息登记删除未报送数据：删除开始----------");
            // 数据批量一次删除条数,如果参数设置过大则强制定位50
            int batchDeleteCount = reportSelectOrDeleteCount;
            // 每批最后一个的下标
            int batchDeleteLastIndex = batchDeleteCount;            
            for(int index = 0; index < deleteDataList.size();){
                if(batchDeleteLastIndex > deleteDataList.size()-1){
                    batchDeleteLastIndex = deleteDataList.size();
                    investTradeRegist001Mapper.deleteByTradeCode(deleteDataList.subList(index, batchDeleteLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    investTradeRegist001Mapper.deleteByTradeCode(deleteDataList.subList(index, batchDeleteLastIndex));
                    index = batchDeleteLastIndex;//设置下一批下标
                    batchDeleteLastIndex = index + batchDeleteCount;                     
                }
            }
            logger.debug("投资交易信息登记删除未报送数据：删除结束----------");
            logger.debug("投资交易信息登记插入数据：插入开始----------");
            int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try{
                InvestTradeRegist001Mapper batchMapper =  session.getMapper(InvestTradeRegist001Mapper.class);
                for(InvestTradeRegist001Dto asset : insertDataList){
                    batchMapper.insert(asset);
                    count++;
                    if(count%1000==0){
                        session.commit();
                        // 清理缓存，防止溢出
                        session.clearCache();
                    }
                }
                session.commit();
                // 清理缓存，防止溢出
                session.clearCache();
            }
            catch(Exception e){
            	logger.error(e.getMessage(),e);
                session.rollback();
                throw e;
            }
            finally {
                session.close();
            }
            logger.debug("投资交易信息登记插入数据：插入结束----------");
        }
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
        try {
            List<String> tradenoList = param.get("tradeNoList");
            autoRegistInvestTrade(executeDate, prodList, tradenoList);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
            throw new BusinessException(e);
        }
    }
    
}
