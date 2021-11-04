package com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.service;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dao.SalesTransactionMaintenanceMapper;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dto.SalesTransactionMaintenanceDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 *销售结算资金交易信息维护
 */
@Service("SalesTransactionMaintenance001Service")

public class SalesTransactionMaintenance001Service extends CommonExportService<SalesTransactionMaintenanceDto> {

    /**
     * 销售结算资金交易信息维护Mapper
     */
    @Autowired
    private SalesTransactionMaintenanceMapper salestransactionmaintenanceMapper;

    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionTemplate template;
    /**
     * 分页查询
     */
    public Page<SalesTransactionMaintenanceDto> selectForPage(int page,int length,Map<String, Object> pageParam)
    {
        return super.queryForPage(page,length,() -> salestransactionmaintenanceMapper.selectForPage(pageParam));
    }

    /**
     * 导出
     * @param params
     * @return
     * @throws Exception
     */
    public List<SalesTransactionMaintenanceDto> queryAllList(Map<String, Object> params) throws Exception{
        return salestransactionmaintenanceMapper.selectForPage(params);
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
                        StringUtils.equals(dataList.get(i).get(4), dataList.get(j).get(4))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"提供销售结算资金服务机构代码和销售结算资金交易流水号与"+"第"+(j+2)+"提供销售结算资金服务机构代码和销售结算资金交易流水号相同");
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
     * 导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     * @return 导入结果
     */
    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("提供销售结算资金服务机构代码");
            headList.add("产品登记编码");
            headList.add("销售结算资金账号");
            headList.add("销售结算资金账户名称");
            headList.add("销售结算资金交易流水号");
            headList.add("销售结算资金流动方向");
            headList.add("销售结算资金交易对手方账号");
            headList.add("销售结算资金交易对手方账户名称");
            headList.add("销售结算资金交易对手方开户行名称");
            headList.add("销售结算资金交易对手方账户开户行号");
            headList.add("销售结算资金交易币种");
            headList.add("销售结算资金交易金额");
            headList.add("销售结算资金交易日期");
            headList.add("销售结算资金交易时间");
            headList.add("销售结算资金交易附言");
            headList.add("备注");
            headList.add("数据状态");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<SalesTransactionMaintenanceDto> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult= convertToEntity(dataList, entities,userName,deptCode);
                if(checkResult.isEmpty()) {
                    for(SalesTransactionMaintenanceDto dto : entities){
                        salestransactionmaintenanceMapper.insert(dto);
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
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<SalesTransactionMaintenanceDto> entities,String userName,String deptCode) {

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
            List<String> message = new ArrayList<>();
            //提供销售结算资金服务机构代码
            if(list.get(0) == null || list.get(0).trim().equals("")){
                flag = false;
                message.add("提供销售结算资金服务机构代码不能为空");
            }
            //产品登记编码
            if(list.get(1) == null || list.get(1).trim().equals("")){
                flag = false;
                message.add("产品登记编码不能为空");
            }
            //销售结算资金账号
            if(list.get(2) == null || list.get(2).trim().equals("")){
                flag = false;
                message.add("销售结算资金账号不能为空");
            }
            //销售结算资金账户名称
            if(list.get(3) == null || list.get(3).trim().equals("")){
                flag = false;
                message.add("销售结算资金账户名称不能为空");
            }
            //销售结算资金交易流水号
            if(list.get(4) == null || list.get(4).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易流水号不能为空");
            }
            //销售结算资金流动方向
            if(list.get(5) == null || list.get(5).trim().equals("")){
                flag = false;
                message.add("销售结算资金流动方向不能为空");
            }
            //销售结算资金交易对手方账号
            if(list.get(6) == null || list.get(6).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易对手方账号不能为空");
            }
            //销售结算资金交易对手方账户名称
            if(list.get(7) == null || list.get(7).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易对手方账户名称不能为空");
            }
            //销售结算资金交易对手方开户行名称
            if(list.get(8) == null || list.get(8).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易对手方开户行名称不能为空");
            }
            //销售结算资金交易对手方账户开户行号
            if(list.get(9) == null || list.get(9).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易对手方账户开户行号不能为空");
            }
            //销售结算资金交易币种
            if(list.get(10) == null || list.get(10).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易币种不能为空");
            }
            //销售结算资金交易金额
            if(list.get(11) == null || list.get(11).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易金额不能为空");
            }else{
                if(!checkFigure(list.get(11))){
                    flag = false;
                    message.add("销售结算资金交易金额不是数字");
                }
            }
            //销售结算资金交易日期
            if(list.get(12) == null || list.get(12).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易日期不能为空");
            }else{
                if (!checkDate(list.get(12))) {
                    flag = false;
                    message.add("销售结算资金交易日期格式不正确");
                }
            }
            //销售结算资金交易时间
            if(list.get(13) == null || list.get(13).trim().equals("")){
                flag = false;
                message.add("销售结算资金交易时间不能为空");
            }
            //数据状态
            if(list.get(16) == null || list.get(16).trim().equals("")){
                flag = false;
                message.add("数据状态不能为空");
            }
            //主键校验
            if(flag){
                param.put("institutionCode",list.get(0));
                param.put("tradeNo",list.get(4));
                int result = salestransactionmaintenanceMapper.getRegistExcelList(param);
                if(result > 0 ){
                    flag = false;
                    message.add("存在和提供销售结算资金服务机构代码与销售结算资金交易流水号相同的数据");
                }
            }

            if(flag){
                SalesTransactionMaintenanceDto entity = new SalesTransactionMaintenanceDto();
                //提供销售结算资金服务机构代码
                entity.setInstitutionCode(list.get(0));
                //产品登记编码
                entity.setPrdRegistCode(list.get(1));
                //销售结算资金账号
                entity.setAccountNo(list.get(2));
                //销售结算资金账户名称
                entity.setAccountName(list.get(3));
                //销售结算资金交易流水号
                entity.setTradeNo(list.get(4));
                //销售结算资金流动方向
                entity.setTradePs(list.get(5));
                //销售结算资金交易对手方账号
                entity.setTradeCounterAccNo(list.get(6));
                //销售结算资金交易对手方账户名称
                entity.setTradeCounterAccName(list.get(7));
                //销售结算资金交易对手方开户行名称
                entity.setTradeCounterAccBankName(list.get(8));
                //销售结算资金交易对手方账户开户行号
                entity.setTradeCounterAccBankNo(list.get(9));
                //销售结算资金交易币种
                entity.setTradeCcy(list.get(10));
                //销售结算资金交易金额
                entity.setTradeAmt(new BigDecimal(list.get(11)));
                //估值日
                entity.setTradeVdate(DateUtil.parse(list.get(12)));
                //销售结算资金交易时间
                entity.setTradeTime(DateUtil.parse(list.get(13)));
                //销售结算资金交易附言
                if(StringUtils.isNotEmpty(list.get(14))){
                    entity.setPostscript(list.get(14));
                }
                //备注
                if(StringUtils.isNotEmpty(list.get(15))){
                    entity.setRemark(list.get(15));
                }
                //数据状态
                entity.setDataStatus(list.get(16));
                entity.setRegistSeqno(UUID.randomUUID().toString().replace("-","").toUpperCase());
                entity.setReceptDate(sysDate);
                entity.setOrgCode(CommonUtil.getOrgCode());
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
