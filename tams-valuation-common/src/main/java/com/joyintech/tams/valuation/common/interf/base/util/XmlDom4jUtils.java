package com.joyintech.tams.valuation.common.interf.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.TempCheckInfo;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;

@Service
@Scope("prototype")
public class XmlDom4jUtils {
    
    /**
     * 根节点
     */
    private String rootElement;
    
    /**
     * 数据过滤器
     */
    private Map<String, List<String>> filter;
    
    private Logger logger = LoggerFactory.getLogger(XmlDom4jUtils.class);
    
    private String entityPath = "com.joyintech.tams.valuation.common.interf.tempTables.entity.";
    
//    private List<String> dealAll =new ArrayList<>();
    
    // xml解析
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public TemplateCheckReturnResult readStringXml(TemplateOrderInfoReaderResult acctEntity){
        
    	Map<String,TempCheckInfo> tmpNameMap = checkFinprodDealMap();//全部交易模板
        TemplateCheckReturnResult result = new TemplateCheckReturnResult();
        // 文件路径
        String filePath = acctEntity.getFilePath();
        // 模板英文名
        String tmpEName = acctEntity.getTmpEName();
        //DTO名称
        String dtoName = acctEntity.getDtoName();
        // check数据
        List<TemplateOrderInfoReaderDTO> listTemplateOrderInfoDTO = acctEntity.getListTemplateOrderInfoDTO();
        // 日志记录list
        List<ValInterflog> valInterflogList = new ArrayList<ValInterflog>();
        List listAll = new ArrayList();
        // 获取文件绝对路径，需要带上日期，避免取到其他名称类似的文件
        List<String> pathList;
        if ("ZG".equals(acctEntity.getSystemid())) {
            pathList = SearchFilePath.queryFilePath(filePath,
                tmpEName + "_" + DateUtil.format(acctEntity.getResovleDate(),
                    DateUtil.DEFAULT_OTHER_FORMAT));
        } else {
            pathList = SearchFilePath.queryFilePath(filePath, tmpEName);
        }

        /**add by wujie on 20210502 start**/
        result.setReadFile(true);
        if(pathList==null || pathList.size()==0){
            result.setReadFile(false);
        }
        /**add by wujie on 20210502 end**/

        for (String path : pathList) {
            List list = new ArrayList();
            try {
            	String entityClassName = entityPath + dtoName;
                SAXParserFactory factory = SAXParserFactory.newInstance();
                // 通过factory获取SAXParser实例
                SAXParser parser = factory.newSAXParser();
                SAXParserHandler handler = new SAXParserHandler(); 
                boolean flag = handler.init(entityClassName,listTemplateOrderInfoDTO,acctEntity,
                		rootElement,filter,tmpNameMap);
                if(flag){
                	parser.parse(path, handler);
                	list = handler.getResultList();
                	//如果有多个文件会导致日志只有最后一个文件的问题修改 leipeng 20180829
                	if(null!=handler.getLogResult()){
                		valInterflogList.addAll(handler.getLogResult());
                	}
                }else{
                	throw new Exception("没有为模版配置对应的校验规则.");
                }
            }
            catch (Exception e) {
            	logger.error("读取文件" + path + "错误", e);
                result.setReadFile(false);
            }
            listAll.addAll(list);
        }
        result.setValInterflog(valInterflogList);
        result.setT(listAll);
        return result;
    }

    
    /**
     * 设置 rootElement
     * @param rootElement rootElement
     */
    public void setRootElement(String rootElement) {
        if (rootElement != null) {
            this.rootElement = rootElement;
        } else {
            this.rootElement = "info";
        }
    }
    
    /**
     * 设置 filter
     * @param filter filter
     */
    public void setFilter(Map<String, List<String>> filter) {
        this.filter = filter;
    }
    
