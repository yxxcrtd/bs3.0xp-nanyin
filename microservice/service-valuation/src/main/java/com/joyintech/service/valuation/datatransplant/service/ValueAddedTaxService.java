/*
 * 文件名：ValueAddedTaxService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.datatransplant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.service.SystemToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.valuation.datatransplant.dao.ValueAddedTaxMapper;
import com.joyintech.service.valuation.datatransplant.dto.TemplateCheckDto;
import com.joyintech.service.valuation.datatransplant.dto.ValueAddedTaxImpDto;
import com.joyintech.tams.common.base.dao.TaxBokBalanceMapper;
import com.joyintech.tams.common.base.dao.TaxBokBalanceSumMapper;
import com.joyintech.tams.common.base.dao.TaxBokDetailMapper;
import com.joyintech.tams.common.base.entity.TaxBokBalance;
import com.joyintech.tams.common.base.entity.TaxBokBalanceSum;
import com.joyintech.tams.common.base.entity.TaxBokDetail;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.DataTransplantCommonService;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 【增值税模版】对应service
 * 
 * @author yangjian
 * @version 1.0 2018年11月9日
 * @see ValueAddedTaxService
 * @since 1.0
 */
@Service(value="serviceValueAddedTaxService")
public class ValueAddedTaxService {

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;

    @Autowired
    private SystemToolService systemToolService;
    /**
     * taxBokBalanceMapper
     */
    @Autowired
    private TaxBokBalanceMapper taxBokBalanceMapper;

    /**
     * taxBokBalanceSumMapper
     */
    @Autowired
    private TaxBokBalanceSumMapper taxBokBalanceSumMapper;

    /**
     * taxBokDetailMapper
     */
    @Autowired
    private TaxBokDetailMapper taxBokDetailMapper;

    @Autowired
    private ValueAddedTaxMapper valueAddedTaxMapper;

    /**
     * 数据移植公用方法
     */
    @Autowired
    private DataTransplantCommonService dataTransplantCommonService;

    /**
     * 【增值税模版】导入数据数据操作 --- （主入口）
     * 
     * @param sheetDataList excel中读取到的数据
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public List<TemplateCheckDto> valueAddedTaxExec(List<List<String>> sheetDataList) {
        // 将excel中的数据转换为实体类
        List<ValueAddedTaxImpDto> dataList = this.convertToDto(sheetDataList);

        // 存储校验信息
        List<TemplateCheckDto> checkList = new ArrayList<>();
        // modify by chendong in 2018-11-22 start
        // 错误条数
        int number = 0;
        Map<String, Object> map = checkInfo(dataList, number, checkList);
        // modify by chendong in 2018-11-22 end
        checkList = (List<TemplateCheckDto>)map.get("checkList");
        number = (int)map.get("number");
        if (number == 0) {
            // 【增值税账务明细余额(TAX_BOK_BALANCE)】数据入库
            this.intoTaxBokBlance(dataList);

            // 【增值税汇总余额(TAX_BOK_BALANCE_SUM)】数据入库
            this.intoTaxBokBlanceSum(dataList);

            // 【增值税账务明细流水(TAX_BOK_DETAIL)】数据入库
            this.intoTaxBokDetail(dataList);
        }
        return checkList;
    }

    /**
     * 【增值税账务明细余额(TAX_BOK_BALANCE)】数据入库
     * 
     * @param dataList
     * @return
     * @see
     * @since 1.0
     */
    public int intoTaxBokBlance(List<ValueAddedTaxImpDto> dataList) {

        // 插入数据条数
        int sucessNum = 0;

        // 循环数据处理
        for (ValueAddedTaxImpDto dto : dataList) {
            // 定义变量
            String taxType = dto.getTaxType();// 税类型
            // String calcType = dto.getCalcType();// 计税方式

            // 过滤，排除一些税类型和计税方式
            if (!DictCodeList.税类型_城建税.getValue().equals(taxType)
                && !DictCodeList.税类型_教育费.getValue().equals(taxType)
                && !DictCodeList.税类型_地方教育费.getValue().equals(taxType)) {

                // 定义表实体类
                TaxBokBalance tableDate = new TaxBokBalance();
                // 流水
                tableDate.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
                // 账套代码
                tableDate.setBooksetId(dto.getBookSetId());
                // 账套日期
                tableDate.setBooksetDate(dto.getImpDate());
                // 账务明细代码
                tableDate.setBokDetailId(dataTransplantCommonService.getBookDetailId(
                    dto.getBusiType(), dto.getBusiId(), null, dto.getInvAim(),dto.getCcy()));
                // 发生日期
                tableDate.setHappenDate(dto.getImpDate());
                // 入账日期
                tableDate.setBookDate(dto.getImpDate());
                // 币种
                tableDate.setoCcy(dto.getCcy());
                // 增值税类型
                tableDate.setVatType(dto.getTaxType());
                // 确认标识
                tableDate.setTaxPType(dto.getConfrim());
                // 计税方式
                tableDate.setCalcType(dto.getCalcType());
                // 发生场景
                tableDate.setIncomeSource(dto.getBalanceType());
                // 计税收入余额
                tableDate.setIncomeAmtAll(dto.getSaleAmt());
                // 当日计税收入发生额
                tableDate.setIncomeHappenAmt(dto.getSaleAmt());
                // 当日结转累加发生额
                tableDate.setCarryAmt(BigDecimal.ZERO);
                // 当日发生额
                tableDate.setHappenAmt(dto.getTaxAmt());
                // 增值税金额
                tableDate.setTaxAmt(dto.getTaxAmt());
                // 处理序列
                tableDate.setBookOrder(BigDecimal.ONE);

                // 创建用户
                tableDate.setCreateUser(GlobalConstant.数据移植账户);
                // 更新用户
                tableDate.setUpdateUser(GlobalConstant.数据移植账户);

                // 数据库入库
                sucessNum = sucessNum + taxBokBalanceMapper.insert(tableDate);
            }
        }

        return sucessNum;
    }

