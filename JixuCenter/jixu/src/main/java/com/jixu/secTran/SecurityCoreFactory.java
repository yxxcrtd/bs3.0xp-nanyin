package com.jixu.secTran;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.TransTypes;
import com.jixu.common.util.ReturnResult;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.SmfDivService;

/** 
 * @ClassName 		SecurityCoreFactory
 * @author  		Pan Wei 
 * @Date 			2017年1月11日 下午7:18:34 	
 * @Description		[]  
 */

@Service("secCoreFactory")
public class SecurityCoreFactory implements InitializingBean, ApplicationContextAware {
	private Map<Integer, SecurityBasic> secTranTypeMap;
	private Map<String, SecurityAssetService> secAssetTypeMap;
	private Map<Integer, SmfDivService> corpActionMap;
    private ApplicationContext applicationContext;
    
    private static Properties tableIDProperties;
    private static Properties bkcashacctProperties;
	
    private static final Logger logger = LoggerFactory.getLogger(SecurityCoreFactory.class);
    //以assettype为key,保存交易类型组成的哈希表
    private static HashMap<String, HashMap<Integer, String>> assetTypeHM;
    private static HashMap<Integer, String> tranTypeOption;
    private static HashMap<String, String> assetTypeOption;
    private static HashMap<Integer, Integer> tranWorkFlowStepOption;
    private static HashMap<Integer, MsgService> msgServiceMap;
    private static HashMap<Integer, String> tranTypeFormMap;
    private static HashMap<Integer, String> tranTypeAssetTypeMap;
    private static HashMap<Integer, String> corpActionNameMap;

    private static List<Integer> commonTypeList;
    public final static int ALL = 0;
    

