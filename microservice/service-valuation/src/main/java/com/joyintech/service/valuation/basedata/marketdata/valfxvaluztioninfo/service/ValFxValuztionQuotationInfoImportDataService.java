package com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.service;

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
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo001Dto;
import com.joyintech.tams.common.base.dao.MstValFxMapper;
import com.joyintech.tams.common.base.entity.MstValFx;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;

/**外汇行情信息导入
 * @auther: 曹锦
 * @date: 2020 年 05 月 15
 * @description:
 */

@Service
public class ValFxValuztionQuotationInfoImportDataService extends CommonExportService<ValFxValuztionInfo001Dto>  {


	/**
    * 每日债券行情 mapper
    */
   @Autowired
   private ValFxValuztionInfo001Mapper valFxValuztionInfo001Mapper;

   @Autowired
   private MstValFxMapper mstValFxMapper;
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
            headList.add("交易编号");
            headList.add("外汇类型");
            headList.add("估值日期");
            headList.add("估值");
            headList.add("币种");
            headList.add("数据来源");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<MstValFx> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult= convertToEntity(dataList, entities,userName,deptCode);
                if(checkResult.isEmpty()) {
	                for(MstValFx dto : entities){
	                	mstValFxMapper.insert(dto);
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
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<MstValFx> entities,String userName,String deptCode) {

        List<ExcelValidDto> checkResult = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        Date nowDate = new Date();
        //外汇类型：即期、远期、掉期。
        String[] waiHuiStr = {"即期","远期","掉期"};
        List waiHuiList = Arrays.asList(waiHuiStr);
        //数据来源枚举值：BAK:行内、CALYPSO：calypso
        String[] dataSourceStr = {"行内","calypso"};
        List dataSourceList = Arrays.asList(dataSourceStr);
        for (int i = 0; i < dataList.size(); i++ ) {
        	//是否正常标志
        	boolean flag = true;
            List<String> list = dataList.get(i);            
           
            Map<String,Object> param = new HashMap<String,Object>();
            // 验证消息
            List<String> message = new ArrayList<>();
            //数据来源
            String dataSource = "";
            String ccy = "";
            
            //交易编号 金融产品代码（市场代码）    
            if(list.get(0) == null || list.get(0).trim().equals("")){
            	flag = false;
            	message.add("交易编号不能为空");
            }else{
            	 param.put("finprodId",list.get(0));
            	 String  result = valFxValuztionInfo001Mapper.checkFinprod(param);
            	 if(StringUtils.isEmpty(result)){
            		flag = false;
                 	message.add("不存在有效的外汇交易编号");
                 }
            }
            //外汇类型
            if(list.get(1) == null || list.get(1).trim().equals("")){
            	flag = false;
            	message.add("外汇类型不能为空");
            }else{
            	if(!waiHuiList.contains(list.get(1).trim())){
            		flag = false;
                	message.add("外汇类型不在枚举值之中");
            	}
            }
            //验证交易编号与外汇类型是否匹配
            if(flag){
            	param.put("finprodId",list.get(0));
            	param.put("finProdType2",list.get(1));
           	    String  result = valFxValuztionInfo001Mapper.checkFinprodAndFinprodType2(param);
           	    if(StringUtils.isEmpty(result)){
           	    	flag = false;
                	message.add("交易编号与外汇类型不匹配");
                }
            }
            //估值日期
            if(list.get(2) == null || list.get(2).trim().equals("")){
            	flag = false;
            	message.add("估值日期不能为空");
            }else{
            	if (!checkDate(list.get(2))) {
            		flag = false;
                	message.add("估值日期格式不正确");
                } 
            }
            //估值
            if(list.get(3) == null || list.get(3).trim().equals("")){
            	flag = false;
            	message.add("估值不能为空");
            }else{
            	if(!checkFigure(list.get(3))){
            		flag = false;
                	message.add("估值不是数字");
            	}
            }
            //币种
            if(StringUtils.isNotEmpty(list.get(4))){
            	if(list.get(4).length() < 2){
            		flag = false;
                	message.add("币种输入不正确");
            	}else{
                    param.put("ccy", list.get(4));
                    ccy =    valFxValuztionInfo001Mapper.checkCcy(param);
                    if(ccy == null){
                    	flag = false;
                    	message.add("币种输入不正确");
                    }
            	}
        	} 
            //数据来源
            if(list.get(5) == null || list.get(5).trim().equals("")){
            	flag = false;
            	message.add("数据来源不能为空");
            }else{
            	if(!dataSourceList.contains(list.get(5).trim())){
            		flag = false;
                	message.add("数据来源不在枚举值之中");
            	}else{
            		if(list.get(5).equals("calypso")){
            			dataSource = "CALYPSO";
            		}else if(list.get(5).equals("行内")){
            			dataSource = "BAK";
            		}
            	}
            }           
            //主键校验
            if(flag){
            	param.put("finprodId",list.get(0));
            	param.put("valDate", DateUtil.parse(list.get(2)));
            	param.put("dataSource", dataSource);
            	String  result = valFxValuztionInfo001Mapper.getRegistExcelList(param);
            	if(StringUtils.isNotEmpty(result)){
            		flag = false;
            		message.add("存在和交易编号、估值日与数据来源相同的数据");
            	}                    	
            }
            
            if(flag){
            	MstValFx entity = new MstValFx();           	
                //交易编号 金融产品代码（市场代码）    
                entity.setFinprodId2(list.get(0));           
                //估值日            
                entity.setValDate(DateUtil.parse((list.get(2))));            	            
                //估值           
                entity.setValuation(new BigDecimal(list.get(3)));            	                       	            
                //币种             
                entity.setCcy(ccy);            	                     	            
                //数据来源   
                entity.setDatasourceType(dataSource);   
                                      	
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
    	//判断excel表中交易编号、估值日与数据来源是否冲突（主键校验）
    	for (int i = 0; i < dataList.size(); i++ ) {
    		for (int j = i+1; j < dataList.size(); j++ ) {
    			if(StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
    					StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2)) &&
    					StringUtils.equals(dataList.get(i).get(5), dataList.get(j).get(5))){
    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行交易编号、估值日和数据来源与"+"第"+(j+2)+"行交易编号、估值日和数据来源相同");
                    errorList.add(validMessage);
    			}
    		}
    	}
    	//判断excel表中相同的交易编号、估值日期、外汇类型、数据来源不能存在重复的有效状态的数据
//    	for (int i = 0; i < dataList.size(); i++ ) {
//    		for (int j = i+1; j < dataList.size(); j++ ) {
//    			if(StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2)) &&
//    					StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))){
//    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行估值日和基金类型与"+"第"+(j+2)+"行估值日和基金类型相同");
//                    errorList.add(validMessage);
//    			}
//    		}
//    	}

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
