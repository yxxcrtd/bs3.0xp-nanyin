package com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dao.QuotaImpMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto.QuotaCreditConfInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto.QuotaImpBaseDto;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto.QuotaImpDto;
import com.joyintech.tams.common.base.dao.CreditConfInfoMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.CreditTagInfoMapper;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.common.base.entity.RepAccessList;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

@Service("backQuotaImpService")
public class QuotaImpService extends BaseService<QuotaImpDto> {
	
	@Autowired
	private IPlatformDate iPlatformDate;
	
    @Autowired
    private QuotaImpMapper quotaImpMapper;
    
    @Autowired
    private CreditTagInfoMapper creditTagInfoMapper;
    
    @Autowired
    private CreditConfInfoMapper creditConfInfoMapper;
    
    @Autowired
    private CreditLinesMapper creditLinesMapper;
    
    
    /**
     * 页面查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<QuotaImpDto> selectAll(int page, int length, Map<String, Object> param) throws Exception {
    	
        Page<QuotaImpDto> pag = super.queryForPage(page, length, () -> quotaImpMapper.queryForPage(param));
        List<QuotaImpDto> list = pag.getRoot();
        pag.setRoot(list);
        return pag;
    }
	
    /**
     * 同业机构额度导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     */
    public List<ExcelValidDto> importTYData(List<List<String>> dataList,List<List<String>> tempList,String seqId,String impType) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("主体代码");
            headList.add("主体名称");
            headList.add("失效日");
            
            Map<String,String> limitTypeDictMap = SysDatadictUtil.getSysDictDataMap(DictCode.额度分类);
            //根据额度分类的数据字典生成的表头set，用于判断excel中的动态表头是否存在额度分类的数据字典中
            Set<String> headResult = new HashSet<String>();
            for(String key : limitTypeDictMap.keySet()){
            	String value = limitTypeDictMap.get(key);
            	headList.add(value);
            	headResult.add(value);
            }
            
            List<ExcelValidDto> tempCheckResult = checkTYExcelTemp(tempList, headList,headResult);
            
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                //标签管理 
                List<CreditTagInfo> tagEntities = new ArrayList<CreditTagInfo>();
                //额度分项集合 
                List<QuotaCreditConfInfoDto> configEntities = new ArrayList<QuotaCreditConfInfoDto>();
            	//同业机构额度导入 额度实体类 （一行数据，一个实体类）
                List<QuotaImpBaseDto> entities = new ArrayList<QuotaImpBaseDto>(dataList.size()-1);

