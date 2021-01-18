package com.joyintech.tams.svreport.chbsd.assetinfosub.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.component.svrreport.chb.dto.ChbAssetInfoDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.service.AssetInfoRegist001Service;
import com.joyintech.tams.svreport.chbsd.assetinfosub.dao.SdAssetInfoSvrMapper;
/**
 * 功能:资产要素信息登记直连报送生成批处理主service
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @创建时间：
 * @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdAssetiInfoRegBatchService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	@Autowired
	private SdAssetInfoSvrMapper SdAssetInfoSvrMapper;
	
	@Autowired
	private SdAssetiInfoRegBatchServiceOneService sdAssetiInfoRegBatchServiceOneService;

	@Autowired
	private AssetInfoRegist001Service assetInfoRegist001Service;
	
	
	
	public void sdAssetInfoRegData(Date batchDate) throws Exception{
		//1、报送数据检索,取得 List
		logger.info("【资产要素信息登记】未报送数据检索开始------" +  df.format(new Date()));
		List<AssetInfoRegist001Dto> resultList = SdAssetInfoSvrMapper.selcteUnRegData(DateUtil.format(batchDate));
		logger.info("【资产要素信息登记】未报送数据检索结束------" +  df.format(new Date()));
		
		if(StringUtil.isEmptyList(resultList)){
			logger.info("没有未报送数据，批处理结束");
    		return;
    	}

		// 调用check逻辑
		logger.debug("【资产要素信息登记】数据检查开始------检查前数据个数：" + resultList.size());
		List<CheckReport001Dto> resultlist = assetInfoRegist001Service.check(null);
		Map<String,CheckReport001Dto> checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1, key2)->key1));
		for (int i = resultList.size() - 1; i >= 0; i--){
			if (checkmap.containsKey(resultList.get(i).getRegistSeqno())
					&& (StringUtil.isNotEmpty(checkmap.get(resultList.get(i).getRegistSeqno()).getErrorInfo())
					|| (StringUtil.isNotEmpty(checkmap.get(resultList.get(i).getRegistSeqno()).getWarnInfo())
					&& !StringUtil.equals("01", CacheUtil.getSysParam("CHB_WARNING_CHECKRESULT_IS_SEND"))))){
				resultList.remove(i);
			}
		}
		logger.debug("【资产要素信息登记】数据检查结束------检查后数据个数：" + resultList.size());

		if(StringUtil.isEmptyList(resultList)){
			logger.info("检查后的数据全部不合规，批处理结束");
    		return;
    	}
		
		//1.1、处理数据项为字典项目数据
		assetInfoRegist001Service.dealDataList(resultList, false);
		assetInfoRegist001Service.dealDataListExt(resultList, false);
//		dealData(resultList);
		
		//2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		List<ChbAssetInfoDto> msgRstList = transformModelToMsg(resultList);

		this.sendBatchCommon(msgRstList);
	}

	/*
	private void dealData(List<SdAssetInfoSvrDto> resultList) {
		resultList.forEach(item -> {
			/**公共信息（必填）
			//资产负债类别
			if (StringUtil.isNotEmpty(item.getAssetType2())){
				item.setAssetType2(item.getAssetType2()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别,item.getAssetType2()));
			}
			//交易流通场所
			if(StringUtil.isNotEmpty(item.getTradeMarket())) {
				item.setTradeMarket(item.getTradeMarket()+" "+SvrDataUtil.getNameFromDataDict(DictCode.交易流通场所_中债,item.getTradeMarket()));
			}
			*//**本行/他行存款||大额存单*//*
			if(DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue().equals(item.getAssetType())){
				//计息基础
				if(StringUtil.isNotEmpty(item.getBasis())) {
					item.setBasis(item.getBasis()+" "+SvrDataUtil.getNameFromDataDict(DictCode.计息基础_中债,item.getBasis()));
				}
				//存款类型
				if(StringUtil.isNotEmpty(item.getDepoType())) {
					item.setDepoType(item.getDepoType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.存款类型,item.getDepoType()));
				}
				//结构性存款挂钩标的类别
				if(StringUtil.isNotEmpty(item.getTargetType())) {
					String[] tmp = item.getTargetType().split(",");
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<tmp.length;i++){
						sb.append(tmp[i] + " " + SvrDataUtil.getNameFromDataDict(DictCode.标的类别,tmp[i])).append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					item.setTargetType(sb.toString());
				}
				
			}
			*//**债券类资产/理财直接融资工具/同业存单*//*
			else if(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(item.getAssetType())){
				//债券类资产，“代码”列不应展示后缀
            	if(StringUtil.isNotEmpty(item.getAssetId())){
            		String assetId=item.getAssetId();
            		if(assetId.contains(".")){
            			assetId=assetId.substring(0,assetId.indexOf("."));
            			item.setAssetId(assetId);
            		}
            	}
            	//具体类别
            	if (StringUtil.isNotEmpty(item.getChbSectype())){
    				item.setChbSectype(item.getChbSectype()+" "+SvrDataUtil.getNameFromDataDict(DictCode.具体类别,item.getChbSectype()));
    			}
				//发行方式
				if(StringUtil.isNotEmpty(item.getIssueMode())) {
					item.setIssueMode(item.getIssueMode()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行方式,item.getIssueMode()));
				}
				//主体评级
				if(StringUtil.isNotEmpty(item.getSecMainGrade())) {
					item.setSecMainGrade(item.getSecMainGrade()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资产评级,item.getSecMainGrade()));
				}
				//发行机构类型（按规模划分）
				if(StringUtil.isNotEmpty(item.getPartyType1())) {
					item.setPartyType1(item.getPartyType1()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分,item.getPartyType1()));
				}
				//发行机构类型（按技术领域划分）
				if(StringUtil.isNotEmpty(item.getPartyType3())) {
					item.setPartyType3(item.getPartyType3()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分,item.getPartyType3()));
				}
				//发行机构类型（按经济类型划分）
				if(StringUtil.isNotEmpty(item.getPartyType2())) {
					item.setPartyType2(item.getPartyType2()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分,item.getPartyType2()));
				}
				//机构所属行业
				if (StringUtil.isNotEmpty(item.getIndustry())){
				    item.setIndustry(item.getIndustry()+" "+SvrDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类,item.getIndustry()));
				}
				//登记托管机构
				if(StringUtil.isNotEmpty(item.getRegistOrg())) {
					item.setRegistOrg(item.getRegistOrg()+" "+SvrDataUtil.getNameFromDataDict(DictCode.登记托管机构,item.getRegistOrg()));
				}
			}
			*//**拆放同业及买入返售/同业拆入及卖出回购*//*
			else if(DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue().equals(item.getAssetType())){
				//计息基础
				if(StringUtil.isNotEmpty(item.getBasis())) {
					item.setBasis(item.getBasis()+" "+SvrDataUtil.getNameFromDataDict(DictCode.计息基础_中债,item.getBasis()));
				}
				//对手方类型
				if(StringUtil.isNotEmpty(item.getRivalPartyType())) {
					item.setRivalPartyType(item.getRivalPartyType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.对手方类型,item.getRivalPartyType()));
				}
				//回购标的类别
				if(StringUtil.isNotEmpty(item.getTargetType1())) {
					item.setTargetType1(item.getTargetType1()+" "+SvrDataUtil.getNameFromDataDict(DictCode.回购标的类别,item.getTargetType1()));
				}
			}
			*//**非标准化债权类资产/新增可投资资产 *//*
			else if(DictCodeListExt2.资产_负债类别_非标准化债权类资产_信贷资产流转项目.getValue().equals(item.getAssetType())){
				//收/受益权类型
				if(StringUtil.isNotEmpty(item.getProfitType())) {
					item.setProfitType(item.getProfitType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.收受益权类型,item.getProfitType()));
				}
				//是否属于买入返售
				if(StringUtil.isNotEmpty(item.getIsResale())) {
					item.setIsResale(item.getIsResale()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIsResale()));
				}
				//是否有预期收益率
				if(StringUtil.isNotEmpty(item.getRateFlag())) {
					item.setRateFlag(item.getRateFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getRateFlag()));
				}
				//计息类型
				if(StringUtil.isNotEmpty(item.getCouponSpecies())) {
					item.setCouponSpecies(item.getCouponSpecies()+" "+SvrDataUtil.getNameFromDataDict(DictCode.计息类型,item.getCouponSpecies()));
				}
				//规则付息标识
//				if(StringUtil.isNotEmpty(item.getInterestRuleFlag())) {
//					item.setInterestRuleFlag(item.getInterestRuleFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.规则付息标识,item.getInterestRuleFlag()));
//				}
				//利息分布方式
				if(StringUtil.isNotEmpty(item.getIntpayRule())) {
					item.setIntpayRule(item.getIntpayRule()+" "+SvrDataUtil.getNameFromDataDict(DictCode.利息分布方式,item.getIntpayRule()));
				}
				//计息基础
				if(StringUtil.isNotEmpty(item.getBasis())) {
					item.setBasis(item.getBasis()+" "+SvrDataUtil.getNameFromDataDict(DictCode.计息基础_中债,item.getBasis()));
				}
				//是否有浮动因子
				if(StringUtil.isNotEmpty(item.getFloatFactorFlag())) {
					item.setFloatFactorFlag(item.getFloatFactorFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getFloatFactorFlag()));
				}
				//结构档次
				if(StringUtil.isNotEmpty(item.getStructGrade())) {
					item.setStructGrade(item.getStructGrade()+" "+SvrDataUtil.getNameFromDataDict(DictCode.结构档次,item.getStructGrade()));
				}
				//还本方式
				if(StringUtil.isNotEmpty(item.getRepaymentType())) {
					item.setRepaymentType(item.getRepaymentType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.还本方式,item.getRepaymentType()));
				}
				//分期还本条款标识
				if(StringUtil.isNotEmpty(item.getPeriodRepaymentFlag())) {
					item.setPeriodRepaymentFlag(item.getPeriodRepaymentFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.分期还本条款标识,item.getPeriodRepaymentFlag()));
				}
				//融资人类型（按规模划分）
				if(StringUtil.isNotEmpty(item.getFinancierType1())) {
					item.setFinancierType1(item.getBasis()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分,item.getFinancierType1()));
				}
				//融资人类型（按技术领域划分）
				if(StringUtil.isNotEmpty(item.getFinancierType2())) {
					item.setFinancierType2(item.getFinancierType2()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分,item.getFinancierType2()));
				}
				//融资人类型（按经济类型划分）
				if(StringUtil.isNotEmpty(item.getFinancierType3())) {
					item.setFinancierType3(item.getFinancierType3()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分,item.getFinancierType3()));
				}
				//项目是否属于重点监控行业和领域
				if(StringUtil.isNotEmpty(item.getIndustryFlag())) {
					item.setIndustryFlag(item.getIndustryFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIndustryFlag()));
				}
				//重点监控行业和领域类别
				if(StringUtil.isNotEmpty(item.getMonitType())) {
					item.setMonitType(item.getMonitType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.重点监控行业和领域类别,item.getMonitType()));
				}
				//担保方式
				if(StringUtil.isNotEmpty(item.getGuaType())) {
					item.setGuaType(item.getGuaType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.担保方式,item.getGuaType()));
				}
				//抵质押物类型
				if(StringUtil.isNotEmpty(item.getPledgeType())) {
					item.setPledgeType(item.getPledgeType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.抵质押物类型,item.getPledgeType()));
				}
				//担保性质
				if(StringUtil.isNotEmpty(item.getNatureGuarantee())) {
					item.setNatureGuarantee(item.getNatureGuarantee()+" "+SvrDataUtil.getNameFromDataDict(DictCode.担保性质,item.getNatureGuarantee()));
				}
				//担保人与融资人关系
				if(StringUtil.isNotEmpty(item.getRelationFinan())) {
					item.setRelationFinan(item.getRelationFinan()+" "+SvrDataUtil.getNameFromDataDict(DictCode.担保人与融资人关系,item.getRelationFinan()));
				}
				//融资人主体评级
				if(StringUtil.isNotEmpty(item.getFinancierGrade())) {
					item.setFinancierGrade(item.getFinancierGrade()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资产评级,item.getFinancierGrade()));
				}
				//资产外部评级
				if(StringUtil.isNotEmpty(item.getOutGrade())) {
					item.setOutGrade(item.getOutGrade()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资产评级,item.getOutGrade()));
				}
				//含权类型
				if(StringUtil.isNotEmpty(item.getContainType())) {
					item.setContainType(item.getContainType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.含权类型,item.getContainType()));
				}
				//行权方式
				if(StringUtil.isNotEmpty(item.getExerciseType())) {
					item.setExerciseType(item.getExerciseType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.行权方式,item.getExerciseType()));
				}
				//永续条款类型
				if(StringUtil.isNotEmpty(item.getPerpetualClauseType())) {
					item.setPerpetualClauseType(item.getPerpetualClauseType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.永续条款类型,item.getPerpetualClauseType()));
				}
				//利息递延条款类型
				if(StringUtil.isNotEmpty(item.getDeferredInterestType())) {
					item.setDeferredInterestType(item.getDeferredInterestType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.利息递延条款类型,item.getDeferredInterestType()));
				}
				//递延利息是否计息
				if(StringUtil.isNotEmpty(item.getDeferredInterestInt())) {
					item.setDeferredInterestInt(item.getDeferredInterestInt()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getDeferredInterestInt()));
				}
				//部分赎回标识
				if(StringUtil.isNotEmpty(item.getPartRedemFlag())) {
					item.setPartRedemFlag(item.getPartRedemFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getPartRedemFlag()));
				}
			}
			*//**票据类/信用证类资产*//*
			else if(DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue().equals(item.getAssetType())){
				//是否为收/受益权
				if(StringUtil.isNotEmpty(item.getIsRight())) {
					item.setIsRight(item.getIsRight()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIsRight()));
				}
				//是否属于买入返售
				if(StringUtil.isNotEmpty(item.getIsResale())) {
					item.setIsResale(item.getIsResale()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIsResale()));
				}
				//类型
				if(StringUtil.isNotEmpty(item.getNotstandSecType())) {
					item.setNotstandSecType(item.getNotstandSecType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.票据类信用证类资产类型,item.getNotstandSecType()));
				}
				//行业
				if(StringUtil.isNotEmpty(item.getIndustryShares())) {
				   item.setIndustryShares(item.getIndustryShares()+" "+SvrDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类,item.getIndustryShares()));
				}
			}
			*//**权益类资产*//*
			else if(DictCodeListExt2.资产_负债类别_权益类资产.getValue().equals(item.getAssetType())){
				//股票类型
				if(StringUtil.isNotEmpty(item.getSharesType())) {
					item.setSharesType(item.getSharesType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.股票类型,item.getSharesType()));
				}
				//投资阶段
				if(StringUtil.isNotEmpty(item.getInvestStage())) {
					item.setInvestStage(item.getInvestStage()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资阶段,item.getInvestStage()));
				}
				//企业类型（按规模划分）
				if(StringUtil.isNotEmpty(item.getSharesType1())) {
					item.setSharesType1(item.getSharesType1()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分,item.getSharesType1()));
				}
				//企业类型（按技术领域划分）
				if(StringUtil.isNotEmpty(item.getSharesType3())) {
					item.setSharesType3(item.getSharesType3()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分,item.getSharesType3()));
				}
				//企业类型（按经济类型划分）
				if(StringUtil.isNotEmpty(item.getSharesType2())) {
					item.setSharesType2(item.getSharesType2()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分,item.getSharesType2()));
				}
				//是否为质押融资
				if(StringUtil.isNotEmpty(item.getIsPledge())) {
					item.setIsPledge(item.getIsPledge()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIsPledge()));
				}
				//是否为债转股
				if(StringUtil.isNotEmpty(item.getDeptEquity())) {
					item.setDeptEquity(item.getDeptEquity()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getDeptEquity()));
				}
			}
			*//**金融衍生品 *//*
			else if(DictCodeListExt2.资产_负债类别_金融衍生品.getValue().equals(item.getAssetType())){
				//标的类别
				if(StringUtil.isNotEmpty(item.getTargetType3())) {
					String[] tmp = item.getTargetType3().split(",");
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<tmp.length;i++){
						sb.append(tmp[i] + " " + SvrDataUtil.getNameFromDataDict(DictCode.标的类别,tmp[i])).append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					item.setTargetType3(sb.toString());
				}
				//持有目的
				if(StringUtil.isNotEmpty(item.getHoldPurpose())) {
					item.setHoldPurpose(item.getHoldPurpose()+" "+SvrDataUtil.getNameFromDataDict(DictCode.持有目的,item.getHoldPurpose()));
				}
			}
			*//**QDII拆出/逆回购资产 *//*
			else if(DictCodeListExt2.资产_负债类别_QDII拆出_逆回购资产.getValue().equals(item.getAssetType())){
				//计息基础
				if(StringUtil.isNotEmpty(item.getBasis())) {
					item.setBasis(item.getBasis()+" "+SvrDataUtil.getNameFromDataDict(DictCode.计息基础_中债,item.getBasis()));
				}
			}
			*//**资产管理产品*//*
			else if(DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(item.getAssetType())){
				//是否由金融资产投资公司发行
				if(StringUtil.isNotEmpty(item.getFinancialComissue())) {
					item.setFinancialComissue(item.getFinancialComissue()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getFinancialComissue()));
				}
				//资金实际投向
				if(StringUtil.isNotEmpty(item.getTruActType())) {
					item.setTruActType(item.getTruActType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资金实际投向,item.getTruActType()));
				}
				//资管计划属性
				if(StringUtil.isNotEmpty(item.getProProperty())) {
					item.setProProperty(item.getProProperty()+" "+SvrDataUtil.getNameFromDataDict(DictCode.委外投资属性_中债,item.getProProperty()));
				}
				//是否有预期收益率
				if(StringUtil.isNotEmpty(item.getRateFlag())) {
					item.setRateFlag(item.getRateFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getRateFlag()));
				}
				//购买结构
				if(StringUtil.isNotEmpty(item.getBuyStruct())) {
					item.setBuyStruct(item.getBuyStruct()+" "+SvrDataUtil.getNameFromDataDict(DictCode.购买结构,item.getBuyStruct()));
				}
				//管理方式
				if(StringUtil.isNotEmpty(item.getManageType())) {
					item.setManageType(item.getManageType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.管理方式,item.getManageType()));
				}
				//是否掌控计划底层资产详情
				if(StringUtil.isNotEmpty(item.getIsCtrlBottom())) {
					item.setIsCtrlBottom(item.getIsCtrlBottom()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIsCtrlBottom()));
				}
			}
			*//**公募基金/私募基金*//*
			else if(DictCodeListExt2.资产_负债类别_公募基金.getValue().equals(item.getAssetType())
					||DictCodeListExt2.资产_负债类别_私募基金.getValue().equals(item.getAssetType())){
				//登记备案机构
				if(StringUtil.isNotEmpty(item.getRegistOrg1())) {
					item.setRegistOrg1(item.getRegistOrg1()+" "+SvrDataUtil.getNameFromDataDict(DictCode.登记备案机构,item.getRegistOrg1()));
				}
				//是否属于政府投资基金
				if(StringUtil.isNotEmpty(item.getIsGovernFund())) {
					item.setIsGovernFund(item.getIsGovernFund()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIsGovernFund()));
				}
				//政府投资基金投向
				if(StringUtil.isNotEmpty(item.getGovernInvestFund())) {
					item.setGovernInvestFund(item.getGovernInvestFund()+" "+SvrDataUtil.getNameFromDataDict(DictCode.政府投资基金投向,item.getGovernInvestFund()));
				}
				//是否由金融资产投资公司发行
				if(StringUtil.isNotEmpty(item.getFinancialComissue())) {
					item.setFinancialComissue(item.getFinancialComissue()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getFinancialComissue()));
				}
				//投资阶段
				if(StringUtil.isNotEmpty(item.getInvestStage())) {
					item.setInvestStage(item.getInvestStage()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资阶段,item.getInvestStage()));
				}
				//投资企业类型（按规模划分）
				if(StringUtil.isNotEmpty(item.getSharesType1())) {
					item.setSharesType1(item.getSharesType1()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分,item.getSharesType1()));
				}
				//投资企业类型（按技术领域划分）
				if(StringUtil.isNotEmpty(item.getSharesType3())) {
					item.setSharesType3(item.getSharesType3()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分,item.getSharesType3()));
				}
				//投资企业类型（按经济类型划分）
				if(StringUtil.isNotEmpty(item.getSharesType2())) {
					item.setSharesType2(item.getSharesType2()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分,item.getSharesType2()));
				}
			}
			*//**委外投资——协议方式*//*
			else if(DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue().equals(item.getAssetType())){
				//资金实际投向
				if(StringUtil.isNotEmpty(item.getTruActType())) {
					item.setTruActType(item.getTruActType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资金实际投向,item.getTruActType()));
				}
				//委外投资属性
				if(StringUtil.isNotEmpty(item.getProProperty())) {
					item.setProProperty(item.getProProperty()+" "+SvrDataUtil.getNameFromDataDict(DictCode.委外投资属性_中债,item.getProProperty()));
				}
				//是否有预期收益率
				if(StringUtil.isNotEmpty(item.getRateFlag())) {
					item.setRateFlag(item.getRateFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getRateFlag()));
				}
			}
			*//**其他非标准化债权类/权益类/代客境外理财投资QDII/新增可投资资产*//*
			else if(DictCodeListExt2.资产_负债类别_各类资产的其他项.getValue().equals(item.getAssetType())){
				//行内资产类别
				if(StringUtil.isNotEmpty(item.getInAssetType())) {
					item.setInAssetType(item.getInAssetType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.行内资产类别,item.getInAssetType()));
				}
				//是否有预期收益率
				if(StringUtil.isNotEmpty(item.getRateFlag())) {
					item.setRateFlag(item.getRateFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getRateFlag()));
				}
				//融资人类型（按规模划分）
				if(StringUtil.isNotEmpty(item.getFinancierType1())) {
					item.setFinancierType1(item.getBasis()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分,item.getFinancierType1()));
				}
				//融资人类型（按技术领域划分）
				if(StringUtil.isNotEmpty(item.getFinancierType2())) {
					item.setFinancierType2(item.getFinancierType2()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分,item.getFinancierType2()));
				}
				//融资人类型（按经济类型划分）
				if(StringUtil.isNotEmpty(item.getFinancierType3())) {
					item.setFinancierType3(item.getFinancierType3()+" "+SvrDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分,item.getFinancierType3()));
				}
				//项目是否属于重点监控行业和领域
				if(StringUtil.isNotEmpty(item.getIndustryFlag())) {
					item.setIndustryFlag(item.getIndustryFlag()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getIndustryFlag()));
				}
				//重点监控行业和领域类别
				if(StringUtil.isNotEmpty(item.getMonitType())) {
					item.setMonitType(item.getMonitType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.重点监控行业和领域类别,item.getMonitType()));
				}
				//对应资产外部评级
				if(StringUtil.isNotEmpty(item.getOutGrade())) {
					item.setOutGrade(item.getOutGrade()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资产评级,item.getOutGrade()));
				}
				//担保方式
				if(StringUtil.isNotEmpty(item.getGuaType())) {
					item.setGuaType(item.getGuaType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.担保方式,item.getGuaType()));
				}
				//抵质押物类型
				if(StringUtil.isNotEmpty(item.getPledgeType())) {
					item.setPledgeType(item.getPledgeType()+" "+SvrDataUtil.getNameFromDataDict(DictCode.抵质押物类型,item.getPledgeType()));
				}
				//担保性质
				if(StringUtil.isNotEmpty(item.getNatureGuarantee())) {
					item.setNatureGuarantee(item.getNatureGuarantee()+" "+SvrDataUtil.getNameFromDataDict(DictCode.担保性质,item.getNatureGuarantee()));
				}
				//担保人与融资人关系
				if(StringUtil.isNotEmpty(item.getRelationFinan())) {
					item.setRelationFinan(item.getRelationFinan()+" "+SvrDataUtil.getNameFromDataDict(DictCode.担保人与融资人关系,item.getRelationFinan()));
				}
				//是否为债转股
				if(StringUtil.isNotEmpty(item.getDeptEquity())) {
					item.setDeptEquity(item.getDeptEquity()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是或否,item.getDeptEquity()));
				}
			}
		});
	}*/

	/**
	 * 将报送数据的DTO转化为报文拼接所需要的DTO
	 * @param list
	 * @return
	 */
	private List<ChbAssetInfoDto> transformModelToMsg(List<AssetInfoRegist001Dto> list) {
		List<ChbAssetInfoDto> resultList = new ArrayList<ChbAssetInfoDto>();
		list.forEach(item -> {
			ChbAssetInfoDto tmp = new ChbAssetInfoDto();
			BeanUtils.copyProperties(item,tmp);
			resultList.add(tmp);
		});
		return resultList;
	}

	/**
	 * 发送共通处理
	 * @param dataList
	 */
	public void sendBatchCommon(List<ChbAssetInfoDto> dataList) throws Exception{
		int dataCountPerTime = Integer.valueOf(
				CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME_FIRST));

		logger.info("开始按批次处理【资产要素信息登记】数据,共检索出未报送数据:" + dataList.size() + " 条,"+"每批次处理数据条数：" + dataCountPerTime);
		// 处理批次
		int handleIndex = 1;

		int dataCount = dataList.size();
		// 报送数据条数
		List<ChbAssetInfoDto> sendResult = new ArrayList<ChbAssetInfoDto>();
		List<ChbAssetInfoDto> newDataList = new ArrayList<ChbAssetInfoDto>();
		newDataList.addAll(dataList);
		//分批处理数据报送
		if (dataCount <= dataCountPerTime) {
			sdAssetiInfoRegBatchServiceOneService
					.handleDataPerTime(handleIndex, dataCountPerTime,dataList,sendResult);
		}else{
			// 分批次数
			int part = dataCount / dataCountPerTime;
			for (int i = 0; i < part; i++ ) {
				sdAssetiInfoRegBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList.subList(0, dataCountPerTime),
								sendResult);
				newDataList.subList(0, dataCountPerTime).clear();
				handleIndex ++;
			}
			// 最后剩下的数据
			if (!StringUtil.isEmptyList(newDataList)) {
				sdAssetiInfoRegBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList,sendResult);
			}
		}

		//3、日志输出
		logger.info("【资产要素信息登记】自动报送处理结束, 共检索出未报送数据 " + dataList.size() + " 条 "
				+" 实际报送数据 " + sendResult.size() + "条");
	}
}