    public Map<String,TempCheckInfo> checkFinprodDealMap(){
    	Map<String,TempCheckInfo> prdNameMap = new HashMap<String,TempCheckInfo>();
		prdNameMap.put(TempConstant.净值型产品存续期交易表, 
				new TempCheckInfo(TempConstant.净值型产品存续期交易表,"prodId","PRD"));
        prdNameMap.put(TempConstant.净值型产品分红除权表, 
        		new TempCheckInfo(TempConstant.净值型产品分红除权表,"prodId","PRD"));
        prdNameMap.put(TempConstant.净值型产品分红交易表, 
        		new TempCheckInfo(TempConstant.净值型产品分红交易表,"prodId","PRD"));
        prdNameMap.put(TempConstant.净值型产品费用交易表, 
        		new TempCheckInfo(TempConstant.净值型产品费用交易表,"prodId","PRD"));
    	
        prdNameMap.put(TempConstant.货币型产品存续期交易表, 
        		new TempCheckInfo(TempConstant.货币型产品存续期交易表,"prodId","PRD"));
        prdNameMap.put(TempConstant.货币型产品分红交易表, 
        		new TempCheckInfo(TempConstant.货币型产品分红交易表,"prodId","PRD"));
        prdNameMap.put(TempConstant.货币型产品费用交易表, 
        		new TempCheckInfo(TempConstant.货币型产品费用交易表,"prodId","PRD"));
        
        prdNameMap.put(TempConstant.预期型产品存续期交易表, 
        		new TempCheckInfo(TempConstant.预期型产品存续期交易表,"prodId","PRD"));
        prdNameMap.put(TempConstant.预期型产品费用交易表, 
        		new TempCheckInfo(TempConstant.预期型产品费用交易表,"prodId","PRD"));
    	
        /**modify by wujie on 20181229 start
        prdNameMap.put(TempConstant.拆借交易, 
        		new TempCheckInfo(TempConstant.拆借交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.拆借收息, 
        		new TempCheckInfo(TempConstant.拆借收息,"prodId","INVEST"));
        **/
        String finprodTypes = DictCodeListExt1.金融产品类型_拆入.getValue()
        		+","+DictCodeListExt1.金融产品类型_拆出.getValue()
        		+","+DictCodeListExt1.金融产品类型_同业借款.getValue();
        TempCheckInfo temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.拆借交易);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("pTradeId");
        temp.setFinprodTypes(finprodTypes);
        
