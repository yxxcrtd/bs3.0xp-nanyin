package com.joyintech.tams.svreport.chbsd.prddeclaresub.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.component.svrreport.chb.dto.ChbPrdDeclareSubDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.dao.SysUploadFileInfoMapper;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.svreport.chb.perprodeclarereg.service.PerProDeclareReg001Service;
import com.joyintech.tams.svreport.chbsd.prddeclaresub.dao.SdProDeclareSvrMapper;

/**
 * 功能:产品申报信息登记报送批处理主service
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @作者：zhaoyongsheng @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdProDeclareBatchService {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SdProDeclareBatchServiceOneService sdProDeclareBatchServiceOneService;
	
	@Autowired
	private SdProDeclareSvrMapper sdProDeclareSvrMapper;

	@Autowired
	private SysUploadFileInfoMapper sysUploadFileInfoMapper;

	@Autowired
	private PerProDeclareReg001Service perProDeclareReg001Service;
	
	public void sendDeclareInfoMsg(Date batchDate, List<String> prodIdList) throws Exception {
	
			logger.debug("产品申报信息登记未报送数据检索开始------" + df.format(new Date()));
			// 1、报送数据检索,取得 List
			List<PerProDeclareRegDto> listSub = sdProDeclareSvrMapper.selcteDataByIds(DateUtil.format(batchDate), prodIdList);
			logger.debug("产品申报信息登记未报送数据检索结束------" + df.format(new Date()));

			if(StringUtil.isEmptyList(listSub)){
				logger.info("没有未报送数据，批处理结束");
	    		return;
	    	} 

			// 调用check逻辑
            logger.debug("产品申报信息登记数据检查开始------检查前数据个数：" + listSub.size());
			List<CheckReport001Dto> resultlist = perProDeclareReg001Service.check(null);
			Map<String,CheckReport001Dto> checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1, key2)->key1));
			for (int i = listSub.size() - 1; i >= 0; i--){
				if (checkmap.containsKey(listSub.get(i).getRegistSeqno())
						&& (StringUtil.isNotEmpty(checkmap.get(listSub.get(i).getRegistSeqno()).getErrorInfo())
						|| (StringUtil.isNotEmpty(checkmap.get(listSub.get(i).getRegistSeqno()).getWarnInfo())
						&& !StringUtil.equals("01", CacheUtil.getSysParam("CHB_WARNING_CHECKRESULT_IS_SEND"))))){
					listSub.remove(i);
				}
			}
            logger.debug("产品申报信息登记数据检查结束------检查后数据个数：" + listSub.size());

    		if(StringUtil.isEmptyList(listSub)){
    			logger.info("检查后的数据全部不合规，批处理结束");
        		return;
        	}

			//1.1、数据字典项需要进行格式转化
//			dealData(listSub);
            perProDeclareReg001Service.dealDataList(listSub, false);

			//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
			List<ChbPrdDeclareSubDto> dataList = new ArrayList<ChbPrdDeclareSubDto>();
			for(PerProDeclareRegDto sdDto : listSub){
				ChbPrdDeclareSubDto target = new ChbPrdDeclareSubDto();
				BeanUtils.copyProperties(target, sdDto);

				// add by zhangliang 20201016 产品申报登记发送接口需要把九大附件的路径传送进去 start
				// 实际申报登记实际生产上每日数据量不大，此处暂不考虑性能影响
				if (StringUtil.isNotEmpty(sdDto.getFileNo())){
					SysUploadFileInfo sysUploadFileInfo = sysUploadFileInfoMapper.selectByPrimaryKey(sdDto.getFileNo());
					if (sysUploadFileInfo != null){
						target.setAttachFilePath(sysUploadFileInfo.getPath());
					}
				}
				// add by zhangliang 20201016 产品申报登记发送接口需要把九大附件的路径传送进去 end

				dataList.add(target);
			}

			this.sendBatchCommon(dataList);
	}

	/*private void dealData(List<SdProDeclareSvrDto> listSub) {
		listSub.forEach(item -> {
			if(item.getProfitType()!=null){
				item.setProfitType(item.getProfitType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品收益类型, item.getProfitType()));
			}
			if(item.getTermtype()!=null){
				item.setTermtype(item.getTermtype().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品期限, item.getTermtype()));
			}
			if(item.getInvestArae()!=null){
				item.setInvestArae(item.getInvestArae().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资金投向地区, item.getInvestArae()));
			}
		    if(item.getInvestCountry()!=null){
		    	List<String> investCountrys=StringUtil.splitToList(GlobalConstant.COMMA, item.getInvestCountry());
				item.setInvestCountry("");
				for(String investCountry : investCountrys){
					item.setInvestCountry(item.getInvestCountry()+","+investCountry+" "+SvrDataUtil.getNameFromDataDict(DictCode.所属国家或地区, investCountry));
				}
				item.setInvestCountry(item.getInvestCountry().substring(1));
		    }
			
			if(item.getFinanSerModel()!=null){
				item.setFinanSerModel(item.getFinanSerModel().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.理财业务服务模式, item.getFinanSerModel()));			
			}
			if(item.getOperateMode()!=null){
				item.setOperateMode(item.getOperateMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品运作模式, item.getOperateMode()));
			}
			if(item.getBookMode()!=null){
				item.setBookMode(item.getBookMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.会计核算方式_中债, item.getBookMode()));
			}
			if(item.getCmmode()!=null){
				item.setCmmode(item.getCmmode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品资产配置方式_中债, item.getCmmode()));
			}
			if(item.getPmmode()!=null){
				item.setPmmode(item.getPmmode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.管理模式, item.getPmmode()));
			}
			if(item.getPricedMode()!=null){
				item.setPricedMode(item.getPricedMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品定价方式, item.getPricedMode()));
			}
			if(item.getInvAssetType()!=null){
				item.setInvAssetType(item.getInvAssetType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资金实际投向, item.getInvAssetType()));
			}
			if(item.getConPrdTraget()!=null){
				List<String> conPrdTragets=StringUtil.splitToList(GlobalConstant.COMMA, item.getConPrdTraget());
				item.setConPrdTraget("");
				for(String conPrdTraget : conPrdTragets){
					item.setConPrdTraget(item.getConPrdTraget()+","+conPrdTraget+" "+SvrDataUtil.getNameFromDataDict(DictCode.标的类别, conPrdTraget));
				}
				item.setConPrdTraget(item.getConPrdTraget().substring(1));
			}
			if(item.getContMode()!=null){
				item.setContMode(item.getContMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.合作模式, item.getContMode()));
			}
			if(item.getRiskType()!=null){
				List<String> riskTypeS=StringUtil.splitToList(GlobalConstant.COMMA, item.getRiskType());
				item.setRiskType("");
				for(String riskType : riskTypeS){
					item.setRiskType(item.getRiskType()+","+riskType+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资者风险偏好, riskType));
				}
				item.setRiskType(item.getRiskType().substring(1));
			}
			if(item.getInvestPrinDate()!=null){
				item.setInvestPrinDate(item.getInvestPrinDate().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日_中债, item.getInvestPrinDate()));
			}
			if(item.getInvestProfDate()!=null){
				item.setInvestProfDate(item.getInvestProfDate().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日_中债, item.getInvestProfDate()));
			}
			if(item.getRiskLevel()!=null){
				item.setRiskLevel(item.getRiskLevel().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品风险等级_申报, item.getRiskLevel()));
			}
			if(item.getTermRightFlag()!=null){
				item.setTermRightFlag(item.getTermRightFlag().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getTermRightFlag()));
			}
			if(item.getCusRedeemFlag()!=null){
				item.setCusRedeemFlag(item.getCusRedeemFlag().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getCusRedeemFlag()));
			}
			if(item.getCreditFlag()!=null){
				item.setCreditFlag(item.getCreditFlag().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getCreditFlag()));
			}
			if(item.getCreditType()!=null){
				List<String> creditTypes=StringUtil.splitToList(GlobalConstant.COMMA, item.getCreditType());
				String crediType="";
				for(String creditType : creditTypes){
					crediType=crediType+","+creditType+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品增信机构类型, creditType);
				}
				item.setCreditType(crediType.substring(1));
			}
			
			if(item.getCreditMode()!=null){
				item.setCreditMode(item.getCreditMode()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品增信形式, item.getCreditMode()));
			}
			if(item.getIsYield()!=null){
				item.setIsYield(item.getIsYield().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getIsYield()));
			}
			if(item.getIsYieldMeasBasis()!=null){
				item.setIsYieldMeasBasis(item.getIsYieldMeasBasis().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getIsYieldMeasBasis()));
			}
			if(item.getSaleArea()!=null){
				List<String> saleAreas=StringUtil.splitToList(GlobalConstant.COMMA, item.getSaleArea());
				item.setSaleArea("");
				for(String saleArea : saleAreas){
					item.setSaleArea(item.getSaleArea()+","+saleArea+" "+SvrDataUtil.getNameFromDataDict(DictCode.销售区域_中债, saleArea));
				}
				item.setSaleArea(item.getSaleArea().substring(1));
			}
			
			if(item.getOutDepCtry()!=null){
				List<String> outDepCtrys=StringUtil.splitToList(GlobalConstant.COMMA, item.getOutDepCtry());
				item.setOutDepCtry("");
				for(String outDepCtry : outDepCtrys){
					item.setOutDepCtry(item.getOutDepCtry()+","+outDepCtry+" "+SvrDataUtil.getNameFromDataDict(DictCode.所属国家或地区, outDepCtry));
				}
				item.setOutDepCtry(item.getOutDepCtry().substring(1));
			}
			
			if(item.getRaiseCcy()!=null){
				List<String> raiseCCYs=StringUtil.splitToList(GlobalConstant.COMMA, item.getRaiseCcy());
				item.setRaiseCcy("");
				for(String raiseCCY : raiseCCYs){
					item.setRaiseCcy(item.getRaiseCcy()+","+SvrDataUtil.getNameFromDataDict(DictCode.币种_中债, raiseCCY));
				}
				item.setRaiseCcy(item.getRaiseCcy().substring(1));
			}
			
			if(item.getCashPrinCcy()!=null){
				List<String> cashPrinCCYs=StringUtil.splitToList(GlobalConstant.COMMA, item.getCashPrinCcy());
				item.setCashPrinCcy("");
				for(String cashPrinCCY : cashPrinCCYs){
					item.setCashPrinCcy(item.getCashPrinCcy()+","+SvrDataUtil.getNameFromDataDict(DictCode.币种_中债, cashPrinCCY));
				}
				item.setCashPrinCcy(item.getCashPrinCcy().substring(1));
			}
			
			if(item.getCashProfCcy()!=null){
				List<String> cashProfCCYs=StringUtil.splitToList(GlobalConstant.COMMA, item.getCashProfCcy());
				item.setCashProfCcy("");
				for(String cashProfCCY : cashProfCCYs){
					item.setCashProfCcy(item.getCashProfCcy()+","+SvrDataUtil.getNameFromDataDict(DictCode.币种_中债, cashProfCCY));
				}
				item.setCashProfCcy(item.getCashProfCcy().substring(1));
			}
			
			//募集方式
			if(item.getRaiseType()!=null){
				item.setRaiseType(item.getRaiseType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.募集方式_中债, item.getRaiseType()));
			}
			//投资性质
			if(item.getIvstorientation()!=null){
				item.setIvstorientation(item.getIvstorientation().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资性质, item.getIvstorientation()));
			}
			//是否金融同业专属
			if(item.getExcFinSector()!=null){
				item.setExcFinSector(item.getExcFinSector().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getExcFinSector()));
			}
			//是否设置最短持有期限
			if(item.getIsShortTerm()!=null){
				item.setIsShortTerm(item.getIsShortTerm().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getIsShortTerm()));
			}
			//最短持有期后是否自由赎回
			if(item.getIsFreeRedem()!=null){
				item.setIsFreeRedem(item.getIsFreeRedem().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债_期限, item.getIsFreeRedem()));
			}
			//是否现金管理类
			if(item.getCashManaCate()!=null){
				item.setCashManaCate(item.getCashManaCate().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getCashManaCate()));
			}
			// 境内托管机构名称
            if(item.getInDepName()!=null){
                item.setInDepName(item.getInDepName().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.境内托管机构名称, item.getInDepName()));
            }
		});
		
	}*/

	/**
	 * 发送共通处理
	 * @param dataList
	 */
	public void sendBatchCommon(List<ChbPrdDeclareSubDto> dataList) throws Exception{
		//获取单批次发送条目数
		int dataCountPerTime = Integer.valueOf(
				CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME_FIRST));
		//MsgSendService msgSend = new MsgSendServiceImpl();
		logger.debug("产品申报信息登记报文报送开始------" + df.format(new Date()));
		// 处理批次
		int handleIndex = 1;
		int dataCount = dataList.size();
		// 报送数据条数
		List<ChbPrdDeclareSubDto> sendResult = new ArrayList<ChbPrdDeclareSubDto>();
		List<ChbPrdDeclareSubDto> newDataList = new ArrayList<ChbPrdDeclareSubDto>();
		newDataList.addAll(dataList);
		//分批处理数据报送
		if (dataCount <= dataCountPerTime) {
			sdProDeclareBatchServiceOneService
					.handleDataPerTime(handleIndex, dataCountPerTime,dataList,sendResult);
		}else{
			// 分批次数
			int part = dataCount / dataCountPerTime;
			for (int i = 0; i < part; i++ ) {
				sdProDeclareBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList.subList(0, dataCountPerTime),
								sendResult);
				newDataList.subList(0, dataCountPerTime).clear();
				handleIndex ++;

			}
			// 最后剩下的数据
			if (!StringUtil.isEmptyList(newDataList)) {
				sdProDeclareBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList,sendResult);
			}
		}
		//ChbMsgReturnInfoDto returnMsg = msgSend.sendChbPrdDeclareSubMsgs(dataList);
		logger.debug("产品申报信息登记报文报送结束------" + df.format(new Date()));
		//4、日志输出
		logger.info("产品申报信息登记自动报送处理结束, 共检索出未报送数据 " + dataList.size() + " 条 "
				+" 实际报送数据 " + sendResult.size() + "条");
	}

}