                Map <String,String> box= new HashMap<String, String>();
                List<ExcelValidDto> checkResult = convertToTYEntity(tempList.get(0),dataList, tagEntities,configEntities,entities,box);
                if(checkResult.isEmpty()) {
                	insertCredit(tagEntities,configEntities,entities,seqId,impType);
                }
                return checkResult;
            }
        }
        return null;
    }
    
    
    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为QuotaImpTYDto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @param box
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToTYEntity(List<String> headList,List<List<String>> dataList,
    											List<CreditTagInfo> tagEntities,List<QuotaCreditConfInfoDto> configEntities,
                                                List<QuotaImpBaseDto> entities, Map<String, String> box) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        
        Map<String,Boolean> existMap = new HashMap<String,Boolean>();
    
        
		//针对动态表头转化
        Set<String> headSet = new HashSet<String>();
        
        Set<String> configSet = new HashSet<String>();
        
        List<String> confList = dataList.get(0);
        
        Map<String,List<QuotaCreditConfInfoDto>> confMap = new HashMap<String,List<QuotaCreditConfInfoDto>>();
        
        Map<String,List<QuotaImpBaseDto>> addBaseDtoMap = new HashMap<String,List<QuotaImpBaseDto>>();

        Map<String,List<QuotaImpBaseDto>> entityMap = new HashMap<String,List<QuotaImpBaseDto>>();
     	// 分项验证消息
        List<String> configMessage = new ArrayList<String>();
        for(int i = 3 ; i < confList.size() ; i++){
        	//当前分项对应的表头内容
    		String headName = headList.get(i);
    		//当前期限
    		String term = confList.get(i);
        	
    		//---------------------设置标签内容---------------------
        	//当set集合不存在时加入set集合中，同时在标签集合中加入
        	if(!headSet.contains(headList.get(i))){
        		CreditTagInfo tagInfo = new CreditTagInfo();
        		tagInfo.setCreditTagName(headName);
        		tagEntities.add(tagInfo);
        		headSet.add(headName);
        	}
            
           //---------------------设置分项内容---------------------
            QuotaCreditConfInfoDto confInfoDto = new QuotaCreditConfInfoDto();
    		//设置excel的表头内容
    		
    		confInfoDto.setCreditTypeName(headName);
    		//设置excel的指针
    		confInfoDto.setExcelPointer(i);
    		//TODO 设置期限
    	    String termError = checkTerm(term,confInfoDto);
            if (StringUtil.isNotEmpty(termError)) {
            	configMessage.add(termError);
            };
            
            //设置key
            String configKey = headName + "|" + term;
            confInfoDto.setConfigKey(configKey);

        	configEntities.add(confInfoDto);
        	
    		if(confMap.containsKey(headName)){
    			confMap.get(headName).add(confInfoDto);
    		}else {
    			List<QuotaCreditConfInfoDto> tempList = new ArrayList<QuotaCreditConfInfoDto>();
    			tempList.add(confInfoDto);
    			confMap.put(headName, tempList);
    		}
    		
    		if(configSet.contains(configKey)){
    			configMessage.add("额度、期限出现重复数据");
    		}else {
    			configSet.add(configKey);
    		}
        	
        }
        
		//对期限的校验
    	if (CollectionUtils.isNotEmpty(configMessage)) {
            checkResult.add(new ExcelValidDto("期限", String.valueOf(2),
                    StringUtil.joinString(configMessage, GlobalConstant.COMMA)));
    	}
        
        for(int i = 1; i < dataList.size(); i++ ){
            //数据内容验证消息
            List<String> message = new ArrayList<String>();
        	//获取基本主体信息
        	List<String> list = dataList.get(i);
        	String customerId = null;
        	Date mdate = null;
        	
        	List<String> customerIds = quotaImpMapper.queryCustomerIdByCode(list.get(0));
            String customerIdError = checkCustomerId(list.get(0),customerIds);
            
            if (StringUtil.isNotEmpty(customerIdError)) {
                message.add(customerIdError);
            }else {
            	customerId = customerIds.get(0);
            };
            //主体名称校验
            String customerNameError = checkcustomerName(list.get(1));
            if (StringUtil.isNotEmpty(customerNameError)) {
                message.add(customerNameError);
            };
        	//失效日校验
            String mdateError = checkMdate(list.get(2));
            if (StringUtil.isNotEmpty(mdateError)) {
                message.add(mdateError);
            };
            
            mdate = DateUtil.parse(list.get(2));
            Map<String,List<QuotaImpBaseDto>> dataMap = new HashMap<String,List<QuotaImpBaseDto>>();
            
            List<QuotaImpBaseDto> rowList = new ArrayList<QuotaImpBaseDto>();
            for(int j = 3 ; j < list.size(); j++){
        		//当前分项对应的表头内容
        		String headName = headList.get(j);
        		//当前期限
        		String term = confList.get(j);
    			
        		//主体代码校验
        		QuotaImpBaseDto entity = new QuotaImpBaseDto();
        		entity.setCustomerId(customerId);
        		entity.setMdate(mdate);
        		//设置额度
        		if(StringUtils.isNotBlank(list.get(j))){
        			entity.setCreditAmt(new BigDecimal(list.get(j)));
        		}else {
        			entity.setCreditAmt(BigDecimal.ZERO);
        		}
        		
        		//设置所在额度的期限,转化后的期限，保持同一个单位  TODO 修改
        		entity.setSortTerm(convertSortTerm(term));
        		//设置所在额度名称
        		entity.setCreditTypeName(headName);
        		//授信类型
        		entity.setCreditType("04");
        		// 额度名称+期限
        		String configKey = headName + "|" + term;
        		entity.setConfigKey(configKey);
        		
        		if(dataMap.containsKey(configKey)){
        			dataMap.get(configKey).add(entity);
        		}else {
        			List<QuotaImpBaseDto> tempList = new ArrayList<QuotaImpBaseDto>();
        			tempList.add(entity);
        			dataMap.put(configKey, tempList);
        		}
        		
        		rowList.add(entity);
    		}
            

        	
        	//唯一性校验
            if(existMap.containsKey(customerId)){
            	message.add("出现重复机构信息！");
            }
        	
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(i), String.valueOf(i+2),
                    StringUtil.joinString(message, GlobalConstant.COMMA)));
            } else {
            	existMap.put(customerId, true);
                entities.addAll(rowList);
                
                //设置计算叠加额度的数据结构
                for(QuotaImpBaseDto addBaseDto : rowList){
                	// 主体Id+额度名称
                	String addKey = customerId + "|" + addBaseDto.getCreditTypeName();
                    if(addBaseDtoMap.containsKey(addKey)){
                    	addBaseDtoMap.get(addKey).add(addBaseDto);
                    }else {
                    	List<QuotaImpBaseDto> tempList = new ArrayList<QuotaImpBaseDto>();
                    	tempList.add(addBaseDto);
                    	addBaseDtoMap.put(addKey, tempList);
                    }	
                }
            }
        }    
        
        //当校验通过时计算
        if(checkResult.size() == 0){
            //根据对应关系自动生成 额外的额度信息内容，要在设置叠加额度之前计算
        	autoAddQuotalmp(entityMap,tagEntities,configEntities,entities);
            
        	//计算叠加额度
        	for(String key : addBaseDtoMap.keySet()){
        		
        		List<QuotaImpBaseDto> calAddAmtList = addBaseDtoMap.get(key);
        		for(QuotaImpBaseDto dto : calAddAmtList){
        			BigDecimal addAmt = BigDecimal.ZERO;
        			for(QuotaImpBaseDto calto : calAddAmtList){
            			//这个判断用于控制excel导入的期限可能为空，以及避免compareTo 空指针
            			if(dto.getSortTerm().compareTo(calto.getSortTerm())<=0){
            				addAmt = addAmt.add(calto.getCreditAmt());
            			}
        			}
        			dto.setCreditAmtAdd(addAmt);
        		}
        	}
        }
        
        return checkResult;
    }
    
    
    public void addImpAmt(List<QuotaImpBaseDto> quotaDtoList,Map<String,List<QuotaCreditConfInfoDto>> map,
    		List<String> data){
    	BigDecimal addAmt = BigDecimal.ZERO;
    	for(QuotaImpBaseDto quotaDto : quotaDtoList){
    		BigDecimal quotaSortTerm = quotaDto.getSortTerm();
    		List<QuotaCreditConfInfoDto> confDtoList = map.get(quotaDto.getCreditTypeName());
    		//当前额度所在的期限是空时
    		if(quotaSortTerm == null){
    			//叠加额度是当前授信额度
    			quotaDto.setCreditAmtAdd(quotaDto.getCreditAmt());
    			continue;
    		}
    		//当前额度所在的期限不为空时
    		for(QuotaCreditConfInfoDto  dto : confDtoList){
    			Integer j = dto.getExcelPointer();
    			BigDecimal sortTerm = dto.getSortTerm();
    			//这个判断用于控制excel导入的期限可能为空，以及避免compareTo 空指针
    			if(sortTerm == null){
    				addAmt = addAmt.add(new BigDecimal(data.get(j)));
    				continue;
    			}
    			if(quotaSortTerm.compareTo(sortTerm)<=0){
    				addAmt = addAmt.add(new BigDecimal(data.get(j)));
    			}
    		}
    		quotaDto.setCreditAmtAdd(addAmt);
    	}
    	
    }
    
    /**
     * 企业额度导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     */
    public List<ExcelValidDto> importQYData(List<List<String>> dataList,List<List<String>> tempList,String seqId,String impType) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("主体代码");
            headList.add("主体名称");
            headList.add("失效日");
            headList.add("额度名称");
            headList.add("核定额度");
            headList.add("期限");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
            	//标签管理 
                List<CreditTagInfo> tagEntities = new ArrayList<CreditTagInfo>();
                //额度分项集合 
                List<QuotaCreditConfInfoDto> configEntities = new ArrayList<QuotaCreditConfInfoDto>();
                
                List<QuotaImpBaseDto> entities = new ArrayList<>(dataList.size());
                
                Map <String,String> box= new HashMap<String, String>();
                List<ExcelValidDto> checkResult = convertToQYEntity(dataList, tagEntities,configEntities,entities,box);
                if(checkResult.isEmpty()) {
                	insertCredit(tagEntities,configEntities,entities,seqId,impType);
                }
                return checkResult;
            }
        }
        return null;
    }
    
    
    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为QuotaImpTYDto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @param box
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToQYEntity(List<List<String>> dataList,
    		                                    List<CreditTagInfo> tagEntities,List<QuotaCreditConfInfoDto> configEntities,
                                                List<QuotaImpBaseDto> entities, Map<String, String> box) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        //标签set集合
        Set<String> tagSet = new HashSet<String>();
        
        Map<String,QuotaCreditConfInfoDto> confMap = new HashMap<String,QuotaCreditConfInfoDto>();
        
        Map<String,Boolean> existMap = new HashMap<String,Boolean>();
        
        Map<String,String> autoHeadMap = new HashMap<String,String>();
        autoHeadMap.put("普通债务额度","普通债务质押限额");
        autoHeadMap.put("次级债务额度","次级债务质押限额");
        autoHeadMap.put("企业信用债投资额度","信用债质押额度");
        
        
        //叠加额度计算Map
        Map<String,List<QuotaImpBaseDto>> addBaseDtoMap = new HashMap<String,List<QuotaImpBaseDto>>();
        
        Map<String,List<QuotaImpBaseDto>> entityMap = new HashMap<String,List<QuotaImpBaseDto>>();
        
     	// 验证消息
        List<String> message = new ArrayList<String>();
        
    	//设置授信额度表
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            QuotaImpBaseDto entity = new QuotaImpBaseDto();
            //主体代码校验
            String customerId = null;
            List<String> customerIds = quotaImpMapper.queryCustomerIdByCode(list.get(0));
            String customerIdError = checkCustomerId(list.get(0),customerIds);
            if (StringUtil.isNotEmpty(customerIdError)) {
                message.add(customerIdError);
            }else {
            	customerId = customerIds.get(0);
            };
            
            entity.setCustomerId(customerId);
            //主体名称校验
            String customerNameError = checkcustomerName(list.get(1));
            if (StringUtil.isNotEmpty(customerNameError)) {
                message.add(customerNameError);
            };
        	//失效日校验
            String mdateError = checkMdate(list.get(2));
            if (StringUtil.isNotEmpty(mdateError)) {
                message.add(mdateError);
            };
            
            Date mdate = DateUtil.parse(list.get(2));
            entity.setMdate(mdate);
            
        	//额度名称校验
            String creditTypeName = list.get(3);
            String creditTypeNameError = checkCreditTypeName(creditTypeName);
            if (StringUtil.isNotEmpty(creditTypeNameError)) {
                message.add(creditTypeNameError);
            }else {
            	entity.setCreditTypeName(creditTypeName);
            	if(!tagSet.contains(creditTypeName)){
                	//当set集合不存在时加入set集合中，同时在标签集合中加入
            		CreditTagInfo tagInfo = new CreditTagInfo();
            		tagInfo.setCreditTagName(creditTypeName);
            		tagInfo.setOrgType("02");
            		tagEntities.add(tagInfo);	
            		tagSet.add(creditTypeName);
            		
            		//针对    普通债务额度、次级债务额度和企业信用债投资额度  分别自动生成 ：普通债务质押限额、次级债务质押限额、信用债质押额度
            		if(autoHeadMap.containsKey(creditTypeName)){
            			CreditTagInfo autoTagInfo = new CreditTagInfo();
            			autoTagInfo.setOrgType("02");
            			autoTagInfo.setCreditTagName(autoHeadMap.get(creditTypeName));
                		tagEntities.add(autoTagInfo);		
            		}
            	}	
            }
            
            String term = list.get(4);
            String configKey = creditTypeName +"|"+ term;
            if(!confMap.containsKey(configKey)){
                QuotaCreditConfInfoDto confInfoDto = new QuotaCreditConfInfoDto();
        		//设置excel的表头内容
        		confInfoDto.setCreditTypeName(creditTypeName);
        		//设置excel的指针  ABS导入无需设置
        		//confInfoDto.setExcelPointer(j);
        		//TODO 设置期限
        	    String termError = checkTerm(term,confInfoDto);
                if (StringUtil.isNotEmpty(termError)) {
                    message.add(termError);
                };
        	    configEntities.add(confInfoDto);
        	    confMap.put(configKey, confInfoDto);
        	    
        		//针对    普通债务额度、次级债务额度和企业信用债投资额度  分别自动生成 ：普通债务质押限额、次级债务质押限额、信用债质押额度
        		if(autoHeadMap.containsKey(creditTypeName)){
        			QuotaCreditConfInfoDto autoConfInfoDto = new QuotaCreditConfInfoDto();
        			//对对象进行拷贝管理
        			BeanUtil.copyPropertiesIgnoreNull(autoConfInfoDto, confInfoDto);
        			autoConfInfoDto.setCreditTypeName(autoHeadMap.get(confInfoDto.getCreditTypeName()));
                    configEntities.add(autoConfInfoDto);			
        		}
            }
    		//校验额度
            //主体名称校验
            String amt = list.get(5);
            String amtError = checkAmt(amt);
            if (StringUtil.isNotEmpty(amtError)) {
                message.add(amtError);
            }else {
            	entity.setCreditAmt(new BigDecimal(amt));
            }
    		//设置所在额度的期限
    		entity.setSortTerm(confMap.get(configKey).getSortTerm());
    		//授信类型
    		entity.setCreditType("04");
    		
    		//设置关联索引
    		entity.setConfigKey(configKey);
        		
            //联动唯一性校验
            StringBuilder sb = new StringBuilder();
            sb.append(customerId).append("|").append(list.get(3)).append("|").append(list.get(5));
            if(existMap.containsKey(sb)){
            	message.add(list.get(1)+"、"+list.get(3)+"、"+list.get(5)+"出现重复数据！");
            }
            
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(1), String.valueOf(i+1),
                    StringUtil.joinString(message, GlobalConstant.COMMA)));
            } else {
            	String addKey = customerId + "|" + creditTypeName;	
            	existMap.put(sb.toString(), true);
                entities.add(entity);
        		
                if(addBaseDtoMap.containsKey(addKey)){
                	addBaseDtoMap.get(addKey).add(entity);
                }else {
                	List<QuotaImpBaseDto> tempList = new ArrayList<QuotaImpBaseDto>();
                	tempList.add(entity);
                	addBaseDtoMap.put(addKey, tempList);
                }
                
                if(entityMap.containsKey(configKey)){
                	entityMap.get(configKey).add(entity);
                }else {
                	List<QuotaImpBaseDto> tempList = new ArrayList<QuotaImpBaseDto>();
                	tempList.add(entity);
                	entityMap.put(configKey, tempList);
                }
 
            }
        }
        
        //叠加额度计算，只有当校验全部通过时才计算  add 自动对应关系计算
        if(checkResult.size() == 0){
        	
        	autoAddQuotalmp(entityMap,tagEntities,configEntities,entities);
        	
        	
        	//计算叠加额度
        	for(String key : addBaseDtoMap.keySet()){
/*        		if(addBaseDtoMap.get(key).size() == 1){
        			QuotaImpBaseDto baseDto = addBaseDtoMap.get(key).get(0);
        			baseDto.setCreditAmtAdd(baseDto.getCreditAmt());
        			continue;
        		}*/
        		List<QuotaImpBaseDto> calAddAmtList = addBaseDtoMap.get(key);
        		for(QuotaImpBaseDto dto : calAddAmtList){
        			BigDecimal addAmt = BigDecimal.ZERO;
/*      				if(dto.getSortTerm()== null){
      					addAmt = addAmt.add(dto.getCreditAmt());
      					continue;
    				}*/
        			for(QuotaImpBaseDto calto : calAddAmtList){
            			//这个判断用于控制excel导入的期限可能为空，以及避免compareTo 空指针
/*            			if(calto.getSortTerm() == null){
            				addAmt = addAmt.add(calto.getCreditAmt());
            				continue;
            			}*/
            			if(dto.getSortTerm().compareTo(calto.getSortTerm())<=0){
            				addAmt = addAmt.add(calto.getCreditAmt());
            			}
        			}
        			dto.setCreditAmtAdd(addAmt);
        		}
        	}
        	
        }
        
        
        return checkResult;
    }
    
    /**
     * 项目额度导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     */
    public List<ExcelValidDto> importXMData(List<List<String>> dataList,List<List<String>> tempList,String seqId,String impType) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("回购对手类型");
            headList.add("客户代码");
            headList.add("主体名称");
            headList.add("回购期限");
            headList.add("限额");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<RepAccessList> entities = new ArrayList<>(dataList.size());
                Map <String,String> box= new HashMap<String, String>();
                List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();//convertToEntity(dataList, entities,box);
                if(checkResult.isEmpty()) {
                	
                	
                }
                return checkResult;
            }
        }
        return null;
    }
    
    
    /**
     * ABS额度导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     */
    public List<ExcelValidDto> importABSData(List<List<String>> dataList,List<List<String>> tempList,String seqId,String impType) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("主体代码");
            headList.add("主体名称");
            headList.add("失效日");
            headList.add("ABS投资限额I类");
            headList.add("");
            headList.add("ABS投资限额II类");
            headList.add("");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
            	//标签管理 
                List<CreditTagInfo> tagEntities = new ArrayList<CreditTagInfo>();
                //额度分项集合 
                List<QuotaCreditConfInfoDto> configEntities = new ArrayList<QuotaCreditConfInfoDto>();
                List<QuotaImpBaseDto> entities = new ArrayList<>(dataList.size()-1);
                Map <String,String> box= new HashMap<String, String>();
                List<ExcelValidDto> checkResult = convertToABSEntity(tempList.get(0),dataList, tagEntities,configEntities,entities,box);
                if(checkResult.isEmpty()) {
                	insertCredit(tagEntities,configEntities,entities,seqId,impType);
                }
                return checkResult;
            }
        }
        return null;
    }
    
    
    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为QuotaImpTYDto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @param box
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToABSEntity(List<String> headList,List<List<String>> dataList,
    		                                    List<CreditTagInfo> tagEntities,List<QuotaCreditConfInfoDto> configEntities,
                                                List<QuotaImpBaseDto> entities, Map<String, String> box) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String,Boolean> existMap = new HashMap<String,Boolean>();
        for(int i = 3 ; i< headList.size();i++){
        	if(StringUtils.isBlank(headList.get(i))){
        		continue;
        	}
        	//当set集合不存在时加入set集合中，同时在标签集合中加入
    		CreditTagInfo tagInfo = new CreditTagInfo();
    		tagInfo.setCreditTagName(headList.get(i));
    		tagEntities.add(tagInfo);	
        }
     	// 验证消息
        List<String> message = new ArrayList<String>();
        
    	//设置授信额度表
        for (int i = 1; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            //主体代码校验
            String customerId = null;
            List<String> customerIds = quotaImpMapper.queryCustomerIdByCode(list.get(0));
            String customerIdError = checkCustomerId(list.get(0),customerIds);
            if (StringUtil.isNotEmpty(customerIdError)) {
                message.add(customerIdError);
            }else {
            	customerId = customerIds.get(0);
            };
            //主体名称校验
            String customerNameError = checkcustomerName(list.get(1));
            if (StringUtil.isNotEmpty(customerNameError)) {
                message.add(customerNameError);
            };
        	//失效日校验
            String mdateError = checkMdate(list.get(2));
            if (StringUtil.isNotEmpty(mdateError)) {
                message.add(mdateError);
            };
            
            Date mdate = DateUtil.parse(list.get(2));
            
            
        	//额度分项 + 额度信息表
            //构建Map集合,数据结构（名称+限额：当前分项实体）
            Map<String,QuotaCreditConfInfoDto> confMap = new HashMap<String,QuotaCreditConfInfoDto>();
        	List<QuotaImpBaseDto> rowList = new ArrayList<QuotaImpBaseDto>();
        	
        	//隔一位迭代
        	for(int j = 3 ; j < list.size(); j = j+2){
        		//当前额度名称
        	    String creditTypeName = headList.get(j);
        		if(StringUtils.isNotBlank(list.get(j)) && StringUtils.isNotBlank(list.get(j+1))){
            	    String term = list.get(j);
            	    String configKey = creditTypeName +"|"+ term;
            	    //组件分项表的model
            	    if(!confMap.containsKey(configKey)){
            	    	QuotaCreditConfInfoDto confInfoDto = new QuotaCreditConfInfoDto();
                		//设置excel的表头内容
                		confInfoDto.setCreditTypeName(headList.get(j));
                		//设置excel的指针  ABS导入无需设置
                		//confInfoDto.setExcelPointer(j);
                		//TODO 设置期限
                	    String termError = checkTerm(term,confInfoDto);
                        if (StringUtil.isNotEmpty(termError)) {
                        	message.add(termError);
                        };
                        confInfoDto.setConfigKey(configKey);
                		configEntities.add(confInfoDto);
                		confMap.put(configKey, confInfoDto);
            	    }
            	    String amt = list.get(j+1);
            	    
            	    QuotaImpBaseDto entity = new QuotaImpBaseDto();
            	    
            		entity.setCustomerId(customerId);
            		
            		entity.setMdate(mdate);
            		//校验额度
                    //主体名称校验
                    String amtError = checkAmt(list.get(j+1));
                    if (StringUtil.isNotEmpty(amtError)) {
                        message.add(amtError);
                    };
            		entity.setCreditAmt(new BigDecimal(amt));
            		//设置所在额度的期限
            		entity.setSortTerm(confMap.get(configKey).getSortTerm());
            		//设置所在额度名称
            		entity.setCreditTypeName(headList.get(j));
            		//授信类型
            		entity.setCreditType("04");
            		//设置key
            		entity.setConfigKey(configKey);
            
            		// ABS模板格式，导致 叠加额度 = 授信额度
            		entity.setCreditAmtAdd(new BigDecimal(amt));
        
            		rowList.add(entity);
        		}
            		
        	}
        		
        	//唯一性校验
            if(existMap.containsKey(customerId)){
            	message.add("出现重复机构信息！");
            }
            
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(1), String.valueOf(i+2),
                    StringUtil.joinString(message, GlobalConstant.COMMA)));
            } else {
            	existMap.put(customerId, true);
                entities.addAll(rowList);
            }
        }

        
        return checkResult;
    }
    
    
    public static List<ExcelValidDto> checkTYExcelTemp(List<List<String>> tempDataList, List<String> headList,Set<String> typeDictSet) {
        List<ExcelValidDto> tempCheckResult = new ArrayList<ExcelValidDto>();
        String errorMsg = "导入模板不一致，请重新选择文件！";
        ExcelValidDto excelValidDto = new ExcelValidDto();
        excelValidDto.setErrorInfo(errorMsg);
      //  excelValidDto.setRowNum("0");
        tempCheckResult.add(excelValidDto);


        if (StringUtil.isEmptyList(tempDataList)) {
            // Excel没有有效数据
            return tempCheckResult;
        }
        
        List<String> excelHeadList = tempDataList.get(0);
        
        Set<String> excelHeadSet = new HashSet<String>();
        //前三个表头顺序内容一致性
        for (int i = 0; i < headList.size(); i++ ) {
            if (i < 3 && !excelHeadList.get(i).contains(headList.get(i))) {
                // Excel中列不一致
                return tempCheckResult;
            }
        }
        
        //后面动态表头
        for(int i = 3; i < excelHeadList.size();i++){
        	excelHeadSet.add(excelHeadList.get(i));
        }
        
        Set<String> result = new HashSet<String>();
        result.clear();
        result.addAll(typeDictSet);
        result.retainAll(excelHeadSet);
        //交集相同才满足导入条件
        if(result.size() != excelHeadSet.size()){
        	tempCheckResult.get(0).setErrorInfo("额度分项不存在");
        	return tempCheckResult;
        }

        
        return null;
    }
    
    
    public String checkTerm(String term,QuotaCreditConfInfoDto confInfoDto){
    	
    	if(StringUtils.isBlank(term)){
    		return "期限不可为空";
    	}
    	Map<String,String> unitmap = new HashMap<String,String>();
    	unitmap.put("年","Y");
    	unitmap.put("月","M");
    	unitmap.put("周","W");
    	unitmap.put("日","D");
    	
    	Set<String> set = new HashSet<String>();
    	set.add("25");
    	set.add("5");
    	set.add("75");
    	set.add("");
    	String termUnit= term.substring(term.length()-1,term.length());
    	
    	if(!unitmap.containsKey(termUnit)){
    		return "期限单位不正确";
    	}
    	//设置期限单位
    	confInfoDto.setTermUnit(unitmap.get(termUnit));
    	
    	String termNum = term.substring(0,term.length()-1).trim();
    	
    	String point = StringUtils.substringAfter(".",termNum);
    	
    	if((!Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$").matcher(termNum).matches())
    			|| !(set.contains(point)&&"年".equals(termUnit))){
    		return "期限格式不正确";
    	}
    	
    	//设置期限
    	confInfoDto.setTerm(new BigDecimal(termNum));
    	
    	//设置计算时的期限
    	confInfoDto.setSortTerm(convertSortTerm(term));
    		
    	return null;
    	
    }
    
    
    public BigDecimal convertSortTerm(String term){
    	
    	BigDecimal sortTerm = null;
    	
    	String termUnit= term.substring(term.length()-1,term.length());
    	
    	String termNum = term.substring(0,term.length()-1).trim();
    	
    	//对于期限进行转换
    	if("日".equals(termUnit)){
    		sortTerm = new BigDecimal(termNum);
    	}else if("周".equals(termUnit)){
    		sortTerm = new BigDecimal(termNum).multiply(new BigDecimal(7));
    	}else if("月".equals(termUnit)){
    		sortTerm = new BigDecimal(termNum).multiply(new BigDecimal(30));
    	}else if("年".equals(termUnit)){
    		sortTerm = new BigDecimal(termNum).multiply(new BigDecimal(360));
    	}
    	
    	return sortTerm;
    }
    
    public String checkCustomerId(String insitituteCode,List<String> customerIds){
    	
    	if(StringUtil.isEmpty(insitituteCode)){
    		return "主体名称为空";
    	}
    	
    	if(!Pattern.compile("^[0-9A-Z]+$").matcher(insitituteCode).matches()){
    		return "主体代码非数字或字母";
    	}
    	if(insitituteCode.contains(" ")){
    		return "主体代码含有空格";
    	}
    	
    	if(customerIds.size() == 0){
    		return "主体未定义";
    	}
    	if(customerIds.size() > 1){
    		return "同一组织机构代码查询出多个市场机构";
    	}
    	
    	return null;
    }
    
    public String checkcustomerName(String customerName){
    	
    	if(StringUtil.isEmpty(customerName)){
    		return "主体名称为空";
    	}
    	if(customerName.contains(" ")){
    		return "主体名称含有空格";
    	}
    	return null;
    }
    
    public String checkMdate(String mdate){
    	if(StringUtil.isEmpty(mdate)){
    		return "失效日为空";
    	}
    	
    	if(mdate.contains(" ")){
    		return "失效日含有空格";
    	}

    	if(!DateUtil.isDateStr(mdate,DateUtil.DEFAULT_DATE_FORMAT_EN)){
    		return "失效日期格式有问题";
    	}
    	
    	return null;
    }
    
    public String checkNotNullAmt(String amt){
    	
    	if(StringUtil.isEmpty(amt)){
    		return "限额不能为空";
    	}
    	return checkAmt(amt);
    	
    }
    
    public String checkAmt(String amt){
    	if(!Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$").matcher(amt).matches()){
    		return "限额的格式不正确";
    	}
    	return null;
    	
    }
    
    public String checkCreditTypeName(String creditTypeName){
    	if(StringUtil.isEmpty(creditTypeName)){
    		return "额度名称为空";
    	}
    	
    	if(creditTypeName.contains(" ")){
    		return "额度名称含有空格";
    	}
    	Map<String,String> limitTypeDictMap = SysDatadictUtil.getSysDictDataMap(DictCode.额度分类);
    	if(!limitTypeDictMap.containsValue(creditTypeName)){
    		return "额度分项不存在";
    	}
    	
    	
    	return null;
    }
    
    public void insertCredit(List<CreditTagInfo> tagEntities,List<QuotaCreditConfInfoDto> configEntities,List<QuotaImpBaseDto> entities,String seqId,String impType){
    	//构建存储(额度名称 : tagId)的map集合，用于分项表的额度标签表使用
    	Map<String,String> tagIdMap = new HashMap<String,String>();
    	//构建存储(excel表头指针 : 分项Id)的map集合，用于授信额度表创建使用
    	Map<String,String> confIdMap = new HashMap<String,String>();
    	//额度标签管理
    	for(CreditTagInfo tagInfo : tagEntities){
    		//查询额度标签 ： 此时额度标签名称当excel表头的中文名
    		String creditTagId = quotaImpMapper.queryTagIdByCreditTypeName(tagInfo.getCreditTagName());
    		//如果没有额度标签，则新建额度标签
    		if(creditTagId != null){
    			tagIdMap.put(tagInfo.getCreditTagName(), creditTagId);
    		}else {
    			String tagUUid = IDGenerator.generate();
    			tagIdMap.put(tagInfo.getCreditTagName(),tagUUid);
    			tagInfo.setCreditTagId(tagUUid);
    			String creditType = SysDatadictUtil.getCodeFromDataDictByName(DictCode.额度分类,tagInfo.getCreditTagName());
    			tagInfo.setCreditType(creditType);
    		    //额度标签名称设置null，不保存额度标签名称
    			tagInfo.setCreditTagName(null);
    			creditTagInfoMapper.insert(tagInfo);
    			
    		}
    	}
    	//额度分项表管理
    	for(QuotaCreditConfInfoDto  confInfoDto : configEntities){
    		//查询分项表Id是否存在
    		String tagId = tagIdMap.get(confInfoDto.getCreditTypeName());
     		String configId = quotaImpMapper.queryConfIdByTypeAndTerm(tagId, confInfoDto.getTerm(), confInfoDto.getTermUnit());
    		String configKey = confInfoDto.getConfigKey();
     		if(configId != null){
     			confIdMap.put(configKey,configId);
     			
     		}else {
     			String confUUid = IDGenerator.generate();
     			confInfoDto.setCreditMstId(confUUid);
     			confInfoDto.setCreditTagId(tagId);
     			creditConfInfoMapper.insert(confInfoDto);
     			confIdMap.put(configKey,confUUid);
     		}
    	}
		//批次号设置问题
		String existSeqId = quotaImpMapper.queryPflshByImpType(impType);
		if("01".equals(impType)){
			seqId = getSeqId(existSeqId,"TY");
		}else if("02".equals(impType)){
			seqId = getSeqId(existSeqId,"QY");
		}else {
			seqId = getSeqId(existSeqId,"ABS");
		}
    	//授信额度管理
    	for(QuotaImpBaseDto quotaImpDto : entities){
    		String creditUUid = IDGenerator.generate();
    		quotaImpDto.setSeqno(creditUUid);
    		quotaImpDto.setVdate(iPlatformDate.getSysDate());
    		quotaImpDto.setPflsh(seqId);
    		quotaImpDto.setCreditFlag("Y");
    		quotaImpDto.setHapCreditAmt(BigDecimal.ZERO);
    		quotaImpDto.setCreditMstId(confIdMap.get(quotaImpDto.getConfigKey()));
    		creditLinesMapper.insert(quotaImpDto);
    	}
    	
    }
    
    public static String getSeqId(String seqId,String head){
    	
		Calendar cal = Calendar.getInstance();
		String time = DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT);
    	
		String seqNo = "";
		// 判断是否为空
		if (seqId != null) {
			seqNo = seqId.substring(seqId.length() -4,seqId.length());
			Integer count = 1;
			count = count + Integer.parseInt(seqNo);

			String stmp = count.toString();
			String sTmp0 = "";
			if (stmp.length() < 4) {
				int itmp = 4 - stmp.length();
				for (int i = 0; i < itmp; i++) {
					sTmp0 += "0";
				}
			}
			seqNo = head + time + sTmp0 + count.toString();
		} else {
			seqNo = head + time + "0001";
		}
		
		return seqNo;
    }
    
    
    public void autoAddQuotalmp(Map<String,List<QuotaImpBaseDto>> entityMap,
    		List<CreditTagInfo> tagEntities,List<QuotaCreditConfInfoDto> configEntities,
            List<QuotaImpBaseDto> entities){
    	
        Map<String,String> autoHeadMap = new HashMap<String,String>();
        autoHeadMap.put("普通债务额度","普通债务质押限额");
        autoHeadMap.put("次级债务额度","次级债务质押限额");
        autoHeadMap.put("企业信用债投资额度","信用债质押额度");
        
        autoHeadMap.put("普通债务质押限额","普通债务质押限额");
        autoHeadMap.put("次级债务质押限额","次级债务质押限额");
        autoHeadMap.put("信用债质押额度","信用债质押额度");
    	
    	
    	for(String key : entityMap.keySet()){
    		
    		String term = StringUtils.substringAfterLast("|",key);
    		
    		String typeName = StringUtils.substringBeforeLast("|", key);
    		
    		String autoTypeName = autoHeadMap.get(typeName);
    		
    		if(StringUtils.isEmpty(autoTypeName)){
    			continue;
    		}
    		
    		String autoKey = autoTypeName + "|" + term;
    		
    		if(!entityMap.containsKey(autoKey)){
    			//标签内容
        		CreditTagInfo tagInfo = new CreditTagInfo();
        		tagInfo.setCreditTagName(autoTypeName);
        		tagEntities.add(tagInfo);
    			
    			
    			//分项内容
    			QuotaCreditConfInfoDto autoConfInfoDto = new QuotaCreditConfInfoDto();
    			//对对象进行拷贝管理
    			autoConfInfoDto.setCreditTypeName(autoTypeName);
    			checkTerm(term,autoConfInfoDto);
                configEntities.add(autoConfInfoDto);
    			
    			
    			//生成额度信息
    			for(QuotaImpBaseDto dto : entityMap.get(key)){
    				QuotaImpBaseDto autoQuotaImpDto = new QuotaImpBaseDto();
        			//对对象进行拷贝管理
        			BeanUtil.copyPropertiesIgnoreNull(autoQuotaImpDto, dto);
        			//把 key 和name名称设置
        			autoQuotaImpDto.setCreditTypeName(autoHeadMap.get(autoTypeName));
        			autoQuotaImpDto.setConfigKey(autoKey);
        			entities.add(autoQuotaImpDto);	
    			}
    		}
    		
    	}
    }
}
