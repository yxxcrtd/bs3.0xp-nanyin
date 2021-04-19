package com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.dto.ComNetAssetQuotationInfo001Dto;
import com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.dao.ComNetAssetQuotationInfo001Mapper;
import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.constant.DictCodeListExt1;
//import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**基金类行情信息导入
 * @auther: 张星1
 * @date: 2020 年 02 月 20
 * @description:
 */

@Service("serviceComNetAssetQuotationInfoImportDataService")
public class ComNetAssetQuotationInfoImportDataService extends CommonExportService<ComNetAssetQuotationInfo001Dto>  {


    /**
     * 计划类（净值型）资产估值行情信息mapper
     */
    @Autowired
    private ComNetAssetQuotationInfo001Mapper comNetAssetQuotationInfo001Mapper;

    /**
     * 场外金融产品估值Mapper
     */
    @Autowired
    private FinProductValuationMapper finProductValuationMapper;
    /**
     * 平台日组件
     */
    @Autowired
    private CommonPlatformDateService commonPlatformDateService;
    
    
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
    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList, String userName, String orgCode, String deptCode) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();

            headList.add("资产代码");
            headList.add("资产名称");
            headList.add("估值日期");
            headList.add("净值");
            headList.add("导入日");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<FinProductValuation> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult= convertToEntity(dataList, entities,userName,deptCode);
                if(checkResult.isEmpty()) {
	                for(FinProductValuation dto : entities){
	                	finProductValuationMapper.insert(dto);
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
     * @param userName
     * @param deptCode
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<FinProductValuation> entities,String userName,String deptCode) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        Date nowDate = new Date();
        for (int i = 0; i < dataList.size(); i++ ) {
        	//是否正常标志
        	boolean flag = true;
            List<String> list = dataList.get(i);
      
            // 验证消息
            List<String> message = new ArrayList<>();
            Map<String,Object> param = new HashMap<String,Object>();
            
            //资产代码
            if(list.get(0) == null || list.get(0).trim().equals("")){
            	flag = false;
            	message.add("资产代码不能为空");
            }else{
            	param.put("finprodId",list.get(0));
                param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                ComNetAssetQuotationInfo001Dto dto = comNetAssetQuotationInfo001Mapper.checkFinprod(param);
                if (dto == null){
                    message.add("资产代码错误");
                }
            }
            //资产名称
            if(list.get(1) == null || list.get(1).trim().equals("")){
            	flag = false;
            	message.add("资产名称不能为空");
            }  
            //验证资产代码和资产名称是否匹配
            if(flag){
            	param.put("finprodId",list.get(0));
            	param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                param.put("finprodName", list.get(1));
                ComNetAssetQuotationInfo001Dto dto = comNetAssetQuotationInfo001Mapper.checkFinprodIdAndFinprodName(param);
                if (dto == null){
                    message.add("资产代码与资产名称不匹配");
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
            //净值/万份收益
            if(list.get(3) == null || list.get(3).trim().equals("")){
            	flag = false;
            	message.add("净值不能为空");
            }else{
            	if(!checkFigure(list.get(3))){
            		flag = false;
                	message.add("净值不是数字");
            	}
            }
            // 导入日
            if(StringUtil.isNotEmpty(list.get(4))){
                if(!checkDate(list.get(4))){
                	flag = false;
                	message.add("导入日期格式不正确");
                }
            }
            
            //主键校验
            if(flag){
            	param.put("finprodId",list.get(0));
                param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                param.put("valDate", DateUtil.parse(list.get(2)));
            	String  result = comNetAssetQuotationInfo001Mapper.getRegistExcelList(param);
            	if(StringUtils.isNotEmpty(result)){
            		flag = false;
            		message.add("存在和资产代码、估值日相同的数据");
            	}                    	
            }
            
            if(flag){
            	FinProductValuation entity = new FinProductValuation();
            	//资产代码               
                entity.setFinprodId(list.get(0));
                //估值日期
                entity.setValDate(DateUtil.parse(list.get(2)));
                //万份收益/净值
                entity.setValuation(new BigDecimal(list.get(3)));  
                // 导入日
                if(StringUtil.isNotEmpty(list.get(4))){
                	entity.setCreateTime(DateUtil.parse(list.get(4)));                    
                }else {
                    // 默认导入日= 录入日 =平台日
                    entity.setCreateTime(nowDate);
                }
                entity.setInputType(DictCodeListExt1.录入方式_系统导入.getValue());             
                entity.setCreateUser(userName);
                entity.setCreateDept(deptCode);
                entity.setUpdateUser(userName);
                entity.setUpdateTime(nowDate);   
                entity.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
                entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                Date sysDate1 = commonPlatformDateService.getSysDate();
                entity.setPublishDate(sysDate1);
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
    	//判断excel表中资产代码和估值日期是否冲突
    	for (int i = 0; i < dataList.size(); i++ ) {
    		for (int j = i+1; j < dataList.size(); j++ ) {
    			if(StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
    					StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2))){
    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行资产代码、估值日期与"+"第"+(j+2)+"行资产代码、估值日期相同");
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
