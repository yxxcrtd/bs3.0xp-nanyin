package com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.service;

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
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dao.ValBondMarketInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001Dto;
import com.joyintech.tams.common.base.dao.MstBondMarketInfoMapper;
import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**每日债券行情信息导入
 * @auther: 曹锦
 * @date: 2020 年 05 月 14
 * @description:
 */

@Service
public class ValBondMarketQuotationInfoImportDataService extends CommonExportService<ValBondMarketInfo001Dto>  {


	/**
    * 每日债券行情 mapper
    */
   @Autowired
   private ValBondMarketInfo001Mapper valBondMarketInfo001Mapper;

    /**
     * 场外金融产品估值Mapper
     */
    @Autowired
    private MstBondMarketInfoMapper mstBondMarketInfoMapper;
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
			headList.add("资产代码");
			headList.add("债券简称");
//            headList.add("估值来源");
			headList.add("估值日");
//            headList.add("剩余期限");
			headList.add("收盘价净价");
			headList.add("平均价净价");
			headList.add("买一价净价");
			headList.add("卖一价净价");
			headList.add("最高价净价");
			headList.add("最低价净价");
			headList.add("收盘价全价");
			headList.add("平均价全价");
			headList.add("买一价全价");
			headList.add("卖一价全价");
			headList.add("最高价全价");
			headList.add("最低价全价");
			List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
			if (!StringUtil.isEmptyList(tempCheckResult)) {
				return tempCheckResult;
			}
			if (null != dataList) {
				List<MstBondMarketInfo> entities = new ArrayList<>(dataList.size());
				List<ExcelValidDto> checkResult= convertToEntity(dataList, entities,userName,deptCode);
				if(checkResult.isEmpty()) {
					for(MstBondMarketInfo dto : entities){
						mstBondMarketInfoMapper.insertFull(dto);
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
	private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<MstBondMarketInfo> entities,String userName,String deptCode) {

		List<ExcelValidDto> checkResult = new ArrayList<>();
		//估值来源枚举值：银行间、商业银行柜台交易、上交所、深交所、机构间私募系统、理财直接融资工具平台、其他场外、交易所、场外。
		String[] valueSourceStr = {"银行间","商业银行柜台交易","上交所","深交所","机构间私募系统","理财直接融资工具平台","其他场外","交易所","场外"};
		List valueSourceList = Arrays.asList(valueSourceStr);
		Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
		Date nowDate = new Date();
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
				String  result = valBondMarketInfo001Mapper.checkFinprod(param);
				if(StringUtils.isEmpty(result)){
					flag = false;
					message.add("资产代码不存在");
				}
			}
			//债券简称
			if(list.get(1) == null || list.get(1).trim().equals("")){
				flag = false;
				message.add("债券简称不能为空");
			}
			//验证债券简称与资产代码是否匹配
			if(flag){
				param.put("finprodId",list.get(0));
				param.put("finprodName",list.get(1));
				String  result = valBondMarketInfo001Mapper.checkFinprodIdAndFinprodName(param);
				if(StringUtils.isEmpty(result)){
					flag = false;
					message.add("资产代码与债券简称不匹配");
				}
			}
			//估值来源
//            if(list.get(2) == null || list.get(2).trim().equals("")){
//            	flag = false;
//            	message.add("估值来源不能为空");
//            }else{
//            	if(!valueSourceList.contains(list.get(2).trim())){
//            		flag = false;
//                	message.add("估值来源不在枚举值之中");
//            	}
//            }
			//估值日
			if(list.get(2) == null || list.get(2).trim().equals("")){
				flag = false;
				message.add("估值日不能为空");
			}else{
				if (!checkDate(list.get(2))) {
					flag = false;
					message.add("估值日期格式不正确");
				}
			}
			//剩余期限
//            if(list.get(4) == null || list.get(4).trim().equals("")){
//            	flag = false;
//            	message.add("剩余期限不能为空");
//            }
			//收盘价净价
			if(list.get(3) == null || list.get(3).trim().equals("")){
				flag = false;
				message.add("收盘价净价不能为空");
			}else{
				if(!checkFigure(list.get(3))){
					flag = false;
					message.add("收盘价净价不是数字");
				}
			}
			//平均价净价
			if(list.get(4) == null || list.get(4).trim().equals("")){
				flag = false;
				message.add("平均价净价不能为空");
			}else{
				if(!checkFigure(list.get(4))){
					flag = false;
					message.add("平均价净价不是数字");
				}
			}
			//买一价净价
			if(list.get(5) == null || list.get(5).trim().equals("")){
				flag = false;
				message.add("买一价净价不能为空");
			}else{
				if(!checkFigure(list.get(5))){
					flag = false;
					message.add("买一价净价不是数字");
				}
			}
			//卖一价净价
			if(list.get(6) == null || list.get(6).trim().equals("")){
				flag = false;
				message.add("卖一价净价不能为空");
			}else{
				if(!checkFigure(list.get(6))){
					flag = false;
					message.add("卖一价净价不能为空");
				}
			}
			//最高价净价
			if(list.get(7) == null || list.get(7).trim().equals("")){
				flag = false;
				message.add("最高价净价不能为空");
			}else{
				if(!checkFigure(list.get(7))){
					flag = false;
					message.add("最高价净价不是数字");
				}
			}
			//最低价净价
			if(list.get(8) == null || list.get(8).trim().equals("")){
				flag = false;
				message.add("最低价净价不能为空");
			}else{
				if(!checkFigure(list.get(8))){
					flag = false;
					message.add("最低价净价不是数字");
				}
			}
			//收盘价全价
			if(StringUtils.isNotEmpty(list.get(9))){
				if(!checkFigure(list.get(9))){
					flag = false;
					message.add("收盘价全价不是数字");
				}
			}
			//平均价全价
			if(StringUtils.isNotEmpty(list.get(10))){
				if(!checkFigure(list.get(10))){
					flag = false;
					message.add("平均价全价不是数字");
				}
			}
			//买一价全价
			if(StringUtils.isNotEmpty(list.get(11))){
				if(!checkFigure(list.get(11))){
					flag = false;
					message.add("买一价全价不是数字");
				}
			}
			//卖一价全价
			if(StringUtils.isNotEmpty(list.get(12))){
				if(!checkFigure(list.get(12))){
					flag = false;
					message.add("卖一价全价不是数字");
				}
			}
			//最高价全价
			if(StringUtils.isNotEmpty(list.get(13))){
				if(!checkFigure(list.get(13))){
					flag = false;
					message.add("最高价全价不是数字");
				}
			}
			//最低价全价
			if(StringUtils.isNotEmpty(list.get(14))){
				if(!checkFigure(list.get(14))){
					flag = false;
					message.add("最低价全价不是数字");
				}
			}
			//主键校验
			if(flag){
				param.put("finprodId",list.get(0));
				param.put("tradeDate", DateUtil.parse(list.get(2)));
				String  result = valBondMarketInfo001Mapper.getRegistExcelList(param);
				if(StringUtils.isNotEmpty(result)){
					flag = false;
					message.add("存在和资产代码与估值日相同的数据");
				}
			}

			if(flag){
				MstBondMarketInfo entity = new MstBondMarketInfo();
				//资产代码
				entity.setSecId(list.get(0));
				//估值日
				entity.setValueDate(DateUtil.parse(list.get(2)));
				//收盘价
				entity.setClosePrice(new BigDecimal(list.get(3)));
				//平均价
				entity.setAveragePrice(new BigDecimal(list.get(4)));
				//最高价
				entity.setMaxPrice(new BigDecimal(list.get(7)));
				//最低价
				entity.setMinPrice(new BigDecimal(list.get(8)));
				//买一价
				entity.setBuyOnePrice(new BigDecimal(list.get(5)));
				//卖一价
				entity.setSellOnePrice(new BigDecimal(list.get(6)));
				//收盘价全价
				if(StringUtils.isNotEmpty(list.get(9))){
					entity.setClosePriceFull(new BigDecimal(list.get(9)));
				}
				//平均价全价
				if(StringUtils.isNotEmpty(list.get(10))){
					entity.setAveragePriceFull(new BigDecimal(list.get(10)));
				}
				//买一价全价
				if(StringUtils.isNotEmpty(list.get(11))){
					entity.setBuyOnePriceFull(new BigDecimal(list.get(11)));
				}
				//卖一价全价
				if(StringUtils.isNotEmpty(list.get(12))){
					entity.setSellOnePriceFull(new BigDecimal(list.get(12)));
				}
				//最高价全价
				if(StringUtils.isNotEmpty(list.get(13))){
					entity.setMaxPriceFull(new BigDecimal(list.get(13)));
				}
				//最低价全价
				if(StringUtils.isNotEmpty(list.get(14))){
					entity.setMinPriceFull(new BigDecimal(list.get(14)));
				}
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
		//判断excel表中资产代码和估值日是否冲突
		for (int i = 0; i < dataList.size(); i++ ) {
			for (int j = i+1; j < dataList.size(); j++ ) {
				if(StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
						StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2))){
					ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行资产代码和估值日与"+"第"+(j+2)+"行资产代码和估值日相同");
					errorList.add(validMessage);
				}
			}
		}
//    	//判断excel表中估值日和估值来源是否冲突
//    	for (int i = 0; i < dataList.size(); i++ ) {
//    		for (int j = i+1; j < dataList.size(); j++ ) {
//    			if(StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2)) &&
//    					StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))){
//    				ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行估值日和估值来源与"+"第"+(j+2)+"行估值日和估值来源相同");
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