    /**
     * 【增值税汇总余额(TAX_BOK_BALANCE_SUM)】数据入库
     * 
     * @param dataList
     * @return
     * @see
     * @since 1.0
     */
    public int intoTaxBokBlanceSum(List<ValueAddedTaxImpDto> dataList) {

        // 插入数据条数
        int sucessNum = 0;
        
        /**add by wujie on 20181205 start remark：本身数据汇总**/
        Map<String,ValueAddedTaxImpDto> map = new HashMap<String,ValueAddedTaxImpDto>();
        /**add by wujie on 20181213 start remark:处理手工记账**/
        Map<String,ValueAddedTaxImpDto> paramsMap = new HashMap<String,ValueAddedTaxImpDto>();
        /**add by wujie on 20181213 start remark:处理手工记账**/
        for (ValueAddedTaxImpDto dto : dataList) {
        	if (!DictCodeList.计税方式_免税.getValue().equals(dto.getCalcType())
                    && !DictCodeList.计税方式_不征税.getValue().equals(dto.getCalcType())) {
        		//账套代码 + 日期 + 币种 + 增值税类型 + 确认标识
            	String key = dto.getBookSetId()+
            			DateUtil.format(dto.getImpDate())+
            			dto.getCcy()+
            			dto.getTaxType()+
            			dto.getConfrim();
            	ValueAddedTaxImpDto object = map.get(key);
            	if(object==null){
            		object = dto.clone();
            	}else{
            		object.setSaleAmt(ArithmeticUtil.doAdd(14,RoundingMode.HALF_UP,object.getSaleAmt(), dto.getSaleAmt()));
            		object.setTaxAmt(ArithmeticUtil.doAdd(14,RoundingMode.HALF_UP,object.getTaxAmt(), dto.getTaxAmt()));
            	}
            	/**add by wujie on 20181213 start remark:处理手工凭证**/
            	if("MA".equals(dto.getCalcType())){
            		ValueAddedTaxImpDto objectMa = paramsMap.get(key);
                	if(objectMa==null){
                		objectMa = dto.clone();
                	}else{
                		objectMa.setSaleAmt(ArithmeticUtil.doAdd(14,RoundingMode.HALF_UP,objectMa.getSaleAmt(), dto.getSaleAmt()));
                		objectMa.setTaxAmt(ArithmeticUtil.doAdd(14,RoundingMode.HALF_UP,objectMa.getTaxAmt(), dto.getTaxAmt()));
                	}
                	paramsMap.put(key, objectMa);
            	}
            	/**add by wujie on 20181213 end**/
            	map.put(key, object);
        	}
        }
        
        /**add by wujie on 20181205 end**/
        // 循环数据处理
        /** modify by wujie on 20181205 start
         * for (ValueAddedTaxImpDto dto : dataList) {*/
        for(String key :map.keySet()){
        	ValueAddedTaxImpDto dto = map.get(key);
        /**modify by wujie on 20181205 end**/
            // 定义变量
            // String taxType = dto.getTaxType();// 税类型
            String calcType = dto.getCalcType();// 计税方式

            // 过滤，排除一些税类型和计税方式
            if (!DictCodeList.计税方式_免税.getValue().equals(calcType)
                && !DictCodeList.计税方式_不征税.getValue().equals(calcType)) {

                // 定义表实体类
                TaxBokBalanceSum tableDate = new TaxBokBalanceSum();
                // 流水
                tableDate.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
                // 账套代码
                tableDate.setBooksetId(dto.getBookSetId());
                // 账套日期
                tableDate.setBooksetDate(dto.getImpDate());
                // 发生日期
                tableDate.setHappenDate(dto.getImpDate());
                // 入账日期
                tableDate.setBookDate(dto.getImpDate());
                // 币种
                tableDate.setoCcy(dto.getCcy());
                // 增值税类型
                tableDate.setVatType(dto.getTaxType());
                // 确认标识
                tableDate.setTaxPType(dto.getConfrim());

                Map<String, Object> sqlMap = new HashMap<String, Object>();
                sqlMap.put("booksetId", dto.getBookSetId());
                sqlMap.put("impDate", dto.getImpDate());
                sqlMap.put("ccy", dto.getCcy());
                sqlMap.put("taxType", dto.getTaxType());
                sqlMap.put("confrim", dto.getConfrim());

                // 计税收入余额(按照相同的【账套代码,日期,币种,税类型,确认标识】汇总)
                BigDecimal tamAmtNow = valueAddedTaxMapper.SumIncomeAmtAllForTaxBokBlanceSum(
                    sqlMap);
                
                /**add by wujie on 20181213 start remark:处理手工凭证**/
                ValueAddedTaxImpDto dtoMa = paramsMap.get(key);
                if(dtoMa!=null){
                	tamAmtNow = ArithmeticUtil.subtract(tamAmtNow, dtoMa.getSaleAmt(),14,RoundingMode.HALF_UP);
                }
                /**add by wujie on 20181213 end**/
                
                tableDate.setIncomeAmtAll(ArithmeticUtil.doAdd(14,RoundingMode.HALF_UP,dto.getSaleAmt(), tamAmtNow));
                // 当日发生额(按照相同的【账套代码,日期,币种,税类型,确认标识】汇总)
                BigDecimal happenAmtNow = valueAddedTaxMapper.SumHappenAmtForTaxBokBlanceSum(
                    sqlMap);
                
                /**add by wujie on 20181213 start remark:处理手工凭证**/
                if(dtoMa!=null){
                	happenAmtNow = ArithmeticUtil.subtract(happenAmtNow, dtoMa.getTaxAmt(),14,RoundingMode.HALF_UP);
                }
                /**add by wujie on 20181213 end**/
                BigDecimal happenAmt = ArithmeticUtil.doAdd(14,RoundingMode.HALF_UP,dto.getTaxAmt(), happenAmtNow);
                happenAmt = happenAmt.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : happenAmt;
                tableDate.setHappenAmt(happenAmt);
                
                // 计税发生额汇总(按照相同的【账套代码,日期,币种,税类型,确认标识】汇总)
                if (DictCodeList.税类型_价差收入.getValue().equals(dto.getTaxType())) {
                	sqlMap.put("taxType", DictCodeList.税类型_价差收入.getValue());
	                BigDecimal taxAmtSumtNow = valueAddedTaxMapper.SumTaxAmtSumForTaxBokBlanceSum(
	                    sqlMap);
	                tableDate.setTaxAmtSum(ArithmeticUtil.doAdd(14,RoundingMode.HALF_UP,dto.getTaxAmt(), taxAmtSumtNow));
                } else {
                	tableDate.setTaxAmtSum(BigDecimal.ZERO);
                }
                // 增值税金额
                BigDecimal taxAmt = dto.getTaxAmt() ==null ? BigDecimal.ZERO : dto.getTaxAmt();
                taxAmt = taxAmt.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : taxAmt;
                tableDate.setTaxAmt(taxAmt);
                // 处理序列
                tableDate.setBookOrder(BigDecimal.ONE);

                // 创建用户
                tableDate.setCreateUser(GlobalConstant.数据移植账户);
                // 更新用户
                tableDate.setUpdateUser(GlobalConstant.数据移植账户);

                // 数据库入库（此处涉及到汇总，故一条一条数据插入）
                sucessNum = sucessNum + taxBokBalanceSumMapper.insert(tableDate);
            }
        }

        return sucessNum;
    }