    public void afterPropertiesSet() throws Exception {
    	logger.info("开始初始化证券服务中心...");
    	secTranTypeMap=new HashMap<Integer, SecurityBasic>();
    	secAssetTypeMap=new HashMap<String, SecurityAssetService>();
		assetTypeHM=new HashMap<String, HashMap<Integer,String>>();
		tranTypeOption=new HashMap<Integer, String>();
		assetTypeOption=new HashMap<String, String>();
		tranWorkFlowStepOption=new HashMap<Integer, Integer>();
		msgServiceMap=new HashMap<Integer, MsgService>();
		tranTypeFormMap=new HashMap<Integer, String>();
		commonTypeList=new ArrayList<Integer>();
		tranTypeAssetTypeMap=new HashMap<Integer,String>();
		corpActionMap=new HashMap<Integer,SmfDivService>();
		corpActionNameMap=new HashMap<Integer,String>();
		Map<String, SecurityBasic> beanMap = applicationContext.getBeansOfType(SecurityBasic.class);
		Map<String, SecurityAssetService> assetbeanMap = applicationContext.getBeansOfType(SecurityAssetService.class);
		Map<String, SmfDivService> smfDivMap = applicationContext.getBeansOfType(SmfDivService.class);
		
		for (SecurityBasic secbean : beanMap.values()) {
			//保证TranType是整个系统的唯一标志
			if(secTranTypeMap.containsKey(secbean.tranType())){
				//判断是否有重复的交易类型,暂停系统,并提出警示
				logger.error(ReturnResult.getResultHM().get(ReturnResult.tranID_Exist) + ": " + secbean.tranType());
				logger.error(secTranTypeMap.get(secbean.tranType()) + " | " + secbean.getClass().getName());
				System.exit(0);
			}else{
				secTranTypeMap.put(secbean.tranType(), secbean);
				tranTypeOption.put(secbean.tranType(), secbean.tranDesc());
			}
			 
			//判断有没有指明资产类型    如果没有放入commonTypeList供所有资产使用
			if(secbean.assetService()==null){
				commonTypeList.add(secbean.tranType());
			}else{
				//如果指明资产类型  则放入到指定的资产模块assetTypeHM中
				if(assetTypeHM.containsKey(secbean.assetService().assetType())){
					assetTypeHM.get(secbean.assetService().assetType()).put(secbean.tranType(), secbean.tranDesc());
				}else{
					HashMap<Integer, String> tranHM=new HashMap<Integer, String>();
					tranHM.put(secbean.tranType(), secbean.tranDesc());
					assetTypeHM.put(secbean.assetService().assetType(), tranHM);
				}
				tranTypeAssetTypeMap.put(secbean.tranType(), secbean.assetService().assetType());
			}
			
			tranWorkFlowStepOption.put(secbean.tranType(), secbean.workFlowSteps());
			
			msgServiceMap.put(secbean.tranType(), secbean.msgService());
			
			tranTypeFormMap.put(secbean.tranType(), secbean.tradeFormURL());
			
			/*headerFormMap.put(secbean.msgService().msgHeader(), secbean.tradeFormURL());*/
		}
		
		for(SecurityAssetService assetbean:assetbeanMap.values()){
			if(secAssetTypeMap.containsKey(assetbean.assetType())){
				//判断是否有重复的交易类型,暂停系统,并提出警示
				logger.error(ReturnResult.getResultHM().get(ReturnResult.assetType_Exist) + ": " + assetbean.assetType());
				logger.error(secAssetTypeMap.get(assetbean.assetType()) + " | " + assetbean.getClass().getName());
				System.exit(0);
			}else{
				secAssetTypeMap.put(assetbean.assetType(), assetbean);
				assetTypeOption.put(assetbean.assetType(), assetbean.assetDesc());
			}
		}
		
		smfDivMap = applicationContext.getBeansOfType(SmfDivService.class);
		for (SmfDivService smfDivBean : smfDivMap.values()) {
			corpActionMap.put(smfDivBean.caType(), smfDivBean);
			corpActionNameMap.put(smfDivBean.caType(), smfDivBean.description());
		}
		
		tableIDProperties = PropertiesLoaderUtils.loadAllProperties("tableid.properties");
		bkcashacctProperties = PropertiesLoaderUtils.loadAllProperties("bkcashacct.properties");
		logger.info("初始化证券服务中心结束");
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 获取该交易类型对应的证券类型
	 * @param tranType
	 * @return
	 */
	public SecurityBasic createSecTranTypeBean(int tranType) {
		return secTranTypeMap.get(tranType);
	}
	
	public SecurityAssetService createSecAssetTypeBean(String assetType){
		return secAssetTypeMap.get(assetType);
	}
	
	public static HashMap<String, HashMap<Integer, String>> getAssetTypeHM(){
		return assetTypeHM;
	}
	//根据caType找到对应的公司行为
	public SmfDivService createSmfDivBean(int caType) {
		return corpActionMap.get(caType);
	}
	/**
	 * @param assetType
	 * @param allFlag	如果allFlag=true,在选项里加入"全部"
	 * @return
	 */
	public static HashMap<Integer, String> getTranTypeHM(String assetType, boolean allFlag){
		HashMap<Integer, String> tranTypeHM=assetTypeHM.get(assetType);
		if(tranTypeHM!=null){
			if(allFlag){
				tranTypeHM.put(ALL, "全部");
			}
		}
		//将公共的交易类型放到所有资产类别下 ，以后添加一个公共交易类型就不用修改代码了
		for(int type:commonTypeList){  
			tranTypeHM.put(type, tranTypeOption.get(type));
		}
		//如果是非 定期回购 、债券、定期存款      则移除收益调整
		if(!assetType.equals(AssetTypes.TERM_REPO)&&!assetType.equals(AssetTypes.Bond)&&!assetType.equals(AssetTypes.TimeDeposit)&&!assetType.equals(AssetTypes.MONEY_MARKET_FUND)){
			tranTypeHM.remove(TransTypes.IncomeUpdate);
		}
		if(assetType.equals(AssetTypes.CAPITAL)){
			tranTypeHM.remove(TransTypes.Portfolio_InternalTran);
			tranTypeHM.remove(TransTypes.CostUpdate);
		}
		if(assetType.equals(AssetTypes.CASH)||assetType.equals(AssetTypes.FXOption)||assetType.equals(AssetTypes.SPOT_CURRENCY)||assetType.equals(AssetTypes.FORWARD_CURRENCY)){
			tranTypeHM.remove(TransTypes.Portfolio_InternalTran);
			tranTypeHM.remove(TransTypes.CostUpdate);
			tranTypeHM.remove(TransTypes.SecContribution);
		}
		//暂时将公司行为(现金股利  股票股利)只放到普通股下面
		if(!assetType.equals(AssetTypes.COMMON_STOCK)){
			tranTypeHM.remove(TransTypes.CashDividend);
			tranTypeHM.remove(TransTypes.StockDividend);
		}
		return tranTypeHM;
	}
	
	public static HashMap<Integer, String> getAllTranTypeHM(){
		return tranTypeOption;
	}
	
	public static HashMap<String, String> getAllAssetTypeHM(){
		return assetTypeOption;
	}

	/**
	 * 获取 secAssetTypeMap
	 */
	
	public Map<String, SecurityAssetService> getSecAssetTypeMap() {
		return secAssetTypeMap;
	}
	
	public static HashMap<Integer, Integer> getTranTypeWorkFlowStepMap(){
		return tranWorkFlowStepOption;
	}
	
	public static HashMap<Integer, MsgService> getMsgService(){
		return msgServiceMap;
	}
	
	public static HashMap<Integer, String> getTranTypeFormMap(){
		return tranTypeFormMap;
	}
	
	public static HashMap<Integer, String> getTranTypeAssetTypeMap(){
		return tranTypeAssetTypeMap;
	}
	public HashMap<Integer, String> getcorpActionNameMap() {
		return corpActionNameMap;
	}
	
	public static Properties getTableIDProperties(){
		return tableIDProperties;
	}
	
	public static Properties getBkcashacctProperties(){
		return bkcashacctProperties;
	}
}
