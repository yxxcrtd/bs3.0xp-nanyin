package com.joyintech.service.basicdata.marketdata.mstshareprice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstshareprice.dao.ComSharePrice001Mapper;
import com.joyintech.service.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import com.joyintech.tams.systemtools.util.ExcelUtil;
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
import com.joyintech.tams.common.base.dao.MstSharePriceMapper;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.constant.DictCodeListExt1;

/**股票类行情信息导入
 * @auther: 曹锦
 * @date: 2020 年 05 月 13
 * @description:
 */

@Service("serviceComSharePriceQuotationInfoImportDataService")
public class ComSharePriceQuotationInfoImportDataService extends CommonExportService<ComSharePriceDto>  {


    /**
     * 计划类（净值型）资产估值行情信息mapper
     */
    @Autowired
    private ComSharePrice001Mapper comSharePrice001Mapper;

    /**
     * 场外金融产品估值Mapper
     */
    @Autowired
    private MstSharePriceMapper mstSharePriceMapper;
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
    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("资产代码");
            headList.add("资产名称");
            headList.add("总股本");
            headList.add("流通股本");
            headList.add("交易日期");
            headList.add("昨收盘价");
            headList.add("开盘价");
            headList.add("最高价");
            headList.add("最低价");
            headList.add("收盘价");
            headList.add("涨跌");
            headList.add("涨跌幅%");
            headList.add("成交量");
            headList.add("成交金额");
            headList.add("均价");
            headList.add("复权昨收盘价");
            headList.add("复权开盘价");
            headList.add("复权最高价");
            headList.add("复权最低价");
            headList.add("复权收盘价");
            headList.add("复权因子");
            headList.add("交易状态");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<MstSharePrice> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult= convertToEntity(dataList, entities,userName,deptCode);
                if(checkResult.isEmpty()) {
	                for(MstSharePrice dto : entities){
	                	mstSharePriceMapper.insert(dto);
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
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<MstSharePrice> entities,String userName,String deptCode) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        Date nowDate = new Date();
        //交易状态枚举值：0：停牌，1：交易，2，待核查，DR：除权除息，N:上市首日，XD:除息，XR：除权
        String[] tradeStatusStr = {"停牌","交易","待核查","除权除息","上市首日","除息","除权"};
        List tradeStatusList = Arrays.asList(tradeStatusStr);
        for (int i = 0; i < dataList.size(); i++ ) {
        	//是否正常标志
        	boolean flag = true;
            List<String> list = dataList.get(i);
            Map<String,Object> param = new HashMap<String,Object>();          
            // 验证消息
            List<String> message = new ArrayList<>();

            //资产代码
            if(list.get(0) == null || list.get(0).trim().equals("")){
            	flag = false;
            	message.add("资产代码不能为空");
            }else{
            	param.put("finprodId",list.get(0));
                param.put("finprodType", DictCodeListExt1.金融产品类型_股票.getValue());
                String result = comSharePrice001Mapper.checkFinprod(param);
                if(StringUtils.isEmpty(result)){
                	flag = false;
            		message.add("资产代码不存在");
                }
            }
            //资产名称
            if(list.get(1) == null || list.get(1).trim().equals("")){
            	flag = false;
            	message.add("资产名称不能为空");
            }
            //校验资产代码和资产名称是否一致
            if(flag){
            	param.put("finprodId",list.get(0));
                param.put("finprodType", DictCodeListExt1.金融产品类型_股票.getValue());
                param.put("finprodName", list.get(1));
                String result = comSharePrice001Mapper.checkFinprodIdAndFinprodName(param);
                if(StringUtils.isEmpty(result)){
                	flag = false;
            		message.add("资产代码和资产名称不匹配");
                }
            }
            //交易日期
            if(list.get(4) == null || list.get(4).trim().equals("")){
            	flag = false;
            	message.add("交易日期不能为空");
            }else{
            	if (!checkDate(list.get(4))) {
            		flag = false;
                	message.add("交易日期格式不正确");
                }
            }
            //总股本
            if(list.get(2) == null || list.get(2).trim().equals("")){
            	flag = false;
            	message.add("总股本不能为空");
            }else{
            	if(!checkFigure(list.get(2))){
            		flag = false;
                	message.add("总股本不是数字");
            	}
            }
            //流通股本
            if(list.get(3) == null || list.get(3).trim().equals("")){
            	flag = false;
            	message.add("流通股本不能为空");
            }else{
            	if(!checkFigure(list.get(3))){
            		flag = false;
                	message.add("流通股本不是数字");
            	}
            }
            //昨收盘价
            if(list.get(5) == null || list.get(5).trim().equals("")){
            	flag = false;
            	message.add("昨收盘价不能为空");
            }else{
            	if(!checkFigure(list.get(5))){
            		flag = false;
                	message.add("昨收盘价不是数字");
            	}
            }
            //开盘价
            if(list.get(6) == null || list.get(6).trim().equals("")){
            	flag = false;
            	message.add("开盘价不能为空");
            }else{
            	if(!checkFigure(list.get(6))){
            		flag = false;
                	message.add("开盘价不是数字");
            	}
            }
            //最高价
            if(list.get(7) == null || list.get(7).trim().equals("")){
            	flag = false;
            	message.add("最高价不能为空");
            }else{
            	if(!checkFigure(list.get(7))){
            		flag = false;
                	message.add("最高价不是数字");
            	}
            }
            //最低价
            if(list.get(8) == null || list.get(8).trim().equals("")){
            	flag = false;
            	message.add("最低价不能为空");
            }else{
            	if(!checkFigure(list.get(8))){
            		flag = false;
                	message.add("最低价不是数字");
            	}
            }
            //收盘价
            if(list.get(9) == null || list.get(9).trim().equals("")){
            	flag = false;
            	message.add("收盘价不能为空");
            }else{
            	if(!checkFigure(list.get(9))){
            		flag = false;
                	message.add("收盘价不是数字");
            	}
            }       
            //涨跌
            if(list.get(10) == null || list.get(10).trim().equals("")){
            	flag = false;
            	message.add("涨跌不能为空");
            }else{
            	if(!checkFigure(list.get(10))){
            		flag = false;
                	message.add("涨跌不是数字");
            	}
            }
            //涨跌幅
            if(list.get(11) == null || list.get(11).trim().equals("")){
            	flag = false;
            	message.add("涨跌幅不能为空");
            }else{
            	if(!checkFigure(list.get(11))){
            		flag = false;
                	message.add("涨跌幅不是数字");
            	}
            }
            //成交量
            if(list.get(12) == null || list.get(12).trim().equals("")){
            	flag = false;
            	message.add("成交量不能为空");
            }else{
            	if(!checkFigure(list.get(12))){
            		flag = false;
                	message.add("成交量不是数字");
            	}
            }
            //成交金额
            if(list.get(13) == null || list.get(13).trim().equals("")){
            	flag = false;
            	message.add("成交金额不能为空");
            }else{
            	if(!checkFigure(list.get(13))){
            		flag = false;
                	message.add("成交金额不是数字");
            	}
            }
            //均价
            if(list.get(14) == null || list.get(14).trim().equals("")){
            	flag = false;
            	message.add("均价不能为空");
            }else{
            	if(!checkFigure(list.get(14))){
            		flag = false;
                	message.add("均价不是数字");
            	}
            }
            //复权昨收盘价
            if(list.get(15) == null || list.get(15).trim().equals("")){
            	flag = false;
            	message.add("复权昨收盘价不能为空");
            }else{
            	if(!checkFigure(list.get(15))){
            		flag = false;
                	message.add("复权昨收盘价不是数字");
            	}
            }
            //复权开盘价
            if(list.get(16) == null || list.get(16).trim().equals("")){
            	flag = false;
            	message.add("复权开盘价不能为空");
            }else{
            	if(!checkFigure(list.get(16))){
            		flag = false;
                	message.add("复权开盘价不是数字");
            	}
            }
            //复权最高价
            if(list.get(17) == null || list.get(17).trim().equals("")){
            	flag = false;
            	message.add("复权最高价不能为空");
            }else{
            	if(!checkFigure(list.get(17))){
            		flag = false;
                	message.add("复权最高价不是数字");
            	}
            }
            //复权最低价
            if(list.get(18) == null || list.get(18).trim().equals("")){
            	flag = false;
            	message.add("复权最低价不能为空");
            }else{
            	if(!checkFigure(list.get(18))){
            		flag = false;
                	message.add("复权最低价不是数字");
            	}
            }
            //复权收盘价
            if(list.get(19) == null || list.get(19).trim().equals("")){
            	flag = false;
            	message.add("复权收盘价不能为空");
            }else{
            	if(!checkFigure(list.get(19))){
            		flag = false;
                	message.add("复权收盘价不是数字");
            	}
            }
            //复权因子
            if(list.get(20) == null || list.get(20).trim().equals("")){
            	flag = false;
            	message.add("复权因子不能为空");
            }else{
            	if(!checkFigure(list.get(20))){
            		flag = false;
                	message.add("复权因子不是数字");
            	}
            }
            //交易状态
            if(list.get(21) == null || list.get(21).trim().equals("")){
            	flag = false;
            	message.add("交易状态不能为空");
            }else{
            	if(!tradeStatusList.contains(list.get(21).trim())){
            		flag = false;
                	message.add("交易状态不在枚举值之中");
            	}
            }
            //主键校验
            if(flag){
            	param.put("finprodId",list.get(0));
            	param.put("tradeDate", DateUtil.parse(list.get(4)));
            	String  result = comSharePrice001Mapper.getRegistExcelList(param);
            	if(StringUtils.isNotEmpty(result)){
            		flag = false;
            		message.add("存在和股票代码、交易日相同的数据");
            	}                    	
            }
            
            if(flag){
            	MstSharePrice entity = new MstSharePrice();
            	//资产代码
            	entity.setSharesId(list.get(0));
            	//交易日期
            	entity.setDealDate(DateUtil.parse(list.get(4)));
            	//昨收盘价
            	entity.setPreClosePrice(new BigDecimal(list.get(5)));            	               
                //开盘价
            	entity.setOpenPrice(new BigDecimal(list.get(6)));            	              
                //最高价
            	entity.setHighPrice(new BigDecimal(list.get(7)));            	              
                //最低价
            	entity.setLowPrice(new BigDecimal(list.get(8)));            	           
                //收盘价
            	entity.setClosePrice(new BigDecimal(list.get(9)));            	            
                //当日总股本
            	entity.setsShares(new BigDecimal(list.get(2)));            	             
                //当日流通股本
            	entity.setcShares(new BigDecimal(list.get(3)));            	             
                //涨跌
            	entity.setChange(new BigDecimal(list.get(10)));            	               
                //涨跌幅
            	entity.setChangePercent(new BigDecimal(list.get(11)));            	              
                //成交量
            	entity.setDealValume(new BigDecimal(list.get(12)));            	             
                //成交金额
            	entity.setDealAmount(new BigDecimal(list.get(13)));            	             
                //均价
            	entity.setAvgPrice(new BigDecimal(list.get(14)));            	               
                //复权昨收盘价
            	entity.setPreClosePriceAdj(new BigDecimal(list.get(15)));            	             
                //复权开盘价
            	entity.setOpenPriceAdj(new BigDecimal(list.get(16)));            	               
                //复权最高价
            	entity.setHighPriceAdj(new BigDecimal(list.get(17)));            	             
                //复权最低价
            	entity.setLowPriceAdj(new BigDecimal(list.get(18)));            	              
                //复权收盘价
            	entity.setClosePriceAdj(new BigDecimal(list.get(19)));            	               
                //复权因子
            	entity.setFactorAdj(new BigDecimal(list.get(20)));            	
                //交易状态
            	String tradeStatus = list.get(21);
            	if(tradeStatus.equals("停牌")){
            		tradeStatus = "0";
            	}else if(tradeStatus.equals("交易")){
            		tradeStatus = "1";
            	}else if(tradeStatus.equals("待核查")){
            		tradeStatus = "2";
            	}else if(tradeStatus.equals("除权除息")){
            		tradeStatus = "DR";
            	}else if(tradeStatus.equals("上市首日")){
            		tradeStatus = "N";
            	}else if(tradeStatus.equals("除息")){
            		tradeStatus = "XD";
            	}else if(tradeStatus.equals("除权")){
            		tradeStatus = "XR";
            	}
            	entity.setTradeStatus(tradeStatus); 
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
    	//判断excel表中资产代码和交易日期是否冲突
    	for (int i = 0; i < dataList.size(); i++ ) {
    		for (int j = i+1; j < dataList.size(); j++ ) {
    			if(StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
    					StringUtils.equals(dataList.get(i).get(4), dataList.get(j).get(4))){
    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行资产代码、交易日期与"+"第"+(j+2)+"行资产代码、交易日期相同");
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