    /**
     * 【增值税账务明细流水(TAX_BOK_DETAIL)】数据入库
     * 
     * @param dataList
     * @return
     * @see
     * @since 1.0
     */
    public int intoTaxBokDetail(List<ValueAddedTaxImpDto> dataList) {

        // 插入数据条数
        int sucessNum = 0;

        // 循环数据处理
        for (ValueAddedTaxImpDto dto : dataList) {
            // 定义变量
            String taxType = dto.getTaxType();// 税类型
            // String calcType = dto.getCalcType();// 计税方式

            // 过滤，排除一些税类型和计税方式
            if (!DictCodeList.税类型_城建税.getValue().equals(taxType)
                && !DictCodeList.税类型_教育费.getValue().equals(taxType)
                && !DictCodeList.税类型_地方教育费.getValue().equals(taxType)) {

                // 定义表实体类
                TaxBokDetail tableDate = new TaxBokDetail();
                // 流水
                tableDate.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
                // 账套代码
                tableDate.setBooksetId(dto.getBookSetId());
                // 账套日期
                tableDate.setBooksetDate(dto.getImpDate());
                // 账务明细流程
                tableDate.setBokSeqno(null);
                // 账务明细代码
                tableDate.setBokDetailId(dataTransplantCommonService.getBookDetailId(
                    dto.getBusiType(), dto.getBusiId(), null, dto.getInvAim(),dto.getCcy()));
                // 发生日期
                tableDate.setHappenDate(dto.getImpDate());
                // 入账日期
                tableDate.setBookDate(dto.getImpDate());
                // 凭证编号
                tableDate.setBookId(null);
                // 处理序列
                tableDate.setBookOrder(BigDecimal.ONE);
                // 币种
                tableDate.setoCcy(dto.getCcy());
                // 发生含税销售额
                tableDate.setHappenAmt(dto.getSaleAmt());
                // 当日计税发生额
                tableDate.setTaxAmt(dto.getTaxAmt());
                // 计税余额发生额
                tableDate.setBalAmt(dto.getTaxAmt());
                // 当日结转发生额
                tableDate.setCarryAmt(BigDecimal.ZERO);
                // 增值税类型
                tableDate.setVatType(dto.getTaxType());
                // 确认标识
                tableDate.setTaxPType(dto.getConfrim());
                // 计税方式
                tableDate.setCalcType(dto.getCalcType());
                // 税率
                tableDate.setTaxRate(dto.getTaxRate());
                // 税则代码
                tableDate.setTaxRegId(null);
                // 免税收入类型
                tableDate.setFeeType(dto.getFeeType());
                // 发生场景
                tableDate.setIncomeSource(dto.getBalanceType());
                // 结转区截止日
                tableDate.setMdate(this.getCurrentQuarterEndTime(dto.getImpDate()));

                // 创建用户
                tableDate.setCreateUser(GlobalConstant.数据移植账户);
                // 更新用户
                tableDate.setUpdateUser(GlobalConstant.数据移植账户);

                // 数据库入库
                sucessNum = sucessNum + taxBokDetailMapper.insert(tableDate);
            }
        }

        return sucessNum;
    }

