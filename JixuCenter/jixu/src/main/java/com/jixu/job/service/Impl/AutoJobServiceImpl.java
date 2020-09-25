package com.jixu.job.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.TranCloType;
import com.jixu.dao.intf.SmfDivDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.job.service.AutoJobService;
import com.jixu.job.service.AutoMaturityService;
import com.jixu.job.service.ContractualSetService;
import com.jixu.job.service.SmfDivService;
import com.jixu.msg.dto.TranBaseMsgDto;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.secTran.SecurityAssetService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SmfDiv;
import com.orm.obj.TranClo;

@Service("autoJobService")
public class AutoJobServiceImpl implements AutoJobService{
	
	@Resource(name="cleanAndPostDataJob")
	private CleanAndPostDataJob cleanAndPostDataJob;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired
	private SmfDivDao smfDivDao;
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Autowired
	protected SecPositionCenter secPositionCenter;
	
	@Resource(name="secCoreFactory")
	protected SecurityCoreFactory secCoreFactory;
	
	@Resource(name="contractualSetService")
	private ContractualSetService setCashService;
	
	@Autowired
	private AutoMaturityService inteSetService;
	
	private static final Logger logger = LoggerFactory.getLogger(AutoJobServiceImpl.class);
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer executeAutoJob(int userID, String portnum, Date ledgerDate) {
		logger.info(portnum + ": 自动任务开始...");
		ResultContainer mainContainer=new ResultContainer();
		//1.清理做账日之后的数据
		logger.info(portnum + ": 清理账后数据...");
		mainContainer=cleanAndPostDataJob.executeJob(portnum, ledgerDate);
		if(!mainContainer.isCorrectFlag())
			return mainContainer;
		
		//2.自动删除当天自动生成的交易
		logger.info(portnum + ": 删除当天自动生成交易...");
		List<String> tanumlist=tranBasicDao.loadAutoTranBasicList(portnum,ledgerDate);
		for (String tanum : tanumlist) {
			ResultContainer container=tradeService.deleteTran(tanum,false);
			if(!container.isCorrectFlag()) {
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorMsg(container.getErrorMsg());
				return mainContainer;
			}
		}
		
		//3.公司行为
		logger.info(portnum + ": 启动公司行为...");
		List<SmfDiv> divList=smfDivDao.loadSmfDivListByDate(ledgerDate);
		if(divList.size()>0){
			for(SmfDiv div:divList){
				//获取记录日那天,该股的持仓
				TranCloType tranCloType=new TranCloType(portnum, div.getCusip(), div.getRecordDate());
				List<TranClo> cloList=secPositionCenter.getSecClo(tranCloType, false);
				if(cloList.size()>0){
					MsgContainer msgContainer=new MsgContainer();
					SmfDivService smfdiv=secCoreFactory.createSmfDivBean(div.getCaType());
					if(smfdiv==null) {
						msgContainer.setCorrectFlag(false);
						msgContainer.setErrorMsg("暂不支持该公司行为: " + div.getCaType());
					}else {
						List<TranBaseMsgDto> dtoList=smfdiv.genCorpActionTranDto(portnum, div);
						for(TranBaseMsgDto dto:dtoList) {
							String json=JSONObject.toJSONString(dto);
							HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
							msgContainer.setFieldsHM(fieldsMap);
							msgContainer.setJsonMsg(json);
							msgContainer.setUserID(JixuUserUtil.getUserID());
							msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
							msgContainer=tradeService.operateTran(msgContainer);
							if(!msgContainer.isCorrectFlag())
								break;
						}
					}
					if(!msgContainer.isCorrectFlag()) {
						mainContainer.setCorrectFlag(false);
						mainContainer.setErrorMsg(msgContainer.getErrorMsg());
						return mainContainer;
					}
				}
			}
		}
		
		//4. 资产类型下auto自动触发的业务
		logger.info(portnum + ": 启动自动交易...");
		Map<String,SecurityAssetService> autoJobServiceHM=secCoreFactory.getSecAssetTypeMap();
		Iterator<Entry<String, SecurityAssetService>> entryIter=autoJobServiceHM.entrySet().iterator();
		while(entryIter.hasNext()){
			ResultContainer container=new ResultContainer();
			try {
				SecurityAssetService assetservice=entryIter.next().getValue();
				container=assetservice.createJobService().autoJob(userID, portnum, ledgerDate);
			} catch (JNLException e) {
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
				container.setCorrectFlag(false);
				container.setErrorMsg(e.getMessage());
				logger.error("Error", e);
			}
			
			//排除没有auto job的证券类型
			if(container!=null){
				if(!container.isCorrectFlag()){
					mainContainer.setCorrectFlag(false);
					mainContainer.setErrorMsg(container.getErrorMsg());
					return mainContainer;
				}
			}
		}
		
		//5. 银行利息结转
		mainContainer=inteSetService.autoMaturity(userID, portnum, ledgerDate);
		if(!mainContainer.isCorrectFlag())
			return mainContainer;
		
		//6. post今天的所有交易
		logger.info(portnum + ": 提交当天交易...");
		mainContainer=cleanAndPostDataJob.postTodayTrans(portnum, ledgerDate);
		if(!mainContainer.isCorrectFlag())
			return mainContainer;
		
		//7. 结转应收应付
		logger.info(portnum + ": 结转应收应付...");
		ResultContainer container=setCashService.settleToCash(userID, portnum, ledgerDate);
		if(container!=null && !container.isCorrectFlag()) {
			mainContainer.setCorrectFlag(false);
			mainContainer.setErrorMsg(container.getErrorMsg());
			return mainContainer;
		}
		logger.info(portnum + ": 自动任务结束...");
		return mainContainer;
	}
}
