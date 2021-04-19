package com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dao.ComCoinFundQuotationInfoMapper;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dto.ComCoinFundQuotationInfo001Dto;
import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**基金类行情信息导入
 * @auther: 张星1
 * @date: 2020 年 02 月 20
 * @description:
 */

@Service("serviceComCoinFundQuotationInfoImportDataService")
public class ComCoinFundQuotationInfoImportDataService extends CommonExportService<ComCoinFundQuotationInfo001Dto> {

    @Autowired
    private ComCoinFundQuotationInfoMapper comCoinFundQuotationInfoMapper;

    /**
     * 金融产品mapper（系统自动生成）
     */
    @Autowired
    private FinProductValuationMapper finProductValuationMapper;
    
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";
    /**
     * 标的金融产品Mapper
     */
   // @Autowired
   // private FinProductMapper finProductMapper;




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
            headList.add("基金代码");
            headList.add("基金类型");
            headList.add("估值日期");
            headList.add("公布日期");
            headList.add("万份收益/净值");
            headList.add("基金管理人");
            headList.add("七日年化收益率(%)");
            headList.add("累计净值");
            headList.add("复权单位净值");
            headList.add("导入日");
            headList.add("基金总份额");   //增加项     杨文君
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
        	//基金代码flag
        	boolean finprodIdFlag = true;
            List<String> list = dataList.get(i);
            // 验证消息
            List<String> message = new ArrayList<>();
            Map<String,Object> param = new HashMap<String,Object>();

            //基金代码
            if(list.get(0) == null || list.get(0).trim().equals("")){
            	flag = false;
            	finprodIdFlag = false;
            	message.add("基金代码不能为空");
            }else{
            	param.put("finprodId",list.get(0));
                param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                ComCoinFundQuotationInfo001Dto dto = comCoinFundQuotationInfoMapper.checkFinprod(param);
                if (dto == null){
                	flag = false;
                	finprodIdFlag = false;
                    message.add("基金代码错误");
                }
            } 
            //基金类型
            if(list.get(1) == null || list.get(1).trim().equals("")){
            	flag = false;
            	message.add("基金类型不能为空");
            }
            //验证基金类型和基金代码是否匹配
            if(flag){
            	param.put("finprodId",list.get(0));
                param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                String type = "";
                if(list.get(1).equals("货币基金")){
                	type = "F01";
                }else if(list.get(1).equals("非货币基金")){
                	type = "F02";
                }
                param.put("type", type);
                ComCoinFundQuotationInfo001Dto dto = comCoinFundQuotationInfoMapper.checkType(param);
                if (dto == null){
                	flag = false;
                    message.add("基金类型和基金代码不匹配");
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
            //公布日期
            if(list.get(3) == null || list.get(3).trim().equals("")){
            	flag = false;
            	message.add("公布日期不能为空");
            }else{
            	if (!checkDate(list.get(3))) {
            		flag = false;
                	message.add("公布日期格式不正确");
                } 
            }
            //万份收益/净值
            if(list.get(4) == null || list.get(4).trim().equals("")){
            	flag = false;
                message.add("万份收益/净值不能为空");
            }else{
            	if(!checkFigure(list.get(4))){
            		flag = false;
                	message.add("万份收益/净值不是数字");
            	}
            }
            //基金管理人
            if(list.get(5) == null || list.get(5).trim().equals("")){
            	flag = false;
                message.add("基金管理人不能为空");
            }else{
            	//验证基金管理人与基金代码是否匹配
            	if(finprodIdFlag){
            		param.put("finprodId",list.get(0));
            		param.put("customerName", list.get(5));
            		String dto = comCoinFundQuotationInfoMapper.checkFundManager(param);
            		if (dto == null){
            			flag = false;
            			message.add("基金管理人和基金代码不匹配");
            		}
            	}           	
            }
            //七日年化收益率(%)
            if(list.get(6) != null && !list.get(6).trim().equals("")){
            	if(!checkFigure(list.get(6))){
            		flag = false;
                	message.add("七日年化收益率不是数字");
            	}
            }
            //累计净值
            if(list.get(7) != null && !list.get(7).trim().equals("")){
            	if(!checkFigure(list.get(7))){
            		flag = false;
                	message.add("累计净值不是数字");
            	}
            }
            //  复权单位净值
            if(list.get(8) != null && !list.get(8).trim().equals("")){
            	if(!checkFigure(list.get(8))){
            		flag = false;
                	message.add("复权单位净值不是数字");
            	}
            }
            // 导入日
            if(list.get(9) != null && !list.get(9).trim().equals("")){
            	if (!checkDate(list.get(9))) {
            		flag = false;
                	message.add("导入日期格式不正确");
                } 
            }
          //基金总份额    杨文君增加
            if(list.get(10) == null || list.get(10).trim().equals("")){
            	flag = false;
                message.add("基金总份额不能为空");
            }else{
            	if(!checkFigure(list.get(10))){
            		flag = false;
                	message.add("基金总份额不是数字");
            	}
            }
            //主键校验
            if(flag){
                param.put("finprodId",list.get(0));
                param.put("valDate", DateUtil.parse(list.get(2)));
                param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                String  result = comCoinFundQuotationInfoMapper.getRegistExcelList(param);
            	if(StringUtils.isNotEmpty(result)){
            		flag = false;
            		message.add("存在和基金代码、估值日相同的数据");
            	}                    	
            }
            if(flag){
            	FinProductValuation entity = new FinProductValuation();
            	//基金代码
                entity.setFinprodId(list.get(0));
                //估值日期
                entity.setValDate(DateUtil.parse(list.get(2)));
                //公布日期
                entity.setPublishDate(DateUtil.parse(list.get(3)));
                //万份收益
                entity.setValuation(new BigDecimal(list.get(4)));               
                //七日年化收益率(%)
                if(StringUtil.isNotEmpty(list.get(6))){
                    BigDecimal day7YearYield = new BigDecimal(list.get(6));
                    entity.setDay7YearYield(day7YearYield.divide(BigDecimal.valueOf(100)));
                }
               //累计净值
                if(StringUtil.isNotEmpty(list.get(7))){
                    BigDecimal totalValue = new BigDecimal(list.get(7));
                    entity.setTotalValue(totalValue);
                }
              //  复权单位净值
                if(StringUtil.isNotEmpty(list.get(8))){
                    BigDecimal restNetValue = new BigDecimal(list.get(8));
                    entity.setRestNetValue(restNetValue);
                }
                // 导入日
                if(StringUtil.isNotEmpty(list.get(9))){
                    entity.setCreateTime(DateUtil.parse(list.get(9)));
                }else {                    
                    // 默认导入日= 录入日 =平台日
                    entity.setCreateTime(nowDate);
                }
              //基金总份额           杨文君添加
                if(StringUtil.isNotEmpty(list.get(10))){
                    BigDecimal cShares = new BigDecimal(list.get(10));
                    entity.setcShares(cShares);
                }
                //  金融产品类型（估值核算）
                entity.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
                //金融产品类型（投管分类）
                entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                entity.setInputType(DictCodeListExt1.录入方式_系统导入.getValue());             
                entity.setCreateUser(userName);
                entity.setCreateDept(deptCode);
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
    	//判断excel表中基金代码、估值日是否冲突
    	for (int i = 0; i < dataList.size(); i++ ) {
    		for (int j = i+1; j < dataList.size(); j++ ) {
    			if(StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
    					StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2)) &&
    					StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))){
    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行基金代码、估值日与"+"第"+(j+2)+"行基金代码、估值日相同");
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