    /**
     * 将excel中的数据转换为实体类
     * 
     * @param sheetDataList
     * @return
     * @see
     * @since 1.0
     */
    public List<ValueAddedTaxImpDto> convertToDto(List<List<String>> sheetDataList) {
        // 定义返回实体类
        List<ValueAddedTaxImpDto> dtoList = new ArrayList<ValueAddedTaxImpDto>();

        // 循环进行数据处理
        for (List<String> row : sheetDataList) {
            // 定义实体类
            ValueAddedTaxImpDto dto = new ValueAddedTaxImpDto();
            // 塞值
            dto.setBookSetId(row.get(0) == "" ? null : row.get(0).trim());// 账套代码
            dto.setBusiId(row.get(1) == "" ? null : row.get(1).trim());// 业务明细代码
            dto.setBusiType(
                row.get(2) == "" ? null : row.get(2).trim().split(GlobalConstant.分号)[0]);// 业务明细类型
            dto.setImpDate(row.get(3) == "" ? null : DateUtil.parse(row.get(3).trim()));// 日期
            
            /**
             * add by wujie on 20181211 start remark:模板增加一列【投资目的】
             */
            int colnum = 4;
            String invAim =row.get(colnum) == "" ? null : row.get(colnum).trim().split(GlobalConstant.分号)[0];
            dto.setInvAim(invAim);
            
            /**add by wujie on 20181211 end**/
            
            
            dto.setCcy(row.get(colnum+1) == "" ? null : row.get(colnum+1).trim());// 币种
            
            dto.setSaleAmt(row.get(colnum+2) == "" ? null : ArithmeticUtil.round(new BigDecimal(row.get(colnum+2).trim()),14,RoundingMode.HALF_UP));// 含税销售额
            
            
            dto.setTaxAmt(row.get(colnum+3) == "" ? null : ArithmeticUtil.round(new BigDecimal(row.get(colnum+3).trim()),14,RoundingMode.HALF_UP));// 计税余额
            
            dto.setTaxType(
                row.get(colnum+4) == "" ? null : row.get(colnum+4).trim().split(GlobalConstant.分号)[0]);// 税类型
            
            dto.setConfrim(
                row.get(colnum+5) == "" ? null : row.get(colnum+5).trim().split(GlobalConstant.分号)[0]);// 确认标识
            
            dto.setCalcType(
                row.get(colnum+6) == "" ? null : row.get(colnum+6).trim().split(GlobalConstant.分号)[0]);// 计税方式
            
            dto.setTaxRate(row.get(colnum+7) == "" ? null : new BigDecimal(row.get(colnum+7).trim()));// 税率
            
            dto.setFeeType(
                row.get(colnum+8) == "" ? null : row.get(colnum+8).trim().split(GlobalConstant.分号)[0]);// 免税收入类型
            
            dto.setBalanceType(
                row.get(colnum+9) == "" ? null : row.get(colnum+9).trim().split(GlobalConstant.分号)[0]);// 余额类型

            // 塞入list
            dtoList.add(dto);

        }

        return dtoList;
    }

