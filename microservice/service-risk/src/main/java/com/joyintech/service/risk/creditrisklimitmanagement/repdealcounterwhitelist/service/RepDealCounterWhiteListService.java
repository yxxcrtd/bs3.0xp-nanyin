package com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.dao.RepDealCounterWhiteListMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.dto.RepDealCounterWhiteListDto;
import com.joyintech.tams.common.base.dao.RepAccessListMapper;
import com.joyintech.tams.common.base.entity.RepAccessList;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

@Service("backRepDealCounterWhiteListService")
public class RepDealCounterWhiteListService  extends BaseService<RepDealCounterWhiteListDto> {
	
    @Autowired
    private RepDealCounterWhiteListMapper repDealCounterWhiteListMapper;
    
    @Autowired
    private RepAccessListMapper repAccessListMapper;
    
    
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
    public Page<RepDealCounterWhiteListDto> selectAll(int page, int length, Map<String, Object> param) throws Exception {
    	
        Page<RepDealCounterWhiteListDto> pag = super.queryForPage(page, length, () -> repDealCounterWhiteListMapper.queryForPage(param));
        List<RepDealCounterWhiteListDto> list = pag.getRoot();
        pag.setRoot(list);
        return pag;
    }
   
	
    /**
     * 导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     */
    public List<ExcelValidDto> importData(List<List<String>> dataList,List<List<String>> tempList,String seqId) {
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
                List<RepAccessList> entities = new ArrayList<RepAccessList>(dataList.size());
                Map <String,String> box= new HashMap<String, String>();
                List<ExcelValidDto> checkResult = convertToEntity(dataList, entities,box);
                if(checkResult.isEmpty()) {
                	//TODO 将历史数据中的有效数据置为无效 status = '02'
                	Map<String, Object> pageParam = new HashMap<String,Object>();
                	pageParam.put("updateUser", CommonUtil.getUid());
                	repDealCounterWhiteListMapper.ivalidHisDate(pageParam);
                	//将excel中数据（剔除维度一致的数据）插入到表中 
                	for(RepAccessList repAccessList : entities){
                		
                		repAccessList.setListId(IDGenerator.generate());
                		repAccessList.setEffDate(new Date());
                		repAccessList.setSeqId(seqId);
                		repAccessList.setStatus("01");
                		//保存数据
                		repAccessListMapper.insert(repAccessList);
                	}
                }
                return checkResult;
            }
        }
        return null;
    }
    
    
    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为RepDealCounterWhiteListDto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @param box
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,
                                                List<RepAccessList> entities, Map<String, String> box) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String,Boolean> existMap = new HashMap<String,Boolean>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            if (null == list || list.size() != 5) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i),
                    "文件内容为空或与模板表头不一致"));
                continue;
            }
            RepAccessList entity = new RepAccessList();
            // 验证消息
            List<String> message = new ArrayList<>();
            Map<String,String> listTypeDictMap = SysDatadictUtil.getSysDictDataMap(DictCode.回购对手类型);
            //回购对手类型校验
            String listTypeDictValue = "";
            for(String key : listTypeDictMap.keySet()){
            	if(listTypeDictMap.get(key).equals(list.get(0))){
            		listTypeDictValue = key;
            	}
            }
            String listTypeError = checkListType(list.get(0),listTypeDictValue);
            if (StringUtil.isNotEmpty(listTypeError)) {
                message.add(listTypeError);
            }else {
            	entity.setListType(listTypeDictValue);
            }
            //期限校验
            String termError = checkTerm(list.get(3));
            if (StringUtil.isNotEmpty(termError)) {
                message.add(termError);
            }else {
            	entity.setTerm(list.get(3));
            }   
            //限额校验
            String amountError = checkAmount(list.get(4));
            if (StringUtil.isNotEmpty(amountError)) {
                message.add(amountError);
            }else {
            	entity.setAmount(new BigDecimal(list.get(4)));
            }   
            //主体代码校验
            List<String> customerIds = repDealCounterWhiteListMapper.queryCustomerIdByCode(list.get(1));
            String customerId = null; 
            String customerIdError = checkCustomerId(list.get(0),customerIds);
            if (StringUtil.isNotEmpty(customerIdError)) {
                message.add(customerIdError);
            }else {
            	customerId = customerIds.get(0);
            };

            //联动唯一性校验
            StringBuilder sb = new StringBuilder();
            sb.append(listTypeDictValue).append("|").append(list.get(1)).append("|").append(list.get(3));
            if(existMap.containsKey(sb)){
            	message.add(list.get(0)+"、"+list.get(1)+"、"+list.get(3)+"出现重复数据！");
            }
            
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(entity.getCustomerId(), String.valueOf(i+1),
                    StringUtil.joinString(message, GlobalConstant.COMMA)));
            } else {
            	//保存已经存在的联动唯一性校验map
            	existMap.put(sb.toString(), true);
                entities.add(entity);
            }
            
        }
        return checkResult;
    }
    
    public String checkListType(String listType,String listTypeDictValue) {
        // 必须验证
        if (StringUtil.isEmpty(listType)) {
            return "回购对手类型不能为空";
        }
        if(StringUtil.isEmpty(listTypeDictValue)){
        	return "回购对手类型不在数据字典中";
        }
        
        return null;
    }
    
    public String checkTerm(String term){	
        // 必须验证
        if (StringUtil.isEmpty(term)) {
            return "期限不能为空";
        }
        if(!Pattern.compile("^[1-9]\\d*$").matcher(term).matches()){
        	return "期限必须为正整数";
        }
        
        return null;
    }
    
    public String checkAmount(String amount){
        // 必须验证
        if (StringUtil.isEmpty(amount)) {
            return "限额不能为空";
        }
        if(!Pattern.compile("[\\d]*[.]?[\\d]*").matcher(amount).matches()){
        	return "限额必须为数字类型";
        }
        return null;
    	
    }
    
    public String checkCustomerId(String insitituteCode,List<String> customerIds){
        // 必须验证
    	if(!Pattern.compile("/^[0-9A-Z]+$/").matcher(insitituteCode).matches()){
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
 
}
