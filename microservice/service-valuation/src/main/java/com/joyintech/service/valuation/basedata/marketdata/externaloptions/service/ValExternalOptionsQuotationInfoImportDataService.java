package com.joyintech.service.valuation.basedata.marketdata.externaloptions.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions001Dto;
import com.joyintech.tams.common.base.dao.MstValOpMapper;
import com.joyintech.tams.common.base.entity.MstValOp;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;

/**期权行情信息导入
 * @auther: 曹锦
 * @date: 2020 年 05 月 15
 * @description:
 */

@Service
public class ValExternalOptionsQuotationInfoImportDataService extends CommonExportService<ExternalOptions001Dto>  {


	/**
    * 每日债券行情 mapper
    */
   @Autowired
   private ExternalOptionsDao externalOptionsDao;

   @Autowired
   private MstValOpMapper mstValOpMapper;
    /**
     * 平台日组件
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    
    
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";




    /**
     * 导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *

     * @return 导入结果
     */
    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("合约标的");
            headList.add("交易编号");
            headList.add("期权价值");
            headList.add("估值日期");
            headList.add("公布日期");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<MstValOp> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult= convertToEntity(dataList, entities,userName,deptCode);
                if(checkResult.isEmpty()) {
	                for(MstValOp dto : entities){
	                	mstValOpMapper.insert(dto);
	                }
                }
                return checkResult;
            }
        }else{
        	List<ExcelValidDto> checkResult = new ArrayList<>();
        	ExcelValidDto excelValidDto = new ExcelValidDto();
        	String errorMsg = "模板为空，请输入数据！";
        	excelValidDto.setErrorInfo(errorMsg);
        	checkResult.add(excelValidDto);
        	return checkResult;
        }
        return null;
    }

    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为ProRegistCodeManage001Dto  FinProductValuation对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @param box
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<MstValOp> entities,String userName,String deptCode) {

        List<ExcelValidDto> checkResult = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        Date nowDate = new Date();
        for (int i = 0; i < dataList.size(); i++ ) {
        	//是否正常标志
        	boolean flag = true;
            List<String> list = dataList.get(i);            
           
            Map<String,Object> param = new HashMap<String,Object>();
            // 验证消息
            List<String> message = new ArrayList<>();
            
            //合约标的
            if(list.get(0) == null || list.get(0).trim().equals("")){
            	flag = false;
            	message.add("合约标的不能为空");
            }else{
           	 param.put("targetId",list.get(0));
           	 String  result = externalOptionsDao.checkTargetId(param);
           	 if(StringUtils.isEmpty(result)){
           		flag = false;
                	message.add("不存在有效的合约标的");
                }
            }
            //交易编号 
            if(list.get(1) == null || list.get(1).trim().equals("")){
            	flag = false;
            	message.add("交易编号不能为空");
            }else{
            	 param.put("tradeId",list.get(1));
            	 String  result = externalOptionsDao.checkTradeId(param);
            	 if(StringUtils.isEmpty(result)){
            		flag = false;
                 	message.add("不存在有效的交易编号");
                 }
            }            
            //验证合约标的与交易编号是否匹配
            if(flag){
            	param.put("targetId",list.get(0));
            	param.put("tradeId",list.get(1));
           	    String  result = externalOptionsDao.checkTargetIdAndTradeId(param);
           	    if(StringUtils.isEmpty(result)){
           	    	flag = false;
                	message.add("合约标的与交易编号不匹配");
                }
            }
            //期权价值
            if(list.get(2) == null || list.get(2).trim().equals("")){
            	flag = false;
            	message.add("期权价值不能为空");
            }else{
            	if(!checkFigure(list.get(2))){
            		flag = false;
                	message.add("期权价值不是数字");
            	}
            }
            //估值日期
            if(list.get(3) == null || list.get(3).trim().equals("")){
            	flag = false;
            	message.add("估值日期不能为空");
            }else{
            	if (!checkDate(list.get(3))) {
            		flag = false;
                	message.add("估值日期格式不正确");
                } 
            }
            //公布日期
            if(list.get(4) == null || list.get(4).trim().equals("")){
            	flag = false;
            	message.add("公布日期不能为空");
            }else{
            	if (!checkDate(list.get(4))) {
            		flag = false;
                	message.add("公布日期格式不正确");
                } 
            }
                     
            //主键校验
            if(flag){
            	param.put("tradeId",list.get(1));
            	param.put("valDate", DateUtil.parse(list.get(3)));
            	String  result = externalOptionsDao.getRegistExcelList(param);
            	if(StringUtils.isNotEmpty(result)){
            		flag = false;
            		message.add("存在和交易编号、估值日相同的数据");
            	}                    	
            }
            
            if(flag){
            	MstValOp entity = new MstValOp();           	
                //交易编号
                entity.setFinprodId2(list.get(1));           
                //期权价值
                entity.setValuation(new BigDecimal(list.get(2)));           
                //估值日期         
                entity.setValDate(DateUtil.parse((list.get(3))));            	            
                //公布日期         
                entity.setPublishDate(DateUtil.parse((list.get(4))));            	            
                                      	
                entity.setInputType(DictCodeListExt1.录入方式_系统导入.getValue());             
                entity.setCreateUser(userName);
                entity.setCreateDept(deptCode);                
                entity.setCreateTime(nowDate);
                entity.setUpdateUser(userName);
                entity.setUpdateTime(nowDate);   
                entities.add(entity);
            }
            
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(0), String.valueOf(i+2),
                        StringUtil.joinString(message, GlobalConstant.SEMICOLON)));
            }
        }
        return checkResult;
    }

        
    /**
     * 导入文件之前检查文件 导入的excel是否有重复数据
     *
     * param fileName 导入的文件名名
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) {
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();        
    	//判断excel表中交易编号、估值日是否冲突（主键校验）
    	for (int i = 0; i < dataList.size(); i++ ) {
    		for (int j = i+1; j < dataList.size(); j++ ) {
    			if(StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1)) &&
    					StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))){
    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行交易编号、估值日与"+"第"+(j+2)+"行交易编号、估值日相同");
                    errorList.add(validMessage);
    			}
    		}
    	}
    	//判断excel表中同一个合约标的、期权价值、估值日期不能存在重复的有效状态的数据；
    	for (int i = 0; i < dataList.size(); i++ ) {
    		for (int j = i+1; j < dataList.size(); j++ ) {
    			if(StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
    					StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2)) &&
    					StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))){
    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行合约标的、期权价值、估值日期与"+"第"+(j+2)+"行合约标的、期权价值、估值日期相同");
                    errorList.add(validMessage);
    			}
    		}
    	}

        if(errorList.size() == 0){
        	webResult.setData(NO_INFO_SHOWOUT);
        }else{
        	webResult.setErrorList(errorList);
        }
        return webResult;
    }
    
    /**
     * 判断是否为数字
     *
     * @param 
     * @return 返回boolean
     */
    private boolean checkFigure(String figure) {
    	try{
    		new BigDecimal(figure);
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }
    
    /**
     * 日期格式校验
     *
     * @param 
     * @return 返回boolean
     */
    private boolean checkDate(String date) {
    	boolean flag = true;
    	if(!DateUtil.isDateStr(date, DateUtil.DEFAULT_OTHER_FORMAT,
                DateUtil.DEFAULT_ANOTHER_FORMAT,DateUtil.DEFAULT_DATE_FORMAT_EN)){
        	flag = false;
        }else{
        	date = date.replace("-", "").replace("/", "");
        	//年
        	int year = Integer.valueOf(date.substring(0, 4));
        	//月
        	int month = Integer.valueOf(date.substring(4, 6));
        	//日
        	int day = Integer.valueOf(date.substring(6));
        	if(month < 1 || month > 12){
        		flag = false;
        	}else{
        		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
        			if(day < 1 || day > 31){
        				flag = false;
        			}
        		}else if(month == 4 || month == 6 || month == 9 || month == 11){
        			if(day < 1 || day > 30){
        				flag = false;
        			}
        		}else if(month == 2){
        			if((year % 4 == 0 && year % 100 != 0) ||year % 400 == 0){
        				if(day < 1 || day > 29){
            				flag = false;
            			}
        			}else{
        				if(day < 1 || day > 28){
            				flag = false;
            			}
        			}
        		}
        	}
        }
    	return flag;
    }
    
}