        prdNameMap.put(TempConstant.拆借交易, temp);
        
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.拆借收息);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("dlId");
        temp.setFinprodTypes(finprodTypes);
        
        prdNameMap.put(TempConstant.拆借收息,temp);
        /**modifyu by wujie on 20181229 end**/
        
        /**add by tanxuan on 20191227 start**/
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.拆借应收款项转让交易);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("dlId");
        temp.setFinprodTypes(finprodTypes);
        
        prdNameMap.put(TempConstant.拆借应收款项转让交易,temp);
        /**add by tanxuan on 20191227 end**/
        
    	
        
        /** modify by wujie on 20181229 start
        prdNameMap.put(TempConstant.回购交易, 
        		new TempCheckInfo(TempConstant.回购交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.买断式回购派息交易, 
        		new TempCheckInfo(TempConstant.买断式回购派息交易,"prodId","INVEST"));
        		**/
        finprodTypes = DictCodeListExt1.金融产品类型_债券质押式正回购.getValue()
        		+","+DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue()
        		+","+DictCodeListExt1.金融产品类型_债券买断式正回购.getValue()
        		+","+DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue();
        
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.回购交易);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("pTradeId");
        temp.setFinprodTypes(finprodTypes);
        prdNameMap.put(TempConstant.回购交易, temp);
        
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.买断式回购派息交易);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("assetId");
        temp.setFinprodTypes(finprodTypes);
        prdNameMap.put(TempConstant.买断式回购派息交易, temp);
        
        /**modify by wujie on 20181229 end**/
        
        prdNameMap.put(TempConstant.场外期权交易, 
        		new TempCheckInfo(TempConstant.场外期权交易,"prodId","INVEST"));
        
        prdNameMap.put(TempConstant.逾期交易, 
        		new TempCheckInfo(TempConstant.逾期交易,"prodId","INVEST"));
    	
        prdNameMap.put(TempConstant.债券买卖交易, 
        		new TempCheckInfo(TempConstant.债券买卖交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.债券存续期交易, 
        		new TempCheckInfo(TempConstant.债券存续期交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.债券应收款项转让交易, 
        		new TempCheckInfo(TempConstant.债券应收款项转让交易,"prodId","INVEST"));

        
        finprodTypes = DictCodeListExt1.金融产品类型_存款.getValue();
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.存款类资产存款交易);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("assetId");
        temp.setFinprodTypes(finprodTypes);
        prdNameMap.put(TempConstant.存款类资产存款交易, temp);
        
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.存款类资产存款存续期表);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("assetId");
        temp.setFinprodTypes(finprodTypes);
        prdNameMap.put(TempConstant.存款类资产存款存续期表, temp);
        
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.存款类资产应收款项转让交易);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("assetId");
        temp.setFinprodTypes(finprodTypes);
        prdNameMap.put(TempConstant.存款类资产应收款项转让交易, temp);
        
    	
        prdNameMap.put(TempConstant.申购申请交易, 
        		new TempCheckInfo(TempConstant.申购申请交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.申购确认交易, 
        		new TempCheckInfo(TempConstant.申购确认交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.货币基金赎回确认交易, 
        		new TempCheckInfo(TempConstant.货币基金赎回确认交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.净值型基金赎回确认交易, 
        		new TempCheckInfo(TempConstant.净值型基金赎回确认交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.赎回到账交易, 
        		new TempCheckInfo(TempConstant.赎回到账交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.现金分红交易, 
        		new TempCheckInfo(TempConstant.现金分红交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.红利再投交易, 
        		new TempCheckInfo(TempConstant.红利再投交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.基金转换交易, 
        		new TempCheckInfo(TempConstant.基金转换交易,"prodId","INVEST"));
    	
        prdNameMap.put(TempConstant.计划类资产利率型买卖交易表, 
        		new TempCheckInfo(TempConstant.计划类资产利率型买卖交易表,"prodId","INVEST"));
        prdNameMap.put(TempConstant.计划类资产利率型存续期交易表, 
        		new TempCheckInfo(TempConstant.计划类资产利率型存续期交易表,"prodId","INVEST"));
        prdNameMap.put(TempConstant.计划类资产利率型应收款项转让交易表, 
        		new TempCheckInfo(TempConstant.计划类资产利率型应收款项转让交易表,"prodId","INVEST"));
    	
        prdNameMap.put(TempConstant.增值税交易, 
        		new TempCheckInfo(TempConstant.增值税交易,"prodId","INVEST"));
    	
        prdNameMap.put(TempConstant.活期存款收息交易, 
        		new TempCheckInfo(TempConstant.活期存款收息交易,"prodId","INVEST"));
        prdNameMap.put(TempConstant.活期存款转仓交易, 
        		new TempCheckInfo(TempConstant.活期存款转仓交易,"prodId","INVEST"));
        
        
        /**add by wujie on 20181229 start**/
        
        finprodTypes = DictCodeListExt1.金融产品类型_债券借贷融入.getValue()
        		+","+ DictCodeListExt1.金融产品类型_债券借贷融出.getValue();
        temp = new TempCheckInfo();
        temp.setDtoEname(TempConstant.债券借贷交易);
        temp.setColprdName("prodId");
        temp.setInvestType("INVEST");
        temp.setAssetId("finprodId");
        temp.setFinprodTypes(finprodTypes);
        prdNameMap.put(TempConstant.债券借贷交易, temp);
        
        
        prdNameMap.put(TempConstant.债券借贷标的券存续期交易, 
        		new TempCheckInfo(TempConstant.债券借贷标的券存续期交易,"prodId","INVEST"));
        
        prdNameMap.put(TempConstant.债券借贷标的券卖出与购回, 
        		new TempCheckInfo(TempConstant.债券借贷标的券卖出与购回,"prodId","INVEST"));
        
        
        /**add by wujie on 20181229 end**/
            
        return prdNameMap;
    }
}