    /**
     * 字符串时间转换为Date
     * 
     * @param DateString 【2018/11/2】格式字符串
     * @return
     * @see
     * @since 1.0
     */
    public Date DateStringToDate(String DateString) {
        // 定义返回时间
        Date resultDate = null;

        // 传递字符串不为空，进行时间转换
        if (DateString != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                resultDate = formatter.parse(DateString);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return resultDate;
    }

    /**
     * 获取所传时间所在季度的最后一天
     * 
     * @param dateNow
     * @return
     * @see
     * @since 1.0
     */
    public Date getCurrentQuarterEndTime(Date dateNow) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        int quarter = calendar.get(Calendar.MONTH) / 3 + 1;

        // 设置所在季度最后一个月
        if (quarter == 1) {// 第1季度
            calendar.set(Calendar.MONTH, 2);
        }
        else if (quarter == 2) {// 第2季度
            calendar.set(Calendar.MONTH, 5);
        }
        else if (quarter == 3) {// 第3季度
            calendar.set(Calendar.MONTH, 8);
        }
        else if (quarter == 4) {// 第4季度
            calendar.set(Calendar.MONTH, 11);
        }

        // 设置当月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        // 设置时间为24点
        calendar.set(Calendar.HOUR_OF_DAY, 24);

        // 获取时间
        Date CurrentQuarterEndTime = calendar.getTime();

        return CurrentQuarterEndTime;
    }

