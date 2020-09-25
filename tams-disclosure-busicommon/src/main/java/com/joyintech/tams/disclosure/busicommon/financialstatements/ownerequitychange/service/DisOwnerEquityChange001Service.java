/*
 * 文件名：OwnerEquityChange001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.common.dao.DisValAssetBook001Mapper;
import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalance;
import com.joyintech.tams.disclosure.busicommon.common.dto.ExportFileDto;
import com.joyintech.tams.disclosure.busicommon.common.service.DisFinProductCommonBatchService;
import com.joyintech.tams.disclosure.busicommon.common.util.ArithmeticUtil;
import com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.dao.DisOwnerEquityChange001Mapper;
import com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.dto.DisOwnerEquityChange001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisProfitCalculationDto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisValProfitQuery001Dto;
import com.joyintech.tools.util.ExcelTools;

/**
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Service
 * @since 1.0
 */
@Service
public class DisOwnerEquityChange001Service extends CommonExportService<DisOwnerEquityChange001Dto> {

    @Autowired
    private DisOwnerEquityChange001Mapper ownerEquityChange001Mapper;
    
    @Autowired
    private  RepProdInfoMapper  repProdInfoMapper;

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 标的类共通方法
     */
    @Autowired
    private DisFinProductCommonBatchService finProductCommonBatchService;
    @Autowired
    private  DisValAssetBook001Mapper disValAssetBook001Mapper;
    /**
     * 分页查询
     * 
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<DisOwnerEquityChange001Dto> selectList(Map<String, Object> pageParam)
        throws Exception {
        //changeMapData(pageParam);
        Map<String, DisOwnerEquityChange001Dto> dtoMap = new HashMap<String, DisOwnerEquityChange001Dto>();
        initData(dtoMap);

        // 调用共通方法【详细设计-标的类共通方法设计】-核算主体查询
        RepProdInfo finProduct = repProdInfoMapper.selectByPrimaryKey(
            (String)pageParam.get("booksetId"));
        String thisTermVdate1 = (String)pageParam.get("thisTermVdate"); // 实收资本
        String thisTermVdate2 = (String)pageParam.get("thisTermVdate"); // 非实收资本
        String thisTermMdate = (String)pageParam.get("thisTermMdate");
        if (finProduct != null
            && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermVdate1)) > 0
            && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermMdate)) < 0) {
            thisTermVdate1 = DateUtil.format(finProduct.getVdate(),
                DateUtil.DEFAULT_DATE_FORMAT_EN);
        }

        String lastTermVdate1 = (String)pageParam.get("lastTermVdate"); // 实收资本
        String lastTermVdate2 = (String)pageParam.get("lastTermVdate"); // 非实收资本
        String lastTermMdate = (String)pageParam.get("lastTermMdate");
        if (finProduct != null
            && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermVdate1)) > 0
            && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermMdate)) < 0) {
            lastTermVdate1 = DateUtil.format(finProduct.getVdate(),
                DateUtil.DEFAULT_DATE_FORMAT_EN);
        }

        // ---------------------------------期初--------------------------------------------
        // 本期期初
        pageParam.put("earlysta", "01");
        pageParam.put("vdate1", thisTermVdate1);
        pageParam.put("vdate2", thisTermVdate2);
        List<DisOwnerEquityChange001Dto> thisTermData = ownerEquityChange001Mapper.selectAll(
            pageParam);
        // 上去期初
        pageParam.put("vdate1", lastTermVdate1);
        pageParam.put("vdate2", lastTermVdate2);
        List<DisOwnerEquityChange001Dto> lastTermData = ownerEquityChange001Mapper.selectAll(
            pageParam);
        // ---------------------------------本期--------------------------------------------
        // 本期期中
        pageParam.put("earlysta", "02");
        pageParam.put("vdate1", thisTermVdate1);
        pageParam.put("vdate2", thisTermVdate2);
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<DisOwnerEquityChange001Dto> thisTermData_end = ownerEquityChange001Mapper.selectAll(
            pageParam);

		String profit="";
        if(finProduct!=null)
		{
			 profit=finProduct.getProfitType();
		}

        //净值类型
        if(profit.equals(DictCodeList.收益类型_净值型.getValue())){
        	pageParam.put("profit", 01);
        }
        //货币类型
        else if (profit.equals(DictCodeList.收益类型_货币型.getValue())){
        	pageParam.put("profit", 03);
        }
        //计算期内发生额本期收入
        List<BigDecimal>  listAmtSr=ownerEquityChange001Mapper.selectAmt(pageParam);
        //计算期内发生额本期支出
        List<BigDecimal>  listAmtZc=ownerEquityChange001Mapper.selectAmtZc(pageParam);
        formatData(thisTermData_end, true, dtoMap, thisTermData,listAmtSr,listAmtZc,profit);
        // 上期其中
        pageParam.put("vdate1", lastTermVdate1);
        pageParam.put("vdate2", lastTermVdate2);
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        pageParam.put("startDate",DateUtil.addDay(lastTermVdate1, 1));
        List<DisOwnerEquityChange001Dto> lastTermData_end = ownerEquityChange001Mapper.selectAll(
            pageParam);
        //计算期内发生额本期收入
        List<BigDecimal>  lastListAmtSr=ownerEquityChange001Mapper.selectAmt(pageParam);
        //计算期内发生额本期支出
        List<BigDecimal>  lastListAmtZc=ownerEquityChange001Mapper.selectAmtZc(pageParam);
        formatData(lastTermData_end, false, dtoMap, lastTermData,lastListAmtSr,lastListAmtZc,profit);

        
        
        
        List<DisOwnerEquityChange001Dto> list = new ArrayList<DisOwnerEquityChange001Dto>();
        list.addAll(dtoMap.values());
        list.sort(new Comparator<DisOwnerEquityChange001Dto>() {

            @Override
            public int compare(DisOwnerEquityChange001Dto arg0, DisOwnerEquityChange001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        return list;
    }

    /**
     * 所有者权益配置
     * @param pageParam
     * @return
     * @throws Exception
     */
    public   List<DisValProfitQuery001Dto> selectListPz(Map<String, Object> pageParam)
            throws Exception {
    	  String thisTermVdate1 = (String)pageParam.get("thisTermVdate"); // 实收资本
          String thisTermVdate2 = (String)pageParam.get("thisTermVdate"); // 非实收资本
          String thisTermMdate = (String)pageParam.get("thisTermMdate");
          
          String lastTermVdate1 = (String)pageParam.get("lastTermVdate"); // 实收资本
          String lastTermVdate2 = (String)pageParam.get("lastTermVdate"); // 非实收资本
          String lastTermMdate = (String)pageParam.get("lastTermMdate");
          //查询表样
    	List<DisProfitCalculationDto>  profitCalculationDtoList=ownerEquityChange001Mapper.selectProfitCalculationDtoList();
        Map<String, DisProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream().collect(Collectors.toMap(DisProfitCalculationDto::getProjCode, Function.identity()));
        Map<String, DisValProfitQuery001Dto> dtoMap = initReportElement(profitCalculationMap);;

     // ---------------------------------期初--------------------------------------------
        // 本期期初
        pageParam.put("earlysta", "01");
        pageParam.put("vdate1", thisTermVdate1);
        pageParam.put("vdate2", thisTermVdate2);
        List<BokBalance> thisTermData = ownerEquityChange001Mapper.selectAll1(
            pageParam);
        // 上去期初
        pageParam.put("vdate1", lastTermVdate1);
        pageParam.put("vdate2", lastTermVdate2);
        List<BokBalance> lastTermData = ownerEquityChange001Mapper.selectAllBamt(
            pageParam);
        
        calValue(thisTermData, profitCalculationMap, true);
        calValue(lastTermData, profitCalculationMap, false);
        // ---------------------------------本期--------------------------------------------
        // 本期期中
        pageParam.put("earlysta", "02");
        pageParam.put("vdate1", thisTermVdate1);
        pageParam.put("vdate2", thisTermVdate2);
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<BokBalance> thisTermData_end = ownerEquityChange001Mapper.selectAllBamt(
        		pageParam);
        String startDate=(String) pageParam.get("startDate");
        //计算期内发生额本期收入
        List<BokBalance>  listAmtSr=ownerEquityChange001Mapper.selectAmtbAmt(pageParam);
        //计算期内发生额本期支出
        List<BokBalance>  listAmtZc=ownerEquityChange001Mapper.selectAmtZcbAmt(pageParam);

        thisTermData_end.addAll(listAmtSr);
        thisTermData_end.addAll(listAmtZc);
        
        // 上期其中
        pageParam.put("vdate1", lastTermVdate1);
        pageParam.put("vdate2", lastTermVdate2);
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        pageParam.put("startDate",DateUtil.addDay(lastTermVdate1, 1));
        List<BokBalance>  lastTermData_end = ownerEquityChange001Mapper.selectAllBamt(
            pageParam);
        //计算期内发生额本期收入
        List<BokBalance>  lastListAmtSr=ownerEquityChange001Mapper.selectAmtbAmt(pageParam);
        //计算期内发生额本期支出
        List<BokBalance>  lastListAmtZc=ownerEquityChange001Mapper.selectAmtZcbAmt(pageParam);
        
        lastTermData_end.addAll(lastListAmtSr);
        lastTermData_end.addAll(lastListAmtZc);
        
        
        // 计算本期
        calValue1(thisTermData_end, profitCalculationMap, true);
        // 计算上期
        calValue1(lastTermData_end, profitCalculationMap, false);
        // 画面赋值
        setValue(profitCalculationMap, dtoMap);
        
        List<DisValProfitQuery001Dto> list = new ArrayList<DisValProfitQuery001Dto>();
        list.addAll(dtoMap.values());	
        list.sort(new Comparator<DisValProfitQuery001Dto>() {

            @Override
            public int compare(DisValProfitQuery001Dto arg0, DisValProfitQuery001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        
        
    	return list;
    	
    }
    
   
    /**
     * 将计算结果复制到返回画面结果集
     * 
     * @param profitCalculationMap
     * @param dtoMap
     */
    public void setValue(Map<String, DisProfitCalculationDto> profitCalculationMap,
			Map<String, DisValProfitQuery001Dto> dtoMap) {
		for (String key : profitCalculationMap.keySet()) {
			if (dtoMap.containsKey(profitCalculationMap.get(key).getProjName())) {
				dtoMap.get(profitCalculationMap.get(key).getProjName()).setThisTermAmt(profitCalculationMap.get(key).getThisTermAmt());
				dtoMap.get(profitCalculationMap.get(key).getProjName()).setLastTermAmt(profitCalculationMap.get(key).getLastTermAmt());
			}
		}
	}
    
    /**
     * 计算利润表各项目值
     * 
     * @param termData
     * @param profitCalculationMap
     * @param thisTermFlag
     */
	public void calValue(List<BokBalance> termData, Map<String, DisProfitCalculationDto> profitCalculationMap, boolean thisTermFlag) {
		// 汇总项key集合
		List<String> keyList = new ArrayList<String>();
		
		// 汇总项key集合
		List<String> keyListTwo = new ArrayList<String>();
				
		// 汇总项key集合
		List<String> keyListThree = new ArrayList<String>();
		// 计算明细项目
		for (String key : profitCalculationMap.keySet()) {
			if(key.equals("6.1")){
				System.out.println(key);
			}
			DisProfitCalculationDto dto = profitCalculationMap.get(key);
			if (1== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyList.add(dto.getProjCode());
				continue;
			}
			if (2== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyListTwo.add(dto.getProjCode());
				continue;
			}
			if (3== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyListThree.add(dto.getProjCode());
				continue;
			}
			
			// 计算当期值
			BigDecimal termAmt = calcTermAmt(termData, dto);
			if (thisTermFlag) {
				profitCalculationMap.get(key).setThisTermAmt(profitCalculationMap.get(key).getThisTermAmt().add(termAmt));
			} else {
				profitCalculationMap.get(key).setLastTermAmt(profitCalculationMap.get(key).getLastTermAmt().add(termAmt));
			}
		}
		// 计算汇总项目
		Collections.sort(keyList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyList) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		// 计算汇总项目two
				Collections.sort(keyListTwo, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						return o2.length() - o1.length();
					}
					
				});
				for (String key : keyListTwo) {
					// 计算汇总项数值
					calcTotalTermAmt(profitCalculationMap, key);
				}
				// 计算汇总项目three
				Collections.sort(keyListThree, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						return o2.length() - o1.length();
					}
					
				});
				for (String key : keyListThree) {
					// 计算汇总项数值
					calcTotalTermAmt(profitCalculationMap, key);
				}
		
		
	}
	
	
	/**
     * 计算利润表各项目值
     * 
     * @param termData
     * @param profitCalculationMap
     * @param thisTermFlag
     */
	public void calValue1(List<BokBalance> termData, Map<String, DisProfitCalculationDto> profitCalculationMap, boolean thisTermFlag) {
		// 汇总项key集合
		List<String> keyList = new ArrayList<String>();
		// 汇总项key集合
		List<String> keyListTwo = new ArrayList<String>();
						
		// 汇总项key集合
		List<String> keyListThree = new ArrayList<String>();
		// 计算明细项目
		for (String key : profitCalculationMap.keySet()) {
			if(key.startsWith("6")){
				continue;
			}
			DisProfitCalculationDto dto = profitCalculationMap.get(key);
			if (1== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyList.add(dto.getProjCode());
				continue;
			}
			if (2== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyListTwo.add(dto.getProjCode());
				continue;
			}
			if (3== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyListThree.add(dto.getProjCode());
				continue;
			}
			
			// 计算当期值
			BigDecimal termAmt = calcTermAmt(termData, dto);
			if (thisTermFlag) {
				profitCalculationMap.get(key).setThisTermAmt(BigDecimal.ZERO.add(termAmt));
			} else {
				profitCalculationMap.get(key).setLastTermAmt(BigDecimal.ZERO.add(termAmt));
			}
		}
		// 计算汇总项目
		Collections.sort(keyList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyList) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		// 计算汇总项目two
		Collections.sort(keyListTwo, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyListTwo) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		// 计算汇总项目three
		Collections.sort(keyListThree, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyListThree) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		
		
	}

	/**
	 * 根据报表项目配置信息，计算项目值，目前计算公式只支持加减，且计算公式不能带左右括号
	 * @param termData
	 * @param dto
	 * @return
	 */
	private BigDecimal calcTermAmt(List<BokBalance> termData, DisProfitCalculationDto dto) {
		// 计算必要信息缺失，直接返回0
		if (termData == null || dto == null || termData.isEmpty() || 
				StringUtils.isEmpty(dto.getSubjectNo()) || StringUtils.isEmpty(dto.getFormula())) {
			return BigDecimal.ZERO;
		}
		BigDecimal termAmt = BigDecimal.ZERO;
		// 标点符号
		String regex = "\\p{Punct}";
		// 计算公式
		String formula = dto.getFormula();
		// 参与计算的字段
		String[] fieldArr = formula.split(regex);
		// 参与计算的运算符
		String punct = "";
		Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(formula);
        while(m.find()){  
        	punct = punct + m.group(0);
        }
        char[] punctArr = punct.toCharArray();
        try {
			for (BokBalance balance : termData) {
				if (balance.getSubjectNo().equals(dto.getSubjectNo())) {
					Method method = balance.getClass().getMethod("get" + fieldArr[0]);
					BigDecimal  obj=BigDecimal.ZERO;
					if(dto.getProjCode().equals("6.1")||
							dto.getProjCode().equals("6.2")||
							dto.getProjCode().equals("6.3")){
						if(balance.getBalFlag().equals("D")){
	                    	 obj =((BigDecimal)method.invoke(balance)).multiply(new  BigDecimal(-1));
						}else{
				             obj =(BigDecimal)method.invoke(balance);
						}
					}else if(dto.getProjCode().equals("7.3")){
						//赎回
	                   	 obj =((BigDecimal)method.invoke(balance)).multiply(new  BigDecimal(-1));
						}
					else{
			             obj =(BigDecimal)method.invoke(balance);
					}
			        termAmt = termAmt.add((BigDecimal)(obj == null ? BigDecimal.ZERO : obj));
			        for (int i = 0; i < punctArr.length; i ++) {
			        	Method method1 = balance.getClass().getMethod("get" + fieldArr[i + 1]);
			            Object obj1 = method1.invoke(balance);
			            if ('+' == punctArr[i]) {
			            	termAmt = termAmt.add((BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
			            }
			            if ('-' == punctArr[i]) {
			            	termAmt = termAmt.subtract((BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
			            }
			        }
				}
			}
        } catch (Exception e) {
		}
		return termAmt;
	}
	/**
	 * 根据报表项目配置信息，计算汇总项目值，目前计算公式只支持加减，且计算公式不能带左右括号
	 * @param termData
	 * @param dto
	 * @return
	 */
	private void calcTotalTermAmt(Map<String, DisProfitCalculationDto> profitCalculationMap, String parentKey) {
		DisProfitCalculationDto dto = profitCalculationMap.get(parentKey);
		BigDecimal thisTermAmt = BigDecimal.ZERO;
		BigDecimal lastTermAmt = BigDecimal.ZERO;
		try {
			// 汇总项计算公式
			String formula = dto.getFormula();
			// 参与运算的子项key
			StringBuilder childKeyBuilder = new StringBuilder();
			// 参与计算的运算符
			StringBuilder punctBuilder = new StringBuilder();
			char[] arr = formula.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if ('+' == arr[i] || '-' == arr[i]) {
					childKeyBuilder.append('@');
					punctBuilder.append(arr[i]);
				} else {
					childKeyBuilder.append(arr[i]);
				}
			}
			
	        char[] punctArr = punctBuilder.toString().toCharArray();
	        String[] childKeyArr = childKeyBuilder.toString().split("@");
	        thisTermAmt = profitCalculationMap.get(childKeyArr[0]).getThisTermAmt();
	        lastTermAmt = profitCalculationMap.get(childKeyArr[0]).getLastTermAmt();
	        for (int i = 0; i < punctArr.length; i ++) {
	        	if ('+' == punctArr[i]) {
	        		
	        		try {
						thisTermAmt = thisTermAmt.add(profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
					} catch (Exception e) {
						System.out.println(i);
						e.printStackTrace();
					}
	        		lastTermAmt = lastTermAmt.add(profitCalculationMap.get(childKeyArr[i + 1]).getLastTermAmt());
	        	}
	        	if ('-' == punctArr[i]) {
	        		thisTermAmt = thisTermAmt.subtract(profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
	        		lastTermAmt = lastTermAmt.subtract(profitCalculationMap.get(childKeyArr[i + 1]).getLastTermAmt());
	        	}
	        }
			profitCalculationMap.get(parentKey).setThisTermAmt(thisTermAmt);
			profitCalculationMap.get(parentKey).setLastTermAmt(lastTermAmt);
		} catch (Exception e) {
		}
	}
    /**
     * 初始化报表项
     * 
     * @param profitCalculationMap
     * @return
     */
    public Map<String, DisValProfitQuery001Dto> initReportElement(Map<String, DisProfitCalculationDto> profitCalculationMap) {
    	Map<String, DisValProfitQuery001Dto> retMap = new HashMap<String, DisValProfitQuery001Dto>();
    	DisValProfitQuery001Dto DisValProfitQuery001Dto = null; 
		for (String key : profitCalculationMap.keySet()) {
			DisProfitCalculationDto configDto = profitCalculationMap.get(key);
			// 是报表展示项
			if (DictCodeList.是否_是.getValue().equals(configDto.getShowFlg())) {
				if (!retMap.containsKey(configDto.getProjName())) {
					DisValProfitQuery001Dto = new DisValProfitQuery001Dto();
					DisValProfitQuery001Dto.setSortNum(configDto.getShowOrder());
					DisValProfitQuery001Dto.setProject(configDto.getProjName());
					DisValProfitQuery001Dto.setThisTermAmt(BigDecimal.ZERO);
					DisValProfitQuery001Dto.setLastTermAmt(BigDecimal.ZERO);
					retMap.put(configDto.getProjName(), DisValProfitQuery001Dto);
				}
			}
		}
		return retMap;
	}
    /**
     * 把科目余额数据转换为查询数据
     * 
     * @param dataList 科目余额信息
     * @param flag true-本期数据 false-上期数据
     * @param dtoMap 转换的dto数据
     * @see
     * @since 1.0
     */
    public void formatData(List<DisOwnerEquityChange001Dto> dataList, boolean flag,
                           Map<String, DisOwnerEquityChange001Dto> dtoMap,
                           List<DisOwnerEquityChange001Dto> dataList2,
                           List<BigDecimal>  listsr,
                           List<BigDecimal>  listzc,
                           String profit) {
        // ---------------------------------------五、期末所有者权益（产品净值）--------------------------------------------
        // 所有者权益
        BigDecimal qm_sszb = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal qm_wfplr = BigDecimal.ZERO;
        // 其他中和收益
        BigDecimal qm_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal qm_syzqy = BigDecimal.ZERO;
        // ---------------------------------------本期向产品份额持有人分配利润产生的产品净值变动（净值减少以“-”号填列） -
        // 应付利润-------------
        // 期初_应付利润
        BigDecimal bqxcpfe_wfplr_qc = BigDecimal.ZERO;
        // ---------------------------------------一、期初所有者权益（产品净值）--------------------------------------------
        // 实收资本
        BigDecimal qcsyzqy_sszb = BigDecimal.ZERO;
        // 未分配利润
        BigDecimal qcsyzqy_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal qcsyzqy_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal qcsyzqy_syzqy = BigDecimal.ZERO;
        //资产总计
        BigDecimal  zczj=BigDecimal.ZERO;
        //负债总计
        BigDecimal  fzzj=BigDecimal.ZERO;
        // 期初
        for (DisOwnerEquityChange001Dto dto2 : dataList2) {
            if (StringUtil.equals("400101", dto2.getSubjectNo())) {
                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(), dto2.getBalFlag())) {
                    // 期初 原币余额 + 原币结转余额贷方- 原币结转余额借方
                    qcsyzqy_sszb = qcsyzqy_sszb.add(dto2.getoAmt()).add(dto2.getoCoCAmt()).subtract(dto2.getoCoDAmt());
                }
                else {
                    // 期初 -1*原币余额 + 原币结转余额贷方- 原币结转余额借方
                    qcsyzqy_sszb = ArithmeticUtil.doAdd(qcsyzqy_sszb,
                        ArithmeticUtil.multiply(dto2.getoAmt(), BigDecimal.valueOf(-1)),
                        ArithmeticUtil.subtract(dto2.getoCoCAmt(), dto2.getoCoDAmt()));
                }
            }
           //资产总计
            else if(StringUtil.equals("100201",dto2.getSubjectNo())
            		||StringUtil.equals("100202",dto2.getSubjectNo())
            		||StringUtil.equals("103101",dto2.getSubjectNo())
            		||StringUtil.equals("105201",dto2.getSubjectNo())
            		||StringUtil.equals("1502300001",dto2.getSubjectNo())
            		||StringUtil.equals("1502300002",dto2.getSubjectNo())
            		||StringUtil.equals("1502400001",dto2.getSubjectNo())
            		||StringUtil.equals("1502400002",dto2.getSubjectNo())
            		||StringUtil.equals("1502500001",dto2.getSubjectNo())
            		||StringUtil.equals("1502500002",dto2.getSubjectNo())
            		||StringUtil.equals("120210",dto2.getSubjectNo())
            		||StringUtil.equals("120212",dto2.getSubjectNo())
            		||StringUtil.equals("120299",dto2.getSubjectNo())
            		||StringUtil.equals("1505100001",dto2.getSubjectNo())
            		||StringUtil.equals("1505100002",dto2.getSubjectNo())
            		||StringUtil.equals("1505300001",dto2.getSubjectNo())
            		||StringUtil.equals("1505300002",dto2.getSubjectNo())
            		||StringUtil.equals("1505400001",dto2.getSubjectNo())
            		||StringUtil.equals("1505400002",dto2.getSubjectNo())
            		||StringUtil.equals("1505400003",dto2.getSubjectNo())
            		||StringUtil.equals("1505500001",dto2.getSubjectNo())
            		||StringUtil.equals("1505500002",dto2.getSubjectNo())
            		||StringUtil.equals("1505500003",dto2.getSubjectNo())
            		||StringUtil.equals("1505500004",dto2.getSubjectNo())
            		||StringUtil.equals("1505990001",dto2.getSubjectNo())
            		||StringUtil.equals("1505990002",dto2.getSubjectNo())
            		||StringUtil.equals("1505990004",dto2.getSubjectNo())
            		||StringUtil.equals("120401",dto2.getSubjectNo())
            		||StringUtil.equals("120406",dto2.getSubjectNo())
            		||StringUtil.equals("120410",dto2.getSubjectNo())
            		||StringUtil.equals("1204200002",dto2.getSubjectNo())
            		||StringUtil.equals("1204200004",dto2.getSubjectNo())
            		||StringUtil.equals("1204200007",dto2.getSubjectNo())
            		){
            	zczj=zczj.add(dto2.getoAmt());
            }
            //负债总计
            else  if(StringUtil.equals("220210",dto2.getSubjectNo())
            		||StringUtil.equals("220212",dto2.getSubjectNo())	
            		||StringUtil.equals("220213",dto2.getSubjectNo())
            		||StringUtil.equals("220299",dto2.getSubjectNo())
            		||StringUtil.equals("220601",dto2.getSubjectNo())
            		||StringUtil.equals("220701",dto2.getSubjectNo())
            		||StringUtil.equals("220801",dto2.getSubjectNo())
            		||StringUtil.equals("223110",dto2.getSubjectNo())
            		||StringUtil.equals("2209710001",dto2.getSubjectNo())
            		||StringUtil.equals("2209710002",dto2.getSubjectNo())
            		||StringUtil.equals("2209710003",dto2.getSubjectNo())
            		||StringUtil.equals("2209710004",dto2.getSubjectNo())
            		||StringUtil.equals("223201",dto2.getSubjectNo())
            		||StringUtil.equals("2222030001",dto2.getSubjectNo())
            		||StringUtil.equals("2222030002",dto2.getSubjectNo())
            		||StringUtil.equals("2222040001",dto2.getSubjectNo())
            		||StringUtil.equals("2222040002",dto2.getSubjectNo())
            		||StringUtil.equals("2222050001",dto2.getSubjectNo())
            		||StringUtil.equals("2222050002",dto2.getSubjectNo())
            		||StringUtil.equals("2222060001",dto2.getSubjectNo())
            		||StringUtil.equals("2222060002",dto2.getSubjectNo())
            		||StringUtil.equals("2222070001",dto2.getSubjectNo())
            		||StringUtil.equals("2222070002",dto2.getSubjectNo())
            		||StringUtil.equals("2222080001",dto2.getSubjectNo())
            		||StringUtil.equals("2222080002",dto2.getSubjectNo())
            		||StringUtil.equals("2222080003",dto2.getSubjectNo())
            		||StringUtil.equals("2222080004",dto2.getSubjectNo())
            		||StringUtil.equals("2222080005",dto2.getSubjectNo())
            		||StringUtil.equals("2222080006",dto2.getSubjectNo())
            		){
            	fzzj=fzzj.add(dto2.getoAmt());
            }
            
//            if (StringUtil.equals(AssetLiabilityStatementEnum.所有者权益类_其他综合收益.getValue(),
//                dto2.getSubjectNo())) {
//                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(), dto2.getBalFlag())) {
//                    // 其他综合收益 = 原币余额
//                    qcsyzqy_qtzhsy = dto2.getoAmt();
//                }
//                else {
//                    // 其他综合收益 = -1*原币余额
//                    qcsyzqy_qtzhsy = ArithmeticUtil.multiply(dto2.getoAmt(),
//                        BigDecimal.valueOf(-1));
//                }
//            }
//            String subject = dto2.getSubjectNo().substring(0, 1);
//
//            if ("4".equals(subject)) {
//                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(), dto2.getBalFlag())) {
//                    qcsyzqy_syzqy = ArithmeticUtil.doAdd(qcsyzqy_syzqy, dto2.getoAmt());
//                }
//                else {
//                    qcsyzqy_syzqy = ArithmeticUtil.subtract(qcsyzqy_syzqy, dto2.getoAmt());
//                }
//            }
//
//            if (StringUtil.equals("2232", dto2.getSubjectNo())) {
//                // 本期 原币余额 + 原币结转余额贷方- 原币结转余额借方
//                bqxcpfe_wfplr_qc = ArithmeticUtil.doAdd(bqxcpfe_wfplr_qc, dto2.getoCAmt(),
//                    ArithmeticUtil.subtract(dto2.getoCoCAmt(), dto2.getoCoDAmt()));
//            }
            
        }
        // 所有者权益合计 = （报告期期初）资产总计 - 负债总计；
        qcsyzqy_syzqy=zczj.subtract(fzzj);
        //未分配利润 = 所有者权益合计 - 实收资本
        qcsyzqy_wfplr = qcsyzqy_syzqy.subtract(qcsyzqy_sszb==null?BigDecimal.ZERO:qcsyzqy_sszb);
        if (flag) {
            dtoMap.get("一、期初所有者权益（产品净值）").settIncomeFund(qcsyzqy_sszb);
            dtoMap.get("一、期初所有者权益（产品净值）").settUnQuarterProfit(qcsyzqy_wfplr);
            dtoMap.get("一、期初所有者权益（产品净值）").settOwnerEquityTot(qcsyzqy_syzqy);
            dtoMap.get("一、期初所有者权益（产品净值）").settOtherCompIncome(qcsyzqy_qtzhsy);
        }
        else {
            dtoMap.get("一、期初所有者权益（产品净值）").setyIncomeFund(qcsyzqy_sszb);
            dtoMap.get("一、期初所有者权益（产品净值）").setyUnQuarterProfit(qcsyzqy_wfplr);
            dtoMap.get("一、期初所有者权益（产品净值）").setyOwnerEquityTot(qcsyzqy_syzqy);
            dtoMap.get("一、期初所有者权益（产品净值）").setyOtherCompIncome(qcsyzqy_qtzhsy);
        }
        // ---------------------------------------二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）--------------------------------------------
        // 实收资本
        BigDecimal bqjyhd_sszb = BigDecimal.ZERO;
        // 未分配利润
        BigDecimal bqjyhd_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal bqjyhd_qtzhsy = BigDecimal.ZERO;
        //本期收入
        BigDecimal bqsy_wfplr=BigDecimal.ZERO;
        //本期支出
        BigDecimal bqzc_wfplr=BigDecimal.ZERO;
        // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_所有者权益 =
        // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-未分配利润+本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-其他综合收益
        //所有者权益合计 = 未分配利润；
        BigDecimal bqjyhd_syzqy = BigDecimal.ZERO;
        // ---------------------------------------三、本期产品份额交易产生的产品净值变动数--------------------------------------------------
        // -实收资本
        BigDecimal bqcpfe_sszb = BigDecimal.ZERO;
        // 应付利润(未分配利润)
        BigDecimal bqcpfe_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal bqcpfe_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal bqcpfe_syzqy = BigDecimal.ZERO;
 
        // ---------------------------------------三、本期产品份额交易产生的产品净值变动数
        // 1.产品认申购------------------------------------------
        // -实收资本
        BigDecimal cprsg_sszb = BigDecimal.ZERO;
        // 应付利润
        BigDecimal cprsg_wfplr = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal cprsg_syzqy = BigDecimal.ZERO;
        // ---------------------------------------三、本期产品份额交易产生的产品净值变动数
        // 2.产品赎回-------------------------------------------
        // -实收资本
        BigDecimal cpsh_sszb = BigDecimal.ZERO;
        // 应付利润
        BigDecimal cpsh_wfplr = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal cpsh_syzqy = BigDecimal.ZERO;
        // ---------------------------------------四、本期向产品份额持有人分配利润产生的产品净值变动--------------------------------------------------------
        // -实收资本
        // BigDecimal bqxcpfe_sszb_bq=BigDecimal.ZERO;
        // 应付利润
        BigDecimal bqxcpfe_wfplr_bq = BigDecimal.ZERO;
        // 所有者权益
        // BigDecimal bqxcpfe_syzqy_bq=BigDecimal.ZERO;
        // -实收资本
        BigDecimal bqxcpfe_sszb = BigDecimal.ZERO;
        // 应付利润
        BigDecimal bqxcpfe_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal bqxcpfe_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal bqxcpfe_syzqy = BigDecimal.ZERO;

        // --------------------------------------- 计算
        // --------------------------------------------------
        
        BigDecimal  ybdrfse_sr=BigDecimal.ZERO;
        for (DisOwnerEquityChange001Dto dto : dataList) {
            // ------------------二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）--------------
            // 实收资本
            bqjyhd_sszb = BigDecimal.ZERO;
//            // 本期收入
//            if (StringUtil.equals("601101", dto.getSubjectNo())
//                || StringUtil.equals("601103", dto.getSubjectNo())
//                || StringUtil.equals("601106", dto.getSubjectNo())
//                || StringUtil.equals("601110", dto.getSubjectNo())
//                || StringUtil.equals("610110", dto.getSubjectNo())
//                || StringUtil.equals("6111100004", dto.getSubjectNo())
//                || StringUtil.equals("6111100005", dto.getSubjectNo())
//                || StringUtil.equals("6111100006", dto.getSubjectNo())
//                || StringUtil.equals("6111100023", dto.getSubjectNo())
//                || StringUtil.equals("6111100024", dto.getSubjectNo())
//                || StringUtil.equals("6111100025", dto.getSubjectNo())
//                || StringUtil.equals("6111300001", dto.getSubjectNo())
//                || StringUtil.equals("6111300002", dto.getSubjectNo())
//                || StringUtil.equals("6111300004", dto.getSubjectNo())
//                || StringUtil.equals("6111300005", dto.getSubjectNo())
//                || StringUtil.equals("6111300006", dto.getSubjectNo())
//                || StringUtil.equals("6111300009", dto.getSubjectNo())
//                || StringUtil.equals("6111300022", dto.getSubjectNo())
//                || StringUtil.equals("6111300023", dto.getSubjectNo())
//                || StringUtil.equals("6111300024", dto.getSubjectNo())
//                || StringUtil.equals("6111309999", dto.getSubjectNo())
//                ) {
//            	ybdrfse_sr=ybdrfse_sr.add((dto.getTdyOCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCAmt()).subtract(dto.getTdyODAmt() == null ? BigDecimal.ZERO : dto.getTdyODAmt()));
//                // 原币当日发生额贷方-原币当日发生额借方+原币结转当日发生额借方 - 原币结转当日发生额贷方
////            	bqsy_wfplr = bqsy_wfplr.add(dto.getTdyOCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCAmt()).
////            			subtract(dto.getTdyODAmt() == null ? BigDecimal.ZERO : dto.getTdyODAmt())
////            			.add(dto.getTdyOCoDAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoDAmt()).subtract
////            					(dto.getTdyOCoCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoCAmt());
//
//            }// 本期支出
//            else if ( StringUtil.equals("640301", dto.getSubjectNo())
//                    || StringUtil.equals("640401", dto.getSubjectNo())
//                    || StringUtil.equals("640601", dto.getSubjectNo())
//                    || StringUtil.equals("640701", dto.getSubjectNo())
//                    || StringUtil.equals("641110", dto.getSubjectNo())
//                    || StringUtil.equals("660599", dto.getSubjectNo())
//                    || StringUtil.equals("6701010001", dto.getSubjectNo())){
//            	  // 原币当日发生额贷方-原币当日发生额借方+原币结转当日发生额借方 - 原币结转当日发生额贷方
//            	bqzc_wfplr = bqzc_wfplr.add(dto.getTdyOCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCAmt()).
//            			subtract(dto.getTdyODAmt() == null ? BigDecimal.ZERO : dto.getTdyODAmt()).
//            			add(dto.getTdyOCoDAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoDAmt()).subtract
//            					(dto.getTdyOCoCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoCAmt());
//            	
//            }
//            if (StringUtil.equals(AssetLiabilityStatementEnum.所有者权益类_其他综合收益.getValue(),
//                dto.getSubjectNo())) {
//                // 其他综合收益 = 原币当日发生额贷方-原币当日发生额借方
//                bqjyhd_qtzhsy = ArithmeticUtil.subtract(dto.getTdyOCAmt(), dto.getTdyODAmt());
//            }
            // ---------------------------------------三、本期产品份额交易产生的产品净值变动数--------------------------------------------------
            if (StringUtil.equals("400101", dto.getSubjectNo())) {
                // -------------------------三、本期产品份额交易产生的产品净值变动数_实收资本--------------------------
                // SUM（原币当日发生额贷方）
                cprsg_sszb = cprsg_sszb.add(dto.getTdyOCAmt()== null ? BigDecimal.ZERO : dto.getTdyOCAmt());
                // -1*SUM（原币当日发生额借方）
                cpsh_sszb = cpsh_sszb.add(dto.getTdyODAmt()== null ? BigDecimal.ZERO : dto.getTdyODAmt());

            }
            // -------------------------三、本期产品份额交易产生的产品净值变动数_未分配利润--------------------------
            
                // 原币申购未实现平准金贷
                cprsg_wfplr = cprsg_wfplr.add(dto.getTdyPurOCUlamt()== null ? BigDecimal.ZERO : dto.getTdyPurOCUlamt())
                		.subtract(dto.getTdyPurODUlamt()== null ? BigDecimal.ZERO : dto.getTdyPurODUlamt());
                // 原币赎回未实现平准金贷
                cpsh_wfplr = cpsh_wfplr.add(dto.getTdyRedOCUlamt()== null ? BigDecimal.ZERO : dto.getTdyRedOCUlamt())
                		.subtract(dto.getTdyRedODUlamt()== null ? BigDecimal.ZERO : dto.getTdyRedODUlamt());
           
            // ------------------------- 四、本期向产品份额持有人分配利润产生的产品净值变动
            // --------------------------------------------------
            if (StringUtil.equals("410402", dto.getSubjectNo())) {
                // 本期 原币余额 + 原币结转余额贷方- 原币结转余额借方
                bqxcpfe_wfplr_bq = bqxcpfe_wfplr_bq.add(dto.getTdyOCAmt()== null ? BigDecimal.ZERO : dto.getTdyOCAmt()
                		).add(dto.getoCoCAmt()== null ? BigDecimal.ZERO : dto.getoCoCAmt())
                				.subtract(dto.getoCoDAmt()== null ? BigDecimal.ZERO : dto.getoCoDAmt());
            }
        }
        if(profit.equals(DictCodeList.收益类型_净值型.getValue())){
        	//本期收入
            if(listsr!=null&&listsr.size()!=0){
            	bqsy_wfplr=(listsr.get(0)==null ? BigDecimal.ZERO:listsr.get(0)).add(listsr.get(1)==null ? BigDecimal.ZERO:listsr.get(1));
            }        
            //本期支出
            if(listzc!=null&&listzc.size()!=0){
            	bqzc_wfplr=(listzc.get(0)==null ? BigDecimal.ZERO:listzc.get(0)).add(listzc.get(1)==null ? BigDecimal.ZERO:listzc.get(1));
            }
        }else if(profit.equals(DictCodeList.收益类型_货币型.getValue())){
        	//本期收入
            if(listsr!=null&&listsr.size()!=0){
            	bqsy_wfplr=listsr.get(0)==null ? BigDecimal.ZERO:listsr.get(0);
            }
            //本期支出
            if(listzc!=null&&listzc.size()!=0){
            	bqzc_wfplr=listzc.get(0)==null ? BigDecimal.ZERO:listzc.get(0);
            }
        }
        
        //未分配利润 = 本期收入 + 本期支出；
        bqjyhd_wfplr=bqsy_wfplr.add(bqzc_wfplr);
        
        //所有者权益合计 = 未分配利润；
        bqjyhd_syzqy=bqjyhd_wfplr;
        
        // 本期-期初
        // bqxcpfe_wfplr = ArithmeticUtil.subtract(bqxcpfe_wfplr_bq, bqxcpfe_wfplr_qc);
        bqxcpfe_wfplr = bqxcpfe_wfplr_bq.subtract(BigDecimal.ZERO);
        // 此数据恒负数
        if (bqxcpfe_wfplr.compareTo(BigDecimal.ZERO) >= 0) {
            bqxcpfe_wfplr = bqxcpfe_wfplr.multiply(BigDecimal.valueOf(-1));
        }
        cpsh_sszb = cpsh_sszb.multiply(BigDecimal.valueOf(-1));
        // --------------------------------------- 赋值
        // --------------------------------------------------
        if (flag) {
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settIncomeFund(bqjyhd_sszb);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settUnQuarterProfit(bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settOtherCompIncome(bqjyhd_qtzhsy);
//            // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-未分配利润+本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-其他综合收益
//            bqjyhd_syzqy = ArithmeticUtil.doAdd(bqjyhd_wfplr, bqjyhd_qtzhsy);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settOwnerEquityTot(bqjyhd_syzqy);
            // 实收资本		
            bqcpfe_sszb = cprsg_sszb.add(cpsh_sszb);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").settIncomeFund(bqcpfe_sszb);
            dtoMap.get("1.产品认申购").settIncomeFund(cprsg_sszb);
            dtoMap.get("2.产品赎回").settIncomeFund(cpsh_sszb.multiply(BigDecimal.valueOf(-1)));
            // 未分配利润
            bqcpfe_wfplr = cpsh_wfplr.add(cprsg_wfplr);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").settUnQuarterProfit(bqcpfe_wfplr);
            dtoMap.get("1.产品认申购").settUnQuarterProfit(cprsg_wfplr);
            dtoMap.get("2.产品赎回").settUnQuarterProfit(cpsh_wfplr);
            // 所有者权益=实收资本+费分配利润
            bqcpfe_syzqy = bqcpfe_wfplr.add(bqcpfe_sszb);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").settOwnerEquityTot(bqcpfe_syzqy);

            cprsg_syzqy = cprsg_wfplr.add(cprsg_sszb);
            dtoMap.get("1.产品认申购").settOwnerEquityTot(cprsg_syzqy);

            cpsh_syzqy = cpsh_wfplr.add(cpsh_sszb);
            dtoMap.get("2.产品赎回").settOwnerEquityTot(cpsh_syzqy.multiply(BigDecimal.valueOf(-1)));

            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").settIncomeFund(bqxcpfe_sszb);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").settUnQuarterProfit(bqxcpfe_wfplr);
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_所有者权益 = 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_实收资本+
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_未分配利润
            bqxcpfe_syzqy = bqxcpfe_wfplr.add(bqxcpfe_sszb);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").settOwnerEquityTot(bqxcpfe_syzqy);
        }
        else {
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyIncomeFund(bqjyhd_sszb);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyUnQuarterProfit(bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyOtherCompIncome(bqjyhd_qtzhsy);
            // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-未分配利润+
            // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-其他综合收益
            bqjyhd_syzqy = bqjyhd_qtzhsy.add(bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyOwnerEquityTot(bqjyhd_syzqy);
            // 实收资本
            bqcpfe_sszb = cpsh_sszb.add(cprsg_sszb);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").setyIncomeFund(bqcpfe_sszb);
            dtoMap.get("1.产品认申购").setyIncomeFund(cprsg_sszb);
            dtoMap.get("2.产品赎回").setyIncomeFund(cpsh_sszb);
            // 未分配利润
            bqcpfe_wfplr = cpsh_wfplr.add(cprsg_wfplr);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").setyUnQuarterProfit(bqcpfe_wfplr);
            dtoMap.get("1.产品认申购").setyUnQuarterProfit(cprsg_wfplr);
            dtoMap.get("2.产品赎回").setyUnQuarterProfit(cpsh_wfplr);
            // 所有者权益=实收资本+费分配利润
            bqcpfe_syzqy = bqcpfe_wfplr.add(bqcpfe_sszb);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").setyOwnerEquityTot(bqcpfe_syzqy);

            cprsg_syzqy = cprsg_wfplr.add(cprsg_sszb);
            dtoMap.get("1.产品认申购").setyOwnerEquityTot(cprsg_syzqy);

            cpsh_syzqy = cpsh_wfplr.add(cpsh_sszb);
            dtoMap.get("2.产品赎回").setyOwnerEquityTot(cpsh_syzqy);

            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").setyIncomeFund(bqxcpfe_sszb);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").setyUnQuarterProfit(bqxcpfe_wfplr);
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_所有者权益 = 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_实收资本+
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_未分配利润
            bqxcpfe_syzqy = bqxcpfe_wfplr.add(bqxcpfe_sszb);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").setyOwnerEquityTot(bqxcpfe_syzqy);

        }
        qm_sszb = qm_sszb.add(qcsyzqy_sszb).add(bqjyhd_sszb).add(bqcpfe_sszb).add(bqxcpfe_sszb); 
           
        qm_wfplr = qm_wfplr.add(qcsyzqy_wfplr).add(bqjyhd_wfplr).add(bqcpfe_wfplr).add(bqxcpfe_wfplr);
         
        // ∑[期初所有者权益（产品净值）其他综合收益合计列+本期经营活动产生的产品净值变动数（本期净利润及其他综合收益）其他综合收益计列+
        // 本期产品份额交易产生的产品净值变动数其他综合收益合计列+本期向产品份额持有人分配利润产生的产品净值变动其他综合收益合计列]
        qm_qtzhsy = qm_qtzhsy.add(qcsyzqy_qtzhsy).add(bqjyhd_qtzhsy).add(bqcpfe_qtzhsy).add(bqxcpfe_qtzhsy);

        qm_syzqy = qm_syzqy.add(qcsyzqy_syzqy).add(bqjyhd_syzqy).add(bqcpfe_syzqy).add(bqxcpfe_syzqy);
           
        // 期末所有者权益-所有者权益合计

        if (flag) {
            dtoMap.get("五、期末所有者权益（产品净值）").settIncomeFund(qm_sszb);
            dtoMap.get("五、期末所有者权益（产品净值）").settUnQuarterProfit(qm_wfplr);
            dtoMap.get("五、期末所有者权益（产品净值）").settOtherCompIncome(qm_qtzhsy);
            dtoMap.get("五、期末所有者权益（产品净值）").settOwnerEquityTot(qm_syzqy);
        }
        else {
            dtoMap.get("五、期末所有者权益（产品净值）").setyIncomeFund(qm_sszb);
            dtoMap.get("五、期末所有者权益（产品净值）").setyUnQuarterProfit(qm_wfplr);
            dtoMap.get("五、期末所有者权益（产品净值）").setyOtherCompIncome(qm_qtzhsy);
            dtoMap.get("五、期末所有者权益（产品净值）").setyOwnerEquityTot(qm_syzqy);
        }

    }

    /**
     * 设置页面传回参数
     * 
     * @param pageParam
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam)
        throws Exception {
        String reportTermType = (String)pageParam.get("reportTermType");
        String valDate = DateUtil.format(analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
        int thisYear = DateUtil.getYear(valDate);
        int lastYear = thisYear - 1;
        int yearBeforeLast = thisYear - 2;
        String reportTerm = (String)pageParam.get("reportTerm");
        String lastDimen = (String)pageParam.get("lastDimen");
        if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_日报.getValue())) {

            pageParam.put("thisTermMdate", valDate); // 本期
            pageParam.put("thisTermVdate", DateUtil.addDay(valDate, -1));// 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate", DateUtil.addDay(valDate, -1)); // 上期本期
                pageParam.put("lastTermVdate", DateUtil.addDay(valDate, -2)); // 上期期初
            }
            else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(DateUtil.addYear(DateUtil.parse(valDate), -1),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(DateUtil.addDate(DateUtil.parse(valDate), -1, 0, -1),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_月报.getValue())) {
            pageParam.put("thisTermMdate", DateUtil.format(
                DateUtil.getLastDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN)); // 本期
            pageParam.put("thisTermVdate",
                DateUtil.format(
                    DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                    DateUtil.DEFAULT_DATE_FORMAT_EN)); // 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -2)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
            else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addYear(DateUtil.parse(valDate), -1)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addDate(DateUtil.parse(valDate), -1, -1, 0)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_季报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第一季度.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-03-31"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-12-31"); // 本期
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-03-31"); // 本期
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第二季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-03-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-06-30"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31"); // 期初
                    pageParam.put("lastTermMdate", thisYear + "-03-31"); // 本期
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-03-31"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-06-30"); // 本期
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第三季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-09-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-03-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-09-30");
                }
            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-09-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-06-30");
                    pageParam.put("lastTermMdate", thisYear + "-09-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_半年报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_上半年.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        }
        else {
            pageParam.put("thisTermVdate", lastYear + "-12-31");
            pageParam.put("thisTermMdate", thisYear + "-12-31");
            pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
            pageParam.put("lastTermMdate", lastYear + "-12-31");
        }
    }

    /**
     * 分析余额日期
     * 
     * @param pageParam 查询条件
     * @param balanceType 期末、年初
     * @return
     * @throws ParseException
     */
    public Date analyseDate(Map<String, Object> pageParam)
        throws ParseException {

        String reportTermType = (String)pageParam.get("reportTermType");
        String reportDate = (String)pageParam.get("valDate");
        String reportTerm = (String)pageParam.get("reportTerm");

        int year = 0;
        int month = -1;
        int day = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
        calendar.set(Calendar.MINUTE, 0);// 分
        calendar.set(Calendar.SECOND, 0);// 秒
        calendar.set(Calendar.MILLISECOND, 0);// 毫秒

        String[] strArr = reportDate.split(",")[0].split("-");
        if (DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)) {// 年底
            year = Integer.valueOf(strArr[0]);
            month += 12;
            day = 31;
        }
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.半年_上半年.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.季度_第一季度.getValue().equals(reportTerm)) {
                month += 3;
                day = 31;
            }
            else if (DictCodeListExt1.季度_第二季度.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else if (DictCodeListExt1.季度_第三季度.getValue().equals(reportTerm)) {
                month += 9;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)) {// 月底：当前月的下个月的第0天
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]) + 1;
            day = 0;
        }
        if (DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = Integer.valueOf(strArr[2]);
        }

        calendar.set(year, month, day);
        return calendar.getTime();

    }

    /**
     * 初始化数据
     * 
     * @param dtoMap
     * @return
     * @see
     * @since 1.0
     */
    public void initData(Map<String, DisOwnerEquityChange001Dto> dtoMap) {
        DisOwnerEquityChange001Dto newDto = new DisOwnerEquityChange001Dto();
        newDto.setSortNum(1);
        newDto.setProject("一、期初所有者权益（产品净值）");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("一、期初所有者权益（产品净值）", newDto);
        newDto = new DisOwnerEquityChange001Dto();

        newDto.setSortNum(2);
        newDto.setProject("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）", newDto);
        newDto = new DisOwnerEquityChange001Dto();

        newDto.setSortNum(3);
        newDto.setProject("三、本期产品份额交易产生的产品净值变动数");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("三、本期产品份额交易产生的产品净值变动数", newDto);
        newDto = new DisOwnerEquityChange001Dto();

        newDto.setSortNum(4);
        newDto.setProject("1.产品认申购");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("1.产品认申购", newDto);
        newDto = new DisOwnerEquityChange001Dto();

        newDto.setSortNum(5);
        newDto.setProject("2.产品赎回");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("2.产品赎回", newDto);
        newDto = new DisOwnerEquityChange001Dto();

        newDto.setSortNum(6);
        newDto.setProject("四、本期向产品份额持有人分配利润产生的产品净值变动");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        dtoMap.put("四、本期向产品份额持有人分配利润产生的产品净值变动", newDto);
        newDto = new DisOwnerEquityChange001Dto();

        newDto.setSortNum(7);
        newDto.setProject("五、期末所有者权益（产品净值）");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        dtoMap.put("五、期末所有者权益（产品净值）", newDto);
        newDto = new DisOwnerEquityChange001Dto();

    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param params
     * @return
     * @throws IOException
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params)
        throws Exception {
        ExportFileDto exportFileDto = createExcel(params);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    private ExportFileDto createExcel(Map<String, Object> params)
        throws Exception {
        // 报表日期
        String reportDate = DateUtil.format(analyseDate(params), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 导出数据集
        List<DisOwnerEquityChange001Dto> dtoList = selectList(params);
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = params.get("booksetId").toString() + "_" + reportDate + "_所有者权益变动表"
                          + ".xls";
        String tempName = "OwnerEquityChange001.xls";
        String tplFlodPath = "/accounadjustmanage/ownerequitychange/OwnerEquityChange001.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        //多级法人改造start add by hxy 20200619
        if(MultilevelDataAuthorityUtil.isEnabled()) {
        	 //联社模式,查询登录人的机构
        /*	String org =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            String orgName=CacheUtil.getAppCacheInstitutionCodeName(org).toString();
        	map.put("org", orgName);*/
        	 //联社模式
	       	 //根据选择的账套组或者账套，查询出它们的所属机构
	       	String booksetOrg;
	       	if ("02".equals((String)params.get("reportTerm"))) {
	            	booksetOrg=(String)params.get("booksetGroup");
		         String orgName=disValAssetBook001Mapper.selectAssociationModeOrgName(booksetOrg);
		         map.put("org", orgName);
		         
	       	}else{
	       		booksetOrg=(String)params.get("booksetId");
	       		String orgName=disValAssetBook001Mapper.selectAssociationModeOrgName(booksetOrg);
	           	map.put("org", orgName);
	       	}
        }else{
        	//银行模式
        	String orgName=disValAssetBook001Mapper.selectBankModeOrgName();
        	map.put("org", orgName);
        	
        }
        //多级法人改造end add by hxy 20200619
        map.put("dtoList", dtoList);
        map.put("reportDate", reportDate);
        map.put("reportTermType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型,
            params.get("reportTermType").toString()));
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);
        try {

            // 针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
            File templeteFile = new File(tplPath);
            // 文件存在情况,直接该路径获取文件流
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            // 文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
            else {
                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
            }

            ExcelTools.writeExcel(stream, tempName, map, outPath);
        }
        catch (IOException e) {
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException e) {
                }
            }
        }
        return exportFileDto;
    }
    
	
	/*** 计算利润表各项目值   add  by dongxiangyang  20200212 start*/
	public void calValue1Yth(List<BokBalance> termData, Map<String, DisProfitCalculationDto> profitCalculationMap, boolean thisTermFlag) {
		// 汇总项key集合
		List<String> keyList = new ArrayList<String>();
		// 汇总项key集合
		List<String> keyListTwo = new ArrayList<String>();
						
		// 汇总项key集合
		List<String> keyListThree = new ArrayList<String>();
		// 计算明细项目
		for (String key : profitCalculationMap.keySet()) {
			//这里是为了不去计算期初
			if(key.equals("6.1")||
					key.equals("6.2")||
					key.equals("6.3")||
					key.equals("6.4")||
					key.equals("6.7.20")
					){
				continue;
			}
			if(key.equals("6.7.1")){
				System.out.println(key);
			}
			DisProfitCalculationDto dto = profitCalculationMap.get(key);
			if (1== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyList.add(dto.getProjCode());
				continue;
			}
			if (2== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyListTwo.add(dto.getProjCode());
				continue;
			}
			if (3== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyListThree.add(dto.getProjCode());
				continue;
			}
			
			// 计算当期值
			BigDecimal termAmt = calcTermAmt(termData, dto);
			if (thisTermFlag) {
				profitCalculationMap.get(key).setThisTermAmt(BigDecimal.ZERO.add(termAmt));
			} else {
				profitCalculationMap.get(key).setLastTermAmt(BigDecimal.ZERO.add(termAmt));
			}
		}
		// 计算汇总项目
		Collections.sort(keyList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyList) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		// 计算汇总项目two
		Collections.sort(keyListTwo, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyListTwo) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		// 计算汇总项目three
		Collections.sort(keyListThree, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyListThree) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		
		
	}
	/*** 计算利润表各项目值   add  by dongxiangyang  20200212 start*/
    
	 /*** 所有者权益配置  add  by  dongxiangyang  20200212  start*/
    public   List<DisValProfitQuery001Dto> selectListPzYth(Map<String, Object> pageParam)
            throws Exception {
    	  String thisTermVdate1 = (String)pageParam.get("thisTermVdate"); // 实收资本
          String thisTermVdate2 = (String)pageParam.get("thisTermVdate"); // 非实收资本
          String thisTermMdate = (String)pageParam.get("thisTermMdate");
          
          String lastTermVdate1 = (String)pageParam.get("lastTermVdate"); // 实收资本
          String lastTermVdate2 = (String)pageParam.get("lastTermVdate"); // 非实收资本
          String lastTermMdate = (String)pageParam.get("lastTermMdate");
    	  String xzlastTermVdate1=(String)pageParam.get("xzlastTermVdate");

          //查询表样
    	List<DisProfitCalculationDto>  profitCalculationDtoList=ownerEquityChange001Mapper.selectProfitCalculationDtoListYth();
        Map<String, DisProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream().collect(Collectors.toMap(DisProfitCalculationDto::getProjCode, Function.identity()));
        Map<String, DisValProfitQuery001Dto> dtoMap = initReportElement(profitCalculationMap);;

     // ---------------------------------期初--------------------------------------------
        // 本期期初
        //查询产品起息日
        String prodId= (String)pageParam.get("booksetId");
        String valDate=(String)pageParam.get("startDate");
        String  vdate=ownerEquityChange001Mapper.getVdate(prodId);
        //定义查询期初实收资本的日期
        String  vdate2="";
        if(compareVdate1(vdate, valDate)){
     	  //如果为true。期初实收资本则取起息日
        	vdate2=vdate;
        }else {
        	//否则还是取期初-1
        	vdate2=thisTermVdate1;
        }
        pageParam.put("earlysta", "01");
        pageParam.put("vdate1", thisTermVdate1);
        pageParam.put("vdate2", vdate2);
        List<BokBalance> thisTermData = ownerEquityChange001Mapper.selectAll1Yth(
            pageParam);
        // 上期期初
        if(xzlastTermVdate1.equals(vdate)){
        	//如果上期期初为起息日
        	lastTermVdate1=xzlastTermVdate1;
        }
        pageParam.put("vdate1", lastTermVdate1);
        pageParam.put("vdate2", lastTermVdate2);
        List<BokBalance> lastTermData = ownerEquityChange001Mapper.selectAll1Yth(
            pageParam);
        //计算本期期初
        calValue(thisTermData, profitCalculationMap, true);
        //计算上期期初
        calValue(lastTermData, profitCalculationMap, false);
        // ---------------------------------本期--------------------------------------------
        String  qzvdate2="";
        if(compareVdate(vdate, valDate)){
        	//起息日大于开始日,期中实收资本取起息日加一天日期
        	qzvdate2= 	DateUtil.addDay(vdate,1);
        }else{
        	//否则还是取开始日减一天
        	qzvdate2=(String)pageParam.get("thisTermVdate");
        }
        // 本期期中
        pageParam.put("earlysta", "02");
        pageParam.put("vdate1", (String)pageParam.get("thisTermVdate"));
        pageParam.put("vdate2", qzvdate2);
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<BokBalance> thisTermData_end = ownerEquityChange001Mapper.selectAll1Yth(
        		pageParam);
//        String startDate=(String) pageParam.get("startDate");
//        //计算期内发生额本期收入
//        List<BokBalance>  listAmtSr=ownerEquityChange001Mapper.selectAmtbAmt(pageParam);
//        //计算期内发生额本期支出
//        List<BokBalance>  listAmtZc=ownerEquityChange001Mapper.selectAmtZcbAmt(pageParam);
//
//        thisTermData_end.addAll(listAmtSr);
//        thisTermData_end.addAll(listAmtZc);
        
        // 上期其中
        pageParam.put("vdate1", lastTermVdate1);
        pageParam.put("vdate2", lastTermVdate2);
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        pageParam.put("startDate",DateUtil.addDay(lastTermVdate1, 1));
        List<BokBalance>  lastTermData_end = ownerEquityChange001Mapper.selectAll1Yth(
            pageParam);
//        //计算期内发生额本期收入
//        List<BokBalance>  lastListAmtSr=ownerEquityChange001Mapper.selectAmtbAmt(pageParam);
//        //计算期内发生额本期支出
//        List<BokBalance>  lastListAmtZc=ownerEquityChange001Mapper.selectAmtZcbAmt(pageParam);
//        
//        lastTermData_end.addAll(lastListAmtSr);
//        lastTermData_end.addAll(lastListAmtZc);
        
        
        // 计算本期期中
        calValue1Yth(thisTermData_end, profitCalculationMap, true);
        // 计算上期期中
        calValue1Yth(lastTermData_end, profitCalculationMap, false);
        // 画面赋值
        setValue(profitCalculationMap, dtoMap);
        
        List<DisValProfitQuery001Dto> list = new ArrayList<DisValProfitQuery001Dto>();
        list.addAll(dtoMap.values());	
        list.sort(new Comparator<DisValProfitQuery001Dto>() {

            @Override
            public int compare(DisValProfitQuery001Dto arg0, DisValProfitQuery001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        
        
    	return list;
    	
    }
    /*** 所有者权益配置  add  by  dongxiangyang  20200212  end*/
    /**
     * vdate1大于等于vdate2则返回true
     * 判断俩个String类型的日期大小
     */
    public static boolean  compareVdate(String  vdate1,String vdate2){
    	boolean  flag=true;
        Date	ddate1=DateUtil.parse(vdate1);
        Date	ddate2=DateUtil.parse(vdate2);

        if(ddate1.compareTo(ddate2)>=0){
        	flag=true;
        }else {
        	flag=false;
        }
    	return flag;
    }
    /**
     * 
     * @param vdate1  起息日
     * @param vdate2  开始日
     * @return
     */
    public static boolean  compareVdate1(String  vdate1,String vdate2){
    	boolean  flag=true;
        Date	ddate1=DateUtil.parse(vdate1);
        Date	ddate2=DateUtil.parse(vdate2);
        Date	ddate3=DateUtil.addDay(ddate2, -1);
        
        if(ddate1.compareTo(ddate3)>=0){
        	flag=true;
        	//开始日-1小于等于起息日 返回true
        }else {
        	flag=false;
        }
    	return flag;
    }
    
}