    private Map<String, Object> checkInfo(List<ValueAddedTaxImpDto> dataList, int number,
                                          List<TemplateCheckDto> checkList) {
        Map<String, Object> map = new HashMap<>();
        String typeError = "错误";
        // String typeWarn = "警告";
        for (int i = 0; i < dataList.size(); i++ ) {
            // 判断是否有错误
            boolean error = false;
            // 校验账套表中是否存在该账套
            if (StringUtil.isNotEmpty(dataList.get(i).getBookSetId())) {
                boolean flag = dataTransplantCommonService.checkBooksetId(
                    dataList.get(i).getBookSetId());
                if (!flag) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage("账套表中不存在该账套");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            // 校验对应的标的是否存在
            if (StringUtil.isNotEmpty(dataList.get(i).getBusiId())
                && StringUtil.isNotEmpty(dataList.get(i).getBusiType())) {
            	
            	Map<String,Object> params = new HashMap<String,Object>();
            	params.put("finprodId", dataList.get(i).getBusiId());
            	params.put("finprodType", dataList.get(i).getBusiType());
            	params.put("booksetId", dataList.get(i).getBookSetId());
            	
                boolean flag = dataTransplantCommonService.checkUnderlying(params);
                if (!flag) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage("对应的标的不存在");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getBookSetId())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("账套代码不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getBusiId())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("业务明细代码不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getBusiType())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("业务明细类型不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            /** 
             * add by wujie on 20181211 start
             * 业务明细类型为 F01 债券类型 时 投资目的不能为空
             */
            if(DictCodeListExt1.金融产品类型_债券.getValue().equals(dataList.get(i).getBusiType())){
            	if (!StringUtil.isNotEmpty(dataList.get(i).getInvAim())) {
            		 TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                     templateCheckDto.setErrorLineNum(i + 2);
                     templateCheckDto.setErrorType(typeError);
                     templateCheckDto.setErrorMessage("业务明细类型为[F01:债券]时，投资目的不能为空");
                     checkList.add(templateCheckDto);
                     error = true;
            	}
            }else{
            	if (StringUtil.isNotEmpty(dataList.get(i).getInvAim())) {
           		 	TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage("业务明细类型非[F01:债券]时，投资目的必须为空");
                    checkList.add(templateCheckDto);
                    error = true;
            	}
            }
            /**add by wujie on 20181211 end**/
            
            if (dataList.get(i).getImpDate() == null) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("日期不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getCcy())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("币种不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getTaxAmt())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("计税余额不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getTaxType())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("税类型不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getConfrim())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("确认标识不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dataList.get(i).getFeeType())) {
                if (DictCodeList.计税方式_免税.getValue().equals(dataList.get(i).getCalcType())) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage("当“计税方式”为”免税“时，”免税收入类型“不能为空");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            // 部分必须校验
            // 税类型
            String taxType = dataList.get(i).getTaxType();
            if (DictCodeList.税类型_利息收入.getValue().equals(taxType)
                || DictCodeList.税类型_红利收入.getValue().equals(taxType)
                || DictCodeList.税类型_价差收入.getValue().equals(taxType)) {
                if (!StringUtil.isNotEmpty(dataList.get(i).getSaleAmt())) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage("增值税时，含税销售额不能为空");
                    checkList.add(templateCheckDto);
                    error = true;
                }
                if (!StringUtil.isNotEmpty(dataList.get(i).getCalcType())) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage("增值税时，计税方式不能为空");
                    checkList.add(templateCheckDto);
                    error = true;
                }
                // if (!StringUtil.isNotEmpty(dataList.get(i).getFeeType())) {
                // TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                // templateCheckDto.setErrorLineNum(i + 2);
                // templateCheckDto.setErrorType(typeError);
                // templateCheckDto.setErrorMessage("增值税时，免税收入类型不能为空");
                // checkList.add(templateCheckDto);
                // error = true;
                // }
                if (DictCodeList.增值税_确认类型_预估计税.getValue().equals(dataList.get(i).getConfrim())
                		/**add by wujie on 20181213 start remark:处理手工凭证**/
                		&& !"MA".equals(dataList.get(i).getCalcType())
                		/**add by wujie on 20181213 end**/
                				) {
                    if (!StringUtil.isNotEmpty(dataList.get(i).getBalanceType())) {
                        TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                        templateCheckDto.setErrorLineNum(i + 2);
                        templateCheckDto.setErrorType(typeError);
                        templateCheckDto.setErrorMessage("增值税且预估时，余额类型不能为空");
                        checkList.add(templateCheckDto);
                        error = true;
                    }
                }
            }
            // 校验枚举字段是否有效
            if (StringUtil.isNotEmpty(dataList.get(i).getBusiType())) {
                DataDictItem from = systemToolService.getDataDictItem(DictCode.金融产品类型.getValue(),
                    dataList.get(i).getBusiType());
                DataDictItem from2 = systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(),
                    dataList.get(i).getBusiType());
                if (!StringUtil.isNotEmpty(from) && !StringUtil.isNotEmpty(from2)) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage(
                        "业务明细类型枚举值：" + dataList.get(i).getBusiType() + "无效");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (StringUtil.isNotEmpty(dataList.get(i).getTaxType())) {
                DataDictItem from = systemToolService.getDataDictItem(DictCode.税类型.getValue(), dataList.get(i).getTaxType());
                if (!StringUtil.isNotEmpty(from)) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage(
                        "税类型枚举值：" + dataList.get(i).getBusiType() + "无效");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (StringUtil.isNotEmpty(dataList.get(i).getConfrim())) {
                DataDictItem from = systemToolService.getDataDictItem(DictCode.增值税_确认类型.getValue(),
                    dataList.get(i).getConfrim());
                if (!StringUtil.isNotEmpty(from)) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage(
                        "确认标识枚举值：" + dataList.get(i).getBusiType() + "无效");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (StringUtil.isNotEmpty(dataList.get(i).getCalcType())) {
            	/**add by wujie on 20181213 start remark:处理手工凭证**/
            	if(!"MA".equals(dataList.get(i).getCalcType())){
            	/**add by wujie on 20181213 end remark:处理手工凭证**/
            	    DataDictItem from = systemToolService.getDataDictItem(DictCode.计税方式.getValue(),
                            dataList.get(i).getCalcType());
                        if (!StringUtil.isNotEmpty(from)) {
                            TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                            templateCheckDto.setErrorLineNum(i + 2);
                            templateCheckDto.setErrorType(typeError);
                            templateCheckDto.setErrorMessage(
                                "计税方式枚举值:" + dataList.get(i).getCalcType() + "无效");
                            checkList.add(templateCheckDto);
                            error = true;
                        }
            	}
                
            }
            if (StringUtil.isNotEmpty(dataList.get(i).getFeeType())) {
                DataDictItem from = systemToolService.getDataDictItem(DictCode.免税收入类型.getValue(),
                    dataList.get(i).getFeeType());
                if (!StringUtil.isNotEmpty(from)) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage(
                        "免税收入类型枚举值:" + dataList.get(i).getFeeType() + "无效");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (StringUtil.isNotEmpty(dataList.get(i).getBalanceType())) {
                DataDictItem from = systemToolService.getDataDictItem(DictCode.余额类型.getValue(),
                    dataList.get(i).getBalanceType());
                if (!StringUtil.isNotEmpty(from)) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage(
                        "余额类型枚举值:" + dataList.get(i).getBalanceType() + "无效");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (error) {
                number++ ;
            }
        }

        map.put("checkList", checkList);
        map.put("number", number);

        return map;
    }

}
